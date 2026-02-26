package com.example.automation.pages;

import com.example.automation.utils.Basetools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.Arrays;
import java.util.List;

import static com.example.automation.utils.Basetools.*;

/**
 * Classe MyAccountPage - Page Object Model pour la page Mon Compte
 */
public class MyAccountPage extends BasePage {

    /** Champ de saisie pour le nom d'utilisateur lors de la connexion */
    @FindBy(id = "username")
    private WebElement usernameField;

    /** Champ de saisie pour le mot de passe lors de la connexion */
    @FindBy(id = "password")
    private WebElement passwordField;

    /** Conteneur du contenu principal de la page Mon Compte */
    @FindBy(xpath = "//div[@class = 'woocommerce-MyAccount-content']")
    private WebElement myAccountContent;

    /** Élément affichant un message d'erreur */
    @FindBy(xpath = "//*[@class = 'woocommerce-error']")
    private WebElement errorMessage;

    /** Champ de saisie pour l'email lors de l'inscription */
    @FindBy(id = "reg_email")
    private WebElement mailField;

    /** Champ de saisie pour le mot de passe lors de l'inscription */
    @FindBy(id = "reg_password")
    private WebElement regPasswordField;

    /** Bouton pour accéder aux détails du compte */
    @FindBy(xpath = "//a[contains(@href,'edit-account') and text()='Account Details']")
    private WebElement detailsButton;

    /** Bouton d'inscription désactivé (utilisé pour vérifier que le formulaire est invalide) */
    @FindBy(xpath = "//input[@name='register' and @disabled]")
    private WebElement disabledRegisterButton;

    /**
     * Vérifie si la page My Account est affichée
     *
     * @return true si la page Mon Compte est affichée, false sinon
     */
    public boolean isPageDisplayed() {
        return Basetools.checkPage("my-account");
    }

    /**
     * Vérifie si le Dashboard est visible
     *
     * @return true si le tableau de bord est affiché, false sinon
     */
    public boolean isDashboardDisplayed() {
        return isElementDisplayed(myAccountContent);
    }

    /**
     * Saisit les identifiants de connexion
     *
     * @param login Le nom d'utilisateur ou email pour la connexion
     * @param password Le mot de passe de l'utilisateur
     */
    public void saisiIdentifiantsConnexion(String login, String password) {
        fill(usernameField, login);
        fill(passwordField, password);
    }

    /**
     * Vérifie si une erreur de connexion est affichée
     *
     * @return true si le message d'erreur est visible, false sinon
     */
    public boolean checkErreurConnexion() {
        return isElementDisplayed(errorMessage);
    }

    /**
     * Navigue vers un écran/section spécifique de My Account
     *
     * @param title Le titre de la section à naviguer (ex: "Orders", "Downloads", "Addresses")
     */
    public void goToScreen(String title) {
        click(
                driver.findElement(By.xpath("//a[text() = '" + title + "']"))
        );
    }

    /**
     * Vérifie si un pavé spécifique est affiché
     *
     * @param title Le titre de la section à vérifier
     * @return true si la section est affichée, false sinon
     */
    public boolean checkSection(String title) {
        return isElementDisplayed(
                driver.findElement(By.xpath("//h2[text() = '" + title + "']"))
        );
    }

    /**
     * Saisit les identifiants d'inscription valides
     *
     * @param mail L'adresse email pour l'inscription (un timestamp sera ajouté)
     * @param password Le mot de passe pour l'inscription
     */
    public void saisiIdentifiantsRegisterValide(String mail, String password) {
        fill(mailField, mail+System.currentTimeMillis());
        fillWithJs(regPasswordField, password);
    }

    /**
     * Saisit les identifiants d'inscription invalides
     *
     * @param mail L'adresse email pour l'inscription (un timestamp sera ajouté)
     * @param password Le mot de passe invalide à saisir
     */
    public void saisiIdentifiantsRegisterInvalide(String mail, String password) {
        fill(mailField, mail+System.currentTimeMillis());
        clearInput(regPasswordField);
        fillHumanLike(regPasswordField, password);
    }

    /**
     * Clique sur le lien vers les détails du compte
     */
    public void clickOnDetails() {
        click(detailsButton);
    }

    /**
     * Récupère l'URL actuelle de la page
     *
     * @return L'URL complète de la page actuelle
     */
    public String getURL() {
        return driver.getCurrentUrl();
    }

    /**
     * Vérifie que les détails du compte sont affichés

     * @return true si tous les champs sont affichés, false sinon
     */
    public boolean checkTextAccountDetails() {
        List<String> expectedTexts = Arrays.asList(
                "First name",
                "Last name",
                "Email address"
        );

        return getWait().until(d -> {
            String bodyText = d.findElement(By.tagName("body")).getText();
            return expectedTexts.stream().allMatch(bodyText::contains);
        });
    }

    /**
     * Vérifie si le bouton d'inscription est désactivé
     *
     * @return true si le bouton d'inscription désactivé est visible, false sinon
     */
    public boolean checkDisabledRegister() {
        return isElementDisplayed(disabledRegisterButton);
    }
}
