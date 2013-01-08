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

import java.util.Random;
import java.util.Vector;

import pfc.Jclic.Jclic;
import pfc.Jclic.R;
import pfc.Parser.Parser;
import pfc.Parser.XMLConstants;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Puzzle extends Activity {

	private Constants CO = Constants.getInstance();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		CO.mostrarSolucio = false;
		CO.buidaVisible = false;

		// Mirem la seguent activitat de quin tipus es per saber on anar

		if (Parser.getActivitats().size() == CO.activitatActual + 1) {
			String missatge = "Ja no queden mï¿½ï¿½ï¿½ï¿½ï¿½ï¿½s activitats.";
			Toast missatgeTemporal = Toast.makeText(this, missatge,
					Toast.LENGTH_LONG);
			missatgeTemporal.setGravity(Gravity.CENTER, 0, 0);
			missatgeTemporal.show();

			Intent i = new Intent(this, Jclic.class);
			finish();
			startActivity(i);
		} else {
			if (Parser.getActivitats().elementAt(CO.activitatActual + 1)
					.getClas().equals(XMLConstants.EXCHANGEPUZZ)) {

				// Tenim un ExchangePuzzle
				agafarDadesParser();
				posarNegrePantalla();

				Intent i = new Intent(this, ExchangePuzzle.class);
				startActivity(i);
				finish();
			} else if (Parser.getActivitats().elementAt(CO.activitatActual + 1)
					.getClas().equals(XMLConstants.HOLEPUZZ)) {
				// Tenim un HolePuzzle
				agafarDadesParser();
				posarNegrePantalla();

				Intent i = new Intent(this, HolePuzzle.class);
				startActivity(i);
				finish();
			} else if (Parser.getActivitats().elementAt(CO.activitatActual + 1)
					.getClas().equals(XMLConstants.DOUBLEPUZZ)) {
				// Tenim un DoublePuzzle
				agafarDadesParser();
				posarNegrePantalla();

				Intent i = new Intent(this, DoublePuzzle.class);
				finish();
				startActivity(i);
			} else if (Parser.getActivitats().elementAt(CO.activitatActual + 1)
					.getClas().equals(XMLConstants.SIMPLEASSOC)) { // Simple
																	// assoc
				agafarDadesParser();
				posarNegrePantalla();
				Intent i = new Intent(this, SimpleAssociation.class);
				startActivity(i);
				finish();
			} else if (Parser.getActivitats().elementAt(CO.activitatActual + 1)
					.getClas().equals(XMLConstants.COMPLEXASSOC)) { // Complex
																	// assoc
				agafarDadesParser();
				posarNegrePantalla();
				Intent i = new Intent(this, ComplexAssociation.class);
				startActivity(i);
				finish();
			} else if (Parser.getActivitats().elementAt(CO.activitatActual + 1)
					.getClas().equals(XMLConstants.MEMORYGAME)) { // Memory
				agafarDadesParser();
				posarNegrePantalla();
				Intent i = new Intent(this, Memory.class);
				startActivity(i);
				finish();
			}

			else if (Parser.getActivitats().elementAt(CO.activitatActual + 1)
					.getClas().equals(XMLConstants.PANIDENTIFY)) {
				agafarDadesParser();
				posarNegrePantalla();
				Intent i = new Intent(this, PanelsIdentify.class);
				startActivity(i);
				finish();
			} else if (Parser.getActivitats().elementAt(CO.activitatActual + 1)
					.getClas().equals(XMLConstants.IDENTIFY)) {
				CO.activitatActual++;
				Intent i = new Intent(this, Identify.class);
				startActivity(i);
				finish();
			} else if (Parser.getActivitats().elementAt(CO.activitatActual + 1)
					.getClas().equals(XMLConstants.TGRIDCROSSWORD)) {
				CO.activitatActual++;
				Intent i = new Intent(this, CrossWord.class);
				startActivity(i);
				finish();
			}

			else if (Parser.getActivitats().elementAt(CO.activitatActual + 1)
					.getClas().equals(XMLConstants.TXTWRITEANSWER)) { // Written
																		// Answer
				agafarDadesParser();
				posarNegrePantalla();
				Intent i = new Intent(this, WrittenAnswer.class);
				startActivity(i);
				finish();
			} else if (Parser.getActivitats().elementAt(CO.activitatActual + 1)
					.getClas().equals(XMLConstants.TXTORDERELEM)) {
				Log.d("ActivityName", "TextOrder");
				CO.activitatActual++;
				Intent i = new Intent(this, TextOrder.class);
				startActivity(i);
				finish();
			} else if (Parser.getActivitats().elementAt(CO.activitatActual + 1)
					.getClas().equals(XMLConstants.FILLINBLANKS)) {
				Intent i = new Intent(this, FillinBlanks.class);
				startActivity(i);
				finish();
			}
		}
	}

	private void agafarDadesParser() {
		if (CO.activitatActual < Parser.getActivitats().size() - 1) {
			// podem agafar l'activitat
			CO.activitatActual++;
			CO.solucioVisible = false;

			CO.rows = Parser.getActivitats().elementAt(CO.activitatActual)
					.getCellRows();
			CO.cols = Parser.getActivitats().elementAt(CO.activitatActual)
					.getCellCols();
			CO.colorBG = Parser.getActivitats().elementAt(CO.activitatActual)
					.getColorBG();
			CO.colorFG = Parser.getActivitats().elementAt(CO.activitatActual)
					.getColorFG();
			CO.mostrarSolucio = Parser.getActivitats()
					.elementAt(CO.activitatActual).getMostrarSolucio();
			CO.imatge = Parser.getActivitats().elementAt(CO.activitatActual)
					.getImage();

			CO.imatges = Parser.getActivitats().elementAt(CO.activitatActual)
					.getImages();
			CO.celes = Parser.getActivitats().elementAt(CO.activitatActual)
					.getCeles();

			CO.cols2 = Parser.getActivitats().elementAt(CO.activitatActual)
					.getCellCols2();
			CO.rows2 = Parser.getActivitats().elementAt(CO.activitatActual)
					.getCellRows2();
			CO.ids = Parser.getActivitats().elementAt(CO.activitatActual)
					.getRelacions();
			CO.inverse = Parser.getActivitats().elementAt(CO.activitatActual)
					.isInverse();

			if (CO.imatge != null) {
				// hi ha una imatge, pel que numero les caselles de 0 a N
				CO.sortida = new Vector<String>();

				for (int i = 0; i < CO.rows * CO.cols; i++)
					CO.sortida.add(String.valueOf(i));

			} else
				CO.sortida = new Vector<String>(Parser.getActivitats()
						.elementAt(CO.activitatActual).getCeles());

			CO.casIni = CO.sortida.size();
			if (CO.casIni > 20)
				CO.casIni = 20;
			CO.correcte = 0;
			CO.incorrecte = 0;
			CO.entrada = new Vector<String>();
			CO.vecBool = new Vector<Boolean>();
			CO.vecCaselles = new Vector<TextView>();
			CO.vecCasellesSort = new Vector<TextView>();

			crearRandom();
			redefinirEntSort();
		} else {
			Dialog finalitzat = new AlertDialog.Builder(Puzzle.this)
					.setIcon(R.drawable.jclic_aqua)
					.setTitle("Atenciï¿½ï¿½ï¿½ï¿½ï¿½ï¿½!")
					.setPositiveButton("D'acord", null)
					.setMessage("Ja no queden mï¿½ï¿½ï¿½ï¿½ï¿½ï¿½s activitats.")
					.create();
			finalitzat.show();
		}
	}

	private void crearRandom() {
		// Inici inicialitzacio vectors

		for (int i = 0; i < CO.sortida.size(); i++) {
			CO.vecBool.add(i, false);
			CO.entrada.add(i, "");
		}
		// Fi inicialitzacio vectors

		Random r = new Random();
		int rand = 0;

		if (Parser.getActivitats().elementAt(CO.activitatActual).getClas()
				.equals(XMLConstants.HOLEPUZZ)
				&& (CO.rows == 1 || CO.cols == 1)) {
			// es un Hole Puzzle i nomes te una fila o columna

			for (int i = 0; i < CO.entrada.size(); i++) {
				CO.entrada.set(i, CO.sortida.elementAt(i));
			}

		} else {
			// es qualsevol puzzle o be Hole Puzzle amb menys d'una columna o
			// fila
			for (int i = 0; i < CO.entrada.size(); i++) {
				/*
				 * entrada[i] = CO.sortida[rand] vecBool ens indica si els
				 * valors de la CO.sortida estan fets servir, de manera que si
				 * vecBool[rand]=true, he de buscar la primera posicio que
				 * valgui false, fent que si arribo al final del vector, torni a
				 * comensar el vector per no donar error. Quan trobi un on
				 * vecBool[X] = false, posar a true i tornar a fer.
				 */
				rand = r.nextInt(CO.sortida.size());

				boolean colocat = false;
				for (int j = 0; !colocat && j < CO.vecBool.size(); j++) {
					if (!CO.vecBool.elementAt(j + rand)) {
						// ess vecBool[j+rand] = false (no s'ha fet servir)
						CO.vecBool.set(j + rand, true);
						CO.entrada.set(i, CO.sortida.elementAt(j + rand));
						colocat = true;
					} else if ((j + rand) == CO.vecBool.size() - 1)
						rand = -(j + 1);
					// -(j+1) per passar a la posicio 0 a la seguent iteracio
				}
			}
		}
	}

	private void redefinirEntSort() {
		int caselles = CO.entrada.size();
		int filaActual = 0;

		// poso buides les caselles que no han de tenir res a CO.entrada i
		// CO.sortida
		if (CO.cols == 1) {
			// columnes 2, 3 i 4 buides
			for (int i = CO.cols; i < caselles || filaActual < CO.rows; i = i + 4) {
				filaActual = i / 4;
				if (filaActual * 4 < i)
					filaActual++;

				CO.entrada.add(i, null);
				CO.entrada.add(i + 1, null);
				CO.entrada.add(i + 2, null);
				CO.sortida.add(i, null);
				CO.sortida.add(i + 1, null);
				CO.sortida.add(i + 2, null);
			}
		} else if (CO.cols == 2) {
			// columnes 3 i 4 buides
			for (int i = CO.cols; i < caselles || filaActual < CO.rows; i = i + 4) {
				filaActual = i / 4;
				if (filaActual * 4 < i)
					filaActual++;

				if (CO.entrada.size() > i && CO.entrada.elementAt(i) != null) {
					CO.entrada.add(i, null);
					CO.entrada.add(i + 1, null);
					CO.sortida.add(i, null);
					CO.sortida.add(i + 1, null);
				}
			}
		} else if (CO.cols == 3) {
			// columna 4 buida
			for (int i = CO.cols; i < caselles || filaActual < CO.rows; i = i + 4) {
				filaActual = i / 4;
				if (filaActual * 4 < i)
					filaActual++;

				CO.entrada.add(i, null);
				CO.sortida.add(i, null);
			}
		}
	}

	static int agafarColor(String color) {
		int c = 0;
		if (color.startsWith("0x")) {
			c = Integer.parseInt(color.substring(2), 16);
			c |= 0xff000000;
		} else
			Log.d("Error", "Color no comenï¿½ï¿½ï¿½ï¿½ï¿½ï¿½a per 0x!");
		return c;
	}

	private void posarNegrePantalla() {
		if (CO.vecCaselles != null) {
			for (int j = 0; j < CO.vecCaselles.size(); j++) {
				if (CO.vecCaselles.elementAt(j) != null)
					CO.vecCaselles.elementAt(j).setVisibility(View.INVISIBLE);
			}
		}
		if (CO.vecCasellesSort != null) {
			for (int j = 0; j < CO.vecCasellesSort.size(); j++) {
				if (CO.vecCasellesSort.elementAt(j) != null)
					CO.vecCasellesSort.elementAt(j).setVisibility(
							View.INVISIBLE);
			}
		}
		if (CO.miss != null)
			CO.miss.setVisibility(View.INVISIBLE);
		if (CO.missCorrectes != null)
			CO.missCorrectes.setVisibility(View.INVISIBLE);
		if (CO.cas1 != null)
			CO.cas1.setVisibility(View.INVISIBLE);
		if (CO.name != null)
			CO.name.setVisibility(View.INVISIBLE);
	}
}
