package pfc.Jclic;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import pfc.ConnectionLayer.ClicMetaData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class LlibreriaSimpleAdapter extends SimpleAdapter {
	
	private TextView mDescView;
	private int mResource;
	private LayoutInflater mInflater;
	private List<? extends Map<String, ?>> mData;
	private int[] mTo;
    private String[] mFrom;
    private Context mContext;

	public LlibreriaSimpleAdapter(Context context,
					List<? extends Map<String, ?>> data,
					int resource,
					String[] from,
					int[] to,
					TextView descView) {
		super(context, data, resource, from, to);
		mDescView = descView;
		mData = data;
		mTo = to;
		mFrom = from;
		mResource = resource;
		mContext = context;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
		if (convertView == null) {
			v = mInflater.inflate(mResource, parent, false);
		} else {
			v = convertView;
		}
		try {
			bindView(position, v);
		} catch (IOException e) {
			mDescView.setText("No es pot omplir la llista");
			e.printStackTrace();
		}
		return v;
	}
	
	
	private void bindView(int position, View view) throws IOException {
	    final Map<String, ?> dataSet = mData.get(position);
        if (dataSet == null) {
            return;
        }

        final String[] from = mFrom;
        final int[] to = mTo;
    	final ClicMetaData data = (ClicMetaData) dataSet.get(from[0]);
    	
        if (dataSet.get(from[0]) == null) {
            return;
        }

        
        for (int i=0; i<2; i++) {
        	final View v = view.findViewById(to[i]);
        	if (v != null && v instanceof ImageView) {
                // image view for clic icons
            	byte[] imageBytes = data.getImage();
            	Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            	imageBitmap = CustomAdapter.getResizedBitmap(imageBitmap, 50, 50);
                ((ImageView) v).setImageBitmap(imageBitmap);
        	} else if (v != null && v instanceof TextView) {
                // text view for clic title
            	String title = data.getTitle() == null ? "" : data.getTitle();
            	setViewText((TextView) v, title);
            } else {
                throw new IllegalStateException(v.getClass().getName() + " is not a " +
                        " view that can be bounds by this SimpleAdapter");
            }

        	// shows clic description on description text view when clicking on icon or title views
            v.setOnClickListener(new OnClickListener() {
            	@Override
    			public void onClick(View v) {
            		String description = data.getBody() == null ? "" : data.getBody();
    				mDescView.setText(description);
    			}
    		});
        }
        
        
        ImageButton addButton = (ImageButton) view.findViewById(to[2]);
        addButton.setImageResource(R.drawable.ico_addclic);
        addButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mDescView.setText(data.getBody());
				Toast.makeText(mContext, "Descarregant clic...", Toast.LENGTH_LONG).show();
				
				byte[] fileBytes;
				File file;
				try {
					// download clic file to sd card
					fileBytes = (byte[]) data.getClic();
					file = new File(Inici.jclicDir, data.getFileName() + ".jclic.zip");
					writeFile(fileBytes, file);
					
					// download icon file to sd card
					fileBytes = (byte[]) data.getImage();
					file = new File(Inici.jclicDir, data.getFileName() + ".ico");
					writeFile(fileBytes, file);
					mDescView.setText("S'ha descarregat el clic");
				} catch (Exception e) {
					mDescView.setText("No es pot descarregar el clic");
					e.printStackTrace();
				}
			}
			
			private void writeFile (byte[] fileBytes, File file) throws IOException {
				BufferedOutputStream bos;
				bos = new BufferedOutputStream(new FileOutputStream(file));
				bos.write(fileBytes);
				bos.flush();
				bos.close();
			}
        });
	}
	

}
