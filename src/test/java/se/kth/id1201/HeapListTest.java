package se.kth.id1201;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HeapListTest {
    @Test
    public void addSuccessTest(){
        HeapList.Node root = new HeapList.Node(1);
        root.left = new HeapList.Node(2);
        root.right = new HeapList.Node(5);
        root.add(4);
        assertEquals(1, root.value.intValue());
        assertEquals(2, root.left.value.intValue());
        assertEquals(4, root.right.value.intValue());
        assertEquals(5, root.right.left.value.intValue());
        
    }

    @Test
    public void addSuccess2Test(){
        HeapList.Node root = new HeapList.Node(2);
        root.left = new HeapList.Node(3);
        root.right = new HeapList.Node(5);
        root.add(1);
        assertEquals(1, root.value.intValue());
        assertEquals(3, root.left.value.intValue());
        assertEquals(2, root.right.value.intValue());
        assertEquals(5, root.right.left.value.intValue());
        
    }

     @Test
    public void promoteSuccess1Test(){
        HeapList.Node root = new HeapList.Node(2);
        root.left = new HeapList.Node(3);
        root.right = new HeapList.Node(5);
        root.left.left = new HeapList.Node(6);
        root.right.right = new HeapList.Node(8);
        root.promote();
        assertEquals(3, root.value.intValue());
        assertEquals(6, root.left.value.intValue());
        assertEquals(5, root.right.value.intValue());
        assertEquals(8, root.right.right.value.intValue());  
    }

      @Test
    public void promoteSuccess2Test(){
        HeapList.Node root = new HeapList.Node(2);
        root.left = new HeapList.Node(3);
        root.right = new HeapList.Node(5);
        root.left.left = new HeapList.Node(6);
        root.left.right = new HeapList.Node(8);
        root.right.right = new HeapList.Node(9);
        root.right.left = new HeapList.Node(7);
        root.right.promote();
        assertEquals(2, root.value.intValue());
        assertEquals(3, root.left.value.intValue());
        assertEquals(7, root.right.value.intValue());
        assertEquals(9, root.right.right.value.intValue());  
    }

     @Test
    public void enqueueDequeueSuccessTest(){

        HeapList heap = new HeapList();
        heap.enqueue(5);
        heap.enqueue(7);
        heap.enqueue(3);
        heap.enqueue(9);

        
        assertEquals(3, heap.dequeue().intValue());
        assertEquals(5, heap.dequeue().intValue());
        assertEquals(7, heap.dequeue().intValue());
        assertEquals(9, heap.dequeue().intValue()); 
    }

    @Test
    public void pushSuccessTest(){

        HeapBaobao heap = new HeapBaobao();
        heap.enqueue(5);
        heap.enqueue(7);
        heap.enqueue(3);
        heap.enqueue(9);
        heap.enqueue(10);
        heap.enqueue(11);
        heap.enqueue(12);

        
        assertEquals(2, heap.push(10));
    }
}
