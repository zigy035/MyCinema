INSERT INTO `authuser` (`id`, `first_name`, `last_name`, `username`, `password`, `access`) VALUES 
('1096edc8-c972-4abb-a43a-d2aee20678ef', 'Igor', 'Milosevic', 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 1),
('df843f9c-7480-45ab-991f-8ec0594cb792', 'Igor', 'Milosevic', 'igor', '78b6854e22ab09d4ae3dac29b92052963103b33e', 0);

INSERT INTO `movie` (`id`, `title`, `description`, `genre`) VALUES 
('28ecb305-bee8-434d-8fc7-715f6ad7a8d0', 'Deadpool', 'A former Special Forces operative turned mercenary is subjected to a rogue experiment that leaves him with accelerated healing powers, adopting the alter ego Deadpool.', 'Action'),
('d3e99255-9d68-4dd0-a2ad-15e3eb222641', 'The Martian', 'During a manned mission to Mars, Astronaut Mark Watney is presumed dead after a fierce storm and left behind by his crew. But Watney has survived and finds himself stranded and alone on the hostile planet. With only meager supplies, he must draw upon his ingenuity, wit and spirit to subsist and find a way to signal to Earth that he is alive.', 'Science fiction'),
('e9a4f7dd-8efc-4992-a11e-97d3a4e70cb3', 'Fifty Shades of Black', 'An inexperienced college student meets a wealthy businessman whose sexual practices put a strain on their relationship.', 'Comedy'),
('d46ea5ef-e348-416b-aebd-d82ef0397f0e', 'The Revenant', 'A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team.', 'Drama'),
('ab7331f6-4452-47a5-ba30-63eff2295d3a', 'Zootopia', 'In a city of anthropomorphic animals, a fugitive con artist fox and a rookie bunny cop must work together to uncover a conspiracy.' , 'Animation'),
('98ad4c82-c8a2-4c4d-a3d8-eefbdef4cbdc', 'Spotlight', 'The true story of how the Boston Globe uncovered the massive scandal of child molestation and cover-up within the local Catholic Archdiocese, shaking the entire Catholic Church to its core.' , 'Thriller');

INSERT INTO `theatre` (`id`, `name`, `row_number`, `column_number`) VALUES 
('657fe321-ca63-462c-a650-90edefd722df', 'Theatre 1', 10, 9),
('7a6228c1-83a5-4932-8fb2-c396f2a65871', 'Theatre 2', 9, 8),
('15754934-43eb-4bd1-a2f0-c09e64316ddd', 'Theatre 3', 8, 7);
