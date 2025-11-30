package dao;

import models.Film;
import models.Spectateur;

import java.beans.PropertyEditorSupport;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpectateurDAO {
    private Connection connection;
    private List<Spectateur> spectateurs = new ArrayList<>();

    public SpectateurDAO(Connection connection) {
        this.connection = connection;
    }

    public void addSpectateur(String nom, String email) throws SQLException {
        String addQuery = "INSERT INTO spectateurs(nom,email) VALUES (?,?)";
        PreparedStatement addStmt = connection.prepareStatement(addQuery);
        addStmt.setString(1, nom);
        addStmt.setString(2, email);
        int result = addStmt.executeUpdate();
        System.out.println(result + " records inserted");
        addStmt.close();
    }

    public void getAllSpectateurs() throws SQLException {
        spectateurs.clear();
        String showQuery = "SELECT * FROM spectateurs";
        Statement showStmt = connection.createStatement();
        ResultSet resultset = showStmt.executeQuery(showQuery);
        while (resultset.next()) {
            int spectateurId = resultset.getInt("spectateurId");
            String nom = resultset.getString("nom");
            String email = resultset.getString("email");
            spectateurs.add(new Spectateur(spectateurId, nom, email));
        }
        resultset.close();
        System.out.println("=== List of spectators ===");
        for(Spectateur spec: spectateurs){
            spec.afficherSpectateur();
        }
    }

    public Spectateur getSpectateurByid(int id) throws SQLException {
        Spectateur spectateur1 = null;
        String idQuery = "SELECT * FROM spectateurs WHERE spectateurId = ?";
        PreparedStatement idStmt = connection.prepareStatement(idQuery);
        idStmt.setInt(1,id);
        ResultSet resultSet = idStmt.executeQuery();
        if (resultSet.next()) {
            int spectateurId = resultSet.getInt("spectateurId");
            String nom = resultSet.getString("nom");
            String  email = resultSet.getString("email");
            spectateur1 = new Spectateur(
                    resultSet.getInt("spectateurId"),
                    resultSet.getString("nom"),
                    resultSet.getString("email")
            );
            System.out.println("ID: " + spectateurId + ", Nom : " + nom + ", email : " + email);
        }
        if(spectateur1 == null){
            System.out.println("No spectator found for id "+id);
        }
        resultSet.close();
        idStmt.close();
        return spectateur1;
    }
    public int login(String nom, String email) throws SQLException {
        Spectateur spec1 = null;
        String Query = "SELECT * FROM spectateurs WHERE nom = ? AND email = ?";
        PreparedStatement loginStmt = connection.prepareStatement(Query);
        loginStmt.setString(1, nom);
        loginStmt.setString(2, email);

       try(ResultSet resultSet = loginStmt.executeQuery()){
           if (resultSet.next()) {
               spec1 = new Spectateur(
                       resultSet.getInt("spectateurId"),
                       resultSet.getString("nom"),
                       resultSet.getString("email")
               );
               System.out.println("Login successfully");
               return spec1.getSpectateurId();
           } else {
               System.out.println("Invalid credentials !!!");
               return-1;
           }
       }

    }

    public void deleteSpectateur() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the spectator id :");
        int id = input.nextInt();
        String idQuery = "DELETE FROM spectateurs WHERE spectateurId = ?";
        PreparedStatement deleteStmt = connection.prepareStatement(idQuery);
        deleteStmt.setInt(1,id);
        int result = deleteStmt.executeUpdate();
        System.out.println(result + " records deleted");
        deleteStmt.close();

    }
}
