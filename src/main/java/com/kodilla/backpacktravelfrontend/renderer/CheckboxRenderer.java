package com.kodilla.backpacktravelfrontend.renderer;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.ValueProvider;

public class CheckboxRenderer<T> extends ComponentRenderer<Checkbox, T> {

    public CheckboxRenderer(ValueProvider<T, Boolean> getter, Setter<T, Boolean> setter) {
        super(Checkbox::new, (checkbox, entity) -> {
            checkbox.setValue(getter.apply(entity));
            checkbox.addValueChangeListener(v -> {
                setter.accept(entity, v.getValue());
                checkbox.getParent().get().getElement().getParent().setEnabled(false);
            });
        });
    }

    public static <T> CheckboxRenderer<T> of(ValueProvider<T, Boolean> getter, Setter<T, Boolean> setter) {
        return new CheckboxRenderer<>(getter, setter);
    }
}
