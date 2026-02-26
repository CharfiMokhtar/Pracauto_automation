package com.example.automation.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static com.example.automation.utils.Basetools.*;

/**
 * Classe CartPage - Page Object Model pour la page du panier
 *
 */
public class CartPage extends BasePage {

    /** Champ de saisie pour la quantité d'un article dans le panier */
    @FindBy(css = "input.qty")
    private WebElement quantityField;

    /** Élément affichant le prix unitaire d'un produit */
    @FindBy(css = ".product-price")
    private WebElement unitPrice;

    /** Élément affichant le sous-total pour un article */
    @FindBy(css = ".product-subtotal")
    private WebElement subtotal;

    /** Bouton pour supprimer un article du panier */
    @FindBy(css = ".remove")
    private WebElement removeButton;

    /** Champ de saisie pour entrer un code coupon */
    @FindBy(id = "coupon_code")
    private WebElement couponField;

    /** Bouton pour appliquer un coupon de réduction */
    @FindBy(name = "apply_coupon")
    private WebElement applyCouponBtn;

    /** Message de succès affiché après une action réussie */
    @FindBy(css = ".woocommerce-message")
    private WebElement successMessage;

    /** Message d'erreur affiché en cas d'erreur */
    @FindBy(css = ".woocommerce-error")
    private WebElement errorMessage;

    /** Conteneur d'un article dans le panier */
    @FindBy(css = ".cart_item")
    private WebElement cartItem;

    /** Bouton pour mettre à jour le contenu du panier */
    @FindBy(name = "update_cart")
    private WebElement updateCartButton;


    /**
     * Vérifie si un article est présent dans le panier
     *
     * @return true si l'article est visible dans le panier, false sinon
     */
    public boolean articlePresent() {
        return isElementDisplayed(cartItem);
    }

    /**
     * Vérifie si aucun article n'est présent dans le panier
     *
     * @return true si aucun article n'est affichée, false sinon
     */
    public boolean articleNotPresent() {
        waitInvisible(cartItem);
        return !isElementDisplayed(cartItem);
    }

    /**
     * Vérifie si le prix unitaire est visible
     *
     * @return true si le prix unitaire est affiché, false sinon
     */
    public boolean prixUnitaireVisible() {
        return isElementDisplayed(unitPrice);
    }

    /**
     * Vérifie si le prix total est visible
     *
     * @return true si le sous-total est affiché, false sinon
     */
    public boolean prixTotalVisible() {
        return isElementDisplayed(subtotal);
    }

    /**
     * Modifie la quantité d'un article dans le panier
     *
     * @param qty La nouvelle quantité à définir pour l'article
     */
    public void modifierQuantite(String qty) {
        quantityField.clear();
        quantityField.sendKeys(qty);
        click(updateCartButton);
    }

    /**
     * Supprime un article du panier
     */
    public void supprimerArticle() {
        click(removeButton);
    }

    /**
     * Saisit un code coupon de réduction
     *
     * @param code Le code coupon à appliquer
     */
    public void saisirCoupon(String code) {
        couponField.clear();
        couponField.sendKeys(code);
    }

    /**
     * Applique le coupon saisi
     */
    public void appliquerCoupon() {
        click(applyCouponBtn);
    }

    /**
     * Récupère le message de succès affiché
     *
     * @return Le texte du message de succès
     */
    public String getMessageSucces() {
        return successMessage.getText();
    }

    /**
     * Récupère le message d'erreur affiché
     *
     * @return Le texte du message d'erreur
     */
    public String getMessageErreur() {
        return errorMessage.getText();
    }
}