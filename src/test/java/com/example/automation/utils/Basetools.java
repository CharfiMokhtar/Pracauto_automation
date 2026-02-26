package com.example.automation.utils;

import com.example.automation.configuration.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class Basetools {

    public static final Logger log = LoggerFactory.getLogger(Basetools.class);
    private static final int TIMEOUT = 10;
    private static final int POLLING = 500;

    // ===================== WAIT =====================

    public static WebDriverWait getWait() {
        return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(TIMEOUT));
    }

    // ===================== CLIC =====================

    /**
     * Tente un clic normal, bascule sur JS si intercepté
     */
    public static void click(WebElement e) {
        log.info("Clic sur l'element : {}", e);
        try {
            getWait().until(ExpectedConditions.elementToBeClickable(e)).click();
        } catch (ElementClickInterceptedException ex) {
            log.warn("Clic intercepte, tentative via JavaScript : {}", e);
            JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
            js.executeScript("arguments[0].click()", e);
        }
    }

    // ===================== SAISIE =====================

    /**
     * Efface le champ et saisit la valeur
     */
    public static void clearInput(WebElement e) {
        getWait().until(ExpectedConditions.visibilityOf(e));
        e.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }

    /**
     * Efface le champ et saisit la valeur
     */
    public static void clearAndFill(WebElement e, String value) {
        log.trace("Saisie de [{}] dans l'element : {}", value, e);
        getWait().until(ExpectedConditions.visibilityOf(e));
        e.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        e.sendKeys(value);
    }

    /**
     * Saisit la valeur sans effacer le champ
     */
    public static void fill(WebElement e, String value) {
        log.trace("Saisie de [{}] dans l'element : {}", value, e);
        getWait().until(ExpectedConditions.visibilityOf(e));
        e.sendKeys(value);
    }

    /**
     * Saisit une valeur dans un champ via JavaScript
     */
    public static void fillWithJs(WebElement e, String value) {
        log.trace("Saisie JS de [{}] dans l'element : {}", value, e);
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        js.executeScript("arguments[0].value = arguments[1]", e, value);
    }

    /**
     * Remplit un champ en simulant une saisie clavier via Actions
     */
    public static void fillHumanLike(WebElement e, String value) {
        log.trace("Saisie humaine de [{}] dans l'element : {}", value, e);
        getWait().until(ExpectedConditions.visibilityOf(e));
        e.clear();
        new Actions(DriverFactory.getDriver())
                .click(e)
                .sendKeys(value)
                .perform();
    }

    // ===================== VISIBILITÉ =====================

    /**
     * Attend que les éléments soient visible
     */
    public static List<WebElement> waitForElementsVisible(By locator) {
        log.trace("Attente visibilite des elements : {}", locator);
        FluentWait<WebDriver> wait = new FluentWait<>(DriverFactory.getDriver())
                .withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofMillis(POLLING))
                .ignoring(StaleElementReferenceException.class);

        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /**
     * Attend que l'élément soit visible
     */
    public static void waitVisible(WebElement e) {
        log.trace("Attente visibilite de l'élément : {}", e);
        getWait().until(ExpectedConditions.visibilityOf(e));
    }

    /**
     * Attend que l'élément soit invisible
     */
    public static void waitInvisible(WebElement e) {
        log.trace("Attente invisibilite de l'élément : {}", e);
        getWait().until(ExpectedConditions.invisibilityOf(e));
    }

    /**
     * Vérifie si l'élément est affiché sans lever d'exception
     */
    public static boolean isElementDisplayed(WebElement e) {
        log.trace("Vérification de la presence de l'element : {}", e);
        try {
            return e.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException ex) {
            return false;
        }
    }

    public static boolean checkPage(String pageName) {
        return getWait().until(ExpectedConditions.urlContains(pageName));
    }

    public static boolean checkExactPage(String pageName) {
        return getWait().until(ExpectedConditions.urlToBe(pageName));
    }

    // ===================== NAVIGATION =====================

    /**
     * Navigue vers une URL
     */
    public static void navigateTo(String url) {
        log.trace("Navigation vers : {}", url);
        DriverFactory.getDriver().get(url);
    }

    /**
     * Scrolle vers un élément
     */
    public static void scrollTo(WebElement e) {
        log.trace("Scroll vers l'element : {}", e);
        new Actions(DriverFactory.getDriver())
                .scrollToElement(e)
                .perform();
    }
}