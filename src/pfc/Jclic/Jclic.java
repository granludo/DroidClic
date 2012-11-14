/*
 * @Projecte: JClic per gPhone
 * @Autora: Miriam Pujol Benet
 * @Versio: Juny 2009
 */

package pfc.Jclic;

import pfc.Activitats.Constants;
import pfc.Activitats.Puzzle;
import pfc.NavegadorArxius.NavegadorArxius;
import pfc.Parser.Parser;
import pfc.Repositori.DadesServidor;
import pfc.Repositori.LlistatProjectes;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Jclic extends Activity {
	
	private Constants CO = Constants.getInstance();
	private TextView inici_url;
	private TextView inici_fitxer;
	private TextView inici_exemple;
	private TextView tsortir;
	private TextView tabout;
	private ImageView burl;
	private ImageView bexemple;
	private ImageView bfitxer;
	private ImageView bsortir;
	private ImageView babout;
	
	public void onCreate(Bundle savedInstanceState) {		
	    super.onCreate(savedInstanceState);
	    LogSystem.Init();
	    setContentView(R.layout.inici);
	    
	    Parser.setActivitatsSaltades(false);
	    CO.activitatActual = -1;
	    CO.exemple = false;
	    DadesServidor.keyword = "";
	    DadesServidor.all = true;
	    
	    inici_url = (TextView)findViewById(R.id.inici_URL);
	    inici_fitxer = (TextView)findViewById(R.id.inici_fitxer);
	    inici_exemple = (TextView)findViewById(R.id.inici_exemple);
	    tsortir = (TextView)findViewById(R.id.tsortir);
	    tabout = (TextView)findViewById(R.id.tabout);

	    burl = (ImageView)findViewById(R.id.icono_url);
	    bfitxer = (ImageView)findViewById(R.id.icono_fitxer);
	    bexemple = (ImageView)findViewById(R.id.icono_exemple);
	    bsortir = (ImageView)findViewById(R.id.bsortir);
	    babout = (ImageView)findViewById(R.id.babout);
	    
	    setOnClickListener();
	}

	private void setOnClickListener(){
		inici_url.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	click_URL();
            }
        });
		
		burl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	inici_url.setTextColor(Color.DKGRAY);            	
            	click_URL();
            }
        });
		
		inici_fitxer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
        		Intent i = new Intent(Jclic.this, NavegadorArxius.class);
            	startActivity(i);
            	finish();
            }
        });
		
		bfitxer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	inici_fitxer.setTextColor(Color.DKGRAY);
        		Intent i = new Intent(Jclic.this, NavegadorArxius.class);
            	startActivity(i);
            	finish();
            }
        });
		
		inici_exemple.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	click_exemple();
            }
        });
		
		bexemple.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	inici_exemple.setTextColor(Color.DKGRAY);
            	click_exemple();
            }
        });
		
		babout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	Dialog about = new AlertDialog.Builder(Jclic.this)
    	        .setIcon(R.drawable.jclic_aqua)
    	        .setTitle("Sobre el projecte")
    	        .setPositiveButton("D'acord", null)
    	        .setMessage("Projecte JClic per a Android\n\nMiriam Pujol Benet\n\nCurs 2008-2009-Q2")
    	        .create();
    			about.show();
            }
        });
		
		tabout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	Dialog about = new AlertDialog.Builder(Jclic.this)
    	        .setIcon(R.drawable.jclic_aqua)
    	        .setTitle("Sobre el projecte")
    	        .setPositiveButton("D'acord", null)
    	        .setMessage("Projecte JClic per a Android\n\nMiriam Pujol Benet\n\nCurs 2008-2009-Q2")
    	        .create();
    			about.show();
            }
        });
		
		bsortir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	click_sortir();
            }
        });
		
		tsortir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	click_sortir();
            }
        });
	}
	
	private void click_URL(){
		try {
			Intent i = new Intent(Jclic.this, LlistatProjectes.class);
        	startActivity(i);
        	finish();
		} catch (Exception e) {
			Log.d("Error", "catch setOnClickListener1 JClic: "+e);
		}
	}
	
	private void click_exemple(){
		try {
    		CO.exemple = true;
    		CO.is = Jclic.this.getAssets().open("balenes.jclic");
    		Parser.ParserXML(CO.is);
    		
    		Intent i = new Intent(Jclic.this, Puzzle.class);
        	startActivity(i);
        	finish();
		} catch (Exception e) {
			Log.d("Error", "catch setOnClickListener3 JClic: "+e);
		}
	}
	
	private void click_sortir(){
		AlertDialog.Builder builder = new AlertDialog.Builder(Jclic.this);
    	builder.setIcon(R.drawable.jclic_aqua);
    	builder.setMessage("Estàs segur de que vols sortir?")
    	       .setCancelable(false)
    	       .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	                Jclic.this.finish();
    	           }
    	       })
    	       .setNegativeButton("No", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	                dialog.cancel();
    	           }
    	       });
    	AlertDialog alert = builder.create();
    	alert.show();
	}
}