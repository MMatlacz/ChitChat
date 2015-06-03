package GUI;

import Analizer.Analizer;
import Analizer.Words;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by marcin on 5/5/15.
 */

public class Interface extends Application {
    private static boolean answer;
    private static boolean isMarkSet = false;
    private Stage window;


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


        //Stats
        ScrollPane stats = new ScrollPane();
        stats.setMinViewportHeight(300);
        stats.setMinViewportWidth(300);
        stats.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        stats.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        GridPane.setConstraints(stats, 1, 0);
        GridPane.setMargin(stats, new Insets(5, 5, 5, 5));
        //Wykres
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> bc = new BarChart<Number, String>(xAxis, yAxis);
        xAxis.setLabel("Liczba wystąpień");
        //xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Wyrazy");

        //wymiary wykresu
        bc.setPrefSize(300, 300);

        //dodaj obiekt BarChart
        stats.setContent(bc);


        //Chat text field
        TextField textPrompt = new TextField();
        textPrompt.setPromptText("Napisz coś! Zatwierdź ENTEREM");
        GridPane.setConstraints(textPrompt, 0, 1);
        GridPane.setMargin(textPrompt, new Insets(5, 5, 5, 5));
        //Ngram mark selector
        TextField mark = new TextField();
        mark.setPromptText("Stopień ngramu Zatwierdź ENTEREM");
        GridPane.setConstraints(mark, 1, 1);
        GridPane.setMargin(mark, new Insets(5, 5, 5, 5));
        //add elements to layout
        layout.getChildren().addAll(chat, textPrompt, stats, mark);
        //Action on setting mark
        mark.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    //domyślny
                    int value = 2;
                    try {
                        value = Integer.decode(mark.getText());
                    } catch (NumberFormatException e) {
                        //ustaw domyślny
                        value = 2;
                    }
                    if (value > 0) {
                        Analizer.setMark(value);
                        layout.getChildren().removeAll(mark);
                        Label markLabel = new Label(mark.getText());
                        GridPane.setConstraints(markLabel, 1, 1);
                        GridPane.setMargin(markLabel, new Insets(5, 5, 5, 5));
                        layout.getChildren().addAll(markLabel);
                    }

                    Words.initializeDictionary(MyFileChooser.fileChooser());
                    isMarkSet = true;
                }
            }
        });
        //Sterowanie aktualizacją
        textPrompt.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    String ans = null;
                    chat.getContent().minWidth(300);
                    chat.getContent().maxWidth(300);
                    Label tmp = new Label(textPrompt.getText());
                    tmp.setStyle("-fx-background-color: green; -fx-background-radius: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                    tmp.setPadding(new Insets(5, 5, 5, 5));
                    tmp.setWrapText(true);
                    tmp.setPrefWidth(150);
                    VBox.setMargin(tmp, new Insets(0, 0, 0, 0));
                    ((VBox) chat.getContent()).getChildren().add(tmp);

                    //przetworzenie wpisanego textu
                    if(isMarkSet) {
                        ans = Analizer.generateAnswer(textPrompt.getText());
                        //odpowiedz
                        Label answer = new Label(ans);
                        answer.setStyle("-fx-background-color: yellow; -fx-background-radius: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                        answer.setPadding(new Insets(5, 5, 5, 5));
                        answer.setWrapText(true);
                        answer.setPrefWidth(150);
                        answer.setAlignment(Pos.CENTER_RIGHT);
                        VBox.setMargin(answer, new Insets(0, 0, 0, 150));
                        ((VBox) chat.getContent()).getChildren().add(answer);
                        textPrompt.clear();
                        //aktualizuj statystyki
                        bc.getData().clear();
                        bc.getData().addAll(Analizer.setStats());
                        chat.setVvalue(1);
                    } else {
                        ans = "Ustaw stopień ngramów!";
                        Label answer = new Label(ans);
                        answer.setStyle("-fx-background-color: yellow; -fx-background-radius: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                        answer.setPadding(new Insets(5, 5, 5, 5));
                        answer.setWrapText(true);
                        answer.setPrefWidth(150);
                        answer.setAlignment(Pos.CENTER_RIGHT);
                        VBox.setMargin(answer, new Insets(0, 0, 0, 150));
                        ((VBox) chat.getContent()).getChildren().add(answer);
                        textPrompt.clear();
                    }
                }
            }
        });

        //New scene
        Scene scene = new Scene(layout, 650, 380);
        //Set windows scene
        window.setScene(scene);
        //Turn window on
        window.show();
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
