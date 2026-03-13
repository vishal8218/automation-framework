package db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FirebaseUtils {

    // Replace with your Firebase database URL


    // -------------------------------
    // INSERT DATA INTO FIREBASE
    // -------------------------------
    public static void insertData(String path, String jsonData) {

        try {

            URL url = new URL(FirebaseConfig.FIREBASE_DB_URL + path + ".json");

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(jsonData.getBytes());
            os.flush();
            os.close();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // -------------------------------
    // GET DATA FROM FIREBASE
    // -------------------------------
    public static String getData(String path) {

        StringBuilder result = new StringBuilder();

        try {

            URL url = new URL(FirebaseConfig.FIREBASE_DB_URL + path + ".json");

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
      System.out.println(result);
        return result.toString();
    }

}