
DROP TABLE IF EXISTS bidlist;
DROP TABLE IF EXISTS trade;
DROP TABLE IF EXISTS curvepoint;
DROP TABLE IF EXISTS rating;
DROP TABLE IF EXISTS rulename;
DROP TABLE IF EXISTS users;

CREATE TABLE BidList (
                         BidListId INT NOT NULL AUTO_INCREMENT,
                         account VARCHAR(30) NOT NULL,
                         type VARCHAR(30) NOT NULL,
                         bidQuantity DOUBLE,
                         askQuantity DOUBLE,
                         bid DOUBLE,
                         ask DOUBLE,
                         benchmark VARCHAR(125),
                         bidListDate TIMESTAMP,
                         commentary VARCHAR(125),
                         security VARCHAR(125),
                         status VARCHAR(10),
                         trader VARCHAR(125),
                         book VARCHAR(125),
                         creationName VARCHAR(125),
                         creationDate TIMESTAMP,
                         revisionName VARCHAR(125),
                         revisionDate TIMESTAMP,
                         dealName VARCHAR(125),
                         dealType VARCHAR(125),
                         sourceListId VARCHAR(125),
                         side VARCHAR(125),
                         PRIMARY KEY (BidListId)
);

CREATE TABLE Trade (
                       TradeId INT NOT NULL AUTO_INCREMENT,
                       account VARCHAR(30) NOT NULL,
                       type VARCHAR(30) NOT NULL,
                       buyQuantity DOUBLE,
                       sellQuantity DOUBLE,
                       buyPrice DOUBLE,
                       sellPrice DOUBLE,
                       tradeDate TIMESTAMP,
                       security VARCHAR(125),
                       status VARCHAR(10),
                       trader VARCHAR(125),
                       benchmark VARCHAR(125),
                       book VARCHAR(125),
                       creationName VARCHAR(125),
                       creationDate TIMESTAMP,
                       revisionName VARCHAR(125),
                       revisionDate TIMESTAMP,
                       dealName VARCHAR(125),
                       dealType VARCHAR(125),
                       sourceListId VARCHAR(125),
                       side VARCHAR(125),
                       PRIMARY KEY (TradeId)
);

CREATE TABLE CurvePoint (
                            Id INT NOT NULL AUTO_INCREMENT,
                            CurveId INT,
                            asOfDate TIMESTAMP,
                            term DOUBLE,
                            value DOUBLE,
                            creationDate TIMESTAMP,
                            PRIMARY KEY (Id)
);

CREATE TABLE Rating (
                        Id INT NOT NULL AUTO_INCREMENT,
                        moodysRating VARCHAR(125),
                        sandPRating VARCHAR(125),
                        fitchRating VARCHAR(125),
                        orderNumber INT,
                        PRIMARY KEY (Id)
);

CREATE TABLE RuleName (
                          Id INT NOT NULL AUTO_INCREMENT,
                          name VARCHAR(125),
                          description VARCHAR(125),
                          json VARCHAR(125),
                          template VARCHAR(512),
                          sqlStr VARCHAR(125),
                          sqlPart VARCHAR(125),
                          PRIMARY KEY (Id)
);

CREATE TABLE Users (
                       Id INT NOT NULL AUTO_INCREMENT,
                       username VARCHAR(125),
                       password VARCHAR(125),
                       fullname VARCHAR(125),
                       role VARCHAR(125),
                       PRIMARY KEY (Id)
);

-- Table: Users

INSERT INTO Users (fullname, username, password, role) VALUES ('Administrator', 'admin', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'ADMIN');
INSERT INTO Users (fullname, username, password, role) VALUES ('User', 'user', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'USER');

-- Table: BidList
INSERT INTO BidList (account, type, bidQuantity, askQuantity, bid, ask, benchmark, bidListDate, commentary, security, status, trader, book, creationName, creationDate, revisionName, revisionDate, dealName, dealType, sourceListId, side) VALUES
                                                                                                                                                                                                                                                ('Account1', 'Type1', 100.0, 110.0, 50.0, 55.0, 'Benchmark1', NOW(), 'Commentary1', 'Security1', 'Open', 'Trader1', 'Book1', 'Creator1', NOW(), 'Reviser1', NOW(), 'Deal1', 'TypeA', 'Source1', 'SideA'),
                                                                                                                                                                                                                                                ('Account2', 'Type2', 200.0, 210.0, 150.0, 155.0, 'Benchmark2', NOW(), 'Commentary2', 'Security2', 'Closed', 'Trader2', 'Book2', 'Creator2', NOW(), 'Reviser2', NOW(), 'Deal2', 'TypeB', 'Source2', 'SideB');

-- Table: Trade
INSERT INTO Trade (account, type, buyQuantity, sellQuantity, buyPrice, sellPrice, tradeDate, security, status, trader, benchmark, book, creationName, creationDate, revisionName, revisionDate, dealName, dealType, sourceListId, side) VALUES
                                                                                                                                                                                                                                            ('Account1', 'Type1', 100.0, 50.0, 1000.0, 1200.0, NOW(), 'Security1', 'Open', 'Trader1', 'Benchmark1', 'Book1', 'Creator1', NOW(), 'Reviser1', NOW(), 'Deal1', 'TypeA', 'Source1', 'SideA'),
                                                                                                                                                                                                                                            ('Account2', 'Type2', 200.0, 100.0, 1500.0, 1700.0, NOW(), 'Security2', 'Closed', 'Trader2', 'Benchmark2', 'Book2', 'Creator2', NOW(), 'Reviser2', NOW(), 'Deal2', 'TypeB', 'Source2', 'SideB');

-- Table: CurvePoint
INSERT INTO CurvePoint (CurveId, asOfDate, term, value, creationDate) VALUES
                                                                          (1, NOW(), 1.5, 100.0, NOW()),
                                                                          (2, NOW(), 2.5, 200.0, NOW());

-- Table: Rating
INSERT INTO Rating (moodysRating, sandPRating, fitchRating, orderNumber) VALUES
                                                                             ('AAA', 'AA+', 'A+', 1),
                                                                             ('BBB', 'BB+', 'B+', 2);

-- Table: RuleName
INSERT INTO RuleName (name, description, json, template, sqlStr, sqlPart) VALUES
                                                                              ('Rule1', 'Description1', '{"key":"value"}', 'Template1', 'SELECT * FROM BidList', 'SQL Part 1'),
                                                                              ('Rule2', 'Description2', '{"key":"value2"}', 'Template2', 'SELECT * FROM Trade', 'SQL Part 2');


