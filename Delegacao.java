package Delegacao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Delegacao {
	private ArrayList<Atleta> atletas;
	
	public Delegacao() {
		this.atletas = new ArrayList<Atleta>();
	}
	public String[] leValores (String[] dadosIn, String[] estiloChoices) {
		String[] dadosOut = new String[dadosIn.length];
		String[] modalidadeChoices = {"Olímpico", "Paraolímpico"}; // escolhas para modalidades

		int i;
		for (i = 0; i < dadosIn.length; i++)
			if (i == 2) {
				dadosOut[i] = (String) JOptionPane.showInputDialog(null, "Escolha a modalidade:",
						"Menu", JOptionPane.PLAIN_MESSAGE, null, modalidadeChoices, modalidadeChoices[0]);
			}
			else if (i == 3) {
				dadosOut[i] = (String) JOptionPane.showInputDialog(null, "Escolha o estilo:",
						"Menu", JOptionPane.PLAIN_MESSAGE, null, estiloChoices, estiloChoices[0]);
			}
			else {
			dadosOut[i] = JOptionPane.showInputDialog("Entre com " + dadosIn[i] + ": ");
			}

		return dadosOut;
	}
	public Corredor leCorredor() {
		
		String [] nomeVal = {"Nome", "Número", "Tipo", "Estilo", "Melhor velocidade média (km/h)", "Cadência (número de passadas/minuto)"};
		String[] estiloChoices = {"Corrida rasa", "Corrida com barreiras", "Corrida com obstáculos"}; // escolhas para estilo
		String [] valores = leValores(nomeVal, estiloChoices);
		
		int numero = this.retornaInteiro(valores[1]);
		float velocidadeMedia = this.retornaFloat(valores[4]);
		int cadencia = this.retornaInteiro(valores[5]);
		
		Corredor corredor = new Corredor (valores[0], numero, valores[2], valores[3], velocidadeMedia, cadencia);
		return corredor;
	}
	public Saltador leSaltador() {
			
			String [] nomeVal = {"Nome", "Número", "Tipo", "Estilo", "Melhor altura/distância de salto (metros)"};
			String[] estiloChoices = {"Salto vertical", "Salto horizontal"}; // escolhas para estilo
			String [] valores = leValores(nomeVal, estiloChoices);
			
			int numero = this.retornaInteiro(valores[1]);
			float alturaMaxSalto = this.retornaFloat(valores[4]);
			
			Saltador saltador = new Saltador (valores[0], numero, valores[2], valores[3], alturaMaxSalto);
			return saltador;
	}
	public Nadador leNadador() {
			
			String [] nomeVal = {"Nome", "Número", "Tipo", "Estilo", "Melhor tempo (segundos)", "Número de braçadas/minuto"};
			String[] estiloChoices = {"Nado borboleta", "Nado costas", "Nado peito", "Nado livre", "Medley"}; // escolhas para estilo
			String [] valores = leValores(nomeVal, estiloChoices);
			
			int numero = this.retornaInteiro(valores[1]);
		    float melhorTempo = this.retornaFloat(valores[4]);
			int numeroBracadas = this.retornaInteiro(valores[5]);

			Nadador nadador = new Nadador (valores[0], numero, valores[2], valores[3], melhorTempo, numeroBracadas);
			return nadador;
	}
	private boolean floatValido(String s) {
		try {
			Float.parseFloat(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public float retornaFloat(String entrada) {
		float numFloat;
		
		while(!this.floatValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número real.");
		}
		return Float.parseFloat(entrada);
	}
	private boolean intValido(String s) {
		try {
			Integer.parseInt(s);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}
	}
	public int retornaInteiro(String entrada) {
		int numInt;
		
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto\n\nDigite um numero inteiro.");
		}
		return Integer.parseInt(entrada);
	}
	public void salvaAtletas (ArrayList<Atleta> atletas) {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream
						(new FileOutputStream("c:\\temp\\delegacao.dados"));
			for (int i=0; i < atletas.size(); i++)
				outputStream.writeObject(atletas.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Impossível criar o arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("finally")
	public ArrayList<Atleta> recuperaAtletas(){
		ArrayList<Atleta> atletasTemp = new ArrayList<Atleta>();
		
		ObjectInputStream inputStream = null;
		
		try {
			inputStream = new ObjectInputStream
					(new FileInputStream("c:\\temp\\delegacao.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject())!= null) {
				if (obj instanceof Atleta) {
					atletasTemp.add((Atleta) obj);
				}
			}
		} catch (EOFException ex) {
			System.out.println("Fim do arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Arquivo com atletas não existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return atletasTemp;
		}
	}
	public void menuDelegacao() {
		
		String menu = "";
		String entrada;
		int opc1, opc2;
		
		do {
			menu = "Controle Delegação\n" +
					"Opções: \n" +
					"1. Entrar Atletas\n" +
					"2. Exibir Atletas\n" +
					"3. Limpar Atletas\n" +
					"4. Gravar Atletas\n" +
					"5. Recuperar Atletas\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);
			
			switch (opc1) {
			case 1:
				menu = "Entrada de Atletas\n" +
						"Opções:\n" +
						"1. Corredor\n" +
						"2. Saltador\n" +
						"3. Nadador\n";
				
				entrada = JOptionPane.showInputDialog(menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);
				
				switch(opc2) {
				case 1: atletas.add((Atleta)leCorredor());
				break;
				case 2: atletas.add((Atleta)leSaltador());
				break;
				case 3: atletas.add((Atleta)leNadador());
				break;
				default:
					JOptionPane.showMessageDialog(null, "Atleta para entrada NÃO escolhido!");
				}
						
				break;
			case 2:
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null, "Entre com atletas primeiramente");
					break;
				}
				String dados = "";
				for (int i = 0; i < atletas.size(); i++) {
					dados += atletas.get(i).toString() + "--------------\n";
				}
				JOptionPane.showMessageDialog(null, dados);
				break;
			case 3:
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null, "Entre com atletas primeiramente!");
					break;
				}
				atletas.clear();
				JOptionPane.showMessageDialog(null, "Dados LIMPOS com sucesso!");
				break;
			case 4:
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null, "Entre com atletas primeiramente");
					break;
				}
				salvaAtletas(atletas);
				JOptionPane.showMessageDialog(null, "Dados SALVOS com sucesso!");
				break;
			case 5: 
				atletas = recuperaAtletas();
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null, "Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null, "Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null, "Fim do aplicativo DELEGAÇÃO!");
				break;
			}
		} while (opc1 != 9);
	}
	
	public static void main (String []  args) {
		
		Delegacao del = new Delegacao ();
		del.menuDelegacao();
	}
}
