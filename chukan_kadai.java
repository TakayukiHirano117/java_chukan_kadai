package chukan_kadai;

public class chukan_kadai {
	public static void main(String args[]) {
		String formula = GetFormula.getFormula();
		String rpnExpression = GetRpn.getRpn(formula);
		double answer = Cal.result(rpnExpression);
		
		System.out.println(answer);
		
	}
	
}
