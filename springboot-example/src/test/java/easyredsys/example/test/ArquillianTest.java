package easyredsys.example.test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.gradle.archive.importer.embedded.EmbeddedGradleImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;

@RunWith(Arquillian.class)
public class ArquillianTest {

    @ArquillianResource
    URL deploymentUrl;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(EmbeddedGradleImporter.class)
                .forThisProjectDirectory()
                .importBuildOutput()
                .as(WebArchive.class)
                .addPackage("easyredsys");
    }

    @Test
    public void validRestNotificationTest()  {

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(deploymentUrl.toString() + "rest/InotificacionSIS");

        Form form = new Form();
        form.param("Ds_SignatureVersion", "HMAC_SHA256_V1");
        form.param("Ds_MerchantParameters", "eyJEc19EYXRlIjoiMjMlMkYwMyUyRjIwMTYiLCJEc19Ib3VyIjoiMTIlM0E0NCIsIkRzX1NlY3VyZVBheW1lbnQiOiIxIiwiRHNfQ2FyZF9Db3VudHJ5IjoiNzI0IiwiRHNfQW1vdW50IjoiMTAwMCIsIkRzX0N1cnJlbmN5IjoiOTc4IiwiRHNfT3JkZXIiOiJucnBkdHllcHpvdW4iLCJEc19NZXJjaGFudENvZGUiOiI1NTI3NDE2MSIsIkRzX1Rlcm1pbmFsIjoiMDAxIiwiRHNfUmVzcG9uc2UiOiIwMDAwIiwiRHNfTWVyY2hhbnREYXRhIjoiIiwiRHNfVHJhbnNhY3Rpb25UeXBlIjoiMCIsIkRzX0NvbnN1bWVyTGFuZ3VhZ2UiOiIxIiwiRHNfQXV0aG9yaXNhdGlvbkNvZGUiOiIxMzM3NzEifQ==");
        form.param("Ds_Signature", "Un6yOO1z-hVoNvrDPns4j9xVrI4l5nUcmH4zZfgeMAA=");

        Invocation.Builder request = target.request();

        Response response = request.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED),Response.class);

        String entity = response.readEntity(String.class);

        Assert.assertEquals(200, response.getStatus());
    }
}
