-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-09-2025 a las 18:32:41
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
-- Base de datos: `bd_sistema_bancario`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_cliente`
--

CREATE TABLE `tb_cliente` (
  `idCliente` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `dni` varchar(20) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `fechaRegistro` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_cliente`
--

INSERT INTO `tb_cliente` (`idCliente`, `nombre`, `apellido`, `dni`, `telefono`, `email`, `fechaRegistro`) VALUES
(1, 'Ana', 'Pérez', '12345678', '555-1234', 'ana.perez@gmail.com', '2025-09-24'),
(2, 'Luis', 'Gómez', '87654321', '555-5678', 'luis.gomez@gmail.com', '2025-09-24'),
(3, 'María', 'López', '11223344', '555-9012', 'maria.lopez@gmail.com', '2025-09-24');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_cuenta`
--

CREATE TABLE `tb_cuenta` (
  `idCuenta` int(11) NOT NULL,
  `idCliente` int(11) DEFAULT NULL,
  `numeroCuenta` varchar(20) DEFAULT NULL,
  `saldo` decimal(15,2) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `fechaApertura` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_cuenta`
--

INSERT INTO `tb_cuenta` (`idCuenta`, `idCliente`, `numeroCuenta`, `saldo`, `estado`, `fechaApertura`) VALUES
(1, 1, '1001-0001', 1500.00, 1, '2025-09-24'),
(2, 2, '1002-0002', 2500.50, 1, '2025-09-24'),
(3, 3, '1003-0003', 500.75, 1, '2025-09-24');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_transaccion`
--

CREATE TABLE `tb_transaccion` (
  `idTransaccion` int(11) NOT NULL,
  `idCuentaOrigen` int(11) DEFAULT NULL,
  `idCuentaDestino` int(11) DEFAULT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `monto` decimal(15,2) DEFAULT NULL,
  `fecha` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_transaccion`
--

INSERT INTO `tb_transaccion` (`idTransaccion`, `idCuentaOrigen`, `idCuentaDestino`, `tipo`, `monto`, `fecha`) VALUES
(1, 1, 2, 'TRANSFERENCIA', 200.00, '2025-09-24 10:30:00'),
(2, 2, 3, 'TRANSFERENCIA', 500.50, '2025-09-24 11:00:00'),
(3, NULL, 1, 'DEPOSITO', 1000.00, '2025-09-24 12:00:00'),
(4, 3, NULL, 'RETIRO', 200.00, '2025-09-24 12:30:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_usuario`
--

CREATE TABLE `tb_usuario` (
  `idUsuario` int(11) NOT NULL,
  `idCliente` int(11) DEFAULT NULL,
  `usuario` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_usuario`
--

INSERT INTO `tb_usuario` (`idUsuario`, `idCliente`, `usuario`, `password`, `estado`) VALUES
(1, 1, 'ana123', 'passwordAna', 1),
(2, 2, 'luis456', 'passwordLuis', 1),
(3, 3, 'maria789', 'passwordMaria', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tb_cliente`
--
ALTER TABLE `tb_cliente`
  ADD PRIMARY KEY (`idCliente`),
  ADD UNIQUE KEY `dni` (`dni`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `tb_cuenta`
--
ALTER TABLE `tb_cuenta`
  ADD PRIMARY KEY (`idCuenta`),
  ADD UNIQUE KEY `numeroCuenta` (`numeroCuenta`),
  ADD KEY `idCliente` (`idCliente`);

--
-- Indices de la tabla `tb_transaccion`
--
ALTER TABLE `tb_transaccion`
  ADD PRIMARY KEY (`idTransaccion`),
  ADD KEY `idCuentaOrigen` (`idCuentaOrigen`),
  ADD KEY `idCuentaDestino` (`idCuentaDestino`);

--
-- Indices de la tabla `tb_usuario`
--
ALTER TABLE `tb_usuario`
  ADD PRIMARY KEY (`idUsuario`),
  ADD UNIQUE KEY `usuario` (`usuario`),
  ADD KEY `idCliente` (`idCliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tb_cliente`
--
ALTER TABLE `tb_cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tb_cuenta`
--
ALTER TABLE `tb_cuenta`
  MODIFY `idCuenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tb_transaccion`
--
ALTER TABLE `tb_transaccion`
  MODIFY `idTransaccion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tb_usuario`
--
ALTER TABLE `tb_usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tb_cuenta`
--
ALTER TABLE `tb_cuenta`
  ADD CONSTRAINT `tb_cuenta_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `tb_cliente` (`idCliente`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `tb_transaccion`
--
ALTER TABLE `tb_transaccion`
  ADD CONSTRAINT `tb_transaccion_ibfk_1` FOREIGN KEY (`idCuentaOrigen`) REFERENCES `tb_cuenta` (`idCuenta`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_transaccion_ibfk_2` FOREIGN KEY (`idCuentaDestino`) REFERENCES `tb_cuenta` (`idCuenta`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `tb_usuario`
--
ALTER TABLE `tb_usuario`
  ADD CONSTRAINT `tb_usuario_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `tb_cliente` (`idCliente`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
