import java.sql.Connection;
import java.sql.SQLException;

import dao.FilmDAO;
import dao.SeanceDAO;
import dao.SpectateurDAO;
import dao.TicketDAO;
import utils.DBConnection;

public class Main {

    public static void main(String[] args) throws SQLException {

        Connection con1 = DBConnection.getConnection();
        TicketDAO tdao1 = new TicketDAO(con1);
        FilmDAO fdao1 = new FilmDAO(con1);
        SeanceDAO sdao1 = new SeanceDAO(con1);
        SpectateurDAO specdao1 = new SpectateurDAO(con1);
        AdminMenu menu1 = new AdminMenu(tdao1,fdao1,sdao1,specdao1);
        menu1.mainAdminMenu();
    }
}


