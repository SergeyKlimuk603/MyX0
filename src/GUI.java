import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GUI extends JFrame {
	
	JLabel label;
	JButton butHum, butComp, butNew;
	JPanel butPanel;
	JPanel gameField;
	//Dimension d;
	Game butLisener;
	
	Font font = new Font ("Arial",  1, 20);
	
	Box matrixGUI[][];
	
	boolean gameOver = false;
	
	Image imageLine;
	int coordX;
	int coordY;
	
	
	GUI(String gameName, Game lisener) {
		
		super(gameName);

		butLisener = lisener;
		initButtons();
		initLabel();
		initGameField();
		initFrame();
		
	}
	
	private void initGameField(){
		gameField = new JPanel(){
						public void paintComponent(Graphics g){
							super.paintComponent(g);
							for(int x = 0; x < 3; x++){
								for(int y = 0; y < 3; y++){
									g.drawImage(matrixGUI[x][y].image,
												x * 100, y * 100, 100, 100, null);
								}
							}
							if(gameOver){
								g.drawImage(imageLine, coordX, coordY, null);
								//label.setText("Сыграем ещё?");
							}
							repaint();
						}
					};
		gameField.setPreferredSize(new Dimension(300, 300));
		gameField.addMouseListener(butLisener);
		//add(gameField);			
	}
	
	public void drawLine(Image imageLine, int coordX, int coordY, boolean gameOver) {
		this.imageLine = imageLine;
		System.out.println(imageLine);
		this.coordX = coordX * 100;
		this.coordY = coordY * 100;
		this.gameOver = gameOver;
		repaint();
	}
	
	public void repaintField(Box[][] matrix) {
		matrixGUI = matrix;
		gameField.repaint();
	}
	
	private void initFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(BorderLayout.CENTER, label);
		setResizable(false);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}
	
	public void setMenu(String menuQuestion) {
		
		label.setText(menuQuestion);
		label.setPreferredSize(new Dimension(300, 300));
		add(label, BorderLayout.CENTER);
		add(butPanel, BorderLayout.SOUTH);
		pack();
		repaint();
	}
	
	// Надо подумать как избежать повторяемости кода
	public void setStartMenu() {
		remove(gameField);
		createButPanel(butNew);
		setMenu("Начнем новую игру?");
	}
	
	public void setChangeMenu() {
		remove(gameField);
		gameOver = false;
		createButPanel(butHum, butComp);
		setMenu("Кто ходит первым?");	
	}
	
	public void gameStart(Box [][] matrix) {
		matrixGUI = matrix;
		remove(label);
		remove(butPanel);
		label.setPreferredSize(new Dimension(300, 60));
		label.setText("Играем!");
		add(label, BorderLayout.SOUTH);
		add(gameField, BorderLayout.CENTER);
		setVisible(true);
	}

	private void initButtons() {
		butHum = new JButton("Человек");
		butHum.addActionListener(butLisener);
		butHum.setFont(font);
		butComp = new JButton("Компьютер");
		butComp.addActionListener(butLisener);
		butComp.setFont(font);
		butNew = new JButton("Новая игра");
		butNew.addActionListener(butLisener);
		butNew.setFont(font);
		butPanel = new JPanel();
		butPanel.setPreferredSize(new Dimension(300, 60));
	}
	
	private void createButPanel(JButton but1, JButton but2) {
		createButPanel(but1);
		butPanel.add(but2);
	}
	
	private void createButPanel(JButton but1) {
		butPanel.removeAll();
		butPanel.setLayout(new GridLayout());
		butPanel.add(but1);
	}
	
	private void initLabel() {
		label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(font);
		label.setPreferredSize(new Dimension(300, 300));
		label.addMouseListener(butLisener);
	}
}
