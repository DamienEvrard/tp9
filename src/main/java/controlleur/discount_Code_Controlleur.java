/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import DAO.discountDAO;
import Entitie.DiscountEntity;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author damie
 */

@WebServlet(name = "discount", urlPatterns = {"/discount"})
public class discount_Code_Controlleur extends HttpServlet {

	protected void processGetRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String jspView;
                
                DataSource myDataSource = DataSourceFactory.getDataSource();
                discountDAO dao = new discountDAO(myDataSource);
		
                ArrayList<DiscountEntity> listeDiscount =dao.findDiscountCode();
                request.setAttribute("Discount", listeDiscount);
                
                jspView = "afficheDiscount.jsp";
		
                
		request.getRequestDispatcher("view/" + jspView).forward(request, response);
	}
        
           
        protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {

                
                DataSource myDataSource = DataSourceFactory.getDataSource();
                discountDAO dao = new discountDAO(myDataSource);
                
                String bouton =request.getParameter("action").toString();
                if(bouton.equals("DEL")){
                    int ok=dao.deleteDiscountCode(request.getParameter("DC").toString());
                    String message;
                    if(ok==1){
                        message="Le code "+request.getParameter("DC").toString()+" a ete supprime";
                        request.setAttribute("message", message);
                    }else{
                       message="Le code "+request.getParameter("DC").toString()+" ne peut pas etre supprime"; 
                       request.setAttribute("message", message);
                    }
                }else{
                    String DC=request.getParameter("code").toString();
                    Float rate =Float.parseFloat(request.getParameter("taux").toString());
                    int ok=dao.addDiscountCode(DC, rate);
                    String message;
                    if(ok==1){
                        message="Le code "+request.getParameter("code").toString()+" a ete ajoute";
                        request.setAttribute("message", message);
                    }else{
                       message="Le code "+request.getParameter("code").toString()+" n'a pas pu etre ajoute"; 
                       request.setAttribute("message", message);
                    }
                }
                
                String jspView;
                
                
		
                ArrayList<DiscountEntity> listeDiscount =dao.findDiscountCode();
                request.setAttribute("Discount", listeDiscount);
                
                jspView = "afficheDiscount.jsp";
		
                
		request.getRequestDispatcher("view/" + jspView).forward(request, response);
	}

        
        
        
        
        
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processGetRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
            try {
                processPostRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(discount_Code_Controlleur.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

    
}
