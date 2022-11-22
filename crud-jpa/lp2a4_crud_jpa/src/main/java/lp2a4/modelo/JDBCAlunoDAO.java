package lp2a4.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JDBCAlunoDAO implements AlunoDAO{
	public static Connection connectToMySQL() {		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/AlunoDBLP2A4"
					, "rodrigoroot"
					, "123456");
			return connection;
		} catch (SQLException e) {
			System.out.println("Falha ao se Conectar ao banco de dados: " + e);
			return null;
		}
		
	}
	
	@Override
	public boolean create(AlunoPOJO aluno) {
		String query = "INSERT INTO Aluno (matricula, nome, endereco, data_inicio, data_termino) VALUES (?,?,?,?,?)";
		PreparedStatement statement;
		try {
			statement = JDBCAlunoDAO.connectToMySQL().prepareStatement(query);
			statement.setString(1, aluno.getMatricula());
			statement.setString(2, aluno.getNome());
			statement.setString(3, aluno.getEndereco());
			statement.setString(4, aluno.getDataIngresso().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
			statement.setString(5, aluno.getDataConclusao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
			statement.execute();
			statement.close();
			JDBCAlunoDAO.connectToMySQL().close();
			return true;
		} catch (SQLException e) {
			System.out.println("Falha ao fazer a inserção: " + e);
			return false;
		}
	}

	@Override
	public AlunoPOJO retrieve(String id) {
		try {
			AlunoPOJO aluno = new AlunoPOJO();
			PreparedStatement statement = connectToMySQL().prepareStatement("SELECT matricula,nome,endereco,data_inicio,data_termino FROM Aluno WHERE matricula = ?;");
			statement.setString(1, id);
			ResultSet alunoFound = statement.executeQuery();
			if (alunoFound.next()) {
				String nome = alunoFound.getString("nome");
				String endereco = alunoFound.getString("endereco");
				String dtIngresso = alunoFound.getString("data_inicio");
				String dtConclusao = alunoFound.getString("data_termino");
				aluno.setMatricula(id);
				aluno.setNome(nome);
				aluno.setEndereco(endereco);
				aluno.setDataIngresso(LocalDate.parse(dtIngresso, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				aluno.setDataConclusao(LocalDate.parse(dtConclusao, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			}
			
			statement.close();
			JDBCAlunoDAO.connectToMySQL().close();
			return aluno;
		} catch(Exception e) {
			System.out.println("Falha ao consultar Registros." + e);
			return null;
		}
		
	}

	@Override
	public boolean update(AlunoPOJO aluno) {
		String query = "UPDATE Aluno SET matricula = ?, nome = ?, endereco = ?, data_inicio = ?, data_termino = ? WHERE matricula = ?;";
		
		PreparedStatement statement;
		
		try {
			statement = JDBCAlunoDAO.connectToMySQL().prepareStatement(query);
			statement.setString(1, aluno.getMatricula());
			statement.setString(2, aluno.getNome());
			statement.setString(3, aluno.getEndereco());
			statement.setString(4, aluno.getDataIngresso().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
			statement.setString(5, aluno.getDataConclusao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
			statement.setString(6, aluno.getMatricula());
			statement.execute();
			statement.close();
			JDBCAlunoDAO.connectToMySQL().close();
			return true;
		} catch (SQLException e) {
			System.out.println("Falha ao realizar a atualização: " + e);
			return false;
		}
	}

	@Override
	public boolean delete(String matricula) {
		try {
			PreparedStatement statement = JDBCAlunoDAO.connectToMySQL().prepareStatement("DELETE FROM Aluno WHERE matricula = ?;");
			statement.setString(1, matricula);
			statement.execute();
			statement.close();
			JDBCAlunoDAO.connectToMySQL().close();
			return true;
			
		} catch (SQLException e) {
			System.out.println("Falha ao realizar a exclusão: " + e);
			return false;
		}
		
	}
}