import java.util.Iterator;
import java.math.BigInteger;
import java.util.function.Function;
import java.util.function.Predicate;


public interface Iteratoren {


    public static class IntRange implements Iterable<Integer> {
        int from;
        int to;
        int step;
        boolean infinite;

        public IntRange(int from, int to, int step) {/*ToDo*/}

        public IntRange(int from, int to) {/*ToDo*/}

        public IntRange(int from) {/*ToDo*/}

        public IntRange() {/*ToDo*/}
    }


    public static class Fib implements Iterable<BigInteger> {
        @Override
        public Iterator<BigInteger> iterator() {
            return null; /*ToDo*/
        }

        public static void main(String[] args) {
            new Fib().forEach(x -> System.out.println(x));
        }
    }


    public static class ArrayIterable<A> implements Iterable<A> {
        A[] as;

        public ArrayIterable(A[] as) {
            this.as = as;
        }
    }


    public static class IterableString implements Iterable<Character> {
        public IterableString(String str) {
        }

        public static void main(String[] args) {
            for (char c : new IterableString("Hello world!")) {
                System.out.println(c);
            }
        }
    }


    public static class Lines implements Iterable<String> {
        static String NEW_LINE = System.getProperty("line.separator");

        public Lines(String str) {
            /*ToDo*/
        }

        @Override
        public Iterator<String> iterator() {
            return     /*ToDo*/;
        }

        public static void main(String[] args) {
            for (String s : new Lines("hallo" + NEW_LINE + "welt!"))
                System.out.println(s);
        }
    }


    public static class Words implements Iterable<String> {
        public Words(String text) {
        }
    }


    public class IndexIterable<A> implements Iterable<A> {
        public IndexIterable(Function<Long, A> f) {
        }
    }


    public static class GenerationIterable<A> implements Iterable<A> {
        A a;
        Function<A, A> f;

        public GenerationIterable(A a, Function<A, A> f) {
            this.a = a;
            this.f = f;
        }
    }


    public static class OddIterable extends GenerationIterable<Long> {
        public OddIterable() {
            //hier der korrekte Aufruf....
        }
    }


    public static record Limit<A>(Iterable<A> itA, long n)
            implements Iterable<A> {
        public Iterator<A> iterator() {
            return new Iterator<>() {
                Iterator<A> it = itA.iterator();
                int i = 0;
                /* ToDo */
            };
        }
    }


    public static record Maperable<A, R>(Iterable<A> itA, Function<A, R> f)
            implements Iterable<R> {
        public Iterator<R> iterator() {
            return new Iterator<>() {
                Iterator<A> it = itA.iterator();
                /* ToDo */
            };
        }
    }


    public static record Filterable<A>(Iterable<A> itA, Predicate<A> p)
            implements Iterable<A> {
        public Iterator<A> iterator() {
            return new MyIterator();
        }

        private class MyIterator implements Iterator<A> {
            Iterator<A> it = itA.iterator();
            A theNext = null;

            MyIterator() {
                getTheNext();
            }

            void getTheNext() {
         /* ToDo store the element,
            for which the predicate p holds in theNext */
            }

            public boolean hasNext() {
                return theNext != null;
            }

            public A next() {
                var result = theNext;
                getTheNext();
                return result;
            }
        }
    }


}
