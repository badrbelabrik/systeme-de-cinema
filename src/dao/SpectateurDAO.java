package dao;

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

    public void getSpectateurByid(){

    }

    public void afficherSpectateurs() {
        System.out.println("=== List of spectators ===");
        for (Spectateur spec : spectateurs) {
            spec.afficherSpectateur();
        }
    }
    public void deleteSpectateur() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the spectator id :");
        int id = input.nextInt();
        String idQuery = "DELETE FROM spectateurs WHERE spectateurId = ?";
        PreparedStatement deleteStmt = connection.prepareStatement(idQuery);
        deleteStmt.setInt(1,id);
        int result = deleteStmt.executeUpdate();
        System.out.println(result + " records deleted");
        deleteStmt.close();

    }
}
