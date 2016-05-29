package cookMe.dao.instance;

import cookMe.model.search.SearchCriteria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anis on 28/05/2016.
 */
public abstract class AbstractDao<T> implements DAO<T> {
    private String dB_HOST;
    private String dB_PORT;
    private String dB_NAME;
    private String dB_USER;
    private String dB_PWD;

    protected AbstractDao(String dB_HOST, String dB_PORT, String dB_NAME, String dB_USER, String dB_PWD) {
        this.dB_HOST = dB_HOST;
        this.dB_PORT = dB_PORT;
        this.dB_NAME = dB_NAME;
        this.dB_USER = dB_USER;
        this.dB_PWD = dB_PWD;
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

            ResultSet rs = statement.executeQuery();

            if (rs.first())
                item = toObject(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
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

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
    }

    protected abstract PreparedStatement getSQLInsert(Connection con, T newItem) throws SQLException;

    protected abstract PreparedStatement getSQLDelete(Connection con, T item) throws SQLException;

    protected abstract PreparedStatement getSQLUpdate(Connection con, T item) throws SQLException;

    protected abstract PreparedStatement getSQLGetAll(Connection con) throws SQLException;

    protected abstract PreparedStatement getSQLGetById(Connection con, int id) throws SQLException;

    protected abstract T toObject(ResultSet rs) throws SQLException;


}
