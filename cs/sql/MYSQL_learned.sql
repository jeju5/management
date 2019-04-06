CREATE DATABASE BowlingLeagueExample;

use BowlingLeagueExample;

SHOW TABLES;

CREATE TABLE Bowler_Scores (
	GameNumber smallint NOT NULL DEFAULT 0 ,
	BowlerID int NOT NULL DEFAULT 0 ,
	RawScore smallint NULL DEFAULT 0 ,
	HandiCapScore smallint NULL DEFAULT 0 ,
	WonGame bit NOT NULL DEFAULT 0 
) ;

DESCRIBE Bowler_Scores;

CREATE TABLE Bowlers (
	BowlerID int NOT NULL DEFAULT 0 ,
	BowlerLastName nvarchar (50) NULL ,
	BowlerFirstName nvarchar (50) NULL ,
	BowlerPhoneNumber nvarchar (14) NULL ,
);

DESCRIBE Bowlers;

CREATE TABLE ztblWeeks (
    WeekStart date NOT NULL ,
    WeekEnd date NULL
);

/* you can name constraint of group of keys */
ALTER TABLE Bowler_Scores
ADD CONSTRAINT Bowler_Scores_PK
PRIMARY KEY (
    GameNumber,
    BowlerID
);


/* You can add Index on a column(s). It works behind the scene */
CREATE INDEX BowlerID
ON Bowler_Scores(BowlerID);

CREATE UNIQUE INDEX TeamID
ON Teams(TeamID);


ALTER TABLE Bowler_Scores 
ADD CONSTRAINT Bowler_Scores_FK00
FOREIGN KEY (
    BowlerID
) REFERENCES Bowlers (
    BowlerID
),
ADD CONSTRAINT Bowler_Scores_FK01
FOREIGN KEY (
    MatchID,
    GameNumber
) REFERENCES Match_Games (
    MatchID,
    GameNumber
);

INSERT INTO Bowler_Scores(MatchID, GameNumber, BowlerID, RawScore, HandiCapScore, WonGame)
VALUES (17, 2, 23, 156, 187, 0);

Use BowlingLeagueExample;

CREATE VIEW CH04_Bowler_Names_Addresses
AS
SELECT  BowlerLastName, BowlerFirstName, BowlerAddress, BowlerCity, BowlerState, BowlerZip
FROM    Bowlers
ORDER BY BowlerLastName, BowlerFirstName DESC;

select * from CH04_Bowler_Names_Addresses;

CREATE VIEW CH04_Tourney_Locations AS
SELECT DISTINCT TourneyLocation
FROM          Tournaments;

CREATE VIEW CH05_Names_Address_For_Mailing AS
SELECT  Concat(BowlerFirstName, ' ', BowlerLastName) AS FullName, BowlerAddress, 
        Concat(BowlerCity, ', ', BowlerState, '  ', BowlerZip) AS CityStateZip,
        BowlerZip
FROM    Bowlers
ORDER BY BowlerZip;

/* Interval X Day is one unit */
CREATE VIEW CH05_Next_Years_Tourney_Dates AS
SELECT     TourneyLocation, TourneyDate, TourneyDate + Interval 364 Day AS NextYearTourneyDate
FROM          Tournaments
ORDER BY TourneyLocation;

/* Between a and b is a<= x <=b */
CREATE VIEW CH06_Eastside_Bowlers_On_Teams_5_Through_8 AS
SELECT BowlerFirstName, BowlerLastName, BowlerCity, TeamID
FROM Bowlers
WHERE (BowlerCity IN ('Bellevue', 'Bothell', 'Duvall')) AND (TeamID BETWEEN 5 AND 8)
ORDER BY BowlerCity, BowlerLastName;

CREATE VIEW CH06_H_Bowlers_Teams_3_Through_5
AS
SELECT  BowlerFirstName, BowlerLastName, TeamID
FROM    Bowlers
WHERE   (BowlerLastName LIKE 'H%') AND (TeamID IN (3, 4, 5));

CREATE VIEW CH06_September_2012_Tournament_Schedule
AS
SELECT  TourneyDate, TourneyLocation
FROM    Tournaments
WHERE   (TourneyDate BETWEEN CAST('2012-9-1' As Date) and CAST('2012-9-30' As Date));


/* if you inner join then you get columns from joined table as well */
CREATE VIEW CH08_Bowler_Game_Scores
AS
SELECT  Bowler_Scores.MatchID,
        Teams.TeamName,
        Concat(Bowlers.BowlerFirstName, ' ', Bowlers.BowlerLastName) AS BowlerFullName, 
        Bowler_Scores.GameNumber,
        Bowler_Scores.RawScore
