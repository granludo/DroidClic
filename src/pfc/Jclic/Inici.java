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
	
	private FuncionsBD FDB;
	
	public void onCreate(Bundle savedInstanceState) {		
	    super.onCreate(savedInstanceState);
	    LogSystem.Init();
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
	    
	    tvDescripcio.setText("Aqui t'explicarem la nostra vida xD");
	    
	    listClics = (ListView)this.findViewById(R.id.listClics);
	    
	    
	    FDB = new FuncionsBD(this);
		FDB.open();
		mostrar_clics();
		
	    setOnClickListener();
	}
	
	private void setOnClickListener(){
		
		bLlibreria.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View view) {
				 FDB.create("joc de naturals", "aquest és el millor joc del mon per apendre coses de la vida :)", 
						 "3-4", "mercè", "català obviament", "mates", "url!!!", "bla, ble, bli", "urlJoc!!!!");			 
				 Toast toast1 =  Toast.makeText(getApplicationContext(), "ha anat tot bé xatos ;)", Toast.LENGTH_SHORT);
			     toast1.show();
			 }
		});
		
		bCategoria.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				 showDialog(DIALOG_CATEGORIA_ID);
			 }
		});
		
		bEdat.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View view) {
				 FDB.create("joc d'anglès", "aquest és el millor joc del mon per apendre coses de la vida :)", 
						 "3-4", "mercè", "castellà", "mates", "url!!!", "bla, ble, bli", "urlJoc!!!!");	
				 Toast toast1 =  Toast.makeText(getApplicationContext(), "Edat en procés ;)", Toast.LENGTH_LONG);
			     toast1.show();
			 }
		});
		
		bIdioma.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View view) {
				 showDialog(DIALOG_IDIOMA_ID);
			 }
		});
		
	}
	
    private void mostrar_clics() {
        Cursor c = FDB.buscar_tots_clics();
        if (c.getCount() == 0) Toast.makeText(getApplicationContext(), "No hi ha cap clic disponible", Toast.LENGTH_LONG).show();
        startManagingCursor(c);
        
        String[] from = new String[] {FDB.TITULO}; 
        int[] to = new int[] {R.id.tvTitolClic} ;
        /* 
        SimpleCursorAdapter adpLlistat =
            new SimpleCursorAdapter(this, R.layout.element_clic, c, from, to);
        listClics.setAdapter(adpLlistat);*/
        
		CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), c, listClics);
		listClics.setAdapter(customAdapter);
    }  
    
    private void mostrar_clics_cursor(Cursor c) {
    	if (c.getCount() == 0) Toast.makeText(getApplicationContext(), "No hi ha cap clic disponible", Toast.LENGTH_LONG).show();
    	startManagingCursor(c);
        
        String[] from = new String[] {FDB.TITULO}; 
        int[] to = new int[] {R.id.tvTitolClic} ;
        
        SimpleCursorAdapter adpLlistat =
            new SimpleCursorAdapter(getApplicationContext(), R.layout.element_clic, c, from, to);
        listClics.setAdapter(adpLlistat);
        
    }  
    
    protected Dialog onCreateDialog(int id) {
    	Dialog dialog;
    	switch (id) {
	    	case DIALOG_CATEGORIA_ID:
				dialog = crearCategoria();
				break;
	    	/*case DIALOG_EDAT_ID:
	    		dialog = crearEdat();
	    		break;*/
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
				String categoria = items[item].toString();
				String[] args = new String[]{categoria};
				if (categoria == "tots")  mostrar_clics();
				else {				
					Cursor c = FDB.buscar_per_categoria(args);
					mostrar_clics_cursor(c);
				}
			}
		});
		return builder.create();
	}
    
    
    private Dialog crearIdioma() {
		// TODO Auto-generated method stub
		final CharSequence[] items ={"català", "castellà", "anglès", "alemany", "italià", "francès", "tots"};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Selecciona un idioma");
		builder.setItems(items, new DialogInterface.OnClickListener() {			
			public void onClick(DialogInterface dialog, int item) {
				// TODO Auto-generated method stub
				String idioma = items[item].toString();
				String[] args = new String[]{idioma};
				if (idioma == "tots")  mostrar_clics();
				else {				
					Cursor c = FDB.buscar_per_idioma(args);
					mostrar_clics_cursor(c);
				}
			}
		});
		return builder.create();
	}
    
}