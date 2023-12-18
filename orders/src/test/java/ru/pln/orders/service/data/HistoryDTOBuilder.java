package ru.pln.orders.service.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import ru.pln.orders.models.HistoryDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(staticName = "historyDTO")
@With
public class HistoryDTOBuilder implements TestDataBuilder<HistoryDTO> {
    private Integer orderNumber = 1111;
    private List<Date> dates = new ArrayList<>();
    @Override
    public HistoryDTO build(){
        final HistoryDTO historyDTO = HistoryDTO.builder().orderNumber(orderNumber).dates(dates).build();
        return historyDTO;
    }
}
