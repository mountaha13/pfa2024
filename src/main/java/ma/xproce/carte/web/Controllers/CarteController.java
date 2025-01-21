package ma.xproce.carte.web.Controllers;

import ma.xproce.carte.dao.entities.Carte;
import ma.xproce.carte.dao.entities.Entreprise;
import ma.xproce.carte.service.CarteManager;
import ma.xproce.carte.service.EntrepriseManager;
import ma.xproce.carte.service.SecteurManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarteController {


        @Autowired
        private EntrepriseManager entrepriseManager;

        @Autowired
        private CarteManager carteManager;
        @GetMapping("/cartes")
        public String listCartes(Model model) {
                List<Carte> cartes = carteManager.getAllCartes();
                model.addAttribute("cartes", cartes);
                return "cartes";
        }
        @GetMapping("/editCarte")
        public String editCarte(Model model, @RequestParam(name = "id") Integer id) {
                Carte carte = carteManager.getCarteById(id);
                if (carte != null) {
                        model.addAttribute("carteToBeUpdated", carte);
                        return "updateCarte";
                } else {
                        return "error";
                }
        }

        @PostMapping("/updateCarte")
        public String updateCarteAction(Model model,
                                        @RequestParam(name = "id") Integer id,
                                        @RequestParam(name = "adresse") String adresse,
                                        @RequestParam(name = "heureOuverture") String heureOuverture,
                                        @RequestParam(name = "heureFermeture") String heureFermeture,
                                        @RequestParam(name = "numeroTelephone") String numeroTelephone,
                                        @RequestParam(name = "emailProfessionnel") String emailProfessionnel,
                                        @RequestParam(name = "imageUrl") String imageUrl) {
                Carte carte = carteManager.getCarteById(id);
                if (carte != null) {
                        carte.setAdresse(adresse);
                        carte.setHeureOuverture(heureOuverture);
                        carte.setHeureFermeture(heureFermeture);
                        carte.setNumeroTelephone(numeroTelephone);
                        carte.setEmailProfessionnel(emailProfessionnel);
                        carte.setImageUrl(imageUrl);
                        carteManager.updateCarte(carte);
                        return "redirect:cartes";
                } else {
                        return "error";
                }
        }
        @GetMapping("/deleteCarte")
        public String deleteCarte(@RequestParam(name = "id") Integer id) {
                carteManager.deleteCarte(id);
                return "redirect:cartes";
        }

        @GetMapping("/addCarte")
        public String showAddCarteForm(Model model) {
                model.addAttribute("carte", new Carte());
                List<Entreprise> entreprises = entrepriseManager.getAllEntreprises();
                model.addAttribute("entreprises", entreprises);
                return "addCarte";
        }

        @PostMapping("/addCarte")
        public String addCarte(@RequestParam("entrepriseId") Integer entrepriseId, @ModelAttribute Carte carte) {
                Entreprise entreprise = entrepriseManager.getEntrepriseById(entrepriseId);
                if (entreprise != null) {
                        carte.setEntreprise(entreprise);
                        carteManager.addCarte(carte);
                }
                return "redirect:/cartes";
        }
        @GetMapping("/searchCartes")
        public String searchCartes(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
                List<Carte> cartes;
                if (keyword != null && !keyword.isEmpty()) {
                        cartes = carteManager.searchCartesByAdresse(keyword);
                } else {
                        cartes = carteManager.getAllCartes();
                }
                model.addAttribute("cartes", cartes);
                return "cartes";
        }

}
