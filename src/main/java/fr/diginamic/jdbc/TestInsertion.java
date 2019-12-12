/**
 * 
 */
package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Fournisseur;

/**
 * @author fla
 *
 */
public class TestInsertion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FournisseurDaoJdbc fournisseurUpdate = new FournisseurDaoJdbc();
		Fournisseur fournisseur = new Fournisseur(4, "La Maison de la Peinture");
		fournisseurUpdate.insert(fournisseur);

	}
}
