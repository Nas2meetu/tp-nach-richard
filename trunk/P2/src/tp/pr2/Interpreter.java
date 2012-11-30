package tp.pr2;
import static tp.pr2.Constants.*;

import java.util.StringTokenizer;

/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 1
*
*/

public class Interpreter {

	
	private Instruction unknown;
	private Instruction turnUnknown;
	private Instruction pickUnknown;
	
	public Interpreter(){
		this.unknown = new Instruction(Action.UNKNOWN);
		this.turnUnknown = new Instruction(Action.TURN, Rotation.UNKNONW);
		this.pickUnknown = new Instruction(Action.PICK, "");
 	}
	
	/**
	 * 
	 * @param prompt is introduced by player 
	 * @return Instruction to interpreter by Robot
	 * 
	 */
	
	public Instruction generateInstruction(String prompt) {
		StringTokenizer st = new StringTokenizer(prompt, " ");
		
		String words = st.nextToken();
		
		if(words.equals("MOVE"))
			return generateMove(st);
		if(words.equals("TURN"))
			return generateTurn(st);
		if(words.equals("PICK"))
			return generatePick(st);
		if(words.equals("HELP"))
			return generateHelp(st);
		if(words.equals("QUIT"))
			return generateQuit(st);
		if(words.equals("SCAN"))
			return generateScan(st);
		if(words.equals("OPERATE"))
			return generateOperate(st);
		else
			return unknown;
		
	/*	if(words.equals("move"))
			return generateMove(st);
		if(words.equalsIgnoreCase("turn"))
			return generateTurn(st);
		if(words.equalsIgnoreCase("pick"))
			return generatePick(st);
		if(words.equalsIgnoreCase("help"))
			return generateHelp(st);
		if(words.equalsIgnoreCase("quit"))
			return generateQuit(st);
		if(words.equalsIgnoreCase("scan"))
			return generateScan(st);
		if(words.equalsIgnoreCase("operate"))
			return generateOperate(st);
		else
			return unknown;
		*/
	}
	
	private Instruction generateMove(StringTokenizer st) {
		if(!st.hasMoreTokens())
			return new Instruction(Action.MOVE);
		else
			return unknown;
	}
	
	private Instruction generateTurn(StringTokenizer st) {
		if (st.hasMoreTokens()){
			String token2 = st.nextToken();
			if (st.hasMoreTokens())
				return unknown; 
			else if (token2.equals("LEFT")) 
				return new Instruction(Action.TURN, Rotation.LEFT);
			else if (token2.equals("RIGHT")) 
				return new Instruction(Action.TURN, Rotation.RIGHT);
			else 
				return turnUnknown;

		} else return unknown;
	}
	
	private Instruction generatePick(StringTokenizer st) {
		if(st.hasMoreTokens()){
			String token2 =  st.nextToken();
			if(st.hasMoreTokens()){
				return unknown;
			}
			return new Instruction(Action.PICK, token2);
		}
		else
			return pickUnknown;
	}
	

	private Instruction generateHelp(StringTokenizer st) {
		if(!st.hasMoreTokens())
			return new Instruction(Action.HELP);
		else
			return unknown;
	}
	
	private Instruction generateQuit(StringTokenizer st) {
		if(!st.hasMoreTokens())
			return new Instruction(Action.QUIT);
		else
			return unknown;
	}
	
	private Instruction generateScan(StringTokenizer st) {
		if(st.hasMoreTokens()){
			String token2 =  st.nextToken();
			if(st.hasMoreTokens()){
				return unknown;
			}
			return new Instruction(Action.SCAN, token2);
		}
		else
			return new Instruction(Action.SCAN);
	}
	private Instruction generateOperate(StringTokenizer st) {
		if(st.hasMoreTokens()){
			String token2 =  st.nextToken();
			if(st.hasMoreTokens()){
				return unknown;
			}
			return new Instruction(Action.OPERATE, token2);
		}
		else
			return unknown;
	}
		
	/**
	 *
	 * @return String override with help message
	 */

	public String interpreterHelp() {
		return HELP + LINE_SEPARATOR;
	}

}
