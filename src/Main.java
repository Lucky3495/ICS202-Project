import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	public static Trie t;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String choice;
		
		// keep taking user input until the input is '7' (the end program option)
		do {
			printMenu(); // print the menu
			choice = in.nextLine(); // take the line next
			switch(choice) {
			case "1" : t = new Trie(); break; // create a new empty trie (it replaces the current trie if any)
			
			case "2" : System.out.print("Enter your list of letters: "); 
					   String l = in.nextLine();
					   initial(l); break;
			
			case "3" : System.out.print("Enter a word to insert: "); 
					   t.insert(in.next()); break;
					   
			case "4" : System.out.print("Enter a word to delete: "); 
			   		   t.delete(in.next()); break;
			   		   
			case "5" : System.out.print("Enter a prefix: ");
					   String list = Arrays.toString(t.allWordsPrefix(in.next()));
			   		   System.out.println(list.substring(1, list.length()-1));  ; break;
			   		   
			case "6" : System.out.println("Size of the trie: " + t.size()); break;
			
			case "7" : System.out.println("Program ended"); break;
			
			default : System.out.println("Choice invalid please try again.");
			}
		} while (!choice.equals("7"));
	}
	
	public static void printMenu() {
		System.out.print("1) Create an empty trie\n"
				+ "2) Create a trie with initial letters\n"
				+ "3) Insert a word\n"
				+ "4) Delete a word\n"
				+ "5) List all words that begin with a prefix\n"
				+ "6) Size of the trie\n"
				+ "7) End\n"
				+ "Enter choice: ");
	}
	
	public static void initial(String w) {
		w.replace(" ", ""); // remove all empty spaces from w
		t = new Trie(); // initilize an empty trie
		
		try(Scanner in = new Scanner(new File("dictionary.txt"))) {
			System.out.print("Words inserted: ");
			// iterate over the words in the dictionary file
			while(in.hasNext()) {
				String dictWord = in.next();
				// if dictWord is the same length or shorter than w check if w contains all the letters of dictWord
				if(dictWord.length() <= w.length() && isIn(dictWord, w)) {
					System.out.print(dictWord + " ");
					t.insert(dictWord);
				}
			}
			System.out.println();
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary file not found.");
		}
	}
	
	public static boolean isIn(String dictWord, String w) {
		ArrayList<Character> chars = new ArrayList<>();
		for(char c : w.toUpperCase().toCharArray()) {
			chars.add(c);
		}
		
		for(char c : dictWord.toUpperCase().toCharArray()) {
			if(!chars.contains(c)) {
				return false;
			}
			else {
				chars.remove((Character)c);
			}
		}
		
		return true;
	}

}
