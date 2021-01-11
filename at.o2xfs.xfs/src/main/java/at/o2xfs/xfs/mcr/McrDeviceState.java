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

package at.o2xfs.xfs.mcr;

import at.o2xfs.xfs.XfsConstant;
import at.o2xfs.xfs.XfsDeviceState;

/**
 * 其中，只要安全门是打开的，模块状态就应该是异常的；并且在对应的最近一次错误码信息中显
 * 示安全门已经打开的错误码。
 */
public enum McrDeviceState implements XfsConstant {

	/**
	 * 在线
	 */
	ONLINE(XfsDeviceState.ONLINE.getValue()),

	/**
	 * 离线
	 */
	OFFLINE(XfsDeviceState.OFFLINE.getValue()),

	/**
	 * 断电
	 */
	POWEROFF(XfsDeviceState.POWEROFF.getValue()),

	/**
	 * 无设备
	 */
	NODEVICE(XfsDeviceState.NODEVICE.getValue()),

	/**
	 * 硬件故障
	 */
	HWERROR(XfsDeviceState.HWERROR.getValue()),

	/**
	 * 设备忙
	 */
	BUSY(XfsDeviceState.BUSY.getValue());


	private final long value;

	McrDeviceState(final long value) {
		this.value = value;
	}

	@Override
	public long getValue() {
		return value;
	}
}