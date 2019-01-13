package app.utils;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XmlParser {
    <O> O parseXml(Class<O> objectClass, String path) throws JAXBException, FileNotFoundException;

    <O> void exportToXml(O object, Class<O> objectClass, String path) throws JAXBException;
}
