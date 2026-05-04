package com.narxoz.rpg.vault;

import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.memento.Caretaker;
import java.util.List;

public class ChronomancerEngine {
    public VaultRunResult runVault(List<Hero> party) {
        int mementosCreated = 0;
        int restoredCount = 0;
        int appraisedCount = 0;

        Caretaker caretaker = new Caretaker();
        Inventory vaultInventory = new Inventory();
        vaultInventory.addArtifact(new Weapon("Void Blade", 500, 10, 25));
        vaultInventory.addArtifact(new Potion("Time Fluid", 100, 1, 50));
        vaultInventory.addArtifact(new Scroll("Rewind Rune", 200, 1, "Chronos"));
        vaultInventory.addArtifact(new Ring("Loop Band", 1000, 0, 15));
        vaultInventory.addArtifact(new Armor("Crystal Plate", 400, 20, 40));

        for (Hero hero : party) {
            System.out.println("Hero " + hero.getName() + " enters the vault.");
            caretaker.save(hero.createMemento());
            mementosCreated++;

            System.out.println("Appraising vault artifacts...");
            GoldAppraiser appraiser = new GoldAppraiser();
            EnchantmentScanner scanner = new EnchantmentScanner();
            WeightCalculator physical = new WeightCalculator();

            vaultInventory.accept(appraiser);
            vaultInventory.accept(scanner);
            vaultInventory.accept(physical);

            System.out.println("Total Est. Value: " + appraiser.getTotalValue() + " gold");
            System.out.println("Total Est. Weight: " + physical.getTotalWeight() + " units");
            appraisedCount += vaultInventory.size();

            System.out.println("A temporal trap activates!");
            hero.takeDamage(80);
            hero.spendGold(50);
            hero.setInventory(new Inventory());

            System.out.println("Before rewind: " + hero);
            hero.restoreFromMemento(caretaker.undo());
            restoredCount++;
            System.out.println("After rewind: " + hero);
            System.out.println("---");
        }

        return new VaultRunResult(appraisedCount, mementosCreated, restoredCount);
    }
}