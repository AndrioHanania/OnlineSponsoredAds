package com.mabaya.task.onlinesponsoredads.campaign;

import com.mabaya.task.onlinesponsoredads.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "campaigns",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name"})
        })
public class Campaign {
    @Id
    @SequenceGenerator(
            name = "campaign_sequence",
            sequenceName = "campaign_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "campaign_sequence")
    @Column(
            name = "id",
            updatable = false)
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT")
    private String name;

    @Column(
            name = "bid",
            nullable = false,
            columnDefinition = "DECIMAL")
    private double bid;

    @Column(
            name = "start_date",
            nullable = false,
            columnDefinition = "TIMESTAMP")
    private LocalDate startDate;// not createdAt!!!

    @Column(
            name = "active",
            nullable = false,
            columnDefinition = "BOOLEAN")
    private boolean active;

    @ManyToMany
    @JoinTable(
            name = "campaign_products",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>();

    public Campaign(
            String name,
            double bid,
            LocalDate startDate,
            Set<Product> products) {
        this.name = name;
        this.bid = bid;
        this.startDate = startDate;
        this.products = products;
        checkActive(LocalDate.now().minus(10, ChronoUnit.DAYS));
    }

    public void checkActive(LocalDate tenDaysAgo){
        active = !startDate.isBefore(tenDaysAgo);
    }
}
