package dlvWrapper;

import interfaccia.GestoreModelli;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class UpdateFileDlv
{
	public UpdateFileDlv()
	{

	}

	public void aggiornaFatti(String[][] matriceFatti)
	{

		try
		{
			FileOutputStream file= new FileOutputStream("FILES/fatti.dl");
			PrintStream Output= new PrintStream(file);

			// Output.println("");
			for (int i= 0; i < 10; i++)
			{
				for (int j= 0; j < 10; j++)
				{
					// le pedine dell'avversario saranno rappresentate come
					// numeri...
					if (matriceFatti[i][j].length() > 2
							&& !matriceFatti[i][j].equals("occupata")
							&& !matriceFatti[i][j].equals("generaleAvversario")
							&& !matriceFatti[i][j].equals("miniereAvversario")
							&& !matriceFatti[i][j].equals("soldatoAvversario")
							&& !matriceFatti[i][j].equals("bombaAvversario")
							&& !matriceFatti[i][j].equals("bandieraAvversario"))
					{
						Output.println("posiziona(" + matriceFatti[i][j] + ","
								+ i + "," + j + ").");
					}

					if (matriceFatti[i][j].equals("generaleAvversario")
							|| matriceFatti[i][j].equals("miniereAvversario")
							|| matriceFatti[i][j].equals("soldatoAvversario"))
					{
						Output.println("posiziona(" + "avversario" + "," + i
								+ "," + j + ").");
					}
				}
			}
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (@SuppressWarnings("hiding") IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args)
	{
		GestoreModelli modello= new GestoreModelli();
		String[][] matrixProva;
		matrixProva= modello.fillMatrix("FILES/models.txt");
		modello.setIndexModello(1);
		modello.fillMatrix("FILES/modelloGiocatore.txt");

		UpdateFileDlv gestore= new UpdateFileDlv();
		gestore.aggiornaFatti(matrixProva);
	}
}
