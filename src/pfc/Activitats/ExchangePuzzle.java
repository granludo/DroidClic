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
import java.util.Vector;

import pfc.Descompressor.Descompressor;
import pfc.Jclic.R;
import pfc.Parser.Parser;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.TextView;

@TargetApi(3)
public class ExchangePuzzle extends Activity {
	private int newWidth;
	private int newHeight;
	private int width;
	private int height;
	private TextView posAgafada1 = null;
	private TextView posAgafada2 = null;
	//private TextView aciertos = null;
	private TextView intentos = null;
	private TextView ttiempo = null;
    private Button bMenu = null;

	private ProgressBar tiempo = null;
	private Vector<BitmapDrawable> vecDraw;
	private Sounds sounds;
	private Constants CO = Constants.getInstance();
	private int maxTime = Parser.getActivitats().get(CO.activitatActual)
			.getTempsMax();
	private int maxIntents = Parser.getActivitats().get(CO.activitatActual)
			.getIntentMax();
	private boolean TimeCountDown = Parser.getActivitats()
			.get(CO.activitatActual).getTimeCutDown();
	private boolean IntentCountDown = Parser.getActivitats()
			.get(CO.activitatActual).getIntentCutdown();
	private int cont = 0;
	private int contador = 0;
	private int contadorT = 0;
	private CountDownTimer timer;

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    setContentView(R.layout.exchange_hole_puzzle);
	    sounds = new Sounds(this.getApplicationContext());
	    //aciertos = (TextView) findViewById(R.id.editAciertos);
	    intentos = (TextView) findViewById(R.id.editIntentos);
	    ttiempo = (TextView)findViewById(R.id.tiempo);
	    tiempo = (ProgressBar) findViewById(R.id.progressTime);
	    
