package com.example.automation.steps;

import com.example.automation.configuration.ConfigReader;
import com.example.automation.pages.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertTrue;

public class CommonSteps {

    BasePage basePage = new BasePage();
    public ConfigReader settings = new ConfigReader();
    String url = settings.getProperty("url");

    @Given("je suis sur la page {string}")
    public void openPage(String page){
        basePage.openPage(url + page);
        basePage.doNotConsent();
    }

    @Then("je suis redirigé vers la page {string}")
    public void checkPage(String page) {
        assertTrue(basePage.isDisplayed(page));
    }

    @And("je clique sur {string}")
    public void valideLaSaisie(String button) {
        basePage.clickButton(button);
    }
}
