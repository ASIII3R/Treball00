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
                    afegirLlibre(scanner);
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
    public static void afegirLlibre(Scanner scanner){
            System.out.println("Escriu el nom del llibre: ");
            int prueba = scanner.nextInt();          //ARREGLAR ESTO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            String nomllibre = scanner.nextLine();


            //comprobar que el nom de l'autor no està buit, té 1 caràcter o conté números entre d'altres coses:
            System.out.println("Escriu el nom de l'autor: ");
            String autor = scanner.nextLine();

            //bucle per comprobar que autor no sigui buit, 1 caràcter o tingui números.
            while (autor.trim().isEmpty()||autor.length()<2||autor.matches(".*\\d.*")){
                System.out.println("Insereix un nom vàlid");
                System.out.print("Escriu el nom de l'autor: ");
                autor = scanner.nextLine();
            }



            System.out.print("El llibre escollit està en préstec? s/n ");
            String siono = scanner.nextLine().toLowerCase();

            //si es que si, que prestec sigui true i si és que no, que préstec sigui false
            if (siono == "s"){
                boolean prestec = true;
            }else if (siono == "n"){
                boolean prestec = false;
            }
            //si no es posa un caràcter vàlid, que torni a demanar una opció
            while (!siono.equals("s")&& !siono.equals("n ")){
                System.out.print("Escull una opció vàlida: s (si) o n (no) ");
                System.out.print("El llibre escollit està en préstec? s/n ");
                siono = scanner.nextLine().toLowerCase();
            }
        }
}
