/*
 * @Projecte: JClic per gPhone
 * @Autora: Miriam Pujol Benet
 * @Versio: Juny 2009
 */

package pfc.Repositori;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import pfc.Activitats.Constants;
import pfc.Activitats.Puzzle;
import pfc.Descompressor.Descompressor;
import pfc.Jclic.Jclic;
import pfc.Jclic.R;
import pfc.Parser.Parser;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class LlistatProjectes extends ListActivity {    
	private Drawable arrow;
    private Object resultRequestSOAP = null;
    private List<ContingutFila> lista;
    private AdaptadorLlistaIconos ad;
    private Constants CO = Constants.getInstance();
    
    public EditText nametext;

	private static final int MENU_AJUDA = 0;
	private static final int MENU_INICI = 1;
	private static final int MENU_NOVETATS = 2;
	private static final int MENU_CERCAR = 3;
	private static final int MENU_SORTIR = 4;
	
	@SuppressWarnings("unchecked")
	@Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.llista_repositori);
        
        Dialog atencio = new AlertDialog.Builder(this)
        .setIcon(R.drawable.jclic_aqua)
        .setTitle("Atenció")
        .setPositiveButton("D'acord", null)
        .setMessage("Selecciona el projecte que vols obrir. " +
        		"Pot trigar una estona en descarregar-se a\n/" +
        		"sdcard/tmp/jclic")
        .create();
        atencio.show();
        
        
        
        Resources res = getResources();
        SoapObject request;
        
        if(DadesServidor.all)
        	request = new SoapObject(DadesServidor.NAMESPACE, DadesServidor.SOAP_ACTION_ALL);
        else {
        	request = new SoapObject(DadesServidor.NAMESPACE, DadesServidor.SOAP_ACTION_SEARCH);
        	request.addProperty("keyword", DadesServidor.keyword);
        }

	    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    envelope.setOutputSoapObject(request);
		  
	    HttpTransportSE androidHttpTransport = new HttpTransportSE(DadesServidor.URL);
	    try
	    {
	    	androidHttpTransport.call(DadesServidor.SOAP_ACTION_ALL, envelope);
	        resultRequestSOAP =  envelope.getResponse();
	        Vector results = (Vector) resultRequestSOAP;
	        
	        lista = new ArrayList<ContingutFila>();
	        arrow = res.getDrawable(R.drawable.arrow);
	        
	        for(int i = 0; i < results.size(); i++){
	        	String title = (String)results.elementAt(i).toString().subSequence(
	        			results.elementAt(i).toString().indexOf("Title=")+6, 
	        			results.elementAt(i).toString().indexOf("; Author="));
	        	
	        	String area = (String)results.elementAt(i).toString().subSequence(
	        			results.elementAt(i).toString().indexOf("Area=")+5, 
	        			results.elementAt(i).toString().indexOf("; Language="));

		        String language = (String)results.elementAt(i).toString().subSequence(
		        		results.elementAt(i).toString().indexOf("Language=")+9, 
		        		results.elementAt(i).toString().indexOf("; Level="));

		        String icona = (String)results.elementAt(i).toString().subSequence(
		        		results.elementAt(i).toString().indexOf("Icon=")+5, 
		        		results.elementAt(i).toString().indexOf("; SubscriptionId="));
		        
		        String file = (String)results.elementAt(i).toString().subSequence(
		        		results.elementAt(i).toString().indexOf("File=")+5, 
		        		results.elementAt(i).toString().indexOf("; Icon="));
		        
		        String[] split = icona.split("/");
		        
		        URL url = new URL(icona);
		        InputStream in = url.openStream();
		        BitmapDrawable btmdrw = agafarImatge(in, split[split.length-1]);
		        
		        if(btmdrw == null)
		        	btmdrw = (BitmapDrawable)res.getDrawable(R.drawable.jclic_aqua);
		        
		        lista.add(new ContingutFila(btmdrw,title,area,language,arrow, file));
	        }
	        
	        ad = new AdaptadorLlistaIconos(this,lista);
	        setListAdapter(ad);
	        
	    } catch(Exception e){
	    	Log.d("Error","error catch Llista: "+e);
	    }
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
        int IDFilaSeleccionada = position;
        
        try {
			ContingutFila ti = (ContingutFila) ad.getItem(IDFilaSeleccionada);
	        
	        URL url = new URL(DadesServidor.ACTIVITATS+ti.file);
			InputStream in = url.openStream();
			
			File dst = new File("/sdcard/tmp/jclic/"+ti.file);
			dst = copiarArxiu(in, dst);
			
			CO.path = dst.getAbsolutePath();
			
			CO.fitxer = (String)CO.path.subSequence(0,CO.path.length()-4);
 			String[] split = CO.fitxer.split("/");
			CO.fitxer = split[split.length-1];
			
			if(Descompressor.descompressor(CO.fitxer, CO.path)){
 				
 				File file = new File("/sdcard/tmp/jclic/"+CO.fitxer);
         		file.createNewFile();
         		
         		Parser.ParserXML(file);
         		
         		if(Parser.getActivitats().size() == 0){
					Dialog noActivitats = new AlertDialog.Builder(LlistatProjectes.this)
	    	        .setIcon(R.drawable.jclic_aqua)
	    	        .setTitle("No hi ha activitats")
	    	        .setPositiveButton("D'acord", null)
	    	        .setMessage("No hi ha activitats o les que hi ha no són vàlides!")
	    	        .create();
					noActivitats.show();
				} else {
					if(Parser.getActivitatsSaltades()){
            			creaMissatgeTemporal(
        					"S'han eliminat algunes activitats\n" +
        					"per problemes de tamany", 
        					false);
            		}
					
					Intent i = new Intent(LlistatProjectes.this, Puzzle.class);
	            	startActivity(i);
	            	finish();
				}
 			} else {
 				Dialog noActivitats = new AlertDialog.Builder(LlistatProjectes.this)
    	        .setIcon(R.drawable.jclic_aqua)
    	        .setTitle("Fitxer invàlid")
    	        .setPositiveButton("D'acord", null)
    	        .setMessage("El fitxer no és vàlid!")
    	        .create();
				noActivitats.show();
 			}
			
		} catch (Exception e) {
			Log.d("Error", "error AgafarFitxer: "+e);
		}
    }
	
	private BitmapDrawable agafarImatge(InputStream in, String imatge){
		try{
			File dst = new File("/sdcard/tmp/jclic/"+imatge);
			dst = copiarArxiu(in, dst);
	        
	        Bitmap bitmapOrg = BitmapFactory.decodeFile(dst.getAbsolutePath());
	        
	        int widthImage = bitmapOrg.getWidth();
	        int heightImage = bitmapOrg.getHeight();
	        
	        float scaleWidth = ((float) 70) / widthImage;
	        float scaleHeight = ((float) 70) / heightImage;
	        
	        Matrix matrix = new Matrix();
	        matrix.postScale(scaleWidth, scaleHeight);
	        
	        Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0,widthImage, heightImage, matrix, true);
	        Bitmap btmp = Bitmap.createBitmap(resizedBitmap, 0, 0, 70, 70);
	        BitmapDrawable btmdrw = new BitmapDrawable(btmp);
	        
	        return btmdrw;
		} catch(Exception e){
			return null;
		}
	}
	
	private File copiarArxiu(InputStream in, File dst){
		try{
			dst.createNewFile();
			
			OutputStream out = new FileOutputStream(dst);
			
			byte[] buf = new byte[1024];
			int len;

			while ((len = in.read(buf)) > 0)
				out.write(buf, 0, len);
			
			in.close();
			out.close();
			return dst;
		} catch(Exception e){
			Log.d("Error","error copiarArxiu: "+e);
			return null;
		}
	}
	
	private void creaMissatgeTemporal(String missatge,boolean curt) {
        Toast missatgeTemporal = Toast.makeText(this, missatge,curt?Toast.LENGTH_SHORT:Toast.LENGTH_LONG);
        missatgeTemporal.setGravity(Gravity.CENTER, 0, 0);
        missatgeTemporal.show();
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        CO.menu = menu;
        CO.menu.clear();
        CO.menu.add(0, MENU_AJUDA, 0, R.string.menu_ajuda);
        CO.menu.add(0, MENU_INICI, 0, R.string.menu_inici);
        CO.menu.add(0, MENU_NOVETATS, 0, R.string.menu_novetats);
        CO.menu.add(0, MENU_CERCAR, 0, R.string.menu_cercar);
        CO.menu.add(0, MENU_SORTIR, 0, R.string.menu_sortir);
       
        CO.menu.getItem(MENU_AJUDA).setIcon(android.R.drawable.ic_menu_help);
		CO.menu.getItem(MENU_INICI).setIcon(android.R.drawable.ic_menu_revert);
        CO.menu.getItem(MENU_NOVETATS).setIcon(android.R.drawable.ic_menu_share);
        CO.menu.getItem(MENU_CERCAR).setIcon(android.R.drawable.ic_menu_search);
		CO.menu.getItem(MENU_SORTIR).setIcon(android.R.drawable.ic_menu_close_clear_cancel);
		
		if(DadesServidor.all) CO.menu.getItem(MENU_NOVETATS).setEnabled(false);
		else CO.menu.getItem(MENU_NOVETATS).setEnabled(true);
		
        return true;
	}	
	
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_AJUDA:
            	Dialog ajuda = new AlertDialog.Builder(this)
    	        .setIcon(R.drawable.jclic_aqua)
    	        .setTitle("Ajuda")
    	        .setPositiveButton("D'acord", null)
    	        .setMessage("Selecciona el projecte que vols obrir. " +
    	        		"Pot trigar una estona en descarregar-se a\n/" +
    	        		"sdcard/tmp/jclic")
    	        .create();
            	ajuda.show();
            	return true;
            case MENU_NOVETATS:
            	DadesServidor.keyword = "";
            	DadesServidor.all = true;
            	Intent nov = new Intent(this, LlistatProjectes.class);
            	startActivity(nov);
            	finish();
            	return true;
            case MENU_CERCAR:
            	final Dialog dialog = new Dialog(this);
            	
                dialog.setContentView(R.layout.llista_repositori_cerca);
                dialog.setTitle("Clau de cerca");
                
                Button auto = (Button) dialog.findViewById(R.id.ok);
                
                auto.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        nametext = (EditText) dialog.findViewById(R.id.text);
                        
                        DadesServidor.keyword = nametext.getText().toString();
                        
                        DadesServidor.all = false;
                        
                    	Intent cerca = new Intent(LlistatProjectes.this, LlistatProjectes.class);
                    	startActivity(cerca);
                    	finish();
                    	
                        dialog.dismiss();
                    }
                });
                dialog.show();
                
            	return true;
            case MENU_SORTIR:            	
            	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            	builder.setIcon(R.drawable.jclic_aqua);
            	builder.setMessage("Estàs segur de que vols sortir?")
            	       .setCancelable(false)
            	       .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            	           public void onClick(DialogInterface dialog, int id) {
            	                LlistatProjectes.this.finish();
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
}