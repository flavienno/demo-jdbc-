package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {

	@Override
	public int update(String ancienNom, String nouveauNom) {
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
		int nb = 0;
		try {
			maConnexion = DriverManager.getConnection(url, userName, passWord);
			System.out.println("Connexion établie");

			// Statement monStatement = maConnexion.createStatement();
			// nb = monStatement
			// .executeUpdate("UPDATE FOURNISSEUR SET NOM='" + nouveauNom + "'
			// WHERE NOM='" + ancienNom + "'");
			PreparedStatement updateFournisseur = maConnexion
					.prepareStatement("UPDATE FOURNISSEUR SET NOM=? WHERE NOM=?");
			updateFournisseur.setString(1, nouveauNom);
			updateFournisseur.setString(2, ancienNom);
			updateFournisseur.executeUpdate();

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
		return nb;
	}

	@Override
	public List<Fournisseur> extraire() {
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
		Statement monStatement = null;
		ResultSet curseur = null;
		ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
		try {
			maConnexion = DriverManager.getConnection(url, userName, passWord);
			System.out.println("Connexion établie");

			monStatement = maConnexion.createStatement();
			curseur = monStatement.executeQuery("SELECT * FROM FOURNISSEUR");

			while (curseur.next()) {
				Integer id = curseur.getInt("ID");
				String nom = curseur.getString("NOM");

				Fournisseur fournisseurCourant = new Fournisseur(id, nom);
				fournisseurs.add(fournisseurCourant);

			}
			for (Fournisseur fournisseur : fournisseurs) {
				System.out.println(fournisseur);
			}

		} catch (SQLException e) {

			System.out.println("Impossible d'établir une connexion");
		} finally {
			try {
				monStatement.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				curseur.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if (maConnexion != null) {
					maConnexion.close();
					System.out.println("Connexion terminée");
				}
			} catch (SQLException e) {
				System.err.println("Impossible de fermer la connexion à la base de données");
			}

		}

		return fournisseurs;
	}

	/*
	 * / fonction qui permet de remonter l'objet fournisseur et donc de
	 * retrouver son ID à partir de son nom
	 */
	public Fournisseur extraireFournisseur(String nomFournisseur) {
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
		Statement monStatement = null;
		ResultSet curseur = null;
		Fournisseur fournisseur = null;
		try {
			maConnexion = DriverManager.getConnection(url, userName, passWord);
			System.out.println("Connexion établie");

			// monStatement = maConnexion.createStatement();
			// curseur = monStatement.executeQuery("SELECT * FROM FOURNISSEUR
			// WHERE NOM='" + nomFournisseur + "'");

			PreparedStatement extraireFournisseur = maConnexion
					.prepareStatement("SELECT * FROM FOURNISSEUR WHERE NOM=?");
			extraireFournisseur.setString(1, nomFournisseur);

			if (curseur.next()) {
				Integer id = curseur.getInt("ID");
				String nom = curseur.getString("NOM");

				fournisseur = new Fournisseur(id, nom);

			}

		} catch (SQLException e) {

			System.out.println("Impossible d'établir une connexion");
		} finally {
			try {
				monStatement.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				curseur.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if (maConnexion != null) {
					maConnexion.close();
					System.out.println("Connexion terminée");
				}
			} catch (SQLException e) {
				System.err.println("Impossible de fermer la connexion à la base de données");
			}

		}

		return fournisseur;
	}

	@Override
	public void insert(Fournisseur fournisseur) {
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
			String requete = "INSERT INTO FOURNISSEUR (ID,NOM) VALUES (" + fournisseur.getId() + ",'"
					+ fournisseur.getNom() + "')";
			int nb = monStatement.executeUpdate(requete);
			maConnexion.commit();
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

	@Override
	public boolean delete(Fournisseur fournisseur) {
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

			// Statement monStatement = maConnexion.createStatement();
			// int nb = monStatement.executeUpdate("DELETE FROM FOURNISSEUR
			// WHERE NOM='La Maison des Peintures'");

			PreparedStatement deleteFournisseur = maConnexion.prepareStatement("DELETE FROM FOURNISSEUR WHERE NOM=?");
			deleteFournisseur.setString(1, "La Maison des Peintures");
			deleteFournisseur.executeUpdate();

			maConnexion.commit();
			return true;
		} catch (SQLException e) {

			System.out.println("Impossible d'établir une connexion");
			return false;
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

	public boolean deleteMP() {
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

			// Statement monStatement = maConnexion.createStatement();
			// int nb = monStatement.executeUpdate("DELETE FROM FOURNISSEUR
			// WHERE NOM='La Maison de la Peinture'");

			PreparedStatement deleteMP = maConnexion.prepareStatement("DELETE FROM FOURNISSEUR WHERE NOM=?");
			deleteMP.setString(1, "La Maison de la Peinture");
			deleteMP.executeUpdate();

			maConnexion.commit();
			return true;
		} catch (SQLException e) {

			System.out.println("Impossible d'établir une connexion");
			return false;
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
