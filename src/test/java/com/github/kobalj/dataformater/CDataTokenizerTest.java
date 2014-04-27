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
public class CDataTokenizerTest {
    
    private final char[] line = "First line§${param1}§last line§".toCharArray();
    private final char[] firstToken = "First line".toCharArray();
    private final char[] secondToken = "${param1}".toCharArray();
    private final char[] thirdToken = "last line".toCharArray();
    private final char delimeter = '§';
    CDataTokenizer instance;
    
    public CDataTokenizerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new CDataTokenizer(delimeter, true);
        instance.putInputData(line);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCurrentToken method, of class CDataTokenizer.
     */
    @Test
    public void testDataTokenizer() {
        System.out.println("DataTokenizer");
        
        // first token
        assertTrue("Result is not as expected - hasMoreTokens firstToken", instance.hasMoreTokens());
        assertNull("Result is not as expected - getCurrentToken", instance.readCurrentToken());
        char[] token = instance.readNextToken();
        assertArrayEquals("Result is not as expected - firstToken", token, firstToken);
        assertArrayEquals("Result is not as expected - getCurrentToken firstToken", instance.readCurrentToken(), firstToken);
        
        // second token
        assertTrue("Result is not as expected - hasMoreTokens secondToken", instance.hasMoreTokens());
        token = instance.readNextToken();
        assertArrayEquals("Result is not as expected - secondToken", token, secondToken);
        assertArrayEquals("Result is not as expected - getCurrentToken secondToken", instance.readCurrentToken(), secondToken);
        
        // third token
        assertTrue("Result is not as expected - hasMoreTokens thirdToken", instance.hasMoreTokens());
        token = instance.readNextToken();
        assertArrayEquals("Result is not as expected - thirdToken", token, thirdToken);
        assertArrayEquals("Result is not as expected - getCurrentToken thirdToken", instance.readCurrentToken(), thirdToken);
        
        // no more tokens
        assertFalse("Result is not as expected - hasMoreTokens", instance.hasMoreTokens());
    }
}
