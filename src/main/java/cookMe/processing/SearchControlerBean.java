package cookMe.processing;

import cookMe.model.recipe.ListRecipeTypeBean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Created by djbranbran on 25/05/16.
 */
@ManagedBean
@ApplicationScoped
public class SearchControlerBean {


    private ListRecipeTypeBean listRecipeTypeBean;

    public SearchControlerBean() {
    }

    public String searchRecipe(){
        listRecipeTypeBean = new ListRecipeTypeBean();
        listRecipeTypeBean.update();

        //récupère l'espace de mémoire de JSF
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> requestMap = externalContext.getRequestMap();
        //place la liste de recette dans l'espace de mémoire de JSF
        requestMap.put("listRecipeTypeBean", listRecipeTypeBean);
        return "search.jsf";
    }

    public ListRecipeTypeBean getListRecipeTypeBean() {
        return listRecipeTypeBean;
    }

    public void setListRecipeTypeBean(ListRecipeTypeBean listRecipeTypeBean) {
        this.listRecipeTypeBean = listRecipeTypeBean;
    }

}
