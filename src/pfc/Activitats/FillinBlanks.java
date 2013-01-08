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

import pfc.Jclic.Jclic;
import pfc.Jclic.R;
import pfc.Parser.Dades;
import pfc.Parser.Parser;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressLint("NewApi")
public class FillinBlanks extends Activity {
	private static final int MENU_ANT = 0;
	private static final int MENU_SEG = 1;
	private static final int MENU_SOLUCIO = 2;
	private static final int MENU_AJUDA = 3;
	private static final int MENU_INICI = 4;
	private static final int MENU_SORTIR = 5;

	private Constants CO = Constants.getInstance();
	private Dades dades = new Dades();
	private ArrayList<Dades.Info> arrayDades;

	Sounds sound;
	private int maxTime = 0;
	private int maxIntents = 0;
	private boolean TimeCountDown = false;
	private boolean IntentCountDown = false;
	int encerts = 0;
	int contador = 10; // Comptador per als intents.
	int contadorTemps = 0; // Comptador per al temps.
	private CountDownTimer timer;
	private ProgressBar tiempo;

	class Info {
		public boolean isBlank;
		public String text;
	}

	private void agafarDadesParser() {
		if (CO.activitatActual < Parser.getActivitats().size() - 1) {
			// podem agafar l'activitat
			CO.activitatActual++;
			CO.solucioVisible = false;

			this.arrayDades = (ArrayList<Dades.Info>) Parser.getActivitats()
					.elementAt(CO.activitatActual).getArrayFillInBlanks();

			CO.InfoArray = Parser.getActivitats().elementAt(CO.activitatActual)
					.getArrayFillInBlanks();

			int maxTime = Parser.getActivitats().elementAt(CO.activitatActual)
					.getTempsMax();
			maxTime = 60;
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
						Log.d("id", "acaba el temporizador");

						Dialog finalitzat = new AlertDialog.Builder(
								FillinBlanks.this)
								.setIcon(R.drawable.jclic_aqua)
								.setTitle("Atenci—")
								.setPositiveButton("D'acord",
										new OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												Intent i = new Intent(
														FillinBlanks.this,
														Jclic.class);
												startActivity(i);
												finish();
											}
										}).setMessage("S'acabat el temps.")
								.create();
						finalitzat.show();

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

		} else {
			Dialog finalitzat = new AlertDialog.Builder(this)
					.setIcon(R.drawable.jclic_aqua).setTitle("Atenci—")
					.setPositiveButton("D'acord", null)
					.setMessage("Ja no queden mŽs activitats.").create();
			finalitzat.show();
		}

	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fillinblanks);
		Button b1 = (Button) findViewById(R.id.buttonMenu);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openOptionsMenu();
			}
		});
		TextView intentos = (TextView) findViewById(R.id.editIntentos);
		TextView aciertos = (TextView) findViewById(R.id.editAciertos);
		intentos.setText(String.valueOf(contador));
		aciertos.setText(String.valueOf(encerts));
		this.agafarDadesParser();

		maxTime = Parser.getActivitats().get(CO.activitatActual).getTempsMax();
		maxIntents = Parser.getActivitats().get(CO.activitatActual)
				.getIntentMax();
		TimeCountDown = Parser.getActivitats().get(CO.activitatActual)
				.getTimeCutDown();
		IntentCountDown = Parser.getActivitats().get(CO.activitatActual)
				.getIntentCutdown();
		LinearLayout container = (LinearLayout) findViewById(R.id.linearLayoutfillinblanks);

		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		dades.Dades();
		for (int i = 0; i < arrayDades.size(); ++i) {
			Info inf = new Info();
			if (arrayDades.get(i).isBlank == true) {

				final EditTextFBlanks tv = new EditTextFBlanks(this,
						arrayDades.get(i).text);
				tv.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
				int size = arrayDades.get(i).text.length();
				InputFilter[] filters = new InputFilter[1];
				filters[0] = new InputFilter.LengthFilter(size);
				tv.setFilters(filters);
				tv.setOnKeyListener(new OnKeyListener() {

					@Override
					public boolean onKey(View v, int keyCode, KeyEvent event) {
						if (event.getAction() == KeyEvent.ACTION_DOWN) {
							switch (keyCode) {
							case KeyEvent.KEYCODE_ENTER:
								String t = tv.getText().toString();
								if (tv.getText().toString()
										.equalsIgnoreCase(tv.getUserInput())) {
									++encerts;
									TextView intentos = (TextView) findViewById(R.id.editIntentos);
									TextView aciertos = (TextView) findViewById(R.id.editAciertos);
									intentos.setText(String.valueOf(contador));
									aciertos.setText(String.valueOf(encerts));
									tv.setFocusable(false);
								} else {
									--contador;
									TextView intentos = (TextView) findViewById(R.id.editIntentos);
									TextView aciertos = (TextView) findViewById(R.id.editAciertos);
									intentos.setText(String.valueOf(contador));
									aciertos.setText(String.valueOf(encerts));

									if (contador <= 0) {
										Dialog finalitzat = new AlertDialog.Builder(
												FillinBlanks.this)
												.setIcon(R.drawable.jclic_aqua)
												.setTitle("Atenci—")
												.setPositiveButton("D'acord",
														new OnClickListener() {

															@Override
															public void onClick(
																	DialogInterface dialog,
																	int which) {
																// TODO
																// Auto-generated
																// method stub
																Intent i = new Intent(
																		FillinBlanks.this,
																		Jclic.class);
																startActivity(i);
																finish();
															}
														})
												.setMessage(
														"Has esgotat els intents.")
												.create();
										finalitzat.show();
									}

								}

								return true;
							default:
								break;
							}
						}
						return false;
					}
				});
				tv.addTextChangedListener(new TextWatcher() {
					boolean b = false;

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub
						int num = tv.getUserInput().length();
						int num2 = tv.getText().length() + 1;
						if (num2 == num) {
							b = true;

						} else {
							tv.setBackgroundColor(0xFFFF0000);

						}
					}

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub

					}

					@Override
					public void afterTextChanged(Editable e) {
						// TODO Auto-generated method stub}
						if (b) {
							String t = tv.getText().toString();
							if (!tv.getText().toString()
									.equals(tv.getUserInput())) {
								tv.setBackgroundColor(0xFFFF0000);
								b = false;
							} else {
								tv.setBackgroundColor(0xFF00FF00);
								b = false;
							}
						}
					}

				});

				ll.addView(tv, layoutParams);
			} else {
				String text = (String) arrayDades.get(i).text;
				text += " ";
				TextView tv = new TextView(this);

				tv.setText(text);

				ll.addView(tv, layoutParams);
			}
			// setContentView(ll);
		}
		container.addView(ll);

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

			// Configuracio del menu per mostrarSolucio-> es un boolean
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

	private void setMissatges() {
		if (CO.solucioVisible) {
			CO.miss.setText("");
			CO.missCorrectes.setText("");
			CO.cas1.setText("");
			CO.p1 = "<buit>";
			CO.p2 = "<buit>";
		} else {
			if ((maxIntents != 0 && maxIntents == contador && CO.correcte != CO.casIni)
					|| contadorTemps == maxTime && maxTime != 0) {
				// fallem per intents o per temps
				sound.playFinished_error();
				if (Parser.getActivitats().elementAt(CO.activitatActual)
						.getMissatgeFi() != null)
					CO.miss.setText(Parser.getActivitats()
							.elementAt(CO.activitatActual).getMissatgeFi());
				else
					CO.miss.setText("Superat els intents màxims");
				if (maxTime != 0)
					timer.cancel();
				CO.missCorrectes.setText("Prem aquí per continuar.");
				CO.missCorrectes.setBackgroundColor(Color.WHITE);
				CO.missCorrectes.setTextColor(Color.BLACK);

				// bloquejarJoc(true);
				if (CO.menu != null)
					CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);
			} else if (CO.correcte == CO.vecCaselles.size()) {
				// Hem acabat el joc
				if (maxTime != 0)
					timer.cancel();
				sound.playFinished_ok();
				if (Parser.getActivitats().elementAt(CO.activitatActual)
						.getMissatgeFi() != null)
					CO.miss.setText(Parser.getActivitats()
							.elementAt(CO.activitatActual).getMissatgeFi());
				else
					CO.miss.setText("Joc finalitzat!");

				CO.missCorrectes.setText("Prem aquí per continuar.");
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
					CO.miss.setText("Comença el joc!");
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
				CO.missCorrectes.setText("C = " + CO.correcte + ", In ="
						+ displayedIntents + ", T =" + displayedTime);

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

		// Configuro els botons d'anterior i seguent
		CO.menu.getItem(MENU_SEG).setEnabled(true);
		CO.menu.getItem(MENU_ANT).setEnabled(true);

		CO.menu.getItem(MENU_ANT).setIcon(android.R.drawable.ic_media_rew);
		CO.menu.getItem(MENU_SEG).setIcon(android.R.drawable.ic_media_ff);
		CO.menu.getItem(MENU_SOLUCIO).setIcon(
				android.R.drawable.btn_star_big_off);
		CO.menu.getItem(MENU_AJUDA).setIcon(android.R.drawable.ic_menu_help);
		CO.menu.getItem(MENU_INICI).setIcon(android.R.drawable.ic_menu_revert);
		CO.menu.getItem(MENU_SORTIR).setIcon(
				android.R.drawable.ic_menu_close_clear_cancel);

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

	@SuppressLint({ "NewApi", "NewApi", "NewApi", "NewApi", "NewApi" })
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
			Dialog ajuda = new AlertDialog.Builder(FillinBlanks.this)
					.setIcon(R.drawable.jclic_aqua).setTitle("Ajuda")
					.setPositiveButton("D'acord", null)
					.setMessage("Introdueix paraules als espais buits")
					.create();
			ajuda.show();
			return true;
		case MENU_SORTIR:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.jclic_aqua);
			builder.setMessage("Estàs segur de que vols sortir?")
					.setCancelable(false)
					.setPositiveButton("Sí",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									FillinBlanks.this.finish();
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

}