/*
 * Copyright (c) 2017, Andreas Fagschlunger. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package at.o2xfs.xfs.v3_20.cim;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.win32.XfsPointerArray;

class PhysicalCashUnitCapabilities320Array extends XfsPointerArray<PhysicalCashUnitCapabilities320> {

	public PhysicalCashUnitCapabilities320Array(PhysicalCashUnitCapabilities320[] array) {
		super(array);
	}

	public PhysicalCashUnitCapabilities320Array(Pointer p, int length) {
		super(p, length);
	}

	@Override
	public PhysicalCashUnitCapabilities320 copy(PhysicalCashUnitCapabilities320 copy) {
		return new PhysicalCashUnitCapabilities320(copy);
	}

	@Override
	public PhysicalCashUnitCapabilities320[] get() {
		PhysicalCashUnitCapabilities320[] result = new PhysicalCashUnitCapabilities320[pointers.length];
		for (int i = 0; i < pointers.length; i++) {
			result[i] = copy(new PhysicalCashUnitCapabilities320(pointers[i]));
		}
		return result;
	}
}
