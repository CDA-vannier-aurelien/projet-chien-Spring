package fr.afpa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import fr.afpa.bean.Client;
import fr.afpa.dao.IClientDao;
import fr.afpa.dao.config.IDatabaseConnection;

public class ClientDaoImpl implements IClientDao {

	Connection connection;

	@Autowired

	public ClientDaoImpl(@Qualifier("connexionMysql") IDatabaseConnection databaseConnection) {

		this.connection = databaseConnection.getConnection();

	}

	@Override
	public List<Client> getListBdd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByIdBdd(int id) {
		try {
			PreparedStatement ps = connection.prepareStatement("delete from client where id_client=" + id + ";");
			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Client selectByIdBdd(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client ajoutClientBdd(Client pClient) {

		try {
			PreparedStatement ps = connection
					.prepareStatement("insert into client (login, password, prenom, nom) values (?,?,?,?); ");

			ps.setString(1, pClient.getLogin());
			ps.setString(2, pClient.getPassword());
			ps.setString(3, pClient.getPrenom());
			ps.setString(4, pClient.getNom());
			ps.executeUpdate();
			ResultSet resultat = ps.getGeneratedKeys();
			if (resultat.next()) {
				pClient.setIdClient(resultat.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pClient;

	}

	@Override
	public Client updateClientBdd(Client pClient) {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE client \r\n" + "SET login= ? , password =?, prenom =?, nom =?\r\n" + "WHERE idClient=?");
			ps.setString(1, pClient.getLogin());
			ps.setString(2, pClient.getPassword());
			ps.setString(3, pClient.getPrenom());
			ps.setString(4, pClient.getNom());
			ps.setInt(5, pClient.getIdClient());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pClient;

	}

	@Override
	public Client selectByLogin(String pLogin) {
		Client c = null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT login from client WHERE login = ?");
			ps.setString(1, pLogin);
			ps.executeUpdate();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Client();
				c.setIdClient(rs.getInt("id_client"));
				c.setPassword(rs.getString("password"));
				c.setPrenom(rs.getString("prenom"));
				c.setNom(rs.getString("nom"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
}