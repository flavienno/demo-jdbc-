/**
 * 
 */
package fr.diginamic.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author fla
 *
 */
public class TestConnexion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		String userName = monFichierConf.getString("database.user");
		String passWord = monFichierConf.getString("database.password");
		String driver = monFichierConf.getString("database.driver");
		String url = monFichierConf.getString("database.url");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {

			System.out.println("Driver non trouvé");
		}
		try {
			Connection maConnection = DriverManager.getConnection(url, userName, passWord);
			System.out.println("Connexion établie");
			maConnection.close();
		} catch (SQLException e) {

			System.out.println("Impossible à établir une connexion");
		}

	}

}