	    tiempo.setMax(maxTime);
	    tiempo.setProgress(0);
	    if (maxTime == 0) {
			tiempo.setVisibility(tiempo.INVISIBLE);
			ttiempo.setVisibility(ttiempo.INVISIBLE);
		}
	    bMenu = (Button) findViewById(R.id.menu);
	    

	    
	    try{

			agafarDades();
			comprobarInicial();
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
			sounds.playStart();
			setOnClickListener();
			if (maxTime != 0) {
				timer = new CountDownTimer(maxTime * 1000, 1000) {

					@Override
					public void onFinish() {
						++contadorT;
						tiempo.setProgress(contadorT);
						setMissatges();

					}

					@Override
					public void onTick(long arg0) {
						contadorT++;
						tiempo.setProgress(contadorT);
						setMissatges();

					}

				}.start();
			}
		} catch (Exception e) {
			Log.d("Error", "catch ExchangePuzzle: " + e);
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


	private void agafarDades() {
		CO.tl = (TableLayout) findViewById(R.id.tl);

		agafarCaselles();
		// CO.intentMax =
		// CO.miss = (TextView) findViewById(R.id.missatge);
		CO.missCorrectes = (TextView) findViewById(R.id.editAciertos);
		CO.cas1 = (TextView) findViewById(R.id.cas1);
		CO.name = (TextView) findViewById(R.id.titulo);

		// CO.miss.setTextColor(Color.WHITE);
		// CO.missCorrectes.setTextColor(Color.WHITE);
		CO.name.setTextColor(Color.WHITE);
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
		bloquejarJoc(false);
	}

	private void agafarCaselles() {
		boolean anterior = false;
		int caselles = CO.entrada.size();

		if (findViewById(R.id.pos1) != null && caselles > 0) {
			if (CO.entrada.elementAt(0) != null) {
				CO.pos1 = (TextView) findViewById(R.id.pos1);
				CO.vecCaselles.addElement(CO.pos1);
				anterior = true;
			} else {
				reiniciarCasella(CO.pos1);
				CO.vecCaselles.addElement(null);
			}
		}

		if (findViewById(R.id.pos2) != null && anterior && caselles > 1) {
			if (CO.entrada.elementAt(1) != null) {
				CO.pos2 = (TextView) findViewById(R.id.pos2);
				CO.vecCaselles.addElement(CO.pos2);
			} else {
				reiniciarCasella(CO.pos2);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos2);
			anterior = false;
		}

		if (findViewById(R.id.pos3) != null && anterior && caselles > 2) {
			if (CO.entrada.elementAt(2) != null) {
				CO.pos3 = (TextView) findViewById(R.id.pos3);
				CO.vecCaselles.addElement(CO.pos3);
			} else {
				reiniciarCasella(CO.pos3);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos3);
			anterior = false;
		}

		if (findViewById(R.id.pos4) != null && anterior && caselles > 3) {
			if (CO.entrada.elementAt(3) != null) {
				CO.pos4 = (TextView) findViewById(R.id.pos4);
				CO.vecCaselles.addElement(CO.pos4);
			} else {
				reiniciarCasella(CO.pos4);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos4);
			anterior = false;
		}

		if (findViewById(R.id.pos5) != null && anterior && caselles > 4) {
			if (CO.entrada.elementAt(4) != null) {
				CO.pos5 = (TextView) findViewById(R.id.pos5);
				CO.vecCaselles.addElement(CO.pos5);
			} else {
				reiniciarCasella(CO.pos5);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos5);
			anterior = false;
		}

		if (findViewById(R.id.pos6) != null && anterior && caselles > 5) {
			if (CO.entrada.elementAt(5) != null) {
				CO.pos6 = (TextView) findViewById(R.id.pos6);
				CO.vecCaselles.addElement(CO.pos6);
			} else {
				reiniciarCasella(CO.pos6);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos6);
			anterior = false;
		}

		if (findViewById(R.id.pos7) != null && anterior && caselles > 6) {
			if (CO.entrada.elementAt(6) != null) {
				CO.pos7 = (TextView) findViewById(R.id.pos7);
				CO.vecCaselles.addElement(CO.pos7);
			} else {
				reiniciarCasella(CO.pos7);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos7);
			anterior = false;
		}

		if (findViewById(R.id.pos8) != null && anterior && caselles > 7) {
			if (CO.entrada.elementAt(7) != null) {
				CO.pos8 = (TextView) findViewById(R.id.pos8);
				CO.vecCaselles.addElement(CO.pos8);
			} else {
				reiniciarCasella(CO.pos8);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos8);
			anterior = false;
		}

		if (findViewById(R.id.pos9) != null && anterior && caselles > 8) {
			if (CO.entrada.elementAt(8) != null) {
				CO.pos9 = (TextView) findViewById(R.id.pos9);
				CO.vecCaselles.addElement(CO.pos9);
			} else {
				reiniciarCasella(CO.pos9);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos9);
			anterior = false;
		}

		if (findViewById(R.id.pos10) != null && anterior && caselles > 9) {
			if (CO.entrada.elementAt(9) != null) {
				CO.pos10 = (TextView) findViewById(R.id.pos10);
				CO.vecCaselles.addElement(CO.pos10);
			} else {
				reiniciarCasella(CO.pos10);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos10);
			anterior = false;
		}

		if (findViewById(R.id.pos11) != null && anterior && caselles > 10) {
			if (CO.entrada.elementAt(10) != null) {
				CO.pos11 = (TextView) findViewById(R.id.pos11);
				CO.vecCaselles.addElement(CO.pos11);
			} else {
				reiniciarCasella(CO.pos11);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos11);
			anterior = false;
		}

		if (findViewById(R.id.pos12) != null && anterior && caselles > 11) {
			if (CO.entrada.elementAt(11) != null) {
				CO.pos12 = (TextView) findViewById(R.id.pos12);
				CO.vecCaselles.addElement(CO.pos12);
			} else {
				reiniciarCasella(CO.pos12);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos12);
			anterior = false;
		}

		if (findViewById(R.id.pos13) != null && anterior && caselles > 12) {
			if (CO.entrada.elementAt(12) != null) {
				CO.pos13 = (TextView) findViewById(R.id.pos13);
				CO.vecCaselles.addElement(CO.pos13);
			} else {
				reiniciarCasella(CO.pos13);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos13);
			anterior = false;
		}

		if (findViewById(R.id.pos14) != null && anterior && caselles > 13) {
			if (CO.entrada.elementAt(13) != null) {
				CO.pos14 = (TextView) findViewById(R.id.pos14);
				CO.vecCaselles.addElement(CO.pos14);
			} else {
				reiniciarCasella(CO.pos14);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos14);
			anterior = false;
		}

		if (findViewById(R.id.pos15) != null && anterior && caselles > 14) {
			if (CO.entrada.elementAt(14) != null) {
				CO.pos15 = (TextView) findViewById(R.id.pos15);
				CO.vecCaselles.addElement(CO.pos15);
			} else {
				reiniciarCasella(CO.pos15);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos15);
			anterior = false;
		}

		if (findViewById(R.id.pos16) != null && anterior && caselles > 15) {
			if (CO.entrada.elementAt(15) != null) {
				CO.pos16 = (TextView) findViewById(R.id.pos16);
				CO.vecCaselles.addElement(CO.pos16);
			} else {
				reiniciarCasella(CO.pos16);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos16);
			anterior = false;
		}

		if (findViewById(R.id.pos17) != null && anterior && caselles > 16) {
			if (CO.entrada.elementAt(16) != null) {
				CO.pos17 = (TextView) findViewById(R.id.pos17);
				CO.vecCaselles.addElement(CO.pos17);
			} else {
				reiniciarCasella(CO.pos17);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos17);
			anterior = false;
		}

		if (findViewById(R.id.pos18) != null && anterior && caselles > 17) {
			if (CO.entrada.elementAt(17) != null) {
				CO.pos18 = (TextView) findViewById(R.id.pos18);
				CO.vecCaselles.addElement(CO.pos18);
			} else {
				reiniciarCasella(CO.pos18);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos18);
			anterior = false;
		}

		if (findViewById(R.id.pos19) != null && anterior && caselles > 18) {
			if (CO.entrada.elementAt(18) != null) {
				CO.pos19 = (TextView) findViewById(R.id.pos19);
				CO.vecCaselles.addElement(CO.pos19);
			} else {
				reiniciarCasella(CO.pos19);
				CO.vecCaselles.addElement(null);
			}
		} else {
			reiniciarCasella(CO.pos19);
			anterior = false;
		}

		if (findViewById(R.id.pos20) != null && anterior && caselles > 19) {
			if (CO.entrada.elementAt(19) != null) {
				CO.pos20 = (TextView) findViewById(R.id.pos20);
				CO.vecCaselles.addElement(CO.pos20);
			} else {
				reiniciarCasella(CO.pos20);
				CO.vecCaselles.addElement(null);
			}
		} else
			reiniciarCasella(CO.pos20);
	}

	/*
	 * private void reestructurarCaselles(TextView pos) { if(CO.cols == 1){
	 * pos.setWidth(250); width = 250; } else if(CO.cols == 2){
	 * pos.setWidth(120); width = 120; } else if(CO.cols == 3){
	 * pos.setWidth(80); width = 80; } else { //cols == 4 pos.setWidth(60);
	 * width = 60; }
	 * 
	 * if(CO.rows == 1 || CO.rows == 2){ pos.setHeight(100); pos.setMaxLines(4);
	 * height = 100; } else if(CO.rows == 3){ pos.setHeight(85);
	 * pos.setMaxLines(3); height = 85; } else if(CO.rows == 4){
	 * pos.setHeight(70); pos.setMaxLines(2); height = 70; } else { //CO.rows ==
	 * 5 pos.setHeight(60); pos.setMaxLines(2); height = 60; } }
	 */

	// nova funcio de reestructurar, es posara en marxa a l'hora de dissenyar la
	// interficie
	private void reestructurarCaselles(TextView pos) {
		// ImageView myView = (ImageView)getWindow().findViewById(R.id.ll);
		Display display = getWindowManager().getDefaultDisplay();

		CO.cMaxHor = display.getWidth() - display.getHeight() / 5;
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

	private void comprobarInicial() {
		for (int i = 0; i < CO.entrada.size(); i++) {
			if (CO.entrada.elementAt(i) != null) {
				if (CO.entrada.elementAt(i).equalsIgnoreCase(
						CO.sortida.elementAt(i)))
					CO.correcte++;
				else
					CO.incorrecte++;
			}
		}
		setMissatges();
	}

	private void setOnClickListener() {
		/*
		 * CO.missCorrectes.setOnClickListener(new View.OnClickListener() {
		 * public void onClick(View view) { if(CO.casIni == CO.correcte ||
		 * contador==maxIntents || contadorT==maxTime){ Intent iSeg = new
		 * Intent(ExchangePuzzle.this, Puzzle.class); startActivity(iSeg);
		 * finish(); } } });
		 * 
		 * CO.miss.setOnClickListener(new View.OnClickListener() { public void
		 * onClick(View view) { if(CO.casIni == CO.correcte ||
		 * contador==maxIntents || contadorT==maxTime){ Intent iSeg = new
		 * Intent(ExchangePuzzle.this, Puzzle.class); startActivity(iSeg);
		 * finish(); } } });
		 */

		for (int i = 0; i < CO.vecCaselles.size(); i++) {
			if (CO.vecCaselles.elementAt(i) != null) {
				final TextView pos = CO.vecCaselles.elementAt(i);

				pos.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
						executarOnClick(pos);
						if (!CO.p1.equalsIgnoreCase("<buit>")
								&& !CO.p2.equalsIgnoreCase("<buit>")) {
							swapCaselles();
							posAgafada1 = null;
							posAgafada2 = null;
						}
					}
				});
			}
		}
	}

	private void executarOnClick(TextView posicio) {
		if (CO.p1.equalsIgnoreCase("<buit>")) {
			// no tenim res seleccionat encara
			CO.p1 = (String) posicio.getText();
			posAgafada1 = posicio;
			sounds.playClick();
			// CO.cas1.setText(CO.p1);

			posicio.setBackgroundColor(Color.WHITE);
			posicio.setTextColor(Color.BLACK);
			// posar que no posi blanc, sino alpha 100!!!

			if (CO.imatge != null) {
				// CO.cas1.setText("");
				int indexEntr = CO.vecCaselles.indexOf(posicio);
				int indexSort = CO.sortida.indexOf(CO.p1);

				CO.vecCaselles.elementAt(indexEntr).setBackgroundColor(
						Color.TRANSPARENT);
				CO.vecCaselles.elementAt(indexEntr).setTextColor(
						Color.TRANSPARENT);

				vecDraw.elementAt(indexSort).setAlpha(100);

				CO.vecCaselles.elementAt(indexEntr).setBackgroundDrawable(
						vecDraw.elementAt(indexSort));
			}
		} else if (CO.p2.equalsIgnoreCase("<buit>")) {
			CO.p2 = (String) posicio.getText();
			posAgafada2 = posicio;
		}
	}

	private void swapCaselles() {
		for (int i = 0; i < CO.vecCaselles.size(); i++) {
			if (CO.vecCaselles.elementAt(i) != null) {
				if (CO.vecCaselles.elementAt(i) == posAgafada1
						|| CO.vecCaselles.elementAt(i) == posAgafada2) {
					// es una de les caselles que he de canviar
					comprovar(CO.vecCaselles.elementAt(i), i);
				}
			}
		}

		setMissatges();

		CO.p1 = "<buit>";
		CO.p2 = "<buit>";
		// CO.cas1.setText("");
	}

	private void comprovar(TextView posicio, int p) {
		int indexSort = 0;

		if (CO.p1.equalsIgnoreCase((String) posicio.getText())) {
			// la posicio es posAgafada1
			if (CO.imatge != null)
				indexSort = CO.sortida.indexOf(CO.p2);
			posAgafada1.setText(CO.p2);
		} else {
			// la posicio es posAgafada2
			if (CO.imatge != null)
				indexSort = CO.sortida.indexOf(CO.p1);
			posAgafada2.setText(CO.p1);
		}

		if (CO.imatge == null) {
			posicio.setBackgroundColor(CO.bg);
			posicio.setTextColor(CO.fg);
		} else {
			int indexEntr = CO.vecCaselles.indexOf(posicio);

			CO.vecCaselles.elementAt(indexEntr).setBackgroundColor(
					Color.TRANSPARENT);
			CO.vecCaselles.elementAt(indexEntr).setTextColor(Color.TRANSPARENT);

			vecDraw.elementAt(indexSort).setAlpha(255);

			CO.vecCaselles.elementAt(indexEntr).setBackgroundDrawable(
					vecDraw.elementAt(indexSort));
		}

		// recalculo les posicions
		int correcte = CO.correcte;
		CO.correcte = 0;
		CO.incorrecte = 0;
		for (int i = 0; i < CO.vecCaselles.size(); i++) {
			if (CO.vecCaselles.elementAt(i) != null) {
				if (((String) CO.vecCaselles.elementAt(i).getText())
						.equalsIgnoreCase(CO.sortida.elementAt(i))) {
					CO.correcte++;
				} else
					CO.incorrecte++;
			}
		}
		++cont;

		if(CO.correcte>correcte && cont == 2){
			contador++;
			sounds.playAction_ok();
			cont =0;
		}
		else if(cont == 2 || CO.p1.equals(CO.p2)){
			contador++;
			sounds.playActionError();
			cont = 0;
		}
		setMissatges();
	}

	@TargetApi(3)
	private void setMissatges() {
		if (CO.solucioVisible) {
			// CO.miss.setText("");
			// CO.missCorrectes.setText("");
			// CO.cas1.setText("");
			CO.p1 = "<buit>";
			CO.p2 = "<buit>";
		} else {
			final Context aC = this;
			Dialog dialog = new Dialog(aC, R.style.Dialog);
			dialog.setContentView(R.layout.menu_clic);
			dialog.setCanceledOnTouchOutside(true);
			MenuActivitats ma = new MenuActivitats(timer);
			ma.butsMenu(dialog, aC, vecDraw);
			TextView textFinal = (TextView) dialog.findViewById(R.id.tMenuClic);

			if((maxIntents != 0 && maxIntents == contador && CO.correcte!=CO.casIni)){
				sounds.playFinished_error();
				if(Parser.getActivitats().elementAt(CO.activitatActual).getMissatgeFi() != null)
					textFinal.setText(Parser.getActivitats().elementAt(CO.activitatActual).getMissatgeFi());
				else textFinal.setText("Superat els intents màxims");
				if(maxTime!=0)timer.cancel();
				//CO.missCorrectes.setText("Prem aquí per continuar.");
				//CO.missCorrectes.setBackgroundColor(Color.WHITE);
				//CO.missCorrectes.setTextColor(Color.BLACK);
				dialog.show();
				bloquejarJoc(true);
			}
			if (contadorT == maxTime && maxTime!=0){
				sounds.playFinished_error();
				if(Parser.getActivitats().elementAt(CO.activitatActual).getMissatgeFi() != null)
					textFinal.setText(Parser.getActivitats().elementAt(CO.activitatActual).getMissatgeFi());
				else textFinal.setText("Superat el temps màxims");
				if(maxTime!=0)timer.cancel();
				//CO.missCorrectes.setText("Prem aquí per continuar.");
				//CO.missCorrectes.setBackgroundColor(Color.WHITE);
				//CO.missCorrectes.setTextColor(Color.BLACK);
				dialog.show();
				bloquejarJoc(true);
			} else if (CO.correcte == CO.casIni) {
				// Hem acabat el joc
				if (maxTime != 0)
					timer.cancel();
				sounds.playFinished_ok();
				if (Parser.getActivitats().elementAt(CO.activitatActual)
						.getMissatgeFi() != null)
					textFinal.setText(Parser.getActivitats()
							.elementAt(CO.activitatActual).getMissatgeFi());
				else
					textFinal.setText("Joc finalitzat!");

				// CO.missCorrectes.setText("Prem aquí per continuar.");
				// CO.missCorrectes.setBackgroundColor(Color.WHITE);
				// CO.missCorrectes.setTextColor(Color.BLACK);
				dialog.show();
				bloquejarJoc(true);

			} else {
				/*
				 * if(Parser.getActivitats().elementAt(CO.activitatActual).
				 * getMissatgeIni() != null)
				 * CO.miss.setText(Parser.getActivitats
				 * ().elementAt(CO.activitatActual).getMissatgeIni()); else
				 * CO.miss.setText("Comença el joc!");
				 */
				int displayedIntents;
				if (IntentCountDown && maxIntents != 0) {
					displayedIntents = maxIntents - contador;
				} else
					displayedIntents = contador;
				int displayedTime;
				if (TimeCountDown && maxTime != 0) {
					displayedTime = maxTime - contadorT;
				} else
					displayedTime = contadorT;
				intentos.setText(Integer.toString(displayedIntents)); // actualitza
																		// numero
																		// intents
				CO.missCorrectes.setText(Integer.toString(CO.correcte)); // actualitza
																			// numero
																			// caselles
																			// correctes
				// CO.missCorrectes.setText("C = " + CO.correcte + ", I = " +
				// CO.incorrecte +"  In ="+displayedIntents +
				// "T ="+displayedTime);
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
	
    protected void onDestroy(){
    	if (maxTime != 0) timer.cancel();
    	sounds.unloadAll();
    	super.onDestroy();
    }
    
    @Override
	public void onBackPressed() {
	}



	/*
	 * @TargetApi(3) public boolean onCreateOptionsMenu(Menu menu) {
	 * super.onCreateOptionsMenu(menu); CO.menu = menu; CO.menu.clear();
	 * CO.menu.add(0, MENU_ANT, 0, R.string.menu_ant); CO.menu.add(0, MENU_SEG,
	 * 0, R.string.menu_seg); CO.menu.add(0, MENU_SOLUCIO, 0,
	 * R.string.menu_solucio); CO.menu.add(0, MENU_AJUDA, 0,
	 * R.string.menu_ajuda); CO.menu.add(0, MENU_INICI, 0, R.string.menu_inici);
	 * CO.menu.add(0, MENU_SORTIR, 0, R.string.menu_sortir);
	 * 
	 * //Configuro els botons d'anterior i seguent
	 * CO.menu.getItem(MENU_SEG).setEnabled(true);
	 * CO.menu.getItem(MENU_ANT).setEnabled(true);
	 * 
	 * CO.menu.getItem(MENU_ANT).setIcon(android.R.drawable.ic_media_rew);
	 * CO.menu.getItem(MENU_SEG).setIcon(android.R.drawable.ic_media_ff);
	 * CO.menu
	 * .getItem(MENU_SOLUCIO).setIcon(android.R.drawable.btn_star_big_off);
	 * CO.menu.getItem(MENU_AJUDA).setIcon(android.R.drawable.ic_menu_help);
	 * CO.menu.getItem(MENU_INICI).setIcon(android.R.drawable.ic_menu_revert);
	 * CO.menu.getItem(MENU_SORTIR).setIcon(android.R.drawable.
	 * ic_menu_close_clear_cancel);
	 * 
	 * if(CO.activitatActual<1){ //estem a la primera activitat, pel que no
	 * podem habilitar l'anterior CO.menu.getItem(MENU_ANT).setEnabled(false); }
	 * if(CO.activitatActual == Parser.getActivitats().size() - 1){ //estem a
	 * l'ultima activitat, pel que no podem habilitar el seguent
	 * CO.menu.getItem(MENU_SEG).setEnabled(false); }
	 * 
	 * if(CO.mostrarSolucio) CO.menu.getItem(MENU_SOLUCIO).setEnabled(true);
	 * else CO.menu.getItem(MENU_SOLUCIO).setEnabled(false); return true; }
	 * 
	 * public boolean onOptionsItemSelected(MenuItem item) { switch
	 * (item.getItemId()) { case MENU_ANT: CO.activitatActual =
	 * CO.activitatActual - 2; Intent iAnt = new Intent(this, Puzzle.class);
	 * startActivity(iAnt); finish(); return true; case MENU_SEG: Intent iSeg =
	 * new Intent(this, Puzzle.class); startActivity(iSeg); finish(); return
	 * true; case MENU_AJUDA: Dialog ajuda = new
	 * AlertDialog.Builder(ExchangePuzzle.this) .setIcon(R.drawable.jclic_aqua)
	 * .setTitle("Ajuda") .setPositiveButton("D'acord", null)
	 * .setMessage("Intercanvia les caselles per ordenar el puzzle.") .create();
	 * ajuda.show(); return true; case MENU_SOLUCIO: if(!CO.solucioVisible){
	 * //Vull mostrar la solucio CO.vecActual = new Vector<CharSequence>();
	 * for(int i = 0; i < CO.vecCaselles.size(); i++){
	 * if(CO.vecCaselles.elementAt(i) != null){
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
	 * CO.vecActual.addElement(null); } bloquejarJoc(true); CO.solucioVisible =
	 * true; setMissatges();
	 * CO.menu.getItem(MENU_SOLUCIO).setTitle(R.string.menu_in_solucio);
	 * CO.menu.getItem(MENU_ANT).setEnabled(false);
	 * CO.menu.getItem(MENU_SEG).setEnabled(false); } else { //Estic mostrant la
	 * solucio i vull continuar for(int i = 0; i < CO.vecCaselles.size(); i++){
	 * if(CO.vecCaselles.elementAt(i) != null){
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
	 * //Configuracio del menu per ant i seguent if(CO.activitatActual<1){
	 * //estem a la primera activitat, pel que nomes habilitem seguent
	 * CO.menu.getItem(MENU_SEG).setEnabled(true); } else if(CO.activitatActual
	 * == Parser.getActivitats().size() - 1){ //estem a l'ultima activitat, pel
	 * que no podem habilitar el seguent
	 * CO.menu.getItem(MENU_ANT).setEnabled(true); } else {
	 * CO.menu.getItem(MENU_SEG).setEnabled(true);
	 * CO.menu.getItem(MENU_ANT).setEnabled(true); } }
	 * 
	 * return true; case MENU_SORTIR: AlertDialog.Builder builder = new
	 * AlertDialog.Builder(this); builder.setIcon(R.drawable.jclic_aqua);
	 * builder.setMessage("EstÃ s segur de que vols sortir?")
	 * .setCancelable(false) .setPositiveButton("SÃ­", new
	 * DialogInterface.OnClickListener() { public void onClick(DialogInterface
	 * dialog, int id) { ExchangePuzzle.this.finish(); } })
	 * .setNegativeButton("No", new DialogInterface.OnClickListener() { public
	 * void onClick(DialogInterface dialog, int id) { dialog.cancel(); } });
	 * AlertDialog alert = builder.create(); alert.show(); return true; case
	 * MENU_INICI: Intent i = new Intent(this, Jclic.class); startActivity(i);
	 * finish(); return true; }
	 * 
	 * 
	 * return false; }
	 */

}
