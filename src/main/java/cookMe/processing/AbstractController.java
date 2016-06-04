package cookMe.processing;

import cookMe.dao.instance.DAO;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Anis on 29/05/2016.
 */
public class AbstractController<Model, Dao extends DAO<Model>, Filtre extends Model> {
    private final String CACHE;
    protected Dao dao;
    protected Filtre lastFilter;

    protected AbstractController(Dao dao) {
        this.dao = dao;
        CACHE = this.getClass().getSimpleName() + "Cache";
        getSessionMap().put(CACHE, new HashMap<Filtre, List<Model>>());
    }

    protected <E extends Enum<E>> List<String> enumToList(Class<E> enumm) {
        return Arrays.asList(enumm.getEnumConstants())
                .stream()
                .map(Enum::name)
                .collect(Collectors.toList());
    }


    protected void putIntoCache(Filtre filter, List<Model> list) {
        Map<Filtre, List<Model>> tListMap = (Map<Filtre, List<Model>>) getSessionMap().get(CACHE);
        tListMap.put(filter, list);
        lastFilter = filter;
    }

    protected List<Model> getFromCache(Filtre filter) {
        Map<Filtre, List<Model>> tListMap = (Map<Filtre, List<Model>>) getSessionMap().get(CACHE);
        return tListMap.containsKey(filter) ? tListMap.get(filter) : null;
    }


    protected Map<String, Object> getSessionMap() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return externalContext.getSessionMap();
    }

    protected Map<String, Object> getRequestMap() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return externalContext.getRequestMap();
    }

}
