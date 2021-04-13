
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class AL<E> implements List<E> {
    protected E[] store = (E[]) new Object[5];
    protected int size = 0;

    public AL(E... es) {
        for (E e : es)
            add(e);
    }

    private void mkNewStore() {
        E[] newStore = (E[]) new Object[size + 5];
        for (int i = 0; i < store.length; i++)
            newStore[i] = store[i];
        store = newStore;
    }

    public void add(E e) {
        if (store.length <= size)
            mkNewStore();
        store[size++] = e;
    }

    @Override
    public void addAll(List<E> cs) {
        for (int i = 0; i < cs.size(); i++) {
            add(cs.get(i));
        }
    }

    @Override
    public void remove(int i) {
        if (i>size||i<0)return;
        for (int j = i; j < size-1; j++) {
            store[j] = store[j+1];
        }
        size = size -1;
    }

    @Override
    public void insert(int i, E e) {
        if (i<0)return;
        if (i>size)i = size;
        if (store.length < size +1) mkNewStore();
        for (int j = size; j > i; j--) {
            store[j] = store[j-1];
        }
        store[i] = e;
        size = size+1;
    }

    @Override
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (get(i).equals(e))return true;
        }
        return false;
    }

    @Override
    public boolean containsWith(Predicate<E> pred) {
        for (int i = 0; i < size; i++) {
            if (pred.test(get(i)))return true;
        }
        return false;
    }

    @Override
    public void reverse() {
        var temp = (E[]) new Object[size()];
        int j = 1;
        for (int i = 0; i < size(); i++) {
            temp[i]=store[size()-j];
            j++;
        }
        store = temp;
    }

    @Override
    public void forEach(Consumer<? super E> consumer) {
        for (int i = 0; i < size; i++) {
            consumer.accept(get(i));
        }
    }

    @Override
    public boolean startsWith(List<E> that) {
        if (that.size()>this.size)return false;
        for (int i = 0; i < that.size(); i++) {
            if (!that.get(i).equals(this.get(i)))return false;
        }
        return true;
    }

    @Override
    public boolean endsWith(List<E> that) {
        if (that.size()>this.size)return false;
        int j = 1;
        for (int i = size-1; i > size- that.size(); i--) {
            if (!that.get(that.size()-j).equals(this.get(i)))return false;
            j++;
        }
        return true;
    }

    @Override
    public List<E> sublist(int i, int j) {
        if (i+j>size)j=size;
        AL<E> temp = new AL<E>();
        for (int k = 0; k < j; k++) {
            temp.add(store[i]);
            i++;
        }
        return temp;
    }

    @Override
    public void sortBy(Comparator<? super E> cmp) {

    }

    public int size() {
        return size;
    }

    public E get(int i) {
        return store[i];
    }
}
