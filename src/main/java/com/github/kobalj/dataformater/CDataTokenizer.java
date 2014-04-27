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

import java.io.CharArrayWriter;
import java.nio.CharBuffer;

/**
 *
 * @author Jure Kobal
 */
public class CDataTokenizer implements IDataTokenizer {

    private CharBuffer buff;
    private boolean moreTokens = true;
    private final char delimeter;
    private final boolean toWindowsFormat;
    private char[] currentToken;

    public CDataTokenizer(char pDelimeter, boolean pToWindowsFormat) {
        this.delimeter = pDelimeter;
        this.toWindowsFormat = pToWindowsFormat;
    }
    
    @Override
    public void putInputData(char[] pInputData) {
        this.buff = CharBuffer.wrap(pInputData);
    }
    
    @Override
    public void clearInputData() {
        this.buff.clear();
        this.moreTokens = true;
        this.currentToken = null;
    }

    @Override
    public char[] readCurrentToken() {
        return this.currentToken;
    }

    @Override
    public char[] readNextToken() {
        boolean work = true;
        CharArrayWriter cw = new CharArrayWriter();

        while (work) {
            if (buff.remaining() == 0) {
                moreTokens = false;
                break;
            }
            char b = buff.get();

            if (b == delimeter) {
                moreTokens = buff.remaining() > 0;
                break;
            } else {
                cw.append(b);
            }
        }
        char[] chrs = cw.toCharArray();
        // For the case someone mixes them up
        if (chrs.length == 2 && chrs[0] == '\n' && chrs[1] == '\r') {
            chrs = new char[]{'\r', '\n'};
        }

        // Conwer to windows form
        if (toWindowsFormat && chrs.length == 1 && chrs[0] == '\n') {
            chrs = new char[]{'\r', '\n'};
        }

        this.currentToken = chrs;
        return chrs;
    }

    @Override
    public boolean hasMoreTokens() {
        return this.moreTokens;
    }

    @Override
    public char getDelimeter() {
        return this.delimeter;
    }

    @Override
    public boolean isToWindowsFormat() {
        return toWindowsFormat;
    }
}
