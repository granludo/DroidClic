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



package pfc.Parser;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import pfc.Activitats.DadesText;
import pfc.Parser.Dades.Info;
import android.util.Log;

public class Parser {
	private static Vector<Dades> activitats;
	private static boolean actSaltades;
	private static ClicSettings clicSettings;

	public static void ParserXML(URL url) {
		try {
			SAXBuilder builder = new SAXBuilder();
			org.jdom.Document doc = builder.build(url);
			parserDoc(doc);

		} catch (Exception e) {
			e.printStackTrace();
			Log.d("Error", "catch parserXML(url): " + e);
		}
	}

	public static void ParserXML(InputStream is) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(is);
			parserDoc(doc);

		} catch (Exception e) {
			e.printStackTrace();
			Log.d("Error", "catch ParserXML(is): " + e);
		}
	}

	public static void ParserXML(File file) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(file);
			parserDoc(doc);

		} catch (Exception e) {
			e.printStackTrace();
			Log.d("Error", "catch ParserXML(file): " + e);
		}
	}

	private static void parserDoc(Document doc) {
		try {
			activitats = new Vector<Dades>();
			Element projecte = doc.getRootElement();

			Element auxElem;
			String title = projecte.getChild(XMLConstants.SETTINGS).getChildText(XMLConstants.TITLE);
			auxElem = projecte.getChild(XMLConstants.SETTINGS).getChild(XMLConstants.AUTHOR);
			String author = "";
			if (auxElem != null) {
				author = auxElem.getAttributeValue(XMLConstants.NAME);
			}
			String language = projecte.getChild(XMLConstants.SETTINGS).getChildText(XMLConstants.LANGUAGE);
			String description = "";
			auxElem = projecte.getChild(XMLConstants.SETTINGS).getChild(XMLConstants.DESCRIPTION);
			if (auxElem != null) {
				description = auxElem.getChildText(XMLConstants.P);
			}
			String keywords = projecte.getChild(XMLConstants.SETTINGS).getChildText(XMLConstants.DESCRIPTORS);
			String category = "";
			auxElem = projecte.getChild(XMLConstants.SETTINGS).getChild(XMLConstants.DESCRIPTORS);
			if (auxElem != null) {
				category = auxElem.getAttributeValue(XMLConstants.AREA);
			}
			String age = "";
			auxElem = projecte.getChild(XMLConstants.SETTINGS).getChild(XMLConstants.DESCRIPTORS);
			if (auxElem != null) {
				age = auxElem.getAttributeValue(XMLConstants.LEVEL);
			}
			if (title == null) title = "";
			if (author == null) author = "";
			if (language == null) language = "";
			if (description == null) description = "";
			if (keywords == null) keywords = "";
			if (category == null) category = "";
			if (age == null) age = "";
			clicSettings = new ClicSettings();
			clicSettings.setTitle(title);
			clicSettings.setAuthor(author);
			clicSettings.setLanguage(language);
			clicSettings.setDescription(description);
			clicSettings.setKeywords(keywords);
			clicSettings.setCategory(category);
			clicSettings.setAge(age);

			
			List activities = projecte.getChild(XMLConstants.ACTIVITIES).getChildren();
			Iterator itr = activities.iterator();

			while (itr.hasNext()) {
				// recorrem les activitats del projecte jclic
				Element activity = (Element) itr.next();
				if (activity.getAttributeValue(XMLConstants.CLASS) != null
						&& (activity.getAttributeValue(XMLConstants.CLASS)
								.equalsIgnoreCase(XMLConstants.EXCHANGEPUZZ)
								|| activity.getAttributeValue(
										XMLConstants.CLASS).equalsIgnoreCase(
										XMLConstants.HOLEPUZZ)

								|| activity.getAttributeValue(
										XMLConstants.CLASS).equalsIgnoreCase(
										XMLConstants.DOUBLEPUZZ)
								|| activity.getAttributeValue(
										XMLConstants.CLASS).equalsIgnoreCase(
										XMLConstants.MEMORYGAME) // descomentar
																	// per
																	// provar
								|| activity.getAttributeValue(
										XMLConstants.CLASS).equalsIgnoreCase(
										XMLConstants.SIMPLEASSOC)
								|| activity.getAttributeValue(
										XMLConstants.CLASS).equalsIgnoreCase(
										XMLConstants.PANIDENTIFY)
								|| activity.getAttributeValue(
										XMLConstants.CLASS).equalsIgnoreCase(
										XMLConstants.COMPLEXASSOC) || activity
								.getAttributeValue(XMLConstants.CLASS)
								.equalsIgnoreCase(XMLConstants.TXTWRITEANSWER)

						)) {

					Dades dades = new Dades();
					dades.setClas(activity
							.getAttributeValue(XMLConstants.CLASS));
					dades.setName(activity.getAttributeValue(XMLConstants.NAME));

					// booleans per assegurar-me de que els atributs hi son al
					// xml
					boolean desc = false, mess = false, sett = false, cells = false;
					List actMessages = null, actSettings = null;
					Element actDescription = null/* , actCells = null */;

					// afegit per si l'activitat te diversos camps <cells>
					// blablabla </cells>,
					// ha implicat l'eliminacio de actCells
					Iterator iterCells = null;

					if (activity.getChild(XMLConstants.DESCRIPTION) != null) {
						actDescription = activity
								.getChild(XMLConstants.DESCRIPTION);
						desc = true;
					}
					if (activity.getChild(XMLConstants.MESSAGES) != null) {
						actMessages = activity.getChild(XMLConstants.MESSAGES)
								.getChildren();
						mess = true;
					}
					if (activity.getChild(XMLConstants.SETTINGS) != null) {
						actSettings = activity.getChild(XMLConstants.SETTINGS)
								.getChildren();
						sett = true;
					}
					if (activity.getChild(XMLConstants.CELLS) != null) {
						// actCells = activity.getChild(XMLConstants.CELLS);
						iterCells = activity.getChildren(XMLConstants.CELLS)
								.iterator();
						cells = true;
					}

					if (activity.getAttributeValue(XMLConstants.INVERSE) != null) {
						dades.setInverse(Boolean.valueOf(activity
								.getAttributeValue(XMLConstants.INVERSE)));
					} else {
						dades.setInverse(false);
					}

					/* Activities - Activity - Description */
					if (desc
							&& actDescription.getChildText(XMLConstants.P) != null) {
						dades.setDescripcio(actDescription
								.getChildText(XMLConstants.P));
					}

					/* Activities - Activity - Messages */
					if (mess) {
						Iterator itMess = actMessages.iterator();

						while (itMess.hasNext()) {
							Element descripcio = (Element) itMess.next();

							String tipus = descripcio
									.getAttributeValue(XMLConstants.TYPE);

							if (tipus.equalsIgnoreCase(XMLConstants.INITIAL)) {
								dades.setMissatgeIni(descripcio
										.getChildText(XMLConstants.P));
							} else if (tipus
									.equalsIgnoreCase(XMLConstants.FINAL)) {
								dades.setMissatgeFi(descripcio
										.getChildText(XMLConstants.P));
							} else if (tipus
									.equalsIgnoreCase(XMLConstants.FINALERROR)) {
								dades.setMissatgeFiErr(descripcio
										.getChildText(XMLConstants.P));
							}
						}
					}

					/* Activities - Activity - Settings */
					if (sett) {
						// **Codi afegit per: Pau Farré**
						if (activity.getChild(XMLConstants.SETTINGS)
								.getAttributeValue(XMLConstants.MAXTIME) != null) {
							dades.setTempsMax(Integer.valueOf(activity
									.getChild(XMLConstants.SETTINGS)
									.getAttributeValue(XMLConstants.MAXTIME)));
						}
						if (activity.getChild(XMLConstants.SETTINGS)
								.getAttributeValue(XMLConstants.COUNTDOWNTIME) != null) {
							dades.setTimeCutdown(Boolean.valueOf(activity
									.getChild(XMLConstants.SETTINGS)
									.getAttributeValue(
											XMLConstants.COUNTDOWNTIME)));
						}
						if (activity.getChild(XMLConstants.SETTINGS)
								.getAttributeValue(XMLConstants.COUNTDOWNACT) != null) {
							dades.setIntentCutdown(Boolean.valueOf(activity
									.getChild(XMLConstants.SETTINGS)
									.getAttributeValue(
											XMLConstants.COUNTDOWNACT)));
						}
						if (activity.getChild(XMLConstants.SETTINGS)
								.getAttributeValue(XMLConstants.MAXACTIONS) != null) {
							dades.setIntentMax(Integer
									.valueOf(activity.getChild(
											XMLConstants.SETTINGS)
											.getAttributeValue(
													XMLConstants.MAXACTIONS)));
						}

						// ******************************
						if (activity.getChild(XMLConstants.SETTINGS).getChild(
								XMLConstants.HELPWINDOW) != null) {
							dades.setMostrarSolucio(Boolean.valueOf(activity
									.getChild(XMLConstants.SETTINGS)
									.getChild(XMLConstants.HELPWINDOW)
									.getAttributeValue(
											XMLConstants.SHOWSOLUTION)));
						} else
							dades.setMostrarSolucio(false);
					}

					/* Activities - Activity - Cells */
					if (cells) {
						Vector<String> celes = new Vector<String>();
						ArrayList<String> images = new ArrayList<String>();
						ArrayList<Integer> relacions = new ArrayList<Integer>();

						while (iterCells.hasNext()) {

							Element elemCells = (Element) iterCells.next();

							if (elemCells.getAttributeValue(XMLConstants.ROWS) != null) {

								if (elemCells
										.getAttributeValue(XMLConstants.ID) == null
										|| (elemCells
												.getAttributeValue(XMLConstants.ID) != null && elemCells
												.getAttributeValue(
														XMLConstants.ID)
												.equals("primary"))) {
									if (dades.getCellRows() == 0)
										dades.setCellRows(Integer.valueOf(elemCells
												.getAttributeValue(XMLConstants.ROWS)));
								} else if (elemCells
										.getAttributeValue(XMLConstants.ID) != null
										&& elemCells.getAttributeValue(
												XMLConstants.ID).equals(
												"secondary")) {
									if (dades.getCellRows2() == 0)
										dades.setCellRows2(Integer.valueOf(elemCells
												.getAttributeValue(XMLConstants.ROWS)));
								}
							}
							if (elemCells
									.getAttributeValue(XMLConstants.COLUMNS) != null) {

								if (elemCells
										.getAttributeValue(XMLConstants.ID) == null
										|| (elemCells
												.getAttributeValue(XMLConstants.ID) != null && elemCells
												.getAttributeValue(
														XMLConstants.ID)
												.equals("primary"))) {
									if (dades.getCellCols() == 0)
										dades.setCellCols(Integer.valueOf(elemCells
												.getAttributeValue(XMLConstants.COLUMNS)));
								} else if (elemCells
										.getAttributeValue(XMLConstants.ID) != null
										&& elemCells.getAttributeValue(
												XMLConstants.ID).equals(
												"secondary")) {
									if (dades.getCellCols2() == 0)
										dades.setCellCols2(Integer.valueOf(elemCells
												.getAttributeValue(XMLConstants.COLUMNS)));
								}

							}

							if (elemCells
									.getAttributeValue(XMLConstants.BORDER) != null)
								dades.setCellBorder(Boolean.valueOf(elemCells
										.getAttributeValue(XMLConstants.BORDER)));

							if (elemCells.getAttributeValue(XMLConstants.IMAGE) != null)
								dades.setImage(elemCells
										.getAttributeValue(XMLConstants.IMAGE));

							if (elemCells.getChild(XMLConstants.STYLE)
									.getChild(XMLConstants.COLOR) != null) {

								Element color = elemCells.getChild(
										XMLConstants.STYLE).getChild(
										XMLConstants.COLOR);

								if (color
										.getAttributeValue(XMLConstants.FOREGROUND) != null)
									dades.setColorFG(color
											.getAttributeValue(XMLConstants.FOREGROUND));

								if (color
										.getAttributeValue(XMLConstants.BACKGROUND) != null)
									dades.setColorBG(color
											.getAttributeValue(XMLConstants.BACKGROUND));
							}

							Iterator itCell = elemCells.getChildren(
									XMLConstants.CELL).iterator();

							while (itCell.hasNext()) {
								Element cell = (Element) itCell.next();
								if (cell.getAttributeValue(XMLConstants.IMAGE) != null) // imatges
									images.add(cell
											.getAttributeValue(XMLConstants.IMAGE));
								else
									images.add("");
								if (cell.getChildText(XMLConstants.P) != null)
									celes.add(cell.getChildText(XMLConstants.P));
								else
									celes.add("");
								if (cell.getAttributeValue(XMLConstants.ID) != null)
									relacions
											.add(Integer.valueOf(cell
													.getAttributeValue(XMLConstants.ID)));

							}
						}
						dades.setCeles(celes);
						dades.setImages(images);
						dades.setRelacions(relacions);
					}

					if (!(dades.getCellCols() > 4 || dades.getCellRows() > 5)) {
						Parser.activitats.add(dades);
					} else
						Parser.actSaltades = true;
				}

				// GRUP11 - Activitats de text
				else if (activity.getAttributeValue(XMLConstants.CLASS)
						.equalsIgnoreCase(XMLConstants.FILLINBLANKS)) {

					Dades dades = new Dades();
					dades.setClas(activity
							.getAttributeValue(XMLConstants.CLASS));
					dades.setName(activity.getAttributeValue(XMLConstants.NAME));

					Info info = dades.new Info();

					Element actTarget = null, section = null, p = null;
					if (activity.getChild(XMLConstants.DESCRIPTION) != null) {
						actTarget = activity.getChild("document");
						section = actTarget.getChild("section");
						p = section.getChild("p");
						List elementos = p.getChildren();
						for (int i = 0; i < elementos.size(); ++i) {
							Element incognita = (Element) elementos.get(i);
							if (incognita.getName() == "target") {
								info = dades.new Info();
								info.isBlank = true;
								info.text = incognita.getChildText("text");
							} else if (incognita.getName() == "text") {
								info = dades.new Info();
								info.isBlank = false;
								info.text = incognita.getText();
							}
							dades.addInfoToArray(i, info);
						}
						Parser.activitats.add(dades);
					}

				} else if (activity.getAttributeValue(XMLConstants.CLASS)
						.equalsIgnoreCase(XMLConstants.IDENTIFY)) {
					Dades dades = new Dades();
					dades.setClas(activity
							.getAttributeValue(XMLConstants.CLASS));
					dades.setName(activity.getAttributeValue(XMLConstants.NAME));

					// booleans per assegurar-me de que els atributs hi son al
					// xml
					boolean desc = false, mess = false, sett = false, text = false;
					List actMessages = null, actSettings = null;
					Element actDescription = null, actdoc = null;

					if (activity.getChild(XMLConstants.DESCRIPTION) != null) {
						actDescription = activity
								.getChild(XMLConstants.DESCRIPTION);
						desc = true;
					}
					if (activity.getChild(XMLConstants.MESSAGES) != null) {
						actMessages = activity.getChild(XMLConstants.MESSAGES)
								.getChildren();
						mess = true;
					}
					if (activity.getChild(XMLConstants.SETTINGS) != null) {
						actSettings = activity.getChild(XMLConstants.SETTINGS)
								.getChildren();
						sett = true;
					}
					if (activity.getChild(XMLConstants.DOCUMENT) != null) {
						actdoc = activity.getChild(XMLConstants.DOCUMENT);
						text = true;
					}

					/* Activities - Activity - Description */
					if (desc
							&& actDescription.getChildText(XMLConstants.P) != null) {
						dades.setDescripcio(actDescription
								.getChildText(XMLConstants.P));
					}

					/* Activities - Activity - Messages */
					if (mess) {
						Iterator itMess = actMessages.iterator();

						while (itMess.hasNext()) {
							Element descripcio = (Element) itMess.next();

							String tipus = descripcio
									.getAttributeValue(XMLConstants.TYPE);

							if (tipus.equalsIgnoreCase(XMLConstants.INITIAL)) {
								dades.setMissatgeIni(descripcio
										.getChildText(XMLConstants.P));
							} else if (tipus
									.equalsIgnoreCase(XMLConstants.FINAL)) {
								dades.setMissatgeFi(descripcio
										.getChildText(XMLConstants.P));
							} else if (tipus
									.equalsIgnoreCase(XMLConstants.FINALERROR)) {
								dades.setMissatgeFiErr(descripcio
										.getChildText(XMLConstants.P));
							}
						}

					}
					/* Activities - Activity - Settings */
					if (sett) {

						if (activity.getChild(XMLConstants.SETTINGS)
								.getAttributeValue(XMLConstants.MAXTIME) != null) {
							dades.setTempsMax(Integer.valueOf(activity
									.getChild(XMLConstants.SETTINGS)
									.getAttributeValue(XMLConstants.MAXTIME)));
						}
						if (activity.getChild(XMLConstants.SETTINGS)
								.getAttributeValue(XMLConstants.COUNTDOWNTIME) != null) {
							dades.setTimeCutdown(Boolean.valueOf(activity
									.getChild(XMLConstants.SETTINGS)
									.getAttributeValue(
											XMLConstants.COUNTDOWNTIME)));
						}
						if (activity.getChild(XMLConstants.SETTINGS)
								.getAttributeValue(XMLConstants.COUNTDOWNACT) != null) {
							dades.setIntentCutdown(Boolean.valueOf(activity
									.getChild(XMLConstants.SETTINGS)
									.getAttributeValue(
											XMLConstants.COUNTDOWNACT)));
						}
						if (activity.getChild(XMLConstants.SETTINGS)
								.getAttributeValue(XMLConstants.MAXACTIONS) != null) {
							dades.setIntentMax(Integer
									.valueOf(activity.getChild(
											XMLConstants.SETTINGS)
											.getAttributeValue(
													XMLConstants.MAXACTIONS)));
						}

						// ******************************
						if (activity.getChild(XMLConstants.SETTINGS).getChild(
								XMLConstants.HELPWINDOW) != null) {
							dades.setMostrarSolucio(Boolean.valueOf(activity
									.getChild(XMLConstants.SETTINGS)
									.getChild(XMLConstants.HELPWINDOW)
									.getAttributeValue(
											XMLConstants.SHOWSOLUTION)));
						} else
							dades.setMostrarSolucio(false);
					}

					List actsec = actdoc.getChild(XMLConstants.SECTION)
							.getChildren();
					ArrayList textus = new ArrayList();
					Iterator itP = actsec.iterator();
					int position = 0;
					int targets = 0;
					for (int i = 0; itP.hasNext(); ++i) {

						Element p = (Element) itP.next();
						List listText = p.getChildren();
						Iterator itTEXT = listText.iterator();
						ArrayList arrayP = new ArrayList();
						for (int j = 0; itTEXT.hasNext(); ++j) {
							Element etext = (Element) itTEXT.next();
							if (etext.getName() == "target") {
								++targets;
								DadesText d1 = new DadesText();
								d1.text = etext.getText();
								d1.polsat = false;
								d1.tag = etext.getName();
								d1.ranginici = position;
								position += d1.text.length();
								d1.rangfi = position;
								arrayP.add(d1);
							}

							else if (etext.getName() == "text") {
								int posfi = position;
								String paraula = "";
								for (int l = 0; l < etext.getText().length(); ++l) {
									if ((char) etext.getText().charAt(l) == ' '
											|| (char) etext.getText().charAt(l) == '	') {
										if (paraula.length() > 0) {
											DadesText d1 = new DadesText();
											d1.text = paraula;
											d1.tag = "text";
											d1.polsat = false;
											d1.ranginici = position;
											d1.rangfi = posfi;
											position = posfi;
											arrayP.add(d1);
											paraula = "";
										}
										DadesText d1 = new DadesText();
										++posfi;
										if ((char) etext.getText().charAt(l) == ' ')
											d1.text = " ";
										else
											d1.text = "	";
										d1.tag = "espais";
										d1.polsat = false;
										d1.ranginici = position;
										d1.rangfi = posfi;
										arrayP.add(d1);
										position = posfi;
									} else {
										paraula += (char) etext.getText()
												.charAt(l);
										++posfi;
									}
								}
								if (paraula.length() > 0) {
									DadesText d1 = new DadesText();
									d1.text = paraula;
									d1.tag = "text";
									d1.polsat = false;
									d1.ranginici = position;
									d1.rangfi = posfi;
									position = posfi;
									arrayP.add(d1);
									paraula = "";
								}

							}
						}
						// ACABA la linia; afegim un salt de linia
						DadesText d1 = new DadesText();
						d1.text = "\n";
						d1.tag = "saltLinia";
						d1.polsat = false;
						d1.ranginici = position;
						++position;
						d1.rangfi = position;
						arrayP.add(d1);
						if (!arrayP.isEmpty())
							textus.add(arrayP);
					}
					dades.setTextus(textus);
					dades.setNumTargets(targets);
					Parser.activitats.add(dades);

				} else if (activity.getAttributeValue(XMLConstants.CLASS)
						.equalsIgnoreCase(XMLConstants.TGRIDCROSSWORD)) {
					Dades dades = new Dades();
					dades.setClas(activity
							.getAttributeValue(XMLConstants.CLASS));
					dades.setName(activity.getAttributeValue(XMLConstants.NAME));

					// booleans per assegurar-me de que els atributs hi son al
					// xml
					boolean desc = false, mess = false, sett = false, text = false;
					List actMessages = null, actSettings = null;
					Element actDescription = null, actdoc = null, actTextGrid = null;

					if (activity.getChild(XMLConstants.DESCRIPTION) != null) {
						actDescription = activity
								.getChild(XMLConstants.DESCRIPTION);
						desc = true;
					}
					if (activity.getChild(XMLConstants.MESSAGES) != null) {
						actMessages = activity.getChild(XMLConstants.MESSAGES)
								.getChildren();
						mess = true;
					}
					if (activity.getChild(XMLConstants.SETTINGS) != null) {
						actSettings = activity.getChild(XMLConstants.SETTINGS)
								.getChildren();
						sett = true;
					}
					if (activity.getChild(XMLConstants.TEXTGRID) != null) {
						actTextGrid = activity.getChild(XMLConstants.TEXTGRID);
						text = true;
					}

					/* Activities - Activity - Description */
					if (desc
							&& actDescription.getChildText(XMLConstants.P) != null) {
						dades.setDescripcio(actDescription
								.getChildText(XMLConstants.P));
					}

					/* Activities - Activity - Messages */
					if (mess) {
						Iterator itMess = actMessages.iterator();

						while (itMess.hasNext()) {
							Element descripcio = (Element) itMess.next();

							String tipus = descripcio
									.getAttributeValue(XMLConstants.TYPE);

							if (tipus.equalsIgnoreCase(XMLConstants.INITIAL)) {
								dades.setMissatgeIni(descripcio
										.getChildText(XMLConstants.P));
							} else if (tipus
									.equalsIgnoreCase(XMLConstants.FINAL)) {
								dades.setMissatgeFi(descripcio
										.getChildText(XMLConstants.P));
							} else if (tipus
									.equalsIgnoreCase(XMLConstants.FINALERROR)) {
								dades.setMissatgeFiErr(descripcio
										.getChildText(XMLConstants.P));
							}
						}

					}
					/* Activities - Activity - Settings */
					if (sett) {

						if (activity.getChild(XMLConstants.SETTINGS)
								.getAttributeValue(XMLConstants.MAXTIME) != null) {
							dades.setTempsMax(Integer.valueOf(activity
									.getChild(XMLConstants.SETTINGS)
									.getAttributeValue(XMLConstants.MAXTIME)));
						}
						if (activity.getChild(XMLConstants.SETTINGS)
								.getAttributeValue(XMLConstants.COUNTDOWNTIME) != null) {
							dades.setTimeCutdown(Boolean.valueOf(activity
									.getChild(XMLConstants.SETTINGS)
									.getAttributeValue(
											XMLConstants.COUNTDOWNTIME)));
						}
						if (activity.getChild(XMLConstants.SETTINGS)
								.getAttributeValue(XMLConstants.COUNTDOWNACT) != null) {
							dades.setIntentCutdown(Boolean.valueOf(activity
									.getChild(XMLConstants.SETTINGS)
									.getAttributeValue(
											XMLConstants.COUNTDOWNACT)));
						}
						if (activity.getChild(XMLConstants.SETTINGS)
								.getAttributeValue(XMLConstants.MAXACTIONS) != null) {
							dades.setIntentMax(Integer
									.valueOf(activity.getChild(
											XMLConstants.SETTINGS)
											.getAttributeValue(
													XMLConstants.MAXACTIONS)));
						}

						// ******************************
						if (activity.getChild(XMLConstants.SETTINGS).getChild(
								XMLConstants.HELPWINDOW) != null) {
							dades.setMostrarSolucio(Boolean.valueOf(activity
									.getChild(XMLConstants.SETTINGS)
									.getChild(XMLConstants.HELPWINDOW)
									.getAttributeValue(
											XMLConstants.SHOWSOLUTION)));
						} else
							dades.setMostrarSolucio(false);
					}
					List acttext = actTextGrid.getChild(XMLConstants.TEXT)
							.getChildren();

					ArrayList rowsus = new ArrayList();
					Iterator itP = acttext.iterator();
					int rows = 0;
					int columnes = 0;
					for (int i = 0; itP.hasNext(); ++i) {
						Element p = (Element) itP.next();
						String row = p.getText();
						columnes = row.length();
						rowsus.add(row);
					}
					rows = rowsus.size();

					dades.setCrossword(rowsus);

					ArrayList horitzontals = new ArrayList();
					ArrayList verticals = new ArrayList();
					if (activity.getChild(XMLConstants.CELLS) != null) {
						List actCells = activity.getChildren();
						Iterator itPo = actCells.iterator();
						Boolean firstTime = true;
						for (int i = 0; itPo.hasNext(); ++i) {
							Element p = (Element) itPo.next();
							if (p.getName() == "cells") {
								List actcell = p.getChildren();
								Iterator itTEXT = actcell.iterator();
								ArrayList arrayP = new ArrayList();
								for (int j = 0; itTEXT.hasNext(); ++j) {
									Element etext = (Element) itTEXT.next();
									if (etext.getName() == "cell") {
										String descp = " ";
										List actPs = etext.getChildren();
										Iterator itPs = actPs.iterator();
										if (itPs.hasNext()) {
											Element Ps = (Element) itPs.next();
											descp = Ps.getText();
										}
										if (firstTime)
											horitzontals.add(descp);
										else
											verticals.add(descp);
									}
								}
								firstTime = false;
							}
						}

					}
					dades.setHoritzontals(horitzontals);
					dades.setVerticals(verticals);

					Parser.activitats.add(dades);
					// Log.d("CROSSWORD", "--------------------------------");

				} else if (activity.getAttributeValue(XMLConstants.CLASS) != null
						&& (activity.getAttributeValue(XMLConstants.CLASS)
								.equalsIgnoreCase(XMLConstants.TXTORDERELEM))) {
					
					Dades dades = new Dades();
					dades.setClas(activity
							.getAttributeValue(XMLConstants.CLASS));
					dades.setName(activity.getAttributeValue(XMLConstants.NAME));
					boolean sec = false;
					Iterator itersec = null;
					if (activity.getChild(XMLConstants.DOCUMENT).getChild(
							XMLConstants.SECTION) != null) {
						itersec = activity.getChild(XMLConstants.DOCUMENT)
								.getChildren(XMLConstants.SECTION).iterator();
						sec = true;
					}

					if (sec) {
						Vector<String> tt = new Vector<String>();
						Vector<Boolean> quees = new Vector<Boolean>();
						while (itersec.hasNext()) {
							Element elemsec = (Element) itersec.next();
							Iterator iterp = elemsec
									.getChildren(XMLConstants.P).iterator();
							while (iterp.hasNext()) {
								Element el = (Element) iterp.next();
								Iterator itertarget = el.getChildren(
										XMLConstants.TARGET).iterator();
								Iterator itertext = el.getChildren(
										XMLConstants.TEXT).iterator();
								List l = null;
								l = el.getChildren();
								String first = "hola";
								if (l.isEmpty() == false)
									first = l.get(0).toString();
								boolean b = false;
								while (itertarget.hasNext()
										&& itertext.hasNext()) {
									Element tar = (Element) itertarget.next();
									Element te = (Element) itertext.next();
									if (first.contains("target")) {
										if (tar != null) {
											b = true;
											String ta = tar.getText();
											tt.add(ta);
											quees.add(b);
										}
										if (te != null) {
											b = false;
											String tee = te.getText();
											if (!tee.contentEquals(" ")) {
												tt.add(tee);
												quees.add(b);
											}
										}
									} else if (first.contains("text")) {
										if (te != null) {
											b = false;
											String tee = te.getText();
											if (!tee.contentEquals(" ")) {
												tt.add(tee);
												quees.add(b);
											}
										}
										if (tar != null) {
											b = true;
											String ta = tar.getText();
											tt.add(ta);
											quees.add(b);
										}
									}

								}
								while (itertarget.hasNext()) {
									Element tar = (Element) itertarget.next();
									if (tar != null) {
										b = true;
										String ta = tar.getText();
										tt.add(ta);
										quees.add(b);
									}

								}
								while (itertext.hasNext()) {
									Element te = (Element) itertext.next();
									if (te != null) {
										b = false;
										String tee = te.getText();
										if (!tee.contentEquals(" ")) {
											tt.add(tee);
											quees.add(b);
										}
									}
								}
								if (iterp.hasNext()) {
									tt.add("\n");
									quees.add(false);
								}
							}
						}
						dades.setT(tt);
						dades.setbool(quees);
						// dades.setTextos(textos);
					}
					Parser.activitats.add(dades);
				}
			}
		} catch (Exception e) {
			Log.d("Error", "Exception parserDoc: " + e);
		}
	}

	public static void setActivitats(Vector<Dades> activitats) {
		Parser.activitats = activitats;
	}

	public static Vector<Dades> getActivitats() {
		return Parser.activitats;
	}

	public static void setActivitatsSaltades(boolean actSaltades) {
		Parser.actSaltades = actSaltades;
	}

	public static boolean getActivitatsSaltades() {
		return Parser.actSaltades;
	}

	public static void setClicSettings(ClicSettings clicSettings) {
		Parser.clicSettings = clicSettings;
	}

	public static ClicSettings getClicSettings() {
		return Parser.clicSettings;
	}
}