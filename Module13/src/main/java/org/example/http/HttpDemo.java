package org.example.http;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class HttpDemo {
    private static final String CREATE_USER_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String GET_USER_BY_ID_URL = "https://jsonplaceholder.typicode.com/users/{id}";
    private static final String GET_USERS_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String DELETE_USERS_URL = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) {

        User bob = new User(1, "Bob", "Bobberson", "male", 10000);
        User alice = new User(2, "Alice", "Cute", "female", 11000);

        try {
            // CREATE USER
            final User createdUser = HttpUtil.sendPost(URI.create(CREATE_USER_URL), bob);
            System.out.println("createdUser = " + createdUser);

            // GET USER BY ID
            int userId = 1;
            String userByIdUrl = GET_USER_BY_ID_URL.replace("{id}", String.valueOf(userId));
            final User userById = HttpUtil.sendGet(URI.create(userByIdUrl));
            System.out.println("userById = " + userById);

            // GET LIST OF USERS
            final List<User> users = HttpUtil.sendGetWithListOfResults(URI.create(GET_USERS_URL));
            System.out.println("users = " + users);

            // DELETE USER
            HttpUtil.sendDelete(URI.create(DELETE_USERS_URL), createdUser);
            System.out.println("users = " + users);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
