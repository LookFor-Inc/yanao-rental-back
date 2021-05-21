package com.lookfor.yanaorental.annotations;

import java.lang.annotation.*;

/**
 * Checks the user's access to rental
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AccessToRental {
    /**
     * Rental id method param
     *
     * @return string method param name
     */
    String rentalId() default "";
}
