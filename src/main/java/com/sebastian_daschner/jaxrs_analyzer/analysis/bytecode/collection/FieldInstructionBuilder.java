/*
 * Copyright (C) 2015 Sebastian Daschner, sebastian-daschner.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sebastian_daschner.jaxrs_analyzer.analysis.bytecode.collection;

import com.sebastian_daschner.jaxrs_analyzer.model.instructions.GetFieldInstruction;
import com.sebastian_daschner.jaxrs_analyzer.model.instructions.GetPropertyInstruction;
import com.sebastian_daschner.jaxrs_analyzer.model.instructions.GetStaticInstruction;
import com.sebastian_daschner.jaxrs_analyzer.model.types.Type;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.SignatureAttribute;

/**
 * Creates the {@link GetPropertyInstruction} for a given byte code position.
 *
 * @author Sebastian Daschner
 */
class FieldInstructionBuilder {

    private final CodeIterator codeIterator;
    private final ConstPool pool;

    FieldInstructionBuilder(final CodeIterator codeIterator, final ConstPool pool) {
        this.codeIterator = codeIterator;
        this.pool = pool;
    }

    /**
     * Creates a {@link GetFieldInstruction}.
     *
     * @param position The bytecode position
     * @return The instruction
     * @throws BadBytecode If the bytecode could not be analyzed
     */
    GetFieldInstruction buildGetField(final int position) throws BadBytecode {
        final int index = codeIterator.u16bitAt(position + 1);

        final Type containingClass = new Type(pool.getFieldrefClassName(index));
        final String fieldName = pool.getFieldrefName(index);
        final String fieldrefType = pool.getFieldrefType(index);
        final Type fieldType = new Type(SignatureAttribute.toTypeSignature(fieldrefType));

        return new GetFieldInstruction(containingClass, fieldName, fieldType);
    }

    /**
     * Creates a {@link GetStaticInstruction}.
     *
     * @param position The bytecode position
     * @return The instruction
     * @throws BadBytecode If the bytecode could not be analyzed
     */
    GetStaticInstruction buildGetStatic(final int position) throws BadBytecode {
        final int index = codeIterator.u16bitAt(position + 1);

        final Type containingClass = new Type(pool.getFieldrefClassName(index));
        final String fieldName = pool.getFieldrefName(index);
        final String fieldrefType = pool.getFieldrefType(index);
        final Type fieldType = new Type(SignatureAttribute.toTypeSignature(fieldrefType));

        return new GetStaticInstruction(containingClass, fieldName, fieldType);
    }

}
