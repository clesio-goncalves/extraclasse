package web.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique = true)
	private String nome;

	@NotNull
	@ManyToOne
	private Curso curso;

	private boolean ativo;

	@Transient
	private int ano_ingresso;

	@Transient
	private int periodo_ingresso;

	@Transient
	private String tipo_turma;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getAno_ingresso() {
		return ano_ingresso;
	}

	public void setAno_ingresso(int ano_ingresso) {
		this.ano_ingresso = ano_ingresso;
	}

	public int getPeriodo_ingresso() {
		return periodo_ingresso;
	}

	public void setPeriodo_ingresso(int periodo_ingresso) {
		this.periodo_ingresso = periodo_ingresso;
	}

	public String getTipo_turma() {
		return tipo_turma;
	}

	public void setTipo_turma(String tipo_turma) {
		this.tipo_turma = tipo_turma;
	}

}
