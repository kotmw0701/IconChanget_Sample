package jp.kotmw.ic;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TextField yellow, blue, red;
    @FXML
    public ImageView canbas;

    private String path = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canbas.setImage(imagedraw(0, 0, 0));
    }

    public void onGenerate(ActionEvent actionEvent) {
        int red = Integer.parseInt(this.red.getText()),
                blue = Integer.parseInt(this.blue.getText()),
                yellow = Integer.parseInt(this.yellow.getText());
        canbas.setImage(imagedraw(red, blue, yellow));
    }

    @FXML
    public void onSettings() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("Setting.fxml"));
        stage.setTitle("設定");
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

    @FXML
    public void onDownload() throws IOException {
        int red = Integer.parseInt(this.red.getText()),
                blue = Integer.parseInt(this.blue.getText()),
                yellow = Integer.parseInt(this.yellow.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("確認");
        alert.setHeaderText(null);
        alert.setContentText("保存していいですか？(/・ω・)/");
        alert.showAndWait();
        canbas.setImage(imagedraw(red, blue, yellow));
        ImageIO.write(SwingFXUtils.fromFXImage(canbas.getImage(), null), "png", new File(red+""+blue+""+yellow+".png"));
    }

    private Image imagedraw(int red, int blue, int yellow) {
        BufferedImage base = null , paste;
        try {
            base = ImageIO.read(ClassLoader.getSystemResourceAsStream("base.png"));
            paste = ImageIO.read(ClassLoader.getSystemResourceAsStream("paste.png"));
            Graphics2D baseGraphics = base.createGraphics(), pasteGraphics = paste.createGraphics();
            pasteGraphics.setFont(new Font("Linux Biolinum O", Font.PLAIN, 180));
            pasteGraphics.setColor(Color.BLACK);
            pasteGraphics.drawString(""+red, 182, 172);
            pasteGraphics.drawString(""+blue, 77, 281);
            pasteGraphics.drawString(""+yellow, 288, 281);
            baseGraphics.drawImage(paste, null, 880, 880);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SwingFXUtils.toFXImage(base, null);
    }
}
