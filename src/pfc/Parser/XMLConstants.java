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

public class XMLConstants {
    // tag
    public static final String ACTIVITIES = "activities";
    public static final String ACTIVITY = "activity";
    public static final String DESCRIPTION = "description";
    public static final String MESSAGES = "messages";
    public static final String CELL = "cell";
    public static final String DOCUMENT = "document";
    public static final String SETTINGS = "settings";
    public static final String CELLS = "cells";
    public static final String TEXT = "text";
    public static final String ID = "id";
    public static final String INVERSE = "inverse";
    public static final String TEXTGRID = "textGrid";

    // Valors dins dels tag
    public static final String EXCHANGEPUZZ = "@puzzles.ExchangePuzzle";
    public static final String HOLEPUZZ = "@puzzles.HolePuzzle";
    public static final String DOUBLEPUZZ = "@puzzles.DoublePuzzle";
    
    // ******Altres activitats**********
    public static final String MEMORYGAME = "@memory.MemoryGame";
    public static final String SIMPLEASSOC = "@associations.SimpleAssociation";
    public static final String COMPLEXASSOC = "@associations.ComplexAssociation";
    public static final String PANEXPLORE = "@panels.Explore";
    public static final String PANIDENTIFY = "@panels.Identify";
    public static final String PANINFORMATION = "@panels.InformationScreen";
    public static final String TXTCOMPLETE = "@text.Complete";
    public static final String TXTWRITEANSWER = "@text.WrittenAnswer";
    public static final String TXTFILLBLANKS = "@text.FillInBlanks";
    public static final String FILLINBLANKS = "@text.FillInBlanks";
    public static final String TXTIDENTIFY = "@text.Identify";
    public static final String IDENTIFY = "@text.Identify";
    public static final String TXTORDERELEM = "@text.Order";
    public static final String TGRIDWORDSEARCH = "@textGrid.WordSearch";
    public static final String TGRIDCROSSWORD = "@textGrid.CrossWord";
    // ***********************************

    public static final String P = "p";
    public static final String CLASS = "class";
    public static final String NAME = "name";
    public static final String TYPE = "type";

    public static final String INITIAL = "initial";
    public static final String FINAL = "final";
    public static final String FINALERROR = "finalError";

    // altres dins de settings
    public static final String ROWS = "rows";
    public static final String COLUMNS = "cols";
    public static final String IMAGE = "image";
    public static final String BORDER = "border";
    public static final String COLOR = "color";
    public static final String FOREGROUND = "foreground";
    public static final String BACKGROUND = "background";
    public static final String SECTION = "section";

    public static final String STYLE = "style";
    public static final String HELPWINDOW = "helpWindow";
    public static final String SHOWSOLUTION = "showSolution";

    public static final String MAXTIME = "maxTime";
    public static final String COUNTDOWNTIME = "countDownTime";
    public static final String COUNTDOWNACT = "countDownActions";
    public static final String MAXACTIONS = "maxActions";
    public static final String TARGET = "target";

	// settings children:
	public static final String TITLE = "title";
	public static final String AUTHOR = "author";
	public static final String LANGUAGE = "language";
	public static final String DESCRIPTORS = "descriptors";
	public static final String AREA = "area";
	public static final String LEVEL = "level";
}