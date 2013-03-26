package interfaccia;

import it.unical.mat.wrapper.DLVInvocationException;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JPanel;

import dlvWrapper.DLVManager;

class Scacchiera extends JPanel
{
	static int spazio;
	private final int dimensione;
	private Image cellaPiena;
	private Image lagoImg;
	private Image cellaLibera;
	private Image generaleAvversario;
	private Image sfondo;
	private boolean primaselezione;
	private int mossaX, mossaY;
	public boolean canMoveDlv;

	private final GestoreModelli modello= new GestoreModelli();
	private DLVManager dlvManager;

	// carica img
	private MediaTracker media;
	// funzioni per accedere a i file di windows...
	private Toolkit tool;

	public Scacchiera()
	{ // senza void !! e' un "costruttore"
		dimensione= 2;
		// new C_Scacchiera();
	} // C_Scacchiera

	public Scacchiera(int bas, int alt, int dim)
	{

		primaselezione= true;
		mossaX= 0;
		mossaY= 0;
		dimensione= dim; // C_Scacchiera scacchiera =
		// new C_Scacchiera(base, altezza, dimensione);
		modello.fillMatrix("FILES/models.txt");


		this.setOpaque(false);


		dlvManager= new DLVManager();

		// caricare le immagini...
		this.tool= Toolkit.getDefaultToolkit();
		this.media= new MediaTracker(this);
		cellaPiena= tool.getImage("./src/orks.png");
		lagoImg= tool.getImage("./src/lago.png");
		sfondo=tool.getImage("./src/Prato.jpg");

		cellaLibera= tool.getImage("./src/vuota.png");
		generaleAvversario= tool.getImage("./src/ranged.png");

		media.addImage(cellaPiena, 0);
		media.addImage(lagoImg, 1);
		media.addImage(cellaLibera, 2);
		media.addImage(generaleAvversario, 3);
		media.addImage(sfondo, 4);

		try
		{
			media.waitForAll();
		} catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		this.addMouseListener(new MouseAdapter()
		{

			@Override
			public void mousePressed(MouseEvent e)
			{
				// elenco variabili usate per disegnare la scacchiera:
				int latox= getWidth() - 10; // dimensione finestra corrente
				int latoy= getHeight() - 10;
				int lato= (latox > latoy) ? latoy : latox;
				// System.out.println(lato);// il piu'piccolo

				int spazioRettangolo= lato / dimensione; // spazio per un

				// rettangolo;
				int x, y;

				int mx= e.getX();
				int my= e.getY();

				// ora inizia il disegno:
				y= 6; // inizia con un po' di bordo

				for (int riga= 0; riga < dimensione; riga++)
				{
					// fai tante righe quanto vale dimensione

					x= 35;

					for (int col= 0; col < dimensione; col++)
					{
						// fai per ogni riga altrettanti quadrati :
						// dentro le {..} posso definire una variabile
						// System.out.println("mx:" + mx + "my:" + my);
						if (mx > x && mx < x + spazioRettangolo && my > y
								&& my < y + spazioRettangolo)
						{
							muovi(riga, col);// manca Gestione dello scontro

						}
						x= x + spazioRettangolo;
					} // for col = for x

					y= y + spazioRettangolo;

				} // for riga = for y
				Repaint();
			}
		});

	} // C_Scacchiera

	// la classe JComponent ha un metodo astratto paintComponent
	// che DEVE essere definito dall'utente, qui e' quanto segue:

