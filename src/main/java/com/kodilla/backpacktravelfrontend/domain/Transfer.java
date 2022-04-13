package com.kodilla.backpacktravelfrontend.domain;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    private String origin;
    private String destination;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private LocalTime duration;
    private String operator;
}
