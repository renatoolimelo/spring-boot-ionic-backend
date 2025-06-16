package com.renatomelo.cursomc.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.renatomelo.cursomc.domain.Categoria;
import com.renatomelo.cursomc.repositories.CategoriaRepository;
import com.renatomelo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	private CategoriaRepository repo;

	public CategoriaService(CategoriaRepository repo) {
		this.repo = repo;
	}

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

}
