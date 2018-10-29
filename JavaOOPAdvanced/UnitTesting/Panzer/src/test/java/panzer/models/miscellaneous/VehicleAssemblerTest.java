package panzer.models.miscellaneous;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import panzer.contracts.AttackModifyingPart;
import panzer.contracts.DefenseModifyingPart;
import panzer.contracts.HitPointsModifyingPart;

import java.math.BigDecimal;

public class VehicleAssemblerTest {
    private VehicleAssembler vehicleAssembler;
    private AttackModifyingPart attackModifyingPart;
    private DefenseModifyingPart defenseModifyingPart;
    private HitPointsModifyingPart hitPointsModifyingPart;

    @Before
    public void setUp() {
        this.vehicleAssembler = new VehicleAssembler();
        this.attackModifyingPart = Mockito.mock(AttackModifyingPart.class);
        this.defenseModifyingPart = Mockito.mock(DefenseModifyingPart.class);
        this.hitPointsModifyingPart = Mockito.mock(HitPointsModifyingPart.class);
        this.vehicleAssembler.addArsenalPart(attackModifyingPart);
        this.vehicleAssembler.addShellPart(defenseModifyingPart);
        this.vehicleAssembler.addEndurancePart(hitPointsModifyingPart);
    }

    @Test
    public void getTotalWeight() {
        Mockito.when(this.attackModifyingPart.getWeight()).thenReturn(10.0);
        Mockito.when(this.defenseModifyingPart.getWeight()).thenReturn(10.0);
        Mockito.when(this.hitPointsModifyingPart.getWeight()).thenReturn(10.0);

        double actualTotalWeight = this.vehicleAssembler.getTotalWeight();
        double expectedTotalWeight = 30.0;

        Assert.assertEquals(expectedTotalWeight, actualTotalWeight, 0.1);
    }

    @Test
    public void getTotalPrice() {
        Mockito.when(this.attackModifyingPart.getPrice()).thenReturn(new BigDecimal(100));
        Mockito.when(this.defenseModifyingPart.getPrice()).thenReturn(new BigDecimal(200));
        Mockito.when(this.hitPointsModifyingPart.getPrice()).thenReturn(new BigDecimal(200));

        BigDecimal actualTotalPrice = this.vehicleAssembler.getTotalPrice();
        BigDecimal expectedTotalPrice = new BigDecimal(500);

        Assert.assertEquals(expectedTotalPrice, actualTotalPrice);
    }


    @Test
    public void getTotalAttackModification() {
        Mockito.when(this.attackModifyingPart.getAttackModifier()).thenReturn(10);

        long actualTotalAttackModification = this.vehicleAssembler.getTotalAttackModification();
        long expectedTotalAttackModification = 10;

        Assert.assertEquals(expectedTotalAttackModification, actualTotalAttackModification);
    }

    @Test
    public void getTotalDefenseModification() {
        Mockito.when(this.defenseModifyingPart.getDefenseModifier()).thenReturn(10);

        long actualTotalDefenseModification = this.vehicleAssembler.getTotalDefenseModification();
        long expectedTotalDefenseModification = 10;

        Assert.assertEquals(expectedTotalDefenseModification, actualTotalDefenseModification);
    }

    @Test
    public void getTotalHitPointModification() {
        Mockito.when(this.hitPointsModifyingPart.getHitPointsModifier()).thenReturn(10);

        long actualTotalHitPointModification = this.vehicleAssembler.getTotalHitPointModification();
        long expectedTotalHitPointModification = 10;

        Assert.assertEquals(expectedTotalHitPointModification, actualTotalHitPointModification);
    }

    @Test
    public void addArsenalPart() {
        AttackModifyingPart newAttackModifyingPart = Mockito.mock(AttackModifyingPart.class);
        Mockito.when(this.attackModifyingPart.getAttackModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(newAttackModifyingPart.getAttackModifier()).thenReturn(Integer.MAX_VALUE);
        this.vehicleAssembler.addArsenalPart(newAttackModifyingPart);

        long actualTotalAttackModification = this.vehicleAssembler.getTotalAttackModification();
        long expectedTotalAttackModification = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;

        Assert.assertEquals(expectedTotalAttackModification, actualTotalAttackModification);
    }

    @Test
    public void addShellPart() {
        DefenseModifyingPart newDefenseModifyingPart = Mockito.mock(DefenseModifyingPart.class);
        Mockito.when(this.defenseModifyingPart.getDefenseModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(newDefenseModifyingPart.getDefenseModifier()).thenReturn(Integer.MAX_VALUE);
        this.vehicleAssembler.addShellPart(newDefenseModifyingPart);

        long actualTotalDefenseModification = this.vehicleAssembler.getTotalDefenseModification();
        long expectedTotalDefenseModification = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;

        Assert.assertEquals(expectedTotalDefenseModification, actualTotalDefenseModification);
    }

    @Test
    public void addEndurancePart() {
        HitPointsModifyingPart newHitPointsModifyingPart = Mockito.mock(HitPointsModifyingPart.class);
        Mockito.when(this.hitPointsModifyingPart.getHitPointsModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(newHitPointsModifyingPart.getHitPointsModifier()).thenReturn(Integer.MAX_VALUE);
        this.vehicleAssembler.addEndurancePart(newHitPointsModifyingPart);

        long actualTotalHitPointModification = this.vehicleAssembler.getTotalHitPointModification();
        long expectedTotalHitPointModification = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;

        Assert.assertEquals(expectedTotalHitPointModification, actualTotalHitPointModification);
    }
}