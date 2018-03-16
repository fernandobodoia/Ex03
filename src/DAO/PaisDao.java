package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Factory.ConnectionFactory;
import model.Pais;


public class PaisDao {
	//Cria pais no banco
	public void criar(Pais pais ) {
		String sqlInsert = "INSERT INTO pais(nome,populacao,area) VALUES (?, ?, ?)";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
		PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
		stm.setString(1, pais.getNome());
		stm.setLong(2, pais.getPopulacao());
		stm.setDouble(3,pais. getArea());
		stm.execute();
		
		String sqlQuery = "SELECT LAST_INSERT_ID()";
		try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
				ResultSet rs = stm2.executeQuery();) {
				if(rs.next()){
					pais.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				System.out.println("TEste");
				e.printStackTrace();
			}
		} catch (SQLException e) {
			System.out.println("TEste2");
			e.printStackTrace();
			}
		}
		
	//Realizar Update no BD
	public void atualizar(Pais pais) {
		//aqui e necessario o ID pois para realizar o update precisa referenciar para algo
		String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3,pais. getArea());
			stm.setInt(4, pais.getId());
			stm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			}
		}
	
	//Realizar exclusao de dado no BD
	public void excluir(Pais pais) {
		String sqlDelete = "DELETE FROM pais WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			
			stm.setInt(1, pais.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
			}
		}
	
	//Mostrar dados importados do banco que foi criado
	public Pais carregar(int i) {
		Pais pais = new Pais();
		String sqlSelect = "SELECT nome, populacao, area FROM pais WHERE pais.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setInt(1, pais.getId());
			try (ResultSet rs = stm.executeQuery();) {
				//se existir retorno do result set, para isso usa o next()
				if (rs.next()) {
					pais.setNome(rs.getString("nome"));
					pais.setPopulacao(rs.getLong("populacao"));
					pais.setArea(rs.getDouble("area"));
				} else {
					pais.setId(-1);
					pais.setNome(null);
					pais.setPopulacao(0);
					pais.setArea(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
			}
		return pais;
		}
	
	
	public void paisHabitantes(Pais pais) {
		
		String sqlGet = "Select * from pais where populacao = (Select Max(populacao) from pais)";
		
		try (Connection conn = ConnectionFactory.obtemConexao()){
			PreparedStatement stm = conn.prepareStatement(sqlGet);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				//enviando para os metodos set o valor coletado da query
				pais.setNome(rs.getString("nome"));
				pais.setPopulacao(rs.getLong("populacao"));
				pais.setId(rs.getInt("id"));
				pais.setArea(rs.getDouble("area"));
			}else {
				System.out.println("Error Result Set");
			}
			
		}catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void paisMenorArea(Pais pais) {
		
		String sqlGet = "Select * from pais where area = (Select Min(area) from pais)";
		
		try (Connection conn = ConnectionFactory.obtemConexao()){
			PreparedStatement stm = conn.prepareStatement(sqlGet);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				//enviando para os metodos set o valor coletado da query
				pais.setNome(rs.getString("nome"));
				pais.setPopulacao(rs.getLong("populacao"));
				pais.setId(rs.getInt("id"));
				pais.setArea(rs.getDouble("area"));
			}else {
				System.out.println("Error Result Set");
			}
			
		}catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	
	public String[] paisVetPaises() {
		
		String sqlGet = "Select nome from pais order by nome";
		String[] array = new String[3];
		int cont = 0 ;
		
		try (Connection conn = ConnectionFactory.obtemConexao()){
			PreparedStatement stm = conn.prepareStatement(sqlGet);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next() && cont < 3 ) {
				//Adicionando itens no arraylist
				array[cont] = rs.getString("nome");
				cont ++;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return array;
	}
	
}
