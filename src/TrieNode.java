import java.util.HashMap;

public class TrieNode implements Comparable<TrieNode>{
	protected char el; // character stored in the node
	protected boolean terminal; // if the node denotes the end of a word
	// list of children of the node
	protected HashMap<Character, TrieNode> children = new HashMap<>();
	
	public TrieNode(char el) {
		this.el = el;
	}
	
	public TrieNode() {
		this.el = '#'; // the hashtag denotes the root node
	}
	
	public void setTerminal(boolean terminal) {
		this.terminal = terminal;
	}
	
	@Override
	public int compareTo(TrieNode o) {
		if(this.el > o.el) {
			return 1;
		}
		if(this.el < o.el) {
			return -1;
		}
		
		return 0;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof TrieNode))
			return false;
		else if(this.el == ((TrieNode)o).el) {
			return true;
		}
		return false;
	}
	
	public boolean contains(char a) {
		if(children.containsKey(a)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public TrieNode get(char a) {
		return children.get(a);
	}
	
	public int size() {
		return children.size();
	}
	
	public void add(char a, TrieNode n) {
		children.put(a, n);
	}
}
