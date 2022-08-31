import java.util.Locale;
import java.util.Scanner;

public class diagonal_negativos {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		int N, cont;
		
		
		System.out.println("Qual a ordem da matriz? ");
		N = sc.nextInt();
		
		int[][] mat = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++){
				System.out.print("Elemento ["+ i + ","  + j + "]: ");
				mat[i][j] = sc.nextInt();	
			}
		}
		System.out.println("Diagonal principal:");
		for (int i = 0; i < N; i++) {
				System.out.print(mat[i][i] + " ");	
		}
		System.out.println();
		
		cont = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (mat[i][j] < 0) {
				   cont = cont + 1;
				}  
			}
		}
		
		System.out.println("Quantidade de negativos = " + cont);
      sc.close();
	}

}
