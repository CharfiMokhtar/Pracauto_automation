package com.example.automation.steps;

import com.example.automation.pages.HomePage;
import io.cucumber.java.en.*;

import static org.junit.Assert.assertTrue;

public class NewsletterSteps {

    HomePage homePage = new HomePage();

    @When("je saisis {string} dans le champ {string}")
    public void saisirEmail(String email, String champ) {
        homePage.saisirEmailNewsletter(email);
    }

    @When("je clique sur SUBSCRIBE")
    public void cliquerSubscribe() {
        homePage.cliquerSubscribe();
    }

    @Then("le message d'erreur s’affiche")
    public void verifierMessageErreur() {
        assertTrue(homePage.checkNewsletterErrorMessage());
    }
}