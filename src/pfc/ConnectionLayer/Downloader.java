package pfc.ConnectionLayer;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
/**
 * The Downloader class contains the code that directly
 * 	manages the connection with the server.
 * @author Albert Cabré Juan
 */
public final class Downloader {
	
	/**
	 * Gets whatever resource there is at the provided URL
	 * and returns it in a byte array.
	 * @param url The URL where the resource is located.
	 * @return A byte array that contains the downloaded resource
	 * @throws IOException Connectivity problems
	 */
	public static byte[] downloadFile(URL url) throws IOException {
		InputStream in = new BufferedInputStream(url.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while ((n = in.read(buf)) != -1) {
			out.write(buf, 0, n);
		}
		out.close();
		in.close();
		return out.toByteArray();
	}

}
