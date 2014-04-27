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

/**
 *
 * @author Jure Kobal 
 * Interfave with methods that every parameter parser needs to provide.
 */
public interface IParameterParser {
    
    /**
     * Method to put the input datainto the parser.
     * @param pInputData 
     */
    public void putInputData(char[] pInputData);
    
    /**
     * Clears the input data in the object.
     */
    public void clearInputData();

    /**
     * Returns the name of the parameter
     * @return Name
     */
    public char[] getParamName();

    /**
     * Returns the fallback value for the parameter
     * @return Fallback value or null if there is no fallback value defined.
     */
    public char[] getFallBackValue();
    
    /**
     * Returns the preceding text after the parameter.
     * @return Preceding text following the parameter definiion or null if 
     * there is none.
     */
    public char[] getPrecedingText();

    /**
     * Returns the succeeding text after the parameter.
     * @return Succeeding text following the parameter definiion or null if 
     * there is none.
     */
    public char[] getSucceedingText();

    /**
     * Returns the name of the parameter
     * @return Name represented as String
     */
    public String getParamNameAsString();

    /**
     * Returns the fallback value for the parameter
     * @return Fallback value represented as String or null if there is no 
     * fallback value defined.
     */
    public String getFallBackValueAsString();

    /**
     * Returns the preceding text after the parameter.
     * @return Preceding text following the parameter definiion represented as
     * String or null if there is none.
     */
    public String getPrecedingTextAsString();
    
    /**
     * Returns the succeeding text after the parameter.
     * @return Succeeding text following the parameter definiion represented as
     * String or null if there is none.
     */
    public String getSucceedingTextAsString();
}
