package pfc.Activitats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import pfc.Parser.Parser;
import pfc.Descompressor.Descompressor;
import pfc.Jclic.Jclic;
import pfc.Jclic.R;


@TargetApi(8)
public class Memory extends Activity {
	private Constants CO = Constants.getInstance();
	private String path = "/sdcard/tmp/jclic/";
	
	private int newWidth;
	private int newHeight;
	private int width;
	private int height;
	
	int fil, col;
	
	private TextView aciertos2=null;
	private TextView intentos=null;
    private Button bMenu = null;
	private ProgressBar tiempo = null;
	private Vector<BitmapDrawable> vecDraw = null;

	
	private static final int MENU_ANT = 0;
	private static final int MENU_SEG = 1;
	private static final int MENU_SOLUCIO = 2;
	private static final int MENU_AJUDA = 3;
	private static final int MENU_INICI = 4;
	private static final int MENU_SORTIR = 5;
	
	private Vector<TextView> textViews = new Vector<TextView>(CO.cols*CO.rows);
	
	private int maxTime = Parser.getActivitats().get(CO.activitatActual).getTempsMax();
	private int maxIntents = Parser.getActivitats().get(CO.activitatActual).getIntentMax();
	private boolean TimeCountDown = Parser.getActivitats().get(CO.activitatActual).getTimeCutDown();
	private boolean IntentCountDown = Parser.getActivitats().get(CO.activitatActual).getIntentCutdown();
	
	Sounds sound;
//	private ArrayList<Integer> ids = Parser.getActivitats().get(CO.activitatActual).getRelacions();
	private ArrayList<String> imagenes = Parser.getActivitats().get(CO.activitatActual).getImages();
	private Vector<String> textos = Parser.getActivitats().get(CO.activitatActual).getCeles();
	
	private ArrayList<Celda> celdas = new ArrayList<Celda>();
	
	private ArrayList<ArrayList<Integer>> idPos = new ArrayList<ArrayList<Integer>>();
	
