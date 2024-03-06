package com.coco.application;

import java.util.HashMap;
import java.util.Map;

public class ApplicationStorage {
    private static final Map storage = new HashMap();
    private static ApplicationStorage instance;
    private ApplicationStorage(){}
    static {
        instance = new ApplicationStorage();
    }
    public static ApplicationStorage getInstance(){
        return instance;
    }
    public Map getMap(){
        return storage;
    }
}
