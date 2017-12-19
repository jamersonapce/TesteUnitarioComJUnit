package jamerson.emprestimo.utils;

import java.time.LocalDate;

public class DataUtils {

	public static boolean isDevolucaoAposDataPrevista(LocalDate dataDevolucao, LocalDate dataPrevista) {
		return dataDevolucao.isAfter(dataPrevista) ? true : false;
	}

}
