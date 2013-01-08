/*
* This file is part of DroidClic
*
* DroidClic is copyright 2012 by
* 	Marc Alier Forment,
* 	Maria Jos� Casany Guerrero,
* 	Enric Mayol
* 
* UPC Students involved in this project:
* 
* Previous version and legacy code:
* ---------------------------------
* 	PUJOL BENET, MIRIAM
*
*
* Project management
* ------------------
*	ALMA SERRANO, ALBERT
* 	CLAVER ARGUDO, MARIA
*	JIMENEZ TARRES, VICTOR
*	CORCHADO MERINO, JUAN CARLOS
* 	JUAN JANE, ANDREU
*	MENES ROUCO, MARTIN
*	ORTEGA GOMEZ, CRISTIAN
*	PURCET SOTO, SERGI
*	RAMOS GONZALEZ, RICARDO
* 	SOLE MORERA, DANIEL
*
*
* Research & support
* --------------------
*	ALBALATE FERNANDEZ, AIDA
* 	CABRE JUAN, ALBERT
* 	CANDON ARENAS, HECTOR
*	ELBAILE SERRA, ABEL
* 	GONZALEZ DE PABLO, BORJA
*	IGLESIAS LOPEZ, OSCAR
* 	MARTINEZ LOPEZ, SERGIO
*	PEREZ PLANAS, ORIAC
*	SANCHEZ MARCOS, IVAN
* 	TORNE GOZALBO, ORIOL
*
*
* Development
* -----------
* Lead developers
*	ALBALATE FERNANDEZ, AIDA
*	COSTA MANSILLA, GERARD
* 	GONZALEZ DE PABLO, BORJA
* Developers:
* 	ALEMANY FONT, ALBERT
* 	ALVAREZ JUSTE, XAVIER
* 	ALVAREZ MORALES, FERRAN
* 	BARRERO MARTINEZ, LINDSAY
* 	BENITEZ VALLS, ALBERT
* 	BERRUEZO MARTINEZ, DAVID
*	BRAMON DEVANT, MARC
* 	BRIGUELLI DA SILVA, LUIS FERNANDO
* 	CABRE JUAN, ALBERT
* 	CANDON ARENAS, HECTOR
* 	CAPEL CATALAN, VICTOR
*	CLAVER ARGUDO, MARIA
*	DE PAULA DE PUIG GUIXE, FRANCESC
* 	DIEZ RUIZ, ALBERT
*	ELBAILE SERRA, ABEL
* 	FARRE GONZALEZ, PAU
*	GARCIA GARCIA, XAVIER
* 	HURTADO OBIOLS, CRISTINA
* 	MARTINEZ DIAZ, ARTURO
* 	MARTINEZ LOPEZ, SERGIO
*	MENES ROUCO, MARTIN
* 	MONTSERRAT GARCIA, EDUARD
* 	ORTIZ GRIMAU, XAVIER
* 	OSORIO ALVAREZ, DAVID
*	PASCUAL VAZQUEZ, PABLO
* 	PEDRAZA GUTIERREZ, M. MERCEDES
* 	PEREZ PLANAS, ORIAC
* 	RODRIGUEZ TORRES, MIREIA
* 	SANCHEZ MARCOS, IVAN
*	SEGARRA RODA, EDUARD
*	SELLES FEITO, MANEL
*	SOLER PASCUAL, GERARD
*	SUBIRATS SALVANS, JOAN
*
*
* Design & usability
* --------------------
* Lead designer:
* 	LEGORBURU CLADERA, I�IGO
* Designers:
* 	OTAL RODRIGUEZ, DANIEL
*	PASCUAL VAZQUEZ, PABLO
*	SEGARRA RODA, EDUARD
*	SOLER PASCUAL, GERARD
*	SUBIRATS SALVANS, JOAN
* 	VIDAL PASTALLE, MARIA
*
*
* Testing, evaluation & audit
* ---------------------------
* Lead tester:
* 	NAVARRO JIMENEZ, GERMAN
*	ALEMANY FONT, ALBERT
* Testers:
*	ALVAREZ MORALES, FERRAN
*	BENITEZ VALLS, ALBERT
* 	CAPEL CATALAN, VICTOR
*	MONTSERRAT GARCIA, EDUARD
* 	ORTIZ GRIMAU, XAVIER
* 	SANCHEZ CORREDOR, MONTSERRAT
*
*
* Documentation, communication & broadcast
* ----------------------------------------
* Lead documentator:
*	ALVAREZ JUSTE, XAVIER
*	SANCHEZ CORREDOR, MONTSERRAT
* Documentators:
*	BARRERO MARTINEZ, LINDSAY
* 	GARCIA GARCIA, XAVIER
*	NAVARRO JIMENEZ, GERMAN
*	OSORIO ALVAREZ, DAVID
*	TORNE GOZALBO, ORIOL
*
* 
* DroidClic is copyright 2012 by
* Universitat Politecnica de Catalunya http://www.upc.edu
* Contact info:
* Marc Alier Forment granludo @ gmail.com or marc.alier @ upc.edu 
*
* DroiClic is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Droidlic is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with DroidClic. If not, see <http://www.gnu.org/licenses/>.
*
* DroidClic is based on the Software JClic by Francesc Busquets
* http://clic.xtec.cat/es/jclic/ 
*
*/




