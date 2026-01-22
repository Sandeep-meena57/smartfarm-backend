-- ============================================
-- Flyway baseline schema for Smart Farming Assistant
-- Compatible with MySQL
-- ============================================

-- 1️⃣ Users table
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255)
    -- role, location, soilType, region columns are commented out in entity
);

-- 2️⃣ Crops table
CREATE TABLE crops (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    category VARCHAR(255),
    idealTempMin FLOAT,
    idealTempMax FLOAT,
    idealHumidityMin FLOAT,
    idealHumidityMax FLOAT,
    soilType VARCHAR(255),
    waterRequirement VARCHAR(255),
    plantingSeason VARCHAR(255),
    harvestSeason VARCHAR(255),
    growthDurationDays INT,
    popular BOOLEAN,
    description VARCHAR(1000),
    imageUrl VARCHAR(255)
);

-- 3️⃣ Fertilizer table
CREATE TABLE fertilizer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    recommendedForCrop VARCHAR(255),
    usageInstruction VARCHAR(255)
);

-- 4️⃣ MarketPrice table
CREATE TABLE market_price (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cropName VARCHAR(255),
    pricePerKg DOUBLE,
    date DATE
);

-- 5️⃣ Notification table
CREATE TABLE notification (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message VARCHAR(255),
    cropName VARCHAR(255),
    location VARCHAR(255),
    sent BOOLEAN,
    createdAt DATETIME
);

-- 6️⃣ PestAlert table
CREATE TABLE pest_alert (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    region VARCHAR(255),
    pestName VARCHAR(255),
    description VARCHAR(255),
    date DATE,
    cropAffected VARCHAR(255)
);

-- 7️⃣ FarmingTip table
CREATE TABLE farming_tip (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    content TEXT,
    cropType VARCHAR(255),
    region VARCHAR(255),
    date DATE,
    user_id BIGINT,
    CONSTRAINT fk_farmingtip_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 8️⃣ Query table
CREATE TABLE query (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    questions VARCHAR(255),
    answers VARCHAR(255),
    askedAt DATETIME,
    user_id BIGINT,
    CONSTRAINT fk_query_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 9️⃣ WeatherData table
CREATE TABLE weather_data (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    location VARCHAR(255),
    temperature DOUBLE,
    humidity INT,
    weather_condition VARCHAR(255),
    windSpeed DOUBLE,
    date DATE
);

-- 🔟 UserCrop table
CREATE TABLE user_crop (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    crop_id BIGINT,
    sowingDate DATE,
    areaInAcres DOUBLE,
    notes VARCHAR(255),
    CONSTRAINT fk_usercrop_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_usercrop_crop FOREIGN KEY (crop_id) REFERENCES crops(id)
);
