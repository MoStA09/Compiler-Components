import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FOLLOW_FIRST {
	//Initialize the required data structures
	static HashMap<Character,String[]> rule_terms = new HashMap<Character,String[]>();
	static HashMap<Character,HashSet> first_res = new HashMap<Character,HashSet>();
	static HashMap<Character,Boolean> e_flags = new HashMap<Character,Boolean>();

	
	//A function to add terminals and calculate the epsilon flags
	public static void addTerminals(char v) {
		HashSet hash_Set = new HashSet<String>();
		for(int i=0;i< rule_terms.get(v).length;i++) {
			if(Character.isLowerCase(rule_terms.get(v)[i].charAt(0))) {
				if(rule_terms.get(v)[i].charAt(0) == 'e'){
					e_flags.put(v, true);
				}
				hash_Set.add(rule_terms.get(v)[i].charAt(0)+"");
			}
			first_res.put(v, hash_Set);
		}
	}
	
	 //A function to loop over the variables
	
	public static void checkVariable(char v) {
		int index = 0;
		for(int i=0;i< rule_terms.get(v).length;i++) {
			if(Character.isUpperCase(rule_terms.get(v)[i].charAt(index))) {
				if(e_flags.get(rule_terms.get(v)[i].charAt(index))) {
					if(rule_terms.get(v)[i].charAt(rule_terms.get(v)[i].length()-1) == rule_terms.get(v)[i].charAt(index)) {
						first_res.put(v, first_res.get(v)+first_res.get(rule_terms.get(v)[i].charAt(index)));
					}
					else {
						String temp = first_res.get(rule_terms.get(v)[i].charAt(index));
						if(temp.contains("e")) {
							temp.replace("e", "");
							first_res.put(v, first_res.get(v)+temp);
						}
						//index+=1;
						//if(Character.isLowerCase(rule_terms.get(v)[i].charAt(index))){
							//first_res.put(v, first_res.get(v)+rule_terms.get(v)[i].charAt(index));
						//}
						/*else{
							checkVariable(v);
							index = 0;
						}*/
					}
				}
				else {
					first_res.put(v, first_res.get(v)+first_res.get(rule_terms.get(v)[i].charAt(index)));
				}
			}
		}
	}
	//A function to fill the hash maps
	public static void splitInput(String cfg) {
		String[] rules = cfg.split("[;]");
		char[] variables = new char[rules.length];
		for(int i = 0;i<rules.length;i++) {
			variables[i] = rules[i].charAt(0);
			rules[i] = rules[i].substring(2, rules[i].length());
		}
		//Put each variable and the terms of it in a hash map
		for(int x=0;x<variables.length;x++) {
			rule_terms.put(variables[x], rules[x].split("[,]"));
			first_res.put(variables[x], "");
			e_flags.put(variables[x], false);
			
		}
		//Calculate terminals for all variables
		for(int y=0;y<variables.length;y++) {
			addTerminals(variables[y]);
			checkVariable(variables[y]);
		}
	}
	
	
	

	

	
	
	
	public static void main(String[] args) {
		splitInput("S,ScT,T;T,aSb,iaLb,e;L,SdL,S");
		//first_res.put('S', first_res.get('S')+first_res.get(rule_terms.get('S')[1].charAt(0)));
		


		System.out.println(first_res.get('S'));
	}

}
