package models;

import java.sql.*;
import java.util.Scanner;
import utils.DBConnection;

public class Ticket {
    Scanner input = new Scanner(System.in);
    private int ticketId;
    private float prix;
    private int spectateurId;
    private int seanceId;

    public Ticket(int ticketId,float prix, int spectateurId ,int seanceId){
        this.ticketId = ticketId;
        this.spectateurId = spectateurId;
        this.seanceId = seanceId;
        this.prix = prix;
    }

    public void afficherTicket(){
        System.out.println("ID: " + this.ticketId + ", prix : " + this.prix + ", spectateur id : " + this.spectateurId+", seance id : "+this.seanceId);
    }

    public int getSpectateurId() {
        return spectateurId;
    }

    public int getSeanceId() {
        return seanceId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }


}
