package cookMe.dao.fabric;

import cookMe.dao.instance.CommentDao;
import cookMe.dao.instance.RecipesDao;
import cookMe.dao.instance.UserDao;

import java.sql.SQLException;

/**
 * Created by djbranbran on 24/05/16.
 */
public final class DaoFabric {
    // L'utilisation du mot clé volatile permet, en Java version 5 et supérieur,
    // permet d'éviter le cas où "Singleton.instance" est non-nul,

    // mais pas encore "réellement" instancié.
    // De Java version 1.2 à 1.4, il est possible d'utiliser la classe
    // ThreadLocal.
    private static volatile DaoFabric instance = null;
    private final String DB_HOST = "dasilvacamposanis.fr";
    private final String DB_PORT = "8080";
    private final String DB_NAME = "JAVA_ASI";
    private final String DB_USER = "java";
    private final String DB_PWD = "TpJavaAsi2016";

    private DaoFabric() {
        super();
        try {
            // Chargement du Driver, puis établissement de la connexion Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME, DB_USER, DB_PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant de renvoyer une instance de la classe Singleton *
     *
     * @return Retourne l'instance du singleton.
     */
    public static DaoFabric getInstance() {
        // Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet // d'éviter un appel coûteux à synchronized,
        // une fois que l'instanciation est faite.
        if (DaoFabric.instance == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation // multiple même par différents "threads".
            synchronized (DaoFabric.class) {
                if (DaoFabric.instance == null) {
                    DaoFabric.instance = new DaoFabric();
                }
            }
        }
        return DaoFabric.instance;
    }

    public UserDao createUserDao() {
        return new UserDao(
                DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PWD);
    }

    public RecipesDao createRecipesDao() {
        return new RecipesDao(DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PWD);
    }

    public CommentDao createCommentDao() {
        return new CommentDao(DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PWD);
    }
}
