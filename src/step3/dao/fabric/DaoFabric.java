package step3.dao.fabric;

import step3.dao.instance.RecipesDao;
import step3.dao.instance.UserDao;

import java.sql.SQLException;

/**
 * Created by djbranbran on 24/05/16.
 */
public class DaoFabric {
    // L'utilisation du mot clé volatile permet, en Java version 5 et supérieur,
    // permet d'éviter le cas où "Singleton.instance" est non-nul,

    // mais pas encore "réellement" instancié.
    // De Java version 1.2 à 1.4, il est possible d'utiliser la classe
    // ThreadLocal.
    private static volatile DaoFabric instance = null;
    private static final String DB_HOST = " db-tp.cpe.fr ";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "binome32";
    private static final String DB_USER = " binome32";
    private static final String DB_PWD = " binome32";

    private DaoFabric() {
        super();
        try {
            // Chargement du Driver, puis établissement de la connexion Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.DriverManager.getConnection("jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME, DB_USER,DB_PWD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant de renvoyer une instance de la classe Singleton *
     * @return Retourne l'instance du singleton.
     */
    public final static DaoFabric getInstance() {
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
        UserDao userDao = new UserDao(
                this.DB_HOST, this.DB_PORT, this.DB_NAME, this.DB_USER, this.DB_PWD);
        return userDao;
    }
    public RecipesDao createRecipesDao(){
        RecipesDao receipesDao = new RecipesDao(this.DB_HOST,this.DB_PORT,this.DB_NAME,this.DB_USER,this.DB_PWD);
        return receipesDao;
    }
}
