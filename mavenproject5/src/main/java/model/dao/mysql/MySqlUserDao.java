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
import model.Client;
import model.Exemplaire;
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
                c = MySqlDaoFactory.getInstance().getConnection();
        try {
    
            String sql = "SELECT user.idUser,user.nom, user.prenom, user.email, user.password, role.idRole, role.nomRole, user.adresse, user.amende from user join role on role.idRole = user.role join inscription on user.idUser = inscription.idUser where user.email = ? and user.password  = ? and inscription.idBibliotheque = ?";

            ps = c.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setInt(3, bibliotheque.getIdBibliotheque());
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        new Role(rs.getInt(6), rs.getString(7)),
                        rs.getString(8),rs.getFloat(9));
                //         user = new User(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"),
                //               new Role(rs.getInt("idRole"), rs.getString("nomRole")),
                //              rs.getString("adresse"));
            }
        } catch (SQLException sqle) {
            System.out.println("ERROR this USER or PASSWROD DOES NOT WORK..." + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return user;
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

        String sql = "SELECT idUser FROM user where user.email = ?";

        String sql1 = "INSERT INTO user(nom, prenom, email, password,  role, adresse)  VALUES (?, ?, ?, ?, ?, ?)";

        String sql2 = "INSERT INTO inscription(idUser, idBibliotheque, cotisation) VALUES (?,?,?)";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql1);
            ps.setString(1, user.getEmail());
            rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
                c = MySqlDaoFactory.getInstance().getConnection();
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
                ps.setInt(3, 0);
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
    public void validationCotisation(User user, Bibliotheque bibliotheque) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  inscription SET cotisation = ? ,dateCotisation = CURRENT_DATE \n"
                + "where idUser = ? and idBibliotheque=?  ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setInt(2, user.getIdUser());
            ps.setInt(3, bibliotheque.getIdBibliotheque());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDAO, method addNewUser(String login, String password): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }

    }

    @Override
    public List<User> getAllManager() {
        List<User> listManager = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection c = MySqlDaoFactory.getInstance().getConnection();
        String sql = "SELECT idUser, nom, prenom, email, password, role, adresse, amende FROM user where role = ?  ";
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, 3);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        new Role(rs.getInt(6), rs.getString(7)),
                        rs.getString(8));
                listManager.add(user);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlAnnuaireDAO, method getUsersList(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listManager;

    }

    @Override
    public void addBook(Exemplaire exemplaire, Bibliotheque bibliotheque) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT livre.titre, livre.idLivre FROM livre where livre.titre = ?";
        String sql1 = "INSERT INTO livre(prixAchat, titre, auteur, editeur,  page)  VALUES (?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO exemplaire(idLivre, type) VALUES (?,?)";
        String sql3 = "INSERT INTO `livrebiliotheque`(`idBibliotheque`, `idExemplaire`) VALUES (?,?)";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, exemplaire.getLivre().getTitre());
            rs = ps.executeQuery();

            if (!rs.next()) {
                ps = c.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, exemplaire.getLivre().getPrixAchat());
                ps.setString(2, exemplaire.getLivre().getTitre());
                ps.setString(3, exemplaire.getLivre().getAuteur());
                ps.setString(4, exemplaire.getLivre().getEditeur());
                ps.setInt(5, exemplaire.getLivre().getPage());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                rs.next();

                ps = c.prepareStatement(sql2, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, rs.getInt(1));
                ps.setString(2, exemplaire.getType());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                rs.next();

                ps = c.prepareStatement(sql3);
                ps.setInt(1, bibliotheque.getIdBibliotheque());
                ps.setInt(2, rs.getInt(1));
                ps.executeUpdate();

            } else {
                ps = c.prepareStatement(sql2, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, rs.getInt("idLivre"));
                ps.setString(2, exemplaire.getType());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                rs.next();

                ps = c.prepareStatement(sql3);
                ps.setInt(1, bibliotheque.getIdBibliotheque());
                ps.setInt(2, rs.getInt(1));
                ps.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDao, method addBook(Livre livre, int idBibliotheque): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }

    }

    @Override
    public void addManager(User user
    ) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        int role = 3;

        String sql = "INSERT INTO `user` (`nom`, `prenom`, `email`, `password`, `role`, `adresse`) VALUES (?, ?, ?, ?, ?, ?);";
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRole().getIdRole());
            ps.setString(6, user.getAdresse());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDAO, method addManager(User user): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
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

}
