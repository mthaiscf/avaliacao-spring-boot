package br.com.tokiomarine.seguradora.avaliacao.entidade;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Estudante {
	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Nome é Obrigatório")
	private String nome;
	
	@NotBlank(message = "E-mail é Obrigatório")
	private String email;
	
	private String telefone;
	
	@NotBlank(message = "Matrícula é obrigatória")
	private String matricula;
	
	private String curso;
	
	
	protected Estudante() {}
	
	public Estudante(String nome, String email, String telefone, String matricula, String curso) {		
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.matricula = matricula;
		this.curso = curso;
	}
	
	
	
	public Long getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}


	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}	
