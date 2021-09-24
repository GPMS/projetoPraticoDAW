package net.ufjnet.project;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import net.ufjnet.project.models.Model;
import net.ufjnet.project.repositories.DAO;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class BckendCalApplication implements CommandLineRunner {

	@Autowired
	private DAO userDAO;

	public static void main(String[] args) {
		SpringApplication.run(BckendCalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
