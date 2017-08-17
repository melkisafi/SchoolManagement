package gestionScolaire.metier.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@SequenceGenerator(name = "seq_salle",sequenceName="seq_salle", initialValue = 1, allocationSize = 1)
public class Salle {
	private Long id;
	private String nom;
	private int capacite;
	private List<Evenement> evenements;
	private List<SalleClasse> salleClasses;
	private List<MatiereSalle> matiereSalles;
	private Etablissement etablissement;
	private int version;
	public Salle(){	
	}

	public Salle(String nom, int capacite, Etablissement etablissement) {
		super();
		this.nom = nom;
		this.capacite = capacite;
		this.etablissement = etablissement;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_salle")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Size(min=3, message="Merci de saisir le nom de la salle")
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Min(value = 1)
	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	
	@OneToMany(mappedBy="salle")
	public List<Evenement> getEvenements() {
		return evenements;
	}

	public void setEvenements(List<Evenement> evenements) {
		this.evenements = evenements;
	}
	
	@OneToMany(mappedBy="salle")
	public List<SalleClasse> getSalleClasses() {
		return salleClasses;
	}

	public void setSalleClasses(List<SalleClasse> salleClasses) {
		this.salleClasses = salleClasses;
	}

	@OneToMany(mappedBy="matiere")
	public List<MatiereSalle> getMatiereSalles() {
		return matiereSalles;
	}

	public void setMatiereSalles(List<MatiereSalle> matiereSalles) {
		this.matiereSalles = matiereSalles;
	}
	
	@ManyToOne
	@JoinColumn(name="etablissement_id")
//	@NotNull
	@Valid
	public Etablissement getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
