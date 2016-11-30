
public class Main {

	public static void main(String[] args) {
		
		Trie names = new Trie("Ralph");
		names.add("Greg");
		names.add("Gregory");
		
		System.out.println(names.size());
		System.out.println(names.contains("Ra"));
		System.out.println(names.contains("ralph"));
		System.out.println(names.containsPrefix("Ra"));
		names.clear();
		System.out.println(names.size());
	}

}
