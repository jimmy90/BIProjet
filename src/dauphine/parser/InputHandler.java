package dauphine.parser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import dauphine.stock.Fond;
import dauphine.stock.Stock;

public class InputHandler extends DefaultHandler {

	private Stock stock = new Stock();
	private Fond fond = new Fond();
	private String currentTag;

	public Fond getFond() {
		return fond;
	}

	public void setFond(Fond fond) {
		this.fond = fond;
	}

	public String getCurrentTag() {
		return currentTag;
	}

	public void setCurrentTag(String currentTag) {
		this.currentTag = currentTag;
	}

	public void startElement(String uri, String ln, String qn, Attributes att) {

		setCurrentTag(ln);
		if (getCurrentTag().equals("input")) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			try {
				fond.setStartDate(formatter.parse(att.getValue("stardate")));

				if (att.getValue("endate") != null) {
					fond.setEnd(formatter.parse(att.getValue("endate")));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (getCurrentTag().equals("stock")) {
			stock.setBenchID(att.getValue("benchID"));
			stock.setBenchmark(att.getValue("benchmark"));
			stock.setCountry(att.getValue("country"));
			stock.setId(att.getValue("id"));
			stock.setIndustry(att.getValue("industry"));
			stock.setName(att.getValue("name"));
			stock.setSector(att.getValue("sector"));
			stock.setZone(att.getValue("zone"));
			fond.getListStock().add(stock);
			stock = null;
			stock = new Stock();
		}
	}

	public static Fond getData(String inputPath) {
		SAXParserFactory sf = SAXParserFactory.newInstance();
		Fond fond = null;
		try {
			sf.setFeature("http://xml.org/sax/features/validation", true);
			sf.setNamespaceAware(true);
			sf.setValidating(true);

			SAXParser parser = sf.newSAXParser();

			InputHandler dh = new InputHandler();
			parser.parse(inputPath, dh);
			fond = dh.getFond();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fond;
	}

}
