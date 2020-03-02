//import Domain.Loghmeh;
//import kong.unirest.HttpResponse;
//import kong.unirest.Unirest;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//import Main.JavalineApp;
//
//import static org.assert.core.api.Assertions.assertThat;
//
//public class IntegrationTest {
//    private static JavalineApp app;
//
//    private static String readFile(String fileName) {
//        String data = "";
//        try {
//            File myObj = new File(fileName);
//            Scanner myReader = new Scanner(myObj);
//            while (myReader.hasNextLine()) {
//                data += myReader.nextLine();
//            }
//            myReader.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//        return data;
//    }
//
//    @BeforeClass
//    public static void setup() {
//        String restaurantsJson = readFile("/home/baharan/Desktop/InternetEngineering/Loghmeh/src/test/java/restaurants.json");
//        System.out.println(restaurantsJson);
//        app = new JavalineApp(restaurantsJson);
//    }
//
//    @Test
//    public void getRestaurant() {
//        HttpResponse<String> response = Unirest.post("http://localhost:7677/getRestaurant")
//                .field("restaurantId", "8").asString();
//
//        assertThat(response.getStatus()).isEqualTo(200);
//        System.out.println(response.getBody());
//        assertThat(response.getBody()).contains("پامچال");
//
//    }
//
//    @Test
//    public void submitOrder() {
//        HttpResponse<String> order_response1 = Unirest.post("http://localhost:7677/getRestaurant")
//                .field("restaurantId", "8")
//                .field("foodName", "پیتزا رست بیف")
//                .asString();
//        HttpResponse<String> order_response2 = Unirest.post("http://localhost:7677/getRestaurant")
//                .field("restaurantId", "8")
//                .field("foodName", "پیتزا رست بیف")
//                .asString();
//        HttpResponse<String> order_response3 = Unirest.post("http://localhost:7677/getRestaurant")
//                .field("restaurantId", "8")
//                .field("foodName", "پیتزا مخصوص")
//                .asString();
//
//        HttpResponse<String> finalize1 = Unirest.get("http://localhost:7677/finalize").asString();
//        assertThat(finalize1.getStatus()).isEqualTo(400);
//        assertThat(finalize1.getBody()).contains("not enough money");
//
//    }
//
//
//}
