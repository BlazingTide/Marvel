package me.blazingtide.marvel.utils;

public class ArrayUtils {


    public static <T> boolean contains(T[] arr, T obj) {
        for (T t : arr) {
            if (t == obj) {
                return true;
            }
        }
        return false;
    }


}
