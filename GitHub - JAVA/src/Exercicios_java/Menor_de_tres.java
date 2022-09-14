import java.util.Locale;
import java.util.Scanner;

public class Menor_de_tres {

	public static void main(String[] args) {
		
	    Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		int a, b, c, menor;
		
		
		System.out.print("Primeiro Valor: ");
		a = sc.nextInt();
		System.out.print("Segundo valor: ");
		b = sc.nextInt();
		System.out.print("Terceiro valor: ");
		c = sc.nextInt();
	
		if (a < b && a < c) {
			menor = a;			
		}
		else if ( b < c) {
			menor = b;
		}
		
		else {
			menor = c;
		}
			
		System.out.println("Menor = " + menor);
	
		
		sc.close();	

	}

}
