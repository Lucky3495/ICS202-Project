
public class Test {
	final static char a = 0;
	public static void main(String[] args) {
		Trie t = new Trie();
		//t.insert("pot");
		t.insert("pots");
		System.out.println(t.contains("pots"));
	}

}
