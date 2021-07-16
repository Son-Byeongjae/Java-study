package selfTest;

import java.util.ArrayList;
import java.util.Collections;

public class GenericManager <T extends Comparable >{
	private ArrayList<T> cList;

	public GenericManager() {
		cList = new ArrayList<T>();
	}
	
	public void add(T a) {
		cList.add(a);
	}
	
	public void sort() {
		Collections.sort(cList);
	}
	
	public String find(T a) {
		String string = "";
		for (T b : cList)
		{
			if(b.equals(a))
				string += b.toString();
		}
		return string;
	}
	
	public String toString() {
		String string = "";
		for (T a : cList)
		{
			string += a.toString();
		}
		return string;
	}
}
