package cookMe.model.search;

/**
 * Created by Anis on 28/05/2016.
 */
public interface SearchCriteria {

    /***
     * Création d'une requète grace aux critères de recherce
     *
     * @return une requèrte SQL
     */
    public String getSQLSearchQuery();
}
