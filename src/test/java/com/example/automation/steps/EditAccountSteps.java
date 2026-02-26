package com.example.automation.steps;

import com.example.automation.pages.EditAccountPage;
import com.example.automation.pages.MyAccountPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertTrue;

public class EditAccountSteps {
    MyAccountPage myAccountPage = new MyAccountPage();
    EditAccountPage editAccountPage = new EditAccountPage();

    @When("je saisis mon prénom {string}, nom {string}, ancien password {string} et un nouveau password {string} que je confirme correctement avec {string}")
    public void fillAccountFields(String firstName, String lastName, String currentPassword, String newPassword, String confirmPassword) {
        editAccountPage.fillAccountDetails(firstName, lastName, currentPassword, newPassword, confirmPassword);
    }

    @When("je saisis mon prénom {string}, nom {string}, ancien password {string} un nouveau password {string} que je confirme incorrectement avec {string}")
    public void fillNoMatchNewPassword(String firstName, String lastName, String currentPassword, String newPassword, String confirmPassword) {
        editAccountPage.fillAccountDetails(firstName, lastName, currentPassword, newPassword, confirmPassword);
    }
    @When("je saisis mon prénom {string}, nom {string}, ancien password erroné {string} un nouveau password {string} que je confirme correctement avec {string}")
    public void fillCurrentPasswordNewPassword(String firstName, String lastName, String currentPassword, String newPassword, String confirmPassword) {
        editAccountPage.fillAccountDetails(firstName, lastName, currentPassword, newPassword, confirmPassword);
    }

    @Then("le message d’erreur “New passwords do not match.” apparaît")
    public void checkNoMatchErrorMessage() {
        assertTrue(editAccountPage.checkErrorMessage());
    }

    @Then("le mot de passe reste inchangé et le message d’erreur “Your current password is incorrect.” apparaît")
    public void checkCurrentPasswordErrorMessage() {
        assertTrue(editAccountPage.checkErrorMessage());
    }

    @And ("je clique sur SAVE CHANGES")
    public void clickSaveChanges() {
        editAccountPage.clickButton("Save changes");
    }

    @Then ("je suis de retour sur la page My account et  le message “Account details changed successfully” apparaît")
    public void checkSuccessMessage() {
        assertTrue(myAccountPage.isPageDisplayed());
        assertTrue(editAccountPage.checkSuccessMessage());
    }

    @Then ("je suis toujours sur la page Edit account et le message Please enter a stronger password. est présent")
    public void checkErrorMessage() {
        assertTrue(editAccountPage.isPageDisplayed());
        assertTrue(editAccountPage.checkPasswordErrorMessage());
    }
}