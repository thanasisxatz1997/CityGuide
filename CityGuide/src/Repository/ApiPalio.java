package Repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.checkerframework.checker.units.qual.A;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

//import static java.lang.VersionProps.build;

public class ApiPalio {
    public static ArrayList<String> JSONstrList = new ArrayList<>();
    private static HttpURLConnection connection;



    private static String testStr;
    public static MongoDatabase database;
    public static int counter=0;



    public static void main(String[] args) {

        new ConnectToDatabase();
        database=ConnectToDatabase.mainDatabase;
        String collName = "restaurant";
        JSONstrList = new ArrayList<>();

        /*String one="ChIJn4dHtmBgLxMRn1S5aJ4OSl0";
        String two="ChIJJ6jqcWNgLxMRHFDyfNfoqQY";
        String three="ChIJGUQ5k41gLxMRNNyCfhaaQS4";
        String four="ChIJw1j8Jj5gLxMRvE_sGvPw86Y";
        String five="ChIJXwZhXlRgLxMRBk-k6U2rTaA";
        String six="ChIJ2Ti8x_hgLxMRXimOeq7uheo";
        String seven="ChIJt9HnNuBfLxMRe3SzP3zx-fY";
        String eight="ChIJu1FIhkVgLxMRNySoiuKxTkA";
        String nine="ChIJqxDU6UVgLxMRT5I1c90WamM";
        String ten="ChIJ4T6-zEhgLxMRSa1VL3N0DyY";
        String eleven="ChIJs1VwIkVgLxMRNRizMrFlqKg";
        String twelve="ChIJX-ZriEVgLxMREkFFzy20WyY";
        String thirteen="ChIJr76feHtgLxMRSpUk8Ed4h24";
        String fourteen="ChIJhZ4W0ItgLxMRJfr7WSjOBP0";
        String fiftteen="ChIJ5UjGdkdgLxMRzT7K8LNlcXU";
        String sixteen="ChIJ3crFaFFgLxMRVR9WTcNxVDo";
        String seventeen="ChIJJ9b6F5BfLxMRbOBDhWehoNk";
        String eighteen="ChIJKTXtulxgLxMRPnil8t3uqZY";
        String nineteen="ChIJNZ6ApEdgLxMRsPTTOiWwclM";
        String twenty="ChIJuW6Uk0dgLxMRgCZKib";
        String twentyone="ChIJeXToVDFeLxMRsQVxDRFnzBc";
        String twentytwo="ChIJg9zsejheLxMRzrDZVdTQXbo";
        String twentythree="ChIJdXrnxDBeLxMROVCzmIG7Pa0";
        String twentyfour="ChIJQZDticxfLxMRIkL43cKDoO0";
        String twentyfive="ChIJdeEBuDheLxMRXsUE2VS6bEI";
        String twentysix="ChIJR30YNSVeLxMRZDu1LgmGt3c";
        String twentyseven="ChIJv4P85IJeLxMR1U6XUKQYnTA";
        String twentynine="ChIJFf_HX-BeLxMRlhMS57pS1Ig";
        String thirty="ChIJB5XJ-FpeLxMRGi0rpfi7TAs";
        String thirtyone="ChIJF8ltQ0heLxMRINh79zmwYkE";
        String thirtytwo="ChIJ6zcUBzheLxMRyfVOI4sQ-MM";
        String thirtythree="ChIJhb2sqDheLxMRIhhaAFxArn8";
        String thirtyfour="ChIJYaBPezdeLxMRyjKKMWXeR-M";
        String thirtyfive="ChIJU47qVbJfLxMR1nV6mMDfUEY";
        String thirtysix="ChIJJVfXfT5eLxMRpgcEk0NBXxI";
        String thirtyseven="ChIJkV-jmLVfLxMRi8w_d1uqOyA";
        String thirtyeight="ChIJKS6lhcpfLxMRHhjww9jKzjs";
        String thirtynine="ChIJHY86fhZeLxMR5MzxS3jgzs0";
        String fourty="ChIJi7flBTBeLxMRpVQJYThyiOg";
        String fourtyone="ChIJj2t8O8tfLxMR1WrSWPThb9E";
        String fourtytwo="ChIJi3pMcjSkKBMR6FayB-EDzoM";
        String fourtythree="ChIJ1wsu-upgLxMRGM2dvU9ZAVY";
        String fourtyfour="ChIJQZbgwMNgLxMRXMRC4Ho4n2Q";
        String fourtyfive="ChIJsyUUT99gLxMR7KOlIpSLX8E";
        String fourtysix="ChIJ1WYOccZgLxMRf6hF1Sseiw0";
        String fourtyseven="ChIJP_kKiNJgLxMRnYSVXjDnROE";
        String fourtyeight="ChIJnS4fS8JgLxMR5Xr0fuPAEho";
        String fourtynine="ChIJ0Y5YIs5gLxMRNZy5e93fbOc";
        String fifty="ChIJH6T9uTRnLxMRhvFannuJ-vk";
        String fiftyone="ChIJ6d2OjdVmLxMRPZx3AN8wDJw";
        String fiftytwo="ChIJlelx88FgLxMRqhqNO_W93ok";
        String fiftythree="ChIJA4SjRu9gLxMRo9DOiTdtHTE";
        String fiftyfour="ChIJMbkOkOBgLxMRw0VmQUJhRKw";
        String fiftyfive="ChIJ2RfcuTRnLxMRFvlU3A8t_E8";
        String fiftysix="ChIJP_iHKtNgLxMRs0lYU6cJjls";
        String fiftyseven="ChIJCXP7F8hgLxMR3xy7zWoWHRM";
        String fiftyeight="ChIJn_IVxslgLxMRM8zNPcHRg20";
        String fiftynine="ChIJ_ftj1clgLxMRrcNlJOMkf40";
        String sixty="ChIJrc1i4MVgLxMRrpEOvnp0bKI";
        String sixtone="ChIJ__9vZsZgLxMRQupdwpFv21g";
        String sixtytwo="ChIJn4dHtmBgLxMRn1S5aJ4OSl0";
        String sixtythree="ChIJJ6jqcWNgLxMRHFDyfNfoqQY";
        String sixtyfour="ChIJ8yZb6cJfLxMR2g81l8PbjyU";
        String sixtyfive="ChIJw1j8Jj5gLxMRvE_sGvPw86Y";
        String sixtysix="ChIJt9HnNuBfLxMRe3SzP3zx-fY";
        String sixtyseven="ChIJG_v2IuVfLxMRjtb1Vlc2jKQ";
        String sixtyeight="ChIJqWq_azlgLxMRlAsoiavBkqg";
        String sixtynine="ChIJr76feHtgLxMRSpUk8Ed4h24";
        String seventy="ChIJUVzTZ9tfLxMRMLYTl9ga6Q0";
        String seventyone="ChIJ5UjGdkdgLxMRzT7K8LNlcXU";
        String seventytwo="ChIJqxDU6UVgLxMRT5I1c90WamM";
        String seventythree="ChIJu1FIhkVgLxMRNySoiuKxTkA";
        String seventyfour="ChIJKTXtulxgLxMRPnil8t3uqZY";
        String seventyfive="ChIJmw0YuSJgLxMRtC7cMr-himU";
        String seventysix="ChIJK_8VekdgLxMRmlkkjkmzgD4";
        String seventyseven="ChIJs1VwIkVgLxMRNRizMrFlqKg";
        String seventyeight="ChIJFXd_WUdgLxMRx081bg4JYaA";
        String seventynine="ChIJNZ6ApEdgLxMRsPTTOiWwclM";
        String eighty="ChIJdVLIOkRgLxMR3IAFaGSSvvI";
        String eightyone="ChIJuW6Uk0dgLxMRgCZKib0NTWc";
        String eightytwo="ChIJk6d0a6RhLxMRVH_wYTNrTDQ";
        String eightythree="ChIJxbdhOW5hLxMRvDAZXmEWatw";
        String eightyfour="ChIJLdn1iAhhLxMRfPXTWz1q1Xo";
        String eightyfive="ChIJOZm9OaRhLxMRHst-uYUrJ3o";
        String eightysix="ChIJYxlGORthLxMRbSbnT_Qc2As";
        String eightyseven="ChIJSXC_NKphLxMR-W1fh44pYOY";
        String eightyeight="ChIJg-CaQ25hLxMROD-gscpjdx0";
        String eightynine="ChIJ6dM9FqZhLxMRQ4sh1q7TANQ";
        String ninety="ChIJmSXvS71hLxMRkUrcLqugcP8";
        String ninetyone="ChIJ1bCHqgBhLxMRSXGjGBD3iKQ";
        String ninetytwo="ChIJM1rSEZ1jLxMRQV99ddpls3k";
        String ninetythree="ChIJXxdyfHthLxMRL0hynEpj8sY";
        String ninetyfour="ChIJTZlS9QlhLxMRtywWGfWxREo";
        String ninetyfive="ChIJBUTiRmZhLxMRdFLeQHf5Os8";
        String ninetysix="ChIJleQGZnphLxMRJRpDBL_0A04";
        String ninetyseven="ChIJr9uAPxBhLxMRIYpzhzqw9cM";
        String ninetyeight="ChIJT-b9RQphLxMRZw9P2_f2mJA";
        String ninetynine="ChIJVeAWP69hLxMR_ksdOSOwJq0";
        String onehundred="ChIJrTRlMpphLxMRXPetaOGBoFU";
        String onehundredone="ChIJqe61RF9hLxMRTzujfBpcmNE";
        String onehundredtwo="ChIJD2sqo-hjLxMRL9ppB4-JqJ0";
        String onehundredthree="ChIJ9dvD0_5jLxMRZNx1Dg2IuVE";
        String onehundredfour="ChIJqe61RF9hLxMRTzujfBpcmNE";
        String onehundredfive="ChIJX1MR_ahmLxMRBLroOZvxfZw";
        String onehundredsix="ChIJJZswd-ZjLxMRPREunlwXmTU";
        String onehundredseven="ChIJn49gFHNkLxMR46ZGVxPGwWg";
        String onehundredeigh="ChIJjRgegwNkLxMRSiqYvJQ7qVA";
        String onehundrednine="ChIJpedoVXFkLxMR3f7y9NrFpsI";
        String onehundredten="ChIJo-JOr-ZjLxMRLxkt1vvywps";
        String onehundredeleven="ChIJPSQHj4pjLxMRoSNAUh9fShE";
        String onehundredtwelve="ChIJ1xeIWRBkLxMRMKnKqHYs-ew";
        String onehundredthirteen="ChIJ07LwvbJmLxMR1PQAD5LmgEI";
        String onehundredfourteen="ChIJHdyRXPtjLxMRb51SzShQ8eg";
        String onehundredfifteen="ChIJi_i6lqpmLxMRU_NCdiATSZc";
        String onehundredsixteen="ChIJ3X977k5hLxMRk5Z4J_6I-AQ";
        String onehundredseventeen="ChIJAZsRtatmLxMRbOYt2k6Rluo";
        String onehundredeighteen="ChIJPcvE21lhLxMR_DNMk_H2Ob4";
        String onehundrednineteen="ChIJBUTiRmZhLxMRdFLeQHf5Os8";
        String onehundredtwenty="ChIJlf57bE9hLxMR3z871PFgnw4";
        String onehundredtwentyone="ChIJ3eSm5t9jLxMRzq0snQY3xHE";
        String onehundredtwentytwo="ChIJSXC_NKphLxMR-W1fh44pYOY";
        String onehundredtwentythree="ChIJxbdhOW5hLxMRvDAZXmEWatw";
        String onehundredtwentyfour="ChIJ3yLYfQBhLxMRHZtDqn639zc";
        String onehundredtwentyfive="ChIJLdn1iAhhLxMRfPXTWz1q1Xo";
        String onehundredtwentysix="ChIJCdGY5eJgLxMRObAtPWO3aag";
        String onehundredtwentyseven="ChIJYxlGORthLxMRbSbnT_Qc2As";
        String onehundredtwentyeight="ChIJ6xPAgaphLxMRRNFYnP_XYdM";
        String onehundredtwentynine="ChIJ1bCHqgBhLxMRSXGjGBD3iKQ";
        String onehundredthirty="ChIJBRYjOaphLxMRG2PLxQSykp0";
        String onehundredthirtyone="ChIJ2Ti8x_hgLxMRXimOeq7uheo";
        String onehundredthirtytwo="ChIJg-CaQ25hLxMROD-gscpjdx0";
        String onehundredthirtythree="ChIJ7V5-9alhLxMRD9EOOC0h4Ac";
        String onehundredthirtyfour="ChIJE70FSCBhLxMRwM-EuaYUKAI";
        String onehundredthirtyfive="ChIJTZlS9QlhLxMRtywWGfWxREo";
        String onehundredthirtysix="ChIJr9uAPxBhLxMRIYpzhzqw9cM";
        String onehundredthirtyseven="ChIJT-b9RQphLxMRZw9P2_f2mJA";
        String onehundredthirtyeight="ChIJW2zyDQFhLxMRUKyZg8yBGio";
        String onehundredthirtynine="ChIJBUTiRmZhLxMRdFLeQHf5Os8";
        String onehundredfourty="ChIJj1kHNyBhLxMRifRDvNs5Isk";
        String onehundredfourtyone="ChIJ4RRuqHJhLxMRbESXxCeisEU";
        String onehundredfourtytwo="ChIJk6d0a6RhLxMRVH_wYTNrTDQ";
        String onehundredfourtythree="ChIJI7PPc7dhLxMRRt-a0qPpdNs";
        String onehundredfourtyfour="ChIJSXC_NKphLxMR-W1fh44pYOY";
        String onehundredfourtyfive="ChIJXwZhXlRgLxMRBk-k6U2rTaA";
        String onehundredfourtysix="ChIJ3yLYfQBhLxMRHZtDqn639zc";
        String onehundredfourtyseven="ChIJLdn1iAhhLxMRfPXTWz1q1Xo";
        String onehundredfourtyeight="ChIJOZm9OaRhLxMRHst-uYUrJ3o";
        String onehundredfourtynine="hChIJYxlGORthLxMRbSbnT_Qc2As";
        String onehundredfifty="ChIJ6xPAgaphLxMRRNFYnP_XYdM";
        String onehundredfiftyone="ChIJCdGY5eJgLxMRObAtPWO3aag";
        String onehundredfiftytwo="ChIJBRYjOaphLxMRG2PLxQSykp0";
        String onehundredfiftythree="ChIJ2Ti8x_hgLxMRXimOeq7uheo";
        String onehundredfiftyfour="ChIJ1bCHqgBhLxMRSXGjGBD3iKQ";
        String onehundredfiftyfive="ChIJn4dHtmBgLxMRn1S5aJ4OSl0";
        String onehundredfiftysix="ChIJ6dM9FqZhLxMRQ4sh1q7TANQ";
        String onehundredfiftyseven="ChIJmSXvS71hLxMRkUrcLqugcP8";
        String onehundredfiftyeight="ChIJu1FIhkVgLxMRNySoiuKxTkA";
        String onehundredfiftynine="ChIJ-yIamFJgLxMRaM-5RKr9-W4";
        String onehundredsixty="ChIJ4T6-zEhgLxMRSa1VL3N0DyY";
        String onehundredsixtyone="ChIJVeAWP69hLxMR_ksdOSOwJq0";
        //https://maps.googleapis.com/maps/api/place/details/json?fields=name%2Crating%2Cformatted_phone_number&place_id=ChIJVeAWP69hLxMR_ksdOSOwJq0&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes


        ArrayList<String> restaurantList = new ArrayList<>();
        restaurantList.add(one);
        restaurantList.add(two);
        restaurantList.add(three);
        restaurantList.add(four);
        restaurantList.add(five);
        restaurantList.add(six);
        restaurantList.add(seven);
        restaurantList.add(eight);
        restaurantList.add(nine);
        restaurantList.add(ten);
        restaurantList.add(eleven);
        restaurantList.add(twelve);
        restaurantList.add(thirteen);
        restaurantList.add(fourteen);
        restaurantList.add(fiftteen);
        restaurantList.add(sixteen);
        restaurantList.add(seventeen);
        restaurantList.add(eighteen);
        restaurantList.add(nineteen);
        restaurantList.add(twenty);
        restaurantList.add(twentyone);
        restaurantList.add(twentytwo);
        restaurantList.add(twentythree);
        restaurantList.add(twentyfour);
        restaurantList.add(twentyfive);
        restaurantList.add(twentysix);
        restaurantList.add(twentyseven);
        restaurantList.add(twentynine);
        restaurantList.add(thirty);
        restaurantList.add(thirtyone);
        restaurantList.add(thirtytwo);
        restaurantList.add(thirtythree);
        restaurantList.add(thirtyfour);
        restaurantList.add(thirtyfive);
        restaurantList.add(thirtysix);
        restaurantList.add(thirtyseven);
        restaurantList.add(thirtyeight);
        restaurantList.add(thirtynine);
        restaurantList.add(fourty);
        restaurantList.add(fourtyone);
        restaurantList.add(fourtytwo);
        restaurantList.add(fourtythree);
        restaurantList.add(fourtyfour);
        restaurantList.add(fourtyfive);
        restaurantList.add(fourtysix);
        restaurantList.add(fourtyseven);
        restaurantList.add(fourtyeight);
        restaurantList.add(fourtynine);
        restaurantList.add(fifty);
        restaurantList.add(fiftyone);
        restaurantList.add(fiftytwo);
        restaurantList.add(fiftyfour);
        restaurantList.add(fiftyfive);
        restaurantList.add(fiftysix);
        restaurantList.add(fiftyseven);
        restaurantList.add(fiftyeight);
        restaurantList.add(fiftynine);
        restaurantList.add(sixty);
        restaurantList.add(sixtone);
        restaurantList.add(sixtytwo);
        restaurantList.add(sixtythree);
        restaurantList.add(sixtyfour);
        restaurantList.add(sixtyfive);
        restaurantList.add(sixtysix);
        restaurantList.add(sixtyseven);
        restaurantList.add(sixtyeight);
        restaurantList.add(sixtynine);
        restaurantList.add(seventy);
        restaurantList.add(seventyone);
        restaurantList.add(seventytwo);
        restaurantList.add(seventythree);
        restaurantList.add(seventyfour);
        restaurantList.add(seventyfive);
        restaurantList.add(seventysix);
        restaurantList.add(seventyseven);
        restaurantList.add(seventyeight);
        restaurantList.add(seventynine);
        restaurantList.add(seventynine);
        restaurantList.add(eighty);
        restaurantList.add(eightyone);
        restaurantList.add(eightytwo);
        restaurantList.add(eightythree);
        restaurantList.add(eightyfour);
        restaurantList.add(eightyfive);
        restaurantList.add(eightysix);
        restaurantList.add(eightyseven);
        restaurantList.add(eightyeight);
        restaurantList.add(eightynine);
        restaurantList.add(ninety);
        restaurantList.add(ninetyone);
        restaurantList.add(ninetytwo);
        restaurantList.add(ninetythree);
        restaurantList.add(ninetyfour);
        restaurantList.add(ninetyfive);
        restaurantList.add(ninetysix);
        restaurantList.add(ninetyseven);
        restaurantList.add(ninetyeight);
        restaurantList.add(ninetynine);
        restaurantList.add(onehundred);
        restaurantList.add(onehundredone);
        restaurantList.add(onehundredtwo);
        restaurantList.add(onehundredthree);
        restaurantList.add(onehundredfour);
        restaurantList.add(onehundredfive);
        restaurantList.add(onehundredsix);
        restaurantList.add(onehundredseven);
        restaurantList.add(onehundredeigh);
        restaurantList.add(onehundrednine);
        restaurantList.add(onehundredten);
        restaurantList.add(onehundredeleven);
        restaurantList.add(onehundredtwelve);
        restaurantList.add(onehundredthirteen);
        restaurantList.add(onehundredfourteen);
        restaurantList.add(onehundredfifteen);
        restaurantList.add(onehundredsixteen);
        restaurantList.add(onehundredseventeen);
        restaurantList.add(onehundredeighteen);
        restaurantList.add(onehundrednineteen);
        restaurantList.add(onehundredtwenty);
        restaurantList.add(onehundredtwentyone);
        restaurantList.add(onehundredtwentytwo);
        restaurantList.add(onehundredtwentythree);
        restaurantList.add(onehundredtwentyfour);
        restaurantList.add(onehundredtwentyfive);
        restaurantList.add(onehundredtwentysix);
        restaurantList.add(onehundredtwentyseven);
        restaurantList.add(onehundredtwentyeight);
        restaurantList.add(onehundredtwentynine);
        restaurantList.add(onehundredthirty);
        restaurantList.add(onehundredthirtyone);
        restaurantList.add(onehundredthirtytwo);
        restaurantList.add(onehundredthirtythree);
        restaurantList.add(onehundredthirtyfour);
        restaurantList.add(onehundredthirtyfive);
        restaurantList.add(onehundredthirtysix);
        restaurantList.add(onehundredthirtyseven);
        restaurantList.add(onehundredthirtyeight);
        restaurantList.add(onehundredthirtynine);
        restaurantList.add(onehundredfourty);
        restaurantList.add(onehundredfourtyone);
        restaurantList.add(onehundredfourtytwo);
        restaurantList.add(onehundredfourtythree);
        restaurantList.add(onehundredfourtyfour);
        restaurantList.add(onehundredfourtyfive);
        restaurantList.add(onehundredfourtysix);
        restaurantList.add(onehundredfourtyseven);
        restaurantList.add(onehundredfourtyeight);
        restaurantList.add(onehundredfourtynine);
        restaurantList.add(onehundredfifty);
        restaurantList.add(onehundredfiftyone);
        restaurantList.add(onehundredfiftytwo);
        restaurantList.add(onehundredfiftythree);
        restaurantList.add(onehundredfiftyfour);
        restaurantList.add(onehundredfiftyfive);
        restaurantList.add(onehundredfiftysix);
        restaurantList.add(onehundredfiftyseven);
        restaurantList.add(onehundredfiftyeight);
        restaurantList.add(onehundredfiftynine);
        restaurantList.add(onehundredsixty);
        restaurantList.add(onehundredsixtyone);*/

        String resultString="";

        MongoCollection collection = database.getCollection(collName);

        ArrayList<String> placesIdList=GetRestaurantIds(collection,collName);
        for (int j = 0; j <placesIdList.size(); j++) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://maps.googleapis.com/maps/api/place/details/json?fields=address_components%2Cadr_address%2Cbusiness_status%2Cformatted_address%2Cformatted_phone_number%2Cgeometry%2Cicon%2Cicon_background_color%2Cicon_mask_base_uri%2Cinternational_phone_number%2Cname%2Copening_hours%2Cphotos%2Cplace_id%2Cplus_code%2Crating%2Creference%2Creviews%2Ctypes%2Curl%2Cuser_ratings_total%2Cutc_offset%2Cvicinity%2Cwebsite&place_id=" + placesIdList.get(j) + "&key=AIzaSyAvBOia81gDaupwTWI02qZGSgbj1Vgwtes")).build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(s -> testStr = s).join();
            resultString=resultString+testStr;
            System.out.println("Merging Doc: "+j);
            }
            System.out.println("AFTER!");
            System.out.println(resultString);
            System.out.println("DONE, Now parsing!");
            ArrayList<Document> docList;
            docList = ParserAPIDebateable.Parse(resultString);
            collection = database.getCollection(collName);
            //collection.insertMany(docList);*/
            System.out.println("docListSize beforeLoop: " + docList.size());
            for (int z = 0; z < docList.size(); z++) {
                Document tempDoc = docList.get(z);
                String docName = (String) tempDoc.get("place_id");
                System.out.println("Place_id: " + tempDoc.get("place_id"));

                if (!CheckIfDataExists(docName, collection)) {
                    System.out.println("STORE ADDED ADDED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    collection.insertOne(tempDoc);
                    counter++;
                } else {
                    System.out.println("NOPE NOPE NOPE");
                }
            }
            System.out.println("AFTER LOOP");
            System.out.println("COUNTER:::: " + counter + " CollName:::: " + collName);
        }

    public static boolean CheckIfDataExists (String place_id, MongoCollection collection) //this function....
    {
        if (collection.find(new Document("place_id", place_id)).first() == null) {
            return false;
        } else {
            return true;
        }
    }
    public static ArrayList<String> GetRestaurantIds(MongoCollection collection,String collName)
    {
        ArrayList<String> placesIdArrayList=new ArrayList<>();
        int i=0;
        collection=database.getCollection(collName);
        ArrayList<Document> results = new ArrayList<>();
        FindIterable<Document> iterable = collection.find(new Document());
        iterable.into(results);
        for (Document doc :results) {
            System.out.println("The place_id "+i+" is: "+doc.get("place_id").toString());
            placesIdArrayList.add(doc.get("place_id").toString());
            i++;
        }
        return placesIdArrayList;
    }
}