	int contadorIntents = 0;
	int contadorTemps = 0;
	int aciertos = 0;
	int fallos = 0;
	int casillasCorrectas = 0;
	int idPar = -1; // id de la casella anterior (possible parella)
	TextView tAnt; // casella seleccionada anterior (possible parella)
	TextView tAnt2; // necess�ria per recordar casella a ocultar al fer un 3r clic
	int sizeNoAlt = CO.cols*CO.rows;
	private CountDownTimer timer;
	private String missatgeInicial = Parser.getActivitats().get(CO.activitatActual).getMissatgeIni();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.panels_identify);
		aciertos2 = (TextView)findViewById(R.id.editAciertos);
	    tiempo = (ProgressBar) findViewById(R.id.progressTime);
	    intentos = (TextView) findViewById(R.id.editIntentos);
	    tiempo.setMax(maxTime);
	    tiempo.setProgress(0);
	    bMenu = (Button) findViewById(R.id.menu);
		sound = new Sounds(getApplicationContext());
		if (maxIntents == 0) maxIntents = -1;
		
		try {
			TextView titulo = (TextView) findViewById(R.id.titulo);
			titulo.setText(missatgeInicial);
	        if(Parser.getActivitats().elementAt(CO.activitatActual).getName() != null)
	        	titulo.setText(Parser.getActivitats().elementAt(CO.activitatActual).getName());
	        else titulo.setText("Activitat JClic");
	        
	        tAnt2 = null; // inicialment no hem seleccionat res
	        tAnt = null;
	        
			
			for(int i = 0; i < sizeNoAlt; ++i) {
				Celda aux = new Celda();
				if(textos.elementAt(i) != "") aux.celda = textos.elementAt(i);
				else{
					if(Descompressor.descompressor(imagenes.get(i), CO.path)) {
						BitmapDrawable img = new BitmapDrawable(path+imagenes.get(i));
					aux.imagen = imagenes.get(i);
					aux.img = img;
					}
				}
				aux.id = i;
				celdas.add(aux);
				if (sizeNoAlt == textos.size()) celdas.add(aux); // tornem a afegir la mateixa cel�la si no ens donen les parelles
			}
			
			if (sizeNoAlt < textos.size()) // si ens donen les parelles, les afegim
			for(int i = sizeNoAlt; i < textos.size(); ++i) {
				Celda aux = new Celda();
				if(textos.elementAt(i) != "") aux.celda =textos.elementAt(i);
				else{
					if(Descompressor.descompressor(imagenes.get(i), CO.path)) {
						BitmapDrawable img = new BitmapDrawable(path+imagenes.get(i));
					aux.imagen = imagenes.get(i);
					aux.img = img;
					}
				}
				aux.id = i-sizeNoAlt;
				celdas.add(aux);
			}

		
			long seed = System.nanoTime();
			Random random = new Random(seed);
			Collections.shuffle(celdas, random);
			
			for(int i = 0; i < sizeNoAlt; i++) casillasCorrectas++;
			
			ArrayList<Integer> row = new ArrayList<Integer>();
		    row.add(R.id.pos1);
		    row.add(R.id.pos2);
		    row.add(R.id.pos3);
		    row.add(R.id.pos4);
		    idPos.add(row);
		    row = new ArrayList<Integer>();
		    row.add(R.id.pos5);
		    row.add(R.id.pos6);
		    row.add(R.id.pos7);
		    row.add(R.id.pos8);
		    idPos.add(row);
		    row = new ArrayList<Integer>();
		    row.add(R.id.pos9);
		    row.add(R.id.pos10);
		    row.add(R.id.pos11);
		    row.add(R.id.pos12);
		    idPos.add(row);
		    row = new ArrayList<Integer>();
		    row.add(R.id.pos13);
		    row.add(R.id.pos14);
		    row.add(R.id.pos15);
		    row.add(R.id.pos16);
		    idPos.add(row);
		    row = new ArrayList<Integer>();
		    row.add(R.id.pos17);
		    row.add(R.id.pos18);
		    row.add(R.id.pos19);
		    row.add(R.id.pos20);
		    idPos.add(row);
				
			reiniciarMenu();
			
			if(TimeCountDown)
				contadorTemps = maxTime;		
			
			if(IntentCountDown)
				contadorIntents = maxIntents;
			
					
			if(maxTime != 0) {
				timer = new CountDownTimer(maxTime*1000, 1000) {
					@Override
					public void onFinish() {
						if(TimeCountDown) { 
							contadorTemps--;
							tiempo.setProgress(contadorTemps);
						}
						else {
							contadorTemps++;
							tiempo.setProgress(contadorTemps);
						}
						sound.playFinished_error();
						finalizarJuego();
					}
					
					@Override
					public void onTick(long arg0) {
						if(TimeCountDown) {
							contadorTemps--;
							tiempo.setProgress(contadorTemps);
						}
						else {
							contadorTemps++;
							tiempo.setProgress(contadorTemps);
						}						
						imprimirInfo();
					}
				}.start();
			}
			
			iniciarCasillas();		
		    sound.playStart();

			imprimirInfo();

			
			//sound.playFinished_error();
			
		} catch(Exception e) {
			Log.d("Error", "catch Memory: "+e);
			e.printStackTrace();
		}
		final Context aC = this;
		bMenu.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Dialog dialog = new Dialog(aC, R.style.Dialog);
				dialog.setContentView(R.layout.menu_clic);
				dialog.setCanceledOnTouchOutside(true);
				dialog.show();
				MenuActivitats ma = new MenuActivitats(timer);
				ma.butsMenu(dialog, aC, vecDraw);
			}
		});	
		
	}
	
	private void iniciarCasillas() {
		
		
		fil = CO.rows;
		col = CO.cols;
		if (col > fil) fil += fil;
		else col += col;

		for(int i = 0; i < fil; ++i) {
			for(int j = 0; j < col; ++j) {
				TextView aux = (TextView) findViewById(idPos.get(i).get(j));
				textViews.add(aux);
			}
		}
		
		for(int i = fil; i < 5; ++i) {
			for(int j = col; j < 4; ++j) {
				TextView aux = (TextView) findViewById(idPos.get(i).get(j));
				
				resizeTextViewsUseless(aux);
			}
		}
		
		for(int i = 0; i < fil; ++i) {
			for(int j = 0; j < col; ++j) {
				TextView aux = textViews.elementAt(col*i+j);
				
				resizeCaselles(aux);
				Celda aux2 = celdas.get(col*i+j);				
				aux.setText(aux2.celda);
				aux.setTextColor(Color.GRAY);
				if (aux2.img != null){
					aux2.img = resizeImg(aux2.img);
					aux.setBackgroundDrawable(aux2.img);
				}
				aux.setBackgroundColor(Color.GRAY);
				aux.setClickable(true);
				aux.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						click(v);
					}
				});
			}
			
		}
		
	}
	
	private void click(View v) {
		
		if (tAnt2 != null){	// si al fer el clic hi havia una parella err�nia a la vista, l'ocultem de nou
			tAnt.setBackgroundColor(Color.GRAY);
			tAnt.setTextColor(Color.GRAY);
			tAnt2.setBackgroundColor(Color.GRAY);
			tAnt2.setTextColor(Color.GRAY);
			tAnt = tAnt2 = null;
		}
				
		TextView t = (TextView) v;
		int id = t.getId();
		for(int i = 0; i < fil; ++i) {
			for(int j = 0; j < col; ++j) {
				if(id == idPos.get(i).get(j)) {
					Celda c = celdas.get(col*i+j);
					if (c.imagen != "") t.setBackgroundDrawable(c.img); 
					else{
						t.setText(c.celda);
						t.setTextColor(Color.BLACK);
						t.setBackgroundColor(Color.WHITE);
					}
					if (idPar == -1){
						idPar = c.id; // si no hi havia una seleccionada abans, agafem l'id de l'actual
						tAnt = t;
					}
					else{
						if (c.id == idPar && t != tAnt){ // si coincideixen id (i no hem clicat sobre ell mateix), hem trobat parella, contem encert
							aciertos++;
							t.setClickable(false);
							tAnt.setClickable(false);
							sound.playAction_ok();
							tAnt = null;
							tAnt2 = null;
						}
						else{
							tAnt2 = t; // guardem la casella per a ocultar-la al pr�xim clic
							sound.playActionError();
						}
						
						idPar = -1;
						
						if(IntentCountDown)
							contadorIntents--;
						else
							contadorIntents++;						
					}
				}
			}
		}
		
		imprimirInfo();
		
		if(aciertos == casillasCorrectas) {
			sound.playFinished_ok();
			finalizarJuego();
		}
		else if(IntentCountDown) {
			if(contadorIntents == 0) {
				sound.playFinished_error();
				finalizarJuego();
			}
		} else if(contadorIntents == maxIntents){
			sound.playFinished_error();
			finalizarJuego();
		}
	}
	
	private BitmapDrawable resizeImg(BitmapDrawable bitmapd) {
		Bitmap bitmapOrg = bitmapd.getBitmap();
		int widthImage = bitmapOrg.getWidth();
		int heightImage = bitmapOrg.getHeight();
		
		newWidth = width*col*2;
		newHeight = height*fil*2;
		
		float scaleWidth = ((float) newWidth) / widthImage;
		float scaleHeight = ((float) newHeight) / heightImage;
		
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg,  0, 0, widthImage, heightImage, matrix, true);
		
		return new BitmapDrawable(resizedBitmap);
	}
	
	private void resizeCaselles(TextView pos) {
		if(col == 1) {
			pos.setWidth(300);
			width = 300;
		} else if(col == 2) {
			pos.setWidth(150);
			width = 150;
		} else if(col == 3) {
			pos.setWidth(100);
			width = 100;
		} else {
			pos.setWidth(75);
			width = 75;
		}
		
		if(fil == 1 || col == 2) {
			pos.setHeight(75);
			pos.setMaxLines(4);
			height = 75;
		} else if(fil == 3) {
			pos.setHeight(50);
			pos.setMaxLines(3);
			height = 50;
		} else if(fil == 4) {
			pos.setHeight(40);
			pos.setMaxLines(2);
			height = 40;
		} else {
			pos.setHeight(30);
			pos.setMaxLines(2);
			height = 30;
		}
		width /= 10;
		height /= 10;
	}
	
	private void resizeTextViewsUseless(TextView pos)
	{
		pos.setWidth(0);
		pos.setHeight(0);
	}
	
	private void imprimirInfo() {
		TextView missIni = (TextView) findViewById(R.id.missatge);
		missIni.setText(missatgeInicial);
		aciertos2.setText(Integer.toString(aciertos));
		intentos.setText(Integer.toString(contadorIntents));

	}
	
	private void reiniciarMenu() {
		if(CO.menu != null) {
			CO.menu.clear();
			CO.menu.add(0, MENU_ANT, 0, R.string.menu_ant);
			CO.menu.add(0, MENU_SEG, 0, R.string.menu_seg);
			CO.menu.add(0, MENU_SOLUCIO, 0, R.string.menu_solucio);
			CO.menu.add(0, MENU_AJUDA, 0, R.string.menu_ajuda);
			CO.menu.add(0, MENU_INICI, 0, R.string.menu_inici);
			CO.menu.add(0, MENU_SORTIR, 0, R.string.menu_sortir);
			
			CO.menu.getItem(MENU_ANT).setIcon(android.R.drawable.ic_media_rew);
			CO.menu.getItem(MENU_SEG).setIcon(android.R.drawable.ic_media_ff);
			CO.menu.getItem(MENU_SOLUCIO).setIcon(android.R.drawable.btn_star_big_off);
			CO.menu.getItem(MENU_AJUDA).setIcon(android.R.drawable.ic_menu_help);
			CO.menu.getItem(MENU_INICI).setIcon(android.R.drawable.ic_menu_revert);
			CO.menu.getItem(MENU_SORTIR).setIcon(android.R.drawable.ic_menu_close_clear_cancel);
			
			if(CO.mostrarSolucio) CO.menu.getItem(MENU_SOLUCIO).setEnabled(true);
			else CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);
			CO.menu.getItem(MENU_SOLUCIO).setTitle(R.string.menu_solucio);
			
			CO.menu.getItem(MENU_SEG).setEnabled(true);
			CO.menu.getItem(MENU_ANT).setEnabled(true);
			
			if(CO.activitatActual<1) {
				CO.menu.getItem(MENU_ANT).setEnabled(false);
			}
			if(CO.activitatActual == Parser.getActivitats().size()-1) {
				CO.menu.getItem(MENU_SEG).setEnabled(false);
			}
		}
	}
	
	private void finalizarJuego()
	{
		for(int i = 0; i < textViews.size(); ++i)
			textViews.get(i).setClickable(false);
		final Context aC = this;
		Dialog dialog = new Dialog(aC, R.style.Dialog);
		dialog.setContentView(R.layout.menu_clic);
		dialog.setCanceledOnTouchOutside(true);
		MenuActivitats ma = new MenuActivitats(timer);
		ma.butsMenu(dialog, aC, vecDraw);
		TextView textFinal = (TextView) dialog.findViewById(R.id.tMenuClic);
		TextView aux = (TextView) findViewById(R.id.cas1);
		TextView aux2 = (TextView) findViewById(R.id.correcte);
		if(aciertos == casillasCorrectas) {
			String s = Parser.getActivitats().get(CO.activitatActual).getMissatgeFi();
			if (s.length() == 0) s = "Molt b�, l'has resolt!";
			textFinal.setText(s);
		}
		else if((contadorIntents == maxIntents && !IntentCountDown) || (contadorIntents == 0 && IntentCountDown)) {
			textFinal.setText("Nombre d'intents superat!");
		}
		else if((contadorTemps == maxTime && !TimeCountDown) || (contadorTemps == 0 && TimeCountDown)) {
			textFinal.setText("S'ha acabat el temps!");
		}

	
		if(maxTime != 0)
			timer.cancel();
		dialog.show();
		aux.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finalClick(v);
			}
		});
		
	}
	
	private void finalClick(View v)
	{
		Intent iSeg = new Intent(this, Puzzle.class);
    	startActivity(iSeg);
		this.finish();
	}
	
	protected void onDestroy() {
		sound.unloadAll();
		super.onDestroy();
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        CO.menu = menu;
        CO.menu.clear();
        CO.menu.add(0, MENU_ANT, 0, R.string.menu_ant);
        CO.menu.add(0, MENU_SEG, 0, R.string.menu_seg);
        CO.menu.add(0, MENU_SOLUCIO, 0, R.string.menu_solucio);
        CO.menu.add(0, MENU_AJUDA, 0, R.string.menu_ajuda);
        CO.menu.add(0, MENU_INICI, 0, R.string.menu_inici);
        CO.menu.add(0, MENU_SORTIR, 0, R.string.menu_sortir);
        
        CO.menu.getItem(MENU_ANT).setIcon(android.R.drawable.ic_media_rew);
		CO.menu.getItem(MENU_SEG).setIcon(android.R.drawable.ic_media_ff);
		CO.menu.getItem(MENU_SOLUCIO).setIcon(android.R.drawable.btn_star_big_off);
		CO.menu.getItem(MENU_AJUDA).setIcon(android.R.drawable.ic_menu_help);
		CO.menu.getItem(MENU_INICI).setIcon(android.R.drawable.ic_menu_revert);
		CO.menu.getItem(MENU_SORTIR).setIcon(android.R.drawable.ic_menu_close_clear_cancel);
		
        //Configuro els botons d'anterior i seguent
        CO.menu.getItem(MENU_SEG).setEnabled(true);
		CO.menu.getItem(MENU_ANT).setEnabled(true);
		
		if(CO.activitatActual<1){
			//estem a la primera activitat, pel que no podem habilitar l'anterior
			CO.menu.getItem(MENU_ANT).setEnabled(false);
		}
		if(CO.activitatActual == Parser.getActivitats().size() - 1){
			//estem a l'ultima activitat, pel que no podem habilitar el seguent
			CO.menu.getItem(MENU_SEG).setEnabled(false);
		}
        
        if(CO.mostrarSolucio) CO.menu.getItem(MENU_SOLUCIO).setEnabled(true);
		else CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);
        return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ANT:
            	CO.activitatActual = CO.activitatActual - 2;
            	Intent iAnt = new Intent(this, Puzzle.class);
            	startActivity(iAnt);
            	finish();
                return true;
            case MENU_SEG:
            	Intent iSeg = new Intent(this, Puzzle.class);
            	startActivity(iSeg);
            	finish();
                return true;
            case MENU_AJUDA:
            	Dialog ajuda = new AlertDialog.Builder(Memory.this)
    	        .setIcon(R.drawable.jclic_aqua)
    	        .setTitle("Ajuda")
    	        .setPositiveButton("D'acord", null)
    	        .setMessage("Col·loca les caselles al seu lloc corresponent del panell de sota.\n" +
    	        		"Pots canviar de panell prement la fletxa, o bé desplaçan-te per la pantalla.")
    	        .create();
            	ajuda.show();
            	return true;
            case MENU_SOLUCIO:
            	/* AIX� no no fEEeEeEEEm :D
            	 * 
            	 * 
            	 * if(!CO.solucioVisible){
            		//Vull mostrar la solucio
            		CO.vecActual = new Vector<CharSequence>();
                	for(int i = 0; i < CO.vecCaselles.size(); i++){
                    	if(CO.vecCaselles.elementAt(i) != null){
                    		CO.vecActual.addElement(CO.vecCaselles.elementAt(i).getText());
                    		CO.vecCaselles.elementAt(i).setText(CO.sortida.elementAt(i));
                    		CO.vecCaselles.elementAt(i).setBackgroundColor(CO.bg);
                    		CO.vecCaselles.elementAt(i).setTextColor(CO.fg);
                    		
                    		if(CO.imatge != null){
            					int indexSort = CO.sortida.indexOf(CO.vecCaselles.elementAt(i).getText());
            		    		
            					CO.vecCaselles.elementAt(i).setBackgroundColor(Color.TRANSPARENT);
            		    		CO.vecCaselles.elementAt(i).setTextColor(Color.TRANSPARENT);
            					
            		    		vecDraw.elementAt(indexSort).setAlpha(250);
            		    		
            		    		CO.vecCaselles.elementAt(i).
            		    			setBackgroundDrawable(vecDraw.elementAt(indexSort));
            				}
                    	} else CO.vecActual.addElement(null);
                    }
                	bloquejarJoc(true);
                	CO.solucioVisible = true;
                	posAgafada = null;
                	setMissatges();
                	CO.menu.getItem(MENU_SOLUCIO).setTitle(R.string.menu_in_solucio);
                	CO.menu.getItem(MENU_ANT).setEnabled(false);
                	CO.menu.getItem(MENU_SEG).setEnabled(false);
            	} else {
            		//Estic mostrant la solucio i vull continuar
            		for(int i = 0; i < CO.vecCaselles.size(); i++){
                    	if(CO.vecCaselles.elementAt(i) != null){
                    		CO.vecCaselles.elementAt(i).setText(CO.vecActual.elementAt(i));
                    		CO.vecCaselles.elementAt(i).setTextColor(CO.fg);
                    		CO.vecCaselles.elementAt(i).setBackgroundColor(CO.bg);
                    		
                    		if(CO.imatge != null){
            					int indexSort = CO.sortida.indexOf(CO.vecCaselles.elementAt(i).getText());
            		    		
            					CO.vecCaselles.elementAt(i).setBackgroundColor(Color.TRANSPARENT);
            		    		CO.vecCaselles.elementAt(i).setTextColor(Color.TRANSPARENT);
            					
            		    		vecDraw.elementAt(indexSort).setAlpha(250);
            		    		
            		    		CO.vecCaselles.elementAt(i).
            		    			setBackgroundDrawable(vecDraw.elementAt(indexSort));
            				}
                    	}
                    }
            		bloquejarJoc(false);
            		CO.solucioVisible = false;
            		setMissatges();
            		CO.menu.getItem(MENU_SOLUCIO).setTitle(R.string.menu_solucio);
            		
            		//Configuracio del menu per ant i seguent
    				if(CO.activitatActual<1){
    					//estem a la primera activitat, pel que nomes habilitem seguent
    					CO.menu.getItem(MENU_SEG).setEnabled(true);
    				} else if(CO.activitatActual == Parser.getActivitats().size() - 1){
    					//estem a l'ultima activitat, pel que no podem habilitar el seguent
    					CO.menu.getItem(MENU_ANT).setEnabled(true);
    				} else {
    					CO.menu.getItem(MENU_SEG).setEnabled(true);
    					CO.menu.getItem(MENU_ANT).setEnabled(true);
    				}
            	}*/
                return true;
            case MENU_SORTIR:
            	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            	builder.setIcon(R.drawable.jclic_aqua);
            	builder.setMessage("Est�s segur de que vols sortir?")
            	       .setCancelable(false)
            	       .setPositiveButton("S�", new DialogInterface.OnClickListener() {
            	           public void onClick(DialogInterface dialog, int id) {
            	                Memory.this.finish();
            	           }
            	       })
            	       .setNegativeButton("No", new DialogInterface.OnClickListener() {
            	           public void onClick(DialogInterface dialog, int id) {
            	                dialog.cancel();
            	           }
            	       });
            	AlertDialog alert = builder.create();
            	alert.show();
                return true;
            case MENU_INICI:
            	Intent i = new Intent(this, Jclic.class);
            	startActivity(i);
            	finish();
            	return true;
        }
        return false;
    }
	
	public class Celda
	{
		String celda;
		String imagen;
		int id;
		BitmapDrawable img;
	//	boolean offset;
		String contenidoAlternativo;
	//	boolean contAltImagen;
		String contAltImagen;
		
		Celda()
		{
			celda = "";
			id = 0;
			img = null;
	//		offset = false;
			contenidoAlternativo = "";
			contAltImagen = "";
			imagen = "";
		}
	}
}
