import java.util.Arrays;
import java.util.Comparator;

public class SortedScores{
    public String[] SortedScores(String [] returnVal){
        // sorts the score by turning the string array into an integer array
        // and using the Arrays.sort method
        int length = returnVal.length;
        Integer[] scoreArray = new Integer[length];
        
        int [] scs = new int[length];
        for(int i = 0; i < length; i++){
            scs[i] = Integer.parseInt(returnVal[i]);
        }
        
        for(int q = 0; q < length; q++){
            scoreArray[q] = new Integer(scs[q]);
        }
        
        Arrays.sort(scoreArray, new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
                return i2.intValue() - i1.intValue();
            }
        });
        
        
        for(int w = 0; w < length; w++){
            scs[w] = scoreArray[w];
        }

        for(int j = 0; j < length; j++){
            returnVal[j] = Integer.toString(scs[j]);
        }

        return returnVal;
    }
    
}