FROM    Teams
        INNER JOIN  Bowlers ON  Teams.TeamID = Bowlers.TeamID
        INNER JOIN  Bowler_Scores ON Bowlers.BowlerID = Bowler_Scores.BowlerID;

/* inner joining the same table. you can name is Bolwers2 like below (alias) */
CREATE VIEW CH08_Bowlers_Same_ZipCode
AS
SELECT  Concat(Bowlers.BowlerLastName, ', ', Bowlers.BowlerFirstName) AS FirstBowler,
        Bowlers.BowlerZip, 
        Concat(Bowlers2.BowlerLastName, ', ', Bowlers2.BowlerFirstName) AS SecondBowler
FROM  Bowlers
      INNER JOIN Bowlers Bowlers2 ON Bowlers.BowlerID <> Bowlers2.BowlerID 
                                  AND Bowlers.BowlerZip = Bowlers2.BowlerZip;

/* BowlerTbird is an alias*/
/* syntax: FROM table-name alias-name */
/* () is optional. by default inner join takes the first one in the inner most manner*/
CREATE  VIEW CH08_Good_Bowlers_TBird_And_Bolero
AS
SELECT  BowlerTbird.BowlerFullName
FROM    (SELECT DISTINCT Bowlers.BowlerID,
                Concat(Bowlers.BowlerLastName, ', ', Bowlers.BowlerFirstName) AS BowlerFullName
        FROM    Bowlers INNER JOIN Bowler_Scores ON Bowlers.BowlerID = Bowler_Scores.BowlerID
                        INNER JOIN Tourney_Matches ON Tourney_Matches.MatchID = Bowler_Scores.MatchID
                        INNER JOIN Tournaments ON Tournaments.TourneyID = Tourney_Matches.TourneyID
                                    WHERE Tournaments.TourneyLocation = 'Thunderbird Lanes' AND Bowler_Scores.RawScore >= 170
        )
        BowlerTbird
        INNER JOIN  (SELECT DISTINCT Bowlers.BowlerID,
                            Concat(Bowlers.BowlerLastName, ', ', Bowlers.BowlerFirstName) AS BowlerFullName
                    FROM    ((Bowlers 
                            INNER JOIN Bowler_Scores ON Bowlers.BowlerID = Bowler_Scores.BowlerID) 
                            INNER JOIN Tourney_Matches ON Tourney_Matches.MatchID = Bowler_Scores.MatchID)
                            INNER JOIN Tournaments ON Tournaments.TourneyID = Tourney_Matches.TourneyID
                                        WHERE Tournaments.TourneyLocation = 'Bolero Lanes' AND Bowler_Scores.RawScore >= 170
                    )
                    BowlerBolero ON BowlerTbird.BowlerID = BowlerBolero.BowlerID;

/*
# inner join selects rows that match "ON condition"
SELECT T1.column1, T2.column2
FROM    T1
        INNER JOIN T2 ON "condition using T1 T2"
ORDER BY T1.column1;


# left join selects all rows from "FROM table" and rows that matches "ON condition" from "LEFT JOIN table"
SELECT T1.column1, T2.column2
FROM    T1
        LEFT JOIN T2 ON "condition using T1 T2"
ORDER BY T1.column1;


# right join selects all rows from "RIGH JOIN table" and rows that matches "ON condition" from "LEFT TABLE"
SELECT T1.column1, T2.column2
FROM    T1
        RIGHT JOIN T2 ON "condition using T1 T2"
ORDER BY T1.column1;

*/

/* left join and left outer join are the same; LEFT JOIN == LEFT OUTER JOIN */
/* join syntax: JOIN table-name AS alias-name */
CREATE VIEW CH09_All_Bowlers_And_Scores_Over_180
AS
SELECT  Concat(Bowlers.BowlerLastName, ', ', Bowlers.BowlerFirstName) AS BowlerName,
        TI.TourneyDate, TI.TourneyLocation, TI.MatchID, TI.RawScore
FROM    Bowlers
        LEFT JOIN CH09_All_Scores_Over_180 AS TI
        ON Bowlers.BowlerID = TI.BowlerID
ORDER BY BowlerName;


CREATE VIEW CH09_All_Match_Results
AS
SELECT     Tourney_Matches.TourneyID, Tourney_Matches.MatchID, Match_Games.GameNumber, Teams.TeamName AS Winner
                            FROM          Tourney_Matches INNER JOIN
                                                   (Teams INNER JOIN
                                                   Match_Games ON Teams.TeamID = Match_Games.WinningTeamID) ON Tourney_Matches.MatchID = Match_Games.MatchID;

