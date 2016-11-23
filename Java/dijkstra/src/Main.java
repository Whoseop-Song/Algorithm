import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        MinHeap heap = new MinHeap();
        PriorityQueue Q = new PriorityQueue();
        ArrayList<node> S = new ArrayList<>();
        int[][] w = {{0,10,3,Integer.MAX_VALUE,Integer.MAX_VALUE},
                    {Integer.MAX_VALUE,0,1,2,Integer.MAX_VALUE},
                    {Integer.MAX_VALUE,4,0,8,2},
                    {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,0,7},
                    {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,9,0}};
        int start =0;
        char point = 'A';
        int[] d = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        for(int i=0;i<5 ; i++){
            if(start==i)
                d[i]=0;
            Q.insert(heap,new node(d[i],i));
        }
        while(Q.size(heap)!=0){
            node temp = Q.Min(heap);
            Q.delete(heap,0);
            S.add(temp);
            System.out.println("--------------------------------------------------------");
            System.out.printf("S[%d] : d[%c] = %d\n",S.get(S.size()-1).getIndex(),point+S.get(S.size()-1).getIndex(),d[S.get(S.size()-1).getIndex()]);
            System.out.println("--------------------------------------------------------");
            for(int i=0;i<5;i++){
                if(i==temp.getIndex())
                    continue;
                if(d[temp.getIndex()]+w[temp.getIndex()][i]<d[i] && w[temp.getIndex()][i]!=Integer.MAX_VALUE) {
                    d[i] = d[temp.getIndex()] + w[temp.getIndex()][i];
                }
            }
            for(int i =0;i<Q.size(heap);i++) {
                System.out.printf("Q[%d] : d[%c] = %d",i,point+Q.get(heap,i).getIndex(),Q.get(heap,i).getDistance());
                if(Q.get(heap,i).getDistance() != d[Q.get(heap,i).getIndex()]) {
                    Q.setPriority(heap, i, d[Q.get(heap, i).getIndex()]);
                    System.out.printf(" -> d[%c] = %d",point+Q.get(heap,i).getIndex(),Q.get(heap,i).getDistance());
                }
                System.out.println();
                Q.buildMinHeap(heap);
            }
            System.out.println();
            for(int i=0; i<d.length;i++)
                System.out.println(d[i]);
        }
    }
}