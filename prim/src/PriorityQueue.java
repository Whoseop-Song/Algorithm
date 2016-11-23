/**
 * Created by song on 2016. 11. 3..
 */
public class PriorityQueue {
    public void insert(MinHeap heap, node input){
        heap.add(input);
    }
    public node Min(MinHeap heap){
        return heap.Min();
    }
    public void buildMinHeap(MinHeap heap){ heap.buildMinheap();}
    public void setPriority(MinHeap heap, int index, int priority){
        heap.setpriority(index, priority);
    }
    public void delete(MinHeap heap,int index){
        heap.delete(index);
    }
    public void print(MinHeap heap){
        heap.print();
    }
    public int size(MinHeap heap){
        return heap.size();
    }
    public node get(MinHeap heap, int index){
        return heap.get(index);
    }
    public boolean haveIndex(MinHeap heap, int index){
        for(int i=0;i<heap.size();i++){
            if(heap.get(i).getIndex() == index)
                return true;
        }
        return false;
    }
}
