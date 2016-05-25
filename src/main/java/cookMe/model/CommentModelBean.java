package cookMe.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by djbranbran on 24/05/16.
 */
@ManagedBean
@SessionScoped
public class CommentModelBean {
    private UserModelBean userModelBean;
    private RecipeModelBean recipeModelBean;
    private String comment;

    public CommentModelBean() {
    }

    public CommentModelBean(UserModelBean userModelBean, RecipeModelBean recipeModelBean, String comment) {
        this.userModelBean = userModelBean;
        this.recipeModelBean = recipeModelBean;
        this.comment = comment;
    }

    public UserModelBean getUserModelBean() {
        return userModelBean;
    }

    public RecipeModelBean getRecipeModelBean() {
        return recipeModelBean;
    }

    public String getComment() {
        return comment;
    }

    public void setUserModelBean(UserModelBean userModelBean) {
        this.userModelBean = userModelBean;
    }

    public void setRecipeModelBean(RecipeModelBean recipeModelBean) {
        this.recipeModelBean = recipeModelBean;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentModelBean{" +
                "userModelBean=" + userModelBean +
                ", recipeModelBean=" + recipeModelBean +
                ", comment='" + comment + '\'' +
                '}';
    }

    public void setRecipeId(int recipeId) {
        this.recipeModelBean.setId(recipeId);
    }
}
