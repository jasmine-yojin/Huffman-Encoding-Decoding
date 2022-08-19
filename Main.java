import java.io.*;
import java.util.Scanner;


public class Main {
	public static void main (String [] args) {
	  try {
			
		  	String nameOrg;
		  	String nameDebugFile;
		  
			Scanner original = new Scanner(new FileReader(args[0]));
			nameOrg = args[0].replaceAll(".txt", "");
			nameDebugFile = nameOrg + "_DeBug.txt";
			FileWriter DebugFile = new FileWriter(nameDebugFile);
			
			//Constructing the character-count-array and character-code array.
			HuffmanCoding testo = new HuffmanCoding ();
			testo.computeCharCounts(original, DebugFile);
			testo.printCountAry(DebugFile);
			testo.constructHuffmanLList(DebugFile);
			testo.constructHuffmanBinTree(DebugFile);
			testo.constructCharCode (testo.huffTree.root, "");
			testo.huffLList.printList(DebugFile);
			original.close();
			
			//Encoding & Decoding
			String nameCompress;
		  	String nameDeCompress;
			
		  	Scanner original2= new Scanner(new FileReader(args[0]));
			nameOrg = args[0].replaceAll(".txt", "");
			nameCompress = nameOrg + "_Compressed.txt";
			nameDeCompress = nameOrg + "_deCompressed.txt";
			FileWriter compFile = new FileWriter(nameCompress);
			FileWriter deCompFile = new FileWriter(nameDeCompress);
			
			testo.encode(original2, compFile);
			compFile.close();
			Scanner compFile2 = new Scanner (new FileReader (nameCompress));
			testo.decode(compFile2, deCompFile);
			compFile2.close();
			deCompFile.close();
			original2.close();
			
			//Traversals
			DebugFile.write("\n\n***PreOrder Traversal\n");
			testo.huffTree.preOrderTraversal (testo.huffTree.root, DebugFile);
			
			DebugFile.write("\n\n***InOrder Traversal Traversal\n");
			testo.huffTree.inOrderTraversal(testo.huffTree.root, DebugFile); 
			
			DebugFile.write("\n\n***PostOrder Traversal\n");
			testo.huffTree.postOrderTraversal(testo.huffTree.root, DebugFile);
			
			DebugFile.close();
			
			testo.userInterface();
		} catch (FileNotFoundException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();}
		
	}
}