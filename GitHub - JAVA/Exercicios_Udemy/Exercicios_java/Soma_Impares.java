package Exercicios_java;
import java.util.Locale;
import java.util.Scanner;

public class Soma_Impares {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		
		int x, y, troca, i, soma;
		
		System.out.println("Digite dois numeros:");
		x = sc.nextInt();
		y = sc.nextInt();
		
		if (x > y) {
			troca = x;
		    x = y;
		    y = troca;
		}    
		soma = 0;
		for ( i = x+1; i < y; i++) {
			if ( i % 2 != 0) { // se o i mod dois for diferente de zero //
				soma = soma + i;
			}	
		}
		    
		System.out.println("Soma dos Impares = " + soma);
		
		
	sc.close();
	

	}

}
