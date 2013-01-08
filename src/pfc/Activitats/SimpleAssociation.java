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

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import pfc.Descompressor.Descompressor;
import pfc.Jclic.R;
import pfc.Parser.Parser;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

@TargetApi(8)
public class SimpleAssociation extends Activity {
	private Constants CO = Constants.getInstance();
	private String path = "/sdcard/tmp/jclic/";

	private int newWidth;
	private int newHeight;
	private int width;
	private int height;
	// TODO esto es del menu y se quitara
	private static final int MENU_ANT = 0;
	private static final int MENU_SEG = 1;
	private static final int MENU_SOLUCIO = 2;
	private static final int MENU_AJUDA = 3;
	private static final int MENU_INICI = 4;
	private static final int MENU_SORTIR = 5;
	Sounds sound;
	private TextView ttiempo = null;
	private TextView aciertos = null;
	private TextView intentos = null;
	private ProgressBar tiempo = null;
	private Vector<BitmapDrawable> vecDraw = null;

	private Vector<TextView> plafoA = new Vector<TextView>(CO.cols * CO.rows);
	private Vector<TextView> plafoB = new Vector<TextView>(CO.cols * CO.rows);
	private Vector<TV_ContAlternatiu> contAlternatiu = new Vector<TV_ContAlternatiu>(
			CO.cols * CO.rows);

	public class TV_ContAlternatiu extends TextView {
		boolean esImatge;

		public TV_ContAlternatiu(Context context) {
			super(context);
		}

		boolean getEsImatge() {
			return esImatge;
		}

		void setEsImatge(boolean b) {
			esImatge = b;
		}
	}

	private int maxTime = Parser.getActivitats().get(CO.activitatActual)
			.getTempsMax();
	private int maxIntents = Parser.getActivitats().get(CO.activitatActual)
			.getIntentMax();
	private boolean TimeCountDown = Parser.getActivitats()
			.get(CO.activitatActual).getTimeCutDown();
	private boolean IntentCountDown = Parser.getActivitats()
			.get(CO.activitatActual).getIntentCutdown();

	private ArrayList<ArrayList<Integer>> idPos = new ArrayList<ArrayList<Integer>>();
	private TextView seleccionat = null;
	private ArrayList<Integer> correspondencies;
	private ArrayList<Integer> correspondenciesB;

