package cookMe.model.comment;

import cookMe.model.recipe.RecipeModelBean;
import cookMe.model.user.UserModelBean;

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
    private String date;

    public CommentModelBean() {
    }

    public CommentModelBean(int userModelId, int recipeModelBeanId, String comment, String date) {
        userModelBean = new UserModelBean();
        userModelBean.setId(userModelId);
        recipeModelBean = new RecipeModelBean();
        recipeModelBean.setId(recipeModelBeanId);
        this.comment = comment;
        this.date = date;
    }

    public CommentModelBean(UserModelBean userModelBean, RecipeModelBean recipeModelBean, String comment, String date) {
        this.userModelBean = userModelBean;
        this.recipeModelBean = recipeModelBean;
        this.comment = comment;
        this.date = date;
    }



    public UserModelBean getUserModelBean() {
        return userModelBean;
    }

    public void setUserModelBean(UserModelBean userModelBean) {
        this.userModelBean = userModelBean;
    }

    public RecipeModelBean getRecipeModelBean() {
        return recipeModelBean;
    }

    public void setRecipeModelBean(RecipeModelBean recipeModelBean) {
        this.recipeModelBean = recipeModelBean;
    }

    public String getComment() {
        return comment;
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

    public String getUserLogin(){
        return userModelBean.getLogin();
    }

    public String getDate(){
        return date;
    }


}
