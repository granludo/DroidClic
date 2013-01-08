/*
* This file is part of DroidClic
*
* DroidClic is copyright 2012 by
* 	Marc Alier Forment,
* 	Maria Jos� Casany Guerrero,
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
* 	LEGORBURU CLADERA, I�IGO
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
import java.util.Vector;

import pfc.Descompressor.Descompressor;
import pfc.Jclic.R;
import pfc.Parser.Parser;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

@TargetApi(3)
public class DoublePuzzle extends Activity {
	private static final int MENU_ANT = 0;
	private static final int MENU_SEG = 1;
	private static final int MENU_SOLUCIO = 2;
	private static final int MENU_AJUDA = 3;
	private static final int MENU_INICI = 4;
	private static final int MENU_SORTIR = 5;
	private TextView posAgafada = null;
	private TextView aciertos = null;
	private TextView intentos = null;
	private TextView ttiempo = null;
	private ProgressBar tiempo = null;
	private Button bMenu = null;
	private int newWidth;
	private int newHeight;
	private int width;
	private int height;
	private Vector<BitmapDrawable> vecDraw;
	private Constants CO = Constants.getInstance();

	Sounds sound;

	private int maxTime = Parser.getActivitats().get(CO.activitatActual)
			.getTempsMax();
	private int maxIntents = Parser.getActivitats().get(CO.activitatActual)
			.getIntentMax();
	private boolean TimeCountDown = Parser.getActivitats()
			.get(CO.activitatActual).getTimeCutDown();
	private boolean IntentCountDown = Parser.getActivitats()
			.get(CO.activitatActual).getIntentCutdown();

	int contador = 0; // Comptador per als intents.
	int contadorTemps = 0; // Comptador per al temps.
	private CountDownTimer timer;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.double_puzzle);

		// aqu� s'inicialitza el so
		sound = new Sounds(getApplicationContext());
		aciertos = (TextView) findViewById(R.id.editAciertos);
		intentos = (TextView) findViewById(R.id.editIntentos);
	    ttiempo = (TextView)findViewById(R.id.tiempo);
		tiempo = (ProgressBar) findViewById(R.id.progressTime);
		tiempo.setMax(maxTime);
		tiempo.setProgress(0);
		bMenu = (Button) findViewById(R.id.menu);
		if (maxTime == 0) {
			tiempo.setVisibility(tiempo.INVISIBLE);
			ttiempo.setVisibility(ttiempo.INVISIBLE);
		}
		try {
			// reiniciarMenu();
			agafarDades();

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

					posarImatges(dst.getAbsolutePath());
				} else {
					if (Descompressor.descompressor(CO.imatge, CO.path)) {
						posarImatges("/sdcard/tmp/jclic/" + CO.imatge);
					}
				}
			}
			sound.playStart();
			setOnClickListener();

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

						setMissatges();
					}

					@Override
					public void onTick(long arg0) {
						contadorTemps++;
						/*
						 * tiempo.setText(Integer .toString(maxTime -
						 * contadorTemps));
						 */
						tiempo.setProgress(contadorTemps);

						setMissatges();
					}
				}.start();
			}
		} catch (Exception e) {
			Log.d("Error", "catch DoublePuzzle: " + e);
		}

		// Insertar esto en una actividad para el uso del menu.
		final Context aC = this;
		bMenu.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Dialog dialog = new Dialog(aC, R.style.Dialog);
				dialog.setContentView(R.layout.menu_clic);
				dialog.setCanceledOnTouchOutside(true);
				dialog.show();
				MenuActivitats ma = new MenuActivitats(timer);
				ma.butsMenu(dialog, aC, vecDraw);
			}
		});
	}

	// @Override
	protected void onDestroy() {
		if (maxTime != 0) timer.cancel();
		super.onDestroy();
		sound.unloadAll();
	}
	
	@Override
	public void onBackPressed() {
	}
