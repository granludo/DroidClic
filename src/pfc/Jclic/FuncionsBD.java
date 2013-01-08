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

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class FuncionsBD extends Activity {

	private static final String DATABASE_NAME = "BDCLic.db";
	public final String KEY_ROWID = "_id";
	public final String TITULO = "titulo";
	public final String DESCRIPCION = "descripcion";
	public final String RANGO_EDAD = "rango_edad";
	public final String AUTOR = "autor";
	public final String IDIOMA = "idioma";
	public final String CATEGORIA = "categoria";
	public final String NOMBRE = "nombre";

	private static final String DATABASE_TABLE = "Clics";

	private Context context;

	private SQLiteDatabase db;
	private DataBaseSQLite dbHelper;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
	}

	public FuncionsBD(Context ctx) {
		this.context = ctx;
	}

	public FuncionsBD open() throws SQLException {
		dbHelper = new DataBaseSQLite(context, DATABASE_NAME, null, 1);
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	public long create(String titol, String descripcio, int edat, String autor,
			int idioma, int categoria, String nombre) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(TITULO, titol);
		initialValues.put(DESCRIPCION, descripcio);
		initialValues.put(RANGO_EDAD, edat);
		initialValues.put(AUTOR, autor);
		initialValues.put(IDIOMA, idioma);
		initialValues.put(CATEGORIA, categoria);
		initialValues.put(NOMBRE, nombre);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	public boolean deleteClic(long rowId) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	public void deletesAll() {
		db.delete(DATABASE_TABLE, null, null);
	}

	// Cursor amb totes les notes de la base de dades
	public Cursor buscar_tots_clics() {
		return db.query(DATABASE_TABLE, new String[] { KEY_ROWID, TITULO,
				DESCRIPCION, RANGO_EDAD, AUTOR, IDIOMA, CATEGORIA, NOMBRE },
				null, null, null, null, null);
	}

	public Cursor buscar_per_idioma(int s) throws SQLException {
		String[] ss = new String[] { String.valueOf(s) };
		Cursor mCursor = db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				TITULO, DESCRIPCION, RANGO_EDAD, AUTOR, IDIOMA, CATEGORIA,
				NOMBRE }, "idioma = ?", ss, null, null, null);
		return mCursor;
	}

	public Cursor buscar_per_categoria(int s) {
		String[] ss = new String[] { String.valueOf(s) };
		Cursor mCursor = db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				TITULO, DESCRIPCION, RANGO_EDAD, AUTOR, IDIOMA, CATEGORIA,
				NOMBRE }, "categoria = ?", ss, null, null, null);
		return mCursor;
	}

	public Cursor buscar_per_edat(int s) {
		String[] ss = new String[] { String.valueOf(s) };
		Cursor mCursor = db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				TITULO, DESCRIPCION, RANGO_EDAD, AUTOR, IDIOMA, CATEGORIA,
				NOMBRE }, "rango_edad = ?", ss, null, null, null);
		return mCursor;
	}

	public Cursor buscar_catEdat(int[] s) {
		String[] ss = new String[] { String.valueOf(s) };
		Cursor mCursor = db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				TITULO, DESCRIPCION, RANGO_EDAD, AUTOR, IDIOMA, CATEGORIA,
				NOMBRE }, "categoria = ? and rango_edad = ?", ss, null, null,
				null);
		return mCursor;
	}

	public Cursor buscar_catIdioma(int[] s) {
		String[] ss = new String[] { String.valueOf(s) };
		Cursor mCursor = db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				TITULO, DESCRIPCION, RANGO_EDAD, AUTOR, IDIOMA, CATEGORIA,
				NOMBRE }, "categoria = ? and idioma = ?", ss, null, null, null);
		return mCursor;
	}

	public Cursor buscar_edatIdioma(int[] s) {
		String[] ss = new String[] { String.valueOf(s) };
		Cursor mCursor = db
				.query(DATABASE_TABLE, new String[] { KEY_ROWID, TITULO,
						DESCRIPCION, RANGO_EDAD, AUTOR, IDIOMA, CATEGORIA,
						NOMBRE }, "rango_edad = ? and idioma = ?", ss, null,
						null, null);
		return mCursor;
	}

	public Cursor buscar_tot(int[] s) {
		String[] ss = new String[] { String.valueOf(s) };
		Cursor mCursor = db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				TITULO, DESCRIPCION, RANGO_EDAD, AUTOR, IDIOMA, CATEGORIA,
				NOMBRE }, "categoria = ? and rango_edad = ? and idioma = ?",
				ss, null, null, null);
		return mCursor;
	}

}
