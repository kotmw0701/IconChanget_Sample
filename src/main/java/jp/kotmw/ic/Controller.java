package jp.kotmw.ic;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    /**
     * このコントローラーを初期化する処理
     *
     * @param location ろけーしょん
     * @param resources りそーるばんぶ・・・？
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canbas.setImage(imagedraw(0, 0, 0));
    }

    /**
     * Generateボタンが押された時のイベント
     * 入力された数値を読み込んで画像として生成処理をして表示してるよ
     *
     * 【注意】FXMLのonActionに記述してないと呼び出されないよ！
     *
     */
    @FXML
    public void onGenerate() {
        int red = Integer.parseInt(this.red.getText()),
                blue = Integer.parseInt(this.blue.getText()),
                yellow = Integer.parseInt(this.yellow.getText());
        canbas.setImage(imagedraw(red, blue, yellow));
    }

    /**
     * Settingsボタンが押された時のイベント
     * Twitterのトークンとか書くアレを表示する(Oauth2使えよって話は突っ込まないで)
     *
     * 【注意】FXMLのonActionに記述してないと呼び出されないよ！
     */
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

    /**
     * OutPutボタンが押された時のイベント
     * 表示されてる画像をJarが置いてる同じ階層にpngファイルとして生成するよ！
     *
     * 【注意】FXMLのonActionに記述してないと呼び出されないよ！
     * @throws IOException
     */
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

    /**
     * 画像を生成します
     * Graphics2Dを使用して2つの画像を重ねる処理をしています
     * あと入力された数値を重ねた画像の上に描画処理をしています
     *
     * @param red 赤の値
     * @param blue 青の値
     * @param yellow 黄色の値
     * @return 画像処理をしたデータが格納されたImageクラスを返します
     */
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
