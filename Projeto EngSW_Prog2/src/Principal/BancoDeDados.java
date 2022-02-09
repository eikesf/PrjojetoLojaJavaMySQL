package Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BancoDeDados {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultset = null;
	
	public void conectar()
	{
		String servidor = "jdbc:mysql://localhost:3306/projeto_loja?useTimezone=true&serverTimezone=GMT";
		String usuario = "root";
		String senha = "123456";
		String driver = "com.mysql.cj.jdbc.Driver";
		try
		{
			Class.forName(driver);
			this.connection = DriverManager.getConnection(servidor,usuario,senha);
			this.statement = this.connection.createStatement();
		} 
		catch (Exception e) 
		{
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public boolean estaConectado()
	{
		if(this.connection != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void desconectar()
	{
		try
		{
			this.connection.close();
			System.out.println("Desconectado");
		} catch (Exception e)
		{
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
}

