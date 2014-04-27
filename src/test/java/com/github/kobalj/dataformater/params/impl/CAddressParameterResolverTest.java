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

import java.util.MissingFormatArgumentException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Jure Kobal
 */
public class CAddressParameterResolverTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    public CAddressParameterResolverTest() {
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
     * Test of resolve method, of class CAddressParameterResolver.
     */
    @Test
    public void testResolveName() throws Exception {
        System.out.println("resolveName");
        CAddressParameterResolver instance = new CAddressParameterResolver();
        String pParameter = "name";
        char[] pFallBackValue = null;
        CAddressDataValues pParameterDataValues = new CAddressDataValues();
        pParameterDataValues.setName("Test name");
        char[] expResult = "Test name".toCharArray();
        char[] result = instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
        assertArrayEquals("Result is not as expected - parname", expResult, result);

        pFallBackValue = "test".toCharArray();
        pParameterDataValues.setName(null);
        expResult = "test".toCharArray();
        result = instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
        assertArrayEquals("Result is not as expected - fallback", expResult, result);

        pFallBackValue = null;
        pParameterDataValues.setName(null);
        exception.expect(MissingFormatArgumentException.class);
        instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
    }

    @Test
    public void testResolveAddress() throws Exception {
        System.out.println("resolveAddress");
        String pParameter = "address";
        char[] pFallBackValue = null;
        CAddressDataValues pParameterDataValues = new CAddressDataValues();
        pParameterDataValues.setAddress("Test address");
        CAddressParameterResolver instance = new CAddressParameterResolver();
        char[] expResult = "Test address".toCharArray();
        char[] result = instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
        assertArrayEquals(expResult, result);

        pFallBackValue = "test".toCharArray();
        pParameterDataValues.setAddress(null);
        expResult = "test".toCharArray();
        result = instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
        assertArrayEquals("Result is not as expected - fallback", expResult, result);

        pFallBackValue = null;
        pParameterDataValues.setAddress(null);
        exception.expect(MissingFormatArgumentException.class);
        instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
    }

    @Test
    public void testResolveCity() throws Exception {
        System.out.println("resolveCity");
        String pParameter = "city";
        char[] pFallBackValue = null;
        CAddressDataValues pParameterDataValues = new CAddressDataValues();
        pParameterDataValues.setCity("Test city");
        CAddressParameterResolver instance = new CAddressParameterResolver();
        char[] expResult = "Test city".toCharArray();
        char[] result = instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
        assertArrayEquals(expResult, result);

        pFallBackValue = "test".toCharArray();
        pParameterDataValues.setCity(null);
        expResult = "test".toCharArray();
        result = instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
        assertArrayEquals("Result is not as expected - fallback", expResult, result);

        pFallBackValue = null;
        pParameterDataValues.setCity(null);
        exception.expect(MissingFormatArgumentException.class);
        instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
    }

    @Test
    public void testResolveCountry() throws Exception {
        System.out.println("resolveCountry");
        String pParameter = "country";
        char[] pFallBackValue = null;
        CAddressDataValues pParameterDataValues = new CAddressDataValues();
        pParameterDataValues.setCountry("Test country");
        CAddressParameterResolver instance = new CAddressParameterResolver();
        char[] expResult = "Test country".toCharArray();
        char[] result = instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
        assertArrayEquals(expResult, result);

        pFallBackValue = "test".toCharArray();
        pParameterDataValues.setCountry(null);
        expResult = "test".toCharArray();
        result = instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
        assertArrayEquals("Result is not as expected - fallback", expResult, result);

        pFallBackValue = null;
        pParameterDataValues.setCountry(null);
        exception.expect(MissingFormatArgumentException.class);
        instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
    }

    @Test
    public void testResolveHouseNumber() throws Exception {
        System.out.println("resolveHouseNumber");
        String pParameter = "houseNumber";
        char[] pFallBackValue = null;
        CAddressDataValues pParameterDataValues = new CAddressDataValues();
        pParameterDataValues.setHouseNumber("Test hn");
        CAddressParameterResolver instance = new CAddressParameterResolver();
        char[] expResult = "Test hn".toCharArray();
        char[] result = instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
        assertArrayEquals(expResult, result);

        pFallBackValue = "test".toCharArray();
        pParameterDataValues.setHouseNumber(null);
        expResult = "test".toCharArray();
        result = instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
        assertArrayEquals("Result is not as expected - fallback", expResult, result);

        pFallBackValue = null;
        pParameterDataValues.setHouseNumber(null);
        exception.expect(MissingFormatArgumentException.class);
        instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
    }

    @Test
    public void testResolvePostNumber() throws Exception {
        System.out.println("resolvePostNumber");
        String pParameter = "postNo";
        char[] pFallBackValue = null;
        CAddressDataValues pParameterDataValues = new CAddressDataValues();
        pParameterDataValues.setPostNumber(150);
        CAddressParameterResolver instance = new CAddressParameterResolver();
        char[] expResult = "150".toCharArray();
        char[] result = instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
        assertArrayEquals(expResult, result);

        pFallBackValue = "150".toCharArray();
        pParameterDataValues.setPostNumber(-1);
        expResult = "150".toCharArray();
        result = instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
        assertArrayEquals("Result is not as expected - fallback", expResult, result);

        pFallBackValue = null;
        pParameterDataValues.setPostNumber(-1);
        exception.expect(MissingFormatArgumentException.class);
        instance.resolve(pParameter, pFallBackValue, pParameterDataValues);
    }

    /**
     * Test of canResolve method, of class CAddressParameterResolver.
     */
    @Test
    public void testCanResolve() {
        System.out.println("canResolve");
        CAddressParameterResolver instance = new CAddressParameterResolver();

        boolean result = instance.canResolve("name");
        assertTrue("Result is not as expected - name", result);

        result = instance.canResolve("address");
        assertTrue("Result is not as expected - address", result);

        result = instance.canResolve("city");
        assertTrue("Result is not as expected - city", result);

        result = instance.canResolve("country");
        assertTrue("Result is not as expected - country", result);

        result = instance.canResolve("houseNumber");
        assertTrue("Result is not as expected - houseNumber", result);
        
        result = instance.canResolve("postNo");
        assertTrue("Result is not as expected - postNo", result);
        
        result = instance.canResolve("test");
        assertFalse("Result is not as expected - test", result);
    }

}
