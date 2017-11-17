package ru.stqa.pft.rest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.SkipException;

public class TestBase {



    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId)  {
        JsonPath json = RestAssured.get("http://demo.bugify.com/api/issues"+issueId+".json")
                .then().extract().body().jsonPath();
        if (json.getString("issues.state_name").equals("Resolved")) {
            return false;
        } else {
            return true;
        }
    }
}
