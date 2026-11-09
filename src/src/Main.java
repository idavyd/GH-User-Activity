import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length > 2 || args.length == 0){System.out.println("Usage github-activity <username>");return;}


        switch (args[0].toLowerCase()){
            case "exit" ->  {
                System.out.print("Session terminated");

            }
            case "github-activity" -> {
                String GHUsername = args[1];

                GitHubClient client = new GitHubClient();

                client.fetchUserActivity(GHUsername);
            }
        }
    }
}
