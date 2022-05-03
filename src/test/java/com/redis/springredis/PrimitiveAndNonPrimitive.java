package com.redis.springredis;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PrimitiveAndNonPrimitive {
    static String ten = "10";
    static String twoAndAHalf = "2.5";

    public static void main(String[] args) {
        //ex1 String -> Integer
        int intValue = Integer.parseInt(ten);
        System.out.println(intValue);
        //ex2 String -> Double
        double doubleValue = Double.parseDouble(twoAndAHalf);
        System.out.println(doubleValue);
        //ex3 Integer -> String
        String stringInt = Integer.toString(intValue);
        System.out.println(stringInt);
        //ex4 Double -> String
        String stringDouble = Double.toString(doubleValue);
        System.out.println(stringDouble);
    }
}

class DateFormatter {
    static Date today = Calendar.getInstance().getTime();
    static String sToday = "12/12/2021 21:30:00";


    public static void main(String[] args) throws ParseException {
        System.out.println("Date obj form: " + stringToDate(sToday));
        System.out.println("String form: " + dateToString(today));
    }
    // parse a String object to Date object
    static Date stringToDate(String date) throws ParseException {
        today = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date);
        return today;
    }
    //format a Date object to String object
    static String dateToString(Date today) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sToday = dateFormat.format(today);
        return sToday;
    }
}

class Car {
    int id;
    static int numberOfCars = 0;

    //Constructor
    public Car() {
        //Cars counter
        numberOfCars++;
        //Special id for each car
        id = numberOfCars;
    }

    public static void main(String[] args) {
        Car BMW = new Car();
        Car Mercedes = new Car();
        Car Toyota = new Car();
        System.out.println("Here are " + Car.numberOfCars + " cars!");

        System.out.println(BMW.id);
        System.out.println(Toyota.id);
        System.out.println(Mercedes.id);
    }
}


class Transport {

    private String seats;
    private Object colour;

    public Transport(String seats, String colour) {
        this.seats = seats;
        this.colour = colour;
    }

    @Override
    public int hashCode() {
        return 17 + (seats == null ? 0 : seats.hashCode()) + (colour == null ? 0 : colour.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Transport)){
            return false;
        }
        Transport other = (Transport) obj;
        return ((this.seats == null ? other.seats == null : this.seats.equals(other.seats))
                && (this.colour == null ? other.colour == null : this.colour.equals(other.colour)));
    }

    public static void main(String[] args) {
        //Check if objects are identical or not (ex 1,2,3)
        Transport bicycle = new Transport("1", "white");
        Transport bike = new Transport("1", "white");
        System.out.println(bicycle.equals(bike));
        System.out.println("hashCode comparison: " + bike.hashCode() + " || " + bicycle.hashCode() + "\n");

        Transport tractor = new Transport("1", "green");
        Transport car = new Transport("4", "black");
        System.out.println(tractor.equals(car));
        System.out.println("hashCode comparison: " + car.hashCode() + " || " + tractor.hashCode() + "\n");
        //ex4 Create2 an object to the list and check if we did it
        List list = new ArrayList();
        list.add(tractor);
        System.out.println(list.contains(tractor));
/*
        ex5 Created two different objects as keys and tried to get first object value with third object, that is same
*/
        HashMap<Object, String> set = new HashMap<>();
        set.put(new Transport("250", "white"), "flying");
        set.put(new Transport("54", "green"), "running");
        Transport airplane = new Transport("250", "white");
        System.out.println(set.get(airplane));
    }
}