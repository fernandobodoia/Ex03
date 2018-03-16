package Factory;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	// Obtém conexão com o banco de dados
	public static Connection obtemConexao() throws SQLException {
		System.out.println("COONECTOOOOO");
		return DriverManager
				.getConnection("jdbc:mysql://localhost/Aula02?user=Vanessa&password=12345");
		
	}

}
