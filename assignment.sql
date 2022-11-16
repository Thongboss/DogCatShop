-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 16, 2022 at 04:16 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `assignment`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `username` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`username`, `password`) VALUES
('admin123', 'admin123'),
('admin123456', 'admin123456');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `category_id` bigint(20) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`category_id`, `name`) VALUES
(1, 'Dog China'),
(2, 'Cat Japan'),
(3, 'Dog America'),
(4, 'Cat Meta'),
(5, 'Dog Beclin');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customer_id` bigint(20) NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `register_date` date DEFAULT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customer_id`, `email`, `name`, `password`, `phone`, `register_date`, `status`) VALUES
(1, 'huuthong251099@gmail.com', 'thông ga', '$2a$10$UZOwUWVpCCDBg1owW7NhquZvomzbJSdxG2A0L4qfbvIQRRAj14WV6', '0368443774', NULL, 'activate'),
(2, 'Huongtram@gmail.com', 'Nguyễn thị hương trầm', '$2a$10$ocOsembeFpeGClSA.glLq.P2lDAdYDQS.Pzc5bx.1ijuqzbGiryCG', '0368445234', NULL, 'activate');

-- --------------------------------------------------------

--
-- Table structure for table `orderdetails`
--

CREATE TABLE `orderdetails` (
  `order_detail_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unit_price` double NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `code_order` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `order_date` date DEFAULT NULL,
  `phone_number` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `total_money` double NOT NULL,
  `customer_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` bigint(20) NOT NULL,
  `description` varchar(500) CHARACTER SET utf8 NOT NULL,
  `discount` double NOT NULL,
  `entereddate` date DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `quantity` int(11) NOT NULL,
  `status` varchar(255) NOT NULL,
  `unit_price` double NOT NULL,
  `category_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `description`, `discount`, `entereddate`, `image`, `name`, `quantity`, `status`, `unit_price`, `category_id`) VALUES
(1, 'Chó là loài \"đi bằng đầu ngón chân\" và có các bàn chân đặc trưng, năm ngón ở chân trước và bốn ngón ở chân sau. Đôi khi có trường hợp chó nhà có năm ngón ở chân sau (móng thứ năm gọi là móng huyền). Chó rừng có đuôi dài, lông dày, thường đồng màu và không có đốm sọc.', 3, NULL, 'pc64e6ceb-bc79-4523-afa7-72d4872b7d0e.jpg', 'shinzhao', 5, 'Bestseller', 437, 1),
(2, 'Mèo là động vật có vú, nhỏ nhắn và chuyên ăn thịt, sống chung với loài người, được nuôi để săn vật gây hại hoặc làm thú nuôi cùng với chó nhà. Mèo nhà đã sống gần gũi với loài người ít nhất 9.500 năm, và hiện nay chúng là con vật cưng phổ biến nhất trên thế giới.', 1, NULL, 'p9fcd3a91-2289-4d7b-b3fd-7ae33252f956.jpg', 'zed', 6, 'Bestseller', 1125, 4),
(3, 'Chó là loài \"đi bằng đầu ngón chân\" và có các bàn chân đặc trưng, năm ngón ở chân trước và bốn ngón ở chân sau. Đôi khi có trường hợp chó nhà có năm ngón ở chân sau (móng thứ năm gọi là móng huyền). Chó rừng có đuôi dài, lông dày, thường đồng màu và không có đốm sọc.', 0, NULL, 'p41ba0f31-e20c-453f-a31a-79b9ccce7be9.jpg', 'Volibear', 6, 'Bestseller', 720, 5),
(4, 'Mèo là động vật có vú, nhỏ nhắn và chuyên ăn thịt, sống chung với loài người, được nuôi để săn vật gây hại hoặc làm thú nuôi cùng với chó nhà. Mèo nhà đã sống gần gũi với loài người ít nhất 9.500 năm, và hiện nay chúng là con vật cưng phổ biến nhất trên thế giới.', 0, NULL, 'p065ad808-2651-4b61-81c0-1faba702777b.jpg', 'BilBil', 3, 'Bestseller', 345, 2),
(5, 'Mèo là động vật có vú, nhỏ nhắn và chuyên ăn thịt, sống chung với loài người, được nuôi để săn vật gây hại hoặc làm thú nuôi cùng với chó nhà. Mèo nhà đã sống gần gũi với loài người ít nhất 9.500 năm, và hiện nay chúng là con vật cưng phổ biến nhất trên thế giới.', 0, NULL, 'pae70dd81-3509-4673-9c18-60ee82b025a6.jpg', 'nami', 7, 'Bestseller', 679, 2),
(6, 'Chó là loài \"đi bằng đầu ngón chân\" và có các bàn chân đặc trưng, năm ngón ở chân trước và bốn ngón ở chân sau. Đôi khi có trường hợp chó nhà có năm ngón ở chân sau (móng thứ năm gọi là móng huyền). Chó rừng có đuôi dài, lông dày, thường đồng màu và không có đốm sọc.', 2, NULL, 'p09ae1e68-086a-4153-9dbe-75f9787baf7d.jpg', 'Wawick', 4, 'Bestseller', 692, 5),
(7, 'Chó là loài \"đi bằng đầu ngón chân\" và có các bàn chân đặc trưng, năm ngón ở chân trước và bốn ngón ở chân sau. Đôi khi có trường hợp chó nhà có năm ngón ở chân sau (móng thứ năm gọi là móng huyền). Chó rừng có đuôi dài, lông dày, thường đồng màu và không có đốm sọc.', 10, NULL, 'p62d8c677-60a5-4877-ba18-e4724f449ff6.jpg', 'Nasus', 3, 'Bestseller', 1568, 3),
(8, 'Mèo là động vật có vú, nhỏ nhắn và chuyên ăn thịt, sống chung với loài người, được nuôi để săn vật gây hại hoặc làm thú nuôi cùng với chó nhà. Mèo nhà đã sống gần gũi với loài người ít nhất 9.500 năm, và hiện nay chúng là con vật cưng phổ biến nhất trên thế giới.', 3, NULL, 'p42ba0dd8-c723-48b8-b385-66bd065dead7.jpg', 'yumi', 4, 'Bestseller', 739, 4),
(9, 'Mèo là động vật có vú, nhỏ nhắn và chuyên ăn thịt, sống chung với loài người, được nuôi để săn vật gây hại hoặc làm thú nuôi cùng với chó nhà. Mèo nhà đã sống gần gũi với loài người ít nhất 9.500 năm, và hiện nay chúng là con vật cưng phổ biến nhất trên thế giới.', 3, NULL, 'pedc507eb-18b7-49a0-9301-ec84cffe0a4d.jpg', 'nekko', 9, 'On Sale', 358, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD PRIMARY KEY (`order_detail_id`),
  ADD KEY `FKhnsosbuy7bhpqpnt3bjr7sh8x` (`order_id`),
  ADD KEY `FK92im1bt9gndrexccag7x5oq92` (`product_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `FKpxtb8awmi0dk6smoh2vp1litg` (`customer_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `category_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `customer_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `orderdetails`
--
ALTER TABLE `orderdetails`
  MODIFY `order_detail_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD CONSTRAINT `FK92im1bt9gndrexccag7x5oq92` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
  ADD CONSTRAINT `FKhnsosbuy7bhpqpnt3bjr7sh8x` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FKpxtb8awmi0dk6smoh2vp1litg` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
