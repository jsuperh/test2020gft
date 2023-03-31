package com.testinditext.test2020gft.service.impl;

import com.testinditext.test2020gft.entity.Price;
import com.testinditext.test2020gft.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PriceServiceImplTest {

    @InjectMocks
    private PriceServiceImpl priceService;

    @Mock
    private PriceRepository priceRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPrice_NoPricesFound() {
        LocalDateTime date = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;

        when(priceRepository.findPriceByDateProductAndBrand(any(LocalDateTime.class), any(Long.class), any(Long.class)))
                .thenReturn(Collections.emptyList());

        Optional<Price> result = priceService.getPrice(date, productId, brandId);
        assertEquals(false, result.isPresent());
    }

    @Test
    public void testGetPrice_SinglePriceFound() {
        LocalDateTime date = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;

        Price price1 = new Price();
        price1.setId(1L);
        price1.setStartDate(LocalDateTime.now().minusDays(1));
        price1.setEndDate(LocalDateTime.now().plusDays(1));
        price1.setPriceList(1);
        price1.setProductId(productId);
        price1.setPriority(1);
        price1.setPrice(BigDecimal.valueOf(100));
        price1.setCurrency("EUR");

        when(priceRepository.findPriceByDateProductAndBrand(any(LocalDateTime.class), any(Long.class), any(Long.class)))
                .thenReturn(Collections.singletonList(price1));

        Optional<Price> result = priceService.getPrice(date, productId, brandId);
        assertEquals(true, result.isPresent());
        assertEquals(price1, result.get());
    }

    @Test
    public void testGetPrice_MultiplePricesFound() {
        LocalDateTime date = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;

        Price price1 = new Price();
        price1.setId(1L);
        price1.setStartDate(LocalDateTime.now().minusDays(1));
        price1.setEndDate(LocalDateTime.now().plusDays(1));
        price1.setPriceList(1);
        price1.setProductId(productId);
        price1.setPriority(1);
        price1.setPrice(BigDecimal.valueOf(100));
        price1.setCurrency("EUR");

        Price price2 = new Price();
        price2.setId(2L);
        price2.setStartDate(LocalDateTime.now().minusDays(1));
        price2.setEndDate(LocalDateTime.now().plusDays(1));
        price2.setPriceList(2);
        price2.setProductId(productId);
        price2.setPriority(2);
        price2.setPrice(BigDecimal.valueOf(200));
        price2.setCurrency("EUR");

        List<Price> prices = Arrays.asList(price1, price2);
        when(priceRepository.findPriceByDateProductAndBrand(any(LocalDateTime.class), any(Long.class), any(Long.class)))
                .thenReturn(prices);

        Optional<Price> result = priceService.getPrice(date, productId, brandId);
        assertEquals(true, result.isPresent());
        assertEquals(price2, result.get()); // price2 tiene la prioridad más alta
    }
}