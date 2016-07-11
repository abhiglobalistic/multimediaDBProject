CREATE OR REPLACE TYPE BODY ImageIndexMethods IS
    STATIC FUNCTION ODCIGetInterfaces(ifclist OUT sys.ODCIObjectList)RETURN NUMBER IS
    BEGIN
      ifclist := sys.ODCIObjectList(sys.ODCIObject('SYS','ODCIINDEX2'));
      return ODCIConst.Success;
    END ODCIGetInterfaces;
    
    STATIC FUNCTION ODCIINDEXCREATE 
    (ia SYS.ODCIINDEXINFO,parms VARCHAR2, env SYS.ODCIEnv) 
    RETURN NUMBER AS LANGUAGE JAVA
    NAME 'image.Application.ODCIIndexCreate(oracle.ODCI.ODCIIndexInfo, java.lang.String, oracle.ODCI.ODCIEnv) return java.lang.Integer';

    STATIC FUNCTION ODCIINDEXDROP
    (ia SYS.ODCIINDEXINFO, 
    env SYS.ODCIEnv)
    RETURN NUMBER AS LANGUAGE JAVA
    NAME 'image.Application.ODCIIndexDrop(oracle.ODCI.ODCIIndexInfo, oracle.ODCI.ODCIEnv) return java.lang.Integer',

  	STATIC FUNCTION ODCIINDEXINSERT
    (ia SYS.ODCIINDEXINFO,
    rid ROWID,
    newval VARCHAR2,
    env SYS.ODCIEnv)
    RETURN NUMBER AS LANGUAGE JAVA
    NAME 'image.Application.ODCIIndexInsert(oracle.ODCI.ODCIIndexInfo, java.lang.String, java.lang.String, oracle.ODCI.ODCIEnv) return java.lang.Integer',

    STATIC FUNCTION ODCIINDEXUPDATE
    (ia SYS.ODCIINDEXINFO,
    rid ROWID,
    oldval VARCHAR2,
    newval VARCHAR2,
    env SYS.ODCIEnv)
    RETURN NUMBER AS LANGUAGE JAVA
    NAME 'image.Application.ODCIIndexUpdate(oracle.ODCI.ODCIIndexInfo, java.lang.String, java.lang.String, java.lang.String, oracle.ODCI.ODCIEnv) return java.lang.Integer',

  	STATIC FUNCTION ODCIINDEXDELETE
    (ia SYS.ODCIINDEXINFO,
    rid ROWID,
    oldval VARCHAR2,
    env SYS.ODCIEnv)
    RETURN NUMBER AS LANGUAGE JAVA
    NAME 'image.Application.ODCIIndexDelete(oracle.ODCI.ODCIIndexInfo, java.lang.String, java.lang.String, oracle.ODCI.ODCIEnv) return java.lang.Integer',

  	STATIC FUNCTION ODCIINDEXUPDATE
    (ia SYS.ODCIINDEXINFO,
    rid ROWID,
    oldval VARCHAR2,
    newval VARCHAR2,
    env SYS.ODCIEnv)
    RETURN NUMBER AS LANGUAGE JAVA
    NAME 'image.Application.ODCIIndexUpdate(oracle.ODCI.ODCIIndexInfo, java.lang.String, java.lang.String, java.lang.String, oracle.ODCI.ODCIEnv) return java.lang.Integer',

  STATIC FUNCTION ODCIINDEXSTART
    (sctx IN OUT LireIndex,
    ia SYS.ODCIINDEXINFO,
    op SYS.ODCIPREDINFO,
    qi SYS.ODCIQUERYINFO,
    strt NUMBER,
    stop NUMBER,
    lower_pos NUMBER,
    upper_pos NUMBER,
    env SYS.ODCIEnv)
    RETURN NUMBER AS LANGUAGE JAVA
    NAME 'image.Application.ODCIIndexStart(oracle.ODCI.ODCIIndexInfo, oracle.ODCI.ODCIPredInfo, oracle.ODCI.ODCIQueryInfo, java.lang.Integer, java.math.BigDecimal, java.lang.Integer, java.lang.Integer, oracle.ODCI.ODCIEnv) return java.lang.Integer',

  
  STATIC FUNCTION ODCIINDEXFETCH
    (sctx IN LireIndex,
    nrows NUMBER,
    rids OUT SYS.ODCIRIDLIST,
    env SYS.ODCIEnv)
    RETURN NUMBER AS LANGUAGE JAVA
    NAME 'image.Application.ODCIIndexFetch( java.lang.Integer, oracle.ODCI.ODCIRidList[], oracle.ODCI.ODCIEnv) return java.lang.Integer',
    
  STATIC FUNCTION ODCIINDEXALTER
    (ia SYS.ODCIINDEXINFO,
    parms VARCHAR2,
    altopt NUMBER,
    env SYS.ODCIEnv)
    RETURN NUMBER AS LANGUAGE JAVA
    NAME 'image.Application.ODCIIndexAlter(oracle.ODCI.ODCIIndexInfo, java.lang.String, java.long.Integer, oracle.ODCI.ODCIEnv) return java.lang.Integer'
   
END;