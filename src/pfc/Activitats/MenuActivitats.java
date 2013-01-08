/*
 * @Projecte: JClic per gPhone
 * @Autora: Miriam Pujol Benet
 * @Versio: Juny 2009
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