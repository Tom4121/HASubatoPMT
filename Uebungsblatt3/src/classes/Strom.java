package classes;

import java.util.function.*;
import java.util.*;
import java.util.stream.*;
import java.math.BigInteger;


public interface Strom {


  static long readBinary(String x) {
    return x.chars().reduce(0, (rs, v) -> {
      if (rs == 0L && Character.getNumericValue(v) == 1) return 1;
      else return rs * 2 + Character.getNumericValue(v);
    });
  }


  static long quersumme(long x) {
    return LongStream.iterate(x, (y) -> y / 10).takeWhile(y -> y > 0).reduce(0, (rs, v) -> rs = rs + (v % 10));
  }


  static long factorial(int x) {
    return LongStream
      .iterate(1L, (y) -> y + 1L)
      .limit(x)
      .reduce(0, (rs, v) -> {
        if (rs == 0) return 1 * v;
        return rs * v;
      });
  }


  static String asBinary(long x) {
    return (x == 0) ? "0"
      : LongStream
      .iterate(x, (y) -> y / 2)
      .takeWhile(y -> y > 0)
      .mapToObj(y -> y)
      .reduce("", (rs, v) -> {
        return v % 2 + rs;
      }, (rs2, v) -> {
        return rs2 + v;
      });
  }


  static String convertToBase(int b, long x) {
    return (x == 0) ? "0"
      : LongStream
      .iterate(x, (y) -> y / b)
      .takeWhile(y -> y > 0)
      .mapToObj(y -> y)
      .reduce("", (rs, v) -> {
        return toDigit((int) (v % b)) + rs;
      }, (String t, String v) -> {
        return t + v;
      });
  }

  static String digits = "0123456789ABFDEFGHIJKLMNOPQRSTUVWXYZ";

  static char toDigit(int x) {
    return digits.charAt(x);
  }


  static long readFromBase(int b, String x) {
    return x.chars().reduce(0, (rs, v) -> {
      if (rs == 0L && Character.getNumericValue(v) == 1) return 1;
      else return rs * b + Character.getNumericValue(v);
    });
  }

  public static record TwoLong(long i1, long i2) {
  }


  static public Stream<TwoLong> fibPairs() {
    return Stream.iterate(new TwoLong(0, 1), (y) -> new TwoLong(y.i2, y.i1 + y.i2));
  }

  static public Stream<Long> fibs() {
    return Stream.generate(new Supplier<Long>() {
      int i = 0;

      @Override
      public Long get() {
        return fibPairs().limit(1000).toList().get(i++).i1;
      }
    });
  }

  static public Stream<Long> fibs100() {
    return fibs().limit(100);
  }

  static public long fib(int n) {
    return fibs().limit(n).toList().get(n - 1);
  }

  static public record TwoBig(BigInteger i1, BigInteger i2) {
  }

  static public Stream<TwoBig> facPairs() {
    return Stream.iterate(new TwoBig(BigInteger.ONE, BigInteger.ONE), y -> new TwoBig(y.i1.add(BigInteger.ONE), y.i2.multiply(y.i1.add(BigInteger.ONE))));
  }

  static public Stream<BigInteger> facs() {
    return Stream.generate(new Supplier<BigInteger>() {
      int i = 0;

      @Override
      public BigInteger get() {
        return facPairs().limit(100).toList().get(i++).i2;
      }
    });
  }

  static public BigInteger fac(int n) {
    return facs().limit(n).toList().get(n - 1);
  }

  static public class SpliterateString implements Spliterator<Character> {
    int i = 0;
    int end;
    String s;

    public SpliterateString(String s) {
      this(0, s.length() - 1, s);
    }

    public SpliterateString(int i, int end, String s) {
      this.i = i;
      this.end = end;
      this.s = s;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
      if (i > end) return false;
      action.accept(s.charAt(i++));
      return true;
    }

    @Override
    public Spliterator<Character> trySplit() {
      if (end - i <= 1) return null;
      int middle = i + ((end - i) / 2);
      int iOld = i;
      i = middle;
      return new SpliterateString(iOld, middle - 1, s);
    }

    @Override
    public long estimateSize() {
      return end - i;
    }

    @Override
    public int characteristics() {
      return 0;//nur Info
    }
  }

}
