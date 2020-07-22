package fr.afpa.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataSourcePostgresql {

	@Value("${database.postgresql.url}")
	private String url;
	@Value("${database.postgresql.driver}")
	private String driver;
	@Value("${database.postgresql.user}")
	private String utilisateur;
	@Value("${database.postgresql.password}")
	private String motDePasse;
}
