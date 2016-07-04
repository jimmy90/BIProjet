package dauphine.stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.TreeSet;

public class DataCollector {

	public DataCollector() {
		// TODO Auto-generated constructor stub
	}

	public static int getDay(Date d){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int getMonth(Date d){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar.get(Calendar.MONTH);
	}
	
	public static int getYear(Date d){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar.get(Calendar.YEAR);
	}
	
	public static Date addDays(Date date, int days)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
	
	public class DateComparator implements Comparator<Date> {

		@Override
		public int compare(Date o1, Date o2) {
			return o1.compareTo(o2);
		}
	}
	
	public static LinkedHashMap<Date, Double> getWeeklyData(LinkedHashMap<Date, Double> pricesData, Date startDate,
			Date endDate) {

		Date weekDate = (Date) startDate.clone();

		Iterator<Date> dataDatesIterator = new TreeSet<Date>(pricesData.keySet()).iterator();

		Date dataDate = dataDatesIterator.next();
		Date tempDataDate = dataDate;

		LinkedHashMap<Date, Double> weeklyPricesData = new LinkedHashMap<Date, Double>();

		while (weekDate.before(endDate)) {

			while (dataDatesIterator.hasNext() && (dataDate.before(weekDate) || dataDate.equals(weekDate))) {
				tempDataDate = dataDate;
				dataDate = dataDatesIterator.next();
			}
			weeklyPricesData.put(weekDate, pricesData.get(tempDataDate));
			weekDate = addDays(weekDate, 7);
		}

		return weeklyPricesData;
	}

	public static Double[] getValuesArray(LinkedHashMap<Date, Double> pricesData) {
		return pricesData.values().toArray(new Double[pricesData.size()]);
	}
	
	public static LinkedHashMap <Date,Double> getDataFromYahooFinance(String yahoo, Date start, Date end) {
		URL url = null;
		String inputLine;
        String[] lineArray;
        BufferedReader in;
        LinkedHashMap <Date,Double> dataLink = new LinkedHashMap <Date,Double>();
		try {
			url = new URL("http://ichart.finance.yahoo.com/table.csv?s="+yahoo+"&a="+getMonth(start)+
					"&b="+getDay(start)+"&c="+ getYear(start) +"&d="+getMonth(end)+
					"&e="+getDay(end)+"&f="+getYear(end)+"&g=d&ignore=.csv");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			in = new BufferedReader(new InputStreamReader(url.openStream()));        
			in.readLine();
	        while ((inputLine = in.readLine()) != null){
	        	lineArray = inputLine.split(",");
	        	Date parsedDate = formatter.parse(lineArray[0]);
	        	dataLink.put(parsedDate, Double.parseDouble(lineArray[lineArray.length-1]));
	        }
	        in.close();
	        
		} catch (IOException | ParseException e) {			
			e.printStackTrace();
		} 
		return dataLink;
	}

}
