import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class F_F {
	
	
	
	
	//Initialize the required data structures
	static HashMap<Character,String[]> rule_terms = new HashMap<Character,String[]>();
	@SuppressWarnings("rawtypes")
	static HashMap<Character,HashSet> first_res = new HashMap<Character,HashSet>();
	static HashMap<Character,Boolean> e_flags = new HashMap<Character,Boolean>();
	
	//A function to add only terminals 
	public static void addTerminals(char v) {
		HashSet<String> hash_Set = new HashSet<String>();
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
	@SuppressWarnings("unchecked")
	public static void variableAdd(char v) {
		HashSet<String> H = new HashSet<String>();
		 for(int i=0;i<rule_terms.get(v).length;i++) {
			 H.add("#");
			 if(Character.isUpperCase(rule_terms.get(v)[i].charAt(0))) {
				 if(first_res.get(v) == null) {
					 first_res.put(v,H);
				 }
				 if(first_res.get(rule_terms.get(v)[i].charAt(0)) == null) {
					 first_res.put(rule_terms.get(v)[i].charAt(0), H);
				 }
				 H = first_res.get(rule_terms.get(v)[i].charAt(0));
				 first_res.get(v).addAll(H);
			 }
		 }
	}
	
	//A function to to add terminals of a variable
	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	public static void addTermForVar(char v) {
		for(int i=0;i<rule_terms.get(v).length;i++) {
			if(Character.isUpperCase(rule_terms.get(v)[i].charAt(0))) {
				if(e_flags.get(rule_terms.get(v)[i].charAt(0)) == true){
					if(rule_terms.get(v)[i].charAt(0) == rule_terms.get(v)[i].charAt(rule_terms.get(v)[i].length()-1)) {
						@SuppressWarnings("unused")
						HashSet<String> hash_Set = new HashSet<String>();
						hash_Set = first_res.get(rule_terms.get(v)[i].charAt(0));
						first_res.get(v).addAll(hash_Set);
					}
					else {
						int epsilonFlag = 0;
						for(int j=1; j<rule_terms.get(v)[i].length();j++) {
							if(Character.isLowerCase(rule_terms.get(v)[i].charAt(j))) {
								first_res.get(v).add(rule_terms.get(v)[i].charAt(j));
								break;
							}
							else {
								epsilonFlag+=1;
								if(e_flags.get(rule_terms.get(v)[i].charAt(j)) == true) {
									@SuppressWarnings("unused")
									HashSet<String> hash_Set = new HashSet<String>();
									hash_Set = first_res.get(rule_terms.get(v)[i].charAt(j));
									hash_Set.remove('e');
									first_res.get(v).addAll(hash_Set);
								}
							}
						}
						if(epsilonFlag == rule_terms.get(v)[i].length()-1) {
							first_res.get(v).add('e');
						}
					}
				}
			}
		}
	}
	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	public static void addVariable(char v) {
		for(int i=0;i<rule_terms.get(v).length;i++) {
			if(Character.isUpperCase(rule_terms.get(v)[i].charAt(0))) {
				if(e_flags.get(rule_terms.get(v)[i].charAt(0)) == true){
					@SuppressWarnings("unused")
					HashSet<String> hash_Set = new HashSet<String>();
					hash_Set = first_res.get(rule_terms.get(v)[i].charAt(0));
					hash_Set.remove('e');
					first_res.get(v).addAll(hash_Set);
				}
				else {
					@SuppressWarnings("unused")
					HashSet<String> hash_Set = new HashSet<String>();
					hash_Set = first_res.get(rule_terms.get(v)[i].charAt(0));
					if(hash_Set != null ) {
						first_res.get(v).addAll(hash_Set);
					}
				}
			}
		}
	}
		
	//Execution function
	@SuppressWarnings("unchecked")
	public static void execute(String cfg) {
		String[] rules = cfg.split("[;]");
		char[] variables = new char[rules.length];
		for(int i = 0;i<rules.length;i++) {
			variables[i] = rules[i].charAt(0);
			rules[i] = rules[i].substring(2, rules[i].length());
		}
		
		//Put each variable and the terms of it in a hash map
		for(int x=0;x<variables.length;x++) {
			rule_terms.put(variables[x], rules[x].split("[,]"));
			e_flags.put(variables[x], false);
		}
		
		//Calculate terminals for all variables
		for(int y=0;y<variables.length;y++) {
			addTerminals(variables[y]);
		}
		for(int j=0;j<variables.length;j++) {
			addVariable(variables[j]);
		}
		
	}
	

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		execute("S,ScT,T;T,aSb,iaLb,e;L,SdL,S");
		/*HashSet<String> hash_Set = new HashSet<String>();
		HashSet<String> hash_Set1 = new HashSet<String>();
		hash_Set = first_res.get(rule_terms.get('S')[1].charAt(0));
		System.out.println(hash_Set);
		first_res.get('S').add('&');
		hash_Set1 = first_res.get('S');
		hash_Set1.addAll(hash_Set);
		System.out.println(hash_Set1);
		//first_res.get('S').addAll(hash_Set);
		//first_res.get('S').remove('&');
		//System.out.println(first_res.get('S'));*/
		System.out.println((first_res.get('S')));
		 //first_res.get('S').addAll(first_res.get(rule_terms.get('S')[1].charAt(0)));

		//System.out.println(rule_terms.get('S')[1].charAt(0));
		
	}

}
