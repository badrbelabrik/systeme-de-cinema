import dao.FilmDAO;
import dao.SeanceDAO;
import dao.SpectateurDAO;
import dao.TicketDAO;
import models.Seance;

import java.sql.SQLException;
import java.util.Scanner;

public class UserMenu {
    TicketDAO tdao;
    FilmDAO fdao;
    SeanceDAO sdao;
    SpectateurDAO specdao;
    Scanner input = new Scanner(System.in);
    public UserMenu(TicketDAO tdao, FilmDAO fdao, SeanceDAO sdao, SpectateurDAO specdao){
        this.tdao = tdao;
        this.fdao = fdao;
        this.sdao = sdao;
        this.specdao = specdao;
    }
    public void Mainmenu() throws SQLException {

        int choix;
        do{
            System.out.println("=== Welcome to Cinema ===");
            System.out.println("1. New account ");
            System.out.println("2. Login ");
            System.out.println("0. Exit");
            System.out.print("Choose an option :");
            choix = input.nextInt();
            input.nextLine();
            switch(choix){
                case 1:
                    System.out.print("Enter your name :");
                    String nom = input.nextLine();
                    System.out.print("Enter your email :");
                    String email = input.nextLine();
                    specdao.addSpectateur(nom,email);
                    System.out.println("account created succesfully");
                    break;
                case 2:
                    System.out.print("Enter your name :");
                    nom = input.nextLine();
                    System.out.print("Enter your email :");
                    email = input.nextLine();
                    int spectateurId = specdao.login(nom, email);
                    if (spectateurId != -1) { // valid login
                        menu1(spectateurId);
                    }
                    break;
                case 0:
                    System.out.println("Exiting");
                default :
                    System.out.println("Invalid choice");
                    break;
            }
        } while (choix != 0);


    }

    public void menu1(int specid) throws SQLException {
        int choix;
        do{
            System.out.println("=== Films Menu ===");
            fdao.getAllFilms();
            System.out.println("1. Choose a film :");
            System.out.println("0. Retour ");
            System.out.print("Choose an option :");
            choix = input.nextInt();

            switch (choix){
                case 1:
                    System.out.print("Enter the film id :");
                    int filmid = input.nextInt();
                    input.nextLine();
                    boolean hasSeances = sdao.getseanceByfilm(filmid);
                    if(hasSeances){
                        System.out.print("Choose the seance id: ");
                        int seanceid = input.nextInt();
                        input.nextLine();
                        Seance seance = sdao.getSeanceByid(seanceid);
                        if (seance != null) {
                            tdao.addTicket(seance.getPrix(), specid, seanceid);
                            System.out.println("Ticket created successfully");
                        } else {
                            System.out.println("Invalid seance ID.");
                        }
                    }
                    break;
                default :
                    System.out.println("Invalid choice !!");
                    break;
            }
        }while(choix != 0);

    }
}
