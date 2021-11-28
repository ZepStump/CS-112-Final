import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatisticsWindow extends JFrame
{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	public static final int NUMBER_OF_DIGITS = 30;
	private FileDriver fileDriver = new FileDriver();
	private double winPercent = -1;
	private double doorChangePercent = -1;
	private double winPercentDoorChange = -1;
	private double winPercentDoorUnchanged = -1;
	private int entries = -1;
	private ArrayList<Person> array = new ArrayList<Person>(1);
	
	
	public StatisticsWindow()
	{
		super("Statistics");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		
		add(bodyBuilder(), BorderLayout.CENTER);
		add(headBuilder(), BorderLayout.NORTH);
	}
	
	public JPanel headBuilder()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
		panel.add(new Label("Statistics Window"));
		
		return panel;
	}
	
	public JPanel bodyBuilder()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new GridLayout(5,2));
		
		calculator();
		
		JPanel box1 = new JPanel();
		box1.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
		JLabel box1Label = new JLabel("Winning percent");
		box1.add(box1Label);
		panel.add(box1);
		
		JPanel box2 = new JPanel();
		box2.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
		JLabel box2Label = new JLabel(new DecimalFormat("##.##").format(winPercent*100)+" %");
		box2.add(box2Label);
		panel.add(box2);
		
		JPanel box3 = new JPanel();
		box3.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
		JLabel box3Label = new JLabel("Door change percent");
		box3.add(box3Label);
		panel.add(box3);
		
		JPanel box4 = new JPanel();
		box4.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
		JLabel box4Label = new JLabel(new DecimalFormat("##.##").format(doorChangePercent*100)+" %");
		box4.add(box4Label);
		panel.add(box4);
		
		JPanel box5 = new JPanel();
		box5.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
		JLabel box5Label = new JLabel("Winning percent when door was changed");
		box5.add(box5Label);
		panel.add(box5);
		
		JPanel box6 = new JPanel();
		box6.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
		JLabel box6Label = new JLabel(new DecimalFormat("##.##").format(winPercentDoorChange*100)+" %");
		box6.add(box6Label);
		panel.add(box6);
		
		JPanel box7 = new JPanel();
		box7.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
		JLabel box7Label = new JLabel("Winning percent when door was NOT changed");
		box7.add(box7Label);
		panel.add(box7);
		
		JPanel box8 = new JPanel();
		box8.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
		JLabel box8Label = new JLabel(new DecimalFormat("##.##").format(winPercentDoorUnchanged*100)+" %");
		box8.add(box8Label);
		panel.add(box8);
		
		JPanel box9 = new JPanel();
		box9.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
		JLabel box9Label = new JLabel("Number of entries");
		box9.add(box9Label);
		panel.add(box9);
		
		JPanel box10 = new JPanel();
		box10.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
		JLabel box10Label = new JLabel(Integer.toString(entries));
		box10.add(box10Label);
		panel.add(box10);
		
		setSize(WIDTH, 400);
		
		return panel;
	}
	
	public void calculator()
	{
		array = fileDriver.inputData();
		array.trimToSize();
		int sum = 0;
		for (int i=0; i<array.size();i++)
		{
			if (array.get(i).getWinDoor()==array.get(i).getSecondChoise())
			{
				sum++;
			}
		}
		winPercent = (double)(sum)/(array.size());
		
		
		int sum2 = 0;
		for (int i=0; i<array.size();i++)
		{
			if (array.get(i).getFirstChoise()!=array.get(i).getSecondChoise())
				sum2++;
		}
		doorChangePercent = (double)(sum2)/(array.size());
		
		
		int sum3 = 0;
		for (int i=0; i<array.size();i++)
		{
			if ((array.get(i).getFirstChoise()!=array.get(i).getSecondChoise())&&(array.get(i).getWinDoor()==array.get(i).getSecondChoise()))
				sum3++;
		}
		winPercentDoorChange = (double)(sum3)/(sum2);
		
		int sum4 = 0;
		for (int i=0; i<array.size();i++)
		{
			if ((array.get(i).getFirstChoise()==array.get(i).getSecondChoise())&&(array.get(i).getWinDoor()==array.get(i).getSecondChoise()))
				sum4++;
		}
		winPercentDoorUnchanged = (double)(sum4)/(array.size()-sum2);
		
		entries = array.size();
	}

	
}
