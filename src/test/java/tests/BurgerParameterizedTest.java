package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;

    private float bunPrice;
    private float saucePrice;
    private float fillingPrice;
    private float expectedTotalPrice;

    public BurgerParameterizedTest(float bunPrice, float saucePrice, float fillingPrice, float expectedTotalPrice) {
        this.bunPrice = bunPrice;
        this.saucePrice = saucePrice;
        this.fillingPrice = fillingPrice;
        this.expectedTotalPrice = expectedTotalPrice;
    }

    @Parameterized.Parameters(name = "Цена булки: {0}; соус: {1}; начинка: {2} -> Итого: {3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                { 100f, 100f, 100f, 400f},
                { 200f, 200f, 200f, 800f},
                { 300f, 300f, 300f, 1200f}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void burgerParameterizedTest() {
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(sauce.getPrice()).thenReturn(saucePrice);
        Mockito.when(filling.getPrice()).thenReturn(fillingPrice);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        float actualTotalPrice = burger.getPrice();

        assertEquals("Итоговая цена бургера неверна", expectedTotalPrice, actualTotalPrice, 0.001);
    }

    @After
    public void tearDown() {
        burger = null;
    }
}
