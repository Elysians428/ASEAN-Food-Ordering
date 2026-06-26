-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 26, 2026 at 12:50 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_restoran_asean`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_meja`
--

CREATE TABLE `data_meja` (
  `no_meja` int(11) NOT NULL,
  `status_meja` varchar(20) NOT NULL DEFAULT 'Tersedia'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data_meja`
--

INSERT INTO `data_meja` (`no_meja`, `status_meja`) VALUES
(1, 'Terisi'),
(2, 'Tersedia'),
(3, 'Tersedia'),
(4, 'Tersedia'),
(5, 'Terisi'),
(6, 'Terisi'),
(7, 'Tersedia'),
(8, 'Tersedia'),
(9, 'Tersedia'),
(10, 'Tersedia');

-- --------------------------------------------------------

--
-- Table structure for table `makanan`
--

CREATE TABLE `makanan` (
  `id_makanan` int(11) NOT NULL,
  `nama_makanan` varchar(100) NOT NULL,
  `asal_negara` varchar(50) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `makanan`
--

INSERT INTO `makanan` (`id_makanan`, `nama_makanan`, `asal_negara`, `created_at`) VALUES
(1, 'Rendang', 'Indonesia', '2026-06-02 10:58:25'),
(2, 'Nasi Lemak', 'Malaysia', '2026-06-02 10:58:25'),
(3, 'Pad Thai', 'Thailand', '2026-06-02 10:58:25'),
(4, 'Pho Bo', 'Vietnam', '2026-06-02 10:58:25'),
(5, 'Hainanese Chicken Rice', 'Singapura', '2026-06-02 10:58:25'),
(6, 'Adobo Chicken', 'Filipina', '2026-06-02 10:58:25'),
(7, 'Amok Fish', 'Kamboja', '2026-06-02 10:58:25'),
(8, 'Mohinga', 'Myanmar', '2026-06-02 10:58:25'),
(9, 'Sticky Rice with Mango', 'Laos', '2026-06-02 10:58:25'),
(10, 'Nasi Katok', 'Brunei Darussalam', '2026-06-02 10:58:25'),
(11, 'Batar Da\'an', 'Timor Leste', '2026-06-02 10:58:25');

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `id_pesanan` int(11) NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `tipe_pesanan` varchar(20) NOT NULL,
  `no_meja` int(11) DEFAULT NULL,
  `id_varian` int(11) NOT NULL,
  `total_harga_rp` int(11) NOT NULL,
  `waktu_pesan` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`id_pesanan`, `nama_pelanggan`, `tipe_pesanan`, `no_meja`, `id_varian`, `total_harga_rp`, `waktu_pesan`) VALUES
(1, 'eqfde', 'TAKEAWAY', NULL, 6, 110000, '2026-06-20 10:05:34');

-- --------------------------------------------------------

--
-- Table structure for table `varian_porsi`
--

CREATE TABLE `varian_porsi` (
  `id_varian` int(11) NOT NULL,
  `id_makanan` int(11) DEFAULT NULL,
  `ukuran` enum('Small','Medium','Large') NOT NULL,
  `harga` decimal(10,2) NOT NULL,
  `bisa_dine_in` tinyint(1) DEFAULT 1,
  `bisa_take_away` tinyint(1) DEFAULT 1,
  `stok` int(11) NOT NULL DEFAULT 20
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `varian_porsi`
--

INSERT INTO `varian_porsi` (`id_varian`, `id_makanan`, `ukuran`, `harga`, `bisa_dine_in`, `bisa_take_away`, `stok`) VALUES
(1, 1, 'Small', 25000.00, 1, 1, 20),
(2, 1, 'Medium', 45000.00, 1, 1, 20),
(3, 1, 'Large', 80000.00, 1, 1, 20),
(4, 2, 'Small', 20000.00, 1, 1, 20),
(5, 2, 'Medium', 35000.00, 1, 1, 20),
(6, 2, 'Large', 55000.00, 1, 1, 18),
(7, 3, 'Small', 22000.00, 1, 1, 20),
(8, 3, 'Medium', 40000.00, 1, 1, 20),
(9, 3, 'Large', 60000.00, 1, 1, 20),
(10, 4, 'Small', 30000.00, 1, 1, 20),
(11, 4, 'Medium', 50000.00, 1, 1, 20),
(12, 4, 'Large', 85000.00, 1, 1, 20),
(13, 5, 'Small', 28000.00, 1, 1, 20),
(14, 5, 'Medium', 48000.00, 1, 1, 20),
(15, 5, 'Large', 70000.00, 1, 1, 20),
(16, 6, 'Small', 26000.00, 1, 1, 20),
(17, 6, 'Medium', 45000.00, 1, 1, 20),
(18, 6, 'Large', 68000.00, 1, 1, 20),
(19, 7, 'Small', 30000.00, 1, 1, 20),
(20, 7, 'Medium', 55000.00, 1, 1, 20),
(21, 7, 'Large', 80000.00, 1, 1, 20),
(22, 8, 'Small', 22000.00, 1, 1, 20),
(23, 8, 'Medium', 38000.00, 1, 1, 20),
(24, 8, 'Large', 55000.00, 1, 1, 20),
(25, 9, 'Small', 20000.00, 1, 1, 20),
(26, 9, 'Medium', 35000.00, 1, 1, 20),
(27, 9, 'Large', 50000.00, 1, 1, 20),
(28, 10, 'Small', 18000.00, 1, 1, 20),
(29, 10, 'Medium', 32000.00, 1, 1, 20),
(30, 10, 'Large', 45000.00, 1, 1, 20),
(31, 11, 'Small', 24000.00, 1, 1, 20),
(32, 11, 'Medium', 42000.00, 1, 1, 20),
(33, 11, 'Large', 60000.00, 1, 1, 20);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_meja`
--
ALTER TABLE `data_meja`
  ADD PRIMARY KEY (`no_meja`);

--
-- Indexes for table `makanan`
--
ALTER TABLE `makanan`
  ADD PRIMARY KEY (`id_makanan`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`id_pesanan`),
  ADD KEY `no_meja` (`no_meja`),
  ADD KEY `id_varian` (`id_varian`);

--
-- Indexes for table `varian_porsi`
--
ALTER TABLE `varian_porsi`
  ADD PRIMARY KEY (`id_varian`),
  ADD KEY `id_makanan` (`id_makanan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `makanan`
--
ALTER TABLE `makanan`
  MODIFY `id_makanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `id_pesanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `varian_porsi`
--
ALTER TABLE `varian_porsi`
  MODIFY `id_varian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD CONSTRAINT `pesanan_ibfk_1` FOREIGN KEY (`no_meja`) REFERENCES `data_meja` (`no_meja`),
  ADD CONSTRAINT `pesanan_ibfk_2` FOREIGN KEY (`id_varian`) REFERENCES `varian_porsi` (`id_varian`);

--
-- Constraints for table `varian_porsi`
--
ALTER TABLE `varian_porsi`
  ADD CONSTRAINT `varian_porsi_ibfk_1` FOREIGN KEY (`id_makanan`) REFERENCES `makanan` (`id_makanan`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
