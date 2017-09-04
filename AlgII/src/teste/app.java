package teste;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import T1_1.Mina;

public class app {
	private static Mina mina;

	public static void main(String[] args) {
		int idMina = 0; //Ajuda a identificar cada mina e verificar em qual linha o leitor está.
		Path path = Paths.get("teste001.txt");
		try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())) {
			String linha = null;
			while ((linha = br.readLine()) != null) {
				// separador: ;
				Scanner sc = new Scanner(linha).useDelimiter(" ");
				Mina[] listaDeMinas = null;
				if(idMina == 0) { //Lê a primeira linha e cria a lista de minas e a matriz do campo que será análisado.
					int tamCampoX = Integer.parseInt(sc.next());
					System.out.println(tamCampoX);
					int tamCampoY = Integer.parseInt(sc.next());
					System.out.println(tamCampoY);
					int[][] campo = new int[tamCampoX][tamCampoY];
					int numeroMinas = Integer.parseInt(sc.next());
					System.out.println(numeroMinas);
					listaDeMinas = new Mina[numeroMinas];
					idMina++;
				} else { //Lê o restante do arquivo e setta os x e y de cada mina na lista.
					mina = new Mina(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()));
					listaDeMinas[idMina] = mina;
					System.out.println(listaDeMinas[idMina].toString()); 
					idMina++;
				} 
			}
		}
		catch (IOException e) {
			System.err.format("Erro de E/S: %s%n", e);
		}

	}

}
