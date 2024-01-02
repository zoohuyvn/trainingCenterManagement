-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 22, 2023 at 07:10 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `training_center_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `advisors`
--

CREATE TABLE `advisors` (
  `code` varchar(50) NOT NULL,
  `indentity` varchar(12) NOT NULL,
  `name` varchar(50) NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `birthday` varchar(10) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `advisors`
--

INSERT INTO `advisors` (`code`, `indentity`, `name`, `gender`, `birthday`, `phone`, `email`, `address`) VALUES
('ZH-GV1', '096202717602', 'Vương Tiến Quyết', 1, '10/06/1977', '0255690468', 'lengthanhhai985@gmail.com', 'Thị trấn Tân Minh, Huyện Hàm Tân, Bình Thuận'),
('ZH-GV2', '046294967086', 'Huỳnh Thị Liên Trà', 0, '18/04/1983', '0277390754', 'macxuanhuy157@yahoo.com', 'Xã Tân Hương, Huyện Châu Thành, Tiền Giang'),
('ZH-GV3', '0773236243', 'Hoàng Công Trợ', 1, '03/08/2001', '0257723418', 'giangquanglinh259@hotmail.com', 'Xã Sơn Định, Huyện Chợ Lách, Bến Tre'),
('ZH-GV4', '094600831554', 'Trần Quỳnh Nga', 0, '11/11/1982', '0798365724', 'tanthuynhu896@yahoo.com', 'Xã An Tường, Huyện Vĩnh Tường, Vĩnh Phúc'),
('ZH-GV5', '0662666975', 'Đoàn Cảnh Duy', 1, '08/08/1984', '0810147892', 'nhamthungoc618@hotmail.com', 'Xã Hà Thanh, Huyện Tứ Kỳ, Hải Dương');

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `code` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `startDay` varchar(10) NOT NULL,
  `price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`code`, `name`, `startDay`, `price`) VALUES
('ZH-ADSFB', 'Chạy quảng cáo FB', '11/12/2023', 15000000.00),
('ZH-DEVFULL', 'Lập trình fullstack', '08/01/2024', 8200000.00),
('ZH-EDITVD', 'Chỉnh sửa video', '18/12/2023', 5500000.00),
('ZH-LIVEST', 'Kỹ năng livestrem', '25/12/2023', 6400000.00),
('ZH-PHOTO', 'Nhiếp ảnh', '01/01/2024', 7300000.00);

-- --------------------------------------------------------

--
-- Table structure for table `learn`
--

CREATE TABLE `learn` (
  `studentCode` varchar(50) NOT NULL,
  `classCode` varchar(50) NOT NULL,
  `point` decimal(3,1) NOT NULL DEFAULT -1.0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `learn`
--

INSERT INTO `learn` (`studentCode`, `classCode`, `point`) VALUES
('ZH-HV01', 'ZH-ADSFB', -1.0),
('ZH-HV02', 'ZH-ADSFB', 8.6),
('ZH-HV03', 'ZH-EDITVD', -1.0),
('ZH-HV04', 'ZH-DEVFULL', 0.0),
('ZH-HV05', 'ZH-DEVFULL', 7.7),
('ZH-HV05', 'ZH-EDITVD', 8.6),
('ZH-HV06', 'ZH-EDITVD', 0.0),
('ZH-HV07', 'ZH-LIVEST', 6.8),
('ZH-HV08', 'ZH-EDITVD', 8.2),
('ZH-HV08', 'ZH-PHOTO', 9.1),
('ZH-HV09', 'ZH-LIVEST', 8.2),
('ZH-HV10', 'ZH-EDITVD', 6.6),
('ZH-HV11', 'ZH-LIVEST', -1.0),
('ZH-HV12', 'ZH-LIVEST', 9.4);

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `code` varchar(50) NOT NULL,
  `indentity` varchar(12) NOT NULL,
  `name` varchar(50) NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `birthday` varchar(10) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `job` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`code`, `indentity`, `name`, `gender`, `birthday`, `phone`, `email`, `address`, `job`) VALUES
