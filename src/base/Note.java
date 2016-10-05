package base;
import java.util.Date;
import java.io.*;


public class Note implements Comparable<Note>,Serializable{
	private Date date;
	private String title;
	
	public Note(String title){
		this.title=title;
		date = new Date(System.currentTimeMillis());	
	}
	
	public String getTitle(){
		return this.title;
	}

	public boolean equals(Note inputNote){

		    if ( title.equals( inputNote.getTitle() ))
		      return true;

		    return false;

		  }

	@Override
	public int compareTo(Note o){
		if(this.date.before(o.date))
			return 1;
		else if(this.date.after(o.date))
			return -1;
		else 
			return 0;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Note other = (Note) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	public String toString(){
		return date.toString()+ "\t" + title;
	}
}
