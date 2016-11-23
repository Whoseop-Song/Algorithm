/**
 * Created by song on 2016. 11. 4..
 */
import java.util.ArrayList;

/**
 * Created by song on 2016. 11. 3..
 */
public class MinHeap {
    private ArrayList<huffmantree> heap;
    public MinHeap(){
        heap = new ArrayList<>();
    }
    public void add(huffmantree input){
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

        if(left<heap.size() && heap.get(left).getFrequency()<heap.get(smallest).getFrequency())
            smallest = left;
        if(right<heap.size() && heap.get(right).getFrequency()<heap.get(smallest).getFrequency())
            smallest = right;
        if(smallest != i) {
            huffmantree temp = heap.get(i);
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
    public huffmantree Min(){
        return heap.get(0);
    }
    public int size(){
        return heap.size();
    }
    public huffmantree get(int index) {
        return heap.get(index);
    }
}
