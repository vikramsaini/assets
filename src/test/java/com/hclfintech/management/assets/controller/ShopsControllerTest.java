package com.hclfintech.management.assets.controller;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Matchers.any;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import com.hclfintech.management.assests.ParentTest;
import com.hclfintech.management.assets.exception.AssetsManagementException;
import com.hclfintech.management.assets.model.Shop;
import com.hclfintech.management.assets.service.ShopService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = ShopsController.class)
public class ShopsControllerTest extends ParentTest {
	@Autowired
	private MockMvc mockMvc;
//    private UserService userService;

     
    @Mock
    ShopService service;
    @InjectMocks
    ShopsController controller;
     
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        RestAssuredMockMvc.standaloneSetup(controller);
    }
        
    @Test
    public void testCreateNewShopInvalidArg() throws Exception{
    	Shop shop = buildShop(null, "1", "122011");
		Mockito.when(service.saveShop(shop)).thenReturn(null);
		given().contentType(ContentType.JSON).body(shop).when().post("/shops/postShop").then().statusCode(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    public void testCreateNewShopSuccess() throws Exception{
    	Shop shop = buildShop("Om Sweets", "1", "122011");
		Mockito.when(service.saveShop(shop)).thenReturn(null);
		given().contentType(ContentType.JSON).body(shop).when().post("/shops/postShop").then().statusCode(HttpStatus.NO_CONTENT.value());
    }
    @Test
    public void testReplaceShop() throws Exception{
    	Shop oldShop = buildShop("Om Sweets", "1", "122011");
    Shop shop=
    	buildShop("Om Sweets", "2", "123456");
    Mockito.when(service.saveShop(shop)).thenReturn(oldShop);
	given().contentType(ContentType.JSON).body(shop).when().post("/shops/postShop").then().statusCode(HttpStatus.CREATED.value()).and().body(containsString("\"shopAddress\":{\"number\":\"2\""));
	 ;
  

    }

    @Test
	public void testGetClosestShop() throws AssetsManagementException {
    	Mockito.when(service.getClosestShop(any())).thenReturn(buildShop("abc", "abc", "abc"));
		given().when().get("/shops/getClosest?latitude=1234.10&longitude=5434.55")
		.then().statusCode(HttpStatus.OK.value()).and().body(containsString("\"shopAddress\":{\"number\":\"abc\""));
	}
    @SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
    public void testGetClosestShopFailed() throws AssetsManagementException {
		given().when().get("/shops/getClosest?latitude&longitude").then().statusCode(HttpStatus.BAD_REQUEST.value());
    	Mockito.when(service.getClosestShop(any())).thenThrow(AssetsManagementException.class);
		given().when().get("/shops/getClosest?latitude=1234.10&longitude=5434.55")
		.then().statusCode(HttpStatus.BAD_REQUEST.value());
	}
    

}
