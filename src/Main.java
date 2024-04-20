import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String apiKey = getApiKey();
        if (apiKey == null) {
            System.out.println("No se encontró la variable de entorno API_KEY");
            return;
        }
        ConversorDeMonedas conversor = new ConversorDeMonedas("https://v6.exchangerate-api.com/v6/", apiKey);
        while (true) {
            int option = menu();
            if (option == 7) {
                break;
            }
            double amount = getAmount();
            double result = 0;
            switch (option) {
                case 1:
                    result = conversor.convertir("USD", "ARS", amount);
                    break;
                case 2:
                    result = conversor.convertir("ARS", "USD", amount);
                    break;
                case 3:
                    result = conversor.convertir("USD", "BRL", amount);
                    break;
                case 4:
                    result = conversor.convertir("BRL", "USD", amount);
                    break;
                case 5:
                    result = conversor.convertir("USD", "COP", amount);
                    break;
                case 6:
                    result = conversor.convertir("COP", "USD", amount);
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
            System.out.println("Resultado: " + result);
        }
    }

    private static double getAmount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa la cantidad: ");
        return scanner.nextDouble();
    }

    public static String getApiKey() {
        return System.getenv("API_KEY");
    }
    public static int menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Dolar => Peso Argentino");
        System.out.println("2. Peso Argentino => Dolar");
        System.out.println("3. Dolar => Real Brasileño");
        System.out.println("4. Real Brasileño => Dolar");
        System.out.println("5. Dolar => Peso Colombiano");
        System.out.println("6. Peso Colombiano => Dolar");
        System.out.println("7. Salir");
        System.out.print("Ingresa tu opción: ");
        return scanner.nextInt();
    }
}