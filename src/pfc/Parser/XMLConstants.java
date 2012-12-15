/*
 * @Projecte: JClic per gPhone
 * @Autora: Miriam Pujol Benet
 * @Versio: Juny 2009
 */

package pfc.Parser;

public class XMLConstants {
	//tag
	public static final String ACTIVITIES = "activities";
	public static final String ACTIVITY = "activity";
	public static final String DESCRIPTION = "description";
	public static final String MESSAGES = "messages";
	public static final String CELL = "cell";
	public static final String SETTINGS = "settings";
	public static final String CELLS = "cells";
	
	public static final String ID = "id";
	public static final String INVERSE = "inverse";
	
	//Valors dins dels tag
	public static final String EXCHANGEPUZZ = "@puzzles.ExchangePuzzle";
	public static final String HOLEPUZZ = "@puzzles.HolePuzzle";
	public static final String DOUBLEPUZZ = "@puzzles.DoublePuzzle";
	//******Altres activitats**********
	public static final String MEMORYGAME = "@memory.MemoryGame";
	public static final String SIMPLEASSOC = "@associations.SimpleAssociation";
	public static final String COMPLEXASSOC = "@associations.ComplexAssociation";
	public static final String PANEXPLORE = "@panels.Explore";
	public static final String PANIDENTIFY = "@panels.Identify";
	public static final String PANINFORMATION = "@panels.InformationScreen";
	public static final String TXTCOMPLETE = "@text.Complete";
	public static final String TXTWRITEANSWER = "@text.WrittenAnswer";
	public static final String TXTFILLBLANKS = "@text.FillInBlanks";
	public static final String TXTIDENTIFY = "@text.Identify";
	public static final String TXTORDERELEM = "@text.Order";
	public static final String TGRIDWORDSEARCH = "@textGrid.WordSearch";
	public static final String TGRIDCROSSWORD = "@textGrid.CrossWord";
	//***********************************
	
	public static final String P = "p";
	public static final String CLASS = "class";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	
	public static final String INITIAL = "initial";
	public static final String FINAL = "final";
	public static final String FINALERROR = "finalError";
	
	//altres dins de settings
	public static final String ROWS = "rows";
	public static final String COLUMNS = "cols";
	public static final String IMAGE = "image";
	public static final String BORDER = "border";
	public static final String COLOR = "color";
	public static final String FOREGROUND = "foreground";
	public static final String BACKGROUND = "background";

	public static final String STYLE = "style";
	public static final String HELPWINDOW = "helpWindow";
	public static final String SHOWSOLUTION = "showSolution";
	
	public static final String MAXTIME = "maxTime";
	public static final String COUNTDOWNTIME = "countDownTime";
	public static final String COUNTDOWNACT = "countDownActions";
	public static final String MAXACTIONS = "maxActions";
}
