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
import android.widget.TextView;

@TargetApi(8)
public class ComplexAssociation extends Activity {
	private Constants CO = Constants.getInstance();
	private String path = "/sdcard/tmp/jclic/";

	private int newWidth;
	private int newHeight;
	private int width;
	private int height;

	private Vector<TextView> plafoA = new Vector<TextView>(CO.cols * CO.rows);
	private Vector<TextView> plafoB = new Vector<TextView>(CO.cols2 * CO.rows2);
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

	Dialog dialog;
	Sounds sound;
	private int maxTime = Parser.getActivitats().get(CO.activitatActual)
			.getTempsMax();
	private int maxIntents = Parser.getActivitats().get(CO.activitatActual)
			.getIntentMax();
	private boolean TimeCountDown = Parser.getActivitats()
			.get(CO.activitatActual).getTimeCutDown();
	private boolean IntentCountDown = Parser.getActivitats()
			.get(CO.activitatActual).getIntentCutdown();

	private ArrayList<ArrayList<Integer>> idPos = new ArrayList<ArrayList<Integer>>();
	private TextView seleccionat;
	private ArrayList<Integer> correspondencies;
	private ArrayList<Integer> correspondenciesB;

	int contador = 0; // Comptador per als intents.
	int contadorTemps = 0; // Comptador per al temps.
	private CountDownTimer timer;
	private TextView aciertos = null;
	private TextView intentos = null;
	private TextView ttiempo = null;
	private ProgressBar tiempo = null;
	private Vector<BitmapDrawable> vecDraw = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.complex_association);

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
				dialog = new Dialog(aC, R.style.Dialog);
				dialog.setContentView(R.layout.menu_clic);
				dialog.setCanceledOnTouchOutside(true);
				dialog.show();
				MenuActivitats ma = new MenuActivitats(timer);
				ma.butsMenu(dialog, aC, vecDraw);
			}
		});

		try {

			agafarDades();
			// reiniciarMenu();

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

			if (maxTime != 0) {
				timer = new CountDownTimer(maxTime * 1000, 1000) {
					@Override
					public void onTick(long arg0) {
						contadorTemps++;
						// tiempo.setProgress(contadorTemps);
						setMissatges();
					}

					@Override
					public void onFinish() {
						contadorTemps++;
						// tiempo.setProgress(contadorTemps);
						setMissatges();
					}
				}.start();
			}
			// fer les dos quadricules
			setMissatges();
			initQuadricules();

			sound.playStart();

		} catch (Exception e) {
			Log.d("Error", "catch SimpleAssociation: " + e);
			e.printStackTrace();
		}
	}

	private void agafarDades() {
		if (CO.inverse) {
			CO.casIni = CO.cols2 * CO.rows2;
		} else {
			CO.casIni = CO.cols * CO.rows;
		}

		CO.miss = (TextView) findViewById(R.id.missatge);
		CO.miss2 = (TextView) findViewById(R.id.missatge2);
		CO.cas1 = (TextView) findViewById(R.id.cas1);
		CO.cas2 = (TextView) findViewById(R.id.cas2);
		CO.name = (TextView) findViewById(R.id.titulo);

		CO.miss.invalidate();
		CO.miss2.invalidate();
		CO.cas1.invalidate();
		CO.cas2.invalidate();

		CO.miss.setTextColor(Color.WHITE);
		CO.miss2.setTextColor(Color.WHITE);
		CO.cas1.setTextColor(Color.WHITE);
		CO.cas2.setTextColor(Color.WHITE);
		CO.name.setTextColor(Color.WHITE);

		CO.correcte = 0;
		CO.incorrecte = CO.casIni;

		CO.p1 = "<buit>";

		if (Parser.getActivitats().elementAt(CO.activitatActual).getName() != null)
			CO.name.setText(Parser.getActivitats()
					.elementAt(CO.activitatActual).getName());
		else
			CO.name.setText("Activitat JClic");

		if (CO.colorBG != null) {
			CO.bg = Puzzle.agafarColor(CO.colorBG);
		} else
			CO.bg = Color.GRAY;

		if (CO.colorFG != null) {
			CO.fg = Puzzle.agafarColor(CO.colorFG);
		} else
			CO.fg = Color.WHITE;

	}

	private void bloquejarJoc(boolean bloquejar) {
		// Fem que es bloquegi o desbloquegi l'activitat
		for (int i = 0; i < plafoA.size(); ++i) {
			plafoA.get(i).setClickable(false);
		}
		for (int i = 0; i < plafoB.size(); ++i) {
			plafoB.get(i).setClickable(false);
		}
	}

	private void setMissatges() {
		if (CO.solucioVisible) {
			CO.miss.setText("");
			CO.miss2.setText("");
			// CO.missCorrectes.setText("");
			// CO.missCorrectes2.setText("");
			CO.cas1.setText("");
			CO.cas2.setText("");
			CO.p1 = "<buit>";
		} else {
			final Context aC = this;
			dialog = new Dialog(aC, R.style.Dialog);
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
				dialog.show();
				bloquejarJoc(true);

			} else if ((CO.correcte != CO.casIni && maxIntents != 0 && maxIntents == contador)
					|| contadorTemps == maxTime && maxTime != 0) {
				sound.playFinished_error();
				if (maxTime != 0)
					timer.cancel();

				if (maxTime != 0 && contadorTemps == maxTime) {
					textFinal.setText("S'ha acabat el temps!");
					CO.miss2.setText("S'ha acabat el temps!");
				} else {
					textFinal.setText("Has superat els intents maxims!");
					CO.miss2.setText("Has superat els intents maxims!");
				}

				dialog.show();
				bloquejarJoc(true);

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
				// if (TimeCountDown && maxTime != 0) {
				// displayedTime = maxTime - contadorTemps;
				// } else
				displayedTime = contadorTemps;
				aciertos.setText(Integer.toString(CO.correcte));
				intentos.setText(Integer.toString(displayedIntents));
				tiempo.setProgress(displayedTime);
			}
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

		for (int i = 0; i < CO.rows2; ++i) { // posar elems plafo B
			for (int j = 0; j < CO.cols2; ++j) {
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
				tmp.setBackgroundColor(CO.bg);

				// plafoA.add(correspondencies.get(i*CO.cols+j), tmp);
				resizeCaselles(tmp);
				if ("".equals(CO.imatges.get(i * CO.cols + j))) { // no hi ha
																	// imatges
																	// -> posar
																	// text
					String text = CO.celes.get(i * CO.cols + j);
					tmp.setText(text);
					tmp.setTextColor(CO.fg);
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
						if (!CO.inverse) {
							click(v);
						} else {
							click_inv(v);
						}
					}
				});
				// plafoA.set(corresp, tmp);
			}
		}

		// int offB = CO.cols*CO.rows; //offset a partir del que comença la info
		// del plafó B

		for (int i = 0; i < CO.rows2; ++i) { // inicialització plafo B
			for (int j = 0; j < CO.cols2; ++j) {
				Integer corresp = correspondenciesB.get(i * CO.cols2 + j);
				TextView tmp = plafoB.get(corresp);

				resizeCaselles(tmp);
				tmp.setBackgroundColor(CO.bg);

				if ("".equals(CO.imatges.get(offB + i * CO.cols2 + j))) { // no
																			// hi
																			// ha
																			// imatges
																			// ->
																			// posar
																			// text
					String text = CO.celes.get(offB + i * CO.cols2 + j);
					tmp.setText(text);
					tmp.setTextColor(CO.fg);
				} else { // hi ha imatges -> posar imatge
					if (Descompressor.descompressor(
							CO.imatges.get(i * CO.cols2 + j), CO.path)) {
						BitmapDrawable img = new BitmapDrawable(path
								+ CO.imatges.get(offB + i * CO.cols2 + j));
						img = resizeImg(img);
						tmp.setBackgroundDrawable(img);
					}
				}

				tmp.setClickable(true);
				tmp.setOnClickListener(new View.OnClickListener() {

					public void onClick(View v) {
						if (!CO.inverse) {
							click(v);
						} else {
							click_inv(v);
						}
					}
				});
				// al plafoB no fem random :/
				// plafoB.set(i*CO.cols+j, tmp);

			}
		}

		int offAlt = offB + CO.cols2 * CO.rows2;

		for (int i = 0; i < CO.rows; ++i) { // inicialització plafo alternatiu
			for (int j = 0; j < CO.cols; ++j) {
				Integer corresp = correspondencies.get(i * CO.cols + j);
				TV_ContAlternatiu tmp = contAlternatiu.get(corresp);
				tmp.setBackgroundColor(CO.bg);

				resizeCaselles(tmp);
				if (CO.imatges.size() > offAlt
						&& "".equals(CO.imatges.get(offAlt + i * CO.cols + j))) { // no
																					// hi
																					// ha
																					// imatge
																					// ->
																					// posar
																					// text
					String text = CO.celes.get(offAlt + i * CO.cols + j);
					tmp.setText(text);
					tmp.setTextColor(CO.fg);
					tmp.setEsImatge(false);
				} else if (CO.imatges.size() > offAlt) { // hi ha imatge ->
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

	private void click_inv(View v) {
		sound.playClick();

		if (seleccionat == null) { // no n'hi ha cap de seleccionat anteriorment
			seleccionat = (TextView) v;

			Drawable draw = seleccionat.getBackground();
			draw.setAlpha(100);
			seleccionat.setBackgroundDrawable(draw);
		}

		else if (seleccionat != null) { // ja en té un de seleccionat

			if ((plafoA.contains(v) && plafoA.contains(seleccionat))
					|| (plafoB.contains(v) && plafoB.contains(seleccionat))) { // si
																				// selecciona
																				// un
																				// del
																				// mateix
																				// plafo
				if (v.equals(seleccionat)) { // torna a seleccionar el mateix
					seleccionat.getBackground().setAlpha(255);
					seleccionat = null;
				} else { // en selecciona un altre
					seleccionat.getBackground().setAlpha(255);
					seleccionat = (TextView) v;
					seleccionat.getBackground().setAlpha(100);
				}
			}

			else { // en selecciona un d'un plafo diferent
				String plafoS;
				Integer posS, posV;

				contador++;

				if (plafoA.contains(v)) {
					posV = plafoA.indexOf(v);
					plafoS = "B";
					posS = plafoB.indexOf(seleccionat);
				} else {
					posV = plafoB.indexOf(v);
					plafoS = "A";
					posS = plafoA.indexOf(seleccionat);
				}

				if ("A".equals(plafoS)) {
					// seleccionat -> A i v -> B

					Integer correspondencia = correspondencies.indexOf(posS); // posicio
																				// del
																				// vector
																				// de
																				// IDs
																				// que
																				// em
																				// diu
																				// la
																				// cel·la
																				// del
																				// B
																				// corresponent
					Integer posInicial = CO.ids.get(correspondencia); // posicio
																		// inicial
																		// de la
																		// correspondencia
																		// del
																		// plafo
																		// B
					Integer posCorrectaB = correspondenciesB.get(posInicial); // pos
																				// correcta
																				// a
																				// B
																				// corresponent
																				// a
																				// seleccionat

					if (posCorrectaB.equals(posV)) { // correcte

						seleccionat.getBackground().setAlpha(255);
						if (CO.imatges.size() > (CO.cols * CO.rows + CO.cols2
								* CO.rows2)) { // hi ha contingut alternatiu

							TextView tmp = (TextView) seleccionat;
							TV_ContAlternatiu tmp2 = contAlternatiu.get(posS);
							tmp.setText("");
							tmp.setBackgroundColor(CO.bg);

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

						seleccionat.getBackground().setAlpha(255);
						seleccionat = null;
					}
				} else {

					// seleccionat -> B i v -> A

					Integer correspondencia = correspondencies.indexOf(posV);
					Integer posInicial = CO.ids.get(correspondencia); // posicio
																		// inicial
																		// de la
																		// correspondencia
																		// del
																		// plafo
																		// B
					Integer posCorrectaB = correspondenciesB.get(posInicial); // pos
																				// correcta
																				// a
																				// B
																				// corresponent
																				// a
																				// seleccionat

					if (posCorrectaB.equals(posS)) { // correcte

						seleccionat.getBackground().setAlpha(255);
						if (CO.imatges.size() > (CO.cols * CO.rows + CO.cols2
								* CO.rows2)) { // hi ha contingut alternatiu
							// TextView tmp = (TextView)
							// contAlternatiu.get(posCorrectaB);
							TextView tmp = (TextView) v;
							TV_ContAlternatiu tmp2 = contAlternatiu.get(posV);
							tmp.setText("");
							tmp.setBackgroundColor(CO.bg);

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
						CO.correcte++;
						sound.playAction_ok();

					} else {

						sound.playActionError();

						seleccionat.getBackground().setAlpha(255);
						seleccionat = null;
					}
				}
			}
		}

		setMissatges();
		// comprovacio de si ha guanyat
		// int cont = 0;
		// for (int i = 0; i < CO.cols2*CO.rows2; ++i) {
		// if (!plafoB.get(i).isClickable()) {
		// ++cont;
		// }
		// }
		//
		// if (cont == CO.cols2*CO.rows2) {
		// for (int i =0; i< CO.rows*CO.cols; ++i) {
		// plafoA.get(i).setClickable(false);
		// }
		// sound.playFinished_ok();
		// }
	}

	private void click(View v) {

		sound.playClick();

		if (seleccionat == null) { // no n'hi ha cap de seleccionat anteriorment
			seleccionat = (TextView) v;

			Drawable draw = seleccionat.getBackground();
			draw.setAlpha(100);
			seleccionat.setBackgroundDrawable(draw);
		}

		else if (seleccionat != null) { // ja en té un de seleccionat

			if ((plafoA.contains(v) && plafoA.contains(seleccionat))
					|| (plafoB.contains(v) && plafoB.contains(seleccionat))) { // si
																				// selecciona
																				// un
																				// del
																				// mateix
																				// plafo
				if (v.equals(seleccionat)) { // torna a seleccionar el mateix
					seleccionat.getBackground().setAlpha(255);
					seleccionat = null;
				} else { // en selecciona un altre
					seleccionat.getBackground().setAlpha(255);
					seleccionat = (TextView) v;
					seleccionat.getBackground().setAlpha(100);
				}
			}

			else { // en selecciona un d'un plafo diferent
				contador++;
				String plafoS;
				Integer posS, posV;
				if (plafoA.contains(v)) {
					posV = plafoA.indexOf(v);
					plafoS = "B";
					posS = plafoB.indexOf(seleccionat);
				} else {
					posV = plafoB.indexOf(v);
					plafoS = "A";
					posS = plafoA.indexOf(seleccionat);
				}

				if ("A".equals(plafoS)) {
					// seleccionat -> A i v -> B

					Integer correspondencia = correspondencies.indexOf(posS); // posicio
																				// del
																				// vector
																				// de
																				// IDs
																				// que
																				// em
																				// diu
																				// la
																				// cel·la
																				// del
																				// B
																				// corresponent
					Integer posInicial = CO.ids.get(correspondencia); // posicio
																		// inicial
																		// de la
																		// correspondencia
																		// del
																		// plafo
																		// B
					Integer posCorrectaB = correspondenciesB.get(posInicial); // pos
																				// correcta
																				// a
																				// B
																				// corresponent
																				// a
																				// seleccionat

					if (posCorrectaB.equals(posV)) { // correcte

						seleccionat.getBackground().setAlpha(255);
						if (CO.imatges.size() > (CO.cols * CO.rows + CO.cols2
								* CO.rows2)) { // hi ha contingut alternatiu

							TextView tmp = (TextView) seleccionat;
							TV_ContAlternatiu tmp2 = contAlternatiu.get(posS);
							tmp.setText("");
							tmp.setBackgroundColor(CO.bg);

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
						// v.setVisibility(View.INVISIBLE);
						// v.setClickable(false);
						seleccionat.setClickable(false);
						seleccionat = null;
						CO.correcte++;
						sound.playAction_ok();

					} else { // es desselecciona el seleccionat abans

						sound.playActionError();

						seleccionat.getBackground().setAlpha(255);
						seleccionat = null;
					}
				} else {

					// seleccionat -> B i v -> A

					Integer correspondencia = correspondencies.indexOf(posV);
					Integer posInicial = CO.ids.get(correspondencia); // posicio
																		// inicial
																		// de la
																		// correspondencia
																		// del
																		// plafo
																		// B
					Integer posCorrectaB = correspondenciesB.get(posInicial); // pos
																				// correcta
																				// a
																				// B
																				// corresponent
																				// a
																				// seleccionat

					if (posCorrectaB.equals(posS)) { // correcte

						seleccionat.getBackground().setAlpha(255);
						if (CO.imatges.size() > (CO.cols * CO.rows + CO.cols2
								* CO.rows2)) { // hi ha contingut alternatiu
							// TextView tmp = (TextView)
							// contAlternatiu.get(posCorrectaB);
							TextView tmp = (TextView) v;
							TV_ContAlternatiu tmp2 = contAlternatiu.get(posV);
							tmp.setText("");
							tmp.setBackgroundColor(CO.bg);

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
						// seleccionat.setVisibility(View.INVISIBLE);
						v.setClickable(false);
						// seleccionat.setClickable(false);
						seleccionat = null;
						CO.correcte++;
						sound.playAction_ok();

					} else {

						sound.playActionError();

						seleccionat.getBackground().setAlpha(255);
						seleccionat = null;
					}
				}
			}
		}

		setMissatges();
		// comprovacio de si ha guanyat
		// int cont = 0;
		// for (int i = 0; i < CO.cols*CO.rows; ++i) {
		// if (!plafoA.get(i).isClickable()) {
		// ++cont;
		// }
		// }
		//
		// if (cont == CO.cols*CO.rows) {
		// for (int i = 0; i < CO.cols2*CO.rows2;++i) {
		// plafoB.get(i).setClickable(false);
		// }
		// sound.playFinished_ok();
		// }
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
		for (int i = 0; i < (CO.cols2 * CO.rows2); i++) {
			agafats.add(false);
		}
		Random r = new Random();

		this.correspondenciesB = new ArrayList<Integer>(CO.cols2 * CO.rows2);
		for (int i = 0; i < (CO.cols2 * CO.rows2); ++i) {
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

	// @Override
	protected void onDestroy() {
		if (maxTime != 0) timer.cancel();
		sound.unloadAll();	
		if (dialog != null) dialog.dismiss();
		super.onDestroy();

	}
	
	@Override
	public void onBackPressed() {
	}
		
	@Override
	protected void onPause() {
		if (dialog != null)
			dialog.dismiss();
		super.onPause();
	}
}
