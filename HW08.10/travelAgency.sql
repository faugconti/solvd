-- database creation (mysql only)
DROP SCHEMA IF EXISTS `travel_agency`;

CREATE SCHEMA IF NOT EXISTS `travel_agency` DEFAULT CHARACTER SET utf8;
USE `travel_agency`;

-- table creation
CREATE TABLE IF NOT EXISTS `travel_agency`.`Customer` (
  `idCustomer` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `email` VARCHAR(75) NOT NULL,
  `phone` VARCHAR(45) NULL,
  PRIMARY KEY (`idCustomer`),
  UNIQUE INDEX `Email_UNIQUE` (`email` ASC) VISIBLE
);

CREATE TABLE IF NOT EXISTS `travel_agency`.`Employee` (
  `idEmployee` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(45) NULL,
  `email` VARCHAR(75) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `hireDate` DATE NOT NULL,
  PRIMARY KEY (`idEmployee`),
  UNIQUE INDEX `Email_UNIQUE` (`email` ASC) VISIBLE
);

CREATE TABLE IF NOT EXISTS `travel_agency`.`Trip` (
  `idTrip` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(100) NULL,
  `startDate` DATE NOT NULL,
  `endDate` DATE NOT NULL,
  PRIMARY KEY (`idTrip`)
);

CREATE TABLE IF NOT EXISTS `travel_agency`.`Rating` (
  `idRating` INT NOT NULL AUTO_INCREMENT,
  `idTrip` INT NOT NULL,
  `idCustomer` INT NULL,
  `rating` INT NOT NULL,
  `commentary` VARCHAR(100) NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`idRating`),
  INDEX `fk_Rating_1_idx` (`idCustomer` ASC) VISIBLE,
  INDEX `fk_Rating_Trip1_idx` (`idTrip` ASC) VISIBLE,
  CONSTRAINT `fk_Rating_1`
    FOREIGN KEY (`idCustomer`) REFERENCES `travel_agency`.`Customer` (`idCustomer`),
  CONSTRAINT `fk_Rating_Trip1`
    FOREIGN KEY (`idTrip`)     REFERENCES `travel_agency`.`Trip` (`idTrip`)
);

CREATE TABLE IF NOT EXISTS `travel_agency`.`Destination` (
  `idDestination` INT NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NOT NULL,
  `description` VARCHAR(100) NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idDestination`)
);

CREATE TABLE IF NOT EXISTS `travel_agency`.`Hotel` (
  `idHotel` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(75) NOT NULL,
  `description` VARCHAR(100) NULL,
  PRIMARY KEY (`idHotel`)
);

CREATE TABLE IF NOT EXISTS `travel_agency`.`Booking` (
  `idBooking` INT NOT NULL AUTO_INCREMENT,
  `idCustomer` INT NOT NULL,
  `idEmployee` INT NOT NULL,
  `idTrip` INT NOT NULL,
  `bookingDate` DATE NOT NULL,
  `totalPrice` FLOAT NOT NULL,
  PRIMARY KEY (`idBooking`),
  INDEX `fk_Booking_2_idx` (`idEmployee` ASC) VISIBLE,
  INDEX `fk_Booking_1_idx` (`idCustomer` ASC) VISIBLE,
  INDEX `fk_Booking_3_idx` (`idTrip` ASC) VISIBLE,
  CONSTRAINT `fk_Booking_1`
    FOREIGN KEY (`idCustomer`)  REFERENCES `travel_agency`.`Customer` (`idCustomer`),
  CONSTRAINT `fk_Booking_2`
    FOREIGN KEY (`idEmployee`)  REFERENCES `travel_agency`.`Employee` (`idEmployee`),
  CONSTRAINT `fk_Booking_3`
    FOREIGN KEY (`idTrip`)      REFERENCES `travel_agency`.`Trip` (`idTrip`)
);

CREATE TABLE IF NOT EXISTS `travel_agency`.`Payment` (
  `idPayment` INT NOT NULL AUTO_INCREMENT,
  `idCustomer` INT NOT NULL,
  `idBooking` INT NOT NULL,
  `paymentDate` DATE NOT NULL,
  `amount` FLOAT NOT NULL,
  `paymentMethod` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPayment`),
  INDEX `fk_Payment_1_idx` (`idCustomer` ASC) VISIBLE,
  UNIQUE INDEX `idBooking_UNIQUE` (`idBooking` ASC) VISIBLE,
  CONSTRAINT `fk_Payment_1`
    FOREIGN KEY (`idCustomer`)  REFERENCES `travel_agency`.`Customer` (`idCustomer`),
  CONSTRAINT `fk_Payment_2`
    FOREIGN KEY (`idBooking`)   REFERENCES `travel_agency`.`Booking` (`idBooking`)
);

