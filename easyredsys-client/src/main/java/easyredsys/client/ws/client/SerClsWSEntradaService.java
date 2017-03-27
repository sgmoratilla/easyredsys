
package easyredsys.client.client.ws.client;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


@WebServiceClient(name = "SerClsWSEntradaService", targetNamespace = "http://webservice.sis.sermepa.es", wsdlLocation = "file:/private/tmp/test.wsdl")
public class SerClsWSEntradaService
    extends Service
{

    private final static URL SERCLSWSENTRADASERVICE_WSDL_LOCATION;
    private final static WebServiceException SERCLSWSENTRADASERVICE_EXCEPTION;
    private final static QName SERCLSWSENTRADASERVICE_QNAME = new QName("http://webservice.sis.sermepa.es", "SerClsWSEntradaService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/private/tmp/test.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SERCLSWSENTRADASERVICE_WSDL_LOCATION = url;
        SERCLSWSENTRADASERVICE_EXCEPTION = e;
    }

    public SerClsWSEntradaService() {
        super(__getWsdlLocation(), SERCLSWSENTRADASERVICE_QNAME);
    }

    public SerClsWSEntradaService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SERCLSWSENTRADASERVICE_QNAME, features);
    }

    public SerClsWSEntradaService(URL wsdlLocation) {
        super(wsdlLocation, SERCLSWSENTRADASERVICE_QNAME);
    }

    public SerClsWSEntradaService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERCLSWSENTRADASERVICE_QNAME, features);
    }

    public SerClsWSEntradaService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SerClsWSEntradaService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    @WebEndpoint(name = "SerClsWSEntrada")
    public SerClsWSEntrada getSerClsWSEntrada() {
        return super.getPort(new QName("http://webservice.sis.sermepa.es", "SerClsWSEntrada"), SerClsWSEntrada.class);
    }

    @WebEndpoint(name = "SerClsWSEntrada")
    public SerClsWSEntrada getSerClsWSEntrada(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservice.sis.sermepa.es", "SerClsWSEntrada"), SerClsWSEntrada.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SERCLSWSENTRADASERVICE_EXCEPTION!= null) {
            throw SERCLSWSENTRADASERVICE_EXCEPTION;
        }
        return SERCLSWSENTRADASERVICE_WSDL_LOCATION;
    }

}
