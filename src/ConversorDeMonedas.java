import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorDeMonedas {
    private final String direccion;
    private final String apiKey;

    public ConversorDeMonedas(String direccion, String apiKey) {
        this.direccion = direccion;
        this.apiKey = apiKey;
    }
    public double convertir(String desde, String hacia, Double cantidad){
        try {
            double tasaDeConversion = getTasaDeConversion(desde, hacia);
            return cantidad * tasaDeConversion;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return 0;
        }


    }
    private double getTasaDeConversion(String desde, String hacia) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion + apiKey +"/latest/" + desde))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        return jsonObject.get("conversion_rates").getAsJsonObject().get(hacia).getAsDouble();

    }
}
