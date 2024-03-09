package com.vedha.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "api")
public class ApiEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String apiName;

    @Column(nullable = false)
    private String apiResTime;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private String apiResponse;
}
