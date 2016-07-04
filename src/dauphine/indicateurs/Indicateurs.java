package dauphine.indicateurs;

import dauphine.stock.Stock;

public class Indicateurs {

	public static Double[] performance(Double[] tab) {
		Double[] res = new Double[tab.length - 1];
		for (int i = 0; i < tab.length - 1; i++) {
			res[i] = (tab[i] / tab[i + 1]) - 1;
		}
		return res;
	}

	public static double perfPeriodique(Double[] tab, int nb) {
		return (Math.pow((tab[tab.length - 1] / tab[tab.length - nb]), ((double)Stock.NB_WEEKS_YR / (nb))) - 1);
	}
	
	public static double mean(Double[] tab) {
		double somme = 0;
		for (double d : tab)
			somme += d;
		return somme / tab.length;
	}

	public static double variance(Double[] tab) {
		double result = 0;
		double avg = mean(tab);
		for (double d : tab)
			result += Math.pow((d - avg), 2);
		return result;
	}

	public static double getBeta(Double[] fondPerf, Double[] indicePerf) {
		return coVariance(fondPerf, indicePerf) / variance(indicePerf);
	}

	public static double getAlpha(double fondsYr, double IndiceYr, double beta) {
		return fondsYr - (beta * IndiceYr);
	}

	public static double coVariance(Double[] fondPerf, Double[] perfI) {
		double result = 0;
		double MoyI = mean(perfI);
		double MoyF = mean(fondPerf);
		for (int i = 0; i < fondPerf.length; i++) {
			result = result + ((fondPerf[i] - MoyF) * (perfI[i] - MoyI));
		}
		return result / fondPerf.length;
	}

	public static double volatility(Double[] tab) {
		double result = variance(performance(tab));
		return Math.sqrt(result / (tab.length - 1));
	}

	public static double periodicalVolatility(Double[] actifs, int nb) {
		return volatility(actifs) * Math.sqrt(nb);
	}
	
	public static double trackingError(double[] perfRelative){
		Double [] copy = new Double[perfRelative.length];
		for (int i=0;i<perfRelative.length;i++){
			copy[i] = perfRelative[i];
		}
		return Math.sqrt(variance(copy))*Math.sqrt((double)Stock.NB_WEEKS_YR) ;
	}


	public static double ratioInformation(Double[] fonds, Double[] Indice, double tE) {
		if (tE==0)
			return 0;
		return (perfPeriodique(fonds, 2) - perfPeriodique(Indice, 2))/tE;
	}

	
}
