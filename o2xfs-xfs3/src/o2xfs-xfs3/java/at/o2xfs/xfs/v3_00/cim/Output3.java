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

package at.o2xfs.xfs.v3_00.cim;

import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.USHORT;
import at.o2xfs.xfs.cim.Position;
import at.o2xfs.xfs.win32.XfsWordBitmask;

public class Output3 extends Struct {

	public static class Builder{

		private final int logicalNumber;
		private final  Set<Position> positions;
		private final int number;

		public Builder(int logicalNumber, Set<Position> positions, int number) {
			this.logicalNumber = logicalNumber;
			this.positions = positions;
			this.number = number;
		}

		public Output3 build() {
			return new Output3(this);
		}
	}

	protected final USHORT logicalNumber = new USHORT();
	protected final XfsWordBitmask<Position> position = new XfsWordBitmask<>(Position.class);
	protected final USHORT number = new USHORT();

	protected Output3() {
		add(logicalNumber);
		add(position);
		add(number);
	}

	public Output3(Pointer p) {
		this();
		assignBuffer(p);
	}

	public Output3(Output3 copy) {
		this();
		allocate();
		set(copy);
	}

	public Output3(Builder builder) {
		this();
		allocate();
		logicalNumber.set(builder.logicalNumber);
		position.set(builder.positions);
		number.set(builder.number);
	}

	protected void set(Output3 copy) {
		logicalNumber.set(copy.getLogicalNumber());
		position.set(copy.getPosition());
		number.set(copy.getNumber());
	}

	public int getLogicalNumber() {
		return logicalNumber.get();
	}

	public Set<Position> getPosition() {
		return position.get();
	}

	public int getNumber() {
		return number.get();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getLogicalNumber()).append(getPosition()).append(getNumber()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Output3) {
			Output3 output3 = (Output3) obj;
			return new EqualsBuilder().append(getLogicalNumber(), output3.getLogicalNumber()).append(getPosition(), output3.getPosition()).append(getNumber(), output3.getNumber())
					.isEquals();
		}
		return false;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("logicalNumber", getLogicalNumber()).append("position", getPosition()).append("number", getNumber()).toString();
	}
}
