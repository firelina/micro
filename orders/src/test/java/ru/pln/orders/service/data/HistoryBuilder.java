package ru.pln.orders.service.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.UpdateTimestamp;
import ru.pln.orders.domains.History;
import ru.pln.orders.domains.Order;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor(staticName = "history")
@With
public class HistoryBuilder implements TestDataBuilder<History> {
    private Integer id = 1;
    private Date updateOrder = new Date();

    @Override
    public History build() {
        final History history = new History();
        history.setId(id);
        history.setUpdateOrder(updateOrder);
        return history;
    }
}
