package dao;

import models.Film;
import models.Seance;
import models.Spectateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeanceDAO {
    private Connection connection;
    List<Seance>seances = new ArrayList<>();
    public SeanceDAO(Connection connection){
        this.connection = connection;
    }

    public void addSeance(String date, String horaire, float prix, int capaciteMaximale, String salle, int filmId) throws SQLException {
        String addQuery = "INSERT INTO seances (date,horaire,prix,capaciteMaximale,salle,filmId) VALUES (?,?,?,?,?,?)";
        PreparedStatement addStmt = connection.prepareStatement(addQuery);
        addStmt.setString(1,date);
        addStmt.setString(2,horaire);
        addStmt.setFloat(3,prix);
        addStmt.setInt(4,capaciteMaximale);
        addStmt.setString(5,salle);
        addStmt.setInt(6, filmId);
        int result = addStmt.executeUpdate();
        System.out.println(result + " recors inserted");
        addStmt.close();
    }

    public void getAllSeances() throws SQLException {
        seances.clear();
        String showQuery = "SELECT * FROM seances";
        Statement showStmt = connection.createStatement();
        ResultSet resultSet = showStmt.executeQuery(showQuery);
        while(resultSet.next()){
           int seanceId = resultSet.getInt("seanceId");
           String date = resultSet.getString("date");
           String horaire = resultSet.getString("horaire");
           float prix = resultSet.getFloat("prix");
           int capaciteMaximale = resultSet.getInt("capaciteMaximale");
           String salle = resultSet.getString("salle");
           int filmId = resultSet.getInt("filmId");
            seances.add(new Seance(seanceId,date,horaire,prix,capaciteMaximale,salle,filmId));
        }
        resultSet.close();
        showStmt.close();
        System.out.println("=== List of seances ===");
        for (Seance seance : seances) {
            seance.afficherSeance();
        }
    }
    public Seance getSeanceByid(int id) throws SQLException {
        Seance seance1 = null;
        String idQuery = "SELECT * FROM seances WHERE seanceId= ?";
        PreparedStatement idStmt = connection.prepareStatement(idQuery);
        idStmt.setInt(1, id);
        ResultSet resultSet = idStmt.executeQuery();
        if (resultSet.next()) {
            int seanceId = resultSet.getInt("seanceId");
            String date = resultSet.getString("date");
            String horaire = resultSet.getString("horaire");
            int prix = resultSet.getInt("prix");
            int capaciteMaximale = resultSet.getInt("capaciteMaximale");
            String salle = resultSet.getString("salle");
            int filmId = resultSet.getInt("filmId");
            seance1 = new Seance(
                    resultSet.getInt("seanceId"),
                    resultSet.getString("date"),
                    resultSet.getString("horaire"),
                    resultSet.getInt("prix"),
                    resultSet.getInt("capaciteMaximale"),
                    resultSet.getString("salle"),
                    resultSet.getInt("filmId")
            );
            System.out.println("ID: " + seanceId + ", date : " +date+", horaire :" +horaire + ", prix : " + prix + ", capacite maximale : " + capaciteMaximale
                    +", salle : "+salle+", film id : "+filmId);
        }

        if(seance1 == null){
            System.out.println("No seance found for id "+id);
        }
        return seance1;
    }
    public boolean getseanceByfilm(int id) throws SQLException {
        seances.clear();
        String idQuery = "SELECT * FROM seances WHERE filmId= ?";
            PreparedStatement filmStmt = connection.prepareStatement(idQuery);
            filmStmt.setInt(1, id);
            ResultSet resultSet = filmStmt.executeQuery();
        while(resultSet.next()){
            int seanceId = resultSet.getInt("seanceId");
            String date = resultSet.getString("date");
            String horaire = resultSet.getString("horaire");
            float prix = resultSet.getFloat("prix");
            int capaciteMaximale = resultSet.getInt("capaciteMaximale");
            String salle = resultSet.getString("salle");
            int filmId = resultSet.getInt("filmId");
            seances.add(new Seance(seanceId,date,horaire,prix,capaciteMaximale,salle,filmId));
        }
        resultSet.close();
        filmStmt.close();
        if (seances.isEmpty()) {
            System.out.println("No seances available for this movie.");
            return false;
        }else{
            System.out.println("=== List of seances for this film ===");
            for (Seance seance : seances) {
                seance.afficherSeance();
            }
            return true;
        }

    }

    public void deleteSeance(int id) throws SQLException {
        String idQuery = "DELETE FROM seances WHERE seanceId = ?";
        PreparedStatement deleteStmt = connection.prepareStatement(idQuery);
        deleteStmt.setInt(1,id);
        int result = deleteStmt.executeUpdate();
        System.out.println(result + " records deleted");
        deleteStmt.close();

    }


}
