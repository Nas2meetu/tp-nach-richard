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
	private JTextArea txtLog; 
	private URL urlImage;
	private PlaceCell[][] placeCell;
	private PlaceCell currentPlace;

	
	public NavigationPanel() {
		super();

		this.setLayout(new BorderLayout());
		pRobotImage = new JPanel();
		pRobotImage.setLayout(new BoxLayout(pRobotImage,BoxLayout.Y_AXIS));

		
		URL urlImage = MainWindow.class.getResource("images/walleNorth.png");
		if(urlImage==null)
			System.out.println("no carga imagen alguna");
		
		robotImage = new ImageIcon(urlImage);
		lbRobotIcon = new JLabel(robotImage);
		
		pRobotImage.add(Box.createVerticalGlue());
		pRobotImage.add(lbRobotIcon);
		pRobotImage.add(Box.createVerticalGlue());
		
	
		
		JPanel pCity = new JPanel();
		pCity.setBorder(new TitledBorder("City Map"));
		pCity.setLayout(new GridLayout(11, 11));

		
		this.placeCell = new PlaceCell[11][11];
		
		for (int i = 0; i < 11; i++)
			for (int j = 0; j < 11; j++){
				placeCell[i][j] = new PlaceCell(this);
				pCity.add(placeCell[i][j]);
			}
		currentPlace= new PlaceCell();
				
		txtLog = new JTextArea();
		JPanel pLog = new JPanel(new GridLayout(1, 1));
		pLog.setBorder(new TitledBorder("Log"));
		pLog.setLayout(new BorderLayout());
		txtLog.setPreferredSize(new Dimension(10,110));
		txtLog.setEditable(false);
		
		txtLog.setText("");
		pLog.add(new JScrollPane(txtLog), BorderLayout.CENTER);

		this.add(pCity, BorderLayout.CENTER);
		this.add(pRobotImage, BorderLayout.WEST);
		this.add(pLog, BorderLayout.SOUTH);

	}

	public void updateIcon(Direction lookingDirection) {
		
		switch (lookingDirection) {
		case NORTH:
			urlImage = MainWindow.class.getResource("images/walleNorth.png");
			robotImage = new ImageIcon(urlImage);
			lbRobotIcon.setIcon(robotImage);
			break;
		case EAST:
			urlImage = MainWindow.class.getResource("images/walleEast.png");
			robotImage = new ImageIcon(urlImage);
			lbRobotIcon.setIcon(robotImage);
			
			break;
		case SOUTH:
			urlImage = MainWindow.class.getResource("images/walleSouth.png");
			robotImage = new ImageIcon(urlImage);
			lbRobotIcon.setIcon(robotImage);
		
			break;
		case WEST:
			urlImage = MainWindow.class.getResource("images/walleWest.png");
			robotImage = new ImageIcon(urlImage);
			lbRobotIcon.setIcon(robotImage);
			
			break;

		default:
			break;

		}

	}
	
	
	public void setInitialPlace(Place actualPlace) {
		this.placeCell[row][col].setPlace(actualPlace);
		this.currentPlace = this.placeCell[row][col];
		this.currentPlace.enterPlace();
		updateLog();
	}
	
	public void showCurrentPlaceLog(Place actualPlace) {
		txtLog.setText(actualPlace.toString());
	}
	
	public void setLog(PlaceCell placeCell){
		txtLog.setText(placeCell.toString());
	}

	public void updateLog(){
		txtLog.setText(currentPlace.toString());
	}
	
	public void updateCity(Place actualPlace, Direction lookingDirection) {
		currentPlace.leavePlace();
		if (lookingDirection.equals(Direction.NORTH))
			row--;
		if (lookingDirection.equals(Direction.EAST))
			col++;
		if (lookingDirection.equals(Direction.SOUTH))
			row++;	
		if (lookingDirection.equals(Direction.WEST))
			col--;
		if (this.placeCell[row][col] != null)
			this.placeCell[row][col].setPlace(actualPlace);
		currentPlace = this.placeCell[row][col];
		currentPlace.enterPlace();
	}

	

		
}
