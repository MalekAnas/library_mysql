package se.blacklemon.utils;

import java.util.UUID;
public class UniqueIdGenerator {

    public static String createUniqueId()  {
        return UUID.randomUUID().toString();
    }
}
