package gestionScolaire.metier.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@SequenceGenerator(name = "seq_evenement",sequenceName="seq_evenement", initialValue = 1, allocationSize = 1)
public class Evenement {

	private Long id;
	private Date dateDebut;
	private Date dateFin;
	private Classe classe;
	private Matiere matiere;
	private Salle salle;
	private Personne personne;
	private Etablissement etablissement;
	private int version;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_evenement")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "dateDebut")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	@Column(name = "dateFin")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@ManyToOne
	@JoinColumn(name="classe_id")
	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	@ManyToOne
	@JoinColumn(name = "matiere_id")
	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	@ManyToOne
	@JoinColumn(name="salle_id")
	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	@ManyToOne
	@JoinColumn(name="personne_id")
	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	@ManyToOne
	@JoinColumn(name="etablissement_id")
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

	public Evenement() {
		super();
	}

	public Evenement(Date dateDebut, Date dateFin, Classe classe, Matiere matiere, Salle salle, Personne personne,
			Etablissement etablissement) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.classe = classe;
		this.matiere = matiere;
		this.salle = salle;
		this.personne = personne;
		this.etablissement = etablissement;
	}
}
