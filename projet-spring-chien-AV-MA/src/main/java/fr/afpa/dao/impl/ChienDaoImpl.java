package fr.afpa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import fr.afpa.bean.Chien;
import fr.afpa.dao.IChienDao;
import fr.afpa.dao.config.IDatabaseConnection;

/**
 * Implémentation de l'inteface dDao afin d'appeler les méthodes de
 * communication avec la BDD.
 * 
 * @author Aurélien
 * @version 1.0
 *
 */
@Repository
public class ChienDaoImpl implements IChienDao {

	Connection connection;

	/**
	 * Connection à la base de donnée anotée par le Qualifier
	 * 
	 * @param databaseConnection base de donnée sélectionnée.
	 */

	@Autowired
	public ChienDaoImpl(@Qualifier("connexionMysql") IDatabaseConnection databaseConnection) {
		this.connection = databaseConnection.getConnection();
	}

	/**
	 * Suppresion du chien sélectionné par son id.
	 * 
	 */
	@Override
	public void deleteByIdBdd(int id) {
		try {
			PreparedStatement ps = connection.prepareStatement("delete from chien where id_chien=?;");
			ps.setInt(1, id);
			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sélection d'un chien par son id.
	 *
	 */
	@Override
	public Chien selectByIdBdd(int id) {
		Chien c = new Chien();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from chien where id_chien= ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c.setIdChien(rs.getInt("id_chien"));
				c.setNom(rs.getString("nom"));
				c.setRace(rs.getString("race"));
				c.setCouleur(rs.getString("couleur"));
				c.setAge(rs.getByte("age"));
				c.setPuce(rs.getLong("puce"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * Ajoute un chien à la base de donnée.
	 * 
	 */
	@Override
	public void ajoutChienBdd(Chien pChien, String pLogin) {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"insert into chien (nom, race, couleur, age, puce, login) values (?,?,?,?,?,?); ",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, pChien.getNom());
			ps.setString(2, pChien.getRace());
			ps.setString(3, pChien.getCouleur());
			ps.setByte(4, pChien.getAge());
			ps.setLong(5, pChien.getPuce());
			ps.setString(6, pLogin);
			ps.executeUpdate();
			ResultSet resultat = ps.getGeneratedKeys();
			if (resultat.next()) {
				pChien.setIdChien(resultat.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Mise à jour des informations en BDD du chien sélectionné
	 */
	@Override
	public void updateChienBdd(Chien pChien) {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE chien  SET nom= ? , race =?, couleur =?, age =?, puce = ?\r\n" + "WHERE id_chien=?");
			ps.setString(1, pChien.getNom());
			ps.setString(2, pChien.getRace());
			ps.setString(3, pChien.getCouleur());
			ps.setByte(4, pChien.getAge());
			ps.setLong(5, pChien.getPuce());
			ps.setInt(6, pChien.getIdChien());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	/**
	 * 
	 * Récupère la liste de tous les chiens présents dans la base de donnée.
	 * 
	 */
	@Override
	public List<Chien> getListBdd() {
		List<Chien> listeChiens = new ArrayList<>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select c.id_chien, c.nom, c.race, c.couleur, c.age, c.puce from chien c ;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Chien chien = new Chien();
				chien.setIdChien(rs.getInt("id_chien"));
				chien.setNom(rs.getString("nom"));
				chien.setRace(rs.getString("race"));
				chien.setCouleur(rs.getString("couleur"));
				chien.setPuce(rs.getLong("puce"));
				chien.setAge(rs.getByte("age"));
				listeChiens.add(chien);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeChiens;
	}

	/**
	 * Récupère la liste de tous les chiens du client sélectionné par son login(id)
	 */
	@Override
	public List<Chien> getListChienByClient(String login) {
		List<Chien> listeChiens = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select c.id_chien, c.nom, c.race, c.couleur, c.age, c.puce from chien c WHERE c.login =? ");

			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Chien chien = new Chien();
				chien.setIdChien(rs.getInt("id_chien"));
				chien.setNom(rs.getString("nom"));
				chien.setRace(rs.getString("race"));
				chien.setCouleur(rs.getString("couleur"));
				chien.setPuce(rs.getLong("puce"));
				chien.setAge(rs.getByte("age"));
				listeChiens.add(chien);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeChiens;
	}

	/**
	 * Sélection du chien par son nom
	 */
	@Override
	public Chien selectByNameBdd(String nom) {
		Chien c = new Chien();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from chien c where c.nom=?");
			ps.setString(1, nom);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c.setIdChien(rs.getInt("id_chien"));
				c.setNom(rs.getString("nom"));
				c.setRace(rs.getString("race"));
				c.setCouleur(rs.getString("couleur"));
				c.setAge(rs.getByte("age"));
				c.setPuce(rs.getLong("puce"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

}
