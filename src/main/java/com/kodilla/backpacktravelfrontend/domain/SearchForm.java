package com.kodilla.backpacktravelfrontend.domain;

import com.kodilla.backpacktravelfrontend.dto.airportDto.AirportDto;
import com.kodilla.backpacktravelfrontend.dto.SearchCriteriaDto;
import com.kodilla.backpacktravelfrontend.service.SkyscannerService;
import com.kodilla.backpacktravelfrontend.service.CountriesCitiesService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.binder.Binder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class SearchForm extends FormLayout {
    private static Map<String, String> userSearchChoicesMap = new HashMap<>();

    private ComboBox<String> originCountry = new ComboBox<>("originCountry");
    private ComboBox<String> destinationCountry = new ComboBox<>("destinationCountry");
    private ComboBox<AirportDto> originAirport = new ComboBox<>("originAirport");
    private ComboBox<AirportDto> destinationAirport = new ComboBox<>("destinationAirport");
    private DatePicker departureDate = new DatePicker("departureDate");
    private DatePicker returnDate = new DatePicker("returnDate");
    private IntegerField passengers = new IntegerField("passengers");
    private RadioButtonGroup<String> currency = new RadioButtonGroup<>();
    private RadioButtonGroup<String> way = new RadioButtonGroup<>();

    private Binder<SearchCriteriaDto> binder = new Binder<SearchCriteriaDto>(SearchCriteriaDto.class);

    private Button search = new Button("Search");

    private CountriesCitiesService countryService = CountriesCitiesService.getInstance();
    private SkyscannerService skyscannerService = SkyscannerService.getInstance();
    private SearchCriteriaDto searchCriteriaDto;

    public SearchForm() {
        ComboBox.ItemFilter<AirportDto> filter = (airportDto, filterString) -> airportDto.getCountry().toLowerCase().startsWith(filterString.toLowerCase());

        VerticalLayout mainLayout = new VerticalLayout();

        HorizontalLayout wayLayout = new HorizontalLayout();
        way.setItems("One way", "With return flight");
        way.setValue("With return flight");

        way.addValueChangeListener(event -> {
            if(event.getValue().equals("One way")) {
                returnDate.setEnabled(false);
            } else {
                returnDate.setEnabled(true);
            }
        });

        wayLayout.add(way);

        HorizontalLayout departure = new HorizontalLayout();
        originCountry.setItems(countryService.getCountries());
        originCountry.setPlaceholder("e.g. Poland");
        originAirport.setPlaceholder("e.g. KRK");
        originAirport.getStyle().set("--vaadin-combo-box-overlay-width", "350px");

        originCountry.addValueChangeListener(event -> {
            originAirport.setItems(AirportDto.builder().build());
        });

        originAirport.addFocusListener(event -> {
            if (originCountry.getValue().length() > 3) {
                skyscannerService.setAirportDtoList(originCountry.getValue());
                originAirport.setItems(filter, skyscannerService.getAirportDtoList());
                originAirport.setItemLabelGenerator(airportDto -> airportDto.getIata_code() + ", " +
                        airportDto.getName() + ", " +
                        airportDto.getCity() + ", " +
                        airportDto.getCountry()
                );
            } else {
                Notification.show("Type at least 3 first letters for origin country");
            }
        });

        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        departureDate.setPlaceholder("e.g. 2022-08-06");
        departureDate.setMin(now);
        departureDate.setMax(now.plusYears(100));
        departure.add(originCountry, originAirport, departureDate);

        HorizontalLayout arrival = new HorizontalLayout();
        destinationCountry.setItems(countryService.getCountries());
        destinationCountry.setPlaceholder("e.g. Spain");
        destinationAirport.setPlaceholder("e.g. BCN");
        destinationAirport.getStyle().set("--vaadin-combo-box-overlay-width", "350px");

        destinationCountry.addValueChangeListener(event -> {
            destinationAirport.setItems(AirportDto.builder().build());
        });

        destinationAirport.addFocusListener(event -> {
            if (destinationCountry.getValue().length() > 3) {
                skyscannerService.setAirportDtoList(destinationCountry.getValue());
                destinationAirport.setItems(filter, skyscannerService.getAirportDtoList());
                destinationAirport.setItemLabelGenerator(airportDto -> airportDto.getIata_code() + ", " +
                        airportDto.getName() + ", " +
                        airportDto.getCity() + ", " +
                        airportDto.getCountry()
                );
            } else {
            Notification.show("Type at least 3 first letters for destination country");
        }
        });

        returnDate.setPlaceholder("e.g. 2022-08-20");
        returnDate.setMin(now);
        returnDate.setMax(now.plusYears(100));
        arrival.add(destinationCountry, destinationAirport, returnDate);

        HorizontalLayout criteria = new HorizontalLayout();
        passengers.setValue(1);
        passengers.setHasControls(true);
        passengers.setMin(1);
        passengers.setMax(10);
        passengers.setPrefixComponent(VaadinIcon.USER.create());
        passengers.getElement().setAttribute("aria-label", "user");
        currency.setLabel("Currency");
        currency.setItems("EUR", "USD");
        currency.setValue("EUR");
        criteria.add(passengers, currency);

        HorizontalLayout button = new HorizontalLayout(search);
        search.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        mainLayout.add(wayLayout, departure, arrival, criteria, button);
        add(mainLayout);

        binder.bindInstanceFields(this);
        search.addClickListener(event -> search());
    }

    private void search() {
        SearchCriteriaDto searchCriteriaDto = binder.getBean();
        setSearchCriteriaDto(searchCriteriaDto);
    }

    public SearchCriteriaDto getSearchCriteriaDto() {
        return searchCriteriaDto;
    }

    public void setSearchCriteriaDto(SearchCriteriaDto searchCriteriaDto) {
        binder.setBean(searchCriteriaDto);
        this.searchCriteriaDto = binder.getBean();
    }

    public Button getSearch() {
        return search;
    }
}
