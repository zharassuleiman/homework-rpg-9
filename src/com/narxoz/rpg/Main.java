package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;
import com.narxoz.rpg.artifact.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===");

        Hero jaras = new Hero("Suleiman", 100, 50, 20, 10, 100, new Inventory());
        Hero partner = new Hero("Narxoz Student", 80, 30, 15, 5, 20, new Inventory());

        ChronomancerEngine engine = new ChronomancerEngine();
        VaultRunResult result = engine.runVault(List.of(jaras, partner));

        System.out.println("Applying 4th Visitor (Open/Closed Proof):");
        Inventory testInv = new Inventory();
        testInv.addArtifact(new Weapon("Cursed Dagger", 10, 2, -5));
        testInv.addArtifact(new Ring("Eye of Sauron", 999, 0, 666));
        testInv.addArtifact(new Potion("Life Water", 50, 1, 20));
        testInv.accept(new CurseDetector());

        System.out.println("\nFinal Result:");
        System.out.println(result);
    }
}