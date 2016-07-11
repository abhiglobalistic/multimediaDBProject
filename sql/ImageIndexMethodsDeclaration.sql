CREATE OR REPLACE TYPE ImageIndexMethods FORCE AS OBJECT(
scanctx RAW(4),
STATIC FUNCTION ODCIGETINTERFACES(ifclist OUT sys.ODCIObjectList) RETURN NUMBER, 
STATIC FUNCTION ODCIINDEXCREATE (ia SYS.ODCIINDEXINFO, parms VARCHAR2, env SYS.ODCIEnv) RETURN NUMBER,
static function ODCIIndexDrop(ia SYS.ODCIIndexInfo, env SYS.ODCIEnv) return number,
static function ODCIIndexInsert(ia SYS.ODCIIndexInfo, rid VARCHAR2,newval VARCHAR2, env SYS.ODCIEnv)return number,
static function ODCIIndexUpdate(ia SYS.ODCIIndexInfo, rid VARCHAR2,oldval VARCHAR2, newval VARCHAR2, env SYS.ODCIEnv)return number,
static function ODCIIndexDelete(ia SYS.ODCIIndexInfo, rid VARCHAR2,oldval VARCHAR2, env SYS.ODCIEnv)return number,
static function ODCIIndexStart(sctx IN OUT psbtree_im, ia SYS.ODCIIndexInfo,op SYS.ODCIPredInfo, qi sys.ODCIQueryInfo, strt number, stop number,cmpval VARCHAR2, env SYS.ODCIEnv)return number,
member function ODCIIndexFetch(nrows NUMBER, rids OUT SYS.ODCIridlist, env SYS.ODCIEnv)return number,
member function ODCIIndexClose(env SYS.ODCIEnv)
return number
);