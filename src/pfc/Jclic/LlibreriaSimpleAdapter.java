/*
* This file is part of DroidClic
*
* DroidClic is copyright 2012 by
* 	Marc Alier Forment,
* 	Maria José Casany Guerrero,
* 	Enric Mayol
* 
* UPC Students involved in this project:
* 
* Previous version and legacy code:
* ---------------------------------
* 	PUJOL BENET, MIRIAM
*
*
* Project management
* ------------------
*	ALMA SERRANO, ALBERT
* 	CLAVER ARGUDO, MARIA
*	JIMENEZ TARRES, VICTOR
*	CORCHADO MERINO, JUAN CARLOS
* 	JUAN JANE, ANDREU
*	MENES ROUCO, MARTIN
*	ORTEGA GOMEZ, CRISTIAN
*	PURCET SOTO, SERGI
*	RAMOS GONZALEZ, RICARDO
* 	SOLE MORERA, DANIEL
*
*
* Research & support
* --------------------
*	ALBALATE FERNANDEZ, AIDA
* 	CABRE JUAN, ALBERT
* 	CANDON ARENAS, HECTOR
*	ELBAILE SERRA, ABEL
* 	GONZALEZ DE PABLO, BORJA
*	IGLESIAS LOPEZ, OSCAR
* 	MARTINEZ LOPEZ, SERGIO
*	PEREZ PLANAS, ORIAC
*	SANCHEZ MARCOS, IVAN
* 	TORNE GOZALBO, ORIOL
*
*
* Development
* -----------
* Lead developers
*	ALBALATE FERNANDEZ, AIDA
*	COSTA MANSILLA, GERARD
* 	GONZALEZ DE PABLO, BORJA
* Developers:
* 	ALEMANY FONT, ALBERT
* 	ALVAREZ JUSTE, XAVIER
* 	ALVAREZ MORALES, FERRAN
* 	BARRERO MARTINEZ, LINDSAY
* 	BENITEZ VALLS, ALBERT
* 	BERRUEZO MARTINEZ, DAVID
*	BRAMON DEVANT, MARC
* 	BRIGUELLI DA SILVA, LUIS FERNANDO
* 	CABRE JUAN, ALBERT
* 	CANDON ARENAS, HECTOR
* 	CAPEL CATALAN, VICTOR
*	CLAVER ARGUDO, MARIA
*	DE PAULA DE PUIG GUIXE, FRANCESC
* 	DIEZ RUIZ, ALBERT
*	ELBAILE SERRA, ABEL
* 	FARRE GONZALEZ, PAU
*	GARCIA GARCIA, XAVIER
* 	HURTADO OBIOLS, CRISTINA
* 	MARTINEZ DIAZ, ARTURO
* 	MARTINEZ LOPEZ, SERGIO
*	MENES ROUCO, MARTIN
* 	MONTSERRAT GARCIA, EDUARD
* 	ORTIZ GRIMAU, XAVIER
* 	OSORIO ALVAREZ, DAVID
*	PASCUAL VAZQUEZ, PABLO
* 	PEDRAZA GUTIERREZ, M. MERCEDES
* 	PEREZ PLANAS, ORIAC
* 	RODRIGUEZ TORRES, MIREIA
* 	SANCHEZ MARCOS, IVAN
*	SEGARRA RODA, EDUARD
*	SELLES FEITO, MANEL
*	SOLER PASCUAL, GERARD
*	SUBIRATS SALVANS, JOAN
*
*
* Design & usability
* --------------------
* Lead designer:
* 	LEGORBURU CLADERA, IÑIGO
* Designers:
* 	OTAL RODRIGUEZ, DANIEL
*	PASCUAL VAZQUEZ, PABLO
*	SEGARRA RODA, EDUARD
*	SOLER PASCUAL, GERARD
*	SUBIRATS SALVANS, JOAN
* 	VIDAL PASTALLE, MARIA
*
*
* Testing, evaluation & audit
* ---------------------------
* Lead tester:
* 	NAVARRO JIMENEZ, GERMAN
*	ALEMANY FONT, ALBERT
* Testers:
*	ALVAREZ MORALES, FERRAN
*	BENITEZ VALLS, ALBERT
* 	CAPEL CATALAN, VICTOR
*	MONTSERRAT GARCIA, EDUARD
* 	ORTIZ GRIMAU, XAVIER
* 	SANCHEZ CORREDOR, MONTSERRAT
*
*
* Documentation, communication & broadcast
* ----------------------------------------
* Lead documentator:
*	ALVAREZ JUSTE, XAVIER
*	SANCHEZ CORREDOR, MONTSERRAT
* Documentators:
*	BARRERO MARTINEZ, LINDSAY
* 	GARCIA GARCIA, XAVIER
*	NAVARRO JIMENEZ, GERMAN
*	OSORIO ALVAREZ, DAVID
*	TORNE GOZALBO, ORIOL
*
* 
* DroidClic is copyright 2012 by
* Universitat Politecnica de Catalunya http://www.upc.edu
* Contact info:
* Marc Alier Forment granludo @ gmail.com or marc.alier @ upc.edu 
*
* DroiClic is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Droidlic is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with DroidClic. If not, see <http://www.gnu.org/licenses/>.
*
* DroidClic is based on the Software JClic by Francesc Busquets
* http://clic.xtec.cat/es/jclic/ 
*
*/




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
import android.os.Environment;
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
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to, TextView descView) {
		super(context, data, resource, from, to);
		mDescView = descView;
		mData = data;
		mTo = to;
		mFrom = from;
		mResource = resource;
		mContext = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

		for (int i = 0; i < 2; i++) {
			final View v = view.findViewById(to[i]);
			if (v != null && v instanceof ImageView) {
				// image view for clic icons
				byte[] imageBytes = data.getImage();
				Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageBytes,
						0, imageBytes.length);
				imageBitmap = CustomAdapter.getResizedBitmap(imageBitmap, 50,
						50);
				((ImageView) v).setImageBitmap(imageBitmap);
			} else if (v != null && v instanceof TextView) {
				// text view for clic title
				String title = data.getTitle() == null ? "" : data.getTitle();
				setViewText((TextView) v, title);
			} else {
				throw new IllegalStateException(v.getClass().getName()
						+ " is not a "
						+ " view that can be bounds by this SimpleAdapter");
			}

			// shows clic description on description text view when clicking on
			// icon or title views
			v.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String description = data.getBody() == null ? "" : data
							.getBody();
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
				
				String state = Environment.getExternalStorageState();
				if ((Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) && Inici.jclicDir != null) {
					Inici.jclicDir.mkdirs();
	
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
				} else {
				    // Something is wrong, cannot read form SDcard
					Toast.makeText(mContext, "The device is not mounted", Toast.LENGTH_LONG).show();		
				}
			}

			private void writeFile(byte[] fileBytes, File file)
					throws IOException {
				BufferedOutputStream bos;
				bos = new BufferedOutputStream(new FileOutputStream(file));
				bos.write(fileBytes);
				bos.flush();
				bos.close();
			}
		});
	}

}
