CREATE OR REPLACE INDEXTYPE ImageIndexType
FOR Similarity(VARCHAR2, VARCHAR2)
USING ImageIndexMethods
WITH SYSTEM MANAGED STORAGE TABLES;