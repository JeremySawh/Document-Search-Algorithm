import java.util.*;
import java.io.*;

/**
 * SYSC 2100
 * Assignment 2
 * @author Jeremysawh
 *
 */


public class CountSubstrings {
	
	private static LinkedList<Character> TextLinkedList    = new LinkedList<Character>();
	private static ArrayList<Character>  TextArrayList     = new ArrayList<Character>();
	private static List<Character>       ArrayListPattern  = new ArrayList<Character>();
	private static List<Character>       LinkedListPattern = new LinkedList<Character>();


	
	/**
	 *  Returns the lowest index at which substring pattern begins in text (or else -1).
	 * 
	 * @param text     of type List<Character>
	 * @param pattern  of type List<Character>
	 * @return
	 */
	
	private static int findBrute(List<Character> text, List<Character> pattern) 
	{
		int n = text.size();
		int m = pattern.size();
		for (int i = 0; i <= n - m; i++) { // try every starting index within text
			int k = 0; // k is index into pattern	
			while (k < m && text.get(i + k) == pattern.get(k)){
				// kth character of pattern matches
				k++;
				}
			if (k == m) // if we reach the end of the pattern,
				return i; // substring text[i..i+m-1] is a match
			}
		return -1; // search failed
		}
	
	
	
	/**
	 * Searches for the pattern speficied within a document and returns the 
	 * total number of occurances of that pattern
	 * 
	 * @param text    the document to be searched of type List<Character>
	 * @param pattern the Pattern we are looking for of type List<Character>
	 * @return        the total amount of occurances for the pattern
	 */
	 
	private static int Search(List<Character> text, List<Character> pattern){
		int count = 0;
		int b = 0;

		while(b !=-1){	
			b = findBrute(text, pattern);
			if(b == -1){}
			else{
				text = text.subList(b+pattern.size(),text.size());
				count++;
			}
		}
		return count;
	}
	
	
	/**
	 * This Method takes the characters in a String and adds them to the 
	 * list specified in the paramerters.  
	 * 
	 * @param S     String of characters 
	 * @param list  List of type Characters
	 */
	
	private static void StringToList(String S, List<Character> list)
	{
		for(int i=0; i<S.toCharArray().length; i++){
			list.add(S.toCharArray()[i]);
		}
	}
	
	
	/**
	 * This function searches a document reading line by line for the pattern.  
	 * Once Pattern is found count is incremented and returned when 
	 * there are no more occurances of that pattern.
	 * 
	 * @param s       String of the file name
	 * @param list    List of type characters
	 * @param pattern list of type characters containing the pattern to look for
	 * @return count  the number of occurances of that pattern
	 */
	
	private static int ReadfileAndSearch(String s, List<Character> list, List<Character> pattern)
	{
		int count=0;		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(s));
			try {
				//Read file line by line and search for pattern in each text line
				for(String line; (line = br.readLine()) != null;) {
				    StringToList(line,list); 
				    count = count + Search(list, pattern);
				    list.clear();
					}
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {

			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	
	
	
	/**
	 * Main Running Argument
	 * @param args 
	 */
	
	public static void main(String[] args)
	{
		Scanner sc;
		String  filename;
		String  pattern;
		double  timeStart;
		double  timeEnd;
		int     matches;
		
		
		System.out.print("Please enter the path for the input file: ");
		sc = new Scanner(System.in);
		filename = sc.nextLine();
		
		//Check if Filename exists, if not prompt user to enter another name
		while(!new File(filename).exists())
		{
			System.out.println("File not found try again\n");
			System.out.print("Please enter the path for the input file: ");
			filename = sc.nextLine();
		}

		System.out.print("Enter the pattern to look for: ");
		pattern = sc.next();
		sc.close();
		
		
		//Store the Characters of the pattern in array for later use
		for(int i=0; i<pattern.length(); i++)
		{
			 
			ArrayListPattern.add(pattern.charAt(i));
			LinkedListPattern.add(pattern.charAt(i));
			//System.out.println(ArrayListPattern.toString());
		}
			
		
		//ArrayList timmed Search
		timeStart = System.currentTimeMillis();
		matches   = ReadfileAndSearch(filename, TextArrayList, ArrayListPattern);
		timeEnd   = System.currentTimeMillis();
		System.out.println("Using Arraylists:  " + matches + " matches, derived in: " + (timeEnd - timeStart)  + " milliseconds.");
		
		
		//LinkList timmed Search
		timeStart = System.currentTimeMillis();
		matches   = ReadfileAndSearch(filename, TextLinkedList, LinkedListPattern);
		timeEnd   = System.currentTimeMillis();
		System.out.println("Using Linkedlists: " + matches + " matches, derived in: " + (timeEnd- timeStart) +  " milliseconds." );

		
	}

}

