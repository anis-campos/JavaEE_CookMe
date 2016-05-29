package cookMe.processing;

import cookMe.dao.instance.DAO;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Anis on 29/05/2016.
 */
public class AbstractControler<Model, Dao extends DAO<Model>, Filtre extends Model> {
    private final String CACHE;
    protected Dao dao;

    protected AbstractControler(Dao dao) {
        this.dao = dao;
        CACHE = this.getClass().getSimpleName() + "Cache";
        getSessionMap().put(CACHE, new HashMap<Filtre, List<Model>>());
    }


    protected void putIntoCache(Filtre filter, List<Model> list) {
        Map<Filtre, List<Model>> tListMap = (Map<Filtre, List<Model>>) getSessionMap().get(CACHE);
        tListMap.put(filter, list);
    }

    protected List<Model> getFromCache(Filtre filter) {
        Map<Filtre, List<Model>> tListMap = (Map<Filtre, List<Model>>) getSessionMap().get(CACHE);
        return tListMap.get(filter);
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
