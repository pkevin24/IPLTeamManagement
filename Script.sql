-- Create the ipl database
CREATE DATABASE ipl;

-- Connect to the ipl database
\c ipl;

-- Create table teams
CREATE TABLE teams (
    team_id SERIAL PRIMARY KEY,
    team_name VARCHAR(255) NOT NULL,
    team_location VARCHAR(255) NOT NULL
);

-- Create table teammembers
CREATE TABLE teammembers (
    member_id SERIAL PRIMARY KEY,
    member_name VARCHAR(255) NOT NULL,
    member_position VARCHAR(255) NOT NULL,
    team_id INT NOT NULL,
    FOREIGN KEY (team_id) REFERENCES teams (team_id)
);

-- Create table users
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Add a user with access to the "ipl" database
CREATE USER ipl_user WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE ipl TO ipl_user;
