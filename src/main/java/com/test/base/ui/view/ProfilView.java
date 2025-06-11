package com.test.base.ui.view;

import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "profil", layout = MainLayout.class) // ← important : layout
@PageTitle("Profil")
@Menu(title = "Profil")
public class ProfilView extends VerticalLayout {
    public ProfilView() {
        add(new H1("Page Profil"));
        List<Person> people = List.of(new Person("Alice", 30), new Person("Bob", 25));

        Grid<Person> grid = new Grid<>(Person.class);
        grid.setItems(people);

        add(grid);
    }
}