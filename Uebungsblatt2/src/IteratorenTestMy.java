import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorenTestMy {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    @Test
    public void testIntRange_stepPositive() {
        new Iteratoren.IntRange(1,100,10).forEach(x-> System.out.print(x+" "));
        assertEquals("1 11 21 31 41 51 61 71 81 91 ", outContent.toString());

    }

    @Test
    public void testIntRange2_stepNegative() {
        new Iteratoren.IntRange(100,1,-9).forEach(x-> System.out.print(x+" "));
        assertEquals("100 91 82 73 64 55 46 37 28 19 10 1 ", outContent.toString());
    }

    @Test
    public void testFib(){
        var fib = new Iteratoren.Fib().iterator();
        new Iteratoren.IntRange(1,20).forEach(x -> System.out.print(fib.next()+" "));
        assertEquals("0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 ", outContent.toString());
    }

    @Test
    public void testArrayIterable(){
        new Iteratoren.ArrayIterable<>(new String[]{"Hello","World"}).forEach(w->System.out.print(w.toUpperCase()+" "));
        assertEquals("HELLO WORLD ", outContent.toString());
    }

    @Test
    public void testIterableString(){
        new Iteratoren.IterableString("Hello").forEach(c->System.out.print(c+" "));
        assertEquals("H e l l o ", outContent.toString());
    }

    @Test
    public void testLines(){
        new Iteratoren.Lines("\nHello\nWorld").forEach(l->System.out.println(l));
        assertEquals("\nHello\nWorld", outContent.toString());
    }

    @Test
    public void testWords(){
        new Iteratoren.Words("words don't   come easy\nto me    ").forEach(w->System.out.print(w+" "));
        assertEquals("words don't come easy to me ", outContent.toString());
    }

    @Test
    public void testIndexIterable(){
        var sqs = new Iteratoren.IndexIterable<>(x->x*x).iterator();
        new Iteratoren.IntRange(1,20).forEach(i->System.out.print(sqs.next()+" "));
        assertEquals("1 4 9 16 25 36 49 64 81 100 121 144 169 196 225 256 289 324 361 400 ", outContent.toString());
    }

    @Test
    public void testGenerationIterable(){
        var xs = new Iteratoren.GenerationIterable<>(5, x->-x).iterator();
        new Iteratoren.IntRange(1,20).forEach(i->System.out.print(xs.next()+" "));
        assertEquals("5 -5 5 -5 5 -5 5 -5 5 -5 5 -5 5 -5 5 -5 5 -5 5 -5 ", outContent.toString());
    }

    @Test
    public void testOddIterable(){
        var xs = new Iteratoren.OddIterable().iterator();
        new Iteratoren.IntRange(1,20).forEach(i->System.out.print(xs.next()+" "));
        assertEquals("1 3 5 7 9 11 13 15 17 19 21 23 25 27 29 31 33 35 37 39 ", outContent.toString());
    }

    @Test
    public void testLimit(){
        new Iteratoren.Limit<>(new Iteratoren.OddIterable(),10).forEach(x->System.out.print(x+" "));
        assertEquals("1 3 5 7 9 11 13 15 17 19 ", outContent.toString());
    }

    @Test
    public void testMaperable(){
        new Iteratoren.Maperable<>(new Iteratoren.Words("words don't come easy"), x->x.length()).forEach(x->System.out.print(x+" "));
        assertEquals("5 5 4 4 ", outContent.toString());
    }

    @Test
    public void testFilterable(){
        new Iteratoren.Filterable<>(new Iteratoren.IntRange(1,100), x->x%9==0).forEach(x-> System.out.print(x+" "));
        assertEquals("9 18 27 36 45 54 63 72 81 90 99 ", outContent.toString());
    }


}

