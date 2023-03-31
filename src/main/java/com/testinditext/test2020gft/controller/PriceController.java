package com.testinditext.test2020gft.controller;

import com.testinditext.test2020gft.entity.Price;
import com.testinditext.test2020gft.exception.PriceNotFoundException;
import com.testinditext.test2020gft.model.PriceResponse;
import com.testinditext.test2020gft.service.PriceService;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam(name = "date")
            @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
            @NotNull(message = "La fecha no puede ser nula")
            LocalDateTime date,
            @RequestParam(name = "productId")
            @NotNull(message = "El ID del producto no puede ser nulo")
            Long productId,
            @RequestParam(name = "brandId")
            @NotNull(message = "El ID de la cadena no puede ser nulo")
            Long brandId
    ) {
        try{
            Optional<Price> optionalPrice = priceService.getPrice(date, productId, brandId);
            if (optionalPrice.isPresent()) {
                Price price = optionalPrice.get();
                return ResponseEntity.ok(PriceResponse.builder()
                        .productId(price.getProductId())
                        .brandId(price.getBrand().getId())
                        .priceList(price.getPriceList())
                        .startDate(price.getStartDate())
                        .endDate(price.getEndDate())
                        .price(price.getPrice())
                        .build());
            } else {
                throw new PriceNotFoundException(
                        String.format("No se encontró ningún precio para la fecha %s, la cadena %d y el producto %d",
                                date,
                                brandId,
                                productId));
            }
        } catch (PriceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }

    // Controlador de excepciones
    @ExceptionHandler(PriceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, String>> handlePriceNotFoundException(PriceNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("mensaje", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(NonUniqueResultException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, String>> handleNonUniqueResultException(NonUniqueResultException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("mensaje", "Se encontró más de un precio para los parámetros especificados");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
