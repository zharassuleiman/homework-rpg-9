package com.narxoz.rpg.artifact;

public class CurseDetector implements ArtifactVisitor {
    @Override
    public void visit(Weapon weapon) { if(weapon.getAttackBonus() < 0) System.out.println("Cursed Weapon!"); }
    @Override
    public void visit(Potion potion) { if(potion.getHealing() < 0) System.out.println("Poison!"); }
    @Override
    public void visit(Scroll scroll) { System.out.println("Safe scroll"); }
    @Override
    public void visit(Ring ring) { if(ring.getMagicBonus() > 50) System.out.println("Evil Ring!"); }
    @Override
    public void visit(Armor armor) { System.out.println("Standard Armor"); }
}