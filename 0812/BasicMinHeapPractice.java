import java.util.*;

public class BasicMinHeapPractice {
    private final List<Integer> heap = new ArrayList<>();

    private int parent(int i){ return (i-1)/2; }
    private int left(int i){ return 2*i+1; }
    private int right(int i){ return 2*i+2; }

    private void heapifyUp(int i){
        while(i>0 && heap.get(i) < heap.get(parent(i))){
            Collections.swap(heap, i, parent(i));
            i = parent(i);
        }
    }

    private void heapifyDown(int i){
        while(true){
            int smallest = i, L = left(i), R = right(i);
            if(L<heap.size() && heap.get(L)<heap.get(smallest)) smallest=L;
            if(R<heap.size() && heap.get(R)<heap.get(smallest)) smallest=R;
            if(smallest==i) break;
            Collections.swap(heap, i, smallest);
            i = smallest;
        }
    }

    public void insert(int val){
        heap.add(val);
        heapifyUp(heap.size()-1);
    }

    public int extractMin(){
        if(heap.isEmpty()) throw new NoSuchElementException("empty");
        int ans = heap.get(0);
        int last = heap.remove(heap.size()-1);
        if(!heap.isEmpty()){
            heap.set(0, last);
            heapifyDown(0);
        }
        return ans;
    }

    public int getMin(){
        if(heap.isEmpty()) throw new NoSuchElementException("empty");
        return heap.get(0);
    }

    public int size(){ return heap.size(); }
    public boolean isEmpty(){ return heap.isEmpty(); }

    public static void main(String[] args){
        BasicMinHeapPractice h = new BasicMinHeapPractice();
        int[] in = {15,10,20,8,25,5};
        for(int x: in) h.insert(x);
        System.out.println("Extract seq:");
        while(!h.isEmpty()) System.out.print(h.extractMin() + (h.isEmpty()?"":" "));
        System.out.println();
    }
}
