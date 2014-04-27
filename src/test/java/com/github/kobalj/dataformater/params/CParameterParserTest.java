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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jure Kobal
 */
public class CParameterParserTest {

    private final char[] withoutParam;
    private final char[] withoutFallBack;
    private final char[] withFallBack;
    private final char[] withPrecedingText;
    private final char[] withSucceedingText;
    private final IParameterParser instance = new CParameterParser();

    public CParameterParserTest() {
        this.withoutParam = "Test message".toCharArray();
        this.withoutFallBack = "${paramName}".toCharArray();
        this.withFallBack = "${paramName:fallbackVal}".toCharArray();
        this.withPrecedingText = "Test message ${paramName}".toCharArray();
        this.withSucceedingText = "${paramName:fallbackVal} message.".toCharArray();
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
     * Test of getParamName method, of class CParameterParser.
     */
    @Test
    public void testGetParamName() {
        System.out.println("getParamName");
        instance.putInputData(withoutParam);
        char[] expResult = null;
        char[] result = instance.getParamName();
        assertArrayEquals("Result is not as expected - withoutParam", expResult, result);
        instance.clearInputData();

        instance.putInputData(withoutFallBack);
        expResult = "paramName".toCharArray();
        result = instance.getParamName();
        assertArrayEquals("Result is not as expected - withoutFallBack", expResult, result);
        instance.clearInputData();

        instance.putInputData(withFallBack);
        expResult = "paramName".toCharArray();
        result = instance.getParamName();
        assertArrayEquals("Result is not as expected - withFallBack", expResult, result);
        instance.clearInputData();
        instance.putInputData(withPrecedingText);
        expResult = "paramName".toCharArray();
        result = instance.getParamName();
        assertArrayEquals("Result is not as expected - withPrecedingText", expResult, result);
        instance.clearInputData();
        instance.putInputData(withSucceedingText);
        expResult = "paramName".toCharArray();
        result = instance.getParamName();
        assertArrayEquals("Result is not as expected - withSucceedingText", expResult, result);
        instance.clearInputData();
    }

    /**
     * Test of getFallBackValue method, of class CParameterParser.
     */
    @Test
    public void testGetFallBackValue() {
        System.out.println("geputInputDataBackValue");
        instance.putInputData(withoutFallBack);
        char[] expResult = null;
        char[] result = instance.getFallBackValue();
        assertArrayEquals("Result is not as expected - withoutFallBack", expResult, result);
        instance.clearInputData();

        instance.putInputData(withFallBack);
        expResult = "fallbackVal".toCharArray();
        result = instance.getFallBackValue();
        assertArrayEquals("Result is not as expected - withFallBack", expResult, result);
        instance.clearInputData();

        instance.putInputData(withSucceedingText);
        expResult = "fallbackVal".toCharArray();
        result = instance.getFallBackValue();
        assertArrayEquals("Result is not as expected - withSucceedingText", expResult, result);
        instance.clearInputData();
    }

    /**
     * Test of getPrecedingText method, of class CParameterParser.
     */
    @Test
    public void testGetPrecedingText() {
        System.out.println("getPrecedingText");
        instance.putInputData(withoutParam);
        char[] expResult = "Test message".toCharArray();
        char[] result = instance.getPrecedingText();
        assertArrayEquals("Result is not as expected - withoutParam", expResult, result);
        instance.clearInputData();

        instance.putInputData(withPrecedingText);
        expResult = "Test message ".toCharArray();
        result = instance.getPrecedingText();
        assertArrayEquals("Result is not as expected - withPrecedingText", expResult, result);
        instance.clearInputData();

        instance.putInputData(withSucceedingText);
        expResult = null;
        result = instance.getPrecedingText();
        assertArrayEquals("Result is not as expected - withSucceedingText", expResult, result);
        instance.clearInputData();
    }

    /**
     * Test of getSucceedingLineText method, of class CParameterParser.
     */
    @Test
    public void testGetSucceedingText() {
        System.out.println("getSucceedingLineText");
        instance.putInputData(withoutParam);
        char[] expResult = null;
        char[] result = instance.getSucceedingText();
        assertArrayEquals("Result is not as expected - withoutParam", expResult, result);
        instance.clearInputData();

        instance.putInputData(withPrecedingText);
        expResult = null;
        result = instance.getSucceedingText();
        assertArrayEquals("Result is not as expected - withPrecedingText", expResult, result);
        instance.clearInputData();

        instance.putInputData(withSucceedingText);
        expResult = " message.".toCharArray();
        result = instance.getSucceedingText();
        assertArrayEquals("Result is not as expected - withSucceedingText", expResult, result);
        instance.clearInputData();
    }

    /**
     * Test of getParamNameAsString method, of class CParameterParser.
     */
    @Test
    public void putInputDataetParamNameAsString() {
        System.out.println("getParamNameAsString");
        instance.putInputData(withoutParam);
        String expResult = null;
        String result = instance.getParamNameAsString();
        assertEquals("Result is putInputDatas expected - withoutParam", expResult, result);
        instance.clearInputData();

        instance.putInputData(withoutFallBack);
        expResult = "paramName";
        result = instance.getParamNameAsString();
        assertEquals("ResultputInputDataot as expected - withoutFallBack", expResult, result);
        instance.clearInputData();

        instance.putInputData(withFallBack);
        expResult = "paramName";
        result = instance.getParamNameAsString();
        assertEquals("Result is not as expected - withFallBack", expResult, result);
        instance.clearInputData();

        instance.putInputData(withPrecedingText);
        expResult = "paramName";
        result = instance.getParamNameAsString();
        assertEquals("Result is not as expected - withPrecedingText", expResult, result);
        instance.clearInputData();

        instance.putInputData(withSucceedingText);
        expResult = "paramName";
        result = instance.getParamNameAsString();
        assertEquals("Result is not as expected - withSucceedingText", expResult, result);
        instance.clearInputData();
    }

    /**
     * Test of getFallBackValueAsString method, of class CParameterParser.
     */
    @Test
    public void testGetFallBackValueAsString() {
        System.out.println("getFallBackValueAsString");
        instance.putInputData(withoutFallBack);
        String expResult = null;
        String result = instance.getFallBackValueAsString();
        assertEquals("Result is not as expected - withoutFallBack", expResult, result);
        instance.clearInputData();

        instance.putInputData(withFallBack);
        expResult = "fallbackVal";
        result = instance.getFallBackValueAsString();
        assertEquals("Result is not as expected - withFallBack", expResult, result);
        instance.clearInputData();

        instance.putInputData(withSucceedingText);
        expResult = "fallbackVal";
        result = instance.getFallBackValueAsString();
        assertEquals("Result is not as expected - withSucceedingText", expResult, result);
        instance.clearInputData();
    }

    /**
     * Test of getPrecedingTextAsString method, of class
     * CParputInputDatarParser.
     */
    @Test
    public void testGetPrecedingTextAsString() {
        System.out.println("getPrecedingTextAsString");
        instance.putInputData(withoutParam);
        String expResult = "Test message";
        String result = instance.getPrecedingTextAsString();
        assertEquals("Result is not as expected - withoutParam", expResult, result);
        instance.clearInputData();

        instance.putInputData(withPrecedingText);
        expResult = "Test message ";
        result = instance.getPrecedingTextAsString();
        assertEquals("Result is not as expected - withPrecedingText", expResult, result);
        instance.clearInputData();

        instance.putInputData(withSucceedingText);
        expResult = null;
        result = instance.getPrecedingTextAsString();
        assertEquals("Result is not as expected - withSucceedingText", expResult, result);
        instance.clearInputData();
    }

    /**
     * Test of getSucceedingTextAsString meputInputData of class
     * CParameterParser.
     */
    @Test
    public void testGetSucceedingTextAsString() {
        System.out.println("getSucceedingTextAsString");
        instance.putInputData(withoutParam);
        String expResult = null;
        String result = instance.getSucceedingTextAsString();
        assertEquals("Result is not as expected - withoutParam", expResult, result);
        instance.clearInputData();

        instance.putInputData(withPrecedingText);
        expResult = null;
        result = instance.getSucceedingTextAsString();
        assertEquals("Result is not as expected - withPrecedingText", expResult, result);
        instance.clearInputData();

        instance.putInputData(withSucceedingText);
        expResult = " message.";
        result = instance.getSucceedingTextAsString();
        assertEquals("Result is not as expected - withSucceedingText", expResult, result);
        instance.clearInputData();
    }
}
