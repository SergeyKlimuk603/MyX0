import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Game implements ActionListener, MouseListener {
	
	GUI gui;
	Box[][] matrix;
	Computer comp;
	boolean humanMoves = true;
	int  step = 8;//Количество оставшихся ходов для определения конца игры в случае если никто не выиграл
	boolean nowX = true;
	boolean gameOver = false;
	Image drawLine;
	Box line[] = new Box[3];
	
	Game() {
		initMatrix();
		comp = new Computer();
		//line = new Box[3];
		gui = new GUI("Крестики Нолики", this);
		gui.setStartMenu();
	}
	
	private void initMatrix() {
		matrix = new Box[3][3];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				matrix[i][j] = Box.EMPTY;
	}
	
	public void actionPerformed(ActionEvent ae){
		if(ae.getActionCommand().equals("Новая игра")){
			gui.setChangeMenu();
		}
		if(ae.getActionCommand().equals("Человек")){
			gameStart(true);
		}
			
		if(ae.getActionCommand().equals("Компьютер")){
			gameStart(false);
		}
	}
	
	public void gameStart(boolean move) {
		
		gameOver = false;
		step = 8;
		nowX = true;
		humanMoves = move;
		if(!humanMoves){
			
			matrix = comp.compMoves(matrix, nowX);
			nowX = !nowX;
			step -= 1;
			System.out.println("step = " + step);
			gui.repaintField(matrix);
		}
		gui.gameStart(matrix);
	}

	private void checkHorLine(int i, int j) {
		int summ = 0;
		try{
			summ = matrix[i][j].weight + matrix[i + 1][j].weight + matrix[i + 2][j].weight;
		} catch(ArrayIndexOutOfBoundsException a) {
			summ = -1;
			return;
		}
		if(summ == 0 || summ == 3){
			gameOver = true;
			gui.drawLine(Line.HORLINE.image, i, j, gameOver);
		}
	}
	
	private void checkVerLine(int i, int j) {
		int summ = 0;
		try{
			summ = matrix[i][j].weight + matrix[i][j + 1].weight + matrix[i][j + 2].weight;
		} catch(ArrayIndexOutOfBoundsException a) {
			summ = -1;
			return;
		}
		if(summ == 0 || summ == 3){
			gameOver = true;
			gui.drawLine(Line.VERTLINE.image, i, j, gameOver);
		}
	}
	
	private void checkSlashLine(int i, int j) {
		int summ = 0;
		try{
			summ = matrix[i][j].weight + matrix[i + 1][j - 1].weight + matrix[i + 2][j - 2].weight;
		} catch(ArrayIndexOutOfBoundsException a) {
			summ = -1;
			return;
		}
		if(summ == 0 || summ == 3){
			gameOver = true;
			gui.drawLine(Line.SLASHLINE.image, 0, 0, gameOver);
		}
	}
	
	private void checkBackSlashLine(int i, int j) {
		int summ = 0;
		try{
			summ = matrix[i][j].weight + matrix[i + 1][j + 1].weight + matrix[i + 2][j + 2].weight;
		} catch(ArrayIndexOutOfBoundsException a) {
			summ = -1;
			return;
		}
		if(summ == 0 || summ == 3){
			gameOver = true;
			gui.drawLine(Line.BACKSLASHLINE.image, 0, 0, gameOver);
		}
	}
	
	private void easyCheckGame(Box[][] matrix) {
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				checkVerLine(i, j);
				checkHorLine(i, j);
				checkSlashLine(i, j);
				checkBackSlashLine(i, j);		
			}
		}
		if(step < 0){
			gameOver = true;
			gui.label.setText("Ничья!");
		}
		
		/*
		int summ;
		
		summ = 0;
		for(int i = 0; i < 3; i++){
			summ = matrix[i][0].weight + matrix[i][1].weight + matrix[i][2].weight;
			if(summ == 0 || summ == 3) {
				gameOver = true;
				drawLine = Line.VERTLINE.image;
				int drawLineX = i * 100;
				int drawLineY = 0;
				gui.drawLine(drawLine, drawLineX, drawLineY, gameOver);
			}
		}
		
		summ = 0;
		for(int j = 0; j < 3; j++){
			summ = matrix[0][j].weight + matrix[1][j].weight + matrix[2][j].weight;
			if(summ == 0 || summ == 3) {
				gameOver = true;
				drawLine = Line.HORLINE.image;
				int drawLineX = 0;
				int drawLineY = j * 100;
				gui.drawLine(drawLine, drawLineX, drawLineY, gameOver);
			}		
		}
		
		summ = 0;
		for(int j = 0; j < 3; j++){
			summ += matrix[j][j].weight;
		}
		
		System.out.println("summ = " + summ);
		if(summ == 0 || summ == 3) {
			gameOver = true;
			drawLine = Line.BACKSLASHLINE.image;
			int drawLineX = 0;
			int drawLineY = 0;
			gui.drawLine(drawLine, drawLineX, drawLineY, gameOver);
		}
			
		summ = 0;
		for(int j = 0; j < 3; j++){
			summ += matrix[j][2 - j].weight;
		}
		
		if(summ == 0 || summ == 3) {
			gameOver = true;
			drawLine = Line.SLASHLINE.image;
			int drawLineX = 0;
			int drawLineY = 0;
			gui.drawLine(drawLine, drawLineX, drawLineY, gameOver);
		}*/
	
	}
	
