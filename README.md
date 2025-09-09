# 🏢 Système de Gestion d'Entreprise

Un projet complet de gestion d'entreprise implémenté en **trois architectures différentes** : Spring Boot, Application Console (Swing), et Java Enterprise Edition (JEE). Ce projet démontre l'application des principes d'architecture logicielle et des patterns de conception dans différents contextes technologiques.

## 📋 Table des matières

- [Vue d'ensemble](#-vue-densemble)
- [Fonctionnalités](#-fonctionnalités)
- [Architectures implémentées](#-architectures-implémentées)
- [Technologies utilisées](#-technologies-utilisées)
- [Structure du projet](#-structure-du-projet)
- [Installation et configuration](#-installation-et-configuration)
- [Utilisation](#-utilisation)
- [Base de données](#-base-de-données)
- [Captures d'écran](#-captures-décran)
- [Contributions](#-contributions)
- [Licence](#-licence)

## 🎯 Vue d'ensemble

Ce projet présente une application de gestion d'entreprise complète avec trois implémentations distinctes :

1. **Spring Boot** - Architecture moderne avec Spring Security et Thymeleaf
2. **Application Console/Swing** - Interface graphique desktop avec Java Swing
3. **Java Enterprise Edition (JEE)** - Application web avec servlets et JSP

Chaque implémentation gère les mêmes entités métier : **Employés** et **Fiches de Salaire**, mais utilise des approches architecturales différentes.

## ✨ Fonctionnalités

### Gestion des Employés
- ✅ Ajout d'employés
- ✅ Modification des informations employés
- ✅ Suppression d'employés
- ✅ Recherche d'employés (par ID ou nom)
- ✅ Affichage de la liste des employés

### Gestion des Fiches de Salaire
- ✅ Création de fiches de salaire
- ✅ Calcul automatique du montant net (brut - taxes)
- ✅ Modification des fiches existantes
- ✅ Suppression de fiches
- ✅ Association fiches-employés
- ✅ Affichage des fiches avec détails employé

## 🏗️ Architectures implémentées

### 1. Spring Boot (`/Spring`)
- **Framework** : Spring Boot 3.4.0
- **Sécurité** : Spring Security
- **Template Engine** : Thymeleaf
- **Base de données** : JPA/Hibernate
- **Packaging** : WAR (déployable sur serveur d'applications)

**Technologies clés :**
- Spring Data JPA
- Spring Security
- Thymeleaf
- MySQL/SQL Server
- Lombok

### 2. Application Console/Swing (`/SwingConsole`)
- **Interface** : Java Swing + Console
- **Pattern** : MVC (Model-View-Controller)
- **Architecture** : Couches séparées (IHM, Services, Models)
- **Base de données** : JDBC direct

**Composants principaux :**
- `IHMSwing` : Interface graphique moderne
- `IHMConsole` : Interface en ligne de commande
- `GestionEmployesV2` / `GestionSalaireV2` : Services métier
- `InterfaceGestionEmployes` / `InterfaceGestionSalaire` : Contrats

### 3. Java Enterprise Edition (`/JEE_project`)
- **Technologie** : Servlets + JSP
- **Architecture** : MVC Web
- **Base de données** : JDBC
- **Packaging** : WAR

**Composants :**
- `EmployeServlet` / `SalaireServlet` : Contrôleurs
- `DatabaseConnection` : Gestion des connexions
- JSP : Vues (GestionEmploye.jsp, GestionSalaire.jsp)
- Services métier réutilisés

## 🛠️ Technologies utilisées

| Catégorie | Technologies |
|-----------|-------------|
| **Backend** | Java 17, Spring Boot, Servlets, JSP |
| **Base de données** | MySQL, SQL Server |
| **Frontend** | Thymeleaf, JSP, Java Swing, HTML/CSS/JS |
| **Build** | Maven |
| **Serveur** | Tomcat (embedded/standalone) |
| **Sécurité** | Spring Security |
| **Outils** | Lombok, Bootstrap |

## 📁 Structure du projet

```
Architecture logicielle/
├── Spring/                          # Application Spring Boot
│   ├── pom.xml                     # Configuration Maven
│   ├── src/main/java/              # Code source Java
│   └── src/main/resources/         # Configuration et ressources
│
├── SwingConsole/                   # Application Desktop
│   ├── src/main/java/
│   │   ├── IHM/                    # Interfaces utilisateur
│   │   ├── models/                 # Modèles de données
│   │   ├── services/               # Services métier
│   │   └── composantEmployes/      # Composants employés
│   │   └── composantSalaires/      # Composants salaires
│   └── build/                      # Fichiers compilés
│
├── JEE_project/                    # Application Web JEE
│   ├── src/main/java/servlet/      # Servlets
│   ├── src/main/webapp/            # Ressources web
│   │   ├── views/                  # Pages JSP
│   │   ├── css/                    # Styles Bootstrap
│   │   └── js/                     # Scripts JavaScript
│   └── backend3.jar                # JAR compilé
│
└── README.md                       # Ce fichier
```

## ⚙️ Installation et configuration

### Prérequis
- **Java 17** ou supérieur
- **Maven 3.6+**
- **MySQL 8.0+** ou **SQL Server**
- **Tomcat 9+** (pour JEE)

### Configuration de la base de données

1. **Créer la base de données :**
```sql
CREATE DATABASE gestionEntreprise1;
```

2. **Créer les tables :**
```sql
-- Table Employés
CREATE TABLE employes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    adresse VARCHAR(255),
    numTel VARCHAR(20)
);

-- Table Fiches de Salaire
CREATE TABLE fiches_salaire (
    numfiche VARCHAR(50) PRIMARY KEY,
    datef DATE NOT NULL,
    tauxtt DECIMAL(10,2),
    nbheures INT,
    montantbrut DECIMAL(10,2),
    taxe DECIMAL(5,2),
    montantnet DECIMAL(10,2),
    emp_id INT,
    FOREIGN KEY (emp_id) REFERENCES employes(id)
);
```

3. **Configurer les connexions :**
   - Modifier les paramètres de connexion dans `DatabaseConnection.java`
   - URL 
   - Utilisateur
   - Mot de passe 

### Démarrage des applications

#### Spring Boot
```bash
cd Spring
mvn spring-boot:run
```
Accès : http://localhost:8080

#### Application Swing
```bash
cd SwingConsole
javac -cp "lib/*" src/main/java/*.java
java -cp ".:lib/*" main
```

#### Application JEE
1. Compiler le projet :
```bash
cd JEE_project
mvn clean package
```

2. Déployer le WAR sur Tomcat
3. Accès : http://localhost:8080/JEE_project

## 🚀 Utilisation

### Spring Boot
- Interface web moderne avec Thymeleaf
- Sécurité intégrée avec Spring Security
- Gestion automatique des sessions

### Application Swing
- Interface graphique intuitive
- Menu principal avec navigation par cartes
- Tables interactives pour l'affichage des données
- Dialogs pour la saisie/modification

### Application JEE
- Interface web classique avec JSP
- Navigation par servlets
- Design responsive avec Bootstrap
- Gestion des erreurs intégrée

## 🗄️ Base de données

### Modèle de données

**Employé :**
- `id` : Identifiant unique (AUTO_INCREMENT)
- `nom` : Nom de famille
- `prenom` : Prénom
- `adresse` : Adresse complète
- `numTel` : Numéro de téléphone

**Fiche de Salaire :**
- `numfiche` : Numéro unique de la fiche
- `datef` : Date de la fiche
- `tauxtt` : Taux horaire
- `nbheures` : Nombre d'heures travaillées
- `montantbrut` : Montant brut
- `taxe` : Pourcentage de taxe
- `montantnet` : Montant net (calculé automatiquement)
- `emp_id` : Référence vers l'employé

## 🎥 Démonstration
[Voir la vidéo de démonstration](https://drive.google.com/file/d/1ml797taRThHjNyBbku_QeF3-3scowSLc/view?usp=sharing))



