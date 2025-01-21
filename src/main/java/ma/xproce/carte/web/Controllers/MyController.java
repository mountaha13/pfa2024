package ma.xproce.carte.web.Controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/")
    public String Home(Model model) {
        return "Home";
    }
    @GetMapping("/secteurs")
    public String viewSecteurs() {
        return "secteurs"; // Nom du fichier HTML dans templates
    }
    /*@GetMapping("/users")
    public String viewEntreprises() {
        return "users"; // Nom du fichier HTML dans templates
    }

   /* @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }*/
}
