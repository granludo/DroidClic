/*
 * @Projecte: JClic per gPhone
 * @Autora: Miriam Pujol Benet
 * @Versio: Juny 2009
 */

package pfc.Repositori;

import java.util.List;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class AdaptadorLlistaIconos extends BaseAdapter {

	private Context mContext;
	private List<ContingutFila> elementos;
	
	public AdaptadorLlistaIconos(Context mContext, List<ContingutFila> elementos)
	{
		this.mContext = mContext;
		this.elementos = elementos;
	}
	
	public int getCount() {
		return elementos.size();
	}

	public Object getItem(int position) {
		return elementos.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ContingutFila ti = elementos.get(position);
		return generarFila(position, ti.icono, ti.title, ti.area, ti.lang, ti.arrow);
	}
	
	public View generarFila(int position, Drawable icono, String name, String arees, String language, Drawable arrow)
	{
		TableRow vista = new TableRow(mContext);

		if(position % 2 == 0) vista.setBackgroundColor(Color.BLACK);
		else vista.setBackgroundColor(Color.DKGRAY);
		
        ImageView img = new ImageView(mContext);
        img.setImageDrawable(icono);
        img.setPadding(15,10,10,10);
        vista.addView(img,new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        
        TableLayout tl = new TableLayout(mContext);
        tl.setMinimumWidth(180);
        
        TextView nom = new TextView(mContext);
        nom.setText(name);
        nom.setTextSize(20);
        nom.setPadding(0, 7, 0, 0);
        nom.setMaxLines(1);
        nom.setTextColor(new ColorStateList(
                new int[][] { new int[] { android.R.attr.state_selected }, new int[0] },
                new int[] { Color.LTGRAY, Color.WHITE }));
        nom.setMaxWidth(10);
        tl.addView(nom, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); 
	    
        TextView area = new TextView(mContext);
        area.setText(arees);
        area.setPadding(0, 3, 0, 0);
        area.setMaxLines(1);
        area.setTextColor(new ColorStateList(
                new int[][] { new int[] { android.R.attr.state_selected }, new int[0] },
                new int[] { Color.LTGRAY, Color.WHITE }));
        area.setMaxWidth(10);
        tl.addView(area, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); 
	    
        TextView lang = new TextView(mContext);
        lang.setText(language);
        lang.setMaxLines(1);
        lang.setTextColor(new ColorStateList(
                new int[][] { new int[] { android.R.attr.state_selected }, new int[0] },
                new int[] { Color.LTGRAY, Color.WHITE }));
        lang.setMaxWidth(10);
        tl.addView(lang, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); 
	    
        vista.addView(tl, new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        
        ImageView ico = new ImageView(mContext);
        ico.setImageDrawable(arrow);
        ico.setPadding(15,35,10,0);
        vista.addView(ico, new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        
		return vista;
	}
}
