package T1_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Leitor {
	
	private Mina mina;
	private Mina[] listaDeMinas;
	private int tamCampoX, tamCampoY;


	//Leitor dos casos;
	public void leitor(){
		int verificador = -1; //Ajuda a identificar cada mina e verificar em qual linha o leitor está.
		Path path = Paths.get("teste001.txt");
		try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())) {
			String linha = null;
			while ((linha = br.readLine()) != null) {
				// separador: ;
				Scanner sc = new Scanner(linha).useDelimiter(" ");
				if(verificador == -1) { //Lê a primeira linha e cria a lista de minas e a matriz do campo que será análisado.
					tamCampoX = Integer.parseInt(sc.next());
					tamCampoY = Integer.parseInt(sc.next());
					listaDeMinas = new Mina[Integer.parseInt(sc.next())];
					verificador++;
				} else { //Lê o restante do arquivo e setta os x e y de cada mina na lista.
					mina = new Mina(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()));
					listaDeMinas[verificador] = mina;
					//System.out.println(listaDeMinas.toString()); 
					verificador++;
				}
			}
		}
		catch (IOException e) {
			System.err.format("Erro de E/S: %s%n", e);
		}
	}

	public Mina[] getListaDeMinas() {
		return listaDeMinas;
	}
	
	public int getTamCampoX() {
		return tamCampoX;
	}

	public int getTamCampoY() {
		return tamCampoY;
	}
	
}
