/*
 * @Projecte: JClic per gPhone
 * @Autora: Miriam Pujol Benet
 * @Versio: Juny 2009
 */

package pfc.Descompressor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.os.Environment;
import android.util.Log;

public class Descompressor {
	
	public static boolean descompressor(String fitxer, String path) {
		/*
		 * Agafa el fitxer que se li demana (CO.fitxer)
		 * del fitxer que se li diu (CO.path)
		 * i el coloca a /sdcard/tmp/jclic
		 */ 
		try {
			String sdpath = Environment.getExternalStorageDirectory().toString();
			String ext = "/tmp";
			String desti = sdpath + ext;
		    File NewFolder = new File(desti);
		    if(!NewFolder.exists()) {
		    	NewFolder.mkdirs();
		    }
		    else NewFolder.delete();
		    ext = "/jclic/";
		    desti += ext;
		    File NewFolder2 = new File(desti);
		    if(!NewFolder2.exists()) {
		    	NewFolder2.mkdirs();
		    }
		    else NewFolder2.delete();
		    
			byte[] buf = new byte[1024];
			
			ZipInputStream zis = new ZipInputStream(new FileInputStream(path));
			ZipEntry ze = zis.getNextEntry();

			while(ze != null){
				String entryName = ze.getName();
				
				if(fitxer.equals(entryName)){
					File newFile = new File(entryName);
					String directory = newFile.getParent();
					
					if(directory == null){
						if(newFile.isDirectory())
							break;
					}
					
					FileOutputStream fos = new FileOutputStream(desti + entryName);
					
					int n;
					while((n = zis.read(buf,0,1024)) > -1){
						fos.write(buf,0,n);
					}
					fos.close();
				}
				zis.closeEntry();
				ze = zis.getNextEntry();
			}
			zis.close();
			
			return true;
		} catch (Exception e) {
			Log.d("Error", "catch Descompressor: "+e);
			return false;
		}
    }
}
