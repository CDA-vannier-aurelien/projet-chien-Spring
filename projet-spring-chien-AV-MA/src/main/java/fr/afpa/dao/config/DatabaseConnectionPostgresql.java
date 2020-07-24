package fr.afpa.dao.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Permet de réaliser la connection à la base de donnée Postgresql
 * 
 * @author Aurélien
 * @version 1.0
 */
@Component("connexionPostgres")
public class DatabaseConnectionPostgresql implements IDatabaseConnection {

	@Autowired
	private DataSourcePostgresql dataSourcePostgresql;

	private Connection connexion = null;

	/**
	 * Démarre la connection à la base de donnée.
	 */
	@Override
	public Connection getConnection() {
		if (connexion == null) {
			try {
				Class.forName(dataSourcePostgresql.getDriver());
				connexion = DriverManager.getConnection(dataSourcePostgresql.getUrl(),
						dataSourcePostgresql.getUtilisateur(), dataSourcePostgresql.getMotDePasse());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connexion;
	}

	/**
	 * Arrêt la connection à la base de donnée.
	 */
	@Override
	public void stop() {
		if (connexion != null) {
			try {
				connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
