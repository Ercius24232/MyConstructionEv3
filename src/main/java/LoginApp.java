import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Introduce tu nombre de usuario: ");
		String username = scanner.nextLine();

		System.out.print("Introduce tu contraseña: ");
		String password = scanner.nextLine();
		
		if(validateLogin(username, password)) {
			System.out.println("Proceso exitoso, Bienvenido.");
		} else {
			System.out.println("Credenciales Incorrectas.");
		}
		scanner.close();
	}
	
	private static boolean validateLogin(String username, String password) {
		String query = "select * from usuario where nombre_usuario = ? and contraseña = ?";
		
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement stnt = conn.prepareStatement(query)) {
			
			stnt.setString(1, username);
			stnt.setString(2, password);
			
			ResultSet rs = stnt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println("Error al conectar con la base de datos.");
			e.printStackTrace();
			return false;
		}
	}
}
