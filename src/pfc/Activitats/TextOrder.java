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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import pfc.Jclic.Jclic;
import pfc.Jclic.R;
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
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.BufferType;

/**
 * Aquesta activitat mostra un text en el qual hi ha paraules desordenades, les
 * quals estan pintades de color blau. Si es fa clic damunt d'una d'aquestes
 * paraules i després clic damunt d'una altra d'aquestes paraules, ambdues
 * paraules s'intercanvien. Després, es pot fer clic al botó Ok per a
 * comprovar si el text ha quedat ordenat o no. En cas que hi hagi paraules que
 * no hagin quedat ordenades, aquestes es mostraran de color vermell.
 * 
 * @author Xavier García García i Xavier Alvarez Juste
 * 
 */
public class TextOrder extends Activity {

	/** Contants rellevants per a poder utilitzar el parser. */
	Constants CO = Constants.getInstance();

	/** Text provinent del parser. */
	Vector<String> textOriginal = new Vector<String>();

	/**
	 * Vector que indica si un element de textOriginal es un target (true) o no
	 * (false).
	 */
	Vector<Boolean> tipusText = new Vector<Boolean>();

	/**
	 * TextView on es mostren les paraules de l'activitat en sí.
	 */
	TextView textView;

	/**
	 * Posició al text de l'inici de la primera de dues paraules que
	 * s'intercanviïn.
	 */
	int iniciPrimeraParaula;

	/**
	 * Posició al text del final de la primera de dues paraules que
	 * s'intercanviïn.
	 */
	int finalPrimeraParaula;

	/**
	 * Cert si ja s'ha fet clic a un target i s'està esperant a què es faci
	 * clic a un altre, fals altrament.
	 */
	boolean primeraParaulaTrobada;

	/**
	 * Primer dels dos targets que s'han d'intercanviar.
	 */
	String primeraParaula;

	/**
	 * Posició inicial i final al text cada de una de les paraules que formen
	 * el text.
	 */
	ArrayList<Posicio> poss = new ArrayList<Posicio>();

	/**
	 * Index al text de l'inici de les dues paraules que s'han d'intercanviar.
	 */
	Paraules paraules;

	private static final int MENU_ANT = 0;

	private static final int MENU_SEG = 1;

	private static final int MENU_SOLUCIO = 2;

	private static final int MENU_AJUDA = 3;

	private static final int MENU_INICI = 4;

	private static final int MENU_SORTIR = 5;

	Sounds sound;

	private int maxTime = Parser.getActivitats().get(CO.activitatActual)
			.getTempsMax();

	private int maxIntents = Parser.getActivitats().get(CO.activitatActual)
			.getIntentMax();

	private boolean TimeCountDown = Parser.getActivitats()
			.get(CO.activitatActual).getTimeCutDown();

	private boolean IntentCountDown = Parser.getActivitats()
			.get(CO.activitatActual).getIntentCutdown();

	int encerts = 0;

	/**
	 * Comptador d'intents.
	 */
	int contador = 0;

	/**
	 * Comptador de temps.
	 */
	int contadorTemps = 0;

	private CountDownTimer timer;

	private ProgressBar tiempo;

	@Override
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

