package org.jgloom.utils.enums;

/**
 * Represents a database of, or a function that searches and sets enumerators for a given library.
 * {@link EnumStore}
 */
@FunctionalInterface
public interface EnumStore {
    /**
     * Searches the object's class and finds fields that the child class defines as enumerator names, then sets them
     * to their appropriate values
     * @param objects The objects to store enumerators in
     */
    void store(Object ... objects);
}
