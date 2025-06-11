package com.test.base.ui.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "profil", layout = MainLayout.class) // ← important : layout
@PageTitle("Profil")
@Menu(title = "Profil")
public class ProfilView extends VerticalLayout {
    public ProfilView() {
        add(new H1("Page Profil"));

        List<Person> people = new ArrayList<>(List.of(new Person("Alice", 30), new Person("Bob", 25)));
        Grid<Person> grid = new Grid<>(Person.class);

        TextField name = new TextField("Ton nom");
        TextField age = new TextField("Ton age");
        Button addButton = new Button("Ajouter", e -> {
            String enteredName = name.getValue().trim();
            Integer ageValue = Integer.parseInt(age.getValue().trim());

            if (!enteredName.isEmpty()) {
                Person newPerson = new Person(enteredName, ageValue);
                people.add(newPerson);
                grid.setItems(people);
                name.clear();
                age.clear();
            }
        });

        add(name, age, addButton);

        grid.setItems(people);
        add(grid);
    }

}