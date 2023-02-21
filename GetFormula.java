package chukan_kadai;

import java.util.Scanner;

//標準入力から数式を受け取る処理
public class GetFormula {
	public static String getFormula() {

		System.out.println("数式を入力してください");
		
		while(true) {
			// ()の数が合っているか確認する処理に用いる変数(はcount1、　)はcount2
			int count1 = 0;
			int count2 = 0;
			boolean rowOperator = true;
			
			Scanner scanner = new Scanner(System.in);
			String formula = scanner.nextLine();
			
//			スペース除去
			formula = formula.replaceAll(" ", "");
			
//			数字と(の前に乗算記号追加
			for(int i = 0; i < formula.length()-1; i++) {
				int count = i + 1;
				if('0' <= formula.charAt(i) && formula.charAt(i) <= '9' && formula.charAt(count) == '(') {
					int index = formula.indexOf(formula.charAt(i));
					formula = formula.substring(0, index+1) + "*" + formula.substring(index+1, formula.length());
				}
			}
			
//                      演算子が連続していたらfalseを返す			
			for(int i = 0; i < formula.length()-1; i++) {
				int count = i + 1;
				if((formula.charAt(i) == '+' || formula.charAt(i) == '-' || formula.charAt(i) == '/' || formula.charAt(i) == '*') && (formula.charAt(count) == '+' || formula.charAt(count) == '-' || formula.charAt(count) == '/' || formula.charAt(count) == '*')) {
					rowOperator = false;
				}
			}
			

			
//			×や÷の記号を*, /に変更
			formula = formula.replace("×", "*");
			formula = formula.replace("÷", "/");
			
//			カッコの数が合っているか処理
			for(int i = 0; i < formula.length(); i++) {
				if(formula.charAt(i) == '(') {
					count1++;
				} else if(formula.charAt(i) == ')') {
					count2++;
				}
			}
				
			if(count1 != count2) {
				System.out.println("カッコの数が合わないようです。式を入力しなおしてください。");
			} else if (rowOperator == false) {
				System.out.println("演算子が連続しています。入力しなおしてください。");
			} else {
				return formula;
			}
			
		}
		
	}
}
