
package org.raml.jaxrs.test.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Generated(value = "org.aml.raml2java", date = "Mon Mar 20 17:15:47 ICT 2017")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Anonimous1 {

    protected List<Label> items = new ArrayList<Label>();
    protected int total;

    public List<Label> getItems() {
        return items;
    }

    public void setItems(List<Label> value) {
        this.items=value;
    }

    public Anonimous1 withItems(List<Label> value) {
        this.items=value;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int value) {
        this.total=value;
    }

    public Anonimous1 withTotal(int value) {
        this.total=value;
        return this;
    }

}
