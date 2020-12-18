

package model.dao.mysql;

import model.dao.AbstractDaoFactory;
import model.dao.BibliothequeDao;
import model.dao.UserDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.dao.LivreDao;

public class MySqlDaoFactory extends AbstractDaoFactory {

    private static MySqlDaoFactory instance;

    private MySqlDaoFactory() {
    }


    public static MySqlDaoFactory getInstance() {
        if (instance == null) {
            instance = new MySqlDaoFactory();
        }
        return instance;
    }

    public Connection getConnection(){
        Connection c = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); // initialise le driver jdbc pour DriverManager
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "");
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) { // gestion erreur pour le Class.forName()
            e.printStackTrace();
        }

        return c;
    }


    public static void closeAll(ResultSet rs, Statement ps, Connection c){ // ferme toutes les connections
        try{
            if(rs != null)
                rs.close();
        }
        catch(SQLException e){}

        try{
            if(ps != null)
                ps.close();
        }
        catch(SQLException e){}

        try{
            if(c != null)
                c.close();
        }
        catch(SQLException e){}
    }


     @Override
    public UserDao createUserDao() {
        return MySqlUserDao.getInstance();
    }

    @Override
    public BibliothequeDao createBibliothequeDao() {
         return MySqlBibliothequeDao.getInstance();
    }

    @Override
    public LivreDao createLivreDao() {
         return MySqlLivreDao.getInstance();
    }
}
