-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 28, 2021 at 02:00 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectmanagementtubes`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id_cus` int(11) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `id_project` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id_cus`, `nama`, `id_project`) VALUES
(1, 'Google.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

CREATE TABLE `manager` (
  `id` int(10) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `jabatan` varchar(30) NOT NULL,
  `headof` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`id`, `nama`, `jabatan`, `headof`) VALUES
(1, 'aku adalah lelaki', 'Manager', 'admin'),
(2, 'aduh mamank', 'Manager', 'admin'),
(36, 'aku bukan diriku', 'Manager', 'marketing'),
(40, 'nama saya siapa', 'Manager', 'aduh abdul'),
(42, 'aku ingat namaku', 'Manager', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `id_project` int(11) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `timeStart` date NOT NULL,
  `timeEnd` date NOT NULL,
  `manager` varchar(50) NOT NULL,
  `worker` varchar(150) NOT NULL,
  `subProject` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`id_project`, `nama`, `timeStart`, `timeEnd`, `manager`, `worker`, `subProject`) VALUES
(1, 'Maintain Google', '2021-12-08', '2021-12-23', 'aku adalah lelaki', '[\"saya gila\", \"eya eyaaaa\", \"aku adalah diriku\"]', '[\"tukang AC\", \"cuci Kulkas\", \"tim doa\"]');

-- --------------------------------------------------------

--
-- Table structure for table `subordinate`
--

CREATE TABLE `subordinate` (
  `id` int(11) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `jabatan` varchar(50) NOT NULL,
  `divisi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subordinate`
--

INSERT INTO `subordinate` (`id`, `nama`, `jabatan`, `divisi`) VALUES
(1, 'saya gila', 'subordinate', 'networking'),
(2, 'eya eyaaaa', 'subordinate', 'database'),
(33, 'aku adalah diriku', 'subordinate', 'project gajelas');

-- --------------------------------------------------------

--
-- Table structure for table `subproject`
--

CREATE TABLE `subproject` (
  `id` int(11) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `isDone` tinyint(1) NOT NULL,
  `id_project` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subproject`
--

INSERT INTO `subproject` (`id`, `nama`, `isDone`, `id_project`) VALUES
(1, 'tukang AC', 0, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id_cus`),
  ADD KEY `id_project` (`id_project`) USING BTREE;

--
-- Indexes for table `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`id_project`);

--
-- Indexes for table `subordinate`
--
ALTER TABLE `subordinate`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subproject`
--
ALTER TABLE `subproject`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_project` (`id_project`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id_cus` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `manager`
--
ALTER TABLE `manager`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `id_project` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `subordinate`
--
ALTER TABLE `subordinate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `subproject`
--
ALTER TABLE `subproject`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`id_project`) REFERENCES `project` (`id_project`);

--
-- Constraints for table `subproject`
--
ALTER TABLE `subproject`
  ADD CONSTRAINT `subproject_ibfk_1` FOREIGN KEY (`id_project`) REFERENCES `project` (`id_project`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
