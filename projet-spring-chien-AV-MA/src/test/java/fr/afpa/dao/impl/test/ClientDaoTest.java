package fr.afpa.dao.impl.test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.context.ApplicationContext;

import fr.afpa.bean.Client;
import fr.afpa.dao.IClientDao;
import util.ContextConfigurationType;
import util.ContextFactory;

@TestMethodOrder(OrderAnnotation.class)
public class ClientDaoTest {

	private static IClientDao clientDao;

	@BeforeAll
	public static void initAll() {
		ApplicationContext context = ContextFactory.getContext(ContextConfigurationType.CLASSPATH);
		clientDao = context.getBean(IClientDao.class);
	}

	@Test
	@Order(1)
	public void getListBddTest() {
		List<Client> listeDesClients = clientDao.getListBdd();
		assertNotNull(listeDesClients);
		assertNotEquals(0, listeDesClients.size());
		assertNotNull(listeDesClients.get(0));
	}

	@Test
	@Order(2)
	public void ajoutClientBddTest() {
		Client clientTest = new Client("loginTest" , "passwordTest" , "prenomTest" , "nomTest");
		clientTest = clientDao.ajoutClientBdd(clientTest);
		assertNotNull(clientTest);
		assertEquals("loginTest" , clientTest.getLogin());
		assertEquals("passwordTest" , clientTest.getPassword());
		assertEquals("prenomTest" , clientTest.getPrenom());
		assertEquals("nomTest" , clientTest.getNom());
		clientDao.deleteClientBdd(clientTest);
	}
	
	@Test
	@Order(3)
	public void deleteClientBddTest() {
		Client clientTest = new Client("loginTest" , "passwordTest" , "prenomTest" , "nomTest");
		clientTest = clientDao.ajoutClientBdd(clientTest);
		clientDao.deleteClientBdd(clientTest);
		clientTest = clientDao.selectByLogin("loginTest");
		assertNull(clientTest);
	}
	
	@Test
	@Order(4)
	public void selectByLoginTest() {
		Client clientTest = new Client("loginTest" , "passwordTest" , "prenomTest" , "nomTest");
		clientTest = clientDao.ajoutClientBdd(clientTest);
		clientTest = clientDao.selectByLogin("loginTest");
		assertNotNull(clientTest);
		clientDao.deleteClientBdd(clientTest);
		
	}
	
}
