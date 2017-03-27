
package easyredsys.client.client.ws.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


@WebService(name = "SerClsWSEntrada", targetNamespace = "http://webservice.sis.sermepa.es")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SerClsWSEntrada {


    @WebMethod
    @WebResult(name = "trataPeticionReturn", targetNamespace = "http://webservice.sis.sermepa.es")
    @RequestWrapper(localName = "trataPeticion", targetNamespace = "http://webservice.sis.sermepa.es", className = "easyredsys.ws.TrataPeticion")
    @ResponseWrapper(localName = "trataPeticionResponse", targetNamespace = "http://webservice.sis.sermepa.es", className = "easyredsys.ws.TrataPeticionResponse")
    public String trataPeticion(
            @WebParam(name = "datoEntrada", targetNamespace = "http://webservice.sis.sermepa.es")
            String datoEntrada);


    @WebMethod
    @WebResult(name = "consultaDCCReturn", targetNamespace = "http://webservice.sis.sermepa.es")
    @RequestWrapper(localName = "consultaDCC", targetNamespace = "http://webservice.sis.sermepa.es", className = "easyredsys.ws.ConsultaDCC")
    @ResponseWrapper(localName = "consultaDCCResponse", targetNamespace = "http://webservice.sis.sermepa.es", className = "easyredsys.ws.ConsultaDCCResponse")
    public String consultaDCC(
            @WebParam(name = "datoEntrada", targetNamespace = "http://webservice.sis.sermepa.es")
            String datoEntrada);

}
