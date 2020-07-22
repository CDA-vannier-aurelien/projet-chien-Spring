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
public class DataSourceMysql {
	@Value("${database.mysql.url}")
	private String url;
	@Value("${database.mysql.driver}")
	private String driver;
	@Value("${database.mysql.user}")
	private String utilisateur;
	@Value("${database.mysql.password}")
	private String motDePasse;
}
