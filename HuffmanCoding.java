import java.io.*;
import java.util.Scanner;


public class HuffmanCoding {
	
	class treeNode{
		String chStr;
		int frequency;
		String code;
		treeNode next;
		treeNode left;
		treeNode right;
		
		public treeNode (String chStr, int frequency, String code, treeNode next, treeNode left, treeNode right) {
			this.chStr = chStr;
			this.frequency = frequency;
			this.code = code;
			this.next = next;
			this.left = left;
			this.right = right;
		}
			
		void printNode (treeNode T, FileWriter DebugFile) {
			try {
					String str = "(" + T.chStr+ "," + T.frequency+ ",";
					if (T.code == "") {str += "NoCode,";} else {str += T.code+",";}
					if(T.next==null) {str += "null,";}else { str += T.next.chStr+",";}
					if(T.left==null) {str += "null,";}else { str += T.left.chStr+",";}
					if(T.right==null) {str += "null)\n";}else {str += T.right.chStr+")\n";}
					DebugFile.write(str);
			} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	class LinkedList {
		treeNode listHead;
		
		public LinkedList () {
			listHead = new treeNode ("dummy", 0, "", null, null, null);
		}
		
		void insertNewNode (treeNode newNode) {
			treeNode spot = listHead;
			while ((spot.next!=null) && (spot.next.frequency < newNode.frequency)) {
				spot = spot.next;
			}
			newNode.next = spot.next;
			spot.next = newNode;
		}
		
		void printList (FileWriter DebugFile) {
			treeNode spot = listHead;
		    try{
		    	DebugFile.write("\n");
			     while (spot!=null) {
					spot.printNode (spot, DebugFile);
					spot = spot.next;
			     }
		    }catch (IOException e) {e.printStackTrace();}
		}
			
	}
	
	class BinaryTree {
		treeNode root;
		
		public BinaryTree(treeNode node) {
			root = node;
		}
		
		boolean isLeaf(treeNode node) {
			return (node.left == null && node.right == null);
		}
		
		void preOrderTraversal (treeNode T, FileWriter outFile) {
			if (isLeaf (T)) {
				T.printNode (T, outFile);
			}else {
				T.printNode (T, outFile);
				preOrderTraversal (T.left, outFile);
				preOrderTraversal (T.right, outFile);
			}
		}
		
		void inOrderTraversal (treeNode T, FileWriter outFile) {
			if (isLeaf (T)) {
				T.printNode (T, outFile);
			}else {
				inOrderTraversal (T.left, outFile);
				T.printNode (T, outFile);
				inOrderTraversal (T.right, outFile);
			}
		}
		
		void postOrderTraversal (treeNode T, FileWriter outFile) {
			if (isLeaf (T)) {
				T.printNode (T, outFile);
			}else {
				postOrderTraversal (T.left, outFile);
				postOrderTraversal (T.right, outFile);
				T.printNode (T, outFile);
			}
		}
		
		
	}

	int charCountAry [];
	String charCode[];
	BinaryTree huffTree;
	LinkedList huffLList;
	
	public HuffmanCoding () {
		charCountAry = new int [256];
		charCode = new String [256];
	}
	
	void computeCharCounts (Scanner inFile, FileWriter DebugFile) {
		String line;
		int index;
		while (inFile.hasNext()) {
			line = inFile.nextLine();
			char [] array = line.toCharArray();
			for (char c : array) {
				index = (int) c;
				charCountAry[index]++;
				
			}
			//charCountAry[10]++;
		}
	}
	
	void printCountAry (FileWriter DebugFile) {
		try {	
			DebugFile.write("***Printing out the Character-Count Array\n");
			for (int i=0; i<256; i++) {
				if (charCountAry[i]>0){
					DebugFile.write("char"+i+" "+charCountAry[i]+"\n");
				}
			}
		}catch (IOException e) {e.printStackTrace();}
	}
	
	void constructHuffmanLList (FileWriter DebugFile) {
		
		try {
			DebugFile.write("\n\n***Constructing a Huffman Linked List:\n");
		} catch (IOException e) {e.printStackTrace();}
		
		String chr;
		int freq;
		huffLList = new LinkedList ();
		for (int index=0; index<256; index++){
			if (charCountAry[index]>0){
				chr = Character.toString((char)index);
				freq = charCountAry[index];
				treeNode newNode = new treeNode (chr, freq,"",null,null,null);
				huffLList.insertNewNode (newNode);
				huffLList.printList(DebugFile);
			}
		}
	}
	
	void constructHuffmanBinTree (FileWriter DebugFile) {
		treeNode newNode = null;
		String concatenated;
		int addedfreq;
		
	 try {
			DebugFile.write("\n***Constructing a Huffman Binary Tree:\n");
			huffLList.printList(DebugFile);
			while (huffLList.listHead.next.next!=null) {
				huffLList.printList(DebugFile);
				concatenated = huffLList.listHead.next.chStr + huffLList.listHead.next.next.chStr;
				addedfreq = huffLList.listHead.next.frequency + huffLList.listHead.next.next.frequency;
				newNode = new treeNode (concatenated, addedfreq, "", null, huffLList.listHead.next, huffLList.listHead.next.next);
				huffLList.insertNewNode (newNode);
				huffLList.listHead.next = huffLList.listHead.next.next.next;
				huffLList.printList(DebugFile);
			}
			huffTree = new BinaryTree (huffLList.listHead.next);		
	  }catch (IOException e) {e.printStackTrace();}
	
	}
	
	void constructCharCode (treeNode T, String code) {
		int index=0;
		if(huffTree.isLeaf (T)) {
			T.code = code;
			index = (int)T.chStr.charAt(0);
			charCode[index] = code;
		}else {
			constructCharCode (T.left, code+"0");
			constructCharCode (T.right, code+"1");
		}
	}

	void encode (Scanner inFile, FileWriter outFile) {
		int index;
		String code;
		String line;
		
		try {
			while (inFile.hasNext()) {
				line = inFile.nextLine();
				char[] array = line.toCharArray();
				for (char c : array) {
					index = (int)c;
					code=charCode[index];
					outFile.write(code);
				}				
			}
		} catch (IOException e) {e.printStackTrace();}
	}
	
	void decode (Scanner inFile, FileWriter outFile) {
		treeNode spot = huffTree.root;
		String line;
		int count =0;
		char oneBit;
		
		if (spot == null) {
			System.out.println("Error! The tree is empty!");
			System.exit(0);
		}
		
		try {
			line = inFile.nextLine();
			char [] array = line.toCharArray();
			while (count < array.length) {
				if(huffTree.isLeaf(spot)) {
					outFile.write(spot.chStr);
					spot = huffTree.root;
				}else{
					oneBit = (char)array[count];
					if(oneBit=='0') {spot=spot.left; count++;}
					else if(oneBit=='1') {spot=spot.right; count++;}
					else {
						System.out.println("Error! The compress file contains invalid character!");
						System.exit(0);}
				}
			}
			
			
			if(!huffTree.isLeaf(spot)) {
				System.out.println("Error! The compress file is corrupted!");
				System.exit(0);
			}else {
				outFile.write(spot.chStr);
			}
		 }catch (IOException e) {e.printStackTrace();}		
	}
		
	void userInterface() {
		Scanner keyboard = new Scanner (System.in);
		String nameOrg;
		String nameCompress;
		String nameDeCompress;
		String DebugFileUser;
		String input;
		char yesNo= 'Y';
		
		try {			
			 do {
				System.out.print("Would you like to encode a file? (Y or N) ");
				input= keyboard.nextLine();
				yesNo = Character.toUpperCase(input.charAt(0));
				if (yesNo=='N') {
					System.exit(0);
				}else {
					System.out.print("What is the name of the file? ");
					input= keyboard.nextLine();
					nameOrg = input.replaceAll(".txt", "");
					nameCompress = nameOrg + "_Compressed.txt";
					nameDeCompress = nameOrg + "_deCompressed.txt";
					DebugFileUser = nameOrg + "_DeBug.txt";
					nameOrg = nameOrg + ".txt";
					
					//Constructing the character-count-array and character-code array.
					HuffmanCoding user = new HuffmanCoding ();
					Scanner OrgFile= new Scanner(new FileReader (nameOrg));
					FileWriter DeBugFile = new FileWriter (DebugFileUser);
					user.computeCharCounts(OrgFile, DeBugFile);
					user.printCountAry(DeBugFile);
					user.constructHuffmanLList(DeBugFile);
					user.constructHuffmanBinTree(DeBugFile);
					user.constructCharCode(user.huffTree.root, "");
					user.huffLList.printList(DeBugFile);
					OrgFile.close();
					
					DeBugFile.write("***Printing out the Character-Code Array\n");
					for (int i=0; i<256; i++) {
						if (charCountAry[i]>0){
							DeBugFile.write("char"+i+" "+charCode[i]+"\n");
						}
					}
					
					//Encoding & Decoding
					Scanner OrgFile2 = new Scanner(new FileReader(nameOrg));
					FileWriter compFile = new FileWriter(nameCompress);
					FileWriter deCompFile = new FileWriter(nameDeCompress);
					user.encode(OrgFile2, compFile);
					compFile.close();
					Scanner compFile2 = new Scanner (new FileReader (nameCompress));
					user.decode(compFile2, deCompFile);
					
					compFile2.close();
					deCompFile.close();
					OrgFile2.close();
					DeBugFile.close();
					
				}
			}while(Character.toUpperCase(yesNo) =='Y');
			 
			 keyboard.close();
			
		 } catch (IOException e) {e.printStackTrace();}
		
	}
}
