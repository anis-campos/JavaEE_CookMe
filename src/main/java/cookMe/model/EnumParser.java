package cookMe.model;

/**
 * Created by Anis on 05/06/2016.
 */
public class EnumParser {

    /***
     * Permet d'attribuer la valeur "None" en remplacement si la première n'éxiste pas
     *
     * @param tClass le type de l'enum
     * @param value  la valeur a convertire
     * @param <T>    un enum qui à une valeur par defaut
     * @return Un enum
     * @throws IllegalArgumentException si la valeur de remplacement n'éxiste pas
     */
    public static <T extends Enum<T> & ParsableEnum> T ParseWithDefault(Class<T> tClass, String value) {
        return ParseWithDefault(tClass, value, ParsableEnum.DEFAULT);
    }

    /***
     * Permet d'attribuer une valeur de remplacement si la première n'éxiste pas
     *
     * @param tClass       le type de l'enum
     * @param value        la valeur a convertire
     * @param defaultValue valeur par defaut
     * @param <T>          un enum qui à une valeur par defaut
     * @return Un enum
     * @throws IllegalArgumentException si la valeur de remplacement n'éxiste pas
     */
    public static <T extends Enum<T> & ParsableEnum> T ParseWithDefault(Class<T> tClass, String value, String defaultValue) {
        try {
            return Enum.valueOf(tClass, value);
        } catch (Exception ex) {
            return Enum.valueOf(tClass, defaultValue);
        }
    }

}
