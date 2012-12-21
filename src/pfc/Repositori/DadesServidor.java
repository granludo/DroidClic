/*
 * @Projecte: JClic per gPhone
 * @Autora: Miriam Pujol Benet
 * @Versio: Juny 2009
 */

package pfc.Repositori;

public class DadesServidor {
	public static final String SOAP_ACTION_ALL = "GetAllProjects";
	public static final String SOAP_ACTION_SEARCH = "SearchProjects";
	
	public static final String ACTIVITATS = "http://alpi1.tgnwifi.net/projectes/activitats/";
    public static final String NAMESPACE = "http://alpi1.tgnwifi.net/projectes/index.php";
    public static final String URL = "http://alpi1.tgnwifi.net/projectes/index.php?wdsl";
    public static boolean all = true;
    public static String keyword = "";
}
