package pfc.ConnectionLayer;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * The ClicMetaData class represents a Clic with all it's metadata as it's
 * stored on the server.
 * 
 * @author Albert Cabré Juan
 */
public final class ClicMetaData {

	// definition for the xml tags
	private static final String TITLE_TAG = "title";
	private static final String BODY_TAG = "body";
	private static final String AUTHOR_TAG = "author";
	private static final String AGE_TAG = "age";
	private static final String LANGUAGE_TAG = "language";
	private static final String THEME_TAG = "thematic";
	private static final String CLIC_TAG = "path";
	private static final String LICENSE_TAG = "license";
	private static final String IMAGE_TAG = "image";

	private final String title;
	private final String body;
	private final String author;
	private final int age;
	private final String license;
	private final int thematic;
	private final int language;
	private final URL image;
	private final URL clic;
	private final String fileName;

	/**
	 * Creates an instance of a ClicMetaData based on a XML representation of
	 * the Clic and it's metadata.
	 * 
	 * @param xml
	 *            Node representation of the XML
	 * @throws ParserConfigurationException
	 *             Should never be thrown.
	 * @throws SAXException
	 *             The file reached is not valid XML
	 * @throws IOException
	 *             Thrown when connectivity problems appear.
	 * @throws DOMException
	 *             The XML format was unexpected (ex:missing attributes)
	 * @throws UnknownHostException
	 *             Unchecked exception, it's thrown when there's no Internet at
	 *             all (DNS servers unreachable).
	 */
	ClicMetaData(Element item) throws ParserConfigurationException,
			SAXException, IOException, DOMException {

		// String attributes
		title = item.getElementsByTagName(TITLE_TAG).item(0).getFirstChild()
				.getNodeValue();

		Node authorNode = item.getElementsByTagName(AUTHOR_TAG).item(0);
		if (authorNode.hasChildNodes()) {
			author = item.getElementsByTagName(AUTHOR_TAG).item(0)
					.getFirstChild().getNodeValue();
		} else {
			author = null;
		}

		body = item.getElementsByTagName(BODY_TAG).item(0).getFirstChild()
				.getNodeValue();

		Node licenseNode = item.getElementsByTagName(LICENSE_TAG).item(0);
		if (licenseNode.hasChildNodes()) {
			license = item.getElementsByTagName(LICENSE_TAG).item(0)
					.getFirstChild().getNodeValue();
		} else {
			license = null;
		}

		thematic = Integer.parseInt(item.getElementsByTagName(THEME_TAG)
				.item(0).getFirstChild().getNodeValue());
		age = Integer.parseInt(item.getElementsByTagName(AGE_TAG).item(0)
				.getFirstChild().getNodeValue());
		language = Integer.parseInt(item.getElementsByTagName(LANGUAGE_TAG)
				.item(0).getFirstChild().getNodeValue());

		// File attributes, only the URL is stored for later lazy downloading
		image = new URL(item.getElementsByTagName(IMAGE_TAG).item(0)
				.getFirstChild().getNodeValue());
		clic = new URL(item.getElementsByTagName(CLIC_TAG).item(0)
				.getFirstChild().getNodeValue());

		String[] split = item.getElementsByTagName(CLIC_TAG).item(0)
				.getFirstChild().getNodeValue().split("/");
		fileName = split[split.length - 1].split("\\.")[0];

	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public String getAuthor() {
		return author;
	}

	public String getLicense() {
		return license;
	}

	public int getThematic() {
		return thematic;
	}

	public int getAge() {
		return age;
	}

	public int getLanguage() {
		return language;
	}

	/**
	 * Downloads and returns the screenshot.
	 * 
	 * @return The screenshot in a byte array
	 * @throws IOException
	 *             Connectivity problems
	 */
	public byte[] getImage() throws IOException {
		return Downloader.downloadFile(image);
	}

	/**
	 * Downloads and returns the clic.
	 * 
	 * @return The clic in a byte array
	 * @throws IOException
	 *             Connectivity problems
	 */
	public byte[] getClic() throws IOException {
		return Downloader.downloadFile(clic);
	}

	public String getFileName() {
		return fileName;
	}

}
