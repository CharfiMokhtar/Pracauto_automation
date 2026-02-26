package com.example.automation.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Fabrique de WebDriver.
 * Gère l'instanciation et la fermeture du driver Selenium en fonction
 * des paramètres système fournis (navigateur, grid, headless).
 */
public class DriverFactory {

    /** Instance unique du WebDriver partagée entre tous les tests. */
    private static WebDriver driver;

    /**
     * Retourne l'instance du WebDriver, en la créant si elle n'existe pas encore.
     * Le driver est configuré à partir des propriétés système suivantes :
     *  - browser : navigateur cible (CHROME, EDGE, RANDOM) — défaut : EDGE
     *  - urlGrid : URL de base du Selenium Grid — si vide, exécution en local
     *  - headless : mode sans interface graphique (true/false) — défaut : false
     *
     * @return l'instance WebDriver prête à l'emploi
     */
    public static WebDriver getDriver() {
        if (driver == null) {

            // Récupération des paramètres système
            String browser = System.getProperty("browser", "CHROME");
            String urlGrid = System.getProperty("urlGrid");
            boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

            // Si RANDOM, le navigateur est tiré au sort entre Chrome et Edge
            if (browser.equals("RANDOM")) {
                browser = Math.random() < 0.5 ? "CHROME" : "EDGE";
            }

            if (browser.equals("CHROME")) {
                ChromeOptions options = buildChromeOptions(headless);
                // Instanciation distante (Grid) ou locale selon urlGrid
                driver = (urlGrid != null && !urlGrid.isBlank())
                        ? new RemoteWebDriver(toURL(urlGrid), options)
                        : new ChromeDriver(options);
            } else {
                EdgeOptions options = buildEdgeOptions(headless);
                // Instanciation distante (Grid) ou locale selon urlGrid
                driver = (urlGrid != null && !urlGrid.isBlank())
                        ? new RemoteWebDriver(toURL(urlGrid), options)
                        : new EdgeDriver(options);
            }

            // En mode non-headless, on affiche la fenêtre du navigateur en plein écran
            if (!headless) {
                driver.manage().window().maximize();
            }

            // Attente implicite globale pour la recherche des éléments
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
        return driver;
    }

    /**
     * Convertit une URL String en objet URL en ajoutant le suffixe Selenium Grid.
     *
     * @param url l'URL de base du Selenium Grid (ex: http://192.168.1.190:4449)
     * @return l'URL complète pointant vers le hub Selenium
     * @throws RuntimeException si l'URL est malformée
     */
    private static URL toURL(String url) {
        try {
            return new URL(url + "/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL de la grid invalide : " + url, e);
        }
    }

    /**
     * Construit les options Chrome avec les préférences communes
     *
     * @param headless true pour activer le mode sans interface graphique
     * @return les options Chrome configurées
     */
    private static ChromeOptions buildChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("prefs", buildCommonPrefs());
        options.addArguments("--incognito");

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        return options;
    }

    /**
     * Construit les options Edge avec les préférences communes
     *
     * @param headless true pour activer le mode sans interface graphique
     * @return les options Edge configurées
     */
    private static EdgeOptions buildEdgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();

        options.setExperimentalOption("prefs", buildCommonPrefs());
        options.addArguments("--inprivate");

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        return options;
    }

    /**
     * Construit les préférences communes à tous les navigateurs :
     * désactivation des notifications, du gestionnaire de mots de passe et des popups.
     *
     * @return la map de préférences navigateur
     */
    private static Map<String, Object> buildCommonPrefs() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_settings.popups", 0);
        return prefs;
    }

    /**
     * Ferme le navigateur et réinitialise l'instance du driver.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
