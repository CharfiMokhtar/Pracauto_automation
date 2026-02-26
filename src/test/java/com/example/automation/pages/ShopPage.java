package com.example.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.example.automation.utils.Basetools.*;

/**
 * Classe ShopPage - Page Object Model pour la page de boutique
 */
public class ShopPage extends BasePage{

    /** Bouton "Add to basket" du premier produit de la liste */
    @FindBy(xpath = "(//a[text() = 'Add to basket'])[1]")
    private WebElement firstAddToBasketButton;

    /** Image du premier produit affichée dans la liste */
    @FindBy(xpath = "(//li[contains(@class, 'product')]//img)[1]")
    private WebElement firstProductImage;

    /** Titre du premier produit affiché dans la liste */
    @FindBy(xpath = "(//li[contains(@class, 'product')]//h3)[1]")
    private WebElement firstProductTitle;

    /** Prix du premier produit affiché dans la liste */
    @FindBy(xpath = "(//li[contains(@class, 'product')]//span[contains(@class, 'price')])[1]")
    private WebElement firstProductPrice;

    /** Liste de tous les conteneurs de produits affichés sur la page */
    @FindBy(xpath = "//li[contains(@class, 'product')]")
    private List<WebElement> products;

    /** Champ de saisie pour le prix minimum du filtre */
    @FindBy(id = "min_price")
    private WebElement minPrice;

    /** Champ de saisie pour le prix maximum du filtre */
    @FindBy(id = "max_price")
    private WebElement maxPrice;

    /** Bouton pour appliquer les filtres de prix */
    @FindBy(xpath = "//button[@type='submit' and text() = 'Filter']")
    private WebElement filterButton;

    /** Lien pour accéder au panier après l'ajout d'un produit */
    @FindBy(xpath = "//a[text() = 'View Basket']")
    private WebElement viewBasketLink;

    /**
     * Ajoute le premier produit de la liste au panier
     */
    public void addFirstProductToBasket() {
        click(firstAddToBasketButton);
    }

    /**
     * Filtre les produits par catégorie/thème
     *
     * @param filter Le nom de la catégorie/thème à appliquer comme filtre
     */
    public void filterTheme(String filter) {
        click(
            driver.findElement(By.xpath("//li[contains(@class,'cat-item')]//a[text() = '" + filter + "']"))
        );
    }

    /**
     * Vérifie que tous les produits affichés appartiennent à la catégorie filtrée
     *
     * @param filter Le nom de la catégorie/thème à vérifier
     * @return true si tous les produits appartiennent à la catégorie, false sinon
     */
    public boolean checkProductsThemes(String filter) {
        for(WebElement element : products) {
            if(!element.getAttribute("class").contains("product_cat-" + filter.toLowerCase())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Filtre les produits par plage de prix
     *
     * @param min Le prix minimum de la plage (valeur entière)
     * @param max Le prix maximum de la plage (valeur entière)
     */
    public void filterPrice(int min, int max) {
        fillWithJs(minPrice, String.valueOf(min));
        fillWithJs(maxPrice, String.valueOf(max));
        click(filterButton);
    }

    /**
     * Vérifie que tous les produits affichés sont dans la plage de prix spécifiée
     *
     * @param min Le prix minimum attendu (valeur entière)
     * @param max Le prix maximum attendu (valeur entière)
     * @return true si tous les produits sont dans la plage, false sinon
     */
    public boolean checkProductsPrice(int min, int max) {

        List<WebElement> prices = waitForElementsVisible(By.xpath("//span[@class = 'price']/span | //span[@class = 'price']/ins/span"));

        for (WebElement element : prices) {
            int price = (int) Double.parseDouble(element.getText().replaceAll("[^0-9.]", ""));
            if (price < min || price > max) {
                return false;
            }
        }
        return true;
    }

    /**
     * Clique sur l'image du premier produit
     *
     * Accède généralement à la page de détail du premier produit.
     */
    public void clickOnLinkPhoto() {
        click(firstProductImage);
    }

    /**
     * Clique sur le titre du premier produit
     *
     * Accède généralement à la page de détail du premier produit.
     */
    public void clickOnLinkTitle() {
        click(firstProductTitle);
    }

    /**
     * Clique sur le prix du premier produit
     *
     * Peut accéder à la page de détail du premier produit ou afficher des informations.
     */
    public void clickOnLinkPrice() {
        click(firstProductPrice);
    }

    /**
     * Clique sur le titre d'un produit spécifié
     *
     * @param productName Le nom exact du produit dont on souhaite accéder à la page de détail
     */
    public void clickOnLinkTitle(String productName) {
        click(
                driver.findElement(By.xpath("//h3[text() = '" + productName + "']"))
        );
    }

    /**
     * Ajoute un produit spécifié au panier
     *
     * @param productName Le nom exact du produit à ajouter au panier
     */
    public void addProductToBasket(String productName) {
        click(
            driver.findElement(By.xpath("//h3[text() = '" + productName + "']/ancestor::li//a[text() = 'Add to basket']"))
        );
    }

    /**
     * Vérifie si le lien "View Basket" est affiché pour un produit spécifique
     *
     * @param productName Le nom exact du produit pour lequel vérifier le lien
     * @return true si le lien "View Basket" est visible pour ce produit, false sinon
     */
    public boolean checkViewBasketLink(String productName) {
        return isElementDisplayed(
            driver.findElement(By.xpath("//h3[text() = '" + productName + "']/ancestor::li//a[text() = 'View Basket']"))
        );
    }

    /**
     * Clique sur le premier lien "View Basket"
     */
    public void clickOnViewBasket() {
        click(viewBasketLink);
    }
}
