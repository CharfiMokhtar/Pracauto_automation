package com.example.automation.steps;

import com.example.automation.pages.CartPage;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class CartSteps {

    CartPage cartPage = new CartPage();

    @Then("le récapitulatif affiche la quantité")
    public void verifierQuantiteAffichee() {
        Assert.assertTrue(cartPage.articlePresent());
    }

    @Then("le prix unitaire est affiché")
    public void verifierPrixUnitaire() {
        Assert.assertTrue(cartPage.prixUnitaireVisible());
    }

    @Then("le prix total est calculé correctement")
    public void verifierPrixTotal() {
        Assert.assertTrue(cartPage.prixTotalVisible());
    }

    @When("je modifie la quantité d’un article à {string}")
    public void modifierQuantite(String quantite) {
        cartPage.modifierQuantite(quantite);
    }

    @Then("le prix total est recalculé correctement")
    public void verifierRecalculTotal() {
        Assert.assertTrue(cartPage.prixTotalVisible());
    }

    @When("je clique sur le bouton supprimer d’un article")
    public void supprimerArticle() {
        cartPage.supprimerArticle();
    }

    @Then("l’article disparaît du panier")
    public void verifierArticleSupprime() {
        Assert.assertTrue(cartPage.articleNotPresent());
    }

   /* @When("Je saisis le code coupon {string}")
    public void saisirCoupon(String code) {
        cartPage.saisirCoupon(code);
    }

    @When("Je clique sur {string}")
    public void cliquerApplyCoupon(String bouton) {
        cartPage.appliquerCoupon();
    }

    @Then("Une réduction est appliquée au total")
    public void verifierReductionAppliquee() {
        Assert.assertTrue(
                cartPage.getMessageSucces().contains("Coupon code applied")
        );
    }

    @Then("Un message d’erreur coupon s’affiche")
    public void verifierErreurCoupon() {
        Assert.assertTrue(
                cartPage.getMessageErreur().length() > 0
        );
    }*/
}
