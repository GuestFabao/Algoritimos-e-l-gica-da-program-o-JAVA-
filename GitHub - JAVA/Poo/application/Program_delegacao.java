package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Triangle;

public class Program_delegacao {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		Triangle x, y;
		x = new Triangle();
		y = new Triangle();
		
		System.out.println("Enter the measures of triangle X: ");
		x.a = sc.nextDouble();
		x.b = sc.nextDouble();
		x.c = sc.nextDouble();
		System.out.println("Entre the measures of triangle Y: ");
		y.a = sc.nextDouble();
		y.b = sc.nextDouble();
		y.c = sc.nextDouble();
       


		double areaX = x.area(); 
		double areaY = y.area();
		
		System.out.printf("Tringle X area: %.4f%n", areaX);
		System.out.printf("Tringle Y area: %.4f%n", areaY);
  
		if (areaX > areaY) {
			System.out.println("Larger area: X");
		}
		else {
			System.out.println("larger area: Y");
		}
		
		sc.close();
	}

}
