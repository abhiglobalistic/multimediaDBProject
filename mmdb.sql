--drop table imageCorel;
--
--CREATE TABLE imageCorel(
--  id integer primary key,
--  path bfile
--)
--
--
--CREATE DIRECTORY imageDirectory AS '/home/heni/Downloads/imageCorel';
--
--DECLARE
--  fileName varchar2(128);
--BEGIN
----  fileName := 'test';
----  dbms_output.Put_line(fileName);
--  FOR i IN 0..9907 LOOP
--    fileName := to_char(i)||'jpg';
--    Insert into imageCorel VALUES(i, BFILENAME('imageDirectory', fileName));
--  END LOOP;
--END;

select count(*) from IMAGECOREL;

--CREATE SEQUENCE idSequence
--  START WITH 1
--  INCREMENT BY 1
--  CACHE 10000;
--
--INSERT INTO imageCorel VALUES (idSequence.nextval, BFILENAME('MY_DIR', '0.jpg'));
