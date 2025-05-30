package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @GetMapping("/user/list")
    public String listUsers(Model model, Principal principal) {
        String username = principal.getName();
        User connectedUser = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (connectedUser.getRole().equals("ROLE_ADMIN")) {
            model.addAttribute("users", userService.findAll());
        } else {
            model.addAttribute("users", List.of(connectedUser));
        }

        model.addAttribute("username", username);
        return "user/list";
    }



    @GetMapping("/user/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }


    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        System.out.println("Mot de passe brut reçu : " + user.getPassword());


        if (result.hasErrors()) {

        return "user/add";
        }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));

            userRepository.save(user);

            model.addAttribute("users", userRepository.findAll());
            return "redirect:/user/list";

    }
    @GetMapping("/user/change-password/{id}")
    public String showChangePasswordForm(@PathVariable("id") Integer id, Model model, Principal principal) {
        User user = userService.findById(id);

        // Vérifie que c'est bien l'utilisateur connecté
        if (!user.getUsername().equals(principal.getName())) {
            return "redirect:/access-denied";
        }

        model.addAttribute("user", user);
        return "user/change-password";
    }

    @PostMapping("/user/change-password/{id}")
    public String changePassword(@PathVariable("id") Integer id,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("password") String newPassword,
                                 Principal principal,
                                 RedirectAttributes redirectAttributes) {

        User user = userService.findById(id);
        if (!user.getUsername().equals(principal.getName())) {
            return "redirect:/access-denied";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Mauvais mot de passe actuel → erreur
        if (!encoder.matches(oldPassword, user.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "Current password is incorrect.");
            return "redirect:/user/change-password/" + id;
        }

        // OK → changement du mot de passe
        user.setPassword(encoder.encode(newPassword));
        userService.update(user);
        return "redirect:/user/change-password/" + id + "?success";
    }


    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id,
                             @Valid User user,
                             BindingResult result,
                             Model model,
                             Principal principal) {

        // Sécurité : Seul l'utilisateur lui-même  peut modifier
        User connectedUser = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!connectedUser.getRole().equals("ROLE_ADMIN") && !connectedUser.getId().equals(id)) {
            return "redirect:/access-denied";
        }

        // 🔁 Validation du formulaire
        if (result.hasErrors()) {
            System.out.println("❌ Erreurs de validation : " + result);
            return "user/update";
        }

        // Récupération des données existantes
        User existingUser = userService.findById(id);

        // 🛡️ Si mot de passe vide → on garde l'ancien
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            user.setPassword(existingUser.getPassword());
        } else {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }

        // S'assurer que l'ID est bien mis (au cas où il manquerait dans le form)
        user.setId(id);

        // Mise à jour en base
        userRepository.save(user);

        // Redirection vers la liste
        return "redirect:/user/list";
    }



    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {

        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        userRepository.delete(user);

        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }
}
