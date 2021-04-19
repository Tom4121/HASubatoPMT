import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.junit.jupiter.api.Assertions.*;

    public class ALTest {
        AL<Integer> list;
        AL<Integer> list2;
        AL<Integer> list3;
        AL<Integer> list4;
        @Before
        public void init() {
            list = AL.of(1,2,3,4,5,6,7,8,9);
            list2 = AL.of(10,11);
            list3 = AL.of();
            list4 = AL.of(1,2,3,4,1,5,6,7,8,1);
        }
        @Test
        public void testLength() {
            assertEquals(list.length(), 9);
        }

        @Test
        public void testLast() {
            assertEquals(list.last(), 9);

        }

        @Test
        public void testAppend() {
            assertEquals(list.append(list2), AL.of(1,2,3,4,5,6,7,8,9,10,11));
        }

        @Test
        public void testDrop() {
            assertEquals(list.drop(6), AL.of(7,8,9));
            assertEquals(list.drop(10), AL.nil());
            assertEquals(list.drop(-1), list);
        }

        @Test
        public void testTake() {
            assertEquals(list.take(4),AL.of(1,2,3,4));
            assertEquals(list.take(10), list);
            assertEquals(list.take(-1), AL.nil());
        }

        @Test
        public void testSublist() {
            assertEquals(list.sublist(3,4), AL.of(4,5,6,7));
            assertEquals(list.sublist(3, 10000), AL.of(4,5,6,7,8,9));
            assertEquals(list.sublist(10, 10000), AL.nil());
        }

        @Test
        public void testReverse() {
            assertEquals(list.reverse(), AL.of(9,8,7,6,5,4,3,2,1));
        }

        @Test
        public void testInterspere() {
            assertEquals(AL.of(1,0,2,0,3,0,4,0,5,0,6,0,7,0,8,0,9), list.intersperse(0));
        }

        @Test
        public void testIsPrefix() {
            assertTrue(AL.of(1,2,3,4).isPrefixOf(list));
            assertFalse(AL.of(2,3,4).isPrefixOf(list));
        }

        @Test
        public void testIsSuffix() {
            assertTrue(AL.of(6,7,8,9).isSuffixOf(list));
            assertFalse(AL.of(6,7,8).isSuffixOf(list));
        }

        @Test
        public void testIsInfix() {
            assertTrue(AL.of(4,5,6,7).isInfixOf(list));
            //assertTrue(AL.of(null).isInfixOf(list));
        }

        @Test
        public void testGet() {
            assertEquals(6, list.get(5));
        }

        @Test
        public void testRotate() {
            assertEquals(AL.of(2,3,4,5,6,7,8,9,1), list.rotate());
        }

        @Test
        public void testTails() {
            assertEquals(AL.of(AL.of(1,2,3,4), AL.of(2,3,4), AL.of(3,4), AL.of(4), AL.nil()), AL.of(1,2,3,4).tails());
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
            assertEquals(AL.of(6,7,8,9),list.dropWhile(x->x<=5));
        }

        @Test
        public void testTakeWhile() {
            assertEquals(AL.of(1,2,3,4,5), list.takeWhile(x->x<=5));
            //assertEquals(AL.of(1,2,3,4,1), list4.takeWhile(x->x<=5));
        }

        @Test
        public void testFilter() {
            assertEquals(AL.of(2,4,6,8), list.filter(x->x%2==0));
        }

        @Test
        public void testMap() {
            assertEquals(AL.of(1,4,9,16,25,36,49,64,81), list.map(x->x*x));
        }

        @Test
        public void testZip() {
            assertEquals(AL.of(new AL.Pair(1,"A"), new AL.Pair<>(2,"B"), new AL.Pair<>(3,"C"), new AL.Pair<>(4,"D")), AL.of(1,2,3,4).zip(AL.of("A","B","C","D")));
        }

        @Test
        public void testSpan() {
            assertEquals(new AL.Pair<>(AL.of(1,2,3),AL.of(4,5,6,1,2,3)), AL.of(1,2,3,4,5,6,1,2,3).span(x->x<4));
        }

        @Test
        public void testPartition() {
            assertEquals(new AL.Pair<>(AL.of(1,2,3,4,1,2,3), AL.of(5,6)), AL.of(1,2,3,4,5,6,1,2,3).partition(x->x<5));
        }

        @Test
        public void testIsSorted() {
            assertFalse(AL.of(1, 2, 3, 4, 5, 66, 7, 8).isSorted((x, y) -> x - y));
            assertTrue(AL.of(1, 2, 3, 4, 5, 6, 7, 8).isSorted((x, y) -> x - y));
        }

        @Test
        public void testQsort() {
            assertEquals(AL.of(0, 1, 2, 3, 4, 4, 5, 22, 33, 423, 435, 453, 2345),AL.of(1,2,4,5,435,4,2345,33,3,453,423,22,0).qsort((x,y)->x-y));
        }
}
