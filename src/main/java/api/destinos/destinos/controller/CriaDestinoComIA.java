package api.destinos.destinos.controller;


import api.destinos.destinos.GoogleGeminiClient;
import api.destinos.destinos.OpeniaClient;
import api.destinos.destinos.model.Destino;
import api.destinos.destinos.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CriaDestinoComIA implements DestinoDescriptionService {

    @Autowired
    private DestinoRepository destinoRepository;

    @Autowired
    private GoogleGeminiClient geminiClient;

    @Autowired
    private OpeniaClient openiaClient;

    @Autowired
    public CriaDestinoComIA(DestinoRepository destinoRepository, GoogleGeminiClient geminiClient, OpeniaClient openiaClient) {
        this.destinoRepository = destinoRepository;
        this.geminiClient = geminiClient;
        this.openiaClient = openiaClient;
    }

    @Override
    public Destino createDescriptionDestino(Destino destino) {
        if (destino.getDescription().isEmpty()) {
            String prompt = "Faça um resumo sobre " + destino.getName() + ", enfatizando o porque este lugar é incrível. Utilize uma linguagem informal e até 250 caracteres no máximo e sem emojis";
//            destino.setDescription(cleanText(this.createDescriptionWithGoogleGemini(prompt)));
            destino.setDescription(this.createDescriptionWithOpenia(prompt));
        }
        destinoRepository.save(destino);

        return destino;
    }

    private String cleanText(String text) {
        return text.replaceAll("(\r\n|\n|\r){2,}", "\n\n").replaceAll(" +", " ").trim();
    }

    private String createDescriptionWithGoogleGemini(String prompt) {
        try {
            return geminiClient.generateDescription(prompt);
        } catch (IOException e) {
            System.err.println("Error calling Google Gemini API: " + e.getMessage());

            return "";
        }
    }

    private String createDescriptionWithOpenia(String prompt) {
        return openiaClient.generateDescription(prompt);
    }
}


