package chukan_kadai;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

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
		
//		 スペース除去
		formula = formula.replaceAll(" ", "");
		
		// 数字と(の前に乗算記号追加
		for(int i = 0; i < formula.length(); i++) {
			int count = i + 1;
			if('0' <= formula.charAt(i) && formula.charAt(i) <= '9' && formula.charAt(count) == '(') {
				int index = formula.indexOf(formula.charAt(i));
				formula = formula.substring(0, index+1) + "*" + formula.substring(index+1, formula.length());
			}
		}
		
		Deque<Character> stack = new ArrayDeque<>();
		String ans = "";
		String tmp = "";
		
		for(int i = 0; i < formula.length(); i++) {
			if('0' <= formula.charAt(i) && formula.charAt(i) <= '9') {
				tmp += formula.charAt(i);
				
			} else {
				if(!tmp.equals("")) {
					ans += tmp;
					tmp = "";
			}
			
			while(!stack.isEmpty() && rpnPriority.get(stack.peek()) >= rpnPriority.get(formula.charAt(i)) && stack.peek() != '(') {
				ans += stack.pop();
			}
			
				if(formula.charAt(i) == ')') {
					stack.pop();
				} else {
					stack.push(formula.charAt(i));
				}
				
			}
		}
		
		ans += (tmp + stack.pop());
		
//		RPN記法にした数式を返す。
		return ans;
	}
}
