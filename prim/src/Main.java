import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        MinHeap heap = new MinHeap();
        PriorityQueue Q = new PriorityQueue();
        //edge init
        int[][] w = {
                {0, 4, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 8, Integer.MAX_VALUE},
                {4, 0, 8, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 11, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 8, 0, 7, Integer.MAX_VALUE, 4, Integer.MAX_VALUE, Integer.MAX_VALUE, 2},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 7, 0, 9, 14, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 9, 0, 10, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 4, 14, 10, 0, 2, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2, 0, 1, 6},
                {8, 11, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, 0, 7},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 2, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 6, 7, 0}
        };
        //init parent
        char parent[] = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        //start vertex
        int start = 0;
        // sum of all edge in MST
        int sum=0;
        int[] key = new int[9];
        //init key array
        for(int i=0;i<key.length;i++){
            if(i==start)
                key[i] = 0;
            else
                key[i] = Integer.MAX_VALUE;
        }
        char st = 'a';
        //init queue
        for (int i = 0; i < key.length; i++) {
            Q.insert(heap, new node(i, key[i]));
        }

        while (Q.size(heap) != 0) {
            Q.print(heap);
            node temp = Q.Min(heap);
            Q.delete(heap, 0);
            System.out.printf("w(%c, %c) = %d\n",parent[temp.getIndex()],st+temp.getIndex(),key[temp.getIndex()]);
            sum+=key[temp.getIndex()];

            // i = vertex
            for (int i = 0; i < key.length; i++) {
                //except itself
                if (i == temp.getIndex())
                    continue;
                //if Q has i vertex and if edge between i and extracted node is smaller than key of i vertex refresh the key and parent
                if (Q.haveIndex(heap, i) && w[i][temp.getIndex()] < key[i]) {
                    key[i] = w[i][temp.getIndex()];
                    parent[i] = (char)(st+temp.getIndex());
                }
            }

            //refresh node's key in queue
            for(int i=0;i<Q.size(heap);i++){
                if(Q.get(heap,i).getDistance()!=key[Q.get(heap,i).getIndex()]){
                    Q.setPriority(heap,i,key[Q.get(heap,i).getIndex()]);
                }
            }
            Q.buildMinHeap(heap);
        }
        System.out.println("w(MST) = "+sum);
    }
}