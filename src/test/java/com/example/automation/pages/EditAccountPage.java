package com.example.automation.pages;

import com.example.automation.utils.Basetools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.example.automation.utils.Basetools.*;

/**
 * Classe EditAccountPage - Page Object Model pour la page de modification de compte
 */
public class EditAccountPage extends BasePage {

    /** Champ de saisie pour le prénom du compte */
    @FindBy(id = "account_first_name")
    public WebElement firstNameField;

    /** Champ de saisie pour le nom de famille du compte */
    @FindBy(id = "account_last_name")
    public WebElement lastNameField;

    /** Champ de saisie pour l'adresse email du compte */
    @FindBy(id = "account_email")
    public WebElement emailField;

    /** Champ de saisie pour le mot de passe actuel */
    @FindBy(id = "password_current")
    public WebElement currentPasswordField;

    /** Champ de saisie pour le nouveau mot de passe */
    @FindBy(id = "password_1")
    public WebElement newPasswordField;

    /** Champ de saisie pour la confirmation du nouveau mot de passe */
    @FindBy(id = "password_2")
    public WebElement confirmPasswordField;

    /** Bouton pour enregistrer les modifications du compte */
    @FindBy(xpath = "//input[@name='save_account_details']")
    public WebElement saveChangesButton;

    /** Élément affichant le message d'erreur */
    @FindBy(className = "woocommerce-error")
    public WebElement errorMessage;

    /**
     * Vérifie si la page de modification de compte est affichée$
     *
     * @return true si la page est affichée, false sinon
     */
    public boolean isPageDisplayed() {
        return checkPage("edit-account");
    }

    /**
     * Renseigne les champs prénom, nom, mot de passe actuel, nouveau mot de passe
     * et confirmation du nouveau mot de passe avec les valeurs fournies.
     *
     * @param firstName Le prénom à saisir
     * @param lastName Le nom de famille à saisir
     * @param currentPassword Le mot de passe actuel
     * @param newPassword Le nouveau mot de passe à définir
     * @param confirmPassword La confirmation du nouveau mot de passe
     */
    public void fillAccountDetails(String firstName, String lastName, String currentPassword, String newPassword, String confirmPassword) {
        fill(firstNameField, firstName);
        fill(lastNameField, lastName);
        fill(currentPasswordField, currentPassword);
        fill(newPasswordField, newPassword);
        fill(confirmPasswordField, confirmPassword);
    }

    /**
     * Clique sur le bouton pour enregistrer les modifications
     */
    public void clickSaveChanges() {
        Basetools.click(saveChangesButton);
    }

    /**
     * Vérifie si le message de succès de modification est affiché
     *
     * @return true si le message de succès est affiché, false sinon
     */
    public boolean checkSuccessMessage() {
        String expectedText = "Account details changed successfully";

        try {
            return getWait().until(d -> {
                String bodyText = d.findElement(By.tagName("body")).getText();
                return bodyText.contains(expectedText);
            });
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Vérifie si le message d'erreur relatif au mot de passe trop faible est affiché
     *
     * @return true si le message d'erreur est affiché, false sinon ou en cas de timeout
     */
    public boolean checkPasswordErrorMessage() {
        String expectedText = "Please enter a stronger password.";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            return wait.until(d -> {
                String bodyText = d.findElement(By.tagName("body")).getText();
                return bodyText.contains(expectedText);
            });
        } catch (Exception e) {
            // Timeout ou élément non trouvé
            return false;
        }
    }

    /**
     * Vérifie si un message d'erreur est affiché
     *
     * @return true si le message d'erreur est visible, false sinon
     */
    public boolean checkErrorMessage() {
        return isElementDisplayed(errorMessage);
    }
}