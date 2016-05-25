package com.hotweather.app.util;

import android.text.TextUtils;

import com.hotweather.app.db.HotWeatherDB;
import com.hotweather.app.model.City;
import com.hotweather.app.model.County;
import com.hotweather.app.model.Province;

/**
 * Created by taojunyang on 25/5/2016.
 */
public class Utility {

    /**
     * Handle the Provinces Data Returned from Server
     */
    public synchronized static boolean handleProvincesResponse(HotWeatherDB hotWeatherDB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces!=null && allProvinces.length>0) {
                for (String p : allProvinces) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    hotWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Handle the Cities Data Returned from Server
     */
    public static boolean handleCitiesResponse(HotWeatherDB hotWeatherDB, String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities!=null && allCities.length>0) {
                for (String c : allCities) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    hotWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Handle the Counties Data Returned from Server
     */
    public static boolean handleCountiesResponse(HotWeatherDB hotWeatherDB, String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties!=null && allCounties.length>0) {
                for (String c : allCounties) {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    hotWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }

}
