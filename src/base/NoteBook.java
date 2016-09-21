package base;
import java.util.*;

public class NoteBook{

	private ArrayList<Folder> folders;

	public NoteBook(){
		folders = new ArrayList<Folder>();
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
}
	

