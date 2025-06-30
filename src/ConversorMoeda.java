import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class ConversorMoeda {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite a moeda de origem (ex: USD): ");
        String moedaOrigem = input.nextLine().toUpperCase();

        System.out.print("Digite a moeda de destino (ex: BRL): ");
        String moedaDestino = input.nextLine().toUpperCase();

        System.out.print("Digite o valor a ser convertido: ");
        double valor = input.nextDouble();

        try {
            String urlString = "https://api.exchangerate.host/convert?from=" + moedaOrigem + "&to=" + moedaDestino + "&amount=" + valor;
            URL url = new URL(urlString);

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            Scanner respostaScanner = new Scanner(conexao.getInputStream());
            StringBuilder resposta = new StringBuilder();
            while (respostaScanner.hasNext()) {
                resposta.append(respostaScanner.nextLine());
            }
            respostaScanner.close();

            JSONObject json = new JSONObject(resposta.toString());
            double resultado = json.getDouble("result");

            System.out.println("\nResultado: " + valor + " " + moedaOrigem + " = " + resultado + " " + moedaDestino);

        } catch (Exception e) {
            System.out.println("Erro ao consultar a API: " + e.getMessage());
        }
    }
}
