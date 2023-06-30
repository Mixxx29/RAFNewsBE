DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    email    VARCHAR(255) NOT NULL,
    name     VARCHAR(255) NOT NULL,
    surname  VARCHAR(255) NOT NULL,
    type     VARCHAR(255) NOT NULL,
    status   VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE category
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE news
(
    id             SERIAL PRIMARY KEY,
    title          VARCHAR(255) NOT NULL,
    content        TEXT,
    datetime       DATE,
    numberOfVisits INTEGER,
    author_id      INTEGER,
    category_id    INTEGER
);

CREATE TABLE comment
(
    id          SERIAL PRIMARY KEY,
    author_name VARCHAR(255) NOT NULL,
    content     TEXT,
    datetime    TIMESTAMP,
    news_id     INTEGER,
    FOREIGN KEY (news_id) REFERENCES news (id) ON DELETE CASCADE
);

CREATE TABLE tag
(
    id   SERIAL PRIMARY KEY,
    text VARCHAR(255) NOT NULL
);

CREATE TABLE news_tag
(
    news_id INTEGER,
    tag_id  INTEGER,
    PRIMARY KEY (news_id, tag_id),
    FOREIGN KEY (news_id) REFERENCES news (id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE
);

INSERT INTO users (email, name, surname, type, status, password)
VALUES ('admin@admin.com', 'Admin', 'Adminovski', 'ADMIN', 'ACTIVE',
        'NHC/Gza4UcGDtJsELQ2dPLqdCXWdZOeiWtWuJPXO+DNQRthlUzzR5slZj+7uYuBK'),
       ('john@doe.com', 'John', 'Doe', 'ADMIN', 'ACTIVE',
        'lAOD8OzYGI9OmE99LY1tVOZeBphhtlKDs5ztLIEIjB7U0RwjNvB5mKJ+I3/f4eOM'),
       ('jane@smith.com', 'Jane', 'Smith', 'CONTENT_CREATOR', 'ACTIVE',
        '3/5R9mACmLYn+b97Vu6OfryB8jRZOt18+Uwc0eR4y5BzPQ5vt8S4sIaG+TWyQ9fK'),
       ('alice@johnson.com', 'Alice', 'Johnson', 'CONTENT_CREATOR', 'INACTIVE',
        'NX3hqzWfi8skPvxDWUiYQIGABA4680UhSD+lIDg8rw3/X5YT+SycGhtfFy41hSdK'),
       ('bob@anderson.com', 'Bob', 'Anderson', 'CONTENT_CREATOR', 'ACTIVE',
        '7YBjT31EJIVUsS2XU0wsmx8FbAfJbdYBM3AztCzTldz8xI4w8MOHPTQRgG8Snt0z'),
       ('charlie@brown.com', 'Charlie', 'Brown', 'CONTENT_CREATOR', 'ACTIVE',
        '+3YmVaeCW3fCtQU5howTvYdTrSd73lC4AlqFs3qRbUllO4bV//Cz82tE0FbI3Emh'),
       ('diana@wilson.com', 'Diana', 'Wilson', 'CONTENT_CREATOR', 'INACTIVE',
        'bj+iD/9Uu+BWXo8urrBhJsqQJofXgG1EFvwAqV+eYdK6BBryVF1HPq7gPXDHBsZp'),
       ('edward@rodriguez.com', 'Edward', 'Rodriguez', 'CONTENT_CREATOR', 'ACTIVE',
        'WofgotnIugMqi4zM9iDFLLycm7dJbgFj6VH0e6pKfkUA2loLsKuQTxzhnGm6kZc/'),
       ('fiona@miller.com', 'Fiona', 'Miller', 'CONTENT_CREATOR', 'INACTIVE',
        'M5aH/cy1OxT5WrqakDSHeia4QUkKVpm3c/WtW+3q6kTopqMj2CvIkvARpYm8j7Dl'),
       ('george@thompson.com', 'George', 'Thompson', 'CONTENT_CREATOR', 'ACTIVE',
        'MEwjuS9PEBh2BBwtU6/AK0a2zuo5SxKVNrXriaP8/2dlZ+aXpsheXtUEiYKfIK2t'),
       ('hannah@lewis.com', 'Hannah', 'Lewis', 'CONTENT_CREATOR', 'INACTIVE',
        'lkg80gaIGVuDM5NuUesELXEqmURmuIHaS/SBFugJrPgwwPvjwseRKyrhMZHBrT2X'),
       ('ian@hall.com', 'Ian', 'Hall', 'CONTENT_CREATOR', 'ACTIVE',
        '	glNLZvWeK+R9hkgNX2qf97ntnpvmaBIWCPUWGWzpEBHZrq+nrZx2la92qkptny0G'),
       ('jessica@lee.com', 'Jessica', 'Lee', 'CONTENT_CREATOR', 'INACTIVE',
        '4DTPk4Pr1ZL0cRaNhvi2icyd+Av8MLP+O6kVdtEmoTptctxuHY3mbA3sxnkYQRaM'),
       ('kevin@young.com', 'Kevin', 'Young', 'CONTENT_CREATOR', 'ACTIVE',
        'ge2NxHSz5s37ssL6f6mP8staCIaXve78ctdz0jDpATal/uyXA0Q67xnVnM1gLmvW'),
       ('linda@adams.com', 'Linda', 'Adams', 'CONTENT_CREATOR', 'INACTIVE',
        'HwjT0jdA7z+VbVdhSDYf1GcOFn+jvl33QTnfq8Ae27iYcueMe1gnDymbqR/jy7iB'),
       ('michael@harris.com', 'Michael', 'Harris', 'CONTENT_CREATOR', 'ACTIVE',
        'vIjGNlcp2d+8SMRZ7kjMS8U7Bo5MUT0C13Ghh31h36rqK8ZWD6fYJcK66rFHMPBe'),
       ('natalie@carter.com', 'Natalie', 'Carter', 'CONTENT_CREATOR', 'INACTIVE',
        'upnENm+pwf0PR95KzDNWjdjG/ppf4QnbOrkFEXrm72flW6efwYDn30VNu+Nxowbf'),
       ('oliver@wright.com', 'Oliver', 'Wright', 'CONTENT_CREATOR', 'ACTIVE',
        'TrGe7aiLfU7cumIuBa6WNCwB8LNjIEtrQz204vh5VVZDNb25pVDWIB+6n3DjoRyk'),
       ('pamela@green.com', 'Pamela', 'Green', 'CONTENT_CREATOR', 'INACTIVE',
        'zINVzlQxsOppk0o5SQ5wv1Hw2YAv7UAirdWjnfTglN9djPr6vrOzuc5BAOGtaWGC'),
       ('quincy@turner.com', 'Quincy', 'Turner', 'CONTENT_CREATOR', 'ACTIVE',
        'kwA1H9yiRoaOUfFqnleF6MWlBD41VkBxtVi4yxoQWshgchPt7FU+aoTjZiShZnuM'),
       ('rachel@bell.com', 'Rachel', 'Bell', 'CONTENT_CREATOR', 'INACTIVE',
        'aHfvhh7WgAiis9eXbIRIdCZf7LJc8RrdUEk46RspwgIJCrhl0Fris3+NARwRyThE'),
       ('steven@morris.com', 'Steven', 'Morris', 'CONTENT_CREATOR', 'ACTIVE',
        '61Q7pk792eBY87BUKEHno50IfBPS5SBraNX7Pf7ZMkWGGfsiGvVWRhr8IjWolPrX'),
       ('tina@scott.com', 'Tina', 'Scott', 'CONTENT_CREATOR', 'INACTIVE',
        'obtvMliPZMTHzZq1OAdkgdjhwdpay4UhgfSZQsKm3ZgYQ6P7hB/yAn9aQxKDWpfy'),
       ('victor@hughes.com', 'Victor', 'Hughes', 'CONTENT_CREATOR', 'ACTIVE',
        'mJOli0K37sSJWlREPRid0fCHzLUf3A3wd11khOywF8p3pHtZSXPsSqDnGPL00Wbz'),
       ('wendy@ross.com', 'Wendy', 'Ross', 'CONTENT_CREATOR', 'INACTIVE',
        'LODU0Oatm64B9qFUCd0Oqr1uGozyFg0qwPjC96pLLxURIZFRF+ZD8GGd76/AO0A/'),
       ('xavier@baker.com', 'Xavier', 'Baker', 'CONTENT_CREATOR', 'ACTIVE',
        '	ZX25keRjoisp1evRXh+TCEZ1EunGN9570F+xSIC/4XaQ6T2P8a0H3XqYjYRdpnCU'),
       ('yvonne@murray.com', 'Yvonne', 'Murray', 'CONTENT_CREATOR', 'INACTIVE',
        'gmOOpedemveka8f7PsXztFLohm3QqSpE6I+eseXqH000udBbFD6jZYDB0pMh+mL3'),
       ('zachary@nelson.com', 'Zachary', 'Nelson', 'CONTENT_CREATOR', 'ACTIVE',
        '3U8ofhYiOmtLRDqJ6pHaXkgZ5XSuSArQp9M6FNAqVbV8O2VkmFzez1dIgqvqmQtt');

INSERT INTO category (name, description)
VALUES ('Sports', 'News about sports events and athletes'),
       ('Politics', 'News related to political affairs'),
       ('Entertainment', 'News about movies, music, and celebrities'),
       ('Technology', 'News on the latest technological advancements'),
       ('Business', 'News related to the business world and economy'),
       ('Health', 'News and information on health and wellness'),
       ('Science', 'News about scientific discoveries and research'),
       ('Education', 'News and updates from the field of education'),
       ('Environment', 'News related to the environment and conservation'),
       ('Travel', 'News and insights on travel and destinations');

INSERT INTO news (title, content, datetime, numberOfVisits, author_id, category_id)
VALUES ('Article 1', 'Content 1', '2021-01-14', 0, 1, 1),
       ('Article 2', 'Content 2', '2023-02-24', 0, 2, 2),
       ('Article 3', 'Content 3', '2022-03-15', 0, 3, 3),
       ('Article 4', 'Content 4', '2023-04-24', 0, 4, 4),
       ('Article 5', 'Content 5', '2023-05-04', 0, 5, 5),
       ('Article 6', 'Content 6', '2022-06-24', 0, 6, 6),
       ('Article 7', 'Content 7', '2023-07-24', 0, 7, 7),
       ('Article 8', 'Content 8', '2023-08-24', 0, 8, 8),
       ('Article 9', 'Content 9', '2020-09-24', 0, 9, 9),
       ('Article 10', 'Content 10', '2023-10-24', 0, 10, 10);