/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.model.PlaceTypes;
import app.model.PlacesData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import app.repository.PlaceRepository;
import app.repository.TypeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlacesController {

    private static final Logger logger = LoggerFactory.getLogger(PlacesController.class);

    @Autowired
    private PlaceRepository placeRepository;
    private TypeRepository typeRepository;

    public PlacesController(
            PlaceRepository placeRepository,
            TypeRepository typeRepository) {
        this.placeRepository = placeRepository;
        this.typeRepository = typeRepository;
    }

    @PostMapping(value = "ananse/places", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String GetGooglePlaces(
            @RequestParam String area,
            @RequestParam String radius,
            @RequestParam String location,
            @RequestParam String key) throws Exception {

        logger.info("BEGIN");
        String URL;

        List<PlaceTypes> types = getAllTypes();
        String output = "";
        for (PlaceTypes type : types) {
            StringBuilder sb = new StringBuilder();
            sb
                    .append("https://maps.googleapis.com/maps/api/place/nearbysearch/json?")
                    .append("location=").append(location).append("&")
                    .append("radius=").append(radius).append("&")
                    .append("types=").append(type.getType()).append("&")
                    .append("key=").append(key);

            RestTemplate restTemplate = new RestTemplate();
            URL = sb.toString();
            logger.info("TYPE :: " + type.getType());
            String response = restTemplate.getForObject(URL, String.class);
            JSONObject responseObject = new JSONObject(response);
            JSONArray array = responseObject.getJSONArray("results");

            for (int n = 0; n < array.length(); n++) {
                JSONObject resultObject = array.getJSONObject(n);
                String place_id = resultObject.optString("place_id");
                String name = resultObject.optString("name");
                String vicinity = resultObject.optString("vicinity");
                JSONObject longlat = new JSONObject(resultObject.getJSONObject("geometry").optString("location"));
                String longitude = longlat.optString("lng");
                String latitude = longlat.optString("lat");

                //Getting Detail Data Using Place id (n)
                JSONObject detail = GetGooglePlacesDetails(place_id, key);
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

                PlacesData data = new PlacesData();
                data.setArea(area);
                data.setPlace_id(place_id);
                data.setName(name);
                data.setWebsite(website);
                data.setPhone(phone);
                data.setAddress(address);
                data.setVicinity(vicinity);
                data.setLongitude(longitude);
                data.setLatitude(latitude);

                placeRepository.save(data);
                logger.info(name + " data saved!");
            }
            output = "{\"records\":\"" + array.length() * types.size() + " records saved\" }";
        }
        return output;
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

    @GetMapping(value = "ananse/types")
    public List<PlaceTypes> getAllTypes() {
        List<PlaceTypes> list = new ArrayList<>();
        typeRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @GetMapping(value = "ananse/types/count")
    public Long getAllCount() {
        return typeRepository.count();
    }
}
