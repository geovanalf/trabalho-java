package Delegacao;

import java.io.Serializable;

public abstract class Atleta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private  String nome;
	private  int numero;
	private  String modalidade;
	private String estilo;
	protected String tipo;
	
	public Atleta(String nome, int numero, String modalidade, String estilo) {
		this.nome = nome;
		this.numero = numero;
		this.modalidade = modalidade;
		this.estilo = estilo;

	}
	public String toString() {
		String retorno = "";
		retorno += "Nome: "  + this.nome   + "\n";
		retorno += "Número: " + this.numero + "\n";
		retorno += "Tipo: " + this.tipo + "\n";
		retorno += "Modalidade: " + this.modalidade + "\n";
		retorno += "Estilo: " + this.estilo + "\n";
		retorno += Modalidade()   + "\n";
		return retorno;
	}
	public abstract String Modalidade();
		
}