('ZH-HV01', '099999999999', 'Pro ẩn danh', 1, '10/10/2000', '0999999999', 'xyzr@gmail.com', 'Nhà', 'Hacker'),
('ZH-HV02', '066238726330', 'Trần Anh Diêm', 1, '02/08/2001', '0263789021', 'bienthaiminh763@microsoft.com', 'Phường 2, Thị xã Quảng Trị, Quảng Trị', 'Sinh viên'),
('ZH-HV03', '041047871693', 'Ngô Anh Độ', 1, '15/01/1992', '0783750142', 'laingochuyen529@google.com', 'Xã Tân Dương, Huyện Lai Vung, Đồng Tháp', 'Thất nghiệp'),
('ZH-HV04', '099999999999', 'Võ Phước Duy', 0, '20/11/2005', '0981932574', 'zoohuy.contact@gmail.com', 'Phú Lộc, Huế, Việt Nam', 'Sinh viên'),
('ZH-HV05', '039051968728', 'Tạ Thị Trúc Thu', 0, '03/07/1988', '0360536241', 'chauquocmanh17@naver.com', 'Xã Nghĩa Hùng, Huyện Nghĩa Hưng, Nam Định', 'Marketing'),
('ZH-HV06', '087969310198', 'Đặng Hoàng Thoa', 0, '31/03/2001', '0226743169', 'bachacthanh345@microsoft.com', 'Thị trấn Tầm Vu, Huyện Châu Thành, Long An', 'Nhân viên sale'),
('ZH-HV07', '057520760067', 'Liễu Thị Lệ Quỳnh', 0, '18/02/1982', '0369537816', 'kieuphihai468@icloud.com', 'Xã Láng Dài, Huyện Đất Đỏ, Bà Rịa - Vũng Tàu', 'Công nhân'),
('ZH-HV08', '088892086394', 'Võ Trường Chính', 1, '12/08/1985', '0235525974', 'hongtruongphuc797@microsoft.com', 'Xã Đội Cấn, Thành phố Tuyên Quang, Tuyên Quang', 'Thợ cắt tóc'),
('ZH-HV09', '089784299333', 'Lê Hưng Trường', 1, '03/08/1994', '0233785103', 'douyentram26@google.com', 'Xã Đại Phạm, Huyện Hạ Hoà, Phú Thọ', 'Shipper'),
('ZH-HV10', '013471499788', 'Hồ Cẩm Hảo', 0, '16/10/1989', '0260389145', 'catnhatruc757@microsoft.com', 'Xã Xuân Tâm, Huyện Xuân Lộc, Đồng Nai', 'Nội trợ'),
('ZH-HV11', '082042387110', 'Lương Viết Huy', 1, '25/06/1997', '0239419638', 'manhuytuong650@gmail.com', 'Phường 7, Thành phố Trà Vinh, Trà Vinh', 'Giáo viên'),
('ZH-HV12', '049237814705', 'Lương Thị Huỳnh Linh', 0, '06/10/1987', '0234435417', 'cadathao550@gmail.com', 'Xã Tân Long, Huyện Thanh Bình, Đồng Tháp', 'Kế toán');

-- --------------------------------------------------------

--
-- Table structure for table `teach`
--

CREATE TABLE `teach` (
  `advisorCode` varchar(50) NOT NULL,
  `classCode` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `teach`
--

INSERT INTO `teach` (`advisorCode`, `classCode`) VALUES
('ZH-GV1', 'ZH-ADSFB'),
('ZH-GV2', 'ZH-DEVFULL'),
('ZH-GV3', 'ZH-DEVFULL'),
('ZH-GV3', 'ZH-EDITVD'),
('ZH-GV4', 'ZH-LIVEST'),
('ZH-GV5', 'ZH-PHOTO');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`email`, `password`, `role`) VALUES
('admin@gmail.com', '$2a$12$o8V/d8pIEVdRMLoJMHt4h.bBKUgyz7O9Vrap0j4oO9xcer7t8kg5i', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `advisors`
--
ALTER TABLE `advisors`
  ADD PRIMARY KEY (`code`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `learn`
--
ALTER TABLE `learn`
  ADD PRIMARY KEY (`studentCode`,`classCode`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`code`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `teach`
--
ALTER TABLE `teach`
  ADD PRIMARY KEY (`advisorCode`,`classCode`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`email`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `learn`
--
ALTER TABLE `learn`
  ADD CONSTRAINT `learn_ibfk_1` FOREIGN KEY (`studentCode`) REFERENCES `students` (`code`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `learn_ibfk_2` FOREIGN KEY (`classCode`) REFERENCES `class` (`code`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `teach`
--
ALTER TABLE `teach`
  ADD CONSTRAINT `teach_ibfk_1` FOREIGN KEY (`advisorCode`) REFERENCES `advisors` (`code`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `teach_ibfk_2` FOREIGN KEY (`classCode`) REFERENCES `class` (`code`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
