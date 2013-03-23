package dlvWrapper;

import it.unical.mat.dlv.program.Literal;
import it.unical.mat.wrapper.DLVError;
import it.unical.mat.wrapper.DLVInputProgram;
import it.unical.mat.wrapper.DLVInputProgramImpl;
import it.unical.mat.wrapper.DLVInvocation;
import it.unical.mat.wrapper.DLVInvocationException;
import it.unical.mat.wrapper.DLVWrapper;
import it.unical.mat.wrapper.Model;
import it.unical.mat.wrapper.ModelBufferedHandler;
import it.unical.mat.wrapper.Predicate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

public class DLVManager
{

	private final DLVInputProgram dlvInputProgram;
	private DLVInvocation dlvInvocation= DLVWrapper.getInstance()
			.createInvocation("FILES/dlv.mingw.exe");
	private final ModelBufferedHandler modelBufferedHandler;

	private final List<Model> models;
	private int coordToX;
	private int coordToY;
	private int coordFromX;
	private int coordFromY;

	String pathStratego;
	String pathLaghi;
	String pathPedina;
	private final String pathFatti;

	private static DLVManager instance;

	public DLVManager()
	{

		this.models= new ArrayList<Model>();

		this.pathStratego= "FILES/stratego.dl";
		this.pathFatti= "FILES/fatti.dl";
		this.pathLaghi= "FILES/laghi.dl";
		this.pathPedina= "FILES/tipoPedina.dl";

		this.dlvInputProgram= new DLVInputProgramImpl();
		this.dlvInvocation= DLVWrapper.getInstance().createInvocation(
				"FILES/dlv.mingw.exe");

		this.dlvInputProgram.addFile(pathStratego);
		this.dlvInputProgram.addFile(pathLaghi);
		this.dlvInputProgram.addFile(pathPedina);
		this.dlvInputProgram.addFile(pathFatti);

		try
		{
			dlvInvocation.setInputProgram(dlvInputProgram);
		} catch (DLVInvocationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// lista filtri
		List<String> list= new ArrayList<>();
		list.add("muovi");
		list.add("coord");

		try
		{
			this.dlvInvocation.setFilter(list, true);

		} catch (DLVInvocationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.modelBufferedHandler= new ModelBufferedHandler(dlvInvocation);
	}

	public static DLVManager getInstance()
	{

		if (instance == null)
		{

			instance= new DLVManager();
		}
		return instance;

	}

	public void createMove() throws DLVInvocationException, IOException
	{

		dlvInvocation.run();

		while (modelBufferedHandler.hasMoreModels())
		{

			models.add(modelBufferedHandler.nextModel());

		}
		int index= new Random().nextInt(models.size());
		Model model= models.get(index);
		Enumeration<Predicate> predicates= model.getPredicates();
		while (predicates.hasMoreElements())
		{

			Predicate predicate= predicates.nextElement();
			if (predicate.name().equals("coord"))
			{
				// System.out.println(predicate);
				Enumeration<Literal> enumeration= predicate.getLiterals();

				Literal literal= enumeration.nextElement();
				coordToX= Integer
						.parseInt(literal.getAttributeAt(0).toString());
				coordToY= Integer
						.parseInt(literal.getAttributeAt(1).toString());

			} else
			{
				Enumeration<Literal> enumeration= predicate.getLiterals();

				Literal literal= enumeration.nextElement();
				coordFromX= Integer.parseInt(literal.getAttributeAt(1)
						.toString());
				coordFromY= Integer.parseInt(literal.getAttributeAt(2)
						.toString());

			}

		}

		/* If i wont to wait the finish of execution, i can use thi method */
		dlvInvocation.waitUntilExecutionFinishes();
		/*
		 * At the term of execution, I can control the errors created by DLV
		 * invocation
		 */
		List<DLVError> listError= dlvInvocation.getErrors();

		// System.out.println(listError.size());

	}

	public int getCoordToX()
	{
		return coordToX;
	}

	public int getCoordToY()
	{
		return coordToY;
	}

	public int getCoordFromX()
	{
		return coordFromX;
	}

	public int getCoordFromY()
	{
		return coordFromY;
	}

	// public String getWinner() throws DLVInvocationException, IOException
	// {
	//
	// this.createMove();
	// return winner;
	// }

	// public void setWinner(String winner)
	// {
	// this.winner= winner;
	// }

	public static void main(String[] args) throws DLVInvocationException,
			IOException
	{

		DLVManager manager= new DLVManager();
		manager.createMove();
		System.out.println(manager.coordFromX + " " + manager.coordFromY);

	}

}