package com.tpo.fizio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;


@SpringBootApplication(exclude = {
//		JpaRepositoriesAutoConfiguration.class,
//		HibernateJpaAutoConfiguration.class,
//		DataSourceAutoConfiguration.class,
//		DataSourceTransactionManagerAutoConfiguration.class,
//		JacksonAutoConfiguration.class,
//		SecurityAutoConfiguration.class,
//		SessionAutoConfiguration.class,
})
public class FizioApplication {

	public static void main(String[] args) {
		SpringApplication.run(FizioApplication.class, args);
	}
}
