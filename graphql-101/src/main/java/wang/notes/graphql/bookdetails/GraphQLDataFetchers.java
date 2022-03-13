package wang.notes.graphql.bookdetails;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GraphQLDataFetchers {

    private static List<Map<String, String>> orders = Arrays.asList(
            ImmutableMap.of("id", "order-1",
                    "dishId", "dish-1",
                    "customerId", "customer-1"),
            ImmutableMap.of("id", "order-2",
                    "dishId", "dish-2",
                    "customerId", "customer-2"),
            ImmutableMap.of("id", "order-3",
                    "dishId", "dish-3",
                    "customerId", "customer-3")
    );

    private static List<Map<String, String>> dishes = Arrays.asList(
            ImmutableMap.of("id", "dish-1",
                    "name", "Doufu"),
            ImmutableMap.of("id", "dish-2",
                    "name", "Veg"),
            ImmutableMap.of("id", "dish-3",
                    "name", "Beaf")
    );

    private static List<Map<String, String>> customers = Arrays.asList(
            ImmutableMap.of("id", "customer-1",
                    "name", "Mike"),
            ImmutableMap.of("id", "customer-2",
                    "name", "John"),
            ImmutableMap.of("id", "customer-3",
                    "name", "Jane")
    );

    public DataFetcher getOrderByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String orderId = dataFetchingEnvironment.getArgument("id");
            return orders
                    .stream()
                    .filter(order -> order.get("id").equals(orderId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getDishDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String,String> orders = dataFetchingEnvironment.getSource();
            String dishId = orders.get("dishId");
            return dishes
                    .stream()
                    .filter(dish -> dish.get("id").equals(dishId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getCustomerDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String,String> orders = dataFetchingEnvironment.getSource();
            String customerId = orders.get("customerId");
            return customers
                    .stream()
                    .filter(customer -> customer.get("id").equals(customerId))
                    .findFirst()
                    .orElse(null);
        };
    }
}

