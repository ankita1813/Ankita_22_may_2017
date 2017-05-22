package com.android.launcher.myassessment;

import java.io.FileInputStream;git init
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ankita on 22/5/17.
 */
public class MyMap {

   private final int CAPACITY = 5;
    private static  Map mapData = new HashMap();


    public synchronized int get(String key){
        Map <String, Integer> myNewlyReadInMap = null;

        if(mapData.size()>= CAPACITY){
            try {
                FileInputStream fileInputStream  = new FileInputStream("/home/ankita/Desktop/myMapData.txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                 myNewlyReadInMap = (HashMap) objectInputStream.readObject();
                 objectInputStream.close();
                return myNewlyReadInMap.get(key);
            }catch ( IOException io){
                io.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }else {
            myNewlyReadInMap = mapData;
            return myNewlyReadInMap.get(key);
        }


        return  -1;
    }

    public synchronized boolean put(String key, int value)  {

        if(mapData.size()>=CAPACITY){
            mapData.put(key,value);
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream("home/ankita/Desktop/myMapData.txt");
                ObjectOutputStream objectOutputStream= new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(mapData);
                //mapData.clear();
                objectOutputStream.close();
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        }else {
            mapData.put(key,value);
            return true;
        }

    }
}


