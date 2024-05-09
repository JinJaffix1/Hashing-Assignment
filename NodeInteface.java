
import java.time.LocalDate;

/**
 * a Java Interface that holds a number of methods you need to implement in the
 * Node class
 *
 * @version Feb 2024
 * @see Node
 */
public interface NodeInteface {

    /**
     * get account id
     *
     * @return account id
     */
    public Integer getId();

    /**
     * get account name
     *
     * @return account name
     */
    public String getName();

    /**
     * get suburb
     *
     * @return suburb
     */
    public String getSuburb();

    /**
     * get date of birth
     *
     * @return date of birth
     */
    public LocalDate getDateOB();

    /**
     * a string representation of a Node object
     *
     * @return a string that contains info about a Node object
     *
     */
    @Override
    public String toString();

    /**
     * overrides a hash code value for the object. This method is supported for
     * the benefit of hash tables such as those provided by HashMap. 
     *
     * @return hash code value for a Node object
     */
    @Override
    public int hashCode();

    @Override
    /**
     * Indicates whether another object is "equal to" this one or not
     *
     * @param obj - the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false
     * otherwise.
     */
    public boolean equals(Object obj);

}
