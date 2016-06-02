package cookMe.model.search;

import cookMe.model.user.UserModelBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by anis.dasilvacampos on 02/06/2016.
 */
@ManagedBean
@RequestScoped
public class SearchUserBean extends UserModelBean implements SearchCriteria {
    private static final String ALL_VALUES_STRING = null;
    private static final int ALL_VALUES_INT = 0;
    public SearchUserBean() {
        super(ALL_VALUES_INT, ALL_VALUES_STRING, ALL_VALUES_STRING, ALL_VALUES_INT, ALL_VALUES_STRING, ALL_VALUES_STRING, ALL_VALUES_STRING, UserType.none);
    }

    @Override
    public String getSQLSearchQuery() {
        //// TODO: 02/06/2016 : Créer la requête de recherche d'un utilisateur si necessaire
        return null;
    }
}
