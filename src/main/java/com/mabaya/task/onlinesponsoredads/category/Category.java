package com.mabaya.task.onlinesponsoredads.category;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name"})
        })
public class Category {
    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_sequence")
    @Column(
            name = "id",
            updatable = false)
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT")
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
