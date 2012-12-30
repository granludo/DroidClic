package pfc.Activitats;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public  class  EditTextFBlanks  extends  EditText  {
	private String userInput = null;

    public  EditTextFBlanks ( Context contexto, String s )  { 
        super ( contexto ); 
        userInput = s;
        init (); 
    }

    public  EditTextFBlanks ( Context contexto ,  AttributeSet attrs )  { 
        super ( contexto , attrs ); 
        init (); 
    }

    public EditTextFBlanks(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private  void init ()  { 
            // configurar el filtro de entrada aqu’ 
    } 
    
    public String getUserInput(){
    	return userInput;
    }
    
}