/*
* This file is part of DroidClic
*
* DroidClic is copyright 2012 by
* 	Marc Alier Forment,
* 	Maria José Casany Guerrero,
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
* 	LEGORBURU CLADERA, IÑIGO
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

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * A SearchResult represents the response from the server for a given query.
 * Aside from the Clics themselves, it also contains information regarding the
 * pagination of the results. One SearchResult represents a single page.
 * 
 * @author Albert
 */
public final class SearchResult {

	private static final String EMPTY_PAGE = "The page is empty";
	private static final String MALFORMED_RESPONSE = "The response obtained from the server is malformed";
	private static final String INDEX_ERROR = "A result with this index doesn't exist";
	private static final String RESULT_TAG = "result";
	private static final String PAGES_TAG = "pages";
	private static final String TOTAL_PAGES_TAG = "last";
	private static final String CURRENT_PAGE_TAG = "current";
	private static final String TOTAL_RESULTS_TAG = "result_count";
	private static final String PAGINATION_TAG = "paging";
	private static final String CLICS_TAG = "clics";

	private final ClicMetaData[] results;
	private final int totalPages;
	private final int currentPage;
	private final int totalResults;

	/**
	 * Constructs a SearchResult from the server response
	 * 
	 * @param xml
	 *            byte array representation of the served xml
	 * @throws SAXException
	 *             malformed xml
	 */
	SearchResult(byte[] xml) throws SAXException {

		DocumentBuilder builder;
		Document response;

		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			response = builder.parse(new ByteArrayInputStream(xml));

			Element result = (Element) response.getElementsByTagName(RESULT_TAG).item(0);
			NodeList items = result.getElementsByTagName(CLICS_TAG).item(0).getChildNodes();

			// clics
			results = new ClicMetaData[items.getLength()];

			for (int i = 0; i < results.length; i++) {
				results[i] = new ClicMetaData((Element) items.item(i));
			}

			// pagination related stuff
			Element paginationNode = (Element) result.getElementsByTagName(PAGINATION_TAG).item(0);
			totalResults = Integer.parseInt(paginationNode.getElementsByTagName(TOTAL_RESULTS_TAG).item(0)
					.getFirstChild().getNodeValue());
			Element pagesNode = ((Element) paginationNode.getElementsByTagName(PAGES_TAG).item(0));
			totalPages = Integer.parseInt(pagesNode.getElementsByTagName(TOTAL_PAGES_TAG).item(0).getFirstChild()
					.getNodeValue());
			currentPage = Integer.parseInt(pagesNode.getElementsByTagName(CURRENT_PAGE_TAG).item(0).getFirstChild()
					.getNodeValue());

		} catch (SAXException e) {
			throw new SAXException(MALFORMED_RESPONSE);
		} catch (IOException e) {
			throw new SAXException(MALFORMED_RESPONSE);
		} catch (ParserConfigurationException e) {
			throw new SAXException(MALFORMED_RESPONSE);
		} catch (NegativeArraySizeException e) {
			throw new IllegalArgumentException(EMPTY_PAGE);
		}
	}

	/**
	 * 
	 * @return The results in this page as an Integer
	 */
	public int getResultsLength() {
		return results.length;
	}

	public ClicMetaData getResult(int index) {
		if (index >= getResultsLength()) {
			throw new IndexOutOfBoundsException(INDEX_ERROR);
		}
		return results[index];
	}

	public int getTotalPages() {
		return totalPages + 1;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 
	 * @return The total results across all the pages
	 */
	public int getTotalResults() {
		return totalResults;
	}

}