package jamerson.emprestimo.modelo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import jamerson.emprestimo.servico.EmprestimoService;

public class UsuarioTest {

	@Test
	public void DeveLocarLivroParaUsuarioComNenhumEmprestimo() {

		Livro livro = new Livro();
		Usuario usuario = new Usuario("Jamerson", "SI1222001-21");
		Emprestimo emprestimo = new Emprestimo();
		EmprestimoService emprestimoService = new EmprestimoService();

		emprestimo = emprestimoService.realizaEmprestimo(usuario, livro);

		assertThat(usuario.getEmprestimos().size(), is(equalTo(1)));
		assertNotNull(emprestimo.getUsuario());

	}

	@Test
	public void DeveLocarLivroParaUsuarioComUmEmprestimo() {

		Livro livro = new Livro();
		Usuario usuario = new Usuario("Jamerson", "SI1222001-21");
		Emprestimo emprestimo = new Emprestimo();
		EmprestimoService emprestimoService = new EmprestimoService();

		usuario.setEmprestimo(new Emprestimo());
		emprestimo = emprestimoService.realizaEmprestimo(usuario, livro);

		assertThat(usuario.getEmprestimos().size(), is(equalTo(2)));
		assertNotNull(emprestimo.getUsuario());

	}

	@Test(expected = RuntimeException.class)
	public void TentativaDeUsuarioRealizarUmTerceiroEmprestimo() {
		Livro livro = new Livro();
		Usuario usuario = new Usuario("Jamerson", "SI1222001-21");
		Emprestimo emprestimo = new Emprestimo();
		EmprestimoService emprestimoService = new EmprestimoService();

		usuario.setEmprestimo(new Emprestimo());
		usuario.setEmprestimo(new Emprestimo());
		emprestimo = emprestimoService.realizaEmprestimo(usuario, livro);

		assertThat(usuario.getEmprestimos().size(), is(equalTo(2)));
		assertNull(emprestimo.getUsuario());

	}

}
