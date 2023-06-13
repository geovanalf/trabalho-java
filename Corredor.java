package Delegacao;

public class Corredor extends Atleta {
	private float velocidadeMedia;
	private int cadencia;
	private static final long serialVersionUID = 1L;
	
	public Corredor(String nome, int numero, String modalidade, String estilo, float velocidadeMedia, int cadencia) {
		super(nome, numero, modalidade, estilo);
		this.tipo = "Corredor";
		this.velocidadeMedia = velocidadeMedia;
		this.cadencia = cadencia;

	}
	@Override
	public String Modalidade() {
		String retorno = "";
		retorno += "Melhor velocidade média (km/h): " + this.velocidadeMedia + "\n";
		retorno += "Cadência (número de passadas/minuto): " + this.cadencia + "\n";
		return retorno;
	}
}
