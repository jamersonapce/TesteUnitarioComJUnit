package jamerson.emprestimo.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String nome;
	private String matricula;
	private List<Emprestimo> emprestimos;

	public Usuario(String nome, String matricula) {
		this.nome = nome;
		this.matricula = matricula;
		this.emprestimos = new ArrayList<>();
	}

	public boolean isRealizaEmprestimo() {
		return this.emprestimos.size() < 2 ? true : false;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimos.add(emprestimo);
	}

}
