package com.test.base.ui.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

@Route(value = "profil", layout = MainLayout.class)
@PageTitle("Profil")
@Menu(title = "Profil")
public class ProfilView extends VerticalLayout {
    public ProfilView() {
        add(new H1("Page Profil"));

        List<Person> people = callExternalApi();

        Grid<Person> grid = new Grid<>(Person.class);

        TextField filterField = new TextField("Filtrer (nom ou âge)");
        filterField.addValueChangeListener(e -> {
            String filter = e.getValue().trim().toLowerCase();
            if (filter.isEmpty()) {
                grid.setItems(people);
            } else {
                List<Person> filtered = people.stream()
                        .filter(p -> p.name().toLowerCase().contains(filter) ||
                                String.valueOf(p.age()).contains(filter))
                        .toList();
                grid.setItems(filtered);
            }
        });
        filterField.setValueChangeMode(ValueChangeMode.EAGER);

        TextField name = new TextField("Ton nom");
        TextField age = new TextField("Ton age");
        Button addButton = new Button("Ajouter", e -> {
            String enteredName = name.getValue().trim();
            Integer ageValue = Integer.parseInt(age.getValue().trim());

            if (!enteredName.isEmpty()) {
                Person newPerson = new Person(enteredName, ageValue, "Ajout manuel");
                people.add(newPerson);
                grid.setItems(people);
                name.clear();
                age.clear();
            }
        });

        add(name, age, filterField, addButton);
        grid.setItems(people);
        add(grid);
    }

    private List<Person> callExternalApi() {
        List<Person> persons = new ArrayList<>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://pokeapi.co/api/v2/pokemon?limit=-1"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            JSONObject json = new JSONObject(responseBody);
            JSONArray results = json.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject pokemon = results.getJSONObject(i);
                String name = pokemon.getString("name");
                String source = pokemon.getString("url");
                persons.add(new Person(name, 0, source));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }
}