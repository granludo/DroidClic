package pfc.ConnectionLayer;


import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.xml.sax.SAXException;

/**
 * The ServerAPI class provides an interface to easily get information from the
 * server.
 * 
 * @author Albert Cabré Juan
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
	public static SearchResult getAll(int page) throws SAXException, IOException {
		return callServer(SERVER_URL + "?page=" + Integer.toString(page) + CLICS_PER_PAGE);

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
	// search params can be "title", "author", "age", "language", "thematic", "link",
	// "license", "other_license" and "license_link"
	public static SearchResult search(Map<String, String> params, int page) throws IOException, SAXException {
		String url = SERVER_URL + "?";
		for (Map.Entry<String, String> param : params.entrySet()) {
			url = url + "data[" + param.getKey() + "]=" + param.getValue() + "&";
		}
		url = url + CLICS_PER_PAGE;
		return callServer(url);
	}

	private static SearchResult callServer(String s) throws IOException, SAXException {
		URL url = new URL(s);
		byte[] response = Downloader.downloadFile(url);
		return new SearchResult(response);
	}
}