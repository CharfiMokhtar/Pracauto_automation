# Projet d'Automatisation des Tests - POEI GP1

## 📋 Description du Projet

Ce projet implémente une **suite d'automatisation des tests** pour une application e-commerce utilisant :
- **Selenium WebDriver** pour l'automatisation des tests web
- **Cucumber (BDD)** pour les scénarios de test en langage naturel
- **JUnit** pour les assertions et la gestion des tests
- **Maven** pour la gestion des dépendances et la construction du projet

Le projet teste les fonctionnalités principales d'un site e-commerce :
- Navigation et recherche de produits
- Panier et commandes
- Gestion du compte utilisateur
- Inscription et connexion
- Modification des informations de compte

---

## 🏗️ Architecture du Projet

### Structure des répertoires

```
POEI_Projet_Automatisation_GP1/
├── src/
│   └── test/
│       ├── java/com/example/automation/
│       │   ├── configuration/          # Configuration et setup
│       │   │   ├── ConfigReader.java   # Lecture des propriétés
│       │   │   ├── DriverFactory.java  # Gestion du WebDriver
│       │   │   └── Hooks.java          # Hooks Cucumber (Before/After)
│       │   ├── pages/                  # Page Object Model
│       │   │   ├── BasePage.java       # Classe de base pour toutes les pages
│       │   │   ├── HomePage.java       # Page d'accueil
│       │   │   ├── ShopPage.java       # Page boutique
│       │   │   ├── ProductPage.java    # Page détail produit
│       │   │   ├── CartPage.java       # Page panier
│       │   │   ├── MyAccountPage.java  # Page Mon Compte
│       │   │   └── EditAccountPage.java # Page édition compte
│       │   ├── steps/                  # Étapes Cucumber (Step Definitions)
│       │   │   ├── CommonSteps.java    # Étapes communes
│       │   │   ├── CartSteps.java      # Étapes panier
│       │   │   ├── ProductSteps.java   # Étapes produit
│       │   │   ├── ShopSteps.java      # Étapes boutique
│       │   │   ├── HomeSteps.java      # Étapes accueil
│       │   │   ├── MyAccountSteps.java # Étapes compte
│       │   │   ├── EditAccountSteps.java # Étapes édition compte
│       │   │   └── NewsletterSteps.java  # Étapes newsletter
│       │   ├── runners/                # Test Runners
│       │   │   └── TestRunner.java     # Runner Cucumber
│       │   └── utils/
│       │       └── Basetools.java      # Utilitaires et méthodes helper
│       └── resources/
│           ├── config.properties       # Propriétés de configuration
│           ├── log4j2.xml              # Configuration logging
│           └── features/               # Fichiers Gherkin (.feature)
├── target/                             # Résultats de la compilation
│   ├── cucumber-reports.html           # Rapport HTML Cucumber
│   ├── cucumber.json                   # Rapport JSON Cucumber
│   └── screenshots/                    # Captures d'écran en cas d'erreur
├── logs/                               # Fichiers de log
├── pom.xml                             # Configuration Maven
├── Jenkinsfile                         # Configuration Jenkins (CI/CD)
└── README.md                           # Ce fichier

```

---

## 📊 Page Object Model (POM)

Le projet utilise le pattern **Page Object Model** pour encapsuler les interactions avec les pages :

### Structure d'une Page Object

```java
public class MyPage extends BasePage {
    
    // Éléments trouvés via @FindBy
    @FindBy(id = "elementId")
    private WebElement element;
    
    // Méthodes pour interagir avec la page
    public void doAction() {
        click(element);
    }
    
    public boolean isDisplayed() {
        return isElementDisplayed(element);
    }
}
```

## 🎯 Behaviour-Driven Development (BDD) avec Cucumber

Le projet utilise **Cucumber** pour écrire les tests en langage Gherkin (proche du langage naturel).

### Format des fichiers .feature

```gherkin
Feature: Gestion du panier
  Scénario : Ajouter un produit au panier
    Étant donné que je suis sur la page "/shop"
    Quand j'ajoute un produit au panier
    Alors le produit est visible dans le panier
    Et le prix total est calculé correctement
```

---

## 🧪 Classes Principales

### Configuration et Setup

#### **ConfigReader.java**
- Lecture des propriétés depuis `config.properties`
- Gestion des configurations (URL, identifiants, etc.)

#### **DriverFactory.java**
- Pattern Factory et Singleton pour le WebDriver
- Support multi-navigateurs (Chrome, Edge)
- Configuration headless et modes spécialisés
- Gestion de la Selenium Grid

#### **Hooks.java**
- Exécution avant/après chaque scénario
- Gestion du navigateur (initialisation, fermeture)
- Gestion des captures d'écran en cas d'erreur
- Nettoyage des ressources

### Page Objects

#### **BasePage.java**
- Classe de base pour toutes les pages
- Méthodes communes (doNotConsent, openPage, clickButton)
- Initialisation via PageFactory

#### **HomePage.java, ShopPage.java, ProductPage.java, etc.**
- Encapsulation des éléments spécifiques à chaque page
- Méthodes pour interagir avec les éléments
- Vérifications spécifiques à la page

### Step Definitions (Étapes Cucumber)

#### **CommonSteps.java**
- Ouverture de page
- Navigation générale
- Clique sur boutons

#### **CartSteps.java, ProductSteps.java, ShopSteps.java, etc.**
- Étapes spécifiques à chaque fonctionnalité
- Lien entre les scénarios Gherkin et le code Java
- Assertions et vérifications

### Utilitaires

#### **Basetools.java**
- Méthodes helper pour Selenium
- Waits explicites et implicites
- Interactions avec les éléments (click, fill, screenshot)
- Gestion de l'attente (waitVisible, waitInvisible)

---

## 📸 Rapports et Artefacts

### Rapports Cucumber

Après exécution, les rapports sont générés dans `target/` :

- **cucumber.json** : Rapport au format JSON (machine-readable)
- **cucumber-reports.html** : Rapport HTML lisible (navigateur)

### Captures d'écran

Les captures sont sauvegardées dans `target/screenshots/` :
- Automatiquement en cas d'échec de test
- Nommées selon le scénario et l'étape

### Logs

Les logs sont écris dans `logs/selenium-tests.log` :
- Suivi de l'exécution
- Messages d'erreur détaillés
- Timestamps pour déboguer

---

## 🔄 Pipeline CI/CD (Jenkins)

Le projet inclut un **Jenkinsfile** pour l'intégration continue :

1. **Build** : Récupération du projet sur GitHub
2. **Export** : Extraction des features depuis Xray 
3. **Test** : Exécution des tests via Maven
4. **import** : importation des résultats vers Xray

---

## 👥 Équipe

**Automation Team**
- Abdel-Aziz DIALLO
- Cyril CARLIER
- Mokhtar CHARFI

---
