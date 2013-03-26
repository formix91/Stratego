package interfaccia;

import java.awt.BorderLayout;

import javax.swing.JFrame;

// ============================================================ 

public class Gui {
	public static int base, altezza, dimensione; // per la scacchiera
	public static JFrame frame;

	public static void main(String[] args) {
		base = 960;
		altezza = 600;
		dimensione = 10;
		frame = new JFrame(); // frame di tipo JFrame e'globale
		frame.setSize(base, altezza);
		frame.setTitle(" ****** STRATEGO ******");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		// il tipo C_Scacchiera e'definito dopo, e' un JComponent

		Scacchiera scacchiera = new Scacchiera(base, altezza, dimensione);

		NewGame newgame = new NewGame(scacchiera);
		startGame start = new startGame();

		frame.add(newgame);
		newgame.setBounds(700, 30, 150, 70);
        
		
		frame.add(start);
		start.setBounds(700, 110, 150, 70);

		frame.add(scacchiera);

		frame.setVisible(true);

	} // main
} // LAB3soluz_scacc

// ============================================================

