/*
 * @Projecte: JClic per gPhone
 * @Autora: Miriam Pujol Benet
 * @Versio: Juny 2009
 */

package pfc.Parser;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import android.util.Log;

public class Parser {
	private static Vector<Dades> activitats;
	private static boolean actSaltades;
	
	public static void ParserXML(URL url){
        try {
        	SAXBuilder builder = new SAXBuilder();
			org.jdom.Document doc = builder.build(url);
			parserDoc(doc);
			
		} catch (Exception e) {
			e.printStackTrace();
			Log.d("Error","catch parserXML(url): "+e);
		}
	}
	
	public static void ParserXML(InputStream is){
		try {
        	SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(is);
			parserDoc(doc);
			
		} catch (Exception e) {
			e.printStackTrace();
			Log.d("Error","catch ParserXML(is): "+e);
		}
	}
	
	public static void ParserXML(File file){
		try {
        	SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(file);
			parserDoc(doc);
			
		} catch (Exception e) {
			e.printStackTrace();
			Log.d("Error","catch ParserXML(file): "+e);
		}
	}
	
	private static void parserDoc(Document doc){
		try{
			activitats = new Vector<Dades>();
			Element projecte = doc.getRootElement();
			
			List activities = projecte.getChild(XMLConstants.ACTIVITIES).getChildren();
			Iterator itr = activities.iterator();
			
			while(itr.hasNext()){
				//recorrem les activitats del projecte jclic
				Element activity = (Element)itr.next();
				if(activity.getAttributeValue(XMLConstants.CLASS) != null &&
						(activity.getAttributeValue(XMLConstants.CLASS).
						equalsIgnoreCase(XMLConstants.EXCHANGEPUZZ) || 
						activity.getAttributeValue(XMLConstants.CLASS).
						equalsIgnoreCase(XMLConstants.HOLEPUZZ) ||
						activity.getAttributeValue(XMLConstants.CLASS).
						equalsIgnoreCase(XMLConstants.DOUBLEPUZZ))){
					
					Dades dades = new Dades();
					dades.setClas(activity.getAttributeValue(XMLConstants.CLASS));
					dades.setName(activity.getAttributeValue(XMLConstants.NAME));
					
					//booleans per assegurar-me de que els atributs hi son al xml
					boolean desc = false, mess = false, sett = false, cells = false;
					List actMessages = null, actSettings = null;
					Element actDescription = null, actCells = null;
					
					if(activity.getChild(XMLConstants.DESCRIPTION) != null){
						actDescription = activity.getChild(XMLConstants.DESCRIPTION);
						desc = true;
					}
					if(activity.getChild(XMLConstants.MESSAGES) != null){
						actMessages = activity.getChild(XMLConstants.MESSAGES).getChildren();
						mess = true;
					}
					if(activity.getChild(XMLConstants.SETTINGS) != null){
						actSettings = activity.getChild(XMLConstants.SETTINGS).getChildren();
						sett = true;
					}
					if(activity.getChild(XMLConstants.CELLS) != null){
						actCells = activity.getChild(XMLConstants.CELLS);
						cells = true;
					}
					
					/* Activities - Activity - Description */
					if(desc && actDescription.getChildText(XMLConstants.P) != null){
						dades.setDescripcio(actDescription.getChildText(XMLConstants.P));
					}
					
					/* Activities - Activity - Messages */
					if(mess){
						Iterator itMess = actMessages.iterator();
						
						while(itMess.hasNext()){
							Element descripcio = (Element)itMess.next();
							
							String tipus = descripcio.getAttributeValue(XMLConstants.TYPE);

							if(tipus.equalsIgnoreCase(XMLConstants.INITIAL)){
								dades.setMissatgeIni(descripcio.getChildText(XMLConstants.P));
							} else if(tipus.equalsIgnoreCase(XMLConstants.FINAL)){
								dades.setMissatgeFi(descripcio.getChildText(XMLConstants.P));
							} else if(tipus.equalsIgnoreCase(XMLConstants.FINALERROR)){
								dades.setMissatgeFiErr(descripcio.getChildText(XMLConstants.P));
							}
						}
					}
					
					/* Activities - Activity - Settings */
					if(sett){
						//**Codi afegit per: Pau Farré**
						if (activity.getChild(XMLConstants.SETTINGS).getAttributeValue(XMLConstants.MAXTIME) != null) {
							dades.setTempsMax(Integer.valueOf(activity.getChild(XMLConstants.SETTINGS).getAttributeValue(XMLConstants.MAXTIME)));
						}
						if (activity.getChild(XMLConstants.SETTINGS).getAttributeValue(XMLConstants.COUNTDOWNTIME) != null) {
							dades.setTimeCutdown(Boolean.valueOf(activity.getChild(XMLConstants.SETTINGS).getAttributeValue(XMLConstants.COUNTDOWNTIME)));
						}
						if (activity.getChild(XMLConstants.SETTINGS).getAttributeValue(XMLConstants.COUNTDOWNACT) != null) {
							dades.setIntentCutdown(Boolean.valueOf(activity.getChild(XMLConstants.SETTINGS).getAttributeValue(XMLConstants.COUNTDOWNACT)));
						}
						if (activity.getChild(XMLConstants.SETTINGS).getAttributeValue(XMLConstants.MAXACTIONS) != null) {
							dades.setIntentMax(Integer.valueOf(activity.getChild(XMLConstants.SETTINGS).getAttributeValue(XMLConstants.MAXACTIONS)));
						}
						
						//******************************
						if(activity.getChild(XMLConstants.SETTINGS).getChild(XMLConstants.HELPWINDOW) != null){
							dades.setMostrarSolucio(Boolean.valueOf(activity.getChild(XMLConstants.SETTINGS).
									getChild(XMLConstants.HELPWINDOW).
									getAttributeValue(XMLConstants.SHOWSOLUTION)));							
						} else dades.setMostrarSolucio(false);
					}
					
					/* Activities - Activity - Cells */
					if(cells){
						Vector<String> celes = new Vector<String>();
						
						if(actCells.getAttributeValue(XMLConstants.ROWS) != null)
							dades.setCellRows(Integer.valueOf(actCells.getAttributeValue(XMLConstants.ROWS)));
						
						if(actCells.getAttributeValue(XMLConstants.COLUMNS) != null)
							dades.setCellCols(Integer.valueOf(actCells.getAttributeValue(XMLConstants.COLUMNS)));
						
						if(actCells.getAttributeValue(XMLConstants.BORDER) != null)
							dades.setCellBorder(Boolean.valueOf(actCells.getAttributeValue(XMLConstants.BORDER)));
						
						if(actCells.getAttributeValue(XMLConstants.IMAGE) != null)
							dades.setImage(actCells.getAttributeValue(XMLConstants.IMAGE));
						
						if(actCells.getChild(XMLConstants.STYLE).getChild(XMLConstants.COLOR) != null){
							Element color = actCells.getChild(XMLConstants.STYLE).getChild(XMLConstants.COLOR);
							
							if(color.getAttributeValue(XMLConstants.FOREGROUND) != null)
								dades.setColorFG(color.getAttributeValue(XMLConstants.FOREGROUND));
							
							if(color.getAttributeValue(XMLConstants.BACKGROUND) != null)
								dades.setColorBG(color.getAttributeValue(XMLConstants.BACKGROUND));
						}
						
						Iterator itCell = actCells.getChildren(XMLConstants.CELL).iterator();
						
						while(itCell.hasNext()){
							Element descripcio = (Element)itCell.next();
							if(descripcio.getChildText(XMLConstants.P) != null)
								celes.add(descripcio.getChildText(XMLConstants.P));
							else celes.add("");
						}
						
						dades.setCeles(celes);
					}
					
					if(!(dades.getCellCols() > 4 || dades.getCellRows() > 5)){
						Parser.activitats.add(dades);
					} else Parser.actSaltades = true;
				}
			}
		}
		catch(Exception e){
			Log.d("Error","Exception parserDoc: "+e);
		}
	}
	
	public static void setActivitats(Vector<Dades> activitats){
		Parser.activitats = activitats;
	}
	
	public static Vector<Dades> getActivitats(){
		return Parser.activitats;
	}
	
	public static void setActivitatsSaltades(boolean actSaltades){
		Parser.actSaltades = actSaltades;
	}
	
	public static boolean getActivitatsSaltades(){
		return Parser.actSaltades;
	}
}
