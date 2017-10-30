package main.dijkstra;

import java.util.ArrayList;

public class Dijkstra {

	private Rota[][] rotas;
	private int inicio, fim;

	public Dijkstra(Rota [] rota, int inicio, int fim) {
		this.rotas = initMatriz(rota);
		this.inicio = inicio;
		this.fim = fim;
	}

	public static int pesoDaRota(ArrayList<Rota> rota) {
		int res = 0;
		if(rota.isEmpty())
			return 0;
		for (Rota r : rota) {
			res += r.getPeso();
		}

		return res;

	}

	public String getOutput() {
		StringBuilder builder = new StringBuilder();
		int tempoRes;
		tempoRes = Dijkstra.pesoDaRota(rotaMaisCurta());
		if(this.rotaMaisCurta().isEmpty())
			return "f";
		for (Rota r : this.rotaMaisCurta()) {
			builder.append(r + "\n");
		}

		builder.append(tempoRes);
		return builder.toString();
	}

	public ArrayList<Rota> rotaMaisCurta(){
		ArrayList<Rota> res = new ArrayList<Rota>();
		resolve(this.inicio, new Pilha(), res);
		return res;
	}

	private void resolve(int inicio, Pilha tempRes, ArrayList<Rota> currRes) {

		//System.out.println(String.format("Saida de: %d ate %d\n", inicio, fim));
		Rota[] lin = rotas[inicio];
		if(tempRes== null)
			tempRes = new Pilha();

		for (int i = 0; i < lin.length; i++) {
			if(lin[i] != null) {
				tempRes.push(lin[i]);
				System.out.println(String.format("Estou em linha %d coluna %d com peso %d\n", inicio, i, lin[i].getPeso()));
				//System.out.println("Pushed! TEMPRES: " + tempRes);
				if(tempRes.getLast().getSaida() == fim || inicio == fim) {
				//	System.out.println("Achei rota: " + tempRes + "RES: " + currRes);
				//	System.out.println("Peso resultante: " + pesoDaRota(tempRes.getList()));
					if(pesoDaRota(tempRes.getList()) < pesoDaRota(currRes) || currRes.isEmpty() ) {
						currRes.clear();
					//	if(tempRes.getLast().getSaida() != fim)
					//		tempRes.pop();
						System.out.println("Achei rota: " + tempRes);
						for (Rota rota : tempRes.getList()) {
							currRes.add(rota);
						}
					}
					tempRes.pop();
					//break;
				}else {
					resolve(i, tempRes, currRes);
					tempRes.pop();
				}


			}
		}

	}

	public Rota[][] initMatriz(Rota[] rotas){
		Rota res[][];
		int size = 0;

		for (Rota r : rotas)
			if(r.getChegada() > size)
				size = r.getChegada();

		res = new Rota[size][size ];

		for (Rota r : rotas) {
			res[r.getSaida() - 1][r.getChegada() - 1] = r;
			///res[ r.getChegada() - 1][r.getSaida() - 1] = r;	///TODO: Perguntar na aula se precisa da matriz espelhada
			//System.out.println(r);
		}


		System.out.println("\t0\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t11\t\t12\t\t13\t\t14\t\t15\t\t16\t\t17\t\t18\t\t19");
		System.out.println();
		for (int i = 0; i < res.length; i++) {
			System.out.print(i + ": ");
			for (int j = 0; j < res[i].length; j++) {
				if(res[i][j] == null)
					System.out.print("\t0\t|");
				else
					System.out.print("\t" + res[i][j].getPeso() + "\t|");
			}
			System.out.println("\n\t----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		}
		return res;
	}

	public void mostraResposta(ArrayList<Rota> rotas, StringBuilder res, int i) {

		Rota tmp1 = null, tmp2 = null;
		if(i + 1 < rotas.size())
			if(rotas.get(i).getCor().equals(rotas.get(i + 1).getCor()) ) {
				tmp1 = rotas.get(i);
				tmp2 = rotas.remove(i + 1);
				tmp1.setChegada(tmp2.getChegada());
				tmp1.setPeso(tmp1.getPeso() + tmp2.getPeso());
				mostraResposta(rotas, res, i++);
			}
			if(!rotas.isEmpty()) {
				tmp1 = rotas.remove(i);
				res.append(String.format("Pegar linha %s de %d a %d (%d)\n", tmp1.getCor(), tmp1.getSaida(), tmp1.getChegada(), tmp1.getPeso()));
				mostraResposta(rotas, res, i++);
				}

	}


}
