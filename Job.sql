-- Database: job_portal

CREATE DATABASE job_portal;

USE job_portal;

-- Users table
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('job_seeker', 'employer') NOT NULL
);

-- Job postings table
CREATE TABLE job_postings (
    job_id INT PRIMARY KEY AUTO_INCREMENT,
    employer_id INT,
    job_title VARCHAR(255) NOT NULL,
    job_description TEXT NOT NULL,
    job_location VARCHAR(255),
    salary DECIMAL(10, 2),
    FOREIGN KEY (employer_id) REFERENCES users(user_id)
);

-- Job applications table
CREATE TABLE job_applications (
    application_id INT PRIMARY KEY AUTO_INCREMENT,
    job_id INT,
    applicant_id INT,
    application_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 status ENUM('pending', 'accepted', 'rejected') DEFAULT 'pending',
    FOREIGN KEY (job_id) REFERENCES job_postings(job_id),
    FOREIGN KEY (applicant_id) REFERENCES users(user_id)
);
