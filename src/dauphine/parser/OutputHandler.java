package dauphine.parser;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import dauphine.stock.Fond;
import dauphine.stock.Stock;

public class OutputHandler {

	public final String FILE_NAME = "output";
	private final String EXTENSION_FILE_NAME = ".xml";

	public OutputHandler(Fond fonds, String path) {
		// TODO Auto-generated constructor stub

		init(fonds, path);

	}

	private File fileGenerator(Document d, String nomFichier) {
		File f = null;
		try {
			DOMSource docxml = new DOMSource(d);
			f = new File(nomFichier);
			transformerCreation().transform(docxml, new StreamResult(f));
		} catch (TransformerException ex) {
			ex.printStackTrace();
		}
		return f;
	}

	private DocumentBuilder documentBuilderCreation() {
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return db;
	}

	private Transformer transformerCreation() {
		Transformer transformer = null;
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			transformer = factory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		return transformer;
	}

	public void init(Fond fnd, String outputPath) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Document newdoc = documentBuilderCreation().newDocument();
		Element root = newdoc.createElement("data");

		for (Stock stk : fnd.getListStock()) {
			Element stock = newdoc.createElement("stock");
			stock.setAttribute("benchID", stk.getBenchID());
			stock.setAttribute("benchmark", stk.getBenchmark());
			stock.setAttribute("id", stk.getId());
			stock.setAttribute("name", stk.getName());

			Element prices = newdoc.createElement("prices");
			Set<Date> dates = stk.getBenchPrices().keySet();
			for (Date d : dates) {
				Element data = newdoc.createElement("obs");
				data.setAttribute("date", formatter.format(d));
				data.setAttribute("price", String.valueOf(stk.getPrices().get(d)));
				data.setAttribute("priceBenchmark", String.valueOf(stk.getBenchPrices().get(d)));
				prices.appendChild(data);
			}
			stock.appendChild(prices);

			Element indicateurs = newdoc.createElement("indicators");
			for (int i = 0; i < stk.getAlpha().length; i++) {
				String period = new String();
				switch (i) {
				case 0:
					period = "3M";
					break;
				case 1:
					period = "6M";
					break;
				case 2:
					period = "1Y";
					break;
				}

				Element indicateur = newdoc.createElement("indicator");
				indicateur.setAttribute("alpha", String.valueOf(stk.getAlpha()[i]));
				indicateur.setAttribute("beta", String.valueOf(stk.getBeta()[i]));
				indicateur.setAttribute("perfRelative", String.valueOf(stk.getPerfRelative()[i]));
				indicateur.setAttribute("perf", String.valueOf(stk.getPerf()[i]));
				indicateur.setAttribute("period", period);
				indicateur.setAttribute("vol", String.valueOf(stk.getVol()[i]));

				indicateurs.appendChild(indicateur);
			}
			stock.appendChild(indicateurs);

			Element trackingError = newdoc.createElement("trackingError");
			trackingError.setAttribute("te", String.valueOf(stk.gettError()));
			stock.appendChild(trackingError);

			Element informationRation = newdoc.createElement("informationRation");
			informationRation.setAttribute("ir", String.valueOf(stk.getiRation()));
			stock.appendChild(informationRation);

			Element zone = newdoc.createElement("zone");
			zone.setAttribute("zname", stk.getZone());
			Element country = newdoc.createElement("country");
			country.setAttribute("cname", stk.getCountry());
			zone.appendChild(country);
			stock.appendChild(zone);

			Element sector = newdoc.createElement("sector");
			sector.setAttribute("sname", stk.getSector());
			Element industry = newdoc.createElement("industry");
			industry.setAttribute("iname", stk.getIndustry());
			sector.appendChild(industry);
			stock.appendChild(sector);

			root.appendChild(stock);
		}

		newdoc.appendChild(root);
		DateFormat mediumDateFormat = new SimpleDateFormat("yyyyMMdd-HH_mm_ss");
		fileGenerator(newdoc, outputPath + File.separator + FILE_NAME + mediumDateFormat.format(new Date()).toString()
				+ EXTENSION_FILE_NAME);

	}

}
