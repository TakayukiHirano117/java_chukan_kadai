package itbegginer6;

import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class test {
	public static void main(String args[]) {
		// 計算する優先順位をMapで定義
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
		
//		 数式を標準入力で受け取る
		System.out.println("数式を入力してください");
//		1+( 2 ( 3 + (( 5 / 2 ) - 3 )))
		
		Scanner scanner = new Scanner(System.in);
		String formula = scanner.nextLine();
		
//		 スペース除去
		formula = formula.replaceAll(" ", "");
		
//		 2(などの時、*を追加
//		for(int i = 0; i < formula.length(); i++) {
//			if('0' <= formula.charAt(i) && formula.charAt(i) <= '9') {
//				if(formula.charAt(i+1).equals("(")) {
//					
//				}
//			}
//		}
		
		Deque<Character> stack = new ArrayDeque<>();
		String ans = "";
		String tmp = "";
		
		for(int i = 0; i < formula.length(); i++) {
			if('0' <= formula.charAt(i) && formula.charAt(i) <= '9') {
				tmp += formula.charAt(i);
//				if(formula.charAt(i+1).equals("(")) {
//					
//				}
			} else {
				if(!tmp.equals("")) {
					ans += tmp;
					tmp = "";
			}
			
			// whileでぶん回して数値が続く限りstackからpopして連結してまたぶち込む
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
//		return ans;　クラスを分けたらprintlnでなくansに戻す
		System.out.println(ans);
	}
}
