package interfaccia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

public class selectionModels extends JFrame {

	private JList lista;
	private int index;
	private Object tmp;
	private Scacchiera scacchieraLista;

	public selectionModels(Scacchiera scacchieraGame) {

		index = 0;
		this.scacchieraLista=scacchieraGame;

		String[] schemi = { "Cyclone Defense", "The Tempest Defense",
				"Tripple Threat", "Scout's Gambit", "On Guard!",
				"Shoreline Bluff", "Corner Fortress", "Shield Defense",
				"Corner Blitz", "Wheel of Danger", "Blitzkrieg",
				"Early Warning", "Man the Barricades" };

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());

		lista = new JList(schemi); // dichiaro una Jlist, riempita con le
									// stringe contenute in schemi
		lista.setVisibleRowCount(8); // visualizziamo 8 strategie
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // possiamo
																		// selezionare
																		// solo
																		// una
																		// strategia
																		// alla
																		// volta

		JScrollPane optionPane = new JScrollPane(lista); // dichiara uno scroll
		this.add(optionPane, BorderLayout.NORTH); // aggiunge lo scroll al
													// frame, in posizione
													// nord

		// ****************** PROVA COMBOBOX ***************
		// JComboBox comboBox = new JComboBox(schemi);
		// comboBox.setSelectedIndex(1);
		// System.out.println(comboBox.getSelectedIndex());
		// frame.add(comboBox);
		// frame.pack();
		// ************************************************

		// ************** BUTTON ************************
		JButton button = new JButton(" START ");
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		Border buttonPanelBorder = BorderFactory.createTitledBorder("Done");
		buttonPanel.setBorder(buttonPanelBorder);
		buttonPanel.add(button);
		this.add(buttonPanel, BorderLayout.SOUTH);
		// ************************************************

		lista.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {

				index = lista.locationToIndex(mouseEvent.getPoint());
				
				if (index >= 0) {
					Object o = lista.getModel().getElementAt(index);
					System.out.println("Stai selezionando: " + o.toString());
					System.out.println("INDICE: " + index);
					
				}

			}

		});

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {

				System.out.println("start");
				scacchieraLista.aggiornaScacchieraPlayer(index+1);
				setVisible(false);
				((AbstractButton) tmp).setEnabled(true);

			}

		});

		this.setVisible(true);
		this.setSize(300, 350);

	}

	public void enableButton(JButton button) {
		tmp = button;
	}
	
	public int getIndexModel()
	{
		return index;
	}

}
