package gestionScolaire.metier.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity	
public class Personne {
	private Long id;
	private Civilite civilite;
	private Role role;
	private Login login;
	private Status status;
	private String nom;
	private String prenom;
	private Date datenaiss;
	private Adresse adresse;
	private List<PersonneEtablissement> personneEtablissement;
	private List<PersonneMatiere> personneMatiere;
	private List<Evenement> evenements;
	private List<PersonneClasse> personneClasses;
	private int version;
	
	
	public Personne(){
	}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Enumerated(EnumType.STRING)
	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}
	
	@Enumerated(EnumType.STRING)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@Column
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Column
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	@Temporal(TemporalType.DATE)
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getDatenaiss() {
		return datenaiss;
	}
	
	public void setDatenaiss(Date datenaiss) {
		this.datenaiss = datenaiss;
	}
	
	@Embedded
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@OneToOne
	@JoinColumn(name="login_id")
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	@ManyToOne
	@JoinColumn(name="status_id")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@OneToMany(mappedBy="personne", fetch=FetchType.EAGER)
	public List<PersonneEtablissement> getPersonneEtablissement() {
		return personneEtablissement;
	}

	public void setPersonneEtablissement(List<PersonneEtablissement> personneEtablissement) {
		this.personneEtablissement = personneEtablissement;
	}

	@OneToMany(mappedBy="personne")
	public List<PersonneMatiere> getPersonneMatiere() {
		return personneMatiere;
	}

	public void setPersonneMatiere(List<PersonneMatiere> personneMatiere) {
		this.personneMatiere = personneMatiere;
	}
	
	@OneToMany(mappedBy="personne")
	public List<Evenement> getEvenements() {
		return evenements;
	}

	public void setEvenements(List<Evenement> evenements) {
		this.evenements = evenements;
	}

	@OneToMany(mappedBy="personne")	
	public List<PersonneClasse> getPersonneClasses() {
		return personneClasses;
	}

	public void setPersonneClasses(List<PersonneClasse> personneClasses) {
		this.personneClasses = personneClasses;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
