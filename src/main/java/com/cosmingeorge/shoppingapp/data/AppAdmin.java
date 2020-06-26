package com.cosmingeorge.shoppingapp.data;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

public class AppAdmin {
    private List<Recipe> recipes = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private ShoppingList shoppingList = new ShoppingList();
    private static AppAdmin instance = null;


    public AppAdmin() {
        generateData();
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }


    public static AppAdmin getInstance(){
        if (instance == null){
            instance = new AppAdmin();
        }
        return instance;
    }


    public List<Category> getCategories() {
        return categories;
    }


    public List<User> getUserList() {
        return userList;
    }


    public ShoppingList getShoppingList() {
        return shoppingList;
    }


    public Category getOthersCategory(){
        for (Category category : categories){
            if (category.getName().equals("Added products")){
                return category;
            }
        }
        return null;
    }


    public Product getProductById(int product_id) {
        for (Category category : categories) {
            for (Product product : category.getProductList()) {
                if (product.getId() == product_id) {
                    return product;
                }
            }
        }
        return null;
    }

    public Recipe getRecipeById(int recipe_id){
        for (Recipe recipe : recipes){
            if (recipe.getId() == recipe_id){
                return recipe;
            }
        }
        return null;
    }

    public List<String> getDevelopers()
    {
        String cosmin = "Cosmin George Mucalau";
        String alex = "Daniel-Alexandru Bejan";
        List<String> res = new ArrayList<>();
        res.add(cosmin);
        res.add(alex);
        return res;
    }

    public Category getCategoryById(int category_id){
        for (Category category : categories){
            if (category.getId() == category_id){
                return category;
            }
        }
        return null;
    }


    public void generateData(){

        Category dairy = new Category("Dairy","/images/dairyingredients.jpg");
        Category meat = new Category("Meat","/images/meat.jpg");
        Category drinks = new Category("Drinks","/images/drinks.jpg");
        Category fruits = new Category("Fruits","/images/fruits.jpg");
        Category vegetables = new Category("Vegetables","/images/vegetables.jpg");
        Category others = new Category("Added products","/images/noimage.png");

        categories.add(dairy);
        categories.add(meat);
        categories.add(drinks);
        categories.add(fruits);
        categories.add(vegetables);
        categories.add(others);


        Product milk = new Product("Milk",1,"/images/milk.jpg");
        Product egg = new Product("Egg",1,"/images/egg.jpg");
        Product gouda = new Product("Cheese",200,"/images/gouda.jpg");
        Product cream = new Product("Cream",200,"/images/cream.jpg");
        Product parmezan = new Product("Parmezan",150,"/images/parmezan.jpg");
        Product gorgonzola = new Product("Gorgonzola", 150,"/images/gorgonzola.jpg");

        dairy.addProduct(milk);
        dairy.addProduct(egg);
        dairy.addProduct(gorgonzola);
        dairy.addProduct(gouda);
        dairy.addProduct(cream);
        dairy.addProduct(parmezan);

        Product trout = new Product("Trout",300,"/images/trout.jpg");
        Product chickenBreast = new Product("Chicken Breast",500,"/images/chickenbreast.jpg");
        Product chickenLegs = new Product("Chicken Legs",200,"/images/chickenlegs.jpg");
        Product beef = new Product("Burger",4,"/images/burger.jpg");
        Product lamb = new Product("Lamb",500,"/images/lamb.jpg");
        Product ham = new Product("Ham", 150,"/images/ham.jpg");

        meat.addProduct(trout);
        meat.addProduct(chickenBreast);
        meat.addProduct(chickenLegs);
        meat.addProduct(beef);
        meat.addProduct(lamb);
        meat.addProduct(ham);

        Product mint = new Product("Mint",150,"/images/mint.jpg");
        Product broccoli = new Product("Broccoli",500,"/images/broccoli.jpg");
        Product carrot = new Product("Carrot",150,"/images/carrot.jpg");
        Product chili = new Product("Chili",1,"/images/chili.jpg");
        Product avocado = new Product("Avocado",500,"/images/avocado.jpg");
        Product garlic = new Product("Garlic", 150,"/images/garlic.jpg");

        vegetables.addProduct(mint);
        vegetables.addProduct(broccoli);
        vegetables.addProduct(carrot);
        vegetables.addProduct(avocado);
        vegetables.addProduct(chili);
        vegetables.addProduct(garlic);

        Product pepsi = new Product("Pepsi",1,"/images/pepsi.jpg");
        Product fanta = new Product("Fanta",1,"/images/fanta.jpg");
        Product water = new Product("Tonic Water",1,"/images/tonicwater.jpg");
        Product whiskey = new Product("Whiskey",1,"/images/whiskey.jpg");
        Product vodka = new Product("Vodka", 1,"/images/vodka.jpg");

        drinks.addProduct(pepsi);
        drinks.addProduct(vodka);
        drinks.addProduct(water);
        drinks.addProduct(fanta);
        drinks.addProduct(whiskey);


        Recipe mushroomsSoup = new Recipe();
        mushroomsSoup.setDescription("Fry the mushrooms in butter with the cleaned and crushed garlic and diced onion for 2-3 minutes. Gradually add the vegetable broth and simmer for another 5-10 minutes. Pass the composition with a blender, adding gradually cream, salt and pepper. Serve with plenty of fresh parsley.");
        mushroomsSoup.setName("Mushrooms cream soup");
        mushroomsSoup.setTime(20);
        mushroomsSoup.setImg("/images/creamsoup.jpg");
        recipes.add(mushroomsSoup);
        mushroomsSoup.getProductList().add(garlic);
        mushroomsSoup.getProductList().add(cream);

        shoppingList.addProduct(avocado);
        shoppingList.addProduct(garlic);

        User user = new User();
        user.setUsername("test");
        user.setPassword("test123");

        User saxion = new User();
        saxion.setUsername("saxion");
        saxion.setPassword("saxion123");

        userList.add(saxion);
        userList.add(user);

    }

    public boolean logIn(User user){
        for (User user1 : userList){
            if (user1.getUsername().equals(user.getUsername())
             && user1.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

    public User getUserByUsername(String username){
        for (User user : userList){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;

    }
}
