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
	public final String ICONO = "icono";
	public final String KEYWORDS = "keywords";
	public final String JCLIC = "jclic";
	
	private static final String DATABASE_TABLE = "Clics";
	
	private Context context;
	
	private SQLiteDatabase db;
	private DataBaseSQLite dbHelper;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
	}
		
    public FuncionsBD(Context ctx) {
		// TODO Auto-generated constructor stub
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
    
    public void create(String titol, String descripcio, String edat, String autor, String idioma, String categoria, String icona, String keywords, String jclic) {
      /*  ContentValues initialValues = new ContentValues();
        initialValues.put(TITULO, titol);
        initialValues.put(DESCRIPCION, descripcio);
        initialValues.put(RANGO_EDAD, edat);
        initialValues.put(AUTOR, autor);
        initialValues.put(IDIOMA, idioma);
        initialValues.put(CATEGORIA, categoria);
        initialValues.put(ICONO, icona);
        initialValues.put(KEYWORDS, keywords);
        initialValues.put(JCLIC, jclic);*/
        db.execSQL("insert into Clics ("+TITULO+","+DESCRIPCION+","+RANGO_EDAD+","+AUTOR+","+IDIOMA+","+CATEGORIA+","+ICONO+","+KEYWORDS+","+JCLIC+") VALUES ('clic7', 'descripcio4..5..castell�..mates','4..5','MMB','castell�', " +
        		"'llengua', '/sdcard/GPS/nadal.jpg', 'brrrrr', '/sdcard/GPS/pipinyer.jclic.zip')");
      
      //  return db.insert(DATABASE_TABLE, null, initialValues);

    }

    public boolean deleteClic(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    public void deletesAll() {
		db.delete(DATABASE_TABLE, null, null);
	}
    
    //Cursor amb totes les notes de la base de dades
    public Cursor buscar_tots_clics() {
    	/*return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, TITULO, DESCRIPCION, 
    			RANGO_EDAD, AUTOR, IDIOMA, CATEGORIA, ICONO, KEYWORDS, JCLIC}, 
    			null, null, null, null, null);*/
    	Cursor mCursor = db.rawQuery("Select * from Clics", null);
    	return mCursor;
    }
 
    
    public Cursor buscar_per_idioma(String[] s) throws SQLException {
    	Cursor mCursor = db.rawQuery("SELECT  _id ,titulo FROM Clics WHERE idioma = ? ", s);
        return mCursor;
    }
    
	public Cursor buscar_per_categoria(String[] s) {
		Cursor mCursor = db.rawQuery("SELECT  _id ,titulo FROM Clics WHERE categoria = ? ", s);
        return mCursor;
	}
    
	public Cursor buscar_per_edat(String[] s) {
		Cursor mCursor = db.rawQuery("SELECT  _id ,titulo FROM Clics WHERE rango_edad = ? ", s);
        return mCursor;
	}
    
	
	public Cursor buscar_catEdat(String[] s) {
		Cursor mCursor = db.rawQuery("SELECT  _id ,titulo FROM Clics WHERE categoria = ? and rango_edad = ?", s);
        return mCursor;
	}
	
	public Cursor buscar_catIdioma(String[] s) {
		Cursor mCursor = db.rawQuery("SELECT  _id ,titulo FROM Clics WHERE categoria = ? and idioma = ?", s);
        return mCursor;
	}
	
	public Cursor buscar_edatIdioma(String[] s) {
		Cursor mCursor = db.rawQuery("SELECT  _id ,titulo FROM Clics WHERE rango_edad = ? and idioma = ?", s);
        return mCursor;
	}
	
	
	
	public Cursor buscar_tot(String[] s) {
		Cursor mCursor = db.rawQuery("SELECT  _id ,titulo FROM Clics WHERE categoria = ? and  rango_edad = ? and idioma = ?", s);
        return mCursor;
	}	
	
	
	
    }
   