/*
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
*/

	private void agafarDades() {
		CO.tr1 = (TableRow) findViewById(R.id.tr1);
		CO.tr2 = (TableRow) findViewById(R.id.tr2);
		CO.tr3 = (TableRow) findViewById(R.id.tr3);
		CO.tr4 = (TableRow) findViewById(R.id.tr4);
		CO.tr5 = (TableRow) findViewById(R.id.tr5);
		CO.tr6 = (TableRow) findViewById(R.id.tr6);
		CO.tr7 = (TableRow) findViewById(R.id.tr7);
		CO.tr8 = (TableRow) findViewById(R.id.tr8);
		CO.tr9 = (TableRow) findViewById(R.id.tr9);
		CO.tr10 = (TableRow) findViewById(R.id.tr10);
		CO.tl = (TableLayout) findViewById(R.id.tl);
		CO.tl2 = (TableLayout) findViewById(R.id.tl2);
		// CO.sv = (ScrollView)findViewById(R.id.sv); // NO fa falta scroll ja
		// CO.bdown = (ImageView) findViewById(R.id.bdown);
		// CO.bup = (ImageView) findViewById(R.id.bup);

		CO.tr1.setPadding(30, 0, 0, 0);
		CO.tr2.setPadding(30, 0, 0, 0);
		CO.tr3.setPadding(30, 0, 0, 0);
		CO.tr4.setPadding(30, 0, 0, 0);
		CO.tr5.setPadding(30, 0, 0, 0);
		CO.tr6.setPadding(30, 0, 0, 0);
		CO.tr7.setPadding(30, 0, 0, 0);
		CO.tr8.setPadding(30, 0, 0, 0);
		CO.tr9.setPadding(30, 0, 0, 0);
		CO.tr10.setPadding(30, 0, 0, 0);

		agafarCaselles();

		CO.miss = (TextView) findViewById(R.id.missatge);
		CO.miss2 = (TextView) findViewById(R.id.missatge2);
		CO.missCorrectes = (TextView) findViewById(R.id.correcte);
		CO.missCorrectes2 = (TextView) findViewById(R.id.correcte2);
		CO.cas1 = (TextView) findViewById(R.id.cas1);
		CO.cas2 = (TextView) findViewById(R.id.cas2);
		CO.name = (TextView) findViewById(R.id.titulo);

		CO.missCorrectes.invalidate();
		CO.missCorrectes2.invalidate();
		CO.miss.invalidate();
		CO.miss2.invalidate();
		CO.cas1.invalidate();
		CO.cas2.invalidate();

		CO.miss.setTextColor(Color.WHITE);
		CO.miss.setPadding(0, 0, 0, 0);
		CO.miss2.setTextColor(Color.WHITE);
		CO.miss2.setPadding(0, 0, 0, 0);
		CO.missCorrectes.setTextColor(Color.WHITE);
		CO.missCorrectes.setPadding(0, 0, 0, 0);
		CO.missCorrectes2.setTextColor(Color.WHITE);
		CO.missCorrectes2.setPadding(0, 0, 0, 0);
		CO.cas1.setTextColor(Color.WHITE);
		CO.cas1.setPadding(0, 0, 0, 0);
		CO.cas2.setTextColor(Color.WHITE);
		CO.cas2.setPadding(0, 0, 0, 0);
		CO.name.setTextColor(Color.WHITE);

		CO.correcte = 0;
		CO.incorrecte = CO.casIni;

		CO.p1 = "<buit>";

		if (Parser.getActivitats().elementAt(CO.activitatActual).getName() != null)
			CO.name.setText(Parser.getActivitats()
					.elementAt(CO.activitatActual).getName());
		else
			CO.name.setText("Activitat JClic");

		if (CO.rows == 1) {
			// cols per tant miro altura: esq-top-dret-bott
			CO.tr1.setPadding(30, 97, 0, 0);
			CO.tr5.setPadding(30, 0, 0, 20);
			CO.tr6.setPadding(30, 100, 0, 0);
			CO.tr10.setPadding(30, 0, 0, 20);
			CO.cas2.setPadding(0, 0, 0, 7);

		} else if (CO.rows == 2) {
			CO.tr1.setPadding(30, 36, 0, 0);
			CO.tr6.setPadding(30, 36, 0, 0);
			CO.cas2.setPadding(0, 0, 0, 7);

		} else if (CO.rows == 3) {
			CO.tr1.setPadding(30, 30, 0, 0);
			CO.tr6.setPadding(30, 30, 0, 0);
			CO.cas2.setPadding(0, 0, 0, 7);

		} else if (CO.rows == 4) {
			CO.tr1.setPadding(30, 14, 0, 0);
			CO.cas1.setPadding(0, 0, 0, 7);
			CO.cas2.setPadding(0, 0, 0, 7);
		} else {
			// rows == 5
			CO.tr1.setPadding(30, 8, 0, 0);
			CO.cas1.setPadding(0, 0, 0, 7);
			CO.cas2.setPadding(0, 0, 0, 7);
		}

		if (CO.colorBG != null) {
			CO.bg = Puzzle.agafarColor(CO.colorBG);
		} else
			CO.bg = Color.BLACK;

		if (CO.colorFG != null) {
			CO.fg = Puzzle.agafarColor(CO.colorFG);
		} else
			CO.fg = Color.WHITE;

		for (int i = 0; i < CO.vecCaselles.size(); i++) {
			if (CO.vecCaselles.elementAt(i) != null) {
				CO.vecCaselles.elementAt(i).setBackgroundColor(CO.bg);
				CO.vecCaselles.elementAt(i).setTextColor(CO.fg);
				CO.vecCaselles.elementAt(i).setPadding(10, 15, 10, 10);
				CO.vecCaselles.elementAt(i).setMaxLines(3);
				CO.vecCaselles.elementAt(i).setText(CO.entrada.elementAt(i));
				if (CO.imatge != null) {
					CO.vecCaselles.elementAt(i).setBackgroundColor(
							Color.TRANSPARENT);
					CO.vecCaselles.elementAt(i).setTextColor(Color.TRANSPARENT);
				}
				reestructurarCaselles(CO.vecCaselles.elementAt(i));
			}
		}
		for (int i = 0; i < CO.vecCasellesSort.size(); i++) {
			if (CO.vecCasellesSort.elementAt(i) != null) {
				CO.vecCasellesSort.elementAt(i).setBackgroundColor(Color.GRAY);
				CO.vecCasellesSort.elementAt(i).setTextColor(Color.TRANSPARENT);
				CO.vecCasellesSort.elementAt(i).setPadding(10, 15, 10, 10);
				CO.vecCasellesSort.elementAt(i).setMaxLines(3);
				CO.vecCasellesSort.elementAt(i)
						.setText(CO.sortida.elementAt(i));
				reestructurarCaselles(CO.vecCasellesSort.elementAt(i));
			}
		}
		bloquejarJoc(false);
		setMissatges();
	}

	private void agafarCaselles() {
		boolean anterior = false;
		int caselles = CO.entrada.size();

		if (findViewById(R.id.pos1) != null && caselles > 0) {
			if (CO.entrada.elementAt(0) != null) {
				CO.pos1 = (TextView) findViewById(R.id.pos1);
				CO.vecCaselles.addElement(CO.pos1);

				CO.pos21 = (TextView) findViewById(R.id.pos21);
				CO.vecCasellesSort.addElement(CO.pos21);
				anterior = true;
			} else {
				reiniciarCasella(CO.pos1);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos21);
				CO.vecCasellesSort.addElement(null);
			}
		}

		if (findViewById(R.id.pos2) != null && anterior && caselles > 1) {
			if (CO.entrada.elementAt(1) != null) {
				CO.pos2 = (TextView) findViewById(R.id.pos2);
				CO.vecCaselles.addElement(CO.pos2);

				CO.pos22 = (TextView) findViewById(R.id.pos22);
				CO.vecCasellesSort.addElement(CO.pos22);
			} else {
				reiniciarCasella(CO.pos2);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos22);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos2);
			reiniciarCasella(CO.pos22);
			anterior = false;
		}

		if (findViewById(R.id.pos3) != null && anterior && caselles > 2) {
			if (CO.entrada.elementAt(2) != null) {
				CO.pos3 = (TextView) findViewById(R.id.pos3);
				CO.vecCaselles.addElement(CO.pos3);

				CO.pos23 = (TextView) findViewById(R.id.pos23);
				CO.vecCasellesSort.addElement(CO.pos23);
			} else {
				reiniciarCasella(CO.pos3);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos23);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos3);
			reiniciarCasella(CO.pos23);
			anterior = false;
		}

		if (findViewById(R.id.pos4) != null && anterior && caselles > 3) {
			if (CO.entrada.elementAt(3) != null) {
				CO.pos4 = (TextView) findViewById(R.id.pos4);
				CO.vecCaselles.addElement(CO.pos4);

				CO.pos24 = (TextView) findViewById(R.id.pos24);
				CO.vecCasellesSort.addElement(CO.pos24);
			} else {
				reiniciarCasella(CO.pos4);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos24);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos4);
			reiniciarCasella(CO.pos24);
			anterior = false;
		}

		if (findViewById(R.id.pos5) != null && anterior && caselles > 4) {
			if (CO.entrada.elementAt(4) != null) {
				CO.pos5 = (TextView) findViewById(R.id.pos5);
				CO.vecCaselles.addElement(CO.pos5);

				CO.pos25 = (TextView) findViewById(R.id.pos25);
				CO.vecCasellesSort.addElement(CO.pos25);
			} else {
				reiniciarCasella(CO.pos5);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos25);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos5);
			reiniciarCasella(CO.pos25);
			anterior = false;
		}

		if (findViewById(R.id.pos6) != null && anterior && caselles > 5) {
			if (CO.entrada.elementAt(5) != null) {
				CO.pos6 = (TextView) findViewById(R.id.pos6);
				CO.vecCaselles.addElement(CO.pos6);

				CO.pos26 = (TextView) findViewById(R.id.pos26);
				CO.vecCasellesSort.addElement(CO.pos26);
			} else {
				reiniciarCasella(CO.pos6);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos26);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos6);
			reiniciarCasella(CO.pos26);
			anterior = false;
		}

		if (findViewById(R.id.pos7) != null && anterior && caselles > 6) {
			if (CO.entrada.elementAt(6) != null) {
				CO.pos7 = (TextView) findViewById(R.id.pos7);
				CO.vecCaselles.addElement(CO.pos7);

				CO.pos27 = (TextView) findViewById(R.id.pos27);
				CO.vecCasellesSort.addElement(CO.pos27);
			} else {
				reiniciarCasella(CO.pos7);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos27);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos7);
			reiniciarCasella(CO.pos27);
			anterior = false;
		}

		if (findViewById(R.id.pos8) != null && anterior && caselles > 7) {
			if (CO.entrada.elementAt(7) != null) {
				CO.pos8 = (TextView) findViewById(R.id.pos8);
				CO.vecCaselles.addElement(CO.pos8);

				CO.pos28 = (TextView) findViewById(R.id.pos28);
				CO.vecCasellesSort.addElement(CO.pos28);
			} else {
				reiniciarCasella(CO.pos8);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos28);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos8);
			reiniciarCasella(CO.pos28);
			anterior = false;
		}

		if (findViewById(R.id.pos9) != null && anterior && caselles > 8) {
			if (CO.entrada.elementAt(8) != null) {
				CO.pos9 = (TextView) findViewById(R.id.pos9);
				CO.vecCaselles.addElement(CO.pos9);

				CO.pos29 = (TextView) findViewById(R.id.pos29);
				CO.vecCasellesSort.addElement(CO.pos29);
			} else {
				reiniciarCasella(CO.pos9);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos29);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos9);
			reiniciarCasella(CO.pos29);
			anterior = false;
		}

		if (findViewById(R.id.pos10) != null && anterior && caselles > 9) {
			if (CO.entrada.elementAt(9) != null) {
				CO.pos10 = (TextView) findViewById(R.id.pos10);
				CO.vecCaselles.addElement(CO.pos10);

				CO.pos30 = (TextView) findViewById(R.id.pos30);
				CO.vecCasellesSort.addElement(CO.pos30);
			} else {
				reiniciarCasella(CO.pos10);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos30);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos10);
			reiniciarCasella(CO.pos30);
			anterior = false;
		}

		if (findViewById(R.id.pos11) != null && anterior && caselles > 10) {
			if (CO.entrada.elementAt(10) != null) {
				CO.pos11 = (TextView) findViewById(R.id.pos11);
				CO.vecCaselles.addElement(CO.pos11);

				CO.pos31 = (TextView) findViewById(R.id.pos31);
				CO.vecCasellesSort.addElement(CO.pos31);
			} else {
				reiniciarCasella(CO.pos11);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos31);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos11);
			reiniciarCasella(CO.pos31);
			anterior = false;
		}

		if (findViewById(R.id.pos12) != null && anterior && caselles > 11) {
			if (CO.entrada.elementAt(11) != null) {
				CO.pos12 = (TextView) findViewById(R.id.pos12);
				CO.vecCaselles.addElement(CO.pos12);

				CO.pos32 = (TextView) findViewById(R.id.pos32);
				CO.vecCasellesSort.addElement(CO.pos32);
			} else {
				reiniciarCasella(CO.pos12);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos32);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos12);
			reiniciarCasella(CO.pos32);
			anterior = false;
		}

		if (findViewById(R.id.pos13) != null && anterior && caselles > 12) {
			if (CO.entrada.elementAt(12) != null) {
				CO.pos13 = (TextView) findViewById(R.id.pos13);
				CO.vecCaselles.addElement(CO.pos13);

				CO.pos33 = (TextView) findViewById(R.id.pos33);
				CO.vecCasellesSort.addElement(CO.pos33);
			} else {
				reiniciarCasella(CO.pos13);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos33);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos13);
			reiniciarCasella(CO.pos33);
			anterior = false;
		}

		if (findViewById(R.id.pos14) != null && anterior && caselles > 13) {
			if (CO.entrada.elementAt(13) != null) {
				CO.pos14 = (TextView) findViewById(R.id.pos14);
				CO.vecCaselles.addElement(CO.pos14);

				CO.pos34 = (TextView) findViewById(R.id.pos34);
				CO.vecCasellesSort.addElement(CO.pos34);
			} else {
				reiniciarCasella(CO.pos14);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos34);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos14);
			reiniciarCasella(CO.pos34);
			anterior = false;
		}

		if (findViewById(R.id.pos15) != null && anterior && caselles > 14) {
			if (CO.entrada.elementAt(14) != null) {
				CO.pos15 = (TextView) findViewById(R.id.pos15);
				CO.vecCaselles.addElement(CO.pos15);

				CO.pos35 = (TextView) findViewById(R.id.pos35);
				CO.vecCasellesSort.addElement(CO.pos35);
			} else {
				reiniciarCasella(CO.pos15);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos35);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos15);
			reiniciarCasella(CO.pos35);
			anterior = false;
		}

		if (findViewById(R.id.pos16) != null && anterior && caselles > 15) {
			if (CO.entrada.elementAt(15) != null) {
				CO.pos16 = (TextView) findViewById(R.id.pos16);
				CO.vecCaselles.addElement(CO.pos16);

				CO.pos36 = (TextView) findViewById(R.id.pos36);
				CO.vecCasellesSort.addElement(CO.pos36);
			} else {
				reiniciarCasella(CO.pos16);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos36);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos16);
			reiniciarCasella(CO.pos36);
			anterior = false;
		}

		if (findViewById(R.id.pos17) != null && anterior && caselles > 16) {
			if (CO.entrada.elementAt(16) != null) {
				CO.pos17 = (TextView) findViewById(R.id.pos17);
				CO.vecCaselles.addElement(CO.pos17);

				CO.pos37 = (TextView) findViewById(R.id.pos37);
				CO.vecCasellesSort.addElement(CO.pos37);
			} else {
				reiniciarCasella(CO.pos17);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos37);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos17);
			reiniciarCasella(CO.pos37);
			anterior = false;
		}

		if (findViewById(R.id.pos18) != null && anterior && caselles > 17) {
			if (CO.entrada.elementAt(17) != null) {
				CO.pos18 = (TextView) findViewById(R.id.pos18);
				CO.vecCaselles.addElement(CO.pos18);

				CO.pos38 = (TextView) findViewById(R.id.pos38);
				CO.vecCasellesSort.addElement(CO.pos38);
			} else {
				reiniciarCasella(CO.pos18);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos38);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos18);
			reiniciarCasella(CO.pos38);
			anterior = false;
		}

		if (findViewById(R.id.pos19) != null && anterior && caselles > 18) {
			if (CO.entrada.elementAt(18) != null) {
				CO.pos19 = (TextView) findViewById(R.id.pos19);
				CO.vecCaselles.addElement(CO.pos19);

				CO.pos39 = (TextView) findViewById(R.id.pos39);
				CO.vecCasellesSort.addElement(CO.pos39);
			} else {
				reiniciarCasella(CO.pos19);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos39);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos19);
			reiniciarCasella(CO.pos39);
			anterior = false;
		}

		if (findViewById(R.id.pos20) != null && anterior && caselles > 19) {
			if (CO.entrada.elementAt(19) != null) {
				CO.pos20 = (TextView) findViewById(R.id.pos20);
				CO.vecCaselles.addElement(CO.pos20);

				CO.pos40 = (TextView) findViewById(R.id.pos40);
				CO.vecCasellesSort.addElement(CO.pos40);
			} else {
				reiniciarCasella(CO.pos20);
				CO.vecCaselles.addElement(null);

				reiniciarCasella(CO.pos40);
				CO.vecCasellesSort.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos20);
			reiniciarCasella(CO.pos40);
		}
	}

	/*
	 * private void reestructurarCaselles(TextView pos) { if(CO.cols == 1){
	 * pos.setWidth(250); width = 250; } else if(CO.cols == 2){
	 * pos.setWidth(120); width = 120; } else if(CO.cols == 3){
	 * pos.setWidth(80); width = 80; } else { //cols == 4 pos.setWidth(60);
	 * width = 60; }
	 * 
	 * if(CO.rows == 1 || CO.rows == 2){ pos.setHeight(100); pos.setMaxLines(4);
	 * height = 100; } else if(CO.rows == 3){ pos.setHeight(75);
	 * pos.setMaxLines(3); height = 75; } else if(CO.rows == 4){
	 * pos.setHeight(65); pos.setMaxLines(2); height = 65; } else { //CO.rows ==
	 * 5 pos.setHeight(57); pos.setMaxLines(2); height = 57; } }
	 */

	private void reestructurarCaselles(TextView pos) {
		// ImageView myView = (ImageView)getWindow().findViewById(R.id.ll);
		Display display = getWindowManager().getDefaultDisplay();

		CO.cMaxHor = (display.getWidth() / 2);
		CO.cMaxVert = display.getHeight() - display.getHeight() / 5;

		if (CO.cols == 1) {
			pos.setWidth(CO.cMaxHor); // cMaxHor es la distancia horitzontal
										// maxima que tenim.
			width = CO.cMaxHor;
		} else if (CO.cols == 2) {
			pos.setWidth(CO.cMaxHor / 2 - CO.cMaxHor / 20);
			width = CO.cMaxHor / 2 - CO.cMaxHor / 20;
		} else if (CO.cols == 3) {
			pos.setWidth(CO.cMaxHor / 3 - CO.cMaxHor / 20);
			width = CO.cMaxHor / 3 - CO.cMaxHor / 20;
		} else {
			// cols == 4
			pos.setWidth(CO.cMaxHor / 4 - CO.cMaxHor / 20);
			width = CO.cMaxHor / 4 - CO.cMaxHor / 20;
		}

		// Aqui a les columnes fa ago raro amb setMaxLines i amb la OR del
		// principi
		// i m'agradaria que ho miressim i tal, xq no ho acabo d'entendre.

		if (CO.rows == 1 || CO.rows == 2) {
			pos.setHeight(CO.cMaxVert / 2 - CO.cMaxVert / 10);
			pos.setMaxLines(4);
			height = CO.cMaxVert / 2 - CO.cMaxVert / 10;
		} else if (CO.rows == 3) {
			pos.setHeight(CO.cMaxVert / 3 - CO.cMaxVert / 10);
			pos.setMaxLines(3);
			height = CO.cMaxVert / 3 - CO.cMaxVert / 10;
		} else if (CO.rows == 4) {
			pos.setHeight(CO.cMaxVert / 4 - CO.cMaxVert / 10);
			pos.setMaxLines(2);
			height = CO.cMaxVert / 4 - CO.cMaxVert / 10;
		} else {
			// CO.rows == 5
			pos.setHeight(CO.cMaxVert / 5 - CO.cMaxVert / 10);
			pos.setMaxLines(2);
			height = CO.cMaxVert / 5 - CO.cMaxVert / 10;
		}
	}

	private void setOnClickListener() {
		/*
		 * CO.bdown.setOnClickListener(new View.OnClickListener() { public void
		 * onClick(View view) { CO.sv.scrollTo(0, 600); } });
		 * 
		 * CO.bup.setOnClickListener(new View.OnClickListener() { public void
		 * onClick(View view) { CO.sv.scrollTo(0, 0); } });
		 */

		CO.missCorrectes.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (CO.casIni == CO.correcte || contador == maxIntents
						|| contadorTemps == maxTime) {
					Intent iSeg = new Intent(DoublePuzzle.this, Puzzle.class);
					startActivity(iSeg);
					finish();
				}
			}
		});

		CO.missCorrectes2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (CO.casIni == CO.correcte || contador == maxIntents
						|| contadorTemps == maxTime) {
					Intent iSeg = new Intent(DoublePuzzle.this, Puzzle.class);
					startActivity(iSeg);
					finish();
				}
			}
		});

		CO.miss.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (CO.casIni == CO.correcte || contador == maxIntents
						|| contadorTemps == maxTime) {
					Intent iSeg = new Intent(DoublePuzzle.this, Puzzle.class);
					startActivity(iSeg);
					finish();
				}
			}
		});

		CO.miss2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (CO.casIni == CO.correcte) {
					Intent iSeg = new Intent(DoublePuzzle.this, Puzzle.class);
					startActivity(iSeg);
					finish();
				}
			}
		});

		// Si apreto dues vecCaselles deselecciono el primer que tenia
		for (int i = 0; i < CO.vecCaselles.size(); i++) {
			if (CO.vecCaselles.elementAt(i) != null) {
				final TextView pos = CO.vecCaselles.elementAt(i);

				pos.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {

						sound.playClick();

						if (posAgafada == pos) {
							// estic agafant la propia casella que ha he agafat
							CO.p1 = "<buit>";

							if (CO.imatge != null) {
								pos.setTextColor(Color.TRANSPARENT);
								pos.setBackgroundColor(Color.TRANSPARENT);
								String text = (String) pos.getText();

								int indexEntr = CO.entrada.indexOf(text);
								int indexSort = CO.sortida.indexOf(text);

								vecDraw.elementAt(indexSort).setAlpha(255);

								CO.vecCaselles.elementAt(indexEntr)
										.setBackgroundDrawable(
												vecDraw.elementAt(indexSort));
							} else {
								CO.cas1.setText("");
								CO.cas2.setText("");
								pos.setTextColor(CO.fg);
								pos.setBackgroundColor(CO.bg);
							}
							posAgafada = null;
						} else {
							if (!CO.p1.equals("<buit>")) {
								// tinc una casella agafada, agafo nova
								if (CO.imatge != null) {
									posAgafada.setTextColor(Color.TRANSPARENT);
									posAgafada
											.setBackgroundColor(Color.TRANSPARENT);

									String text = (String) posAgafada.getText();

									int indexEntr = CO.entrada.indexOf(text);
									int indexSort = CO.sortida.indexOf(text);

									vecDraw.elementAt(indexSort).setAlpha(255);

									CO.vecCaselles
											.elementAt(indexEntr)
											.setBackgroundDrawable(
													vecDraw.elementAt(indexSort));
								} else {
									posAgafada.setBackgroundColor(CO.bg);
									posAgafada.setTextColor(CO.fg);
								}
							}
							CO.p1 = (String) pos.getText();

							if (CO.imatge != null) {
								pos.setTextColor(Color.TRANSPARENT);
								pos.setBackgroundColor(Color.TRANSPARENT);

								String text = (String) pos.getText();

								int indexEntr = CO.entrada.indexOf(text);
								int indexSort = CO.sortida.indexOf(text);

								vecDraw.elementAt(indexSort).setAlpha(100);

								CO.vecCaselles.elementAt(indexEntr)
										.setBackgroundDrawable(
												vecDraw.elementAt(indexSort));
							} else {
								CO.cas1.setText(CO.p1);
								CO.cas2.setText(CO.p1);

								pos.setBackgroundColor(Color.WHITE);
								pos.setTextColor(Color.BLACK);
							}
							posAgafada = pos;
						}
					}
				});
			}
		}

		// si tinc un vecCaselles seleccionat, comprovo amb aquest
		for (int i = 0; i < CO.vecCasellesSort.size(); i++) {
			if (CO.vecCasellesSort.elementAt(i) != null) {
				final TextView pos = CO.vecCasellesSort.elementAt(i);

				pos.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
						if (!CO.p1.equalsIgnoreCase("<buit>")) {
							// tinc un valor agafat, miro si va aqui
							contador++;
							if (CO.p1.equalsIgnoreCase((String) pos.getText())) {
								// el valor es el correcte
								// intercanvio les posicions
								pos.setText(CO.p1);
								pos.setBackgroundColor(CO.bg);
								pos.setTextColor(CO.fg);
								pos.setEnabled(false);
								posAgafada.setEnabled(false);
								posAgafada.setBackgroundColor(Color.GRAY);
								posAgafada.setTextColor(Color.TRANSPARENT);
								CO.correcte++;
								CO.incorrecte--;
								sound.playAction_ok();

								if (CO.imatge != null) {
									// faig que tingui la imatge i no el text
									pos.setTextColor(Color.TRANSPARENT);
									pos.setBackgroundColor(Color.TRANSPARENT);
									String text = (String) pos.getText();
									int index = CO.sortida.indexOf(text);
									vecDraw.elementAt(index).setAlpha(255);
									CO.vecCasellesSort.elementAt(index)
											.setBackgroundDrawable(
													vecDraw.elementAt(index));
								}
							} else {
								// el valor no es correcte, poso posAgafada a
								// null
								posAgafada.setBackgroundColor(CO.bg);
								posAgafada.setTextColor(CO.fg);
								sound.playActionError();

								if (CO.imatge != null) {
									// faig que tingui la imatge i no el text
									posAgafada.setTextColor(Color.TRANSPARENT);
									posAgafada
											.setBackgroundColor(Color.TRANSPARENT);

									String text = (String) posAgafada.getText();

									int indexEntr = CO.entrada.indexOf(text);
									int indexSort = CO.sortida.indexOf(text);

									vecDraw.elementAt(indexSort).setAlpha(255);

									CO.vecCaselles
											.elementAt(indexEntr)
											.setBackgroundDrawable(
													vecDraw.elementAt(indexSort));
								}
							}
							posAgafada = null;
							CO.p1 = "<buit>";
							CO.cas1.setText("");
							CO.cas2.setText("");
							setMissatges();
						}
					}
				});
			}
		}
	}

	private void setMissatges() {
		if (CO.solucioVisible) {
			CO.miss.setText("");
			CO.miss2.setText("");
			CO.missCorrectes.setText("");
			CO.missCorrectes2.setText("");
			CO.cas1.setText("");
			CO.cas2.setText("");
			CO.p1 = "<buit>";
		} else {
			final Context aC = this;
			Dialog dialog = new Dialog(aC, R.style.Dialog);
			dialog.setContentView(R.layout.menu_clic);
			dialog.setCanceledOnTouchOutside(true);
			MenuActivitats ma = new MenuActivitats(timer);
			ma.butsMenu(dialog, aC, vecDraw);
			TextView textFinal = (TextView) dialog.findViewById(R.id.tMenuClic);
			if (CO.correcte == CO.casIni) {
				// Hem acabat el joc
				sound.playFinished_ok();
				if (maxTime != 0)
					timer.cancel();
				if (Parser.getActivitats().elementAt(CO.activitatActual)
						.getMissatgeFi() != null) {
					textFinal.setText(Parser.getActivitats()
							.elementAt(CO.activitatActual).getMissatgeFi());
					/*
					 * CO.miss2.setText(Parser.getActivitats()
					 * .elementAt(CO.activitatActual).getMissatgeFi());
					 */
				} else {
					textFinal.setText("Joc finalitzat!");
					CO.miss2.setText("Joc finalitzat!");
				}

				// CO.missCorrectes.setText("Prem aqui per continuar.");
				CO.missCorrectes.setBackgroundColor(Color.WHITE);
				CO.missCorrectes.setTextColor(Color.BLACK);

				// CO.missCorrectes2.setText("Prem aqui per continuar.");
				CO.missCorrectes2.setBackgroundColor(Color.WHITE);
				CO.missCorrectes2.setTextColor(Color.BLACK);
				dialog.show();
				bloquejarJoc(true);
				if (CO.menu != null)
					CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);

			} else if ((CO.correcte != CO.casIni && maxIntents != 0 && maxIntents == contador)
					|| contadorTemps == maxTime && maxTime != 0) {
				sound.playFinished_error();
				if (maxTime != 0)
					timer.cancel();
				/*
				 * if(Parser.getActivitats().elementAt(CO.activitatActual).
				 * getMissatgeFi() != null) {
				 * CO.miss.setText(Parser.getActivitats
				 * ().elementAt(CO.activitatActual).getMissatgeFi());
				 * CO.miss2.setText
				 * (Parser.getActivitats().elementAt(CO.activitatActual
				 * ).getMissatgeFi()); } else {
				 */
				if (maxTime != 0 && contadorTemps == maxTime) {
					textFinal.setText("S'ha acabat el temps!");
					CO.miss2.setText("S'ha acabat el temps!");
				} else {
					textFinal.setText("Has superat els intents maxims!");
					CO.miss2.setText("Has superat els intents maxims!");
				}
				CO.missCorrectes.setText("Prem aqui per continuar.");
				CO.missCorrectes.setBackgroundColor(Color.WHITE);
				CO.missCorrectes.setTextColor(Color.BLACK);

				CO.missCorrectes2.setText("Prem aqui per continuar.");
				CO.missCorrectes2.setBackgroundColor(Color.WHITE);
				CO.missCorrectes2.setTextColor(Color.BLACK);
				dialog.show();
				bloquejarJoc(true);
				if (CO.menu != null)
					CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);
			} else {
				if (Parser.getActivitats().elementAt(CO.activitatActual)
						.getMissatgeIni() != null) {
					CO.miss.setText(Parser.getActivitats()
							.elementAt(CO.activitatActual).getMissatgeIni());
					CO.miss2.setText(Parser.getActivitats()
							.elementAt(CO.activitatActual).getMissatgeIni());
				} else {
					CO.miss.setText("Comenca el joc!");
					CO.miss2.setText("Comenca el joc!");
				}
				int displayedIntents;
				if (IntentCountDown && maxIntents != 0) {
					displayedIntents = maxIntents - contador;
				} else
					displayedIntents = contador;
				int displayedTime;
				if (TimeCountDown && maxTime != 0) {
					displayedTime = maxTime - contadorTemps;
				} else
					displayedTime = contadorTemps;
				aciertos.setText(Integer.toString(CO.correcte));
				intentos.setText(Integer.toString(displayedIntents));
			}
		}
	}

	private void reiniciarCasella(TextView posicio) {
		if (posicio != null) {
			posicio.setBackgroundColor(Color.TRANSPARENT);
			posicio.setTextColor(Color.TRANSPARENT);
			posicio.setPadding(0, 0, 0, 0);
			posicio.setHeight(0);
			posicio.setWidth(0);
			posicio.setText(null);
		}
	}

	private void bloquejarJoc(boolean bloquejar) {
		// Fem que es bloquegi o desbloquegi l'activitat
		for (int i = 0; i < CO.vecCaselles.size(); i++) {
			if (CO.vecCaselles.elementAt(i) != null)
				CO.vecCaselles.elementAt(i).setEnabled(!bloquejar);
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

	/*
	 * public boolean onCreateOptionsMenu(Menu menu) {
	 * super.onCreateOptionsMenu(menu); CO.menu = menu; CO.menu.clear();
	 * CO.menu.add(0, MENU_ANT, 0, R.string.menu_ant); CO.menu.add(0, MENU_SEG,
	 * 0, R.string.menu_seg); CO.menu.add(0, MENU_SOLUCIO, 0,
	 * R.string.menu_solucio); CO.menu.add(0, MENU_AJUDA, 0,
	 * R.string.menu_ajuda); CO.menu.add(0, MENU_INICI, 0, R.string.menu_inici);
	 * CO.menu.add(0, MENU_SORTIR, 0, R.string.menu_sortir);
	 * 
	 * CO.menu.getItem(MENU_ANT).setIcon(android.R.drawable.ic_media_rew);
	 * CO.menu.getItem(MENU_SEG).setIcon(android.R.drawable.ic_media_ff);
	 * CO.menu.getItem(MENU_SOLUCIO).setIcon(
	 * android.R.drawable.btn_star_big_off);
	 * CO.menu.getItem(MENU_AJUDA).setIcon(android.R.drawable.ic_menu_help);
	 * CO.menu.getItem(MENU_INICI).setIcon(android.R.drawable.ic_menu_revert);
	 * CO.menu.getItem(MENU_SORTIR).setIcon(
	 * android.R.drawable.ic_menu_close_clear_cancel);
	 * 
	 * // Configuro els botons d'anterior i seguent
	 * CO.menu.getItem(MENU_SEG).setEnabled(true);
	 * CO.menu.getItem(MENU_ANT).setEnabled(true);
	 * 
	 * if (CO.activitatActual < 1) { // estem a la primera activitat, pel que no
	 * podem habilitar // l'anterior
	 * CO.menu.getItem(MENU_ANT).setEnabled(false); } if (CO.activitatActual ==
	 * Parser.getActivitats().size() - 1) { // estem a l'ultima activitat, pel
	 * que no podem habilitar el seguent
	 * CO.menu.getItem(MENU_SEG).setEnabled(false); }
	 * 
	 * if (CO.mostrarSolucio) CO.menu.getItem(MENU_SOLUCIO).setEnabled(true);
	 * else CO.menu.getItem(MENU_SOLUCIO).setEnabled(false); return true; }
	 * 
	 * public boolean onOptionsItemSelected(MenuItem item) { switch
	 * (item.getItemId()) { case MENU_ANT: CO.activitatActual =
	 * CO.activitatActual - 2; Intent iAnt = new Intent(this, Puzzle.class);
	 * startActivity(iAnt); finish(); return true; case MENU_SEG: Intent iSeg =
	 * new Intent(this, Puzzle.class); startActivity(iSeg); finish(); return
	 * true; case MENU_AJUDA: Dialog ajuda = new
	 * AlertDialog.Builder(DoublePuzzle.this) .setIcon(R.drawable.jclic_aqua)
	 * .setTitle("Ajuda") .setPositiveButton("D'acord", null) .setMessage(
	 * "Col·loca les caselles al seu lloc corresponent del panell de sota.\n" +
	 * "Pots canviar de panell prement la fletxa, o bé desplaçan-te per la pantalla."
	 * ) .create(); ajuda.show(); return true; case MENU_SOLUCIO: if
	 * (!CO.solucioVisible) { // Vull mostrar la solucio CO.vecActual = new
	 * Vector<CharSequence>(); for (int i = 0; i < CO.vecCaselles.size(); i++) {
	 * if (CO.vecCaselles.elementAt(i) != null) {
	 * CO.vecActual.addElement(CO.vecCaselles.elementAt(i) .getText());
	 * CO.vecCaselles.elementAt(i).setText( CO.sortida.elementAt(i));
	 * CO.vecCaselles.elementAt(i).setBackgroundColor(CO.bg);
	 * CO.vecCaselles.elementAt(i).setTextColor(CO.fg);
	 * 
	 * if (CO.imatge != null) { int indexSort =
	 * CO.sortida.indexOf(CO.vecCaselles .elementAt(i).getText());
	 * 
	 * CO.vecCaselles.elementAt(i).setBackgroundColor( Color.TRANSPARENT);
	 * CO.vecCaselles.elementAt(i).setTextColor( Color.TRANSPARENT);
	 * 
	 * vecDraw.elementAt(indexSort).setAlpha(250);
	 * 
	 * CO.vecCaselles.elementAt(i).setBackgroundDrawable(
	 * vecDraw.elementAt(indexSort)); } } else CO.vecActual.addElement(null); }
	 * bloquejarJoc(true); CO.solucioVisible = true; posAgafada = null;
	 * setMissatges(); CO.menu.getItem(MENU_SOLUCIO)
	 * .setTitle(R.string.menu_in_solucio);
	 * CO.menu.getItem(MENU_ANT).setEnabled(false);
	 * CO.menu.getItem(MENU_SEG).setEnabled(false); } else { // Estic mostrant
	 * la solucio i vull continuar for (int i = 0; i < CO.vecCaselles.size();
	 * i++) { if (CO.vecCaselles.elementAt(i) != null) {
	 * CO.vecCaselles.elementAt(i).setText( CO.vecActual.elementAt(i));
	 * CO.vecCaselles.elementAt(i).setTextColor(CO.fg);
	 * CO.vecCaselles.elementAt(i).setBackgroundColor(CO.bg);
	 * 
	 * if (CO.imatge != null) { int indexSort =
	 * CO.sortida.indexOf(CO.vecCaselles .elementAt(i).getText());
	 * 
	 * CO.vecCaselles.elementAt(i).setBackgroundColor( Color.TRANSPARENT);
	 * CO.vecCaselles.elementAt(i).setTextColor( Color.TRANSPARENT);
	 * 
	 * vecDraw.elementAt(indexSort).setAlpha(250);
	 * 
	 * CO.vecCaselles.elementAt(i).setBackgroundDrawable(
	 * vecDraw.elementAt(indexSort)); } } } bloquejarJoc(false);
	 * CO.solucioVisible = false; setMissatges();
	 * CO.menu.getItem(MENU_SOLUCIO).setTitle(R.string.menu_solucio);
	 * 
	 * // Configuracio del menu per ant i seguent if (CO.activitatActual < 1) {
	 * // estem a la primera activitat, pel que nomes habilitem // seguent
	 * CO.menu.getItem(MENU_SEG).setEnabled(true); } else if (CO.activitatActual
	 * == Parser.getActivitats().size() - 1) { // estem a l'ultima activitat,
	 * pel que no podem habilitar el // seguent
	 * CO.menu.getItem(MENU_ANT).setEnabled(true); } else {
	 * CO.menu.getItem(MENU_SEG).setEnabled(true);
	 * CO.menu.getItem(MENU_ANT).setEnabled(true); } } return true; case
	 * MENU_SORTIR: AlertDialog.Builder builder = new AlertDialog.Builder(this);
	 * builder.setIcon(R.drawable.jclic_aqua);
	 * builder.setMessage("Estàs segur de que vols sortir?")
	 * .setCancelable(false) .setPositiveButton("Sí", new
	 * DialogInterface.OnClickListener() { public void onClick(DialogInterface
	 * dialog, int id) { DoublePuzzle.this.finish(); } })
	 * .setNegativeButton("No", new DialogInterface.OnClickListener() { public
	 * void onClick(DialogInterface dialog, int id) { dialog.cancel(); } });
	 * AlertDialog alert = builder.create(); alert.show(); return true; case
	 * MENU_INICI: Intent i = new Intent(this, Jclic.class); startActivity(i);
	 * finish(); return true; } return false; }
	 */
}
