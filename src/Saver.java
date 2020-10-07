import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;


public class Saver {
	
	HashMap<String, String> mem;
	
	public void save(String string) {
		
		try{
			FileWriter fileWriter = new FileWriter("memory.txt");
			BufferedWriter writer = new BufferedWriter(fileWriter);
			writer.write(string);
			writer.close();
		} catch(IOException e) {
			//System.out.println("I could not save memory!!!!!!!!!!!!!!!!!!!!");
		}
		
	}
	
	public HashMap<String, String> getMemory() {
		String[] str = new String[2];
		String string;
		mem = new HashMap<String, String>();
		
		try{
			FileReader fileReader = new FileReader("memory.txt");
			BufferedReader reader = new BufferedReader(fileReader);
			string = null;
			while((string = reader.readLine()) != null) {
				str = string.split("/");
				mem.put(str[0], str[1]);
			}
			reader.close();
			
		} catch(IOException e) {
			string = "";
			//System.out.println("I could not read memory!!!!!!!!!!!!!!!!!!!!");
		}
		
		return mem;	
	}
	
	
	

}
