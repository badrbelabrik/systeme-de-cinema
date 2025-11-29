import java.sql.SQLException;
import java.util.Scanner;

import dao.FilmDAO;
import dao.SeanceDAO;
import dao.SpectateurDAO;
import dao.TicketDAO;

public class AdminMenu {
    TicketDAO tdao;
    FilmDAO fdao;
    SeanceDAO sdao;
    SpectateurDAO specdao;
    Scanner input = new Scanner(System.in);

    public AdminMenu(TicketDAO tdao, FilmDAO fdao, SeanceDAO sdao, SpectateurDAO specdao){
        this.tdao = tdao;
        this.fdao = fdao;
        this.sdao = sdao;
        this.specdao = specdao;
    }
    public void mainAdminMenu() throws SQLException {
        int choix;
        do{
            System.out.println("=== Admin menu ===");
            System.out.println("1. Spectateurs management");
            System.out.println("2. Films management");
            System.out.println("3. Seances management");
            System.out.println("4. Ticket management");
            System.out.println("0. Exit");
            System.out.print("Enter you choice :");
            choix = input.nextInt();

            switch(choix){
                case 1:
                    spectateurMenu();
                    break;
                case 2:
                    filmMenu();
                    break;
                case 3:
                    seanceMenu();
                    break;
                case 4:
                    ticketMenu();
                    break;
                case 0:
                    System.out.print("Exiting");
                    break;
                default:
                    System.out.println("invalid choice");
            }
        }while (choix != 0);

    }
    public void spectateurMenu() throws SQLException {
        int choix;
        do{
           System.out.println("1. Show all spectators");
           System.out.println("2. get spectator by id");
           System.out.println("3. delete spectator");
           System.out.println("0. Retour");
           System.out.print("choose an option :");
           choix = input.nextInt();

           switch(choix){
               case 1:
                   specdao.getAllSpectateurs();
                   break;
               case 2:
                   specdao.getSpectateurByid();
                   break;
               case 3:
                   specdao.deleteSpectateur();
                   break;
               default :
                   System.out.println("incorrect choice");
       }

        }while (choix != 0);
    }

    public void ticketMenu() throws SQLException {
        int choix;
       do{
           System.out.println("1. Ajouter une ticket");
           System.out.println("2. Show all tickets");
           System.out.println("3. show ticket by id");
           System.out.println("4. delete ticket");
           System.out.println("0. retour");
           System.out.println("Choose an option :");
           choix = input.nextInt();

           switch(choix){
               case 1:

                   break;
               case 2:
                   tdao.getAllTickets();
                   break;
               case 3:
                   System.out.print("Enter the ticket id :");
                   int id = input.nextInt();
                   tdao.getTicketById(id);
                   break;
               case 4:
                   System.out.print("Enter ticket id that you want to delete :");
                   id = input.nextInt();
                   tdao.deleteTicket(id);
                   break;

           }
       } while (choix != 0);
    }

    public void filmMenu() throws SQLException {
        int choix;
        do{
            System.out.println("1.ajouter une film");
            System.out.println("2. Show all films");
            System.out.println("3. show film by id");
            System.out.println("4. delete film");
            System.out.println("0. retour");
            System.out.print("choose an option :");
            choix = input.nextInt();

            switch(choix){
                case 1:
                    System.out.print("Enter the film title : ");
                    String titre = input.nextLine();
                    System.out.print("Enter the film duration :");
                    String duree = input.nextLine();
                    System.out.print("Enter the film categorie :");
                    String categorie = input.nextLine();
                    fdao.addFilm(titre,duree,categorie);
                    break;
                case 2:
                    fdao.getAllFilms();
                    break;
                case 3:
                    System.out.print("Enter the film id :");
                    int id = input.nextInt();
                    fdao.getFilmByid(id);
                    break;
                case 4:
                    System.out.print("Enter film id that you want to delete :");
                    id = input.nextInt();
                    fdao.deleteFilm(id);
                    break;
                default :
                    System.out.println("invalid choice");
            }
        } while (choix != 0);
    }

    public void seanceMenu() throws SQLException {
        int choix;
        do{
            System.out.println("1.ajouter une seance");
            System.out.println("2. Show all seances");
            System.out.println("3. show seance by id");
            System.out.println("4. delete seance");
            System.out.println("0. retour");
            System.out.print("choose an option :");
            choix = input.nextInt();
            input.nextLine();

            switch (choix){
                case 1:
                    System.out.print("Enter the date : ");
                    String date = input.nextLine();
                    System.out.print("Enter horaire :");
                    String horaire = input.nextLine();
                    System.out.print("Enter prix :");
                    float prix = input.nextFloat();
                    System.out.print("Enter capacite maximal :");
                    int capaciteMaximale = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter salle :");
                    String salle = input.nextLine();
                    System.out.print("Enter the film id :");
                    int filmId = input.nextInt();
                    sdao.addSeance(date,horaire,prix,capaciteMaximale,salle,filmId);
                    break;
                case 2:
                    sdao.getAllSeances();
                    break;
                case 3:
                    System.out.print("Enter the seance id :");
                    int id = input.nextInt();
                    sdao.getSeanceByid(id);
                    break;
                case 4:
                    System.out.println("Enter the seance id :");
                    id = input.nextInt();
                    sdao.deleteSeance(id);
                    break;
                default :
                    System.out.println("invalid choice");
            }
        }while (choix != 0);
    }
}
