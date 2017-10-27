import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends JFrame implements ActionListener{

	ArrayList<JButton> buttons = new ArrayList<JButton>();
	int  saida, chegada, clickCount = 0;

	public Gui(Rota[] rotas) {
		setTitle("My Gui");
		setSize(800, 800);
		System.out.println("DAAAA");
		for(int i = 1; i < 21; i++){
			buttons.add(new JButton(String.format("%d", i)));
		}

		// Create JButton and JPanel
		JPanel panel = new JPanel();
		// Add button to JPanel
		for (JButton b : buttons) {
			panel.add(b);
			b.addActionListener(this);
		}
		// And JPanel needs to be added to the JFrame itself!
		this.getContentPane().add(panel);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		JButton click = (JButton ) e.getSource();
		if(clickCount % 2 == 0)
			this.saida = Integer.parseInt(click.getText());
		else
			this.chegada = Integer.parseInt(click.getText());
		clickCount++;
	}
	
	
	public int getSaida(){
		return saida;
		
	}
	
	
	public int getChegada(){
		return chegada;
	}
}
