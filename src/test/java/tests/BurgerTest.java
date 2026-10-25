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
    private Ingredient ingredientBun;

    @Mock
    private Ingredient ingredientSauce;

    @Mock
    private Ingredient ingredientFilling;


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
        burger.addIngredient(ingredientBun);
        assertEquals("Список должен увеличиться", 1, burger.ingredients.size());
    }

    @Test
    public void addIngredientContainsTest() {
        burger.addIngredient(ingredientBun);
        assertTrue("Ингредиент должен совпадать", burger.ingredients.contains(ingredientBun));
    }

    @Test
    public void removeIngredientsTest() {
        burger.addIngredient(ingredientBun);
        burger.removeIngredient(0);
        assertEquals("Список должен стать пустым", 0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientFirstPositionTest() {
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        burger.moveIngredient(0, 1);
        assertEquals("В списке должен быть под первым номером", 1, burger.ingredients.indexOf(ingredientSauce));
    }

    @Test
    public void moveIngredientSecondPositionTest() {
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        burger.moveIngredient(0, 1);
        assertEquals("В списке должен быть нулевым", 0, burger.ingredients.indexOf(ingredientFilling));
    }

    @Test
    public void getReceiptNotNullTest() {
        Mockito.when(bun.getName()).thenReturn("red bun");
        burger.setBuns(bun);
        String receipt = burger.getReceipt();

        assertNotNull("Чек должен быть", receipt);
    }

    @Test
    public void getReceiptContainsBunTest() {
        Mockito.when(bun.getName()).thenReturn("red bun");
        burger.setBuns(bun);
        String receipt = burger.getReceipt();

        assertTrue("Булка должна быть красной", receipt.contains("red bun"));
    }

    @Test
    public void getReceiptContainsSauceTest() {
        Mockito.when(bun.getName()).thenReturn("red bun");
        Mockito.when(ingredientBun.getType()).thenReturn(SAUCE);
        Mockito.when(ingredientBun.getName()).thenReturn("chili sauce");
        Mockito.when(bun.getPrice()).thenReturn(300f);
        Mockito.when(ingredientBun.getPrice()).thenReturn(300f);

        burger.setBuns(bun);
        burger.addIngredient(ingredientBun);
        String receipt = burger.getReceipt();

        assertTrue("Ингредиент должен быть соусом", receipt.contains("sauce"));
    }

    @Test
    public void getReceiptContainsChiliSauceTest() {
        Mockito.when(bun.getName()).thenReturn("red bun");
        Mockito.when(ingredientBun.getType()).thenReturn(SAUCE);
        Mockito.when(ingredientBun.getName()).thenReturn("chili sauce");
        Mockito.when(bun.getPrice()).thenReturn(300f);
        Mockito.when(ingredientBun.getPrice()).thenReturn(300f);

        burger.setBuns(bun);
        burger.addIngredient(ingredientBun);
        String receipt = burger.getReceipt();

        assertTrue("Ингредиент должен быть соусом чили", receipt.contains("chili sauce"));
    }

    @Test
    public void getReceiptContainsPriceTest() {
        Mockito.when(bun.getName()).thenReturn("red bun");
        Mockito.when(ingredientBun.getType()).thenReturn(SAUCE);
        Mockito.when(ingredientBun.getName()).thenReturn("chili sauce");
        Mockito.when(bun.getPrice()).thenReturn(300f);
        Mockito.when(ingredientBun.getPrice()).thenReturn(300f);

        burger.setBuns(bun);
        burger.addIngredient(ingredientBun);
        String receipt = burger.getReceipt();

        assertTrue("Должна быть строка с ценой", receipt.contains("Price:"));
    }

    @After
    public void tearDown() {
        burger = null;
    }
}
