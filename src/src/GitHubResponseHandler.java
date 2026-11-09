import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GitHubResponseHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    private void printActivityDescription(String username, GitHubUserAction action, JsonNode event){
        System.out.printf("- %s %s %s '%s' in %s\n",
                username,
                action.getLabel(),
                event.path("payload").path("ref_type").asText(),
                event.path("payload").path("ref").asText(),
                event.path("repo").path("name").asText());
    }
    void formatUserActivity(HttpResponse<String> response, String username) throws JsonProcessingException {

        switch (response.statusCode()){

            case 404 -> {
                System.out.printf("Username '\u001B[31m%s\u001B[0m' not found\n", username);
                System.out.printf("HTTP Status code - \u001B[31m%s\u001B[0m\n", response.statusCode());
            }

            case 200 -> {

                if (response.body().equals("[]")) {
                    System.out.printf("No recent activity for user '%s' reported\n", username);
                    return;
                }

                JsonNode events = mapper.readTree(response.body());

                Map<String, Integer> pushCounts = new HashMap<>();

                events.forEach(event -> {

                    String type = event.path("type").asText();

                    switch (type){
                        case "PushEvent"-> {
                            String repoName = event.path("repo").path("name").asText();

                            pushCounts.merge(repoName, 1, Integer::sum);

                        }

                        case "CreateEvent" -> this.printActivityDescription(username,GitHubUserAction.CREATE,event);

                        case "DeleteEvent" -> this.printActivityDescription(username,GitHubUserAction.DELETE,event);

                    }

                });
                pushCounts.forEach((repo, count) -> System.out.printf("- %s pushed %d pushes to %s\n",
                        username,
                        count,
                        repo));
            }
        }
        }
}
