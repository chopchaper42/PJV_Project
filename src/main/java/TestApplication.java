import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class TestApplication extends Application
{
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            System.out.println("Hello!");
        }
    };
    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception
    {
        Button btn = new Button();
        btn.setText("Print Hello!");
        btn.setTranslateX(150);
        btn.setTranslateY(50);
        btn.setOnAction(event);
        Group root = new Group(btn);
        Scene scene = new Scene(root, 600, 300);
        stage.setTitle("Hello");
        stage.setScene(scene);
        stage.show();
    }
}
