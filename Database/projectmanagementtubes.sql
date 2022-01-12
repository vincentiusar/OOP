-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 12, 2022 at 09:43 AM
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
(1, 'ajikonde');

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
(1, 'google.com', 1),
(4, 'Facebook.com', 2),
(5, 'tukang bakso coba', 3),
(6, 'tukang yes', 4),
(7, 'tukang coba coba', 5),
(8, 'orang ngaret', 6),
(10, 'tukang bakso coba coba', 14);

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
(2, 'aduh mamank', 'Manager', 'admin', 0),
(57, 'nama saya siapa', 'Manager', 'aduh abdul', 0),
(61, 'aku bukan diriku', 'Manager', 'marketing', 0),
(63, 'aku adalah lelaki', 'Manager', 'admin', 0);

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
(1, 'Maintain Google', '2021-12-08', '2021-12-23', 'aku adalah lelaki', '[\"saya gila\", \"eya eyaaaa\", \"aku adalah diriku\"]', '[\"tukang AC\",\"cuci Kulkas\",\"tim doa\",]'),
(2, 'Facebook Vunerability', '2021-12-08', '2021-12-29', 'aduh mamank', '[\"saya gila\"]', '[\"matiin lampu\", \"cuci piring\"]'),
(3, 'coba nih pasti', '2021-12-08', '2021-12-23', 'aku adalah lelaki', '[\"aku adalah diriku\"]', '[\"bener kah?\",\"baa\",]'),
(4, 'YESSSs', '2021-12-31', '2022-12-31', 'nama saya siapa', '[\"eya eyaaaa\", \"saya gila\"]', '[\"yes 1\"]'),
(5, 'bisa nih liatin aja', '2021-12-31', '2022-12-31', 'aku bukan diriku', '[\"eya eyaaaa\", \"aku adalah diriku\", \"aku ingat namaku\"]', '[\"makan nasi\"]'),
(6, 'coba delay', '2022-01-07', '2022-01-08', 'aduh mamank', '[\"aku adalah diriku\"]', '[\"coba delayed 1\"]'),
(14, 'coba makan bakso borax', '2022-01-12', '2022-01-12', 'aku adalah lelaki', '[\"saya gila\"]', '[\"mati makan borax\"]');

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
(2, 'eya eyaaaa', 'subordinate', 'database', 0),
(33, 'aku adalah diriku', 'subordinate', 'project gajelas', 0),
(37, 'aku ingat namaku', 'subordinate', 'admin', 0),
(52, 'saya gila', 'subordinate', 'networking', 1);

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
(1, 'tukang AC', 1, 1),
(2, 'cuci Kulkas', 1, 1),
(3, 'tim doa', 1, 1),
(4, 'cuci piring', 0, 2),
(5, 'matiin lampu', 1, 2),
(13, 'bener kah?', 0, 3),
(14, 'baa', 0, 3),
(15, 'makan nasi', 1, 5),
(16, 'coba delayed 1', 0, 6),
(17, 'yes 1', 0, 4),
(18, 'mati makan borax', 0, 14);

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
  MODIFY `id_company` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id_cus` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `manager`
--
ALTER TABLE `manager`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `id_project` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `subordinate`
--
ALTER TABLE `subordinate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `subproject`
--
ALTER TABLE `subproject`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

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
