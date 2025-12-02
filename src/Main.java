import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import dao.FilmDAO;
import dao.SeanceDAO;
import dao.SpectateurDAO;
import dao.TicketDAO;
import utils.DBConnection;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        Connection con1 = DBConnection.getConnection();
        TicketDAO tdao1 = new TicketDAO(con1);
        FilmDAO fdao1 = new FilmDAO(con1);
        SeanceDAO sdao1 = new SeanceDAO(con1);
        SpectateurDAO specdao1 = new SpectateurDAO(con1);
        AdminMenu menu1 = new AdminMenu(tdao1,fdao1,sdao1,specdao1);
        UserMenu usermenu1 = new UserMenu(tdao1,fdao1,sdao1,specdao1);
        int choix;
        do{
            System.out.println("1. Admin menu");
            System.out.println("2. User menu");
            System.out.println("0.Exit");
            System.out.print("Select menu :");
            choix = input.nextInt();
            switch(choix){
                case 1:
                    menu1.mainAdminMenu();
                    break;
                case 2:
                    usermenu1.Mainmenu();
                    break;
                default:
                    System.out.println("Invalid choice !");
            }
        }while(choix !=0);
    }
}


