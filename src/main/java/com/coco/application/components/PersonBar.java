package com.coco.application.components;

import com.coco.celldata.Person;
import com.coco.utils.PersonStatus;
import com.coco.utils.PersonType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

/**
 * ClassName:PersonBar
 * Package:com.coco.application.components
 * Description:
 *
 * @Author Coco
 * @Create 2024/5/6 15:35
 * @Version 1.0
 */
public class PersonBar {
    private final ChoiceBox<PersonType> personTypeChoice = new ChoiceBox<>();
    private final VBox bar = new VBox();
    private Person person = new Person(PersonType.NORMAL,PersonStatus.S);
    private Button save = new Button("save");
    private final ChoiceBox<PersonStatus> personStatusChoice = new ChoiceBox<>();

    /**
     */
    public PersonBar() {
        personTypeChoice.getItems().add(0, PersonType.NORMAL);
        personTypeChoice.getItems().add(1, PersonType.CHILD);
        personTypeChoice.getItems().add(2, PersonType.OLD);
        personTypeChoice.setValue(PersonType.NORMAL);

        personStatusChoice.getItems().add(0, PersonStatus.S);
        personStatusChoice.getItems().add(1, PersonStatus.E);
        personStatusChoice.getItems().add(2, PersonStatus.I);
        personStatusChoice.getItems().add(3, PersonStatus.R);
        personStatusChoice.getItems().add(4, PersonStatus.D);
        personStatusChoice.setValue(PersonStatus.S);

//        save.setOnAction(event -> cell.setStatus(statusChoice.getValue()));
        save.setOnAction(event -> {
            person.setPersonType(personTypeChoice.getValue());
            person.setStatus(personStatusChoice.getValue());
        });
        bar.getChildren().add(personTypeChoice);
        bar.getChildren().add(personStatusChoice);
        bar.getChildren().add(save);
        bar.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
    }

    public VBox getBar() {
        return bar;
    }

    public Person getPerson() {
        return person;
    }

}
