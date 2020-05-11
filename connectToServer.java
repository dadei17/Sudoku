import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class connectToServer {
	private Connection connect; 
	private Statement stmt;
	private  String account = "root";
	private  String password = "dito2017";
	private  String server = "localhost:3306/"; 
	private  String database = "dito";
	private  String createTable = 	"CREATE TABLE metropolises (\r\n" + 
											"    metropolis CHAR(64),\r\n" + 
											"    continent CHAR(64),\r\n" + 
											"    population BIGINT\r\n" + 
											");";
	private  String insertMets = 	"INSERT INTO metropolises VALUES\r\n" + 
										"	(\"Mumbai\",\"Asia\",20400000),\r\n" + 
										"        (\"New York\",\"North America\",21295000),\r\n" + 
										"	(\"San Francisco\",\"North America\",5780000),\r\n" + 
										"	(\"London\",\"Europe\",8580000),\r\n" + 
										"	(\"Rome\",\"Europe\",2715000),\r\n" + 
										"	(\"Melbourne\",\"Australia\",3900000),\r\n" + 
										"	(\"San Jose\",\"North America\",7354555),\r\n" + 
										"	(\"Rostov-on-Don\",\"Europe\",1052000);";
	public connectToServer() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
		stmt = connect.createStatement();
		stmt.executeQuery("USE " + database);
		stmt.executeUpdate("DROP TABLE IF EXISTS metropolises;");
		stmt.executeUpdate(createTable);
		stmt.executeUpdate(insertMets);
	}
	
	
	public Statement getState() {
		return stmt;
	}
}
