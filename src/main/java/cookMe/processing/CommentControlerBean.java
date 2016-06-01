package cookMe.processing;

import cookMe.dao.fabric.DaoFabric;
import cookMe.dao.instance.CommentDao;
import cookMe.model.CommentModelBean;
import cookMe.model.recipe.RecipeModelBean;
import cookMe.model.search.SearchCommentBean;
import cookMe.model.search.SearchRecipeBean;
import cookMe.model.user.UserModelBean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.xml.stream.events.Comment;
import java.util.List;

/**
 * Created by Antoine on 01/06/2016.
 */
@ManagedBean
@ApplicationScoped
public class CommentControlerBean extends AbstractControler<CommentModelBean, CommentDao, CommentModelBean>{
    public CommentControlerBean() {
        super(DaoFabric.getInstance().createCommentDao());
    }

    public List<CommentModelBean> getListCommentWithRecipe(RecipeModelBean recipe){
        List<CommentModelBean> comments = dao.search(new SearchCommentBean(new CommentModelBean(recipe.getId())));
        return comments;
    }

    public void addComment(CommentModelBean comment, RecipeModelBean recipe){
        //TODO : vérifier les injections !!!
        if(!comment.getComment().isEmpty()) {
            CommentModelBean commentToAdd = comment;
            commentToAdd.setUserModelBean((UserModelBean) getSessionMap().get("loggedUser"));
            commentToAdd.setRecipeModelBean(recipe);
            dao.create(comment);
        }
    }
}


