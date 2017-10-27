package main.dijkstra;

public class Rota {
	private int saida, chegada, peso;
	private String cor;

	public Rota(int saida, int chegada, int peso, String cor) {
		super();
		this.saida = saida;
		this.chegada = chegada;
		this.peso = peso;
		this.cor = cor;
	}



	public Rota(int saida, int chegada, int peso) {
		this(saida, chegada, peso, null);
	}


	public int getSaida() {
		return saida;
	}

	public void setSaida(int saida) {
		this.saida = saida;
	}

	public int getChegada() {
		return chegada;
	}

	public void setChegada(int chegada) {
		this.chegada = chegada;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}



	public String toString() {

		return String.format("De %d para %d, com peso %d e cor %s",saida, chegada, peso, cor );
	}

}
