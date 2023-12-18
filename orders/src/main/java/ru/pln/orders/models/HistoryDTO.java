package ru.pln.orders.models;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class HistoryDTO implements Serializable {
    private Integer orderNumber;
    private List<Date> dates;
}
