import java.util.Random;


public class Computer {
	
	Box [][] matrixComp;
	Random random = new Random();
	
	Computer() {
		matrixComp = new Box[3][3];
		
	}
	
	public Box [][] compMoves(Box [][] matrix, boolean nowX) {
		matrixComp = matrix;
		
		// ќпредел€ем есть ли свободные €чейки
		boolean emptyCell = false;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(matrix[i][j] == Box.EMPTY)
					emptyCell = true;
			}
		}
		
		// Ќаходим свободную €чейку и ставим в нее крестик или нолик
		if(emptyCell){
			int randCell;
			int x;
			int y;
			do{
				randCell = random.nextInt(9);
				System.out.println("randCell = " + randCell);
				x = randCell % 3;
				System.out.println("x = " + x);
				y = randCell / 3;
				System.out.println("y = " + y);
			} while(matrixComp[x][y] != Box.EMPTY);
			
			if(nowX){
				matrixComp[x][y] = Box.X;
			} else {
				matrixComp[x][y] = Box.O;
			}
		}	
		return matrixComp;
	}
}
