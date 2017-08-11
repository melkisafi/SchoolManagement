package gestionScolaire.metier.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

@Entity
@SequenceGenerator(name = "seq_matieresalle",sequenceName="seq_matieresalle", initialValue = 1, allocationSize = 1)
public class MatiereSalle {

	
	private Long id;
	private Salle salle;
	private Matiere matiere;
	private int version;
	
	public MatiereSalle() {
	}
	
	public MatiereSalle(Salle salle, Matiere matiere) {
		super();
		this.salle = salle;
		this.matiere = matiere;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_matieresalle")
	public Long getId() {
		return id;
	}
	
	
	public void setId(Long id) {
		this.id = id;
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
	@JoinColumn(name="matiere_id")
	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
