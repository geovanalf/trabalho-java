package Delegacao;

public class Nadador extends Atleta{
	private float melhorTempo;
	private float numeroBracadas;
	private static final long serialVersionUID = 1L;
	
	public Nadador(String nome, int numero, String modalidade, String estilo, float melhorTempo, int numeroBracadas) {
		super(nome, numero, modalidade, estilo);
		this.tipo = "Nadador";
		this.melhorTempo = melhorTempo;
		this.numeroBracadas = numeroBracadas;
	}

	@Override
	public String Modalidade() {
		String retorno = "";
		retorno +="Melhor tempo (segundos): " + this.melhorTempo + "\n";
		retorno +="Número de braçadas/minuto: " + this.numeroBracadas + "\n";
		return retorno;
	}
}
