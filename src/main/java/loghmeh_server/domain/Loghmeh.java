package loghmeh_server.domain;

import java.util.*;
import loghmeh_server.deserializer.*;
import loghmeh_server.repository.location.Location;

//Singleton class
public class Loghmeh {
    private static Loghmeh loghmeh = null;

    private ArrayList<Restaurant> restaurants;
    private ArrayList<Customer> customers;

    private HashMap<String, String> idToIndex; //from user view id to restaurant id
    private HashMap<String, String> indexToId; //from restaurant id to user view id

    float nextFoodPartySchedulerFire;

    private Loghmeh() {
        restaurants = new ArrayList<Restaurant>();
        customers = new ArrayList<Customer>();
        customers.add(new Customer(1, "احسان", "خامس‌پناه", "۰۹۱۲۳۴۵۶۷۸۹", "ekhamespanah@yahoo.com", 0f, 0f));
        idToIndex = new HashMap<String, String>();
        indexToId = new HashMap<String , String>();
    }

    public static Loghmeh getInstance() {
        if(loghmeh == null){
            loghmeh = new Loghmeh();
        }
        return loghmeh;
    }

    public Restaurant restaurantAlreadyExists(Restaurant restaurant) {
        for (Restaurant rest : restaurants){
            if (restaurant.equals(rest)) {
                return rest;
            }
        }
        return null;
    }

    public Restaurant sameRestaurant(Restaurant restaurant) {
        for (Restaurant rest : restaurants){
            if(rest.getName().equals(restaurant.getName()) && !rest.getLocation().equals(restaurant.getLocation()) &&
                    rest.getMenu().equals(restaurant.getMenu())){//chain restaurant
                return restaurant;
            }
        }
        return null;
    }

    public String addRestaurants(String jsonInput) {
        ArrayList<Restaurant> restaurants = loghmeh_server.deserializer.restaurantDeserializer.deserializeRestaurants(jsonInput);
        for (Restaurant restaurant : restaurants){
            if(restaurantAlreadyExists(restaurant) != null){
                return "Restaurant Already Exists\n";
            }
            Restaurant otherBranch = sameRestaurant(restaurant);
            if(otherBranch != null){
                restaurant.setMenu(otherBranch.getMenu());
            }
            addRestaurantInfo(restaurant);
        }
        return "Restaurants Added Successfully";
    }

    public String addFoodPartyRestaurants(String jsonInput) {
        ArrayList<Restaurant> restaurants = loghmeh_server.deserializer.restaurantDeserializer.deserializeFoodPartyRestaurants(jsonInput);
        for (Restaurant restaurant: restaurants){
            Restaurant existedRestaurant = restaurantAlreadyExists(restaurant);
            if(existedRestaurant != null) {
                existedRestaurant.getMenu().setFoodPartyFoods(restaurant.getMenu().getFoodPartyFoods());
                continue;
            }
            Restaurant otherBranch = sameRestaurant(restaurant);
            if(otherBranch != null){
                restaurant.setMenu(otherBranch.getMenu());
            }
            addRestaurantInfo(restaurant);
        }
        return "Restaurant With Food Party Added Successfully";
    }

    public void addRestaurantInfo(Restaurant restaurant) {
        indexToId.put(Integer.toString(this.restaurants.size()+1), restaurant.getId());
        idToIndex.put(restaurant.getId(), Integer.toString(this.restaurants.size()+1));
        this.restaurants.add(restaurant);
    }

    public void deleteFoodParty() {
        for(int j = this.restaurants.size() - 1; j >= 0; j--){
            if(this.restaurants.get(j).getMenu().getFoodPartyFoods() != null && this.restaurants.get(j).getMenu().getFoods() == null){
                this.restaurants.remove(j);
            }
            else if(this.restaurants.get(j).getMenu().getFoodPartyFoods() != null && this.restaurants.get(j).getMenu().getFoods() != null) {
                restaurants.get(j).getMenu().setFoodPartyFoods(null);
            }
        }
    }

    public ArrayList<Restaurant> getSpecifiedRestaurants(String type) {
        ArrayList<Restaurant>selectedRestaurants = new ArrayList<>();
        if(type.equals("ordinary")){
            for(Restaurant restaurant: this.restaurants) {
                if(restaurant.getMenu().getFoods() != null) {
                    selectedRestaurants.add(restaurant);
                }

            }
        }

        else if(type.equals("foodparty")) {
            for(Restaurant restaurant: this.restaurants) {
                if(restaurant.getMenu().getFoodPartyFoods() != null){
                    selectedRestaurants.add(restaurant);
                }
            }
        }
        return selectedRestaurants;
    }

