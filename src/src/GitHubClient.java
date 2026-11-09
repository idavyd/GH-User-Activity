import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GitHubClient {

    private static final String BASE_URL = "https://api.github.com";

    private final HttpClient client = HttpClient.newHttpClient();

    private final GitHubResponseHandler responseHandler = new GitHubResponseHandler();


    public void fetchUserActivity(String username) throws IOException, InterruptedException {

        String fetchUserActivityURL = BASE_URL + "/users/" + username + "/events";

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(fetchUserActivityURL))
        .GET()
        .build();


        try {
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            responseHandler.formatUserActivity(response, username);
        }
        catch (IOException  e){
            System.out.println("Could not communicate with GitHub: " + e.getMessage());

        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Request was interrupted.");
    }
    }
 }

