package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.CLASS)

public @interface AccelerationPotential {

	AccelerationLevel lvl();

}