    public ArrayList<FoodPartyFood> getFoodPartyFoods() {
        ArrayList<Restaurant> foodPartyRests = getSpecifiedRestaurants("foodparty");
        ArrayList<FoodPartyFood> foodPartyFoods = new ArrayList<>();
        for(Restaurant restaurant: foodPartyRests){
            for(FoodPartyFood foodPartyFood: restaurant.getMenu().getFoodPartyFoods()) {
                foodPartyFoods.add(foodPartyFood);
            }
        }
        return foodPartyFoods;
    }

    public FoodPartyFood getFoodPartyFood(String restarantId, String foodName) {
        ArrayList<Restaurant> foodPartyRests = getSpecifiedRestaurants("foodparty");
        for(Restaurant restaurant: foodPartyRests){
            for(FoodPartyFood foodPartyFood: restaurant.getMenu().getFoodPartyFoods()) {
                if(foodPartyFood.getName().equals(foodName) && foodPartyFood.getRestaurantId().equals(restarantId))
                    return foodPartyFood;
            }
        }
        return null;
    }

    public String updateCart(int customerId, String restaurantId, String foodName, int foodCount, boolean isFoodParty, String operation) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        if(restaurant == null) {
            System.out.println("There Is No Restaurant With ID " + restaurantId);
            return "not found";
        }

        Customer customer = getCustomerById(customerId);
        if(customer == null){
            System.out.println("There Is No Customer With ID " + customerId);
            return "not found";
        }

        Food food;
        if(isFoodParty){
            food = restaurant.getFoodPartyFoodByName(foodName);
        }
        else{
            food = restaurant.getFoodByName(foodName);
        }
        if(food == null){
            System.out.println("Food Does Not Exist");
            return "not found";
        }


