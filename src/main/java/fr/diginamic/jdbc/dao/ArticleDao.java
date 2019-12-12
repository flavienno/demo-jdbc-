/**
 * 
 */
package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

/**
 * @author fla
 *
 */

public interface ArticleDao {

	/**
	 * fonction permettant d'extraire une liste d'articles
	 * 
	 * @return liste d'articles
	 */
	List<Article> extraire();

	/**
	 * fonction permettant d'insérer un nouvel article
	 * 
	 * @param article
	 */
	void insert(Article article);

	/**
	 * fonction permettant la suppression d'un article
	 * 
	 * @param article
	 * @return article
	 */
	boolean delete(Article article);

	/**
	 * fonction permettant de modifier un article existant en base
	 * 
	 * @param ancienRef
	 * @param nvRef
	 * @param ancienDesignation
	 * @param nvDesignation
	 * @param ancienPrix
	 * @param nvPrix
	 * @return
	 */
	int update(String ancienRef, String nvRef, String ancienDesignation, String nvDesignation, Float ancienPrix,
			Float nvPrix);

}
