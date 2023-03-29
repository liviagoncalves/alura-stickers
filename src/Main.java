import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

//        fazer uma conexão HTTP e buscar

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

//        Extrair só os dados que interessam (titulo, poster, classificação

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

//        Exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[38;2;255;255;255m\u001b[48;2;3;0;3m\u001b[1mTítulo:\u001b[m " + filme.get("title"));
            System.out.println("\u001b[38;2;255;255;255m\u001b[48;2;255;0;197m\u001b[1mIMagem:\u001b[m " + filme.get("image"));
            System.out.println("\u001b[38;2;255;255;255m\u001b[48;2;0;27;255m\u001b[1mClassificação:\u001b[m " + filme.get("imDbRating"));
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrelinhas = (int) classificacao;
            for (int n = 1; n <= numeroEstrelinhas ; n++) {
                System.out.print("⭐️");
            }
            System.out.println("\n");
        }

    }
}