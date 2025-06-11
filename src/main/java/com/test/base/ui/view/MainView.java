package com.test.base.ui.view;

import java.util.List;

import com.test.base.ui.component.ViewToolbar;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;

/**
 * This view shows up when a user navigates to the root ('/') of the application.
 */
@Route
@PermitAll // When security is enabled, allow all authenticated users
public final class MainView extends Main {

    // TODO Replace with your own main view.

    MainView() {
        addClassName(LumoUtility.Padding.MEDIUM);
        add(new ViewToolbar("Main"));
        add(new Div("Please select a view from the menu on the left. BONJKOUfzeR"));
        add(new Button("Clique-moi", e -> Notification.show("Bonjour Vaadin !")));

        TextField name = new TextField("Ton nom");
        Button hello = new Button("Dire bonjour", e -> Notification.show("Bonjour " + name.getValue() + " !"));
        add(name, hello);


    }

    /**
     * Navigates to the main view.
     */
    public static void showMainView() {
        UI.getCurrent().navigate(MainView.class);
    }
}
