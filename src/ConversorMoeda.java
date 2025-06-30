import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ConversorMoeda {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite a moeda de origem (ex: USD): ");
        String origem = input.nextLine().toUpperCase();

        System.out.print("Digite a moeda de destino (ex: BRL): ");
        String destino = input.nextLine().toUpperCase();

        System.out.print("Digite o valor a ser convertido: ");
        double valor = input.nextDouble();

        try {
            String urlStr = "https://api.exchangerate.host/convert?from=" + origem + "&to=" + destino + "&amount=" + valor;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            Scanner resp = new Scanner(conn.getInputStream());
            StringBuilder sb = new StringBuilder();
            while (resp.hasNext()) sb.append(resp.nextLine());
            resp.close();
            String json = sb.toString();

            // Busca simplificada do número "result":
            double result = 0;
            String marker = "\"result\":";
            int idx = json.indexOf(marker);
            if (idx != -1) {
                int start = idx + marker.length();
                int end = json.indexOf(",", start);
                if (end == -1) end = json.indexOf("}", start);
                String numStr = json.substring(start, end).trim();
                result = Double.parseDouble(numStr);
            } else {
                throw new RuntimeException("Campo 'result' não encontrado na resposta JSON");
            }

            System.out.printf("\nResultado: %.2f %s = %.2f %s%n", valor, origem, result, destino);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        input.close();
    }
}
