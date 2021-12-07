
public class Trie {
	TrieNode root = new TrieNode();
	
	public Trie() {}
	
	public boolean isEmpty() {
		return root.size() == 0;
	}
	
	public void clear() {
		root = new TrieNode();
	}
	
	public int size() {
		return root.size();
	}
	
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
	}
	
	public boolean isPrefix(String pre) {
		return true;
	}
	
	public void insert(String w) {
		// convert the string to uppercase char array
		char[] chars = w.toUpperCase().toCharArray();
		
		TrieNode temp = root;
		// iterate over the characters of the word w
		for(char c : chars) {
			if(!temp.contains(c)) { // if temp does not have a child for char c
				temp.add(c, new TrieNode(c)); // add a child that has char c
			}
			temp = temp.get(c); // move to the child that has char c
		}
		
		temp.setTerminal(true);
	}
	
	// deletes the word w from the trie
	public void delete(String w) {
		
	}
	
	public String[] allWordsPrefix(String w) {
		return null;
	}
}