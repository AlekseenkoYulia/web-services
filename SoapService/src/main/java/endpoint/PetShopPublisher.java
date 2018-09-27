package endpoint;

import service.soap.PetShopSoapService;

import javax.xml.ws.Endpoint;

public class PetShopPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9090/soap", new PetShopSoapService());
    }
}
