package T1;

import java.util.ArrayList;

public class EncontraCampoRef {
		
	private ArrayList<CampoRef> listaDeCampos = new ArrayList<CampoRef>();
	private ArrayList<Integer> novoLimite = new ArrayList<Integer>();	
	private int[][] campo;
	private Campo popCampo;
	private CampoRef campoOcupado;
	private CampoRef campoPerfeito = null;
	private int cont = 0, limite = 0;
	private int xOriginal ,yOriginal; 	
	
	public String start() {
		popCampo = new Campo(null, null, 0, 0);
		popCampo.populaCampo();
		analisaRegiao(popCampo.getListaDeMinas(), popCampo.getCampo());
		return listaDeCampos.toString();
	}
	
	//Chama os métodos do algoritmo:
	public void analisaRegiao(ArrayList<Mina> minas, int[][] campos) {
		campo = campos;
		for(int i=0; i<minas.size()-1; i++) {
			analisaMina(minas.get(i).getX(), minas.get(i).getY());
		}
	}
	
	//Analisa todos os possiveis retangulos de cada MINA.
	public void analisaMina(int x, int y) {
		int i = 0;
		//Garente a primeira execução!
		novoLimite.add(-1);
		do { 
			limite = novoLimite.get(i);
			campoDir(x+1, y);
			campoOcupado.setTamanho(cont);
			listaDeCampos.add(campoOcupado);
			cont = 0;	
			i++;
		} while(i<novoLimite.size());
		novoLimite.clear();
		melhorCampo();		
		//repetir pra esquerda, cima e baixo.
	}
	
	//Analisa o campo livre da direita da mina:
	public void campoDir(int x, int y) {
		//Verifica se x ou y ultrassam o limite, se sim quebra a recursão.
		if(x >= popCampo.getTamCampoX() || y >= popCampo.getTamCampoY() || x < 0 || y < 0) return;  
		int contIn = 0;	
		//Para saber aonde começou a análise
		if(cont == 0) {
			if(limite == x) return; //Verifica se o limite é igual ao x, se for, quebra a chamada.
			yOriginal = y;
			xOriginal = x;
			campoOcupado = new CampoRef(1, null);
		}
		while(campo[x][y] == 0) {
			//caso a leitura chegue no limite, passa para a próxima linha.
			if(limite != -1 && x >= limite) {
				if(y >= yOriginal) { 
					campoOcupado.setPontas(2, xOriginal + ", " + y);
					campoOcupado.setPontas(3, x-1 + ", " + y); 
					campoDir(xOriginal, y+1);
					
				}
				if(y <= yOriginal) {
					campoOcupado.setPontas(0, xOriginal + ", " + y); 
					campoOcupado.setPontas(1, x-1 + ", " + y); 
					campoDir(xOriginal, y-1);
				}
				break;
			}
			cont++;
			contIn++;
			//Para grantir q não ultrapace o X da matriz
			//int quadrado = idMina.getTamCampoX();
			if(x+1 < popCampo.getTamCampoX() && y+1 < popCampo.getTamCampoY()) {
				x++;
			} else {x++; break;}
		}
		
		/*Caso ache-se um 1 na matriz ou chegue ao final dela, procura em mais uma linha da matriz ou cancela a leituera naquela linha.
		 *E indica que pode haver mais de um limite, sendo assim, mais um retângulo possível.
		 *Também verifica se x e y já ultrapassaram o tamnho da linha da matriz.*/
		
		if(x == popCampo.getTamCampoX() || y == popCampo.getTamCampoY() || campo[x][y] == 1){
			if(limite == -1 || x >= limite) {
				limite = x;
				if(y >= yOriginal) {
					campoOcupado.setPontas(2, xOriginal + ", " + y);
					campoOcupado.setPontas(3, x-1 + ", " + y); 
					campoDir(xOriginal, y+1);	
				}
				if(y <= yOriginal) {
					campoOcupado.setPontas(0, xOriginal + ", " + y); 
					campoOcupado.setPontas(1, x-1 + ", " + y);  
					campoDir(xOriginal, y-1);
				}
			} else {
				cont = cont - contIn;
				if(!novoLimite.contains(x)) {
					novoLimite.add(x);
				}
			}
		}	
	}
	
	public void melhorCampo() {
		int aux = 0;
		for(int i = 0; i<listaDeCampos.size(); i++) {
			if(listaDeCampos.get(i).getTamanho() > aux) {
				aux = listaDeCampos.get(i).getTamanho();
				campoPerfeito = listaDeCampos.get(i);
			}
		}
		listaDeCampos.clear();
		listaDeCampos.add(campoPerfeito);
	}
}
