-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 13, 2022 at 11:33 AM
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
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `id_company` int(11) NOT NULL,
  `nama` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`id_company`, `nama`) VALUES
(0, 'ajigile'),
(1, 'ajikonde brok'),
(4, 'mamank resing');

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
(12, 'PT Dwicak Petot', 16),
(13, 'PT Masker Memang', 17),
(14, 'PT Merdeka', 18);

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

CREATE TABLE `manager` (
  `id` int(11) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `jabatan` varchar(30) NOT NULL,
  `headof` varchar(30) NOT NULL,
  `id_company` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`id`, `nama`, `jabatan`, `headof`, `id_company`) VALUES
(68, 'Ahmad Kurniawan', 'Manager', 'Network', 0),
(69, 'Hadi Wijaya', 'Manager', 'maintainance', 1),
(70, 'Bobi Saputro', 'Manager', 'konsumsi', 0),
(71, 'Al Faizi', 'Manager', 'kesehatan', 4);

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
(16, 'Maintain Data Center', '2022-01-12', '2022-01-12', 'Ahmad Kurniawan', '[\"Bobo Sam Agadis\", \"Nur Uta Iga\", \"Suharjo Nampar\"]', '[\"Server Checking\", \"Cable Checking\", \"Database Checking\"]'),
(17, 'Power Support GBK', '2022-01-12', '2022-03-02', 'Al Faizi', '[\"Konro Babat\", \"Bobo Sam Agadis\", \"Suharjo Nampar\", \"Hermano Juming\", \"Chou Lie\"]', '[\"Listrik A1\", \"Gen set 900KWH\"]'),
(18, 'Covid-19 Vaccine A19', '2022-01-13', '2022-12-15', 'Bobi Saputro', '[\"Suharjo Nampar\", \"Bobo Sam Agadis\", \"Nur Uta Iga\", \"Chou Lie\", \"Konro Babat\"]', '[\"900 Peoples Done\", \"Predict Mount\"]');

-- --------------------------------------------------------

--
-- Table structure for table `subordinate`
--

CREATE TABLE `subordinate` (
  `id` int(11) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `jabatan` varchar(50) NOT NULL,
  `divisi` varchar(50) NOT NULL,
  `id_company` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subordinate`
--

INSERT INTO `subordinate` (`id`, `nama`, `jabatan`, `divisi`, `id_company`) VALUES
(62, 'Suharjo Nampar', 'subordinate', 'Network', 4),
(63, 'Bobo Sam Agadis', 'subordinate', 'maintanance', 0),
(64, 'Nur Uta Iga', 'subordinate', 'konsumsi', 0),
(65, 'Hermano Juming', 'subordinate', 'kesehatan', 1),
(66, 'Chou Lie', 'subordinate', 'Maintainance', 1),
(67, 'Konro Babat', 'subordinate', 'kesehatan', 0);

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
(20, 'Server Checking', 1, 16),
(21, 'Cable Checking', 1, 16),
(22, 'Database Checking', 0, 16),
(23, 'Listrik A1', 1, 17),
(24, 'Gen set 900KWH', 1, 17),
(25, '900 Peoples Done', 0, 18),
(26, 'Predict Mount', 0, 18);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`id_company`);

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
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_company` (`id_company`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`id_project`);

--
-- Indexes for table `subordinate`
--
ALTER TABLE `subordinate`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_company` (`id_company`);

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
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
  MODIFY `id_company` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id_cus` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `manager`
--
ALTER TABLE `manager`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `id_project` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `subordinate`
--
ALTER TABLE `subordinate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT for table `subproject`
--
ALTER TABLE `subproject`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`id_project`) REFERENCES `project` (`id_project`);

--
-- Constraints for table `manager`
--
ALTER TABLE `manager`
  ADD CONSTRAINT `manager_ibfk_1` FOREIGN KEY (`id_company`) REFERENCES `company` (`id_company`);

--
-- Constraints for table `subordinate`
--
ALTER TABLE `subordinate`
  ADD CONSTRAINT `subordinate_ibfk_1` FOREIGN KEY (`id_company`) REFERENCES `company` (`id_company`);

--
-- Constraints for table `subproject`
--
ALTER TABLE `subproject`
  ADD CONSTRAINT `subproject_ibfk_1` FOREIGN KEY (`id_project`) REFERENCES `project` (`id_project`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
