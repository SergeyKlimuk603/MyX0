
public class TestSaver {

	static Box[][] boxes = new Box[3][3];
	
	
	
	
	public static void main(String[] args) {
		Computer comp = new Computer();
		Box.EMPTY.weight = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				boxes[i][j] = Box.EMPTY;
			}
		}
		
		String str;
		str = comp.boxToString(boxes);
		boxes = comp.StringToBox(str);
		str = comp.boxToString(boxes);
		
		System.out.println(str);
		

	}

}
