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


package pfc.Activitats;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Vector;

import pfc.Descompressor.Descompressor;
import pfc.Jclic.Jclic;
import pfc.Jclic.R;
import pfc.Parser.Parser;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CrossWord extends Activity {
	private static final int MENU_ANT = 0;
	private static final int MENU_SEG = 1;
	private static final int MENU_SOLUCIO = 2;
	private static final int MENU_AJUDA = 3;
	private static final int MENU_INICI = 4;
	private static final int MENU_SORTIR = 5;

	private TextView posAgafada = null;
	private int newWidth;
	private int newHeight;
	private int width;
	private int height;
	private Vector<BitmapDrawable> vecDraw;
	private String textu;
	private ArrayList rowsus;
	private CountDownTimer timer;
	int contadorTemps = 0;
	int targets = 0;
	private ProgressBar tiempo;
	private ArrayList horizontal;
	private ArrayList vertical;
	private int aciertos;
	private int intentos;
	static String[] TAULA;
	static String[] TAULA2;
	static Boolean[] TAULATRUE;
	GridView gridView;

	private Constants CO = Constants.getInstance();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.crossword);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		aciertos = 0;
		intentos = 0;

		gridView = (GridView) findViewById(R.id.gridView1);

		Button b1 = (Button) findViewById(R.id.menu);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openOptionsMenu();
			}
		});

		agafarDades();

		try {
			reiniciarMenu();

			if (CO.imatge != null) {
				if (CO.exemple) {
					InputStream in = this.getAssets().open(CO.imatge);

					File dst = new File("/sdcard/tmp/jclic/imatge.jpg");
					dst.createNewFile();

					OutputStream out = new FileOutputStream(dst);

					byte[] buf = new byte[1024];
					int len;

					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}

					in.close();
					out.close();

					// posarImatges(dst.getAbsolutePath());
				} else {
					if (Descompressor.descompressor(CO.imatge, CO.path)) {
						posarImatges("/sdcard/tmp/jclic/" + CO.imatge);
					}
				}
			}
			// setOnClickListener();
		} catch (Exception e) {
			Log.d("Error", "catch Identify " + e);
		}
	}

	private void agafarDades() {

		int maxTime = Parser.getActivitats().elementAt(CO.activitatActual)
				.getTempsMax();
		// maxTime = 20;
		tiempo = (ProgressBar) findViewById(R.id.progressTime);
		tiempo.setMax(maxTime);
		tiempo.setProgress(0);
		if (maxTime != 0) {
			timer = new CountDownTimer(maxTime * 1000, 1000) {
				@Override
				public void onFinish() {
					contadorTemps++;
					/*
					 * tiempo.setText(Integer .toString(maxTime -
					 * contadorTemps));
					 */
					tiempo.setProgress(contadorTemps);

					// setMissatges();
				}

				@Override
				public void onTick(long arg0) {
					contadorTemps++;
					/*
					 * tiempo.setText(Integer .toString(maxTime -
					 * contadorTemps));
					 */
					tiempo.setProgress(contadorTemps);

					// setMissatges();
				}
			}.start();
		}

		gridView.setBackgroundColor(Color.GRAY);

		String titol = "";
		if (Parser.getActivitats().elementAt(CO.activitatActual).getName() != null) {
			rowsus = Parser.getActivitats().elementAt(CO.activitatActual)
					.getCrossword();
			horizontal = Parser.getActivitats().elementAt(CO.activitatActual)
					.getHoritzontals();
			vertical = Parser.getActivitats().elementAt(CO.activitatActual)
					.getVertical();
			gridView.setNumColumns(((String) rowsus.get(0)).length());
			gridView.getLayoutParams().width = ((String) rowsus.get(0))
					.length() * 70;
			// gridView.getLayoutParams().height = rowsus.size() * 80;

			titol = Parser.getActivitats().elementAt(CO.activitatActual)
					.getName();

		} else
			CO.name.setText("Activitat JClic");
		int numElems = ((String) rowsus.get(0)).length() * rowsus.size();
		TAULA = new String[numElems];
		TAULA2 = new String[numElems];
		TAULATRUE = new Boolean[numElems];
		for (int i = 0; i < numElems; ++i)
			TAULATRUE[i] = false;
		int pos = 0;
		for (int i = 0; i < rowsus.size(); ++i) {
			for (int j = 0; j < ((String) rowsus.get(i)).length(); ++j) {
				TAULA[pos] = String.valueOf(((String) rowsus.get(i)).charAt(j));
				TAULA2[j * rowsus.size() + i] = String.valueOf(((String) rowsus
						.get(i)).charAt(j));

				++pos;
			}
		}

		TextView etHor = (TextView) findViewById(R.id.desc_hor);
		TextView etVer = (TextView) findViewById(R.id.desc_ver);
		String hor = "HORIZONTALES: " + '\n';
		String ver = "VERTICALES: " + '\n';
		for (int i = 0; i < horizontal.size(); ++i) {
			if (((String) (horizontal.get(i))).equals(" "))
				hor += "{FOTO}" + '\n';
			else
				hor += (String) horizontal.get(i) + '\n';
		}
		for (int i = 0; i < vertical.size(); ++i) {
			if (((String) (vertical.get(i))).equals(" "))
				ver += "{FOTO}" + '\n';
			else
				ver += (String) vertical.get(i) + '\n';
		}
		etHor.setText(hor);
		etVer.setText(ver);

		gridView.setAdapter(new CrossWordAdapter(this, TAULA));

		TextView tv = (TextView) findViewById(R.id.titulo);
		tv.setText(titol);
	}

	public void Corregir(View view) {

		int lletres = 0;
		for (int i = 0; i < TAULA.length; ++i) {
			int idItem = (int) gridView.getAdapter().getItemId(i);
			View vv = gridView.getChildAt(i);
			EditText ett = (EditText) vv.findViewById((int) idItem);
			if (!TAULA[i].equals("*")) {
				++lletres;
				if (ett.getText().toString().length() > 0) {
					if ((ett.getText().toString().equals(TAULA[i]))
							&& TAULATRUE[i] == false) {
						++aciertos;
						TAULATRUE[i] = true;
						++intentos;
					}
					if (TAULATRUE[i] == false)
						++intentos;
				}

			}
		}
		if (lletres == aciertos && aciertos > 0) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.jclic_aqua);
			builder.setMessage("Has completado con exito el crucigrama!")
					.setCancelable(false)
					.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// CrossWord.this.finish();
								}
							});

			AlertDialog alert = builder.create();
			alert.show();

		}

		TextView tvaciertos = (TextView) findViewById(R.id.editAciertos);
		tvaciertos.setText(String.valueOf(aciertos));
		TextView tvintentos = (TextView) findViewById(R.id.editIntentos);
		tvintentos.setText(String.valueOf(intentos));

	}

	public void clicked(View v) {

	}

	public void clickItem(int position) {

		if (!TAULA[position].equals("*")) {
			int numAst = 0;
			Boolean astTrobat = true;
			for (int i = 0; i <= position; ++i) {
				if (TAULA[i].equals("*")) {
					if (astTrobat == false)
						++numAst;
					astTrobat = true;
				} else
					astTrobat = false;
			}

		}
		if (!TAULA2[position].equals("*")) {
			int numAst = 0;
			Boolean astTrobat = true;
			for (int i = 0; i <= position; ++i) {
				if (TAULA2[i].equals("*")) {
					if (astTrobat == false)
						++numAst;
					astTrobat = true;
				} else
					astTrobat = false;
			}

		}

	}

	private void posarImatges(String path) {
		Bitmap bitmapOrg = BitmapFactory.decodeFile(path);

		int widthImage = bitmapOrg.getWidth();
		int heightImage = bitmapOrg.getHeight();

		newWidth = width * CO.cols;
		newHeight = height * CO.rows;

		float scaleWidth = ((float) newWidth) / widthImage;
		float scaleHeight = ((float) newHeight) / heightImage;

		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);

		Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0, widthImage,
				heightImage, matrix, true);

		int x = 0, y = 0;
		Bitmap btmp = null;
		BitmapDrawable btmdrw = null;

		int rowActual = 0;
		int colActual = -1;
		int colAnt = 0;

		vecDraw = new Vector<BitmapDrawable>();

		for (int i = 0; i < CO.vecCaselles.size(); i++) {
			if (CO.vecCaselles.elementAt(i) != null) {
				colAnt = colActual;

				if (colActual == CO.cols - 1)
					colActual = 0;
				else
					colActual++;

				if (colAnt == CO.cols - 1)
					rowActual++;

				if (CO.cols == 1)
					x = CO.cols - 1;
				else
					x = (newWidth / CO.cols) * (colActual);

				if (CO.rows == 1)
					y = CO.rows - 1;
				else
					y = (newHeight / CO.rows) * (rowActual);

				btmp = Bitmap.createBitmap(resizedBitmap, x, y, newWidth
						/ CO.cols, newHeight / CO.rows);
				btmdrw = new BitmapDrawable(btmp);
				// CO.vecCaselles.elementAt(i).setBackgroundDrawable(btmdrw);
				vecDraw.add(btmdrw);
			} else
				vecDraw.add(null);
		}

		for (int i = 0; i < CO.vecCaselles.size(); i++) {
			if (CO.vecCaselles.elementAt(i) != null) {
				String text = (String) CO.vecCaselles.elementAt(i).getText();
				int index = CO.sortida.indexOf(text);
				CO.vecCaselles.elementAt(i).setBackgroundDrawable(
						vecDraw.elementAt(index));
			}
		}
	}

	private void reiniciarMenu() {
		if (CO.menu != null) {
			CO.menu.clear();
			CO.menu.add(0, MENU_ANT, 0, R.string.menu_ant);
			CO.menu.add(0, MENU_SEG, 0, R.string.menu_seg);
			CO.menu.add(0, MENU_SOLUCIO, 0, R.string.menu_solucio);
			CO.menu.add(0, MENU_AJUDA, 0, R.string.menu_ajuda);
			CO.menu.add(0, MENU_INICI, 0, R.string.menu_inici);
			CO.menu.add(0, MENU_SORTIR, 0, R.string.menu_sortir);

			CO.menu.getItem(MENU_ANT).setIcon(android.R.drawable.ic_media_rew);
			CO.menu.getItem(MENU_SEG).setIcon(android.R.drawable.ic_media_ff);
			CO.menu.getItem(MENU_SOLUCIO).setIcon(
					android.R.drawable.btn_star_big_off);
			CO.menu.getItem(MENU_AJUDA)
					.setIcon(android.R.drawable.ic_menu_help);
			CO.menu.getItem(MENU_INICI).setIcon(
					android.R.drawable.ic_menu_revert);
			CO.menu.getItem(MENU_SORTIR).setIcon(
					android.R.drawable.ic_menu_close_clear_cancel);

			// Configuracio del menu per mostrarSolucio
			if (CO.mostrarSolucio)
				CO.menu.getItem(MENU_SOLUCIO).setEnabled(true);
			else
				CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);
			CO.menu.getItem(MENU_SOLUCIO).setTitle(R.string.menu_solucio);

			// Configuracio del menu per ant i seguent
			CO.menu.getItem(MENU_SEG).setEnabled(true);
			CO.menu.getItem(MENU_ANT).setEnabled(true);

			if (CO.activitatActual < 1) {
				// estem a la primera activitat, pel que no podem habilitar
				// l'anterior
				CO.menu.getItem(MENU_ANT).setEnabled(false);
			}
			if (CO.activitatActual == Parser.getActivitats().size() - 1) {
				// estem a l'ultima activitat, pel que no podem habilitar el
				// seguent
				CO.menu.getItem(MENU_SEG).setEnabled(false);
			}
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		CO.menu = menu;
		CO.menu.clear();
		CO.menu.add(0, MENU_ANT, 0, R.string.menu_ant);
		CO.menu.add(0, MENU_SEG, 0, R.string.menu_seg);
		CO.menu.add(0, MENU_SOLUCIO, 0, R.string.menu_solucio);
		CO.menu.add(0, MENU_AJUDA, 0, R.string.menu_ajuda);
		CO.menu.add(0, MENU_INICI, 0, R.string.menu_inici);
		CO.menu.add(0, MENU_SORTIR, 0, R.string.menu_sortir);

		CO.menu.getItem(MENU_ANT).setIcon(android.R.drawable.ic_media_rew);
		CO.menu.getItem(MENU_SEG).setIcon(android.R.drawable.ic_media_ff);
		CO.menu.getItem(MENU_SOLUCIO).setIcon(
				android.R.drawable.btn_star_big_off);
		CO.menu.getItem(MENU_AJUDA).setIcon(android.R.drawable.ic_menu_help);
		CO.menu.getItem(MENU_INICI).setIcon(android.R.drawable.ic_menu_revert);
		CO.menu.getItem(MENU_SORTIR).setIcon(
				android.R.drawable.ic_menu_close_clear_cancel);

		// Configuro els botons d'anterior i seguent
		CO.menu.getItem(MENU_SEG).setEnabled(true);
		CO.menu.getItem(MENU_ANT).setEnabled(true);

		if (CO.activitatActual < 1) {
			// estem a la primera activitat, pel que no podem habilitar
			// l'anterior
			CO.menu.getItem(MENU_ANT).setEnabled(false);
		}
		if (CO.activitatActual == Parser.getActivitats().size() - 1) {
			// estem a l'ultima activitat, pel que no podem habilitar el seguent
			CO.menu.getItem(MENU_SEG).setEnabled(false);
		}

		if (CO.mostrarSolucio)
			CO.menu.getItem(MENU_SOLUCIO).setEnabled(true);
		else
			CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ANT:
			CO.activitatActual = CO.activitatActual - 2;
			Intent iAnt = new Intent(this, Puzzle.class);
			startActivity(iAnt);
			finish();
			return true;
		case MENU_SEG:
			Intent iSeg = new Intent(this, Puzzle.class);
			startActivity(iSeg);
			finish();
			return true;
		case MENU_AJUDA:
			Dialog ajuda = new AlertDialog.Builder(CrossWord.this)
					.setIcon(R.drawable.jclic_aqua).setTitle("Ajuda")
					.setPositiveButton("D'acord", null)
					.setMessage("Completa el crucigrama.\n" + "").create();
			ajuda.show();
			return true;
		case MENU_SOLUCIO:

			Dialog solucio = new AlertDialog.Builder(CrossWord.this)
					.setIcon(R.drawable.jclic_aqua).setTitle("Solucions")
					.setPositiveButton("D'acord", null).setMessage("Solucio")
					.create();
			solucio.show();

			return true;
		case MENU_SORTIR:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.jclic_aqua);
			builder.setMessage("Estas segur de que vols sortir?")
					.setCancelable(false)
					.setPositiveButton("Si",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									CrossWord.this.finish();
								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
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

	private void setMissatges() {
		if (CO.solucioVisible) {
			CO.miss.setText("");
			CO.missCorrectes.setText("");
			CO.cas1.setText("");
			CO.p1 = "<buit>";
			CO.p2 = "<buit>";
		} else {
			if (CO.correcte == CO.casIni) {
				// Hem acabat el joc
				if (Parser.getActivitats().elementAt(CO.activitatActual)
						.getMissatgeFi() != null)
					CO.miss.setText(Parser.getActivitats()
							.elementAt(CO.activitatActual).getMissatgeFi());
				else
					CO.miss.setText("Joc finalitzat!");

				CO.missCorrectes.setText("Prem aqui per continuar.");
				CO.missCorrectes.setBackgroundColor(Color.WHITE);
				CO.missCorrectes.setTextColor(Color.BLACK);

				// bloquejarJoc(true);
				if (CO.menu != null)
					CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);

			} else {
				if (Parser.getActivitats().elementAt(CO.activitatActual)
						.getMissatgeIni() != null)
					CO.miss.setText(Parser.getActivitats()
							.elementAt(CO.activitatActual).getMissatgeIni());
				else
					CO.miss.setText("Comenca el joc!");
				CO.missCorrectes.setText("Correctes = " + CO.correcte
						+ ", Incorrectes = " + CO.incorrecte);
			}
		}
	}

}
