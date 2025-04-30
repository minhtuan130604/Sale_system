package com.example.Sale_system.service;


import com.example.Sale_system.model.FavoriteItem;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteService {
    private List<FavoriteItem> favoriteItems = new ArrayList<>();

    public void addToFavorites(FavoriteItem item) {
        favoriteItems.add(item);
    }

    public void removeFromFavorites(Long id) {
        favoriteItems.removeIf(item -> item.getId().equals(id));
    }

    public List<FavoriteItem> getFavoriteItems() {
        return favoriteItems;
    }
}
