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