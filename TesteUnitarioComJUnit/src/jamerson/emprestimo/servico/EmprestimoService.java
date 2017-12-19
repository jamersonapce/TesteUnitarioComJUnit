package jamerson.emprestimo.servico;

import java.time.LocalDate;
import java.time.Period;

import jamerson.emprestimo.modelo.Emprestimo;
import jamerson.emprestimo.modelo.Livro;
import jamerson.emprestimo.modelo.Usuario;
import jamerson.emprestimo.utils.DataUtils;
import jamerson.emprestimo.utils.ValorUtils;

public class EmprestimoService {

	private Emprestimo emprestimo;

	public EmprestimoService() {
		emprestimo = new Emprestimo();
	}

	public Emprestimo realizaEmprestimo(Usuario usuario, Livro livro) {

		if (livro.isDisponivel() && usuario.isRealizaEmprestimo()) {
			emprestimo.setUsuario(usuario);
			emprestimo.setLivro(livro);
			emprestimo.setDataEmprestimo(LocalDate.now());
			emprestimo.setDataPrevista(LocalDate.now().plusDays(7));
			livro.setEmprestado(true);
			usuario.setEmprestimo(emprestimo);
			return emprestimo;
		} else {
			throw new RuntimeException("Não foi possível realizar o emprestimo");
		}

	}

	public void realizaDevolucao(LocalDate dataDevolucao) {
		emprestimo.setDataDevolucao(dataDevolucao);

		valorASerPago();

	}

	private void valorASerPago() {
		if (DataUtils.isDevolucaoAposDataPrevista(emprestimo.getDataDevolucao(), emprestimo.getDataPrevista())) {
			int totalDeDiasExcedidos = Period.between(emprestimo.getDataPrevista(), emprestimo.getDataDevolucao())
					.getDays();
			emprestimo.setValor(
					ValorUtils.calculaValorComAcrescimoDeMultaPara(totalDeDiasExcedidos, emprestimo.getValor()));
		}

	}

}
