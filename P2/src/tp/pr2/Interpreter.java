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
	
	public Interpreter(){
		this.unknown = new Instruction(Action.UNKNOWN);
		this.turnUnknown = new Instruction(Action.TURN, Rotation.UNKNONW);
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
		
		if(words.equalsIgnoreCase("move"))
			return generateMove(st);
		if(words.equalsIgnoreCase("turn"))
			return generateTurn(st);
		if(words.equalsIgnoreCase("pick"))
			return generatePick(st);
		if(words.equalsIgnoreCase("pick"))
			return generateHelp(st);
		if(words.equalsIgnoreCase("quit"))
			return generateQuit(st);
		if(words.equalsIgnoreCase("scan"))
			return generateScan(st);
		if(words.equalsIgnoreCase("operate"))
			return generateOperate(st);
		else
			return unknown;
		
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
			else if (token2.equalsIgnoreCase("left")) 
				return new Instruction(Action.TURN, Rotation.LEFT);
			else if (token2.equalsIgnoreCase("right")) 
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
			return unknown;
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
