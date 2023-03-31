package com.testinditext.test2020gft.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PriceResponse {
    private Long productId;

    private Long brandId;

    private Integer priceList;


    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime endDate;

    private BigDecimal price;

}
