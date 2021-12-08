import java.util.HashMap;

public class TrieNode {
	protected char el; // character stored in the node
	protected boolean terminal; // if the node denotes the end of a word
	// list of children of the node
	protected HashMap<Character, TrieNode> children = new HashMap<>();
	
	public TrieNode(char el) {
		this.el = el;
	}
	
	// returns true if this TrieNode has no children
	public boolean isEmpty() {
		return children.isEmpty();
	}
	
	// returns true if this TrieNode terminates a word
	public boolean isTerminal() {
		return this.terminal;
	}
	
	// sets the boolean value of terminal to the input of the method
	public void setTerminal(boolean terminal) {
		this.terminal = terminal;
	}
	
	// returns true if this TrieNode has a child associated with the char a
	public boolean contains(char a) {
		if(children.containsKey(a)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// returns the child of this TrieNode that is associated with the char a
	// returns null if there is no such child
	public TrieNode get(char a) {
		return children.get(a);
	}
	
	// returns the number of children of this TrieNode
	public int childrenSize() {
		return children.size();
	}
	
	// returns the number of nodes that are below this TrieNode
	public int size() {
		return size(0);
	}
	
	// recursive helper method for the method size()
	private int size(int s) {
		for(char key : children.keySet()) {
			s += children.get(key).size();
		}
		return children.size() + s;
	}
	
	// add a child to this TrieNode that is associated with the char a
	public void add(char a) {
		children.put(a, new TrieNode(a));
	}
	
	// remove the child of this TrieNode that is associated with the char a
	public void remove(char a) {
		children.remove(a);
	}
	
}
