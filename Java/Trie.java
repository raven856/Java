/**
 * @author Ralph
 * a trie tree class, contents are case-insensitive
 */
public class Trie {
	
	TrieNode root;
	/**
	 * number of words that have been added to the Trie
	 */
	int size;
	/**
	 *Each node has a character, a flag, and up to 26 children.
	 *Flag will be set to true if it is the node
	 * that contains last character of a word
	 */
	class TrieNode {

		boolean flag = false;
		
		boolean hasChild = false;
		
		char character;
				
		TrieNode[] children = new TrieNode[26]; 
		
		public boolean addNodesFor(String word){

			char letter = word.charAt(0);
			int index = letter - 'a';
			
			if (this.children[index] == null){
				this.children[index] = new TrieNode();
				//added a character value to the node
				this.children[index].character = letter;
				
			} 
			if (word.length()==1){
				this.flag = true;
				return true;
			}
				
			return this.children[index].addNodesFor(word.substring(1));
		}

		public boolean nodeContains(TrieNode node, String subString) {
//			if (node==null){
//				return false;
//			}
			if (subString.length()==1){
				return node.flag;
			}
			char letter = subString.charAt(0);
			int index = letter - 'a';
			if (node.children[index]==null){
				return false;
			}
			return node.children[index].nodeContains(node.children[index], subString.substring(1));

		}

		public boolean nodeContainsPrefix(TrieNode node, String subString) {
//			if (node == null){
//				return false;
//			}
			
			if (subString.isEmpty()){
				return true;
			}
			char letter = subString.charAt(0);
			int index = letter - 'a';
			if (node.children[index]==null){
				return false;
			}
			return node.children[index].nodeContainsPrefix(node.children[index], subString.substring(1));
		}
	}
		/**
		 * Constructor for a new Trie
		 */
	public Trie(){
		root = new TrieNode();
    }
	/**
	 * Constructor for a new Trie with parameter added as a word
	 * @param word of type string
	 */
    public Trie(String word){
    	root = new TrieNode();
        this.add(word);
    }
	
	/**
	 * @param word of type string
	 * @return true if success (not already stored)
	 */
	public boolean add(String word){  
		word=word.toLowerCase();
		if (this.contains(word)){
			return false;
		}
		if (word.isEmpty()){
			return true;
		}

		char letter = word.charAt(0);
		int index = letter - 'a';
		
		//If child node for the character is not in place->add it
		if (root.children[index] == null){
			root.children[index] = new TrieNode();
			//added a character value to the new node
			root.children[index].character = letter;	
		} 
		//Child character is in place
		size++;
		return root.children[index].addNodesFor(word.substring(1));			
	}
	
	/**
	 * @param word of type string
	 * @return true if word is already stored in the Trie
	 */
	public boolean contains(String word){ 
		word=word.toLowerCase();
		char letter = word.charAt(0);
		int index = letter - 'a';
		if (root.children[index]==null){
			return false;
		}
		return root.nodeContains(root, word);
	}
	/** 
	 * returns true if pre is the prefix of a word in the tree   
	 * @param pre is a string which represents a prefix
	 * @return true if trie contains that string 
	 * or false if does not
	 */
	public boolean containsPrefix(String pre){ 
		pre = pre.toLowerCase();
		return this.root.nodeContainsPrefix(this.root,pre);
	}
	
	/**
	 * @return the number of words stored
	 */
	public int size(){                          		
		return size;
	}
	
	/**
	 * clears the Trie by setting all children to null,
	 * and setting size to 0
	 */
	public void clear(){                        
		for (int i = 0; i<26; i++){
			this.root.children[i] = null;
		}
		size=0;
	}
	
}
