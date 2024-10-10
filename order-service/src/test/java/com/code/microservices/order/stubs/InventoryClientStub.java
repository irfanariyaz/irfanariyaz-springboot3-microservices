package com.code.microservices.order.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStub {

    public  static void  stubInventoryCall(String skuCode,Integer quantity){
        System.out.println("Inventory Client Stub");
        stubFor(get(urlEqualTo("/api/inventory?skuCode="+ skuCode +"&quantity="+quantity))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")));
    }
}
