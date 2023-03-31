package com.testinditext.test2020gft.service.impl;

import com.testinditext.test2020gft.entity.Price;
import com.testinditext.test2020gft.repository.PriceRepository;
import com.testinditext.test2020gft.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Optional<Price> getPrice(LocalDateTime date, Long productId, Long brandId) {
        List<Price> prices = priceRepository.findPriceByDateProductAndBrand(date, productId, brandId);

        if (prices.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(prices.get(0));
        /*else if (prices.size() == 1) {
            return Optional.of(prices.get(0));
        } else {
            return Optional.of(Collections.max(prices, Comparator.comparing(Price::getPriority)));
        }*/

    }
}
