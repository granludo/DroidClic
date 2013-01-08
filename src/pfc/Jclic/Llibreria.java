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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pfc.ConnectionLayer.ClicMetaData;
import pfc.ConnectionLayer.SearchResult;
import pfc.ConnectionLayer.ServerAPI;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Llibreria extends Activity {

	static final int DIALOG_CATEGORIA_ID = 1;
	static final int DIALOG_EDAT_ID = 2;
	static final int DIALOG_IDIOMA_ID = 3;

	private ImageButton bTornar;
	private ImageButton bCategoria;
	private ImageButton bEdat;
	private ImageButton bIdioma;
	private TextView tvTornar;
	private TextView tvCategoria;
	private TextView tvEdat;
	private TextView tvIdioma;
	private TextView tvDescripcio;
	private ListView listClics;

	private Integer edat = -1;
	private Integer categoria = -1;
	private Integer idioma = -1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);

		bTornar = (ImageButton) findViewById(R.id.bLlibreria);
		bCategoria = (ImageButton) findViewById(R.id.bCategoria);
		bEdat = (ImageButton) findViewById(R.id.bEdat);
		bIdioma = (ImageButton) findViewById(R.id.bIdioma);

		bTornar.setBackgroundResource(R.drawable.ico_back);

		tvTornar = (TextView) findViewById(R.id.tvLlibreria);
		tvCategoria = (TextView) findViewById(R.id.tvCategoria);
		tvEdat = (TextView) findViewById(R.id.tvEdat);
		tvIdioma = (TextView) findViewById(R.id.tvIdioma);
		tvDescripcio = (TextView) findViewById(R.id.tvDescripcio);

		tvTornar.setText("Tornar");
		tvCategoria.setText("Categoria");
		tvEdat.setText("Edat");
		tvIdioma.setText("Idioma");
		tvDescripcio.setText("Descripció del Clic");

		listClics = (ListView) this.findViewById(R.id.listClics);

		populateListFromRepo(getAllResults());

		setOnClickListener();
	}

	private SearchResult getAllResults() {
		SearchResult results = null;
		Toast.makeText(getApplicationContext(), "Carregant Resultats...",
				Toast.LENGTH_LONG).show();
		try {
			results = ServerAPI.getAll(0);
		} catch (Exception e) {
			e.printStackTrace();
			tvDescripcio.setText("No es pot connectar amb la Llibreria");
		}
		return results;
	}

	private SearchResult getSearchResults(Map<String, String> params) {
		SearchResult results = null;
		Toast.makeText(getApplicationContext(), "Carregant Resultats...",
				Toast.LENGTH_LONG).show();
		try {
			results = ServerAPI.search(params, 0);
		} catch (Exception e) {
			tvDescripcio.setText("No es pot connectar amb la Llibreria");
			e.printStackTrace();
		}
		return results;
	}

	private void populateListFromRepo(SearchResult results) {
		List<Map<String, Object>> dadesLlista = new ArrayList<Map<String, Object>>();
		int numResults;
		if (results == null) {
			numResults = 0;
			tvDescripcio.setText("No hi ha resultats");
		} else {
			numResults = results.getResultsLength();
			tvDescripcio.setText("Selecciona un clic");
		}
		for (int i = 0; i < numResults; i++) {
			ClicMetaData result = results.getResult(i);
			Map<String, Object> dadesClic = new HashMap<String, Object>();
			dadesClic.put("clicmetadata", result);
			dadesLlista.add(dadesClic);
		}
		LlibreriaSimpleAdapter listAdapter = new LlibreriaSimpleAdapter(this,
				dadesLlista, R.layout.element_clic,
				new String[] { "clicmetadata" }, new int[] { R.id.iconClic,
						R.id.titulo, R.id.bplay }, tvDescripcio);
		listClics.setAdapter(listAdapter);
	}

	private void setOnClickListener() {

		bTornar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(), Inici.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				Toast.makeText(getApplicationContext(), "Inici...",
						Toast.LENGTH_LONG).show();
				startActivity(i);
				Llibreria.this.finish();
			}
		});

		bCategoria.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				showDialog(DIALOG_CATEGORIA_ID);
			}
		});

		bEdat.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				showDialog(DIALOG_EDAT_ID);
			}
		});

		bIdioma.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				showDialog(DIALOG_IDIOMA_ID);
			}
		});

	}

	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		switch (id) {
		case DIALOG_CATEGORIA_ID:
			dialog = crearCategoria();
			break;
		case DIALOG_EDAT_ID:
			dialog = crearEdat();
			break;
		case DIALOG_IDIOMA_ID:
			dialog = crearIdioma();
			break;
		default:
			dialog = null;
		}
		return dialog;
	}

	private void cridaFiltres() {
		Map<String, String> params = new HashMap<String, String>();
		if (categoria != -1)
			params.put("thematic", categoria.toString());
		if (edat != -1)
			params.put("age", edat.toString());
		if (idioma != -1)
			params.put("language", idioma.toString());
		populateListFromRepo(getSearchResults(params));
	}

	private Dialog crearCategoria() {
		final CharSequence[] items = { "Tots", "Llengües", "Matemàtiques",
				"Ciències socials", "Ciències experimentals", "Música",
				"Plàstica i visual", "Educació física", "Diversos" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Selecciona una categoria");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				categoria = categoryToInt(items[item].toString());
				cridaFiltres();
			}
		});
		return builder.create();
	}

	private Dialog crearEdat() {
		final CharSequence[] items = { "Tots", "Infantil (3-6)",
				"Primària (6-12)", "Secundària (12-16)", "Batxillerat (16-18)" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Selecciona un rang d'edat");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				edat = ageToInt(items[item].toString());
				cridaFiltres();
			}
		});
		return builder.create();
	}

	private Dialog crearIdioma() {
		final CharSequence[] items = { "Tots", "Català", "Español", "English",
				"German", "French", "Other" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Selecciona un idioma");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				idioma = languageToInt(items[item].toString());
				cridaFiltres();
			}
		});
		return builder.create();
	}

	private int ageToInt(String s) {
		int age;
		s = s.split(" ")[0];
		if (s.equals("Infantil") || s.equals("Kindergarten"))
			age = 0;
		else if (s.equals("Primària") || s.equals("Primaria")
				|| s.equals("Primary"))
			age = 1;
		else if (s.equals("Secundària") || s.equals("Secundaria")
				|| s.equals("Secondary"))
			age = 2;
		else if (s.equals("Batxillerat") || s.equals("Bachillerato")
				|| s.equals("High"))
			age = 3;
		else
			age = -1;
		return age;
	}

	private int languageToInt(String s) {
		int language;
		s = s.split(",")[0];
		if (s.equals("català") || s.equals("Català"))
			language = 0;
		else if (s.equals("español") || s.equals("Español"))
			language = 1;
		else if (s.equals("English"))
			language = 2;
		else if (s.equals("French"))
			language = 3;
		else if (s.equals("German"))
			language = 4;
		else if (s.equals("Other"))
			language = 5;
		else
			language = -1;
		return language;
	}

	private int categoryToInt(String s) {
		int category;
		s = s.split(",")[0];
		if (s.equals("Llengües") || s.equals("Lenguas")
				|| s.equals("Languages"))
			category = 0;
		else if (s.equals("Matemàtiques") || s.equals("Matemáticas")
				|| s.equals("Mathematics"))
			category = 1;
		else if (s.equals("Ciències socials") || s.equals("Ciencias sociales"))
			category = 2;
		else if (s.equals("Ciències experimentals")
				|| s.equals("Ciencias experimentales"))
			category = 3;
		else if (s.equals("Música") || s.equals("Music"))
			category = 4;
		else if (s.equals("Plàstica i visual") || s.equals("Plástica y visual"))
			category = 5;
		else if (s.equals("Educació física") || s.equals("Educación física"))
			category = 6;
		else if (s.equals("Tecnologia") || s.equals("Tecnología"))
			category = 7;
		else if (s.equals("Diversos") || s.equals("Varios"))
			category = 8;
		else
			category = -1;
		return category;
	}

}
