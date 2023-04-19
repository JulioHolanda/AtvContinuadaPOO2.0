package br.gov.cesarschool.poo.fidelidade.util;

public class ValidadorCPF {
	private ValidadorCPF() {
		
	}

	public  static boolean ehCpfValido(String cpf){
		
		if (cpf == null || cpf.length() != 11 || cpf == "") {
    	return false;
		}

		try {
			Long.parseLong(cpf);
		} catch (NumberFormatException e) {
			return false;
		}
		
		//validar primeiro digito
		int somatorioPrimeiroDigito = 0;
		int restoPrimeiroDigito=0;
		int primeiroDigito=0;
		for (int i = 0; i< (cpf.length()-2); i++) {
			somatorioPrimeiroDigito = somatorioPrimeiroDigito + ((cpf.charAt(i)-'0')*(10-i) );
		}
		restoPrimeiroDigito = somatorioPrimeiroDigito % 11;
		if (restoPrimeiroDigito < 2) {
			primeiroDigito = 0;
		}else {
			primeiroDigito = 11 - restoPrimeiroDigito;
		}
		
		if( (cpf.charAt(9) - '0') != primeiroDigito) {
			return false;
		}
		
		//validar segundo digito
		int somatorioSegundoDigito = 0;
		int restoSegundoDigito1=0;
		int segundoDigito=0;
		for (int i = 0; i< (cpf.length()-1); i++) {
			somatorioSegundoDigito = somatorioSegundoDigito + ((cpf.charAt(i)-'0')*(11-i) );
		}
		restoSegundoDigito1 = somatorioSegundoDigito % 11;
		if (restoSegundoDigito1 < 2) {
			segundoDigito = 0;
		}else {
			segundoDigito = 11 - restoSegundoDigito1;
		}
		
		if( (cpf.charAt(10) - '0') != segundoDigito) {
			return false;
		}
		
		if (cpf == "11111111111" || cpf == "22222222222" || cpf == "33333333333"
				|| cpf == "44444444444" || cpf == "55555555555" || cpf == "66666666666"
				|| cpf == "77777777777" || cpf == "88888888888" || cpf == "99999999999"
				|| cpf == "00000000000" ){
			return false;
		}
		
		return true;
	}
}

