import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.Comparator;




public class AL<E>  {


    private int size = 0;
    private Object[]store = new Object[10];



    public boolean isEmpty(){return size==0;}



    public int length(){
        return size;
    }



    public E get(int i){
        if (i>=size||i<0) throw new IndexOutOfBoundsException();
        return (E)store[i];
    }



    public E head(){return get(0);}



    public static <E>AL<E> nil(){return new AL<>();}



    public void add(E e){
        if (size>=store.length) enlargeStore();
        store[size++] = e;
    }



    private void enlargeStore(){
        Object[]newStore = new Object[store.length+10];
        for (int i=0;i<size;i++) newStore[i]=store[i];
        store=newStore;
    }




    public static <E>AL<E> of(E...es){
        AL<E> r = nil();
        for (var e:es) r.add(e);
        return r;
    }




    @Override public String toString(){
        StringBuffer result = new StringBuffer("[");
        boolean first = true;
        for (var i=0;i<size;i++){
            if (first) first = false;else result.append(", ");
            result.append(store[i]);
        }
        result.append("]");
        return result.toString();
    }



    @Override public boolean equals(Object o){
        if (o.getClass()!=AL.class) return false;
        var that = (AL<E>)o;
        if (this.length()!=that.length()) return false;
        for (int i=0;i<size;i++){
            if (!this.get(i).equals(that.get(i)))return false;
        }
        return true;
    }



    public E last(){
        if (isEmpty())throw new NoSuchElementException();
        return get(length()-1);
    }




    public AL<E> append(AL<E> that){
        AL<E> rs = nil();
        for (int i = 0; i < size; i++) {
            rs.add(get(i));
        }
        for (int i = 0; i < that.size; i++) {
            rs.add(that.get(i));
        }
        return rs;
    }





    public void addAll(AL<E> that){
        for (int i = 0; i < that.size; i++) {
            add(that.get(i));
        }
    }




    public AL<E> drop(int i){
        if (i>=length())return nil();
        if (i<0)return this;
        AL<E> rs = nil();
        for (; i < length(); i++) {
            rs.add(get(i));
        }
        return rs;
    }




    public AL<E> tail(){return drop(1);}



    public AL<E> take(int i){
        if (i>length())return this;
        if (i<0)return nil();
        AL<E> rs = nil();
        for (int j=0; j < i; j++) {
            rs.add(get(j));
        }
        return rs;
    }




    public AL<E> sublist(int from, int length) {
        if (from>length())return nil();
        AL<E> rs = nil();
        for (; from <= length(); from++) {
            length--;
            if (length<0)return rs;
            if (from>=length())return rs;
            rs.add(get(from));
        }
        return rs;
    }



    public AL<E> reverse(){
        AL<E> temp = nil();
        for (int i = length()-1; i >=0 ; i--) {
            temp.add(get(i));
        }
        return temp;
    }



    public AL<E> intersperse(E e){
        if (isEmpty())return nil();
        AL<E> temp = nil();
        temp.add(get(0));
        for (int i = 1; i < length(); i++) {
            temp.add(e);
            temp.add(get(i));
        }
        return temp;
    }



    public boolean isPrefixOf(AL<E> that){
        if (length()>that.length())return false;
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(that.get(i)))return false;
        }
        return true;
    }



    public boolean isSuffixOf(AL<E> that){
        if (length()>that.length())return false;
        return reverse().isPrefixOf(that.reverse());
    }



    public boolean isInfixOf(AL<E> that){
        if (this.length() > that.length()) return false;
        for (int i = 0; i < that.length(); i++) {
            //if (length()==that.length()-i&&!isPrefixOf(that))return false;
            if (isPrefixOf(that.drop(i))) return true;
        }

        return false;
    }



    public AL<E> rotate(){
        AL<E> temp = nil();
        for (int i = 1; i < length(); i++) {
            temp.add(get(i));
        }
        temp.add(get(0));
        return temp;
    }



    public AL<AL<E>> tails(){
        AL<AL<E>> tempMain = nil();
        AL<E> temp = nil();
        for (int i = 0; i < size; i++) {
            for (int j = i; j < length(); j++) {
                temp.add(get(j));
            }
            tempMain.add(temp);
            temp=nil();
        }
        tempMain.add(nil());
        return tempMain;
    }



    public void forEach(Consumer<? super E> con) {
        for (int i = 0; i < length(); i++) {
            con.accept(get(i));
        }
    }




    public boolean containsWith(Predicate< ? super E> p) {
        for (int i = 0; i < length(); i++) {
            if (p.test(get(i)))return true;
        }
        return false;
    }




    public boolean contains(E el) {
        return containsWith(x->x.equals(el));
    }




    public AL<E> dropWhile(Predicate< ? super E> p){
        AL<E> temp = nil();
        int i = 0;
        for (; i < length(); i++) {
            if (!p.test(get(i)))break;
        }
        for (; i < length(); i++) {
            temp.add(get(i));
        }
        return temp;
    }



    public AL<E> takeWhile(Predicate< ? super E> p){
        AL<E> temp = nil();
        for (int j = 0; j < length(); j++) {
            if (!p.test(get(j))) {
                break;
            }
            temp.add(get(j));
        }
        return temp;
    }



    public AL<E> filter(Predicate<? super E> p){
        AL<E> temp = nil();
        for (int i = 0; i < length(); i++) {
            if (p.test(get(i)))temp.add(get(i));
        }
        return temp;
    }



    public <R> AL<R> map(Function<? super E, ? extends R> f){
        AL<R> temp = nil();
        for (int i = 0; i < length(); i++) {
            temp.add(f.apply(get(i)));
        }
        return temp;
    }



    static public record Pair<A,B>(A fst,B snd){
        public String toString(){return "("+fst()+", "+snd()+")";}
    }



    public <B> AL<Pair<E,B>> zip(AL<B> that){
        AL<Pair<E,B>> temp = nil();
        int length = length();
        if (that.length()<this.length())length = that.length();
        for (int i = 0; i < length; i++) {
            temp.add(new Pair<>(get(i),that.get(i)));
        }
        return temp;
    }




    public Pair<AL<E>,AL<E>> span(Predicate<? super E> p){
        return new Pair<>(takeWhile(p),dropWhile(p));
    }




    public Pair<AL<E>,AL<E>> partition(Predicate<? super E> p){
        return new Pair<>(this.filter(p), this.filter(Predicate.not(p)));
    }




    public boolean isSorted(Comparator<? super E> cmp){
        for (int i = 0; i < length()-1; i++) {
            if (cmp.compare(get(i),get(i+1))>0)return false;
        }
        return true;
    }




    public AL<E> qsort(Comparator<? super E> cmp){
        if (length() <= 1) return this;
        var temp = this.tail().partition(x -> cmp.compare(x, head()) <= 0);
        return temp.fst.qsort(cmp).append(of(head())).append(temp.snd.qsort(cmp));
    }



}
