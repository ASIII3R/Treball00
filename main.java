import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //print del menú
        System.out.println("gestió de biblioteca");
        System.out.println("1. Llibres");
        System.out.println("2. Usuaris");
        System.out.println("3. Préstecs");
        System.out.println("0. Sortir");
        System.out.print("Escull una opció: ");

        //Elegir una opción del menú principal

        int opcion = scanner.nextInt();
        switch(opcion){
            case 1:
            //Opciones si se escoge el apartado llibres
                System.out.println("Gestió de Llibres\n1. Afegir\n2. Modificar\n3. Eliminar\n4.Llistar\n0. Tornar al menú principal");
                System.out.print("Escull una opció:");
                int opc = scanner.nextInt();

                //Afegir llibre
                if (opc == 1){

                }else if (opc ==2){
                    System.out.println("Modificar");
                }else if(opc == 3){
                    System.out.println("Eliminar");
                }else if (opc == 4){
                    System.out.println("Llistar");
                }
            case 2:
                System.out.println("Usuaris");
            case 3:
                System.out.println("Préstecs");
            case 0:
                System.out.println("Salir");
            default:
                System.out.println("Opció no vàlida");
            scanner.close();
        }
        
    }
}
