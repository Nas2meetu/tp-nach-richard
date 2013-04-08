package tp.pr4.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import tp.pr4.Direction;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.EnumMap;


/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 4
*
*/

public class NavigationPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	//private int column, row;

	
	//public enum Directions {
	//	LEFT, RIGHT;
	//}
	
	public NavigationPanel(){
		super();

		this.setLayout(new BorderLayout());
		JPanel pRobotImage = new JPanel();
				
		EnumMap<Direction, ImageIcon> icons = null;
		//URL urlImage = MainWindow.class.getResource("images/*.png");
		if (icons==null)
			JOptionPane.showMessageDialog(getRootPane(), "The Folders image cannot found");
		else{
			URL urlImage2 = MainWindow.class.getResource("images/walleEast.png");
		}
		ImageIcon robotImage = new ImageIcon(); 
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
