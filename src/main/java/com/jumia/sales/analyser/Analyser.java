package com.jumia.sales.analyser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map.Entry;

import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.FRIDAY;
import static java.util.Calendar.MONDAY;
import static java.util.Calendar.SATURDAY;
import static java.util.Calendar.SUNDAY;
import static java.util.Calendar.THURSDAY;
import static java.util.Calendar.TUESDAY;
import static java.util.Calendar.WEDNESDAY;
import static java.util.Calendar.getInstance;

public class Analyser {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String SEPARATOR = "=>";

    private final Gson gson;

    private final HashMap<Integer, HashMap<String, Integer>> weekDayCategoriesSales;

    public Analyser() {
        this.gson = new GsonBuilder()
                .setDateFormat(DATE_TIME_FORMAT).create();
        this.weekDayCategoriesSales = new HashMap<>();
        this.weekDayCategoriesSales.put(MONDAY, new HashMap<>());
        this.weekDayCategoriesSales.put(TUESDAY, new HashMap<>());
        this.weekDayCategoriesSales.put(WEDNESDAY, new HashMap<>());
        this.weekDayCategoriesSales.put(THURSDAY, new HashMap<>());
        this.weekDayCategoriesSales.put(FRIDAY, new HashMap<>());
        this.weekDayCategoriesSales.put(SATURDAY, new HashMap<>());
        this.weekDayCategoriesSales.put(SUNDAY, new HashMap<>());
    }

    public void analyse(String fileLocation) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
            String line;
            while ((line = br.readLine()) != null) {
                Sale currentSale = gson.fromJson(line, Sale.class);
                if (currentSale != null) {
                    saveSale(currentSale);
                }
            }
        }
    }

    private void saveSale(Sale currentSale) {
        Calendar calendar = getInstance();
        calendar.setTime(currentSale.getTimestamp());
        HashMap<String, Integer> categoryNbSales = weekDayCategoriesSales.get(calendar.get(DAY_OF_WEEK));
        Integer nbSales = categoryNbSales.get(currentSale.getCategory()) == null ?
                0 : categoryNbSales.get(currentSale.getCategory());
        categoryNbSales.put(currentSale.getCategory(), nbSales + currentSale.getNbSold());
    }

    public String report() {
        String result = "";
        result += getTopCategoryByDay("MONDAY", MONDAY);
        result += getTopCategoryByDay("TUESDAY", TUESDAY);
        result += getTopCategoryByDay("WEDNESDAY", WEDNESDAY);
        result += getTopCategoryByDay("THURSDAY", THURSDAY);
        result += getTopCategoryByDay("FRIDAY", FRIDAY);
        result += getTopCategoryByDay("SATURDAY", SATURDAY);
        result += getTopCategoryByDay("SUNDAY", SUNDAY);
        return result;
    }

    private String getTopCategoryByDay(String dayLabel, int dayKey) {
        HashMap<String, Integer> categoryNbSales = weekDayCategoriesSales.get(dayKey);
        Entry<String, Integer> maxEntry = new AbstractMap.SimpleEntry<>("", 0);
        for (Entry<String, Integer> entry : categoryNbSales.entrySet()) {
            if (entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        return dayLabel + SEPARATOR + maxEntry.getKey() + "\n";
    }

}
