import Domain.Loghmeh;
import Domain.Order;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class UnitTest {
    private Loghmeh loghmehTester;

    @Before
    public void setup() {
        loghmehTester = new Loghmeh();
        loghmehTester.addRestaurant("{\"name\": \"Hesturan\", \"description\": \"luxury\", \"location\": {\"x\": 1, \"y\": 3}, \"menu\": [{\"name\": \"Gheime\", \"description\": \"it’s yummy!\", \"popularity\": 0.8, \"price\": 20000}, {\"name\": \"Kabab\", \"description\": \"it’s delicious!\", \"popularity\": 0.6, \"price\": 30000}]}");
        loghmehTester.addRestaurant("{\"name\": \"Nayeb\", \"description\": \"luxury\", \"location\": {\"x\": 10, \"y\": 4}, \"menu\": [{\"name\": \"Barg\", \"description\": \"yummyy!\", \"popularity\": 0.8, \"price\": 120000}, {\"name\": \"Jujeh\", \"description\": \"awsum!\", \"popularity\": 0.9, \"price\": 50000}]}");
        loghmehTester.addRestaurant("{\"name\": \"HotChicks\", \"description\": \"cheap\", \"location\": {\"x\": 0, \"y\": 4}, \"menu\": [{\"name\": \"Fries\", \"description\": \"Deeel!\", \"popularity\": 0.7, \"price\": 10000}, {\"name\": \"CheeseBurger\", \"description\": \"ok!\", \"popularity\": 0.6, \"price\": 32000}]}");
        loghmehTester.addRestaurant("{\"name\": \"Perperook\", \"description\": \"so so\", \"location\": {\"x\": 5, \"y\": 3}, \"menu\": [{\"name\": \"Arosto\", \"description\": \"it’s yummy!\", \"popularity\": 0.9, \"price\": 40000}, {\"name\": \"PanjereE\", \"description\": \"it’s delicious!\", \"popularity\": 0.7, \"price\": 30000}, {\"name\": \"Peperoni\", \"description\": \"it’s good!\", \"popularity\": 0.6, \"price\": 35000}]}");
        loghmehTester.addRestaurant("{\"name\": \"DoLopi\", \"description\": \"Kasif\", \"location\": {\"x\": 5, \"y\": 3}, \"menu\": [{\"name\": \"Falafel\", \"description\": \"Kare!:))\", \"popularity\": 0.6, \"price\": 10000}, {\"name\": \"Bandari\", \"description\": \"Tooond!\", \"popularity\": 0.3, \"price\": 9000}, {\"name\": \"Maghz\", \"description\": \"Good!\", \"popularity\": 0.7, \"price\": 30000}]}");


        loghmehTester.addToCart("{\"foodName\": \"Kabab\", \"restaurantName\": \"Hesturan\"}");
        loghmehTester.addToCart("{\"foodName\": \"Gheime\", \"restaurantName\": \"Hesturan\"}");
        loghmehTester.addToCart("{\"foodName\": \"Kabab\", \"restaurantName\": \"Hesturan\"}");
    }

    @Test
    public void finalizeOrderTest(){
        String result = "[\n" +
                "  {\n" +
                "    \"foodName\": \"Kabab\",\n" +
                "    \"orderCount\": 2\n" +
                "  },\n" +
                "  {\n" +
                "    \"foodName\": \"Gheime\",\n" +
                "    \"orderCount\": 1\n" +
                "  }\n" +
                "]\n" +
                "Order was submitted successfully";

        assertEquals(result, loghmehTester.finalizeOrder());
        assertEquals(Order.orderStatus.Submitted, loghmehTester.getLastOrder().getStatus());
    }

    @Test
    public void getRecommendedRestaurantsTest(){
        String result = "Hesturan\n" +
                "HotChicks\n" +
                "Perperook";

        assertEquals(result, loghmehTester.getRecommendedRestaurants());

        int count = loghmehTester.getRecommendedRestaurants().length() - loghmehTester.getRecommendedRestaurants().replace("\n", "").length() + 1;

        assertEquals(3, count);
    }

}

//addRestaurant {"name": "Hesturan", "description": "luxury", "location": {"x": 1, "y": 3}, "menu": [{"name": "Gheime", "description": "it’s yummy!", "popularity": 0.8, "price": 20000}, {"name": "Kabab", "description": "it’s delicious!", "popularity": 0.6, "price": 30000}]}
//addRestaurant {"name": "Nayeb", "description": "luxury", "location": {"x": 10, "y": 4}, "menu": [{"name": "Barg", "description": "yummyy!", "popularity": 0.8, "price": 120000}, {"name": "Jujeh", "description": "awsum!", "popularity": 0.9, "price": 50000}]}
//addRestaurant {"name": "HotChicks", "description": "cheap", "location": {"x": 0, "y": 4}, "menu": [{"name": "Fries", "description": "Deeel!", "popularity": 0.7, "price": 10000}, {"name": "CheeseBurger", "description": "ok!", "popularity": 0.6, "price": 32000}]}
//addRestaurant {"name": "Perperook", "description": "so so", "location": {"x": 5, "y": 3}, "menu": [{"name": "Arosto", "description": "it’s yummy!", "popularity": 0.9, "price": 40000}, {"name": "PanjereE", "description": "it’s delicious!", "popularity": 0.7, "price": 30000}, {"name": "Peperoni", "description": "it’s good!", "popularity": 0.6, "price": 35000}]}
//addRestaurant {"name": "DoLopi", "description": "Kasif", "location": {"x": 5, "y": 3}, "menu": [{"name": "Falafel", "description": "Kare!:))", "popularity": 0.6, "price": 10000}, {"name": "Bandari", "description": "Tooond!", "popularity": 0.3, "price": 9000}, {"name": "Maghz", "description": "Good!", "popularity": 0.7, "price": 30000}]}
