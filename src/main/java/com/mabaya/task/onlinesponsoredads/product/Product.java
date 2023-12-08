package com.mabaya.task.onlinesponsoredads.product;

import com.mabaya.task.onlinesponsoredads.category.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"serial_number"})
        })
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence")
    @Column(
            name = "id",
            updatable = false)
    private Long id;

    @Column(
            name = "serial_number",
            nullable = false,
            columnDefinition = "INTEGER")
    private int serialNumber;

    @Column(
            name = "title",
            nullable = false,
            columnDefinition = "TEXT")
    private String title;

    @ManyToOne
    @JoinColumn(nullable = false, name = "category_id")
    private Category category;

    @Column(
            name = "price",
            nullable = false,
            columnDefinition = "DECIMAL")
    private double price;

    public Product(
            int serialNumber,
            String title,
            Category category,
            double price) {
        this.serialNumber = serialNumber;
        this.title = title;
        this.category = category;
        this.price = price;
    }
}
