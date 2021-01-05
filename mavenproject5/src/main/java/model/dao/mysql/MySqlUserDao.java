/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mysql;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Bibliotheque;
import model.Livre;
import model.dao.UserDao;
import model.User;
import model.Role;

/**
 *
 * @author Fabien
 */
public class MySqlUserDao implements UserDao {

    private static MySqlUserDao instance;

    public static MySqlUserDao getInstance() {
        if (instance == null) {
            instance = new MySqlUserDao();
        }
        return instance;
    }

    private MySqlUserDao() {
    }

    @Override
    public User authentification(User user, Bibliotheque bibliotheque) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        User result = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT user.idUser,user.nom, user.prenom, user.email, user.password, role.idRole, role.nomRole, user.adresse, user.amende from user join role on role.idRole = user.role join inscription on user.idUser = inscription.idUser where user.email = ?  and inscription.idBibliotheque = ?";
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setInt(2, bibliotheque.getIdBibliotheque());
            rs = ps.executeQuery();
            if (rs.next()) {
                result = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        new Role(rs.getInt(6), rs.getString(7)),
                        rs.getString(8), rs.getFloat(9));

            }
        } catch (SQLException sqle) {
            System.out.println("ERROR this USER or PASSWROD DOES NOT WORK..." + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;
    }

    public void ajoutUser(String login, String password) {
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO user(login,password) VALUES (?,?)";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDAO, method addNewUser(String login, String password): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
    }

    @Override
    public boolean signIn(User user, Bibliotheque bibliotheque) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        boolean result = false;

        String sql = "SELECT idUser FROM user WHERE user.email = ?";

        String sql1 = "INSERT INTO user(nom, prenom, email, password,  role, adresse)  VALUES (?, ?, ?, ?, ?, ?)";

        String sql2 = "INSERT INTO inscription(idUser, idBibliotheque) VALUES (?,?)";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            rs = ps.executeQuery();
            if (!rs.next()) {
                result = true;
                ps = c.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getNom());
                ps.setString(2, user.getPrenom());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                ps.setInt(5, 1);
                ps.setString(6, user.getAdresse());
                ps.executeUpdate();

                rs = ps.getGeneratedKeys();
                ps = c.prepareStatement(sql2);
                rs.next();
                ps.setInt(1, rs.getInt(1));
                ps.setInt(2, bibliotheque.getIdBibliotheque());
                ps.executeUpdate();
            }

        } catch (SQLException sqle) {
            System.err.println("MySqlUserDao, method signIn(User user, int idBibliotheque): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;
    }

    @Override
    public List<User> getAllUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getStatutCotisation(User user, Bibliotheque bibliotheque) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        boolean result = false;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT cotisation from inscription where idUser = ? and idBibliotheque = ? ";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, user.getIdUser());
            ps.setInt(2, bibliotheque.getIdBibliotheque());
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getBoolean("cotisation") != true) {
                    result = true;
                } else {
                    result = false;
                }
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDao, method getStatutCotisation(User user) : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;

    }

    @Override
    public boolean addManager(User user) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        boolean result = false;

        String sql = "SELECT user.email FROM `user` WHERE user.email = ?";
        String sql1 = "INSERT INTO `user` (`nom`, `prenom`, `email`, `password`, `role`, `adresse`) VALUES (?, ?, ?, ?, ?, ?);";

        try {

            ps = c.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            rs = ps.executeQuery();
            if (!rs.next()) {
                result = true;
                ps = c.prepareStatement(sql1);
                ps.setString(1, user.getNom());
                ps.setString(2, user.getPrenom());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                ps.setInt(5, user.getRole().getIdRole());
                ps.setString(6, user.getAdresse());
                ps.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDAO, method addManager(User user): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;
    }

    @Override
    public void insertAvis(Livre livre, User user,
            String commentaire, double note
    ) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        int role = 3;

        String sql = "INSERT INTO `avis` (`idLivre`, `idUser`, `commentaire`, `note`) VALUES (?, ?, ?, ?)";
        String sql2 = "UPDATE livre SET livre.noteTotal =(SELECT AVG(avis.note) FROM avis WHERE avis.idLivre = ?) WHERE livre.idLivre = ? ";
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, livre.getIdLivre());
            ps.setInt(2, user.getIdUser());
            ps.setString(3, commentaire);
            ps.setDouble(4, note);
            ps.executeUpdate();

            ps = c.prepareStatement(sql2);
            ps.setInt(1, livre.getIdLivre());
            ps.setInt(2, livre.getIdLivre());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDAO, method insertAvis(Livre livre, User user, String commentaire, int note) : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
    }

    @Override
    public Role getRoleByid(int idRole) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Role role = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        try {

            String sql = "SELECT role.idRole, role.nomRole FROM role";

            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                role = new Role(rs.getInt("idRole"), rs.getString("nomRole"));

            }
        } catch (SQLException sqle) {
            System.out.println("MySqlUserDAO, method List<Role> getAllRole() : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return role;

    }

    @Override
    public boolean updateProfil(User user) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        boolean result = false;

        String sql1 = "SELECT user.idUser FROM `user` where user.email = ? and user.idUser NOT IN (SELECT user.idUser FROM user WHERE user.idUser = ?) ";
        String sql2 = "UPDATE  user SET nom = ? ,prenom = ?, email = ?, password = ?,adresse = ?  where idUser = ? ";

        try {

            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql1);
            ps.setString(1, user.getEmail());
            ps.setInt(2, user.getIdUser());
            rs = ps.executeQuery();

            if (!rs.next()) {
                result = true;
                ps = c.prepareStatement(sql2);
                ps.setString(1, user.getNom());
                ps.setString(2, user.getPrenom());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                ps.setString(5, user.getAdresse());
                ps.setInt(6, user.getIdUser());
                ps.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.out.println("MySqlUserDAO, method updateProfil(User user) : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;
    }

    @Override
    public void payerAmende(User user) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "UPDATE user SET user.amende = 0  WHERE user.idUser = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, user.getIdUser());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.err.println("MySqlLivreDAO, method payerAmende(User user): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
    }

    @Override
    public void insertQuestion(String question, User user) {
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        String sql = "INSERT INTO faq(idUserQuestion, question) VALUES (?,?)";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, user.getIdUser());
            ps.setString(2, question);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDAO, method addNewUser(String name,String role, String password): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
    }

    @Override
    public List<Role> getAllRole() {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Role role = null;
        List<Role> listRole = new ArrayList();
        c = MySqlDaoFactory.getInstance().getConnection();
        try {

            String sql = "SELECT role.idRole, role.nomRole FROM role";

            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                role = new Role(rs.getInt("idRole"), rs.getString("nomRole"));
                listRole.add(role);
            }
        } catch (SQLException sqle) {
            System.out.println("MySqlUserDAO, method List<Role> getAllRole() : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listRole;
    }

    @Override
    public boolean addProfil(User user, Bibliotheque bibliotheque) {
           Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        boolean result = false;

        String sql = "SELECT idUser FROM user WHERE user.email = ?";

        String sql1 = "INSERT INTO user(nom, prenom, email, password,  role, adresse)  VALUES (?, ?, ?, ?, ?, ?)";

        String sql2 = "INSERT INTO inscription(idUser, idBibliotheque) VALUES (?,?)";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            rs = ps.executeQuery();
            if (!rs.next()) {
                result = true;
                ps = c.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getNom());
                ps.setString(2, user.getPrenom());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                ps.setInt(5, user.getRole().getIdRole());
                ps.setString(6, user.getAdresse());
                ps.executeUpdate();

                rs = ps.getGeneratedKeys();
                ps = c.prepareStatement(sql2);
                rs.next();
                ps.setInt(1, rs.getInt(1));
                ps.setInt(2, bibliotheque.getIdBibliotheque());
                ps.executeUpdate();
            }

        } catch (SQLException sqle) {
            System.err.println("MySqlUserDao, method signIn(User user, int idBibliotheque): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;
    }
}
