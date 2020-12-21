package com.denzelcode.test;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import com.denzelcode.form.FormAPI;
import com.denzelcode.form.element.Button;
import com.denzelcode.form.element.Input;
import com.denzelcode.form.event.PlayerCustomFormSubmit;
import com.denzelcode.form.event.PlayerModalFormSubmit;
import com.denzelcode.form.event.PlayerSimpleFormButtonClick;
import com.denzelcode.form.window.CustomWindowForm;
import com.denzelcode.form.window.ModalWindowForm;
import com.denzelcode.form.window.SimpleWindowForm;

public class EventListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onLoginFormSubmit(PlayerCustomFormSubmit event) {
        CustomWindowForm form = event.getForm();
        Player player = event.getPlayer();

        if (!event.isFormValid("login")) return;

        Input username = form.getElement("username");
        Input password = form.getElement("password");

        player.sendMessage("Player: " + player.getName());
        player.sendMessage("Form: " + form.getName());
        player.sendMessage("Username: " + username.getValue());
        player.sendMessage("Password: " + password.getValue());

        FormAPI.modalWindowForm(
                "login_remember",
                "Remember",
                "Do you want to remember your account in this device?",
                "Yes",
                "No"
        ).sendTo(player);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onRememberFormSubmit(PlayerModalFormSubmit event) {
        ModalWindowForm form = event.getForm();
        Player player = event.getPlayer();

        if (!event.isFormValid("login_remember")) return;

        boolean accepted = event.isAccepted();

        player.sendMessage("Player: " + player.getName());
        player.sendMessage("Form: " + form.getName());
        player.sendMessage("Accepted: " + (accepted ? "Yes" : "No"));

        FormAPI.simpleWindowForm("minigames", "Minigames", "Select a minigame which you want to play!")
                .addButton("skywars", "SkyWars")
                .addButton("luckyislands", "LuckyIslands")
                .sendTo(player);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onMinigameFormSubmit(PlayerSimpleFormButtonClick event) {
        SimpleWindowForm form = event.getForm();
        Player player = event.getPlayer();
        Button button = event.getButton();

        if (!event.isFormValid("minigames")) return;

        player.sendMessage("Player: " + player.getName());
        player.sendMessage("Form: " + form.getName());
        player.sendMessage("Clicked button: " + button.getName());

        player.sendMessage("Successfully joined Minigame: " + button.getText() + "!");
    }
}