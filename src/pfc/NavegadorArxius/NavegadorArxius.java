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




package pfc.NavegadorArxius;

import java.io.File;
import java.util.ArrayList;

import pfc.Activitats.Constants;
import pfc.Activitats.Puzzle;
import pfc.Descompressor.Descompressor;
import pfc.Jclic.Jclic;
import pfc.Jclic.R;
import pfc.Parser.Parser;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

@TargetApi(3)
public class NavegadorArxius extends ListActivity {
	private ArrayList<String> elementos = null;
	private Constants CO = Constants.getInstance();
	private static final int MENU_AJUDA = 0;
	private static final int MENU_INICI = 1;
	private static final int MENU_SORTIR = 2;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navegador_llistat);
		llistarArrel();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		int IDFilaSeleccionada = position;
		if (IDFilaSeleccionada == 0) {
			llistarArrel();
		} else {
			File arxiu = new File(elementos.get(IDFilaSeleccionada));
			if (arxiu.isDirectory())
				rellenar(arxiu.listFiles());
			else {
				String path = arxiu.getAbsolutePath();
				CO.path = path;

				try {
					if (CO.path.endsWith(".zip")) {
						// descomprimir fitxer i buscar el jclic
						CO.fitxer = (String) CO.path.subSequence(0,
								CO.path.length() - 4);
						String[] split = CO.fitxer.split("/");
						CO.fitxer = split[split.length - 1];

						if (Descompressor.descompressor(CO.fitxer, CO.path)) {

							File file = new File("/sdcard/tmp/jclic/"
									+ CO.fitxer);
							file.createNewFile();

							Parser.ParserXML(file);

							if (Parser.getActivitats().size() == 0) {
								Dialog noActivitats = new AlertDialog.Builder(
										NavegadorArxius.this)
										.setIcon(R.drawable.jclic_aqua)
										.setTitle("No hi ha activitats")
										.setPositiveButton("D'acord", null)
										.setMessage(
												"No hi ha activitats o les que hi ha no sï¿½ï¿½n vï¿½ï¿½lides!")
										.create();
								noActivitats.show();
							} else {
								if (Parser.getActivitatsSaltades()) {
									creaMissatgeTemporal(
											"S'han eliminat algunes activitats\n"
													+ "per problemes de tamany",
											false);
								}

								Intent i = new Intent(NavegadorArxius.this,
										Puzzle.class);
								startActivity(i);
								finish();
							}
						} else {
							Dialog noActivitats = new AlertDialog.Builder(
									NavegadorArxius.this)
									.setIcon(R.drawable.jclic_aqua)
									.setTitle("Fitxer invï¿½ï¿½lid")
									.setPositiveButton("D'acord", null)
									.setMessage(
											"El fitxer no ï¿½ï¿½s vï¿½ï¿½lid!")
									.create();
							noActivitats.show();
						}
					} else {
						Dialog noActivitats = new AlertDialog.Builder(
								NavegadorArxius.this)
								.setIcon(R.drawable.jclic_aqua)
								.setTitle("Fitxer invï¿½ï¿½lid")
								.setPositiveButton("D'acord", null)
								.setMessage("El fitxer no ï¿½ï¿½s vï¿½ï¿½lid!")
								.create();
						noActivitats.show();
					}
				} catch (Exception e) {
					Log.d("Error", "catch NavegadorArxius: " + e);
				}
			}
		}
	}

	private void rellenar(File[] arxius) {
		elementos = new ArrayList<String>();
		if (arxius.length > 0) {
			if (!arxius[0].getParent().equalsIgnoreCase("/sdcard")) {
				elementos.add("... /sdcard");
			} else
				elementos.add("");
		}

		for (File arxiu : arxius)
			elementos.add(arxiu.getPath());

		ArrayAdapter<String> listaArchivos = new ArrayAdapter<String>(this,
				R.layout.navegador_fila, elementos);

		if (listaArchivos.isEmpty()) {
			// no hi ha coses dins
			Dialog noContingut = new AlertDialog.Builder(NavegadorArxius.this)
					.setTitle("Carpeta Buida").setPositiveButton("OK", null)
					.setMessage("Aquesta carpeta estï¿½ï¿½ buida")
					.setIcon(R.drawable.jclic_aqua).create();
			noContingut.show();

			llistarArrel();
		} else
			setListAdapter(listaArchivos);
	}

	private void llistarArrel() {
		rellenar(new File("/sdcard").listFiles());
	}

	private void creaMissatgeTemporal(String missatge, boolean curt) {
		Toast missatgeTemporal = Toast.makeText(this, missatge,
				curt ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
		missatgeTemporal.setGravity(Gravity.CENTER, 0, 0);
		missatgeTemporal.show();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		CO.menu = menu;
		CO.menu.clear();
		CO.menu.add(0, MENU_AJUDA, 0, R.string.menu_ajuda);
		CO.menu.add(0, MENU_INICI, 0, R.string.menu_inici);
		CO.menu.add(0, MENU_SORTIR, 0, R.string.menu_sortir);

		CO.menu.getItem(MENU_AJUDA).setIcon(android.R.drawable.ic_menu_help);
		CO.menu.getItem(MENU_INICI).setIcon(android.R.drawable.ic_menu_revert);
		CO.menu.getItem(MENU_SORTIR).setIcon(
				android.R.drawable.ic_menu_close_clear_cancel);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_AJUDA:
			Dialog ajuda = new AlertDialog.Builder(NavegadorArxius.this)
					.setIcon(R.drawable.jclic_aqua).setTitle("Ajuda")
					.setPositiveButton("D'acord", null)
					.setMessage("Selecciona el fitxer que vols obrir.")
					.create();
			ajuda.show();
			return true;
		case MENU_SORTIR:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.jclic_aqua);
			builder.setMessage("Estï¿½ï¿½s segur de que vols sortir?")
					.setCancelable(false)
					.setPositiveButton("Sï¿½ï¿½",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									NavegadorArxius.this.finish();
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