CREATE VIEW CH09_All_Tourneys_Match_Results
AS
SELECT   Tournaments.TourneyID, Tournaments.TourneyDate, Tournaments.TourneyLocation, TM.MatchID, TM.GameNumber, 
                      TM.Winner
FROM         Tournaments LEFT OUTER JOIN
                          CH09_All_Match_Results AS TM ON 
                      Tournaments.TourneyID = TM.TourneyID
ORDER BY Tournaments.TourneyID;

CREATE VIEW CH09_Matches_Not_Played_Yet
AS
SELECT     Tourney_Matches.MatchID, Tourney_Matches.TourneyID, Teams.TeamName AS OddLaneTeam, 
                         Teams_1.TeamName AS EvenLaneTeam
FROM          Teams Teams_1 INNER JOIN
                         (Teams INNER JOIN
                         (Tourney_Matches LEFT OUTER JOIN
                         Match_Games ON Tourney_Matches.MatchID = Match_Games.MatchID) ON 
                         Teams.TeamID = Tourney_Matches.OddLaneTeamID) ON Teams_1.TeamID = Tourney_Matches.EvenLaneTeamID
WHERE     (Match_Games.MatchID IS NULL);

CREATE VIEW CH09_Tourney_Not_Yet_Played
AS
SELECT     Tournaments.TourneyID, Tournaments.TourneyDate, Tournaments.TourneyLocation
FROM 
         Tournaments LEFT OUTER JOIN
                         Tourney_Matches ON Tournaments.TourneyID = Tourney_Matches.TourneyID
WHERE     (Tourney_Matches.MatchID IS NULL);

CREATE VIEW CH10_Bowling_Schedule
AS
SELECT     Tournaments.TourneyLocation, Tournaments.TourneyDate, Tourney_Matches.MatchID, Teams.TeamName,
           Concat(Bowlers.BowlerLastName, ', ', Bowlers.BowlerFirstName) AS Captain, 'Odd Lane' AS Lane
FROM          ((Tournaments INNER JOIN
              Tourney_Matches ON Tournaments.TourneyID = Tourney_Matches.TourneyID) INNER JOIN
              Teams ON Teams.TeamID = Tourney_Matches.OddLaneTeamID) INNER JOIN
              Bowlers ON Bowlers.BowlerID = Teams.CaptainID
UNION ALL
SELECT     Tournaments.TourneyLocation, Tournaments.TourneyDate, Tourney_Matches.MatchID, Teams.TeamName,
           Concat(Bowlers.BowlerLastName, ', ', Bowlers.BowlerFirstName) AS Captain, 'Even Lane' AS Lane
FROM         ((Tournaments INNER JOIN
             Tourney_Matches ON Tournaments.TourneyID = Tourney_Matches.TourneyID) INNER JOIN
             Teams ON Teams.TeamID = Tourney_Matches.EvenLaneTeamID) INNER JOIN
             Bowlers ON Bowlers.BowlerID = Teams.CaptainID
ORDER BY 2, 3;

CREATE VIEW CH10_Good_Bowlers_TBird_Bolero_UNION
AS
SELECT     Tournaments.TourneyLocation, Bowlers.BowlerLastName, Bowlers.BowlerFirstName, Bowler_Scores.RawScore
FROM          Bowlers INNER JOIN
                         ((Tournaments INNER JOIN
                         Tourney_Matches ON (Tournaments.TourneyID = Tourney_Matches.TourneyID)) INNER JOIN
                         Bowler_Scores ON (Tourney_Matches.MatchID = Bowler_Scores.MatchID)) ON (Bowlers.BowlerID = Bowler_Scores.BowlerID)
WHERE     Tournaments.TourneyLocation = 'Thunderbird Lanes' AND Bowler_Scores.RawScore > 165
UNION
SELECT     Tournaments.TourneyLocation, Bowlers.BowlerLastName, Bowlers.BowlerFirstName, Bowler_Scores.RawScore
FROM          Bowlers INNER JOIN
                         ((Tournaments INNER JOIN
 
                        Tourney_Matches ON (Tournaments.TourneyID = Tourney_Matches.TourneyID)) INNER JOIN
                         Bowler_Scores ON (Tourney_Matches.MatchID = Bowler_Scores.MatchID)) ON (Bowlers.BowlerID = Bowler_Scores.BowlerID)
WHERE     Tournaments.TourneyLocation = 'Bolero Lanes' AND Bowler_Scores.RawScore > 150;

