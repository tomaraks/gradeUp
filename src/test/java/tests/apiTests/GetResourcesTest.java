package tests.apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.api.DataResource;
import utilities.ExcelWriter;
import utilities.Properties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GetResourcesTest {
    private static final String FILE_NAME = "p4e4-a5a7";

    @BeforeClass(description = "Setup necessary data for tests")
    public void setup() {
        RestAssured.baseURI = Properties.BASE_URI;
    }

    @Test(description = "Verifying All Permission By Un Authorized User")
    public void writingJSONResponseToExcel() {
        Response getResponse = DataResource.getJSONResource(FILE_NAME);
        Assert.assertEquals(getResponse.getStatusCode(), HttpStatus.SC_OK, "Could not Get data from file :- " + FILE_NAME);
        JSONArray jsonArray = new JSONArray(getResponse.getBody().asString());
        ArrayList<String> allHeaders = getHeaders((JSONObject) jsonArray.get(0));
        ExcelWriter.writeJSONToExcel(allHeaders, getValues(jsonArray, allHeaders));
    }

    private ArrayList<String> getHeaders(JSONObject jsonObject) {
        ArrayList<String> headers = new ArrayList<String>();
        Iterator<String> iterator = jsonObject.keys();
        String vl = "";
        while (iterator.hasNext()) {
            vl = String.valueOf(iterator.next());
            if (vl.equalsIgnoreCase("location")) {
                JSONObject jsonObject1 = jsonObject.getJSONObject(vl);
                Iterator<String> iterator1 = jsonObject1.keys();
                while (iterator1.hasNext()) {
                    vl = String.valueOf(iterator1.next());
                    headers.add(vl);
                }
            } else {
                headers.add(vl);
            }

        }
        return headers;
    }

    private HashMap<Integer, ArrayList> getValues(JSONArray jsonArray, ArrayList<String> headers) {
        HashMap<Integer, ArrayList> values = new HashMap<Integer, ArrayList>();
        ArrayList vals;
        for (int i = 0; i < jsonArray.length(); i++) {
            vals = new ArrayList();
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            for (String header : headers) {
                try {
                    if (header.equalsIgnoreCase("latitude") || header.equalsIgnoreCase("human_address") || header.equalsIgnoreCase("needs_recoding") || header.equalsIgnoreCase("longitude")) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("location");
                        vals.add(jsonObject1.get(header));
                    } else {
                        vals.add(jsonObject.get(header));
                    }
                } catch (JSONException jse) {
                    vals.add("");
                }
            }
            values.put(i, vals);
        }
        return values;
    }
}
