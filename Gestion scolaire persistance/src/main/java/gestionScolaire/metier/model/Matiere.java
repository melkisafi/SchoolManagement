package gestionScolaire.metier.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@Entity
@SequenceGenerator(name = "seq_sub",sequenceName="seq_sub", initialValue = 1, allocationSize = 1)
public class Matiere {
	private Long idMatiere;
	private String nomMatiere;
	private String couleurMatiere;
	private List <Evenement> evenements;
	private List <MatiereSalle> matiereSalles;
	private List <PersonneMatiere> personneMatieres;
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
	@Size(min=3, message="Le nom de la mati�re doit avoir au moins 3 caract�res")
	public String getNomMatiere() {
		return nomMatiere;
	}

	public void setNomMatiere(String nomMatiere) {
		this.nomMatiere = nomMatiere;
	}

	@Column(unique = true)
	@Size(min=3, message="La couleur de la mati�re doit avoir selectionn�")
	public String getCouleurMatiere() {
		return couleurMatiere;
	}

	public void setCouleurMatiere(String couleurMatiere) {
		this.couleurMatiere = couleurMatiere;
	}

	@OneToMany(mappedBy="matiere",fetch=FetchType.EAGER)
	public List<Evenement> getEvenements() {
		return evenements;
	}

	public void setEvenements(List<Evenement> evenements) {
		this.evenements = evenements;
	}

	@OneToMany(mappedBy="matiere",fetch=FetchType.EAGER)
	public List<MatiereSalle> getMatiereSalles() {
		return matiereSalles;
	}

	public void setMatiereSalles(List<MatiereSalle> matiereSalles) {
		this.matiereSalles = matiereSalles;
	}

	@OneToMany(mappedBy="matiere",fetch=FetchType.EAGER)
	public List<PersonneMatiere> getPersonneMatieres() {
		return personneMatieres;
	}

	public void setPersonneMatieres(List<PersonneMatiere> personneMatieres) {
		this.personneMatieres = personneMatieres;
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
