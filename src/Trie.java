import java.util.ArrayList;

public class Trie {
	// char used for the root does not matter
	// a unique character is used for debuggng purposes
	TrieNode root = new TrieNode('#');
	
	public Trie() {}
	
	public boolean isEmpty() {
		return root.childrenSize() == 0;
	}
	
	public void clear() {
		root = new TrieNode('#');
	}
	
	public int size() {
		return root.size();
	}
	
	// returns true if the word w is in the trie
	public boolean contains(String w) {
		char[] chars = w.toUpperCase().toCharArray();
		
		TrieNode temp = root;
		// iterate over the chars of the word w
		for(char c : chars) {
			if(!temp.contains(c))
				return false;
			else
				temp = temp.get(c);
		}
		
		return temp.terminal;
		// if temp is not terminal then there is'nt a word w in the trie
		// e.g. if w is POT and the only word in the trie is POTS then
		// the node that has 'T' will not be terminal since POT is not in the trie
	}
	
	// returns true if the prefix pre is inside the trie
	public boolean isPrefix(String pre) {
		// convert the string to uppercase char array
		char[] chars = pre.toUpperCase().toCharArray();
		
		TrieNode temp = root;
		// iterate over the characters of the word w
		// exit if any of the chars is not found in the sequence
		for(char c : chars) {
			if(!temp.contains(c))
				return false;
			else
				temp = temp.get(c);
		}
		
		// all chars were found, return true
		return true;
	}
	
	// inserts the word w into the trie
	public void insert(String w) {
		// convert the string to uppercase char array
		char[] chars = w.toUpperCase().toCharArray();
		
		TrieNode temp = root;
		// iterate over the characters of the word w
		for(char c : chars) {
			if(!temp.contains(c)) { // if temp does not have a child for char c
				temp.add(c); // add a child that has char c
			}
			temp = temp.get(c); // move to the child that has char c
		}
		
		temp.setTerminal(true);
	}
	
	// deletes the word w from the trie
	public void delete(String w) {
		// convert the string to uppercase char array
		char[] chars = w.toUpperCase().toCharArray();
		
		// temp is used for traversal
		// head points to the node where deletion will happen
		TrieNode temp = root, head = root;
		char del = chars[0]; // the character that should be deleted from head
		
		// iterate over the characters of the word w
		for(char c : chars) {
			if(temp.childrenSize() > 1 || temp.isTerminal()) {
				head = temp;
				del = c;
			}
			
			if(!temp.contains(c)) { // if temp does not have a child for char c
				return; // word is not found, no deletion should happen
			}
			temp = temp.get(c); // move to the child that has char c
		}
		
		// if temp (the last node in the word) is not terminal then return
		// i.e. the word is not in the trie
		if(!temp.isTerminal()) {
			return;
		}
		
		/*
		 * if the temp (the last node in the word) has children then
		 * deletion should not occur from the head as it will remove
		 * the children of temp which contain more words that will be lost
		 * instead we should simply set temp to be non-terminal
		 */
		if(temp.childrenSize() > 0) {
			temp.setTerminal(false);
		}
		else {
			head.remove(del);
		}
	}
	
	// returns a String array that contains all the words that start with
	// the prefix pre
	public String[] allWordsPrefix(String pre) {
		ArrayList<String> array = new ArrayList<>();
		
		// convert the string to uppercase char array
		char[] chars = pre.toUpperCase().toCharArray();
		
		TrieNode temp = root;
		// iterate over the characters of pre
		for(char c : chars) {
			if(!temp.contains(c)) 
				return null; // return null if the trie does not contain the prefix
			else {
				temp = temp.get(c);
			}
		}
		
		// call the recursive method
		// the last character in pre is not taken since it is already in temp 
		allWordsPrefix(array, temp, pre.toUpperCase().substring(0, pre.length()-1));
		
		return array.toArray(new String[array.size()-1]);
	}
	
	// recursive helper method for allWordsPrefix(String pre)
	private void allWordsPrefix(ArrayList<String> arr, TrieNode n, String pre) {
		// if a word ends at n then add the word to arr
		if(n.isTerminal()) {
			arr.add(pre + n.el);
		}
		
		// iterate over the nodes in n
		// call the recursive method on all children of n
		for(char key : n.children.keySet()) {
			allWordsPrefix(arr, n.get(key), pre + n.el);
		}
	}
}