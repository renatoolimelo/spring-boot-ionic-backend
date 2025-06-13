package com.renatomelo.cursomc.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.renatomelo.cursomc.domain.Cliente;
import com.renatomelo.cursomc.repositories.ClienteRepository;
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

}
