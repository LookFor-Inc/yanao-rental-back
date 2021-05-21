package com.lookfor.yanaorental.config.bpp;

import com.lookfor.yanaorental.annotations.AccessToRental;
import com.lookfor.yanaorental.models.Rental;
import com.lookfor.yanaorental.services.RentalService;
import com.lookfor.yanaorental.services.auth.impl.AuthenticationFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AccessToRentalBeanPostProcessor implements BeanPostProcessor {
    @Autowired
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private RentalService rentalService;

    private final Map<String, Class<?>> beans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (Arrays.stream(beanClass.getMethods())
                .anyMatch(method -> method.isAnnotationPresent(AccessToRental.class))) {
            beans.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> originalBean = beans.get(beanName);
        if (originalBean != null) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(originalBean);
            enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
                Optional<Method> originalMethod =
                        Arrays.stream(originalBean.getMethods())
                                .filter(method::equals)
                                .findFirst();

                if (originalMethod.isPresent()) {
                    AccessToRental annotation = originalMethod.get().getAnnotation(AccessToRental.class);
                    if (annotation != null) {
                        checkAccess(annotation, method, args);
                        return proxy.invoke(bean, args);
                    }
                }
                try {
                    return method.invoke(bean, args);
                } catch (InvocationTargetException exc) {
                    log.debug(exc + ": " + exc.getTargetException().getMessage());
                    throw exc.getTargetException();
                }
            });
            return enhancer.create();
        }
        return bean;
    }

    private void checkAccess(AccessToRental annotation, Method method, Object[] args) {
        Long userId = authenticationFacade.getUserId();
        String rentalId = getValueByParamName(
                annotation.rentalId(),
                method.getParameters(),
                args);

        if (rentalId != null) {
            Rental rental = rentalService.fetchById(Long.parseLong(rentalId));
            checkRental(rental, userId);
        }
    }

    private String getValueByParamName(String paramName, Parameter[] parameters, Object[] args) {
        Parameter parameter = Arrays.stream(parameters)
                .filter(param -> param.getName().equals(paramName))
                .findFirst()
                .orElse(null);
        if (parameter == null) {
            return null;
        }
        int id = Arrays.stream(parameters)
                .collect(Collectors.toList())
                .indexOf(parameter);
        return String.valueOf(args[id]);
    }

    private void checkRental(Rental rental, long userId) {
        long rentalId = rental.getId();
        rentalService.isUserInRental(rentalId, userId);
    }
}
