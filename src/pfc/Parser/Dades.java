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

import java.util.ArrayList;
import java.util.Vector;

public class Dades {
    private String clas;
    private String name;
    private String descripcio;
    private Vector<String> celes;
    private int cellRows;
    private int cellCols;

    private int cellRows2;
    private int cellCols2;
    private boolean inverse;
    private ArrayList<Integer> relacions;

    private boolean cellBorder;
    private String colorFG;
    private String colorBG;
    private String missIni;
    private String missFi;
    private String missFiErr;
    private boolean mostrarSolucio;
    private String image;
    
    
    public class Info {
		public boolean isBlank;
		public String text;
	}
	ArrayList<Info> infoArray = new ArrayList<Info>();
    
    // *camps nous*******
    private int tempsMax = 0;
    private boolean timeCutdown = false;
    private boolean intentCutdown = false;
    private int intentMax = 0;
    private ArrayList<String> images;
    ArrayList textus;
    private ArrayList rowsus; 
    private ArrayList horitzontals;
    private ArrayList verticals;
    private int targets;
    private Vector<String> tt;
    private Vector<Boolean> quees;

    public void Dades() {
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getClas() {
        return this.clas;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getDescripcio() {
        return this.descripcio;
    }

    public void setCeles(Vector<String> celes) {
        this.celes = celes;
    }

    public Vector<String> getCeles() {
        return this.celes;
    }

    public void setCellRows(int cellRows) {
        this.cellRows = cellRows;
    }

    public int getCellRows() {
        return this.cellRows;
    }

    public void setCellCols(int cellCols) {
        this.cellCols = cellCols;
    }

    public int getCellCols() {
        return this.cellCols;
    }

    public void setCellBorder(boolean cellBorder) {
        this.cellBorder = cellBorder;
    }

    public boolean getCellBorder() {
        return this.cellBorder;
    }

    public void setColorBG(String color) {
        this.colorBG = color;
    }

    public String getColorBG() {
        return this.colorBG;
    }

    public void setColorFG(String color) {
        this.colorFG = color;
    }

    public String getColorFG() {
        return this.colorFG;
    }

    public void setMissatgeIni(String missIni) {
        this.missIni = missIni;
    }

    public String getMissatgeIni() {
        return this.missIni;
    }

    public void setMissatgeFi(String missFi) {
        this.missFi = missFi;
    }

    public String getMissatgeFi() {
        return this.missFi;
    }

    public void setMissatgeFiErr(String missFiErr) {
        this.missFiErr = missFiErr;
    }

    public String getMissatgeFiErr() {
        return this.missFiErr;
    }

    public void setMostrarSolucio(boolean mostrarSolucio) {
        this.mostrarSolucio = mostrarSolucio;
    }

    public boolean getMostrarSolucio() {
        return this.mostrarSolucio;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return this.image;
    }

    // *******Codi nou************
    public void setTempsMax(int tempsMax) {
        this.tempsMax = tempsMax;
    }

    public int getTempsMax() {
        return this.tempsMax;
    }

    public void setTimeCutdown(boolean timeCutdown) {
        this.timeCutdown = timeCutdown;
    }

    public boolean getTimeCutDown() {
        return this.timeCutdown;
    }

    public void setIntentCutdown(boolean intentCutdown) {
        this.intentCutdown = intentCutdown;
    }

    public boolean getIntentCutdown() {
        return this.intentCutdown;
    }

    public void setIntentMax(int intentMax) {
        this.intentMax = intentMax;
    }

    public int getIntentMax() {
        return this.intentMax;
    }

    // *******************************************

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public int getCellRows2() {
        return cellRows2;
    }

    public void setCellRows2(int cellRows2) {
        this.cellRows2 = cellRows2;
    }

    public int getCellCols2() {
        return cellCols2;
    }

    public void setCellCols2(int cellCols2) {
        this.cellCols2 = cellCols2;
    }

    public boolean isInverse() {
        return inverse;
    }

    public void setInverse(boolean inverse) {
        this.inverse = inverse;
    }

    public ArrayList<Integer> getRelacions() {
        return relacions;
    }

    public void setRelacions(ArrayList<Integer> relacions) {
        this.relacions = relacions;
    }

    public void setTextus(ArrayList textus) {
        this.textus = textus;
    }

    public ArrayList getTextus() {
        return textus;
    }
    
    public void setCrossword(ArrayList rowsus) {
        this.rowsus = rowsus;
    }

    public ArrayList getCrossword() {
        return rowsus;
    }
    
    public void setHoritzontals(ArrayList hor) {
        this.horitzontals = hor;
    }

    public ArrayList getHoritzontals() {
        return horitzontals;
    }
    
    public void setVerticals(ArrayList ver) {
        this.verticals = ver;
    }

    public ArrayList getVertical() {
        return verticals;
    }

    public void setNumTargets(int n) {
        targets = n;
    }

    public int getNumTargets() {
        return targets;
    }

    public Vector<String> getT() {
        return tt;
    }

    public Vector<Boolean> getbool() {
        return quees;
    }

    public void setT(Vector<String> textos) {
        tt = textos;
    }

    public void setbool(Vector<Boolean> quees) {
        this.quees = quees;
    }

    public void addInfoToArray(int j, Info i){
    	infoArray.add(j, i);
    }
    public ArrayList<Info> getArrayFillInBlanks(){
    	return this.infoArray;
    }
    // ********************************************
}
