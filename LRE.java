import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LRE {
	
	public static String LRE1(String cfg) {
		String[] rules = cfg.split("[;]");
		char[] variables = new char[rules.length];
		for(int i = 0;i<rules.length;i++) {
			variables[i] = rules[i].charAt(0);
			rules[i] = rules[i].substring(2, rules[i].length());
		}
		HashMap<Character,String[]> splittedRules = new HashMap<Character,String[]>(); 
		for(int x=0;x<variables.length;x++) {
			splittedRules.put(variables[x], rules[x].split("[,]"));
		}
		System.out.println(Arrays.toString(splittedRules.get('S')));
		int i=0;
		int j=0;
		for(int y=0;y<variables.length;y++) {
			for(int z=0;z<splittedRules.get(variables[y]).length;z++) {
				if(splittedRules.get(variables[y])[z].charAt(0) == variables[y]) {
					
				}
			}
		}
		
		
		
		return null;
		
	}

	public static void main(String[] args) {
		LRE1("S,ScT,Sa,T,b;T,aSb,iaLb,i;L,SdL,S");
	}

}
