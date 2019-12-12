/**
 * 
 */
package fr.diginamic.jdbc.entites;

/**
 * @author fla
 *
 */
public class Article {
	private int id;
	private String ref;
	private String designation;
	private Float prix;
	private Fournisseur fournisseur;

	/**
	 * @param id
	 * @param ref
	 * @param designation
	 * @param prix
	 * @param fournisseur
	 */
	public Article(int id, String ref, String designation, Float prix, Fournisseur fournisseur) {
		super();
		this.id = id;
		this.ref = ref;
		this.designation = designation;
		this.prix = prix;
		this.fournisseur = fournisseur;
	}

	public Article(int id, String ref, String designation, Float prix) {
		super();
		this.id = id;
		this.ref = ref;
		this.designation = designation;
		this.prix = prix;

	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter
	 * 
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}

	/**
	 * Setter
	 * 
	 * @param ref
	 *            the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}

	/**
	 * Getter
	 * 
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * Setter
	 * 
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * Getter
	 * 
	 * @return the prix
	 */
	public Float getPrix() {
		return prix;
	}

	/**
	 * Setter
	 * 
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(Float prix) {
		this.prix = prix;
	}

	/**
	 * Getter
	 * 
	 * @return the fournisseur
	 */
	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	/**
	 * Setter
	 * 
	 * @param fournisseur
	 *            the fournisseur to set
	 */
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Article [id=" + id + ", ref=" + ref + ", designation=" + designation + ", prix=" + prix
				+ ", fournisseur=" + fournisseur + "]";
	}

}
