package com.kodilla.backpacktravelfrontend.view;

import com.kodilla.backpacktravelfrontend.domain.*;
import com.kodilla.backpacktravelfrontend.dto.*;
import com.kodilla.backpacktravelfrontend.dto.flightResponseDto.BestFlightDto;
import com.kodilla.backpacktravelfrontend.service.SkyscannerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "tripSearcher", layout = MainView.class)
@PageTitle("Checkout your next trip!")
@RouteAlias(value = "", layout = MainView.class)
public class FlightsSearcher extends VerticalLayout {
    private SearchForm searchForm = new SearchForm();
    private Label label = new Label();
    private SearchResult searchResult;
    private Button save = new Button("Save");

    private SearchCriteriaDto searchCriteriaDto;
    private BestFlightDto bestFlightDto;
    private SkyscannerService skyscannerService = SkyscannerService.getInstance();

    public FlightsSearcher() {
        setMargin(true);
        setPadding(true);;
        setSpacing(true);

        searchCriteriaDto = SearchCriteriaDto.builder()
                .passengers(1)
                .originCountry(null)
                .originAirport(null)
                .destinationCountry(null)
                .destinationAirport(null)
                .departureDate(null)
                .returnDate(null)
                .currency("EUR")
                .way("With return flight")
                .build();
        searchForm.setSearchCriteriaDto(searchCriteriaDto);
        add(searchForm, label);

        searchForm.getSearch().addClickListener(event -> {
            skyscannerService.setBestFlightDto(searchCriteriaDto);
            bestFlightDto = skyscannerService.getBestFlightDto();

            if(bestFlightDto.getItineraries().getOptionsDto().size() == 0) {
                label.setText("There is no flight available. Change criteria and try again");

            } else {
                label.setText("");
                searchResult = new SearchResult(bestFlightDto);
                add(searchResult.getFlightResultLayout());

                searchResult.getConfirmDialog().getYesButton().addClickListener(e -> {
                    Label label = new Label("Under construction:)");
                    remove(searchResult.getFlightResultLayout());
                    add(label);
                });
            }
        });
    }
}