	int contador = 0; // Comptador per als intents.
	int contadorTemps = 0; // Comptador per al temps.
	private CountDownTimer timer;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.simple_association);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		sound = new Sounds(getApplicationContext());
		aciertos = (TextView) findViewById(R.id.editAciertos);
		intentos = (TextView) findViewById(R.id.editIntentos);
		ttiempo = (TextView)findViewById(R.id.tiempo);
		tiempo = (ProgressBar) findViewById(R.id.progressTime);

	    tiempo.setMax(maxTime);
	    tiempo.setProgress(0);
	    
	    if (maxTime == 0) {
			tiempo.setVisibility(tiempo.INVISIBLE);
			ttiempo.setVisibility(ttiempo.INVISIBLE);
		}
	    
	    Button bMenu = (Button) findViewById(R.id.menu);

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

		try {
			agafarDades();
			ArrayList<Integer> row = new ArrayList<Integer>();
			row.add(R.id.pos1);
			row.add(R.id.pos2);
			row.add(R.id.pos3);
			row.add(R.id.pos4);
			idPos.add(row);
			row = new ArrayList<Integer>();
			row.add(R.id.pos5);
			row.add(R.id.pos6);
			row.add(R.id.pos7);
			row.add(R.id.pos8);
			idPos.add(row);
			row = new ArrayList<Integer>();
			row.add(R.id.pos9);
			row.add(R.id.pos10);
			row.add(R.id.pos11);
			row.add(R.id.pos12);
			idPos.add(row);
			row = new ArrayList<Integer>();
			row.add(R.id.pos13);
			row.add(R.id.pos14);
			row.add(R.id.pos15);
			row.add(R.id.pos16);
			idPos.add(row);
			row = new ArrayList<Integer>();
			row.add(R.id.pos17);
			row.add(R.id.pos18);
			row.add(R.id.pos19);
			row.add(R.id.pos20);
			idPos.add(row);

			// agafem els noms
			row = new ArrayList<Integer>();
			row.add(R.id.pos21);
			row.add(R.id.pos22);
			row.add(R.id.pos23);
			row.add(R.id.pos24);
			idPos.add(row);
			row = new ArrayList<Integer>();
			row.add(R.id.pos25);
			row.add(R.id.pos26);
			row.add(R.id.pos27);
			row.add(R.id.pos28);
			idPos.add(row);
			row = new ArrayList<Integer>();
			row.add(R.id.pos29);
			row.add(R.id.pos30);
			row.add(R.id.pos31);
			row.add(R.id.pos32);
			idPos.add(row);
			row = new ArrayList<Integer>();
			row.add(R.id.pos33);
			row.add(R.id.pos34);
			row.add(R.id.pos35);
			row.add(R.id.pos36);
			idPos.add(row);
			row = new ArrayList<Integer>();
			row.add(R.id.pos37);
			row.add(R.id.pos38);
			row.add(R.id.pos39);
			row.add(R.id.pos40);
			idPos.add(row);

			// fer les dos quadricules
			initQuadricules();

			// inicialitzar onClickslisteners
			sound.playStart();
			// listeners de menus

			if (maxTime != 0) {
				timer = new CountDownTimer(maxTime * 1000, 1000) {
					@Override
					public void onTick(long arg0) {
						contadorTemps++;
						tiempo.setProgress(contadorTemps);
						setMissatges();
					}

					@Override
					public void onFinish() {
						contadorTemps++;
						contadorTemps++;
						tiempo.setProgress(contadorTemps);
						setMissatges();
					}
				}.start();
			}

		} catch (Exception e) {
			Log.d("Error", "catch SimpleAssociation: " + e);
			e.printStackTrace();
		}

	}

	private void agafarDades() {
		CO.tl = (TableLayout) findViewById(R.id.tl);

		// agafarCaselles();
		// CO.intentMax =
		// CO.miss = (TextView) findViewById(R.id.missatge);
		// CO.missCorrectes = (TextView) findViewById(R.id.editAciertos);
		// CO.cas1 = (TextView) findViewById(R.id.cas1);
		CO.name = (TextView) findViewById(R.id.titulo);

		// CO.miss.setTextColor(Color.WHITE);
		// CO.missCorrectes.setTextColor(Color.WHITE);
		// CO.name.setTextColor(Color.WHITE);
		// CO.cas1.setTextColor(Color.WHITE);

		CO.p1 = "<buit>";
		CO.p2 = "<buit>";

		if (Parser.getActivitats().elementAt(CO.activitatActual).getName() != null)
			CO.name.setText(Parser.getActivitats()
					.elementAt(CO.activitatActual).getName());
		else
			CO.name.setText("Activitat JClic");

		if (CO.rows == 1)
			CO.tl.setPadding(0, 100, 0, 0);
		else if (CO.rows == 2) {
			CO.tl.setPadding(0, 30, 0, 0);
		} else
			CO.tl.setPadding(0, 0, 0, 0);

		if (CO.colorBG != null) {
			CO.bg = Puzzle.agafarColor(CO.colorBG);
		} else
			CO.bg = Color.BLACK;

		if (CO.colorFG != null) {
			CO.fg = Puzzle.agafarColor(CO.colorFG);
		} else
			CO.fg = Color.WHITE;
	}

	private void setMissatges() {
		final Context aC = this;
		Dialog dialog = new Dialog(aC, R.style.Dialog);
		dialog.setContentView(R.layout.menu_clic);
		dialog.setCanceledOnTouchOutside(true);
		MenuActivitats ma = new MenuActivitats(timer);
		ma.butsMenu(dialog, aC, vecDraw);
		TextView textFinal = (TextView) dialog.findViewById(R.id.tMenuClic);
		if (CO.correcte == CO.cols*CO.rows) {
			// Hem acabat el joc
			sound.playFinished_ok();
			if (maxTime != 0) {
				timer.cancel();
			}
			if (Parser.getActivitats().elementAt(CO.activitatActual)
					.getMissatgeFi() != null) {
				textFinal.setText(Parser.getActivitats()
						.elementAt(CO.activitatActual).getMissatgeFi());
			} else {
				textFinal.setText("Joc finalitzat!");
				// CO.miss2.setText("Joc finalitzat!");
			}
			dialog.show();
		} else if ((CO.correcte != CO.casIni && maxIntents != 0 && maxIntents == contador)
				|| contadorTemps == maxTime && maxTime != 0) {
			sound.playFinished_error();
			timer.cancel();
			if (maxTime != 0 && contadorTemps == maxTime) {
				textFinal.setText("S'ha acabat el temps!");
			} else {
				textFinal.setText("Has superat els intents maxims!");
			}
			dialog.show();
		} else {
			if (Parser.getActivitats().elementAt(CO.activitatActual)
					.getMissatgeIni() != null) {
				CO.miss.setText(Parser.getActivitats()
						.elementAt(CO.activitatActual).getMissatgeIni());
				// CO.miss2.setText(Parser.getActivitats()
				// .elementAt(CO.activitatActual).getMissatgeIni());
			} else {
				// CO.miss.setText("Comenca el joc!");
				// CO.miss2.setText("Comenca el joc!");
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
			intentos.setText(Integer.toString(contador));
		}
	}

	private void initQuadricules() {

		makeRandomPlafoA();
		makeRandomPlafoB();

		for (int i = 0; i < CO.rows; ++i) { // posar elems plafo A
			for (int j = 0; j < CO.cols; ++j) {
				TextView tmp = (TextView) findViewById(idPos.get(i).get(j));
				plafoA.add(tmp);
			}
		}

		int offB = CO.cols * CO.rows; // offset a partir del que comença la info
										// del plafó B

		for (int i = 0; i < CO.rows; ++i) { // posar elems plafo B
			for (int j = 0; j < CO.cols; ++j) {
				TextView tmp = (TextView) findViewById(idPos.get(5 + i).get(j));
				plafoB.add(tmp);

			}
		}

		for (int i = 0; i < CO.rows; ++i) { // posar elems conAlternatiu
			for (int j = 0; j < CO.cols; ++j) {
				TV_ContAlternatiu tmp = new TV_ContAlternatiu(
						getApplicationContext());
				contAlternatiu.add(tmp);
			}
		}

		for (int i = 0; i < CO.rows; ++i) { // inicialització plafo A
			for (int j = 0; j < CO.cols; ++j) {
				Integer corresp = correspondencies.get(i * CO.cols + j);
				TextView tmp = plafoA.get(corresp);
				tmp.setBackgroundColor(Color.DKGRAY);

				// plafoA.add(correspondencies.get(i*CO.cols+j), tmp);
				resizeCaselles(tmp);
				if ("".equals(CO.imatges.get(i * CO.cols + j))) { // no hi ha
																	// imatges
																	// -> posar
																	// text
					String text = CO.celes.get(i * CO.cols + j);
					tmp.setText(text);
					tmp.setTextColor(Color.WHITE);
				} else { // hi ha imatges -> posar imatge
					if (Descompressor.descompressor(
							CO.imatges.get(i * CO.cols + j), CO.path)) {
						BitmapDrawable img = new BitmapDrawable(path
								+ CO.imatges.get(i * CO.cols + j));
						img = resizeImg(img);
						tmp.setBackgroundDrawable(img);
					}
				}

				tmp.setClickable(true);
				tmp.setOnClickListener(new View.OnClickListener() {

					public void onClick(View v) {
						click(v);
					}
				});
				// plafoA.set(corresp, tmp);
			}
		}

		// int offB = CO.cols*CO.rows; //offset a partir del que comença la info
		// del plafó B

		for (int i = 0; i < CO.rows; ++i) { // inicialització plafo B
			for (int j = 0; j < CO.cols; ++j) {
				Integer corresp = correspondenciesB.get(i * CO.cols + j);
				TextView tmp = plafoB.get(corresp);
				resizeCaselles(tmp);
				tmp.setBackgroundColor(Color.DKGRAY);

				if ("".equals(CO.imatges.get(offB + i * CO.cols + j))) { // no
																			// hi
																			// ha
																			// imatges
																			// ->
																			// posar
																			// text
					String text = CO.celes.get(offB + i * CO.cols + j);
					tmp.setText(text);
					tmp.setTextColor(Color.WHITE);
				} else { // hi ha imatges -> posar imatge
					if (Descompressor.descompressor(
							CO.imatges.get(i * CO.cols + j), CO.path)) {
						BitmapDrawable img = new BitmapDrawable(path
								+ CO.imatges.get(offB + i * CO.cols + j));
						img = resizeImg(img);
						tmp.setBackgroundDrawable(img);
					}
				}

				tmp.setClickable(true);
				tmp.setOnClickListener(new View.OnClickListener() {

					public void onClick(View v) {
						click(v);
					}
				});
				// al plafoB no fem random :/ Integer corresp =
				// correspondencies.get(i*CO.cols+j);
				// plafoB.set(i*CO.cols+j, tmp);

			}
		}

		for (int i = 0; i < CO.rows; ++i) { // inicialització plafo alternatiu
			for (int j = 0; j < CO.cols; ++j) {
				Integer corresp = correspondencies.get(i * CO.cols + j);
				TV_ContAlternatiu tmp = contAlternatiu.get(corresp);
				tmp.setBackgroundColor(Color.DKGRAY);

				resizeCaselles(tmp);
				if (CO.imatges.size() > offB * 2
						&& "".equals(CO.imatges.get(offB * 2 + i * CO.cols + j))) { // no
																					// hi
																					// ha
																					// imatge
																					// ->
																					// posar
																					// text
					String text = CO.celes.get(offB * 2 + i * CO.cols + j);
					tmp.setText(text);
					tmp.setTextColor(Color.WHITE);
					tmp.setEsImatge(false);
				} else if (CO.imatges.size() > offB * 2) { // hi ha imatge ->
															// posar imatge
					if (Descompressor.descompressor(
							CO.imatges.get(i * CO.cols + j), CO.path)) {
						BitmapDrawable img = new BitmapDrawable(path
								+ CO.imatges.get(i * CO.cols + j));
						img = resizeImg(img);
						tmp.setBackgroundDrawable(img);
						tmp.setEsImatge(true);
					}
				}

			}
		}

	}

	private void click(View v) {

		sound.playClick();
		Drawable draw;

		if (seleccionat == null) { // no n'hi ha cap de seleccionat anteriorment
			seleccionat = (TextView) v;
			draw = seleccionat.getBackground();
			draw.setAlpha(100);
			seleccionat.setBackgroundDrawable(draw);
		} else if (seleccionat != null) { // ja en té un de seleccionat

			// Arreglado, se utilizaban backgrounds que no existian.
			// si selecciona un del mateix plafo
			if ((plafoA.contains(v) && plafoA.contains(seleccionat))
					|| (plafoB.contains(v) && plafoB.contains(seleccionat))) {

				if (v.equals(seleccionat)) { // torna a seleccionar el mateix
					draw = seleccionat.getBackground();
					draw.setAlpha(255);
					seleccionat.setBackgroundDrawable(draw);
					seleccionat = null;
				} else { // en selecciona un altre
					draw = seleccionat.getBackground();
					draw.setAlpha(255);
					seleccionat.setBackgroundDrawable(draw);

					seleccionat = (TextView) v;

					draw = seleccionat.getBackground();
					draw.setAlpha(100);
					// seleccionat.setBackgroundDrawable(draw);
				}
			}

			else { // en selecciona un d'un plafo diferent
				contador++;// S'incrementa el contador d'intents
				String plafoS, plafoV;
				Integer posS, posV;
				if (plafoA.contains(v)) {
					plafoV = "A";
					posV = plafoA.indexOf(v);
					plafoS = "B";
					posS = plafoB.indexOf(seleccionat);
				} else {
					plafoV = "B";
					posV = plafoB.indexOf(v);
					plafoS = "A";
					posS = plafoA.indexOf(seleccionat);
				}

				if ("A".equals(plafoS)) { // && "B".equals(plafoV)
					Integer posInicial = correspondencies.indexOf(posS);
					Integer posCorrectaB = correspondenciesB.get(posInicial);

					if (posCorrectaB.equals(posV)) { // correcte
						draw = seleccionat.getBackground();
						draw.setAlpha(255);
						seleccionat.setBackgroundDrawable(draw);
						if (CO.imatges.size() > (CO.cols * CO.rows) * 2) { // hi
																			// ha
																			// contingut
																			// alternatiu

							TextView tmp = (TextView) seleccionat;
							TV_ContAlternatiu tmp2 = contAlternatiu.get(posS);
							tmp.setText(null);
							tmp.setBackgroundColor(Color.DKGRAY);

							if (!tmp2.esImatge) {
								tmp.setText(tmp2.getText());
							} else {
								tmp.setBackgroundDrawable(tmp2.getBackground());
							}

							tmp.getBackground().setAlpha(50);
							// plafoA.set(posS, tmp);
						} else { // es posen transparents
							seleccionat.setVisibility(View.INVISIBLE);
						}
						v.setVisibility(View.INVISIBLE);
						v.setClickable(false);
						seleccionat.setClickable(false);
						seleccionat = null;
						CO.correcte++;
						sound.playAction_ok();

					} else { // es desselecciona el seleccionat abans

						sound.playActionError();

						draw = seleccionat.getBackground();
						draw.setAlpha(255);
						seleccionat.setBackgroundDrawable(draw);
						seleccionat = null;
					}
				} else { // "B".equals(plafoS) && "A".equals(plafoV)
					Integer posInicial = correspondencies.indexOf(posV);
					Integer posCorrectaB = correspondenciesB.get(posInicial);

					if (posCorrectaB.equals(posS)) { // correcte

						seleccionat.getBackground().setAlpha(255);
						if (CO.imatges.size() > (CO.cols * CO.rows) * 2) { // hi
																			// ha
																			// contingut
																			// alternatiu
							// TextView tmp = (TextView)
							// contAlternatiu.get(posCorrectaB);
							TextView tmp = (TextView) v;
							TV_ContAlternatiu tmp2 = contAlternatiu.get(posV);
							tmp.setText(null);
							tmp.setBackgroundColor(Color.DKGRAY);

							if (!tmp2.esImatge) {
								tmp.setText(tmp2.getText());
							} else {
								tmp.setBackgroundDrawable(tmp2.getBackground());
							}
							tmp.getBackground().setAlpha(50);
							// plafoA.set(posS, tmp);
						} else { // es posen transparents
							v.setVisibility(View.INVISIBLE);
						}
						seleccionat.setVisibility(View.INVISIBLE);
						v.setClickable(false);
						seleccionat.setClickable(false);
						seleccionat = null;

						sound.playAction_ok();

					} else {

						sound.playActionError();

						seleccionat.getBackground().setAlpha(255);
						seleccionat = null;
					}
				}
			}
		}

		int cont = 0;
		for (int i = 0; i < CO.cols * CO.rows; ++i) {
			if (plafoB.get(i).getVisibility() == View.INVISIBLE) {
				++cont;
			}
		}
		if (cont == CO.cols * CO.rows) {
//			sound.playFinished_ok();
		}
		setMissatges();
	}

	private void makeRandomPlafoA() {
		ArrayList<Boolean> agafats = new ArrayList<Boolean>();
		for (int i = 0; i < (CO.cols * CO.rows); i++) {
			agafats.add(false);
		}
		Random r = new Random();

		this.correspondencies = new ArrayList<Integer>(CO.cols * CO.rows);
		for (int i = 0; i < (CO.cols * CO.rows); ++i) {
			int rand = r.nextInt(agafats.size());
			if (agafats.get(rand) != true) {
				this.correspondencies.add(Integer.valueOf(rand));
				agafats.set(rand, true);
			} else
				--i;
		}
	}

	private void makeRandomPlafoB() {
		ArrayList<Boolean> agafats = new ArrayList<Boolean>();
		for (int i = 0; i < (CO.cols * CO.rows); i++) {
			agafats.add(false);
		}
		Random r = new Random();

		this.correspondenciesB = new ArrayList<Integer>(CO.cols * CO.rows);
		for (int i = 0; i < (CO.cols * CO.rows); ++i) {
			int rand = r.nextInt(agafats.size());
			if (agafats.get(rand) != true) {
				this.correspondenciesB.add(Integer.valueOf(rand));
				agafats.set(rand, true);
			} else
				--i;
		}
	}

	private BitmapDrawable resizeImg(BitmapDrawable bitmapd) {

		Bitmap bitmapOrg = bitmapd.getBitmap();
		int widthImage = bitmapOrg.getWidth();
		int heightImage = bitmapOrg.getHeight();

		// newWidth = width*CO.cols;
		// newHeight = height*CO.rows;
		newWidth = width;
		newHeight = height;

		float scaleWidth = ((float) newWidth) / widthImage;
		float scaleHeight = ((float) newHeight) / heightImage;

		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);

		Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0, widthImage,
				heightImage, matrix, true);

		return new BitmapDrawable(resizedBitmap);
	}

	private void resizeCaselles(TextView pos) {

		Display display = getWindowManager().getDefaultDisplay();
		int screenWidth = display.getWidth();
		int screenHeigh = display.getHeight();

		width = screenWidth / (CO.cols * 2);

		pos.setWidth(width);

		height = screenHeigh / (CO.rows * 4);

		pos.setHeight(height);

		// if(CO.cols == 1){
		// pos.setWidth(250);
		// width = 250;
		// } else if(CO.cols == 2){
		// pos.setWidth(120);
		// width = 120;
		// } else if(CO.cols == 3){
		// pos.setWidth(50);
		// width = 50;
		// } else {
		// //cols == 4
		// pos.setWidth(40);
		// width = 40;
		// }
		//
		// if(CO.rows == 1 || CO.rows == 2){
		// pos.setHeight(100);
		// pos.setMaxLines(4);
		// height = 100;
		// } else if(CO.rows == 3){
		// pos.setHeight(85);
		// pos.setMaxLines(3);
		// height = 85;
		// } else if(CO.rows == 4){
		// pos.setHeight(50);
		// pos.setMaxLines(2);
		// height = 50;
		// } else {
		// //CO.rows == 5
		// pos.setHeight(40);
		// pos.setMaxLines(2);
		// height = 40;
		// }
		// width /= 10;
		// height /= 10;

	}

	// @Override
	protected void onDestroy() {
		super.onDestroy();
		sound.unloadAll();
	}
	
	@Override
	public void onBackPressed() {
	}
	
	private void reiniciarMenu(){		
		if(CO.menu != null){
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
/*
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
			Dialog ajuda = new AlertDialog.Builder(SimpleAssociation.this)
					.setIcon(R.drawable.jclic_aqua).setTitle("Ajuda")
					.setPositiveButton("D'acord", null)
					.setMessage("Associa les caselles dels dos panells")
					.create();
			ajuda.show();
			return true;
		case MENU_SOLUCIO:
			/*
			 * Això no ho fem
			 * 
			 * 
			 * if(!CO.solucioVisible){ //Vull mostrar la solucio CO.vecActual =
			 * new Vector<CharSequence>(); for(int i = 0; i <
			 * CO.vecCaselles.size(); i++){ if(CO.vecCaselles.elementAt(i) !=
			 * null){
			 * CO.vecActual.addElement(CO.vecCaselles.elementAt(i).getText());
			 * CO.vecCaselles.elementAt(i).setText(CO.sortida.elementAt(i));
			 * CO.vecCaselles.elementAt(i).setBackgroundColor(CO.bg);
			 * CO.vecCaselles.elementAt(i).setTextColor(CO.fg);
			 * 
			 * if(CO.imatge != null){ int indexSort =
			 * CO.sortida.indexOf(CO.vecCaselles.elementAt(i).getText());
			 * 
			 * CO.vecCaselles.elementAt(i).setBackgroundColor(Color.TRANSPARENT);
			 * CO.vecCaselles.elementAt(i).setTextColor(Color.TRANSPARENT);
			 * 
			 * vecDraw.elementAt(indexSort).setAlpha(250);
			 * 
			 * CO.vecCaselles.elementAt(i).
			 * setBackgroundDrawable(vecDraw.elementAt(indexSort)); } } else
			 * CO.vecActual.addElement(null); } bloquejarJoc(true);
			 * CO.solucioVisible = true; posAgafada = null; setMissatges();
			 * CO.menu.getItem(MENU_SOLUCIO).setTitle(R.string.menu_in_solucio);
			 * CO.menu.getItem(MENU_ANT).setEnabled(false);
			 * CO.menu.getItem(MENU_SEG).setEnabled(false); } else { //Estic
			 * mostrant la solucio i vull continuar for(int i = 0; i <
			 * CO.vecCaselles.size(); i++){ if(CO.vecCaselles.elementAt(i) !=
			 * null){
			 * CO.vecCaselles.elementAt(i).setText(CO.vecActual.elementAt(i));
			 * CO.vecCaselles.elementAt(i).setTextColor(CO.fg);
			 * CO.vecCaselles.elementAt(i).setBackgroundColor(CO.bg);
			 * 
			 * if(CO.imatge != null){ int indexSort =
			 * CO.sortida.indexOf(CO.vecCaselles.elementAt(i).getText());
			 * 
			 * CO.vecCaselles.elementAt(i).setBackgroundColor(Color.TRANSPARENT);
			 * CO.vecCaselles.elementAt(i).setTextColor(Color.TRANSPARENT);
			 * 
			 * vecDraw.elementAt(indexSort).setAlpha(250);
			 * 
			 * CO.vecCaselles.elementAt(i).
			 * setBackgroundDrawable(vecDraw.elementAt(indexSort)); } } }
			 * bloquejarJoc(false); CO.solucioVisible = false; setMissatges();
			 * CO.menu.getItem(MENU_SOLUCIO).setTitle(R.string.menu_solucio);
			 * 
			 * //Configuracio del menu per ant i seguent
			 * if(CO.activitatActual<1){ //estem a la primera activitat, pel que
			 * nomes habilitem seguent
			 * CO.menu.getItem(MENU_SEG).setEnabled(true); } else
			 * if(CO.activitatActual == Parser.getActivitats().size() - 1){
			 * //estem a l'ultima activitat, pel que no podem habilitar el
			 * seguent CO.menu.getItem(MENU_ANT).setEnabled(true); } else {
			 * CO.menu.getItem(MENU_SEG).setEnabled(true);
			 * CO.menu.getItem(MENU_ANT).setEnabled(true); } }
			 */
/*			return true;
		case MENU_SORTIR:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.jclic_aqua);
			builder.setMessage("Estàs segur de que vols sortir?")
					.setCancelable(false)
					.setPositiveButton("Sí",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									SimpleAssociation.this.finish();
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
	}*/

}
