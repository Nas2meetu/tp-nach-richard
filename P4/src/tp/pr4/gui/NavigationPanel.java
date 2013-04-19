package tp.pr4.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import tp.pr4.Direction;
import tp.pr4.Place;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.ScrollPane;
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
	
	private int col = 5;
	private int row = 5;
	
	private JLabel lbRobotIcon;
	private ImageIcon robotImage;
	private JPanel pRobotImage;
	private JTextArea txtLog = new JTextArea();
	private URL urlImage;
	private PlaceCell[][] placeCell;
	private PlaceCell currentPlace;

	// public enum Directions {
	// LEFT, RIGHT;
	// }

	public NavigationPanel() {
		super();

		this.setLayout(new BorderLayout());
		pRobotImage = new JPanel();
		pRobotImage.setLayout(new BoxLayout(pRobotImage,BoxLayout.Y_AXIS));

		// EnumMap<Direction, ImageIcon> icons = null;
		URL urlImage = MainWindow.class.getResource("images/walleNorth.png");
		if(urlImage==null)
			System.out.println("no carga imagen alguna");
		/*
		 * if (urlImage==null) JOptionPane.showMessageDialog(getRootPane(),
		 * "The Folders image cannot found"); else{ URL urlImage2 =
		 * MainWindow.class.getResource("images/walleEast.png"); }
		 */
		robotImage = new ImageIcon(urlImage);
		lbRobotIcon = new JLabel(robotImage);
		
		pRobotImage.add(Box.createVerticalGlue());
		pRobotImage.add(lbRobotIcon);
		pRobotImage.add(Box.createVerticalGlue());
		
	
		//boxImage = Box.createVerticalBox();
		//boxImage.add(Box.createVerticalGlue());
		//boxImage.add(pRobotImage.add(lbRobotIcon));
		//boxImage.add(Box.createVerticalGlue());
		
		
		
		JPanel pCity = new JPanel();
		pCity.setBorder(new TitledBorder("City Map"));
		pCity.setLayout(new GridLayout(11, 11));

		
		this.placeCell = new PlaceCell[11][11];
		
		for (int i = 0; i < 11; i++)
			for (int j = 0; j < 11; j++)
				placeCell[i][j] = new PlaceCell(this);
				
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				pCity.add(placeCell[i][j]);
			}
		}
		
		//pCity.getComponentAt(5, 5).setBackground(Color.GREEN);
	
		//this.placeCell[row][col].setVisited();
		//this.placeCell[row][col].setBackground(Color.GREEN);
		
		//pCity.add(placeCell[row][col]);

		JPanel pLog = new JPanel(new GridLayout(1, 1));
		pLog.setBorder(new TitledBorder("Log"));
		pLog.setLayout(new BorderLayout());
		txtLog.setPreferredSize(new Dimension(8,100));
		txtLog.setEditable(false);
		txtLog.setText("");
		pLog.add(new JScrollPane(txtLog), BorderLayout.CENTER);

		this.add(pCity, BorderLayout.CENTER);
		this.add(pRobotImage, BorderLayout.WEST);
		this.add(pLog, BorderLayout.SOUTH);

	}

	public void update(Direction lookingDirection) {
		
		switch (lookingDirection) {
		case NORTH:
			urlImage = MainWindow.class.getResource("images/walleNorth.png");
			robotImage = new ImageIcon(urlImage);
			lbRobotIcon.setIcon(robotImage);
			//pRobotImage.seti(lbRobotIcon);
			//lbRobotIcon.setIcon(robotImage);
			//boxImage.add(pRobotImage.add(lbRobotIcon));
			break;
		case EAST:
			urlImage = MainWindow.class.getResource("images/walleEast.png");
			robotImage = new ImageIcon(urlImage);
			lbRobotIcon.setIcon(robotImage);
			//pRobotImage.add(lbRobotIcon);
			break;
		case SOUTH:
			urlImage = MainWindow.class.getResource("images/walleSouth.png");
			robotImage = new ImageIcon(urlImage);
			lbRobotIcon.setIcon(robotImage);
			//pRobotImage.add(lbRobotIcon);
			break;
		case WEST:
			urlImage = MainWindow.class.getResource("images/walleWest.png");
			robotImage = new ImageIcon(urlImage);
			lbRobotIcon.setIcon(robotImage);
			//pRobotImage.add(lbRobotIcon);
			break;

		default:
			break;

		}

	}
	
	public void setInitialPlace(Place place) {
		this.placeCell[row][col].setPlace(place);
		this.currentPlace = this.placeCell[row][col];
		updateLog();
	}
	
	public void showCurrentPlaceLog() {
		txtLog.setText(currentPlace.toString());
	}
	
	public void setLog(PlaceCell placeCell){
		txtLog.setText(placeCell.toString());
	}

	public void updateLog(){
		txtLog.setText(currentPlace.toString());
	}
		
}
