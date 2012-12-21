package pfc.Jclic;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import pfc.Activitats.Constants;
import pfc.Activitats.Puzzle;
import pfc.Descompressor.Descompressor;
import pfc.NavegadorArxius.NavegadorArxius;
import pfc.Parser.Parser;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends CursorAdapter {
	private Constants CO = Constants.getInstance();
	private Cursor mCursor;
	private Context mContext;
	private final LayoutInflater mInflater;
	public int id = 0;
	private SQLiteDatabase db;
	public ListView lista = null;  
	TextView tvDesc;
	public CustomAdapter(Context context, Cursor c, ListView list, TextView text) {
		super(context, c);
		lista = list;
		mCursor = c;   
		mInflater=LayoutInflater.from(context);
		mContext=context;
		tvDesc=text;
	}
	
	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// CREATE A MATRIX FOR THE MANIPULATION
		Matrix matrix = new Matrix();
		// RESIZE THE BIT MAP
		matrix.postScale(scaleWidth, scaleHeight);
		// RECREATE THE NEW BITMAP
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
		return resizedBitmap;
	}
	@Override
	public void bindView(final View view,  final Context context, final Cursor cursor) {

		ImageView icona = (ImageView)view.findViewById(R.id.iconClic);
		//Uri dir = Uri.parse("/sdcard/GPS/nadal.jpg");
		//Bitmap bMap = BitmapFactory.decodeFile("/sdcard/GPS/icon_example.png");

		//bMap = Bitmap.createBitmap(bMap);
		//BitmapDrawable bMap2 = new BitmapDrawable(bMap);
		//icona.setImageBitmap(bMap);
		//Bitmap bMap = BitmapFactory.decodeFile("/sdcard/GPS/nadal.jpg");
		String s = cursor.getString(cursor.getColumnIndex("icono"));
		Bitmap bMap = BitmapFactory.decodeFile(s);
		bMap = getResizedBitmap(bMap, 75, 75);
		BitmapDrawable bMap2 = new BitmapDrawable(bMap);
		icona.setBackgroundDrawable(bMap2);

		TextView titol = (TextView)view.findViewById(R.id.titulo);
		titol.setText(cursor.getString(cursor.getColumnIndex("titulo")));
		
		ImageButton play = (ImageButton)view.findViewById(R.id.bplay);



		titol.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				//tvDesc = (TextView)view.findViewById(R.id.tvDescripcio);
				tvDesc.setText(cursor.getString(cursor.getColumnIndex("descripcion")));
			}
		});

		play.setOnClickListener(new OnClickListener() {
			public void onClick(View v){
				
				tvDesc.setText("mireia masmola");
				//String s = cursor.getString(cursor.getColumnIndex("jclic"));
				//File arxiu = new File(s);
				File arxiu = new File("/sdcard/GPS/pipinyer.jclic.zip");
				String path = arxiu.getAbsolutePath();
				CO.path = path;

			///	try{
					//descomprimir fitxer i buscar el jclic
					CO.fitxer = (String)CO.path.subSequence(0,CO.path.length()-4);
					String[] split = CO.fitxer.split("/");
					CO.fitxer = split[split.length-1];

					if(Descompressor.descompressor(CO.fitxer, CO.path)){

						File file = new File("/sdcard/tmp/jclic/"+CO.fitxer);
						try {
							file.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						Parser.ParserXML(file);

						if(Parser.getActivitats().size() == 0){
							Dialog noActivitats = new AlertDialog.Builder(context)
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
						
							Intent i = new Intent(context.getApplicationContext(), Puzzle.class);
							i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							context.startActivity(i);
						}
					} else {
						Dialog noActivitats = new AlertDialog.Builder(context)
						.setIcon(R.drawable.jclic_aqua)
						.setTitle("Fitxer invàlid")
						.setPositiveButton("D'acord", null)
						.setMessage("El fitxer no és vàlid!")
						.create();
						noActivitats.show();
					}

				//} catch(Exception e){
					//Log.d("Error", "catch NavegadorArxius: "+e);
				//}
			}
		});

	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		final View view = mInflater.inflate(R.layout.element_clic, parent,false); 
		return view;
	}
	private void creaMissatgeTemporal(String missatge,boolean curt) {
		Toast missatgeTemporal = Toast.makeText(mContext, missatge,curt?Toast.LENGTH_SHORT:Toast.LENGTH_LONG);	
		missatgeTemporal.setGravity(Gravity.CENTER, 0, 0);
		missatgeTemporal.show();
	}

}



