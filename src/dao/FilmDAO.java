package dao;

import models.Film;
import models.Seance;
import models.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilmDAO {
    Scanner input = new Scanner(System.in);
    private Connection connection;
    private List<Film>films = new ArrayList<>();

    public FilmDAO(Connection connection) {
        this.connection = connection;
    }

    public void addFilm(String titre, String duree, String categorie) throws SQLException {
        String addQuery = "INSERT INTO films(titre,duree,categorie) VALUES (?,?,?)";
        PreparedStatement addStmt = connection.prepareStatement(addQuery);
        addStmt.setString(1, titre);
        addStmt.setString(2, duree);
        addStmt.setString(3, categorie);
        int result = addStmt.executeUpdate();
        System.out.println(result + " records inserted");
        addStmt.close();
    }

    public void getAllFilms() throws SQLException {
        String showQuery = "SELECT * FROM films";
        Statement showStmt = connection.createStatement();
        ResultSet resultSet = showStmt.executeQuery(showQuery);
        while (resultSet.next()) {
            int filmId = resultSet.getInt("filmId");
            String titre = resultSet.getString("titre");
            String duree = resultSet.getString("duree");
            String categorie = resultSet.getString("categorie");
            films.add(new Film(filmId,titre,duree,categorie));
        }
        resultSet.close();
        showStmt.close();
        System.out.println("=== List of films ===");
        for (Film film : films) {
            film.afficherFilm();
        }
    }

    public Film getFilmByid(int id) throws SQLException {
        Film film1 = null;
        String idQuery = "SELECT * FROM films WHERE filmId = ?";
        PreparedStatement idStmt = connection.prepareStatement(idQuery);
        idStmt.setInt(1, id);
        ResultSet resultSet = idStmt.executeQuery();
        if (resultSet.next()) {
            int filmId = resultSet.getInt("filmId");
            String titre = resultSet.getString("titre");
            int  duree = resultSet.getInt("duree");
            String categorie = resultSet.getString("categorie");
            film1 = new Film(
                    resultSet.getInt("filmId"),
                    resultSet.getString("titre"),
                    resultSet.getString("duree"),
                    resultSet.getString("categorie")
            );
            System.out.println("ID: " + filmId + ", titre : " + titre + ", duree : " + duree + ", categorie : " + categorie);
        }
        resultSet.close();
        idStmt.close();
        return film1;
    }

    public void deleteFilm(int id) throws SQLException {
        String idQuery = "DELETE FROM films WHERE filmId = ?";
        PreparedStatement deleteStmt = connection.prepareStatement(idQuery);
        deleteStmt.setInt(1,id);
        int result = deleteStmt.executeUpdate();
        System.out.println(result + " records deleted");
        deleteStmt.close();

    }

}
