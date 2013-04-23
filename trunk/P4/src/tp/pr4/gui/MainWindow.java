package tp.pr4.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import tp.pr4.RobotEngine;


public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RobotEngine robot;
	private RobotPanel robotPanel;
	private NavigationPanel navigationPanel;
	private Vector<Instruction> lastInstructions;
	
	
	

	/**
	 * Creates the window and the panels using Swing Components. It stores a
	 * reference to the RobotEngine object and provides the panels to the robot
	 * engine in order to communicate the simulation events.
	 * 
	 * @param robot
	 *            The RobotEngine that receives the instructions performed by
	 *            the action panel
	 */
	
	public MainWindow(RobotEngine robot) {
		
		super("WALL·E the garbage collector");
		this.robot=robot;
		this.setPreferredSize(new Dimension(900, 700));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel(new BorderLayout());
		
		lastInstructions = new Vector<Instruction>();

		createMenu();
		
		RobotPanel robotPanel = new RobotPanel();
		
		InstructionPanel instructionsPanel = new InstructionPanel(robot, robotPanel);
		JSplitPane SuperiorPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				instructionsPanel, robotPanel);
		mainPanel.add(SuperiorPanel, BorderLayout.NORTH);
		
		this.navigationPanel = new NavigationPanel();
		JSplitPane inferiorPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, SuperiorPanel, this.navigationPanel);
		mainPanel.add(inferiorPanel, BorderLayout.CENTER);
		
		this.add(mainPanel);
		
		
		pack(); // compacta la ventana 
		setLocationRelativeTo(null);
		setVisible(true);
		
		robot.setNavigationPanel(navigationPanel);
		robot.setRobotPanel(robotPanel);

	}
	
	/**
	 * 
	 */

	private void createMenu() {
		JMenuBar mbMenu = new JMenuBar();
		this.setJMenuBar(mbMenu);
		JMenu mDialogs = new JMenu("File");
		mbMenu.add(mDialogs);
		initializeMenu(mDialogs);
	}
	
	/**
	 * 
	 * Quit from menu of window
	 * 
	 * @param mDialogs menu for window
	 */

	private void initializeMenu(JMenu mDialogs) {
	
		JMenuItem mUndo = new JMenuItem("Undo");
		mDialogs.add(mUndo);
		mUndo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		
		
		JMenuItem mQuit = new JMenuItem("Quit");
		mDialogs.add(mQuit);
			mQuit.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Game Over");
				System.exit(0);

			}
		});
	}

	


	/**
	 * @return the lastCommand
	 */
	public Instruction getLastInstruction() {
		if (lastInstructions.size() > 0)
			return lastInstructions.elementAt(lastInstructions.size() - 1);
		else
			return null;

	}
	
}
