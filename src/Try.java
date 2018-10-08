import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class Try extends Application {

    ObservableList<Sequence> sequences;

    class Sequence {
        public StringProperty name = new SimpleStringProperty();

        public Sequence(String name) {
            super();
            this.name.set(name);
        }

        @Override
        public String toString() {
            return "null";
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("ComboBoxSample");

        Callback<Sequence, Observable[]> extractor = new Callback<Sequence, Observable[]>() {
            @Override
            public Observable[] call(Sequence s) {
                return new Observable[] { s.name };
            }
        };
        this.sequences = FXCollections.observableArrayList(extractor);
        this.sequences.addAll(new Sequence("Toto"), new Sequence("Titi"));

        ComboBox<Sequence> combo = new ComboBox<>();
        combo.setItems(this.sequences);
        combo.setEditable(true);
        combo.getSelectionModel().selectFirst();
        combo.setConverter(new StringConverter<Try.Sequence>() {
            @Override
            public String toString(Sequence sequence) {
                return sequence.name.get();
            }

            @Override
            public Sequence fromString(String string) {
                System.out.println("call fromString");
                return null;
            }
        });
        combo.valueProperty().addListener((obs, oldValue, newValue) -> System.out.println("Change from " + oldValue.name.get() + " to " + newValue.name.get()));

        Button renameButton = new Button("Rename");

        renameButton.setOnAction(evt -> {
            combo.getValue().name.set(combo.getEditor().getText());
        });

        HBox root = new HBox(combo, renameButton);

        stage.setScene(new Scene(root));
        stage.show();

    }

    public static void main(String... args) {
        launch(args);
    }
}
