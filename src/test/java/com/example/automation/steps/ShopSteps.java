package com.example.automation.steps;

import com.example.automation.pages.ShopPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class ShopSteps {

    ShopPage shopPage = new ShopPage();

    @And("j'ajoute un article au panier")
    public void addFirstProductToBasket() {
        shopPage.addFirstProductToBasket();
    }

    @When("je sélectionne le filtre de thème {string}")
    public void filterTheme(String filter) {
        shopPage.filterTheme(filter);
    }

    @Then("seul les articles du thème {string} s’affichent")
    public void checkThemes(String filter) {
        assertTrue(shopPage.checkProductsThemes(filter));
    }

    @When("je filtre les prix entre {int} et {int}")
    public void filterPrice(int min, int max) {
        shopPage.filterPrice(min, max);
    }

     @Then("seul les articles dont le prix est compris entre {int} et {int} s’affichent")
    public void checkPrice(int min, int max) {
         assertTrue(shopPage.checkProductsPrice(min, max));
    }

    @When("je clique sur le {string}")
    public void clickOnButton(String idButton) {
        switch (idButton) {
            case "lienPhoto" : shopPage.clickOnLinkPhoto(); break;
            case "lienLibelle" : shopPage.clickOnLinkTitle(); break;
            case "lienPrix" : shopPage.clickOnLinkPrice(); break;
        }
    }

    @And("je clique sur le \"lienLibelle\" de l'article {string}")
    public void clickOnLink(String productName) {
        shopPage.clickOnLinkTitle(productName);
    }

    @And("j'ajoute l'article {string} au panier")
    public void addProductToBasket(String productName) {
        shopPage.addProductToBasket(productName);
    }

    @Then("le lien View Basket est affiché sous l'article {string}")
    public void checkViewBasketLink(String productName) {
        assertTrue(shopPage.checkViewBasketLink(productName));
    }

    @And("je clique sur View Basket")
    public void clickOnViewBasket() {
        shopPage.clickOnViewBasket();
    }
}