        if(operation.equals("add")){
            if (customer.addToCart(restaurant, food, foodCount)){
                return "added";
            }
            return "different restaurant order";
        }
        else if(operation.equals("remove")) {
            return customer.removeFromCart(restaurantId, foodName);
        }
        return "not found";
    }


    public Order getCart(int i) {
        return customers.get(i).getCart();
    }

    public String finalizeOrder(int customerId) {
        Customer customer = getCustomerById(customerId);
        if(customer == null)
            return "not found";

        Order order = customer.getCart();
        if(order != null){
            String result;
            if(isNewFoodParty(order) && areFoodPartyFoodsAvailable(order)){
                if(customer.finalizeOrder())
                    result = "done";
                else
                    result = "no credit";
            }
            else if(isNewFoodParty(order) && !areFoodPartyFoodsAvailable(order)){
                result = "count problem";
            }
            else if(!isNewFoodParty(order) && areFoodPartyFoodsAvailable(order)){
                customer.removeFoodPartyFoodsFromCart();
                result = "time problem";
            }
            else{
                customer.removeFoodPartyFoodsFromCart();
                result = "both problem";
            }
            return result;
        }
        return "not found";
    }

    public boolean doesRestaurantExist(Restaurant restaurant) {
        for(Restaurant rest: this.restaurants) {
            if(restaurant.equals(rest)){
                return true;
            }
        }
        return false;
    }

    public boolean isNewFoodParty(Order order) {
        for (OrderItem orderItem : order.getOrders()) {
            if (orderItem.getFood() instanceof FoodPartyFood) {
                if (!doesRestaurantExist(orderItem.getFood().getMenu().getRestaurant())) {
                    return false;
                }

                if (orderItem.getFood().getMenu().getFoodPartyFoods() == null) {
                    return false;
                }
                for (FoodPartyFood foodPartyFood : orderItem.getFood().getMenu().getFoodPartyFoods()) {
                    if (((FoodPartyFood) orderItem.getFood()).equals(foodPartyFood)){
                       return true;
                    }
                }
                return false;
            }
        }
        return true;
    }

    public boolean areFoodPartyFoodsAvailable(Order order) {
        for(OrderItem orderItem: order.getOrders()){
            if(orderItem.getFood() instanceof FoodPartyFood){
                if(!((FoodPartyFood) orderItem.getFood()).checkCount(orderItem.getOrderCount())){
                    return false;
                }
            }
        }
        return true;
    }

    public void assignDelivery(Order order, Customer customer, ArrayList<Delivery> deliveries) {
        double deliveryTime = Double.POSITIVE_INFINITY;
        double time, distance;
        Delivery selectedDelivery = null;

        for(Delivery delivery: deliveries) {
            Location restaurantLocation = order.getRestaurant().getLocation();
            distance = Math.sqrt(Math.pow(delivery.getLocation().getX() - restaurantLocation.getX(), 2) + Math.pow(delivery.getLocation().getY() - restaurantLocation.getY(), 2));
            distance += Math.sqrt(Math.pow(customer.getLocation().getX() - restaurantLocation.getX(), 2) + Math.pow(customer.getLocation().getY() - restaurantLocation.getY(), 2));
            time = distance / delivery.getVelocity();
            if (time < deliveryTime) {
                deliveryTime = time;
                selectedDelivery = delivery;
            }
        }
        order.setDelivery(selectedDelivery);
        order.setStatus(Order.orderStatus.OnTheWay);
        order.setEstimatedDeliveryTime(deliveryTime);
        order.setDeliveryDate(new Date());
        setStatusToDeliveredTimer(order, deliveryTime);
    }

    public void findDelivery(final Order order) {
        TimerTask getDeliveries = new TimerTask() {
            public void run() {
                String deliveriesJson = loghmeh_server.external_services.ExternalServices.getFromExtenalAPI("http://138.197.181.131:8080/deliveries");
                ArrayList<Delivery> deliveries = loghmeh_server.deserializer.deliveryDeserializer.deserialize(deliveriesJson);
                if(deliveries.size() != 0){
                    assignDelivery(order, customers.get(0), deliveries);
                    cancel();
                }
            }
        };
        new Timer().scheduleAtFixedRate(getDeliveries, 0, 1000*3);
    }

    public void setStatusToDeliveredTimer(final Order order, double delay) {
        TimerTask setStatusToDelivered = new TimerTask() {
            public void run() {
                order.setStatus(Order.orderStatus.Delivered);
            }
        };
        Timer timer = new Timer();
        timer.schedule(setStatusToDelivered, (long)delay * 1000);
    }

    public String convertMillisToDateFormat(long durationInMillis) {
        long millis = durationInMillis % 1000;
        long second = (durationInMillis / 1000) % 60;
        long minute = (durationInMillis / (1000 * 60)) % 60;
        long hour = (durationInMillis / (1000 * 60 * 60)) % 24;

        return String.format("%02d h %02d min %02d.%d sec", hour, minute, second, millis);
    }

    public Restaurant getRestaurantByName(String restaurantName) {
        for(Restaurant rest: restaurants){
            if(rest.getName().equals(restaurantName))
                return rest;
        }
        return null;
    }

    public Restaurant getRestaurantById(String restaurantId) {
        if(restaurantId == null) {
            return null;
        }
        for(Restaurant rest: this.restaurants){
            if(rest.getId().equals(restaurantId))
                return rest;
        }
        return null;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Customer getCustomer(int i) {
        if(i >= this.customers.size())
            return null;
        return customers.get(i);
    }

    public Customer getCustomerById(int id) {
        for(Customer customer: this.customers){
            if(customer.getCustomerId() == id)
                return customer;
        }
        return null;
    }

    public void setNextFoodPartySchedulerFire(float nextFoodPartySchedulerFire) {
        this.nextFoodPartySchedulerFire = nextFoodPartySchedulerFire;
    }

    public float getNextFoodPartySchedulerFire() {
        return nextFoodPartySchedulerFire;
    }


    public String getRecommendedRestaurants() {
        ArrayList<RecommendationItem> recommendations = new ArrayList<RecommendationItem>();
        for(Restaurant restaurant: restaurants){
            recommendations.add(new RecommendationItem(restaurant, restaurant.getMenuPopulationAverage() / (restaurant.getLocation().euclideanDistance(new Location(.0f, .0f))) ));
        }
        Collections.sort(recommendations, new Comparator<RecommendationItem>(){
            public int compare(RecommendationItem recommendationItem1, RecommendationItem recommendationItem2){
                if(recommendationItem1.getRatingForUser() == recommendationItem2.getRatingForUser())
                    return 0;
                return recommendationItem1.getRatingForUser() > recommendationItem2.getRatingForUser() ? -1 : 1;
            }
        });
        String result = "";
        for(int i = 0; i < 3; i++){
            if(i < recommendations.size()){
                result += (recommendations.get(i).getRestaurant().getName() + "\n");
            }
        }
        if(result.contains("\n")){
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    public String getFoodFromRestaurant(String jsonInput) {
        String restaurantName = foodDeserializer.getRestaurantNameFromJson(jsonInput);
        Restaurant restaurant = getRestaurantByName(restaurantName);
        if(restaurant == null)
            return ("There Is No Restaurant Named " + restaurantName);
        return restaurant.getFood(jsonInput);
    }


    public String getIndexFromRestaurantId(String restaurantId) {
        return idToIndex.get(restaurantId);
    }

    public ArrayList<Restaurant> getChainingRestaurantsByName(String restaurantName) {
        ArrayList<Restaurant> chainingRestaurants = new ArrayList<Restaurant>();

        for(Restaurant rest: restaurants){
            if(rest.getName().equals(restaurantName))
                chainingRestaurants.add(rest);
        }
        return chainingRestaurants;
    }

    public ArrayList<Restaurant> getChainingRestaurantsById(String restaurantId) {
        ArrayList<Restaurant> chainingRestaurants = new ArrayList<Restaurant>();

        for(Restaurant rest: restaurants){
            if(rest.getId().equals(restaurantId))
                chainingRestaurants.add(rest);
        }
        return chainingRestaurants;
    }

}