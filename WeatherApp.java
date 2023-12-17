import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * This class creates a table representation of weather information from the OpenWeatherMap API
 * For the information in the table I chose what I thought to be the most important weather info
 */
public class WeatherApp {
    public static void main(String[] args) {

        try {
            //Creating the API request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.openweathermap.org/data/2.5/weather?q=Milwaukee&appid=265062beead14fa619a05408d519a749"))
                    .GET().build();

            //Creating the client
            HttpClient client = HttpClient.newBuilder().build();

            //Getting the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //Creating the JSONObject
            JSONObject object = new JSONObject(response.body());

            //Creating the builder for the table
            StringBuilder table = new StringBuilder();
            table.append("\n---------------------------\n");
            table.append("City: ");

            //The addition of the name of the city from the JSONObject to the table
            String cityName = object.getString("name");
            table.append(cityName);
            table.append("\n---------------------------\n");

            //Creating the main JSONObject
            JSONObject main = object.getJSONObject("main");

            //Getting the temperature and adding to table
            double temperature = main.getDouble("temp");
            table.append(Color.RED).append("Temperature: ").append(Color.RESET).append(temperature).append(" K");

            //Getting the temperature feel and adding to table
            double temperatureFeel = main.getDouble("feels_like");
            table.append(Color.RED).append("\nFeels like: ").append(Color.RESET).append(temperatureFeel).append(" K");
            table.append("\n---------------------------\n");

            //Getting the humidity and adding to table
            double humidity = main.getDouble("humidity");
            table.append(Color.YELLOW).append("Humidity: ").append(Color.RESET).append(humidity);
            table.append("\n---------------------------\n");

            //Creating the wind JSONObject
            JSONObject wind = object.getJSONObject("wind");

            //Getting the wind speed and adding to table
            double windSpeed = wind.getDouble("speed");
            table.append(Color.BLUE).append("Wind Speed: ").append(Color.RESET).append(windSpeed);

            //Getting the wind temperature and adding to table
            double windTemp = wind.getDouble("deg");
            table.append(Color.BLUE).append("\nWind Temperature: ").append(Color.RESET).append(windTemp).append(" K");
            table.append("\n---------------------------\n");

            //Printing final table
            System.out.println(table);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
