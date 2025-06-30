import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConversorMoeda {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Digite a moeda de origem (ex: USD): ");
            String origem = input.nextLine().toUpperCase();

            System.out.print("Digite a moeda de destino (ex: BRL): ");
            String destino = input.nextLine().toUpperCase();

            System.out.print("Digite o valor a ser convertido: ");
            double valor = input.nextDouble();

            String urlStr = String.format("https://api.exchangerate.host/convert?from=%s&to=%s&amount=%f", origem, destino, valor);
            URI uri = URI.create(urlStr);
            HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;

            while ((linha = reader.readLine()) != null) {
                resposta.append(linha);
            }

            reader.close();

            // Pegando o valor manualmente (sem biblioteca JSON)
            String json = resposta.toString();
            int start = json.indexOf("\"result\":") + 9;
            int end = json.indexOf(",", start);

            if (start > 8 && end > start) {
                String numStr = json.substring(start, end).trim();
                double resultado = Double.parseDouble(numStr);

                System.out.printf("Resultado: %.2f %s = %.2f %s%n", valor, origem, resultado, destino);
            } else {
                System.out.println("Erro ao processar a resposta da API.");
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            input.close();
        }
    }
}
