package main.gui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.dijkstra.Dijkstra;
import main.dijkstra.Rota;

public class Gui extends JFrame implements ActionListener{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	int  saida = -1, chegada = -1, clickCount = 0;

	ArrayList<JButton> buttons = new ArrayList<JButton>();

	Rota[] rotas;
	Color defaultBg = new Color(255, 0, 0), saidaBg = new Color(0, 255, 0), chegadaBg = new Color(0, 0, 255);
	String dspTxt = "Ola! Selecione a saida e a chegada. A saida estara em verde, e o destino  em azul\n";
	//JLabel label = new JLabel(defaultTxt), saidaTxt = new JLabel(""), chegadaTxt = new JLabel(""), resultadoTxt = new JLabel();
	JTextArea txt = new JTextArea("");
	public Gui(Rota[] rotas) {
		JButton go = new JButton("GO!");
		JPanel panel = new JPanel();

		this.setTitle("My Gui");
		this.setSize(800, 800);
		this.rotas = rotas;

		for(int i = 1; i < 21; i++){
			buttons.add(new JButton(String.format("%d", i)));
		}

		for (JButton b : buttons) {
			panel.add(b);
			b.addActionListener(this);
			b.setBackground(defaultBg);
		}
		go.setLocation(new Point(400, 600));
		panel.add(go);
		go.addActionListener(this);
		txt.setText(dspTxt);
		panel.add(txt);

		this.getContentPane().add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	@Override
	public void actionPerformed(ActionEvent e){
		JButton click = (JButton ) e.getSource();

		//System.out.println("CLICK: " + click.getText());
		if(click.getText().equals("GO!")) {
			if(saida == -1) {
				txt.setText("Selecione uma saida\n");
				return;
			}else if(chegada == -1) {
				txt.setText("Selecione uma entrada\n");
				return;
			}
			System.out.println("GO!");
			System.out.println(String.format("Chegada: %d\tSaida: %d\n", chegada, saida));
			Dijkstra dij = new Dijkstra(rotas, saida , chegada);
			txt.setText(dij.getOutput());

			System.out.println("RES: " + dij.getOutput());
			for(JButton b : buttons) {
				b.setBackground(defaultBg);
			}
			return;
		}

		if(clickCount % 2 == 0) {
			buttons.get(saida - 1 < 0 ? 0 : saida - 1).setBackground(defaultBg);
			this.saida = Integer.parseInt(click.getText()) - 1;
			dspTxt = "Ola! Selecione a saida e a chegada. A saida estara em verde, e a chegada em azul\nSaida: " + saida + "\n";
			txt.setText(dspTxt);
			click.setBackground(saidaBg);
		}else {
			buttons.get(chegada - 1 < 0 ? 0 : chegada - 1).setBackground(defaultBg);
			this.chegada = Integer.parseInt(click.getText()) - 1;
			dspTxt  = "Ola! Selecione a saida e a chegada. A saida estara em verde, e a chegada em azul\nSaida: " + saida + "\n" + "Chegada: " + chegada + "\n";
			txt.setText(dspTxt);
			click.setBackground(chegadaBg);
		}

		clickCount++;
	}


	public int getSaida(){
		return saida;

	}


	public int getChegada(){
		return chegada;
	}
}
