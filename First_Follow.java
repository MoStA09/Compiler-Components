import java.util.Arrays;
import java.util.HashMap;

public class First_Follow {
	static HashMap<Character,String[]> splittedRules = new HashMap<Character,String[]>(); 
	static HashMap<Character,String> results = new HashMap<Character,String>(); 
	static HashMap<Character,Boolean> e_flags =  new HashMap<Character,Boolean>();
	static HashMap<Character,Boolean> directCycle =  new HashMap<Character,Boolean>();
	
	
	// A helper method to add the start terminals of the variables
	public static void addTerminal(char var) {
		for(int i=0;i< splittedRules.get(var).length;i++) {
			if(Character.isLowerCase(splittedRules.get(var)[i].charAt(0))) {
				if(splittedRules.get(var)[i].charAt(0) == 'e'){
					e_flags.put(var, true);
				}
				if(results.get(var).indexOf(splittedRules.get(var)[i].charAt(0)) == -1) {
					results.put(var, results.get(var)+splittedRules.get(var)[i].charAt(0));
				}
			}
			else {
				if(splittedRules.get(var)[i].charAt(0) != var) {
					addTerminal(splittedRules.get(var)[i].charAt(0));
					results.put(var, results.get(var)+splittedRules.get(var)[i].charAt(0));
				}
			}
		}
	}
	
	//First function
	@SuppressWarnings("unlikely-arg-type")
	public static String First(String cfg) {
		//Splitting the context free grammar to separate rules and get each variable of a rule alone.
		String[] rules = cfg.split("[;]");
		char[] variables = new char[rules.length];
		for(int i = 0;i<rules.length;i++) {
			variables[i] = rules[i].charAt(0);
			rules[i] = rules[i].substring(2, rules[i].length());
		}
		//Put each variable and the terms of it in a hash map
		for(int x=0;x<variables.length;x++) {
			splittedRules.put(variables[x], rules[x].split("[,]"));
		}
		//Loop over the variables and compute first to each of them recursively.
		for(int s=0;s<variables.length;s++) {
			results.put(variables[s], "");
			e_flags.put(variables[s], false);
		}
		
		//---------------------------------
		for(int i=0;i<variables.length;i++){
			addTerminal(variables[i]);
		}
		for(int j=0;j<variables.length;j++){
			int index = 0;
			for(int z=0;z< splittedRules.get(variables[j]).length;z++) {
				if(Character.isUpperCase(splittedRules.get(variables[j])[z].charAt(0))){
					if(e_flags.get(splittedRules.get(variables[j])[z].charAt(index)) == true){
						if(splittedRules.get(variables[j])[z].charAt(index)  ==
						   splittedRules.get(variables[j])[z].charAt(splittedRules.get(variables[j])[z].length()-1)) {
							
						}
						else {
							index+=1;
							if(Character.isLowerCase(splittedRules.get(variables[j])[z].charAt(index))){
								if(splittedRules.get(variables[j])[z].charAt(index) == 'e'){
									e_flags.put(variables[j], true);
								}
								if(results.get(variables[j]).indexOf(splittedRules.get(variables[j])[z].charAt(index)) == -1) {
									results.put(variables[j], results.get(variables)+splittedRules.get(variables[j])[z].charAt(index));
								}
							}
							else {
								addTerminal(splittedRules.get(variables[j])[z].charAt(index));
							}
						}
					}
				}
			}
		}
		
		
		System.out.println(Arrays.toString(splittedRules.get('S')));
		
		return null;
	}


	// Main Method
	public static void main(String[] args) {
		First("S,ScT,T;T,aSb,iaLb,e;L,SdL,S");
		addTerminal('S');
		addTerminal('T');
		addTerminal('L');

		System.out.println(results.get('T'));
	}
}
