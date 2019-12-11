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
import fr.diginamic.jdbc.entites.Fournisseur;

/**
 * @author fla
 *
 */
public class TestDelete {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		FournisseurDaoJdbc fournisseurUpdate = new FournisseurDaoJdbc();
		Fournisseur fournisseur = new Fournisseur(4, "La Maison des Peintures");
		fournisseurUpdate.delete(fournisseur);

	}
}
