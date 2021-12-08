
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
		// if temp is not terminal then there is'nt a word w in the trie
		// e.g. if w is POT and the only word in the trie is POTS then
		// the node that has 'T' will not be terminal since POT is not in the trie
	}
	
	public boolean isPrefix(String pre) {
		// convert the string to uppercase char array
		char[] chars = pre.toUpperCase().toCharArray();
		
		TrieNode temp = root;
		// iterate over the characters of the word w
		for(char c : chars) {
			if(!temp.contains(c))
				return false;
			else
				temp = temp.get(c);
		}
		
		// TODO Should I keep it like this or simply return true?
		// if temp is not empty then there is a word with prefix pre
		return !temp.isEmpty();
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
		// TODO
	}
	
	public String[] allWordsPrefix(String w) {
		// TODO
		return null;
	}
}