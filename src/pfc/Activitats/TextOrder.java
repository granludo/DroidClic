package pfc.Activitats;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import pfc.Jclic.Jclic;
import pfc.Jclic.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import pfc.Parser.Dades;
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

    //private Timer timer;

    Chronometer cr;

    private pfc.Parser.Dades dades = new pfc.Parser.Dades();

    final Handler mHandler = new Handler();

    // int posicioTargets[];

    // int posicioFinalTargets[];

    int iniciPrimeraParaula;

    int finalPrimeraParaula;

    boolean primeraParaulaTrobada;

    String primeraParaula;

    ArrayList<Posicio> poss = new ArrayList<Posicio>();

    Paraules paraules;
    private static final int MENU_ANT = 0;
	private static final int MENU_SEG = 1;
	private static final int MENU_SOLUCIO = 2;
	private static final int MENU_AJUDA = 3;
	private static final int MENU_INICI = 4;
	private static final int MENU_SORTIR = 5;



	private ArrayList<Dades.Info> arrayDades;

	Sounds sound;
	private int maxTime = 0;
	private int maxIntents =  0;
	private boolean TimeCountDown = false;
	private boolean IntentCountDown = false;
	int encerts = 0;
	int contador = 10; //Comptador per als intents.
	int contadorTemps = 0; //Comptador per al temps.
	private CountDownTimer timer;
	private ProgressBar tiempo;
	
	private void agafarDadesParser(){		
		if(CO.activitatActual < Parser.getActivitats().size()-1){
			//podem agafar l'activitat
			CO.activitatActual++;
			CO.solucioVisible = false;


			this.arrayDades = (ArrayList<Dades.Info>) Parser.getActivitats().elementAt(CO.activitatActual).getArrayFillInBlanks();

			CO.InfoArray = Parser.getActivitats().elementAt(CO.activitatActual).getArrayFillInBlanks();


			int maxTime = Parser.getActivitats().elementAt(CO.activitatActual).getTempsMax();
			maxTime = 60;
			tiempo = (ProgressBar) findViewById(R.id.progressTime);
			tiempo.setMax(maxTime);
			tiempo.setProgress(0);
			if (maxTime != 0) {
				timer = new CountDownTimer(maxTime * 1000, 1000) {
					@Override
					public void onFinish() {
						contadorTemps++;
						/*tiempo.setText(Integer
								.toString(maxTime - contadorTemps));*/
						tiempo.setProgress(contadorTemps);
						Log.d("id","acaba el temporizador");
						
						Dialog finalitzat = new AlertDialog.Builder(TextOrder.this)
						.setIcon(R.drawable.jclic_aqua)
						.setTitle("Atenci�")
						.setPositiveButton("D'acord", new OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent i = new Intent(TextOrder.this, Jclic.class);
								startActivity(i);
								finish();
							}
						})
						.setMessage("S'acabat el temps.")
						.create();
						finalitzat.show();
						
						//setMissatges();
					}

					@Override
					public void onTick(long arg0) {
						contadorTemps++;
						/*tiempo.setText(Integer
								.toString(maxTime - contadorTemps));*/
						tiempo.setProgress(contadorTemps);

						//setMissatges();
					}
				}.start();
			}



		} else{
			Dialog finalitzat = new AlertDialog.Builder(this)
			.setIcon(R.drawable.jclic_aqua)
			.setTitle("Atenci�")
			.setPositiveButton("D'acord", null)
			.setMessage("Ja no queden m�s activitats.")
			.create();
			finalitzat.show();
		}

	}
	
	private void reiniciarMenu(){			
		if(CO.menu != null){
			CO.menu.clear();
			CO.menu.add(0, MENU_ANT, 0, R.string.menu_ant);
			CO.menu.add(0, MENU_SEG, 0, R.string.menu_seg);
			CO.menu.add(0, MENU_SOLUCIO, 0, R.string.menu_solucio);
			CO.menu.add(0, MENU_AJUDA, 0, R.string.menu_ajuda);
			CO.menu.add(0, MENU_INICI, 0, R.string.menu_inici);
			CO.menu.add(0, MENU_SORTIR, 0, R.string.menu_sortir);

			CO.menu.getItem(MENU_ANT).setIcon(android.R.drawable.ic_media_rew);
			CO.menu.getItem(MENU_SEG).setIcon(android.R.drawable.ic_media_ff);
			CO.menu.getItem(MENU_SOLUCIO).setIcon(android.R.drawable.btn_star_big_off);
			CO.menu.getItem(MENU_AJUDA).setIcon(android.R.drawable.ic_menu_help);
			CO.menu.getItem(MENU_INICI).setIcon(android.R.drawable.ic_menu_revert);
			CO.menu.getItem(MENU_SORTIR).setIcon(android.R.drawable.ic_menu_close_clear_cancel);

			//Configuracio del menu per mostrarSolucio-> es un boolean
			if(CO.mostrarSolucio) CO.menu.getItem(MENU_SOLUCIO).setEnabled(true);
			else CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);
			CO.menu.getItem(MENU_SOLUCIO).setTitle(R.string.menu_solucio);

			//Configuracio del menu per ant i seguent
			CO.menu.getItem(MENU_SEG).setEnabled(true);
			CO.menu.getItem(MENU_ANT).setEnabled(true);

			if(CO.activitatActual < 1){
				//estem a la primera activitat, pel que no podem habilitar l'anterior
				CO.menu.getItem(MENU_ANT).setEnabled(false);
			}
			if(CO.activitatActual == Parser.getActivitats().size() - 1){
				//estem a l'ultima activitat, pel que no podem habilitar el seguent
				CO.menu.getItem(MENU_SEG).setEnabled(false);
			}
		}
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		CO.menu = menu;
		CO.menu.clear();
		CO.menu.add(0, MENU_ANT, 0, R.string.menu_ant);
		CO.menu.add(0, MENU_SEG, 0, R.string.menu_seg);
		CO.menu.add(0, MENU_SOLUCIO, 0, R.string.menu_solucio);
		CO.menu.add(0, MENU_AJUDA, 0, R.string.menu_ajuda);
		CO.menu.add(0, MENU_INICI, 0, R.string.menu_inici);
		CO.menu.add(0, MENU_SORTIR, 0, R.string.menu_sortir);

		//Configuro els botons d'anterior i seguent
		CO.menu.getItem(MENU_SEG).setEnabled(true);
		CO.menu.getItem(MENU_ANT).setEnabled(true);

		CO.menu.getItem(MENU_ANT).setIcon(android.R.drawable.ic_media_rew);
		CO.menu.getItem(MENU_SEG).setIcon(android.R.drawable.ic_media_ff);
		CO.menu.getItem(MENU_SOLUCIO).setIcon(android.R.drawable.btn_star_big_off);
		CO.menu.getItem(MENU_AJUDA).setIcon(android.R.drawable.ic_menu_help);
		CO.menu.getItem(MENU_INICI).setIcon(android.R.drawable.ic_menu_revert);
		CO.menu.getItem(MENU_SORTIR).setIcon(android.R.drawable.ic_menu_close_clear_cancel);

		if(CO.activitatActual<1){
			//estem a la primera activitat, pel que no podem habilitar l'anterior
			CO.menu.getItem(MENU_ANT).setEnabled(false);
		}
		if(CO.activitatActual == Parser.getActivitats().size() - 1){
			//estem a l'ultima activitat, pel que no podem habilitar el seguent
			CO.menu.getItem(MENU_SEG).setEnabled(false);
		}

		if(CO.mostrarSolucio) CO.menu.getItem(MENU_SOLUCIO).setEnabled(true);
		else CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);
		return true;
	}
	
	private void setMissatges() {
		if(CO.solucioVisible){
			CO.miss.setText("");
			CO.missCorrectes.setText("");
			CO.cas1.setText("");
			CO.p1 = "<buit>";
			CO.p2 = "<buit>";
		} else {
			if((maxIntents != 0 && maxIntents == contador && CO.correcte!=CO.casIni)||contadorTemps == maxTime && maxTime!=0){
				//fallem per intents o per temps
				sound.playFinished_error();
				if(Parser.getActivitats().elementAt(CO.activitatActual).getMissatgeFi() != null)
					CO.miss.setText(Parser.getActivitats().elementAt(CO.activitatActual).getMissatgeFi());
				else CO.miss.setText("Superat els intents m�xims");
				if(maxTime!=0)timer.cancel();
				CO.missCorrectes.setText("Prem aqu� per continuar.");
				CO.missCorrectes.setBackgroundColor(Color.WHITE);
				CO.missCorrectes.setTextColor(Color.BLACK);

				//bloquejarJoc(true);
				if(CO.menu != null) CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);
			}
			else if(CO.correcte == CO.vecCaselles.size()){
				//Hem acabat el joc
				if(maxTime!=0)timer.cancel();
				sound.playFinished_ok();
				if(Parser.getActivitats().elementAt(CO.activitatActual).getMissatgeFi() != null)
					CO.miss.setText(Parser.getActivitats().elementAt(CO.activitatActual).getMissatgeFi());
				else CO.miss.setText("Joc finalitzat!");

				CO.missCorrectes.setText("Prem aqu� per continuar.");
				CO.missCorrectes.setBackgroundColor(Color.WHITE);
				CO.missCorrectes.setTextColor(Color.BLACK);

				//bloquejarJoc(true);
				if(CO.menu != null) CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);

			} 
			else {
				if(Parser.getActivitats().elementAt(CO.activitatActual).getMissatgeIni() != null)
					CO.miss.setText(Parser.getActivitats().elementAt(CO.activitatActual).getMissatgeIni());
				else CO.miss.setText("Comen�a el joc!");
				int displayedIntents;
				if(IntentCountDown && maxIntents != 0){
					displayedIntents = maxIntents - contador;
				}
				else displayedIntents=contador;
				int displayedTime;
				if(TimeCountDown && maxTime != 0){
					displayedTime = maxTime - contadorTemps;
				}
				else displayedTime=contadorTemps;
				CO.missCorrectes.setText("C = " + CO.correcte + ", In ="+displayedIntents + ", T ="+displayedTime);

			}
		}
	}

	@SuppressLint({ "NewApi", "NewApi", "NewApi", "NewApi", "NewApi" })
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ANT:
			CO.activitatActual = CO.activitatActual - 2;
			Intent iAnt = new Intent(this, Puzzle.class);
			startActivity(iAnt);
			finish();
			return true;
		case MENU_SEG:
			Intent iSeg = new Intent(this, Puzzle.class);
			startActivity(iSeg);
			finish();
			return true;
		case MENU_AJUDA:
			Dialog ajuda = new AlertDialog.Builder(TextOrder.this)
			.setIcon(R.drawable.jclic_aqua)
			.setTitle("Ajuda")
			.setPositiveButton("D'acord", null)
			.setMessage("Posa les paraules en l'ordre correcte")
			.create();
			ajuda.show();
			return true;
		case MENU_SORTIR:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.jclic_aqua);
			builder.setMessage("Est�s segur de que vols sortir?")
			.setCancelable(false)
			.setPositiveButton("S�", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					TextOrder.this.finish();
				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
			return true;
		case MENU_INICI:
			Intent i = new Intent(this, Jclic.class);
			startActivity(i);
			finish();
			return true;
		}
		return false;
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textorder);
        Button b1 = (Button) findViewById(R.id.buttonMenu);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openOptionsMenu();
			}
		});
		TextView intentos = (TextView) findViewById(R.id.editIntentos);
		TextView aciertos = (TextView) findViewById(R.id.editAciertos);
		intentos.setText(String.valueOf(contador));
		aciertos.setText(String.valueOf(encerts));
		this.agafarDadesParser();

		maxTime = Parser.getActivitats().get(CO.activitatActual).getTempsMax();
		maxIntents =  Parser.getActivitats().get(CO.activitatActual).getIntentMax();
		TimeCountDown =  Parser.getActivitats().get(CO.activitatActual).getTimeCutDown();
		IntentCountDown =  Parser.getActivitats().get(CO.activitatActual).getIntentCutdown();
        primeraParaulaTrobada = false;
        paraules = new Paraules();
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
        inicialitzaPosicioTargets(textBarrejat);
        inicialitzaTextView(text);
        //inicialitzaTemporitzador();
    }

    private void inicialitzaPosicioTargets(Vector<String> textBarrejat) {
        int sum = 0;
        int j = 0;
        for (int i = 0; i < tipusText.size(); ++i) {
            if (tipusText.get(i)) {
                Posicio p = new Posicio();
                p.posicioInicial = sum;
                p.posicioFinal = sum + textBarrejat.get(i).length() - 1;
                poss.add(p);
                // posicioTargets[j] = sum;
                // posicioFinalTargets[j] = sum + textBarrejat.get(i).length();
                ++j;
            }
            sum += textBarrejat.get(i).length();
            if (textBarrejat.get(i).length() != 0)
                ++sum;
        }

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
        }
        // posicioTargets = new int[posini.length];
        // posicioFinalTargets = new int[posini.length];
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
                    CharSequence textComplet = textView.getText();
                    String text = textView
                        .getText()
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
                            int targets = 0;
                            for (int i = 0; i < textComplet.length(); ++i) {
                                if (i != iniciPrimeraParaula) {
                                    if (i == textView.getSelectionStart()) {
                                        nouText = nouText
                                            .concat(primeraParaula);
                                        i += text.length() - 1;
                                    }
                                    else {
                                        nouText = nouText.concat(Character
                                            .toString(textComplet.charAt(i)));
                                    }
                                }
                                else {
                                    nouText = nouText.concat(text);
                                    i += primeraParaula.length() - 1;
                                }
                            }
                            if (Math.min(paraules.indexSegonaParaula,
                                paraules.indexPrimeraParaula) == paraules.indexPrimeraParaula)
                                actualitzaPosicions(
                                    paraules.indexPrimeraParaula,
                                    paraules.indexSegonaParaula,
                                    text.length(), primeraParaula.length());
                            else
                                actualitzaPosicions(
                                    paraules.indexSegonaParaula,
                                    paraules.indexPrimeraParaula,
                                    primeraParaula.length(), text.length());

                            inicialitzaTextView(nouText);
                        }
                    }
                }

                /**
                 * Actualitza les posicions dels targets.
                 * @param indexInicial Index del primer target a partir del qual s'han d'actualitzar les posicions.
                 * @param indexFinal Index del segon target fins al qual s'han d'actualitzar les posicions.
                 * @param sizePrimeraParaula Mida de la paraula que va al primer index.
                 * @param sizeSegonaParaula Mida de la paraula que va al segon index.
                 */
                private void actualitzaPosicions(int indexInicial,
                    int indexFinal, int sizePrimeraParaula,
                    int sizeSegonaParaula) {
                    Posicio pIni = poss.get(indexInicial);
                    pIni.posicioFinal =  pIni.posicioInicial + sizePrimeraParaula - 1;
                    poss.set(indexInicial, pIni);
                    for (int i = indexInicial + 1; i < indexFinal; ++i) {
                        Posicio p = poss.get(i);
                        System.out.println("MIDA1: " + sizePrimeraParaula);
                        System.out.println("MIDA2: " + sizeSegonaParaula);
                        p.posicioInicial += sizePrimeraParaula - sizeSegonaParaula;
                        p.posicioFinal += sizePrimeraParaula - sizeSegonaParaula;
                        poss.set(i, p);
                    }
                    Posicio pFi = poss.get(indexFinal);
                    pFi.posicioInicial += sizePrimeraParaula - sizeSegonaParaula;
                    pFi.posicioFinal = pFi.posicioInicial + sizeSegonaParaula - 1;
                    poss.set(indexFinal, pFi);
                }

                private boolean esTarget(int selectionStart, String text) {
                    for (int i = 0; i < poss.size(); ++i) {
                        if (selectionStart == poss.get(i).posicioInicial) {
                            if (!primeraParaulaTrobada)
                                paraules.indexPrimeraParaula = i;
                            else
                                paraules.indexSegonaParaula = i;
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setColor(Color.WHITE); // Aquest es el color per les
                    // paraules que no siguin target.
                    ds.setUnderlineText(false);
                }
            };
            end = (i < indices.length ? indices[i] : spans.length());
            spans.setSpan(clickSpan, start, end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            for (int j = 0; j < poss.size(); ++j) {
                spans.setSpan(new ForegroundColorSpan(Color.BLUE),
                    poss.get(j).posicioInicial, poss.get(j).posicioFinal + 1,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            start = end + 1;
        }
    }

    /*private void inicialitzaTemporitzador() {
        cr = (Chronometer) findViewById(R.id.chronometer1);
        cr.start();

        time = dades.getTempsMax();
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                mHandler.post(handler);
            }
        }, 30 * 1000);
    }*/

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

        @Override
        public void run() {
            time = -1;
            cr.stop();
            System.out.println("S'HA ACABAT EL TEMPS");
        }
    };

    class Posicio {

        public int posicioInicial;

        public int posicioFinal;
    }

    class Paraules {

        public int indexPrimeraParaula;

        public int indexSegonaParaula;
    }
}