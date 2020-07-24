package fr.afpa.dao.config;

import java.sql.Connection;

/**
 * Interface de récupération de connection
 * 
 * @author Aurélien
 * @version 1.0
 */
public interface IDatabaseConnection {
	public Connection getConnection();

	public void stop();
}
