package se.kth.id1201;

public class HeapArray implements PriorityQueue{
    private int pointer;
    private Integer[] heap;

    public HeapArray (int size){
        this.heap = new Integer[size];
        this.pointer = 0;
    }

    public void enqueue(Integer k){
        int index = pointer;
        heap[index] = k;
        pointer += 1;
        if(index==0){
            return;
        }
        while(index > 0){
            if(heap[index].intValue() < heap[(index+1)/2-1].intValue()){
                swap(index, (index+1)/2-1);
                index = (index+1)/2-1;
            }else{
                break;
            }
        }
        if(pointer == heap.length){
            expand();
        }
    }

    public Integer dequeue(){
        if(pointer == 0){
            return null;
        }
        Integer ret = heap[0];
        pointer -= 1;
        heap[0] = heap[pointer];
        heap[pointer] = null;
        sink();
        if(pointer < heap.length/4){
            shrink();
        }
        return ret;
    }

    private int minChildPos(int index){
        int pos = index;
        if(index*2+1 < heap.length && heap[index*2+1] != null){
            pos = heap[index*2+1].intValue() < heap[pos].intValue() ? index*2+1 : pos;
        }

        if(index*2+2 < heap.length && heap[index*2+2] != null){
            pos = heap[index*2+2].intValue() < heap[pos].intValue() ? index*2+2 : pos;
        }
        return pos;
    }

    public void sink(){
        int index = 0;
        int minPos;
        while(index < pointer-1){
            minPos = minChildPos(index);
            if(minPos == index){
                return;
            }
            swap(index, minPos);
            index = minPos;
        }      
    }


    public void clear(){
        for(int i = 0; i<heap.length; i++){
            heap[i] = null;
        }
    }

    public void expand(){
        Integer[] newHeap = new Integer[heap.length*2];
        for(int i = 0; i<heap.length; i++){
            newHeap[i]=heap[i];
        }
        heap = newHeap;
    }

    public void shrink(){
        Integer[] newHeap = new Integer[heap.length/2];
        for(int i = 0; i<newHeap.length; i++){
            newHeap[i]=heap[i];
        }
        heap = newHeap;
    }

    public void swap(int i, int j){
        Integer tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}
