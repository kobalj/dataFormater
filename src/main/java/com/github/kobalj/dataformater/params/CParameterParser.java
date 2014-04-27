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

import java.io.CharArrayWriter;
import java.nio.CharBuffer;

/**
 *
 * @author Jure Kobal 
 * Parameter parser for data where the parameter is defined
 * with the following stucture: ${<paramName>:<fallBack value>} where the
 * paramName is used to get the method to resolv the content and fallBack value
 * is used in case the resolver could not resolve the parameter into value.
 */
public class CParameterParser implements IParameterParser {

    private char[] param = null;
    private char[] fbvalue = null;
    private char[] ptext = null;
    private char[] stext = null;
    private static final int PARAM_NAME = 1;
    private static final int PARAM_FB_VAL = 2;
    private static final int TEXT = 3;
    private int type = TEXT;
    public boolean isPreceding = true;
    
    @Override
    public void putInputData(char[] pData) {
        final CharBuffer buff = CharBuffer.wrap(pData);
        int size = buff.limit();
        CharArrayWriter cw = new CharArrayWriter();

        for (int i = 0; i < size; i++) {
            char b = buff.get();

            if (b == '$') {
                // we could have a parameter
                int b2 = buff.get();
                if (b2 == '{') {
                    // add 1 to i since we read one more character
                    i++;
                    type = PARAM_NAME;
                    ptext = cw.toCharArray();
                    cw.reset();
                    isPreceding = false;
                } else {
                    // Next character isn't the needed one, no parameter
                    buff.position(buff.position() - 1);
                    cw.append(b);
                }
            } else if (b == ':' && type == PARAM_NAME) {
                // fallback value is defined
                param = cw.toCharArray();
                cw.reset();
                type = PARAM_FB_VAL;
            } else if (b == '}' && (type == PARAM_FB_VAL || type == PARAM_NAME)) {
                if (type == PARAM_FB_VAL) {
                    fbvalue = cw.toCharArray();
                } else {
                    param = cw.toCharArray();
                }
                cw.reset();
            } else {
                cw.append(b);
            }
        }
        if (isPreceding) {
            ptext = cw.toCharArray();
        } else {
            stext = cw.toCharArray();
        }
    }
    
    @Override
    public void clearInputData() {
        this.fbvalue = null;
        this.isPreceding = true;
        this.param = null;
        this.ptext = null;
        this.stext = null;
        this.type = TEXT;
    }

    @Override
    public char[] getParamName() {
        return this.param;
    }

    @Override
    public char[] getFallBackValue() {
        return this.fbvalue;
    }

    @Override
    public char[] getPrecedingText() {
        if (this.ptext != null && this.ptext.length > 0) {
            return this.ptext;
        }
        return null;
    }

    @Override
    public char[] getSucceedingText() {
        if (this.stext != null && this.stext.length > 0) {
            return this.stext;
        }
        return null;
    }

    @Override
    public String getParamNameAsString() {
        if (this.param != null && this.param.length != 0) {
            return new String(this.param);
        }
        return null;
    }

    @Override
    public String getFallBackValueAsString() {
        if (this.fbvalue != null && this.fbvalue.length != 0) {
            return new String(this.fbvalue);
        }
        return null;
    }

    @Override
    public String getPrecedingTextAsString() {
        if (this.ptext != null && this.ptext.length != 0) {
            return new String(this.ptext);
        }
        return null;
    }

    @Override
    public String getSucceedingTextAsString() {
        if (this.stext != null && this.stext.length != 0) {
            return new String(this.stext);
        }
        return null;
    }
}
