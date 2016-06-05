package cookMe.processing;

import cookMe.dao.fabric.DaoFabric;
import cookMe.dao.instance.CommentDao;
import cookMe.model.comment.CommentModelBean;
import cookMe.model.comment.CommentSubmissionModelBean;
import cookMe.model.recipe.RecipeModelBean;
import cookMe.model.search.SearchCommentBean;
import cookMe.model.user.UserModelBean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by Antoine on 01/06/2016.
 */
@ManagedBean
@ApplicationScoped
public class CommentControllerBean extends AbstractController<CommentModelBean, CommentDao, SearchCommentBean> {
    public CommentControllerBean() {
        super(DaoFabric.getInstance().createCommentDao());
    }


    public List<CommentModelBean> getListCommentWithRecipe(RecipeModelBean recipe){
        SearchCommentBean searchCommentBean = new SearchCommentBean();
        searchCommentBean.setRecipeId(recipe.getId());
        return dao.search(searchCommentBean);
    }

    public void addComment(CommentSubmissionModelBean comment, RecipeModelBean recipe) {
        if(comment.isValid()) {
            comment.setUserModelBean((UserModelBean) getSessionMap().get("loggedUser"));
            comment.setRecipeModelBean(recipe);
            dao.create(comment);
        }

    }
}


