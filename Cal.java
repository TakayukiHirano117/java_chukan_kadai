package chukan_kadai;

import java.util.ArrayDeque;
import java.util.Deque;

// 逆ポーランド記法の数式を計算する処理
public class Cal {
	public static int result(String rpnExpression) {
		
		String[] formulaArray = rpnExpression.split("");
		Deque<Integer> stack = new ArrayDeque<Integer>();
		
		for(String e : formulaArray) {
			if('0' <= e.charAt(0) && e.charAt(0) <= '9') {
//				String型からintegerにキャスト
				stack.push(Integer.parseInt(e));
			} else {
				int a = stack.pop();
				int b = stack.pop();
				if(e.equals("*")) {
					stack.push(b * a);
				} else if(e.equals("/")) {
					stack.push(b / a);
				} else if(e.equals("+")) {
					stack.push(b + a);
				} else if(e.equals("-")) {
					stack.push(b - a);
				}
			}
		}
		
		int result = stack.pop();
		return result;
	}
}
