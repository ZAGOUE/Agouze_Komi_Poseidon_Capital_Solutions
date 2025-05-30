package com.nnk.springboot.domain;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "curvepoint")
@Data
public class CurvePoint {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Positive(message = "Curve ID must be positive")
    @NotNull(message = "Curve ID is mandatory")
    private Integer curveId;

    private Timestamp asOfDate;

    @Positive(message = "Term must be positive")
    @NotNull(message = "Term is mandatory")
    private Double term;

    @Positive(message = "Value must be positive")
    @NotNull(message = "Value is mandatory")
    private Double value;

    private Timestamp creationDate;
}
