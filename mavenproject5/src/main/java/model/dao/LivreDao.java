
package model.dao;

import java.util.List;
import model.Avis;
import model.Bibliotheque;
import model.Exemplaire;
import model.Livre;
import model.Location;
import model.User;



public interface LivreDao {

    public Livre getLivre();

    public List<Livre> getAllLivre();

    public List<Livre> getAllLocation(Bibliotheque bibliotheque, User user);

    public Livre getLivreByNom(String titreLivreAvis);

    public void insertAvis(Avis avis);

    public Exemplaire getExemplaireById(int idExemplaireRendu);

    public Location getLocationById(int idLocationRendu);


    public Livre getLivreById(int idLivreAvis);

    public List<Avis> getAvisByIdUser(User user);

    public boolean getAvisByIdUSerIdLivreSelected(User user, Livre livre);


    public void registerPage(int pageSelect, int idLocation);


}
