package api.destinos.destinos;

import api.destinos.destinos.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClientResponseException;


@Component
public class OpeniaClient {


    @Value("${openai.api.key}")
    private String apiKey;

    @Autowired
    private DestinoRepository destinoRepository;


    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public String generateDescription(String prompt) {
        try {
            WebClient webClient = webClientBuilder.build();
            String requestJson = "{ \"model\": \"gpt-4o-mini\", \"prompt\": \"" + prompt + "\", \"max_tokens\": 100 }";
            Mono<String> responseMono = webClient.post().uri(API_URL).header("Authorization", "Bearer " + apiKey).header("Content-Type", "application/json").bodyValue(requestJson).retrieve().bodyToMono(String.class);
            System.out.println(responseMono.block());
            return responseMono.block();  //
        } catch (WebClientResponseException e) {
            System.err.println("Erro na requisição: " + e.getStatusCode());
            System.err.println("Corpo da resposta de erro: " + e.getResponseBodyAsString());
            throw e;
        }
    }
}
