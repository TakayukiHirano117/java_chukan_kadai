package chukan_kadai;

import java.util.Scanner;

//標準入力から数式を受け取る処理
public class GetFormula {
	public static String getFormula() {
		System.out.println("数式を入力してください");
		
		Scanner scanner = new Scanner(System.in);
		String formula = scanner.nextLine();
		
		return formula;
	}
}
