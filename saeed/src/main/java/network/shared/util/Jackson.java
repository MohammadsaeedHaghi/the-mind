package network.shared.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson {
    public static ObjectMapper getNetworkObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return objectMapper;
    }

    // Declare other mappers

    // Test function for jackson
    public static void main(String[] args) {
//        Book book = new Book("bookname", "bookauthor", 1);
//
//        ArrayList<Object> books = new ArrayList<>();
//        books.add(book);
//
//        ObjectMapper objectMapper = Jackson.getNetworkObjectMapper();
//
//        String string = null;
//        try {
//            string = objectMapper.writeValueAsString(books);
//            System.out.println(string);
//
//            ArrayList<Object> fromJacksonBooks = objectMapper.readValue(string, ArrayList.class);
//            System.out.println(fromJacksonBooks);
//
//            Book bookFromList = (Book) fromJacksonBooks.get(0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}