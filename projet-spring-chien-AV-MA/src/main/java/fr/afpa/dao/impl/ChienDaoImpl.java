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

import fr.afpa.bean.Chien;
import fr.afpa.dao.IChienDao;
import fr.afpa.dao.config.IDatabaseConnection;

@Repository
public class ChienDaoImpl implements IChienDao {

	Connection connection;

	@Autowired

	public ChienDaoImpl(@Qualifier("connexionMysql") IDatabaseConnection databaseConnection) {
		this.connection = databaseConnection.getConnection();
	}

	@Override
	public void deleteByIdBdd(int id) {
		try {
			PreparedStatement ps = connection.prepareStatement("delete from chien where id_chien=" + id + ";");
			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Chien selectByIdBdd(int id) {
		Chien c = new Chien();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select c.id_chien, c.nom, c.race, c.couleur, c.age from chien c where p.id_chien=" + id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c.setIdChien(rs.getInt("id_chien"));
				c.setNom(rs.getString("nom"));
				c.setRace(rs.getString("prenom"));
				c.setCouleur("couleur");
				c.setAge(rs.getByte("age"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public Chien ajoutChienBdd(Chien pChien) {
		List<Chien> listeChiens = getListBdd();
		Chien lastChien = listeChiens.get(listeChiens.size() - 1);

		try {
			PreparedStatement ps = connection
					.prepareStatement("insert into chien (nom, race, couleur, age) values (?,?,?,?); ");

			ps.setString(1, pChien.getNom());
			ps.setString(2, pChien.getRace());
			ps.setString(3, pChien.getCouleur());
			ps.setByte(4, pChien.getAge());
			ps.executeUpdate();
			ResultSet resultat = ps.getGeneratedKeys();
			if (resultat.next()) {
				pChien.setIdChien(resultat.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pChien;

	}

	@Override
	public void updateChienBdd(Chien pChien) {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE chien \r\n" + "SET nom= ? , race =?, couleur =?, age =?\r\n" + "WHERE id_chien=?");
			ps.setString(1, pChien.getNom());
			ps.setString(2, pChien.getRace());
			ps.setString(3, pChien.getCouleur());
			ps.setByte(4, pChien.getAge());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public List<Chien> getListBdd() {
		List<Chien> listeChiens = new ArrayList<>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select c.nom, c.race, c.couleur, c.age from chien c order by c.age desc;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Chien chien = new Chien();

				chien.setNom(rs.getString("nom"));
				chien.setRace(rs.getString("race"));
				chien.setCouleur(rs.getString("couleur"));
				chien.setAge(rs.getByte("age"));
				listeChiens.add(chien);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeChiens;
	}

	@Override
	public List<Chien> getListChienByClient(String login) {
		List<Chien> listeChiens = new ArrayList<>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select c.nom, c.race, c.couleur, c.age from chien c WHERE c.login = " + login);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Chien chien = new Chien();

				chien.setNom(rs.getString("nom"));
				chien.setRace(rs.getString("race"));
				chien.setCouleur(rs.getString("couleur"));
				chien.setAge(rs.getByte("age"));
				listeChiens.add(chien);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeChiens;
	}

}
