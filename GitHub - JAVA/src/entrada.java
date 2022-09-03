import java.util.Locale;
import java.util.Scanner;

public class entrada {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		int x, y;
		double preco;
		
		System.out.println("Entrada");
		x = sc.nextInt();
		y = sc.nextInt();
		
		if (x == 1) {
			preco = y * 4.00;
		}
		else if (x == 2) {
			preco = y * 4.50;
		}
		else if (x == 3) {
			preco = y * 5.00;
		}
		else if (x == 4) {
			preco = y * 2.00;
		}
		else{
			preco = y * 4.00;
		}
		
		System.out.println("Saida: " + "R$ " + String.format("%.2f", preco) );
		
    sc.close();
	}

}
