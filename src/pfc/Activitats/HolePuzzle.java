/*
 * @Projecte: JClic per gPhone
 * @Autora: Miriam Pujol Benet
 * @Versio: Juny 2009
 */

package pfc.Activitats;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import java.util.Vector;

import pfc.Descompressor.Descompressor;
import pfc.Jclic.Jclic;
import pfc.Jclic.R;
import pfc.Parser.Parser;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

public class HolePuzzle extends Activity {
        private static final int MENU_ANT = 0;
        private static final int MENU_SEG = 1;
        private static final int MENU_BUIDA = 2;
        private static final int MENU_SOLUCIO = 3;
        private static final int MENU_AJUDA = 4;
        private static final int MENU_INICI = 5;
        private static final int MENU_SORTIR = 6;
        private int newWidth;
        private int newHeight;
        private int width;
        private int height;
        private Vector<BitmapDrawable> vecDraw;
        
        private int indexPos;
        private int indexBuida;
        private TextView posBuida = null;
        private Constants CO = Constants.getInstance();
        
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.exchange_hole_puzzle);
            try{
                reiniciarMenu();
                        agafarDades();
                        if(CO.imatge != null) {
                        if(CO.exemple){
                                InputStream in = this.getAssets().open(CO.imatge);
                                
                        File dst = new File("/sdcard/tmp/jclic/imatge.jpg");
                        dst.createNewFile();
                        
                                OutputStream out = new FileOutputStream(dst);
                                
                                byte[] buf = new byte[1024];
                                int len;

                                while ( (len = in.read(buf) ) > 0)
                                {
                                        out.write(buf, 0, len);
                                }
                                
                                in.close();
                                out.close(); 
                                
                                
                                posarImatges(dst.getAbsolutePath());
                        } else {
                                if(Descompressor.descompressor(CO.imatge, CO.path)){
                                                posarImatges("/sdcard/tmp/jclic/"+CO.imatge);
                                        }
                        }
                    }
                        comprobarInicial();
                    setOnClickListener();
            } catch(Exception e){
                Log.d("Error", "catch HolePuzzle: "+e);
            }
        }
        
        @TargetApi(3)
		private void reiniciarMenu(){                   
                if(CO.menu != null){
                        CO.menu.clear();
                CO.menu.add(0, MENU_ANT, 0, R.string.menu_ant);
                CO.menu.add(0, MENU_SEG, 0, R.string.menu_seg);
                CO.menu.add(0, MENU_BUIDA, 0, R.string.menu_buida);
                CO.menu.add(0, MENU_SOLUCIO, 0, R.string.menu_solucio);
                CO.menu.add(0, MENU_AJUDA, 0, R.string.menu_ajuda);
                CO.menu.add(0, MENU_INICI, 0, R.string.menu_inici);
                CO.menu.add(0, MENU_SORTIR, 0, R.string.menu_sortir);
                
                CO.menu.getItem(MENU_ANT).setIcon(android.R.drawable.ic_media_rew);
                        CO.menu.getItem(MENU_SEG).setIcon(android.R.drawable.ic_media_ff);
                        CO.menu.getItem(MENU_BUIDA).setIcon(android.R.drawable.btn_star_big_off);
                        CO.menu.getItem(MENU_SOLUCIO).setIcon(android.R.drawable.btn_star_big_off);
                        CO.menu.getItem(MENU_AJUDA).setIcon(android.R.drawable.ic_menu_help);
                        CO.menu.getItem(MENU_INICI).setIcon(android.R.drawable.ic_menu_revert);
                        CO.menu.getItem(MENU_SORTIR).setIcon(android.R.drawable.ic_menu_close_clear_cancel);
                        
                        //Configuracio del menu per mostrarSolucio
                        if(CO.mostrarSolucio) CO.menu.getItem(MENU_SOLUCIO).setEnabled(true);
                        else CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);
                        CO.menu.getItem(MENU_SOLUCIO).setTitle(R.string.menu_solucio);
                        
                        //Configuracio del menu per ant i seguent
                        CO.menu.getItem(MENU_SEG).setEnabled(true);
                        CO.menu.getItem(MENU_ANT).setEnabled(true);
                        
                        if(CO.activitatActual<1){
                                //estem a la primera activitat, pel que no podem habilitar l'anterior
                                CO.menu.getItem(MENU_ANT).setEnabled(false);
                        }
                        if(CO.activitatActual == Parser.getActivitats().size() - 1){
                                //estem a l'ultima activitat, pel que no podem habilitar el seguent
                                CO.menu.getItem(MENU_SEG).setEnabled(false);
                        }
                }
        }
                
        private void agafarDades() {
                CO.tl = (TableLayout)findViewById(R.id.tl);
            
                agafarCaselles();
        
        CO.miss = (TextView) findViewById(R.id.missatge);
        CO.missCorrectes = (TextView) findViewById(R.id.correcte);
        CO.cas1 = (TextView) findViewById(R.id.cas1);
        CO.name = (TextView) findViewById(R.id.titulo);
        
        CO.miss.setTextColor(Color.WHITE);
        CO.missCorrectes.setTextColor(Color.WHITE);
        CO.name.setTextColor(Color.WHITE);
        CO.cas1.setTextColor(Color.WHITE);
        
        CO.p1 = "<buit>";
        
        if(Parser.getActivitats().elementAt(CO.activitatActual).getName() != null)
                CO.name.setText(Parser.getActivitats().elementAt(CO.activitatActual).getName());
        else CO.name.setText("Activitat JClic");
        
        if(CO.rows == 1) CO.tl.setPadding(0,100,0,0);
        else if(CO.rows == 2) {
                CO.tl.setPadding(0,30,0,0);
        }
        else CO.tl.setPadding(0,0,0,0);
        
        if(CO.colorBG != null){
                CO.bg = Puzzle.agafarColor(CO.colorBG);
        } else CO.bg = Color.BLACK;
        
        if(CO.colorFG != null){
                CO.fg = Puzzle.agafarColor(CO.colorFG);
        } else CO.fg = Color.WHITE;
        
        for(int i = 0; i < CO.vecCaselles.size(); i++){
                if(CO.vecCaselles.elementAt(i) != null){
                        CO.vecCaselles.elementAt(i).setBackgroundColor(CO.bg);
                        CO.vecCaselles.elementAt(i).setTextColor(CO.fg);
                        CO.vecCaselles.elementAt(i).setPadding(10,15,10,10);
                        CO.vecCaselles.elementAt(i).setMaxLines(3);
                        CO.vecCaselles.elementAt(i).setText(CO.entrada.elementAt(i));
                        if(CO.imatge != null){
                                CO.vecCaselles.elementAt(i).setBackgroundColor(Color.TRANSPARENT);
                        CO.vecCaselles.elementAt(i).setTextColor(Color.TRANSPARENT);
                        }
                reestructurarCaselles(CO.vecCaselles.elementAt(i));
                }
        }
        
        Random r = new Random();
        int rand = 0;
        
        boolean trobat = false;
        
        while(!trobat){
                rand = r.nextInt(CO.sortida.size());
                
                if(CO.vecCaselles.elementAt(rand) != null){
                posBuida = CO.vecCaselles.elementAt(rand);
                indexBuida = rand;
                trobat = true;
            }
        }
        bloquejarJoc(false);
        
        posBuida.setEnabled(false);
        posBuida.setTextColor(Color.TRANSPARENT);
        posBuida.setBackgroundColor(Color.GRAY);
        }
        
        private void agafarCaselles(){
                boolean anterior = false;
                int caselles = CO.entrada.size();
                
                if(findViewById(R.id.pos1) != null && caselles > 0){
                if(CO.entrada.elementAt(0) != null){
                        CO.pos1 = (TextView) findViewById(R.id.pos1);
                CO.vecCaselles.addElement(CO.pos1);
                anterior = true;
                } else {
                        reiniciarCasella(CO.pos1);
                        CO.vecCaselles.addElement(null);
                }
        }

        if(findViewById(R.id.pos2) != null && anterior && caselles > 1) {
                if(CO.entrada.elementAt(1) != null){
                        CO.pos2 = (TextView) findViewById(R.id.pos2);
                CO.vecCaselles.addElement(CO.pos2);
                } else {
                        reiniciarCasella(CO.pos2);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos2);
                anterior = false;
        }

        if(findViewById(R.id.pos3) != null && anterior && caselles > 2){
                if(CO.entrada.elementAt(2) != null){
                        CO.pos3 = (TextView) findViewById(R.id.pos3);
                    CO.vecCaselles.addElement(CO.pos3);
                } else {
                        reiniciarCasella(CO.pos3);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos3);
                anterior = false;
        }

        if(findViewById(R.id.pos4) != null && anterior && caselles > 3){
                if(CO.entrada.elementAt(3) != null){
                        CO.pos4 = (TextView) findViewById(R.id.pos4);
                CO.vecCaselles.addElement(CO.pos4);
                } else {
                        reiniciarCasella(CO.pos4);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos4);
                anterior = false;
        }

        if(findViewById(R.id.pos5) != null && anterior && caselles > 4){
                if(CO.entrada.elementAt(4) != null){
                        CO.pos5 = (TextView) findViewById(R.id.pos5);
                CO.vecCaselles.addElement(CO.pos5);
                } else {
                        reiniciarCasella(CO.pos5);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos5);
                anterior = false;
        }

        if(findViewById(R.id.pos6) != null && anterior && caselles > 5){
                if(CO.entrada.elementAt(5) != null){
                        CO.pos6 = (TextView) findViewById(R.id.pos6);
                CO.vecCaselles.addElement(CO.pos6);
                } else {
                        reiniciarCasella(CO.pos6);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos6);
                anterior = false;
        }

        if(findViewById(R.id.pos7) != null && anterior && caselles > 6){
                if(CO.entrada.elementAt(6) != null){
                        CO.pos7 = (TextView) findViewById(R.id.pos7);
                CO.vecCaselles.addElement(CO.pos7);
                } else {
                        reiniciarCasella(CO.pos7);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos7);
                anterior = false;
        }

        if(findViewById(R.id.pos8) != null && anterior && caselles > 7){
                if(CO.entrada.elementAt(7) != null){
                        CO.pos8 = (TextView) findViewById(R.id.pos8);
                CO.vecCaselles.addElement(CO.pos8);
                } else {
                        reiniciarCasella(CO.pos8);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos8);
                anterior = false;
        }

        if(findViewById(R.id.pos9) != null && anterior && caselles > 8){
                if(CO.entrada.elementAt(8) != null){
                        CO.pos9 = (TextView) findViewById(R.id.pos9);
                CO.vecCaselles.addElement(CO.pos9);
                } else {
                        reiniciarCasella(CO.pos9);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos9);
                anterior = false;
        }

        if(findViewById(R.id.pos10) != null && anterior && caselles > 9){
                if(CO.entrada.elementAt(9) != null){
                        CO.pos10 = (TextView) findViewById(R.id.pos10);
                CO.vecCaselles.addElement(CO.pos10);
                } else {
                        reiniciarCasella(CO.pos10);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos10);
                anterior = false;
        }

        if(findViewById(R.id.pos11) != null && anterior && caselles > 10){
                if(CO.entrada.elementAt(10) != null){
                        CO.pos11 = (TextView) findViewById(R.id.pos11);
                CO.vecCaselles.addElement(CO.pos11);
                } else {
                        reiniciarCasella(CO.pos11);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos11);
                anterior = false;
        }

        if(findViewById(R.id.pos12) != null && anterior && caselles > 11){
                if(CO.entrada.elementAt(11) != null){
                        CO.pos12 = (TextView) findViewById(R.id.pos12);
                CO.vecCaselles.addElement(CO.pos12);
                } else {
                        reiniciarCasella(CO.pos12);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos12);
                anterior = false;
        }

        if(findViewById(R.id.pos13) != null && anterior && caselles > 12){
                if(CO.entrada.elementAt(12) != null){
                        CO.pos13 = (TextView) findViewById(R.id.pos13);
                CO.vecCaselles.addElement(CO.pos13);
                } else {
                        reiniciarCasella(CO.pos13);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos13);
                anterior = false;
        }

        if(findViewById(R.id.pos14) != null && anterior && caselles > 13){
                if(CO.entrada.elementAt(13) != null){
                        CO.pos14 = (TextView) findViewById(R.id.pos14);
                CO.vecCaselles.addElement(CO.pos14);
                } else {
                        reiniciarCasella(CO.pos14);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos14);
                anterior = false;
        }

        if(findViewById(R.id.pos15) != null && anterior && caselles > 14){
                if(CO.entrada.elementAt(14) != null){
                        CO.pos15 = (TextView) findViewById(R.id.pos15);
                CO.vecCaselles.addElement(CO.pos15);
                } else {
                        reiniciarCasella(CO.pos15);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos15);
                anterior = false;
        }

        if(findViewById(R.id.pos16) != null && anterior && caselles > 15){
                if(CO.entrada.elementAt(15) != null){
                        CO.pos16 = (TextView) findViewById(R.id.pos16);
                CO.vecCaselles.addElement(CO.pos16);
                } else {
                        reiniciarCasella(CO.pos16);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos16);
                anterior = false;
        }

        if(findViewById(R.id.pos17) != null && anterior && caselles > 16){
                if(CO.entrada.elementAt(16) != null){
                        CO.pos17 = (TextView) findViewById(R.id.pos17);
                CO.vecCaselles.addElement(CO.pos17);
                } else {
                        reiniciarCasella(CO.pos17);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos17);
                anterior = false;
        }

        if(findViewById(R.id.pos18) != null && anterior && caselles > 17){
                if(CO.entrada.elementAt(17) != null){
                CO.pos18 = (TextView) findViewById(R.id.pos18);
                CO.vecCaselles.addElement(CO.pos18);
                } else {
                        reiniciarCasella(CO.pos18);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos18);
                anterior = false;
        }

        if(findViewById(R.id.pos19) != null && anterior && caselles > 18){
                if(CO.entrada.elementAt(18) != null){
                        CO.pos19 = (TextView) findViewById(R.id.pos19);
                CO.vecCaselles.addElement(CO.pos19);
                } else {
                        reiniciarCasella(CO.pos19);
                        CO.vecCaselles.addElement(null);
                }
        }
        else {
                reiniciarCasella(CO.pos19);
                anterior = false;
        }

        if(findViewById(R.id.pos20) != null && anterior && caselles > 19){
                if(CO.entrada.elementAt(19) != null){
                        CO.pos20 = (TextView) findViewById(R.id.pos20);
                CO.vecCaselles.addElement(CO.pos20);
                } else {
                        reiniciarCasella(CO.pos20);
                        CO.vecCaselles.addElement(null);
                }
        } else reiniciarCasella(CO.pos20);
        }
        
        private void reestructurarCaselles(TextView pos) {
                if(CO.cols == 1){
                pos.setWidth(250);
                width = 250;
        } else if(CO.cols == 2){
                pos.setWidth(120);
                width = 120;
        } else if(CO.cols == 3){
                pos.setWidth(80);
                width = 80;
        } else {
                //cols == 4
                pos.setWidth(60);
                width = 60;
        }
                
                if(CO.rows == 1 || CO.rows == 2){
                pos.setHeight(100);
                pos.setMaxLines(4);
                height = 100;
        } else if(CO.rows == 3){
                pos.setHeight(85);
                pos.setMaxLines(3);
                height = 85;
        } else if(CO.rows == 4){
                pos.setHeight(70);
                pos.setMaxLines(2);
                height = 70;
        } else {
                //CO.rows == 5
                pos.setHeight(60);
                pos.setMaxLines(2);
                height = 60;
        }
        }

        private void comprobarInicial(){
                CO.correcte = 0;
                CO.incorrecte = 0;
                for(int i = 0; i < CO.entrada.size(); i++){
                        if(CO.entrada.elementAt(i) != null){
                                if(CO.entrada.elementAt(i).equalsIgnoreCase(CO.sortida.elementAt(i))) CO.correcte++;
                                else CO.incorrecte++;
                        }
                }
                setMissatges();
        }
        
        private void comprobarCaselles(){
                CO.correcte = 0;
                CO.incorrecte = 0;
                for(int i = 0; i < CO.vecCaselles.size(); i++){
                        if(CO.vecCaselles.elementAt(i) != null){
                                if(((String)CO.vecCaselles.elementAt(i).getText()).
                                                equalsIgnoreCase(CO.sortida.elementAt(i))) CO.correcte++;
                                else CO.incorrecte++;
                        }
                }
                setMissatges();
        }
        
        private void setOnClickListener(){
                CO.missCorrectes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(CO.casIni == CO.correcte){
                        Intent iSeg = new Intent(HolePuzzle.this, Puzzle.class);
                        startActivity(iSeg);
                        finish();
                }
            }
        });
                
                CO.miss.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(CO.casIni == CO.correcte){
                        Intent iSeg = new Intent(HolePuzzle.this, Puzzle.class);
                        startActivity(iSeg);
                        finish();
                }
            }
        });
                
                for(int i = 0; i < CO.vecCaselles.size(); i++){
                        if(CO.vecCaselles.elementAt(i) != null){
                                final TextView pos = CO.vecCaselles.elementAt(i);
                                
                                pos.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                indexPos = CO.vecCaselles.indexOf(pos);
                                
                                int dalt = indexPos - 4;
                                int baix = indexPos + 4;
                                int esq = indexPos - 1;
                                int dreta = indexPos + 1;
                                
                                if(indexBuida == dalt || indexBuida == baix){
                                        //esta a dalt o a baix, pel que segur que la puc intercanviar       
                                        swapCaselles();
                                } else if(indexBuida == esq){
                                        //esta a l'esquerra, pel que he de vigilar si no es fila anterior
                                        //nomes tindre problemes per posicions 0, 4, 8, 12 i 16, ja que llavors es fila anterior
                                        if(!(indexPos == 0 || indexPos == 4 || indexPos == 8 || 
                                                indexPos == 12 || indexPos == 16)){
                                                swapCaselles();
                                        }
                                } else if(indexBuida == dreta){
                                        //esta a la dreta, pel que he de vigilar si no es fila seguent
                                        //nomes tindre problemes per posicions 3, 7, 11, 15, 19, ja que llavors es fila anterior
                                        if(!(indexPos == 3 || indexPos == 7 || indexPos == 11 || 
                                                indexPos == 15 || indexPos == 19)){
                                                swapCaselles();
                                        }
                                }
                            }
                        });
                        }
                }
        }
        
        private void swapCaselles(){
                String aux = (String)CO.vecCaselles.elementAt(indexPos).getText();
                String aux2 = (String)CO.vecCaselles.elementAt(indexBuida).getText();
                
                //a on hi havia la casella buida, poso text, color text i background de la plena
                CO.vecCaselles.elementAt(indexBuida).setBackgroundColor(CO.bg);
                CO.vecCaselles.elementAt(indexBuida).setTextColor(CO.fg);
                CO.vecCaselles.elementAt(indexBuida).setText(aux);
                CO.vecCaselles.elementAt(indexBuida).setEnabled(true);
                
                //a on hi havia la casella plena, poso text, color text i background de la buida
                CO.vecCaselles.elementAt(indexPos).setBackgroundColor(Color.GRAY);
                CO.vecCaselles.elementAt(indexPos).setTextColor(Color.TRANSPARENT);
                CO.vecCaselles.elementAt(indexPos).setText(aux2);
                CO.vecCaselles.elementAt(indexPos).setEnabled(false);
                
                //a indexBuida tinc casella plena
                //a indexPos tinc la caella buida
                
                if(CO.imatge != null){
                        int indexEntr = CO.vecCaselles.indexOf(posBuida);
                        int indexSort = CO.sortida.indexOf(posBuida.getText());
                
                        CO.vecCaselles.elementAt(indexEntr).setBackgroundColor(Color.TRANSPARENT);
                CO.vecCaselles.elementAt(indexEntr).setTextColor(Color.TRANSPARENT);
                        
                vecDraw.elementAt(indexSort).setAlpha(250);
                
                CO.vecCaselles.elementAt(indexEntr).
                        setBackgroundDrawable(vecDraw.elementAt(indexSort));
                }
                
                posBuida = CO.vecCaselles.elementAt(indexPos);
                indexBuida = CO.vecCaselles.indexOf(posBuida);

                comprobarCaselles();
        }
        
        @TargetApi(3)
		private void setMissatges(){
                if(CO.solucioVisible || CO.buidaVisible){
                        CO.miss.setText("");
                        CO.missCorrectes.setText("");
                        CO.cas1.setText("");
                CO.p1 = "<buit>";
                CO.p2 = "<buit>";
                } else {
                        if(CO.correcte == CO.casIni){
                                //Hem acabat el joc
                                if(Parser.getActivitats().elementAt(CO.activitatActual).getMissatgeFi() != null)
                                        CO.miss.setText(Parser.getActivitats().elementAt(CO.activitatActual).getMissatgeFi());
                                else CO.miss.setText("Joc finalitzat!");
                                
                                CO.missCorrectes.setText("Prem aqu� per continuar.");
                                CO.missCorrectes.setBackgroundColor(Color.WHITE);
                                CO.missCorrectes.setTextColor(Color.BLACK);
                                
                                if(CO.imatge != null){
                                        int indexEntr = CO.vecCaselles.indexOf(posBuida);
                                        int indexSort = CO.sortida.indexOf(posBuida.getText());
                                
                                        CO.vecCaselles.elementAt(indexEntr).setBackgroundColor(Color.TRANSPARENT);
                                CO.vecCaselles.elementAt(indexEntr).setTextColor(Color.TRANSPARENT);
                                        
                                if(vecDraw.elementAt(indexSort) != null)
                                        vecDraw.elementAt(indexSort).setAlpha(250);
                                
                                CO.vecCaselles.elementAt(indexEntr).
                                        setBackgroundDrawable(vecDraw.elementAt(indexSort));
                                }
                                
                                bloquejarJoc(true);
                                if(CO.menu != null) CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);
                        } else {
                                if(Parser.getActivitats().elementAt(CO.activitatActual).getMissatgeIni() != null)
                                        CO.miss.setText(Parser.getActivitats().elementAt(CO.activitatActual).getMissatgeIni());
                                else CO.miss.setText("Comen�a el joc!");
                                CO.missCorrectes.setText("Correctes = " + CO.correcte + ", Incorrectes = " + CO.incorrecte);
                        }
                }
        }
        
        private void reiniciarCasella(TextView posicio){
                if(posicio != null){
                        posicio.setBackgroundColor(Color.TRANSPARENT);
                        posicio.setTextColor(Color.TRANSPARENT);
                        posicio.setPadding(0,0,0,0);
                        posicio.setHeight(0);
                        posicio.setWidth(0);
                        posicio.setText(null);
                }
        }
        
        private void bloquejarJoc(boolean bloquejar){
                //Fem que es bloquegi o desbloquegi l'activitat
                for(int i = 0; i < CO.vecCaselles.size(); i++){
                        if(CO.vecCaselles.elementAt(i) != null) CO.vecCaselles.elementAt(i).setEnabled(!bloquejar);
                }
        }
    
        private void posarImatges(String path){
        Bitmap bitmapOrg = BitmapFactory.decodeFile(path);
        
        int widthImage = bitmapOrg.getWidth();
        int heightImage = bitmapOrg.getHeight();
        
        newWidth = width*CO.cols;
        newHeight = height*CO.rows;
        
        float scaleWidth = ((float) newWidth) / widthImage;
        float scaleHeight = ((float) newHeight) / heightImage;
        
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0,widthImage, heightImage, matrix, true);
        
        int x = 0, y = 0;
        Bitmap btmp = null;
        BitmapDrawable btmdrw = null;
        
        int rowActual = 0;
        int colActual = -1;
        int colAnt = 0;
        
        vecDraw = new Vector<BitmapDrawable>();         
        
        for(int i = 0; i < CO.vecCaselles.size(); i++){
                if(CO.vecCaselles.elementAt(i) != null){
                        colAnt = colActual;
                        
                        if(colActual == CO.cols-1) colActual = 0;
                        else colActual++;
                        
                        if(colAnt == CO.cols-1) rowActual++;
                        
                        if(CO.cols == 1) x = CO.cols-1;
                        else x = (newWidth/CO.cols)*(colActual);
                        
                        if(CO.rows == 1) y = CO.rows-1;
                        else y = (newHeight/CO.rows)*(rowActual);
                        
                        btmp = Bitmap.createBitmap(resizedBitmap, x, y, newWidth/CO.cols, newHeight/CO.rows);
                        btmdrw = new BitmapDrawable(btmp);
                        //CO.vecCaselles.elementAt(i).setBackgroundDrawable(btmdrw);
                        vecDraw.add(btmdrw);
                } else vecDraw.add(null);
        }
        
        for(int i = 0; i < CO.vecCaselles.size(); i++){
                if(CO.vecCaselles.elementAt(i) != null && CO.vecCaselles.elementAt(i) != posBuida){
                        String text = (String)CO.vecCaselles.elementAt(i).getText();
                        int index = CO.sortida.indexOf(text);
                        CO.vecCaselles.elementAt(i).setBackgroundDrawable(vecDraw.elementAt(index));
                }
        }
        }
        
        @TargetApi(3)
		public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        CO.menu = menu;
        CO.menu.clear();
        CO.menu.add(0, MENU_ANT, 0, R.string.menu_ant);
        CO.menu.add(0, MENU_SEG, 0, R.string.menu_seg);
        CO.menu.add(0, MENU_BUIDA, 0, R.string.menu_buida);
        CO.menu.add(0, MENU_SOLUCIO, 0, R.string.menu_solucio);
        CO.menu.add(0, MENU_AJUDA, 0, R.string.menu_ajuda);
        CO.menu.add(0, MENU_INICI, 0, R.string.menu_inici);
        CO.menu.add(0, MENU_SORTIR, 0, R.string.menu_sortir);
        
        CO.menu.getItem(MENU_ANT).setIcon(android.R.drawable.ic_media_rew);
                CO.menu.getItem(MENU_SEG).setIcon(android.R.drawable.ic_media_ff);
                CO.menu.getItem(MENU_BUIDA).setIcon(android.R.drawable.btn_star_big_off);
                CO.menu.getItem(MENU_SOLUCIO).setIcon(android.R.drawable.btn_star_big_off);
                CO.menu.getItem(MENU_AJUDA).setIcon(android.R.drawable.ic_menu_help);
                CO.menu.getItem(MENU_INICI).setIcon(android.R.drawable.ic_menu_revert);
                CO.menu.getItem(MENU_SORTIR).setIcon(android.R.drawable.ic_menu_close_clear_cancel);
                
        //Configuro els botons d'anterior i seguent
        CO.menu.getItem(MENU_SEG).setEnabled(true);
                CO.menu.getItem(MENU_ANT).setEnabled(true);
                
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
        
    @TargetApi(3)
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
                Dialog ajuda = new AlertDialog.Builder(HolePuzzle.this)
                .setIcon(R.drawable.jclic_aqua)
                .setTitle("Ajuda")
                .setPositiveButton("D'acord", null)
                .setMessage("Intercanvia la casella buida per una del costat per ordenar el puzzle.")
                .create();
                ajuda.show();
                return true;
            case MENU_BUIDA:                    
                if(!CO.buidaVisible){
                        //Vull mostrar la casella buida
                        CO.vecActual = new Vector<CharSequence>();
                        for(int i = 0; i < CO.vecCaselles.size(); i++){
                        if(CO.vecCaselles.elementAt(i) != null){
                                CO.vecActual.addElement(CO.vecCaselles.elementAt(i).getText());
                                if(CO.vecCaselles.elementAt(i) == posBuida){
                                        CO.vecCaselles.elementAt(i).setTextColor(CO.fg);
                                        CO.vecCaselles.elementAt(i).setBackgroundColor(CO.bg);
                                        
                                        if(CO.imatge != null){
                                                        int indexEntr = CO.vecCaselles.indexOf(posBuida);
                                                        int indexSort = CO.sortida.indexOf(posBuida.getText());
                                                
                                                        CO.vecCaselles.elementAt(indexEntr).setBackgroundColor(Color.TRANSPARENT);
                                                CO.vecCaselles.elementAt(indexEntr).setTextColor(Color.TRANSPARENT);
                                                        
                                                vecDraw.elementAt(indexSort).setAlpha(250);
                                                
                                                CO.vecCaselles.elementAt(indexEntr).
                                                        setBackgroundDrawable(vecDraw.elementAt(indexSort));
                                                }
                                } else {
                                        CO.vecCaselles.elementAt(i).setTextColor(Color.TRANSPARENT);
                                        CO.vecCaselles.elementAt(i).setBackgroundColor(Color.GRAY);
                                }
                        } else CO.vecActual.addElement(null);
                    }
                        bloquejarJoc(true);
                        CO.buidaVisible = true;
                        setMissatges();
                        CO.menu.getItem(MENU_BUIDA).setTitle(R.string.menu_in_solucio);
                        CO.menu.getItem(MENU_ANT).setEnabled(false);
                        CO.menu.getItem(MENU_SEG).setEnabled(false);
                        CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);
                } else {
                        //Estic mostrant la casella buida i vull continuar
                        for(int i = 0; i < CO.vecCaselles.size(); i++){
                        if(CO.vecCaselles.elementAt(i) != null){
                                CO.vecCaselles.elementAt(i).setText(CO.vecActual.elementAt(i));
                                        CO.vecCaselles.elementAt(i).setTextColor(CO.fg);
                                CO.vecCaselles.elementAt(i).setBackgroundColor(CO.bg);
                                
                                if(CO.imatge != null){
                                                int indexSort = CO.sortida.indexOf(CO.vecCaselles.elementAt(i).getText());
                                        
                                                CO.vecCaselles.elementAt(i).setBackgroundColor(Color.TRANSPARENT);
                                        CO.vecCaselles.elementAt(i).setTextColor(Color.TRANSPARENT);
                                                
                                        vecDraw.elementAt(indexSort).setAlpha(250);
                                        
                                        CO.vecCaselles.elementAt(i).
                                                setBackgroundDrawable(vecDraw.elementAt(indexSort));
                                        }
                        }
                    }
                        bloquejarJoc(false);
                        
                        posBuida.setTextColor(Color.TRANSPARENT);
                        posBuida.setBackgroundColor(Color.GRAY);
                        posBuida.setEnabled(false);
                        
                        CO.buidaVisible = false;
                        setMissatges();
                        CO.menu.getItem(MENU_BUIDA).setTitle(R.string.menu_buida);

                        if(CO.mostrarSolucio) CO.menu.getItem(MENU_SOLUCIO).setEnabled(true);
                        else CO.menu.getItem(MENU_SOLUCIO).setEnabled(false);
                        
                        //Configuracio del menu per ant i seguent
                                if(CO.activitatActual<1){
                                        //estem a la primera activitat, pel que nomes habilitem seguent
                                        CO.menu.getItem(MENU_SEG).setEnabled(true);
                                } else if(CO.activitatActual == Parser.getActivitats().size() - 1){
                                        //estem a l'ultima activitat, pel que no podem habilitar el seguent
                                        CO.menu.getItem(MENU_ANT).setEnabled(true);
                                } else {
                                        CO.menu.getItem(MENU_SEG).setEnabled(true);
                                        CO.menu.getItem(MENU_ANT).setEnabled(true);
                                }
                }
                return true;
            case MENU_SOLUCIO:
                if(!CO.solucioVisible){
                        //Vull mostrar la solucio
                        CO.vecActual = new Vector<CharSequence>();
                        for(int i = 0; i < CO.vecCaselles.size(); i++){
                        if(CO.vecCaselles.elementAt(i) != null){
                                CO.vecActual.addElement(CO.vecCaselles.elementAt(i).getText());
                                CO.vecCaselles.elementAt(i).setText(CO.sortida.elementAt(i));
                                        CO.vecCaselles.elementAt(i).setTextColor(CO.fg);
                                CO.vecCaselles.elementAt(i).setBackgroundColor(CO.bg);
                                
                                if(CO.imatge != null){
                                                int indexSort = CO.sortida.indexOf(CO.vecCaselles.elementAt(i).getText());
                                        
                                                CO.vecCaselles.elementAt(i).setBackgroundColor(Color.TRANSPARENT);
                                        CO.vecCaselles.elementAt(i).setTextColor(Color.TRANSPARENT);
                                                
                                        vecDraw.elementAt(indexSort).setAlpha(250);
                                        
                                        CO.vecCaselles.elementAt(i).
                                                setBackgroundDrawable(vecDraw.elementAt(indexSort));
                                        }
                        } else CO.vecActual.addElement(null);
                    }
                        bloquejarJoc(true);
                        CO.solucioVisible = true;
                        setMissatges();
                        CO.menu.getItem(MENU_SOLUCIO).setTitle(R.string.menu_in_solucio);
                        CO.menu.getItem(MENU_ANT).setEnabled(false);
                        CO.menu.getItem(MENU_SEG).setEnabled(false);
                        CO.menu.getItem(MENU_BUIDA).setEnabled(false);
                } else {
                        //Estic mostrant la solucio i vull continuar
                        for(int i = 0; i < CO.vecCaselles.size(); i++){
                        if(CO.vecCaselles.elementAt(i) != null){
                                CO.vecCaselles.elementAt(i).setText(CO.vecActual.elementAt(i));
                                CO.vecCaselles.elementAt(i).setTextColor(CO.fg);
                                CO.vecCaselles.elementAt(i).setBackgroundColor(CO.bg);
                                
                                if(CO.imatge != null){
                                                int indexSort = CO.sortida.indexOf(CO.vecCaselles.elementAt(i).getText());
                                        
                                                CO.vecCaselles.elementAt(i).setBackgroundColor(Color.TRANSPARENT);
                                        CO.vecCaselles.elementAt(i).setTextColor(Color.TRANSPARENT);
                                                
                                        vecDraw.elementAt(indexSort).setAlpha(250);
                                        
                                        CO.vecCaselles.elementAt(i).
                                                setBackgroundDrawable(vecDraw.elementAt(indexSort));
                                        }
                        }
                    }
                        bloquejarJoc(false);
                        
                        posBuida.setTextColor(Color.TRANSPARENT);
                        posBuida.setBackgroundColor(Color.GRAY);
                        posBuida.setEnabled(false);
                        
                        CO.solucioVisible = false;
                        setMissatges();
                        CO.menu.getItem(MENU_SOLUCIO).setTitle(R.string.menu_solucio);
                        CO.menu.getItem(MENU_BUIDA).setEnabled(true);
                        
                        //Configuracio del menu per ant i seguent
                                if(CO.activitatActual<1){
                                        //estem a la primera activitat, pel que nomes habilitem seguent
                                        CO.menu.getItem(MENU_SEG).setEnabled(true);
                                } else if(CO.activitatActual == Parser.getActivitats().size() - 1){
                                        //estem a l'ultima activitat, pel que no podem habilitar el seguent
                                        CO.menu.getItem(MENU_ANT).setEnabled(true);
                                } else {
                                        CO.menu.getItem(MENU_SEG).setEnabled(true);
                                        CO.menu.getItem(MENU_ANT).setEnabled(true);
                                }
                }
                return true;
            case MENU_SORTIR:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.jclic_aqua);
                builder.setMessage("Est�s segur de que vols sortir?")
                       .setCancelable(false)
                       .setPositiveButton("S�", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                                HolePuzzle.this.finish();
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
}