package pfc.Activitats;

import pfc.Jclic.R;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;



public class CrossWordAdapter extends BaseAdapter {
	private Context context;
	private final String[] mobileValues;
	private final int[] ids;

	public CrossWordAdapter(Context context, String[] mobileValues) {
		this.context = context;
		this.mobileValues = mobileValues;
		ids = new int[mobileValues.length];
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

		if (convertView == null) {

			gridView = new View(context);

			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.mobile, null);

			// set value into textview
			EditText textView = (EditText) gridView
					.findViewById(R.id.txtChar);
			ids[position] = textView.getId();
			//textView.setText(mobileValues[position]);
			textView.setText("");
			textView.setTextSize(20);
			textView.setTextColor(Color.BLACK);
			//Log.d("mobile", mobileValues[position]);
			if (mobileValues[position].equals("*")) {textView.setBackgroundColor(Color.BLACK);}
			else {
				textView.setBackgroundColor(Color.WHITE);
			}
			gridView.setLayoutParams(new GridView.LayoutParams(70,70));
		} 
		
		
		else {
			gridView = (View) convertView;
		}

		return gridView;
	}

	@Override
	public int getCount() {
		return mobileValues.length;
	}

	
	@Override
	public Object getItem(int position) {
		return null;
	}

	
	@Override
	public long getItemId(int position) {
		return ids[position];
	}

	public int getIdItem(int position){
		return ids[position];
	}
}
