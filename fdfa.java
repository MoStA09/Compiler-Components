import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class fdfa {
	HashMap<Integer,Integer> zero_transitions = new HashMap<Integer,Integer>();
	HashMap<Integer,Integer> one_transitions = new HashMap<Integer,Integer>();
	HashMap<Integer,String> actions = new HashMap<Integer,String>();
	ArrayList<Integer> acceptStates = new ArrayList<Integer>();
	int hash = 0;
	
	
	//Constructor
	public fdfa(String s) {
		for(int y=0;y<s.length();y++) {
			if(s.charAt(y)=='#') {
				hash = y;
				break;
			}
		}
		String P = s.substring(0,hash); 
		String[] splitted = P.split("[,;#]");
		String A = s.substring(hash+1);
		String[] splitted1 = A.split("[,;#]");
		for(int i = 0;i<splitted.length;i+=4) {
			zero_transitions.put(Integer.parseInt(splitted[i]), Integer.parseInt(splitted[i+1]));
			one_transitions.put(Integer.parseInt(splitted[i]), Integer.parseInt(splitted[i+2]));
			actions.put(Integer.parseInt(splitted[i]), splitted[i+3]);
		}
		for(int j = 0;j<splitted1.length;j++) {
			acceptStates.add(Integer.parseInt(splitted1[j]));
		}
	}
	//_____________________________________________________________________________________________
	
	
	//Method run()
	public static String run(String r, fdfa D) {
		String result = "";
		Stack<Integer> stk = new Stack<Integer>();
		int currentState = 0;
		int L = 0;
		int R = 0;
		while(R < r.length()) {
			stk.push(0);
			currentState = 0;
			for(int i = L;i<r.length();i++) {
				if(Character.getNumericValue(r.charAt(i)) == 0) {
					stk.push(D.zero_transitions.get(currentState));
					currentState = D.zero_transitions.get(currentState);
				}
				else {
					stk.push(D.one_transitions.get(currentState));
					currentState = D.one_transitions.get(currentState);
				}
			}
			L = r.length();
			int flag  = 0;
			String temp = "";
			int lastState = stk.pop();
			stk.push(lastState);
			//System.out.println(lastState);
			for(int j = L;j>R;j--) {
				flag = stk.pop();
				if(D.acceptStates.contains(flag)) {
					temp = D.actions.get(flag);
					L = j;
					break;
				}
			}
			if(temp.length() == 0) {
				temp = D.actions.get(lastState);
			}
			result+=temp;
			temp = "";
			
			flag = 0;
			//L = L+1;
			R = L;
			stk.clear();
		}
		return result;
	}
	//___________________________________________________________________________________________
	
	

	public static void main(String[] args) {
		String s = "0,2,1,A;1,1,2,B;2,3,4,C;3,4,5,D;4,5,5,E;5,5,2,F#3,5";
		fdfa D = new fdfa(s);
		String result0 = run("010",D);
		String result1 = run("1100",D);
		String result2 = run("1000",D);
		String result3 = run("01011",D);
		String result4 = run("110011",D);
		System.out.println(result0);
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
		//__________________________________
		System.out.println("______________________________________");
		System.out.println();
		//__________________________________
		String s1 = "0,1,2,A;1,2,4,B;2,3,5,C;3,4,3,D;4,5,6,E;5,6,0,F;6,3,6,G#1,6";
		fdfa D1 = new fdfa(s1);
		String result00 = run ("110011",D1);
		String result01 = run ("000",D1);
		String result02 = run ("111",D1);
		String result03 = run ("1100",D1);
		String result04 = run ("1101000",D1);
		System.out.println(result00);
		System.out.println(result01);
		System.out.println(result02);
		System.out.println(result03);
		System.out.println(result04);
	}
}
