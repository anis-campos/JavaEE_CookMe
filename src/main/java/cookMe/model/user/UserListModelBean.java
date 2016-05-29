package cookMe.model.user;

import cookMe.view.DataCollection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created by Anis on 26/05/2016.
 */
@ManagedBean
@SessionScoped
public class UserListModelBean extends DataCollection<UserModelBean> {

    public UserListModelBean() {
        super();
    }

    public UserListModelBean(List<UserModelBean> list) {
        super(list);
    }
}
