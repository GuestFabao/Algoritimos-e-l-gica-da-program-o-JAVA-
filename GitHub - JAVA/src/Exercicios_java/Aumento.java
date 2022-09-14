import java.util.Locale;
import java.util.Scanner;

public class Aumento {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		
		double salario, porcentagem, aumento, novoSalario, teste;
		
		System.out.print("Digite o salario da pessoa: ");
		salario = sc.nextDouble();
		
		if (salario <= 1000.0) {
			porcentagem = 20;
		}
		else if (salario <= 3000.0) {
			porcentagem = 15;
		}
		else if (salario <= 8000.0) {
			porcentagem = 10;
		}
		else {
			porcentagem = 5;
		}
		
		aumento = salario * porcentagem / 100;
		novoSalario = salario + aumento;
		
		System.out.println("Novo salario = " + " R$ " + String.format("%.2f", novoSalario));
		System.out.println("Aumento = " + " RS " + String.format("%.2f", aumento));
		System.out.println("porcentagem = " + porcentagem + " R$ ");
		
		
		
		
		
    sc.close();
	}

}
