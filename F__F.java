import java.util.Arrays;
import java.util.HashMap;

public class F__F {
	//Initialize the required data structures
	static HashMap<Character,String[]> rule_terms = new HashMap<Character,String[]>();
	static HashMap<Character,Boolean> e_flags = new HashMap<Character,Boolean>();
	static HashMap<Character,String> first_res = new HashMap<Character,String>();
	static HashMap<Character,String> follow_res = new HashMap<Character,String>();
	static char[] variables	;
	static char startVar ;
	
	// A helper method to add the start terminals of the variables
	public static void addTerminal(char var) {
		for(int i=0;i< rule_terms.get(var).length;i++) {
			if(Character.isLowerCase(rule_terms.get(var)[i].charAt(0))) {
				if(rule_terms.get(var)[i].charAt(0) == 'e'){
					e_flags.put(var, true);
				}
				if(first_res.get(var).indexOf(rule_terms.get(var)[i].charAt(0)) == -1) {
					first_res.put(var, first_res.get(var)+rule_terms.get(var)[i].charAt(0));
				}
			}
		}
	}
	
	//A method to add variable's terminals
		public static void addTermOfVariable(char v, int index)  {
			for(int i=0;i<rule_terms.get(v).length;i++) {
				if(index < rule_terms.get(v)[i].length() && Character.isUpperCase(rule_terms.get(v)[i].charAt(index)) ) {
					String tv = first_res.get(rule_terms.get(v)[i].charAt(index));
					if(tv.contains("e")) {
						if(rule_terms.get(v)[i].charAt(index) == rule_terms.get(v)[i].charAt(rule_terms.get(v)[i].length()-1)){
							String rv = first_res.get(v);
							for(int j=0;j<rv.length();j++) {
								if(tv.contains(rv.charAt(j)+"") == false) {
									tv+=rv.charAt(j)+"";
								}
							}
							first_res.put(v, tv);
						}
						else {
							tv.replace("e", "");
							String rv = first_res.get(v);
							for(int j=0;j<rv.length();j++) {
								if(tv.contains(rv.charAt(j)+"") == false) {
									tv+=rv.charAt(j)+"";
								}
							}
							first_res.put(v, tv);
						}
					}
					else {
						String rv = first_res.get(v);
						for(int j=0;j<rv.length();j++) {
							if(tv.contains(rv.charAt(j)+"") == false) {
								tv+=rv.charAt(j)+"";
							}
						}
					first_res.put(v, tv);
					}
				first_res.put(v, tv);
				}
			}
		}
		
	//A method to check for the e	
	public static void e_check(char v) {
		for(int i=0;i<rule_terms.get(v).length;i++){
			if(Character.isUpperCase(rule_terms.get(v)[i].charAt(0))) {
				if(first_res.get(rule_terms.get(v)[i].charAt(0)).contains("e") &&
				   rule_terms.get(v)[i].charAt(0) != rule_terms.get(v)[i].charAt(rule_terms.get(v)[i].length()-1)) {
					for(int j=1;j<rule_terms.get(v)[i].length();j++) {
						if(Character.isLowerCase(rule_terms.get(v)[i].charAt(j))) {
							first_res.put(v, first_res.get(v)+rule_terms.get(v)[i].charAt(1));
							break;
						}
						else {
							 addTermOfVariable(v, j);
						}
					}
				}
			}
		}
	}
	
