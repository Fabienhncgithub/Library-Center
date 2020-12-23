-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 23 déc. 2020 à 23:21
-- Version du serveur :  10.4.14-MariaDB
-- Version de PHP : 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bibliotheque`
--

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

CREATE TABLE `avis` (
  `idLivre` int(255) DEFAULT NULL,
  `idUser` int(255) DEFAULT NULL,
  `commentaire` varchar(255) DEFAULT NULL,
  `note` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `avis`
--

INSERT INTO `avis` (`idLivre`, `idUser`, `commentaire`, `note`) VALUES
(1, 17, 'nul', 2),
(1, 17, 'null', 2),
(1, 17, 'null', 2),
(0, 17, 'cdcdc', 1),
(1, 17, 'frfr', 2),
(0, 17, '', 0),
(0, 17, '', 0),
(0, 17, '', 0),
(0, 17, '', 0),
(1, 17, 'rezrezr', 1),
(1, 17, 'rezrezrfsdfsdfsd', 1),
(1, 17, 'testamin', 5),
(1, 17, 'fristi', 3),
(2, 17, 'sent mauvais', 2),
(1, 17, 'olalalal', 2),
(1, 17, 'note 5/5', 5),
(1, 17, 't(t(t', 0),
(1, 17, 'zeero', 4);

-- --------------------------------------------------------

--
-- Structure de la table `bibliotheque`
--

CREATE TABLE `bibliotheque` (
  `idBibliotheque` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `idManager` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `bibliotheque`
--

INSERT INTO `bibliotheque` (`idBibliotheque`, `nom`, `adresse`, `idManager`) VALUES
(1, 'Anderlecht', NULL, NULL),
(2, 'Charleroi', NULL, NULL),
(3, 'Evere', NULL, NULL),
(4, 'Uccle', NULL, NULL),
(5, 'test', 'test', 10),
(6, 'nom', 'adresse', 25),
(9, 'nom', 'ad', 28),
(10, 'nom', 'adresse', 24),
(11, 'trrr', 'ytttttt', 21);

-- --------------------------------------------------------

--
-- Structure de la table `exemplaire`
--

CREATE TABLE `exemplaire` (
  `idExemplaire` int(255) NOT NULL,
  `idLivre` int(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `disponible` tinyint(255) NOT NULL,
  `rendu` tinyint(255) NOT NULL,
  `verifier` tinyint(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `exemplaire`
--

INSERT INTO `exemplaire` (`idExemplaire`, `idLivre`, `type`, `disponible`, `rendu`, `verifier`) VALUES
(1, 1, 'livre', 0, 1, 1),
(2, 1, 'livre', 1, 0, 0),
(3, 2, 'livre', 1, 1, 1),
(4, 2, 'livre', 0, 0, 0),
(7, 1, 'ebook', 0, 0, 0),
(8, 1, 'livre', 0, 1, 1),
(9, 1, 'ebook', 0, 0, 1),
(10, 3, 'livre', 0, 0, 0),
(11, 3, 'ebook', 0, 0, 0),
(17, 10, 'livre', 0, 1, 1),
(27, 19, 'livre', 0, 0, 0),
(28, 20, 'livre', 0, 0, 0),
(29, 21, 'livre', 0, 1, 0),
(30, 22, 'livre', 0, 1, 0),
(31, 23, 'livre', 0, 1, 0),
(32, 24, 'livre', 0, 0, 0),
(33, 25, 'livre', 0, 1, 0),
(34, 26, 'livre', 0, 1, 0),
(35, 10, 'livre', 0, 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `faq`
--

CREATE TABLE `faq` (
  `idQuestion` int(255) NOT NULL,
  `idUserQuestion` int(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `idUserReponse` int(255) DEFAULT NULL,
  `reponse` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `faq`
--

INSERT INTO `faq` (`idQuestion`, `idUserQuestion`, `question`, `idUserReponse`, `reponse`) VALUES
(1, 17, 'test', 1, 'test2'),
(2, 17, 'ceci est une quesion ', 1, 't'),
(3, 17, 'ff', NULL, NULL),
(4, 17, 'tt', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

CREATE TABLE `inscription` (
  `idUser` int(255) DEFAULT NULL,
  `idBibliotheque` int(255) DEFAULT NULL,
  `cotisation` tinyint(1) DEFAULT NULL,
  `dateCotisation` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`idUser`, `idBibliotheque`, `cotisation`, `dateCotisation`) VALUES
(9, 1, NULL, NULL),
(1, 1, 1, NULL),
(17, 1, 1, '2020-12-13'),
(13, 1, 0, NULL),
(13, 1, 0, NULL),
(24, 3, 0, NULL),
(29, 1, 0, NULL),
(30, 1, 0, NULL),
(10, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `idLivre` int(11) NOT NULL,
  `prixAchat` double(255,0) DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `auteur` varchar(255) DEFAULT NULL,
  `editeur` varchar(255) DEFAULT NULL,
  `page` int(255) DEFAULT NULL,
  `noteTotal` double(255,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`idLivre`, `prixAchat`, `titre`, `auteur`, `editeur`, `page`, `noteTotal`) VALUES
(1, 0, 'Harry Potter', 'auteur', 'editeur2', 2, 2),
(2, 0, 'auteur1', NULL, 'editeur1', 1, 2),
(3, 5, 'SAS', 'toto', 'chair', 234, 5),
(4, 200, 'JAVA', 'romano', 'Evere', 2356, 3),
(10, 900, 'iphone', 'iphone', 'iphone', 900, NULL),
(11, 36, 'vadormir', 'vadormir', 'vadormir', 36, NULL),
(12, 38, 'velo', 'velo', 'velo', 38, NULL),
(13, 40, 'mar', 'mar', 'mar', 40, NULL),
(14, 54, 'partir', 'partir', 'partir', 54, NULL),
(15, 23, 'bass', 'bass', 'bass', 23, NULL),
(16, 1, 'add', 'add', 'add', 1, NULL),
(17, 3, 'green', 'green', 'green', 3, NULL),
(18, 37, 'dimanche', 'dimanche', 'dimanche', 37, NULL),
(19, 1, '1', '1', '1', 1, NULL),
(20, 2, '2', '2', '2', 2, NULL),
(21, 3, '3', '3', '3', 3, NULL),
(22, 5, '5', '5', '5', 5, NULL),
(23, 89, '89', '89', '89', 89, NULL),
(24, 123, '123', '123', '123', 123, NULL),
(25, 39, 'liv', 'liv', 'liv', 39, NULL),
(26, 2, 'iphone', 'iphon', 'p', 2, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `livrebiliotheque`
--

CREATE TABLE `livrebiliotheque` (
  `idBibliotheque` int(255) DEFAULT NULL,
  `idExemplaire` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `livrebiliotheque`
--

INSERT INTO `livrebiliotheque` (`idBibliotheque`, `idExemplaire`) VALUES
(1, 1),
(2, 2),
(1, 8),
(1, 9),
(1, 3),
(1, 11),
(11, 11),
(11, 11),
(1, 17),
(1, 11),
(1, 33),
(1, 34),
(1, 35);

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

CREATE TABLE `location` (
  `idLocation` int(255) NOT NULL,
  `idExemplaire` int(255) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  `dateLocation` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `location`
--

INSERT INTO `location` (`idLocation`, `idExemplaire`, `idUser`, `dateLocation`) VALUES
(168, 1, 17, '2020-10-14 00:00:00'),
(169, 3, 17, '2020-12-16 00:00:00'),
(170, 17, 17, '2020-12-17 00:00:00'),
(171, 8, 17, '2020-12-16 00:00:00'),
(172, 9, 17, '2020-12-24 00:00:00'),
(173, 1, 1, '2020-12-23 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `idReservation` int(255) NOT NULL,
  `idExemplaire` int(255) DEFAULT NULL,
  `idUser` int(255) DEFAULT NULL,
  `dateLocation` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `idRole` int(255) NOT NULL,
  `nomRole` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`idRole`, `nomRole`) VALUES
(1, 'client'),
(2, 'bibliothécaire'),
(3, 'manager'),
(4, 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `idUser` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `amende` float(255,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`idUser`, `nom`, `prenom`, `email`, `password`, `role`, `adresse`, `amende`) VALUES
(1, 'test', 'test', 'admin', 'admin', 4, 'test', 0),
(9, 'test', 'test', 'client', 'client', 1, 'test', 0),
(10, 'root', 'root', 'manager', 'manager', 3, 'root', 0),
(11, 't', 't', 'bibliothécaire', 'bibliothécaire', 2, 't', 0),
(12, 'wesh', 'wesh', 'wesh', 'wesh', 3, 'te', 0),
(13, 'to', 'to', 'to', 'to', 1, 'to', 0),
(14, 'deb', 'deb', 'deb', 'deb', 1, 'deb', 0),
(16, 'fabien', 'hance', 'fab@test.com', 'root', 1, 'root', 0),
(17, 'fab', 'fab', 'root', 'root', 1, 'fab', 0),
(19, 'boonen', 'pierre', 'pierre', 'pierre', 1, 'hulpe', 0),
(20, 'selena', 'lopreno', 'selena', 'selena', 1, 'geneve', 0),
(21, 'lopreno', 'selena', 'selen', 'selena', 3, 'geneve', 0),
(23, 'bwcvbcxvb', 'xcvbxcvbxcv', 'bxcvbxcvb', 'xcvbxcvb', 1, 'xcvbxcvbx', 0),
(24, 'marc', 'pichot', 'marc', 'marc', 1, 'marc', 0),
(29, 'n', 'p', 'e', 'a', 1, 'p', 0),
(30, 'dsfsdf', 'dfsdfsdf', 'dfsdfsdf', 'dfsdfsdf', 1, 'sdfsdfsdf', 0),
(31, 'nom', 'preom', 'fdsfsd', 'mm', 3, 'mmmm', 0),
(32, 'nom', 'preom', 'fdsfsd', 'mm', 3, 'mmmm', 0),
(33, 'nom', 'preom', 'fdsfsd', 'mm', 3, 'mmmm', 0),
(34, 'nom', 'preom', 'fdsfsd', 'mm', 3, 'mmmm', 0),
(35, 'nom', 'preom', 'fdsfsd', 'mm', 3, 'mmmm', 0),
(57, 'test', 'test', 'test', '111111', 3, 'test', 0),
(58, 'tsssss', 'tsssss', 'tsssss', 'tsssss', 3, 'tsssss', 0),
(59, 'tsssss', 'tsssss', 'tsssss', 'tsssss', 3, 'tsssss', 0),
(60, 'amin', 'amin', 'amin', 'amin', 3, 'amin', 0),
(61, 'aaaa', 'aaaaaaa', 'aaaaaaaaa', 'aaaaaaaaaa', 3, 'aaaaaaaaaa', 0),
(62, 'aminnn', 'aminnn', 'aminnn', 'aminnn', 3, 'aminnn', 0),
(63, 'aminnn', 'aminnn', 'aminnn', 'aminnn', 3, 'aminnn', 0),
(64, 'aminnn', 'aminnn', 'aminnn', 'aminnn', 3, 'aminnn', 0),
(65, 'aminnn', 'aminnn', 'aminnn', '', 3, 'aminnn', 0),
(66, 'olala', 'olala', 'olala', 'olala', 3, 'olala', 0),
(67, 'bibliothecaire', 'bibliothecaire', 'bibliothecaire', 'bibliothecaire', 1, 'bibliothecaire', 0),
(68, 'you', 'you', 'you', '39', 1, '39', 0),
(69, '1', 'a', 'a', '1', 1, '1', 0);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `vw_dateexemplaire`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `vw_dateexemplaire` (
`idLivre` int(11)
,`titre` varchar(255)
,`exeIdLivre` int(255)
,`locidExemplaire` int(255)
,`dateLocation` datetime
,`idExemplaire` int(255)
);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `vw_historique`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `vw_historique` (
`dateLocation` datetime
,`idBibliotheque` int(255)
,`titre` varchar(255)
,`idUser` int(11)
);

-- --------------------------------------------------------

--
-- Structure de la vue `vw_dateexemplaire`
--
DROP TABLE IF EXISTS `vw_dateexemplaire`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_dateexemplaire`  AS SELECT `livre`.`idLivre` AS `idLivre`, `livre`.`titre` AS `titre`, `exemplaire`.`idLivre` AS `exeIdLivre`, `location`.`idExemplaire` AS `locidExemplaire`, `location`.`dateLocation` AS `dateLocation`, `exemplaire`.`idExemplaire` AS `idExemplaire` FROM ((`livre` join `exemplaire` on(`exemplaire`.`idLivre` = `livre`.`idLivre`)) join `location` on(`location`.`idExemplaire` = `exemplaire`.`idExemplaire`)) ;

