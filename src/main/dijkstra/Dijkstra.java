package main.dijkstra;

import java.util.ArrayList;

public class Dijkstra {

	private Rota[] rotas;

	public Dijkstra(Rota [] rota) {
		this.rotas = rota;
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

	public void resolve(Rota[][] r, int inicio, int fim, Pilha tempRes, ArrayList<Rota> currRes) {

		Rota[] lin = r[inicio];
		for (int i = 0; i < lin.length; i++) {
			if(lin[i] != null) {
				tempRes.push(lin[i]);
				if(tempRes.getLast().getSaida() == fim) {
					System.out.println("Achei rota: " + tempRes);
					System.out.println("Peso resultante: " + pesoDaRota(tempRes.getList()));
					if(pesoDaRota(tempRes.getList()) < pesoDaRota(currRes) || currRes.isEmpty() ) {
						currRes.clear();
						System.out.println("Achei rota: " + tempRes);
						for (Rota rota : tempRes.getList()) {
							currRes.add(rota);
						}
					}
					tempRes.pop();
					break;
				}else {
					resolve(r, i, fim, tempRes, currRes);
					tempRes.pop();
				}


			}
		}

	}

	public Rota[][] initMatriz(){
		Rota res[][];
		int size = 0;

		for (Rota r : rotas)
			if(r.getChegada() > size)
				size = r.getChegada();

		res = new Rota[size][size];

		for (Rota r : rotas) {
			res[r.getSaida() - 1][r.getChegada() - 1] = r;
			System.out.println(r);
		}

		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				if(res[i][j] == null)
					System.out.print("0\t");
				else
					System.out.print(res[i][j].getPeso() + "\t");
			}
			System.out.println();
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
