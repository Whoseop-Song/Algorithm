/**
 * Created by song on 2016. 11. 11..
 */
public class PriorityQueue {
    public void insert(MinHeap heap, huffmantree input){
        heap.add(input);
    }
    public huffmantree Min(MinHeap heap){
        return heap.Min();
    }
    public void buildMinHeap(MinHeap heap){ heap.buildMinheap();}
    public void delete(MinHeap heap,int index){
        heap.delete(index);
    }
    public void print(MinHeap heap){
        heap.print();
    }
    public int size(MinHeap heap){
        return heap.size();
    }
    public huffmantree get(MinHeap heap, int index){
        return heap.get(index);
    }
    public huffmantree extractMin(MinHeap heap){
        huffmantree temp = heap.Min();
        heap.delete(0);
        return temp;
    }
}