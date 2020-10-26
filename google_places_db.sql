-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 26, 2020 at 02:17 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE `google_places_db`;

USE `google_places_db`;

--
-- Database: `google_places_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `placesdata`
--

CREATE TABLE `placesdata` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `place_id` varchar(255) DEFAULT NULL,
  `vicinity` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `placetypes`
--

CREATE TABLE `placetypes` (
  `id` bigint(20) NOT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `placetypes`
--

INSERT INTO `placetypes` (`id`, `type`) VALUES
(1, 'accounting'),
(2, 'airport'),
(3, 'amusement_park'),
(4, 'aquarium'),
(5, 'art_gallery'),
(6, 'atm'),
(7, 'bakery'),
(8, 'bank'),
(9, 'bar'),
(10, 'beauty_salon'),
(11, 'bicycle_store'),
(12, 'book_store'),
(13, 'bowling_alley'),
(14, 'bus_station'),
(15, 'cafe'),
(16, 'campground'),
(17, 'car_dealer'),
(18, 'car_rental'),
(19, 'car_repair'),
(20, 'car_wash'),
(21, 'casino'),
(22, 'cemetery'),
(23, 'church'),
(24, 'city_hall'),
(25, 'clothing_store'),
(26, 'convenience_store'),
(27, 'courthouse'),
(28, 'dentist'),
(29, 'department_store'),
(30, 'doctor'),
(31, 'drugstore'),
(32, 'electrician'),
(33, 'electronics_store'),
(34, 'embassy'),
(35, 'fire_station'),
(36, 'florist'),
(37, 'funeral_home'),
(38, 'furniture_store'),
(39, 'gas_station'),
(40, 'gym'),
(41, 'hair_care'),
(42, 'hardware_store'),
(43, 'hindu_temple'),
(44, 'home_goods_store'),
(45, 'hospital'),
(46, 'insurance_agency'),
(47, 'jewelry_store'),
(48, 'laundry'),
(49, 'lawyer'),
(50, 'library'),
(51, 'light_rail_station'),
(52, 'liquor_store'),
(53, 'local_government_office'),
(54, 'locksmith'),
(55, 'lodging'),
(56, 'meal_delivery'),
(57, 'meal_takeaway'),
(58, 'mosque'),
(59, 'movie_rental'),
(60, 'movie_theater'),
(61, 'moving_company'),
(62, 'museum'),
(63, 'night_club'),
(64, 'painter'),
(65, 'park'),
(66, 'parking'),
(67, 'pet_store'),
(68, 'pharmacy'),
(69, 'physiotherapist'),
(70, 'plumber'),
(71, 'police'),
(72, 'post_office'),
(73, 'primary_school'),
(74, 'real_estate_agency'),
(75, 'restaurant'),
(76, 'roofing_contractor'),
(77, 'rv_park'),
(78, 'school'),
(79, 'secondary_school'),
(80, 'shoe_store'),
(81, 'shopping_mall'),
(82, 'spa'),
(83, 'stadium'),
(84, 'storage'),
(85, 'store'),
(86, 'subway_station'),
(87, 'supermarket'),
(88, 'synagogue'),
(89, 'taxi_stand'),
(90, 'tourist_attraction'),
(91, 'train_station'),
(92, 'transit_station'),
(93, 'travel_agency'),
(94, 'university'),
(95, 'veterinary_care'),
(96, 'zoo');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `placesdata`
--
ALTER TABLE `placesdata`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `placetypes`
--
ALTER TABLE `placetypes`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `placesdata`
--
ALTER TABLE `placesdata`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `placetypes`
--
ALTER TABLE `placetypes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
