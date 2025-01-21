package ma.xproce.carte.web.Controllers;

import ma.xproce.carte.dao.entities.Carte;
import ma.xproce.carte.dao.entities.Entreprise;

import ma.xproce.carte.dao.entities.Secteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ma.xproce.carte.service.*;


import org.springframework.data.domain.Pageable;

import java.util.List;

    @Controller
    public class EntrepriseController {

        @Autowired
        private EntrepriseManager entrepriseManager;
       @Autowired
       private SecteurManager secteurManager;
       @Autowired
       private CarteManager carteManager;

        @GetMapping("/entreprises")
        public String listEntreprises(Model model, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size) {
            Page<Entreprise> entreprises = entrepriseManager.findAll(PageRequest.of(page, size));
            model.addAttribute("entreprises", entreprises.getContent());
            model.addAttribute("pages",new int[entreprises.getTotalPages()]);
            model.addAttribute("currentPage",page);
            return "entreprises";
        }
        @GetMapping("/entreprises/by-secteur")
        public String listEntreprisesBySecteur(Model model, @RequestParam String secteur) {
            List<Entreprise> entreprises = entrepriseManager.findBySecteur(secteur, PageRequest.of(0, 5));
            model.addAttribute("entreprises", entreprises);
            model.addAttribute("secteur", secteur);
            return "users";
        }



        @GetMapping("/deleteEntreprise")
        public String delete(@RequestParam(name="id") Integer id) {
            entrepriseManager.deleteEntreprise(id);
            return "redirect:/entreprises";
        }

        @GetMapping("/editEntreprise")
        public String editEntreprise(Model model, @RequestParam(name = "id") Integer id) {
            Entreprise entreprise = entrepriseManager.getEntrepriseById(id);
            if (entreprise != null) {
                List<Secteur> secteurs = secteurManager.getAllSecteurs(); // Obtenez tous les secteurs
                model.addAttribute("entrepriseToBeUpdated", entreprise);
                model.addAttribute("carte", entreprise.getCarte());
                model.addAttribute("secteurs", secteurs); // Ajoutez les secteurs au modèle
                return "updateEntreprise";
            } else {
                return "error";
            }
        }



        @PostMapping("/updateEntreprise")
        public String updateEntrepriseAction(Model model,
                                             @RequestParam(name = "id") Integer id,
                                             @RequestParam(name = "nom") String nom,
                                             @RequestParam(name = "description") String description,
                                             @RequestParam(name = "carteId") Integer carteId,
                                             @RequestParam(name = "adresse") String adresse,
                                             @RequestParam(name = "heureOuverture") String heureOuverture,
                                             @RequestParam(name = "heureFermeture") String heureFermeture,
                                             @RequestParam(name = "numeroTelephone") String numeroTelephone,
                                             @RequestParam(name = "emailProfessionnel") String emailProfessionnel,
                                             @RequestParam(name = "imageUrl") String imageUrl,
                                             @RequestParam(name = "secteurId") Integer secteurId) { // Ajouter le secteurId
            Entreprise entreprise = entrepriseManager.getEntrepriseById(id);
            if (entreprise != null) {
                entreprise.setNom(nom);
                entreprise.setDescription(description);

                Secteur secteur = secteurManager.getSecteurById(secteurId); // Obtenez le secteur par ID
                entreprise.setSecteur(secteur); // Mettez à jour le secteur de l'entreprise

                Carte carte = carteManager.getCarteById(carteId);
                if (carte != null) {
                    carte.setAdresse(adresse);
                    carte.setHeureOuverture(heureOuverture);
                    carte.setHeureFermeture(heureFermeture);
                    carte.setNumeroTelephone(numeroTelephone);
                    carte.setEmailProfessionnel(emailProfessionnel);
                    carte.setImageUrl(imageUrl);
                    carteManager.updateCarte(carte);
                } else {
                    // Si la carte n'existe pas, créer une nouvelle carte
                    carte = new Carte();
                    carte.setAdresse(adresse);
                    carte.setHeureOuverture(heureOuverture);
                    carte.setHeureFermeture(heureFermeture);
                    carte.setNumeroTelephone(numeroTelephone);
                    carte.setEmailProfessionnel(emailProfessionnel);
                    carte.setImageUrl(imageUrl);
                    carte = carteManager.addCarte(carte);
                }

                entreprise.setCarte(carte);
                entrepriseManager.updateEntreprise(entreprise);
                return "redirect:/entreprises"; // Corrigez le chemin de redirection
            } else {
                return "error";
            }
        }


        @GetMapping("/addEntreprise")
        public String addEntrepriseForm(Model model) {
            model.addAttribute("entreprise", new Entreprise());
            model.addAttribute("carte", new Carte());
            List<Secteur> secteurs = secteurManager.getAllSecteurs();
            model.addAttribute("secteurs", secteurs);
            return "ajouter";
        }

        @PostMapping("/addEntreprise")
        public String addEntrepriseAction(@RequestParam String nom,
                                          @RequestParam String description,
                                          @RequestParam Integer secteurId,
                                          @RequestParam String adresse,
                                          @RequestParam String heureOuverture,
                                          @RequestParam String heureFermeture,
                                          @RequestParam String numeroTelephone,
                                          @RequestParam String emailProfessionnel,
                                          @RequestParam String imageUrl) {
            Secteur secteur = secteurManager.getSecteurById(secteurId);
            Carte carte = new Carte();
            carte.setAdresse(adresse);
            carte.setHeureOuverture(heureOuverture);
            carte.setHeureFermeture(heureFermeture);
            carte.setNumeroTelephone(numeroTelephone);
            carte.setEmailProfessionnel(emailProfessionnel);
            carte.setImageUrl(imageUrl);
            carte = carteManager.addCarte(carte);

            Entreprise entreprise = new Entreprise();
            entreprise.setNom(nom);
            entreprise.setDescription(description);
            entreprise.setSecteur(secteur);
            entreprise.setCarte(carte);
            entrepriseManager.addEntreprise(entreprise);
            return "redirect:/entreprises";
        }


        @GetMapping("/searchEntreprises")
        public String searchEntreprises(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
            List<Entreprise> entreprises;
            if (keyword != null && !keyword.isEmpty()) {
                entreprises = entrepriseManager.searchEntreprisesByNom(keyword);
            } else {
                entreprises = entrepriseManager.getAllEntreprises();
            }
            model.addAttribute("entreprises", entreprises);
            return "entreprises";
        }
    }




       /* @GetMapping("/updateEntreprise")
        public String editEntreprise(Model model, @RequestParam(name = "id") Integer id) {
            Entreprise entreprise = entrepriseManager.getEntrepriseById(id);
            if (entreprise != null) {
                model.addAttribute("entrepriseToBeUpdated", entreprise);
                return "updateEntreprise";
            } else {
                return "error";
            }
        }*/

       /* @PostMapping("/ajouter")
        public String ajouterEntrepriseAction(Model model,
                                              @RequestParam(name = "nom") String nom,
                                              @RequestParam(name = "description") String description,
                                              @RequestParam(name = "secteurId") Long secteurId,
                                              @RequestParam(name = "carteId") Long carteId) {
            // Créer une nouvelle instance d'Entreprise en utilisant les paramètres reçus
            Entreprise entreprise = new Entreprise();
            entreprise.setNom(nom);
            entreprise.setDescription(description);

            // Charger le secteur à partir de son identifiant
            Secteur secteur = secteurManager.getSecteurById(carteId.intValue());
            entreprise.setSecteur(secteur);

            // Charger la carte à partir de son identifiant
            Carte carte = carteManager.getCarteById(carteId.intValue());
            entreprise.setCarte(carte);


            entrepriseManager.addEntreprise(entreprise);

            // Rediriger vers une page appropriée après l'ajout de l'entreprise
            return "redirect:entreprises";
        }*/





