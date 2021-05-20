package com.lookfor.yanaorental.annotations;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public @interface TransactionReadOnly {
}
