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

@TargetApi(3) 
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
		   // setOnClickListener();
		    
		    //listeners de menus
	    } catch(Exception e){
	    	Log.d("Error", "catch SimpleAssociation: "+e);
	    }
	}	
	
	/*private void setOnClickListener() {
		// TODO Auto-generated method stub
		CO.missCorrectes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	if(CO.casIni == CO.correcte || contador==maxIntents || contadorTemps==maxTime){
            		Intent iSeg = new Intent(SimpleAssociation.this, Puzzle.class);
                	startActivity(iSeg);
                	finish();
            	}
            }
        });
		
		CO.missCorrectes2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	if(CO.casIni == CO.correcte ||contador==maxIntents || contadorTemps==maxTime){
            		Intent iSeg = new Intent(DoublePuzzle.this, Puzzle.class);
                	startActivity(iSeg);
                	finish();
            	}
            }
        });
		
		CO.miss.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	if(CO.casIni == CO.correcte ||contador==maxIntents || contadorTemps==maxTime){
            		Intent iSeg = new Intent(DoublePuzzle.this, Puzzle.class);
                	startActivity(iSeg);
                	finish();
            	}
            }
        });
		
		CO.miss2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	if(CO.casIni == CO.correcte){
            		Intent iSeg = new Intent(DoublePuzzle.this, Puzzle.class);
                	startActivity(iSeg);
                	finish();
            	}
            }
        });
		
		//Si apreto dues vecCaselles deselecciono el primer que tenia
		for(int i = 0; i < CO.vecCaselles.size(); i++){
			if(CO.vecCaselles.elementAt(i) != null){
				final TextView pos = CO.vecCaselles.elementAt(i);
				
				pos.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View view) {
		                       			
	                	sound.playClick();
	                	
		            	if(posAgafada == pos) {
		            		//estic agafant la propia casella que ha he agafat							
							CO.p1 = "<buit>";
							
							if(CO.imatge != null){
								pos.setTextColor(Color.TRANSPARENT);
	            				pos.setBackgroundColor(Color.TRANSPARENT);
	            				String text = (String)pos.getText();
	            				
	                    		int indexEntr = CO.entrada.indexOf(text);
	                    		int indexSort = CO.sortida.indexOf(text);
	                    		
	                    		vecDraw.elementAt(indexSort).setAlpha(255);
	                    		
	                    		CO.vecCaselles.elementAt(indexEntr).
	                    			setBackgroundDrawable(vecDraw.elementAt(indexSort));
	            			} else {
	            				CO.cas1.setText("");
								CO.cas2.setText("");
								pos.setTextColor(CO.fg);
								pos.setBackgroundColor(CO.bg);
	            			}
							posAgafada = null;
						} else {
							if(!CO.p1.equals("<buit>")){
								//tinc una casella agafada, agafo nova
			            		if(CO.imatge != null){
			            			posAgafada.setTextColor(Color.TRANSPARENT);
		            				posAgafada.setBackgroundColor(Color.TRANSPARENT);
		            				
		            				String text = (String)posAgafada.getText();
		            				
		                    		int indexEntr = CO.entrada.indexOf(text);
		                    		int indexSort = CO.sortida.indexOf(text);
		                    		
		                    		vecDraw.elementAt(indexSort).setAlpha(255);
		                    		
		                    		CO.vecCaselles.elementAt(indexEntr).
		                    			setBackgroundDrawable(vecDraw.elementAt(indexSort));
			            		} else {
			            			posAgafada.setBackgroundColor(CO.bg);
				            		posAgafada.setTextColor(CO.fg);
			            		}
							}
							CO.p1 = (String)pos.getText();
							
							if(CO.imatge != null){
								pos.setTextColor(Color.TRANSPARENT);
	            				pos.setBackgroundColor(Color.TRANSPARENT);
	            				
	            				String text = (String)pos.getText();
	            				
	                    		int indexEntr = CO.entrada.indexOf(text);
	                    		int indexSort = CO.sortida.indexOf(text);
	                    		
	                    		vecDraw.elementAt(indexSort).setAlpha(100);
	                    		
	                    		CO.vecCaselles.elementAt(indexEntr).
	                    			setBackgroundDrawable(vecDraw.elementAt(indexSort));
	            			} else {
	            				CO.cas1.setText(CO.p1);
								CO.cas2.setText(CO.p1);
								
	            				pos.setBackgroundColor(Color.WHITE);
								pos.setTextColor(Color.BLACK);
	            			}
							posAgafada = pos;
						}
		            }
		        });
			}
		}
		
		//si tinc un vecCaselles seleccionat, comprovo amb aquest
		for(int i = 0; i < CO.vecCasellesSort.size(); i++){
			if(CO.vecCasellesSort.elementAt(i) != null){
				final TextView pos = CO.vecCasellesSort.elementAt(i);
				
				pos.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View view) {		            	
		               	if(!CO.p1.equalsIgnoreCase("<buit>")){
							//tinc un valor agafat, miro si va aqui
		            		if(CO.p1.equalsIgnoreCase((String)pos.getText())){
		            			//el valor es el correcte
		            			//intercanvio les posicions	            	            			
		            			pos.setText(CO.p1);
		            			pos.setBackgroundColor(CO.bg);
		            			pos.setTextColor(CO.fg);
		            			pos.setEnabled(false);
		            			posAgafada.setEnabled(false);
		            			posAgafada.setBackgroundColor(Color.GRAY);
		            			posAgafada.setTextColor(Color.TRANSPARENT);
		            			CO.correcte++;
		            			CO.incorrecte--;
		            			sound.playAction_ok();
		            			
		            			if(CO.imatge != null){
		            				//faig que tingui la imatge i no el text
		            				pos.setTextColor(Color.TRANSPARENT);
		            				pos.setBackgroundColor(Color.TRANSPARENT);
		            				String text = (String)pos.getText();
		                    		int index = CO.sortida.indexOf(text);
		                    		vecDraw.elementAt(index).setAlpha(255);
		                    		CO.vecCasellesSort.elementAt(index).
		                    			setBackgroundDrawable(vecDraw.elementAt(index));
		            			}
		            		} else {
		            			//el valor no es correcte, poso posAgafada a null
		            			posAgafada.setBackgroundColor(CO.bg);
		            			posAgafada.setTextColor(CO.fg);
			                	sound.playActionError();
		            			if (contador < maxIntents) contador++;
		            			
		            			if(CO.imatge != null){
		            				//faig que tingui la imatge i no el text
		            				posAgafada.setTextColor(Color.TRANSPARENT);
		            				posAgafada.setBackgroundColor(Color.TRANSPARENT);
		            				
		            				String text = (String)posAgafada.getText();
		            				
		                    		int indexEntr = CO.entrada.indexOf(text);
		                    		int indexSort = CO.sortida.indexOf(text);
		                    		
		                    		vecDraw.elementAt(indexSort).setAlpha(255);
		                    		
		                    		CO.vecCaselles.elementAt(indexEntr).
		                    			setBackgroundDrawable(vecDraw.elementAt(indexSort));
		            			}
		            		}
		            		posAgafada = null;
		            		CO.p1 = "<buit>";
	            			CO.cas1.setText("");
	            			CO.cas2.setText("");
		            		setMissatges();
		            	}
		            }
		        });
			}
		}
	}*/

	private void initQuadricules() {

		
		makeRandomImgs();
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
	    			//CO.poss.get(i*CO.cols+j).setBackgroundDrawable(img);
	    			CO.poss.get(correspondencies.get(i)*CO.cols+j).setBackgroundDrawable(img);
	    			CO.poss.get(i*CO.cols+j).setClickable(true);
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
	    			
	    			makeRandomText();
				}
				

			}
		}

	}

	private void makeRandomText() {
		// TODO Auto-generated method stub
		
	}

	private void makeRandomImgs() {
		// TODO Auto-generated method stub
		ArrayList<Boolean> agafats = new ArrayList<Boolean>();
		for(int i = 0; i < CO.sortida.size(); i++){
			agafats.add(false);
		}
		Random r = new Random();
		
		this.correspondencies = new ArrayList<Integer>(CO.imatges.size());
		for(int i=0; i < CO.imatges.size(); ++i) {
			Integer rand = Integer.valueOf(r.nextInt(agafats.size()));
			if(agafats.get(rand)!= true) {
				this.correspondencies.set(i, rand);
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
