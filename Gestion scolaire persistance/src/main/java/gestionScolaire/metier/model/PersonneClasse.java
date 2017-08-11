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
@SequenceGenerator(name = "seq_personneclasse",sequenceName="seq_personneclasse", initialValue = 1, allocationSize = 1)
public class PersonneClasse {

	private Long id;
	private Personne personne;
	private Classe classe;
	private boolean principal;
	private int version;
	
	public PersonneClasse() {
		
	}
	
	public PersonneClasse(Personne personne, Classe classe, boolean principal) {
		super();
		this.personne = personne;
		this.classe = classe;
		this.principal = principal;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_personneclasse")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name = "personne_id")
	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	@ManyToOne
	@JoinColumn(name = "classe_id")
	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
	
}
