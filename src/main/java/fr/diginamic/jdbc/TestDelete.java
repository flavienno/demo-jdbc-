/**
 * 
 */
package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * @author fla
 *
 */
public class TestDelete {

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
		Connection maConnexion = null;
		try {
			maConnexion = DriverManager.getConnection(url, userName, passWord);
			System.out.println("Connexion établie");

			Statement monStatement = maConnexion.createStatement();
			int nb = monStatement.executeUpdate("DELETE FROM FOURNISSEUR WHERE NOM='La Maison des Peintures'");

			maConnexion.close();
		} catch (SQLException e) {

			System.out.println("Impossible d'établir une connexion");
		} finally {
			try {
				if (maConnexion != null) {
					maConnexion.close();
				}
			} catch (SQLException e) {
				System.err.println("Impossible de fermer la connexion à la base de données");
			}

		}

	}
}
