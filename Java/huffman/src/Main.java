import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by song on 2016. 11. 11..
 */
public class Main {
    public static void main(String[] args) throws IOException {
        incoding("data10.txt");
        decoding("data10_encoded.txt","data10_table.txt");
    }

    private static void incoding(String data) throws IOException {
        PriorityQueue queue = new PriorityQueue();
        MinHeap heap = new MinHeap();
        String input;
        BufferedReader reader = new BufferedReader(new FileReader(data));
        input = reader.readLine();
        String output= "";
        reader.close();
        //System.out.println(input);
        int alphaCnt[] = new int[27];
        String incodedAlpha[] = new String[27];
        //count frequency a-z is 0-25 and ' ' is 26
        for(int i=0;i<input.length();i++){
            //space
            if(input.charAt(i) == 32)
                alphaCnt[26]++;
            //alpha
            else
                alphaCnt[input.charAt(i)-'a']++;
        }

        for(int i=0;i<alphaCnt.length;i++){
            if(alphaCnt[i]==0)
                continue;
            if(i==26)
                queue.insert(heap, new huffmantree(' ',alphaCnt[i]));
            else
                queue.insert(heap,new huffmantree((char)('a'+i),alphaCnt[i]));
        }
        int n= queue.size(heap);
        //extract two nodes in priority queue and make node that it can be parent for those two nodes
        for(int i=1;i< n;i++){
            huffmantree temp = new huffmantree();
            temp.setLeft(queue.extractMin(heap));
            temp.setRight(queue.extractMin(heap));
            temp.setFrequency(temp.left.getFrequency()+temp.right.getFrequency());
            queue.insert(heap,temp);
        }
        //queue.print(heap);

        //setup array that can store incoded character
        for(int i=0;i<26;i++){
            incodedAlpha[i] = getIncodeString(queue.get(heap,0),(char)('a'+i));
            //System.out.println("'"+(char)('a'+i)+"'   "+incodedAlpha[i]);
        }
        incodedAlpha[26] = getIncodeString(queue.get(heap,0),' ');
        //System.out.println("' '   "+incodedAlpha[26]);

        FileWriter file = new FileWriter("hw08_01_201102460_table");
        for(int i=0;i<incodedAlpha.length;i++){
            if(!incodedAlpha[i].equals(".")) {
                if(i==26)
                    file.write(" ," + incodedAlpha[i] + "\n");
                else
                    file.write((char) ('a' + i) + "," + incodedAlpha[i] + "\n");
            }
        }
        file.close();
        //incoding
        for(int i=0;i<input.length();i++){
            if(input.charAt(i) == 32)
                output += incodedAlpha[26];
            else
                output += incodedAlpha[(input.charAt(i)-'a')];
        }
        //System.out.println(output);
        FileWrite("hw08_01_201102460_encoded.txt",output);
    }
    //pre-order for find incoded char
    //recursive
    //left -> add 0 right -> add 1 correct return "" incorect return "."
    private static String getIncodeString(huffmantree huffmantree, char c) {
        if(c==huffmantree.getCharacter())
            return "";
        //threre's no child
        if(huffmantree.getLeft()==null && huffmantree.getRight()==null)
            return ".";
        //visit left first
        String temp  = getIncodeString(huffmantree.getLeft(),c);
        if(temp.equals(""))
            return "0"+temp;
        else if(temp.charAt(temp.length()-1)!='.')
            return "0"+temp;
        //if there's no character in left child visit right
        temp  = getIncodeString(huffmantree.getRight(),c);
        if(temp.equals(""))
            return "1"+temp;
        if(temp.charAt(temp.length()-1)!='.')
            return "1"+temp;
        return ".";
    }

    //decode encoded file using huffmantree which is made by table
    private static void decoding(String fileName,String decodeTree) throws IOException {
        String temp;
        BufferedReader reader = new BufferedReader(new FileReader(decodeTree));
        temp = reader.readLine();
        huffmantree head = new huffmantree();
        makeHuffmanTree(reader, temp, head);
        System.out.println(head);
        reader.close();
        reader = new BufferedReader(new FileReader(fileName));
        temp = reader.readLine();
        reader.close();
        String result = "";
        result = decode(temp, head, result);
        //System.out.println(result);
        FileWrite("hw08_01_201102460_decoded.txt",result);
    }

    //decode using huffmantree
    //when pointer meet pointer.character except '\0' add character and go beck to tree's root
    private static String decode(String temp, huffmantree head, String result) {
        huffmantree pointer = head;
        for(int i=0;i<temp.length();i++){
            char next = temp.charAt(i);
            if(next=='0') {
                pointer = pointer.getLeft();
            }
            else if(next=='1') {
                pointer = pointer.getRight();
            }
            if(pointer.getCharacter() != '\0'){
                result += pointer.getCharacter();
                pointer = head;
            }

        }
        return result;
    }

    //read table and make huffmantree
    private static void makeHuffmanTree(BufferedReader reader, String temp, huffmantree head) throws IOException {
        while(temp !=null) {
            huffmantree pointer = head;
            //System.out.println(temp);
            StringTokenizer stk = new StringTokenizer(temp,",");
            String str = stk.nextToken();
            String root = stk.nextToken();
            for(int i=0;i<root.length();i++){
                char next = root.charAt(i);
                if(next=='0') {
                    if(pointer.getLeft() == null)
                        pointer.setLeft(new huffmantree());
                    pointer = pointer.getLeft();
                }
                else if(next=='1') {
                    if (pointer.getRight() == null)
                        pointer.setRight(new huffmantree());
                    pointer = pointer.getRight();
                }
            }
            pointer.setCharacter(str.charAt(0));
            temp = reader.readLine();
        }
    }

    private static void FileWrite(String filename, String output) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write(output);
        writer.close();
    }

}
