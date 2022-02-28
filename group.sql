/*データベース作成、選択*/
DROP DATABASE IF EXISTS groupCreate;
CREATE DATABASE groupCreate;
USE groupCreate CHARACTER SET utf8 COLLATE utf8_general_ci;

/*ユーザー定義、全権限付与*/
/*CREATE USER intern2021 IDENTIFIED BY 'intern2021';
GRANT ALL PRIVILEGES ON sampledb.* TO intern2021;*/

/*テーブル作成*/
CREATE TABLE users(
	id		INT	AUTO_INCREMENT	PRIMARY KEY,
	name	VARCHAR(25)	NOT NULL,
	password	VARCHAR(25) NOT NULL,
	resultA    INT NOT NULL,
	resultB    INT NOT NULL,
	resultC    INT NOT NULL,
	employment    INT NOT NULL
);


/*初期レコード追加*/
INSERT INTO users(id, name, password, resultA, resultB, resultC, employment) VALUES(1, 'ost', 'abcd', 0, 0, 0, 0);
INSERT INTO users(id, name, password, resultA, resultB, resultC, employment) VALUES(2, 'open', '1234', 0, 0, 0, 0);
INSERT INTO users(id, name, password, resultA, resultB, resultC, employment) VALUES(3, 'sesame', 'efgh', 0, 0, 0, 0);

