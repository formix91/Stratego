package interfaccia;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MyDialog extends JFrame
{

	public MyDialog()
	{

		JFrame frame= new JFrame("Questo è il frame Principale");
		frame.setBounds(300, 300, 300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new FlowLayout());
		JButton btn= new JButton("Clicca qui per aprire il JDialog");
		frame.add(btn);
		btn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				showDialog();
			}
		});
	}

	private void showDialog()
	{

		JDialog dialog= new JDialog(); // dicharo un JFrame
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL); // Rende
																		// il
																		// JDialog
																		// MODALE,
																		// rende
																		// cioè
																		// non
																		// cliccabile
																		// il
																		// contenuto
																		// del
																		// frame
																		// principale
		dialog.setLayout(new FlowLayout());

		String[] schemi=
		{ "Cyclone Defense", "The Tempest Defense", "Tripple Threat",
				"Scout's Gambit", "On Guard!", "Shoreline Bluff",
				"Corner Fortress", "Shield Defense", "Corner Blitz",
				"Wheel of Danger", "Blitzkrieg", "Early Warning",
				"Man the Barricades" };

		JList lista= new JList(schemi); // dichiaro una Jlist, riempita con le
										// stringe contenute in schemi
		lista.setVisibleRowCount(5); // Inizialmente, visualizziamo 5 strategie
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // possiamo
																		// selezionare
																		// solo
																		// una
																		// strategia
																		// alla
																		// volta

		JScrollPane optionPane= new JScrollPane(lista); // dichiara uno
														// scrollPane
		dialog.add(optionPane, BorderLayout.NORTH); // aggiunge lo scrollPane al
													// frame, in posizione nord

		JButton b= new JButton("Strategia Scelta");
		dialog.add(b);

		dialog.setBounds(350, 350, 200, 200);
		dialog.setVisible(true);
	}

	public static void main(String[] args)
	{
		new MyDialog();
	}
}
