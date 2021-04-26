package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_feels;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    private Text temp_dav;

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            if (!getUserCity.equals("")) {
                String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=61cfa5af390efe7a61a83f5ae61f91f3&units=metric");
                System.out.println(output);
                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    temp_info.setText("Температура: " + obj.getJSONObject("main").getDouble("temp"));
                    temp_feels.setText("Ощущается: " + obj.getJSONObject("main").getDouble("feels_like"));
                    temp_max.setText("Максимум: " + obj.getJSONObject("main").getDouble("temp_max"));
                    temp_min.setText("Минимум: " + obj.getJSONObject("main").getDouble("temp_min"));
                    temp_dav.setText("Давление: " + obj.getJSONObject("main").getDouble("pressure"));
                }
            }
        });

    }
    private static String getUrlContent(String urlAdress){

        StringBuffer content = new StringBuffer();
        try{
            URL url = new URL(urlAdress);
            URLConnection urlCon = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine())!=null){
                content.append(line+"\n");
            }
            bufferedReader.close();
        }catch (Exception e){
            System.out.println("Такой город небыл найден");
        }
        return  content.toString();
    }
}
