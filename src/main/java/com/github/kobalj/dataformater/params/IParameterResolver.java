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

package com.github.kobalj.dataformater.params;

import java.security.InvalidAlgorithmParameterException;
import java.util.MissingFormatArgumentException;

/**
 *
 * @author Jure Kobal
 */
public interface IParameterResolver {

    /**
     * 
     * @param pParameter Name of the parameter to resolve.
     * @param pFallBackValue Value to use if the data object does not contain the 
     * needed value.
     * @param pParameterDataValues Bean that contains the needed content for 
     * the resolver.
     * @return Character array with the content. In case pParameterDataValues 
     * does not contains the needed data pFallBackValue is returned.
     * @throws InvalidAlgorithmParameterException
     * @throws MissingFormatArgumentException In case there is no parameter 
     * value found and no fallback value defined.
     */
    char[] resolve(String pParameter, char[] pFallBackValue, IParameterDataValues pParameterDataValues) 
            throws MissingFormatArgumentException, InvalidAlgorithmParameterException;

    /**
     * Method return true if the resolver can handle the parameter, false if not.
     * @param pParameter Name of the parameter to resolve.
     * @return Status of handling.
     */
    boolean canResolve(String pParameter);
}
