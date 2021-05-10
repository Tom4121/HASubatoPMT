public class Main {
    public static void main(String[] args) {
//        String str = "\nHello\nWorld";
        //new Iteratoren.IntRange(1,100,10).forEach(x-> System.out.print(x+" "));
        //new Iteratoren.IntRange(100,1,-9).forEach(x-> System.out.print(x+" "));
        //new Iteratoren.Lines("\nHello\nWorld").forEach(l->System.out.println(l));
        //new Iteratoren.Words("words don't   come easy\nto me    ").forEach(w->System.out.print(w+" "));
        //System.out.println(2222);
//        System.out.println(str.split("\n").length);
//        var fib = new Iteratoren.Fib().iterator();
//        new Iteratoren.IntRange(1,20).forEach(i->System.out.print(fib.next()+" "));
        //new Iteratoren.IterableString(" hallo").forEach(c-> System.out.print(c.toString().toUpperCase()));
        new Iteratoren.Words("words don't       come easy\nto me    ").forEach(w->System.out.print(w+" 2"));
//        new Iteratoren.Filterable<>(new Iteratoren.IntRange(1,100), x->x%9==0).forEach(x-> System.out.print(x+" "));
    }
}
