/*
 * @Projecte: JClic per gPhone
 * @Autora: Miriam Pujol Benet
 * @Versio: Juny 2009
 */

package pfc.Jclic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import android.util.Log;

/*
 * Classe per poder fer que els System.out i System.err surtin per la consola
 * de debugacio
 */
public class LogSystem extends OutputStream
{
	final private static int OUT = 0; 
	final private static int ERR = 1;
	
	private ByteArrayOutputStream bos = new ByteArrayOutputStream();
	private int tipus;
	
	public static void Init() {
		System.setOut(new PrintStream(new LogSystem(OUT)));
		System.setErr(new PrintStream(new LogSystem(ERR)));
	}
	
	public LogSystem(int _tipus) {
		tipus = _tipus;
	}

	@Override
	public void write(int b) throws IOException
	{
		if (b == (int)'\n' ) {
			String s = this.bos.toString();
			switch(tipus) {
				case OUT: Log.d("System.out", s); break;
				case ERR: Log.e("System.err", s); break;
			}
			this.bos = new ByteArrayOutputStream();
		}
		else {
			this.bos.write(b);
		}
	}
} 
