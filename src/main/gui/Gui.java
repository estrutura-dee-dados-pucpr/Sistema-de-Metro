package main.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;

import main.dijkstra.Dijkstra;
import main.dijkstra.Rota;

public class Gui extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	int saida = -1, chegada = -1, clickCount = 0;
	Point[] points = { new Point(5, 84), new Point(140, 127), new Point(270, 173), new Point(394, 217),
			new Point(519, 260), new Point(644, 305), new Point(4, 196), new Point(124, 231), new Point(171, 337),
			new Point(301, 430), new Point(490, 402), new Point(3, 319), new Point(272, 324), new Point(395, 307),
			new Point(225, 62), new Point(334, 69), new Point(467, 139), new Point(599, 133), new Point(511, 31),
			new Point(663, 17) };
	private static int bttWdt = 100, bttHgt = 100;
	boolean mudaSaida = true;A
	Rota[] rotas;
	ImagePanel panel;
	ArrayList<Rota> resp = null;


	public Gui(Rota[] rotas) {
		panel = new ImagePanel();
		this.addMouseListener(this);
		this.setTitle("Sistema de metro da Grafolandia");
		this.setSize(800, 800);
		this.rotas = rotas;

		this.getContentPane().add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void paint(Graphics g) {
		CircleList estacoes;
		if(resp == null) {
			estacoes = new CircleList(points, new ArrayList<Rota>(Arrays.asList(rotas)));
		}else {
			Rota[] dest = new Rota[resp.size()] ;
			for(int i = 0; i < resp.size(); i++) {
				dest[i] = resp.get(i);
			}
			estacoes = new CircleList(points, new ArrayList<Rota>(Arrays.asList(rotas)), new ArrayList<Rota>(Arrays.asList(dest)));
		}
		estacoes.drawEstacoes((Graphics2D) g);
	}


	public int getSaida() {
		return saida;

	}

	public int getChegada() {
		return chegada;
	}

	private void calculaDij() {
		Dijkstra d = new Dijkstra(rotas, this.saida , this.chegada );
		ArrayList<Rota> resp = d.rotaMaisCurta();

		this.resp = resp;
		System.out.println("RESP saindo de  " + this.saida + " chegando em " + this.chegada + " : \n" + d.getOutput());
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX(), y = e.getY();
		/// ESSE FOR ENCONTRA OS NOS DE LIGACAO
		for (int i = 0; i < points.length; i++) {
			Point p = points[i];
			if (x >= p.getX() + 100 && x <= p.getX() + bttWdt+ 100) {
				if (y >= p.getY()+ 100 && y <= p.getY() + bttHgt+ 100) {
				//	System.out.println("DALHEE");
					if (mudaSaida) {
						this.saida = i;
						mudaSaida = false;
					} else {
						this.chegada = i;
						mudaSaida = true;
					}

					break;
				}
			}
		}

		if (saida != -1 && chegada != -1) {
			calculaDij();
			System.out.println("SAIDA: " + (saida + 1) + " CHEGADA: " + (chegada + 1));
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	public static int getBttWdt() {
		return bttWdt;
	}

	public static int getBttHgt() {
		return bttHgt;
	}

	class CircleList {
		class Circle {
			public int centerX, centerY, diam;

			public Circle(int x, int y, int diam) {
				this.centerX = x;
				this.centerY = y;
				this.diam = diam;
			}
		}

		ArrayList<Circle> circles= new ArrayList<Circle>();
		ArrayList<Rota> rotas, destaques = null;

		public CircleList(Point[] pts, ArrayList<Rota> rotas, ArrayList<Rota> destaques) {
			this(pts);
			for(Rota r : destaques) {
				rotas.remove(r);
			}
			this.rotas = rotas;
			this.destaques = destaques;
		}

		///TODO: TROCAR ESSES 2 CONTRUTORES
		public CircleList(Point[] pts,  ArrayList<Rota> rotas) {
			this(pts);
			this.rotas = rotas;
		}

		public CircleList(Point[] pts) {
			for (Point r : pts) {
				// +100 é a margem
				circles.add(new Circle((int) r.getX() + 100, (int) r.getY() + 100, 50));
			}
		}

		public void drawEstacoes(Graphics g) {
			int i = 1;
			drawRotas(g);

			g.setColor(new Color(255, 255, 255));
			for (Circle c : circles)
				g.fillOval(c.centerX, c.centerY, c.diam, c.diam);
			((Graphics2D) g).setStroke(new BasicStroke(1));
			g.setColor(new Color(0, 0, 0));
			g.setFont(new Font("TimesRoman", Font.BOLD, 20));
			for (Circle c : circles) {
				g.drawOval(c.centerX, c.centerY, c.diam, c.diam);
				///TODO: CENTRALIZAR NUMEROS MAIORES ou IGUAis a 10
				g.drawString(String.format("%d", i), c.centerX <=10? c.centerX + 5 : c.centerX + 20, c.centerY + 30);
				i++;
			}
		}

		public void drawRotas(Graphics g) {
			for (int i = 0; i < rotas.size(); i++) {
				String cor = rotas.get(i).getCor();
				int saida = rotas.get(i).getSaida();
				int chegada = rotas.get(i).getChegada();

				if(cor.equals("vermelho"))
					g.setColor(new Color(255, 0, 0));
				else if(cor.equals("verde"))
					g.setColor(new Color(0, 255, 0));
				else	if(cor.equals("azul"))
					g.setColor(new Color(0, 0, 255));
				else if(cor.equals("amarelo"))
					g.setColor(new Color(255, 255, 0));
				else if(cor.equals("roxo"))
					g.setColor(new Color(200, 0, 175));

				((Graphics2D) g).setStroke(new BasicStroke(3));
				//TODO: FAZER COM QUE A RETA NAO ENTRE NA AREA DO CIRCULO
				g.drawLine(circles.get(saida - 1).centerX + 20 ,
						circles.get(saida - 1).centerY + 30 ,
						circles.get(chegada - 1).centerX + 20 ,
						circles.get(chegada - 1).centerY + 30 );

			}
			if(this.destaques == null)
				return;

			System.out.println("PINTEI O DEMO!!!!!");
			for (int i = 0; i < destaques.size(); i++) {
				int saida = this.destaques.get(i).getSaida();
				int chegada = this.destaques.get(i).getChegada();

				g.setColor(new Color(0, 0, 0));

				((Graphics2D) g).setStroke(new BasicStroke(5));
				//TODO: FAZER COM QUE A RETA NAO ENTRE NA AREA DO CIRCULO
				g.drawLine(circles.get(saida - 1).centerX + 20 ,
						circles.get(saida - 1).centerY + 30 ,
						circles.get(chegada - 1).centerX + 20 ,
						circles.get(chegada - 1).centerY + 30 );

			}
		}
	}

}
