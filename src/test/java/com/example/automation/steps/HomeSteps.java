package com.example.automation.steps;

import com.example.automation.configuration.ConfigReader;
import com.example.automation.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class HomeSteps {
    HomePage homePage = new HomePage();
    public ConfigReader settings = new ConfigReader();
    String url = settings.getProperty("url");

    @Given("je suis sur la page d'accueil")
    public void openHomePage() {
        homePage.openPage(url);
        homePage.doNotConsent();
    }

    @And("j'accède à l'espace {string}")
    public void openSpace(String space) {
        homePage.goToSpace(space);
    }

    @When("je clique sur le logo du site")
    public void clickCompanyLogo() {
        homePage.clickCompanyLogo();
    }

    @Then("je suis redirigé vers la page d'accueil")
    public void checkHomePage() {
        assertTrue(homePage.isDisplayed(url));
    }

    @When("je clique sur l’icone panier")
    public void clickBasketLogo() {
        homePage.clickBasketIcon();
    }

    @Then("Le champ {string} est visible")
    public void checkNewsletterSubscribeInput(String basket) {
        assertTrue(homePage.checkNewsletterSubscribeInput());
    }

    @Then("Le bouton {string} est visible")
    public void checkNewsletterSubscribeButton(String basket) {
        assertTrue(homePage.checkNewsletterSubscribeButton());
    }
}
