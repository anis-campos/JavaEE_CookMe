package cookMe.dao.instance;

import cookMe.model.search.SearchCriteria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anis on 28/05/2016.
 */
abstract class AbstractDao<T> implements DAO<T> {
    private final String connectionString;

    AbstractDao(String connectionString) {
        this.connectionString = connectionString;
    }

    @Override
    public List<T> search(SearchCriteria searchCriteria) {
        List<T> rep = new ArrayList<>();
        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement(searchCriteria.getSQLSearchQuery());

            ResultSet rs = statement.executeQuery();

            while (rs.next())
                rep.add(toObject(rs));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rep;
    }

    @Override
    public void delete(T item) {
        try (Connection connection = getConnection()) {

            PreparedStatement statement = getSQLDelete(connection, item);

            statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public T update(T item) {
        T rep = null;
        try (Connection connection = getConnection()) {

            PreparedStatement statement = getSQLUpdate(connection, item);

            statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        //// TODO: 28/05/2016 Renvoyer l'élement depuis la base
        return item;
    }

    @Override
    public T create(T newItem) {
        T item = null;
        try (Connection connection = getConnection()) {

            PreparedStatement statement = getSQLInsert(connection, newItem);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //TODO: 29/05/2016 : Trouver un moyer de recuperer l'element depuis la base
        return null;
    }

    @Override
    public T find(int id) {
        T item = null;
        try (Connection connection = getConnection()) {

            PreparedStatement statement = getSQLGetById(connection, id);

            ResultSet rs = statement.executeQuery();

            if (rs.first())
                item = toObject(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public List<T> getAll() {
        List<T> rep = new ArrayList<>();
        try (Connection connection = getConnection()) {

            PreparedStatement statement = getSQLGetAll(connection);

            ResultSet rs = statement.executeQuery();

            while (rs.next())
                rep.add(toObject(rs));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rep;
    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString);
    }

    protected abstract PreparedStatement getSQLInsert(Connection con, T newItem) throws SQLException;

    protected abstract PreparedStatement getSQLDelete(Connection con, T item) throws SQLException;

    protected abstract PreparedStatement getSQLUpdate(Connection con, T item) throws SQLException;

    protected abstract PreparedStatement getSQLGetAll(Connection con) throws SQLException;

    protected abstract PreparedStatement getSQLGetById(Connection con, int id) throws SQLException;

    protected abstract T toObject(ResultSet rs) throws SQLException;


}
