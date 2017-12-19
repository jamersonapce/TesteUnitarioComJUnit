package jamerson.emprestimo.utils;

public class ValorUtils {

	public static double calculaValorComAcrescimoDeMultaPara(int totalDeDiasExcedidos, double valor) {
		double valorDaMulta;
		double limiteDaMulta;

		limiteDaMulta = valor * 0.6;
		valorDaMulta = 0.4 * totalDeDiasExcedidos;

		return valorDaMulta > limiteDaMulta ? valor + limiteDaMulta : valor + valorDaMulta;

	}

}
