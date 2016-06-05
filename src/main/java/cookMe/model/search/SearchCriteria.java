package cookMe.model.search;

/**
 * Created by Anis on 28/05/2016.
 */
public interface SearchCriteria<Model> {

    /***
     * Valeur par defaut des champs entiers
     */
    int ALL_VALUES_INT = Integer.MIN_VALUE;
    /***
     * Valeur par defaut des champs string
     */
    String ALL_VALUES_STRING = "[ALL]";

    /***
     * Création d'une requète grace aux critères de recherce
     *
     * @return une requèrte SQL
     */
    String getSQLSearchQuery();


}