package pfc.ConnectionLayer;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.xml.sax.SAXException;

/**
 * The ServerAPI class provides an interface to easily get information from the
 * server.
 * 
 * @author Albert Cabr� Juan
 */
public final class ServerAPI {

	// url for the default index without params query
	private static final String SERVER_URL = "http://clic.sushitos.org/js-api/clic.xml";
	// clics per page parameter
	private static final String CLICS_PER_PAGE = "&clicsPerPage=5";

	/**
	 * Returns all the clics of the servers
	 * 
	 * @param page
	 *            Page to be obtained.
	 * @return A SearchResult that represents the page that results from this
	 *         query.
	 * @throws IOException
	 *             Connectivity problems.
	 * @throws SAXException
	 *             Malformed response.
	 * 
	 */
	public static SearchResult getAll(int page) throws SAXException,
			IOException {
		return callServer(SERVER_URL + "?page=" + Integer.toString(page)
				+ CLICS_PER_PAGE);

	}

	/**
	 * Returns all the clics of the servers
	 * 
	 * @param page
	 *            Page to be obtained.
	 * @return A SearchResult that represents the page that results from this
	 *         query.
	 * @throws IOException
	 *             Connectivity problems.
	 * @throws SAXException
	 *             Malformed response.
	 * 
	 */
	public static SearchResult getTopTen() throws IOException, SAXException {
		return callServer(SERVER_URL + "?topten=true");
	}

	/**
	 * 
	 * @param params
	 *            Map that contains all the parameters and their values.
	 * @param page
	 *            Page to be obtained
	 * @return A SearchResult that represents the page that results from this
	 *         query.
	 * @throws IOException
	 *             Connectivity problems.
	 * @throws SAXException
	 *             Malformed response.
	 */
	// search params can be "title", "author", "age", "language", "thematic",
	// "link",
	// "license", "other_license" and "license_link"
	public static SearchResult search(Map<String, String> params, int page)
			throws IOException, SAXException {
		String url = SERVER_URL + "?";
		for (Map.Entry<String, String> param : params.entrySet()) {
			url = url + "data[" + param.getKey() + "]=" + param.getValue()
					+ "&";
		}
		url = url + CLICS_PER_PAGE;
		return callServer(url);
	}

	private static SearchResult callServer(String s) throws IOException,
			SAXException {
		URL url = new URL(s);
		byte[] response = Downloader.downloadFile(url);
		return new SearchResult(response);
	}
}