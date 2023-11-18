package github.meshhi.utils;

public class Warehouse {
    private static String awUrl = "https://aw-demo.ru/";
    private static String firstName = "John";
    private static String lastName = "Doe";
    private static String postalCode = "163000";

    public static String getAwUrl() {
        return Warehouse.awUrl;
    }

    public static String getFirstName() {
        return Warehouse.firstName;
    }

    public static String getLastName() {
        return Warehouse.lastName;
    }

    public static String getPostalCode() {
        return Warehouse.postalCode;
    }
}
