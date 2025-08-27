package com.smartfarm.smartfarm.Config;

import com.smartfarm.smartfarm.entity.*;
import com.smartfarm.smartfarm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataSeeder  {
//
//    @Autowired private UserService userService;
//    @Autowired private CropService cropService;
//    @Autowired private FertilizerService fertilizerService;
//    @Autowired private MarketPriceService marketPriceService;
//    @Autowired private QueryService queryService;
//    @Autowired private WeatherDataService weatherDataService;
//    @Autowired private NotificationService notificationService;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        // Add Users
//        User user1 = new User(null, "Sandeep Meena", "sandeep@gmail.com", "password", "USER", "Bhopal", "Black Soil");
//        User admin = new User(null, "Admin", "admin@smartfarm.com", "admin123", "ADMIN", "Delhi", "Alluvial");
//        user1 = userService.registerUser(user1);
//        admin = userService.registerUser(admin);
//
//        // Add Crops
//        Crop wheat = new Crop(null, "Wheat", "Rabi", "Alluvial", "Requires moderate temperature and rainfall");
//        Crop rice = new Crop(null, "Rice", "Kharif", "Clayey", "Needs heavy rainfall and high temp");
//        cropService.addCrop(wheat);
//        cropService.addCrop(rice);
//
//        // Add Fertilizers
//        Fertilizer urea = new Fertilizer(null, "Urea", "Wheat", "Use 120kg/ha at sowing time");
//        Fertilizer dap = new Fertilizer(null, "DAP", "Rice", "Apply 80kg/ha after 2 weeks");
//        fertilizerService.addFertilizer(urea);
//        fertilizerService.addFertilizer(dap);
//
//        // Add Market Prices
//        marketPriceService.addPrice(new MarketPrice(null, "Wheat", 22.50, LocalDate.now()));
//        marketPriceService.addPrice(new MarketPrice(null, "Rice", 18.75, LocalDate.now().minusDays(1)));
//
//        // Add Weather Data
//        weatherDataService.saveData(new WeatherData(null, "Bhopal", 32.5, 65.0, 12.3, LocalDate.now()));
//        weatherDataService.saveData(new WeatherData(null, "Delhi", 29.0, 55.0, 8.6, LocalDate.now().minusDays(1)));
//
//        // Add Query
//        Query query = new Query();
//        query.setUser(user1);
//        query.setQuestions("How to protect wheat from rust disease?");
//        query.setAnswers("Use rust-resistant varieties and spray fungicide.");
//        query.setAskedAt(java.time.LocalDateTime.now());
//        queryService.submitQuery(query);
//
//        // Add Notification
//        notificationService.createNotification(new Notification(null, "Rain Alert", "Heavy rain expected tomorrow in Bhopal", "WEATHER", LocalDate.now().atStartOfDay()));
//        notificationService.createNotification(new Notification(null, "Fertilizer Tip", "Apply Urea to wheat this week", "TIP", LocalDate.now().atStartOfDay()));
//
//        System.out.println("✅ Dummy data seeded successfully!");
//    }
}
