package GUI;

import Analizer.Analizer;
import Analizer.Words;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Created by marcin on 5/5/15.
 */

public class Interface extends Application {
    private static boolean answer;
    private Stage window;
    private int counter = 0;//usun

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("ChitChat");

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        //Main layout
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10, 10, 10, 10));
        //Chat window
        ScrollPane chat = new ScrollPane();
        chat.setMinViewportHeight(300);
        chat.setMinViewportWidth(300);
        chat.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chat.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        GridPane.setConstraints(chat, 0, 0);
        GridPane.setMargin(chat, new Insets(5, 5, 5, 5));
            //setting chat window content
        chat.setContent(new VBox());
        ((VBox) chat.getContent()).setMaxWidth(300);

        //Chat text field
        TextField textPrompt = new TextField();
        textPrompt.setPromptText("Napisz coś! Zatwierdź ENTEREM");
        GridPane.setConstraints(textPrompt, 0, 1);
        GridPane.setMargin(textPrompt, new Insets(5, 5, 5, 5));
        textPrompt.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    //poprzez fasade
                    Analizer.generateAnswer(textPrompt.getText());
                    //do usuniecia bez fasady
                    Words.addText(textPrompt.getText());
                    chat.getContent().minWidth(300);
                    chat.getContent().maxWidth(300);
                    //zrob tu porzadek
                    if (counter % 2 == 0) {
                        Label tmp = new Label(textPrompt.getText());
                        tmp.setStyle("-fx-background-color: yellow; -fx-background-radius: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                        tmp.setPadding(new Insets(5, 5, 5, 5));
                        tmp.setWrapText(true);
                        tmp.setPrefWidth(150);
                        tmp.setAlignment(Pos.CENTER_RIGHT);
                        VBox.setMargin(tmp, new Insets(0, 0, 0, 150));
                        ((VBox) chat.getContent()).getChildren().add(tmp);
                    } else {
                        Label tmp = new Label(textPrompt.getText());
                        tmp.setStyle("-fx-background-color: green; -fx-background-radius: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                        tmp.setPadding(new Insets(5, 5, 5, 5));
                        tmp.setWrapText(true);
                        tmp.setPrefWidth(150);
                        VBox.setMargin(tmp, new Insets(0, 0, 0, 0));
                        ((VBox) chat.getContent()).getChildren().add(tmp);
                    }
                    textPrompt.clear();
                    counter++;
                }
            }
        });
        //Stats
        ScrollPane stats = new ScrollPane();
        stats.setMinViewportHeight(300);
        stats.setMinViewportWidth(300);
        stats.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        stats.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        GridPane.setConstraints(stats, 1, 0);
        GridPane.setMargin(stats, new Insets(5, 5, 5, 5));
        //Ngram mark selector
        TextField mark = new TextField();
        mark.setPromptText("Stopień ngramu Zatwierdź ENTEREM");
        GridPane.setConstraints(mark, 1, 1);
        GridPane.setMargin(mark, new Insets(5, 5, 5, 5));
        //add elements to layout
        layout.getChildren().addAll(chat, textPrompt, stats, mark);
        //New scene
        Scene scene = new Scene(layout, 650, 380);
        //Set windows scene
        window.setScene(scene);
        //Turn window on
        window.show();
        Words.initializeDictionary(MyFileChooser.fileChooser());
    }

    //Action on close request
    private void closeProgram() {
        answer = AlertBox.alertBox("Uwaga!", "Czy chcesz zakończyć pracę programu?");
        if (answer)
            window.close();
    }

    public static void main(String[] args) {
        launch();
    }

}
