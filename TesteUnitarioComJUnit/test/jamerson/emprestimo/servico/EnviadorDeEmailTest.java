package jamerson.emprestimo.servico;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import jamerson.emprestimo.modelo.Emprestimo;
import jamerson.emprestimo.modelo.Livro;
import jamerson.emprestimo.modelo.Usuario;

public class EnviadorDeEmailTest {

	@Test
	public void deveEnviarEmailParaUsuariosAtrasados() {

		Emprestimo emprestimo1 = new EmprestimoService().realizaEmprestimo(new Usuario("Jamerson", "SI1222001-21"), new Livro());
		Emprestimo emprestimo2 = new EmprestimoService().realizaEmprestimo(new Usuario("Natalia", "SI1222222-21"), new Livro());
		Emprestimo emprestimo3 = new EmprestimoService().realizaEmprestimo(new Usuario("José", "SI1222333-21"), new Livro());
		
		emprestimo1.setDataDevolucao(LocalDate.now().plusDays(7));
		emprestimo2.setDataDevolucao(LocalDate.now().plusDays(8));
		emprestimo3.setDataDevolucao(LocalDate.now().plusDays(9));
		
		List<Emprestimo> EmprestimosEmAtraso = Arrays.asList(emprestimo1, emprestimo2, emprestimo3);

//		Mockito.when(Repository.buscaLocacoesEmAtraso()).thenReturn(locacoesEmAtraso);
//
//		locacaoService.notificaUsuariosEmAtraso();
//
//		Mockito.verify(emailService, Mockito.times(1)).notifica(usuario1);
//		Mockito.verify(emailService, Mockito.times(1)).notifica(usuario2);
//		Mockito.verify(emailService, Mockito.times(1)).notifica(usuario3);
//
//		Mockito.verify(emailService, Mockito.never()).notifica(usuario4);
//
//		Mockito.verifyNoMoreInteractions(emailService);
	}

}
