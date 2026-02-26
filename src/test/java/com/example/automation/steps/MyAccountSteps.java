package com.example.automation.steps;

import com.example.automation.pages.MyAccountPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class MyAccountSteps {
    MyAccountPage myAccountPage = new MyAccountPage();

    @When("je saisis le login {string} et le mot de passe {string}")
    public void saisirIdentifiants(String login, String password) {
        myAccountPage.saisiIdentifiantsConnexion(login, password);
    }

    @And("je clique sur LOGIN")
    public void valideLaSaisie() {
        myAccountPage.clickButton("Login");
    }

    @And("je clique sur REGISTER")
    public void clickRegister() {
        myAccountPage.clickButton("Register");
    }

    @Then("le dashboard s'affiche")
    public void checkDashboard() {
        assertTrue(myAccountPage.isDashboardDisplayed());
    }

    @Then("un message d'erreur s'affiche")
    public void checkErreurConnexion() {
        assertTrue(myAccountPage.checkErreurConnexion());
    }

    @When("je clique sur le lien {string}")
    public void goToScreen(String title) {
        myAccountPage.goToScreen(title);
    }

    @Then("le pavé {string} s'affiche")
    public void checkSection(String sectionName) {
        assertTrue(myAccountPage.checkSection(sectionName));
    }

    @When("je saisis le mail {string} et le mot de passe {string} valide dans register")
    public void saisirIdentifiantsRegister(String mail, String password) {
        myAccountPage.saisiIdentifiantsRegisterValide(mail, password);
    }

    @When("je saisis le mail {string} et le mot de passe {string} invalide dans register")
    public void saisirIdentifiantsRegisterInvalide(String mail, String password) {
        myAccountPage.saisiIdentifiantsRegisterInvalide(mail, password);
    }

    @Then("le bouton register est désactivé")
    public void checkDisabledRegister() {
        assertTrue(myAccountPage.checkDisabledRegister());
    }

    @Then("j'accède à mes informations de compte")
    public void checkDetailsFields() {
        assertTrue(myAccountPage.checkTextAccountDetails());
    }

}
