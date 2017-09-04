package T1_1;

import java.util.Arrays;

public class CampoRef {
	private int tamanho;
	private String[] pontas;
	
	public CampoRef(int tamanho, String[] pontas) {
		super();
		this.tamanho = tamanho;
		this.pontas = new String[4];
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public String[] getPontas() {
		return pontas;
	}

	public void setPontas(int i, String txt) {
		this.pontas[i] = txt;
	}

	@Override
	public String toString() {
		return "O maior Campo para abrigar os refugiados tem:\nTamanho = " +
				tamanho + "\nE fica nas cordenadas:\n- " + pontas[0] + "\n- " +
				pontas[1] + "\n- " + pontas[2] + "\n- " + pontas[3];
	}	
	
}
