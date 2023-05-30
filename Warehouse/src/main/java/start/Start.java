package start;

import businessLogic.ClientBLL;
import model.Client;
import presentation.Controller;
import presentation.view.StartView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class
 * @author david
 */
public class Start {
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    /**
     *
     * @param args
     * @throws SQLException
     */

    public static void main(String[] args) throws SQLException {
        Controller controller = new Controller();
    }
}
