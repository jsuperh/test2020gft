package com.testinditext.test2020gft.controller;


import com.testinditext.test2020gft.model.PriceResponse;
import com.testinditext.test2020gft.repository.PriceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PriceRepository priceRepository;

    @LocalServerPort
    private int port;

    @Test
    public void getPriceTest1() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        ResponseEntity<PriceResponse> responseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/prices?date={date}&productId={productId}&brandId={brandId}",
                PriceResponse.class,
                date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")),
                productId,
                brandId
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        PriceResponse priceResponse = responseEntity.getBody();
        assertNotNull(priceResponse);
        assertEquals(productId, priceResponse.getProductId());
        assertEquals(brandId, priceResponse.getBrandId());
        assertEquals(1, priceResponse.getPriceList());
        assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0, 0), priceResponse.getStartDate());
        assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), priceResponse.getEndDate());
        assertEquals(new BigDecimal("35.50"), priceResponse.getPrice());

    }

    @Test
    public void getPriceTest2() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;
        String url = createURLWithParams(date, productId, brandId);

        ResponseEntity<PriceResponse> responseEntity = restTemplate.getForEntity(url, PriceResponse.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        PriceResponse priceResponse = responseEntity.getBody();
        assertNotNull(priceResponse);
        assertEquals(productId, priceResponse.getProductId());
        assertEquals(brandId, priceResponse.getBrandId());
        assertEquals(Integer.valueOf(2), priceResponse.getPriceList());
        assertEquals(LocalDateTime.of(2020, 6, 14, 15, 0, 0), priceResponse.getStartDate());
        assertEquals(LocalDateTime.of(2020, 6, 14, 18, 30, 00), priceResponse.getEndDate());
        assertEquals(new BigDecimal("25.45"), priceResponse.getPrice());

    }

    @Test
    public void getPriceTest3() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;
        String url = createURLWithParams(date, productId, brandId);

        ResponseEntity<PriceResponse> responseEntity = restTemplate.getForEntity(url, PriceResponse.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        PriceResponse priceResponse = responseEntity.getBody();
        assertNotNull(priceResponse);
        assertEquals(productId, priceResponse.getProductId());
        assertEquals(brandId, priceResponse.getBrandId());
        assertEquals(Integer.valueOf(1), priceResponse.getPriceList());
        assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0, 0), priceResponse.getStartDate());
        assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), priceResponse.getEndDate());
        assertEquals(new BigDecimal("35.50"), priceResponse.getPrice());

    }

    @Test
    public void getPriceTest4() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;
        String url = createURLWithParams(date, productId, brandId);

        ResponseEntity<PriceResponse> responseEntity = restTemplate.getForEntity(url, PriceResponse.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        PriceResponse priceResponse = responseEntity.getBody();
        assertNotNull(priceResponse);
        assertEquals(productId, priceResponse.getProductId());
        assertEquals(brandId, priceResponse.getBrandId());
        assertEquals(Integer.valueOf(3), priceResponse.getPriceList());
        assertEquals(LocalDateTime.of(2020, 6, 15, 0, 0, 0), priceResponse.getStartDate());
        assertEquals(LocalDateTime.of(2020, 06, 15, 11, 00, 00), priceResponse.getEndDate());
        assertEquals(new BigDecimal("30.50"), priceResponse.getPrice());

    }

    @Test
    public void getPriceTest5() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;
        String url = createURLWithParams(date, productId, brandId);

        ResponseEntity<PriceResponse> responseEntity = restTemplate.getForEntity(url, PriceResponse.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        PriceResponse priceResponse = responseEntity.getBody();
        assertNotNull(priceResponse);
        assertEquals(productId, priceResponse.getProductId());
        assertEquals(brandId, priceResponse.getBrandId());
        assertEquals(Integer.valueOf(4), priceResponse.getPriceList());
        assertEquals(LocalDateTime.of(2020, 6, 15, 16, 0, 0), priceResponse.getStartDate());
        assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), priceResponse.getEndDate());
        assertEquals(new BigDecimal("38.95"), priceResponse.getPrice());

    }



    private String createURLWithParams(LocalDateTime date, Long productId, Long brandId) {
        String baseUrl = "http://localhost:" + port + "/api/prices";
        String dateStr = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss").format(date);
        return UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("date", dateStr)
                .queryParam("productId", productId)
                .queryParam("brandId", brandId)
                .toUriString();
    }





}