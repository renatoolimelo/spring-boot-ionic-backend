package com.renatomelo.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import com.renatomelo.cursomc.domain.enums.TipoCliente;
import com.renatomelo.cursomc.dto.ClienteNewDTO;
import com.renatomelo.cursomc.repositories.exception.FieldMessage;
import com.renatomelo.cursomc.services.validation.utils.BR;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Override
	public void initialize(ClienteInsert clienteInsert) {
	}

	@Override
	public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod())
				&& !BR.isValidCPF(clienteNewDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		
		if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod())
				&& !BR.isValidCNPJ(clienteNewDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}

		for (FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
					.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
