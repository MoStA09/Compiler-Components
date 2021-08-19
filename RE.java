import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;



public class RE {
	
	public static String RegToNFA(String r) {
		Stack<NFA> NFAs = new Stack<NFA>();
		int counter = -1;
		for(int i = 0;i<r.length();i++) {
			if(r.charAt(i) == '0') {
				ArrayList<Integer> states = new ArrayList<Integer>();
				states.add(counter+1);
				states.add(counter+2);
				int initialState = counter+1;
				int finalState = counter+2;
				ArrayList<String> zeroTransitions = new ArrayList<String>();
				ArrayList<String> oneTransitions = new ArrayList<String>();
				ArrayList<String> e_Transitions = new ArrayList<String>();
				zeroTransitions.add(String.valueOf(initialState)+"."+String.valueOf(finalState));
				NFA n = new NFA(states, initialState, finalState, zeroTransitions, oneTransitions, e_Transitions);
				NFAs.push(n);
				counter+=2;
			}
			if(r.charAt(i) == '1') {
				ArrayList<Integer> states = new ArrayList<Integer>();
				states.add(counter+1);
				states.add(counter+2);
				int initialState = counter+1;
				int finalState = counter+2;
				ArrayList<String> zeroTransitions = new ArrayList<String>();
				ArrayList<String> oneTransitions = new ArrayList<String>();
				ArrayList<String> e_Transitions = new ArrayList<String>();
				oneTransitions.add(String.valueOf(initialState)+"."+String.valueOf(finalState));
				NFA n = new NFA(states, initialState, finalState, zeroTransitions, oneTransitions, e_Transitions);
				NFAs.push(n);
				counter+=2;
			}
			if(r.charAt(i) == 'e') {
				ArrayList<Integer> states = new ArrayList<Integer>();
				states.add(counter+1);
				states.add(counter+2);
				int initialState = counter+1;
				int finalState = counter+2;
				ArrayList<String> zeroTransitions = new ArrayList<String>();
				ArrayList<String> oneTransitions = new ArrayList<String>();
				ArrayList<String> e_Transitions = new ArrayList<String>();
				e_Transitions.add(String.valueOf(initialState)+"."+String.valueOf(finalState));
				NFA n = new NFA(states, initialState, finalState, zeroTransitions, oneTransitions, e_Transitions);
				NFAs.push(n);
				counter+=2;
			}
			if(r.charAt(i) == '|') {
				ArrayList<Integer> states = new ArrayList<Integer>();
				states.add(counter+1);
				states.add(counter+2);
				int initialState = counter+1;
				int finalState = counter+2;
				ArrayList<String> zeroTransitions = new ArrayList<String>();
				ArrayList<String> oneTransitions = new ArrayList<String>();
				ArrayList<String> e_Transitions = new ArrayList<String>();
				NFA n2 = NFAs.pop();
				NFA n1 = NFAs.pop();
				if(n2.e_Transitions.size() != 0) {
					e_Transitions.addAll(n2.e_Transitions);
				}
				if(n1.e_Transitions.size() != 0) {
					e_Transitions.addAll(n1.e_Transitions);
				}
				e_Transitions.add(String.valueOf(initialState)+"."+String.valueOf(n2.initialState));
				e_Transitions.add(String.valueOf(initialState)+"."+String.valueOf(n1.initialState));
				e_Transitions.add(String.valueOf(n2.finalState)+"."+String.valueOf(finalState));
				e_Transitions.add(String.valueOf(n1.finalState)+"."+String.valueOf(finalState));
				states.addAll(n2.states);
				states.addAll(n1.states);
				if(n2.oneTransitions.size() != 0) {
					oneTransitions.addAll(n2.oneTransitions);
				}
				if(n1.oneTransitions.size() != 0) {
					oneTransitions.addAll(n1.oneTransitions);
				}
				if(n2.zeroTransitions.size() != 0) {
					zeroTransitions.addAll(n2.zeroTransitions);
				}
				if(n1.zeroTransitions.size() != 0) {
					zeroTransitions.addAll(n1.zeroTransitions);
				}
				NFA n = new NFA(states, initialState, finalState, zeroTransitions, oneTransitions, e_Transitions);
				NFAs.push(n);
				counter+=2;
			}
			if(r.charAt(i) == '.') {
				ArrayList<Integer> states = new ArrayList<Integer>();
				ArrayList<String> zeroTransitions = new ArrayList<String>();
				ArrayList<String> oneTransitions = new ArrayList<String>();
				ArrayList<String> e_Transitions = new ArrayList<String>();
				NFA n2 = NFAs.pop();
				NFA n1 = NFAs.pop();
				int initialState = n1.initialState;
				int finalState = n2.finalState;
				if(n2.e_Transitions.size() != 0) {
					e_Transitions.addAll(n2.e_Transitions);
				}
				if(n1.e_Transitions.size() != 0) {
					e_Transitions.addAll(n1.e_Transitions);
				}
				e_Transitions.add(String.valueOf(n1.finalState)+"."+String.valueOf(n2.initialState));
				states.addAll(n2.states);
				states.addAll(n1.states);
				if(n2.oneTransitions.size() != 0) {
					oneTransitions.addAll(n2.oneTransitions);
				}
				if(n1.oneTransitions.size() != 0) {
					oneTransitions.addAll(n1.oneTransitions);
				}
				if(n2.zeroTransitions.size() != 0) {
					zeroTransitions.addAll(n2.zeroTransitions);
				}
				if(n1.zeroTransitions.size() != 0) {
					zeroTransitions.addAll(n1.zeroTransitions);
				}
				NFA n = new NFA(states, initialState, finalState, zeroTransitions, oneTransitions, e_Transitions);
				NFAs.push(n);
			}
			if(r.charAt(i) == '*') {
				ArrayList<Integer> states = new ArrayList<Integer>();
				states.add(counter+1);
				states.add(counter+2);
				int initialState = counter+1;
				int finalState = counter+2;
				ArrayList<String> zeroTransitions = new ArrayList<String>();
				ArrayList<String> oneTransitions = new ArrayList<String>();
				ArrayList<String> e_Transitions = new ArrayList<String>();
				NFA n2 = NFAs.pop();
				if(n2.e_Transitions.size() != 0) {
					e_Transitions.addAll(n2.e_Transitions);
				}
				e_Transitions.add(String.valueOf(initialState)+"."+String.valueOf(n2.initialState));
				e_Transitions.add(String.valueOf(initialState)+"."+String.valueOf(finalState));
				e_Transitions.add(String.valueOf(n2.finalState)+"."+String.valueOf(finalState));
				e_Transitions.add(String.valueOf(n2.finalState)+"."+String.valueOf(n2.initialState));
				states.addAll(n2.states);
				if(n2.oneTransitions.size() != 0) {
					oneTransitions.addAll(n2.oneTransitions);
				}
				if(n2.zeroTransitions.size() != 0) {
					zeroTransitions.addAll(n2.zeroTransitions);
				}
				NFA n = new NFA(states, initialState, finalState, zeroTransitions, oneTransitions, e_Transitions);
				NFAs.push(n);
				counter+=2;
			}
		}
		NFA n = NFAs.pop();
		String Counter = String.valueOf(counter+1)+"#";
		String InitialState = String.valueOf(n.initialState)+"#";
		String FinalState = String.valueOf(n.finalState)+"#";
		
		//_________________________________________________
		double[] zeros = new double[n.zeroTransitions.size()];
		for(int i = 0;i<n.zeroTransitions.size();i++) {
			zeros[i] = Double.parseDouble(n.zeroTransitions.get(i));
		}
		Arrays.sort(zeros);
		String forZeros = "";
		for(int j = 0;j<zeros.length;j++) {
			double doubleNumber = zeros[j];
			String doubleAsString = String.valueOf(doubleNumber);
			int indexOfDecimal = doubleAsString.indexOf(".");
			if(j<zeros.length) {
				forZeros+= doubleAsString.substring(0, indexOfDecimal)+","+doubleAsString.substring(indexOfDecimal+1)+";";
			}
			else {
				forZeros+= doubleAsString.substring(0, indexOfDecimal)+","+doubleAsString.substring(indexOfDecimal+1);
			}
		}
		//_____________________________________________________________________________________________________
	
		double[] ones = new double[n.oneTransitions.size()];
		for(int i = 0;i<n.oneTransitions.size();i++) {
			ones[i] = Double.parseDouble(n.oneTransitions.get(i));
		}
		Arrays.sort(ones);
		String forOnes = "";
		for(int j = 0;j<ones.length;j++) {
			double doubleNumber = ones[j];
			String doubleAsString = String.valueOf(doubleNumber);
			int indexOfDecimal = doubleAsString.indexOf(".");
			if(j<ones.length) {
				forOnes+= doubleAsString.substring(0, indexOfDecimal)+","+doubleAsString.substring(indexOfDecimal+1)+";";
			}
			else {
				forOnes+= doubleAsString.substring(0, indexOfDecimal)+","+doubleAsString.substring(indexOfDecimal+1);
			}
		}
		//_______________________________________________________________________________________________________
		double[] Es = new double[n.e_Transitions.size()];
		for(int i = 0;i<n.e_Transitions.size();i++) {
			Es[i] = Double.parseDouble(n.e_Transitions.get(i));
		}
		Arrays.sort(Es);
		String forEs = "";
		for(int j = 0;j<Es.length;j++) {
			double doubleNumber = Es[j];
			String doubleAsString = String.valueOf(doubleNumber);
			int indexOfDecimal = doubleAsString.indexOf(".");
			if(j<Es.length-1) {
				forEs+= doubleAsString.substring(0, indexOfDecimal)+","+doubleAsString.substring(indexOfDecimal+1)+";";
			}
			else {
				forEs+= doubleAsString.substring(0, indexOfDecimal)+","+doubleAsString.substring(indexOfDecimal+1);
			}
		}
		//______________________________________________________________________
		
		String result = Counter+InitialState+FinalState+forZeros+"#"+forOnes+"#"+forEs;
		return result;
		
	}


	public static void main(String[] args) {
		String r1 = "0*1.";
		String r2 = "11.1*.";
		String r3 = "0e1*|.1.";
		String r4 = "0*1e.|";
		String r5 = "10.*0|";
		String result1 =  RegToNFA(r1);
		String result2 =  RegToNFA(r2);
		String result3 =  RegToNFA(r3);
		String result4 =  RegToNFA(r4);
		String result5 =  RegToNFA(r5);
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
		System.out.println(result5);
	}

}

	
