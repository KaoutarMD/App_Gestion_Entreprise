<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.FicheSalaire" %>
<%@ page import="models.Employe" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Fiches de Salaire</title>
    
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
                <th>Numéro de Fiche</th>
                <th>Date</th>
                <th>Taux Horaire</th>
                <th>Nombre d'Heures</th>
                <th>Montant Brut</th>
                <th>Taxe</th>
                <th>Montant Net</th>
                <th>Nom Employé</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <%
            // Retrieve the list of fiches from the request
            List<FicheSalaire> fiches = (List<FicheSalaire>) request.getAttribute("fiches");
            if (fiches != null) {
                for (FicheSalaire fiche : fiches) {
        %>
            <tr>
                <td><%= fiche.getNumfiche() %></td>
                <td><%= fiche.getDatef() %></td>
                <td><%= fiche.getTauxtt() %></td>
                <td><%= fiche.getNbheures() %></td>
                <td><%= fiche.getMontantbrut() %></td>
                <td><%= fiche.getTaxe() %></td>
                <td><%= fiche.getMontantnet() %></td>
                <td><%= fiche.getEmp().getNom() %></td>
                <td>
                  
                    <a href="<%= request.getContextPath() + "/SalaireServlet?action=modifier&id=" + fiche.getNumfiche() %>" class="btn">Modifier</a>
                   <br>  <br> 
                    
                    <!-- Form for deleting the fiche -->
                    <form action="SalaireServlet" method="get" style="display:inline;">
                        <input type="hidden" name="action" value="supprimer">
                        <input type="hidden" name="numfiche" value="<%= fiche.getNumfiche() %>">
                        <button type="submit" onclick="return confirm('Confirmez la suppression ?')">Supprimer</button>
                    </form>
                </td>
                

            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="9">Aucune fiche de salaire trouvée.</td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
    

    <a href="<%= request.getContextPath() + "/SalaireServlet?action=ajouter"  %> " class="btn">Ajouter une Fiche</a>

    <!-- Retour Button to redirect back to the index page -->
    
    <a href="Index.jsp"><button>Retour</button></a>
    </main>
     <footer>
        <p>&copy; 2024 Gestion d'Entreprise. Tous droits réservés.</p>
    </footer>
</body>
</html>
