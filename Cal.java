package chukan_kadai;

import java.util.ArrayDeque;
import java.util.Deque;

// 逆ポーランド記法の数式を計算する処理
public class Cal {
	public static double result(String rpnExpression) {
//		1+( 2 ( 3 + (( 5 / 2 ) - 3 )))
		
		String[] formulaArray = rpnExpression.split("");
		Deque<Double> stack = new ArrayDeque<Double>();
		
		for(String e : formulaArray) {
			if('0' <= e.charAt(0) && e.charAt(0) <= '9') {
//				String型からDoubleにキャスト
				stack.push(Double.parseDouble(e));
			} else {
				double a = stack.pop();
				double b = stack.pop();
				if(e.equals("*")) {
					stack.push(b * a);
				} else if(e.equals("/")) {
					try {
						stack.push(b / a);
					} catch(ArithmeticException error) {
						System.out.println("エラー：0で割ることはできません。");
					}
					
				} else if(e.equals("+")) {
					stack.push(b + a);
				} else if(e.equals("-")) {
					stack.push(b - a);
				}
			}
		}
		
		double result = stack.pop();
		return result;
	}
}
