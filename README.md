# Huffman-Encoding-Decoding
This program demonstrates Huffman coding of compressing a large text file size into a string made of 0's and 1's. Once compressed, it is them decompressed to the original text file. Binary tree structure is used to map the path of each character.

<h2>Algorithm Steps for Implementing Huffman Coding</h2>

<b>Step 0:</b> Read in the name of the text file using the args[0]. Convert this name into a string. Use the name
of the original file to name the debug, compressed, and decompressed files. Concatenate “.txt”
extension accordingly using the ‘+’ operator. Open these files.<br>
<b>Step 1:</b> computeCharCounts (inFile, charCountAry) This function will collect the statistics on the
frequency of the occurrence of each character. The character that occurs most often will be placed
higher in the binary tree for a better compression rate and entropy. Read in each line as a string text
then store them into a character array. The int charCountAry [256] index represents the ascii code of
each character. Therefore, the size is 256. It is an identity function.<br>
<b>Step 2:</b> printCountAry (charCountAry, DebugFile) This function will print out the contents of the
charCountAry. It shows how many times a character occurs in a text file. Use a for loop and print out the
contents only if it exists (count > 0).<br>
<b>Step 3:</b> constructHuffmanLList (charCountAry, DebugFile) Construct a linkedlist with each node
representing a character. It will be an ordered list (ascending order) based on the frequency of the
character. Code for insertion is like that of project 2 on hash function.<br>
<b>Step 4:</b> constructHuffmanBinTree (listHead, DebugFile) Take the first two nodes from the linkedlist and
create a new node by adding the combined frequency and concatenated characters. The new node will
now have pointers to left (first) and right (second) nodes. Insert this new node into the list based on the
order of frequency. The listhead (dummy) will now point to the third node. This will continue until the
linkedlist is condensed into a single node not including the dummy. Create an instance of a Huffman
Binary Tree and insert this remaining node as the tree’s root.<br>
<b>Step 5:</b> constructCharCode (Root, ‘’) This is a recursive function that maps the path of each character on
the Huffman Binary Tree. The path is represented in a string in series of 0’s and 1’s. The path to each
character is stored inside the charCode array.<br>
<b>Step 6:</b> printList (listHead, DebugFile) Print the LinkedList to check whether each character node was
inserted properly.<br>
<b>Step 7:</b> preOrderTraversal (Root, DebugFile) Recursive function. The ‘print’ happens before the two
recursive calls.<br>
inOrderTraversal (Root, DebugFile) Recursive function. The ‘print’ happens between the two recursive
calls.<br>
postOrderTraversal (Root, DebugFile) Recursive function. The ‘print’ happens after the two recursive
calls.<br>
<b>Step 8:</b> userInterface ( ) Use the console to take the user’s input. Ask the user to enter the name of the
text file to be encoded. Conduct the statistics on the frequency of each character count. Create a
linkedlist and a binary tree. With the binary tree and an array of character codes, convert the text into a
series of 0’s and 1’s. This is the compression/encoding process. Then decode the compressed file to see
whether compression worked correctly. Starting at the root of the binary tree, move left on 0 and right
on 1 from the encoded file. When the leaf node is reached, print out the character.<br>
<b>Step 9:</b> close all files. 
