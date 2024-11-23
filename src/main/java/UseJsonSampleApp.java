import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class UseJsonSampleApp {

	public static void main(String[] args) {
		//APIのURLを定義
        String apiUrl = "API URL"; 

        try {
            URL url = new URL(apiUrl);
            
            //HTTPコネクションを確立
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //コネクションのメソッドを定義
            connection.setRequestMethod("GET"); 

            // HTTPステータスのチェック
            int status = connection.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                // レスポンスを取得
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONArray jsonArray = new JSONArray(response.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    System.out.println("----------------------------------------");
                    System.out.print("| no: " + jsonObject.getInt("no") + " | ");
                    System.out.println("ms: " + jsonObject.getString("ms") + " |");
                    System.out.println("----------------------------------------");
                }
            } else {
            	//HTTPステータスチェックでOKじゃなかった場合
                System.out.println("Error: " + status);
            }
            connection.disconnect();
        } catch (Exception e) {
        	System.out.println(new RuntimeException());
        }
    }

}
