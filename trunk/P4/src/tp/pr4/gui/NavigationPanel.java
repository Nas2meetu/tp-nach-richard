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
	public JLabel robotIcon;
	public JPanel robotImage;
	
	public enum Directions {
		LEFT, RIGHT;
	}
	
	public NavigationPanel(){
		super();

		this.setLayout(new BorderLayout());
		JPanel robotImage = new JPanel();
		JLabel robotIcon= new JLabel();
		JButton perro = new JButton("perro");
		JButton gato = new JButton("gato");
		
		robotImage.add(Box.createVerticalGlue());
		robotImage.add(perro);
		robotImage.add(Box.createVerticalGlue());
		robotImage.add(gato);
		robotImage.add(Box.createVerticalGlue());
		
		
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
		this.add(robotImage, BorderLayout.WEST);
		this.add(pLog, BorderLayout.SOUTH);
		
	}

}
