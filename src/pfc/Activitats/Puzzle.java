/*
 * @Projecte: JClic per gPhone
 * @Autora: Miriam Pujol Benet
 * @Versio: Juny 2009
 */

package pfc.Activitats;

import java.util.Random;
import java.util.Vector;

import pfc.Jclic.Jclic;
import pfc.Jclic.R;
import pfc.Parser.Parser;
import pfc.Parser.XMLConstants;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Puzzle extends Activity{
	
	private Constants CO = Constants.getInstance();
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
		CO.mostrarSolucio = false;
		CO.buidaVisible = false;
	    
	    //Mirem la seguent activitat de quin tipus es per saber on anar
	    
		if(Parser.getActivitats().size() == CO.activitatActual+1){
			String missatge = "Ja no queden més activitats.";
			Toast missatgeTemporal = Toast.makeText(this, missatge,Toast.LENGTH_LONG);
	        missatgeTemporal.setGravity(Gravity.CENTER, 0, 0);
	        missatgeTemporal.show();
			
			Intent i = new Intent(this, Jclic.class);
	    	finish();
	    	startActivity(i);
		} else {
			if(Parser.getActivitats().elementAt(CO.activitatActual+1).
		    		getClas().equals(XMLConstants.EXCHANGEPUZZ)){
	    		
		    	//Tenim un ExchangePuzzle
		    	agafarDadesParser();
				posarNegrePantalla();
				
				Intent i = new Intent(this, ExchangePuzzle.class);
		    	startActivity(i);
		    	finish();
		    } else if(Parser.getActivitats().elementAt(CO.activitatActual+1).
		    		getClas().equals(XMLConstants.HOLEPUZZ)) {
		    	//Tenim un HolePuzzle
		    	agafarDadesParser();
		    	posarNegrePantalla();
	    	
				Intent i = new Intent(this, HolePuzzle.class);
				startActivity(i);
				finish();
		    } else if(Parser.getActivitats().elementAt(CO.activitatActual+1).
		    		getClas().equals(XMLConstants.DOUBLEPUZZ)) {
		    	//Tenim un DoublePuzzle
		    	agafarDadesParser();
				posarNegrePantalla();
				
		    	Intent i = new Intent(this, DoublePuzzle.class);
		    	finish();
		    	startActivity(i);
		    }
		}
	}
	
	private void agafarDadesParser(){		
		if(CO.activitatActual < Parser.getActivitats().size()-1){
			//podem agafar l'activitat
			CO.activitatActual++;
			CO.solucioVisible = false;
			
			CO.rows = Parser.getActivitats().elementAt(CO.activitatActual).getCellRows();
			CO.cols = Parser.getActivitats().elementAt(CO.activitatActual).getCellCols();
			CO.colorBG = Parser.getActivitats().elementAt(CO.activitatActual).getColorBG();
			CO.colorFG = Parser.getActivitats().elementAt(CO.activitatActual).getColorFG();
			CO.mostrarSolucio = Parser.getActivitats().elementAt(CO.activitatActual).getMostrarSolucio();
			CO.imatge = Parser.getActivitats().elementAt(CO.activitatActual).getImage();
			
			if(CO.imatge != null){
				//hi ha una imatge, pel que numero les caselles de 0 a N
				CO.sortida = new Vector<String>();
				
				for(int i = 0; i < CO.rows * CO.cols; i++)
					CO.sortida.add(String.valueOf(i));
				
			} else CO.sortida = new Vector<String>(Parser.getActivitats().elementAt(CO.activitatActual).getCeles());
			
			CO.casIni = CO.sortida.size();
			if(CO.casIni > 20) CO.casIni = 20;
			CO.correcte = 0;
			CO.incorrecte = 0;
			CO.entrada = new Vector<String>();
			CO.vecBool = new Vector<Boolean>();
			CO.vecCaselles = new Vector<TextView>();
			CO.vecCasellesSort = new Vector<TextView>();
			
			crearRandom();
			redefinirEntSort();
		} else{
			Dialog finalitzat = new AlertDialog.Builder(Puzzle.this)
	        .setIcon(R.drawable.jclic_aqua)
	        .setTitle("Atenció!")
	        .setPositiveButton("D'acord", null)
	        .setMessage("Ja no queden més activitats.")
	        .create();
			finalitzat.show();
		}
	}
	
	private void crearRandom(){
		//Inici inicialitzacio vectors
		
		for(int i = 0; i < CO.sortida.size(); i++){
			CO.vecBool.add(i, false);
			CO.entrada.add(i, "");
		}
		//Fi inicialitzacio vectors
        
        Random r = new Random();
        int rand = 0;
        
        if(Parser.getActivitats().elementAt(CO.activitatActual).
	    		getClas().equals(XMLConstants.HOLEPUZZ) && 
	    		(CO.rows == 1 || CO.cols == 1)){
        	//es un Hole Puzzle i nomes te una fila o columna
        	
        	for(int i = 0; i < CO.entrada.size(); i++){
    			CO.entrada.set(i,CO.sortida.elementAt(i));
    		}
        	
        } else {
        	//es qualsevol puzzle o be Hole Puzzle amb menys d'una columna o fila
        	for(int i = 0; i < CO.entrada.size(); i++){
            	/*
            	 * entrada[i] = CO.sortida[rand]
            	 * vecBool ens indica si els valors de la CO.sortida estan fets servir,
            	 * de manera que si vecBool[rand]=true, he de buscar la primera posicio
            	 * que valgui false, fent que si arribo al final del vector, torni a 
            	 * comensar el vector per no donar error.
            	 * Quan trobi un on vecBool[X] = false, posar a true i tornar a fer.
            	 */
            	rand = r.nextInt(CO.sortida.size());
            	
            	boolean colocat = false;
        		for(int j = 0; !colocat && j < CO.vecBool.size(); j++){
        			if(!CO.vecBool.elementAt(j+rand)){
                		//ess vecBool[j+rand] = false (no s'ha fet servir)
        				CO.vecBool.set(j+rand, true);
        				CO.entrada.set(i,CO.sortida.elementAt(j+rand));
                		colocat = true;
                	}
        			else if((j+rand) == CO.vecBool.size()-1) rand = -(j+1);
        				//-(j+1) per passar a la posicio 0 a la seguent iteracio
        		}
            }
        }
	}
	
	private void redefinirEntSort(){
		int caselles = CO.entrada.size();
		int filaActual = 0;

		//poso buides les caselles que no han de tenir res a CO.entrada i CO.sortida
		if(CO.cols == 1){
			//columnes 2, 3 i 4 buides
			for(int i = CO.cols; i < caselles || filaActual < CO.rows; i = i+4){
				filaActual = i/4;
				if(filaActual*4 < i) filaActual++;
				
				CO.entrada.add(i,null);
				CO.entrada.add(i+1,null);
				CO.entrada.add(i+2,null);
				CO.sortida.add(i,null);
				CO.sortida.add(i+1,null);
				CO.sortida.add(i+2,null);
			}			
		} else if(CO.cols == 2){
			//columnes 3 i 4 buides
			for(int i = CO.cols; i < caselles || filaActual < CO.rows; i = i+4){
				filaActual = i/4;
				if(filaActual*4 < i) filaActual++;
				
				if(CO.entrada.size() >i && CO.entrada.elementAt(i) != null){
					CO.entrada.add(i,null);
					CO.entrada.add(i+1,null);
					CO.sortida.add(i,null);
					CO.sortida.add(i+1,null);
				}
			}
		} else if(CO.cols == 3){
			//columna 4 buida
			for(int i = CO.cols; i < caselles || filaActual < CO.rows; i = i+4){
				filaActual = i/4;
				if(filaActual*4 < i) filaActual++;
				
				CO.entrada.add(i,null);
				CO.sortida.add(i,null);
			}
		}
	}
	
	static int agafarColor(String color){
		int c = 0;
		if (color.startsWith("0x")) {
        	c = Integer.parseInt(color.substring(2), 16);
        	c |= 0xff000000;
        }
        else Log.d("Error","Color no comença per 0x!");
		return c;
	}
	
	private void posarNegrePantalla(){
		if(CO.vecCaselles != null){
    		for(int j = 0; j < CO.vecCaselles.size(); j++){
	    		if(CO.vecCaselles.elementAt(j) != null) 
	    			CO.vecCaselles.elementAt(j).setVisibility(View.INVISIBLE);
	    	}
    	}
		if(CO.vecCasellesSort != null){
    		for(int j = 0; j < CO.vecCasellesSort.size(); j++){
	    		if(CO.vecCasellesSort.elementAt(j) != null) 
	    			CO.vecCasellesSort.elementAt(j).setVisibility(View.INVISIBLE);
	    	}
    	}
    	if(CO.miss != null) CO.miss.setVisibility(View.INVISIBLE);
    	if(CO.missCorrectes != null) CO.missCorrectes.setVisibility(View.INVISIBLE);
    	if(CO.cas1 != null) CO.cas1.setVisibility(View.INVISIBLE);
    	if(CO.name != null) CO.name.setVisibility(View.INVISIBLE);
	}
}
