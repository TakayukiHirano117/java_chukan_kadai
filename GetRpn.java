package chukan_kadai;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GetRpn {
	public static String getRpn(String formula) {
//		 計算する優先順位をMapで定義
		Map<Character, Integer> rpnPriority = new HashMap<Character, Integer>() {
			{
				put('(', 3);
				put('*', 2);
				put('/', 2);
				put('+', 1);
				put('-', 1);
				put(')', 0);
			}
		};
		
		Deque<Character> stack = new ArrayDeque<>();
		String ans = "";
		String tmp = "";
		
		for(int i = 0; i < formula.length(); i++) {

			if(('0' <= formula.charAt(i) && formula.charAt(i) <= '9'  || 'a' <= formula.charAt(i) && formula.charAt(i) <= 'z' || 'A' <= formula.charAt(i) && formula.charAt(i) <= 'Z')) {
				tmp += formula.charAt(i);
				
			} else {
				if(!tmp.equals("")) {
					if (ans.length() > 0) {
	                    ans += " ";
	                }
					ans += tmp;
					tmp = "";
			}
			
//			whileでぶん回して数値が続く限りstackからpopして連結してまたぶち込む
			while(!stack.isEmpty() && rpnPriority.get(stack.peek()) >= rpnPriority.get(formula.charAt(i)) && stack.peek() != '(') {
				ans += " ";
				ans += stack.pop();
			}
			
				if(formula.charAt(i) == ')') {
					stack.pop();
				} else {
					stack.push(formula.charAt(i));
				}
				
			}
		}
		
		if(!tmp.equals("")) {
			ans += " ";
			ans += tmp;
		}
		
		
		while(!stack.isEmpty()) {
			ans += " ";
			ans += stack.pop();
			
		}
		
//		RPN記法にした数式を返す。
		return ans;
	}
}
