package com.vmware.q3team7.util;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamException;

import org.codehaus.jettison.AbstractXMLStreamWriter;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

import com.vmware.q3team7.models.RLAData;
import com.vmware.q3team7.models.RLAExceptionMessage;

/**
 * @author kdaniel
 *
 */
public class MarshalUtil {
    public static class Property {
        private final String key;
        private final Object value;

        public Property(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    public static String marshal(RLAData data) {
        if (data == null) {
            return null;
        }
        try (StringWriter stringWriter = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(RLAData.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(data, stringWriter);
            return stringWriter.toString();
        } catch (JAXBException | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String marshalContent(Object o){
        Configuration config = new Configuration();
        MappedNamespaceConvention convention = new MappedNamespaceConvention(config);
        StringWriter stringWriter = new StringWriter();
        AbstractXMLStreamWriter xmlWriter = new MappedXMLStreamWriter(convention, stringWriter);

        try {
            JAXBContext context = JAXBContext.newInstance(o.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(o, xmlWriter);
            return stringWriter.toString();
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        } finally {
            try {
                xmlWriter.close();
            } catch (XMLStreamException e) {
                // TODO
            }
        }
    }

    public static String marshalExceptionMessage(RLAExceptionMessage rem) {
        if (rem == null) {
            return null;
        }
        try (StringWriter stringWriter = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(RLAExceptionMessage.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(rem, stringWriter);
            return stringWriter.toString();
        } catch (JAXBException | IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
