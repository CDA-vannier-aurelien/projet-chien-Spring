package util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Permet le chargement du fichier de configuration de Spring
 * 
 * @author MOMO
 * 
 */
public class ContextFactory {

	private static ApplicationContext applicationContext = null;

	private static final String PATH = "C:\\Java\\...\\";
	private static final String APPLICATION_CONTEXT_XML = "applicationContext.xml";

	/**
	 * Permet de charger le contexte selon le type fourni
	 * 
	 * @param typeConfig : le type de configuration initialiser
	 * @return ApplicationContext initialise
	 */
	public static ApplicationContext getContext(final ContextConfigurationType typeConfig) {
		synchronized (ContextFactory.class) {
			if (applicationContext == null) {
				if (ContextConfigurationType.CLASSPATH.equals(typeConfig)) {
					applicationContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
				} else if (ContextConfigurationType.FILE_SYSTEM.equals(typeConfig)) {
					applicationContext = new FileSystemXmlApplicationContext(PATH + APPLICATION_CONTEXT_XML);
				} else {
					System.err.println("Localisation non definie");
				}
			}
		}
		return applicationContext;
	}

}
