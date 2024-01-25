package fibonaccilogaritmico;

import java.util.Scanner;

public class FibonacciLogaritmico {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario el valor de n
        System.out.print("Ingrese el valor de n para calcular F(n): ");
        int n = scanner.nextInt();

        // Comenzamos a calcular el n-ésimo término de Fibonacci
        // por restricción del lenguaje únicamente se puede llegar
        // a mostrar hasta fn161, dado que el tipo de dato long
        // muestra hasta 19 digitos.
        long resultado = fibonacciLogaritmico(n);

        // Mostramos el resultado
        System.out.println("El término F(" + n + ") de la sucesión de Fibonacci es: " + resultado);
    }
    
    public static long fibonacciLogaritmico(int n){
        //Los casos base principales de la sucesión fibonacci
        //son el termino fn0 y fn1, los cuales están ya definidos
        if (n <= 0) {
            return 0;
        }else if(n==1){
            return 1;
        }

        // Inicializamos la matriz de Fibonacci
        long[][] matrizFibonacci = {{1, 1}, {1, 0}};

        // Calculamos la matriz elevada a la potencia (n-1)
        long[][] resultado = matrizExponenciacion(matrizFibonacci, n - 1);

        // El resultado[0][0] es el término F(n)
        // expresado matricialmente sería lo sigueinte
        // |fn+1  fn  |
        // |fn    fn-1|
        return resultado[0][0];
    }
    
    public static long[][] matrizExponenciacion(long[][] matriz, int n){
        // Se comienza a realizar la exponenciación rápida de las matrices
        if (n == 1) {
            return matriz;
        } else if (n % 2 == 0) {
            // Cumplimos con la condición de si x^n es par entonces
            // aplicamos (x^2)^n/2
            long[][] mitad = matrizExponenciacion(matriz, n / 2);
            return multiplicarMatrices(mitad, mitad);
        } else {
            // Cumplimos la condición de si x^n es impar entonces
            // aplicamos x(x^2)^n-1 / 2
            long[][] mitad = matrizExponenciacion(matriz, (n - 1) / 2);
            return multiplicarMatrices(multiplicarMatrices(mitad, mitad), matriz);
        }
    }
    
    public static long[][] multiplicarMatrices(long[][] a, long[][] b){
        //Unicamente realizamos la multiplicación de matrices
        // que nos ayudara a encontrar el termino fn+1 que buscamos
        long[][] resultado = new long[2][2];
        resultado[0][0] = a[0][0] * b[0][0] + a[0][1] * b[1][0];
        resultado[0][1] = a[0][0] * b[0][1] + a[0][1] * b[1][1];
        resultado[1][0] = a[1][0] * b[0][0] + a[1][1] * b[1][0];
        resultado[1][1] = a[1][0] * b[0][1] + a[1][1] * b[1][1];
        return resultado;
    }
}