CREATE TABLE IF NOT EXISTS `travel_agency`.`Excursion` (
  `idExcursion` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(75) NOT NULL,
  `price` FLOAT NOT NULL,
  `duration` INT NULL,
  `description` VARCHAR(100) NULL,
  PRIMARY KEY (`idExcursion`)
);

CREATE TABLE IF NOT EXISTS `travel_agency`.`Transportation` (
  `idTransport` INT NOT NULL AUTO_INCREMENT,
  `company` VARCHAR(45) NOT NULL,
  `destination` VARCHAR(45) NOT NULL,
  `flightNumber` VARCHAR(45) NOT NULL,
  `departureDate` DATE NOT NULL,
  `price` FLOAT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTransport`)
);

CREATE TABLE IF NOT EXISTS `travel_agency`.`Package` (
  `idPackage` INT NOT NULL AUTO_INCREMENT,
  `idTrip` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `basePrice` FLOAT NOT NULL,
  PRIMARY KEY (`idPackage`),
  CONSTRAINT `fk_Package_1`
    FOREIGN KEY (`idPackage`)   REFERENCES `travel_agency`.`Trip` (`idTrip`)
);

CREATE TABLE IF NOT EXISTS `travel_agency`.`PackageDestinations` (
  `idDestination` INT NOT NULL,
  `idPackage` INT NOT NULL,
  PRIMARY KEY (`idDestination`, `idPackage`),
  INDEX `fk_Destination_has_Package_Package1_idx` (`idPackage` ASC) VISIBLE,
  INDEX `fk_Destination_has_Package_Destination1_idx` (`idDestination` ASC) VISIBLE,
  CONSTRAINT `fk_Destination_has_Package_Destination1`
    FOREIGN KEY (`idDestination`)   REFERENCES `travel_agency`.`Destination` (`idDestination`),
  CONSTRAINT `fk_Destination_has_Package_Package1`
    FOREIGN KEY (`idPackage`)       REFERENCES `travel_agency`.`Package` (`idPackage`)
); 

CREATE TABLE IF NOT EXISTS `travel_agency`.`PackageHotel` (
  `idHotel` INT NOT NULL,
  `idPackage` INT NOT NULL,
  PRIMARY KEY (`idHotel`, `idPackage`),
  INDEX `fk_Hotel_has_Package_Package1_idx` (`idPackage` ASC) VISIBLE,
  INDEX `fk_Hotel_has_Package_Hotel1_idx` (`idHotel` ASC) VISIBLE,
  CONSTRAINT `fk_Hotel_has_Package_Hotel1`
    FOREIGN KEY (`idHotel`)     REFERENCES `travel_agency`.`Hotel` (`idHotel`),
  CONSTRAINT `fk_Hotel_has_Package_Package1`
    FOREIGN KEY (`idPackage`)   REFERENCES `travel_agency`.`Package` (`idPackage`)
);

CREATE TABLE IF NOT EXISTS `travel_agency`.`PackageTransport` (
  `idTransport` INT NOT NULL,
  `idPackage` INT NOT NULL,
  PRIMARY KEY (`idTransport`, `idPackage`),
  INDEX `fk_Flight_has_Package_Package1_idx` (`idPackage` ASC) VISIBLE,
  INDEX `fk_Flight_has_Package_Flight1_idx` (`idTransport` ASC) VISIBLE,
  CONSTRAINT `fk_Flight_has_Package_Transport`
    FOREIGN KEY (`idTransport`) REFERENCES `travel_agency`.`Transportation` (`idTransport`),
  CONSTRAINT `fk_Flight_has_Package_Package1`
    FOREIGN KEY (`idPackage`)   REFERENCES `travel_agency`.`Package` (`idPackage`)
);

CREATE TABLE IF NOT EXISTS `travel_agency`.`PackageExcursion` (
  `idExcursion` INT NOT NULL,
  `idPackage` INT NOT NULL,
  PRIMARY KEY (`idExcursion`, `idPackage`),
  INDEX `fk_Excursion_has_Package_Package1_idx` (`idPackage` ASC) VISIBLE,
  INDEX `fk_Excursion_has_Package_Excursion1_idx` (`idExcursion` ASC) VISIBLE,
  CONSTRAINT `fk_Excursion_has_Package_Excursion1`
    FOREIGN KEY (`idExcursion`) REFERENCES `travel_agency`.`Excursion` (`idExcursion`),
  CONSTRAINT `fk_Excursion_has_Package_Package1`
    FOREIGN KEY (`idPackage`)   REFERENCES `travel_agency`.`Package` (`idPackage`)
);

CREATE TABLE IF NOT EXISTS `travel_agency`.`PassportDetails` (
  `idCustomer` INT NOT NULL,
  `idPassport` INT NOT NULL AUTO_INCREMENT,
  `passportNumber` VARCHAR(45) NOT NULL,
  `nationality` VARCHAR(45) NULL,
  `issueDate` DATE NULL,
  `expirationDate` DATE NULL,
  PRIMARY KEY (`idPassport`),
  INDEX `fk_Passport_Customer1_idx` (`idCustomer` ASC) VISIBLE,
  CONSTRAINT `fk_Passport_Customer1`
    FOREIGN KEY (`idCustomer`)  REFERENCES `travel_agency`.`Customer` (`idCustomer`)
);

-- inserts rows for examples

INSERT INTO Customer (firstName, lastName, email, phone) VALUES 
('Joseph', 'Lapena', 'joseph.lapenae@solvd.com', '123-456-7890'),
('Maria', 'Johnson', 'Maria.Johnson@solvd.com', '987-654-3210'),
('Manuel', 'Lister', 'Manuel.j@solvd.com', '654-321-9870'),
('Hera', 'Brown', 'emily.brown@solvd.com', '321-654-0987'),
('Sarah', 'Wilson', 'sarah.w@solvd.com', '876-123-4560');

INSERT INTO Employee (idEmployee, firstName, lastName, phone, email, role, hireDate) VALUES
(1, 'John', 'Doe', '555-1234', 'john.doe@example.com', 'Travel Agent', '2020-05-12'),
(2, 'Jane', 'Smith', '555-5678', 'jane.smith@example.com', 'Manager', '2019-03-15'),
(3, 'Bob', 'Brown', '555-8765', 'bob.brown@example.com', 'Tour Guide', '2021-06-01'),
(4, 'John', 'Doe', '000000', 'john@doe.com', 'Generic Employee', '1310-03-05');

INSERT INTO PassportDetails (idCustomer, passportNumber, nationality, issueDate, expirationDate) VALUES 
(1, 'A12345678', 'USA', '2022-01-15', '2032-01-15'),
(2, 'B87654321', 'Canada', '2020-05-01', '2030-05-01'),
(3, 'C54321987', 'UK', '2021-11-10', '2031-11-10'),
(4, 'D09876543', 'Australia', '2023-04-12', '2033-04-12'),
(5, 'E11223344', 'Germany', '2020-09-05', '2030-09-05');

INSERT INTO Trip (name, description, startDate, endDate) VALUES 
('Champions League Tour', 'A tour across European stadiums', '2024-06-01', '2024-06-15'),
('Asian food adventures', 'Explore Asia', '2024-07-10', '2024-07-25'),
('African Safari', 'Wildlife Safari in Africa', '2024-08-05', '2024-08-20'),
('Andes expedition', 'Cultural exploration in South America', '2024-09-12', '2024-09-28'),
('Apalaches expedition', 'Adventure across North America mountains', '2024-10-01', '2024-10-15');

INSERT INTO Destination (country, description, name) VALUES 
('France', 'The city of lights', 'Paris'),
('Japan', 'The land of the rising sun', 'Tokyo'),
('Kenya', 'Safari paradise', 'Nairobi'),
('Brazil', 'The land of samba', 'Rio de Janeiro'),
('Canada', 'The great outdoors', 'Vancouver');

INSERT INTO Package (idTrip, name, basePrice) VALUES 
(1, 'Premium European Package', 1500.00),
(2, 'Standard Asian Package', 1200.00),
(3, 'Safari Adventure Package', 2000.00),
(4, 'South American Explorer Package', 1800.00),
(5, 'North American Discoverer Package', 1600.00);

INSERT INTO PackageDestinations (idDestination, idPackage) VALUES 
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5);

INSERT INTO Hotel (name, description, address) VALUES 
('Grand Paris Hotel', 'Luxury hotel in Paris','51 Rue Bonnet'),
('Tokyo Central Hotel', 'Modern hotel in Tokyo','187-1277, Hifumicho'),
('Nairobi Safari Lodge', 'Exclusive lodge for safari adventures',' Gor Mahia Rd, 78659-00100 GPO'),
('Rio Grande Hotel', '5-star hotel in Rio de Janeiro next to the beach','Praça Carmo Couri 1480'),
('Overlook Hotel', 'i bet you seen this one in a terror movie...','333 E Wonderview Ave, Estes Park');

INSERT INTO PackageHotel (idHotel, idPackage) VALUES 
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5);

-- INSERT INTO Transportation (company, destination, flight

INSERT INTO Excursion (name, price, duration, description) VALUES 
('Eiffel Tower Tour', 100.00, 2, 'Guided tour of the Eiffel Tower'),
('Mount Fuji Hike', 150.00, 5, 'A guided hike up Mount Fuji, dont bring sharp items'),
('Masai Mara Safari', 300.00, 8, 'Full-day safari experience, please dont harm the animals'),
('Sugarloaf Mountain Tour', 80.00, 3, 'Cable car ride to the summit, bring comfortable clothing'),
('Whale Watching in Vancouver', 120.00, 4, 'Boat trip for whale watching, follow the safety guide'),
('Louvre visit',50,3,'Visit to the most famous museum of the world');

INSERT INTO PackageExcursion (idExcursion, idPackage) VALUES 
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5),(6,1);

INSERT INTO Booking (idCustomer, idTrip, idEmployee, bookingDate, totalPrice) VALUES 
(1, 1, 1, '2024-05-01', 1600.00),
(2, 2, 2, '2024-06-01', 1300.00),
(1, 2, 2, '2024-06-01', 1300.00),
(3, 3, 1, '2024-07-15', 2500.00),
(4, 4, 3, '2024-08-10', 1900.00),
(5, 5, 1, '2024-09-15', 1700.00);

INSERT INTO Rating (idTrip, idCustomer, rating, commentary, date) VALUES
(1,1,9,"a little bit expensive, but a good experience overall",'2024-06-05'),
(2,1,1,"i didn't like the trip, i was too dizzy to enjoy it",'2024-06-25'),
(2,2,6,"had fun",'2024-07-25'),
(3,3,3,"mediocre, badly organized",'2024-09-11'),
(4,4,9,"Excellent",'2024-08-15');

INSERT INTO Payment (idCustomer, idBooking, paymentDate, amount, paymentMethod) VALUES 
(1, 1, '2024-05-05', 1600.00, 'Credit Card'),
(2, 2, '2024-06-05', 1300.00, 'PayPal'),
(3, 4, '2024-07-18', 2500.00, 'Bank Transfer'),
(4, 5, '2024-08-12', 1900.00, 'Credit Card'),
(5, 6, '2024-09-18', 1700.00, 'Cash');

-- Updation
UPDATE Customer SET email = 'jo.la@solvd.com' WHERE idCustomer = 1;
UPDATE Customer SET phone = '111-222-3333' WHERE idCustomer = 2;
UPDATE PassportDetails SET nationality = 'AR' WHERE idCustomer = 1;
UPDATE PassportDetails SET expirationDate = '2035-01-15' WHERE passportNumber = 'B87654321';
UPDATE Trip SET description = 'Soccer based trip' WHERE idTrip = 1;
UPDATE Trip SET startDate = '2024-06-05' WHERE idTrip = 1;
UPDATE Package SET basePrice = 1800.00 WHERE idPackage = 1;
UPDATE Destination SET name = 'Kyoto' WHERE name = 'Tokyo';
UPDATE Transportation SET company = 'Boeing Airlines' WHERE idTransport = 1;

-- Deletions
DELETE FROM PassportDetails where idCustomer = 4;
DELETE FROM Rating WHERE idCustomer = 4;
DELETE FROM Payment where idCustomer = 4;
DELETE FROM Booking WHERE idBooking = 5;
DELETE FROM Customer where idCustomer = 4;
DELETE FROM PackageDestinations where idPackage=4 AND idDestination=4;
DELETE FROM Destination WHERE idDestination = 4;
DELETE FROM PackageExcursion WHERE idPackage = 4 AND idExcursion=4;
DELETE FROM Excursion WHERE idExcursion = 4;
DELETE FROM Employee where idEmployee = 4;

-- Alter tables
ALTER TABLE Customer ADD COLUMN gender VARCHAR(10);
ALTER TABLE PassportDetails MODIFY COLUMN passportNumber VARCHAR(50);
ALTER TABLE Customer ADD CONSTRAINT UNIQUE (email);
ALTER TABLE Hotel DROP COLUMN address;

-- Big Join 
SELECT
    C.firstName,
    C.lastName,
    P.passportNumber,
    T.name AS TripName,
    T.description AS TripDescription,
    PKG.name AS PackageName,
    D.name AS DestinationName,
    HT.name AS HotelName,
    EX.name AS ExcursionName,
    B.totalPrice,
    PAY.amount AS PaymentAmount
FROM Customer C
JOIN PassportDetails P ON C.idCustomer = P.idCustomer
JOIN Booking B ON C.idCustomer = B.idCustomer
JOIN Trip T ON B.idTrip = T.idTrip
JOIN Package PKG ON T.idTrip = PKG.idTrip
JOIN PackageDestinations PD ON PKG.idPackage = PD.idPackage
JOIN Destination D ON PD.idDestination = D.idDestination
JOIN PackageHotel PH ON PKG.idPackage = PH.idPackage
JOIN Hotel HT ON PH.idHotel = HT.idHotel
JOIN PackageExcursion PE ON PKG.idPackage = PE.idPackage
JOIN Excursion EX ON PE.idExcursion = EX.idExcursion
JOIN Payment PAY ON B.idBooking = PAY.idBooking;

-- joins
SELECT C.firstName, B.bookingDate
FROM Customer C
INNER JOIN Booking B ON C.idCustomer = B.idCustomer;

SELECT C.firstName, PAY.amount
FROM Customer C
LEFT JOIN Payment PAY ON C.idCustomer = PAY.idCustomer;

SELECT D.name, PKG.name
FROM Destination D
RIGHT JOIN PackageDestinations PD ON D.idDestination = PD.idDestination
RIGHT JOIN Package PKG ON PD.idPackage = PKG.idPackage;

SELECT C.firstName, R.rating
FROM Customer C
LEFT JOIN Rating R ON C.idCustomer = R.idCustomer;

-- full outer joinish
SELECT T.name, B.idBooking
FROM Trip T
LEFT JOIN Booking B ON T.idTrip = B.idTrip
UNION
SELECT T.name, B.idBooking
FROM Trip T
RIGHT JOIN Booking B ON T.idTrip = B.idTrip;


-- 								aggregate functions/groupyBY without having

-- 1)bookings per client
SELECT C.firstName as name, C.lastName, COUNT(B.idBooking) as bookCount 
FROM Customer C
JOIN Booking B ON C.idCustomer = B.idCustomer
GROUP BY C.firstName,C.lastName;
-- 2)destinations per trip
SELECT T.name as trip, COUNT(D.idDestination) AS Destinations
FROM Trip T
JOIN Package PKG ON T.idTrip = PKG.idTrip
JOIN PackageDestinations PD ON PKG.idPackage = PD.idPackage
JOIN Destination D ON PD.idDestination = D.idDestination
GROUP BY T.name;
-- 3)packages per trip
SELECT idTrip, COUNT(*) AS packageCount
FROM Package
GROUP BY idTrip;
-- 4)bookings per trip
SELECT idTrip, COUNT(idBooking) AS bookingCount
FROM Booking
GROUP BY idTrip;
-- 5) Revenue per trip 
SELECT idTrip, SUM(totalPrice) AS totalRevenue
FROM Booking
GROUP BY idTrip;
-- 6) trips generated by employee
SELECT idEmployee, COUNT(*) AS totalTrips
FROM Booking
GROUP BY idEmployee;
-- 7) average rating per trip
SELECT idTrip, AVG(rating) AS averageRating
FROM Rating
GROUP BY idTrip;

--                                  aggregate functions with groupBy with having

-- 1)Customers with more than 1 bookings
SELECT idCustomer, COUNT(*) AS totalBookings
FROM Booking
GROUP BY idCustomer
HAVING totalBookings > 1;
-- 2)Trips with total revenue above 2000
SELECT idTrip, SUM(totalPrice) AS totalRevenue
FROM Booking
GROUP BY idTrip
HAVING totalRevenue > 2000;
-- 3)Employees who managed more than 2 trips
SELECT idEmployee, COUNT(*) AS totalTrips
FROM Booking
GROUP BY idEmployee
HAVING totalTrips > 2;
-- 4)Trips with an average rating above 4
SELECT idTrip, AVG(rating) AS averageRating
FROM Rating
GROUP BY idTrip
HAVING averageRating > 3;
-- 5)customers who spent more than $1500
SELECT idCustomer, SUM(amount) AS totalSpent
FROM Payment
GROUP BY idCustomer
HAVING totalSpent > 1500;
-- 6)Packages with more than one excursion
SELECT P.name as Package, COUNT(*) as Excursions
FROM Package P
JOIN PackageExcursion PE ON P.idPackage = PE.idPackage 
GROUP BY P.idPackage
HAVING Excursions >1;
-- 7)Trips with more than one customer
SELECT T.name as Trip, COUNT(B.idCustomer) AS Customers
FROM Trip T
JOIN Booking B ON T.idTrip = B.idTrip
GROUP BY T.idTrip, T.name
HAVING COUNT(B.idCustomer) > 1;
