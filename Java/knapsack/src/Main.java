import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException{
        ArrayList<Item> items= new ArrayList<>();
        fileRead(items,"data11.txt");
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        scan.close();

        //make matrix's row number not items.size()+1 but items.size() because item start from 1
        int[][] matrix = new int[items.size()][input+1];
        calculateAllOPT(items, input, matrix);
        printMatrix(items.size(), input, matrix);
        printUsedItems(items, input, matrix);

    }

    private static void printUsedItems(ArrayList<Item> items, int input, int[][] matrix) {
        //calculate items
        ArrayList<Integer> selectedItem = new ArrayList<>();
        //set weight
        int weight = input;
        for (int n = items.size()-1; n > 0 ; n--){
            //check weight can be include item's weight
            if(weight >= items.get(n).getWeight()){
                // check metrix value that point (item n,weight) are same as item n's value + matrix value that point (item n-1,weight - item n's weight)
                if(matrix[n][weight] == (items.get(n).getValue() + matrix[n-1][weight - items.get(n).getWeight()])){
                    //add item and subtract weight
                    selectedItem.add(items.get(n).getItemNo());
                    weight = weight- items.get(n).getWeight();
                }
            }
        }
        //sort order ascending order
        selectedItem.sort(new ItemCompare());
        System.out.print("Items :\t");
        for(int i=0;i<selectedItem.size()-1;i++)
            System.out.print(selectedItem.get(i)+", ");
        System.out.println(selectedItem.get(selectedItem.size()-1));
    }

    private static void printMatrix(int itemCnt, int input, int[][] matrix) {
        //print matrix
        for(int i=0;i<itemCnt;i++){
            for(int j=0;j<input+1;j++){
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.println();
        }
        //print max value of Matrix
        System.out.println("Max   :\t" + matrix[itemCnt-1][input]);
    }

    private static void calculateAllOPT(ArrayList<Item> items, int input, int[][] matrix) {
        for(int i=0;i<items.size();i++){
            for(int j=0;j<input+1;j++){
                matrix[i][j] = OPT(i,j,items);
            }
        }
    }

    private static void fileRead(ArrayList<Item> items,String fileName) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        //make 0 item to use normal index
        items.add(new Item(0,0,0));
        while((line=reader.readLine())!=null){
            String[] split = line.split(",");
            items.add(new Item(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])));
        }
        reader.close();
    }

    public static int OPT(int itemNo,int weight,ArrayList<Item> items){
        //item 0 is 0
        if(itemNo == 0)
            return 0;
        //if item weight is bigger than item's weight
        else if(items.get(itemNo).getWeight()>weight)
            //return before item's opt
            return OPT(itemNo-1,weight,items);
        else
            //return max value of opt(before item's,weight) and this item's value + opt(before item's,weight - this item's weight)
            return Math.max(OPT(itemNo - 1, weight, items), OPT(itemNo - 1, weight - items.get(itemNo).getWeight(), items) + items.get(itemNo).getValue());
    }

    //ascending order comparator
    static class ItemCompare implements Comparator<Integer>{
        public int compare(Integer o1, Integer o2) {
            return o1 > o2 ? 1 : ( o1 < o2 ? -1 : 0 );
        }
    }
}