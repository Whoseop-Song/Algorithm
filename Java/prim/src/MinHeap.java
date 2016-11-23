/**
 * Created by song on 2016. 11. 4..
 */
import java.util.ArrayList;

/**
 * Created by song on 2016. 11. 3..
 */
public class MinHeap {
    private ArrayList<node> heap;
    public MinHeap(){
        heap = new ArrayList<>();
    }
    public void add(node input){
        heap.add(input);
        buildMinheap();
    }

    public void buildMinheap() {
        for(int i=heap.size()/2;i>=0;i--){
            heapify(i);
        }
    }
    private void heapify(int i) {
        int left =2*i+1;
        int right =2*i+2;
        int smallest = i;

        if(left<heap.size() && heap.get(left).getDistance()<heap.get(smallest).getDistance())
            smallest = left;
        if(right<heap.size() && heap.get(right).getDistance()<heap.get(smallest).getDistance())
            smallest = right;
        if(smallest != i) {
            node temp = heap.get(i);
            heap.set(i, heap.get(smallest));
            heap.set(smallest, temp);
            heapify(smallest);
        }
    }
    public void delete(int index) {
        heap.set(index, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        buildMinheap();
    }
    public void print(){
        System.out.println("=======print start========");
        for(int i=0;i<heap.size();i++)
            System.out.println(heap.get(i));
        System.out.println("========print end=========");
    }
    public void setpriority(int index,int priority){
        node temp = heap.get(index);
        temp.setDistance(priority);
        heap.set(index,temp);
        //buildMinheap();
    }
    public node Min(){
        return heap.get(0);
    }
    public int size(){
        return heap.size();
    }
    public node get(int index) {
        return heap.get(index);
    }
}
