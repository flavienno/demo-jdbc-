package fr.diginamic.jdbc;

import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.dao.ArticleDaoJdbc;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestJdbcArticles {

	public static void main(String[] args) {

		// creation du fournisseur "La Maison de la Peinture"
		FournisseurDaoJdbc fournisseurDao = new FournisseurDaoJdbc();
		fournisseurDao.insert(new Fournisseur(4, "La Maison de la Peinture"));

		// on retrouve l'ID fournisseur à partir de son nom "La Maison de la
		// Peinture"
		Fournisseur fournisseur = fournisseurDao.extraireFournisseur("La Maison de la Peinture");

		// insertion des nouveaux articles
		ArticleDaoJdbc articleDao = new ArticleDaoJdbc();

		articleDao.insert(new Article(11, "B01", "Peinture blanche 1L", (float) 12.5, fournisseur));
		articleDao.insert(new Article(12, "B02", "Peinture rouge mate 1L", (float) 15.5, fournisseur));
		articleDao.insert(new Article(13, "B03", "Peinture noire laquée 1L ", (float) 17.8, fournisseur));
		articleDao.insert(new Article(14, "B04", "Peinture bleue mate 1L", (float) 15.5, fournisseur));

		// modification du prix de certains articles
		// Article article = null;
		// articleDao.updatePrix(article.getPrix(), (float) (article.getPrix() *
		// 0.25));
		// articleDao.updatePrix(article3.getPrix(), (float) (article3.getPrix()
		// * 0.25));
		List<Article> articles = articleDao.extraire();
		for (Article articleObj : articles) {
			articleDao.updatePrix(articleObj.getPrix(), (float) (articleObj.getPrix() * 0.25));

		}

		// affichage de la liste des articles
		// List<Article> articles = articleDao.extraire();
		for (Article articleObj : articles) {
			System.out.println(articleObj);
		}

		// affichage de la moyenne de prix des articles
		System.out.println("La moyenne de prix de ces articles est: " + articleDao.moyenne());

		// // suppresion de tous les articles dont le nom contient "peinture"
		articleDao.deleteArticlePeinture();
		//
		// // suppression du fournisseur « La Maison de la Peinture »
		fournisseurDao.deleteMP();

	}

}
