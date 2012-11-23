package pfc.Activitats;

import java.util.ArrayList;
import java.util.Vector;

import pfc.Jclic.R;
import pfc.Parser.Parser;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

@TargetApi(3) 
public class SimpleAssociation extends Activity{
	private Constants CO = Constants.getInstance();
	private String path = "/sdcard/tmp/jclic/";
	
	Sounds sound;
	private int maxTime = Parser.getActivitats().get(CO.activitatActual).getTempsMax();
	private int maxIntents =  Parser.getActivitats().get(CO.activitatActual).getIntentMax();
	private boolean TimeCountDown =  Parser.getActivitats().get(CO.activitatActual).getTimeCutDown();
	private boolean IntentCountDown =  Parser.getActivitats().get(CO.activitatActual).getIntentCutdown();
	private ArrayList<Integer> idPos = new ArrayList<Integer>();
	
	int contador = 0; //Comptador per als intents.
	int contadorTemps = 0; //Comptador per al temps.
	private CountDownTimer timer;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.simple_association); 
	    //TODO: posar SetRequestedOrientation(asdfsdfdf);

	    //aqu� s'inicialitza el so
	    sound = new Sounds(getApplicationContext());
	    
	    try{	
	    	//agafarDadesParser();		

		    idPos.add(R.id.pos1);
		    idPos.add(R.id.pos2);
		    idPos.add(R.id.pos3);
		    idPos.add(R.id.pos4);
		    idPos.add(R.id.pos5);
		    idPos.add(R.id.pos6);
		    idPos.add(R.id.pos7);
		    idPos.add(R.id.pos8);
		    idPos.add(R.id.pos9);
		    idPos.add(R.id.pos10);
		    idPos.add(R.id.pos11);
		    idPos.add(R.id.pos12);
		    idPos.add(R.id.pos13);
		    idPos.add(R.id.pos14);
		    idPos.add(R.id.pos15);
		    idPos.add(R.id.pos16);
		    idPos.add(R.id.pos17);
		    idPos.add(R.id.pos18);
		    idPos.add(R.id.pos19);
		    idPos.add(R.id.pos20);
		    
		    sound.playStart();
		    
		    if(maxTime != 0){
		    	timer = new CountDownTimer(maxTime*1000, 1000){
		    		@Override
					public void onFinish() {
						contadorTemps++;
						//setMissatges();						
					}
					@Override
					public void onTick(long arg0) {
						contadorTemps++;
						//setMissatges();							
					}				    
			    }.start();
		    }
		    //fer les dos quadricules
		    initQuadricules();
		    
		    //inicialitzar onClickslisteners
		    
		    //listeners de menus
	    } catch(Exception e){
	    	Log.d("Error", "catch SimpleAssociation: "+e);
	    }
	}	
	
	private void initQuadricules() {
		// TODO Auto-generated method stub


		for (int i = 0; i < CO.cols*CO.rows; ++i) {
			BitmapDrawable img = new BitmapDrawable("/mnt"+path+CO.imatges.get(i));
			
			TextView tmp = (TextView) findViewById(idPos.get(i));
			CO.poss.add(tmp);
			CO.poss.get(i).setBackgroundDrawable(img);
		}

	}

	private void agafarDadesParser() {
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
			CO.imatges = Parser.getActivitats().elementAt(CO.activitatActual).getImages();

			
			if(CO.imatges != null){
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

		} else{
			Dialog finalitzat = new AlertDialog.Builder(SimpleAssociation.this)
	        .setIcon(R.drawable.jclic_aqua)
	        .setTitle("Atenció!")
	        .setPositiveButton("D'acord", null)
	        .setMessage("Ja no queden més activitats.")
	        .create();
			finalitzat.show();
		}
	}
	
	//@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
	
		super.onDestroy();
		sound.unloadAll();	
	}
	

}
