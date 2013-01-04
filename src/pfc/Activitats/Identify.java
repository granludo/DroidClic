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
import pfc.Parser.Dades;
import pfc.Parser.Parser;
import pfc.Parser.XMLConstants;
import android.R.bool;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.ScrollingMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Identify extends Activity {
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
	private ArrayList textus;
	private CountDownTimer timer;
	int contadorTemps = 0;
	int targets = 0;
	private ProgressBar tiempo;

	private Constants CO = Constants.getInstance();

	public void onCreate(Bundle savedInstanceState) {
		Log.d("TEST", "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.identify);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		EditText et = (EditText) findViewById(R.id.textuss);

		// et.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		// et.setInputType(InputType.TYPE_NULL);

		// et.setMaxLines(100);
		et.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("TEST", "onClic");
				onClick1(v);
			}
		});

		Button b1 = (Button) findViewById(R.id.menu);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openOptionsMenu();
			}
		});

		Log.d("MyApp", "Identify");
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

		textus = new ArrayList();
		String titol = "";
		if (Parser.getActivitats().elementAt(CO.activitatActual).getName() != null) {
			textus = Parser.getActivitats().elementAt(CO.activitatActual)
					.getTextus();
			titol = Parser.getActivitats().elementAt(CO.activitatActual)
					.getName();

		} else
			CO.name.setText("Activitat JClic");

		textu = "";
		for (int i = 0; i < textus.size(); ++i) {
			Log.d("TEST", String.valueOf(i));
			ArrayList hh = new ArrayList();
			hh = (ArrayList) textus.get(i);

			for (int j = 0; j < hh.size(); ++j) {

				DadesText d2 = new DadesText();
				d2 = (DadesText) hh.get(j);
				textu += d2.text;

				// Log.d("ARA", String.valueOf(d2.text));
				// Log.d("ARA",String.valueOf(d2.ranginici));
				// Log.d("ARA",String.valueOf(d2.rangfi));

			}

			// Log.d("TEXT", textu);

			EditText b = (EditText) findViewById(R.id.textuss);
			b.setHorizontallyScrolling(true);
			TextView tv = (TextView) findViewById(R.id.titulo);
			tv.setText(titol);
			b.setText(textu);

		}

	}

	public void onClick1(final View view) {
		EditText a = (EditText) findViewById(R.id.textuss);
		TextView intentos = (TextView) findViewById(R.id.editIntentos);
		TextView aciertos = (TextView) findViewById(R.id.editAciertos);
		// Log.d("abc", g);
		// int n = Integer.valueOf( (String) intentos.getText());

		// intentos.setText(String.valueOf(n));
		a.setBackgroundColor(Color.WHITE);

		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(a.getWindowToken(), 0);

		int pos_cursor = a.getSelectionStart();
		int pos_cursor2 = a.getSelectionEnd();
		if (pos_cursor2 == pos_cursor) {

			for (int i = 0; i < textus.size(); ++i) {
				ArrayList hh = new ArrayList();
				hh = (ArrayList) textus.get(i);

				for (int j = 0; j < hh.size(); ++j) {
					DadesText d2 = new DadesText();
					d2 = (DadesText) hh.get(j);
					if (pos_cursor >= d2.ranginici && pos_cursor <= d2.rangfi) {

						if (d2.tag == "target") {
							int k = Integer.valueOf((String.valueOf(intentos
									.getText())));
							++k;
							intentos.setText(String.valueOf(k));
							if (d2.polsat == false)
								++targets;
							if (targets == Parser.getActivitats()
									.elementAt(CO.activitatActual)
									.getNumTargets()) {
								Dialog ajuda = new AlertDialog.Builder(
										Identify.this)
										.setIcon(R.drawable.jclic_aqua)
										.setTitle("ENHORABUENA!!")
										.setPositiveButton("D'acord", null)
										.setMessage(
												"Has completado con exito la actividad.\n"
														+ "").create();
								ajuda.show();
							}
							d2.polsat = true;
							int p = Integer.valueOf((String.valueOf(aciertos
									.getText())));
							++p;
							aciertos.setText(String.valueOf(p));
							EditText b = (EditText) findViewById(R.id.textuss);
							Spannable spannable = b.getText();
							spannable.setSpan(
									new BackgroundColorSpan(Color.argb(255, 0,
											0, 255)), d2.ranginici, d2.rangfi,
									Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						} else if (d2.tag == "text") {
							int k = Integer.valueOf((String.valueOf(intentos
									.getText())));
							++k;
							intentos.setText(String.valueOf(k));
							EditText b = (EditText) findViewById(R.id.textuss);
							Spannable spannable = b.getText();
							spannable.setSpan(
									new BackgroundColorSpan(Color.argb(255,
											255, 0, 0)), d2.ranginici,
									d2.rangfi,
									Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						}
					}
				}
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
			Dialog ajuda = new AlertDialog.Builder(Identify.this)
					.setIcon(R.drawable.jclic_aqua).setTitle("Ajuda")
					.setPositiveButton("D'acord", null)
					.setMessage("Prem la paraula correcte.\n" + "").create();
			ajuda.show();
			return true;
		case MENU_SOLUCIO:

			String sol = "";
			for (int i = 0; i < textus.size(); ++i) {
				ArrayList hh = new ArrayList();
				hh = (ArrayList) textus.get(i);

				for (int j = 0; j < hh.size(); ++j) {
					DadesText d2 = new DadesText();
					d2 = (DadesText) hh.get(j);
					if (d2.tag == "target") {
						sol += d2.text + " ";
					}
				}
			}

			Dialog solucio = new AlertDialog.Builder(Identify.this)
					.setIcon(R.drawable.jclic_aqua).setTitle("Solucions")
					.setPositiveButton("D'acord", null).setMessage(sol)
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
									Identify.this.finish();
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
