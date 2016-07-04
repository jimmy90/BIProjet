package dauphine.stock;

import java.util.ArrayList;
import java.util.Date;

public class Fond {

	private ArrayList<Stock> listStock = new ArrayList<Stock>();
	private Date startDate;
	private Date end = new Date();

	public void initData() {
		for (Stock s : listStock) {
			s.setRawPrices(DataCollector.getDataFromYahooFinance(s.getName(), this.startDate, this.end));
			s.setRawBenchPrices(DataCollector.getDataFromYahooFinance(s.getBenchmark(), this.startDate, this.end));
			s.setPrices(DataCollector.getWeeklyData(s.getRawPrices(), this.startDate, this.end));
			s.setBenchPrices(DataCollector.getWeeklyData(s.getRawBenchPrices(), this.startDate, this.end));
			s.setPricesArray(DataCollector.getValuesArray(s.getPrices()));
			s.setBenchPricesArray(DataCollector.getValuesArray(s.getBenchPrices()));
			s.initIndicators();
		}
	}

	public ArrayList<Stock> getListStock() {
		return listStock;
	}

	public void setListStock(ArrayList<Stock> listStock) {
		this.listStock = listStock;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date starDate) {
		this.startDate = starDate;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

}
