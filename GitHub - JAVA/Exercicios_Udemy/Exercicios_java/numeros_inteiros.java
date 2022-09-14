package Exercicios_java;
import java.util.Locale;
import java.util.Scanner;

public class numeros_inteiros {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		int  N;

		System.out.println("Quantos numeros voce vai digitar: ");
		N = sc.nextInt();
		
		double[] vet = new double[N];
		
		for (int i = 0; i < N; i++) {
			
		    System.out.print("Digite um Numero: ");
		    vet[i] = sc.nextDouble(); 
		}
		
		System.out.println();
		System.out.println("Numeros Digitador:");
		for (int i = 0; i < N; i++) {
			System.out.println(String.format("%.1f", vet[i]));
		}
		
		
		
		sc.close();
		
	}

}
