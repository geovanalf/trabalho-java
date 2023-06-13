package Delegacao;

import java.util.Arrays;

public class Saltador extends Atleta{
	private float alturaMaxSalto;
	private static final long serialVersionUID = 1L;
	
	public Saltador(String nome, int numero, String modalidade, String estilo, float alturaMaxSalto) {
		super(nome, numero, modalidade, estilo);
		this.tipo = "Saltador";
		this.alturaMaxSalto = alturaMaxSalto;
	}

	@Override
	public String Modalidade() {
		String retorno = "";
		retorno += "Melhor altura/distância de salto (metros): " + this.alturaMaxSalto + "\n";
		return retorno;
	}
}
