package com.smartfarm.smartfarm.service;
import com.smartfarm.smartfarm.entity.MarketPrice;
import com.smartfarm.smartfarm.repositories.MarketPriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketPriceService {
    @Autowired
    private MarketPriceRepo marketPriceRepo;
     public  MarketPrice addPrice(MarketPrice marketPrice){
         return marketPriceRepo.save(marketPrice);

     }
     public  MarketPrice getById(Long id){

         return marketPriceRepo.findById(id).orElseThrow(()->new RuntimeException("market price not found"));
     }

     public List<MarketPrice> getPriceByCrop(String cropName){
         return marketPriceRepo.findByCropNameOrderByDateDesc(cropName);
     }

     public List<MarketPrice> getAllPrices(){
         return marketPriceRepo.findAll();
     }

     public MarketPrice updatedPrice(Long id,MarketPrice updatedMarketPrice){
         MarketPrice price = marketPriceRepo.findById(id).orElseThrow(()->new RuntimeException("Price Not Found "));
         price.setPricePerKg(updatedMarketPrice.getPricePerKg());
         price.setDate(updatedMarketPrice.getDate());
         price.setCropName(updatedMarketPrice.getCropName());

         return marketPriceRepo.save(price);
     }

     public  void deleteMarketPrice(Long id){
          marketPriceRepo.deleteById(id);
     }
}
