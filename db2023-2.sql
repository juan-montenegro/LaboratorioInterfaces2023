-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-12-2023 a las 01:11:50
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db2023-2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `int_proceso`
--

CREATE TABLE `int_proceso` (
  `id` int(10) UNSIGNED NOT NULL,
  `int_proceso_tipo_id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(300) NOT NULL,
  `tiempo_muestreo` float NOT NULL,
  `archivo_especificaciones` blob NOT NULL,
  `archivo_manual` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `int_proceso`
--

INSERT INTO `int_proceso` (`id`, `int_proceso_tipo_id`, `nombre`, `descripcion`, `tiempo_muestreo`, `archivo_especificaciones`, `archivo_manual`) VALUES
(1, 1, 'Control de nivel', 'Control de nivel de uno o dos tanques acoplados', 0.1, 0x636f6e74726f6c4e6976656c2e747874, 0x67756961436f6e74726f6c4e6976656c2e706466),
(2, 2, 'Control de temperatura', 'Control de temperatura en un tanque', 0.99, 0x636f6e74726f6c54656d70657261747572612e747874, 0x67756961436f6e74726f6c54656d70657261747572612e706466),
(3, 2, 'Arduino', 'Se leen 8 señales analogicas, se leen 4 señales digitales, envia 4 señales digitales', 100, '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `int_proceso_control`
--

CREATE TABLE `int_proceso_control` (
  `id` int(10) UNSIGNED NOT NULL,
  `int_proceso_id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `descripcion` blob NOT NULL,
  `parametro1` float(5,5) NOT NULL,
  `parametro2` float(5,5) NOT NULL,
  `parametro3` float(5,5) NOT NULL,
  `parametro4` float(5,5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `int_proceso_control`
--

INSERT INTO `int_proceso_control` (`id`, `int_proceso_id`, `nombre`, `descripcion`, `parametro1`, `parametro2`, `parametro3`, `parametro4`) VALUES
(1, 1, 'Control RST', 0x436f6e74726f6c206469676974616c207469706f20525354, 0.10000, 0.20000, 0.30000, 0.00000),
(2, 2, 'Control PID', 0x436f6e74726f6c20616e616cc3b36769636f207469706f20504944, 0.99999, 0.10000, 0.50000, 0.00000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `int_proceso_refs`
--

CREATE TABLE `int_proceso_refs` (
  `id` int(10) UNSIGNED NOT NULL,
  `int_proceso_id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `descripcion` varchar(300) NOT NULL,
  `max_2` float NOT NULL,
  `min` float NOT NULL,
  `flag` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `int_proceso_refs`
--

INSERT INTO `int_proceso_refs` (`id`, `int_proceso_id`, `nombre`, `descripcion`, `max_2`, `min`, `flag`) VALUES
(1, 1, 'Nivel Tanque 1', 'Nivel deseado del tanque No. 1', 1, 0, 0),
(2, 1, 'Nivel Tanque 2', 'Nivel deseado del tanque No. 2', 1, 0, 0),
(3, 2, 'Temperatura Tanque 1', 'Temperatura deseada del tanque No. 1', 1, 0, 0),
(4, 3, 'DO0', 'Salida Digital 1', 1, 0, 0),
(5, 3, 'DO1', 'Salida Digital 2', 1, 0, 0),
(6, 3, 'DO2', 'Salida Digital 3', 1, 0, 0),
(7, 3, 'DO3', 'Salida Digital 4', 1, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `int_proceso_refs_data`
--

CREATE TABLE `int_proceso_refs_data` (
  `id` int(10) UNSIGNED NOT NULL,
  `int_proceso_refs_id` int(10) UNSIGNED NOT NULL,
  `valor` float NOT NULL,
  `tiempo` float NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `int_proceso_refs_data`
--

INSERT INTO `int_proceso_refs_data` (`id`, `int_proceso_refs_id`, `valor`, `tiempo`, `fecha`, `hora`) VALUES
(1, 4, 0.99999, 0, '2023-12-14', '13:57:10'),
(2, 5, 0.99999, 0, '2023-12-14', '13:57:17'),
(3, 7, 0.99999, 0, '2023-12-14', '13:57:20'),
(4, 6, 0.99999, 0, '2023-12-14', '13:57:23'),
(5, 7, 0.99999, 0, '2023-12-14', '14:00:57'),
(6, 4, 0.99999, 0, '2023-12-14', '14:05:54'),
(7, 6, 0.99999, 0, '2023-12-14', '14:06:32'),
(8, 7, 0.99999, 0, '2023-12-14', '14:10:18'),
(9, 5, 0.99999, 0, '2023-12-14', '14:31:17'),
(10, 6, 0.99999, 0, '2023-12-14', '14:31:18'),
(11, 4, 0.99999, 0, '2023-12-14', '14:31:19'),
(12, 7, 0.99999, 0, '2023-12-14', '14:36:48'),
(13, 6, 0.99999, 0, '2023-12-14', '14:36:58'),
(14, 7, 0, 0, '2023-12-14', '14:36:58'),
(15, 7, 0, 0, '2023-12-14', '15:13:07'),
(16, 7, 0.99999, 0, '2023-12-14', '15:13:09'),
(17, 7, 0, 0, '2023-12-14', '15:13:12'),
(18, 4, 0.99999, 0, '2023-12-14', '15:13:19'),
(19, 5, 0.99999, 0, '2023-12-14', '15:13:21'),
(20, 6, 0.99999, 0, '2023-12-14', '15:13:22'),
(21, 7, 0.99999, 0, '2023-12-14', '15:13:23'),
(22, 4, 0, 0, '2023-12-14', '15:14:48'),
(23, 5, 0, 0, '2023-12-14', '15:14:50'),
(24, 7, 0, 0, '2023-12-14', '15:14:52'),
(25, 6, 0, 0, '2023-12-14', '15:14:54'),
(26, 7, 1, 0, '2023-12-14', '16:03:08'),
(27, 7, 0, 0, '2023-12-14', '16:03:10'),
(28, 4, 1, 0, '2023-12-14', '16:47:21'),
(29, 4, 0, 0, '2023-12-14', '16:47:26'),
(30, 4, 1, 0, '2023-12-14', '17:43:12'),
(31, 4, 0, 0, '2023-12-14', '17:43:15'),
(32, 5, 1, 0, '2023-12-14', '18:00:40'),
(33, 5, 0, 0, '2023-12-14', '18:00:45'),
(34, 7, 1, 0, '2023-12-14', '18:00:47'),
(35, 7, 0, 0, '2023-12-14', '18:00:52'),
(36, 7, 1, 0, '2023-12-14', '18:06:18'),
(37, 7, 0, 0, '2023-12-14', '18:06:22'),
(38, 6, 1, 0, '2023-12-14', '18:06:25'),
(39, 4, 1, 0, '2023-12-14', '18:06:28'),
(40, 5, 1, 0, '2023-12-14', '18:06:31'),
(41, 7, 1, 0, '2023-12-14', '18:06:33'),
(42, 7, 0, 0, '2023-12-14', '18:06:41'),
(43, 6, 0, 0, '2023-12-14', '18:06:42'),
(44, 4, 0, 0, '2023-12-14', '18:06:43'),
(45, 5, 0, 0, '2023-12-14', '18:06:44'),
(46, 5, 1, 0, '2023-12-14', '18:06:50'),
(47, 5, 0, 0, '2023-12-14', '18:06:52'),
(48, 6, 1, 0, '2023-12-14', '18:06:54'),
(49, 6, 0, 0, '2023-12-14', '18:06:56'),
(50, 4, 1, 0, '2023-12-14', '18:06:58'),
(51, 4, 0, 0, '2023-12-14', '18:06:59'),
(52, 4, 1, 0, '2023-12-14', '18:07:18'),
(53, 4, 0, 0, '2023-12-14', '18:07:18'),
(54, 4, 1, 0, '2023-12-14', '18:07:20'),
(55, 4, 0, 0, '2023-12-14', '18:07:25'),
(56, 4, 1, 0, '2023-12-14', '18:07:25'),
(57, 4, 0, 0, '2023-12-14', '18:07:25'),
(58, 4, 1, 0, '2023-12-14', '18:07:32'),
(59, 4, 0, 0, '2023-12-14', '18:07:32'),
(60, 4, 1, 0, '2023-12-14', '18:07:37'),
(61, 4, 0, 0, '2023-12-14', '18:07:39'),
(62, 5, 1, 0, '2023-12-14', '18:08:13'),
(63, 5, 0, 0, '2023-12-14', '18:08:17'),
(64, 4, 1, 0, '2023-12-14', '18:08:30'),
(65, 4, 0, 0, '2023-12-14', '18:08:45'),
(66, 5, 1, 0, '2023-12-14', '18:08:51'),
(67, 5, 0, 0, '2023-12-14', '18:08:55'),
(68, 7, 1, 0, '2023-12-14', '18:39:08'),
(69, 5, 1, 0, '2023-12-14', '18:39:13'),
(70, 5, 0, 0, '2023-12-14', '18:39:15'),
(71, 7, 0, 0, '2023-12-14', '18:39:20'),
(72, 5, 1, 0, '2023-12-14', '18:43:31'),
(73, 5, 0, 0, '2023-12-14', '18:43:37'),
(74, 7, 1, 0, '2023-12-14', '18:43:39'),
(75, 7, 0, 0, '2023-12-14', '18:43:42'),
(76, 4, 1, 0, '2023-12-14', '20:05:21'),
(77, 4, 0, 0, '2023-12-14', '20:05:23'),
(78, 7, 1, 0, '2023-12-14', '20:05:24'),
(79, 7, 0, 0, '2023-12-14', '20:05:29'),
(80, 6, 1, 0, '2023-12-14', '20:05:48'),
(81, 5, 1, 0, '2023-12-14', '20:05:51'),
(82, 4, 1, 0, '2023-12-14', '20:05:53'),
(83, 4, 0, 0, '2023-12-14', '20:05:55'),
(84, 4, 1, 0, '2023-12-14', '20:05:58'),
(85, 7, 1, 0, '2023-12-14', '20:06:08'),
(86, 7, 0, 0, '2023-12-14', '20:06:10'),
(87, 6, 0, 0, '2023-12-14', '20:06:13'),
(88, 4, 0, 0, '2023-12-14', '20:06:15'),
(89, 5, 0, 0, '2023-12-14', '20:06:17'),
(90, 7, 1, 0, '2023-12-14', '20:06:20'),
(91, 7, 0, 0, '2023-12-14', '20:06:23'),
(92, 4, 1, 0, '2023-12-15', '10:56:14'),
(93, 6, 1, 0, '2023-12-15', '10:56:18'),
(94, 5, 1, 0, '2023-12-15', '10:56:20'),
(95, 7, 1, 0, '2023-12-15', '10:56:22'),
(96, 7, 0, 0, '2023-12-15', '10:56:25'),
(97, 6, 0, 0, '2023-12-15', '10:56:27'),
(98, 4, 0, 0, '2023-12-15', '10:56:29'),
(99, 5, 0, 0, '2023-12-15', '10:56:31'),
(100, 7, 1, 0, '2023-12-15', '10:56:34'),
(101, 7, 0, 0, '2023-12-15', '10:56:36');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `int_proceso_tipo`
--

CREATE TABLE `int_proceso_tipo` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `descripcion` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `int_proceso_tipo`
--

INSERT INTO `int_proceso_tipo` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Digital', 'Control Digital de un proceso'),
(2, 'Anal?gico', 'Control Analógico de un proceso'),
(3, 'Hibrido', 'Control Digital y Analógico de un proceso');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `int_proceso_vars`
--

CREATE TABLE `int_proceso_vars` (
  `id` int(10) UNSIGNED NOT NULL,
  `int_proceso_id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `descripcion` varchar(300) NOT NULL,
  `max_2` float NOT NULL,
  `min` float NOT NULL,
  `flag` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `int_proceso_vars`
--

INSERT INTO `int_proceso_vars` (`id`, `int_proceso_id`, `nombre`, `descripcion`, `max_2`, `min`, `flag`) VALUES
(1, 1, 'Error Nivel Tanque 1', 'Error del nivel en el tanque No. 1', 0.99999, 0, 0),
(2, 1, 'Error Nivel Tanque 2', 'Error del nivel en el tanque No. 2', 0.99999, 0, 0),
(3, 1, 'Salida Controlador Nivel Tanque 1', 'Salida Controlador del nivel en el tanque No. 1', 0.99999, 0, 0),
(4, 1, 'Salida Controlador Nivel Tanque 2', 'Salida Controlador del nivel en el tanque No. 2', 0.99999, 0, 0),
(5, 1, 'Medida Nivel Tanque 1', 'Medida del nivel en el tanque No. 1', 0.99999, 0, 0),
(6, 1, 'Medida Nivel Tanque 2', 'Medida del nivel en el tanque No. 2', 0.99999, 0, 0),
(7, 2, 'Error Temperatura Tanque 1', 'Error del temperatura en el tanque No. 1', 0.99999, 0, 0),
(8, 2, 'Salida Controlador Temperatura Tanque 1', 'Salida Controlador del temperatura en el tanque No. 1', 0.99999, 0, 0),
(9, 2, 'Medida Temperatura Tanque 1', 'Medida del temperatura en el tanque No. 1', 0.99999, 0, 0),
(10, 3, 'A0', 'Señal analogica de la entrada A0', 1023, 0, 0),
(11, 3, 'A1', 'Señal analogica de la entrada A1', 1023, 0, 0),
(12, 3, 'A2', 'Señal analogica de la entrada A2', 1023, 0, 0),
(13, 3, 'A3', 'Señal analogica de la entrada A3', 1023, 0, 0),
(14, 3, 'A4', 'Señal analogica de la entrada A4', 1023, 0, 0),
(15, 3, 'A5', 'Señal analogica de la entrada A5', 1023, 0, 0),
(16, 3, 'A6', 'Señal analogica de la entrada A6', 1023, 0, 0),
(17, 3, 'A7', 'Señal analogica de la entrada A7', 1023, 0, 0),
(18, 3, 'D0', 'Señal de entrada Digital D0', 1, 0, 0),
(19, 3, 'D1', 'Señal de entrada Digital D1', 1, 0, 0),
(20, 3, 'D2', 'Señal de entrada Digital D2', 1, 0, 0),
(21, 3, 'D3', 'Señal de entrada Digital D3', 1, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `int_proceso_vars_data`
--

CREATE TABLE `int_proceso_vars_data` (
  `id` int(10) UNSIGNED NOT NULL,
  `int_proceso_vars_id` int(10) UNSIGNED NOT NULL,
  `valor` float NOT NULL,
  `tiempo` float NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `int_proceso_vars_data`
--

INSERT INTO `int_proceso_vars_data` (`id`, `int_proceso_vars_id`, `valor`, `tiempo`, `fecha`, `hora`) VALUES
(1993, 10, 457, 0, '2023-12-15', '10:56:42'),
(1994, 10, 457, 100, '2023-12-15', '10:56:42'),
(1995, 10, 456, 200, '2023-12-15', '10:56:43'),
(1996, 10, 456, 300, '2023-12-15', '10:56:43'),
(1997, 10, 457, 400, '2023-12-15', '10:56:43'),
(1998, 10, 457, 500, '2023-12-15', '10:56:43'),
(1999, 10, 457, 600, '2023-12-15', '10:56:43'),
(2000, 10, 456, 700, '2023-12-15', '10:56:44'),
(2001, 10, 456, 800, '2023-12-15', '10:56:44'),
(2002, 10, 457, 900, '2023-12-15', '10:56:44'),
(2003, 10, 457, 1000, '2023-12-15', '10:56:44'),
(2004, 10, 457, 1100, '2023-12-15', '10:56:44'),
(2005, 10, 457, 1200, '2023-12-15', '10:56:45'),
(2006, 10, 457, 1300, '2023-12-15', '10:56:45'),
(2007, 10, 457, 1400, '2023-12-15', '10:56:45'),
(2008, 10, 457, 1500, '2023-12-15', '10:56:45'),
(2009, 10, 457, 1600, '2023-12-15', '10:56:45'),
(2010, 10, 457, 1700, '2023-12-15', '10:56:46'),
(2011, 10, 457, 1800, '2023-12-15', '10:56:46'),
(2012, 10, 457, 1900, '2023-12-15', '10:56:46'),
(2013, 10, 457, 2000, '2023-12-15', '10:56:46'),
(2014, 10, 457, 2100, '2023-12-15', '10:56:46'),
(2015, 10, 457, 2200, '2023-12-15', '10:56:47'),
(2016, 10, 457, 2300, '2023-12-15', '10:56:47'),
(2017, 10, 457, 2400, '2023-12-15', '10:56:47'),
(2018, 10, 457, 2500, '2023-12-15', '10:56:47'),
(2019, 10, 457, 2600, '2023-12-15', '10:56:47'),
(2020, 10, 456, 2700, '2023-12-15', '10:56:48'),
(2021, 10, 457, 2800, '2023-12-15', '10:56:48'),
(2022, 10, 457, 2900, '2023-12-15', '10:56:48'),
(2023, 10, 456, 3000, '2023-12-15', '10:56:48'),
(2024, 10, 457, 3100, '2023-12-15', '10:56:48'),
(2025, 10, 457, 3200, '2023-12-15', '10:56:49'),
(2026, 10, 457, 3300, '2023-12-15', '10:56:49'),
(2027, 10, 456, 3400, '2023-12-15', '10:56:49'),
(2028, 10, 456, 3500, '2023-12-15', '10:56:49'),
(2029, 10, 457, 3600, '2023-12-15', '10:56:49'),
(2030, 10, 457, 3700, '2023-12-15', '10:56:50'),
(2031, 10, 457, 3800, '2023-12-15', '10:56:50'),
(2032, 10, 457, 3900, '2023-12-15', '10:56:50'),
(2033, 10, 456, 4000, '2023-12-15', '10:56:50'),
(2034, 10, 457, 4100, '2023-12-15', '10:56:50'),
(2035, 10, 456, 4200, '2023-12-15', '10:56:51'),
(2036, 10, 457, 4300, '2023-12-15', '10:56:51'),
(2037, 10, 456, 4400, '2023-12-15', '10:56:51'),
(2038, 10, 456, 4500, '2023-12-15', '10:56:51'),
(2039, 10, 457, 4600, '2023-12-15', '10:56:51'),
(2040, 10, 457, 4700, '2023-12-15', '10:56:52'),
(2041, 10, 457, 4800, '2023-12-15', '10:56:52'),
(2042, 10, 456, 4900, '2023-12-15', '10:56:52'),
(2043, 10, 457, 5000, '2023-12-15', '10:56:52'),
(2044, 10, 456, 5100, '2023-12-15', '10:56:52'),
(2045, 10, 456, 5200, '2023-12-15', '10:56:53'),
(2046, 10, 456, 5300, '2023-12-15', '10:56:53'),
(2047, 10, 456, 5400, '2023-12-15', '10:56:53'),
(2048, 10, 457, 5500, '2023-12-15', '10:56:53'),
(2049, 10, 457, 5600, '2023-12-15', '10:56:53'),
(2050, 10, 457, 5700, '2023-12-15', '10:56:54'),
(2051, 10, 457, 5800, '2023-12-15', '10:56:54');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `int_usuarios`
--

CREATE TABLE `int_usuarios` (
  `id` int(10) UNSIGNED NOT NULL,
  `int_usuarios_tipo_id` int(10) UNSIGNED NOT NULL,
  `nombres` varchar(255) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `email` varchar(200) NOT NULL,
  `clave` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `int_usuarios`
--

INSERT INTO `int_usuarios` (`id`, `int_usuarios_tipo_id`, `nombres`, `apellidos`, `email`, `clave`) VALUES
(1, 1, 'Super', 'User', 'root@univalle.edu.co', '1234'),
(2, 2, 'Exp. 1', 'Controller', 'exp1.control@univalle.edu.co', '1234'),
(3, 3, 'Exp. 2', 'Controller', 'exp2.control@univalle.edu.co', '1234'),
(4, 4, 'Exp. 3', 'Controller', 'exp3.control@univalle.edu.co', '1234'),
(5, 5, 'Exp. 1', 'Monitor1', 'exp1.monitor1@univalle.edu.co', '1234'),
(6, 5, 'Exp. 1', 'Monitor2', 'exp1.monitor2@univalle.edu.co', '1234'),
(7, 6, 'Exp. 2', 'Monitor1', 'exp2.monitor1@univalle.edu.co', '1234'),
(8, 6, 'Exp. 2', 'Monitor2', 'exp2.monitor2@univalle.edu.co', '1234'),
(9, 7, 'Exp. 3', 'Monitor1', 'exp3.monitor1@univalle.edu.co', '1234'),
(10, 7, 'Exp. 3', 'Monitor2', 'exp3.monitor2@univalle.edu.co', '1234'),
(11, 8, 'Prueba', 'otra prueba', 'prueba@prueba.com', '*6658DCC4CA0C34FB17856E18A519A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `int_usuarios_priv`
--

CREATE TABLE `int_usuarios_priv` (
  `id` int(10) UNSIGNED NOT NULL,
  `int_usuarios_tipo_id` int(10) UNSIGNED NOT NULL,
  `control` enum('0','1') NOT NULL,
  `configuracion` enum('0','1') NOT NULL,
  `descarga_datos` enum('0','1') NOT NULL,
  `monitor` enum('0','1') NOT NULL,
  `envio_datos` enum('0','1') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `int_usuarios_priv`
--

INSERT INTO `int_usuarios_priv` (`id`, `int_usuarios_tipo_id`, `control`, `configuracion`, `descarga_datos`, `monitor`, `envio_datos`) VALUES
(1, 1, '1', '1', '1', '1', '1'),
(2, 2, '1', '1', '1', '1', '1'),
(3, 3, '1', '1', '1', '0', '1'),
(4, 4, '1', '1', '1', '1', '1'),
(5, 5, '0', '0', '1', '1', '0'),
(6, 6, '0', '0', '1', '1', '0'),
(7, 7, '0', '0', '1', '1', '0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `int_usuarios_proceso`
--

CREATE TABLE `int_usuarios_proceso` (
  `id` int(10) UNSIGNED NOT NULL,
  `int_proceso_id` int(10) UNSIGNED NOT NULL,
  `int_usuarios_id` int(10) UNSIGNED NOT NULL,
  `fecha` date NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  `hits` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `int_usuarios_proceso`
--

INSERT INTO `int_usuarios_proceso` (`id`, `int_proceso_id`, `int_usuarios_id`, `fecha`, `hora_inicio`, `hora_fin`, `hits`) VALUES
(1, 1, 2, '2014-05-05', '08:00:00', '10:00:00', 1),
(2, 1, 2, '2014-05-06', '08:00:00', '10:00:00', 1),
(3, 1, 2, '2014-05-07', '08:00:00', '10:00:00', 1),
(4, 1, 5, '2014-05-05', '10:00:00', '11:00:00', 1),
(5, 1, 5, '2014-05-05', '08:00:00', '10:00:00', 1),
(6, 1, 6, '2014-05-06', '10:00:00', '11:00:00', 1),
(7, 1, 6, '2014-05-06', '08:00:00', '10:00:00', 1),
(8, 1, 6, '2014-05-06', '12:00:00', '13:00:00', 1),
(9, 2, 3, '2014-05-07', '08:00:00', '10:00:00', 1),
(10, 2, 3, '2014-05-07', '14:00:00', '16:00:00', 1),
(11, 2, 3, '2014-05-08', '10:00:00', '12:00:00', 1),
(12, 2, 7, '2014-05-07', '10:00:00', '12:00:00', 1),
(13, 2, 7, '2014-05-08', '10:00:00', '12:00:00', 1),
(14, 2, 8, '2014-05-08', '10:00:00', '12:00:00', 1),
(15, 2, 8, '2014-05-07', '10:00:00', '12:00:00', 1),
(16, 2, 8, '2014-05-07', '14:00:00', '16:00:00', 1),
(17, 2, 8, '2014-05-09', '14:00:00', '16:00:00', 1),
(18, 3, 2, '2023-12-14', '12:45:54', '12:46:45', 1),
(19, 3, 2, '2023-12-14', '12:47:42', '12:50:21', 1),
(20, 3, 2, '2023-12-14', '12:53:05', '12:54:14', 1),
(21, 3, 2, '2023-12-14', '13:18:50', '13:21:58', 1),
(22, 3, 2, '2023-12-14', '13:35:40', '13:36:14', 1),
(23, 3, 2, '2023-12-14', '13:40:32', '13:40:35', 1),
(24, 3, 2, '2023-12-14', '13:43:12', '13:46:30', 1),
(25, 3, 4, '2023-12-14', '13:47:28', '13:51:28', 1),
(26, 3, 4, '2023-12-14', '13:52:44', '13:56:39', 1),
(27, 3, 4, '2023-12-14', '13:57:03', '14:00:01', 1),
(28, 3, 5, '2023-12-14', '14:00:42', '14:04:22', 1),
(29, 3, 4, '2023-12-14', '14:05:36', '14:07:19', 1),
(30, 3, 4, '2023-12-14', '14:10:09', '14:12:41', 1),
(31, 3, 4, '2023-12-14', '14:31:01', '14:37:05', 1),
(32, 3, 4, '2023-12-14', '15:01:05', '15:01:24', 1),
(33, 3, 4, '2023-12-14', '15:08:03', '15:08:23', 1),
(34, 3, 4, '2023-12-14', '15:09:36', '15:14:59', 1),
(35, 3, 5, '2023-12-14', '16:03:05', '16:03:28', 1),
(36, 3, 5, '2023-12-14', '16:05:19', '16:05:19', 1),
(37, 3, 6, '2023-12-14', '16:46:03', '16:46:13', 1),
(38, 3, 6, '2023-12-14', '16:47:13', '16:48:39', 1),
(39, 3, 6, '2023-12-14', '17:37:13', '17:37:13', 1),
(40, 3, 6, '2023-12-14', '17:37:58', '17:38:00', 1),
(41, 3, 6, '2023-12-14', '17:38:41', '17:38:41', 1),
(42, 3, 6, '2023-12-14', '17:42:42', '17:42:43', 1),
(43, 3, 6, '2023-12-14', '17:43:08', '17:52:46', 1),
(44, 3, 6, '2023-12-14', '17:59:51', '18:05:24', 1),
(45, 3, 6, '2023-12-14', '18:06:05', '18:09:39', 1),
(46, 3, 6, '2023-12-14', '18:10:06', '18:10:37', 1),
(47, 3, 6, '2023-12-14', '18:11:17', '18:16:33', 1),
(48, 3, 4, '2023-12-14', '18:23:20', '18:35:51', 1),
(49, 3, 7, '2023-12-14', '18:36:29', '18:36:51', 1),
(50, 3, 7, '2023-12-14', '18:37:02', '18:38:25', 1),
(51, 3, 7, '2023-12-14', '18:39:08', '18:48:53', 1),
(52, 3, 7, '2023-12-14', '19:26:06', '19:26:48', 1),
(53, 3, 7, '2023-12-14', '19:33:19', '19:49:19', 1),
(54, 3, 2, '2023-12-14', '20:03:18', '20:04:23', 1),
(55, 3, 2, '2023-12-14', '20:05:17', '20:06:42', 1),
(56, 3, 4, '2023-12-15', '09:51:34', '09:54:42', 1),
(57, 3, 3, '2023-12-15', '10:05:06', '10:07:31', 1),
(58, 3, 4, '2023-12-15', '10:09:04', '10:13:14', 1),
(59, 3, 4, '2023-12-15', '10:26:42', '10:31:02', 1),
(60, 3, 2, '2023-12-15', '10:56:11', '10:56:54', 1),
(61, 3, 2, '2023-12-15', '11:10:24', '11:10:47', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `int_usuarios_tipo`
--

CREATE TABLE `int_usuarios_tipo` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `descripcion` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `int_usuarios_tipo`
--

INSERT INTO `int_usuarios_tipo` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Admin', 'Administrador'),
(2, 'ControlExp1', 'Controlador Exp. No. 1'),
(3, 'ControlExp2', 'Controlador Exp. No. 2'),
(4, 'ControlExp3', 'Controlador Exp. No. 3'),
(5, 'ViewExp1', 'Supervisión Exp. No. 1'),
(6, 'ViewExp2', 'Supervisión Exp. No. 2'),
(7, 'ViewExp3', 'Supervisión Exp. No. 3'),
(8, 'ControlExpNo4', 'Controlador Exp. No. 4'),
(9, 'ViewExpNo4', 'Supervisión del Exp. No. 4'),
(10, 'jdbcTest', 'jdbc Text No 1'),
(11, 'jdbcTest', 'Jdbc Test No 1'),
(12, 'JDBC_0', 'JDBC_user_0'),
(13, 'JDBC_1', 'JDBC_user_1'),
(14, 'JDBC_2', 'JDBC_user_2'),
(15, 'JDBC_3', 'JDBC_user_3'),
(16, 'JDBC_4', 'JDBC_user_4'),
(17, 'JDBC_5', 'JDBC_user_5'),
(18, 'JDBC_6', 'JDBC_user_6'),
(19, 'JDBC_7', 'JDBC_user_7'),
(20, 'JDBC_8', 'JDBC_user_8'),
(21, 'JDBC_9', 'JDBC_user_9'),
(22, 'JDBC_10', 'JDBC_user_10');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `int_proceso`
--
ALTER TABLE `int_proceso`
  ADD PRIMARY KEY (`id`),
  ADD KEY `int_proceo_FKIndex1` (`int_proceso_tipo_id`);

--
-- Indices de la tabla `int_proceso_control`
--
ALTER TABLE `int_proceso_control`
  ADD PRIMARY KEY (`id`),
  ADD KEY `int_proceso_control_FKIndex1` (`int_proceso_id`);

--
-- Indices de la tabla `int_proceso_refs`
--
ALTER TABLE `int_proceso_refs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `int_proceso_refs_FKIndex1` (`int_proceso_id`);

--
-- Indices de la tabla `int_proceso_refs_data`
--
ALTER TABLE `int_proceso_refs_data`
  ADD PRIMARY KEY (`id`),
  ADD KEY `int_proceso_refs_data_FKIndex1` (`int_proceso_refs_id`);

--
-- Indices de la tabla `int_proceso_tipo`
--
ALTER TABLE `int_proceso_tipo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `int_proceso_vars`
--
ALTER TABLE `int_proceso_vars`
  ADD PRIMARY KEY (`id`),
  ADD KEY `int_proceso_vars_FKIndex1` (`int_proceso_id`);

--
-- Indices de la tabla `int_proceso_vars_data`
--
ALTER TABLE `int_proceso_vars_data`
  ADD PRIMARY KEY (`id`),
  ADD KEY `int_proceso_vars_data_FKIndex1` (`int_proceso_vars_id`);

--
-- Indices de la tabla `int_usuarios`
--
ALTER TABLE `int_usuarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `int_usuarios_FKIndex1` (`int_usuarios_tipo_id`);

--
-- Indices de la tabla `int_usuarios_priv`
--
ALTER TABLE `int_usuarios_priv`
  ADD PRIMARY KEY (`id`),
  ADD KEY `int_usuarios_priv_FKIndex1` (`int_usuarios_tipo_id`);

--
-- Indices de la tabla `int_usuarios_proceso`
--
ALTER TABLE `int_usuarios_proceso`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Table_12_FKIndex1` (`int_usuarios_id`),
  ADD KEY `Table_12_FKIndex2` (`int_proceso_id`);

--
-- Indices de la tabla `int_usuarios_tipo`
--
ALTER TABLE `int_usuarios_tipo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `int_proceso`
--
ALTER TABLE `int_proceso`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `int_proceso_control`
--
ALTER TABLE `int_proceso_control`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `int_proceso_refs`
--
ALTER TABLE `int_proceso_refs`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `int_proceso_refs_data`
--
ALTER TABLE `int_proceso_refs_data`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- AUTO_INCREMENT de la tabla `int_proceso_tipo`
--
ALTER TABLE `int_proceso_tipo`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `int_proceso_vars`
--
ALTER TABLE `int_proceso_vars`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `int_proceso_vars_data`
--
ALTER TABLE `int_proceso_vars_data`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2052;

--
-- AUTO_INCREMENT de la tabla `int_usuarios`
--
ALTER TABLE `int_usuarios`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `int_usuarios_priv`
--
ALTER TABLE `int_usuarios_priv`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `int_usuarios_proceso`
--
ALTER TABLE `int_usuarios_proceso`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT de la tabla `int_usuarios_tipo`
--
ALTER TABLE `int_usuarios_tipo`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `int_proceso`
--
ALTER TABLE `int_proceso`
  ADD CONSTRAINT `int_proceso_ibfk_1` FOREIGN KEY (`int_proceso_tipo_id`) REFERENCES `int_proceso_tipo` (`id`) ON UPDATE NO ACTION;

--
-- Filtros para la tabla `int_proceso_control`
--
ALTER TABLE `int_proceso_control`
  ADD CONSTRAINT `int_proceso_control_ibfk_1` FOREIGN KEY (`int_proceso_id`) REFERENCES `int_proceso` (`id`) ON UPDATE NO ACTION;

--
-- Filtros para la tabla `int_proceso_refs`
--
ALTER TABLE `int_proceso_refs`
  ADD CONSTRAINT `int_proceso_refs_ibfk_1` FOREIGN KEY (`int_proceso_id`) REFERENCES `int_proceso` (`id`) ON UPDATE NO ACTION;

--
-- Filtros para la tabla `int_proceso_refs_data`
--
ALTER TABLE `int_proceso_refs_data`
  ADD CONSTRAINT `int_proceso_refs_data_ibfk_1` FOREIGN KEY (`int_proceso_refs_id`) REFERENCES `int_proceso_refs` (`id`) ON UPDATE NO ACTION;

--
-- Filtros para la tabla `int_proceso_vars`
--
ALTER TABLE `int_proceso_vars`
  ADD CONSTRAINT `int_proceso_vars_ibfk_1` FOREIGN KEY (`int_proceso_id`) REFERENCES `int_proceso` (`id`) ON UPDATE NO ACTION;

--
-- Filtros para la tabla `int_proceso_vars_data`
--
ALTER TABLE `int_proceso_vars_data`
  ADD CONSTRAINT `int_proceso_vars_data_ibfk_1` FOREIGN KEY (`int_proceso_vars_id`) REFERENCES `int_proceso_vars` (`id`);

--
-- Filtros para la tabla `int_usuarios`
--
ALTER TABLE `int_usuarios`
  ADD CONSTRAINT `int_usuarios_ibfk_1` FOREIGN KEY (`int_usuarios_tipo_id`) REFERENCES `int_usuarios_tipo` (`id`) ON UPDATE NO ACTION;

--
-- Filtros para la tabla `int_usuarios_priv`
--
ALTER TABLE `int_usuarios_priv`
  ADD CONSTRAINT `int_usuarios_priv_ibfk_1` FOREIGN KEY (`int_usuarios_tipo_id`) REFERENCES `int_usuarios_tipo` (`id`) ON UPDATE NO ACTION;

--
-- Filtros para la tabla `int_usuarios_proceso`
--
ALTER TABLE `int_usuarios_proceso`
  ADD CONSTRAINT `int_usuarios_proceso_ibfk_1` FOREIGN KEY (`int_usuarios_id`) REFERENCES `int_usuarios` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `int_usuarios_proceso_ibfk_2` FOREIGN KEY (`int_proceso_id`) REFERENCES `int_proceso` (`id`) ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
