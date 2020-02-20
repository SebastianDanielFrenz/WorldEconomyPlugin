package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 
 * Applying this annotation to a class means that its (possibly public) values
 * can be changes, but will not affect the state of the SQL database.
 *
 */
@Target(value = { ElementType.TYPE })
public @interface DataBaseRepresentation {

}
