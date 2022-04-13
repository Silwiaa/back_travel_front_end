package com.kodilla.backpacktravelfrontend.mapper;

import com.kodilla.backpacktravelfrontend.domain.*;
import com.kodilla.backpacktravelfrontend.dto.flightResponseDto.LegsDto;
import com.kodilla.backpacktravelfrontend.dto.flightResponseDto.ItemDto;
import com.kodilla.backpacktravelfrontend.dto.flightResponseDto.SegmentDto;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkyscannerMapper {

    public Option mapToOption(ItemDto itemDto) {
        Option option = Option.builder()
                .enabled(false)
                .type(null)
                .arrivalAtDestination(itemDto.getLegs().get(0).getArrival())
                .transfersDuringDestinationFlight(itemDto.getLegs().get(0).getStopCount())
                .comeBackArrival(null)
                .transfersDuringComeBackFlight(0)
                .price(itemDto.getPrice().get("formatted"))
                .notFormattedPrice(itemDto.getPrice().get("raw"))
                .connections(new ArrayList<>(itemDto.getLegs()))
                .build();

        if(itemDto.getLegs().size() > 1) {
            option.setComeBackArrival(itemDto.getLegs().get(1).getArrival());
            option.setTransfersDuringComeBackFlight(itemDto.getLegs().get(1).getStopCount());
        }

        return option;
    }

    public List<Option> mapToOptionList(List<ItemDto> itemDtoList, String filter) {
        List<Option> optionList = itemDtoList.stream()
                .map(this::mapToOption)
                .collect(Collectors.toList());

        optionList.stream().forEach(o -> o.setType(filter));
        return optionList;
    }

    public List<Transfer> mapToTransferList(LegsDto legsDto) {
        List<Transfer> transfers = new ArrayList<>();

        for(SegmentDto segmentDto : legsDto.getSegmentsDto()) {
            Transfer transfer = Transfer.builder()
                    .origin(segmentDto.getOriginDto().getName())
                    .destination(segmentDto.getDestinationDto().getName())
                    .departure(segmentDto.getDeparture())
                    .arrival(segmentDto.getArrival())
                    .duration(LocalTime.of(segmentDto.getDurationInMinutes() / 60, segmentDto.getDurationInMinutes() % 60))
                    .operator(segmentDto.getOperatingCarrier().getName())
                    .build();
            transfers.add(transfer);
        }
        return transfers;
    }
}
