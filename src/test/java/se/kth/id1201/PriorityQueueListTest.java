package se.kth.id1201;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class PriorityQueueListTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void add_1SuccessTest()
    {
        PriorityQueueList q = new PriorityQueueList();
        q.add_1(1);
        q.add_1(3);
        q.add_1(5);
        assertTrue(q.first.item == 5);
    }

    @Test
    public void remove_nSuccessTest()
    {
        PriorityQueueList q = new PriorityQueueList();
        q.add_1(1);
        q.add_1(3);
        q.add_1(5);
        Integer minValue = q.remove_n();
        assertEquals(2, q.length);
        assertEquals(1, minValue.intValue());
    }

    @Test
    public void add_nSuccessTest()
    {
        PriorityQueueList q = new PriorityQueueList();
        q.add_n(3);
        q.add_n(1);
        q.add_n(5);
        assertTrue(q.length == 3);
        assertTrue(q.first.item == 1);
    }

    @Test
    public void remove_1SuccessTest()
    {
        PriorityQueueList q = new PriorityQueueList();
        q.add_n(3);
        q.add_n(1);
        q.add_n(5);
        assertEquals(1, q.remove_1().intValue());
        assertEquals(3, q.remove_1().intValue());
        assertEquals(5, q.remove_1().intValue());
    }
}
