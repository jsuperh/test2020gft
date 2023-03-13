package com.testinditext.test2020gft.service;

import com.testinditext.test2020gft.model.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public interface PriceService {

     Optional<Price> getPrice(LocalDateTime date, Long productId, Long brandId);
}
