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
                .price(itemDto.getPrice().get("formatted"))
                .notFormattedPrice(itemDto.getPrice().get("raw"))
                .arrival(itemDto.getLegs().get(0).getArrival())
                .duration(LocalTime.of(itemDto.getLegs().get(0).getDurationInMinutes() / 60, itemDto.getLegs().get(0).getDurationInMinutes() % 60))
                .transfers(itemDto.getLegs().get(0).getStopCount())
                .comeBack(null)
                .durationComeBack(null)
                .transfersComeBack(0)
                .connections(new ArrayList<>(itemDto.getLegs()))
                .build();

        if(itemDto.getLegs().size() > 1) {
            option.setComeBack(itemDto.getLegs().get(1).getArrival());
            option.setTransfersComeBack(itemDto.getLegs().get(1).getStopCount());
            option.setDurationComeBack(LocalTime.of(itemDto.getLegs().get(1).getDurationInMinutes() / 60, itemDto.getLegs().get(1).getDurationInMinutes() % 60));
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
