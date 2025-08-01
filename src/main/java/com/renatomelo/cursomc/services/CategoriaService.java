package com.renatomelo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.renatomelo.cursomc.domain.Categoria;
import com.renatomelo.cursomc.dto.CategoriaDTO;
import com.renatomelo.cursomc.repositories.CategoriaRepository;
import com.renatomelo.cursomc.services.exceptions.DataIntegrityException;
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

	public Categoria update(Categoria categoria) {
		Categoria categoriaAtualizado = find(categoria.getId());
		updateData(categoriaAtualizado, categoria);
		return repo.save(categoriaAtualizado);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
		}
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}

	public void updateData(Categoria categoriaAtualizado, Categoria categoria) {
		categoriaAtualizado.setNome(categoria.getNome());
	}

}
