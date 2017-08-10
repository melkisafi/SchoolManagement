package gestionScolaire.metier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

@Entity
@SequenceGenerator(name = "seq_sub", initialValue = 1, allocationSize = 1)
public class Matiere {
	private Long idMatiere;
	private String nomMatiere;
	private String couleurMatiere;
	private int version;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sub")
	public Long getIdMatiere() {
		return idMatiere;
	}

	public void setIdMatiere(Long idMatiere) {
		this.idMatiere = idMatiere;
	}

	@Column(unique = true)
	public String getNomMatiere() {
		return nomMatiere;
	}

	public void setNomMatiere(String nomMatiere) {
		this.nomMatiere = nomMatiere;
	}

	@Column(unique = true)
	public String getCouleurMatiere() {
		return couleurMatiere;
	}

	public void setCouleurMatiere(String couleurMatiere) {
		this.couleurMatiere = couleurMatiere;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Matiere() {
		super();
	}

	public Matiere(String nomMatiere, String couleurMatiere) {
		super();
		this.nomMatiere = nomMatiere;
		this.couleurMatiere = couleurMatiere;
	}
}
