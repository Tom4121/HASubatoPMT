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

    public IntRange(int from, int to, int step) {
      this.from = from;
      this.to = to;
      this.step = step;
      infinite = false;
    }

    public IntRange(int from, int to) {
      this(from, to, 1);
    }

    public IntRange(int from) {
      this(from, Integer.MAX_VALUE);
      infinite = true;
    }

    public IntRange() {
      this(1);
    }

    @Override
    public Iterator<Integer> iterator() {

      return new IntRangeIterator();
    }

    private class IntRangeIterator implements Iterator<Integer> {
      int from = IntRange.this.from;
      int to = IntRange.this.to;
      int step = IntRange.this.step;

      @Override
      public boolean hasNext() {
        return step >= 0 && from <= to || step <= 0 && from >= to || infinite;
      }

      @Override
      public Integer next() {
        int result = from;
        from = from + step;
        return result;
      }
    }
  }


  public static class Fib implements Iterable<BigInteger> {

    @Override
    public Iterator<BigInteger> iterator() {
      return new Iterator<BigInteger>() {
        int from = 1;
        BigInteger beforeLast = BigInteger.ZERO;
        BigInteger last = BigInteger.ONE;

        @Override
        public boolean hasNext() {
          return true;
        }

        @Override
        public BigInteger next() {
          if (from == 1) {
            from++;
            return beforeLast;
          }
          if (from == 2) {
            from++;
            return last;
          }
          BigInteger result = beforeLast.add(last);
          beforeLast = last;
          last = result;
          from++;
          return result;
        }
      };
    }

    public static void main(String[] args) {
      new Fib().forEach(x -> System.out.println(x));
    }
  }


  public static class ArrayIterable<A> implements Iterable<A> {
    A[] as;
    int counter = 0;

    public ArrayIterable(A[] as) {
      this.as = as;
    }

    @Override
    public Iterator<A> iterator() {
      return new ArrayIterableIterator();
    }

    private class ArrayIterableIterator implements Iterator<A> {
      @Override
      public boolean hasNext() {
        return counter < as.length;
      }

      @Override
      public A next() {
        A result = (A) as[counter];
        counter++;
        return result;
      }
    }
  }


  public static class IterableString implements Iterable<Character> {
    String str;
    int length;
    int counter = 0;

    public IterableString(String str) {
      this.str = str;
      length = str.length();
    }

    public static void main(String[] args) {
      for (char c : new IterableString("Hello world!")) {
        System.out.println(c);
      }
    }

    @Override
    public Iterator<Character> iterator() {
      return new Iterator() {
        int counter = 0;

        @Override
        public boolean hasNext() {
          return counter < length;
        }

        @Override
        public Character next() {
          char result = str.charAt(counter);
          counter++;
          return result;
        }
      };
    }
  }


  public static class Lines implements Iterable<String> {
    static String NEW_LINE = System.getProperty("line.separator");
    String str;
    String[] strArr;

    public Lines(String str) {
      this.str = str;
      if (str.equals("")) strArr = new String[0];
      else strArr = str.split(NEW_LINE,-1);
    }

    @Override
    public Iterator<String> iterator() {
      return new Iterator<String>() {
        int counter = 0;

        @Override
        public boolean hasNext() {
          if (strArr.length == 0) return false;
          return counter < strArr.length;
        }

        @Override
        public String next() {
          String result = strArr[counter];
          counter++;
          return result;
        }
      };
    }

    public static void main(String[] args) {
      for (String s : new Lines("hallo" + NEW_LINE + "welt!" + NEW_LINE)) System.out.println(s);
    }
  }


  public static class Words implements Iterable<String> {
    String str;
    String[] strArr;


    public Words(String str) {
      this.str = str;
      while (str.startsWith("\s")) str = str.substring(1);
      if (str.equals("")) strArr = new String[0];
      else if (str.endsWith("\\s+")) {
        str += "tmp";
        strArr = str.split("\\s+");
        strArr[strArr.length - 1] = "";
      } else {
        strArr = str.split("\\s+");
      }
    }

    @Override
    public Iterator<String> iterator() {
      return new Iterator<String>() {
        int counter = 0;

        @Override
        public boolean hasNext() {
          return counter < strArr.length;
        }

        @Override
        public String next() {
          String result = strArr[counter];
          counter++;
          return result;
        }
      };
    }
  }


  public class IndexIterable<A> implements Iterable<A> {

    Function<Long, A> f;

    public IndexIterable(Function<Long, A> f) {
      this.f = f;
    }

    @Override
    public Iterator<A> iterator() {
      return new Iterator<A>() {
        long i = 1;
        Function<Long, A> f = IndexIterable.this.f;

        @Override
        public boolean hasNext() {
          return false;
        }

        @Override
        public A next() {
          A result = f.apply(i);
          i++;
          return result;
        }
      };
    }

  }


  public static class GenerationIterable<A> implements Iterable<A> {
    A a;
    Function<A, A> f;
    A next;

    public GenerationIterable(A a, Function<A, A> f) {
      this.a = a;
      this.f = f;
    }

    @Override
    public Iterator<A> iterator() {
      return new Iterator<A>() {
        @Override
        public boolean hasNext() {
          return true;
        }

        @Override
        public A next() {
          A result = a;
          a = f.apply(a);
          return result;
        }
      };
    }
  }


  public static class OddIterable extends GenerationIterable<Long> {
    public OddIterable() {
      super(1L, x -> x + 2);
      //hier der korrekte Aufruf....
    }
  }


  public static record Limit<A>(Iterable<A> itA, long n) implements Iterable<A> {
    public Iterator<A> iterator() {
      return new Iterator<>() {
        Iterator<A> it = itA.iterator();
        int i = 0;

        @Override
        public boolean hasNext() {
          return i < n;
        }

        @Override
        public A next() {
          var result = it.next();
          i++;
          return result;
        }
      };
    }
  }


  public static record Maperable<A, R>(Iterable<A> itA, Function<A, R> f) implements Iterable<R> {
    public Iterator<R> iterator() {
      return new Iterator<>() {
        Iterator<A> it = itA.iterator();

        @Override
        public boolean hasNext() {
          return it.hasNext();
        }

        @Override
        public R next() {
          var result = f.apply(it.next());
          return result;
        }
      };
    }
  }


  public static record Filterable<A>(Iterable<A> itA, Predicate<A> p) implements Iterable<A> {
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
        // ToDo store the element, for which the predicate p holds in theNext
        A temp = it.next();
        while (!p.test(temp)) {
          temp = it.next();
        }
        theNext = temp;
      }

      public boolean hasNext() {
        return theNext != null && it.hasNext();
      }

      public A next() {
        var result = theNext;
        getTheNext();
        return result;
      }
    }
  }
}
