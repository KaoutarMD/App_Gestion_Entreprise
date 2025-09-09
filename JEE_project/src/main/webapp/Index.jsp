<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page d'accueil - Gestion Entreprise</title>
    
    <style>
    
main {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh; 
    background: #f4f4f9; 
    margin: 0;
    padding: 0;
}
.welcome-container {
    background: rgba(0, 0, 0, 0.5); /* Fond semi-transparent pour contraster avec le fond clair */
    color: #fff;
    padding: 2rem;
    border-radius: 15px;
    text-align: center;
    max-width: 600px;
    width: 80%;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2); /* Ombre subtile pour effet de profondeur */
    transition: transform 0.3s ease-in-out;
}
.welcome-container p {
    font-size: 1.2rem;
    line-height: 1.6;
    animation: fadeIn 2s ease-out;
}

@keyframes fadeIn {
    0% {
        opacity: 0;
        transform: translateY(20px);
    }
    100% {
        opacity: 1;
        transform: translateY(0);
    }
}

       /* Appliquer une configuration de base pour tout le corps */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

html, body {
    height: 100%; /* Permet de faire en sorte que le corps couvre toute la hauteur */
    font-family: 'Arial', sans-serif;
    background-color: #f4f4f9;
    line-height: 1.6; /* Améliorer la lisibilité en augmentant l'interligne */
}

body {
    display: flex;
    flex-direction: column;
}

/* Style pour l'entête */
header {
    background-color: #333;
    color: #fff;
    padding: 1.2rem 0; /* Augmenter l'espacement pour plus de visibilité */
    text-align: center;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Ombre subtile pour l'effet de profondeur */
}

header h1 {
    font-size: 2rem; /* Agrandir légèrement le titre */
    font-weight: 600;
    letter-spacing: 0.5px; /* Espacement des lettres pour un effet moderne */
}

/* Style pour le menu de navigation */
nav {
    background-color: #444;
    display: flex;
    justify-content: center;
    padding: 0.8rem 0; /* Un peu plus d'espace pour rendre la navigation plus aérée */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Ombre discrète pour le menu */
}

nav a {
    color: #fff;
    text-decoration: none;
    margin: 0 1.5rem;
    font-size: 1.2rem;
    font-weight: 500;
    transition: color 0.3s, text-decoration 0.3s; /* Transition douce pour l'effet de survol */
}

nav a:hover {
    color: #f4f4f9;
    text-decoration: underline;
}

/* Ajouter une version mobile-friendly */
@media (max-width: 768px) {
    nav {
        flex-direction: column; /* Passage en mode colonne sur les petits écrans */
        padding: 1rem 0;
    }

    nav a {
        margin: 0.5rem 0; /* Espacement réduit entre les éléments du menu */
    }

    header h1 {
        font-size: 1.8rem; /* Réduction de la taille du titre sur mobile */
    }
}


        main {
            flex: 1; /* S'étend pour remplir l'espace restant */
            padding: 2rem;
            text-align: center;
        }

        footer {
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 1rem 0;
        }
    </style>

</head>
<body>
    <header>
        <h1>Gestion d'Entreprise</h1>
    </header>
    
    <nav>
        <a href="EmployeServlet">Gestion Employés</a>
        <a href="SalaireServlet">Gestion Fiche de Salaire</a>
    </nav>

    <main>
    <div class="welcome-container">
        <h1>Bienvenue sur la plateforme de gestion d'entreprise</h1>
        <p>Sélectionnez une option dans la barre de navigation pour continuer.</p>
    </div>
</main>

    <footer>
        <p>&copy; 2024 Gestion d'Entreprise. Tous droits réservés.</p>
    </footer>
</body>
</html>