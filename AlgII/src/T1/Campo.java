package T1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Campo {
	
	private Mina mina = new Mina(0, 0);;
	private ArrayList<Mina> listaDeMinas;
	private int campo[][] = null;
	private int tamCampoX, tamCampoY;
	
	public Campo(int campo[][], Mina listaDeMina[], int tamCampoX, int tamCampoY) {
		this.campo = campo;
		this.listaDeMinas = new ArrayList<Mina>();
		this.tamCampoX = tamCampoX;
		this.tamCampoY = tamCampoY;
		
	}
	
	public void populaCampo(){
		leitor();
		campo = new int[tamCampoX][tamCampoY];		
		for(int i=0; i<campo.length; i++) {
			mina = listaDeMinas.get(i);
			campo[mina.getX()][mina.getY()] = 1;
		}
	}

	//Leitor dos casos;
	public void leitor(){
		int verificador = -1; //Ajuda a identificar cada mina e verificar em qual linha o leitor está.
		Path path = Paths.get("caso100.txt");
		try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())) {
			String linha = null;
			while ((linha = br.readLine()) != null) {
				// separador: ;
				Scanner sc = new Scanner(linha).useDelimiter(" ");
				if(verificador == -1) { //Lê a primeira linha e cria a lista de minas e a matriz do campo que será análisado.
					tamCampoX = Integer.parseInt(sc.next());
					tamCampoY = Integer.parseInt(sc.next());
					verificador = 0;
				} else { //Lê o restante do arquivo e setta os x e y de cada mina na lista.
					mina.setX(Integer.parseInt(sc.next()));
					mina.setY(Integer.parseInt(sc.next()));
					listaDeMinas.add(mina);
					//System.out.println(listaDeMinas.toString()); 
					verificador = 0;
				}
			}
		}
		catch (IOException e) {
			System.err.format("Erro de E/S: %s%n", e);
		}
	}

	public ArrayList<Mina> getListaDeMinas() {
		return listaDeMinas;
	}

	public int[][] getCampo() {
		return campo;
	}
	
	public int getTamCampoX() {
		return tamCampoX;
	}

	public int getTamCampoY() {
		return tamCampoY;
	}
	
}
