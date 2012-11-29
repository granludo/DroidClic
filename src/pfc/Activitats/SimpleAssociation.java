package pfc.Activitats;

import java.util.ArrayList;
import java.util.Random;

import pfc.Descompressor.Descompressor;
import pfc.Jclic.R;
import pfc.Parser.Parser;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

@TargetApi(11) 
public class SimpleAssociation extends Activity{
	private Constants CO = Constants.getInstance();
	private String path = "/sdcard/tmp/jclic/";
	
	private int newWidth;
	private int newHeight;
	private int width;
	private int height;
	
	private TextView posAgafada;
	
	Sounds sound;
	private int maxTime = Parser.getActivitats().get(CO.activitatActual).getTempsMax();
	private int maxIntents =  Parser.getActivitats().get(CO.activitatActual).getIntentMax();
	private boolean TimeCountDown =  Parser.getActivitats().get(CO.activitatActual).getTimeCutDown();
	private boolean IntentCountDown =  Parser.getActivitats().get(CO.activitatActual).getIntentCutdown();
	private ArrayList<ArrayList<Integer>> idPos = new ArrayList<ArrayList<Integer>>();
	private TextView seleccionat;
	private ArrayList<Integer> correspondencies;
	
	
	int contador = 0; //Comptador per als intents.
	int contadorTemps = 0; //Comptador per al temps.
	private CountDownTimer timer;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.simple_association); 
	    //TODO: posar SetRequestedOrientation(asdfsdfdf);

	    //aquí s'inicialitza el so
	    sound = new Sounds(getApplicationContext());
	    
	    try{	
	    	//agafarDadesParser();
	    	// agafem les imatges
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
		    
		    // agafem els noms
	    	row = new ArrayList<Integer>();
		    row.add(R.id.pos21);
		    row.add(R.id.pos22);
		    row.add(R.id.pos23);
		    row.add(R.id.pos24);
		    idPos.add(row);
		    row = new ArrayList<Integer>();
		    row.add(R.id.pos25);
		    row.add(R.id.pos26);
		    row.add(R.id.pos27);
		    row.add(R.id.pos28);
		    idPos.add(row);
		    row = new ArrayList<Integer>();
		    row.add(R.id.pos29);
		    row.add(R.id.pos30);
		    row.add(R.id.pos31);
		    row.add(R.id.pos32);
		    idPos.add(row);
		    row = new ArrayList<Integer>();
		    row.add(R.id.pos33);
		    row.add(R.id.pos34);
		    row.add(R.id.pos35);
		    row.add(R.id.pos36);
		    idPos.add(row);
		    row = new ArrayList<Integer>();
		    row.add(R.id.pos37);
		    row.add(R.id.pos38);
		    row.add(R.id.pos39);
		    row.add(R.id.pos40);
		    idPos.add(row);
		    
		    
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
	    	e.printStackTrace();
	    }
	}	


	private void initQuadricules() {

		
		makeRandomImgs();
		for (int i = 0; i < CO.rows; ++i) {
			for (int j = 0; j < CO.cols; ++j) {
				if (CO.imatges.get(i*CO.cols+j) != null) {
					if(Descompressor.descompressor(CO.imatges.get(i*CO.cols+j), CO.path)) {
						TextView tmp = (TextView) findViewById(idPos.get(i).get(j));
		    			CO.poss.add(tmp);
					}
				}
			}
		}
					

		for (int i = 0; i < CO.rows; ++i) {
			for (int j = 0; j < CO.cols; ++j) {
				if (CO.imatges.get(i*CO.cols+j) != null) {
		    		if(Descompressor.descompressor(CO.imatges.get(i*CO.cols+j), CO.path)) {
		    			BitmapDrawable img = new BitmapDrawable(path+CO.imatges.get(i*CO.cols+j));
	
		    			
		    			resizeCaselles(CO.poss.get(correspondencies.get(i*CO.cols+j)));
		    			img = resizeImg(img);
		    			CO.poss.get(correspondencies.get(i*CO.cols+j)).setBackgroundDrawable(img);
		    			//CO.poss.get(i*CO.cols+j).setBackgroundDrawable(img);
		    			CO.poss.get(correspondencies.get(i*CO.cols+j)).setClickable(true);
		    			CO.poss.get(correspondencies.get(i*CO.cols+j)).setOnClickListener(new View.OnClickListener() {
							
							public void onClick(View v) {
								click(v);
							}
						});
					}
				}
			}
		}
		
		for (int i = 0; i < CO.rows; ++i) 
			for (int j = 0; j < CO.cols; ++j) {
			if (CO.celes.get(i*CO.cols+j) != null) {
				
	    		if(Descompressor.descompressor(CO.celes.get(i*CO.cols+j), CO.path)){
	    			
	    			//	height = img.getGravity();
	    			TextView tmp = (TextView) findViewById(idPos.get(5+i).get(j)); //al loro! 5 son maxrows
	    			CO.poss.add(tmp);
	    			resizeCaselles(CO.poss.get(CO.rows*CO.cols+i*CO.cols+j));
	    			CO.poss.get(CO.rows*CO.cols+i*CO.cols+j).setText(CO.celes.get(i*CO.cols+j));
	    			CO.poss.get(i*CO.cols+j).setClickable(true);
	    			
				}	
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void click(View v) {
		if (seleccionat == null) {
			seleccionat = (TextView) v;
			int pos = CO.poss.indexOf(seleccionat);
			
			Drawable draw = CO.poss.get(pos).getBackground();
			draw.setAlpha(0);
			CO.poss.get(pos).setBackgroundDrawable(draw);
			
			
		}
		
		else if (seleccionat != null) {
			if (seleccionat.equals(v)) {
				//(TextView) v.setBackgroundColor(Color.)
			}
		}
	}

	private void makeRandomImgs() {
		// TODO Auto-generated method stub
		ArrayList<Boolean> agafats = new ArrayList<Boolean>();
		for(int i = 0; i < CO.imatges.size(); i++){
			agafats.add(false);
		}
		Random r = new Random();
		
		this.correspondencies = new ArrayList<Integer>(CO.imatges.size());
		for(int i=0; i < CO.imatges.size(); ++i) {
			int rand =  r.nextInt(agafats.size());
			if(agafats.get(rand)!= true) {
				this.correspondencies.add(Integer.valueOf(rand));
				agafats.set(rand,true);  
			}
			else --i;
		}
	}

	private BitmapDrawable resizeImg(BitmapDrawable bitmapd){
    	
		Bitmap bitmapOrg = bitmapd.getBitmap();
        int widthImage = bitmapOrg.getWidth();
        int heightImage = bitmapOrg.getHeight();
        
        newWidth = width*CO.cols;
        newHeight = height*CO.rows;
        
        float scaleWidth = ((float) newWidth) / widthImage;
        float scaleHeight = ((float) newHeight) / heightImage;
        
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0,widthImage, heightImage, matrix, true);
        
        return new BitmapDrawable(resizedBitmap);
	}	
	
	private void resizeCaselles(TextView pos) {
		
		if(CO.cols == 1){
    		pos.setWidth(250);
    		width = 250;
    	} else if(CO.cols == 2){
    		pos.setWidth(120);
    		width = 120;
    	} else if(CO.cols == 3){
    		pos.setWidth(50);
    		width = 50;
    	} else {
    		//cols == 4
    		pos.setWidth(40);
    		width = 40;
    	}
    	
		if(CO.rows == 1 || CO.rows == 2){
    		pos.setHeight(100);
    		pos.setMaxLines(4);
    		height = 100;
    	} else if(CO.rows == 3){
    		pos.setHeight(85);
    		pos.setMaxLines(3);
    		height = 85;
    	} else if(CO.rows == 4){
    		pos.setHeight(50);
    		pos.setMaxLines(2);
    		height = 50;
    	} else {
    		//CO.rows == 5
    		pos.setHeight(40);
    		pos.setMaxLines(2);
    		height = 40;
    	}
		width /= 10;
		height /= 10;

	}
	
	//@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
	
		super.onDestroy();
		sound.unloadAll();	
	}
}
