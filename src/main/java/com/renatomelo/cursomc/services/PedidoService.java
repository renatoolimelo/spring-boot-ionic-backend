package com.renatomelo.cursomc.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.renatomelo.cursomc.domain.Pedido;
import com.renatomelo.cursomc.repositories.PedidoRepository;
import com.renatomelo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	private PedidoRepository repo;

	public PedidoService(PedidoRepository repo) {
		this.repo = repo;
	}

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
