/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entitie.DiscountEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author damie
 */
public class discountDAO {
    
    /**
    *
    * @param dataSource la source de données à utiliser
    */
    protected final DataSource myDataSource;

    public discountDAO(DataSource dataSource) {
	this.myDataSource = dataSource;
    }
    
    public ArrayList<DiscountEntity> findDiscountCode(){
		ArrayList<DiscountEntity> result = new ArrayList<DiscountEntity>();

		String sql = "SELECT * FROM DISCOUNT_CODE";
		try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
			PreparedStatement stmt = connection.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					String DC = rs.getString("DISCOUNT_CODE");
					float rate = rs.getFloat("RATE");
                                        DiscountEntity disc= new DiscountEntity(DC, rate);
					result.add(disc);
				}
			}
		}  catch (SQLException ex) {
                    
		}

		return result;
    }
    
    public int addDiscountCode(String DC, Float rate){
        String sql = "insert into DISCOUNT_CODE(DISCOUNT_CODE,RATE) values(?,?)";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
			PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, DC);
            stmt.setFloat(2, rate);
            
            return stmt.executeUpdate();
        }  catch (Exception ex) {
             return 0;       
        }
    }
    
    public int deleteDiscountCode(String DC){
        String sql = "DELETE FROM DISCOUNT_CODE WHERE DISCOUNT_CODE = ?";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
			PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, DC);
            return stmt.executeUpdate();
        }  catch (Exception ex) {
              return 0;      
	}
    }
    
}