	@Override
	public void paintComponent(Graphics g)
	{
		// elenco variabili usate per disegnare la scacchiera:
		int latox= getWidth() - 10; // dimensione finestra corrente
		int latoy= getHeight() - 10;
		int lato= (latox > latoy) ? latoy : latox; // il piu'piccolo

		int spazioRettangolo= lato / dimensione; // spazio per un rettangolo;
		this.spazio=spazioRettangolo;
		g.drawImage(sfondo,35,5,spazioRettangolo*10,spazioRettangolo*10,null);//colore sfondo schacchiera;

		int x, y;
		Graphics2D g2= (Graphics2D) g; // modo abituale per
		// ottenere l'ambiente grafico g in versione 2D (nuova) :
		// ora inizia il disegno:
		y= 0; // inizia con un po' di bordo

		for (int riga= 0; riga < dimensione; riga++)
		{
			// fai tante righe quanto vale dimensione
			x= 30;
			for (int col= 0; col < dimensione; col++)
			{
				// fai per ogni riga altrettanti quadrati :
				// dentro le {..} posso definire una variabile
				// locale a queste parentesi:
				Rectangle cella= new Rectangle(x + 5, y + 5, spazioRettangolo,
						spazioRettangolo);

				try
				{
					switch (modello.getTipoPedina(riga, col))
					{

					case "occupata":
						g2.drawImage(lagoImg, x + 5, y + 5, spazioRettangolo,
								spazioRettangolo, null);
						break;

					case "soldato":
						g2.drawImage(cellaPiena, x + 5, y + 5,
								spazioRettangolo, spazioRettangolo, null);
						break;
					case "bomba":
						g2.drawImage(cellaPiena, x + 5, y + 5,
								spazioRettangolo, spazioRettangolo, null);
						break;
					case "miniere":
						g2.drawImage(cellaPiena, x + 5, y + 5,
								spazioRettangolo, spazioRettangolo, null);
						break;
					case "generale":
						g2.drawImage(cellaPiena, x + 5, y + 5,
								spazioRettangolo, spazioRettangolo, null);
						break;
					case "generaleAvversario":
						g2.drawImage(generaleAvversario, x + 5, y + 5,
								spazioRettangolo, spazioRettangolo, null);
						break;
					case "0":
						g2.drawImage(cellaLibera, x + 5, y + 5,
								spazioRettangolo, spazioRettangolo, null);
						break;

					}
				} catch (Exception e)
				{
					// TODO: handle exception
				}

				g2.draw(cella); // solo contorno
				// ogni secondo, alternati ...

				x= x + spazioRettangolo;

			} // for col = for x

			y= y + spazioRettangolo;

		} // for riga = for y

		Repaint();

	} // paintComponent

	public void Repaint()
	{

		this.repaint();
	}

	public void muovi(int riga, int col)
	{
		canMoveDlv=false;
		if (primaselezione == true && !modello.cellaVuota(riga, col)
				&& modello.verificaPedina(modello.getTipoPedina(riga, col)))
		{
			mossaX= riga;
			mossaY= col;
			primaselezione= false;
			System.out.println("hai selezionato un "
					+ modello.getTipoPedina(riga, col));
		} else
		{
			if (modello.cellaVuota(riga, col)
					&& modello.verificaAdiacenza(mossaX, mossaY, riga, col))
			{
				modello.setCella(riga, col,
						modello.getTipoPedina(mossaX, mossaY));
				System.out.println("hai spostato "
						+ modello.getTipoPedina(riga, col));
				modello.setCella(mossaX, mossaY, "0");

				mossaDlv();

				primaselezione= true;
			}
			if (!modello.cellaVuota(riga, col) && primaselezione != true
					&& modello.verificaPedina(modello.getTipoPedina(riga, col)))
			{
				mossaX= riga;
				mossaY= col;
				System.out.println("hai selezionato un "
						+ modello.getTipoPedina(riga, col));
			}
		}

	}// fine funzione muovi

	public void mossaDlv()
	{
		 
		try
		{
			dlvManager.createMove();

			modello.setCella(dlvManager.getCoordToX(), dlvManager.getCoordToY(),modello.getTipoPedina(dlvManager.getCoordFromX(),dlvManager.getCoordFromY()));
            modello.setCella(dlvManager.getCoordFromX(), dlvManager.getCoordFromY(), "0");
			Repaint();
			modello.aggiornaFatti();
		} catch (DLVInvocationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void aggiornaScacchieraPlayer(int index)
	{
		modello.setIndexModello(index);
		modello.fillMatrix("FILES/modelloGiocatore.txt");
		this.Repaint();
	}

} // C_Scacchiera

