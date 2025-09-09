<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.FicheSalaire" %>
<%@ page import="models.Employe" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Fiches De Salaire</title>
    
<style>


 
 /* Styles généraux pour le tableau */

 
 
    table {
        width: 100%;
        border-collapse: collapse;
        margin: 2rem auto; /* Centrer le tableau avec un espace vertical */
        background-color: #fff;
        border: 1px solid #ddd;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Ombre subtile pour un effet moderne */
    }

    th, td {
        padding: 1rem;
        text-align: left;
        border: 1px solid #ddd; /* Bordure entre les cellules */
    }

    th {
        background-color: #444; /* Couleur d'entête */
        color: #fff;
        font-weight: bold;
    }

    tr:nth-child(even) {
        background-color: #f4f4f9; /* Couleur alternée pour les lignes */
    }

    tr:hover {
        background-color: #ddd; /* Couleur de survol */
        cursor: pointer; /* Indiquer qu'une action est possible */
    }

    /* Styles pour les liens et les boutons */
    a {
        color: #444;
        text-decoration: none;
    }

    a:hover {
        text-decoration: underline;
        color: #000;
    }

    button {
        background-color: #333;
        color: #fff;
        border: none;
        padding: 0.5rem 1rem;
        cursor: pointer;
        font-size: 0.9rem;
        border-radius: 5px;
        font-weight: bold;
    }

    button:hover {
        background-color: #333;
        color: #f4f4f9;
    }

.btn {
        display: inline-block;
        padding: 0.5rem 1rem;
        background-color: #333;
        color: #fff;
        text-decoration: none;
        font-size: 1rem;
        font-weight: bold;
        border-radius: 5px;
        border: 1px solid transparent;
        transition: background-color 0.3s, border-color 0.3s;
    }
     .btn:hover {
        background-color: #333;
        color: #f4f4f9;
    }
        /* Appliquer une configuration de base pour tout le corps */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        html, body {
            height: 100%; /* Permet de faire en sorte que le corps couvre toute la hauteur */
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
        }

        body {
            display: flex;
            flex-direction: column;
        }

        header {
            background-color: #333;
            color: #fff;
            padding: 1rem 0;
            text-align: center;
        }

        nav {
            background-color: #444;
            display: flex;
            justify-content: center;
            padding: 0.5rem;
        }

        nav a {
            color: #fff;
            text-decoration: none;
            margin: 0 1rem;
            font-size: 1.1rem;
        }

        nav a:hover {
            color: #f4f4f9;
            text-decoration: underline;
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
        
        
        
        /* Styles généraux pour le formulaire */
    form {
        background-color: #fff;
        padding: 1.5rem;
        border-radius: 8px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        width: 40%;
        margin: 2rem auto;
    }

    label {
        display: inline-block;
        margin: 0.5rem 0;
        font-weight: bold;
        color: #333;
    }

    input {
        width: 100%;
        padding: 0.6rem;
        border: 1px solid #ddd;
        border-radius: 5px;
        margin-bottom: 1rem;
        font-size: 1rem;
        background-color: #f4f4f9;
    }

    input:focus {
        border-color: #333;
        background-color: #fff;
        outline: none;
    }

    button[type="submit"] {
        background-color: #333;
        color: #fff;
        border: none;
        padding: 0.6rem 1.2rem;
        font-size: 1rem;
        font-weight: bold;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
        width: 100%;
    }
    </style>

</head>
<body>
    <header>
        <h1>Modifier Fiche De Salaire</h1>
    </header>
    
    <main>

    <form action="SalaireServlet" method="post">
        <input type="hidden" name="action" value="update">

        <!-- Null-safe access to fiche -->
        <input type="hidden" name="numfiche" value="<%= request.getAttribute("fiche") != null ? ((FicheSalaire) request.getAttribute("fiche")).getNumfiche() : "" %>">

        <label for="datef">Date:</label>
        <input type="date" id="datef" name="datef" value="<%= request.getAttribute("fiche") != null ? ((FicheSalaire) request.getAttribute("fiche")).getDatef().toString() : "" %>" required><br>

        <label for="tauxtt">Taux Horaire:</label>
        <input type="number" id="tauxtt" name="tauxtt" step="0.01" value="<%= request.getAttribute("fiche") != null ? ((FicheSalaire) request.getAttribute("fiche")).getTauxtt() : "" %>" required><br>

        <label for="nbheures">Nombre d'Heures:</label>
        <input type="number" id="nbheures" name="nbheures" value="<%= request.getAttribute("fiche") != null ? ((FicheSalaire) request.getAttribute("fiche")).getNbheures() : "" %>" required><br>

        <label for="montantbrut">Montant Brut:</label>
        <input type="number" id="montantbrut" name="montantbrut" step="0.01" value="<%= request.getAttribute("fiche") != null ? ((FicheSalaire) request.getAttribute("fiche")).getMontantbrut() : "" %>" required><br>

        <label for="taxe">Taxe:</label>
        <input type="number" id="taxe" name="taxe" step="0.01" value="<%= request.getAttribute("fiche") != null ? ((FicheSalaire) request.getAttribute("fiche")).getTaxe() : "" %>" required><br>

        <label for="empId">ID Employé:</label>
        <input type="number" id="empId" name="empId" value="<%= request.getAttribute("fiche") != null ? ((FicheSalaire) request.getAttribute("fiche")).getEmp().getId() : "" %>" required><br>

        <button type="submit">Mettre à jour</button>
    </form>
    <br>
    <a href="SalaireServlet"><button>Retour</button></a>
    </main>
    
     <footer>
        <p>&copy; 2024 Gestion d'Entreprise. Tous droits réservés.</p>
    </footer>
</body>
</html>
