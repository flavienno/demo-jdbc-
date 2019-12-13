/**
 * 
 */
package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Article;

/**
 * @author fla
 *
 */
public class ArticleDaoJdbc implements ArticleDao {

	private String userName;
	private String url;
	private String passWord;

	public ArticleDaoJdbc() {
		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		userName = monFichierConf.getString("database.user");
		passWord = monFichierConf.getString("database.password");
		String driver = monFichierConf.getString("database.driver");
		url = monFichierConf.getString("database.url");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {

			System.out.println("Driver non trouvé");
		}
	}

	public Double moyenne() {

		Connection maConnexion = null;
		Double moyenne = 0.0;
		try {
			maConnexion = DriverManager.getConnection(url, userName, passWord);
			System.out.println("Connexion établie");

			Statement monStatement = maConnexion.createStatement();
			String requete = "SELECT AVG(PRIX) AS MOY FROM ARTICLE";

			ResultSet result = monStatement.executeQuery(requete);

			if (result.next()) {
				moyenne = result.getDouble("MOY");
			}
			;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Insertion impossible");
		} finally {
			try {
				if (maConnexion != null) {
					maConnexion.close();
				}
			} catch (SQLException e) {
				System.err.println("Impossible de fermer la connexion à la base de données");
			}

		}
		return moyenne;
	}

	/*
	 * 
	 * 
	 * @see fr.diginamic.jdbc.dao.ArticleDao#extraire()
	 */
	@Override
	public List<Article> extraire() {

		// récupération ds monFichierConf des infos de database.properties
		// nécessaires à la connection

		Connection maConnexion = null;
		Statement monStatement = null;
		ResultSet curseur = null;

		// création de la liste d'articles
		ArrayList<Article> articles = new ArrayList<>();

		// tentative de connection
		try {
			maConnexion = DriverManager.getConnection(url, userName, passWord);
			System.out.println("Connexion établie");

			monStatement = maConnexion.createStatement();
			curseur = monStatement.executeQuery("SELECT * FROM ARTICLE");

			// remplissage de la liste
			while (curseur.next()) {
				Integer id = curseur.getInt("ID");
				String ref = curseur.getString("REF");
				String designation = curseur.getString("DESIGNATION");
				Float prix = curseur.getFloat("PRIX");

				Article articleCourant = new Article(id, ref, designation, prix);
				articles.add(articleCourant);

			}

		} catch (SQLException e) {

			System.out.println("Impossible d'établir une liste");
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

		return articles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.diginamic.jdbc.dao.ArticleDao#insert(fr.diginamic.jdbc.entites.
	 * Article)
	 */
	@Override
	public void insert(Article article) {

		Connection maConnexion = null;
		try {
			maConnexion = DriverManager.getConnection(url, userName, passWord);
			System.out.println("Connexion établie");

			Statement monStatement = maConnexion.createStatement();
			String requete = "INSERT INTO ARTICLE (ID,REF,DESIGNATION,PRIX,ID_FOU) VALUES (" + article.getId() + ",'"
					+ article.getRef() + "','" + article.getDesignation() + "'," + article.getPrix() + ","
					+ article.getFournisseur().getId() + ")";

			int nb = monStatement.executeUpdate(requete);
			maConnexion.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Insertion impossible");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.diginamic.jdbc.dao.ArticleDao#update(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String,
	 * fr.diginamic.jdbc.entites.Fournisseur,
	 * fr.diginamic.jdbc.entites.Fournisseur)
	 */
	@Override
	public int update(String ancienRef, String nvRef, String ancienDesignation, String nvDesignation, Float ancienPrix,
			Float nvPrix) {

		Connection maConnexion = null;
		int nb = 0;
		try {
			maConnexion = DriverManager.getConnection(url, userName, passWord);
			System.out.println("Connexion établie");

			Statement monStatement = maConnexion.createStatement();
			nb = monStatement.executeUpdate("UPDATE ARTICLE SET REF='" + nvRef + "' WHERE REF='" + ancienRef
					+ "', DESIGNATION='" + nvDesignation + "' WHERE DESIGNATION='" + ancienDesignation + "', PRIX='"
					+ nvPrix + "' WHERE PRIX='" + ancienPrix + "'");

		} catch (SQLException e) {

			System.out.println("Mise à jour impossible");
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

	public int updatePrix(Float ancienPrix, Float nvPrix) {
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

			Statement monStatement = maConnexion.createStatement();
			nb = monStatement.executeUpdate("UPDATE ARTICLE SET PRIX=" + nvPrix + " WHERE PRIX=" + ancienPrix
					+ " AND DESIGNATION LIKE '%mate%'");
			maConnexion.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Mise à jour impossible");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.diginamic.jdbc.dao.ArticleDao#delete(fr.diginamic.jdbc.entites.
	 * Article)
	 */
	@Override
	public boolean delete(Article article) {

		Connection maConnexion = null;
		try {
			maConnexion = DriverManager.getConnection(url, userName, passWord);
			System.out.println("Connexion établie");

			Statement monStatement = maConnexion.createStatement();
			int nb = monStatement.executeUpdate("DELETE FROM ARTICLE WHERE REF='" + article.getRef() + "'");

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

	public boolean deleteArticlePeinture() {

		Connection maConnexion = null;
		try {
			maConnexion = DriverManager.getConnection(url, userName, passWord);
			System.out.println("Connexion établie");

			Statement monStatement = maConnexion.createStatement();
			int nb = monStatement.executeUpdate("DELETE FROM ARTICLE WHERE DESIGNATION LIKE '%Peinture%'");

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
