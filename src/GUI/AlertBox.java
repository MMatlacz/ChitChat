package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by marcin on 5/15/15.
 */
public class AlertBox {

    private static boolean answer;

    //Pop-up alert window
    public static boolean alertBox(String title, String message) {
        //New stage
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        VBox layout = new VBox(2);
        HBox layout2 = new HBox(1);
        //New label with message
        Label label = new Label(message);
        //Two buttons with options
        Button yes = new Button("tak");
        //Actions on yes or no
        yes.setOnAction(e -> {
            answer = true;
            window.close();
        });
        Button no = new Button("nie");
        no.setOnAction(e -> {
            answer = false;
            window.close();
        });
        //Layout2 additional layout to have yes and no buttons aligned vertically
        layout2.getChildren().addAll(yes, no);
        layout2.setAlignment(Pos.CENTER);
        layout2.setPadding(new Insets(10, 0, 10, 0));
        layout2.setSpacing(50);
        //Layout main layout of this window
        layout.getChildren().addAll(label, layout2);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(5, 5, 5, 5));
        Scene scene = new Scene(layout);
        window.setScene(scene);
        //showAndWait - user cannot overpass this action
        window.showAndWait();
        return answer;

    }
}