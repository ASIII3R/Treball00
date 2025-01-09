import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("gestió de biblioteca");
        System.out.println("1. Llibres");
        System.out.println("2. Usuaris");
        System.out.println("3. Préstecs");
        System.out.println("0. Sortir");
        System.out.print("Escull una opció");

        int opcion = scanner.nextInt();
        switch(opcion){
            case 1:
                System.out.println("Llibres");
            case 2:
                System.out.println("Usuaris");
            case 3:
                System.out.println("Préstecs");
            case 0:
                System.out.println("Salir");
            default:
                System.out.println("Opció no vàlida");
        }
        
    }
}
