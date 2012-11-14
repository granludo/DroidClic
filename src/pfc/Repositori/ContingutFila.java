/*
 * @Projecte: JClic per gPhone
 * @Autora: Miriam Pujol Benet
 * @Versio: Juny 2009
 */

package pfc.Repositori;

import android.graphics.drawable.Drawable;

public class ContingutFila {
	public Drawable icono;
	public String title;
	public String area;
	public String lang;
	public Drawable arrow;
	public String file;
	
	public ContingutFila(Drawable icono, String title, String area, 
			String lang, Drawable arrow, String file) {
		this.icono = icono;
		this.title = title;
		this.area = area;
		this.lang = lang;
		this.arrow = arrow;
		this.file = file;
    }	
}
