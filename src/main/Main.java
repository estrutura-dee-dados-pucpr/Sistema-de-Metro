package main;

import main.dijkstra.Rota;
import main.gui.Gui;

public class Main {

	public static void main(String args[]) {
		Rota[] rotas = {new Rota(1, 2, 16, "vermelho"),new Rota(2, 3, 14, "vermelho"), new Rota(3, 4, 12, "vermelho"), new Rota(4, 5, 12, "vermelho"), new Rota(5, 6, 14, "vermelho")
				, new Rota(7, 8, 15, "verde"),new Rota(8, 9, 11, "verde"), new Rota(9, 10, 13, "verde"), new Rota(10, 11, 16, "verde"), new Rota(11, 6, 15, "verde")
				, new Rota(12, 9, 17, "azul"), new Rota(9, 13, 7, "azul"), new Rota(13, 14, 9, "azul"), new Rota(14, 5, 9, "azul"), new Rota(5, 17, 10, "azul")
				, new Rota(12, 8, 11, "amarelo"), new Rota(8, 2, 8, "amarelo"), new Rota(2, 15, 7, "amarelo"), new Rota(15, 16, 7, "amarelo"), new Rota(16, 17, 12, "amarelo"), new Rota(17, 18, 9, "amarelo")
				, new Rota(10, 13, 11, "roxo"), new Rota(13, 3, 13, "roxo"), new Rota(3, 16, 11, "roxo"), new Rota(16, 19, 13, "roxo"), new Rota(19, 20, 12, "roxo") };


		Gui gui = new Gui(rotas);

	}

}
