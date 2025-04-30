package com.example.Sale_system.controller;


import com.example.Sale_system.model.FavoriteItem;
import com.example.Sale_system.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/favorites")
    public String showFavorites(Model model) {
        model.addAttribute("favoriteItems", favoriteService.getFavoriteItems());
        return "dashboard/favorites"; // Đảm bảo đường dẫn trỏ đến đúng file
    }



    // Thêm sản phẩm vào danh sách yêu thích
    @PostMapping("/favorites/add")
    public String addToFavorites(@RequestParam Long id, @RequestParam String name,
                                 @RequestParam Double price, @RequestParam String imageUrl) {
        FavoriteItem item = new FavoriteItem(id, name, price, imageUrl);
        favoriteService.addToFavorites(item);
        return "redirect:/favorites";
    }

    // Xóa sản phẩm khỏi danh sách yêu thích
    @GetMapping("/favorites/remove/{id}")
    public String removeFromFavorites(@PathVariable Long id) {
        favoriteService.removeFromFavorites(id);
        return "redirect:/favorites";
    }
}

