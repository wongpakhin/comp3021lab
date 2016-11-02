package base;
import java.util.*;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NoteBook implements Serializable{
	
	private static final long serialVersionUID = 1L;   
	private ArrayList<Folder> folders;

	public NoteBook(){
		folders = new ArrayList<Folder>();
	}
	
	public NoteBook(String file){
		// TODO
		FileInputStream fis = null;   
		ObjectInputStream in = null;
		
		try {
				fis = new FileInputStream(file);  
				in = new ObjectInputStream(fis);
				NoteBook loadedObject = (NoteBook) in.readObject();   
				this.folders = loadedObject.getFolders();   //load object into folder
				in.close();   //close file
				
			} catch (Exception e) {
				e.printStackTrace();     
			}
	
		
		
		}
	

	public boolean createTextNote(String folderName,String title){
		TextNote note =  new TextNote(title);
		return insertNote(folderName,note);

	}

	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName,note);
	}

	//overloading method createTextNote
	public boolean createTextNote(String folderName, String title, String content){
		TextNote note = new TextNote(title, content);
		return insertNote(folderName,note);	
	}

	public ArrayList<Folder> getFolders() {
		return this.folders;
	}

	public boolean insertNote(String folderName,Note note){
		Folder f = null;

		for (Folder f1 : folders ){
			if ( folderName.equals(f1.getName()))
				f = f1;
		}
		if (f == null){
			f = new Folder(folderName);
			folders.add(f);
		}
		for (Note n : f.getNotes()){
			if (note.equals( n )){
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			}
		}
		f.addNote(note);
		return true;
	}


	public void sortFolders(){
		for(Folder f : this.folders){
			f.sortNotes();
		}
		Collections.sort(folders);
	}

	
	public List<Note> searchNotes(String Keywords){
		ArrayList<Note> result = new ArrayList<Note>();
			for (Folder f: folders){
			
				result.addAll(f.searchNotes(Keywords));
			}
		return result;
	}
	
	
	
	public boolean save(String file){
		//TODO
			FileOutputStream fos = null;
			ObjectOutputStream out = null;
			
			try {
			//TODO
				fos = new FileOutputStream(file);
				out = new ObjectOutputStream(fos);
				out.writeObject(this);
				out.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
		return true;
		}
	
	public void addFolder(String folderName) {
		if(folderName != null) {
			folders.add(new Folder(folderName));
		}
	}
	
	
	public Folder getFolder(String title) {
		Folder matchedFolder = null;
		for(Folder folder: folders) {
			// if same folder name is matched
			if(folder.getName().equals(title)) {
				matchedFolder = folder;
				break;
			}
		}
		return matchedFolder;
	}
}
	

