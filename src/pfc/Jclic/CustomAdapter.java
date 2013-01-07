package pfc.Jclic;

import java.io.File;
import java.io.IOException;

import pfc.Activitats.Constants;
import pfc.Activitats.Puzzle;
import pfc.Descompressor.Descompressor;
import pfc.Parser.Parser;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends CursorAdapter {
	private Constants CO = Constants.getInstance();
	private Context mContext;
	private final LayoutInflater mInflater;
	public int id = 0;
	public ListView lista = null;
	TextView tvDesc;

	public CustomAdapter(Context context, Cursor c, ListView list, TextView text) {
		super(context, c);
		lista = list;
		mInflater = LayoutInflater.from(context);
		mContext = context;
		tvDesc = text;
	}

	public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// CREATE A MATRIX FOR THE MANIPULATION
		Matrix matrix = new Matrix();
		// RESIZE THE BIT MAP
		matrix.postScale(scaleWidth, scaleHeight);
		// RECREATE THE NEW BITMAP
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
				matrix, false);
		return resizedBitmap;
	}

	@Override
	public void bindView(final View view, final Context context,
			final Cursor cursor) {

		ImageView icona = (ImageView) view.findViewById(R.id.iconClic);
		// el tag es per guardar la posicio dins la llista
		icona.setTag(cursor.getPosition());
		
		String state = Environment.getExternalStorageState();
		if ((Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) && Inici.jclicDir != null) {
			Inici.jclicDir.mkdirs();
			String iconName = cursor.getString(cursor.getColumnIndexOrThrow("nombre")) + ".ico";
			String iconPath = Inici.jclicDir.getAbsolutePath() + "/" + iconName;
			Bitmap bMap = BitmapFactory.decodeFile(iconPath);
			if (bMap != null) {
				bMap = getResizedBitmap(bMap, 50, 50);
				BitmapDrawable bMap2 = new BitmapDrawable(bMap);
				icona.setBackgroundDrawable(bMap2);
			} else {
				icona.setBackgroundResource(R.drawable.jclic_aqua);
			}
		} else {
		    // Something is wrong, cannot read form SDcard
			Toast.makeText(mContext, "The device is not mounted", Toast.LENGTH_LONG).show();		
			icona.setBackgroundResource(R.drawable.jclic_aqua);
		}

		TextView titol = (TextView) view.findViewById(R.id.titulo);
		titol.setText(cursor.getString(cursor.getColumnIndex("titulo")));
		titol.setTag(cursor.getPosition());

		ImageButton play = (ImageButton) view.findViewById(R.id.bplay);
		play.setImageResource(R.drawable.ico_go);
		play.setTag(cursor.getPosition());

		titol.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				cursor.moveToPosition((Integer) v.getTag());
				tvDesc.setText(Html.fromHtml(cursor.getString(cursor
						.getColumnIndex("descripcion"))));
			}
		});

		icona.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				cursor.moveToPosition((Integer) v.getTag());
				tvDesc.setText(cursor.getString(cursor
						.getColumnIndex("descripcion")));
			}
		});

		play.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				cursor.moveToPosition((Integer) v.getTag());
				tvDesc.setText(cursor.getString(cursor
						.getColumnIndex("descripcion")));
				String s = cursor.getString(cursor.getColumnIndex("nombre"));
				
				String state = Environment.getExternalStorageState();
				if ((Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) && Inici.jclicDir != null) {
					File arxiu = new File(Inici.jclicDir, s + ".jclic.zip");
					String path = arxiu.getAbsolutePath();
					CO.path = path;
	
					//descomprimir fitxer i buscar el jclic
					CO.fitxer = (String) CO.path.subSequence(0, CO.path.length() - 4);
					String[] split = CO.fitxer.split("/");
					CO.fitxer = split[split.length - 1];
	
					if(Descompressor.descompressor(CO.fitxer, CO.path)){
	
						File file = new File("/sdcard/tmp/jclic/"+CO.fitxer);
						try {
							file.createNewFile();
						} catch (IOException e) {
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
				} else {
				    // Something is wrong, cannot read form SDcard
					Toast.makeText(mContext, "The device is not mounted", Toast.LENGTH_LONG).show();		
				}
			}
		});

	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		final View view = mInflater.inflate(R.layout.element_clic, parent,
				false);
		return view;
	}

	private void creaMissatgeTemporal(String missatge, boolean curt) {
		Toast missatgeTemporal = Toast.makeText(mContext, missatge,
				curt ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
		missatgeTemporal.setGravity(Gravity.CENTER, 0, 0);
		missatgeTemporal.show();
	}

}