CREATE VIEW CH10_Good_Bowlers_TBird_Bolero_WHERE
AS
SELECT     Tournaments.TourneyLocation, Bowlers.BowlerLastName, Bowlers.BowlerFirstName, Bowler_Scores.RawScore
FROM          Tournaments INNER JOIN
                         (Bowlers INNER JOIN
                         (Tourney_Matches INNER JOIN
                         Bowler_Scores ON Tourney_Matches.MatchID = Bowler_Scores.MatchID) ON Bowlers.BowlerID = Bowler_Scores.BowlerID) ON 
                         Tournaments.TourneyID = Tourney_Matches.TourneyID
WHERE     (Tournaments.TourneyLocation = 'Thunderbird Lanes') AND (Bowler_Scores.RawScore > 165) OR
                         (Tournaments.TourneyLocation = 'Bolero Lanes') AND (Bowler_Scores.RawScore > 150);

CREATE VIEW CH11_Bowler_High_Score
AS
SELECT     BowlerFirstName, BowlerLastName,
                              (SELECT     MAX(RawScore)
                                FROM           Bowler_Scores
                                WHERE       Bowler_Scores.BowlerID = Bowlers.BowlerID) AS HighScore
FROM          Bowlers;

CREATE VIEW CH11_Bowlers_And_Count_Games
AS
SELECT     BowlerFirstName, BowlerLastName,
                              (SELECT     COUNT(*)
                                FROM           Bowler_Scores
                                WHERE       Bowler_Scores.BowlerID = Bowlers.BowlerID) AS Games
FROM          Bowlers;

CREATE VIEW CH11_Bowlers_Low_Score
AS
SELECT DISTINCT Bowlers.BowlerID, Bowlers.BowlerFirstName, Bowlers.BowlerLastName, Bowler_Scores.RawScore
FROM          Bowlers INNER JOIN
                         Bowler_Scores ON Bowlers.BowlerID = Bowler_Scores.BowlerID
WHERE     (Bowler_Scores.RawScore < ALL
                              (SELECT     BS2.RawScore
 
                               FROM           Bowlers AS B2 INNER JOIN
                                                           Bowler_Scores AS BS2 ON B2.BowlerID = BS2.BowlerID
                                WHERE       B2.BowlerID <> Bowlers.BowlerID AND B2.TeamID = Bowlers.TeamID));

CREATE VIEW CH11_Team_Captains_High_Score
AS
SELECT     Teams.TeamName, Bowlers.BowlerID, Bowlers.BowlerFirstName, Bowlers.BowlerLastName, 
                         Bowler_Scores.HandiCapScore
FROM          Bowlers INNER JOIN
                         Teams ON Bowlers.BowlerID = Teams.CaptainID INNER JOIN
                         Bowler_Scores ON Bowlers.BowlerID = Bowler_Scores.BowlerID
WHERE     (Bowler_Scores.HandiCapScore > ALL
                              (SELECT     BS2.HandiCapScore
                                FROM           Bowlers AS B2 INNER JOIN
                                                           Bowler_Scores AS BS2 ON B2.BowlerID = BS2.BowlerID
                                WHERE       B2.BowlerID <> Bowlers.BowlerID AND B2.TeamID = Bowlers.TeamID));

CREATE VIEW CH12_Better_Than_Overall_Average
AS
SELECT     BowlerLastName, BowlerFirstName
FROM          Bowlers
WHERE     ((SELECT     AVG(RawScore)
                             FROM          Bowler_Scores AS BS
                             WHERE     BS.BowlerID = Bowlers.BowlerID) >=
                              (SELECT     AVG(RawScore)
                                FROM           Bowler_Scores))
ORDER BY BowlerLastName, BowlerFirstName;

CREATE VIEW CH12_Current_Highest_Handicap
AS
SELECT Max(ROUND((200 - ROUND((SELECT     AVG(RawScore)
                               FROM         Bowler_Scores
                               WHERE     Bowler_Scores.BowlerID = Bowlers.BowlerID)
                      , 0)) * 0.9, 0)) AS HighHandicap
FROM Bowlers;

CREATE VIEW CH12_Last_Tourney_Date
AS
SELECT     MAX(TourneyDate) AS MostRecentDate
FROM          Tournaments;

CREATE VIEW CH12_Number_Of_Tournaments_At_Red_Rooster_Lanes
AS
SELECT     COUNT(TourneyLocation) AS NumberOfTournaments
FROM          Tournaments
WHERE     (TourneyLocation = 'Red Rooster Lanes');

