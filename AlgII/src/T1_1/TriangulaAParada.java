package T1_1;

import java.util.ArrayList;

public class TriangulaAParada {
	private Leitor auxDoLeitor = new Leitor();
	private Mergesort ordenador = new Mergesort();
	private Mina[] minas, minasEsq, minasDir, minasCima, minasBaixo;
	private int cont, limite;
	private ArrayList<Integer> listaLimites = new ArrayList<Integer>();
	private CampoRef campoFoda;
	
	public void preparaOsEsquemas() {
		auxDoLeitor.leitor();
		minas = auxDoLeitor.getListaDeMinas();
//		minasEsq = ordenador.sort(minas, 0).clone();
//		minasDir = ordenador.sort(minas, 1).clone();
		minasEsq = ordenador.sort(minas, 2).clone();
		minasDir = ordenador.sort(minas, 3).clone();
		limite = auxDoLeitor.getTamCampoX() -1;
		chamaGod();
	}
	
	//0 é esquerda
	//OrdX é para cima e para baixo. OrdY é para esquerda e direita
	public void chamaGod() {
		for(int i=0; i<minasEsq.length; i++) {
			do {
				analisaArea(2, i, 0);
				System.out.println(cont);
				cont = 0;
				if(listaLimites.isEmpty()) limite = auxDoLeitor.getTamCampoX();
				else limite = listaLimites.remove(0);
			} while(limite != auxDoLeitor.getTamCampoX());
		}
	}
	
	public void analisaArea(int direcao, int aPoderosa, int y) {
		Mina[] minasOrdenadas;
		Mina[] minasAntagonicas;
		if(direcao == 2) {
			minasOrdenadas = minasEsq; 
			minasAntagonicas = minasDir;
		}
//		else if(direcao == 1) {
//			minasOrdenadas = minasDir; 
//			minasAntagonicas = minasEsq;
//		}
//		else if(direcao == 2) {
//			minasOrdenadas = minasCima;
//			minasAntagonicas = minasBaixo;
//		}
		else {
			minasOrdenadas = minasBaixo; 
			minasAntagonicas = minasCima;
		}
		
		//Garante que o y receba somente na primeira execução o yOrigem
		if(cont == 0) {
			y = minasOrdenadas[aPoderosa].getY();
		}
		
//		if(y < minasOrdenadas[aPoderosa].getY()) {
//			minasOrdenadas = minasAntagonicas;
//			if(aPoderosa+1 == minasOrdenadas.length) {return;}
//		}
		
		for(int i = aPoderosa+1; i < minasOrdenadas.length; i++) {
				
			if(minasOrdenadas[i].getY() == y && minasOrdenadas[i].getX() < limite) {
				if(cont == 0) limite = minasOrdenadas[i].getX(); 
				else {
					listaLimites.add(minasOrdenadas[i].getX()); 
					return;
				}
				//Atualiza o cont
				cont += minasOrdenadas[i].getX() - minasOrdenadas[aPoderosa].getX() - 1;
				
				//Chama a recursão
				if(y >= minasOrdenadas[aPoderosa].getY()) {
					analisaArea(direcao, aPoderosa, y+1);
				}
				if(y <= minasOrdenadas[aPoderosa].getY()) {
//					for(int j=0; i<minasAntagonicas.length; j++) {
//						if(minasAntagonicas[j] == minasOrdenadas[aPoderosa]) aPoderosa = j;
//					}
					analisaArea(direcao, aPoderosa, y-1);
				}		
				return;
			}
			else if(i == minasOrdenadas.length-1){
				cont += limite - minasOrdenadas[aPoderosa].getX() -1;
				if(y >= minasOrdenadas[aPoderosa].getY() && y <= auxDoLeitor.getTamCampoY()) {
					analisaArea(direcao, aPoderosa, y+1);
				}
				if(y <= minasOrdenadas[aPoderosa].getY() && y >= 0) {
//					for(int j=0; i<minasAntagonicas.length; j++) {
//						if(minasAntagonicas[j].equals(minasOrdenadas[aPoderosa])) {
//							aPoderosa = j; 
//							break;
//						}
//					}
					analisaArea(direcao, aPoderosa, y-1);
				}	
				return;
			}
		}
	}
}
