package com.example.automation.pages;

import com.example.automation.configuration.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.example.automation.utils.Basetools.*;

/**
 * Classe BasePage - Classe de base pour tous les pages de l'application
 *
 * Cette classe contient les éléments et méthodes communes à toutes les pages
 * de l'application.$
 */
public class BasePage {

    /** Instance du WebDriver utilisée pour contrôler le navigateur */
    protected WebDriver driver;

    /** Bouton pour refuser le consentement aux cookies */
    @FindBy(xpath = "//button[@aria-label=\"Do not consent\"]")
    WebElement doNotConsent;

    /**
     * Constructeur de BasePage
     *
     * Initialise le WebDriver à partir de la DriverFactory et effectue
     * l'initialisation des éléments @FindBy via PageFactory.
     */
    public BasePage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    /**
     * Refuse le consentement aux cookies
     *
     * Clique sur le bouton "Do not consent" si celui-ci est affiché à l'écran.
     * Cette méthode est généralement appelée lors de la première visite sur le site.
     */
    public void doNotConsent(){
        if(isElementDisplayed(doNotConsent)) {
            click(doNotConsent);
        }
    }

    /**
     * Ouvre une page web avec l'URL spécifiée
     *
     * @param url L'URL complète de la page à ouvrir
     */
    public void openPage(String url) {
        navigateTo(url);
    }

    /**
     * Vérifie si une page spécifiée est affichée
     *
     * @param pageName Le nom de la page à vérifier
     * @return true si la page est affichée, false sinon
     */
    public boolean isDisplayed(String pageName){
        return checkPage(pageName);
    }

    /**
     * Clique sur un bouton d'envoi identifié par sa valeur
     *
     * @param buttonName La valeur du bouton submit à cliquer
     */
    public void clickButton(String buttonName) {
        click(
                driver.findElement(By.xpath("//input[@type='submit' and @value='" + buttonName + "']"))
        );
    }
}
