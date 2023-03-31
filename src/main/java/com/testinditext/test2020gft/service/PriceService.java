package com.testinditext.test2020gft.service;

import com.testinditext.test2020gft.entity.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {

     Optional<Price> getPrice(LocalDateTime date, Long productId, Long brandId);
}
