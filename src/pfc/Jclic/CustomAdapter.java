package pfc.Jclic;

import java.util.Vector;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
       
	    public CustomAdapter(Context context, Cursor c, ListView list) {
	        super(context, c);
	        lista = list;
	        mCursor = c;   
	        mInflater=LayoutInflater.from(context);
			mContext=context;
	    }

	    @Override
	    public void bindView(View view,  final Context context, final Cursor cursor) {

	    	ImageView icona = (ImageView)view.findViewById(R.id.iconClic);
	    	
	    	TextView titol = (TextView)view.findViewById(R.id.titulo);
	    	titol.setText(cursor.getString(cursor.getColumnIndex("titulo")));
	    	
	    	ImageButton play = (ImageButton)view.findViewById(R.id.bplay);
	    	
	    	final TextView tvDesc = (TextView)view.findViewById(R.id.tvDescripcio);
	    	
	    	titol.setOnClickListener(new OnClickListener() {			
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast toast1 =  Toast.makeText(context, "va va va va", Toast.LENGTH_LONG);
				    toast1.show();
				    tvDesc.setText("això fa algo o que?");
				}
			});
	    	
	    }

	    @Override
	    public View newView(Context context, Cursor cursor, ViewGroup parent) {
	        final View view = mInflater.inflate(R.layout.element_clic, parent,false); 
	        return view;
	    }
	    

	}



