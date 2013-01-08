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
import java.util.Vector;

import pfc.Descompressor.Descompressor;
import pfc.Jclic.Jclic;
import pfc.Jclic.R;
import pfc.Parser.Parser;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WrittenAnswer extends Activity {
	private static final int MENU_ANT = 0;
	private static final int MENU_SEG = 1;
	private static final int MENU_SOLUCIO = 2;
	private static final int MENU_AJUDA = 3;
	private static final int MENU_INICI = 4;
	private static final int MENU_SORTIR = 5;
	private int newWidth;
	private int newHeight;
	private int width;
	private int height;
	private Vector<BitmapDrawable> vecDraw;
	private ArrayList<String> imatges;
	private Vector<String> celes;
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
	private TextView posAgafada = null;
	private String rEntrada;
	private CountDownTimer timer;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exchange_hole_puzzle);

		try {
			reiniciarMenu();
			imatges = Parser.getActivitats().get(CO.activitatActual)
					.getImages();
			celes = Parser.getActivitats().get(CO.activitatActual).getCeles();
			agafarDades();
			colocaImatges();

			// aquí s'inicialitza el so
			sound = new Sounds(getApplicationContext());

			sound.playStart();
			setOnClickListener();

			// inicialitzem el contador de temps maxim
			if (maxTime != 0) {
				timer = new CountDownTimer(maxTime * 1000, 1000) {
					@Override
					public void onFinish() {
						contadorTemps++;
						setMissatges();
					}

					@Override
					public void onTick(long arg0) {
						contadorTemps++;
						setMissatges();
					}
				}.start();
			}
		} catch (Exception e) {
			Log.d("Error", "catch Memory: " + e);
		}
	}

	protected void onDestroy() {
		// TODO Auto-generated method stub

		super.onDestroy();
		sound.unloadAll();
	}

	// Aquesta funció col·loca les imatges a cada casella
	private void colocaImatges() {
		for (int i = 0; i < CO.vecCaselles.size(); ++i) {
			if (!imatges.get(i).equals("")) {
				String path = "";
				Descompressor.descompressor(imatges.get(i), CO.path);
				path = "/sdcard/tmp/jclic/" + imatges.get(i);
				Bitmap bMap = BitmapFactory.decodeFile(path);
				bMap = getResizedBitmap(bMap, height, width);
				BitmapDrawable bMap2 = new BitmapDrawable(bMap);
				CO.vecCaselles.elementAt(i).setBackgroundDrawable(bMap2);
			}
			if (!celes.get(i).equals("")) {
				CO.vecCaselles.elementAt(i).setText(celes.get(i));
			}
		}
	}

	// funcio per redimensionar les imatges
	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// CREATE A MATRIX FOR THE MANIPULATION
		Matrix matrix = new Matrix();
		// RESIZE THE BIT MAP
		matrix.postScale(scaleWidth, scaleHeight);
		// RECREATE THE NEW BITMAP
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
				matrix, false);
		return resizedBitmap;
	}

	@TargetApi(3)
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

	private void agafarDades() {
		CO.tl = (TableLayout) findViewById(R.id.tl);

		agafarCaselles();

		// inicialitzem els text views
		CO.miss = (TextView) findViewById(R.id.missatge);
		CO.missCorrectes = (TextView) findViewById(R.id.correcte);
		CO.cas1 = (TextView) findViewById(R.id.cas1);
		CO.name = (TextView) findViewById(R.id.titulo);

		CO.miss.setTextColor(Color.WHITE);
		CO.missCorrectes.setTextColor(Color.WHITE);
		CO.name.setTextColor(Color.WHITE);
		CO.cas1.setTextColor(Color.WHITE);

		// agafem el nom de l'activitat
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

		// inicialitzem colors
		if (CO.colorBG != null) {
			CO.bg = Puzzle.agafarColor(CO.colorBG);
		} else
			CO.bg = Color.GRAY;

		if (CO.colorFG != null) {
			CO.fg = Puzzle.agafarColor(CO.colorFG);
		} else
			CO.fg = Color.BLACK;

		// inicialitzar caselles
		for (int i = 0; i < CO.vecCaselles.size(); i++) {
			if (CO.vecCaselles.elementAt(i) != null) {
				CO.vecCaselles.elementAt(i).setBackgroundColor(CO.bg);
				CO.vecCaselles.elementAt(i).setTextColor(CO.fg);
				CO.vecCaselles.elementAt(i).setPadding(10, 15, 10, 10);
				CO.vecCaselles.elementAt(i).setMaxLines(3);
				// CO.vecCaselles.elementAt(i).setText(CO.entrada.elementAt(i));
				/*
				 * if(CO.imatge != null){
				 * CO.vecCaselles.elementAt(i).setBackgroundColor
				 * (Color.TRANSPARENT);
				 * CO.vecCaselles.elementAt(i).setTextColor(Color.TRANSPARENT);
				 * }
				 */
				reestructurarCaselles(CO.vecCaselles.elementAt(i));
			}
		}
		bloquejarJoc(false);
	}

	// funcio en proces
	/*
	 * private void agafarCaselles() {
	 * 
	 * boolean anterior = true; int caselles = parelles.size(); for (int i = 0;
	 * i < caselles; ++i) { if(findViewById(Recursos.get(i)) != null &&
	 * anterior) { if(CO.entrada.elementAt(i) != null){ String nPos =
	 * "pos"+String.valueOf(i); CO.pos.set(i, (TextView)
	 * findViewById(Recursos.get(i))); CO.vecCaselles.addElement(CO.pos.get(i));
	 * } else { reiniciarCasella(CO.pos2); CO.vecCaselles.addElement(null); } }
	 * 
	 * else { reiniciarCasella(CO.pos2); anterior = false; } } }
	 */

	private void agafarCaselles() {
		/*
		 * Aquesta funció s'ha de canviar. Hem d'agafar el que toca del parser
		 * :)
		 */
		boolean anterior = false;
		int caselles = CO.rows * CO.cols;

		// de moment ho deixo comentat fins que no sapiguem el que
		// if (CO.cols > CO.maxCols or CO.rows > CO.maxRows)
		// activitatNoPermesa();

		if (findViewById(R.id.pos1) != null && caselles > 0) {
			if (imatges.get(0) != null) {
				CO.pos1 = (TextView) findViewById(R.id.pos1);
				CO.vecCaselles.addElement(CO.pos1);
				anterior = true;
			} else {
				reiniciarCasella(CO.pos1);
				CO.vecCaselles.addElement(null);
			}
		}

		if (findViewById(R.id.pos2) != null && anterior && caselles > 1) {
			if (imatges.get(1) != null) {
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
			if (imatges.get(2) != null) {
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
			if (imatges.get(3) != null) {
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
			if (imatges.get(4) != null) {
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
			if (imatges.get(5) != null) {
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
			if (imatges.get(6) != null) {
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
			if (imatges.get(7) != null) {
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
			if (imatges.get(8) != null) {
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
			if (imatges.get(9) != null) {
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
			if (imatges.get(10) != null) {
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
			if (imatges.get(11) != null) {
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
			if (imatges.get(12) != null) {
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
			if (imatges.get(13) != null) {
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
			if (imatges.get(14) != null) {
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
			if (imatges.get(15) != null) {
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
			if (imatges.get(16) != null) {
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
			if (imatges.get(17) != null) {
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
			if (imatges.get(18) != null) {
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
			if (imatges.get(19) != null) {
				CO.pos20 = (TextView) findViewById(R.id.pos20);
				CO.vecCaselles.addElement(CO.pos20);
			} else {
				reiniciarCasella(CO.pos20);
				CO.vecCaselles.addElement(null);
			}
		} else
			reiniciarCasella(CO.pos20);
	}

	private void reestructurarCaselles(TextView pos) {
		if (CO.cols == 1) {
			pos.setWidth(250);
			width = 250;
		} else if (CO.cols == 2) {
			pos.setWidth(120);
			width = 120;
		} else if (CO.cols == 3) {
			pos.setWidth(80);
			width = 80;
		} else {
			// cols == 4
			pos.setWidth(60);
			width = 60;
		}

		if (CO.rows == 1 || CO.rows == 2) {
			pos.setHeight(100);
			pos.setMaxLines(4);
			height = 100;
		} else if (CO.rows == 3) {
			pos.setHeight(85);
			pos.setMaxLines(3);
			height = 85;
		} else if (CO.rows == 4) {
			pos.setHeight(70);
			pos.setMaxLines(2);
			height = 70;
		} else {
			// CO.rows == 5
			pos.setHeight(60);
			pos.setMaxLines(2);
			height = 60;
		}
	}

	// nova funcio de reestructurar, es posara en marxa a l'hora de dissenyar la
	// interficie
	/*
	 * private void reestructurarCaselles(TextView pos) {
	 * 
	 * CO.cMaxHor = 250; CO.cMaxVert = 250;
	 * 
	 * if(CO.cols == 1){ pos.setWidth(CO.cMaxHor); // cMaxHor es la distancia
	 * horitzontal maxima que tenim. width = CO.cMaxHor; } else if(CO.cols ==
	 * 2){ pos.setWidth(CO.cMaxHor/2 - CO.cMaxHor/20); width = CO.cMaxHor/2 -
	 * CO.cMaxHor/20; } else if(CO.cols == 3){ pos.setWidth(CO.cMaxHor/3 -
	 * CO.cMaxHor/20); width = CO.cMaxHor/3 - CO.cMaxHor/20; } else { //cols ==
	 * 4 pos.setWidth(CO.cMaxHor/4 - CO.cMaxHor/20); width = CO.cMaxHor/4 -
	 * CO.cMaxHor/20; }
	 * 
	 * // Aqui a les columnes fa ago raro amb setMaxLines i amb la OR del
	 * principi // i m'agradaria que ho miressim i tal, xq no ho acabo
	 * d'entendre.
	 * 
	 * if(CO.rows == 1 || CO.rows == 2){ pos.setHeight(CO.cMaxVert/2 -
	 * CO.cMaxVert/10); pos.setMaxLines(4); height = CO.cMaxVert/2 -
	 * CO.cMaxVert/10; } else if(CO.rows == 3){ pos.setHeight(CO.cMaxVert/3 -
	 * CO.cMaxVert/10); pos.setMaxLines(3); height = CO.cMaxVert/3 -
	 * CO.cMaxVert/10; } else if(CO.rows == 4){ pos.setHeight(CO.cMaxVert/4 -
	 * CO.cMaxVert/10); pos.setMaxLines(2); height = CO.cMaxVert/4 -
	 * CO.cMaxVert/10; } else { //CO.rows == 5 pos.setHeight(CO.cMaxVert/5 -
	 * CO.cMaxVert/10); pos.setMaxLines(2); height = CO.cMaxVert/5 -
	 * CO.cMaxVert/10; } }
	 */

	private void setOnClickListener() {
		CO.missCorrectes.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (CO.casIni == CO.correcte || contador == maxIntents
						|| contadorTemps == maxTime) {
					Intent iSeg = new Intent(WrittenAnswer.this, Puzzle.class);
					startActivity(iSeg);
					finish();
				}
			}
		});

		CO.miss.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (CO.casIni == CO.correcte || contador == maxIntents
						|| contadorTemps == maxTime) {
					Intent iSeg = new Intent(WrittenAnswer.this, Puzzle.class);
					startActivity(iSeg);
					finish();
				}
			}
		});

		for (int i = 0; i < CO.vecCaselles.size(); i++) {
			if (CO.vecCaselles.elementAt(i) != null) {
				final TextView pos = CO.vecCaselles.elementAt(i);

				pos.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
						executarOnClick(pos);
					}
				});
			}
		}
	}

	// funcio que executa el clic sobre una casella
	private void executarOnClick(TextView posicio) {
		posAgafada = posicio;
		LayoutInflater factory = LayoutInflater.from(this);
		final View textEntryView = factory.inflate(R.layout.w_answer, null);
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Resposta:");
		alert.setView(textEntryView);
		// S'han d'afegir els edittext a les inicialitzacions
		final EditText input1 = (EditText) textEntryView
				.findViewById(R.id.m_name);
		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				sound.playClick();
				rEntrada = input1.getText().toString().toUpperCase();
				int indexEntr = CO.vecCaselles.indexOf(posAgafada);
				String rCorrecta = celes.elementAt(
						indexEntr + CO.rows + CO.cols - 1).toUpperCase();
				if (!rEntrada.equals("")) {
					++contador;
					if (rCorrecta.equals(rEntrada)) {
						sound.playAction_ok();
						++CO.correcte;
						// El que fem és amagar la casella i deixar-la fixe
						posAgafada.setBackgroundColor(Color.WHITE);
						posAgafada.setTextColor(Color.BLACK);
						posAgafada.setEnabled(false);
						int pos = indexEntr + 2 * (CO.rows + CO.cols - 1);
						if (!imatges.get(pos).equals("")) {
							String path = "";
							Descompressor.descompressor(imatges.get(pos),
									CO.path);
							path = "/sdcard/tmp/jclic/" + imatges.get(pos);
							Bitmap bMap = BitmapFactory.decodeFile(path);
							bMap = getResizedBitmap(bMap, height, width);
							BitmapDrawable bMap2 = new BitmapDrawable(bMap);
							CO.vecCaselles.elementAt(indexEntr)
									.setBackgroundDrawable(bMap2);
						}
						if (!celes.get(pos).equals("")) {
							CO.vecCaselles.elementAt(indexEntr).setText(
									celes.get(pos));
						} else
							posAgafada.setTextColor(Color.TRANSPARENT);
					} else {
						sound.playActionError();
						dialog.cancel();
					}
					setMissatges();
				} else {
					CharSequence unfilled = "Has d'indicar una resposta!";
					int duration = Toast.LENGTH_SHORT;
					Toast toastunfilled = Toast.makeText(
							getApplicationContext(), unfilled, duration);
					toastunfilled.show();
				}
			}
		});

		alert.setNegativeButton("Cancel·la",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.cancel();
					}
				});
		alert.show();
	}

	@TargetApi(3)
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

				bloquejarJoc(true);
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

				bloquejarJoc(true);
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

	// de moment està bé com està ja que fins que no decidim res amb usabilitat
	// no podem decidir colors
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

	@TargetApi(3)
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
			Dialog ajuda = new AlertDialog.Builder(WrittenAnswer.this)
					.setIcon(R.drawable.jclic_aqua).setTitle("Ajuda")
					.setPositiveButton("D'acord", null)
					.setMessage("Aparella les imatges iguals").create();
			ajuda.show();
			return true;
		case MENU_SOLUCIO:
			if (!CO.solucioVisible) {
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
				setMissatges();
				CO.menu.getItem(MENU_SOLUCIO)
						.setTitle(R.string.menu_in_solucio);
				CO.menu.getItem(MENU_ANT).setEnabled(false);
				CO.menu.getItem(MENU_SEG).setEnabled(false);
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
				setMissatges();
				CO.menu.getItem(MENU_SOLUCIO).setTitle(R.string.menu_solucio);

				// Configuracio del menu per ant i seguent
				if (CO.activitatActual < 1) {
					// estem a la primera activitat, pel que nomes habilitem
					// seguent
					CO.menu.getItem(MENU_SEG).setEnabled(true);
				} else if (CO.activitatActual == Parser.getActivitats().size() - 1) {
					// estem a l'ultima activitat, pel que no podem habilitar el
					// seguent
					CO.menu.getItem(MENU_ANT).setEnabled(true);
				} else {
					CO.menu.getItem(MENU_SEG).setEnabled(true);
					CO.menu.getItem(MENU_ANT).setEnabled(true);
				}
			}

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
									WrittenAnswer.this.finish();
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
