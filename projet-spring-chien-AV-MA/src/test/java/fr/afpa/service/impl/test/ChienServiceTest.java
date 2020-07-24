package fr.afpa.service.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import fr.afpa.service.IChienService;
import fr.afpa.service.IClientService;
import util.ContextConfigurationType;
import util.ContextFactory;

@TestMethodOrder(OrderAnnotation.class)
public class ChienServiceTest {

	private static IChienService chienService;
	private static IClientService clientService;
	static Chien chienTest;
	static Client maitreTest = new Client("maitreChien", "pwd", "prenomMaitre", "nomMaitre");

	@BeforeAll
	public static void initAll() {
		ApplicationContext context = ContextFactory.getContext(ContextConfigurationType.CLASSPATH);
		chienService = context.getBean(IChienService.class);
		clientService = context.getBean(IClientService.class);
	}

	@Test
	@Order(1)
	public void ajouterChienTest() {
		clientService.ajouterClient(maitreTest);
		chienTest = new Chien("nomChienTest", "raceChienTest", "couleurChienTest", (byte) 4, 123456);
		chienService.ajouterChien(chienTest, maitreTest.getLogin());

		// Récupération du chien pour tester si bien présent
		chienTest = chienService.selectByName("nomChienTest");
		assertNotNull(chienTest.getNom());
		assertEquals("nomChienTest", chienTest.getNom());
		assertEquals("raceChienTest", chienTest.getRace());
		assertEquals("couleurChienTest", chienTest.getCouleur());
		assertEquals((byte) 4, chienTest.getAge());
		assertEquals(123456, chienTest.getPuce());
	}

	@Order(2)
	public void getListTest() {
		List<Chien> listChiens = chienService.getList();
		assertNotNull(listChiens);
		assertNotEquals(0, listChiens.size());
		assertNotNull(listChiens.get(0));
	}

	@Test
	@Order(3)
	public void selectByIdTest() {
		chienTest = chienService.selectById(1);
		assertNotNull(chienTest);
	}

	@Test
	@Order(4)
	public void selectByName() {
		chienTest = chienService.selectByName("nomChienTest");
		assertNotNull(chienTest);
	}

	@Test
	@Order(5)
	public void updateChienTest() {
		chienTest = chienService.selectByName("nomChienTest");
		chienTest.setNom("nomChienModif");
		assertEquals("nomChienModif", chienTest.getNom());

		// remise de la valeur par défaut
		chienTest.setNom("nomChienTest");
	}

	@Test
	@Order(6)
	public void getListChienByClientTest() {
		List<Chien> listChiens = chienService.getListChienByClient(maitreTest.getLogin());
		assertNotNull(listChiens);
		assertNotEquals(0, listChiens.size());
		assertNotNull(listChiens.get(0));
	}

	@Test
	@Order(7)
	public void parseByteTest() {
		String vAgeByte = "3";
		assertEquals(true, chienService.parseByteTest(vAgeByte));
	}

	@Test
	@Order(8)
	public void parseLongTest() {
		String vNumPuceLong = "4";
		assertEquals(true, chienService.parseLongTest(vNumPuceLong));
	}

	
	//Test à améliorer car les champs sont bien == null mais l'objet CHIEN est toujours présent
	@Test
	@Order(9)
	public void deleteByIdTest() {
		chienTest = chienService.selectByName("nomChienTest");
		chienService.deleteById(chienTest.getIdChien());

		// Recherche du chien supprimé
		chienTest = chienService.selectByName("nomChienTest");
		assertNull(chienTest.getNom());
	}

	@Test
	@AfterAll
	// Suppression du Client créé pour le besoin
	public static void getOut() {
		clientService.deleteClient(maitreTest);
	}

}
