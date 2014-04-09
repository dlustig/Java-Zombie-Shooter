import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Export {

    public Export(String scoreT) {
    	// this exports the high scores and saves them to a file
		FileWriter output = null;
		BufferedWriter writer = null;
		try{
		    output = new FileWriter("scores.txt");
		    writer = new BufferedWriter(output);
		    writer.write(scoreT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
	    if(writer != null) {
			try{
			    writer.close();
			} catch (IOException e) {
			}
	    }
	}
    }
}
