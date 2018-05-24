package jp.kotmw.ic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * JavaFXでlaunch(String args)を起動したときに呼び出されるメソッド
     *
     * @param primaryStage 一番最初に表示されるウィンドウのこと
     * @throws Exception エラーが投げられます(適当
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(ClassLoader.getSystemResource("Main.fxml"));
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /**
     * Javaプログラミングでプログラムを起動したときに一番最初に呼び出されるメソッド
     *
     * @param args 起動時に設定した引数
     */
    public static void main(String[] args) {
        launch(args);
    }
}
