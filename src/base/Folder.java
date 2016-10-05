package base;
import java.util.*;
import java.io.*;


public class Folder implements Comparable<Folder>,Serializable{
	
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name){
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note){
		notes.add(note);
	}
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Note> getNotes(){
		return this.notes;
	}
	
	public String toString(){
		int nText = 0;
		int nImage = 0;
		
		for ( Note note : notes  ){
			if ( note instanceof ImageNote )
		        nImage +=1;
			else if ( note instanceof TextNote )
		        nText +=1;
		    }
		return name + ":" + nText + ":" + nImage;
	}

	
	
	public void sortNotes(){
		
		Collections.sort(notes);
		
	}
	
	
	public List<Note> searchNotes(String keywords){
		
		List<Note> result = new ArrayList<Note>();
		String[] keywordsList = keywords.split(" ");

		List<String> andArr = new ArrayList<>();
		List<String> orArr  = new ArrayList<>();

		for (int i =0 ; i< keywordsList.length ; i++){

			if (keywordsList[i]!=null&&keywordsList[i].equalsIgnoreCase("or")){
				orArr.add(keywordsList[i+1].toLowerCase());
				orArr.add(keywordsList[i-1].toLowerCase()); 
				keywordsList[i-1] = null;
				keywordsList[i] = null;
				keywordsList[i+1] = null;

			}

		}

		for (String s : keywordsList){
			if ( s != null )
				andArr.add(s.toLowerCase());
		}

		for (Note n: notes){

			boolean exist=false;
			
			if(n instanceof TextNote){
				String testTitle = n.getTitle().toLowerCase();
				String testContent = ((TextNote)n).getContent().toLowerCase();

				for (String s : andArr){ //search title (and)
					if (testTitle.contains(s))
						exist = true;
					else 
						exist = false;
				}

				for (int j=0;j<orArr.size();j+=2){ 
					if (testTitle.contains(orArr.get(j)) || testTitle.contains(orArr.get(j+1)))
						exist = true;
					else 
						exist = false;

				}

				for (String s : andArr){ //search content
					if (s!=null && testContent.contains(s))
						exist = true;
					else 
						exist = false;

				}

				for (int j=0;j<orArr.size();j+=2){
					if (testContent.contains(orArr.get(j))||testContent.contains(orArr.get(j+1)))
						exist = true;
					else 
						exist = false;
				}
			}
			else{

				String testTitle = n.getTitle().toLowerCase();

				for (String s: andArr){
					if (testTitle.contains(s))
						exist = true;
					else 
						exist = false;
				}

				for (int j=0;j<orArr.size();j+=2){
					if (testTitle.contains(orArr.get(j)) || testTitle.contains(orArr.get(j+1)))
						exist = true;
					else 
						exist = false;
				} 

			}
			if (exist)
				result.add(n);
		}
		return result;
	}


	
	
	
	@Override
	public int compareTo(Folder f){
		return this.name.compareTo(f.name);
	}
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		return true;
	}

}
