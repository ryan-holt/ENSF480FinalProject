package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is responsible for the connection to the database and
 * the database model
 * @author Ryan Holt
 * @since November 25, 2019
 */
public class DatabaseController implements DatabaseCredentials{

    //MEMBER VARIABLES
    public static DatabaseController databaseControllerInstance;
    private Connection myConnection;
    private DatabaseModel databaseModel;

    /**
     * creates a database controller object
     */
    private DatabaseController(){
        initializeConnection();
    }

    /**
     * creates a connection with the database
     */
    public void initializeConnection() {
        try {
            setMyConnection(DriverManager.getConnection(DB_URL, USERNAME, PASSWORD));
            databaseModel = new DatabaseModel(myConnection);
            System.out.println("Connected to Database!");
        } catch (SQLException e) {
            System.out.println("Unable to open database.");
            e.printStackTrace();
        }
    }

    /**
     * closes the connection with the database
     */
    public void close() {
        try {
            myConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //GETTERS AND SETTERS

    /**
     * Singleton design pattern to enforce only once connection between server and database
     * @return DatabaseController instance
     */
    public static DatabaseController getDatabaseControllerInstance(){
        if(databaseControllerInstance == null){
            databaseControllerInstance = new DatabaseController();
        }
        return databaseControllerInstance;
    }

    /**
     * @return the databaseModel
     */
    public DatabaseModel getDatabaseModel() {
        return databaseModel;
    }

    /**
     * @param databaseModel the databaseModel to set
     */
    public void setDatabaseModel(DatabaseModel databaseModel) {
        this.databaseModel = databaseModel;
    }

    /**
     * @return the myConnection
     */
    public Connection getMyConnection() {
        return myConnection;
    }

    /**
     * @param myConnection the myConnection to set
     */
    public void setMyConnection(Connection myConnection) {
        this.myConnection = myConnection;
    }



}
