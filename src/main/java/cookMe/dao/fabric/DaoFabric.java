package cookMe.dao.fabric;

import cookMe.dao.instance.CommentDao;
import cookMe.dao.instance.RecipesDao;
import cookMe.dao.instance.UserDao;

import java.sql.*;

/**
 * Created by djbranbran on 24/05/16.
 */
public final class DaoFabric {

    private static final DaoFabric instance = new DaoFabric();
    private final String DB_HOST = "dasilvacamposanis.fr";
    private final String DB_PORT = "8080";
    private final String DB_NAME = "JAVA_ASI";
    private final String DB_USER = "java";
    private final String DB_PWD = "TpJavaAsi2016";
    private final String mySQLPattern = "jdbc:mysql://%s:%s/%s?user=%s&password=%s";

    private final String connectionString;

    private DaoFabric() {
        connectionString = String.format(mySQLPattern, DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PWD);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant de renvoyer une instance de la classe Singleton *
     *
     * @return Retourne l'instance du singleton.
     */
    public static DaoFabric getInstance() {
        return DaoFabric.instance;
    }

    public static void main(String[] args) {
        DaoFabric instance = getInstance();

        String connectionString = instance.connectionString;

        try (Connection connection = DriverManager.getConnection(connectionString)) {
            System.out.println("Connexion reussi !");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM  JAVA_ASI.users;");
            int columnsNumber = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print("\t\t");
                System.out.print(rs.getMetaData().getColumnName(i));
            }
            System.out.println("");
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print("\t\t");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue);
                }
                System.out.println("");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public UserDao createUserDao() {
        return new UserDao(connectionString);
    }

    public RecipesDao createRecipesDao() {
        return new RecipesDao(connectionString);
    }

    public CommentDao createCommentDao() {
        return new CommentDao(connectionString);
    }
}
