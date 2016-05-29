package cookMe.model.recipe;

import cookMe.view.DataCollection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created by djbranbran on 24/05/16.
 */
@ManagedBean
@SessionScoped
public class RecipeListModelBean extends DataCollection<RecipeModelBean> {
	public RecipeListModelBean() {
		super();
	}

	public RecipeListModelBean(List<RecipeModelBean> list) {
		super(list);
	}
}
