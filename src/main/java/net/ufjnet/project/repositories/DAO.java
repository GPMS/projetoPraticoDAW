package net.ufjnet.project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ufjnet.project.models.Model;

public interface DAO extends JpaRepository<Model, Integer> {

	public Optional<Model> findByName(String name);
	public Optional<Model> findByEmail(String email);
	
}