CREATE VIEW CH12_Tourney_Locations_For_Earliest_Date
AS
SELECT     TourneyLocation
FROM          Tournaments
WHERE     (TourneyDate =
                              (SELECT     MIN(TourneyDate)
                                FROM           Tournaments));

CREATE VIEW CH13_Bowler_Average_Handicap
AS
SELECT     Bowler_Scores.BowlerID, Bowlers.BowlerLastName, Bowlers.BowlerFirstName, SUM(Bowler_Scores.RawScore) AS TotalPins, 
                         COUNT(Bowler_Scores.RawScore) AS GamesBowled, ROUND(AVG(Bowler_Scores.RawScore), 0) AS CurAvg, 
                         ROUND(0.9 * (200 - ROUND(AVG(Bowler_Scores.RawScore), 0)), 0) AS CurHcp
FROM          Bowlers INNER JOIN
                         Bowler_Scores ON Bowlers.BowlerID = Bowler_Scores.BowlerID
GROUP BY Bowler_Scores.BowlerID, Bowlers.BowlerLastName, Bowlers.BowlerFirstName;

CREATE VIEW CH13_Bowler_Averages
AS
SELECT     Concat(Bowlers.BowlerLastName, ', ', Bowlers.BowlerFirstName) AS BowlerFullName, AVG(Bowler_Scores.RawScore) 
                         AS AvgOfRawScore
FROM          Bowlers INNER JOIN
                         Bowler_Scores ON Bowlers.BowlerID = Bowler_Scores.BowlerID
GROUP BY Bowlers.BowlerLastName, Bowlers.BowlerFirstName;

CREATE VIEW CH13_Bowler_High_Score_Group
AS
SELECT     Bowlers.BowlerFirstName, Bowlers.BowlerLastName, MAX(Bowler_Scores.RawScore) AS HighScore
FROM          Bowlers INNER JOIN
                         Bowler_Scores ON Bowlers.BowlerID = Bowler_Scores.BowlerID
GROUP BY Bowlers.BowlerFirstName, Bowlers.BowlerLastName;

CREATE VIEW CH13_Bowler_High_Score_Subquery
AS
SELECT     BowlerFirstName, BowlerLastName,
                              (SELECT     MAX(RawScore)
                                FROM           Bowler_Scores
                                WHERE       Bowler_Scores.BowlerID = Bowlers.BowlerID) AS HighScore
FROM          Bowlers;

CREATE VIEW CH13_Tournament_Match_Team_Results
AS
SELECT     Tournaments.TourneyID, Tournaments.TourneyLocation, Tourney_Matches.MatchID, Teams.TeamName, 
                        SUM(Bowler_Scores.HandiCapScore) AS TotHandiCapScore
FROM          Tournaments INNER JOIN
                         Tourney_Matches ON Tournaments.TourneyID = Tourney_Matches.TourneyID INNER JOIN
                         Match_Games ON Tourney_Matches.MatchID = Match_Games.MatchID INNER JOIN
                         Bowler_Scores ON Match_Games.GameNumber = Bowler_Scores.GameNumber AND 
                         Match_Games.MatchID = Bowler_Scores.MatchID INNER JOIN
                         Bowlers ON Bowlers.BowlerID = Bowler_Scores.BowlerID INNER JOIN
                         Teams ON Teams.TeamID = Bowlers.TeamID
GROUP BY Tournaments.TourneyID, Tournaments.TourneyLocation, Tourney_Matches.MatchID, Teams.TeamName;

CREATE VIEW CH14_Better_Than_Overall_Average_Having
AS
SELECT     Bowlers.BowlerLastName, Bowlers.BowlerFirstName
FROM          Bowlers INNER JOIN
                         Bowler_Scores ON Bowlers.BowlerID = Bowler_Scores.BowlerID
GROUP BY Bowlers.BowlerLastName, Bowlers.BowlerFirstName
HAVING       (AVG(Bowler_Scores.RawScore) >=
                              (SELECT     AVG(RawScore)
                                FROM           Bowler_Scores));

CREATE VIEW CH14_Bowlers_Big_High_Score
AS
SELECT     Bowlers.BowlerFirstName, Bowlers.BowlerLastName, ROUND(AVG(Bowler_Scores.RawScore), 0) AS CurrentAverage, 
                         MAX(Bowler_Scores.RawScore) AS HighGame
FROM          Bowlers INNER JOIN
                         Bowler_Scores ON Bowlers.BowlerID = Bowler_Scores.BowlerID
