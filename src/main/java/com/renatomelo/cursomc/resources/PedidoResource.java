package com.renatomelo.cursomc.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renatomelo.cursomc.domain.Pedido;
import com.renatomelo.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	private PedidoService service;

	public PedidoResource(PedidoService service) {
		this.service = service;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

}
