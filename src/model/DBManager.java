package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
	
	public Connection getConnection() throws SQLException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return DriverManager.getConnection("jdbc:mysql://localhost/Einkauf?user=root&password=");
	}
	
	public void releaseConnection(Connection con) throws SQLException {
		con.close();
	}
	
	static final String deleteProduktSQL = "DELETE FROM Produkt WHERE id = ?";
	
	/**
	 * Aufgabe 2.1
	 * @param shopId
	 */
	public void loescheProdukte(int shopId) throws SQLException {
		
		PreparedStatement pstmDeleteProdukte = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmDeleteProdukte = con.prepareStatement(deleteProduktSQL);
			pstmDeleteProdukte.setInt(1, shopId);
			pstmDeleteProdukte.execute();
		}
		finally {
			if(pstmDeleteProdukte != null) pstmDeleteProdukte.close();
			if(con != null) releaseConnection(con);
		}
	}
	
	static final String holeProdukteSQL = "SELECT p.id, p.name, p.amount, p.shop_id, FROM Produkt p WHERE name LIKE ?";
	
	/**
	 * Aufgabe 2.1
	 * Die Produkte, die im Namen den String s beinhalten sollen aus der Datenbank gelesen und in einer Liste gespeichert werden.
	 * @param s
	 * @return
	 */
	public List<Produkt> holeProdukte(String s) throws SQLException {
		
		PreparedStatement pstmGetProdukte = null;
		List<Produkt> produkte = new ArrayList<Produkt>();
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmGetProdukte = con.prepareStatement(holeProdukteSQL);
			pstmGetProdukte.setString(1, "%" + s + "%");
			rs = pstmGetProdukte.executeQuery();
			while(rs.next()) {
				produkte.add(new Produkt(rs.getInt(1), rs.getString(2), rs.getString(3), holeShop(rs.getInt(4))));
			}
		}
		finally {
			if(rs != null) rs.close();
			if(pstmGetProdukte != null) pstmGetProdukte.close();
			if(con != null) releaseConnection(con);
		}
		return produkte;
	}
	
	static final String holeProdukteSQL2 = "SELECT id, name, amount, shop_id FROM Produkt";
	
	/**
	 * Aufgabe 2.1
	 * Die Produkte, die im Namen den String s beinhalten sollen aus der Datenbank gelesen und in einer Liste gespeichert werden.
	 * @param s
	 * @return
	 */
	public List<Produkt> holeProdukte() throws SQLException {
		
		PreparedStatement pstmGetProdukte = null;
		List<Produkt> produkte = new ArrayList<Produkt>();
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmGetProdukte = con.prepareStatement(holeProdukteSQL2);
			rs = pstmGetProdukte.executeQuery();
			while(rs.next()) {
				produkte.add(new Produkt(rs.getInt(1), rs.getString(2), rs.getString(3), holeShop(rs.getInt(4))));
			}
			return produkte;
		}
		finally {
			if(rs != null) rs.close();
			if(pstmGetProdukte != null) pstmGetProdukte.close();
			if(con != null) releaseConnection(con);
		}
	}
	
	static final String holeShopSQL = "SELECT name FROM Shop WHERE id = ?";
	
	public Shop holeShop(int shopId) throws SQLException {
		PreparedStatement pstmHoleShop = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmHoleShop = con.prepareStatement(holeShopSQL);
			pstmHoleShop.setInt(1, shopId);
			rs = pstmHoleShop.executeQuery();
			if(rs.next()) {
				return new Shop(shopId, rs.getString(1));
			}
			return null;
		}
		finally {
			if(pstmHoleShop != null) pstmHoleShop.close();
			if(rs != null) rs.close();
			if(con != null) releaseConnection(con);
		}
	}
	
	static final String holeShopsSQL = "SELECT s.id, s.name FROM Produkt p JOIN Shop s ON(p.shop_id=s.id) WHERE p.name = ?";
	
	public ArrayList<Shop> holeShops(String produktName) throws SQLException {
		PreparedStatement pstmHoleShops = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmHoleShops = con.prepareStatement(holeShopsSQL);
			pstmHoleShops.setString(1, produktName);
			rs = pstmHoleShops.executeQuery();
			ArrayList<Shop> shops = new ArrayList<>();
			while(rs.next()) {
				shops.add(new Shop(rs.getInt(1), rs.getString(2)));
			}
			return shops;
		}
		finally {
			if(rs != null) rs.close();
			if(pstmHoleShops != null) pstmHoleShops.close();
			if(con != null) releaseConnection(con);
		}
	}
	
	static final String holeShopsSQL2 = "SELECT s.id, s.name FROM Shop s";
	
	public ArrayList<Shop> holeShops() throws SQLException {
		PreparedStatement pstmHoleShops = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmHoleShops = con.prepareStatement(holeShopsSQL2);
			rs = pstmHoleShops.executeQuery();
			ArrayList<Shop> shops = new ArrayList<>();
			while(rs.next()) {
				shops.add(new Shop(rs.getInt(1), rs.getString(2)));
			}
			return shops;
		}
		finally {
			if(rs != null) rs.close();
			if(pstmHoleShops != null) pstmHoleShops.close();
			if(con != null) releaseConnection(con);
		}
	}
	
	/** Laut Aufgabenstellung 2.3 (bereits vorhanden) */
	static final String holeProduktSQL = "SELECT id, name, amount, shop_id FROM Produkt WHERE id = ?";
	
	/**
	 * Laut Aufgabenstellung 2.3 (bereits vorhanden)
	 * @param id ID des Produkts
	 * @return Produkt-Objekt
	 */
	public Produkt holeProdukt(int id) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstm = con.prepareStatement(holeProduktSQL);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if(rs.next()) {
				return new Produkt(rs.getInt(1), rs.getString(2), rs.getString(3), holeShop(rs.getInt(4)));
			}
			else return null;
		}
		finally {
			if(pstm != null) pstm.close();
			if(rs != null) rs.close();
			if(con != null) releaseConnection(con);
		}
	}
	
	static final String holeProduktePerShopSQL = "SELECT s.name, p.amount FROM Produkt p JOIN Shop s ON(s.id=p.shop_id) WHERE p.name = ?";
	
	public ArrayList<String[]> holeProduktePerShop(String produktName) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstm = con.prepareStatement(holeProduktePerShopSQL);
			pstm.setString(1, produktName);
			rs = pstm.executeQuery();
			ArrayList<String[]> produktePerShop = new ArrayList<>();
			while(rs.next()) {
				produktePerShop.add(new String[] {rs.getString(1), rs.getString(2)});
			}
			return produktePerShop;
		}
		finally {
			if(rs != null) rs.close();
			if(pstm != null) pstm.close();
			if(con != null) releaseConnection(con);
		}
	}
}