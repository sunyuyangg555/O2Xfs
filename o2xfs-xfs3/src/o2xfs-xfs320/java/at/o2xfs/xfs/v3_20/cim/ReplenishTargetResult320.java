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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.ULONG;
import at.o2xfs.win32.USHORT;

public class ReplenishTargetResult320 extends Struct {

	protected final USHORT numberTarget = new USHORT();
	protected final USHORT noteID = new USHORT();
	protected final ULONG numberOfItemsReceived = new ULONG();

	protected ReplenishTargetResult320() {
		add(numberTarget);
		add(noteID);
		add(numberOfItemsReceived);
	}

	public ReplenishTargetResult320(Pointer p) {
		this();
		assignBuffer(p);
	}

	public ReplenishTargetResult320(ReplenishTargetResult320 copy) {
		this();
		allocate();
		set(copy);
	}

	protected void set(ReplenishTargetResult320 copy) {
		numberTarget.set(copy.getNumberTarget());
		noteID.set(copy.getNoteID());
		numberOfItemsReceived.set(copy.getNumberOfItemsReceived());
	}

	public int getNumberTarget() {
		return numberTarget.get();
	}

	public int getNoteID() {
		return noteID.get();
	}

	public long getNumberOfItemsReceived() {
		return numberOfItemsReceived.get();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getNumberTarget()).append(getNoteID()).append(getNumberOfItemsReceived()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ReplenishTargetResult320) {
			ReplenishTargetResult320 replenishTargetResult320 = (ReplenishTargetResult320) obj;
			return new EqualsBuilder().append(getNumberTarget(), replenishTargetResult320.getNumberTarget()).append(getNoteID(), replenishTargetResult320.getNoteID())
					.append(getNumberOfItemsReceived(), replenishTargetResult320.getNumberOfItemsReceived()).isEquals();
		}
		return false;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("numberTarget", getNumberTarget()).append("noteID", getNoteID()).append("numberOfItemsReceived", getNumberOfItemsReceived())
				.toString();
	}
}
