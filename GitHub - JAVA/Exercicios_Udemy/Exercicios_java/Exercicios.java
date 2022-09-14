package Exercicios_java;
import java.util.Locale;
import java.util.Scanner;

public class Exercicios {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
	    int mask = 0b100000;
		int N = sc.nextInt();
		
		if ((N & mask) != 0 ){
			System.out.println("6th bit is true");
		}
		else {
			System.out.println("6th bit is false");
		}
	
	
	
	
	
		sc.close();
	}

}
