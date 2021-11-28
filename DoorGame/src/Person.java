import java.io.Serializable;
import java.util.Random;

public class Person implements Serializable
{
	private String name;
	private int[] doors = {1,0,0};
	private int haveName;
	private int firstTry;
	private int firstChoise;
	private int secondChoise;
	private int winDoor;
	
	public Person()
	{
		this.name = null;
		haveName=0;
		firstTry=0;
		firstChoise=0;
		secondChoise=0;
		setWinDoor(0);
		
		Random rnd = new Random();
		int rightDoor = rnd.nextInt(3);
		
		for (int i=0; i<3; i++)
		{
			if (i==rightDoor)
			{
				doors[i]=1;
				winDoor=i;
			}
			else
			doors[i]=0;
		}
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	public void setHaveName(int haveName)
	{
		this.haveName = haveName;
	}
	public int getHaveName()
	{
		return haveName;
	}
	public int getRightDoor(int i)
	{
		return doors[i];
	}
	public int getFirstTry()
	{
		return firstTry;
	}
	public void setFirstTry(int firstTry)
	{
		this.firstTry = firstTry;
	}

	public int getFirstChoise() {
		return firstChoise;
	}

	public void setFirstChoise(int firstChoise) {
		this.firstChoise = firstChoise;
	}

	public int getSecondChoise() {
		return secondChoise;
	}

	public void setSecondChoise(int secondChoise) {
		this.secondChoise = secondChoise;
	}
	public int getWinDoor() {
		return winDoor;
	}

	public void setWinDoor(int winDoor) {
		this.winDoor = winDoor;
	}
}
