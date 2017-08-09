package gestionScolaire.metier.model;

import javax.persistence.Embeddable;

@Embeddable
public class Adresse {
	private String adresse;
	private String codepostal;
	private String ville;
	private String pays;
	
	public Adresse() {
		super();
	}
	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCodepostal() {
		return codepostal;
	}
	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	
	
}
