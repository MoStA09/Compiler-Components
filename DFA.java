import java.util.ArrayList;
import java.util.HashMap;

public class DFA {
	
	//Data structure for the DFA
	HashMap<Integer,Integer> zeroTransitions=new HashMap<Integer,Integer>();
	HashMap<Integer,Integer> onesTransitions=new HashMap<Integer,Integer>();
	ArrayList<Integer> acceptStates = new ArrayList<Integer>();
	int hash = 0;
	
	
	//Constructor of the DFA
	public DFA(String s){
		for(int y=0;y<s.length();y++) {
			if(s.charAt(y)=='#') {
				hash = y;
				break;
			}
		}
		
		for (int i = 0; i < hash; i+=6) {
			zeroTransitions.put(Character.getNumericValue(s.charAt(i)), Character.getNumericValue(s.charAt(i+2)));
			onesTransitions.put(Character.getNumericValue(s.charAt(i)), Character.getNumericValue(s.charAt(i+4)));
        }
		for (int u=hash+1;u<s.length();u+=2) {
			acceptStates.add(Character.getNumericValue(s.charAt(u)));
		}
		
	}
	
	
	//A method to run the DFA on input
	public static String run (String in, DFA D) {
		int lastState = 0;
		int currentState = 0;
		for(int j = 0;j<in.length();j++){
			if(Character.getNumericValue(in.charAt(j)) == 0) {
				lastState = D.zeroTransitions.get(currentState);
			}
			else {
				lastState = D.onesTransitions.get(currentState);
			}
			
			currentState = lastState;
		}
		if(D.acceptStates.contains(lastState)) {
			return "true";
		}
		else {
			return "false";
		}
	}

	//____________________________________________________________________
	
	public static void main(String[] args) {
		String s  = "0,0,1;1,2,1;2,3,3;3,4,1;4,5,2;5,5,4#1,2";
		/*String t1 = "01010101";
		String t2 = "10101010";
		String t3 = "0111";
		String t4 = "11001100";*/
		String t5 = "01010001";
		DFA D = new DFA(s);
		String s1 = "0,1,0;1,1,2;2,1,3;3,4,3;4,4,4#2";
		/*String e1 = "0011110101";
		String e2 = "01010101";
		String e3 = "10101011";
		String e4 = "11001100";*/
		String e5 = "10010101011010";
		DFA D1 = new DFA(s1);
	
		
		String result  = run(t5,D);
		String result1 = run(e5,D1);
		System.out.println(result);
		System.out.println(result1);
	}

}
