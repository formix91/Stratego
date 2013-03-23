package interfaccia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

import dlvWrapper.UpdateFileDlv;

public class GestoreModelli
{
	final private int numeroModelli= 1;
	private int indexModello;
	private final String[][] matriceLetta= new String[10][10];
	private final UpdateFileDlv updateFile;

	public GestoreModelli()
	{
		for (int i= 0; i < 10; i++)
		{
			for (int j= 0; j < 10; j++)
			{
				matriceLetta[i][j]= "0";
			}
		}

		updateFile= new UpdateFileDlv();
		indexModello= new Random().nextInt(numeroModelli) + 1;
	}

	private String linea;

	public String[][] fillMatrix(String path)
	{

		int idModello= 0;
		String[] posizioniPedineModelli= new String[3];

		File file= new File(path);
		try
		{
			BufferedReader reader= new BufferedReader(new FileReader(file));

			while ((linea= reader.readLine()) != null)
			{
				if (linea.equals("@"))
				{
					idModello++;
				}
				if (idModello == indexModello)
				{

					while (!(linea= reader.readLine()).equals("&"))
					{
						posizioniPedineModelli= linea.split(",");
						matriceLetta[Integer
								.parseInt(posizioniPedineModelli[1]) - 1][Integer
								.parseInt(posizioniPedineModelli[2]) - 1]= posizioniPedineModelli[0];

					}
				}

			}

			reader.close();

		} catch (Exception e)
		{
			// TODO: handle exception
		}

		// for (String[] string : matriceLetta)
		// {
		// for (String string2 : string)
		// {
		// System.out.print(string2 + " ");
		// }
		// System.out.println();
		// }

		return matriceLetta;
	}

	String getTipoPedina(int x, int y)
	{

		return matriceLetta[x][y];
	}

	boolean cellaVuota(int x, int y)
	{
		if (matriceLetta[x][y] == "0")
		{
			return true;
		}
		return false;
	}

	boolean verificaAdiacenza(int x1, int y1, int x2, int y2)
	{
		if ((x2 == x1 - 1 || x2 == x1 + 1) && y2 == y1)
		{
			return true;
		} else if ((y2 == y1 - 1 || y2 == y1 + 1) && x1 == x2)
		{
			return true;
		}

		return false;
	}

	void setCella(int x, int y, String a)
	{
		matriceLetta[x][y]= a;
	}

	boolean verificaPedina(String a)
	{
		if (!a.equals("bomba") && !a.equals("bandiera")
				&& !a.equals("occupata") && !a.equals("generale")
				&& !a.equals("soldato") && !a.equals("miniere")
				&& !a.equals("bombaAvversario")
				&& !a.equals("bandieraAvversario"))
			return true;

		return false;

	}

	public void setIndexModello(int indexModello)
	{
		this.indexModello= indexModello;
	}

	public void aggiornaFatti()
	{

		updateFile.aggiornaFatti(matriceLetta);
	}

	public static void main(String[] args)
	{
		GestoreModelli modello= new GestoreModelli();
		// modello.getMatrixModels();

		modello.fillMatrix("FILES/models.txt");
	}
}
