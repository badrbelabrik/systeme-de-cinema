package dao;
import models.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketDAO {
    private Connection connection;
    private List<Ticket> tickets = new ArrayList<>();
    public TicketDAO(Connection connection){
        this.connection = connection;
    }
    public void addTicket(float prix, int spectateurId, int seanceId) throws SQLException {
        String addQuery = "INSERT INTO tickets(prix,spectateurId,seanceId) VALUES (?,?,?)";
        PreparedStatement addStmt = connection.prepareStatement(addQuery);
        addStmt.setFloat(1, prix);
        addStmt.setInt(2, spectateurId );
        addStmt.setInt(3, seanceId);
        int result = addStmt.executeUpdate();
        System.out.println(result + " records inserted");
        addStmt.close();
    }
    public void getAllTickets() throws SQLException {
        tickets.clear();
        String selectQuery = "SELECT * FROM tickets";
        Statement showStmt = connection.createStatement();
        ResultSet resultSet = showStmt.executeQuery(selectQuery);
        while (resultSet.next()) {
            int ticketId = resultSet.getInt("ticketId");
            float prix = resultSet.getFloat("prix");
            int spectateurId = resultSet.getInt("spectateurId");
            int seanceId = resultSet.getInt("seanceId");
            System.out.println("ID: " + ticketId + ", Prix : " + prix + ", spectateurId : " + spectateurId + ", seanceId : " + seanceId);
        }
        resultSet.close();
        showStmt.close();
        System.out.println("=== list of tickets ===");
        for(Ticket t: tickets){
            t.afficherTicket();
        }
    }
    public Ticket getTicketById(int id) throws SQLException {
        Ticket ticket1 = null;
        String idQuery = "SELECT * FROM tickets WHERE ticketId = ?";
        PreparedStatement idStmt = connection.prepareStatement(idQuery);
        idStmt.setInt(1,id);
        ResultSet resultSet = idStmt.executeQuery();
        if (resultSet.next()) {
            int ticketId = resultSet.getInt("ticketId");
            float prix = resultSet.getFloat("prix");
            int spectateurId = resultSet.getInt("spectateurId");
            int seanceId = resultSet.getInt("seanceId");
            ticket1 = new Ticket(
                    resultSet.getInt("ticketId"),
                    resultSet.getFloat("prix"),
                    resultSet.getInt("spectateurId"),
                    resultSet.getInt("seanceId")
            );
            System.out.println("ID: " + ticketId + ", Prix : " + prix + ", spectateurId : " + spectateurId + ", seanceId : " + seanceId);
        }
        resultSet.close();
        idStmt.close();
        if (ticket1 == null) {
            System.out.println("No ticket found with id " + id);
        }
        return ticket1;
    }

    public void deleteTicket(int id) throws SQLException {
        String idQuery = "DELETE FROM tickets WHERE ticketId = ?";
        PreparedStatement deleteStmt = connection.prepareStatement(idQuery);
        deleteStmt.setInt(1,id);
        int result = deleteStmt.executeUpdate();
        System.out.println(result + " records deleted");
        deleteStmt.close();

    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }



}