	//A method to calculate the follow
	public static void follow(char v) {
		for(int i = 0;i<variables.length;i++) {
			for(int j=0;j<rule_terms.get(variables[i]).length;j++) {
				for(int k=0;k<rule_terms.get(variables[i])[j].length();k++) {
					if(rule_terms.get(variables[i])[j].charAt(k) == v) {
						if(rule_terms.get(variables[i])[j].charAt(k) == 
							rule_terms.get(variables[i])[j].charAt(rule_terms.get(variables[i])[j].length()-1)){
							String tv = follow_res.get(variables[i]);
							String rv = follow_res.get((rule_terms.get(variables[i])[j].charAt(k)));
							follow_res.put((rule_terms.get(variables[i])[j].charAt(k)), tv+rv);
						}
						else {
							if(rule_terms.get(variables[i])[j].charAt(k) == v) {
								if(Character.isLowerCase(rule_terms.get(variables[i])[j].charAt(k+1))) {
										String ev = rule_terms.get(variables[i])[j].charAt(k+1)+"";
										follow_res.put(v, follow_res.get(v)+ev);
										break;
									
								}
								else {
									String yv = first_res.get(rule_terms.get(variables[i])[j].charAt(k+1));
									if(e_flags.get(rule_terms.get(variables[i])[j].charAt(k+1))) {
										yv = yv.replace("e", "");
										String rv = follow_res.get(v);
										for(int n=0;n<rv.length();n++) {
											if(yv.contains(rv.charAt(n)+"") == false) {
												yv+=rv.charAt(n)+"";
											}
										}
										follow_res.put(v, yv);
									}
									else {
										String rv = follow_res.get(v);
										for(int n=0;n<rv.length();n++) {
											if(yv.contains(rv.charAt(n)+"") == false) {
												yv+=rv.charAt(n)+"";
											}
										}
										follow_res.put(v, yv);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	/*public static void follow_v(char v) {
		for(int i = 0;i<variables.length;i++) {
			for(int j=0;j<rule_terms.get(variables[i]).length;j++) {
				for(int k=0;k<rule_terms.get(variables[i])[j].length();k++) {
					if(rule_terms.get(variables[i])[j].charAt(k) == 
							rule_terms.get(variables[i])[j].charAt(rule_terms.get(variables[i])[j].length()-1)) {
					}
				}
			}
		}
	}*/
	
	//A function to split the input and do necessary things
	public static void execute(String cfg) {
		startVar = cfg.charAt(0);
		String[] rules = cfg.split("[;]");
		variables = new char[rules.length];
		for(int i = 0;i<rules.length;i++) {
			variables[i] = rules[i].charAt(0);
			rules[i] = rules[i].substring(2, rules[i].length());
		}
		//Put each variable and the terms of it in a hash map
		for(int x=0;x<variables.length;x++) {
			rule_terms.put(variables[x], rules[x].split("[,]"));
			first_res.put(variables[x], "");
			follow_res.put(variables[x], "");
			e_flags.put(variables[x], false);
		}
		follow_res.put(startVar, "$");
		//Calculate terminals for all variables
		for(int y=0;y<variables.length;y++) {
			addTerminal(variables[y]);
			//addTermOfVariable(variables[y]);
		}
		//number of cases to check
		for(int d=0;d<variables.length;d++) {
			addTermOfVariable(variables[d],0);
		}
		for(int n=0;n<variables.length;n++) {
			e_check(variables[n]);
		}
		for(int d=0;d<variables.length;d++) {
			addTermOfVariable(variables[d],0);
		}
		for(int n=0;n<variables.length;n++) {
			e_check(variables[n]);
		}
		//---------------------------------------------
		for(int m=0;m<variables.length;m++) {
			follow(variables[m]);
		}
	}
	
	public static void main(String[] args) {
		/*
		 S,sk,A;P,xSdP,k,e;A,Pcat,s
		 S,oPZ,o,SpP;Z,ppSP,Poo,e;P,PS,xq,e
		 S,SdSjj,dLa,e;L,dwL,to,e
		 S,UzQ,yzS,dz;N,Nndz,rS,rdN;Q,rrUn,guc;U,N,UiSu
		 S,vHv,Tf,e;H,Sa,vSz,an,e;T,aT,kqd,xdl
		 */
		//execute("S,sk,A;P,xSdP,k,e;A,Pcat,s");
		//execute("S,oPZ,o,SpP;Z,ppSP,Poo,e;P,PS,xq,e");
		//execute("S,SdSjj,dLa,e;L,dwL,to,e");
		//execute("S,UzQ,yzS,dz;N,Nndz,rS,rdN;Q,rrUn,guc;U,N,UiSu");
		execute("S,vHv,Tf,e;H,Sa,vSz,an,e;T,aT,kqd,xdl");
		//execute("S,ACB,CbB,Ba;A,da,BC;B,g,e;C,h,e");
		//System.out.println(first_res.get('F'));
		
		//System.out.println(Arrays.toString(rule_terms.get(variables[0])));
		String follow = "";
		String first = "";
		for(int i=0;i<variables.length;i++) {
			char charArray[] = follow_res.get(variables[i]).toCharArray();
			char charArray1[] = first_res.get(variables[i]).toCharArray();
			Arrays.sort(charArray);
			Arrays.sort(charArray1);
			String v = new String (charArray);
			String a = new String (charArray1);
			StringBuilder output = new StringBuilder();
			output.append(v.charAt(0));
			for (int idx = 1; idx < v.length(); idx++) {
			    if(v.charAt(idx) != v.charAt(idx-1)) {
			        output.append(v.charAt(idx));
			    }
			 }
			String out = output.toString();
			if(out.charAt(0) == '$' ) {
				out = out.replace("$","");
				out+="$";
			}
			out.replace("e", "");
			
			//first builder
			StringBuilder output1 = new StringBuilder();
			output1.append(a.charAt(0));
			for (int idx = 1; idx < a.length(); idx++) {
			    if(a.charAt(idx) != a.charAt(idx-1)) {
			        output1.append(a.charAt(idx));
			    }
			 }
			String out1 = output1.toString();
			follow+=variables[i]+","+out+";";
			first+=variables[i]+","+out1+";";
		}
		follow = follow.replaceAll("e", "");
		System.out.println(first);
		System.out.println(follow);
	}

}
