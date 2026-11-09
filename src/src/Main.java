import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length > 2 || args.length == 0){System.out.println("Usage github-activity <username>");return;}

        if (args[0].equals("exit")){System.out.println("Session has been terminated");return;}

        if (args[0].toLowerCase().equals("github-activity")) {
            GitHubClient client = new GitHubClient();

            client.fetchUserActivity(args[1].toLowerCase());
        } else {
            System.out.println("Usage github-activity <username>");
        }

    }
}