GROUP BY Bowlers.BowlerFirstName, Bowlers.BowlerLastName
HAVING       (MAX(Bowler_Scores.RawScore) > ROUND(AVG(Bowler_Scores.RawScore), 0) + 20);

CREATE VIEW CH14_Captains_Who_Are_Hotshots
AS
SELECT     Teams.TeamID, Bowlers.BowlerID, Bowlers.BowlerFirstName, Bowlers.BowlerLastName, MAX(Bowler_Scores.RawScore) 
                         AS MaxOfRawScore
FROM          Bowlers INNER JOIN
                         Bowler_Scores ON Bowlers.BowlerID = Bowler_Scores.BowlerID INNER JOIN
                         Teams ON Bowlers.BowlerID = Teams.CaptainID
GROUP BY Teams.TeamID, Bowlers.BowlerID, Bowlers.BowlerFirstName, Bowlers.BowlerLastName
HAVING       (MAX(Bowler_Scores.RawScore) >
                              (SELECT     MAX(RawScore)
                                FROM           (Teams AS T2 INNER JOIN
                                                           Bowlers AS B2 ON T2.TeamID = B2.TeamID) INNER JOIN
                                                           Bowler_Scores ON B2.BowlerID = Bowler_Scores.BowlerID
                                WHERE       T2.TeamID = Teams.TeamID AND B2.BowlerID <> Bowlers.BowlerID));

CREATE VIEW CH14_Good_Bowlers
AS
SELECT     Bowlers.BowlerFirstName, Bowlers.BowlerLastName, AVG(Bowler_Scores.RawScore) AS AvgScore
FROM          Bowlers INNER JOIN
                         Bowler_Scores ON Bowlers.BowlerID = Bowler_Scores.BowlerID
GROUP BY Bowlers.BowlerFirstName, Bowlers.BowlerLastName
HAVING       (AVG(Bowler_Scores.RawScore) > 155);


CREATE VIEW CH18_Bowlers_LTE_165_Thunderbird_Bolero
AS 
SELECT Bowlers.BowlerID, Bowlers.BowlerLastName, Bowlers.BowlerFirstName
FROM Bowlers
WHERE Bowlers.BowlerID NOT IN 
(SELECT Bowler_Scores.BowlerID 
FROM (Tournaments
INNER JOIN Tourney_Matches 
ON Tournaments.TourneyID = Tourney_Matches.TourneyID) 
INNER JOIN Bowler_Scores  
ON Tourney_Matches.MatchID = Bowler_Scores.MatchID
WHERE (Bowler_Scores.RawScore > 165)
AND (Tournaments.TourneyLocation IN ('Thunderbird Lanes', 'Bolero Lanes')));


CREATE VIEW CH18_Bowlers_Won_LowScore_TBird_Totem_Bolero_RIGHT
AS 
SELECT Bowlers.BowlerID, Bowlers.BowlerFirstName, Bowlers.BowlerLastName, 
   Bowler_Scores.MatchID, Bowler_Scores.GameNumber, Bowler_Scores.HandiCapScore, 
   Tournaments.TourneyDate, Tournaments.TourneyLocation
FROM ((Bowlers
INNER JOIN Bowler_Scores
ON Bowlers.BowlerID=Bowler_Scores.BowlerID)
INNER JOIN Tourney_Matches
ON Bowler_Scores.MatchID=Tourney_Matches.MatchID)
INNER JOIN Tournaments
ON Tournaments.TourneyID=Tourney_Matches.TourneyID
WHERE (Bowler_Scores.HandiCapScore <= 190)
AND (Bowler_Scores.WonGame = 1)
AND (Tournaments.TourneyLocation IN ('Thunderbird Lanes', 'Totem Lanes', 'Bolero Lanes')) 
AND (Bowlers.BowlerID IN  (SELECT Bowler_Scores.BowlerID  FROM (Tournaments
INNER JOIN Tourney_Matches 
ON Tournaments.TourneyID = Tourney_Matches.TourneyID) 
INNER JOIN Bowler_Scores 
ON Tourney_Matches.MatchID = Bowler_Scores.MatchID WHERE Bowler_Scores.WonGame = 1 
AND Bowler_Scores.HandiCapScore <=190 
AND Tournaments.TourneyLocation = 'Thunderbird Lanes'))
AND (Bowlers.BowlerID IN  (SELECT Bowler_Scores.BowlerID  FROM (Tournaments
INNER JOIN Tourney_Matches 
ON Tournaments.TourneyID = Tourney_Matches.TourneyID) 
INNER JOIN Bowler_Scores 
ON Tourney_Matches.MatchID = Bowler_Scores.MatchID WHERE Bowler_Scores.WonGame = 1 
AND Bowler_Scores.HandiCapScore <=190 
AND Tournaments.TourneyLocation = 'Totem Lanes'))
AND (Bowlers.BowlerID IN (SELECT Bowler_Scores.BowlerID  FROM (Tournaments
INNER JOIN Tourney_Matches 
ON Tournaments.TourneyID = Tourney_Matches.TourneyID) 
INNER JOIN Bowler_Scores 
ON Tourney_Matches.MatchID = Bowler_Scores.MatchID WHERE Bowler_Scores.WonGame = 1 
AND Bowler_Scores.HandiCapScore <=190 
AND Tournaments.TourneyLocation = 'Bolero Lanes'));


