import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileDriver
{
	private static String fileName;
	private static ArrayList<Person> people = new ArrayList<Person>(3);
	
	public FileDriver()
	{
		fileName = "Statistics";
	}	
	
	public static void outputData()
	{
		people.trimToSize();
		try
		{
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(fileName));
			file.writeObject(people);
			//System.out.println(people.get(0).getName());
			file.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			//System.out.println("Error writing to binary file");
		}
	}
	
	public static ArrayList<Person> inputData()
	{
		ArrayList<Person> fileArray = null;
		try
		{
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
			fileArray = (ArrayList<Person>)inputStream.readObject();
			inputStream.close();
		}
		catch (ClassNotFoundException e)
        {
			System.out.println("ERROR: cannot read object from " + fileName);
			System.out.println("Ending program...");
            System.exit(0);
		}
        catch (FileNotFoundException e)
        {
            System.out.println("ERROR: cannot find file " + fileName);
            System.out.println("Ending program...");
            System.exit(0);
        }
        catch (IOException e)
        {
            System.out.println("ERROR: problem with file input from " + fileName);
            System.out.println("Ending program...");
            System.exit(0);
        }
		
		return fileArray;
	}

	public ArrayList<Person> getPeople() 
	{
		return people;
	}
	public void addToArrayList(Person player)
	{
		people = inputData();
		people.add(player);
		outputData();
	}

	public String getFileName() {
		return fileName;
	}

}