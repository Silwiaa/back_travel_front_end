package com.kodilla.backpacktravelfrontend.domain;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ConfirmDialog extends Div {
    private Button rentACar;
    private static Button yesButton = new Button("Yes");

    public ConfirmDialog() {
        Dialog dialog = new Dialog();
        dialog.getElement().setAttribute("aria-label", "Add a car");

        VerticalLayout dialogLayout = createDialogLayout(dialog);
        dialog.add(dialogLayout);

        rentACar = new Button("Rent a car", e -> dialog.open());
        add(dialog, rentACar);
    }

    private static VerticalLayout createDialogLayout(Dialog dialog) {
        H2 headline = new H2("Do you need a car after arrival?");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        yesButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button noButton = new Button("No");
        HorizontalLayout buttons = new HorizontalLayout(yesButton, noButton);
        buttons.setSpacing(true);
        buttons.setPadding(true);
        buttons.setAlignItems(FlexComponent.Alignment.CENTER);

        yesButton.addClickListener(event -> {
            dialog.close();
        });

        noButton.addClickListener(event -> {
            dialog.close();
        });

        buttons.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        VerticalLayout dialogLayout = new VerticalLayout(headline, buttons);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        dialogLayout.getStyle().set("width", "300px").set("max-width", "100%");

        return dialogLayout;
    }

    public Button getRentACar() {
        return rentACar;
    }

    public Button getYesButton() {
        return yesButton;
    }
}