-- --------------------------------------------------------

--
-- Structure de la vue `vw_historique`
--
DROP TABLE IF EXISTS `vw_historique`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_historique`  AS SELECT `location`.`dateLocation` AS `dateLocation`, `livrebiliotheque`.`idBibliotheque` AS `idBibliotheque`, `livre`.`titre` AS `titre`, `location`.`idUser` AS `idUser` FROM (((`livre` join `exemplaire` on(`exemplaire`.`idLivre` = `livre`.`idLivre`)) join `location` on(`location`.`idExemplaire` = `exemplaire`.`idExemplaire`)) join `livrebiliotheque` on(`livrebiliotheque`.`idExemplaire` = `exemplaire`.`idExemplaire`)) ;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `bibliotheque`
--
ALTER TABLE `bibliotheque`
  ADD PRIMARY KEY (`idBibliotheque`) USING BTREE;

--
-- Index pour la table `exemplaire`
--
ALTER TABLE `exemplaire`
  ADD PRIMARY KEY (`idExemplaire`) USING BTREE,
  ADD KEY `livreExemplaire` (`idLivre`);

--
-- Index pour la table `faq`
--
ALTER TABLE `faq`
  ADD PRIMARY KEY (`idQuestion`);

--
-- Index pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD KEY `userInscription` (`idUser`),
  ADD KEY `bibliothequeInscription` (`idBibliotheque`);

--
-- Index pour la table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`idLivre`) USING BTREE;

--
-- Index pour la table `livrebiliotheque`
--
ALTER TABLE `livrebiliotheque`
  ADD KEY `livreid` (`idBibliotheque`),
  ADD KEY `exempl` (`idExemplaire`);

--
-- Index pour la table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`idLocation`),
  ADD KEY `exemplaireLocation` (`idExemplaire`),
  ADD KEY `userLocation` (`idUser`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`idReservation`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`idRole`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`) USING BTREE,
  ADD KEY `role` (`role`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `bibliotheque`
--
ALTER TABLE `bibliotheque`
  MODIFY `idBibliotheque` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `exemplaire`
--
ALTER TABLE `exemplaire`
  MODIFY `idExemplaire` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT pour la table `faq`
--
ALTER TABLE `faq`
  MODIFY `idQuestion` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `livre`
--
ALTER TABLE `livre`
  MODIFY `idLivre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT pour la table `location`
--
ALTER TABLE `location`
  MODIFY `idLocation` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=174;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `idRole` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `exemplaire`
--
ALTER TABLE `exemplaire`
  ADD CONSTRAINT `livreExemplaire` FOREIGN KEY (`idLivre`) REFERENCES `livre` (`idLivre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `bibliothequeInscription` FOREIGN KEY (`idBibliotheque`) REFERENCES `bibliotheque` (`idBibliotheque`),
  ADD CONSTRAINT `userInscription` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);

--
-- Contraintes pour la table `livrebiliotheque`
--
ALTER TABLE `livrebiliotheque`
  ADD CONSTRAINT `exempl` FOREIGN KEY (`idExemplaire`) REFERENCES `exemplaire` (`idExemplaire`),
  ADD CONSTRAINT `livreid` FOREIGN KEY (`idBibliotheque`) REFERENCES `bibliotheque` (`idBibliotheque`);

--
-- Contraintes pour la table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `exemplaireLocation` FOREIGN KEY (`idExemplaire`) REFERENCES `exemplaire` (`idExemplaire`),
  ADD CONSTRAINT `userLocation` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `roleUser` FOREIGN KEY (`role`) REFERENCES `role` (`idRole`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
