package db;

import java.sql.Connection;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO<T> {

	protected Connection connection = Connect.getConnection();

	/**
	 * Permet de récupérer un objet via son ID
	 * @param id
	 * @return
	 */
	public abstract T find(int id);

	/**
	 * Permet de créer une entrée dans la base de données
	 * par rapport à un objet
	 * @param obj
	 */
	public abstract T create(T obj);

	/**
	 * Permet de mettre à jour les données d'une entrée dans la base
	 * @param obj
	 */
	public abstract T update(T obj);

	/**
	 * Permet la suppression d'une entrée de la base
	 * @param obj
	 */
	public abstract void delete(T obj);
	
	/**
	 * Permet d'obtenir l'ensemble des données de la table
	 * @return une liste d'objet T
	 */
	public abstract List<T> getAll();

	protected abstract T toObject(ResultSet result) throws SQLException;

}
