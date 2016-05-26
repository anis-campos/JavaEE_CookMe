package cookMe.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anis on 26/05/2016.
 */
public abstract class DataCollection<T> {
    private List<T> list;


    public DataCollection() {
        this(new ArrayList<T>());
    }

    public DataCollection(List<T> list) {
        this.list = list;
    }

    public void add(T item) {
        this.list.add(item);
    }

    public List<T> getAll() {
        return list;
    }
}
