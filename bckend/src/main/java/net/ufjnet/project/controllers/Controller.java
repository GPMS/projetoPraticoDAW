package net.ufjnet.project.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.ufjnet.project.dtos.DTO;
import net.ufjnet.project.services.DBService;

@RestController
//@RequestMapping("")
public class Controller {

	@Autowired
	private DBService service;
	
	@GetMapping
	public ResponseEntity<CollectionModel<DTO>> buscarTodos(@RequestParam(value = "page", defaultValue = "0") int page,
															@RequestParam(value = "limit", defaultValue = "12") int limit,
															@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));

		Page<DTO> pages = service.FindAll(pageable);
		pages
			.stream()
			.forEach(p -> p.add(
						linkTo(methodOn(Controller.class).buscarUm(p.getId())).withSelfRel()
					)
			);

		return ResponseEntity.ok(CollectionModel.of(pages));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DTO> buscarUm(@PathVariable Integer id) {
		DTO objDTO = service.FindByID(id);
		objDTO.add(linkTo(methodOn(Controller.class).buscarUm(id)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DTO> incluir(@Valid @RequestBody DTO objBody) {
		DTO objDTO = service.Save(objBody);
		objDTO.add(linkTo(methodOn(Controller.class).buscarUm(objDTO.getId())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}
	
}
