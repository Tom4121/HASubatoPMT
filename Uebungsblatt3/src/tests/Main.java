package tests;

import classes.Strom;

import java.util.stream.Collectors;

import static classes.Strom.*;

public class Main {
  public static void main(String[] args) {
//    System.out.println(classes.Reduction.readRoman(classes.Reduction.toList("CMXCIX")));
//    System.out.println(classes.Reduction.readBinary(List.of('1','0','1','0')));
//    System.out.println(Strom.quersumme(4242));
//    System.out.println( fibPairs().limit(10).collect(Collectors.toList()));
//    System.out.println(fibs().skip(4).findFirst().get().longValue());
    System.out.println( facPairs().limit(5).collect(Collectors.toList()));
    System.out.println(facs().limit(20).collect(Collectors.toList()));
  }
}