CREATE VIEW CH18_Bowlers_Won_LowScore_TBird_Totem_Bolero_WRONG
AS 
SELECT Bowlers.BowlerID, Bowlers.BowlerFirstName, Bowlers.BowlerLastName, 
   Bowler_Scores.MatchID, Bowler_Scores.GameNumber, Bowler_Scores.HandiCapScore, 
   Tournaments.TourneyDate, Tournaments.TourneyLocation
FROM ((Bowlers
INNER JOIN Bowler_Scores
ON Bowlers.BowlerID=Bowler_Scores.BowlerID)
INNER JOIN Tourney_Matches
ON Bowler_Scores.MatchID=Tourney_Matches.MatchID)
INNER JOIN Tournaments
ON Tournaments.TourneyID=Tourney_Matches.TourneyID
WHERE (Bowler_Scores.HandiCapScore<=190)
AND (Bowler_Scores.WonGame = 1)
AND (Tournaments.TourneyLocation In ('Thunderbird Lanes','Totem Lanes','Bolero Lanes'));


CREATE VIEW CH18_Good_Bowlers_TBird_And_Bolero_EXISTS
AS 
SELECT Bowlers.BowlerID, Bowlers.BowlerLastName, Bowlers.BowlerFirstName
FROM Bowlers
WHERE EXISTS  
(SELECT *  
FROM (Tournaments
INNER JOIN Tourney_Matches 
ON Tournaments.TourneyID = Tourney_Matches.TourneyID) 
INNER JOIN Bowler_Scores  
ON Tourney_Matches.MatchID = Bowler_Scores.MatchID
WHERE (Bowler_Scores.BowlerID = Bowlers.BowlerID) 
AND (Bowler_Scores.RawScore >= 170)
AND (Tournaments.TourneyLocation = 'Thunderbird Lanes'))
AND EXISTS  
(SELECT *  
FROM (Tournaments
INNER JOIN Tourney_Matches 
ON Tournaments.TourneyID = Tourney_Matches.TourneyID) 
INNER JOIN Bowler_Scores  
ON Tourney_Matches.MatchID = Bowler_Scores.MatchID
WHERE (Bowler_Scores.BowlerID = Bowlers.BowlerID) 
AND (Bowler_Scores.RawScore >= 170)
AND (Tournaments.TourneyLocation = 'Bolero Lanes'));


CREATE VIEW CH18_Mediocre_Bowlers
AS 
SELECT Bowlers.BowlerID, Bowlers.BowlerFirstName, Bowlers.BowlerLastName
FROM Bowlers
WHERE NOT EXISTS 
(SELECT * FROM Bowler_Scores 
WHERE Bowler_Scores.RawScore > 150 
AND Bowler_Scores.BowlerID = Bowlers.BowlerID);


/* MySQL does not support complex embedded SELECT statements in the FROM clause in the following VIEW.
See below for views constructed on views to obtain the same result.
CREATE VIEW CH19_All_Tourney_Matches
AS
SELECT Tournaments.TourneyDate, Tournaments.TourneyLocation, Tourney_Matches.Lanes,
                         Tourney_Matches.MatchID, Teams.TeamName AS OddLaneTeam, 
                         Teams_1.TeamName AS EvenLaneTeam, GameResults.GameNumber, 
                         (CASE WHEN GameResults.TeamName IS NULL 
                         THEN 'Match not played' ELSE GameResults.TeamName END) AS Winner
FROM   (((Tournaments INNER JOIN Tourney_Matches 
       ON Tournaments.TourneyID = Tourney_Matches.TourneyID) 
       INNER JOIN Teams 
       ON Tourney_Matches.OddLaneTeamID = Teams.TeamID) 
       INNER JOIN Teams AS Teams_1 
       ON Tourney_Matches.EvenLaneTeamID = Teams_1.TeamID) 
       LEFT OUTER JOIN
       (SELECT  Match_Games.MatchID, Match_Games.GameNumber, Teams_2.TeamName
        FROM    Match_Games INNER JOIN Teams AS Teams_2 
                ON Match_Games.WinningTeamID = Teams_2.TeamID) AS GameResults 
       ON Tourney_Matches.MatchID = GameResults.MatchID
ORDER BY Tournaments.TourneyDate, Tourney_Matches.MatchID, GameResults.GameNumber;

Note, however, that you can copy and paste the above SELECT statement (without CREATE VIEW) into the query window to get the results directly.
*/

