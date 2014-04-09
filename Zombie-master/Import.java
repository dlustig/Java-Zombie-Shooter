import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;

public class Import{
	public static String[] Import() {
	    // this imports the high scores from a text file
	    String returnVal[] = new String[10];
	    FileReader file = null;

	    try{
		file = new FileReader("scores.txt");
		BufferedReader reader = new BufferedReader(file);
		for(int i = 0; i < 10; i++){
		    returnVal[i] = reader.readLine();
		}
	    } catch(Exception e){
		throw new RuntimeException(e);
	    }finally{
		if(file!=null){
		    try{
			file.close();
		    } catch(IOException e){
		    }
		}
	    }
	// calls the sorted scores function to sort the scores in descending order    
        SortedScores sort = new SortedScores();
        returnVal = sort.SortedScores(returnVal);
        System.out.println(sort);
	    return returnVal;
	}
}
