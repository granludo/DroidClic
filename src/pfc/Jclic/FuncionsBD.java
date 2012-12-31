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
	public final String KEY_ROWID =  "_id";
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
    
    public long create(String titol, String descripcio, int edat, String autor, int idioma, int categoria, String nombre) {
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
    
    //Cursor amb totes les notes de la base de dades
    public Cursor buscar_tots_clics() {
    	return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, TITULO, DESCRIPCION, 
    			RANGO_EDAD, AUTOR, IDIOMA, CATEGORIA, NOMBRE}, 
    			null, null, null, null, null);
    }
 
    public Cursor buscar_per_idioma(int s) throws SQLException {
    	String[] ss = new String[] { String.valueOf(s)};
    	Cursor mCursor = db.query(DATABASE_TABLE, new String[] {KEY_ROWID, TITULO, DESCRIPCION, 
    			RANGO_EDAD, AUTOR, IDIOMA, CATEGORIA, NOMBRE},  "idioma = ?", ss, null, null, null);
        return mCursor;
    }
    
	public Cursor buscar_per_categoria(int s) {
    	String[] ss = new String[] { String.valueOf(s)};
    	Cursor mCursor = db.query(DATABASE_TABLE, new String[] {KEY_ROWID, TITULO, DESCRIPCION, 
    			RANGO_EDAD, AUTOR, IDIOMA, CATEGORIA, NOMBRE},  "categoria = ?", ss, null, null, null);
        return mCursor;
	}
    
	public Cursor buscar_per_edat(int s) {
		String[] ss = new String[] { String.valueOf(s)};
    	Cursor mCursor = db.query(DATABASE_TABLE, new String[] {KEY_ROWID, TITULO, DESCRIPCION, 
    			RANGO_EDAD, AUTOR, IDIOMA, CATEGORIA, NOMBRE},  "rango_edad = ?", ss, null, null, null);
        return mCursor;
	}
    
	public Cursor buscar_catEdat(int[] s) {
		String[] ss = new String[] { String.valueOf(s)};
    	Cursor mCursor = db.query(DATABASE_TABLE, new String[] {KEY_ROWID, TITULO, DESCRIPCION, 
    			RANGO_EDAD, AUTOR, IDIOMA, CATEGORIA, NOMBRE},  "categoria = ? and rango_edad = ?", ss, null, null, null);
        return mCursor;
	}
	
	public Cursor buscar_catIdioma(int[] s) {
		String[] ss = new String[] { String.valueOf(s)};
    	Cursor mCursor = db.query(DATABASE_TABLE, new String[] {KEY_ROWID, TITULO, DESCRIPCION, 
    			RANGO_EDAD, AUTOR, IDIOMA, CATEGORIA, NOMBRE},  "categoria = ? and idioma = ?", ss, null, null, null);
        return mCursor;
	}
	
	public Cursor buscar_edatIdioma(int[] s) {
		String[] ss = new String[] { String.valueOf(s)};
    	Cursor mCursor = db.query(DATABASE_TABLE, new String[] {KEY_ROWID, TITULO, DESCRIPCION, 
    			RANGO_EDAD, AUTOR, IDIOMA, CATEGORIA, NOMBRE},  "rango_edad = ? and idioma = ?", ss, null, null, null);
        return mCursor;
	}
	
	
	public Cursor buscar_tot(int[] s) {
		String[] ss = new String[] { String.valueOf(s)};
    	Cursor mCursor = db.query(DATABASE_TABLE, new String[] {KEY_ROWID, TITULO, DESCRIPCION, 
    			RANGO_EDAD, AUTOR, IDIOMA, CATEGORIA, NOMBRE},  "categoria = ? and rango_edad = ? and idioma = ?", ss, null, null, null);
        return mCursor;
	}	
	
}
   

