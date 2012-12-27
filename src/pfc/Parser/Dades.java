/*
 * @Projecte: JClic per gPhone
 * @Autora: Miriam Pujol Benet
 * @Versio: Juny 2009
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

    // *camps nous*******
    private int tempsMax = 0;
    private boolean timeCutdown = false;
    private boolean intentCutdown = false;
    private int intentMax = 0;
    private ArrayList<String> images;
    ArrayList textus;
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

    // ********************************************
}
