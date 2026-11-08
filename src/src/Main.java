import java.util.Locale;

public class Main {
    public static void main(String[] args){

        if (args.length > 2 || args.length == 0){System.out.println("Usage github-activity <username>");return;}

        if (args[0].equals("exit")){System.out.println("Session has been terminated");return;}

        switch (args.toString().toLowerCase(Locale.ROOT)){
            case "github-activity" -> {

            }
            default -> System.out.println("");

    }
}
