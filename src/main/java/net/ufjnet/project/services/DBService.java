package net.ufjnet.project.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.ufjnet.project.dtos.DTO;
import net.ufjnet.project.models.Model;
import net.ufjnet.project.repositories.DAO;
import net.ufjnet.project.services.exceptions.BusinessException;

@AllArgsConstructor
@Service
public class DBService {
	
	private DAO dao;
	
	@Transactional(readOnly = true)
	public Page<DTO> FindAll(Pageable pageable) {
		return dao.findAll(pageable).map(obj -> new DTO(obj));
	}
	
	@Transactional(readOnly = true)
	public DTO FindByID(Integer id) {
		Model result = dao.findById(id).orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		return new DTO(result);

	}
	
	@Transactional(readOnly = true)
	public DTO FindByEmail(String email) {
		Model result = dao.findByEmail(email)
				.orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
		
		return new DTO(result);
		
	}
	
	@Transactional
	public DTO Save(DTO obj) {
		Model entity = new Model(obj.getId(), obj.getName(), obj.getEmail());
		
		boolean emailExists = dao.findByEmail(obj.getEmail()).stream().anyMatch(objResult -> !objResult.equals(obj));
		if (emailExists) {
			throw new BusinessException("E-mail já existente!");
		}
		
		return new DTO(dao.save(entity));
	}

}
