package jamerson.emprestimo.modelo;

import java.util.List;

public class Livro {

	private String autor;
	private String titulo;
	private boolean isEmprestado;
	private boolean isReservado;
	private List<Emprestimo> historico;

	public boolean isDisponivel() {
		return !(this.isReservado() || isEmprestado()) ? true : false;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	private boolean isEmprestado() {
		return isEmprestado;
	}

	public void setEmprestado(boolean isEmprestado) {
		this.isEmprestado = isEmprestado;
	}

	private boolean isReservado() {
		return isReservado;
	}

	public void setReservado(boolean isReservado) {
		this.isReservado = isReservado;
	}

	public Iterable<Emprestimo> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Emprestimo> historico) {
		this.historico = historico;
	}

}
