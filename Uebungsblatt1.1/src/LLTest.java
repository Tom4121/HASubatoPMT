import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.junit.jupiter.api.Assertions.*;

public class LLTest {
    LL<Integer> list;
    LL<Integer> list2;
    @Before
    public void init() {
        list = LL.of(1,2,3,4,5,6,7,8,9);
        list2 = LL.of(10,11,12,13,14,15);
    }
    @Test
    public void testForEach() {
        var x = new int[1];
        list.forEach(y -> x[0] += y);
        assertEquals(45, x[0]);
    }

    @Test
    public void testContainsWith() {
        assertTrue(list.containsWith(x->x>7 && x<10));
        assertFalse(list.containsWith(x -> x==6 && x==7));
    }
    @Test
    public void testContains() {
        assertTrue(list.contains(7));
        assertFalse(list.contains(10));
    }
    @Test
    public void testDropWhile() {
        assertEquals(LL.of(6,7,8,9),list.dropWhile(x->x<=5));
    }
    @Test
    public void testTakeWhile() {
        assertEquals(LL.of(1,2,3,4,5), list.takeWhile(x->x<=5));
    }
    @Test
    public void testFilter() {
        assertEquals(LL.of(2,4,6,8), list.filter(x->x%2==0));
    }

    @Test
    public void testMap() {
        assertEquals(LL.of(1,4,9,16,25,36,49,64,81), list.map(x->x*x));
    }
}
