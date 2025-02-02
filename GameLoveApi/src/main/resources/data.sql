-- Create tables
CREATE TABLE IF NOT EXISTS game
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS players
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS game_loves
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    player_id BIGINT NOT NULL,
    game_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (player_id) REFERENCES players(id),
    FOREIGN KEY (game_id) REFERENCES game(id)
);

-- Insert initial games
INSERT INTO game (id, title) VALUES
(1, 'Game 1'),
(2, 'Game 2');

-- Insert initial players
INSERT INTO players (id, username) VALUES
(1, 'Player1'),
(2, 'Player2');

-- Insert initial game loves with current timestamp
INSERT INTO game_loves (id, player_id, game_id, created_at) VALUES
(1, 1, 1, CURRENT_TIMESTAMP),
(2, 2, 2, CURRENT_TIMESTAMP);