<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="models.Employe" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Employes</title>
    
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
    </style>

</head>
<body>
    <header>
        <h1>Gestion des Fiches de Salaire</h1>
    </header>
    
    <main>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prenom</th>
                <th>numTel</th>
                <th>Adresse</th>
                <th>Modification</th>
                <th>Suppression</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Employe> employes = (List<Employe>) request.getAttribute("employes");
                if (employes != null) {
                    for (Employe employe : employes) {
            %>
            <tr>
                <td><%= employe.getId() %></td>
                <td><%= employe.getNom() %></td>
                <td><%= employe.getPrenom() %></td>
                 <td><%= employe.getNumTel() %></td>
                 <td><%= employe.getAdresse() %></td>
                <td><a href="<%= request.getContextPath() + "/EmployeServlet?action=edit&id=" + employe.getId() %>" class="btn">Modifier</a></td>
                <td><a href="<%= request.getContextPath() + "/EmployeServlet?action=delete&id=" + employe.getId() %>" class="btn">Supprimer</a></td>
            </tr>
            <% 
                    }
                } else {
            %>
                <tr><td colspan="5">Aucun employé trouvé.</td></tr>
            <% 
                }
            %>
        </tbody>
    </table>
    <!-- Button to go to the add employee page -->
    <a href="<%= request.getContextPath() + "/EmployeServlet?action=add"  %>" class="btn">Ajouter un Employé</a>

<br><br>
    <a href="Index.jsp"><button>Retour</button></a>
    </main>
    <footer>
        <p>&copy; 2024 Gestion d'Entreprise. Tous droits réservés.</p>
    </footer>
</body>
</html>
