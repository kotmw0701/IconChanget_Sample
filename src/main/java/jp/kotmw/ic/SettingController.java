package jp.kotmw.ic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class SettingController implements Initializable {

    @FXML
    public TextField cKey, cSecret, aToken, aTokenSecret;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Properties properties = new Properties();
        File file = new File("twitter.properties");
        try {
            if(!file.exists())
                file.createNewFile();
            properties.load(new FileInputStream("twitter.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cKey.setText(properties.getProperty("consumerKey"));
        cSecret.setText(properties.getProperty("consumerSecret"));
        aToken.setText(properties.getProperty("accessToken"));
        aTokenSecret.setText(properties.getProperty("accessTokenSecret"));
    }

    @FXML
    public void onButton(ActionEvent actionEvent) throws IOException {
        Properties properties = new Properties();
        String consumerKey = checknull(cKey.getText()),
                consumerSecret = checknull(cSecret.getText()),
                accessToken = checknull(aToken.getText()),
                accessTokenSecret = checknull(aTokenSecret.getText());
        properties.setProperty("consumerKey", consumerKey);
        properties.setProperty("consumerSecret", consumerSecret);
        properties.setProperty("accessToken", accessToken);
        properties.setProperty("accessTokenSecret", accessTokenSecret);

        properties.store(new FileOutputStream("twitter.properties"), "comment");
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    private String checknull(String str) {
        return (str == null ? "" : str);
    }
}
