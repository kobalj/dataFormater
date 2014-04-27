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

package com.github.kobalj.dataformater;

import com.github.kobalj.dataformater.params.CParameterParser;
import com.github.kobalj.dataformater.params.impl.CAddressDataValues;
import com.github.kobalj.dataformater.params.impl.CAddressParameterResolver;
import java.io.IOException;
import java.util.MissingFormatArgumentException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Jure Kobal
 */
public class CDataFormaterTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    public CDataFormaterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getFormatedData method, of class CDataFormater.
     */
    @Test
    public void testGetFormatedData() throws Exception {
        System.out.println("getFormatedData");
        CAddressDataValues pParameterDataValues = new CAddressDataValues();
        pParameterDataValues.setAddress("Real 12");
        pParameterDataValues.setName("Name");
        pParameterDataValues.setCity("Test City");
        
        char[] pRawData = "${city}§${address:Test 12}§last line§test ${name}§".toCharArray();
        char[] expResult = "Test City§Real 12§last line§test Name§".toCharArray();
        
        CAddressParameterResolver addressParameterResolver = new CAddressParameterResolver();
        CParameterParser parameterParser = new CParameterParser();
        CDataTokenizer tokenizer = new CDataTokenizer('§', true);
        CDataFormater instance = new CDataFormater(tokenizer, addressParameterResolver, parameterParser, false);
        
        char[] result = instance.getFormatedData(pParameterDataValues, pRawData);
        assertArrayEquals("Result is not as expected", expResult, result);
    }
    
    /**
     * TEst of adding new line (Windows) to the result.
     * @throws Exception 
     */
    @Test
    public void testGetFormatedDataNewLine() throws Exception {
        System.out.println("getFormatedDataNewLineWindows");
        CAddressDataValues pParameterDataValues = new CAddressDataValues();
        pParameterDataValues.setAddress("Real 12");
        pParameterDataValues.setName("Name");
        pParameterDataValues.setCity("Test City");
        
        char[] pRawData = "${city}§${address:Test 12}§last line§test ${name}§".toCharArray();
        char[] expResult = "Test City§Real 12§last line§test Name§\r\n".toCharArray();
        
        CAddressParameterResolver addressParameterResolver = new CAddressParameterResolver();
        CParameterParser parameterParser = new CParameterParser();
        CDataTokenizer tokenizer = new CDataTokenizer('§', true);
        CDataFormater instance = new CDataFormater(tokenizer, addressParameterResolver, parameterParser, true);
        
        char[] result = instance.getFormatedData(pParameterDataValues, pRawData);
        assertArrayEquals("Result is not as expected", expResult, result);
    }
    
    /**
     * TEst of adding new line (Unix) to the result.
     * @throws Exception 
     */
    @Test
    public void testGetFormatedDataNewLineUnix() throws Exception {
        System.out.println("getFormatedDataNewLineUnix");
        CAddressDataValues pParameterDataValues = new CAddressDataValues();
        pParameterDataValues.setAddress("Real 12");
        pParameterDataValues.setName("Name");
        pParameterDataValues.setCity("Test City");
        
        char[] pRawData = "${city}§${address:Test 12}§last line§test ${name}§".toCharArray();
        char[] expResult = "Test City§Real 12§last line§test Name§\n".toCharArray();
        
        CAddressParameterResolver addressParameterResolver = new CAddressParameterResolver();
        CParameterParser parameterParser = new CParameterParser();
        CDataTokenizer tokenizer = new CDataTokenizer('§', false);
        CDataFormater instance = new CDataFormater(tokenizer, addressParameterResolver, parameterParser, true);
        
        char[] result = instance.getFormatedData(pParameterDataValues, pRawData);
        assertArrayEquals("Result is not as expected", expResult, result);
    }
    
    /**
     * Parameter with no value in object and no fallback value. 
     * Throws MissingFormatArgumentException.
     * @throws Exception 
     */
    @Test
    public void testGetFormatedDataMissingFalback() throws Exception {
        System.out.println("getFormatedDataMissingParam");
        CAddressDataValues pParameterDataValues = new CAddressDataValues();
        pParameterDataValues.setAddress("Real 12");
        pParameterDataValues.setCity("Test City");
        
        char[] pRawData = "${city}§${address:Test 12}§last line§test ${name}§".toCharArray();
        char[] expResult = "Test City§Real 12§last line§test §\r\n".toCharArray();        
        CAddressParameterResolver addressParameterResolver = new CAddressParameterResolver();
        CParameterParser parameterParser = new CParameterParser();
        CDataTokenizer tokenizer = new CDataTokenizer('§', true);
        CDataFormater instance = new CDataFormater(tokenizer, addressParameterResolver, parameterParser, true);
        
        exception.expect(MissingFormatArgumentException.class);
        instance.getFormatedData(pParameterDataValues, pRawData);
    }
    
    /**
     * No value defined. Fallback used.
     */
    @Test
    public void testGetFormatedDataFallBack() throws Exception {
        System.out.println("getFormatedDataFallBack");
        CAddressDataValues pParameterDataValues = new CAddressDataValues();
        pParameterDataValues.setName("Name");
        pParameterDataValues.setCity("Test City");
        
        char[] pRawData = "${city}§${address:Test 12}§last line§test ${name}§".toCharArray();
        char[] expResult = "Test City§Test 12§last line§test Name§".toCharArray();
        
        CAddressParameterResolver addressParameterResolver = new CAddressParameterResolver();
        CParameterParser parameterParser = new CParameterParser();
        CDataTokenizer tokenizer = new CDataTokenizer('§', true);
        CDataFormater instance = new CDataFormater(tokenizer, addressParameterResolver, parameterParser, false);
        
        char[] result = instance.getFormatedData(pParameterDataValues, pRawData);
        assertArrayEquals("Result is not as expected", expResult, result);
    }
}
