package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;

import org.mockito.Mock;

import praktikum.Ingredient;


import static org.junit.Assert.*;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;


    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        assertEquals("Булочка должна прилететь в бургер", bun, burger.bun);
    }

    @Test
    public void addIngredientsTest() {
        burger.addIngredient(ingredient);
        assertEquals("Список должен увеличиться", 1, burger.ingredients.size());
        assertTrue("Ингредиент должен совпадать", burger.ingredients.contains(ingredient));
    }

    @Test
    public void removeIngredientsTest() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertEquals("Список должен стать пустым", 0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientsTest() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals("В списке должен быть под первым номером", 1, burger.ingredients.indexOf(ingredient1));
        assertEquals("В списке должен быть нулевым", 0, burger.ingredients.indexOf(ingredient2));
    }

    @Test
    public void getReceiptTest() {
        Mockito.when(bun.getName()).thenReturn("red bun");
        Mockito.when(ingredient.getType()).thenReturn(SAUCE);
        Mockito.when(ingredient.getName()).thenReturn("chili sauce");
        Mockito.when(bun.getPrice()).thenReturn(300f);
        Mockito.when(ingredient.getPrice()).thenReturn(300f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        String receipt = burger.getReceipt();

        assertNotNull("Чек должен быть", receipt);
        assertTrue("Булка должна быть красной", receipt.contains("red bun"));
        assertTrue("Ингредиент должен быть соусом", receipt.contains("sauce"));
        assertTrue("Ингредиент должен быть соусом чили", receipt.contains("chili sauce"));
        assertTrue("Должна быть строка с ценой", receipt.contains("Price:"));
    }

    @After
    public void tearDown() {
        burger = null;
    }
}
