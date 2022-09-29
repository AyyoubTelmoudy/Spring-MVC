-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 24 juin 2022 à 04:15
-- Version du serveur : 10.4.21-MariaDB
-- Version de PHP : 7.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `claims_management_app`
--

-- --------------------------------------------------------

--
-- Structure de la table `admins`
--

CREATE TABLE `admins` (
  `id` bigint(20) NOT NULL,
  `email` varchar(120) NOT NULL,
  `encrypted_password` varchar(255) NOT NULL,
  `login` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `admins`
--

INSERT INTO `admins` (`id`, `email`, `encrypted_password`, `login`) VALUES
(1, 'kay.technologie.gdr@gmail.com', '$2a$10$3KBwiDXQjMfsZ6I5X8ob7.L9J267lsNWQc/.tUNlNRftXLvAouAxu', 'kay.technologie.gdr@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `attachments`
--

CREATE TABLE `attachments` (
  `id` bigint(20) NOT NULL,
  `attachment_name` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `public_id` varchar(255) NOT NULL,
  `complaint_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `id` bigint(20) NOT NULL,
  `email` varchar(120) NOT NULL,
  `phone_number` varchar(30) NOT NULL,
  `public_id` varchar(255) NOT NULL,
  `social_reason` varchar(255) NOT NULL,
  `starting_entering_next_complaint_date` datetime DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`id`, `email`, `phone_number`, `public_id`, `social_reason`, `starting_entering_next_complaint_date`, `status`, `user_id`) VALUES
(18, 'client@gmail.com', '+212638983554', 'UsYVnT7PJFhuNLcklWbcxNCoZ000SI', 'ALSA', NULL, 'ACTIVE', 17);

-- --------------------------------------------------------

--
-- Structure de la table `clients_projects`
--

CREATE TABLE `clients_projects` (
  `id` bigint(20) NOT NULL,
  `assignment_date` date NOT NULL,
  `public_id` varchar(255) NOT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `collaborator_id` bigint(20) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `clients_projects`
--

INSERT INTO `clients_projects` (`id`, `assignment_date`, `public_id`, `client_id`, `collaborator_id`, `project_id`) VALUES
(20, '2022-03-05', '6rBf7hjijl95EKeirA0012EMYqTdcX', 18, 16, 19);

-- --------------------------------------------------------

--
-- Structure de la table `collaborators`
--

CREATE TABLE `collaborators` (
  `id` bigint(20) NOT NULL,
  `email` varchar(120) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `phone_number` varchar(30) NOT NULL,
  `public_id` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `collaborators`
--

INSERT INTO `collaborators` (`id`, `email`, `first_name`, `last_name`, `phone_number`, `public_id`, `status`, `user_id`) VALUES
(16, 'collaborateur@gmail.com', 'collaborateur', 'collaborateur', '+212638983557', 'wvZSkuwkowesNNXt60EpJPfJL6fEce', 'BLOCKED', 15);

-- --------------------------------------------------------

--
-- Structure de la table `complaints`
--

CREATE TABLE `complaints` (
  `id` bigint(20) NOT NULL,
  `closing_date` date DEFAULT NULL,
  `complaint_date` date NOT NULL,
  `consulted_by_collaborator` bit(1) NOT NULL,
  `description` varchar(255) NOT NULL,
  `ordinal_number` bigint(20) NOT NULL,
  `public_id` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `ticket_number` bigint(20) NOT NULL,
  `client_project_id` bigint(20) DEFAULT NULL,
  `complaint_type_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `complaint_types`
--

CREATE TABLE `complaint_types` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `public_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `complaint_types`
--

INSERT INTO `complaint_types` (`id`, `name`, `public_id`) VALUES
(21, 'Bug', 'Gojo1TTVl0HFKtru2DlUs0x8aeNb0E'),
(22, 'Nouveau besoin', 'GME3BPjZ42LdR4yvFDRQ0OpwDn4Tr4');

-- --------------------------------------------------------

--
-- Structure de la table `conversations`
--

CREATE TABLE `conversations` (
  `id` bigint(20) NOT NULL,
  `consulted_by_client` bit(1) NOT NULL,
  `consulted_by_collaborator` bit(1) NOT NULL,
  `ordinal_number` bigint(20) NOT NULL,
  `public_id` varchar(255) NOT NULL,
  `complaint_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(111);

-- --------------------------------------------------------

--
-- Structure de la table `messages`
--

CREATE TABLE `messages` (
  `id` bigint(20) NOT NULL,
  `is_picture` bit(1) NOT NULL,
  `message_content` varchar(255) NOT NULL,
  `message_date` datetime NOT NULL,
  `public_id` varchar(255) NOT NULL,
  `sender` varchar(255) NOT NULL,
  `conversation_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `projects`
--

CREATE TABLE `projects` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `public_id` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `projects`
--

INSERT INTO `projects` (`id`, `name`, `public_id`, `status`) VALUES
(19, 'Gestion de transport', 'WH36Wz29YVvUWewVdABc45NYdnpwqn', 'new project'),
(100, 'Application web', 'rafNjMNZEDZLPpi4ZlQrK0Imd16kTc', 'new project');

-- --------------------------------------------------------

--
-- Structure de la table `supervisors`
--

CREATE TABLE `supervisors` (
  `id` bigint(20) NOT NULL,
  `email` varchar(120) NOT NULL,
  `encrypted_password` varchar(255) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `login` varchar(120) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `public_id` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `supervisors`
--

INSERT INTO `supervisors` (`id`, `email`, `encrypted_password`, `first_name`, `last_name`, `login`, `phone_number`, `public_id`, `status`) VALUES
(14, 'superviseur@gmail.com', '$2a$10$3KBwiDXQjMfsZ6I5X8ob7.L9J267lsNWQc/.tUNlNRftXLvAouAxu', 'superviseur', 'superviseur', 'superviseur@gmail.com', '+212690983550', 'h7MiFXGby7H1ciGsquSUipkUKcjpJD', 'ACTIVE');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `encrypted_password` varchar(255) NOT NULL,
  `login` varchar(120) NOT NULL,
  `public_id` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `encrypted_password`, `login`, `public_id`, `role`, `status`) VALUES
(15, '$2a$10$3KBwiDXQjMfsZ6I5X8ob7.L9J267lsNWQc/.tUNlNRftXLvAouAxu', 'collaborateur@gmail.com', '6WHlYiGLdmYkBKspj00XXYCGC2W0YK', 'ROLE_COLLABORATOR', 'ACTIVE'),
(17, '$2a$10$3KBwiDXQjMfsZ6I5X8ob7.L9J267lsNWQc/.tUNlNRftXLvAouAxu', 'client@gmail.com', 'qFJiN0Imvk8FdDACz1AuW8lNq5C75G', 'ROLE_CLIENT', 'ACTIVE');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_7gb4hqhf0qd8bhppyp3wq3hj` (`login`);

--
-- Index pour la table `attachments`
--
ALTER TABLE `attachments`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_4bqcnpdhqa14tvjb29tfuq4hj` (`public_id`),
  ADD KEY `FKs8jrkoxukg9d202qb7gv4hqou` (`complaint_id`);

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_srv16ica2c1csub334bxjjb59` (`email`),
  ADD UNIQUE KEY `UK_f5el9sb49w291va5xivw6farh` (`public_id`),
  ADD UNIQUE KEY `UK_9qcii7xlaj3f38hnxm7tpomo2` (`social_reason`),
  ADD KEY `FKtiuqdledq2lybrds2k3rfqrv4` (`user_id`);

--
-- Index pour la table `clients_projects`
--
ALTER TABLE `clients_projects`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_lygm44lhubio3gr3olbfb8b1q` (`public_id`),
  ADD KEY `FKn9eylqtc62p6gxvl8nj4pfx4g` (`client_id`),
  ADD KEY `FKetch1ljqgnc4iko5l9r7b60h1` (`collaborator_id`),
  ADD KEY `FK2ef95i7vc3lbspt8t4xauqpms` (`project_id`);

--
-- Index pour la table `collaborators`
--
ALTER TABLE `collaborators`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_pvbu8dv28glio528o836ne14p` (`email`),
  ADD UNIQUE KEY `UK_43565guxoomx68jwgueyy25oy` (`public_id`),
  ADD KEY `FKmg9syr136b4f774tvh50ghff` (`user_id`);

--
-- Index pour la table `complaints`
--
ALTER TABLE `complaints`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_9mdhk6q2wkk7hnnon858jm4pu` (`public_id`),
  ADD KEY `FKlh083yp5x07j3ptl1eotwshxu` (`client_project_id`),
  ADD KEY `FKcyf40wtjac3a9et8llfuubcht` (`complaint_type_id`);

--
-- Index pour la table `complaint_types`
--
ALTER TABLE `complaint_types`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_qufkbi5n2dlylh65o5ribus49` (`public_id`);

--
-- Index pour la table `conversations`
--
ALTER TABLE `conversations`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_q07914pcv9kk9toltnas25mrh` (`public_id`),
  ADD KEY `FKjjsk1kx47wpcljs8hl2uj1it5` (`complaint_id`);

--
-- Index pour la table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_hj7un9hohyyvirrtpd8qycy1q` (`public_id`),
  ADD KEY `FKt492th6wsovh1nush5yl5jj8e` (`conversation_id`);

--
-- Index pour la table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_1u4xomqvjov7mnx23fmwevuvo` (`public_id`);

--
-- Index pour la table `supervisors`
--
ALTER TABLE `supervisors`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_d5b4mr41jms9nxuorxq9d6vkk` (`login`),
  ADD UNIQUE KEY `UK_kc4vmq8glmmhgid7iiosysi19` (`phone_number`),
  ADD UNIQUE KEY `UK_ahwt1mdi1dd18dr81xus1b0ps` (`public_id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ow0gan20590jrb00upg3va2fn` (`login`),
  ADD UNIQUE KEY `UK_s24bux761rbgowsl7a4b386ba` (`public_id`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `attachments`
--
ALTER TABLE `attachments`
  ADD CONSTRAINT `FKs8jrkoxukg9d202qb7gv4hqou` FOREIGN KEY (`complaint_id`) REFERENCES `complaints` (`id`);

--
-- Contraintes pour la table `clients`
--
ALTER TABLE `clients`
  ADD CONSTRAINT `FKtiuqdledq2lybrds2k3rfqrv4` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `clients_projects`
--
ALTER TABLE `clients_projects`
  ADD CONSTRAINT `FK2ef95i7vc3lbspt8t4xauqpms` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
  ADD CONSTRAINT `FKetch1ljqgnc4iko5l9r7b60h1` FOREIGN KEY (`collaborator_id`) REFERENCES `collaborators` (`id`),
  ADD CONSTRAINT `FKn9eylqtc62p6gxvl8nj4pfx4g` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`);

--
-- Contraintes pour la table `collaborators`
--
ALTER TABLE `collaborators`
  ADD CONSTRAINT `FKmg9syr136b4f774tvh50ghff` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `complaints`
--
ALTER TABLE `complaints`
  ADD CONSTRAINT `FKcyf40wtjac3a9et8llfuubcht` FOREIGN KEY (`complaint_type_id`) REFERENCES `complaint_types` (`id`),
  ADD CONSTRAINT `FKlh083yp5x07j3ptl1eotwshxu` FOREIGN KEY (`client_project_id`) REFERENCES `clients_projects` (`id`);

--
-- Contraintes pour la table `conversations`
--
ALTER TABLE `conversations`
  ADD CONSTRAINT `FKjjsk1kx47wpcljs8hl2uj1it5` FOREIGN KEY (`complaint_id`) REFERENCES `complaints` (`id`);

--
-- Contraintes pour la table `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `FKt492th6wsovh1nush5yl5jj8e` FOREIGN KEY (`conversation_id`) REFERENCES `conversations` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
