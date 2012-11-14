/*
 * @Projecte: JClic per gPhone
 * @Autora: Miriam Pujol Benet
 * @Versio: Juny 2009
 */

package pfc.Activitats;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class Constants {
	public TextView pos1;
    public TextView pos2;
    public TextView pos3;
    public TextView pos4;
    public TextView pos5;
    public TextView pos6;
    public TextView pos7;
    public TextView pos8;
    public TextView pos9;
    public TextView pos10;
    public TextView pos11;
    public TextView pos12;
    public TextView pos13;
    public TextView pos14;
    public TextView pos15;
    public TextView pos16;
    public TextView pos17;
    public TextView pos18;
    public TextView pos19;
    public TextView pos20;
    public TextView pos21;
    public TextView pos22;
    public TextView pos23;
    public TextView pos24;
    public TextView pos25;
    public TextView pos26;
    public TextView pos27;
    public TextView pos28;
    public TextView pos29;
    public TextView pos30;
    public TextView pos31;
    public TextView pos32;
    public TextView pos33;
    public TextView pos34;
    public TextView pos35;
    public TextView pos36;
    public TextView pos37;
    public TextView pos38;
    public TextView pos39;
    public TextView pos40;
    
    public TextView miss;
    public TextView miss2;
    public TextView missCorrectes;
    public TextView missCorrectes2;
    public TextView cas1;
    public TextView cas2;
    public TextView name;
    
    public int correcte;
    public int incorrecte;
    
    public TableRow tr1;
    public TableRow tr2;
    public TableRow tr3;
    public TableRow tr4;
    public TableRow tr5;
    public TableRow tr6;
    public TableRow tr7;
    public TableRow tr8;
    public TableRow tr9;
    public TableRow tr10;
    public TableLayout tl;
    public TableLayout tl2;
    public ScrollView sv;
    public ImageView bup;
    public ImageView bdown;
        
    public String p1 = "<buit>";
    public String p2 = "<buit>";
    
    public Vector<String> entrada;
    public Vector<String> sortida;
    public Vector<Boolean> vecBool;
    public Vector<TextView> vecCaselles;
    public Vector<TextView> vecCasellesSort;
    public Vector<CharSequence> vecActual;
    
    public int maxCols = 4;
    public int maxRows = 5;
    public int cols;
    public int rows;
    public int casIni;
    public int activitatActual;
    
    public String colorBG;
    public String colorFG;
    public int fg;
    public int bg;
    
    public Menu menu;
    
    public boolean mostrarSolucio;
	public boolean solucioVisible;
	public boolean buidaVisible;
	public boolean exemple;
	
	public String imatge;
	public String fitxer;
	
	public InputStream is;
	public URL url;
	public File file;
	public String path;
	
	private static Constants INSTANCE = null;
	
	public Constants(){
	}
    
    private synchronized static void createInstance() 
    {
        if(INSTANCE == null) INSTANCE = new Constants();
    }
 
    public static Constants getInstance()
    {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
  	}
}
