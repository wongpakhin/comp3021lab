package base;
import java.io.Serializable;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class TextNote extends Note implements Serializable{
	private String content;
	
	public TextNote(String title){
		super(title);
	}
	
	public TextNote(String title, String content){
		super(title);
		this.content = content;
	}
	
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
		}
	
	
	
	public String getContent(){
		return this.content;
	}
	
	
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		
		File file = new File(absolutePath);
		FileInputStream fis = null;
		BufferedReader reader = null;
		// TODO
		
		
		try {
			fis = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(fis));
			while ((result = reader.readLine()) != null) {
				System.out.println(result);
			}
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file cannot be found!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
		}
	
	public void exportTextToFile(String pathFolder) {
		//TODO
		
		FileWriter writer = null;
		BufferedWriter out = null;
		try {
			String noteTitle = this.getTitle();
			noteTitle = noteTitle.replaceAll(" ", "_");
			

			File file = new File(pathFolder + noteTitle + ".txt");
			

			writer = new FileWriter(file.getAbsolutePath());
			out = new BufferedWriter(writer);
			out.write(this.getContent());
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return;
		
		}
	
	public Character countLetters(){
		HashMap<Character,Integer> count = new HashMap<Character,Integer>();
		String a = this.getTitle() + this.getContent();
		int b = 0;
		Character r = ' ';
		for (int i = 0; i < a.length(); i++) {
			Character c = a.charAt(i);
			if (c <= 'Z' && c >= 'A' || c <= 'z' && c >= 'a') {
				if (!count.containsKey(c)) {
					count.put(c, 1);
				} else {
					count.put(c, count.get(c) + 1);
					if (count.get(c) > b) {
						b = count.get(c);
						r = c;
					}
				}
			}
		}
		return r;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}

