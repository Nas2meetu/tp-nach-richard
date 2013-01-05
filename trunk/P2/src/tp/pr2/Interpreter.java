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

	
	private static Instruction unknown;
	
	
	/**
	 * 
	 * Verified if instruction's syntax is correct.
	 * 
	 * @param prompt is introduced by player 
	 * @return instruction is a instruction to interpreter by Robot
	 * 
	 */
	
	public static Instruction generateInstruction(String prompt) {
		StringTokenizer st = new StringTokenizer(prompt, " ");
		
		String words = st.nextToken().toUpperCase();
		
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
			return new Instruction(Action.UNKNOWN);
		
	
	}
	
	/**
	 * 
	 * Generate an action of type MOVE
	 * 
	 * @param st is a possible instruction introduced by player 
	 * @return instruction is a instruction of type MOVE
	 */
	
	private static Instruction generateMove(StringTokenizer st) {
		if(!st.hasMoreTokens())
			return new Instruction(Action.MOVE);
		else
			return unknown;
	}
	
	/**
	 * 
	 * Verified if TURN instruction syntax is correct.
	 * 
	 * @param st is an instruction introduced by player 
	 * @return instruction is a instruction of type TURN
	 */
	
	private static Instruction generateTurn(StringTokenizer st) {
		if (st.hasMoreTokens()){
			String token2 = st.nextToken().toUpperCase();
			if (st.hasMoreTokens())
				return unknown; 
			else if (token2.equals("LEFT")) 
				return new Instruction(Action.TURN, Rotation.LEFT);
			else if (token2.equals("RIGHT")) 
				return new Instruction(Action.TURN, Rotation.RIGHT);
			else 
				return new Instruction(Action.TURN, Rotation.UNKNONW);

		} else return new Instruction(Action.UNKNOWN);
	}
	
	/**
	 * 
	 * Generate an action of type PICK
	 * 
	 * @param st is an instruction introduced by player 
	 * @return instruction is a instruction of type PICK
	 */
	
	private static Instruction generatePick(StringTokenizer st) {
		 
		if(st.hasMoreTokens()){
			String token2 =  st.nextToken(); 
			if(st.hasMoreTokens()){
				return unknown;
			}
			return new Instruction(Action.PICK, token2);
		}
		else
			return new Instruction(Action.UNKNOWN);
	}
	
	/**
	 * 
	 * Generate an action of type HELP
	 * 
	 * @param st is an instruction introduced by player
	 * @return instruction is a instruction of type HELP
	 * 
	 */
	

	private static Instruction generateHelp(StringTokenizer st) {
		if(!st.hasMoreTokens())
			return new Instruction(Action.HELP);
		else
			return unknown;
	}
	
	/**
	 * 
	 * Generate an action of type QUIT
	 * 
	 * @param st is an instruction introduced by player
	 * @return instruction is a instruction of type QUIT
	 * 
	 */
	
	private static Instruction generateQuit(StringTokenizer st) {
		if(!st.hasMoreTokens())
			return new Instruction(Action.QUIT);
		else
			return unknown;
	}
	
	/**
	 * 
	 * Generate an action of type SCAN
	 * 
	 * @param st is an instruction introduced by player
	 * @return instruction is a instruction of type SCAN
	 * 
	 */
	
	private static Instruction generateScan(StringTokenizer st) {
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
	
	/**
	 * 
	 * Generate an action of type OPERATE
	 * 
	 * @param st is an instruction introduced by player
	 * @return instruction is a instruction of type OPERATE
	 * 
	 */
	
	private static Instruction generateOperate(StringTokenizer st) {
		if(st.hasMoreTokens()){
			String token2 =  st.nextToken();
			if(st.hasMoreTokens()){
				return unknown;
			}
			return new Instruction(Action.OPERATE, token2);
		}
		else
			return new Instruction(Action.UNKNOWN);
	}
		
	/**
	 *
	 * @return String override with help message
	 * 
	 */

	public static String interpreterHelp() {
		return HELP + LINE_SEPARATOR;
	}

}
