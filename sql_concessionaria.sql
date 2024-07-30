-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.4.32-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.7.0.6850
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para concessionaria
CREATE DATABASE IF NOT EXISTS `concessionaria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `concessionaria`;

-- Copiando estrutura para tabela concessionaria.agendamentos
CREATE TABLE IF NOT EXISTS `agendamentos` (
  `agendamento_id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_id` int(11) DEFAULT NULL,
  `carro_id` int(11) DEFAULT NULL,
  `data_agendamento` date DEFAULT NULL,
  `descricao` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`agendamento_id`),
  KEY `cliente_id` (`cliente_id`),
  KEY `carro_id` (`carro_id`),
  CONSTRAINT `agendamentos_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`cliente_id`),
  CONSTRAINT `agendamentos_ibfk_2` FOREIGN KEY (`carro_id`) REFERENCES `carro` (`carro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela concessionaria.agendamentos: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela concessionaria.carro
CREATE TABLE IF NOT EXISTS `carro` (
  `carro_id` int(11) NOT NULL AUTO_INCREMENT,
  `marca` varchar(50) NOT NULL,
  `modelo` varchar(50) NOT NULL,
  `ano` varchar(10) NOT NULL,
  `cor` varchar(30) NOT NULL,
  `preco` decimal(10,2) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  `percentual_comissao` decimal(5,2) NOT NULL,
  PRIMARY KEY (`carro_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela concessionaria.carro: ~2 rows (aproximadamente)
INSERT INTO `carro` (`carro_id`, `marca`, `modelo`, `ano`, `cor`, `preco`, `status`, `percentual_comissao`) VALUES
	(2, 'Citroen', 'C3 GLX', '2011', 'Preto', 27000.00, 'Disponivel', 3.00),
	(4, 'Chevrolet', 'Corsa', '2002', 'prata', 15000.00, 'Disponivel', 3.00);

-- Copiando estrutura para tabela concessionaria.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `cliente_id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cliente_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela concessionaria.cliente: ~1 rows (aproximadamente)
INSERT INTO `cliente` (`cliente_id`, `nome`, `endereco`, `telefone`, `email`) VALUES
	(3, 'Carlos Eduardo Silva', 'Rua das Andorinhas, 87', '84984984', 'carloseduardodasilvafd@gmail.com');

-- Copiando estrutura para tabela concessionaria.detalhes_financiamento
CREATE TABLE IF NOT EXISTS `detalhes_financiamento` (
  `financiamento_id` int(11) NOT NULL AUTO_INCREMENT,
  `pagamento_id` int(11) DEFAULT NULL,
  `entrada` decimal(10,2) DEFAULT NULL,
  `parcelas` int(11) DEFAULT NULL,
  `valor_parcela` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`financiamento_id`),
  KEY `pagamento_id` (`pagamento_id`),
  CONSTRAINT `detalhes_financiamento_ibfk_1` FOREIGN KEY (`pagamento_id`) REFERENCES `pagamento` (`pagamento_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela concessionaria.detalhes_financiamento: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela concessionaria.funcionario
CREATE TABLE IF NOT EXISTS `funcionario` (
  `funcionario_id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `cargo` varchar(50) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `salario_base` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`funcionario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela concessionaria.funcionario: ~4 rows (aproximadamente)
INSERT INTO `funcionario` (`funcionario_id`, `nome`, `cargo`, `telefone`, `email`, `salario_base`) VALUES
	(1, 'Fernando Torres', 'Vendedor', '98516489', 'fernandotorres3424e@gmail.com', 1500.00),
	(2, 'Luis Carlos Fernandes', 'Vendedor', '28948984', 'luiscarlosfernandes344@gmail.com', 1500.00),
	(3, 'Renata Oliveira', 'Secretaria', '988819561', 'renataoliveira4543@gmail.com', 2000.00),
	(7, 'Catarina Rocha', 'Secretaria', '298489', 'catarinafsd4@gmail.com', 1600.00);

-- Copiando estrutura para tabela concessionaria.pagamento
CREATE TABLE IF NOT EXISTS `pagamento` (
  `pagamento_id` int(11) NOT NULL AUTO_INCREMENT,
  `venda_id` int(11) DEFAULT NULL,
  `tipo_pagamento` enum('cartão de crédito','débito','transferência','boleto','financiamento') DEFAULT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  `data_pagamento` date DEFAULT NULL,
  `metodo_pagamento` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pagamento_id`),
  KEY `venda_id` (`venda_id`),
  CONSTRAINT `pagamento_ibfk_1` FOREIGN KEY (`venda_id`) REFERENCES `venda` (`venda_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela concessionaria.pagamento: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela concessionaria.preparacao
CREATE TABLE IF NOT EXISTS `preparacao` (
  `preparacao_id` int(11) NOT NULL AUTO_INCREMENT,
  `carro_id` int(11) DEFAULT NULL,
  `data_inicio` date DEFAULT NULL,
  `data_fim` date DEFAULT NULL,
  `descricao` varchar(50) DEFAULT NULL,
  `custo` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`preparacao_id`),
  KEY `carro_id` (`carro_id`),
  CONSTRAINT `preparacao_ibfk_1` FOREIGN KEY (`carro_id`) REFERENCES `carro` (`carro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela concessionaria.preparacao: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela concessionaria.salarios_funcionario
CREATE TABLE IF NOT EXISTS `salarios_funcionario` (
  `salario_id` int(11) NOT NULL AUTO_INCREMENT,
  `funcionario_id` int(11) DEFAULT NULL,
  `mes` int(11) DEFAULT NULL,
  `ano` int(11) DEFAULT NULL,
  `salario_base` decimal(10,2) DEFAULT NULL,
  `valor_comissao` decimal(10,2) DEFAULT NULL,
  `salario_total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`salario_id`),
  KEY `funcionario_id` (`funcionario_id`),
  CONSTRAINT `salarios_funcionario_ibfk_1` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`funcionario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela concessionaria.salarios_funcionario: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela concessionaria.test_drive
CREATE TABLE IF NOT EXISTS `test_drive` (
  `test_drive_id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_id` int(11) DEFAULT NULL,
  `carro_id` int(11) DEFAULT NULL,
  `data_test_drive` date DEFAULT NULL,
  `resultado` enum('interessado','não interessado') DEFAULT NULL,
  PRIMARY KEY (`test_drive_id`),
  KEY `cliente_id` (`cliente_id`),
  KEY `carro_id` (`carro_id`),
  CONSTRAINT `test_drive_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`cliente_id`),
  CONSTRAINT `test_drive_ibfk_2` FOREIGN KEY (`carro_id`) REFERENCES `carro` (`carro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela concessionaria.test_drive: ~0 rows (aproximadamente)

-- Copiando estrutura para tabela concessionaria.venda
CREATE TABLE IF NOT EXISTS `venda` (
  `venda_id` int(11) NOT NULL AUTO_INCREMENT,
  `carro_id` int(11) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `funcionario_id` int(11) DEFAULT NULL,
  `data_venda` date DEFAULT NULL,
  `preco_venda` decimal(10,2) DEFAULT NULL,
  `valor_comissao` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`venda_id`),
  KEY `carro_id` (`carro_id`),
  KEY `cliente_id` (`cliente_id`),
  KEY `funcionario_id` (`funcionario_id`),
  CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`carro_id`) REFERENCES `carro` (`carro_id`),
  CONSTRAINT `venda_ibfk_2` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`cliente_id`),
  CONSTRAINT `venda_ibfk_3` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`funcionario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela concessionaria.venda: ~0 rows (aproximadamente)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
