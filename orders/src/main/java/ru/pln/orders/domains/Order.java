package ru.pln.orders.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private Integer number;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_order"))
    private Set<ProductOrder> productOrders = new HashSet<>();
    @Column(name = "creation_order", nullable = false, updatable = false)
    @CreationTimestamp
    private Date creationOrder;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "history_id", foreignKey = @ForeignKey(name = "fk_history"))
    private Set<History> histories;
}