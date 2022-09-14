package Exercicios_java;
import java.util.Locale;
import java.util.Scanner;


public class Crescente {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		int x, y;
		
		System.out.println("Digite dois numero:");
		x = sc.nextInt();
		y = sc.nextInt();
		
		while (x != y) { // o sinal de diferente em java é usado o !=//
			if (x < y ) {
				System.out.println("CRESCENTE!");
			}
			else {
				System.out.println("DECRECENTE!");
			}
			System.out.println("DIGITE outros dois numeros ");
			x = sc.nextInt();
			y = sc.nextInt();
		}
		
         sc.close();
	}
}
