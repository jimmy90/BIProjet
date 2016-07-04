package dauphine.stock;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;

import dauphine.indicateurs.Indicateurs;

public class Stock {
	
	public static final int NB_TIME_PERIODS = 3;
	public static final int NB_WEEKS_YR = 52;

	private String benchID;
	private String benchmark;
	private String country;
	private String id;
	private String industry;
	private String name;
	private String sector;
	private String zone;

	private double alpha[];
	private double beta[];
	private double vol[];
	private double perf[];
	private double perfRelative[];

	private LinkedHashMap<Date, Double> rawPrices;
	private LinkedHashMap<Date, Double> rawBenchPrices;

	private LinkedHashMap<Date, Double> prices;
	private LinkedHashMap<Date, Double> benchPrices;

	private Double pricesArray[];
	private Double benchPricesArray[];

	private double tError;
	private double iRation;

	private int[] indexPerdiode;
	private int[] nbWeeksPeriode;

	public void initIndicators() {
		Double[] stockPerf = null;
		Double[] indexPerf = null;
		double indexPerfYr;
		Double[] stockPricesC;
		timeIndexes();
		initIndicatorsLength();
		for (int i = 0; i < indexPerdiode.length; i++) {
			if (indexPerdiode[i] >= 0) {
				stockPricesC = Arrays.copyOfRange(pricesArray, indexPerdiode[i], pricesArray.length - 1);
				perf[i] = Indicateurs.perfPeriodique(pricesArray, nbWeeksPeriode[i]);
				indexPerfYr = Indicateurs.perfPeriodique(benchPricesArray, nbWeeksPeriode[i]);
				stockPerf = Indicateurs.performance(stockPricesC);
				indexPerf = Indicateurs
						.performance(Arrays.copyOfRange(benchPricesArray, indexPerdiode[i], pricesArray.length - 1));

				beta[i] = Indicateurs.getBeta(stockPerf, indexPerf);
				alpha[i] = Indicateurs.getAlpha(perf[i], indexPerfYr, beta[i]);
				vol[i] = Indicateurs.periodicalVolatility(stockPricesC, nbWeeksPeriode[i]);
				perfRelative[i] = perf[i] - indexPerfYr;
			}
		}
		settError(Indicateurs.trackingError(perfRelative));
		if ((stockPerf!=null)&&(indexPerf!=null))
			setiRation(Indicateurs.ratioInformation(stockPerf, indexPerf, tError));

	}

	private void initIndicatorsLength() {
		int size = 0;
		int iteration = indexPerdiode.length - 1;
		while (size == 0 && iteration > 0) {
			if (indexPerdiode[iteration] >= 0) {
				size = iteration + 1;
				iteration -= 1;
			}
		}
		alpha = new double[size];
		beta = new double[size];
		vol = new double[size];
		perf = new double[size];
		perfRelative = new double[size];
	}

	private void timeIndexes() {
		// 3M 6M 1Y
		nbWeeksPeriode = new int[NB_TIME_PERIODS];
		nbWeeksPeriode[2] = NB_WEEKS_YR;
		nbWeeksPeriode[1] = NB_WEEKS_YR / 2;
		nbWeeksPeriode[0] = NB_WEEKS_YR / 4;

		indexPerdiode = new int[NB_TIME_PERIODS];
		indexPerdiode[2] = pricesArray.length - nbWeeksPeriode[2];
		indexPerdiode[1] = pricesArray.length - nbWeeksPeriode[1];
		indexPerdiode[0] = pricesArray.length - nbWeeksPeriode[0];

	}

	public LinkedHashMap<Date, Double> getPrices() {
		return prices;
	}

	public void setPrices(LinkedHashMap<Date, Double> prices) {
		this.prices = prices;
	}

	public LinkedHashMap<Date, Double> getBenchPrices() {
		return benchPrices;
	}

	public void setBenchPrices(LinkedHashMap<Date, Double> benchPrices) {
		this.benchPrices = benchPrices;
	}

	public double[] getAlpha() {
		return alpha;
	}

	public void setAlpha(double[] alpha) {
		this.alpha = alpha;
	}

	public double[] getBeta() {
		return beta;
	}

	public void setBeta(double[] beta) {
		this.beta = beta;
	}

	public double[] getVol() {
		return vol;
	}

	public void setVol(double[] vol) {
		this.vol = vol;
	}

	public double[] getPerf() {
		return perf;
	}

	public void setPerf(double[] perf) {
		this.perf = perf;
	}

	public double[] getPerfRelative() {
		return perfRelative;
	}

	public void setPerfRelative(double[] perfRelative) {
		this.perfRelative = perfRelative;
	}

	public double gettError() {
		return tError;
	}

	public void settError(double tError) {
		this.tError = tError;
	}

	public double getiRation() {
		return iRation;
	}

	public void setiRation(double iRation) {
		this.iRation = iRation;
	}

	public String getBenchID() {
		return benchID;
	}

	public void setBenchID(String benchID) {
		this.benchID = benchID;
	}

	public String getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public LinkedHashMap<Date, Double> getRawPrices() {
		return rawPrices;
	}

	public void setRawPrices(LinkedHashMap<Date, Double> rwPrices) {
		this.rawPrices = rwPrices;
	}

	public LinkedHashMap<Date, Double> getRawBenchPrices() {
		return rawBenchPrices;
	}

	public void setRawBenchPrices(LinkedHashMap<Date, Double> rwBenchPrices) {
		this.rawBenchPrices = rwBenchPrices;
	}

	public Double[] getPricesArray() {
		return pricesArray;
	}

	public void setPricesArray(Double[] pricesArray) {
		this.pricesArray = pricesArray;
	}

	public Double[] getBenchPricesArray() {
		return benchPricesArray;
	}

	public void setBenchPricesArray(Double[] benchPricesArray) {
		this.benchPricesArray = benchPricesArray;
	}

	@Override
	public String toString() {
		return "Stock [benchmark=" + benchmark + ", name=" + name + ", alpha=" + Arrays.toString(alpha) + ", beta="
				+ Arrays.toString(beta) + ", vol=" + Arrays.toString(vol) + ", perf=" + Arrays.toString(perf)
				+ ", prices=" + prices + "\n, benchPrices=" + benchPrices + "\n, te=" + tError + ", ir=" + iRation
				+ "]";
	}

	public int[] getIndexPerdiode() {
		return indexPerdiode;
	}

	public void setIndexPerdiode(int[] indexPerdiode) {
		this.indexPerdiode = indexPerdiode;
	}

	public int[] getNbWeeksPeriode() {
		return nbWeeksPeriode;
	}

	public void setNbWeeksPeriode(int[] nbWeeksPeriode) {
		this.nbWeeksPeriode = nbWeeksPeriode;
	}

}
