import java.util.ArrayList;

public class NFA {
	
	ArrayList<Integer> states = new ArrayList<Integer>();
	int initialState;
	int finalState;
	ArrayList<String> zeroTransitions = new ArrayList<String>();
	ArrayList<String> oneTransitions = new ArrayList<String>();
	ArrayList<String> e_Transitions = new ArrayList<String>();

	
	//Constructor
	public NFA(ArrayList<Integer> states, int initialState, int finalState, 
			ArrayList<String> zeroTransitions, 
			ArrayList<String> oneTransitions,
			ArrayList<String> e_Transitions) 
	{
		this.states = states;
		this.initialState = initialState;
		this.finalState = finalState;
		this.zeroTransitions = zeroTransitions;
		this.oneTransitions = oneTransitions;
		this.e_Transitions = e_Transitions;
	}


	public static void main(String[] args) {
		

	}

}
