package com.example.PriceWatch.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.DocFlavor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String nameStore;
    String urlStore;

    @ManyToOne
    @JoinColumn(name = "historicPrice_id", nullable = false)
    HistoricPriceEntity historicPriceId;
}
