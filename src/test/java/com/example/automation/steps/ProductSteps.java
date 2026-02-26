package com.example.automation.steps;

import com.example.automation.pages.ProductPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class ProductSteps {

    ProductPage productPage = new ProductPage();

    @And("la photo, la description, le prix et le stock sont affichés")
    public void checkProductDetails() {
        assertTrue(productPage.checkProductImage());
        assertTrue(productPage.checkProductDescription());
        assertTrue(productPage.checkProductPrice());
    }

    @When("je renseigne la quantité {int}")
    public void enterQuantity(int quantity) {
        productPage.enterQuantity(quantity);
    }

    @And("je clique sur ADD TO BASKET")
    public void addToBasket() {
        productPage.addToBasket();
    }

    @Then("le message {string} s’affiche")
    public void verifySuccessMessage(String expectedMessage) {
        assertTrue(productPage.checkSuccessMessage());
    }

    @And("un lien View Basket apparaît")
    public void verifyViewBasketLink() {
        assertTrue(productPage.checkViewBasketLink());
    }
}
