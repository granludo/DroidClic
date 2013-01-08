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

import java.util.Vector;

import pfc.Jclic.Inici;
import pfc.Jclic.R;
import pfc.Parser.Parser;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

@TargetApi(3)
public class MenuActivitats extends Activity {
	private static final int MENU_SOLUCIO = 2;
	private CountDownTimer timer;
	private Constants CO = Constants.getInstance();
	private int activitatMenu;
	private String activitatMenuName;
	private Boolean ultimaActivitat = false;

	Sounds sound;

	private int maxTime = Parser.getActivitats().get(CO.activitatActual)
			.getTempsMax();
	private int maxIntents = Parser.getActivitats().get(CO.activitatActual)
			.getIntentMax();
	private boolean TimeCountDown = Parser.getActivitats()
			.get(CO.activitatActual).getTimeCutDown();
	private boolean IntentCountDown = Parser.getActivitats()
			.get(CO.activitatActual).getIntentCutdown();
	private int maxTime1 = Parser.getActivitats().get(CO.activitatActual)
			.getTempsMax();

	int contador = 0; // Comptador per als intents.
	int contadorTemps = 0; // Comptador per al temps.

	public MenuActivitats(CountDownTimer t) {
		timer = t;
		activitatMenu = CO.activitatActual;
	}

