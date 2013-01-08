
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



package pfc.Activitats;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import pfc.Parser.Dades;

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
    
    /////////////////////
    public int cols2;
    public int rows2;
    public boolean inverse;
    public ArrayList<Integer> ids;
    /////////////////////
    
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
	
	public ArrayList<String> imatges;
	public Vector<String> celes;
	
	public String fitxer;
	
	public InputStream is;
	public URL url;
	public File file;
	public String path;
	public ArrayList<Dades.Info> InfoArray;
	
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
    
    public int tempsMax;
	public boolean timeCutdown;
	public boolean intentCutdown;
	public int intentMax;
	public int cMaxHor;
	public int cMaxVert;
}