/*	private void checkGame(Box[][] matrix) {
		if(matrix[0][0] != Box.EMPTY
		   && matrix[0][0] == matrix[1][0] 
		   && matrix[1][0] ==  matrix[2][0]){
				gameOver = true;
				drawLine = Line.HORLINE.image;
				int drawLineX = 0;
				int drawLineY = 0;
				gui.drawLine(drawLine, drawLineX, drawLineY, gameOver);
		}
		
		if(matrix[0][1] != Box.EMPTY
				   && matrix[0][1] == matrix[1][1] 
				   && matrix[1][1] ==  matrix[2][1]){
						gameOver = true;
						drawLine = Line.HORLINE.image;
						int drawLineX = 0;
						int drawLineY = 100;
						gui.drawLine(drawLine, drawLineX, drawLineY, gameOver);
		}
		
		if(matrix[0][2] != Box.EMPTY
				   && matrix[0][2] == matrix[1][2] 
				   && matrix[1][2] ==  matrix[2][2]){
						gameOver = true;
						drawLine = Line.HORLINE.image;
						int drawLineX = 0;
						int drawLineY = 200;
						gui.drawLine(drawLine, drawLineX, drawLineY, gameOver);
		}
		
		if(matrix[0][0] != Box.EMPTY
				   && matrix[0][0] == matrix[0][1] 
				   && matrix[0][1] ==  matrix[0][2]){
						gameOver = true;
						drawLine = Line.VERTLINE.image;
						int drawLineX = 0;
						int drawLineY = 0;
						gui.drawLine(drawLine, drawLineX, drawLineY, gameOver);
		}
				
		if(matrix[1][0] != Box.EMPTY
				   && matrix[1][0] == matrix[1][1] 
				   && matrix[1][1] ==  matrix[1][2]){
						gameOver = true;
						drawLine = Line.VERTLINE.image;
						int drawLineX = 100;
						int drawLineY = 0;
						gui.drawLine(drawLine, drawLineX, drawLineY, gameOver);
		}
				
		if(matrix[2][0] != Box.EMPTY
				   && matrix[2][0] == matrix[2][1] 
				   && matrix[2][1] ==  matrix[2][2]){
						gameOver = true;
						drawLine = Line.VERTLINE.image;
						int drawLineX = 200;
						int drawLineY = 0;
						gui.drawLine(drawLine, drawLineX, drawLineY, gameOver);
		}
		
		if(matrix[0][0] != Box.EMPTY
				   && matrix[0][0] == matrix[1][1] 
				   && matrix[1][1] ==  matrix[2][2]){
						gameOver = true;
						drawLine = Line.BACKSLASHLINE.image;
						int drawLineX = 0;
						int drawLineY = 0;
						gui.drawLine(drawLine, drawLineX, drawLineY, gameOver);
		}
		
		if(matrix[0][2] != Box.EMPTY
				   && matrix[0][2] == matrix[1][1] 
				   && matrix[1][1] ==  matrix[2][0]){
						gameOver = true;
						drawLine = Line.SLASHLINE.image;
						int drawLineX = 0;
						int drawLineY = 0;
						gui.drawLine(drawLine, drawLineX, drawLineY, gameOver);
		}
				
	}
*/
	//Тут записаны ходы компьютера и человека, надо убрать повторяемость кода
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(gameOver){
			initMatrix();
			gui.setStartMenu();
		} else {
		
		
			int x = e.getX()/100;
			int y = e.getY()/100;
			if(matrix[x][y] == Box.EMPTY){
				//nowX = !nowX;
				if(nowX){
					matrix[x][y] = Box.X;
				} else {
					matrix[x][y] = Box.O;
				}
				step -= 1;
				System.out.println("step = " + step);
				gui.repaintField(matrix);
				easyCheckGame(matrix);
				if(gameOver)return;
				nowX = !nowX;
				matrix = comp.compMoves(matrix, nowX);
				step -= 1;
				System.out.println("step = " + step);
				gui.repaintField(matrix);
				easyCheckGame(matrix);
				nowX = !nowX;
			}	
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
