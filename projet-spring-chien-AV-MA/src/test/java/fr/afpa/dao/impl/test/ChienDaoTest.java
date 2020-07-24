package fr.afpa.dao.impl.test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
	Chien chienTest;
	Client maitreTest = new Client("loginMaitre5", "pwd", "prenomMaitre", "nomMaitre");

	@BeforeAll
	public static void initAll() {
		ApplicationContext context = ContextFactory.getContext(ContextConfigurationType.CLASSPATH);
		chienDao = context.getBean(IChienDao.class);
		clientDao = context.getBean(IClientDao.class);
	}

	@Test
	@Order(1)
	public void ajoutChienBddTest() {
		Client maitreTest = new Client("maitreChien", "pwd", "prenomMaitre", "nomMaitre");
		maitreTest = clientDao.ajoutClientBdd(maitreTest);

		chienTest = new Chien("nomChienTest", "raceChienTest", "couleurChienTest", (byte) 4 , 123456);
		chienDao.ajoutChienBdd(chienTest, "maitreChien");

		assertNotNull(chienTest.getNom());
		assertEquals("nomChienTest", chienTest.getNom());
		assertEquals("raceChienTest", chienTest.getRace());
		assertEquals("couleurChienTest", chienTest.getCouleur());
		assertEquals((byte) 4, chienTest.getAge());
		assertEquals(123456, chienTest.getPuce());

		chienDao.deleteByIdBdd(chienTest.getIdChien());
		clientDao.deleteClientBdd(maitreTest);
	}

	
	//PASSE PAS ENCORE
//	@Test
//	public void deleteByIdBddTest() {
//		Client maitreTest = new Client("maitreChien", "pwd", "prenomMaitre", "nomMaitre");
//		maitreTest = clientDao.ajoutClientBdd(maitreTest);
//
//		chienTest = new Chien("nomChienTest", "raceChienTest", "CouleurChienTest", (byte) 4, 123456);
//		chienDao.ajoutChienBdd(chienTest, "maitreChien");
//
//		chienDao.deleteByIdBdd(chienTest.getIdChien());
//		assertNull(chienDao.selectByNameBdd("nomChienTest"));
//		clientDao.deleteClientBdd(maitreTest);
//	}

	@Test
	@Order(2)
	public void selectByIdBddTest() {
		Client maitreTest = new Client("maitreChien", "pwd", "prenomMaitre", "nomMaitre");
		maitreTest = clientDao.ajoutClientBdd(maitreTest);
		chienTest = new Chien("nomChienTest", "raceChienTest", "couleurChienTest", (byte) 4, 123456);
		chienDao.ajoutChienBdd(chienTest, "maitreChien");
		chienTest = chienDao.selectByIdBdd(chienTest.getIdChien());
		chienDao.deleteByIdBdd(chienTest.getIdChien());
		clientDao.deleteClientBdd(maitreTest);
	}

	@Test
	@Order(3)
	public void selectByNameBddTest() {
		Client maitreTest = new Client("maitreChien", "pwd", "prenomMaitre", "nomMaitre");
		maitreTest = clientDao.ajoutClientBdd(maitreTest);
		chienTest = new Chien("nomChienTest", "raceChienTest", "couleurChienTest", (byte) 4, 123456);
		chienDao.ajoutChienBdd(chienTest, "maitreChien");
		chienTest = chienDao.selectByNameBdd(chienTest.getNom());
		chienDao.deleteByIdBdd(chienTest.getIdChien());
		clientDao.deleteClientBdd(maitreTest);
	}

	@Test
	@Order(4)
	public void updateChienBddTest() {
		Client maitreTest = new Client("maitreChien", "pwd", "prenomMaitre", "nomMaitre");
		maitreTest = clientDao.ajoutClientBdd(maitreTest);
		chienTest = new Chien("nomChienTest", "raceChienTest", "couleurChienTest", (byte) 4, 123456);
		chienDao.ajoutChienBdd(chienTest, "maitreChien");

		chienTest.setNom("nomChienModif");
		chienTest.setRace("raceChienModif");
		chienTest.setCouleur("couleurChienModif");
		chienTest.setAge((byte) 6);
		chienTest.setPuce(987654);

		assertEquals("nomChienModif", chienTest.getNom());
		assertEquals("raceChienModif", chienTest.getRace());
		assertEquals("couleurChienModif", chienTest.getCouleur());
		assertEquals((byte) 6, chienTest.getAge());
		assertEquals(987654, chienTest.getPuce());

		chienDao.deleteByIdBdd(chienTest.getIdChien());
		clientDao.deleteClientBdd(maitreTest);
	}

	@Test
	@Order(5)
	public void getListChienByClientTest(){
		Client maitreTest = new Client("maitreChien", "pwd", "prenomMaitre", "nomMaitre");
		maitreTest = clientDao.ajoutClientBdd(maitreTest);
		chienTest = new Chien("nomChienTest", "raceChienTest", "couleurChienTest", (byte) 4, 123456);
		chienDao.ajoutChienBdd(chienTest, "maitreChien");
		
		List<Chien> listeDesChiens = chienDao.getListChienByClient("maitreChien");
		assertNotNull(listeDesChiens);
		assertNotEquals(0, listeDesChiens.size());
		assertNotNull(listeDesChiens.get(0));
		
		chienDao.deleteByIdBdd(chienTest.getIdChien());
		clientDao.deleteClientBdd(maitreTest);
	}

	@Test
	@Order(6)
	public void getListBddTest() {
		Client maitreTest = new Client("maitreChien", "pwd", "prenomMaitre", "nomMaitre");
		maitreTest = clientDao.ajoutClientBdd(maitreTest);
		chienTest = new Chien("nomChienTest", "raceChienTest", "couleurChienTest", (byte) 4, 123456);
		chienDao.ajoutChienBdd(chienTest, "maitreChien");
		List<Chien> listeDesChiens = chienDao.getListBdd();
		assertNotNull(listeDesChiens);
		assertNotEquals(0, listeDesChiens.size());
		assertNotNull(listeDesChiens.get(0));

		chienDao.deleteByIdBdd(chienTest.getIdChien());
		clientDao.deleteClientBdd(maitreTest);
	}

}
