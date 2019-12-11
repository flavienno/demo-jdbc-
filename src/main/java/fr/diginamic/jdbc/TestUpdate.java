/**
 * 
 */
package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;

/**
 * @author fla
 *
 */
public class TestUpdate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		FournisseurDaoJdbc fournisseurUpdate = new FournisseurDaoJdbc();
		fournisseurUpdate.update("La Maison de la Peinture", "La Maison des Peintures");

	}
}
