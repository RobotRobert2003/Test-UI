package com.denzelcode.test;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.denzelcode.form.FormAPI;

public class TestCommand extends Command {
    public TestCommand() {
        super("test");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        FormAPI.customWindowForm("login", "Custom Form")
                .addInput("username", "Username", "Enter your username")
                .addInput("password", "Password", "Enter your password")
                .sendTo((Player) sender);

        return true;
    }
}