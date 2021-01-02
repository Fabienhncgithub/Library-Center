/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mysql;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Avis;
import model.Bibliotheque;
import model.dao.BibliothequeDao;
import model.Exemplaire;
import model.Faq;
import model.Livre;
import model.Location;
import model.Role;
import model.User;

//verifier le boolean rendu et le rajouter dans tot les SQL
public class MySqlBibliothequeDao implements BibliothequeDao {

    private static MySqlBibliothequeDao instance;

    public MySqlBibliothequeDao() {
    }

    static {
        instance = new MySqlBibliothequeDao();
    }

    public static MySqlBibliothequeDao getInstance() {
        return instance;
    }

    @Override
    public List<Bibliotheque> getAllBibliotheque() {
        List<Bibliotheque> listForm = new ArrayList<>();
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            String sql = "SELECT * FROM bibliotheque";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Bibliotheque bibliotheque = new Bibliotheque(rs.getString("nom"));
                bibliotheque.setIdBibliotheque(rs.getInt("idBibliotheque"));
                listForm.add(bibliotheque);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlBibliothequeDao, method getBibliothequeList(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listForm;
    }

    @Override
    public Bibliotheque getBibliotheque() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bibliotheque getBibliothèqueById(int id) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Bibliotheque bibliothèque = null;
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            String sql = "SELECT * FROM bibliotheque where idBibliotheque = ? ";
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                bibliothèque = new Bibliotheque(rs.getInt("idBibliotheque"), rs.getString("nom"), rs.getString("nom"), rs.getInt("idManager"));
            } else {
                System.out.println("Erreur de connection, l'utilisateur ou le mot de passe est incorrect");
            }
        } catch (SQLException sqle) {
            System.out.println("ERROR this BIBLI DOES NOT WORK..." + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return bibliothèque;
    }

    @Override
    public Bibliotheque verifyMember(int idUser, Bibliotheque bibliotheque) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Bibliotheque bibliothèque = null;
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            String sql = "SELECT * FROM bibliotheque join iduserbibli on bibliotheque.id = iduserbibli.idBibli where idBibli = ? and idUser =  ? ";
            ps = c.prepareStatement(sql);
            ps.setInt(1, bibliotheque.getIdBibliotheque());
            ps.setInt(2, idUser);
            rs = ps.executeQuery();
            if (rs.next()) {
                bibliothèque = new Bibliotheque(rs.getInt("id"), rs.getString("nom"));
            } else {
                System.out.println("Erreur de connection, l'utilisateur n'est pas inscrit dans cette bibliothèque");
            }
        } catch (SQLException sqle) {
            System.out.println("ERROR this BIBLI DOES NOT WORK..." + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return bibliothèque;
    }

    @Override
    public List<Exemplaire> exemplaireByType(int idlivre) {
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Exemplaire exemplaire = new Exemplaire();
        List<Exemplaire> listeExemplaire = new ArrayList();

        c = MySqlDaoFactory.getInstance().getConnection();
        String sql = "SELECT * FROM exemplaire where exemplaire.idlivre = ? groub by type";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(2, idlivre);
            rs = ps.executeQuery();
            while (rs.next()) {
                exemplaire = new Exemplaire(rs.getInt("idexemplaires"),
                        rs.getString("type"),
                        new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("editeur"), rs.getInt("page"), rs.getDouble("noteTotal")));

                listeExemplaire.add(exemplaire);
            }
        } catch (SQLException sqle) {
            System.err.println(" List<Exemplaire> exemplaireByType(int idlivre): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listeExemplaire;
    }

    @Override
    public boolean createBibliotheque(Bibliotheque bibliotheque, int idUser) {
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        boolean result = false;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT bibliotheque.idBibliotheque FROM bibliotheque WHERE bibliotheque.nom = ?";
        String sql1 = "INSERT INTO bibliotheque(nom, adresse,idManager) VALUES (?,?,?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, bibliotheque.getNom());
            rs = ps.executeQuery();
            if (!rs.next()) {
                result = true;
                ps = c.prepareStatement(sql1);
                ps.setString(1, bibliotheque.getNom());
                ps.setString(2, bibliotheque.getAdresse());
                ps.setInt(3, idUser);
                ps.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDAO, method addNewUser(String name,String role, String password): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;
    }

    @Override
    public List<Livre> getAllLivreByBibliotheque(Bibliotheque bibliotheque) {
        List<Livre> listForm = new ArrayList<>();
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT livre.idLivre, livre.titre ,livre.editeur, livre.page, livre.noteTotal from livre join exemplaire on livre.idLivre = exemplaire.idLivre join livrebiliotheque on exemplaire.idExemplaire  = livrebiliotheque.idExemplaire where livrebiliotheque.idBibliotheque = ? group by livre.idLivre";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, bibliotheque.getIdBibliotheque());
            rs = ps.executeQuery();
            while (rs.next()) {
                Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("editeur"), rs.getInt("page"), rs.getDouble("noteTotal"));
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
    public void location(int idexemplaire, int iduser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Exemplaire> getAllExemplaireByTypeByBibliotheque(int idLivre, Bibliotheque bibliotheque) {
        List<Exemplaire> listForm = new ArrayList<>();
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT exemplaire.idExemplaire, exemplaire.idLivre, exemplaire.type, livre.titre,livre.editeur, livre.page from exemplaire join livrebiliotheque on exemplaire.idExemplaire = livrebiliotheque.idExemplaire join livre on exemplaire.idLivre = livre.idLivre where exemplaire.idLivre = ? and idBibliotheque = ? GROUP BY type";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idLivre);
            ps.setInt(2, bibliotheque.getIdBibliotheque());
            rs = ps.executeQuery();
            while (rs.next()) {

                Exemplaire exemplaire = new Exemplaire(rs.getInt(1), rs.getString(3),
                        new Livre(rs.getInt(2), rs.getString(4), rs.getString(5), rs.getInt(6)));
                listForm.add(exemplaire);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlLivreDAO, method getLivreList(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listForm;
    }

    @Override
    public List<Date> getDateLocation(int idExemplaireSelected) {
        List<Date> listDate = new ArrayList<>();
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT dateLocation from vw_dateexemplaire where idLivre = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idExemplaireSelected);
            rs = ps.executeQuery();
            while (rs.next()) {
                Date date = rs.getDate("dateLocation");
                listDate.add(date);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlLivreDAO, method getLivreList(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listDate;
    }

    @Override
    public void location(Location location) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "INSERT INTO location(idExemplaire, idUser, dateLocation) VALUES (?,?,?)";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, location.getExemplaire().getIdExemplaire());
            ps.setInt(2, location.getIdUser());
            ps.setDate(3, new java.sql.Date(location.getDateLocation().getTime()));
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlBibliothequeDAO, method location(Location location)): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
    }

    @Override
    public boolean verifyDispoLocation(Location location, Bibliotheque bibliotheque) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Boolean result = false;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT exemplaire.idExemplaire, exemplaire.type, livre.idLivre, livre.titre, livre.auteur, livre.editeur, livre.page, livre.prixAchat, livre.noteTotal from exemplaire join livre on exemplaire.idLivre = livre.idLivre JOIN livrebiliotheque ON livrebiliotheque.idExemplaire = exemplaire.idExemplaire WHERE exemplaire.idLivre = ? AND exemplaire.type=? AND exemplaire.idExemplaire NOT IN (SELECT location.idExemplaire FROM location WHERE location.dateLocation BETWEEN DATE_SUB(?,INTERVAL 30 DAY) AND DATE_ADD(?,INTERVAL 30 DAY)) GROUP BY exemplaire.idExemplaire ORDER BY livrebiliotheque.idBibliotheque = ? DESC LIMIT 1";
        String sql2 = "INSERT INTO location(idExemplaire, idUser, dateLocation) VALUES (?,?,?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, location.getExemplaire().getLivre().getIdLivre());
            ps.setString(2, location.getExemplaire().getType());
            ps.setDate(3, new java.sql.Date(location.getDateLocation().getTime()));
            ps.setDate(4, new java.sql.Date(location.getDateLocation().getTime()));
            ps.setInt(5, bibliotheque.getIdBibliotheque());
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = c.prepareStatement(sql2);
                ps.setInt(1, rs.getInt("idExemplaire"));
                ps.setInt(2, location.getIdUser());
                ps.setDate(3, new java.sql.Date(location.getDateLocation().getTime()));
                ps.executeUpdate();
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlBibliothequeDAO, method verifyDispoLocation(Location location, int idBibliotheque): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;
    }

    @Override
    public Exemplaire getExemplaireById(int idExemplaire) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Exemplaire exemplaire = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT exemplaire.idExemplaire, exemplaire.type, livre.idLivre, livre.titre, livre.editeur, livre.page,exemplaire.disponible, exemplaire.rendu,exemplaire.verifier  FROM exemplaire join livre on exemplaire.idLivre = livre.idLivre WHERE exemplaire.idExemplaire = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idExemplaire);
            rs = ps.executeQuery();
            if (rs.next()) {
                exemplaire = new Exemplaire(rs.getInt(1), rs.getString(2),
                        new Livre(rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6)),
                        rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9));
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlBibliothequeDAO, method getExemplaireById(int idExemplaire): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return exemplaire;
    }

    @Override
    public boolean verifyDispoLocationLivre(Location location, Bibliotheque bibliotheque) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Boolean result = false;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT exemplaire.idExemplaire, exemplaire.type, livre.idLivre, livre.titre, livre.auteur, livre.editeur, livre.page, livre.prixAchat, livre.noteTotal FROM exemplaire JOIN livre ON exemplaire.idLivre = livre.idLivre JOIN livrebiliotheque ON livrebiliotheque.idExemplaire = exemplaire.idExemplaire WHERE exemplaire.idLivre = ? AND exemplaire.type=? AND livrebiliotheque.idBibliotheque = ? AND exemplaire.idExemplaire NOT IN (SELECT location.idExemplaire FROM location WHERE location.dateLocation BETWEEN DATE_SUB(?,INTERVAL 30 DAY) AND DATE_ADD(?,INTERVAL 30 DAY))GROUP BY exemplaire.idExemplaire LIMIT 1";
        String sql2 = "SELECT exemplaire.idExemplaire, exemplaire.type, livre.idLivre, livre.titre, livre.auteur, livre.editeur, livre.page, livre.prixAchat, livre.noteTotal FROM"
                + " exemplaire join livre on exemplaire.idLivre = livre.idLivre JOIN livrebiliotheque ON livrebiliotheque.idExemplaire = exemplaire.idExemplaire "
                + "WHERE exemplaire.idLivre = ? AND exemplaire.type=? "
                + "AND exemplaire.idExemplaire NOT IN (SELECT location.idExemplaire FROM location WHERE location.dateLocation BETWEEN DATE_SUB(?,INTERVAL 32 DAY) AND DATE_ADD(?,INTERVAL 32 DAY))GROUP BY exemplaire.idExemplaire LIMIT 1";
        String sql3 = "INSERT INTO location(idExemplaire, idUser, dateLocation) VALUES (?,?,?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, location.getExemplaire().getLivre().getIdLivre());
            ps.setString(2, location.getExemplaire().getType());
            ps.setInt(3, bibliotheque.getIdBibliotheque());
            ps.setDate(4, new java.sql.Date(location.getDateLocation().getTime()));
            ps.setDate(5, new java.sql.Date(location.getDateLocation().getTime()));
            rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
                ps = c.prepareStatement(sql3);
                ps.setInt(1, rs.getInt("idExemplaire"));
                ps.setInt(2, location.getIdUser());
                ps.setDate(3, new java.sql.Date(location.getDateLocation().getTime()));
                ps.executeUpdate();
            } else if (!rs.next()) {
                ps = c.prepareStatement(sql2);
                ps.setInt(1, location.getExemplaire().getLivre().getIdLivre());
                ps.setString(2, location.getExemplaire().getType());
                ps.setDate(3, new java.sql.Date(location.getDateLocation().getTime()));
                ps.setDate(4, new java.sql.Date(location.getDateLocation().getTime()));
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = true;
                    ps = c.prepareStatement(sql3);
                    ps.setInt(1, rs.getInt("idExemplaire"));
                    ps.setInt(2, location.getIdUser());
                    ps.setDate(3, new java.sql.Date(location.getDateLocation().getTime()));
                    ps.executeUpdate();
                }
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlBibliothequeDAO, method verifyDispoLocationLivre(Location location, int idBibliotheque): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;
    }

    @Override
    public List<Faq> listeQuestionByUser(User user, Bibliotheque bibliotheque) {
        List<Faq> listeQuestions = new ArrayList<>();
        Faq question;
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT faq.idQuestion,faq.idUserQuestion, faq.question, faq.idUserReponse, faq.reponse FROM faq JOIN `user` on faq.idUserQuestion = `user`.idUser JOIN inscription ON `user`.idUser = inscription.idUser WHERE faq.idUserQuestion = ? AND inscription.idBibliotheque = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, user.getIdUser());
            ps.setInt(2, bibliotheque.getIdBibliotheque());
            rs = ps.executeQuery();
            while (rs.next()) {
                question = new Faq(rs.getInt("idQuestion"), rs.getInt("idUserQuestion"), rs.getString("question"), rs.getInt("idUserReponse"), rs.getString("reponse"));
                listeQuestions.add(question);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlLivreDAO, method listeQuestionByUser(User user, int idBibliotheque): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listeQuestions;
    }

    @Override
    public void reponseQuestion(User user, String reponse, int idQuestion) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  faq SET reponse = ? , idUserReponse = ?  WHERE idQuestion=? ";
        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, reponse);
            ps.setInt(2, user.getIdUser());
            ps.setInt(3, idQuestion);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDAO, method addNewUser(String login, String password): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
    }

    @Override
    public List<Faq> listeQuestionByIdBibliotheque(Bibliotheque bibliotheque) {
        List<Faq> listeQuestions = new ArrayList<>();
        Faq question;
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT faq.idQuestion,faq.idUserQuestion, faq.question, faq.idUserReponse, faq.reponse FROM faq JOIN `user` on faq.idUserQuestion = `user`.idUser JOIN inscription ON `user`.idUser = inscription.idUser WHERE  inscription.idBibliotheque = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, bibliotheque.getIdBibliotheque());
            rs = ps.executeQuery();
            while (rs.next()) {
                question = new Faq(rs.getInt("idQuestion"), rs.getInt("idUserQuestion"), rs.getString("question"), rs.getInt("idUserReponse"), rs.getString("reponse"));
                listeQuestions.add(question);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlLivreDAO, method listeQuestionByUser(User user, int idBibliotheque): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listeQuestions;
    }

    @Override
    public Map<String, Integer> searchBook(String searchLivre) {

        HashMap<String, Integer> searchResult = new HashMap<>();
        Connection c;
        PreparedStatement ps = null;
        ResultSet rs = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT livre.titre,count(*)  FROM exemplaire JOIN livre ON exemplaire.idLivre = livre.idLivre  WHERE  livre.titre  LIKE?  GROUP BY livre.titre ";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, "%" + searchLivre + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                String titre = rs.getString(1);
                Integer cpt = rs.getInt(2);
                searchResult.put(titre, cpt);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method searchBook(String searchLivre) : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return searchResult;

    }

    @Override
    public List<Location> getAllLocationByIdUserIdBibliotheque(Bibliotheque bibliotheque, User user) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        List<Location> listLocation = new ArrayList<>();

        String sql = "SELECT location.idLocation, location.idExemplaire, location.idUser, location.dateLocation, exemplaire.idLivre, exemplaire.type, exemplaire.disponible,exemplaire.rendu,exemplaire.verifier , livre.idLivre, livre.prixAchat, livre.titre, livre.auteur, livre.editeur, livre.page, livre.noteTotal FROM location JOIN exemplaire ON location.idExemplaire = exemplaire.idExemplaire JOIN livre ON exemplaire.idLivre = livre.idLivre JOIN livrebiliotheque ON livrebiliotheque.idExemplaire = exemplaire.idExemplaire  WHERE idUser = ? AND livrebiliotheque.idBibliotheque  = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, user.getIdUser());
            ps.setInt(2, bibliotheque.getIdBibliotheque());
            rs = ps.executeQuery();

            while (rs.next()) {
                Location location = new Location(rs.getInt("idLocation"),
                        new Exemplaire(rs.getInt("idExemplaire"), rs.getString("type"),
                                new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"), rs.getString("editeur"), rs.getInt("page"), rs.getInt("prixAchat"), rs.getDouble("noteTotal")), rs.getBoolean("disponible"), rs.getBoolean("rendu"), rs.getBoolean("verifier")),
                        rs.getInt("idUser"), rs.getDate("dateLocation"));
                listLocation.add(location);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method searchBook(String searchLivre) : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listLocation;
    }

    @Override
    public List<Avis> getAllAvisbyIdLivre(int idLivre) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        List<Avis> listAvis = new ArrayList<>();

        String sql = "SELECT avis.idLivre, avis.idUser, avis.commentaire, avis.note, `user`.idUser, `user`.nom, `user`.prenom, `user`.email,`user`.password, role.idRole, role.nomRole, user.adresse, user.amende FROM avis JOIN `user` ON `user`.idUser = avis.idUser JOIN role ON `user`.role = role.idRole WHERE avis.idLivre  = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idLivre);
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
            System.err.println("MySqlCentreDAO, method getAllAvisbyIdLivre(int idLivre) : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listAvis;
    }

    @Override
    public void updateCoti() {

        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "UPDATE inscription SET inscription.cotisation = 0 WHERE DATEDIFF(CURRENT_DATE,inscription.dateCotisation)>365";
        try {

            ps = c.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDAO, method updateCoti(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }

    }

    @Override
    public List<User> getAvailableManager() {
        List<User> listManager = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection c = MySqlDaoFactory.getInstance().getConnection();
        String sql = "SELECT idUser, nom, prenom, email, `password`, role, adresse, amende FROM user WHERE role = ? NOT IN (SELECT inscription.idUser FROM inscription)";
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, 3);
            rs = ps.executeQuery();
            while (rs.next()) {
                User usr = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        new Role(rs.getInt(6), rs.getString(7)),
                        rs.getString(8));
                listManager.add(usr);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlAnnuaireDAO, method getUsersList(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listManager;

    }

    @Override
    public List<Location> getAllLocationByBibliotheque(Bibliotheque bibliotheque) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        List<Location> listLocation = new ArrayList<>();

        String sql = "SELECT location.idLocation, location.idExemplaire, location.idUser, location.dateLocation, exemplaire.idLivre, exemplaire.type, exemplaire.disponible,exemplaire.rendu, exemplaire.verifier,livre.idLivre, livre.prixAchat, livre.titre, livre.auteur, livre.editeur, livre.page, livre.noteTotal FROM location JOIN exemplaire ON location.idExemplaire = exemplaire.idExemplaire JOIN livre ON exemplaire.idLivre = livre.idLivre JOIN livrebiliotheque ON livrebiliotheque.idExemplaire = exemplaire.idExemplaire  WHERE livrebiliotheque.idBibliotheque  = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, bibliotheque.getIdBibliotheque());
            rs = ps.executeQuery();

            while (rs.next()) {
                Location location = new Location(rs.getInt("idLocation"),
                        new Exemplaire(rs.getInt("idExemplaire"), rs.getString("type"),
                                new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"), rs.getString("editeur"), rs.getInt("page"), rs.getInt("prixAchat"), rs.getDouble("noteTotal")), rs.getBoolean("disponible"), rs.getBoolean("rendu"), rs.getBoolean("verifier")),
                        rs.getInt("idUser"), rs.getDate("dateLocation"));
                listLocation.add(location);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method searchBook(String searchLivre) : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listLocation;
    }

    @Override
    public void validationRetour(int idExemplaire) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "UPDATE exemplaire SET verifier = true  WHERE IdExemplaire = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idExemplaire);
            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.err.println("MySqlLivreDAO, method getLocationById(Location location, User user): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }

    }

    @Override
    public void supprimerLocation(Location location) {
        //metre dispo, rendu et verifier à zero
        //rajouter une mande 

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT IdUser from location where location.IdLocation = ? ";
        String sqlUpDateUser = "UPDATE `user` SET `Amende`= user.amende + ?  WHERE user.IdUser = ? ";
        String sqlSupprimerExemplaire = "UPDATE `exemplaire` SET `rendu`= 1,`verifier`= 1,`disponible`= 0 WHERE exemplaire.IdExemplaire = ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql);
            ps.setInt(1, location.getIdLocation());
            rs = ps.executeQuery();
            int idUser = 0;
            if (rs.next()) {
                idUser = rs.getInt(1);
            }

            ps = c.prepareStatement(sqlUpDateUser);
            ps.setFloat(1, location.getExemplaire().getLivre().getPrixAchat());
            ps.setInt(2, idUser);
            ps.executeUpdate();

            ps = c.prepareStatement(sqlSupprimerExemplaire);
            ps.setInt(1, location.getExemplaire().getIdExemplaire());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("MySqlBibliothequeDAO, method SuppressionExemplaire(Location lctn): \n" + e.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);

        }
    }

    @Override
    public void addBook(Exemplaire exemplaire, Bibliotheque bibliotheque) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT livre.titre, livre.idLivre FROM livre where livre.titre = ?";
        String sql1 = "INSERT INTO livre(prixAchat, titre, auteur, editeur,  page)  VALUES (?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO exemplaire(idLivre, type, path) VALUES (?,?,?)";
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
                ps.setString(3, exemplaire.getPath());
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
                ps.setString(3, exemplaire.getPath());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                //rs.next();
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
    public void rendreLocation(Location location, User user) {
       
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        float jourRetard;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql1 = "UPDATE exemplaire SET rendu = true  WHERE IdExemplaire = ?";
        String sql2 = "SELECT DATEDIFF(CURRENT_DATE, dateLocation ) as jourRetard from location where location.idUser = ? and location.idLocation = ?";
        String sql3 = "UPDATE user SET amende = user.amende + ?  WHERE user.idUser = ? ";

        try {
            c = MySqlDaoFactory.getInstance().getConnection();
            ps = c.prepareStatement(sql1);
            ps.setInt(1, location.getExemplaire().getIdExemplaire());
            ps.executeUpdate();

            ps = c.prepareStatement(sql2);
            ps.setInt(1, user.getIdUser());
            ps.setInt(2, location.getIdLocation());
            rs = ps.executeQuery();

            if (rs.next()) {
                jourRetard = rs.getInt("jourRetard") - 30.0f;
                if ((jourRetard) > 0) {
                    jourRetard = jourRetard * 0.1f;
                    ps = c.prepareStatement(sql3);
                    ps.setFloat(1, jourRetard);
                    ps.setInt(2, user.getIdUser());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlLivreDAO, method getLocationById(Location location, User user): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
    }

}
