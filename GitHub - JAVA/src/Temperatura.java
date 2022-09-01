import java.util.Locale;
import java.util.Scanner;

public class Temperatura {

	public static void main(String[] args) {
		
     Locale.setDefault(Locale.US);
     Scanner sc = new Scanner(System.in);
     
     char unidade;
     double celsius, fahrenheit;
     
     
     System.out.print("Voce vai digitar a temperatura em qual escala (C/F)? ");
     unidade = sc.next().charAt(0);
     
     if (unidade == 'F') {
    	 System.out.print("Digite a temperatura em Fahrenheit: ");
    	 fahrenheit = sc.nextDouble();
    	 
    	 celsius = 5.0 / 9.0 * (fahrenheit - 32.0);
    	 System.out.print("Temperatura equivalente em Celsius: " + String.format("%.2f", celsius));
     }
    	 else {
    		  System.out.print("Digite a temperatura em Celsius: ");
    	      celsius = sc.nextDouble();
    	      
    	      fahrenheit = 9.0 * celsius / 5.0 + 32.0;
    	      System.out.println("Temperatura equivalente em Fahrenheit: " + String.format("%.2f", fahrenheit));
    	 
     }
     
     
     
     sc.close();
     
	}

}
