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
@SequenceGenerator(name = "seq_salleclasse",sequenceName="seq_salleclasse", initialValue = 1, allocationSize = 1)
public class SalleClasse {

	private Long id;
	private Salle salle;
	private Classe classe;
	private int version;
	
	public SalleClasse() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_salleclasse")
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
	@JoinColumn(name="classe_id")
	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
