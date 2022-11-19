package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Jogo
 *
 */
@Entity

public class Jogo implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	private int id;
	
	@Column
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="id_estudio")
	private Estudio estudio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estudio getEstudio() {
		return estudio;
	}

	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}

	@Override
	public String toString() {
		return "Jogo [id=" + id + ", nome=" + nome + ", estudio=" + estudio + "]";
	}
	
	
	
   
}
