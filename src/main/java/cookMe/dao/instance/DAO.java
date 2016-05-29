package cookMe.dao.instance;

import cookMe.model.SearchCriteria;

import java.util.List;

/***
 * Interface definissant les méthodes CRUD :
 * - Create
 * - Read (find)
 * - Update
 * - Delete
 *
 * @param <T> Le type de donnée
 */
public interface DAO<T> {


    /***
     * Insertion  d'un élémént dans la base de données
     *
     * @param newItem l'élément à insérer
     * @return L'élément depuis la base de donnée
     */
    public T create(T newItem);

    /***
     * Mise à jour  d'un élémént dans la base de données
     *
     * @param item l'élément à mettre à jour
     * @return L'élément depuis la base de donnée
     */
    public T update(T item);

    /***
     * Supression  d'un élémént dans la base de données
     *
     * @param item l'élément à suprimmer
     */
    public void delete(T item);

    /***
     * Retrouve  un élémént dans la base de données avec son id
     *
     * @param id l'Id de l'élément à retrouver
     * @return L'élément depuis la base de donnée
     */
    public T find(int id);

    /***
     * Récupère tous élémént dans la base de données
     *
     * @return La liste des éléménts
     */
    public List<T> getAll();

    /***
     * Récupère tous élémént dans la base de données qui ont les même caleur que les critères de recherche
     *
     * @param searchCriteria le critère de recherche
     * @return La liste des éléménts
     */
    public List<T> search(SearchCriteria searchCriteria);
}
