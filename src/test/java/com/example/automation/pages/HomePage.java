package com.example.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.example.automation.utils.Basetools.*;

/**
 * Classe HomePage - Page Object Model pour la page d'accueil
 */
public class HomePage extends BasePage {

    /** Logo du site utilisé comme lien retour à l'accueil */
    @FindBy(id = "site-logo")
    WebElement siteLogo;

    /** Icône du panier dans l'en-tête */
    @FindBy(id = "wpmenucartli")
    WebElement basketIcon;

    /** Champ d'entrée email pour la newsletter */
    @FindBy(xpath = "//h4[text() = 'Subscribe Here']//following::input[@type='email']")
    WebElement newLetterInput;

    /** Bouton pour soumettre l'inscription à la newsletter */
    @FindBy(xpath = "//h4[text() = 'Subscribe Here']//following::input[@type='submit']")
    WebElement newLetterButton;

    /** Champ de saisie email pour la newsletter (alternative) */
    @FindBy(xpath = "//input[@name='EMAIL']")
    private WebElement newsletterField;

    /** Bouton de souscription à la newsletter */
    @FindBy(css = "input[value='Subscribe']")
    private WebElement subscribeButton;

    /** Élément affichant le message d'erreur pour la newsletter */
    @FindBy(css = ".woocommerce-error")
    private WebElement newsletterError;

    /**
     * Vérifie si la page accueil est affichée avec l'URL exact spécifiée
     *
     * @param url L'URL exacte à vérifier
     * @return true si la page avec l'URL spécifiée est affichée, false sinon
     */
    public boolean isDisplayed(String url) {
        return checkExactPage(url);
    }

    /**
     * Navigue vers un espace spécifié par son nom
     *
     * @param space Le nom de l'espace ou section à naviguer (ex: "Shop", "Account")
     */
    public void goToSpace(String space) {
        click(
                driver.findElement(By.xpath("//a[text() = '" + space + "']"))
        );
    }

    /**
     * Clique sur le logo du site
     */
    public void clickCompanyLogo() {
        click(siteLogo);
    }

    /**
     * Clique sur l'icône du panier
     */
    public void clickBasketIcon() {
        click(basketIcon);
    }

    /**
     * Vérifie si le champ de saisie de la newsletter est visible
     *
     * @return true si l'input newsletter est affiché, false sinon
     */
    public boolean checkNewsletterSubscribeInput() {
        return isElementDisplayed(newLetterInput);
    }

    /**
     * Vérifie si le bouton de souscription à la newsletter est visible
     *
     * @return true si le bouton Subscribe est affiché, false sinon
     */
    public boolean checkNewsletterSubscribeButton() {
        return isElementDisplayed(newLetterButton);
    }

    /**
     * Saisit une adresse email dans le champ de newsletter
     *
     * @param email L'adresse email à saisir
     */
    public void saisirEmailNewsletter(String email) {
        newsletterField.clear();
        newsletterField.sendKeys(email);
    }

    /**
     * Clique sur le bouton de souscription à la newsletter
     */
    public void cliquerSubscribe() {
        click(subscribeButton);
    }

    /**
     * Vérifie si le champ de newsletter est visible
     *
     * @return true si le champ newsletter est affiché, false sinon
     */
    public boolean newsletterEstVisible() {
        return newsletterField.isDisplayed();
    }

    /**
     * Récupère le message d'erreur de la newsletter
     *
     * @return Le texte du message d'erreur affichée
     */
    public String getMessageErreurNewsletter() {
        return newsletterError.getText();
    }

    /**
     * Vérifie si un message d'erreur de validation de la newsletter est affiché
     *
     * @return true si un message de validation est présent, false sinon
     */
    public boolean checkNewsletterErrorMessage() {
        String message = newsletterField.getAttribute("validationMessage");
        return message != null && !message.isEmpty();
    }

}