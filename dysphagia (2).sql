-- phpMyAdmin SQL Dump
-- version 4.6.6deb5ubuntu0.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 03/08/2021 às 08:44
-- Versão do servidor: 5.7.35-0ubuntu0.18.04.1
-- Versão do PHP: 7.2.34-23+ubuntu18.04.1+deb.sury.org+1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `dysphagia`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `addresses`
--

CREATE TABLE `addresses` (
  `id` int(10) UNSIGNED NOT NULL,
  `country` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `user_id` int(10) UNSIGNED DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `addresses`
--

INSERT INTO `addresses` (`id`, `country`, `state`, `city`, `street`, `user_id`, `created_at`, `updated_at`) VALUES
(1, 'Brasil', 'RN', NULL, NULL, 1, '2021-08-03 08:36:47', '2021-08-03 08:36:47');

-- --------------------------------------------------------

--
-- Estrutura para tabela `adonis_schema`
--

CREATE TABLE `adonis_schema` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `batch` int(11) DEFAULT NULL,
  `migration_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `adonis_schema`
--

INSERT INTO `adonis_schema` (`id`, `name`, `batch`, `migration_time`) VALUES
(15, '1503250034279_user', 1, '2021-08-03 11:30:28'),
(16, '1503250034280_token', 1, '2021-08-03 11:30:30'),
(17, '1621255479479_address_schema', 1, '2021-08-03 11:30:31'),
(18, '1621452660500_specialty_schema', 1, '2021-08-03 11:30:32'),
(19, '1621464214921_disease_schema', 1, '2021-08-03 11:30:32'),
(20, '1621547458347_user_disease_schema', 1, '2021-08-03 11:30:36'),
(21, '1622117143574_device_data_schema', 1, '2021-08-03 11:30:37'),
(22, '1622117241007_device_data_classified_schema', 1, '2021-08-03 11:30:38'),
(23, '1721452351008_healthcare_professional_schema', 1, '2021-08-03 11:30:40'),
(24, '1821444658599_medical_record_schema', 1, '2021-08-03 11:30:42'),
(25, '1821463558820_notification_schema', 1, '2021-08-03 11:30:45'),
(26, '1922033220366_eat_10_schema', 1, '2021-08-03 11:30:47'),
(27, '1922033282397_global_nutritional_assessment_schema', 1, '2021-08-03 11:30:49'),
(28, '2021444713426_consultation_schema', 1, '2021-08-03 11:30:54');

-- --------------------------------------------------------

--
-- Estrutura para tabela `consultations`
--

CREATE TABLE `consultations` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED DEFAULT NULL,
  `healthcare_professional_id` int(10) UNSIGNED DEFAULT NULL,
  `observation` varchar(500) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `consultations`
--

INSERT INTO `consultations` (`id`, `user_id`, `healthcare_professional_id`, `observation`, `created_at`, `updated_at`) VALUES
(1, 1, 1, 'Observation example', '2021-08-03 08:36:18', '2021-08-03 08:36:18');

-- --------------------------------------------------------

--
-- Estrutura para tabela `device_data`
--

CREATE TABLE `device_data` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED DEFAULT NULL,
  `sound_value` float(6,4) DEFAULT NULL,
  `movement_value1` float(6,4) DEFAULT NULL,
  `movement_value2` float(6,4) DEFAULT NULL,
  `movement_value3` float(6,4) DEFAULT NULL,
  `datetime` varchar(255) DEFAULT NULL,
  `duration` float(5,3) DEFAULT NULL,
  `is_present` int(11) DEFAULT '1',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `device_data_classifieds`
--

CREATE TABLE `device_data_classifieds` (
  `id` int(10) UNSIGNED NOT NULL,
  `device_data_id` int(10) UNSIGNED DEFAULT NULL,
  `dysphagia_level` enum('N1_N2','N2_N3','N3_N4','N4_N5','N5_N6','N6_N7','NOT_CLASSIFIED') DEFAULT 'NOT_CLASSIFIED',
  `type_food` enum('LIQUID','PASTY','SOLID','NOT_CLASSIFIED') DEFAULT 'NOT_CLASSIFIED',
  `datetime` varchar(255) DEFAULT NULL,
  `duration` float(5,3) DEFAULT NULL,
  `indicated_foods` varchar(255) DEFAULT NULL,
  `consistency_foods` varchar(255) DEFAULT NULL,
  `has_obstruction` enum('YES','NOT','NOT_CLASSIFIED') DEFAULT 'NOT_CLASSIFIED',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `diseases`
--

CREATE TABLE `diseases` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `diseases`
--

INSERT INTO `diseases` (`id`, `name`, `created_at`, `updated_at`) VALUES
(1, 'Diabetes', '2021-08-03 08:37:23', NULL),
(2, 'Hipertensão', '2021-08-03 08:37:23', NULL);

-- --------------------------------------------------------

--
-- Estrutura para tabela `eat_10_s`
--

CREATE TABLE `eat_10_s` (
  `id` int(10) UNSIGNED NOT NULL,
  `medical_record_id` int(10) UNSIGNED DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `global_nutritional_assessments`
--

CREATE TABLE `global_nutritional_assessments` (
  `id` int(10) UNSIGNED NOT NULL,
  `medical_record_id` int(10) UNSIGNED DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `healthcare_professionals`
--

CREATE TABLE `healthcare_professionals` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(254) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `specialty_id` int(10) UNSIGNED DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `healthcare_professionals`
--

INSERT INTO `healthcare_professionals` (`id`, `name`, `email`, `password`, `specialty_id`, `created_at`, `updated_at`) VALUES
(1, 'Usuário Nutricionista', 'usuario@nutri', '$2a$10$LG4WGmhljBnk6gCr847Hs.AnTzMNtQ5AnWBQ2G.60iEESRrysBulm', 3, '2021-08-03 08:35:17', '2021-08-03 08:35:17');

-- --------------------------------------------------------

--
-- Estrutura para tabela `medical_records`
--

CREATE TABLE `medical_records` (
  `id` int(10) UNSIGNED NOT NULL,
  `height` int(11) DEFAULT NULL,
  `sex` enum('male','female') DEFAULT NULL,
  `physical_type` enum('brevilineo','normolineo','longilineo') DEFAULT NULL,
  `current_weight` float(6,3) DEFAULT NULL,
  `usual_weight` float(6,3) DEFAULT NULL,
  `ideal_weight` float(6,3) DEFAULT NULL,
  `usual_weight_percentage` float(6,3) DEFAULT NULL,
  `ideal_weight_percentage` float(6,3) DEFAULT NULL,
  `loss_weight_percentage` float(6,3) DEFAULT NULL,
  `bmi` float(8,2) DEFAULT NULL,
  `knee_height` int(11) DEFAULT NULL,
  `arm_circumference` int(11) DEFAULT NULL,
  `calf_circumference` int(11) DEFAULT NULL,
  `waist_circumference` int(11) DEFAULT NULL,
  `depletion` enum('mild','moderate','severe') DEFAULT NULL,
  `basal_energy_spend` float(6,3) DEFAULT NULL,
  `total_energy_expenditure` float(6,3) DEFAULT NULL,
  `dentition` enum('absent','present','prosthesis') DEFAULT NULL,
  `work_hours` int(11) DEFAULT NULL,
  `has_allergy` tinyint(1) DEFAULT NULL,
  `alcohol` tinyint(1) DEFAULT NULL,
  `tobacco` tinyint(1) DEFAULT NULL,
  `user_id` int(10) UNSIGNED DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `medical_records`
--

INSERT INTO `medical_records` (`id`, `height`, `sex`, `physical_type`, `current_weight`, `usual_weight`, `ideal_weight`, `usual_weight_percentage`, `ideal_weight_percentage`, `loss_weight_percentage`, `bmi`, `knee_height`, `arm_circumference`, `calf_circumference`, `waist_circumference`, `depletion`, `basal_energy_spend`, `total_energy_expenditure`, `dentition`, `work_hours`, `has_allergy`, `alcohol`, `tobacco`, `user_id`, `created_at`, `updated_at`) VALUES
(1, 165, 'male', 'brevilineo', 55.000, 55.000, 65.000, NULL, NULL, NULL, 20.20, NULL, NULL, NULL, NULL, 'mild', NULL, NULL, 'present', NULL, NULL, NULL, NULL, 1, '2021-08-03 08:42:22', '2021-08-03 08:42:22');

-- --------------------------------------------------------

--
-- Estrutura para tabela `notifications`
--

CREATE TABLE `notifications` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED DEFAULT NULL,
  `medical_id` int(10) UNSIGNED DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `status` enum('read','unread') DEFAULT NULL,
  `type` enum('alert','recomendation','reminder') DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `notifications`
--

INSERT INTO `notifications` (`id`, `user_id`, `medical_id`, `message`, `status`, `type`, `created_at`, `updated_at`) VALUES
(1, 1, 1, 'Alimentação Supervisionada', 'unread', 'recomendation', '2021-08-03 08:41:00', NULL);

-- --------------------------------------------------------

--
-- Estrutura para tabela `specialties`
--

CREATE TABLE `specialties` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `specialties`
--

INSERT INTO `specialties` (`id`, `name`, `created_at`, `updated_at`) VALUES
(1, 'Enfermeiro', '2021-08-03 08:32:13', NULL),
(2, 'Fonoaudiólogo', '2021-08-03 08:32:13', NULL),
(3, 'Nutricionista', '2021-08-03 08:32:13', NULL);

-- --------------------------------------------------------

--
-- Estrutura para tabela `tokens`
--

CREATE TABLE `tokens` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED DEFAULT NULL,
  `token` varchar(255) NOT NULL,
  `type` varchar(80) NOT NULL,
  `is_revoked` tinyint(1) DEFAULT '0',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `fullname` varchar(80) NOT NULL,
  `email` varchar(254) NOT NULL,
  `password` varchar(60) NOT NULL,
  `cpf` varchar(12) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `sex` enum('male','female') DEFAULT NULL,
  `type` enum('patient','nutritionist','speech_therapist','nurse') NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `users`
--

INSERT INTO `users` (`id`, `fullname`, `email`, `password`, `cpf`, `birthdate`, `sex`, `type`, `created_at`, `updated_at`) VALUES
(1, 'Jose Santos', 'jose@mail', '$2a$10$tt5URh9jGesfARcy.0QEVulfj7ckNU7bqjSm6Mks4l5CAFGuDeXfC', '12312312323', '1980-07-26', 'male', 'patient', '2021-08-03 08:36:08', '2021-08-03 08:36:08');

-- --------------------------------------------------------

--
-- Estrutura para tabela `user_disease`
--

CREATE TABLE `user_disease` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED DEFAULT NULL,
  `disease_id` int(10) UNSIGNED DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `user_disease`
--

INSERT INTO `user_disease` (`id`, `user_id`, `disease_id`, `created_at`, `updated_at`) VALUES
(1, 1, 1, '2021-08-03 08:39:00', NULL),
(2, 1, 2, '2021-08-03 08:40:00', NULL);

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `addresses_user_id_foreign` (`user_id`);

--
-- Índices de tabela `adonis_schema`
--
ALTER TABLE `adonis_schema`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `consultations`
--
ALTER TABLE `consultations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_consultation_id` (`user_id`),
  ADD KEY `healthcare_professional_ consultation_id` (`healthcare_professional_id`);

--
-- Índices de tabela `device_data`
--
ALTER TABLE `device_data`
  ADD PRIMARY KEY (`id`),
  ADD KEY `device_data_user_id_foreign` (`user_id`);

--
-- Índices de tabela `device_data_classifieds`
--
ALTER TABLE `device_data_classifieds`
  ADD PRIMARY KEY (`id`),
  ADD KEY `device_data_classifieds_device_data_id_foreign` (`device_data_id`);

--
-- Índices de tabela `diseases`
--
ALTER TABLE `diseases`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `eat_10_s`
--
ALTER TABLE `eat_10_s`
  ADD PRIMARY KEY (`id`),
  ADD KEY `eat_10_s_medical_record_id_foreign` (`medical_record_id`);

--
-- Índices de tabela `global_nutritional_assessments`
--
ALTER TABLE `global_nutritional_assessments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `medical_record_id` (`medical_record_id`);

--
-- Índices de tabela `healthcare_professionals`
--
ALTER TABLE `healthcare_professionals`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `healthcare_professionals_email_unique` (`email`),
  ADD KEY `healthcare_professionals_specialty_id_foreign` (`specialty_id`);

--
-- Índices de tabela `medical_records`
--
ALTER TABLE `medical_records`
  ADD PRIMARY KEY (`id`),
  ADD KEY `medical_records_user_id_foreign` (`user_id`);

--
-- Índices de tabela `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `notifications_user_id_foreign` (`user_id`),
  ADD KEY `notifications_medical_id_foreign` (`medical_id`);

--
-- Índices de tabela `specialties`
--
ALTER TABLE `specialties`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `tokens`
--
ALTER TABLE `tokens`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `tokens_token_unique` (`token`),
  ADD KEY `tokens_user_id_foreign` (`user_id`),
  ADD KEY `tokens_token_index` (`token`);

--
-- Índices de tabela `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- Índices de tabela `user_disease`
--
ALTER TABLE `user_disease`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `disease_id` (`disease_id`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `addresses`
--
ALTER TABLE `addresses`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de tabela `adonis_schema`
--
ALTER TABLE `adonis_schema`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT de tabela `consultations`
--
ALTER TABLE `consultations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de tabela `device_data`
--
ALTER TABLE `device_data`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `device_data_classifieds`
--
ALTER TABLE `device_data_classifieds`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `diseases`
--
ALTER TABLE `diseases`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de tabela `eat_10_s`
--
ALTER TABLE `eat_10_s`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `global_nutritional_assessments`
--
ALTER TABLE `global_nutritional_assessments`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `healthcare_professionals`
--
ALTER TABLE `healthcare_professionals`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de tabela `medical_records`
--
ALTER TABLE `medical_records`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de tabela `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de tabela `specialties`
--
ALTER TABLE `specialties`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de tabela `tokens`
--
ALTER TABLE `tokens`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de tabela `user_disease`
--
ALTER TABLE `user_disease`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `addresses`
--
ALTER TABLE `addresses`
  ADD CONSTRAINT `addresses_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Restrições para tabelas `consultations`
--
ALTER TABLE `consultations`
  ADD CONSTRAINT `consultations_healthcare_professional_id_foreign` FOREIGN KEY (`healthcare_professional_id`) REFERENCES `healthcare_professionals` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `consultations_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Restrições para tabelas `device_data`
--
ALTER TABLE `device_data`
  ADD CONSTRAINT `device_data_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Restrições para tabelas `device_data_classifieds`
--
ALTER TABLE `device_data_classifieds`
  ADD CONSTRAINT `device_data_classifieds_device_data_id_foreign` FOREIGN KEY (`device_data_id`) REFERENCES `device_data` (`id`) ON DELETE CASCADE;

--
-- Restrições para tabelas `eat_10_s`
--
ALTER TABLE `eat_10_s`
  ADD CONSTRAINT `eat_10_s_medical_record_id_foreign` FOREIGN KEY (`medical_record_id`) REFERENCES `medical_records` (`id`) ON DELETE CASCADE;

--
-- Restrições para tabelas `global_nutritional_assessments`
--
ALTER TABLE `global_nutritional_assessments`
  ADD CONSTRAINT `global_nutritional_assessments_medical_record_id_foreign` FOREIGN KEY (`medical_record_id`) REFERENCES `medical_records` (`id`) ON DELETE CASCADE;

--
-- Restrições para tabelas `healthcare_professionals`
--
ALTER TABLE `healthcare_professionals`
  ADD CONSTRAINT `healthcare_professionals_specialty_id_foreign` FOREIGN KEY (`specialty_id`) REFERENCES `specialties` (`id`);

--
-- Restrições para tabelas `medical_records`
--
ALTER TABLE `medical_records`
  ADD CONSTRAINT `medical_records_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Restrições para tabelas `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `notifications_medical_id_foreign` FOREIGN KEY (`medical_id`) REFERENCES `healthcare_professionals` (`id`),
  ADD CONSTRAINT `notifications_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Restrições para tabelas `tokens`
--
ALTER TABLE `tokens`
  ADD CONSTRAINT `tokens_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Restrições para tabelas `user_disease`
--
ALTER TABLE `user_disease`
  ADD CONSTRAINT `user_disease_disease_id_foreign` FOREIGN KEY (`disease_id`) REFERENCES `diseases` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_disease_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
