package tp.pr4.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import tp.pr4.RobotEngine;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RobotEngine robot;
	public RobotPanel robotPanel;
	public NavigationPanel navigationPanel;

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
		
		
		this.setPreferredSize(new Dimension(720, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel(new BorderLayout());

		JMenuBar mbMenu = new JMenuBar();
		this.setJMenuBar(mbMenu);

		JMenu mDialogs = new JMenu("File");
		mbMenu.add(mDialogs);
		JMenuItem mLoad = new JMenuItem("Load");

		initializeMenu(mDialogs, mLoad);
		JPanel robotPanel = new RobotPanel();

		JPanel instructionsPanel = new InstructionPanel(robot);
		JSplitPane SuperiorPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				instructionsPanel, robotPanel);
		mainPanel.add(SuperiorPanel, BorderLayout.NORTH);
		
		JPanel cityPanel = new NavigationPanel();
		JSplitPane inferiorPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, SuperiorPanel, cityPanel);
		mainPanel.add(inferiorPanel, BorderLayout.CENTER);
		
		this.add(mainPanel);
		
		pack(); // compacta la ventana 
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void initializeMenu(JMenu mDialogs, JMenuItem mLoad) {
		mDialogs.add(mLoad);
		mLoad.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(rootPane, "A message");
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

	/*public static void main(String[] args) {

		final MainWindow window = new MainWindow(robot);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				window.pack(); /* compacta la ventana /*
				window.setLocationRelativeTo(null);
				window.setVisible(true);
			}
		});

	}*/
}
