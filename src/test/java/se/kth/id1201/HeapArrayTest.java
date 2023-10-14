package se.kth.id1201;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HeapArrayTest {
    @Test
    public void enqueueDequeueSuccessTest(){

        HeapArray heap = new HeapArray(4);
        heap.enqueue(3);
        heap.enqueue(9);
        heap.enqueue(5);
        heap.enqueue(7);
        assertEquals(3, heap.dequeue().intValue());
        assertEquals(5, heap.dequeue().intValue());
        assertEquals(7, heap.dequeue().intValue());
        assertEquals(9, heap.dequeue().intValue());
        assertEquals(null, heap.dequeue());
    }
}
