package gestionScolaire.metier.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

@Embeddable
public class Adresse {
	private String adresse;
	private String codepostal;
	private String ville;
	private String pays;
	
	public Adresse() {
		super();
	}
	
	public Adresse(String adresse, String codepostal, String ville, String pays) {
		super();
		this.adresse = adresse;
		this.codepostal = codepostal;
		this.ville = ville;
		this.pays = pays;
	}

	@Size(min=3,message="Merci de saisir le numéro et le nom de la rue")
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	@Size(min=3,message="Merci de saisir le code postal")
	public String getCodepostal() {
		return codepostal;
	}
	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}
	
	@Size(min=3,message="Merci de saisir la ville")
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	@Size(min=3,message="Merci de saisir le pays")
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	
	
}