	public void butsMenu(final Dialog d, final Context aC,
			final Vector<BitmapDrawable> vecDraw) {
		activitatMenuName = Parser.getActivitats().get(CO.activitatActual)
				.getName();
		TextView clicText = (TextView) d.findViewById(R.id.tMenuClic);
		clicText.setText(activitatMenuName);

		final Button bAnt = (Button) d.findViewById(R.id.bMenuAnterior);
		// Drawable d = Drawable.createFromPath("@drawable/ic_media_rew");
		// bAnt.setBackgroundDrawable(d);
		final Button bSeg = (Button) d.findViewById(R.id.bMenuPosterior);
		// d = Drawable.createFromPath("@drawable/ic_media_ff");
		// bAnt.setBackgroundDrawable(d);
		final Button bOk = (Button) d.findViewById(R.id.bMenuOk);
		final Button bSolucio = (Button) d.findViewById(R.id.bMenuResolver);
		// d = Drawable.createFromPath("@drawable/btn_star_big_off");
		// bAnt.setBackgroundDrawable(d);
		final Button bAjuda = (Button) d.findViewById(R.id.bMenuAyuda);
		// d = Drawable.createFromPath("@drawable/ic_menu_help");
		// bAnt.setBackgroundDrawable(d);
		final Button bInici = (Button) d.findViewById(R.id.bMenuInici);
		// d = Drawable.createFromPath("@drawable/ic_menu_revert");
		// bAnt.setBackgroundDrawable(d);

		bAnt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				activitatMenu--;
				activitatMenuName = Parser.getActivitats().get(activitatMenu)
						.getName();
				TextView clicText = (TextView) d.findViewById(R.id.tMenuClic);
				clicText.setText(activitatMenuName);
				if (activitatMenu <= 0)
					bAnt.setEnabled(false);
				else
					bAnt.setEnabled(true);
				if (activitatMenu >= (Parser.getActivitats().size()) - 1)
					ultimaActivitat = true;
				else
					ultimaActivitat = false;
			}
		});

		if (activitatMenu == 0) {
			// estem a la primera activitat, pel que no podem habilitar
			// l'anterior
			bAnt.setEnabled(false);
		}
		if (activitatMenu == (Parser.getActivitats().size() - 1)) {
			// estem a l'ultima activitat, pel que no podem habilitar el seguent
			ultimaActivitat = true;
		} else
			ultimaActivitat = false;

		bSeg.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (ultimaActivitat) {
					if (maxTime1 != 0) timer.cancel();
						Intent i = new Intent(aC, Inici.class);
						aC.startActivity(i);
						((Activity) aC).finish();
				}
				else {
					activitatMenu++;
					activitatMenuName = Parser.getActivitats()
							.get(activitatMenu).getName();
					TextView clicText = (TextView) d
							.findViewById(R.id.tMenuClic);
					clicText.setText(activitatMenuName);
					if (activitatMenu <= 0)
						bAnt.setEnabled(false);
					else
						bAnt.setEnabled(true);
					if (activitatMenu >= (Parser.getActivitats().size()) - 1)
						ultimaActivitat = true;
					else
						ultimaActivitat = false;
				}
			}
		});

		bOk.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (maxTime1 != 0)
					timer.cancel();
				CO.activitatActual = activitatMenu - 1;
				Intent iSeg = new Intent(aC, Puzzle.class);
				aC.startActivity(iSeg);
				((Activity) aC).finish();
			}
		});

		bSolucio.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				d.dismiss();
				if (!CO.solucioVisible) {
					if (maxTime1 != 0)
						timer.cancel();
					// Vull mostrar la solucio
					CO.vecActual = new Vector<CharSequence>();
					for (int i = 0; i < CO.vecCaselles.size(); i++) {
						if (CO.vecCaselles.elementAt(i) != null) {
							CO.vecActual.addElement(CO.vecCaselles.elementAt(i)
									.getText());
							CO.vecCaselles.elementAt(i).setText(
									CO.sortida.elementAt(i));
							CO.vecCaselles.elementAt(i).setBackgroundColor(
									CO.bg);
							CO.vecCaselles.elementAt(i).setTextColor(CO.fg);

							if (CO.imatge != null) {
								int indexSort = CO.sortida
										.indexOf(CO.vecCaselles.elementAt(i)
												.getText());

								CO.vecCaselles.elementAt(i).setBackgroundColor(
										Color.TRANSPARENT);
								CO.vecCaselles.elementAt(i).setTextColor(
										Color.TRANSPARENT);

								vecDraw.elementAt(indexSort).setAlpha(250);

								CO.vecCaselles.elementAt(i)
										.setBackgroundDrawable(
												vecDraw.elementAt(indexSort));
							}
						} else
							CO.vecActual.addElement(null);
					}
					bloquejarJoc(true);
					CO.solucioVisible = true;
					// posAgafada = null;
					//setMissatges();
				} else {
					// Estic mostrant la solucio i vull continuar
					for (int i = 0; i < CO.vecCaselles.size(); i++) {
						if (CO.vecCaselles.elementAt(i) != null) {
							CO.vecCaselles.elementAt(i).setText(
									CO.vecActual.elementAt(i));
							CO.vecCaselles.elementAt(i).setTextColor(CO.fg);
							CO.vecCaselles.elementAt(i).setBackgroundColor(
									CO.bg);

							if (CO.imatge != null) {
								int indexSort = CO.sortida
										.indexOf(CO.vecCaselles.elementAt(i)
												.getText());

								CO.vecCaselles.elementAt(i).setBackgroundColor(
										Color.TRANSPARENT);
								CO.vecCaselles.elementAt(i).setTextColor(
										Color.TRANSPARENT);

								vecDraw.elementAt(indexSort).setAlpha(250);

								CO.vecCaselles.elementAt(i)
										.setBackgroundDrawable(
												vecDraw.elementAt(indexSort));
							}
						}
					}
					bloquejarJoc(false);
					CO.solucioVisible = false;
					/*
					 * setMissatges();
					 * CO.menu.getItem(MENU_SOLUCIO).setTitle(R.string
					 * .menu_solucio);
					 */
				}
			}
		});

		if (!CO.solucioVisible && vecDraw != null)
			bSolucio.setEnabled(true);
		else
			bSolucio.setEnabled(false);

		bAjuda.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Dialog ajuda = new AlertDialog.Builder(aC)
						.setIcon(R.drawable.jclic_aqua).setTitle("Ajuda")
						.setPositiveButton("D'acord", null)
						.setMessage("Cambiarlo para cada actividad").create();
				ajuda.show();
			}
		});

		bInici.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (maxTime1 != 0) timer.cancel();
				Intent i = new Intent(aC, Inici.class);
				aC.startActivity(i);
				((Activity) aC).finish();
			}
		});
	}

	private void bloquejarJoc(boolean bloquejar) {
		// Fem que es bloquegi o desbloquegi l'activitat
		for (int i = 0; i < CO.vecCaselles.size(); i++) {
			if (CO.vecCaselles.elementAt(i) != null)
				CO.vecCaselles.elementAt(i).setEnabled(!bloquejar);
		}
	}

}