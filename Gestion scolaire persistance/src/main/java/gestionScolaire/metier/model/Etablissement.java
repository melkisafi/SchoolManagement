package gestionScolaire.metier.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Version;

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
	private List<EtablissementClasse> etablissementClasses;
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
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Enumerated(EnumType.STRING)
	public TypeEtab getType() {
		return type;
	}

	public void setType(TypeEtab type) {
		this.type = type;
	}

	@Column
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Embedded
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

	@OneToMany(mappedBy = "etablissement")
	public List<PersonneEtablissement> getPersonneEtablissement() {
		return personneEtablissement;
	}

	public void setPersonneEtablissement(List<PersonneEtablissement> personneEtablissement) {
		this.personneEtablissement = personneEtablissement;
	}

	@OneToMany(mappedBy = "etablissement")
	public List<Evenement> getEvenements() {
		return evenements;
	}

	public void setEvenements(List<Evenement> evenements) {
		this.evenements = evenements;
	}

	@OneToMany(mappedBy = "etablissement")
	public List<Salle> getSalles() {
		return salles;
	}

	public void setSalles(List<Salle> salles) {
		this.salles = salles;
	}

	@OneToMany(mappedBy = "etablissement")
	public List<EtablissementClasse> getEtablissementClasses() {
		return etablissementClasses;
	}

	public void setEtablissementClasses(List<EtablissementClasse> etablissementClasses) {
		this.etablissementClasses = etablissementClasses;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
