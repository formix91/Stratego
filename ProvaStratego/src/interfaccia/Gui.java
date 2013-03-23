package interfaccia;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

// ============================================================ 

public class Gui
{
	public static int base, altezza, dimensione; // per la scacchiera
	public static JFrame frame;

	public static void main(String[] args)
	{
		base= 960;
		altezza= 600;
		dimensione= 10;
		frame= new JFrame(); // frame di tipo JFrame e'globale
		frame.setSize(base, altezza);
		frame.setTitle(" ****** STRATEGO ******");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// selectionModels modellsgen = new selectionModels();

		// frame.add(new JButton("new game")).setBounds(700,80,170,100);

		// il tipo C_Scacchiera e'definito dopo, e' un JComponent

		C_Scacchiera scacchiera= new C_Scacchiera(base, altezza, dimensione);

		JButton button= new JButton(new ImageIcon("./src/images32.jpg"));
		frame.add(button);
		button.setBounds(700, 100, 100, 30);
		button.addActionListener(new NewGameEvent());

		frame.add(scacchiera);
		frame.setVisible(true);

	} // main
}

// ============================================================

