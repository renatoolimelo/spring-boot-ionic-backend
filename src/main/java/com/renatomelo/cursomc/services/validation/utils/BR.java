package com.renatomelo.cursomc.services.validation.utils;

public class BR {

	private static final Integer[] weightCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final Integer[] weightCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	private static int calculate(final String str, final Integer[] weight) {
		int sum = 0;
		for (int i = str.length() - 1, digit; i >= 0; i--) {
			digit = Integer.parseInt(str.substring(i, i + 1));
			sum += digit * weight[weight.length - str.length() + i];
		}
		sum = 11 - sum % 11;
		return sum > 9 ? 0 : sum;
	}

	public static boolean isValidCPF(final String cpf) {
		if ((cpf == null) || (cpf.length() != 11) || cpf.matches(cpf.charAt(0) + "{11}"))
			return false;
		final Integer digit1 = calculate(cpf.substring(0, 9), weightCPF);
		final Integer digit2 = calculate(cpf.substring(0, 9) + digit1, weightCPF);
		return cpf.equals(cpf.substring(0, 9) + digit1.toString() + digit2.toString());
	}

	public static boolean isValidCNPJ(final String cnpj) {
		if ((cnpj == null) || (cnpj.length() != 14) || cnpj.matches(cnpj.charAt(0) + "{14}"))
			return false;

		final Integer digit1 = calculate(cnpj.substring(0, 12), weightCNPJ);
		final Integer digit2 = calculate(cnpj.substring(0, 12) + digit1, weightCNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digit1.toString() + digit2.toString());
	}

}