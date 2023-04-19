package br.gov.cesarschool.poo.fidelidade.testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.gov.cesarschool.poo.fidelidade.util.ValidadorCPF;

public class ValidadorCPFTest {

	@Test
	public void testNull() {
		assertFalse(ValidadorCPF.ehCpfValido(null));
	}
	
//	@Test
//	public void testConstrutor() {
//		assertEquals(null,new ValidadorCPF());
//	}
	
	@Test
	public void testStringVazio() {
		assertFalse(ValidadorCPF.ehCpfValido(""));
	}
	
	@Test
	public void testQuantidadeDigitos() {
		assertFalse(ValidadorCPF.ehCpfValido("1010101010"));
		assertFalse(ValidadorCPF.ehCpfValido("121212121212"));
	}

	@Test
	public void testApenasNumeros() {
		assertFalse(ValidadorCPF.ehCpfValido(" 234567891 "));
	}
	
	@Test
	public void testPrimeiroDigito() {
		assertFalse(ValidadorCPF.ehCpfValido("11144477725"));
	}

	@Test
	public void testSegundoDigito() {
		assertFalse(ValidadorCPF.ehCpfValido("11144477734"));
	}
	
	@Test
	public void testDigitosRepetidos() {
		assertFalse(ValidadorCPF.ehCpfValido("11111111111"));
	}
}
