package pfc.Jclic;

import java.util.Vector;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

	@Override
	public void bindView(final View view,  final Context context, final Cursor cursor) {

		ImageView icona = (ImageView)view.findViewById(R.id.iconClic);
		Uri dir = Uri.parse("/sdcard/GPS/nadal.jpg");
		Bitmap bMap = BitmapFactory.decodeFile("/sdcard/GPS/icon_example.png");

		bMap = Bitmap.createBitmap(bMap);
		BitmapDrawable bMap2 = new BitmapDrawable(bMap);

		icona.setImageBitmap(bMap);



		TextView titol = (TextView)view.findViewById(R.id.titulo);
		titol.setText(cursor.getString(cursor.getColumnIndex("titulo")));

		ImageButton play = (ImageButton)view.findViewById(R.id.bplay);

	

		titol.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				//tvDesc = (TextView)view.findViewById(R.id.tvDescripcio);
				tvDesc.setText(cursor.getString(cursor.getColumnIndex("descripcion")));
			}
		});

	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		final View view = mInflater.inflate(R.layout.element_clic, parent,false); 
		return view;
	}


}



