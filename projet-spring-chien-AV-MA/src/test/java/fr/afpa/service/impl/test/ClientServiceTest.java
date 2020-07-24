package fr.afpa.service.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.context.ApplicationContext;

import fr.afpa.bean.Client;
import fr.afpa.service.IClientService;
import util.ContextConfigurationType;
import util.ContextFactory;

@TestMethodOrder(OrderAnnotation.class)
public class ClientServiceTest {

	private static IClientService clientService;

	@BeforeAll
	public static void initAll() {
		ApplicationContext context = ContextFactory.getContext(ContextConfigurationType.CLASSPATH);
		clientService = context.getBean(IClientService.class);
	}

	@Test
	@Order(1)
	public void getListTest() {
		List<Client> listeDesClients = clientService.getList();
		assertNotNull(listeDesClients);
		assertNotEquals(0, listeDesClients.size());
		assertNotNull(listeDesClients.get(0));
	}

	@Test
	@Order(2)
	public void ajouterClientTest() {
		Client clientTest = new Client("loginTest", "passwordTest", "prenomTest", "nomTest");
		clientService.ajouterClient(clientTest);
		clientTest = clientService.selectByLogin("loginTest");
		assertNotNull(clientTest);
		assertEquals("loginTest", clientTest.getLogin());
		assertEquals("passwordTest", clientTest.getPassword());
		assertEquals("prenomTest", clientTest.getPrenom());
		assertEquals("nomTest", clientTest.getNom());
		clientService.deleteClient(clientTest);
	}

	@Test
	@Order(3)
	public void deleteClientBddTest() {
		Client clientTest = new Client("loginTest", "passwordTest", "prenomTest", "nomTest");
		clientService.ajouterClient(clientTest);
		clientService.deleteClient(clientTest);
		clientTest = clientService.selectByLogin("loginTest");
		assertNull(clientTest);
	}

	@Test
	@Order(4)
	public void selectByLoginTest() {
		Client clientTest = new Client("loginTest", "passwordTest", "prenomTest", "nomTest");
		clientService.ajouterClient(clientTest);
		clientTest = clientService.selectByLogin("loginTest");
		assertNotNull(clientTest);
		clientService.deleteClient(clientTest);

	}

}
