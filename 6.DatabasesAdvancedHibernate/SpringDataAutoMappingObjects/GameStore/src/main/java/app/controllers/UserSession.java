package app.controllers;

import app.domain.entities.Game;
import app.domain.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserSession {
    private User currentUser;
    private boolean isCurrentUserAdmin;
    private boolean isCurrentUserLoggedIn;
    private List<Game> shoppingCart;

    public UserSession() {
        this.shoppingCart = new ArrayList<>();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void login(User user) {
        this.setCurrentUser(user);
        this.setCurrentUserLoggedIn(true);
        this.setCurrentUserAdmin(user.getAdmin());
    }

    public void logout() {
        this.setCurrentUser(null);
        this.setCurrentUserLoggedIn(false);
        this.setCurrentUserAdmin(false);
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isCurrentUserAdmin() {
        return isCurrentUserAdmin;
    }

    public void setCurrentUserAdmin(boolean currentUserAdmin) {
        isCurrentUserAdmin = currentUserAdmin;
    }

    public boolean isCurrentUserLoggedIn() {
        return isCurrentUserLoggedIn;
    }

    public void setCurrentUserLoggedIn(boolean currentUserLoggedIn) {
        isCurrentUserLoggedIn = currentUserLoggedIn;
    }

    public List<Game> getShoppingCart() {
        return shoppingCart;
    }

    public void addGameToShoppingCart(Game game) {
        this.shoppingCart.add(game);
    }

    public void removeGameFromShoppingCart(Game game) {
        this.shoppingCart.remove(game);
    }

    public void clearShoppingCart() {
        this.shoppingCart.clear();
    }
}
