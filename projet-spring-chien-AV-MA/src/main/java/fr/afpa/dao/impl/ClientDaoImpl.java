package fr.afpa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import fr.afpa.bean.Client;
import fr.afpa.dao.IClientDao;
import fr.afpa.dao.config.IDatabaseConnection;

/**
 * Implémentation des méthodes de communication avec la BDD concernant le
 * client.
 * 
 * @author Mathieu
 * @version 1.0
 */
@Repository
public class ClientDaoImpl implements IClientDao {

	/*
	 * Récupération de la connection
	 */
	Connection connection;

	@Autowired

	/**
	 * Détermine quelle base de donnée appeler.
	 * 
	 * @param databaseConnection Base de donnée avec laquelle on travaille.
	 */
	public ClientDaoImpl(@Qualifier("connexionMysql") IDatabaseConnection databaseConnection) {

		this.connection = databaseConnection.getConnection();

	}

	/**
	 * récupère la liste de tous les clients.
	 */
	@Override
	public List<Client> getListBdd() {
		List<Client> list = new ArrayList<Client>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM client;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Client c = new Client();
				c.setLogin(rs.getString("login"));
				c.setPassword(rs.getString("password"));
				c.setPrenom(rs.getString("prenom"));
				c.setNom(rs.getString("nom"));
				list.add(c);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	/**
	 * Ajoute un nouveau client dans la BDD
	 */
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pClient;

	}

	/**
	 * Sélectionne le client par son login qui correspond à son Id.
	 */
	@Override
	public Client selectByLogin(String pLogin) {
		Client c = null;
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT password, prenom, nom from client WHERE login =?");
			ps.setString(1, pLogin);

			ps.executeUpdate();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Client();
				c.setLogin(pLogin);
				c.setPassword(rs.getString("password"));
				c.setPrenom(rs.getString("prenom"));
				c.setNom(rs.getString("nom"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * Supprime le client sélectionné de la base de donnée.
	 */
	@Override
	public void deleteClientBdd(Client pClient) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM client WHERE login=?");
			ps.setString(1, pClient.getLogin());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}