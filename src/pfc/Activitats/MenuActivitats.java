/*
 * @Projecte: JClic per gPhone
 * @Autora: Miriam Pujol Benet
 * @Versio: Juny 2009
 */

package pfc.Activitats;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

import pfc.Descompressor.Descompressor;
import pfc.Jclic.Jclic;
import pfc.Jclic.R;
import pfc.Parser.Parser;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent.OnFinished;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

@TargetApi(3)
public class MenuActivitats extends Activity {
	private static final int MENU_SOLUCIO = 2;
	private CountDownTimer timer;
	private Constants CO = Constants.getInstance();
	private int activitatMenu;
	private String activitatMenuName;

	Sounds sound;

	private int maxTime = Parser.getActivitats().get(CO.activitatActual)
			.getTempsMax();
	private int maxIntents = Parser.getActivitats().get(CO.activitatActual)
			.getIntentMax();
	private boolean TimeCountDown = Parser.getActivitats()
			.get(CO.activitatActual).getTimeCutDown();
	private boolean IntentCountDown = Parser.getActivitats()
			.get(CO.activitatActual).getIntentCutdown();
	private int maxTime1 = Parser.getActivitats().get(CO.activitatActual).getTempsMax();


	int contador = 0; // Comptador per als intents.
	int contadorTemps = 0; // Comptador per al temps.

	
	public MenuActivitats(CountDownTimer t) {
		timer = t;
		activitatMenu = CO.activitatActual;
	}

	
	public void butsMenu(final Dialog d, final Context aC, final Vector<BitmapDrawable> vecDraw) {
		activitatMenuName = Parser.getActivitats().get(CO.activitatActual).getName();
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
				activitatMenuName = Parser.getActivitats().get(activitatMenu).getName();
				TextView clicText = (TextView) d.findViewById(R.id.tMenuClic);
				clicText.setText(activitatMenuName);
			    if(activitatMenu <= 0) bAnt.setEnabled(false);
			    else bAnt.setEnabled(true);
			    if(activitatMenu >= (Parser.getActivitats().size())-1) bSeg.setEnabled(false);
			    else bSeg.setEnabled(true);
			}
		});
		
		bSeg.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				activitatMenu++;
				activitatMenuName = Parser.getActivitats().get(activitatMenu).getName();
				TextView clicText = (TextView) d.findViewById(R.id.tMenuClic);
				clicText.setText(activitatMenuName);
			    if(activitatMenu <= 0) bAnt.setEnabled(false);
			    else bAnt.setEnabled(true);
			    if(activitatMenu >= (Parser.getActivitats().size())-1) bSeg.setEnabled(false);
			    else bSeg.setEnabled(true); 
			}
		});
		
		if(activitatMenu == 0){
			//estem a la primera activitat, pel que no podem habilitar l'anterior
			bAnt.setEnabled(false);
		}
	    if(activitatMenu == (Parser.getActivitats().size() - 1)){
			//estem a l'ultima activitat, pel que no podem habilitar el seguent
			bSeg.setEnabled(false);
		}

		bOk.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (maxTime1 != 0) 
					timer.cancel();
				CO.activitatActual = activitatMenu-1;
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
							CO.vecCaselles.elementAt(i).setBackgroundColor(CO.bg);
							CO.vecCaselles.elementAt(i).setTextColor(CO.fg);

							if (CO.imatge != null) {
								int indexSort = CO.sortida.indexOf(CO.vecCaselles
										.elementAt(i).getText());

								CO.vecCaselles.elementAt(i).setBackgroundColor(
										Color.TRANSPARENT);
								CO.vecCaselles.elementAt(i).setTextColor(
										Color.TRANSPARENT);

								vecDraw.elementAt(indexSort).setAlpha(250);

								CO.vecCaselles.elementAt(i).setBackgroundDrawable(
										vecDraw.elementAt(indexSort));
							}
						} else
							CO.vecActual.addElement(null);
					}
					bloquejarJoc(true);
					CO.solucioVisible = true;
					//posAgafada = null;
					setMissatges();
				} else {
					// Estic mostrant la solucio i vull continuar
					for (int i = 0; i < CO.vecCaselles.size(); i++) {
						if (CO.vecCaselles.elementAt(i) != null) {
							CO.vecCaselles.elementAt(i).setText(
									CO.vecActual.elementAt(i));
							CO.vecCaselles.elementAt(i).setTextColor(CO.fg);
							CO.vecCaselles.elementAt(i).setBackgroundColor(CO.bg);

							if (CO.imatge != null) {
								int indexSort = CO.sortida.indexOf(CO.vecCaselles
										.elementAt(i).getText());

								CO.vecCaselles.elementAt(i).setBackgroundColor(
										Color.TRANSPARENT);
								CO.vecCaselles.elementAt(i).setTextColor(
										Color.TRANSPARENT);

								vecDraw.elementAt(indexSort).setAlpha(250);

								CO.vecCaselles.elementAt(i).setBackgroundDrawable(
										vecDraw.elementAt(indexSort));
							}
						}
					}
					bloquejarJoc(false);
					CO.solucioVisible = false;
					/*setMissatges();
					CO.menu.getItem(MENU_SOLUCIO).setTitle(R.string.menu_solucio);*/
				}
			}
		});
		
		if(!CO.solucioVisible) bSolucio.setEnabled(true);
	    else bSolucio.setEnabled(false);
		
		

		bAjuda.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Dialog ajuda = new AlertDialog.Builder(aC)
						.setIcon(R.drawable.jclic_aqua)
						.setTitle("Ajuda")
						.setPositiveButton("D'acord", null)
						.setMessage(
								"Intercanvia les caselles per ordenar el puzzle.")
						.create();
				ajuda.show();
			}
		});


		bInici.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (maxTime1 != 0)
				timer.cancel();
				Intent i = new Intent(aC, Jclic.class);
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
			if (CO.correcte == CO.casIni) {
				// Hem acabat el joc
				sound.playFinished_ok();
				if (maxTime != 0)
					timer.cancel();
				if (Parser.getActivitats().elementAt(CO.activitatActual)
						.getMissatgeFi() != null) {
					CO.miss.setText(Parser.getActivitats()
							.elementAt(CO.activitatActual).getMissatgeFi());
					CO.miss2.setText(Parser.getActivitats()
							.elementAt(CO.activitatActual).getMissatgeFi());
				} else {
					CO.miss.setText("Joc finalitzat!");
					CO.miss2.setText("Joc finalitzat!");
				}

				// CO.missCorrectes.setText("Prem aqui per continuar.");
				CO.missCorrectes.setBackgroundColor(Color.WHITE);
				CO.missCorrectes.setTextColor(Color.BLACK);

				// CO.missCorrectes2.setText("Prem aqui per continuar.");
				CO.missCorrectes2.setBackgroundColor(Color.WHITE);
				CO.missCorrectes2.setTextColor(Color.BLACK);

				bloquejarJoc(true);
				/*if (CO.menu != null)
					CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);*/

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
				if (maxTime!= 0 && contadorTemps == maxTime) {
					CO.miss.setText("S'ha acabat el temps!");
					CO.miss2.setText("S'ha acabat el temps!");
				} else {
					CO.miss.setText("Has superat els intents maxims!");
					CO.miss2.setText("Has superat els intents maxims!");
				}
				CO.missCorrectes.setText("Prem aqui per continuar.");
				CO.missCorrectes.setBackgroundColor(Color.WHITE);
				CO.missCorrectes.setTextColor(Color.BLACK);

				CO.missCorrectes2.setText("Prem aqui per continuar.");
				CO.missCorrectes2.setBackgroundColor(Color.WHITE);
				CO.missCorrectes2.setTextColor(Color.BLACK);

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
			}
		}
	}
}