		// Configuro els botons d'anterior i següent.
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
			/*
			 * Estem a la primera activitat així que no podem habilitar
			 * l'anterior.
			 */
			CO.menu.getItem(MENU_ANT).setEnabled(false);
		}
		if (CO.activitatActual == Parser.getActivitats().size() - 1) {
			/*
			 * Estem a l'íltima activitat així que no podem habilitar el
			 * següent.
			 */
			CO.menu.getItem(MENU_SEG).setEnabled(false);
		}

		if (CO.mostrarSolucio)
			CO.menu.getItem(MENU_SOLUCIO).setEnabled(true);
		else
			CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);
		return true;
	}

	@SuppressWarnings("unused")
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
				// Fallem per intents o per temps.
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
				// Hem acabat el joc.
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

	@Override
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
			Dialog ajuda = new AlertDialog.Builder(TextOrder.this)
					.setIcon(R.drawable.jclic_aqua).setTitle("Ajuda")
					.setPositiveButton("D'acord", null)
					.setMessage("Posa les paraules en l'ordre correcte")
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

								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									TextOrder.this.finish();
								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {

								@Override
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textorder);
		// maxTime = 20;
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
					fesDialeg("Atenció", "S'ha acabat el temps");
					openOptionsMenu();
					// setMissatges();

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

		Button b1 = (Button) findViewById(R.id.buttonMenu);
		Button buttonOk = (Button) findViewById(R.id.button1);
		buttonOk.setBackgroundColor(Color.GREEN);
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

		// this.agafarDadesParser();

		primeraParaulaTrobada = false;
		paraules = new Paraules();
		textView = (TextView) findViewById(R.id.textView1);
		textOriginal = Parser.getActivitats().elementAt(CO.activitatActual)
				.getT();
		tipusText = Parser.getActivitats().elementAt(CO.activitatActual)
				.getbool();

		// Eliminem possibles espais al principi i al final de cada element.
		for (int i = 0; i < textOriginal.size(); ++i)
			textOriginal.set(i, textOriginal.get(i).trim());

		Vector<String> textBarrejat = barrejaTargets();
		String text = converteixAString(textBarrejat);
		inicialitzaPosicioTargets(textBarrejat);
		inicialitzaTextView(text);
		buttonOk.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String textCorrecte = converteixAString(textOriginal);
				Spannable spans = (Spannable) textView.getText();
				String textActual = spans.toString();
				TextView aciertos = (TextView) findViewById(R.id.editAciertos);
				int nTargets = getNTargets();
				if (textActual.contentEquals(textCorrecte)) {
					timer.cancel();
					textView.setText(textCorrecte);
					textView.setTextColor(Color.BLACK);
					fesDialeg("Resultat", "Resposta correcta!");
					aciertos.setText(String.valueOf(nTargets));
					openOptionsMenu();
				} else {
					int encerts = nTargets;
					String[] correctes = textCorrecte.split("\\s+");
					String[] actuals = textActual.split("\\s+");
					boolean targetLlarg = false;
					int posicio = 0;
					for (int i = 0; i < correctes.length; ++i) {
						if (!correctes[i].contentEquals(actuals[i])) {
							spans.setSpan(new ForegroundColorSpan(Color.RED),
									posicio, posicio + actuals[i].length(),
									Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
							if (!targetLlarg) {
								targetLlarg = true;
								--encerts;
							}
						} else
							targetLlarg = false;
						posicio += actuals[i].length() + 1;

					}
					aciertos.setText(String.valueOf(encerts));
					fesDialeg("Resultat", "Resposta incorrecta!");
				}
			}
		});
	}

	/**
	 * Guarda la posició inicial i final dels targets.
	 * 
	 * @param textBarrejat
	 *            Text que es mostra inicialment al TextView.
	 */
	private void inicialitzaPosicioTargets(Vector<String> textBarrejat) {
		int sum = 0;
		for (int i = 0; i < tipusText.size(); ++i) {
			if (tipusText.get(i)) {
				Posicio p = new Posicio();
				p.posicioInicial = sum;
				p.posicioFinal = sum + textBarrejat.get(i).length() - 1;
				poss.add(p);
			}
			sum += textBarrejat.get(i).length();
			if (textBarrejat.get(i).length() != 0)
				++sum;
		}

	}

	/**
	 * Converteix un Vector de String a String.
	 * 
	 * @param textBarrejat
	 *            Vector de String.
	 * @return String que representa el Vector de String.
	 */
	private String converteixAString(Vector<String> textBarrejat) {
		StringBuffer convertidor = new StringBuffer();
		int ntargets = 0;
		int i, j;
		for (j = 0; j < tipusText.size(); j++) {
			if (tipusText.elementAt(j) == true)
				ntargets++;
		}
		final int posini[] = new int[ntargets];
		int posfi[] = new int[ntargets];
		int var = 0;
		String anterior = "";
		for (i = 0; i < textBarrejat.size(); i++) {
			int pos = convertidor.length();
			String aux = textBarrejat.elementAt(i);
			if (aux.length() == 0)
				convertidor.append("\n");
			else if (anterior.length() != 0)
				convertidor.append(" " + aux);
			else
				convertidor.append(aux);
			if (tipusText.elementAt(i)) {
				int posfinal = convertidor.length();
				posini[var] = pos;
				posfi[var] = posfinal;
				var++;

			}
			anterior = aux;
		}
		// posicioTargets = new int[posini.length];
		// posicioFinalTargets = new int[posini.length];
		return convertidor.toString();
	}

	/**
	 * Inicialitza el TextView amb el text que s'ha de mostrar i el prepara per
	 * a què es pugui fer clic a cada una de les paraules que el formen.
	 * 
	 * @param text
	 *            Text que es mostra al TextView.
	 */
	private void inicialitzaTextView(String text) {
		Editable str = Editable.Factory.getInstance().newEditable(text);
		textView.setMovementMethod(LinkMovementMethod.getInstance());
		textView.setText(str, BufferType.SPANNABLE);
		Spannable spans = (Spannable) textView.getText();

		Integer[] indices = getSpaceIndices(textView.getText().toString());
		int start = 0;
		int end = 0;
		for (int i = 0; i <= indices.length; i++) {
			ClickableSpan clickSpan = new ClickableSpan() {

				@Override
				public void onClick(View widget) {
					CharSequence textComplet = textView.getText();
					String text = textView
							.getText()
							.subSequence(textView.getSelectionStart(),
									textView.getSelectionEnd()).toString();
					if (esTarget(textView.getSelectionStart(), text)) {
						if (!primeraParaulaTrobada) {
							iniciPrimeraParaula = textView.getSelectionStart();
							finalPrimeraParaula = textView.getSelectionEnd();
							primeraParaulaTrobada = true;
							primeraParaula = text;
						} else {
							primeraParaulaTrobada = false;
							String nouText = "";
							for (int i = 0; i < textComplet.length(); ++i) {
								if (i != iniciPrimeraParaula) {
									if (i == textView.getSelectionStart()) {
										nouText = nouText
												.concat(primeraParaula);
										i += text.length() - 1;
									} else {
										nouText = nouText
												.concat(Character
														.toString(textComplet
																.charAt(i)));
									}
								} else {
									nouText = nouText.concat(text);
									i += primeraParaula.length() - 1;
								}
							}
							if (Math.min(paraules.indexSegonaParaula,
									paraules.indexPrimeraParaula) == paraules.indexPrimeraParaula)
								actualitzaPosicions(
										paraules.indexPrimeraParaula,
										paraules.indexSegonaParaula,
										text.length(), primeraParaula.length());
							else
								actualitzaPosicions(
										paraules.indexSegonaParaula,
										paraules.indexPrimeraParaula,
										primeraParaula.length(), text.length());

							inicialitzaTextView(nouText);
							++contador;
							TextView intentos = (TextView) findViewById(R.id.editIntentos);
							TextView aciertos = (TextView) findViewById(R.id.editAciertos);
							intentos.setText(String.valueOf(contador));
							aciertos.setText(String.valueOf(encerts));
						}
					}
				}

				/**
				 * Actualitza les posicions dels targets.
				 * 
				 * @param indexInicial
				 *            Index del primer target a partir del qual s'han
				 *            d'actualitzar les posicions.
				 * @param indexFinal
				 *            Index del segon target fins al qual s'han
				 *            d'actualitzar les posicions.
				 * @param sizePrimeraParaula
				 *            Mida de la paraula que va al primer I�ndex.
				 * @param sizeSegonaParaula
				 *            Mida de la paraula que va al segon Index.
				 */
				private void actualitzaPosicions(int indexInicial,
						int indexFinal, int sizePrimeraParaula,
						int sizeSegonaParaula) {
					Posicio pIni = poss.get(indexInicial);
					pIni.posicioFinal = pIni.posicioInicial
							+ sizePrimeraParaula - 1;
					poss.set(indexInicial, pIni);
					for (int i = indexInicial + 1; i < indexFinal; ++i) {
						Posicio p = poss.get(i);
						p.posicioInicial += sizePrimeraParaula
								- sizeSegonaParaula;
						p.posicioFinal += sizePrimeraParaula
								- sizeSegonaParaula;
						poss.set(i, p);
					}
					Posicio pFi = poss.get(indexFinal);
					pFi.posicioInicial += sizePrimeraParaula
							- sizeSegonaParaula;
					pFi.posicioFinal = pFi.posicioInicial + sizeSegonaParaula
							- 1;
					poss.set(indexFinal, pFi);
				}

				/**
				 * Determina si una paraula és o no target.
				 * 
				 * @param selectionStart
				 *            Index del text que apunta a l'inici de la paraula
				 *            en questio.
				 * @param text
				 *            Text on apareix la paraula en qüestió.
				 * @return Cert si la paraula és un target, false altrament.
				 */
				private boolean esTarget(int selectionStart, String text) {
					for (int i = 0; i < poss.size(); ++i) {
						if (selectionStart == poss.get(i).posicioInicial) {
							if (!primeraParaulaTrobada)
								paraules.indexPrimeraParaula = i;
							else
								paraules.indexSegonaParaula = i;
							return true;
						}
					}
					return false;
				}

				@Override
				public void updateDrawState(TextPaint ds) {
					ds.setColor(Color.BLACK); /*
											 * BLACK és el color per les
											 * paraules que no són target.
											 */
					ds.setUnderlineText(false);
				}
			};
			end = (i < indices.length ? indices[i] : spans.length());
			spans.setSpan(clickSpan, start, end,
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			for (int j = 0; j < poss.size(); ++j) {
				spans.setSpan(new ForegroundColorSpan(Color.BLUE),
						poss.get(j).posicioInicial,
						poss.get(j).posicioFinal + 1,
						Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); /*
															 * BLUE és el color
															 * per paraules que
															 * són target.
															 */
			}
			start = end + 1;
		}
	}

	/**
	 * Barreja els targets del text original.
	 * 
	 * @return Vector que representa el text amb els targets ens posicions
	 *         aleatories.
	 */
	private Vector<String> barrejaTargets() {
		int nTargets = 0;
		for (int i = 0; i < textOriginal.size(); i++) {
			if (tipusText.elementAt(i).equals(true)) {
				++nTargets;
			}
		}
		int posicions[] = new int[nTargets];
		int posdes[] = new int[nTargets];
		boolean trobats[] = new boolean[nTargets];
		int p = 0;
		for (int z = 0; z < textOriginal.size(); z++) {
			if (tipusText.elementAt(z).equals(true)) {
				posicions[p] = z;
				++p;
			}
		}
		boolean imparell = false;
		if (nTargets % 2 == 1)
			imparell = true;
		int j = 0;
		Random r = new Random();
		while (j < nTargets) {
			int a = r.nextInt(nTargets);
			if (trobats[a] == false
					&& (a != j || (j == nTargets - 1 && imparell))) {
				posdes[j] = posicions[a];
				trobats[a] = true;
				if (j == nTargets - 1 && imparell)
					posdes[j] = posicions[a];
				++j;
			}
		}
		String taritext[] = new String[textOriginal.size()];
		String resultat[] = new String[textOriginal.size()];
		for (int y = 0; y < textOriginal.size(); y++) {
			taritext[y] = textOriginal.elementAt(y);
			if (tipusText.elementAt(y).equals(false))
				resultat[y] = textOriginal.elementAt(y);
		}
		int p2 = 0;
		for (int k = 0; k < textOriginal.size(); k++) {
			if (tipusText.elementAt(k).equals(true)) {
				int val = posdes[p2];
				resultat[k] = taritext[val];
				++p2;
			}
		}
		int c;
		Vector<String> fin = new Vector<String>();
		for (c = 0; c < textOriginal.size(); c++) {
			String valor = resultat[c];
			fin.add(valor);
		}
		return fin;
	}

	/**
	 * Calcula on comença i on acaba cada paraula.
	 * 
	 * @param s
	 *            Text complet.
	 * @return Array que indica les posicions de cada una de les paraules.
	 */
	public static Integer[] getSpaceIndices(String s) {
		int posEspai = s.indexOf(' ', 0);
		int posLinia = s.indexOf('\n', 0);
		List<Integer> indices = new ArrayList<Integer>();
		int posicio = Math.min(posEspai, posLinia);
		if (posicio == -1)
			posicio = posEspai;
		while (posicio != -1) {
			indices.add(posicio);
			if (posicio == posLinia)
				posLinia = s.indexOf('\n', posLinia + 1);
			else
				posEspai = s.indexOf(' ', posEspai + 1);
			posicio = Math.min(posEspai, posLinia);
			if (posicio == -1)
				posicio = posEspai;
		}
		return indices.toArray(new Integer[0]);
	}

	/**
	 * Obté el número de targets del text.
	 * 
	 * @return Número de targets.
	 */
	public int getNTargets() {
		int nTargets = 0;
		for (int i = 0; i < tipusText.size(); ++i)
			if (tipusText.get(i))
				++nTargets;
		return nTargets;
	}

	/**
	 * Construeix i mostra un diàleg.
	 * 
	 * @param titol
	 *            Títol del diàleg.
	 * @param missatge
	 *            Missatge del diàleg.
	 */
	public void fesDialeg(String titol, String missatge) {
		Dialog dialeg = new AlertDialog.Builder(TextOrder.this)
				.setIcon(R.drawable.jclic_aqua).setTitle(titol)
				.setPositiveButton("D'acord", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						/*
						 * Es pot passar a la següent activitat a través del
						 * menú. així que aquí no cal fer res.
						 */

					}
				}).setMessage(missatge).create();
		dialeg.show();

	}

	class Posicio {

		public int posicioInicial;

		public int posicioFinal;
	}

	class Paraules {

		public int indexPrimeraParaula;

		public int indexSegonaParaula;
	}
}