import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.Comparator;

import java.util.NoSuchElementException;


public sealed interface LL<E> permits LL.Nil, LL.Cons {


    static public final record Nil<E>() implements LL<E> {
        @Override
        public String toString() {
            return "[]";
        }
    }


    static public final record Cons<E>(E hd, LL<E> tl) implements LL<E> {
        @Override
        public String toString() {
            return show();
        }
    }


    default boolean isEmpty() {
        return this instanceof Nil;
    }


    default E head() {
        if (this instanceof Cons<E> c) return c.hd();
        throw new NoSuchElementException("head on empty list");
    }


    default LL<E> tail() {
        if (this instanceof Cons<E> c) return c.tl();
        throw new NoSuchElementException("tail on empty list");
    }


    static final LL nil = new Nil<>();

    static <E> LL<E> nil() {
        return nil;
    }


    static <E> LL<E> cons(E hd, LL<E> tl) {
        return new Cons<>(hd, tl);
    }


    static <E> LL<E> of(E... es) {
        LL<E> r = nil();
        for (int i = es.length - 1; i >= 0; i--) r = cons(es[i], r);
        return r;
    }


    default String show() {
        StringBuffer result = new StringBuffer("[");
        boolean first = true;
        for (var it = this; !it.isEmpty(); it = it.tail()) {
            if (first) first = false;
            else result.append(", ");
            result.append(it.head());
        }
        result.append("]");
        return result.toString();
    }


    default int length() {
        if (isEmpty()) return 0;
        return 1 + tail().length();
    }


    default E last() {
        if (tail().isEmpty()) return head();
        return tail().last();
    }


    default LL<E> append(LL<E> that) {
        if (this instanceof Cons) { // alternativ !isEmpty()
            return cons(head(), tail().append(that));
        } else {
            return that;
        }
    }


    default LL<E> drop(int i) {
        if (i <= 0) return this;
        if (i > length()) return nil();
        return tail().drop(i - 1);
    }


    default LL<E> take(int i) {
        if (i > length()) return this;
        if (i <= 0) return nil();
        return cons(head(),tail().take(i-1));
    }


    default LL<E> sublist(int from, int length) {
        return drop(from).take(length);
    }


    default LL<E> reverse() {
        return reverse1(nil());
    }

    default LL<E> reverse1(LL<E> element){
        if (tail().isEmpty()) return cons(head(),element);
        return tail().reverse1(cons(head(),element));
    }


    default LL<E> intersperse(E e) {
        if (this.tail() instanceof Cons)return new Cons<E>(head(),cons(e,tail().intersperse(e)));
        return cons(head(),tail());
    }


    default boolean isPrefixOf(LL<E> that) {
        if (isEmpty())return true;
        if(this.length()> that.length())return false;
        if (tail() instanceof Cons) {
            if (this.head().equals(that.head())) return this.tail().isPrefixOf(that.tail());
            return false;
        }
        return this.head().equals(that.head());
    }


    default boolean isSuffixOf(LL<E> that) {
        return this.reverse().isPrefixOf(that.reverse());
    }


    default boolean isInfixOf(LL<E> that) {
        //return that.tail().isPrefixOf(this);
        if(this.length()> that.length())return false;
        if(!isPrefixOf(that)) return this.isInfixOf(that.tail());
        return true;
    }


    default E get(int i) {
        //if (i>length())new IndexOutOfBoundsException();
        if (i==0)return head();
        return tail().get(i-1);
    }


    default LL<E> rotate() {
        return tail().append(of(head()));
    }


    default LL<LL<E>> tails() {
        if (isEmpty())return cons(nil(),nil());
        return cons(this,tail().tails());   /*ToDo*/
    }


    default void forEach(Consumer<? super E> con) {
        if (this instanceof Cons){
            con.accept(head());
            tail().forEach(con);
        }
    }


    default boolean containsWith(Predicate<? super E> p) {
        if (this instanceof Cons) {
            if (p.test(head())) return true;
            else return tail().containsWith(p);
        }
        return false;
    }


    default boolean contains(E el) {
        return this.containsWith(x->x.equals(el));
    }


    default LL<E> dropWhile(Predicate<? super E> p) {
        if (this instanceof Cons) {
            if (p.test(head())) return tail().dropWhile(p);
            else return this;
        }
        return this;
    }


    default LL<E> takeWhile(Predicate<? super E> p) {
        if (this instanceof Cons) {
            if (p.test(head())) {
                return cons(head(), tail().takeWhile(p));
            }
        }
        return nil();


    }


    default LL<E> filter(Predicate<? super E> p) {
        if (this instanceof Cons){
            if (p.test(head()))return cons(head(),tail().filter(p));
            else return tail().filter(p);
        }
        return nil();
    }


    default <R> LL<R> map(Function<? super E, ? extends R> f) {
        if (this instanceof Cons){
            return cons(f.apply(head()),tail().map(f));
        }
        return nil();
    }


    static public record Pair<A, B>(A fst, B snd) {

        public String toString() {
            return "(" + fst() + ", " + snd() + ")";
        }
    }


    default <B> LL<Pair<E, B>> zip(LL<B> that) {
        if (this instanceof Cons && that instanceof Cons){
            return cons(new Pair(head(),that.head()),tail().zip(that.tail()));
        }
        return nil();   /*ToDo*/
    }


    default Pair<LL<E>, LL<E>> span(Predicate<? super E> p) {

        return new Pair<>(nil(), nil());   /*ToDo*/
    }


    default Pair<LL<E>, LL<E>> partition(Predicate<? super E> p) {
        return new Pair<>(nil(), nil());   /*ToDo*/
    }


    default boolean isSorted(Comparator<? super E> cmp) {
        return false;   /*ToDo*/
    }


    default LL<E> qsort(Comparator<? super E> cmp) {
        return nil();     /*ToDo*/
    }


}
