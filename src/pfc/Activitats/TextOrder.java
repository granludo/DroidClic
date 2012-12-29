package pfc.Activitats;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import pfc.Jclic.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import pfc.Parser.Parser;

public class TextOrder extends Activity {

    /** Contants rellevants per a poder utilitzar el parser. */
    Constants CO = Constants.getInstance();

    /** Text provinent del parser. */
    Vector<String> textOriginal = new Vector<String>();

    /** Vector que indica si un element de textOriginal es un target (true) o no
     * (false). */
    Vector<Boolean> tipusText = new Vector<Boolean>();

    TextView textView;

    private int time;

    private Timer timer;

    Chronometer cr;

    private pfc.Parser.Dades dades = new pfc.Parser.Dades();

    final Handler mHandler = new Handler();
    
    int posicioTargets[];
    
    int iniciPrimeraParaula;
    
    int finalPrimeraParaula;
    
    boolean primeraParaulaTrobada;
    
    String primeraParaula;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textorder);
        primeraParaulaTrobada = false;
        textView = (TextView) findViewById(R.id.textView1);
        textOriginal = Parser.getActivitats().elementAt(CO.activitatActual)
            .getT();
        tipusText = Parser.getActivitats().elementAt(CO.activitatActual)
            .getbool();

        // Eliminem possibles espais al principi i al final de cada element
        for (int i = 0; i < textOriginal.size(); ++i)
            textOriginal.set(i, textOriginal.get(i).trim());

        Vector<String> textBarrejat = barrejaTargets();
        String text = converteixAString(textBarrejat);
        inicialitzaTextView(text);
        inicialitzaTemporitzador();
    }

    private String converteixAString(Vector<String> textBarrejat) {
        StringBuffer convertidor = new StringBuffer();
        int ntargets = 0;
        int i, j, k;
        for (j = 0; j < tipusText.size(); j++) {
            if (tipusText.elementAt(j) == true)
                ntargets++;
        }
        final int posini[] = new int[ntargets];
        int posfi[] = new int[ntargets];
        int var = 0;
        String anterior = "";
        for (i = 0; i < textBarrejat.size(); i++) {
            int pos = convertidor.length();
            String aux = textBarrejat.elementAt(i);
            if (aux.length() == 0)
                convertidor.append("\n");
            else if (anterior.length() != 0)
                convertidor.append(" " + aux);
            else
                convertidor.append(aux);
            if (tipusText.elementAt(i)) {
                int posfinal = convertidor.length();
                posini[var] = pos;
                posfi[var] = posfinal;
                var++;

            }
            anterior = aux;
            posicioTargets = posini;
        }
        return convertidor.toString();
    }

    private void inicialitzaTextView(String text) {
        Editable str = Editable.Factory.getInstance().newEditable(text);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(str, BufferType.SPANNABLE);
        Spannable spans = (Spannable) textView.getText();

        Integer[] indices = getSpaceIndices(textView.getText().toString());
        int start = 0;
        int end = 0;
        for (int i = 0; i <= indices.length; i++) {
            ClickableSpan clickSpan = new ClickableSpan() {

                @Override
                public void onClick(View widget) {
                    CharSequence textComplet = (Spannable) textView.getText();
                    String text = textView.getText()
                        .subSequence(textView.getSelectionStart(),
                            textView.getSelectionEnd()).toString();                    
                    if (esTarget(textView.getSelectionStart(), text)) {
                        if (!primeraParaulaTrobada) {
                            iniciPrimeraParaula = textView.getSelectionStart();
                            finalPrimeraParaula = textView.getSelectionEnd();
                            primeraParaulaTrobada = true;
                            primeraParaula = text;
                        }
                        else {
                            primeraParaulaTrobada = false;
                            String nouText = "";
                            for (int i = 0; i < textComplet.length(); ++i) {
                                if (i != iniciPrimeraParaula) {
                                    if (i == textView.getSelectionStart()) {
                                        nouText = nouText.concat(primeraParaula);
                                        i += text.length() - 1;
                                    }
                                    else
                                        nouText = nouText.concat(Character.toString(textComplet.charAt(i)));
                                }
                                else {
                                    nouText = nouText.concat(text);
                                    i += primeraParaula.length() - 1;
                                }
                            }
                            inicialitzaTextView(nouText);
                        }
                    }
                }

                private boolean esTarget(int selectionStart, String text) {
                    for (int i = 0; i < posicioTargets.length; ++i) {
                        if ((selectionStart == posicioTargets[i] && i == 0) ||
                            selectionStart ==  1 + posicioTargets[i])
                            return true;
                    }
                    return false;
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                }
            };
            end = (i < indices.length ? indices[i] : spans.length());
            spans.setSpan(clickSpan, start, end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = end + 1;
        }
    }

    private void inicialitzaTemporitzador() {
        cr = (Chronometer) findViewById(R.id.chronometer1);
        cr.start();

        time = dades.getTempsMax();
        timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
                mHandler.post(handler);
            }
        }, 30 * 1000);
    }

    /** Barreja els targets del text original.
     * 
     * @return Un vector que representa el text amb els targets ens posicions
     *         aleatories. */
    private Vector<String> barrejaTargets() {
        int nTargets = 0;
        for (int i = 0; i < textOriginal.size(); i++) {
            if (tipusText.elementAt(i).equals(true)) {
                ++nTargets;
            }
        }
        int posicions[] = new int[nTargets];
        int posdes[] = new int[nTargets];
        boolean trobats[] = new boolean[nTargets];
        int p = 0;
        for (int z = 0; z < textOriginal.size(); z++) {
            if (tipusText.elementAt(z).equals(true)) {
                posicions[p] = z;
                ++p;
            }
        }
        boolean imparell = false;
        if (nTargets % 2 == 1)
            imparell = true;
        int j = 0;
        Random r = new Random();
        while (j < nTargets) {
            int a = r.nextInt(nTargets);
            if (trobats[a] == false
                && (a != j || (j == nTargets - 1 && imparell))) {
                posdes[j] = posicions[a];
                trobats[a] = true;
                if (j == nTargets - 1 && imparell)
                    posdes[j] = posicions[a];
                ++j;
            }
        }
        String taritext[] = new String[textOriginal.size()];
        String resultat[] = new String[textOriginal.size()];
        for (int y = 0; y < textOriginal.size(); y++) {
            taritext[y] = textOriginal.elementAt(y);
            if (tipusText.elementAt(y).equals(false))
                resultat[y] = textOriginal.elementAt(y);
        }
        int p2 = 0;
        for (int k = 0; k < textOriginal.size(); k++) {
            if (tipusText.elementAt(k).equals(true)) {
                int val = posdes[p2];
                resultat[k] = taritext[val];
                ++p2;
            }
        }
        int c;
        Vector<String> fin = new Vector<String>();
        for (c = 0; c < textOriginal.size(); c++) {
            String valor = resultat[c];
            fin.add(valor);
        }
        return fin;
    }

    public static Integer[] getSpaceIndices(String s) {
        int posEspai = s.indexOf(' ', 0);
        int posLinia = s.indexOf('\n', 0);
        List<Integer> indices = new ArrayList<Integer>();
        int posicio = Math.min(posEspai, posLinia);
        while (posicio != -1) {
            indices.add(posicio);
            if (posicio == posLinia)
                posLinia = s.indexOf('\n', posLinia + 1);
            else
                posEspai = s.indexOf(' ', posEspai + 1);
            posicio = Math.min(posEspai, posLinia);
        }
        return indices.toArray(new Integer[0]);
    }

    final Runnable handler = new Runnable() {

        public void run() {
            time = -1;
            cr.stop();
            System.out.println("S'HA ACABAT EL TEMPS");
        }
    };
}