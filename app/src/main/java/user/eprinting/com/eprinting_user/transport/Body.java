package user.eprinting.com.eprinting_user.transport;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

import user.eprinting.com.eprinting_user.model.MediaType;
import user.eprinting.com.eprinting_user.transport.body.BodyBuilder;
import user.eprinting.com.eprinting_user.transport.body.Multipart;
import user.eprinting.com.eprinting_user.transport.body.XML;

/**
 * @author AKBAR <dicky.akbar@dwidasa.com>
 */

public class Body {
    private String xml = "";
    private String boundary = "===" + System.currentTimeMillis() + "===";

    public BodyBuilder form(Map<String, String> params) {
        String body = "";
        for (Map.Entry<String, String> entry : params.entrySet()) {
            body += entry.getKey() + "=" + entry.getValue() + "&";
        }

        return new BodyBuilder()
                .setBody(body.substring(0, body.length() - 1))
                .setMediaType(MediaType.FORM_URLENCODED.toString());
    }

    public BodyBuilder json(String json) {
        return new BodyBuilder()
                .setBody(json)
                .setMediaType(MediaType.JSON.toString());
    }

    public BodyBuilder json(JSONObject json) {
        return new BodyBuilder()
                .setBody(json.toString())
                .setMediaType(MediaType.JSON.toString());
    }

    public BodyBuilder json(JSONArray json) {
        return new BodyBuilder()
                .setBody(json.toString())
                .setMediaType(MediaType.JSON.toString());
    }

    public BodyBuilder multipart(Multipart multipart) {
        return new BodyBuilder()
                .setBody(multipart)
                .setMediaType(MediaType.MULTIPART.toString() + boundary)
                .setBoundary(boundary);
    }

    public BodyBuilder xml(XML body) {
        return new BodyBuilder()
                .setBody(createXml(body))
                .setMediaType(MediaType.TEXT_XML.toString());
    }

    public BodyBuilder xml(String body) {
        return new BodyBuilder()
                .setBody(body)
                .setMediaType(MediaType.TEXT_XML.toString());
    }

    private String createXml(XML xml) {
        for (Map.Entry<String, Object> entry : xml.getParams().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            this.xml += "<" + key + ">";
            if (value instanceof XML) {
                XML innerXml = (XML) value;
                createXml(innerXml);
            } else {
                this.xml += String.valueOf(value);
            }
            this.xml += "</" + key + ">";
        }

        return this.xml;
    }
}