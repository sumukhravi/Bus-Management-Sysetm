-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 22, 2018 at 08:48 AM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bus`
--

-- --------------------------------------------------------

--
-- Table structure for table `bustable`
--

CREATE TABLE `bustable` (
  `BId` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Number` varchar(100) NOT NULL,
  `Type` varchar(100) NOT NULL,
  `RId` varchar(20) NOT NULL,
  `Make` varchar(50) NOT NULL,
  `Model` varchar(50) NOT NULL,
  `seats` varchar(10) NOT NULL,
  `DId` varchar(10) NOT NULL,
  `CId` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bustable`
--

INSERT INTO `bustable` (`BId`, `Name`, `Number`, `Type`, `RId`, `Make`, `Model`, `seats`, `DId`, `CId`) VALUES
(2, 'jjn', '5455', 'hjbkj', '6554', ',nbn', 'klj', '54', 'jkh', 'dds');

-- --------------------------------------------------------

--
-- Table structure for table `employeetable`
--

CREATE TABLE `employeetable` (
  `EId` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Designation` varchar(100) DEFAULT NULL,
  `BId` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employeetable`
--

INSERT INTO `employeetable` (`EId`, `Name`, `Designation`, `BId`) VALUES
(1, 'dds', 'Conductor', 0),
(2, 'jkh', 'Driver', 0);

-- --------------------------------------------------------

--
-- Table structure for table `passengertable`
--

CREATE TABLE `passengertable` (
  `TicketId` int(11) DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Age` varchar(50) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `routetable`
--

CREATE TABLE `routetable` (
  `Id` int(11) NOT NULL,
  `RId` int(11) NOT NULL,
  `Station_Code` varchar(100) DEFAULT NULL,
  `Dist_From_Src` varchar(100) DEFAULT NULL,
  `Arr_Time` varchar(100) DEFAULT NULL,
  `Depart_Time` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `routetable`
--

INSERT INTO `routetable` (`Id`, `RId`, `Station_Code`, `Dist_From_Src`, `Arr_Time`, `Depart_Time`) VALUES
(1, 6554, 'glb33', '520', '2000', '2100'),
(2, 6554, '556', '44', '44', '54');

-- --------------------------------------------------------

--
-- Table structure for table `stationtable`
--

CREATE TABLE `stationtable` (
  `Id` int(11) NOT NULL,
  `Scode` varchar(20) NOT NULL,
  `Station_Name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stationtable`
--

INSERT INTO `stationtable` (`Id`, `Scode`, `Station_Name`) VALUES
(1, 'glb33', 'gulbarga'),
(2, '556', 'mum');

-- --------------------------------------------------------

--
-- Table structure for table `tickettable`
--

CREATE TABLE `tickettable` (
  `Id` int(11) NOT NULL,
  `Bus_Number` varchar(100) NOT NULL,
  `Src` varchar(45) NOT NULL,
  `Dest` varchar(45) NOT NULL,
  `TotalFare` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bustable`
--
ALTER TABLE `bustable`
  ADD PRIMARY KEY (`BId`);

--
-- Indexes for table `employeetable`
--
ALTER TABLE `employeetable`
  ADD PRIMARY KEY (`EId`);

--
-- Indexes for table `passengertable`
--
ALTER TABLE `passengertable`
  ADD KEY `par_ind` (`TicketId`);

--
-- Indexes for table `routetable`
--
ALTER TABLE `routetable`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `par_ind` (`Station_Code`);

--
-- Indexes for table `stationtable`
--
ALTER TABLE `stationtable`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Scode` (`Scode`);

--
-- Indexes for table `tickettable`
--
ALTER TABLE `tickettable`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bustable`
--
ALTER TABLE `bustable`
  MODIFY `BId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `employeetable`
--
ALTER TABLE `employeetable`
  MODIFY `EId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `routetable`
--
ALTER TABLE `routetable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `stationtable`
--
ALTER TABLE `stationtable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tickettable`
--
ALTER TABLE `tickettable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `passengertable`
--
ALTER TABLE `passengertable`
  ADD CONSTRAINT `fk_ticketTable` FOREIGN KEY (`TicketId`) REFERENCES `tickettable` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `routetable`
--
ALTER TABLE `routetable`
  ADD CONSTRAINT `fk_RouteTable` FOREIGN KEY (`Station_Code`) REFERENCES `stationtable` (`Scode`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
