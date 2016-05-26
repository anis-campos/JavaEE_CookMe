package cookMe.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
/**
 * Created by Antoine on 25/05/2016.
 */

@ManagedBean
@ViewScoped
public class DataGridView<L extends DataCollection<T>, T> implements Serializable {

    private L listData;

    private T selectedData;

    public DataGridView(){

    }

    public DataGridView(L list){
        this.listData = list;
    }

    public L getListData() {
        return listData;
    }

    public T getSelectedData() {
        return selectedData;
    }

    public void setSelectedData(T selectedData) {
        this.selectedData = selectedData;
    }
}
