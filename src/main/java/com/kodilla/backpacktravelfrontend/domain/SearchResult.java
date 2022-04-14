package com.kodilla.backpacktravelfrontend.domain;

import com.kodilla.backpacktravelfrontend.dto.flightResponseDto.BestFlightDto;
import com.kodilla.backpacktravelfrontend.dto.flightResponseDto.LegsDto;
import com.kodilla.backpacktravelfrontend.dto.flightResponseDto.ItemDto;
import com.kodilla.backpacktravelfrontend.mapper.SkyscannerMapper;
import com.kodilla.backpacktravelfrontend.renderer.CheckboxRenderer;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResult {
    private VerticalLayout mainLayout;
    private Button save = new Button("Save");
    private Button reset = new Button("Reset");
    private H3 filtrTitle = new H3("Filter");
    private H4 transferDetails = new H4("Check flights details");
    private ComboBox sortResultComboBox = new ComboBox();
    private ComboBox sortTransferType = new ComboBox();
    private Grid<Option> resultGrid = new Grid<>(Option.class);
    private Grid<Transfer> transfersGrid = new Grid<>(Transfer.class);

    private BestFlightDto bestFlightDto;
    private Option option;
    private ConfirmDialog confirmDialog = new ConfirmDialog();
    private SkyscannerMapper skyscannerMapper = new SkyscannerMapper();

    public SearchResult(BestFlightDto bestFlightDto) {
        this.bestFlightDto = bestFlightDto;

        mainLayout = new VerticalLayout();

        HorizontalLayout resultGridHeader = new HorizontalLayout();
        filtrTitle.getStyle().set("margin", "0");

        List<String> resultComboBoxItems = bestFlightDto.getItineraries().getOptionsDto().stream().map(m -> m.getId()).collect(Collectors.toList());
        sortResultComboBox.setItems(resultComboBoxItems);
        sortResultComboBox.setValue(resultComboBoxItems.get(0));

        resultGridHeader.setAlignItems(FlexComponent.Alignment.CENTER);
        resultGridHeader.setHeight("var(--lumo-space-xl)");
        resultGridHeader.setFlexGrow(1, filtrTitle);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        reset.setEnabled(false);
        confirmDialog.setEnabled(false);

        save.addClickListener(event -> {
            if(resultGrid.isEnabled()) {
                Notification.show("Check preferred flight before save");

            } else {
                reset.setEnabled(true);
                confirmDialog.setEnabled(true);
                Notification.show("Flight saved");
                confirmDialog.getRentACar().click();
            }
        });

        reset.addClickListener(event -> {
            option.setEnabled(false);
            reset.setEnabled(false);
        });

        resultGridHeader.add(filtrTitle, sortResultComboBox, save, reset, confirmDialog);
        setGridItems(sortResultComboBox.getValue().toString(), resultGrid);
        mainLayout.add(resultGridHeader, resultGrid);

        HorizontalLayout transferHeader = new HorizontalLayout();
        transferDetails.getStyle().set("margin", "0");
        String flightType1 = "Departure flight";
        String flightType2 = "Come back flight";

        sortTransferType.setItems(flightType1, flightType2);
        sortTransferType.setValue(flightType1);
        sortTransferType.setEnabled(false);

        transferHeader.setAlignItems(FlexComponent.Alignment.CENTER);
        transferHeader.setHeight("var(--lumo-space-xl)");
        transferHeader.setFlexGrow(1, transferDetails);
        transferHeader.add(transferDetails, sortTransferType);

        sortResultComboBox.addValueChangeListener(event -> {
            resultGrid.asSingleSelect().clear();
            transfersGrid.asSingleSelect().clear();
            setGridItems(sortResultComboBox.getValue().toString(), resultGrid);

            List<LegsDto> legsDtoList = new ArrayList<>();
            legsDtoList.add(LegsDto.builder()
                    .durationInMinutes(0)
                    .origin(null)
                    .destination(null)
                    .stopCount(0)
                    .departure(null)
                    .arrival(null)
                    .segmentsDto(new ArrayList<>())
                    .build());
            setTransfersGrid(Option.builder().connections(new ArrayList<>(legsDtoList)).build(), transfersGrid);
        });

        resultGrid.addItemClickListener(event -> {
            mainLayout.add(transferHeader);
            transfersGrid.asSingleSelect().clear();
            setTransfersGrid(event.getItem(), transfersGrid);
            setOption(event.getItem());
            mainLayout.add(transfersGrid);
        });
    }

    public VerticalLayout getFlightResultLayout() {
        return mainLayout;
    }

    public void setGridItems(String sortComboBoxValue, Grid<Option> grid) {
        List<ItemDto> itemDtoList = bestFlightDto.getItineraries().getOptionsDto().stream()
                .filter(f -> f.getId().equals(sortComboBoxValue))
                .flatMap(m -> m.getItems().stream())
                .collect(Collectors.toList());

        grid.setColumns("type", "price", "arrivalAtDestination", "transfersDuringDestinationFlight", "comeBackArrival", "transfersDuringComeBackFlight");
        grid.addColumn(CheckboxRenderer.of(Option::getEnabled, Option::setEnabled));
        grid.setItems(skyscannerMapper.mapToOptionList(itemDtoList, sortComboBoxValue));
    }

    public void setTransfersGrid(Option option, Grid<Transfer> grid) {
        grid.setColumns("origin", "destination", "departure", "arrival", "duration", "operator");
        grid.setItems(skyscannerMapper.mapToTransferList(option.getConnections().get(0)));

        if(option.getConnections().size() > 1) {
            sortTransferType.setEnabled(true);

            sortTransferType.addValueChangeListener(event -> {
               if(sortTransferType.equals("Departure flight")) {
                   grid.setItems(skyscannerMapper.mapToTransferList(option.getConnections().get(0)));

               } else {
                   grid.setItems(skyscannerMapper.mapToTransferList(option.getConnections().get(1)));
               }
            });
        }
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public ConfirmDialog getConfirmDialog() {
        return confirmDialog;
    }
}
