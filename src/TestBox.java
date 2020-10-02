import java.awt.Image;

import javax.swing.ImageIcon;


public class TestBox {
	
	private final int BOXES_FIELD_SIZE = 3;
	
	Box boxesField[][];
	
	Game game;
	
	public static void main(String args[]) {
		
		new TestBox();
		
	}
	
	TestBox(){
		
		boxesField = new Box[BOXES_FIELD_SIZE][BOXES_FIELD_SIZE];
		setImageBoxes();
		setImageLines();
		game = new Game();
	}
	
	private void setImageBoxes() {
		
		for(Box box : Box.values()){
			String nameBox = box.name().toLowerCase();
			box.image = getImage(nameBox);
			box.weight = box.ordinal() * box.ordinal(); 
			System.out.println("box.weight = " + box.ordinal());
		}
	}

	private void setImageLines() {
		for(Line line : Line.values()){
			String nameLine = line.name().toLowerCase();
			System.out.println(nameLine);
			line.image = getImage(nameLine);
		}
	}
	
	private Image getImage(String nameImage) {
		String nameImageFile = "img/" + nameImage + ".png";
		Image img = new ImageIcon(getClass().getResource(nameImageFile)).getImage();
		return img;
	}
	
	public Game getGame() {
		return game;
	}
}
