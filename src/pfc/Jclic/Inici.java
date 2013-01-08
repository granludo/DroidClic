package pfc.Jclic;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import pfc.Activitats.Constants;
import pfc.Descompressor.Descompressor;
import pfc.Parser.Parser;
import pfc.Repositori.DadesServidor;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Inici extends Activity {

	private Constants CO = Constants.getInstance();

	static final int DIALOG_CATEGORIA_ID = 1;
	static final int DIALOG_EDAT_ID = 2;
	static final int DIALOG_IDIOMA_ID = 3;

	private ImageButton bLlibreria;
	private ImageButton bCategoria;
	private ImageButton bEdat;
	private ImageButton bIdioma;
	private TextView tvLlibreria;
	private TextView tvCategoria;
	private TextView tvEdat;
	private TextView tvIdioma;
	private TextView tvDescripcio;
	private ListView listClics;
	private CustomAdapter ca;

	private int edat = -1;
	private int categoria = -1;
	private int idioma = -1;
	
	public static File jclicDir;
	
	private FuncionsBD FDB;
	
	public void onCreate(Bundle savedInstanceState) {		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.test);
	    
	    Parser.setActivitatsSaltades(false);
	    CO.activitatActual = -1;
	    CO.exemple = false;
	    DadesServidor.keyword = "";
	    DadesServidor.all = true;
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	    jclicDir = new File(Environment.getExternalStorageDirectory(), "JClic"); 	
	    } else {
	    // Something is wrong, cannot read form SDcard
	    Toast.makeText(getApplicationContext(), "The device is not mounted", Toast.LENGTH_LONG).show();    
	    }
	    
	    bLlibreria = (ImageButton)findViewById(R.id.bLlibreria);
	    bCategoria = (ImageButton)findViewById(R.id.bCategoria);
	    bEdat = (ImageButton)findViewById(R.id.bEdat);
	    bIdioma = (ImageButton)findViewById(R.id.bIdioma);

	    bLlibreria.setBackgroundResource(R.drawable.ico_libreriab);
	    
	    tvLlibreria = (TextView)findViewById(R.id.tvLlibreria);
	    tvCategoria = (TextView)findViewById(R.id.tvCategoria);
	    tvEdat = (TextView)findViewById(R.id.tvEdat);
	    tvIdioma = (TextView)findViewById(R.id.tvIdioma);	    
 	    tvDescripcio = (TextView)findViewById(R.id.tvDescripcio);
	    
	    tvLlibreria.setText("Llibreria");
	    tvCategoria.setText("Categoria");
	    tvEdat.setText("Edat");
	    tvIdioma.setText("Idioma");
	    tvDescripcio.setText("Descripció del Clic");
	    
	    listClics = (ListView)this.findViewById(R.id.listClics);

	    FDB = new FuncionsBD(this);
	    
	    // TODO: nomes s'omple la llista en crear lactivitat, esto esta MAAL, perque al tornar surt la llista buida!
	    // TODO: caldria veure quan cal i quan no repoblar la bd
	    prepareDB();
	    fillListFromDB();
	    
	    setOnClickListener();
	}

	private void prepareDB() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			// We can read the media

			// we look for clics on the SDcard
			jclicDir.mkdirs();
			FilenameFilter jclicFilter = new FilenameFilter() {
				public boolean accept(File directory, String fileName) {
					return fileName.endsWith(".jclic.zip");
				}
			};
			
			Log.d("GPS", "Files in directory:");
			for (String fileName : jclicDir.list()) {
				Log.d("GPS", fileName);
			}
			
			File[] files = jclicDir.listFiles(jclicFilter);

			fillDatabaseFromSD(files);

		} else {
			// Something is wrong, cannot read form SDcard
			Toast.makeText(getApplicationContext(),
					"The device is not mounted", Toast.LENGTH_LONG).show();
		}
	}

	private void fillDatabaseFromSD(File[] files) {
		FDB.open();
		FDB.deletesAll();

		for (File file : files) {
			// uncompress clics to get metadata
			CO.path = file.getAbsolutePath();
			CO.fitxer = (String) CO.path.subSequence(0, CO.path.length() - 4);
			String[] split = CO.fitxer.split("/");
			CO.fitxer = split[split.length - 1];

			if (Descompressor.descompressor(CO.fitxer, CO.path)) {
				File tmpFile = new File(
						Environment.getExternalStorageDirectory(), "tmp/jclic/"
								+ CO.fitxer);
				try {
					tmpFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					Context context = getApplicationContext();
					CharSequence text = e.getMessage();
					int duration = Toast.LENGTH_LONG;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}
				Parser.ParserXML(tmpFile);

				// get clics metadata
				String title = Parser.getClicSettings().getTitle();
				if (title == null) title = "";
				String description = Parser.getClicSettings().getDescription();
				if (description == null) description = "";
				String author = Parser.getClicSettings().getAuthor();
				if (author == null) author = "";
				int age = ageToInt(Parser.getClicSettings().getAge());
				int language = languageToInt(Parser.getClicSettings()
						.getLanguage());
				int category = categoryToInt(Parser.getClicSettings()
						.getCategory());
				String fname = file.getName();
				String[] split1 = fname.split("\\.");
				String name = split1[0];

				// save clics metadata to DB
				FDB.create(title, description, age, author, language, category,
						name);
			}
		}
		FDB.close();
	}

	private void fillListFromDB() {
		FDB.open();
		Cursor c = FDB.buscar_tots_clics();
		startManagingCursor(c);
		if (c.moveToFirst()) {
			ca = new CustomAdapter(getApplicationContext(), c, listClics,
					tvDescripcio);
			listClics.setAdapter(ca);
		} else {
			tvDescripcio.setText("No hi ha activitats");
		}
		FDB.close();
	}

	private void setOnClickListener() {

		bLlibreria.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Toast.makeText(getApplicationContext(), "Obrint Llibreria...",
						Toast.LENGTH_LONG).show();
				Intent i = new Intent(getApplicationContext(), Llibreria.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(i);
				Inici.this.finish();
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
		FDB.open();
		Cursor c = null;

		if (categoria != -1 && edat == -1 && idioma == -1)
			c = FDB.buscar_per_categoria(categoria);
		else if (categoria == -1 && edat == -1 && idioma != -1)
			c = FDB.buscar_per_idioma(idioma);
		else if (categoria == -1 && edat != -1 && idioma == -1)
			c = FDB.buscar_per_edat(edat);
		else if (categoria != -1 && edat != -1 && idioma == -1) {
			int[] x = new int[] { categoria, edat };
			c = FDB.buscar_catEdat(x);
		} else if (categoria != -1 && edat == -1 && idioma != -1) {
			int[] x = new int[] { categoria, idioma };
			c = FDB.buscar_catIdioma(x);
		} else if (categoria == -1 && edat != -1 && idioma != -1) {
			int[] x = new int[] { edat, idioma };
			c = FDB.buscar_edatIdioma(x);
		} else if (categoria != -1 && edat != -1 && idioma != -1) {
			int[] x = new int[] { categoria, edat, idioma };
			c = FDB.buscar_tot(x);
		} else
			c = FDB.buscar_tots_clics();

		startManagingCursor(c);
		if (!c.moveToFirst())
			tvDescripcio.setText("No hi ha clics");
		else
			tvDescripcio.setText("Selecciona un clic");
		ca = new CustomAdapter(getApplicationContext(), c, listClics,
				tvDescripcio);
		listClics.setAdapter(ca);
		FDB.close();
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