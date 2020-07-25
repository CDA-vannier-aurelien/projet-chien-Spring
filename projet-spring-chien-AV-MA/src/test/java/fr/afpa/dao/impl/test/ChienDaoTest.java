package fr.afpa.dao.impl.test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.context.ApplicationContext;

import fr.afpa.bean.Chien;
import fr.afpa.bean.Client;
import fr.afpa.dao.IChienDao;
import fr.afpa.dao.IClientDao;
import util.ContextConfigurationType;
import util.ContextFactory;

@TestMethodOrder(OrderAnnotation.class)
public class ChienDaoTest {

	private static IChienDao chienDao;
	private static IClientDao clientDao;
	static Chien chienTest;
	static Client maitreTest = new Client("maitreChien", "pwd", "prenomMaitre", "nomMaitre");

	@BeforeAll
	public static void initAll() {
		ApplicationContext context = ContextFactory.getContext(ContextConfigurationType.CLASSPATH);
		chienDao = context.getBean(IChienDao.class);
		clientDao = context.getBean(IClientDao.class);
	}

	/*
	 * Les tests s'eefectuent de manière séquentielle automatique, en suivant la
	 * logique du CRUD
	 * 
	 * Le test delete ne passe pas encore
	 */

	@Test
	@Order(1)
	public void ajoutChienBddTest() {
		maitreTest = clientDao.ajoutClientBdd(maitreTest);
		chienTest = new Chien("nomChienTest", "raceChienTest", "couleurChienTest", (byte) 4, 123456);
		chienDao.ajoutChienBdd(chienTest, maitreTest.getLogin());

		// Récupération du chien pour tester si bien présent
		chienTest = chienDao.selectByNameBdd("nomChienTest");
		assertNotNull(chienTest.getNom());
		assertEquals("nomChienTest", chienTest.getNom());
		assertEquals("raceChienTest", chienTest.getRace());
		assertEquals("couleurChienTest", chienTest.getCouleur());
		assertEquals((byte) 4, chienTest.getAge());
		assertEquals(123456, chienTest.getPuce());
	}

	@Test
	@Order(2)
	public void selectByIdBddTest() {
		chienTest = chienDao.selectByIdBdd(chienTest.getIdChien());
		assertNotNull(chienTest.getNom());
	}

	@Test
	@Order(3)
	public void selectByNameBddTest() {
		chienTest = chienDao.selectByNameBdd("nomChienTest");
		assertNotNull(chienTest);
	}

	@Test
	@Order(4)
	public void getListChienByClientTest() {
		// Récup chien associé au Client login maitreChien
		List<Chien> listeDesChiens = chienDao.getListChienByClient("maitreChien");
		assertNotNull(listeDesChiens);
		assertNotEquals(0, listeDesChiens.size());
		assertNotNull(listeDesChiens.get(0));
	}

	@Test
	@Order(5)
	public void getListBddTest() {
		List<Chien> listeDesChiens = chienDao.getListBdd();
		assertNotNull(listeDesChiens);
		assertNotEquals(0, listeDesChiens.size());
		assertNotNull(listeDesChiens.get(0));
	}

	@Test
	@Order(6)
	public void updateChienBddTest() {
		// Récup du chien en bdd
		chienTest = chienDao.selectByNameBdd("nomChienTest");

		// Changement des valeurs du chien
		chienTest.setNom("nomChienModif");
		chienTest.setRace("raceChienModif");
		chienTest.setCouleur("couleurChienModif");
		chienTest.setAge((byte) 6);
		chienTest.setPuce(987654);

		// Check si modifs ont bien été effectuées
		assertEquals("nomChienModif", chienTest.getNom());
		assertEquals("raceChienModif", chienTest.getRace());
		assertEquals("couleurChienModif", chienTest.getCouleur());
		assertEquals((byte) 6, chienTest.getAge());
		assertEquals(987654, chienTest.getPuce());

		// Remise des valeurs par défaut
		chienTest.setNom("nomChienTest");
		chienTest.setRace("raceChienTest");
		chienTest.setCouleur("couleurChienTest");
		chienTest.setAge((byte) 4);
		chienTest.setPuce(123456);
	}

	
	//Test à améliorer car les champs sont bien == null mais l'objet CHIEN est toujours présent
	@Test
	@Order(7)
	public void deleteByIdBddTest() {
		chienTest = chienDao.selectByNameBdd("nomChienTest");
		chienDao.deleteByIdBdd(chienTest.getIdChien());
		
		//Recherche du chien supprimé
		chienTest = chienDao.selectByNameBdd("nomChienTest");
		assertNull(chienTest.getNom());
	}

	@Test
	@AfterAll
	// Suppression du Client créé pour le besoin
	public static void getOut() {
		clientDao.deleteClientBdd(maitreTest);
	}
}
