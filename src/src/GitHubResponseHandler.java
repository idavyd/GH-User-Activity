import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GitHubResponseHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    void formatUserActivity(HttpResponse response, String username) throws JsonProcessingException {

        String responseToString = response.body().toString();
        if(response.statusCode() == 404){
            System.out.printf("Username '\u001B[31m%s\u001B[0m' not found\n", username);
            System.out.printf("HTTP Status code - \u001B[31m%s\u001B[0m\n", response.statusCode());
        }

        if(response.statusCode() == 200){

            JsonNode events = mapper.readTree(responseToString);

            Map<String, Integer> pushCounts = new HashMap<>();

            events.forEach(event -> {

                String type = event.path("type").asText();

                if (type.equals("PushEvent")) {

                    String repoName = event.path("repo")
                            .path("name")
                            .asText();

                    pushCounts.merge(repoName, 1, Integer::sum);
                }
            });
            pushCounts.forEach((repo, count) -> {
                System.out.printf("%-30s - %d pushes%n", repo, count);
            });

//            events.forEach(event -> {
//                String type = event.get("type").asText();
//
//            });
//            events.get(0).get("actor").get("login").asText();

        }



    }
}
