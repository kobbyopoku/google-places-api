/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.model.PlacesData;
import app.model.PlacesRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import app.repository.PlaceRepository;

/**
 *
 * @author godwin.duah
 *
 *
 * // GET PLACE URL //
 * https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=5.606422,-0.250081&radius=50&type=food&key=AIzaSyA5ScSpYgUvFenu_b71_Bb5P6uWE5OPdIc
 *
 *
 *
 * // GET PLACE DETAILS URL //
 * https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJAQAAAAmZ3w8RqqVAm5OYnX8&fields=name,address,formatted_phone_number,website&key=AIzaSyB8YFRGSAePfR6X4uvWFrAyjoeH6nSCZPg
 *
 *
 */
@RestController
@RequestMapping(value = "/api/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PlacesController {    
    
    private static final Logger logger = LoggerFactory.getLogger(PlacesController.class);

    @Autowired
    private PlaceRepository placeRepository;

    public PlacesController(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }


    @GetMapping(value = "/places")
    public String GetGooglePlaces(@RequestBody PlacesRequest places) throws Exception {

        String KEY = places.getKey();
        String TYPE = places.getTypes();
        String LOCATION = places.getLocation();
        String RADIUS = places.getRadius();
        String URL;
        String message = null;

        StringBuilder sb = new StringBuilder();
        sb
                .append("https://maps.googleapis.com/maps/api/place/nearbysearch/json?")
                .append("location=").append(LOCATION).append("&")
                .append("radius=").append(RADIUS).append("&")
                .append("types=").append(TYPE).append("&")
                .append("key=").append(KEY);

        RestTemplate restTemplate = new RestTemplate();
        URL = sb.toString();
        logger.info("URL = = = :: " + URL);
        String response = restTemplate.getForObject(URL, String.class);
        JSONObject responseObject = new JSONObject(response);
        JSONArray array = responseObject.getJSONArray("results");

        PlacesData data = new PlacesData();
        for (int n = 1; n < array.length(); n++) {

            JSONObject resultObject = array.getJSONObject(n);
            String place_id = resultObject.optString("place_id");
            String name = resultObject.optString("name");
            String vicinity = resultObject.optString("vicinity");
            JSONObject longlat = new JSONObject(resultObject.getJSONObject("geometry").optString("location"));
            String longitude = longlat.optString("lng");
            String latitude = longlat.optString("lat");

            //Getting Detail Data Using Place id (n)
            JSONObject detail = GetGooglePlacesDetails(place_id, KEY);
            logger.info(detail.toString());
            JSONObject result = new JSONObject(detail.optString("result"));
            String website = result.optString("website");
            String phone = result.optString("formatted_phone_number");
            String address = result.optString("address");

            //Outputs
            logger.info("=======================================================================================");
            logger.info("PLACE ID : " + place_id);
            logger.info("NAME : " + name);
            logger.info("WEBSITE : " + website);
            logger.info("PHONE NUM : " + phone);
            logger.info("ADDRESS : " + address);
            logger.info("VICINITY : " + vicinity);
            logger.info("LONGITUDE : " + longitude);
            logger.info("LATITUDE : " + latitude);

            data.setPlace_id(place_id);
            data.setName(name);
            data.setWebsite(website);
            data.setPhone(phone);
            data.setAddress(address);
            data.setVicinity(vicinity);
            data.setLongitude(longitude);
            data.setLatitude(latitude);

            placeRepository.save(data);

        }

        response = "{\"records\":\"" + array.length() + " records saved\",  " + data.toString() + " }";
        return response;
    }

    public JSONObject GetGooglePlacesDetails(String place_id, String key) {
        StringBuilder sb = new StringBuilder();
        sb
                .append("https://maps.googleapis.com/maps/api/place/details/json?")
                .append("placeid=").append(place_id).append("&")
                .append("type=name,formatted_address,formatted_phone_number,website").append("&")
                .append("key=").append(key);
        RestTemplate restTemplate = new RestTemplate();
        String URL = sb.toString();
        String response = restTemplate.getForObject(URL, String.class);
        JSONObject responseObject = new JSONObject(response);
        return responseObject;
    }

}
