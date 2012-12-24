package pfc.Jclic;

import pfc.Activitats.Constants;
import pfc.Parser.Parser;
import pfc.Repositori.DadesServidor;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Inici extends Activity {
	
	private Constants CO = Constants.getInstance();
	
	static final int DIALOG_CATEGORIA_ID = 1;
	static final int DIALOG_EDAT_ID = 2;
	static final int DIALOG_IDIOMA_ID = 3;
	
	private ImageButton bLlibreria;
	private ImageButton bCategoria;
	private ImageButton bEdat;
	private ImageButton bIdioma;
	private TextView tvLlibreria;
	private TextView tvCategoria;
	private TextView tvEdat;
	private TextView tvIdioma;
	private TextView tvDescripcio;
	ListView listClics;
	CustomAdapter ca;
	
	String edat= "tots";
	String categoria= "tots";
	String idioma= "tots";
	
	
	private FuncionsBD FDB;
	
	public void onCreate(Bundle savedInstanceState) {		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.test);
	    
	    Parser.setActivitatsSaltades(false);
	    CO.activitatActual = -1;
	    CO.exemple = false;
	    DadesServidor.keyword = "";
	    DadesServidor.all = true;
	    
	    bLlibreria = (ImageButton)findViewById(R.id.bLlibreria);
	    bCategoria = (ImageButton)findViewById(R.id.bCategoria);
	    bEdat = (ImageButton)findViewById(R.id.bEdat);
	    bIdioma = (ImageButton)findViewById(R.id.bIdioma);
	    
	    tvLlibreria = (TextView)findViewById(R.id.tvLlibreria);
	    tvCategoria = (TextView)findViewById(R.id.tvCategoria);
	    tvEdat = (TextView)findViewById(R.id.tvEdat);
	    tvIdioma = (TextView)findViewById(R.id.tvIdioma);
	    
 	    tvDescripcio = (TextView)findViewById(R.id.tvDescripcio);
	    
	    tvLlibreria.setText("Llibreria");
	    tvCategoria.setText("Categoria");
	    tvEdat.setText("Edat");
	    tvIdioma.setText("Idioma");
	    
	    tvDescripcio.setText("Descripci� del Clic");
	    
	    listClics = (ListView)this.findViewById(R.id.listClics);
	    FDB = new FuncionsBD(this);
		FDB.open();
		//
		//FDB.deletesAll();
		FDB.create("clic2","A veure si funciona","4..5","Mireia & Borja & Merce(laBrasile�a)","alemany","mates","/sdcard/GPS/nadal.jpg","la mireia mola","/sdcard/GPS/pipinyer.jclic.zip");
		//
	    Cursor c = FDB.buscar_tots_clics();
	    int mireia =  c.getCount();
	    c.moveToFirst();
	    String cagarruta = c.getString(c.getColumnIndex("titulo"));
		ca = new CustomAdapter(getApplicationContext(), c, listClics, tvDescripcio);
		listClics.setAdapter(ca);
	   
	    
		//mostrar_clics();
		
	    setOnClickListener();
	}
	
	private void setOnClickListener(){
		
		bLlibreria.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View view) {
				// FDB.create("Clidfdfc","Demostraci� una mica m� no?","+18","Mireia & Borja & Merce(laBrasile�a)","Esperanto","Demo","/sdcard/GPS/nadal.jpg","la mireia mola","/sdcard/GPS/pipinyer.jclic.zip");

				/* FDB.create("joc de naturals", "aquest �s el millor joc del mon per apendre coses de la vida :)", 
						 "3-4", "merc�", "catal� obviament", "mates", "url!!!", "bla, ble, bli", "urlJoc!!!!");			 
				 Toast toast1 =  Toast.makeText(getApplicationContext(), "ha anat tot b� xatos ;)", Toast.LENGTH_SHORT);
			     toast1.show();*/
				 Toast toast1 =  Toast.makeText(getApplicationContext(), "Working..", Toast.LENGTH_SHORT);
			     toast1.show();
			 }
		});
		
		bCategoria.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				showDialog(DIALOG_CATEGORIA_ID);
				Toast toast1 =  Toast.makeText(getApplicationContext(), "Filtratge per categoria", Toast.LENGTH_SHORT);
			    toast1.show();
			 }
		});
		
		bEdat.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View view) {
				 /*FDB.create("joc d'angl�s", "aquest �s el millor joc del mon per apendre coses de la vida :)", 
						 "3-4", "merc�", "castell�", "mates", "url!!!", "bla, ble, bli", "urlJoc!!!!");	
				 Toast toast1 =  Toast.makeText(getApplicationContext(), "Edat en proc�s ;)", Toast.LENGTH_LONG);
			     toast1.show();*/
				 showDialog(DIALOG_EDAT_ID);
				 Toast toast1 =  Toast.makeText(getApplicationContext(), "Filtratge per edat", Toast.LENGTH_SHORT);
			     toast1.show();
			 }
		});
		
		bIdioma.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View view) {
				 showDialog(DIALOG_IDIOMA_ID);
				 Toast toast1 =  Toast.makeText(getApplicationContext(), "Filtratge per idioma", Toast.LENGTH_SHORT);
			     toast1.show();
			 }
		});
		
	}
	
    private void mostrar_clics() {
       /* Cursor c = FDB.buscar_tots_clics();
        if (c.getCount() == 0) Toast.makeText(getApplicationContext(), "No hi ha cap clic disponible", Toast.LENGTH_LONG).show();
        startManagingCursor(c);
        
        String[] from = new String[] {FDB.TITULO}; 
        int[] to = new int[] {R.id.tvTitolClic} ;
        /* 
        SimpleCursorAdapter adpLlistat =
            new SimpleCursorAdapter(this, R.layout.element_clic, c, from, to);
        listClics.setAdapter(adpLlistat);
        
		CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), c, listClics);
		listClics.setAdapter(customAdapter);*/
    }  
    
    private void mostrar_clics_cursor(Cursor c) {
    /*	if (c.getCount() == 0) Toast.makeText(getApplicationContext(), "No hi ha cap clic disponible", Toast.LENGTH_LONG).show();
    	startManagingCursor(c);
        
        String[] from = new String[] {FDB.TITULO}; 
        int[] to = new int[] {R.id.tvTitolClic} ;
        
        SimpleCursorAdapter adpLlistat =
            new SimpleCursorAdapter(getApplicationContext(), R.layout.element_clic, c, from, to);
        listClics.setAdapter(adpLlistat);*/
        
    }  
    
    protected Dialog onCreateDialog(int id) {
    	Dialog dialog;
    	switch (id) {
	    	case DIALOG_CATEGORIA_ID:
				dialog = crearCategoria();
				break;
	    	case DIALOG_EDAT_ID:
	    		dialog = crearEdat();
	    		break;
    		case DIALOG_IDIOMA_ID:
    			dialog = crearIdioma();
    			break;    
    	    default: 
    	    	dialog = null;
    	}
    	return dialog;
    }
    
    private Dialog crearCategoria() {
		// TODO Auto-generated method stub
		final CharSequence[] items ={"llengua", "mates", "ciencies", "socials", "tecnologia", "tots"};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Selecciona una categoria");
		builder.setItems(items, new DialogInterface.OnClickListener() {			
			public void onClick(DialogInterface dialog, int item) {
				// TODO Auto-generated method stub
				categoria = items[item].toString();
				cridaFiltes();
			}
		});
		return builder.create();
    }
    
    private void cridaFiltes() {
    	
		Cursor c = null;
		String[] args = new String[]{categoria, edat, idioma};
		
		if ((categoria == "tots") && (edat == "tots") && (idioma == "tots") )  c = FDB.buscar_tots_clics();
		
		else if ((categoria == "tots") && (idioma != "tots") && (edat == "tots")){	// buscar per idioma	
			args = new String[]{idioma};
			c = FDB.buscar_per_idioma(args);
			//mostrar_clics_cursor(c);
		}
		else if ((categoria == "tots") && (idioma == "tots") && (edat != "tots")){	// buscar per edat
			args = new String[]{edat};
			c = FDB.buscar_per_edat(args);
		}
		else if ((categoria != "tots") && (idioma == "tots") && (edat == "tots")){	// buscar per categoria
			args = new String[]{categoria};
			c = FDB.buscar_per_categoria(args);
		}
		
		else if ((categoria != "tots") && (idioma == "tots") && (edat != "tots")){	// buscar per categoria + edat
			args = new String[]{categoria, edat};
			c = FDB.buscar_catEdat(args);
		}
		else if ((categoria != "tots") && (idioma != "tots") && (edat == "tots")){	// buscar per categoria + idioma
			args = new String[]{categoria, idioma};
			c = FDB.buscar_catIdioma(args);
		}
		else if ((categoria == "tots") && (idioma != "tots") && (edat != "tots")){	// buscar per edat + idioma
			args = new String[]{edat, idioma};
			c = FDB.buscar_edatIdioma(args);
		}
		else if ((categoria != "tots") && (idioma != "tots") && (edat != "tots")){	// buscar per categoria + idioma + edat
			c = FDB.buscar_tot(args);
		}
		
		ca = new CustomAdapter(getApplicationContext(), c, listClics, tvDescripcio);
		listClics.setAdapter(ca);		
	}
    
	
	
	
	
	
    private Dialog crearEdat() {
		// TODO Auto-generated method stub
		final CharSequence[] items ={"0..3", "4..5", "6..8", "9..12", "13..15", "16..17", "tots"};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Selecciona un rang d'edat");
		builder.setItems(items, new DialogInterface.OnClickListener() {			
			public void onClick(DialogInterface dialog, int item) {
				// TODO Auto-generated method stub
				edat = items[item].toString();
				cridaFiltes();
				/*
				Cursor c;
				String edat = items[item].toString();
				
				String[] args = new String[]{edat};
				if (edat == "tots")  c = FDB.buscar_tots_clics();
				else c = FDB.buscar_per_edat(args);
				ca = new CustomAdapter(getApplicationContext(), c, listClics, tvDescripcio);
				listClics.setAdapter(ca);
				*/
			}
		});
		return builder.create();
	}
    
    
    private Dialog crearIdioma() {
		// TODO Auto-generated method stub
		final CharSequence[] items ={"catal�", "castell�", "angl�s", "alemany", "itali�", "franc�s", "tots"};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Selecciona un idioma");
		builder.setItems(items, new DialogInterface.OnClickListener() {			
			public void onClick(DialogInterface dialog, int item) {
				// TODO Auto-generated method stub
				idioma = items[item].toString();
				cridaFiltes();
				/*
				Cursor c;
				String idioma = items[item].toString();
				String[] args = new String[]{idioma};
				if (idioma == "tots")  c = FDB.buscar_tots_clics();
				else c = FDB.buscar_per_idioma(args);
				ca = new CustomAdapter(getApplicationContext(), c, listClics, tvDescripcio);
				listClics.setAdapter(ca);
				*/
			}
		});
		return builder.create();
	}
    
}