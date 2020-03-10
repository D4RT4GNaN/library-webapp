
package generated.libraryservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="newLoan" type="{http://LibraryService/}loan"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "newLoan"
})
@XmlRootElement(name = "addNewLoan")
public class AddNewLoan {

    @XmlElement(required = true)
    protected Loan newLoan;

    /**
     * Gets the value of the newLoan property.
     * 
     * @return
     *     possible object is
     *     {@link Loan }
     *     
     */
    public Loan getNewLoan() {
        return newLoan;
    }

    /**
     * Sets the value of the newLoan property.
     * 
     * @param value
     *     allowed object is
     *     {@link Loan }
     *     
     */
    public void setNewLoan(Loan value) {
        this.newLoan = value;
    }

}
