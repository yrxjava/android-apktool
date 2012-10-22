/*
 * Copyright 2012, Google Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *     * Neither the name of Google Inc. nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.jf.dexlib2.immutable.instruction;

import org.jf.dexlib2.Format;
import org.jf.dexlib2.Opcode;
import org.jf.dexlib2.iface.instruction.formats.Instruction35c;
import org.jf.dexlib2.util.Preconditions;

import javax.annotation.Nonnull;

public class ImmutableInstruction35c extends ImmutableInstruction implements Instruction35c {
    public static final Format FORMAT = Format.Format35c;

    public final int registerCount;
    public final int registerC;
    public final int registerD;
    public final int registerE;
    public final int registerF;
    public final int registerG;
    @Nonnull public final String reference;

    public ImmutableInstruction35c(@Nonnull Opcode opcode,
                                   int registerCount,
                                   int registerC,
                                   int registerD,
                                   int registerE,
                                   int registerF,
                                   int registerG,
                                   @Nonnull String reference) {
        super(opcode);
        Preconditions.checkFormat(opcode, Format.Format35c);
        this.registerCount = Preconditions.check35cRegisterCount(registerCount);
        this.registerC = (registerCount>0) ? Preconditions.checkNibbleRegister(registerC) : 0;
        this.registerD = (registerCount>1) ? Preconditions.checkNibbleRegister(registerD) : 0;
        this.registerE = (registerCount>2) ? Preconditions.checkNibbleRegister(registerE) : 0;
        this.registerF = (registerCount>3) ? Preconditions.checkNibbleRegister(registerF) : 0;
        this.registerG = (registerCount>4) ? Preconditions.checkNibbleRegister(registerG) : 0;
        this.reference = Preconditions.checkReference(reference, opcode.referenceType);
    }

    public static ImmutableInstruction35c of(Instruction35c instruction) {
        if (instruction instanceof ImmutableInstruction35c) {
            return (ImmutableInstruction35c)instruction;
        }
        return new ImmutableInstruction35c(
                instruction.getOpcode(),
                instruction.getRegisterCount(),
                instruction.getRegisterC(),
                instruction.getRegisterD(),
                instruction.getRegisterE(),
                instruction.getRegisterF(),
                instruction.getRegisterG(),
                instruction.getReference());
    }

    @Override public int getRegisterCount() { return registerCount; }
    @Override public int getRegisterC() { return registerC; }
    @Override public int getRegisterD() { return registerD; }
    @Override public int getRegisterE() { return registerE; }
    @Override public int getRegisterF() { return registerF; }
    @Override public int getRegisterG() { return registerG; }
    @Nonnull @Override public String getReference() { return reference; }

    @Override public Format getFormat() { return FORMAT; }
}