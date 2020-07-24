package fr.afpa.dao.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Permet de réaliser la connection à la base de donnée MySql
 * 
 * @author Aurélien
 * @version 1.0
 */
@Component("connexionMysql")
public class DatabaseConnectionMysql implements IDatabaseConnection {

	@Autowired
	private DataSourceMysql dataSourceMysql;

	private Connection connexion = null;

	/**
	 * Démarre la connection à la base de donnée.
	 */
	@Override
	public Connection getConnection() {
		if (connexion == null) {
			try {
				Class.forName(dataSourceMysql.getDriver());
				connexion = DriverManager.getConnection(dataSourceMysql.getUrl(), dataSourceMysql.getUtilisateur(),
						dataSourceMysql.getMotDePasse());
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
