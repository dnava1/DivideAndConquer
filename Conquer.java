import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileVisitResult;
import java.util.Arrays;
import java.util.Random;
//smallblock

public class Conquer
{
    private int comparisonCount;
    private int ComparisonCount2;
    private int ComparisonCount3;
    private int sizeOfBlock2;
    //initial array size
    private int sizeOfArray = 20;
    public static void main(String[] args)
    {
    new SourceConquer();
    }
    
    public SourceConquer()
    {
        //opens the text file
        String outputFilename = "output.csv";
        PrintWriter output = null;
        //open output stream
        try
        {
            output = new PrintWriter(new FileWriter(outputFilename));
        } catch (IOException ex)
        {
            System.exit(1);
        }

        //loop of the 10,000 iterations
        for(int i=0; i<10000;i++)
        {
            Random rnd = new Random();
            //size of array is set to 20, this gets incremented by 10 every time
            comparisonCount = 0;
            ComparisonCount2 = 0;
            ComparisonCount3 = 0;
            
            int[] list= new int[sizeOfArray];
            

            fillArray(list);
            Arrays.sort(list);
            printArray(list);
        
            //random search key
	    //change this to 20 to find large block
            int k = rnd.nextInt(1000);

            System.out.println("This is my random key: " + k + "\n");
            System.out.println("Number of occurences for approach 1: " + sequentialSearch(k, sizeOfArray, list));
            System.out.println("Number of comparisons for approach 1: " + comparisonCount + "\n");

            binarySearch(list, k);
            System.out.println("Number of occurences for approach 2: " + sizeOfBlock2 );
            System.out.println("Number of comparisons for approach 2: " + ComparisonCount2 + "\n");

            System.out.println("Number of occurences for approach 3: " + (locateRightEnd(list, k) - locateLeftEnd(list, k)));
            System.out.println("Number of comparisons for appraoch 3: " + ComparisonCount3 + "\n");

    
        output.println(sizeOfArray + "," + comparisonCount + "," + ComparisonCount2 + "," + ComparisonCount3);
        sizeOfArray +=1;
        sizeOfBlock2=0;
      }
        output.close();
        
    }
    /**
     * 
     * @param list sorted array
     * @return array of length 20 with random integers
     */
    
    public void fillArray(int[] list)
    {
        //max random number, this determines bloc size
        int n = 1000;
	//change this to 20 to find large block
        Random rnd = new Random();
        for(int i=0; i<list.length;i++){
        list[i] = rnd.nextInt(n);
    }
}
/**
 * 
 * @param list sorted array
 * @return printed array
 */


public void printArray(int[] list)
{
    for(int i=0; i<list.length; i++)
    {
        System.out.print(list[i] + " ");
    }
    System.out.println();
}


/**
 * 
 * @param n key we are looking for
 * @param len size of the array
 * @param arr sorted array
 * @return number of occurences of key in array, also checks number of comparisons
 */

public int sequentialSearch(int n, int len, int[] arr){
    int sizeOfBlock = 0;
    this.comparisonCount = 0;
    boolean found = false;
    for(int i = 0; i< len - 1; i++) {
        this.comparisonCount++;
        if(arr[i]==n){
            found = true;
            sizeOfBlock++;
        
        }
        
        if(arr[i+1]!= n && found)
            break;
    }
    return sizeOfBlock;
}

/**
 * 
 * @param list sorted array
 * @param x key we are looking for
 * @return found if x is in the array; false otherwise, also checks number of occurences and comparisons
 */

public boolean binarySearch(int[] list, int x)
{
    
    return binarySearch(list, 0, list.length-1, x);
    
}
/**
 * 
 * @param list sorted array
 * @param first first position to check
 * @param last last position to check
 * @param x key we are looking for 
 * @return found if x is in the array; false otherwise, also checks number of occurences and comparisons
 */
private boolean binarySearch(int[] list, int first, int last, int x)
{
    
    boolean found;
    ComparisonCount2++;

    if(first > last)
    {
        found = false;  
    } 
    else{
        int mid = (first+last)/2;

        ComparisonCount2++;
        if (list[mid] ==x) 
        {
            found = true;
            sizeOfBlock2++;
            try{
                for(int i=1;i<list.length;i++)
            {
                if(list[mid+i]==x)
                {
                sizeOfBlock2++;
                ComparisonCount2++;
                }
                
            }
            }
            catch (ArrayIndexOutOfBoundsException exception) {   
            }


            try{
                for(int i=1;i<500;i++)
            {
                if(list[mid-i]==x)
                {
                sizeOfBlock2++;
                ComparisonCount2++;
                }
            }
            }
            catch (ArrayIndexOutOfBoundsException exception) {    
            }   
        }
        else{
            if (x < list[mid])
                found = binarySearch(list, first, mid-1, x);
            else
                found = binarySearch(list, mid+1, last, x);  
        }
    }

    
    return found;
}

/**
 * 
 * @param list sorted array
 * @param x key we are looking for
 * @return location of the left end of the key in the array
 */
public int locateLeftEnd(int[] list, int x)
{
    
    return locateLeftEnd(list, 0, list.length-1, x);
    
}
/**
 * 
 * @param list sorted array
 * @param first first position to check
 * @param last last position to check
 * @param x key we are looking for
 * @return location of the left end of the key in the array
 */
private int locateLeftEnd(int[] list, int first, int last, int x)
{
    int loc;
    ComparisonCount3++;
    if (first > last) loc = first;
    else
    {
        int mid = (first + last)/2;
        ComparisonCount3++;
        if (x <= list[mid])
            loc = locateLeftEnd(list, first, mid-1, x);
        else    
            loc = locateLeftEnd(list, mid+1, last, x);
    }

    return loc;
}

/**
 * 
 * @param list sorted array
 * @param x key we are looking for
 * @return location of the right end of the key in the array
 */


public int locateRightEnd(int[] list, int x)
{
    
    return locateRightEnd(list, 0, list.length-1, x);
    
}
/**
 * 
 * @param list sorted array
 * @param first first position to check
 * @param last last position to check
 * @param x key we are looking for
 * @return location of the right end of the key in the array
 */
private int locateRightEnd(int[] list, int first, int last, int x)
{
    int loc;
    ComparisonCount3++;
    if (first > last) loc = first;
    else
    {
        int mid = (first + last)/2;
        ComparisonCount3++;
        if (x >= list[mid])
            loc = locateRightEnd(list, mid+1, last, x);
        else    
            loc = locateRightEnd(list, first, mid-1, x);
    }

    return loc;
}
}