package api.destinos.destinos;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class GoogleGeminiClient {
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent";
    @Value("${google.api.key}")
    private String googleApiKey;

    private final OkHttpClient client;

    public GoogleGeminiClient() {
        this.client = new OkHttpClient();
    }

    public String generateDescription(String prompt) throws IOException {
        JSONObject json = new JSONObject();
        JSONArray contents = new JSONArray();
        JSONObject parts = new JSONObject();
        parts.put("text", prompt);
        JSONObject content = new JSONObject();
        content.put("parts", new JSONArray().put(parts));
        contents.put(content);
        json.put("contents", contents);

        JSONArray safetySettings = new JSONArray();
        JSONObject safetySetting = new JSONObject();
        safetySetting.put("category", "HARM_CATEGORY_DANGEROUS_CONTENT");
        safetySetting.put("threshold", "BLOCK_ONLY_HIGH");
        safetySettings.put(safetySetting);
        json.put("safetySettings", safetySettings);

        JSONObject generationConfig = new JSONObject();
        generationConfig.put("stopSequences", new JSONArray().put("Title"));
        generationConfig.put("temperature", 1.0);
        generationConfig.put("maxOutputTokens", 60);
        generationConfig.put("topP", 0.8);
        generationConfig.put("topK", 10);
        json.put("generationConfig", generationConfig);

        HttpUrl url = Objects.requireNonNull(HttpUrl.parse(API_URL)).newBuilder().addQueryParameter("key", googleApiKey).build();

        RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder().url(url).header("Content-Type", "application/json").post(body).build();

        try (Response response = this.client.newCall(request).execute()) {

//            if (!response.isSuccessful() || response.body() == null) {
            if (response.body().contentLength() == 0) {
                request = new Request.Builder().url(url).header("Content-Type", "application/json").post(body).build();
                System.out.println("cair aqui");
            }
            //            throw new IOException("Unexpected code " + response);
            String responseGemini = extractTextFromResponse(response.body().string());
            System.out.println(responseGemini);
            if (responseGemini.length() > 250) {
                System.out.println("cair aqui");
                responseGemini = responseGemini.substring(0, 250);
            }
            return responseGemini;
        }
    }

    private String extractTextFromResponse(String responseBody) throws IOException {
        JSONObject responseJson = new JSONObject(responseBody);
        JSONArray candidates = responseJson.getJSONArray("candidates");
        JSONObject firstCandidate = candidates.getJSONObject(0);
        JSONObject content = firstCandidate.getJSONObject("content");
        JSONArray parts = content.getJSONArray("parts");
        JSONObject firstPart = parts.getJSONObject(0);
        return firstPart.getString("text");
    }
}
