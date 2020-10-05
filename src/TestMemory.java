import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class TestMemory {
	
	ArrayList<String> memoryArray = new ArrayList<String>();
	String[] memoryString;
	String memoryElement;
	String[] memElement = new String[2];
	
	
	int count;

	public static void main(String[] args) {
		new TestMemory();

	}
	
	TestMemory() {
		
		try{
			FileReader fileReader = new FileReader("memory.txt");
			BufferedReader reader = new BufferedReader(fileReader);
		
			memoryElement = null;
			while((memoryElement = reader.readLine()) != null){
				memElement = memoryElement.split("/");
				memoryArray.add(memElement[0]);	
			}
			reader.close();
			
		} catch (IOException e) {
			System.out.println("Не удалось прочитать файл(((");
		}
		int memorySize = memoryArray.size();
		memoryString = new String[memorySize];
		for(int k = 0; k < memorySize; k++){
			memoryString[k] = memoryArray.get(k);
		}
		
		count = 0;
		int i = 0;
		
		for(int k = 0; k < memorySize; k++){
			for(int m = (k + 1); m < memorySize; m++){
				
					if(memoryString[k].equals(memoryString[m])){
					count++;
					}
			}
		}
		if(count == 0){
			System.out.println("Совпадений не найдено");
		} else {
			System.out.println("Количество совпадений = " + count);
		}
		System.out.println("Всего количество строк = " + memorySize);
		
	}

}
