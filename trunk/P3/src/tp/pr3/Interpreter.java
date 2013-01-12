package tp.pr3;
import static tp.pr3.Constants.*;

import java.util.StringTokenizer;



/**
*
* @author Ignacio Cerda Sanchez
* @author Ricardo Eugui Fernandez
* @version 3
*
*/

public class Interpreter {

	
		
	/**
	 * 
	 * @param prompt is introduced by player 
	 * @return Instruction to interpreter by Robot
	 * 
	 */
	
	public static Instruction generateInstruction(String prompt) {
		StringTokenizer st = new StringTokenizer(prompt, " ");
		
		String words = st.nextToken().toUpperCase();
		
		if(words.equals("DROP"))
			return generateDrop(st);
		if(words.equals("HELP"))
			return generateHelp(st);
		if(words.equals("MOVE"))
			return generateMove(st);
		if(words.equals("OPERATE"))
			return generateOperate(st);
		if(words.equals("PICK"))
			return generatePick(st);
		if(words.equals("QUIT"))
			return generateQuit(st);
		if(words.equals("RADAR"))
			return generateRadar(st);
		if(words.equals("SCAN"))
			return generateScan(st);
		if(words.equals("TURN"))
			return generateTurn(st);
		else
			return new Instruction(Action.UNKNOWN);
		
	}
	
	private static Instruction generateDrop(StringTokenizer st) {
		if (st.hasMoreTokens()){
			String token2 = st.nextToken();
			if(st.hasMoreTokens())
				return new Instruction(Action.UNKNOWN);
			else
				return new Instruction(Action.DROP, token2);
		}
		else
			return new Instruction(Action.UNKNOWN);
	}
	
	private static Instruction generateHelp(StringTokenizer st) {
		if(!st.hasMoreTokens())
			return new Instruction(Action.HELP);
		else
			return new Instruction(Action.UNKNOWN);
	}
	

	/**
	 * 
	 * @param st
	 * @return
	 */
	
	private static Instruction generateMove(StringTokenizer st) {
		if(!st.hasMoreTokens())
			return new Instruction(Action.MOVE);
		else
			return new Instruction(Action.UNKNOWN);
	}
	
	
	private static Instruction generateOperate(StringTokenizer st) {
		if(st.hasMoreTokens()){
			String token2 =  st.nextToken();
			if(st.hasMoreTokens()){
				return new Instruction(Action.UNKNOWN);
			}
			return new Instruction(Action.OPERATE, token2);
		}
		else
			return new Instruction(Action.UNKNOWN);
	}
	
	private static Instruction generatePick(StringTokenizer st) {
		 
		if(st.hasMoreTokens()){
			String token2 =  st.nextToken(); 
			if(st.hasMoreTokens()){
				return new Instruction(Action.UNKNOWN);
			}
			return new Instruction(Action.PICK, token2);
		}
		else
			return new Instruction(Action.UNKNOWN);
	}
	
	private static Instruction generateQuit(StringTokenizer st) {
		if(!st.hasMoreTokens())
			return new Instruction(Action.QUIT);
		else
			return new Instruction(Action.UNKNOWN);
	}
	
	private static Instruction generateRadar(StringTokenizer st) {
		if (!st.hasMoreTokens())
			return new Instruction(Action.RADAR);
		else
			return new Instruction(Action.UNKNOWN);
	}
	
	private static Instruction generateScan(StringTokenizer st) {
		if(st.hasMoreTokens()){
			String token2 =  st.nextToken();
			if(st.hasMoreTokens()){
				return new Instruction(Action.UNKNOWN);
			}
			return new Instruction(Action.SCAN, token2);
		}
		else
			return new Instruction(Action.SCAN);
	}
	
	private static Instruction generateTurn(StringTokenizer st) {
		if (st.hasMoreTokens()){
			String token2 = st.nextToken().toUpperCase();
			if (st.hasMoreTokens())
				return new Instruction(Action.UNKNOWN); 
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
	 * @return String override with help message
	 */

	public static String interpreterHelp() {
		return HELP + LINE_SEPARATOR;
	}

}