-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 23, 2018 at 11:39 AM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mymenu`
--

-- --------------------------------------------------------

--
-- Table structure for table `cool_items`
--

CREATE TABLE `cool_items` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cool_items`
--

INSERT INTO `cool_items` (`id`, `name`, `image`, `price`) VALUES
(1, 'coca cola', 'coca cola', 30),
(2, 'limca', 'limca', 30),
(3, 'mazza', 'mazza', 30),
(4, 'pepsi', 'pepsi', 30);

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `feedback` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ice_items`
--

CREATE TABLE `ice_items` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ice_items`
--

INSERT INTO `ice_items` (`id`, `name`, `image`, `price`) VALUES
(1, 'strawberry', 'strawberry', 45),
(2, 'raspberry', 'raspberry', 65),
(3, 'vanilla', 'vanilla', 35),
(4, 'chocolate', 'chocolate', 75);

-- --------------------------------------------------------

--
-- Table structure for table `menu_detail`
--

CREATE TABLE `menu_detail` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu_detail`
--

INSERT INTO `menu_detail` (`id`, `name`, `image`, `price`) VALUES
(1, 'chicken dum', 'chicken dum', 250),
(2, 'chicken joint', 'chicken joint', 270),
(3, 'chicken wing', 'chicken wing', 245),
(4, 'chicken manchuria', 'chicken manchuria', 170),
(5, 'chilli chicken', 'chilli chicken', 220),
(6, 'chilli baby corn', 'chilli baby corn', 190);

-- --------------------------------------------------------

--
-- Table structure for table `totalc`
--

CREATE TABLE `totalc` (
  `name` varchar(50) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `tablebooked` varchar(10) NOT NULL,
  `total` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `totalc`
--

INSERT INTO `totalc` (`name`, `phone`, `tablebooked`, `total`) VALUES
('ssdf@gmail.com', '234', '2', 270);

-- --------------------------------------------------------

--
-- Table structure for table `veg_items`
--

CREATE TABLE `veg_items` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `veg_items`
--

INSERT INTO `veg_items` (`id`, `name`, `image`, `price`) VALUES
(1, 'puneer', 'puneer', 250),
(2, 'vegbiryani', 'vegbiryani', 270),
(3, 'veg shangai', 'veg shangai', 245),
(4, 'ulavacharu', 'ulavacharu', 170);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cool_items`
--
ALTER TABLE `cool_items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ice_items`
--
ALTER TABLE `ice_items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `menu_detail`
--
ALTER TABLE `menu_detail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `veg_items`
--
ALTER TABLE `veg_items`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cool_items`
--
ALTER TABLE `cool_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `ice_items`
--
ALTER TABLE `ice_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `menu_detail`
--
ALTER TABLE `menu_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `veg_items`
--
ALTER TABLE `veg_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
