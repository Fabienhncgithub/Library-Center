/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Avis;
import model.Bibliotheque;
import model.Exemplaire;
import model.dao.LivreDao;
import model.Livre;
import model.Location;
import model.Role;
import model.User;

/**
 *
 * @author Fabien
 */
public class MySqlLivreDao implements LivreDao {

    private static MySqlLivreDao instance;

    public MySqlLivreDao() {
    }

    static {
        instance = new MySqlLivreDao();
    }

    public static MySqlLivreDao getInstance() {
        return instance;
    }

    @Override
    public Livre getLivre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Livre> getAllLivre() {
        List<Livre> listForm = new ArrayList<>();
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            String sql = "SELECT idLivre, titre, editeur, page, type FROM livre";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("editeur"), rs.getInt("page"));
                listForm.add(livre);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlLivreDAO, method  List<Livre> getAllLivre() : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listForm;
    }

    @Override
    public List<Livre> getAllLocation(Bibliotheque bibliotheque, User user) {
        List<Livre> listForm = new ArrayList<>();
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        c = MySqlDaoFactory.getInstance().getConnection();
        String sql = "SELECT livre.idLivre, livre.titre ,livre.editeur, livre.page from livre join exemplaire on livre.idLivre = exemplaire.idLivre join livrebiliotheque on exemplaire.idExemplaire  = livrebiliotheque.idExemplaire join location on exemplaire.idExemplaire = location.idExemplaire where livrebiliotheque.idBibliotheque = ? and location.idUser = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, bibliotheque.getIdBibliotheque());
            ps.setInt(2, user.getIdUser());
            rs = ps.executeQuery();
            while (rs.next()) {
                Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("editeur"), rs.getInt("page"));
                listForm.add(livre);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlLivreDAO, method getLivreList(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listForm;
    }

    @Override
    public Livre getLivreByNom(String titreLivreAvis) {
        Livre livre = new Livre();
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        String sql = "SELECT idLivre, titre, editeur, page FROM livre where titre = ? ";
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, titreLivreAvis);
            rs = ps.executeQuery();
            if (rs.next()) {
                livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("editeur"), rs.getInt("page"));
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlLivreDAO, method getLivreByNom(String titreLivreAvis): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return livre;
    }

    @Override
    public void insertAvis(Avis avis) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
      

        String sql = "INSERT INTO `avis` (`idLivre`, `idUser`, `commentaire`, `note`) VALUES (?, ?, ?, ?)";
        String sql2 = "UPDATE livre SET livre.noteTotal =(SELECT AVG(avis.note) FROM avis WHERE avis.idLivre = ?) WHERE livre.idLivre = ? ";
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, avis.getIdLivre());
            ps.setInt(2, avis.getUser().getIdUser());
            ps.setString(3, avis.getCommentaire());
            ps.setDouble(4, avis.getNote());
            ps.executeUpdate();

            ps = c.prepareStatement(sql2);
            ps.setInt(1, avis.getIdLivre());
            ps.setInt(2, avis.getIdLivre());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDAO, method insertAvis(Livre livre, User user, String commentaire, int note) : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
    }

    @Override
    public Exemplaire getExemplaireById(int idExemplaireRendu) {
        Exemplaire exemplaire = null;
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

//        String sql = "SELECT exemplaire.idExemplaire, exemplaire.idLivre, exemplaire.type, exemplaire.disponible, exemplaire.rendu  FROM exemplaire where exemplaire.idExemplaire = ? ";
//        try {
//            ps = c.prepareStatement(sql);
//            ps.setInt(1, idExemplaireRendu);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                
//                exemplaire = new Exemplaire(rs.getInt("idExemplaire"), rs.getString("type"),
//                        new Livre(rs.getInt("")));
//           
//            }
//        } catch (SQLException sqle) {
//            System.err.println("MySqlLivreDAO, method getLivreByNom(String titreLivreAvis): \n" + sqle.getMessage());
        //    }
        //finally {
        //      MySqlDaoFactory.closeAll(rs, ps, c);
        //  }
        return exemplaire;
    }

    @Override
    public Location getLocationById(int idLocation) {
        Location location = new Location();
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        String sql = "SELECT location.idLocation, location.idExemplaire, location.idUser, location.dateLocation, exemplaire.idLivre, exemplaire.type, livre.idLivre, livre.prixAchat, livre.titre, livre.auteur, livre.editeur, livre.page, livre.noteTotal,exemplaire.disponible, exemplaire.rendu,exemplaire.verifier FROM location JOIN exemplaire ON location.idExemplaire = exemplaire.idExemplaire JOIN livre ON exemplaire.idLivre = livre.idLivre  WHERE location.idLocation = ? ";
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idLocation);
            rs = ps.executeQuery();
            if (rs.next()) {
                location = new Location(rs.getInt("idLocation"),
                        new Exemplaire(rs.getInt("idExemplaire"), rs.getString("type"),
                                new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"), rs.getString("editeur"), rs.getInt("page"), rs.getInt("prixAchat"), rs.getDouble("noteTotal")),
                                rs.getBoolean("disponible"), rs.getBoolean("rendu"), rs.getBoolean("verifier")),
                        rs.getInt("idUser"), rs.getDate("dateLocation"));
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlLivreDAO, method getLocationById(int idLocation): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return location;
    }

    @Override
    public Livre getLivreById(int idLivre) {
        Livre livre = new Livre();
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        String sql = "SELECT idLivre, titre, editeur, page FROM livre where idLivre = ? ";
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idLivre);
            rs = ps.executeQuery();
            if (rs.next()) {
                livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("editeur"), rs.getInt("page"));
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlLivreDAO, method getLivreById(int idLivre): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return livre;
    }

    @Override
    public List<Avis> getAvisByIdUser(User user) {
        List<Avis> listAvis = new ArrayList<>();
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        String sql = "SELECT avis.idLivre, avis.idUser, avis.commentaire, avis.note, `user`.idUser, `user`.nom, `user`.prenom, `user`.email,`user`.password, role.idRole, role.nomRole, user.adresse, user.amende FROM avis JOIN `user` ON `user`.idUser = avis.idUser JOIN role ON `user`.role = role.idRole WHERE avis.idUser  = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, user.getIdUser());
            rs = ps.executeQuery();

            while (rs.next()) {
                Avis avis = new Avis(rs.getInt(1),
                        new User(rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                                new Role(rs.getInt(10), rs.getString(11)),
                                rs.getString(12), rs.getFloat(13)),
                        rs.getString(3), rs.getInt(4));
                listAvis.add(avis);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlLivreDAO, method getLivreById(int idLivre): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listAvis;

    }

    @Override
    public boolean getAvisByIdUSerIdLivreSelected(User user, Livre livre) {
        boolean result = false;
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        String sql = "SELECT idLivre, idUser FROM avis WHERE idUser = ? AND idLivre = ?";

        try {

            ps = c.prepareStatement(sql);
            ps.setInt(1, user.getIdUser());
            ps.setInt(2, livre.getIdLivre());
            rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlLivreDAO, method  List<Livre> getAllLivre() : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;
    }

    @Override
    public void registerPage(int pageSelect, int idLocation) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        String sql = "UPDATE location SET location.pageSelect = ? WHERE location.idLocation = ? ";

        try {

            ps = c.prepareStatement(sql);
            ps.setInt(1, pageSelect);
            ps.setInt(2, idLocation);
            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.err.println("MySqlLivreDAO, method  registerPage(int pageSelect, Livre livre): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
    }

}
