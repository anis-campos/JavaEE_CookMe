package cookMe.processing;

import cookMe.dao.instance.DAO;
import cookMe.model.ParsableEnum;
import cookMe.model.search.SearchCriteria;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Anis on 29/05/2016.
 */
public class AbstractController<Model, Dao extends DAO<Model>, Filter extends SearchCriteria<Model>> {
    private final String CACHE;
    protected Dao dao;
    protected Filter lastFilter;

    protected AbstractController(Dao dao) {
        this.dao = dao;
        CACHE = this.getClass().getSimpleName() + "Cache";
        getSessionMap().put(CACHE, new HashMap<Filter, List<Model>>());
    }

    protected <E extends Enum<E> & ParsableEnum> List<String> ParsableEnumToList(Class<E> enumm) {
        return Arrays.asList(enumm.getEnumConstants())
                .stream()
                .map(Enum::name)
                .filter(e -> !e.contains(ParsableEnum.DEFAULT))
                .collect(Collectors.toList());
    }


    protected void putIntoCache(Filter filter, List<Model> list) {
        Map<Filter, List<Model>> tListMap = (Map<Filter, List<Model>>) getSessionMap().get(CACHE);
        if (tListMap != null) {
            tListMap.put(filter, list);
            lastFilter = filter;
        }
    }

    protected List<Model> getFromCache(Filter filter) {
        Map<Filter, List<Model>> tListMap = (Map<Filter, List<Model>>) getSessionMap().get(CACHE);
        if (tListMap != null)
            return tListMap.containsKey(filter) ? tListMap.get(filter) : null;
        return null;
    }


    protected Map<String, Object> getSessionMap() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return externalContext.getSessionMap();
    }

    protected Map<String, Object> getRequestMap() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return externalContext.getRequestMap();
    }

    protected String getRequestUri() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        String uri = request.getRequestURI();
        return uri;
    }

    protected Map<String, Object> getViewMap() {
        return FacesContext.getCurrentInstance().getViewRoot().getViewMap();
    }
}
