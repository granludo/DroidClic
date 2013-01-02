/*
 * @Projecte: JClic per gPhone
 * @Autora: Miriam Pujol Benet
 * @Versio: Juny 2009
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

    public static void ParserXML(URL url) {
        try {
            SAXBuilder builder = new SAXBuilder();
            org.jdom.Document doc = builder.build(url);
            parserDoc(doc);

        }
        catch (Exception e) {
            e.printStackTrace();
            Log.d("Error", "catch parserXML(url): " + e);
        }
    }

    public static void ParserXML(InputStream is) {
        try {
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(is);
            parserDoc(doc);

        }
        catch (Exception e) {
            e.printStackTrace();
            Log.d("Error", "catch ParserXML(is): " + e);
        }
    }

    public static void ParserXML(File file) {
        try {
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(file);
            parserDoc(doc);

        }
        catch (Exception e) {
            e.printStackTrace();
            Log.d("Error", "catch ParserXML(file): " + e);
        }
    }

    private static void parserDoc(Document doc) {
        try {
            activitats = new Vector<Dades>();
            Element projecte = doc.getRootElement();

            List activities = projecte.getChild(XMLConstants.ACTIVITIES)
                .getChildren();
            Iterator itr = activities.iterator();

            while (itr.hasNext()) {
                // recorrem les activitats del projecte jclic
                Element activity = (Element) itr.next();
                if (activity.getAttributeValue(XMLConstants.CLASS) != null
                    && (activity.getAttributeValue(XMLConstants.CLASS)
                        .equalsIgnoreCase(XMLConstants.EXCHANGEPUZZ)
                        // ||
                        // activity.getAttributeValue(XMLConstants.CLASS).equalsIgnoreCase(XMLConstants.HOLEPUZZ)

                        || activity.getAttributeValue(XMLConstants.CLASS)
                            .equalsIgnoreCase(XMLConstants.DOUBLEPUZZ)
                        || activity.getAttributeValue(XMLConstants.CLASS)
                            .equalsIgnoreCase(XMLConstants.MEMORYGAME) // descomentar
                                                                       // per
                                                                       // provar
                        || activity.getAttributeValue(XMLConstants.CLASS)
                            .equalsIgnoreCase(XMLConstants.SIMPLEASSOC)
                        || activity.getAttributeValue(XMLConstants.CLASS)
                            .equalsIgnoreCase(XMLConstants.PANIDENTIFY)
                        || activity.getAttributeValue(XMLConstants.CLASS)
                            .equalsIgnoreCase(XMLConstants.COMPLEXASSOC) || activity
                        .getAttributeValue(XMLConstants.CLASS)
                        .equalsIgnoreCase(XMLConstants.TXTWRITEANSWER))) {

                    Dades dades = new Dades();
                    dades.setClas(activity
                        .getAttributeValue(XMLConstants.CLASS));
                    dades
                        .setName(activity.getAttributeValue(XMLConstants.NAME));

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
                    }
                    else {
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
                            }
                            else if (tipus.equalsIgnoreCase(XMLConstants.FINAL)) {
                                dades.setMissatgeFi(descripcio
                                    .getChildText(XMLConstants.P));
                            }
                            else if (tipus
                                .equalsIgnoreCase(XMLConstants.FINALERROR)) {
                                dades.setMissatgeFiErr(descripcio
                                    .getChildText(XMLConstants.P));
                            }
                        }
                    }

                    /* Activities - Activity - Settings */
                    if (sett) {
                        // **Codi afegit per: Pau Farr�**
                        if (activity.getChild(XMLConstants.SETTINGS)
                            .getAttributeValue(XMLConstants.MAXTIME) != null) {
                            dades.setTempsMax(Integer.valueOf(activity
                                .getChild(XMLConstants.SETTINGS)
                                .getAttributeValue(XMLConstants.MAXTIME)));
                        }
                        if (activity.getChild(XMLConstants.SETTINGS)
                            .getAttributeValue(XMLConstants.COUNTDOWNTIME) != null) {
                            dades
                                .setTimeCutdown(Boolean.valueOf(activity
                                    .getChild(XMLConstants.SETTINGS)
                                    .getAttributeValue(
                                        XMLConstants.COUNTDOWNTIME)));
                        }
                        if (activity.getChild(XMLConstants.SETTINGS)
                            .getAttributeValue(XMLConstants.COUNTDOWNACT) != null) {
                            dades.setIntentCutdown(Boolean.valueOf(activity
                                .getChild(XMLConstants.SETTINGS)
                                .getAttributeValue(XMLConstants.COUNTDOWNACT)));
                        }
                        if (activity.getChild(XMLConstants.SETTINGS)
                            .getAttributeValue(XMLConstants.MAXACTIONS) != null) {
                            dades.setIntentMax(Integer.valueOf(activity
                                .getChild(XMLConstants.SETTINGS)
                                .getAttributeValue(XMLConstants.MAXACTIONS)));
                        }

                        // ******************************
                        if (activity.getChild(XMLConstants.SETTINGS).getChild(
                            XMLConstants.HELPWINDOW) != null) {
                            dades.setMostrarSolucio(Boolean.valueOf(activity
                                .getChild(XMLConstants.SETTINGS)
                                .getChild(XMLConstants.HELPWINDOW)
                                .getAttributeValue(XMLConstants.SHOWSOLUTION)));
                        }
                        else
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
                                        .getAttributeValue(XMLConstants.ID)
                                        .equals("primary"))) {
                                    if (dades.getCellRows() == 0)
                                        dades
                                            .setCellRows(Integer.valueOf(elemCells
                                                .getAttributeValue(XMLConstants.ROWS)));
                                }
                                else if (elemCells
                                    .getAttributeValue(XMLConstants.ID) != null
                                    && elemCells.getAttributeValue(
                                        XMLConstants.ID).equals("secondary")) {
                                    if (dades.getCellRows2() == 0)
                                        dades
                                            .setCellRows2(Integer.valueOf(elemCells
                                                .getAttributeValue(XMLConstants.ROWS)));
                                }
                            }
                            if (elemCells
                                .getAttributeValue(XMLConstants.COLUMNS) != null) {

                                if (elemCells
                                    .getAttributeValue(XMLConstants.ID) == null
                                    || (elemCells
                                        .getAttributeValue(XMLConstants.ID) != null && elemCells
                                        .getAttributeValue(XMLConstants.ID)
                                        .equals("primary"))) {
                                    if (dades.getCellCols() == 0)
                                        dades
                                            .setCellCols(Integer.valueOf(elemCells
                                                .getAttributeValue(XMLConstants.COLUMNS)));
                                }
                                else if (elemCells
                                    .getAttributeValue(XMLConstants.ID) != null
                                    && elemCells.getAttributeValue(
                                        XMLConstants.ID).equals("secondary")) {
                                    if (dades.getCellCols2() == 0)
                                        dades
                                            .setCellCols2(Integer.valueOf(elemCells
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
                                    dades
                                        .setColorFG(color
                                            .getAttributeValue(XMLConstants.FOREGROUND));

                                if (color
                                    .getAttributeValue(XMLConstants.BACKGROUND) != null)
                                    dades
                                        .setColorBG(color
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
                                    celes
                                        .add(cell.getChildText(XMLConstants.P));
                                else
                                    celes.add("");
                                if (cell.getAttributeValue(XMLConstants.ID) != null)
                                    relacions.add(Integer.valueOf(cell
                                        .getAttributeValue(XMLConstants.ID)));

                            }
                        }
                        dades.setCeles(celes);
                        dades.setImages(images);
                        dades.setRelacions(relacions);
                    }

                    if (!(dades.getCellCols() > 4 || dades.getCellRows() > 5)) {
                        Parser.activitats.add(dades);
                    }
                    else
                        Parser.actSaltades = true;
                }
                else if (activity.getAttributeValue(XMLConstants.CLASS)
                    .equalsIgnoreCase(XMLConstants.FILLINBLANKS)) {

                    Dades dades = new Dades();
                    dades.setClas(activity
                        .getAttributeValue(XMLConstants.CLASS));
                    dades
                        .setName(activity.getAttributeValue(XMLConstants.NAME));

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
                            }
                            else if (incognita.getName() == "text") {
                                info = dades.new Info();
                                info.isBlank = false;
                                info.text = incognita.getText();
                            }
                            dades.addInfoToArray(i, info);
                        }
                        Parser.activitats.add(dades);
                    }

                }
                else if (activity.getAttributeValue(XMLConstants.CLASS)
                    .equalsIgnoreCase(XMLConstants.IDENTIFY)) {
                    Log.d("hh", "hh");
                    Dades dades = new Dades();
                    dades.setClas(activity
                        .getAttributeValue(XMLConstants.CLASS));
                    dades
                        .setName(activity.getAttributeValue(XMLConstants.NAME));

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
                            }
                            else if (tipus.equalsIgnoreCase(XMLConstants.FINAL)) {
                                dades.setMissatgeFi(descripcio
                                    .getChildText(XMLConstants.P));
                            }
                            else if (tipus
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
                            dades
                                .setTimeCutdown(Boolean.valueOf(activity
                                    .getChild(XMLConstants.SETTINGS)
                                    .getAttributeValue(
                                        XMLConstants.COUNTDOWNTIME)));
                        }
                        if (activity.getChild(XMLConstants.SETTINGS)
                            .getAttributeValue(XMLConstants.COUNTDOWNACT) != null) {
                            dades.setIntentCutdown(Boolean.valueOf(activity
                                .getChild(XMLConstants.SETTINGS)
                                .getAttributeValue(XMLConstants.COUNTDOWNACT)));
                        }
                        if (activity.getChild(XMLConstants.SETTINGS)
                            .getAttributeValue(XMLConstants.MAXACTIONS) != null) {
                            dades.setIntentMax(Integer.valueOf(activity
                                .getChild(XMLConstants.SETTINGS)
                                .getAttributeValue(XMLConstants.MAXACTIONS)));
                        }

                        // ******************************
                        if (activity.getChild(XMLConstants.SETTINGS).getChild(
                            XMLConstants.HELPWINDOW) != null) {
                            dades.setMostrarSolucio(Boolean.valueOf(activity
                                .getChild(XMLConstants.SETTINGS)
                                .getChild(XMLConstants.HELPWINDOW)
                                .getAttributeValue(XMLConstants.SHOWSOLUTION)));
                        }
                        else
                            dades.setMostrarSolucio(false);
                    }

                    List actsec = actdoc.getChild(XMLConstants.SECTION)
                        .getChildren();
                    ArrayList textus = new ArrayList();
                    Iterator itP = actsec.iterator();
                    int position = 0;
                    int targets = 0;
                    Log.d("abc", "entra");
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
                                Log.d("Filtre", etext.getText());
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
                                    }
                                    else {
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
                    Log.d("TEXT", "--------------------------------");

                }
                else if (activity.getAttributeValue(XMLConstants.CLASS) != null
                    && (activity.getAttributeValue(XMLConstants.CLASS)
                        .equalsIgnoreCase(XMLConstants.TXTORDERELEM))) {
                    Dades dades = new Dades();
                    dades.setClas(activity
                        .getAttributeValue(XMLConstants.CLASS));
                    dades
                        .setName(activity.getAttributeValue(XMLConstants.NAME));
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
                                            tt.add(tee);
                                            quees.add(b);
                                        }
                                    }

                                    else if (first.contains("text")) {
                                        if (te != null) {
                                            b = false;
                                            String tee = te.getText();
                                            tt.add(tee);
                                            quees.add(b);
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
                                        tt.add(tee);
                                        quees.add(b);
                                    }
                                }
                                if (iterp.hasNext()) {
                                tt.add("\n");
                                quees.add(false);
                                }
                            }
                        }
                      //tt.setSize(tt.size()-1);
                       // quees.setSize(quees.size()-1);
                        dades.setT(tt);
                        dades.setbool(quees);
                        // dades.setTextos(textos);
                    }
                    Parser.activitats.add(dades);
                }
            }
        }
        catch (Exception e) {
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
}