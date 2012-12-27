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
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import pfc.Descompressor.Descompressor;
import pfc.Jclic.Jclic;
import pfc.Parser.Parser;

public class TextOrder extends Activity {
    private pfc.Parser.Dades dades = new pfc.Parser.Dades();
    Constants CO = Constants.getInstance();
    private int time;
    private Timer timer;
    Chronometer cr;
    String text;
    Vector<String> tt = new Vector<String>();
    Vector<Boolean> quees = new Vector<Boolean>();
    Vector<String> fin = new Vector<String>();
    boolean clic = false;
    int startt;
    int fint;
    TextView textView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textorder);

        tt = Parser.getActivitats().elementAt(CO.activitatActual).getT();
        
        quees = Parser.getActivitats().elementAt(CO.activitatActual).getbool();
        // inicialitzaTextView();
        cr = (Chronometer) findViewById(R.id.chronometer1);
        cr.start();

        time = dades.getTempsMax();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                mHandler.post(handler);
            }
        }, 30 * 1000);

        Random r = new Random();

        int i, k;
        int tamtt = tt.size();
        int tamp = 0;
        int j = 0;
        for (i = 0; i < tamtt; i++) {
            if (quees.elementAt(i).equals(true)) {
                ++tamp;
            }
        }

        int posicions[] = new int[tamp];
        int posdes[] = new int[tamp];
        boolean trobats[] = new boolean[tamp];
        int p = 0;
        int z;
        for (z = 0; z < tamtt; z++) {
            if (quees.elementAt(z).equals(true)) {
                posicions[p] = z;
                ++p;
            }

        }
        boolean imparell = false;
        if (tamp % 2 == 1)
            imparell = true;
        while (j < tamp) {
            int a = r.nextInt(tamp);
            if (trobats[a] == false && (a != j || (j == tamp - 1 && imparell))) {
                posdes[j] = posicions[a];
                trobats[a] = true;
                if (j == tamp - 1 && imparell)
                    posdes[j] = posicions[a];
                ++j;
            }
        }

        String taritext[] = new String[tamtt];
        String resultat[] = new String[tamtt];
        Boolean queson[] = new Boolean[tamtt];
        for (int y = 0; y < tamtt; y++) {
            taritext[y] = tt.elementAt(y);
            if (quees.elementAt(y).equals(false))
                resultat[y] = tt.elementAt(y);
        }
        int p2 = 0;
        for (k = 0; k < tamtt; k++) {
            if (quees.elementAt(k).equals(true)) {
                int val = posdes[p2];
                resultat[k] = taritext[val];
                ++p2;
            }
        }
        int c;
        for (c = 0; c < tamtt; c++) {
            String valor = resultat[c];
            fin.add(valor);
        }
        converteix_a_string();

    }

    final Handler mHandler = new Handler();

    final Runnable handler = new Runnable() {
        public void run() {
            time = -1;
            cr.stop();
            System.out.println("S'HA ACABAT EL TEMPS");
        }
    };

    private void converteix_a_string() {
        StringBuffer convertidor = new StringBuffer();
        textView = (TextView) findViewById(R.id.textView1);

        int ntargets = 0;
        int i, j, k;
        for (j = 0; j < quees.size(); j++) {
            if (quees.elementAt(j) == true)
                ntargets++;
        }
        final int posini[] = new int[ntargets];
        int posfi[] = new int[ntargets];
        int var = 0;
        for (i = 0; i < fin.size(); i++) {
            int pos = convertidor.length();
            String aux = fin.elementAt(i);
            convertidor.append(aux);
            if (quees.elementAt(i)) {
                int posfinal = convertidor.length();
                posini[var] = pos;
                posfi[var] = posfinal;
                var++;

            }
        }
        text = convertidor.toString();
        System.out.println(text);
        Editable str = Editable.Factory.getInstance().newEditable(text);
        // str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), posini[0],
        // posfi[0],Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        for (k = 0; k < posini.length; k++) {
            str.setSpan(new ForegroundColorSpan(android.graphics.Color.RED),
                    posini[k], posfi[k], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        // textView.setText(str);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(str, BufferType.SPANNABLE);
        Spannable spans = (Spannable) textView.getText();
        Integer[] indices = getSpaceIndices(textView.getText().toString(), ' ');
        int start = 0;
        int end = 0;
        for (i = 0; i <= indices.length; i++) {
            ClickableSpan clickSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    boolean trobat = false;
                    TextView tv = (TextView) widget;
                    int k;
                    for (k = 0; k < posini.length && !trobat; k++) {
                        if (tv.getSelectionStart() == posini[k])
                            trobat = true;
                    }

                    if (trobat) {
                        if (!clic) {
                            startt = tv.getSelectionStart();
                            fint = tv.getSelectionEnd();
                            clic = true;
                        }
                        else {
                            int z;
                            String s2 = "";
                            int posVector1 = 0;
                            int posVector2 = 0;
                            int mida = 0;
                            for (z = 0; z < fin.size(); z++) {
                                if (mida == tv.getSelectionStart())
                                    posVector1 = z;
                                else if (mida == startt)
                                    posVector2 = z;
                                mida += fin.get(z).length();
                            }
                            // System.out.println("acaba bucle");
                            Vector<String> fin2 = fin;
                            String paraula2 = fin.get(posVector2);
                            String paraula1 = fin.get(posVector1);
                            fin2.setElementAt(paraula2, posVector1);
                            fin2.setElementAt(paraula1, posVector2);
                            StringBuffer convert = new StringBuffer();

                            int y;

                            for (y = 0; y < fin2.size(); y++) {
                                String aux = fin2.elementAt(y);
                                convert.append(aux);

                            }
                            String aa = convert.toString();
                            System.out.println(aa);
                            System.out.println(text.length());
                            System.out.println(aa.length());
                            textView.setText(aa, BufferType.SPANNABLE);

                        }
                    }

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

    /*
     * private void inicialitzaTextView() { String s =
     * "Aquesta es una frase de prova"; TextView textView = (TextView)
     * findViewById(R.id.textView1);
     * textView.setMovementMethod(LinkMovementMethod.getInstance());
     * textView.setText(s, BufferType.SPANNABLE);
     * 
     * 
     * Spannable spans = (Spannable) textView.getText(); Integer[] indices =
     * getSpaceIndices(textView.getText().toString(), ' '); int start = 0; int
     * end = 0; for (int i = 0; i <= indices.length; i++) { ClickableSpan
     * clickSpan = new ClickableSpan() {
     * 
     * @Override public void onClick(View widget) { TextView tv = (TextView)
     * widget; String s = tv .getText() .subSequence(tv.getSelectionStart(),
     * tv.getSelectionEnd()).toString(); Log.d("called", s); }
     * 
     * 
     * @Override public void updateDrawState(TextPaint ds) {
     * super.updateDrawState(ds); } }; end = (i < indices.length ? indices[i] :
     * spans.length()); spans.setSpan(clickSpan, start, end,
     * Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); start = end + 1; }
     * 
     * }
     */

    public static Integer[] getSpaceIndices(String s, char c) {
        int pos = s.indexOf(c, 0);
        List<Integer> indices = new ArrayList<Integer>();
        while (pos != -1) {
            indices.add(pos);
            pos = s.indexOf(c, pos + 1);
        }
        return indices.toArray(new Integer[0]);
    }

}