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



package pfc.Jclic;

import pfc.Activitats.Constants;
import pfc.Activitats.Puzzle;
import pfc.NavegadorArxius.NavegadorArxius;
import pfc.Parser.Parser;
import pfc.Repositori.DadesServidor;
import pfc.Repositori.LlistatProjectes;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Jclic extends Activity {

	private Constants CO = Constants.getInstance();
	private TextView inici_url;
	private TextView inici_fitxer;
	private TextView inici_exemple;
	private TextView tsortir;
	private TextView tabout;
	private ImageView burl;
	private ImageView bexemple;
	private ImageView bfitxer;
	private ImageView bsortir;
	private ImageView babout;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// LogSystem.Init();
		setContentView(R.layout.inici);

		Parser.setActivitatsSaltades(false);
		CO.activitatActual = -1;
		CO.exemple = false;
		DadesServidor.keyword = "";
		DadesServidor.all = true;

		inici_url = (TextView) findViewById(R.id.inici_URL);
		inici_fitxer = (TextView) findViewById(R.id.inici_fitxer);
		inici_exemple = (TextView) findViewById(R.id.inici_exemple);
		tsortir = (TextView) findViewById(R.id.tsortir);
		tabout = (TextView) findViewById(R.id.tabout);

		burl = (ImageView) findViewById(R.id.icono_url);
		bfitxer = (ImageView) findViewById(R.id.icono_fitxer);
		bexemple = (ImageView) findViewById(R.id.icono_exemple);
		bsortir = (ImageView) findViewById(R.id.bsortir);
		babout = (ImageView) findViewById(R.id.babout);

		setOnClickListener();
	}

	private void setOnClickListener() {
		inici_url.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				click_URL();
			}
		});

		burl.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				inici_url.setTextColor(Color.DKGRAY);
				click_URL();
			}
		});

		inici_fitxer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent i = new Intent(Jclic.this, NavegadorArxius.class);
				startActivity(i);
				finish();
			}
		});

		bfitxer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				inici_fitxer.setTextColor(Color.DKGRAY);
				Intent i = new Intent(Jclic.this, NavegadorArxius.class);
				startActivity(i);
				finish();
			}
		});

		inici_exemple.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				click_exemple();
			}
		});

		bexemple.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				inici_exemple.setTextColor(Color.DKGRAY);
				click_exemple();
			}
		});

		babout.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Dialog about = new AlertDialog.Builder(Jclic.this)
						.setIcon(R.drawable.jclic_aqua)
						.setTitle("Sobre el projecte")
						.setPositiveButton("D'acord", null)
						.setMessage(
								"Projecte JClic per a Android\n\nMiriam Pujol Benet\n\nCurs 2008-2009-Q2")
						.create();
				about.show();
			}
		});

		tabout.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Dialog about = new AlertDialog.Builder(Jclic.this)
						.setIcon(R.drawable.jclic_aqua)
						.setTitle("Sobre el projecte")
						.setPositiveButton("D'acord", null)
						.setMessage(
								"Projecte JClic per a Android\n\nMiriam Pujol Benet\n\nCurs 2008-2009-Q2")
						.create();
				about.show();
			}
		});

		bsortir.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				click_sortir();
			}
		});

		tsortir.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				click_sortir();
			}
		});
	}

	private void click_URL() {
		try {
			Intent i = new Intent(Jclic.this, LlistatProjectes.class);
			startActivity(i);
			finish();
		} catch (Exception e) {
			Log.d("Error", "catch setOnClickListener1 JClic: " + e);
		}
	}

	private void click_exemple() {
		try {
			CO.exemple = true;
			CO.is = Jclic.this.getAssets().open("balenes.jclic");
			Parser.ParserXML(CO.is);

			Intent i = new Intent(Jclic.this, Puzzle.class);
			startActivity(i);
			finish();
		} catch (Exception e) {
			Log.d("Error", "catch setOnClickListener3 JClic: " + e);
		}
	}

	private void click_sortir() {
		AlertDialog.Builder builder = new AlertDialog.Builder(Jclic.this);
		builder.setIcon(R.drawable.jclic_aqua);
		builder.setMessage("Estï¿½ï¿½s segur de que vols sortir?")
				.setCancelable(false)
				.setPositiveButton("Sï¿½ï¿½",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Jclic.this.finish();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
}