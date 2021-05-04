package frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws IOException {
        String userQuery = getUserInput();
        connectToCloudBackend(userQuery);
    }

    private static void connectToCloudBackend(String query) throws IOException {
        System.out.println("Connecting...\n");
        // The url that the request should be sent to
        String querySt = "https://rocky-citadel-89881.herokuapp.com/calc?operation=" + query;
        // A string builder to
        StringBuilder result = new StringBuilder();
        // initializing the url with the wished url
        URL url = new URL(querySt);
        // creating an HTTP url connection to open connection with the url above
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // Defining the request method that is wanted to be used
        conn.setRequestMethod("GET");
        // Reading the input stream from the connection above
        try (var reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            // putting the value of the reader.readLine() into the line variable
            // reader.readLine() will only be null if it reached the end of the stream
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        // Showing the respond that I got back from the url
        System.out.print("ANSWER = " + result.toString());
    }

    // Getting the user input straight forward
    public static String getUserInput() {
        String input = "";
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the cloud calculator!");
        System.out.println("Choose operation");
        System.out.println("1- Addition \n2- Multiplication \n3- Division \n4- Subtraction");
        System.out.print(">> ");

        int choice = in.nextInt();
        switch (choice) {
            case 1:
                input = "add";
                break;
            case 2:
                input = "mul";

                break;
            case 3:
                input = "div";

                break;
            case 4:
                input = "sub";

                break;
            default:
                break;


        }
        int[] theInput = getNumbers();
        //Building the sub-query to be sent to be appended to the url
        input = input + "&numberone=" + theInput[0] + "&numbertwo=" + theInput[1];
        return input;
    }

    //Getting the numbers of the input
    public static int[] getNumbers() {
        System.out.println(" ");
        int[] numbers = new int[2];
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the first #");
        System.out.print(">> ");
        numbers[0] = in.nextInt();
        System.out.println("Enter the second #");
        System.out.print(">> ");
        numbers[1] = in.nextInt();
        return numbers;
    }

}
