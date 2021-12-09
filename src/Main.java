import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	public static Trie t;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String choice;
		do {
			printMenu();
			choice = in.nextLine();
			switch(choice) {
			case "1" : t = new Trie(); break;
			
			case "2" : System.out.print("Enter your list of letters: "); 
					   String l = in.nextLine();
					   initial(l); break;
			
			case "3" : System.out.print("Enter a word to insert: "); 
					   t.insert(in.next()); break;
					   
			case "4" : System.out.print("Enter a word to delete: "); 
			   		   t.delete(in.next()); break;
			   		   
			case "5" : System.out.print("Enter a prefix: ");
					   String list = Arrays.toString(t.allWordsPrefix(in.next()));
			   		   System.out.println(list.substring(0, list.length()));  ; break;
			   		   
			case "6" : System.out.println("Size of the trie: " + t.size()); break;
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
		w.replace(" ", "");
		t = new Trie();
		try(Scanner in = new Scanner(new File("dictionary.txt"))) {
			while(in.hasNext()) {
				String dictWord = in.next();
				if(dictWord.length() <= w.length() && isIn(dictWord, w)) {
					System.out.print(dictWord + " ");
					t.insert(dictWord);
				}
			}
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
