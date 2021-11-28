import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DriverClass extends JFrame implements ActionListener
{
	private static final int WIDTH = 720;
	private static final int HEIGHT = 617;
	public static final int NUMBER_OF_DIGITS = 30;
	private JLabel doorLabel[] = new JLabel[3];
	private JTextField nameField = new JTextField();
	private Person player = new Person();
	private FileDriver fileDriver = new FileDriver();
	private JLabel rules = new JLabel("Enter your name, press submit button, choose a door and win 10,000$!!!");
	private ImageIcon doorPic = new ImageIcon("Door5.jpg");
	private ImageIcon goatPic = new ImageIcon("goat3.jpg");
	private ImageIcon stanPic = new ImageIcon("stan8.jpg");
	
	public DriverClass()
	{
		super("Door Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		
		add(buildHeader(), BorderLayout.NORTH);
		add(buildBottom(), BorderLayout.SOUTH);
		add(buildCenter(), BorderLayout.CENTER);
		
	}
	
	private JPanel buildHeader()
	{
		JPanel panel = new JPanel();		
		panel.setBackground(Color.green);
		panel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
		
		panel.add(rules);
		
		return panel;
	}
	
	public void setRules(JLabel rules)
	{
		this.rules = rules;
	}	
	public JLabel getRules()
	{
		return rules;
	}
		
	private JPanel buildBottom()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout (3, 3));
		
		JButton door1Button = new JButton("Door 1");
		door1Button.addActionListener(this);
		panel.add(door1Button);
		
		JButton door2Button = new JButton("Door 2");
		door2Button.addActionListener(this);
		panel.add(door2Button);
		
		JButton door3Button = new JButton("Door 3");
		door3Button.addActionListener(this);
		panel.add(door3Button);
		
		JPanel betta = new JPanel();
		betta.setBackground(Color.YELLOW);
		JLabel alpha = new JLabel("Your Name:", SwingConstants.RIGHT);
		betta.add(alpha);
		panel.add(betta);
		
		nameField = new JTextField("",NUMBER_OF_DIGITS);
		nameField.setBackground(Color.WHITE);
		panel.add(nameField);
		
		JButton submitName = new JButton("Submit Name");
		submitName.addActionListener(this);
		panel.add(submitName);
		
		JLabel empty1 = new JLabel("");
		panel.add(empty1);
		
		JButton statistics = new JButton("Statistics");
		statistics.addActionListener(this);
		panel.add(statistics);
		
		JButton tryAgain = new JButton("Try Again");
		tryAgain.addActionListener(this);
		panel.add(tryAgain);
		
		return panel;
	}
	
	private JPanel buildCenter()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout (1, 3));	
		
	/*	doorPanel[0] = new JPanel();
		doorPanel[0].setBackground(Color.WHITE);
		doorPanel[0].add(new JLabel("Door 1"));
		panel.add(doorPanel[0]); */
		
		doorLabel[0] = new JLabel("");
		doorLabel[0].setIcon(doorPic);
		panel.add(doorLabel[0]);
		
		doorLabel[1] = new JLabel("");
		doorLabel[1].setIcon(doorPic);
		panel.add(doorLabel[1]);
		
		doorLabel[2] = new JLabel("");
		doorLabel[2].setIcon(doorPic);
		panel.add(doorLabel[2]);
		
		return panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String actionCommand = e.getActionCommand();
		try {
			if (nameField.getText().isEmpty())
				throw new Exception();
		}
		catch (Exception f)
		{
			nameField.setText("Please enter your name here");
		}
		
		if (actionCommand.equals("Try Again"))
		{
			doorLabel[0].setIcon(doorPic);
			doorLabel[1].setIcon(doorPic);
			doorLabel[2].setIcon(doorPic);
			Person empty = new Person();
			player = empty;
			rules.setText("Now choose a door");
			rules.setVisible(true);
		}
		
		if (actionCommand.equals("Statistics"))
		{
			StatisticsWindow statWindow = new StatisticsWindow();
			statWindow.setVisible(true);
		}
		
		if (actionCommand.equals("Submit Name"))
		{
			player.setHaveName(1);
			player.setName(nameField.getText());
			rules.setText("Choose a door");
			rules.setVisible(true);
		}
		else if ((player.getHaveName()==1)&&(player.getFirstTry()==1))
		{
			player.setFirstTry(2);
			switch(actionCommand) 
			{
			case "Door 1": 
				if (player.getRightDoor(0)==1)
					doorLabel[0].setIcon(stanPic);
				else
					doorLabel[0].setIcon(goatPic);
				player.setSecondChoise(0);
				break;
			case "Door 2": 
				if (player.getRightDoor(1)==1)
					doorLabel[1].setIcon(stanPic);
				else
					doorLabel[1].setIcon(goatPic);
				player.setSecondChoise(1);
				break;
			case "Door 3": 
				if (player.getRightDoor(2)==1)
					doorLabel[2].setIcon(stanPic);
				else
					doorLabel[2].setIcon(goatPic);
				player.setSecondChoise(2);
				break;	
			}
			fileDriver.addToArrayList(player);
			if (player.getSecondChoise()==player.getWinDoor())
			{
				rules.setText("Congratulations you won!");
				rules.setVisible(true);
			}
			else
			{
				rules.setText("Sorry, you lost. Would you like to try again?");
				rules.setVisible(true);
			}
		}
		else if ((player.getHaveName()==1)&&(player.getFirstTry()==0))
		{
			int i=0;
			Random rnd = new Random();
			player.setFirstTry(1);
			while (i==0)
			{
			int rnd1 = rnd.nextInt(3);
			switch(actionCommand)
			{
			case "Door 1": 
				if ((player.getRightDoor(rnd1)==0)&&(rnd1!=0))
				{
					doorLabel[rnd1].setIcon(goatPic);
					i=1;
					player.setFirstChoise(0);
					rules.setText("There was a goat in Door "+(rnd1+1)+", would you like to change your door or keep your first choice?");
					rules.setVisible(true);
				}	
				break;
			case "Door 2": 
				if ((player.getRightDoor(rnd1)==0)&&(rnd1!=1))
				{
					doorLabel[rnd1].setIcon(goatPic);
					i=1;
					player.setFirstChoise(1);
					rules.setText("There was a goat in Door "+(rnd1+1)+", would you like to change your door or keep your first choice?");
					rules.setVisible(true);
				}	
				break;
			case "Door 3": 
				if ((player.getRightDoor(rnd1)==0)&&(rnd1!=2))
				{
					doorLabel[rnd1].setIcon(goatPic);
					i=1;
					player.setFirstChoise(2);
					rules.setText("There was a goat in Door "+(rnd1+1)+", would you like to change your door or keep your first choice?");
					rules.setVisible(true);
				}	
				break;
			}
			}
		}
	}
	
	public void setPlayer(Person player)
	{
		this.player = player;
	}
	
	public Person getPlayer()
	{
		return player;
	}
}