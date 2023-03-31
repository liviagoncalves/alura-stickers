import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
public class Main {
    public static void main(String[] args) throws Exception {

        //fazer uma conexão HTTP e buscar

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        ExtratorDeConteudoIMDB extrator = new ExtratorDeConteudoIMDB();

        //String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2023-01-01&end_date=2023-01-10";
        //ExtratorDeConteudoNasa extrator = new ExtratorDeConteudoNasa();

        var http = new ClienteHttp();
        String json = http.buscarDados(url);

        //Exibir e manipular os dados

        List<Conteudo> conteudos = extrator.extrairConteudos(json);

        var geradora = new GeradoraDeFigurinhas();


        for (int i = 0; i < 10; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }

    }
}