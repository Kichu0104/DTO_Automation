package tests;

import base.BaseTest;
import utils.TokenManager;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class BusinessCapabilityTest extends BaseTest {

        private static int parentId;
        private static int childId;
        private String token;
        private String parentName;
        private String childName;
        private String parentNameUpdated;

        @BeforeClass
        public void setupTest() {
                token = TokenManager.getToken();
                long timestamp = System.currentTimeMillis();
                parentName = "Automation Capability " + timestamp;
                childName = "Child Capability " + timestamp;
                parentNameUpdated = "Automation Capability Updated " + timestamp;
        }

        // 1️⃣ Create Business Capability
        @Test(priority = 1)
        public void createBusinessCapability() {
                String body = """
                                {
                                  "parent_id": null,
                                  "name": "%s",
                                  "owner_id": 154,
                                  "description": "Created via automation",
                                  "lifecycle_stage": "Active"
                                }
                                """.formatted(parentName);

                Response response = given()
                                .header("Authorization", "Bearer " + token)
                                .header("Content-Type", "application/json")
                                .body(body)
                                .when()
                                .post("/api/v1/business-capability");

                if (response.getStatusCode() == 201) {
                        parentId = response.jsonPath().getInt("id");
                        System.out.println("Passed");
                }
                Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201 for parent creation");
                parentId = response.jsonPath().getInt("id");

        }

        // 2️⃣ Create Child Capability
        @Test(priority = 2, dependsOnMethods = "createBusinessCapability")
        public void createChildCapability() {
                String body = """
                                {
                                  "parent_id": %d,
                                  "name": "%s",
                                  "owner_id": 154,
                                  "description": "Child created via automation",
                                  "lifecycle_stage": "Active"
                                }
                                """.formatted(parentId, childName);

                Response response = given()
                                .header("Authorization", "Bearer " + token)
                                .header("Content-Type", "application/json")
                                .body(body)
                                .when()
                                .post("/api/v1/business-capability");

                Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201 for child creation");
                childId = response.jsonPath().getInt("id");
        }

        // 3️⃣ Edit Child
        @Test(priority = 3, dependsOnMethods = "createChildCapability")
        public void editChild() {
                String body = """
                                {
                                  "parent_id": %d,
                                  "name": "%s",
                                  "owner_id": 154,
                                  "description": "Updated child",
                                  "lifecycle_stage": "Active"
                                }
                                """.formatted(parentId, childName + " Updated");

                Response response = given()
                                .header("Authorization", "Bearer " + token)
                                .header("Content-Type", "application/json")
                                .body(body)
                                .when()
                                .put("/api/v1/business-capability/" + childId);
                Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for child update");
        }

        // 4️⃣ Edit Parent Capability
        @Test(priority = 4, dependsOnMethods = "createBusinessCapability")
        public void editBusinessCapability() {
                String body = """
                                {
                                  "parent_id": null,
                                  "name": "%s",
                                  "owner_id": 154,
                                  "description": "Updated parent",
                                  "lifecycle_stage": "Active"
                                }
                                """.formatted(parentNameUpdated);

                Response response = given()
                                .header("Authorization", "Bearer " + token)
                                .header("Content-Type", "application/json")
                                .body(body)
                                .when()
                                .put("/api/v1/business-capability/" + parentId);
                Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 for parent update");
        }

        // 5️⃣ Delete Child
        @Test(priority = 5, dependsOnMethods = "createChildCapability")
        public void deleteChild() {
                Response response = given()
                                .header("Authorization", "Bearer " + token)
                                .when()
                                .delete("/api/v1/business-capability/" + childId);

                Assert.assertEquals(response.getStatusCode(), 204, "Expected status code 204 for child deletion");
        }

        // 6️⃣ Delete Parent
        @Test(priority = 6, dependsOnMethods = { "createBusinessCapability", "deleteChild" })
        public void deleteBusinessCapability() {
                Response response = given()
                                .header("Authorization", "Bearer " + token)
                                .when()
                                .delete("/api/v1/business-capability/" + parentId);

                Assert.assertEquals(response.getStatusCode(), 204, "Expected status code 204 for parent deletion");
        }
}
