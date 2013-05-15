package tp.pr5.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import tp.pr5.RobotEngine;
import tp.pr5.RobotEngineObserver;
import static tp.pr5.Constants.*;

public class MainWindow extends JFrame implements RobotEngineObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NavigationPanel navigationPanel;
	private InstructionPanel instructionsPanel;
	private InfoPanel infoPanel;
	private GUIController guiController;
	private JPanel mainPanel;
	private boolean end = false;

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	/**
	 * Creates the window and the panels using Swing Components. It stores a
	 * reference to the RobotEngine object and provides the panels to the robot
	 * engine in order to communicate the simulation events.
	 * 
	 * @param guiController
	 *            The RobotEngine that receives the instructions performed by
	 *            the action panel
	 */

	public MainWindow(GUIController	guiController) {

		super("WALL·E the garbage collector");
		guiController = guiController;
		this.setPreferredSize(new Dimension(900, 700));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel(new BorderLayout());

		createMenu();

		RobotPanel robotPanel = new RobotPanel();

		instructionsPanel = new InstructionPanel(guiController, robotPanel, this);
		JSplitPane SuperiorPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				instructionsPanel, robotPanel);
		mainPanel.add(SuperiorPanel, BorderLayout.NORTH);

		this.navigationPanel = new NavigationPanel();
		JSplitPane inferiorPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				SuperiorPanel, this.navigationPanel);
		mainPanel.add(inferiorPanel, BorderLayout.CENTER);
		mainPanel.add(infoPanel, BorderLayout.SOUTH);
		
		this.infoPanel = new InfoPanel();

		this.add(mainPanel);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	
	}

	/**
	 * Create dialog menu for window
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
	 * @param mDialogs
	 *            menu for window
	 * 
	 */

	private void initializeMenu(JMenu mDialogs) {

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
	 * 
	 * Return a public method (end) of a private attribute (isEnd).
	 * 
	 * @return isEnd if game finish or not.
	 * 
	 */

	public boolean isEnd() {
		return end;
	}

	/**
	 * 
	 * Show different message dialog at interface mode if robot is at space ship
	 * or robot hasn't got fuel
	 * 
	 * @param ship
	 *            boolean game finish if robot is at space ship or hasn't got
	 *            fuel
	 */

	public void engineOff(boolean ship) {
		if (ship) {
			JOptionPane.showMessageDialog(rootPane, END_GAME);
			end = true;
		} else {
			JOptionPane.showMessageDialog(rootPane, END_FUEL);
			end = true;

		}
	}

	@Override
	public void communicationCompleted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void communicationHelp(String help) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void raiseError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void robotSays(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		// TODO Auto-generated method stub
		
	}

}
