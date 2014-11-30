package DatabaseConnection;

import DatabaseConnection.ConnectionFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kelvin
 */
public class DBConnectionImp extends ConnectionFactory {

    private String _driver = "com.mysql.jdbc.Driver";
    private String _password = "";
    private String _url = "jdbc:mysql://127.0.0.1:3306/school";
    private String _username = "root";

    public Connection getConnection() {
        try {

            Class.forName(_driver);
            Connection connection = DriverManager.getConnection(_url, _username, _password);
            return connection;
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

}
