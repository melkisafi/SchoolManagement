package gestionScolaire.metier.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

@Entity
public class Etablissement {
	private Long id;
	private String nom;
	private TypeEtab type;
	private String tel;
	private Adresse adr;
	private byte[] logo;
	private List<PersonneEtablissement> personneEtablissement;
	private List<Evenement> evenements;
	private List<Salle> salles;
	private List<Classe> classes;
	private int version;

	public Etablissement() {
	}
	
	public Etablissement(String nom, TypeEtab type, String tel, Adresse adr) {
		super();
		this.nom = nom;
		this.type = type;
		this.tel = tel;
		this.adr = adr;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column
	@Size(min=3, message="Merci de saisir le nom de l'établissement")
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Enumerated(EnumType.STRING)
	@NotNull(message="Merci de choisir le type de l'établissement")
	public TypeEtab getType() {
		return type;
	}

	public void setType(TypeEtab type) {
		this.type = type;
	}

	@Column
	@Size(min=10, max=10, message="Merci de saisir le n° tel de l'établissement. Celui-ci doit être composé de 10 chiffres")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Embedded
	@Valid
	public Adresse getAdr() {
		return adr;
	}

	
	public void setAdr(Adresse adr) {
		this.adr = adr;
	}

	@Column
	@Lob
	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	@OneToMany(mappedBy = "etablissement", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<PersonneEtablissement> getPersonneEtablissement() {
		return personneEtablissement;
	}

	public void setPersonneEtablissement(List<PersonneEtablissement> personneEtablissement) {
		this.personneEtablissement = personneEtablissement;
	}

	@OneToMany(mappedBy = "etablissement", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Evenement> getEvenements() {
		return evenements;
	}

	public void setEvenements(List<Evenement> evenements) {
		this.evenements = evenements;
	}

	@OneToMany(mappedBy = "etablissement", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Salle> getSalles() {
		return salles;
	}

	public void setSalles(List<Salle> salles) {
		this.salles = salles;
	}

	@Version
	public int getVersion() {
		return version;
	}
	
	@OneToMany(mappedBy = "etablissement", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Classe> getClasses() {
		return classes;
	}

	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
