
# Anti-Guessing app

Anti-Guessing app generates unguessable 8-digit number combinations by analyzing the submitted combinations.

This app is written in Java using Dropwizard framework. 

Database: MariaDB

## Installation

For MariaDB, use syntax below to create following tables;

```sql
CREATE TABLE `ROUND` (
  `round_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `round_name` varchar(100) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`round_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4


CREATE TABLE `BETTING` (
  `betting_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `digit_0` int(11) DEFAULT NULL,
  `digit_1` int(11) DEFAULT NULL,
  `digit_2` int(11) DEFAULT NULL,
  `digit_3` int(11) DEFAULT NULL,
  `digit_4` int(11) DEFAULT NULL,
  `digit_5` int(11) DEFAULT NULL,
  `digit_6` int(11) DEFAULT NULL,
  `digit_7` int(11) DEFAULT NULL,
  `insert_datetime` datetime DEFAULT NULL,
  `round_id` bigint(20) NOT NULL,
  PRIMARY KEY (`betting_id`),
  KEY `BETTING_FK` (`round_id`),
  CONSTRAINT `BETTING_FK` FOREIGN KEY (`round_id`) REFERENCES `ROUND` (`round_id`) 
  ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4
```

Download, build and run the source code.

```linux
java -jar <appname>.jar server config.yml
```
Alternatively, you can import project into NetBeans.

## Usage

1. Create a new round as below;

   Method | Path | Request
   ------- | ------------- | -----------
   POST | /round/create | { "roundName": "Round 1" }

2. Place your 8-digit number combinations with correct round id. You can post your combinations as many as you want. If possible, you can ask your friend to guess some.

   Method | Path | Request
   ------- | ------------- | -----------
   POST | /betting/place | { "roundId": "2", "combination": "38330504" }
   POST | /betting/placeList | { "roundId": "6", "combination": [ "12345678", "12345678", "12345678" ] }

3. Get the generated combination as below;

   Method | Path | Param
   ------- | ------------- | -----------
   GET | /betting/generate | round-id

4. To check if generated combination exists, use following API to find out.

   Method | Path | Param
   ------- | ------------- | -----------
   GET | /betting/find | round-id & combination

5. To view all combinations collected for specific round, use following API;

   Method | Path | Param
   ------- | ------------- | -----------
   GET | /betting/view | round-id

6. You can also auto generate the betting if you don't have someone to play with. Select round and insert total number of bettings needed.

    Method | Path | Param
   ------- | ------------- | -----------
    GET | /betting/autobet/{round-id} | total

7. Hit me up if you are lucky :grin:

