package interfaccia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class NewGameEvent implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		System.out.println("new game");

		selectionModels modellsgen = new selectionModels();

		JButton tmp = (JButton) e.getSource();
		tmp.setEnabled(false);
		modellsgen.enableButton(tmp);

	}
}