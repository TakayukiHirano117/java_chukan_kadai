package chukan_kadai;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

// 逆ポーランド記法の数式を計算する処理
public class Cal {
	public static double result(String rpnExpression) {
		
		String[] formulaArray = rpnExpression.split(" ");
		
		Deque<Double> stack = new ArrayDeque<Double>();
		
		for(String e : formulaArray) {
			if(('0' <= e.charAt(0) && e.charAt(0) <= '9'  || 'a' <= e.charAt(0) && e.charAt(0) <= 'z' || 'A' <= e.charAt(0) && e.charAt(0) <= 'Z')) {
					if(isNum(e) == false && checkHead(e) == true) {
						System.out.println(e + "に数値を入力してください。");
						Scanner s = new Scanner(System.in);
						String var = s.nextLine();
						e = var;
					}
					
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
	
	public static boolean isNum(String s) {
	    try {
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	public static boolean checkHead(String s) {
        boolean res = true;
        
        for(int i = 0; i < s.length(); i++) {
            // もし先頭が数値でなかったら次の処理へ
            if(!Character.isDigit(s.charAt(0))) {
                continue;
            } else {
            // 変数にfalseを代入して処理を終了する
                res =  false;
                break;
            }
        }
		
        return res;
    }
	
}
