/*
 * Copyright (c) 2014, Jure Kobal
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.kobalj.dataformater.params.impl;

import com.github.kobalj.dataformater.params.IParameterDataValues;
import com.github.kobalj.dataformater.params.IParameterResolver;
import com.github.kobalj.dataformater.params.IParameterResolverAnotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingFormatArgumentException;

/**
 *
 * @author Jure Kobal
 */
public class CAddressParameterResolver implements IParameterResolver {

    private final Map<String, Method> resolvers;

    public CAddressParameterResolver() {
        this.resolvers = new HashMap<>();

        Method[] declaredMethods = CAddressParameterResolver.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.isAnnotationPresent(IParameterResolverAnotation.class)) {
                Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
                if (parameterTypes.length == 1) {
                    IParameterResolverAnotation annotation = declaredMethod.getAnnotation(IParameterResolverAnotation.class);
                    resolvers.put(annotation.parameterName(), declaredMethod);
                }
            }
        }
    }

    @IParameterResolverAnotation(parameterName = "name")
    private String resolveName(CAddressDataValues pAddressDataValues) {
        return pAddressDataValues.getName();
    }

    @IParameterResolverAnotation(parameterName = "city")
    private String resolveCity(CAddressDataValues pAddressDataValues) {
        return pAddressDataValues.getCity();
    }

    @IParameterResolverAnotation(parameterName = "country")
    private String resolveCountry(CAddressDataValues pAddressDataValues) {
        return pAddressDataValues.getCountry();
    }

    @IParameterResolverAnotation(parameterName = "houseNumber")
    private String resolveHouseNumber(CAddressDataValues pAddressDataValues) {
        return pAddressDataValues.getHouseNumber();
    }

    @IParameterResolverAnotation(parameterName = "address")
    private String resolveAddress(CAddressDataValues pAddressDataValues) {
        return pAddressDataValues.getAddress();
    }

    @IParameterResolverAnotation(parameterName = "postNo")
    private String resolvePostNumber(CAddressDataValues pAddressDataValues) {
        if (pAddressDataValues.getPostNumber() <= 0) {
            return null;
        }
        return String.valueOf(pAddressDataValues.getPostNumber());
    }

    @Override
    public char[] resolve(String pParameter, char[] pFallBackValue, IParameterDataValues pParameterDataValues) throws MissingFormatArgumentException, InvalidAlgorithmParameterException {
        Method m = resolvers.get(pParameter);
        char[] result;
        if (m != null) {
            String tmp = null;
            try {
                tmp = (String) m.invoke(this, pParameterDataValues);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new InvalidAlgorithmParameterException("Error while processing placeholder \"" + pParameter + "\".", ex);
            }
            check(pFallBackValue, tmp, pParameter);
            if (tmp == null || tmp.isEmpty()) {
                result = pFallBackValue;
            } else {
                result = tmp.toCharArray();
            }

        } else {
            result = pFallBackValue;
        }
        return result;
    }

    private void check(char[] pFallBackValue, String pValue, String pParameter) throws MissingFormatArgumentException {
        if ((pValue == null || pValue.isEmpty()) && (pFallBackValue == null || pFallBackValue.length == 0)) {
            throw new MissingFormatArgumentException("Value for \"" + pParameter + "\" is not defined");
        }
    }

    @Override
    public boolean canResolve(String pParameter) {
        boolean canResolve = false;

        if (resolvers.containsKey(pParameter)) {
            canResolve = true;
        }
        return canResolve;
    }
}
