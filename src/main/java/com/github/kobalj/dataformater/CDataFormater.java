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

import com.github.kobalj.dataformater.params.IParameterDataValues;
import com.github.kobalj.dataformater.params.IParameterParser;
import com.github.kobalj.dataformater.params.IParameterResolver;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.util.MissingFormatArgumentException;

/**
 *
 * @author Jure Kobal
 */
public class CDataFormater implements IDataFormater {

    private final IDataTokenizer dataTokenizer;
    private final IParameterResolver parameterResolver;
    private final IParameterParser parameterParser;
    private final boolean addNewLine;

    public CDataFormater(
            IDataTokenizer pDataTokenizer,
            IParameterResolver pParameterResolver,
            IParameterParser pParameterParser,
            boolean pAddNewLine) {
        this.dataTokenizer = pDataTokenizer;
        this.parameterResolver = pParameterResolver;
        this.parameterParser = pParameterParser;
        this.addNewLine = pAddNewLine;
    }

    @Override
    public char[] getFormatedData(IParameterDataValues pParameterDataValues, char[] pRawData)
            throws IOException, InvalidAlgorithmParameterException, MissingFormatArgumentException {

        dataTokenizer.putInputData(pRawData);
        CharArrayWriter cw = new CharArrayWriter();
        while (dataTokenizer.hasMoreTokens()) {
            char[] token = dataTokenizer.readNextToken();
            parameterParser.putInputData(token);

            if (parameterParser.getParamName() != null) {
                if (parameterParser.getPrecedingText() != null) {
                    cw.write(parameterParser.getPrecedingText());
                }

                cw.write(parameterResolver.resolve(parameterParser.getParamNameAsString(), parameterParser.getFallBackValue(), pParameterDataValues));

                if (parameterParser.getSucceedingText() != null) {
                    cw.write(parameterParser.getSucceedingText());
                }
            } else {
                cw.write(token);
            }
            // Add delimeter at the end of each token
            if (dataTokenizer.hasMoreTokens()) {
                cw.write(dataTokenizer.getDelimiter());
            }
            parameterParser.clearInputData();
        }
        char[] lastToken = dataTokenizer.readCurrentToken();
        if (!(lastToken != null && lastToken.length == 2 && lastToken[0] == '\r' && lastToken[1] == '\n')) {
            cw.write(dataTokenizer.getDelimiter());

            if (addNewLine && dataTokenizer.isToWindowsFormat()) {
                cw.write(new char[]{'\r', '\n'});
            } else if (addNewLine) {
                cw.write('\n');
            }
        }
        dataTokenizer.clearInputData();
        return cw.toCharArray();
    }
}
