package fr.afpa.dao.config;

import java.sql.Connection;

public interface IDatabaseConnection {
	public Connection getConnection();

	public void stop();
}
