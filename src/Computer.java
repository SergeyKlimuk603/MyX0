import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;


public class Computer {
	
	//m = Maps.BuildHashMap.
	
	public static final int MOVE_WEIGHT = 1000;
	Box [][] matrixComp;
	Random random = new Random();
	HashMap<String, String> mainMemory;
	ArrayList<String> makedMovies;
	Saver saver = new Saver();
	String memory;
	
	
	Computer() {
		matrixComp = new Box[3][3];
		createMainMemory();
	}
	
	private void createMainMemory() {
		mainMemory = new HashMap<String, String>();
		getMemory();
		//String str = "014444444/9";
		//String str1 = "014444444/9";
		//mainMemory.put("104444444", "9");
		//mainMemory.put("014444444", "9");
		//System.out.println("tttttttttttttt" + mainMemory.get("104444444"));
	}
	
	private void getMemory(){
		mainMemory = saver.getMemory();	
	}
	
	private String boxToString(Box [][] boxes) {
		String str = "";
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				str += String.valueOf(boxes[j][i].weight);
			}
		}
		return str;
	}
	
	private Box [][] StringToBox(String str) {
		Box[][] boxes = new Box[3][3];
		for(int j = 0; j < 3; j++){
			for(int i = 0; i < 3; i++){
				int k = j * 3 + i;
				if(str.charAt(k) == '0') boxes[i][j] = Box.X;
				if(str.charAt(k) == '1') boxes[i][j] = Box.O;
				if(str.charAt(k) == '4') boxes[i][j] = Box.EMPTY;
			}
		}
		return boxes;
	}
	
	private ArrayList<String> createPossibleMovies(String str, boolean nowX) {
		char XO = '1';
		if(nowX) XO = '0';
		ArrayList<String> createPossibleMovies = new ArrayList();
		int lenghtStr = str.length();
		//Разбиваем строку на отдельные символы для удобства формирования новых строк состояния ходов
		char [] charString = new char[lenghtStr];
		for(int k = 0; k < lenghtStr; k++){
			charString[k] = str.charAt(k);
		}
		
		// Проверяем наличие пустых клеток, если клетка пуста - вставляем в нее наш символ XO и формируем строку возможного хода.
		for(int k = 0; k < lenghtStr; k++){
			if(charString[k] == '4'){
				String st = "";
				for(int m = 0; m < lenghtStr; m++){
					if(m == k){
						st = st + XO;
					} else {
						st = st + charString[m];
					}
				}
				//System.out.println("PossibleMovie " + st);
				createPossibleMovies.add(st);
			}
		}
		return createPossibleMovies;
	}
	
	private ArrayList<String> formMovies(ArrayList<String> list) {
		ArrayList<String> newStr = new ArrayList<String>();
		String mem;
		int leight = list.size();
		for(int i = 0; i < leight; i++){
			mem = list.get(i);  //Получаем строку переданного хода
			                      //Разделяем строку на ключ и вес хода
			int weight;           //Переменная значения
			//System.out.println("mem = " + mem);
			//System.out.println("key = " + mainMemory.get(mem));
			if(mainMemory.get(mem) != null){    
				weight = Integer.parseInt(mainMemory.get(mem));  //Если такой ход имеется в памяти, получаем его вес
			} else {
				weight = MOVE_WEIGHT / 2;  //Если нет, то ставим вес по умолчанию
				}
			String st = mem + "/" + weight;
			//System.out.println("Новая строка кода" + st);
			newStr.add(st); // добавляем в новый передаваемый массив ходов
			
		}
		
		return newStr;
	}
	
	private String getExelentMovie(ArrayList<String> list) {
		int leight = list.size();
		String[] str = new String[2];
		int summ = 0;
		int weight[] = new int[leight];
		
		for(int i = 0; i < leight; i++){
			str = list.get(i).split("/");
			//System.out.println(str[0] + "   " + str[1]);
			weight[i] = Integer.parseInt(str[1]);
			summ += weight[i];
		}
		System.out.println("summ = " + summ);
		
		int rand = random.nextInt(summ);
		int step = -1;
		
		while(rand >= 0){
			step++;
			rand = rand - weight[step];
		}
		String s = list.get(step);
		//str = s.split("/");
		return s;//str[0];
			
	}
	//В начале новой игры создается новая память makedMovies сделанных ходов
	private void gameBegin(String st) {
		int summ = 0;
		for(int i = 0; i < 9; i++){
			st.charAt(i);
			summ += Character.getNumericValue(st.charAt(i));
		}
		if(summ > 31){
			makedMovies = new ArrayList<String>();
			//System.out.println("Создали новую память");
		}
	}
	
	
	
	public Box [][] compMoves(Box [][] matrix, boolean nowX) {
		String str = "";
		str = boxToString(matrix);
		gameBegin(str);
		ArrayList<String> possibleMovies = new ArrayList();
		possibleMovies = createPossibleMovies(str, nowX);
		//Формируем вес хода. если в основной памяти есть такой ход, то берем значение веса оттуда
		//если в основной памяти такого хода нет, то присваиваем ходу вес 0,5MOVE_WEIGHT.
		possibleMovies = formMovies(possibleMovies);
		String exelentMovie = getExelentMovie(possibleMovies);
		makedMovies.add(exelentMovie);
		
		
		//for(String pr : makedMovies){
		//	System.out.println(pr);
		//}
		
		//System.out.println("Exelent movie = " + getExelentMovie(possibleMovies));
		matrix = StringToBox(exelentMovie);
		//System.out.println(str);
		return matrix;
	}
	
	public void saveMemory(int result) {
		System.out.println("результат игры " + result);
		String[] str;
		String st;
		int weight;
		int leight = makedMovies.size();
		int newResult = result;
		for(int i = 0; i < leight; i++){
			st = makedMovies.get(i);
			str = new String[2];
			str = st.split("/");
			weight = Integer.parseInt(str[1]);
			newResult = newResult * (i+1);
			weight += newResult;
			System.out.println("В ход " + str[0] + " записалась разность " + newResult);
			if(weight > MOVE_WEIGHT) {
				weight = MOVE_WEIGHT;
			} 
			if(weight < 1) {
				weight = 1;
			} 
			st = str[0] + "/" + weight;
			
			//System.out.println("Новая память " + st);
			mainMemory.put(str[0], "" + weight);
		}
		convertMemoryToString();
		saver.save(memory);
		
	}
	
	private void convertMemoryToString() {
		memory = "";
		for(Entry<String, String> entry : mainMemory.entrySet()){
			String key = entry.getKey();
			String weight = entry.getValue();
			memory += key + "/" + weight + "\n";
		}
		//System.out.println("Ищи файл с таким содержимым: " + memory);
	}
}
