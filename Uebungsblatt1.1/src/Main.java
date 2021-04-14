import org.junit.Test;

public class Main {
    public static void main(String[] args) {
        System.out.println(LL.of(1,2,3,4,5).length());
        System.out.println(LL.of(1,3,9).last());
        System.out.println(LL.of(1,2,3,4).append(LL.of(1,2,3)));
        System.out.println(LL.of(1,2,3,4,5,6,7,8,9).drop(5));
        System.out.println(LL.of(1,2,3,4,5,6,7,8,9).take(5));
        System.out.println(LL.of(1,2,3,4,5,6,7,8,9).sublist(3,4));
        System.out.println(LL.of(1,2,3,4,5,6,7,8,9).reverse());
        System.out.println(LL.of(1,2,3,4,5,6).intersperse(42));
        System.out.println(LL.of(7,8,9).isPrefixOf(LL.of(1,2,3,4,5,6,7,8,9)));
        System.out.println(LL.of(6,7,8,9).isSuffixOf(LL.of(1,2,3,4,5,6,7,8,8,9)));
        System.out.println(LL.of(6,7,8,9).isInfixOf(LL.of(1,2,3,4,5,6,7,8)));
        //System.out.println(LL.of(1,2,3,4,5,6,7,8,9).reverse());
        System.out.println(LL.of(1,2,3,4,5,6).get(3));
        System.out.println(LL.of(1,2,3,4,5,6).rotate());
        System.out.println(LL.of(1,2,3,4,5,6).tails());
        System.out.println();
        var x = new int[1];
        LL.of(1,2,3,4,5,6).forEach(y->y+=2);
        System.out.println(LL.of(1,2,3,4,5,6,7,8,9,10).dropWhile(z->z<6));
        System.out.println(LL.of(1,2,3,4,5,6,7,8,9,10).takeWhile(z->z<8));
        System.out.println(LL.of(1,2,3,4,5,6,7,8,9,10).filter(z->z%2==0));
        System.out.println(LL.of(1,2,3,4,5,6,7,8,9,10).map(z->z*z));
        System.out.println(LL.of(1,2,3,4,5).zip(LL.of("A","B","C","D")));
        System.out.println(LL.of(1,2,3,4,5,6,1,2,3).span(z->z<4));
        System.out.println(LL.of(1,2,3,4,5,6,1,2,3).partition(z->z<5));
        System.out.println(LL.of(1,2,3,4,5).isSorted((z,y)->z-y));
        System.out.println(LL.of(1,2,4,5,435,4,2345,33,3,453,423,22,0).qsort((z,y)->z-y));
    }

}
