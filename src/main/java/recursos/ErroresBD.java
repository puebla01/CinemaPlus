package recursos;

public class ErroresBD {

	 static final String DB_ERR = "Error de la base de datos";

	 static final int ORACLE_DUPLICATE_PK = 1062; //fallo MYSQL
	 static final int ORACLE_DELETE_FK = 1451;// fallo MYSQL 1451 fk no existe
	 static final int ORACLE_FALLO_FK = 1452; //fallo MYSQL FK en uso

}

