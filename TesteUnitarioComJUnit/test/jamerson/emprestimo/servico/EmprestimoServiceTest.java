package jamerson.emprestimo.servico;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import jamerson.emprestimo.modelo.Emprestimo;
import jamerson.emprestimo.modelo.Livro;
import jamerson.emprestimo.modelo.Usuario;

public class EmprestimoServiceTest {

	@Test
	public void deveRealizarEmprestimoEmLivroQueNaoEstejaResevado() {

		EmprestimoService service = new EmprestimoService();
		Livro livro = new Livro();
		Usuario usuario = new Usuario("Jamerson", "SI1222001-21");

		assertTrue(livro.isDisponivel());

		Emprestimo emprestimo = service.realizaEmprestimo(usuario, livro);

		assertNotNull(emprestimo.getLivro());
	}

	@Test(expected = RuntimeException.class)
	public void deveImpedirEmprestimoEmLivroQueJaPossuiUmaReseva() {

		EmprestimoService service = new EmprestimoService();
		Livro livro = new Livro();
		Usuario usuario = new Usuario("Jamerson", "SI1222001-21");

		livro.setReservado(true);

		Emprestimo emprestimo = service.realizaEmprestimo(usuario, livro);

		assertThat(livro.isDisponivel(), is(equalTo(false)));
		assertThat(emprestimo.getLivro(), is(nullValue()));
	}

	@Test
	public void deveGarantirQueADataPrevistaEstaCorretaAposLocacao() {

		EmprestimoService service = new EmprestimoService();
		Livro livro = new Livro();
		Usuario usuario = new Usuario("Jamerson", "SI1222001-21");

		Emprestimo emprestimo = service.realizaEmprestimo(usuario, livro);
		LocalDate dataPrevista = LocalDate.now().plusDays(7);

		assertNotNull(emprestimo.getDataPrevista());
		assertEquals(dataPrevista, emprestimo.getDataPrevista());
	}

	@Test
	public void deveGarantirADevolucaoAntesDaDataPrevista() {

		EmprestimoService service = new EmprestimoService();
		Livro livro = new Livro();
		Usuario usuario = new Usuario("Jamerson", "SI1222001-21");

		Emprestimo emprestimo = service.realizaEmprestimo(usuario, livro);
		service.realizaDevolucao(LocalDate.now().plusDays(5));

		// LocalDate dataDevolucao = LocalDate.of(2017, 11, 24);
		LocalDate dataDevolucao = LocalDate.now().plusDays(5);

		assertEquals(dataDevolucao, emprestimo.getDataDevolucao());
		assertThat(emprestimo.getValor(), is(equalTo(5.0)));
	}

	@Test
	public void deveGarantirADevolucaoNaDaDataPrevista() {

		EmprestimoService service = new EmprestimoService();
		Livro livro = new Livro();
		Usuario usuario = new Usuario("Jamerson", "SI1222001-21");

		Emprestimo emprestimo = service.realizaEmprestimo(usuario, livro);
		service.realizaDevolucao(LocalDate.now().plusDays(7));

		LocalDate dataDevolucao = LocalDate.now().plusDays(7);

		assertEquals(dataDevolucao, emprestimo.getDataDevolucao());
		assertThat(emprestimo.getValor(), is(equalTo(5.0)));
	}

	@Test
	public void deveGarantirADevolucaoUmDiaAposADaDataPrevistaComMulta() {

		EmprestimoService service = new EmprestimoService();
		Livro livro = new Livro();
		Usuario usuario = new Usuario("Jamerson", "SI1222001-21");

		Emprestimo emprestimo = service.realizaEmprestimo(usuario, livro);
		service.realizaDevolucao(LocalDate.now().plusDays(8));

		LocalDate dataDevolucao = LocalDate.now().plusDays(8);

		assertEquals(dataDevolucao, emprestimo.getDataDevolucao());
		assertThat(emprestimo.getValor(), is(equalTo(5.4)));
	}

	@Test
	public void deveGarantirADevolucaoTrintaDiasAposADaDataPrevistaComMulta() {

		EmprestimoService service = new EmprestimoService();
		Livro livro = new Livro();
		Usuario usuario = new Usuario("Jamerson", "SI1222001-21");

		Emprestimo emprestimo = service.realizaEmprestimo(usuario, livro);
		service.realizaDevolucao(LocalDate.now().plusDays(30));

		LocalDate dataDevolucao = LocalDate.now().plusDays(30);

		assertEquals(dataDevolucao, emprestimo.getDataDevolucao());
		assertThat(emprestimo.getValor(), is(equalTo(8.0)));
	}

}
