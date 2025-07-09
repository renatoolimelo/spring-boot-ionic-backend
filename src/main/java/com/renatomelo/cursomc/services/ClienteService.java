package com.renatomelo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.renatomelo.cursomc.domain.Cliente;
import com.renatomelo.cursomc.dto.ClienteDTO;
import com.renatomelo.cursomc.repositories.ClienteRepository;
import com.renatomelo.cursomc.services.exceptions.DataIntegrityException;
import com.renatomelo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	private ClienteRepository repo;

	public ClienteService(ClienteRepository repo) {
		this.repo = repo;
	}

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente update(Cliente cliente) {
		Cliente clienteAtualizado = find(cliente.getId());
		updateData(clienteAtualizado, cliente);
		return repo.save(clienteAtualizado);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque a entidades relacionadas");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}

	private void updateData(Cliente clienteAtualizado, Cliente cliente) {
		clienteAtualizado.setNome(cliente.getNome());
		clienteAtualizado.setEmail(cliente.getEmail());
	}

}
