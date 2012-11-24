package pfc.Activitats;

import java.util.ArrayList;

import pfc.Descompressor.Descompressor;
import pfc.Jclic.R;
import pfc.Parser.Parser;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

@TargetApi(3) 
public class SimpleAssociation extends Activity{
	private Constants CO = Constants.getInstance();
	private String path = "/sdcard/tmp/jclic/";
	
	private int newWidth;
	private int newHeight;
	private int width;
	private int height;
	
	Sounds sound;
	private int maxTime = Parser.getActivitats().get(CO.activitatActual).getTempsMax();
	private int maxIntents =  Parser.getActivitats().get(CO.activitatActual).getIntentMax();
	private boolean TimeCountDown =  Parser.getActivitats().get(CO.activitatActual).getTimeCutDown();
	private boolean IntentCountDown =  Parser.getActivitats().get(CO.activitatActual).getIntentCutdown();
	private ArrayList<ArrayList<Integer>> idPos = new ArrayList<ArrayList<Integer>>();
	
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


		for (int i = 0; i < CO.rows; ++i) 
			for (int j = 0; j < CO.cols; ++j) {
			if (CO.imatges.get(i*CO.cols+j) != null) {
				
	    		if(Descompressor.descompressor(CO.imatges.get(i*CO.cols+j), CO.path)){
	    			BitmapDrawable img = new BitmapDrawable(path+CO.imatges.get(i*CO.cols+j));

	    			//	height = img.getGravity();
	    			//img.createFromPath("/mnt"+path+CO.imatges.get(i));
	    			TextView tmp = (TextView) findViewById(idPos.get(i).get(j));
	    			CO.poss.add(tmp);
	    			resizeCaselles(CO.poss.get(i*CO.cols+j));
	    			img = resizeImg(img);
	    			CO.poss.get(i*CO.cols+j).setBackgroundDrawable(img);
				}
				

			}
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
    		pos.setWidth(80);
    		width = 80;
    	} else {
    		//cols == 4
    		pos.setWidth(60);
    		width = 60;
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
    		pos.setHeight(70);
    		pos.setMaxLines(2);
    		height = 70;
    	} else {
    		//CO.rows == 5
    		pos.setHeight(60);
    		pos.setMaxLines(2);
    		height = 60;
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
