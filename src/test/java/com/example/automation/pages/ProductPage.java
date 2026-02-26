package com.example.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.example.automation.utils.Basetools.*;

/**
 * Classe ProductPage - Page Object Model pour la page de détail produit
 */
public class ProductPage extends BasePage{

    /** Image du produit affichée sur la page de détail */
    @FindBy(xpath = "//div[contains(@class, 'product')]//img")
    private WebElement productImage;

    /** Description détaillée du produit */
    @FindBy(xpath = "//div[contains(@class, 'description')]")
    private WebElement productDescription;

    /** Prix du produit affiché sur la page */
    @FindBy(xpath = "//p[contains(@class, 'price')]")
    private WebElement productPrice;

    /** Champ de saisie pour définir la quantité du produit */
    @FindBy(name = "quantity")
    private WebElement quantityInput;

    /** Bouton pour ajouter le produit au panier */
    @FindBy(xpath = "//button[contains(text(), 'Add to basket')]")
    private WebElement addToBasketButton;

    /** Message de succès affiché après l'ajout au panier */
    @FindBy(xpath = "//div[contains(@class, 'woocommerce-message')]")
    private WebElement successMessage;

    /** Titre du produit affiché en haut de la page */
    @FindBy(xpath = "//h1[contains(@class, 'product_title')]")
    private WebElement productTitle;

    /** Lien pour consulter le panier après ajout du produit */
    @FindBy(xpath = "//a[contains(text(), 'View Basket')]")
    private WebElement viewBasketLink;

    /**
     * Vérifie si l'image du produit est affichée
     *
     * @return true si l'image du produit est visible, false sinon
     */
    public boolean checkProductImage() {
        return isElementDisplayed(productImage);
    }

    /**
     * Vérifie si la description du produit est affichée
     *
     * @return true si la description du produit est visible, false sinon
     */
    public boolean checkProductDescription() {
        return isElementDisplayed(productDescription);
    }

    /**
     * Vérifie si le prix du produit est affichée
     *
     * @return true si le prix du produit est visible, false sinon
     */
    public boolean checkProductPrice() {
        return isElementDisplayed(productPrice);
    }

    /**
     * Saisit la quantité du produit à ajouter au panier
     *
     * @param quantity La quantité du produit à ajouter (nombre entier)
     */
    public void enterQuantity(int quantity) {
        clearAndFill(quantityInput, String.valueOf(quantity));
    }

    /**
     * Ajoute le produit au panier
     */
    public void addToBasket() {
        click(addToBasketButton);
    }

    /**
     * Vérifie si le message de succès d'ajout au panier est affiché
     *
     * @return true si le message de succès corresponde à la valeur attendue, false sinon
     */
    public boolean checkSuccessMessage() {
        String message = successMessage.getText().replace("VIEW BASKET\n", "");
        String regex = ".*" + productTitle.getText() + ".*added to your basket.*";
        return message.matches(regex);
    }

    /**
     * Vérifie si le lien "View Basket" est affiché
     *
     * @return true si le lien "View Basket" est visible, false sinon
     */
    public boolean checkViewBasketLink() {
        return isElementDisplayed(viewBasketLink);
    }
}
