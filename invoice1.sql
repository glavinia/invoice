-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 27, 2013 at 03:40 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `invoice1`
--

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE IF NOT EXISTS `invoice` (
  `documentNo` varchar(100) NOT NULL,
  `customerName` varchar(50) NOT NULL,
  `dateInvoiced` varchar(10) NOT NULL DEFAULT '0',
  `totalNetAmt` varchar(30) NOT NULL DEFAULT '0',
  `totalVatAmt` varchar(30) NOT NULL DEFAULT '0',
  `totalAmt` varchar(30) NOT NULL DEFAULT '0',
  `line` varchar(200) NOT NULL DEFAULT '0',
  PRIMARY KEY (`documentNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`documentNo`, `customerName`, `dateInvoiced`, `totalNetAmt`, `totalVatAmt`, `totalAmt`, `line`) VALUES
('1', 'Tudorut Paul', '02.01.2009', '2275.0', '546.0', '2821.0', 'LineNetAmt: 32.5 LineVatAmt: 7.8 LineTotalAmt40.3'),
('3', 'Tudorut Iulian', '04.02.2013', '2278.0', '552.0', '2830.0', 'LineNetAmt: 3.0 LineVatAmt: 6.0 LineTotalAmt9.0'),
('4', 'Gruia Lavinia', '07.04.2013', '73.0', '31.6', '104.6', 'LineNetAmt: 13.0 LineVatAmt: 3.12 LineTotalAmt16.12');

-- --------------------------------------------------------

--
-- Table structure for table `pack`
--

CREATE TABLE IF NOT EXISTS `pack` (
  `customerName` varchar(50) NOT NULL,
  `productName` varchar(50) NOT NULL,
  `price` double(3,2) NOT NULL,
  `qty` int(10) NOT NULL,
  `vatrate` double(3,2) NOT NULL,
  `lineNetAmt` varchar(15) NOT NULL,
  `lineVatAmt` varchar(15) NOT NULL,
  `lineTotalAmt` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pack`
--

INSERT INTO `pack` (`customerName`, `productName`, `price`, `qty`, `vatrate`, `lineNetAmt`, `lineVatAmt`, `lineTotalAmt`) VALUES
('Tudorut Paul', 'Coca-Col', 6.50, 50, 0.24, '325.0', '78.0', '650.0'),
('Tudorut Paul', 'Coca-Col', 6.50, 80, 0.24, '520.0', '124.8', '644.8'),
('Tudorut Paul', 'Coca-Col', 6.50, 5, 0.24, '32.5', '7.8', '40.3'),
('Tudorut Iulian', 'fanta', 1.00, 3, 2.00, '3.0', '6.0', '9.0'),
('Gruia Lavinia', 'Coca-Col', 6.50, 4, 0.24, '26.0', '6.24', '32.24'),
('Gruia Lavinia', 'fanta', 1.00, 4, 2.00, '4.0', '8.0', '12.0'),
('Gruia Lavinia', 'Coca-Col', 6.50, 2, 0.24, '13.0', '3.12', '16.12');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `productName` varchar(50) NOT NULL,
  `umSymbol` varchar(50) NOT NULL,
  `price` double(3,2) NOT NULL,
  `vatrate` double(3,2) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`productName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productName`, `umSymbol`, `price`, `vatrate`, `description`) VALUES
('Coca-Col', 'CC', 6.50, 0.24, 'Coca Cola este cea mai buna bautura racoritoare'),
('fanta', '3', 1.00, 2.00, 'bautura racoritoare');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
