package fr.afpa.test.connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import fr.afpa.dao.config.IDatabaseConnection;
import util.ContextConfigurationType;
import util.ContextFactory;

public class ConnexionTest {

	private static ApplicationContext context;

	@BeforeAll
	public static void initAll() {
		context = ContextFactory.getContext(ContextConfigurationType.CLASSPATH);
	}

	@Test
	public void connexion() {
		Connection c = context.getBean("connexionMysql", IDatabaseConnection.class).getConnection();
		assertNotNull(c);
	}

	@Test
	public void request() {
		Integer iTest = null;
		try {
			Connection c = context.getBean("connexionMysql", IDatabaseConnection.class).getConnection();
			assertNotNull(c);
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("select 1");

			while (r.next()) {
				iTest = r.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(1, iTest);
	}

}
