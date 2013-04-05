package tp.pr4.gui;



import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import tp.pr4.Direction;



/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 4
*
*/

public class NavigationPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private int column, row;

	
	public enum Directions {
		LEFT, RIGHT;
	}
	
	public NavigationPanel(){
		super();

		this.setLayout(new BorderLayout());
		JPanel pRobotImage = new JPanel();
		
		ImageIcon robotImage = new ImageIcon("C:/hlocal/workspace-4.2-32bits/tp4/src/tp/pr4/gui/images/walleNorth.png"); 
		JLabel lbRobotIcon = new JLabel(robotImage); 
		Box boxImage = Box.createVerticalBox();
		boxImage.add(Box.createVerticalGlue());
		boxImage.add(pRobotImage.add(lbRobotIcon));
		boxImage.add(Box.createVerticalGlue());
		
		
		JPanel pCity= new JPanel();
		pCity.setBorder(new TitledBorder("City Map"));
		pCity.setLayout(new GridLayout(11,11));
		
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				pCity.add(new PlaceCell());
			}
		}
		
		JPanel pLog = new JPanel();
		pLog.setBorder(new TitledBorder("Log"));
		pLog.setLayout(new BorderLayout());
		JTextArea txtLog = new JTextArea(8,100);
		txtLog.setEditable(false);
		pLog.add(new JScrollPane(txtLog),BorderLayout.CENTER);
		
		this.add(pCity, BorderLayout.CENTER);
		this.add(boxImage, BorderLayout.WEST);
		this.add(pLog, BorderLayout.SOUTH);
		
	}

}