CREATE VIEW CH19_All_Tourney_Games
AS
SELECT  Match_Games.MatchID, Match_Games.GameNumber, Teams_2.TeamName
        FROM    Match_Games INNER JOIN Teams AS Teams_2 
                ON Match_Games.WinningTeamID = Teams_2.TeamID;

CREATE VIEW CH19_All_Tourney_Matches
AS
SELECT Tournaments.TourneyDate, Tournaments.TourneyLocation, Tourney_Matches.Lanes,
                         Tourney_Matches.MatchID, Teams.TeamName AS OddLaneTeam, 
                         Teams_1.TeamName AS EvenLaneTeam, GameResults.GameNumber, 
                         (CASE WHEN GameResults.TeamName IS NULL 
                         THEN 'Match not played' ELSE GameResults.TeamName END) AS Winner
FROM   (((Tournaments INNER JOIN Tourney_Matches 
       ON Tournaments.TourneyID = Tourney_Matches.TourneyID) 
       INNER JOIN Teams 
       ON Tourney_Matches.OddLaneTeamID = Teams.TeamID) 
       INNER JOIN Teams AS Teams_1 
       ON Tourney_Matches.EvenLaneTeamID = Teams_1.TeamID) 
       LEFT OUTER JOIN CH19_All_Tourney_Games AS GameResults 
       ON Tourney_Matches.MatchID = GameResults.MatchID
ORDER BY Tournaments.TourneyDate, Tourney_Matches.MatchID, GameResults.GameNumber;


CREATE VIEW CH19_Bowler_Averages_Avoid_0_Games

AS
SELECT  Bowlers.BowlerID, Bowlers.BowlerFirstName, Bowlers.BowlerLastName, 
          COUNT(Bowler_Scores.MatchID) AS GamesBowled, SUM(Bowler_Scores.RawScore) AS TotalPins, 
          (CASE COUNT(Bowler_Scores.MatchID) 
           WHEN 0 THEN 0 
           ELSE CAST((SUM(Bowler_Scores.RawScore)/COUNT(Bowler_Scores.MatchID)) AS Signed Integer) END) AS BowlerAverage
FROM    Bowlers LEFT OUTER JOIN Bowler_Scores 
        ON Bowlers.BowlerID = Bowler_Scores.BowlerID
GROUP BY Bowlers.BowlerID, Bowlers.BowlerFirstName, Bowlers.BowlerLastName;


CREATE VIEW CH19_Bowler_Ratings AS
SELECT   Bowlers.BowlerID, Bowlers.BowlerLastName, Bowlers.BowlerFirstName, CAST(AVG(Bowler_Scores.RawScore) AS Signed Integer) AS BowlerAverage, 
            (CASE WHEN CAST(AVG(Bowler_Scores.RawScore) AS Signed Integer) < 140 THEN 'Fair' 
                  WHEN CAST(AVG(Bowler_Scores.RawScore) AS Signed Integer) < 160 THEN 'Average' 
                  WHEN CAST(AVG(Bowler_Scores.RawScore) AS Signed Integer) < 185 THEN 'Good' 
                  ELSE 'Excellent' END) AS BowlerRating
FROM     Bowlers INNER JOIN Bowler_Scores 
         ON Bowlers.BowlerID = Bowler_Scores.BowlerID
GROUP BY Bowlers.BowlerID, Bowlers.BowlerLastName, Bowlers.BowlerFirstName;

CREATE VIEW CH20_Bowler_Mailing_Skip_3
AS 
SELECT ' ' As BowlerLastName, ' ' As BowlerFirstName, ' ' As BowlerAddress, ' ' As BowlerCity, ' ' As BowlerState, ' ' As BowlerZip
FROM ztblSkipLabels
WHERE ztblSkipLabels.LabelCount <= 3
UNION ALL SELECT BowlerLastName, BowlerFirstName, BowlerAddress, BowlerCity, BowlerState, BowlerZip
FROM Bowlers
ORDER BY BowlerZip, BowlerLastName;