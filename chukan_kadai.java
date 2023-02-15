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
//		1+( 2 * ( 3 + (( 5 / 2 ) - 3 )))
//		(5*2)/30+4-7
		
		Scanner scanner = new Scanner(System.in);
		String formula = scanner.nextLine();
		
//		 スペース除去
		formula = formula.replaceAll(" ", "");
		
		// 数字と(の前に乗算記号追加
		for(int i = 0; i < formula.length(); i++) {
			int count = i + 1;
			if('0' <= formula.charAt(i) && formula.charAt(i) <= '9' && formula.charAt(count) == '(') {
//				 2のインデックス番号を取得
				int index = formula.indexOf(formula.charAt(i));
//				System.out.println(index);
//				System.out.println(formula.substring(0, index)); これだと1+(までしか出ないので↓ので
//				System.out.println(formula.substring(0, index+1));
//				System.out.println(formula.substring(index+1, formula.length()));
				System.out.println(formula.substring(0, index+1) + "*" + formula.substring(index+1, formula.length()));
				
				formula = formula.substring(0, index+1) + "*" + formula.substring(index+1, formula.length());
			}
		}
		
		Deque<Character> stack = new ArrayDeque<>();
		String ans = "";
		String tmp = "";
		
		for(int i = 0; i < formula.length(); i++) {
			if('0' <= formula.charAt(i) && formula.charAt(i) <= '9') {
				tmp += formula.charAt(i);
//				if(formula.charAt(i+1).equals("(")) {
//                System.out.println(tmp); 
//				}
//				if(i == formula.length()) {
//					ans += tmp;
//					tmp = "";
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
		
		ans += (tmp + stack.pop());
		
//		return ans;　クラスを分けたらprintlnでなくansに戻す
		System.out.println(ans);
	}
}
