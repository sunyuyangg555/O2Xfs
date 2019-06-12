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

package at.o2xfs.operator.task.xfs.cim;

import at.o2xfs.operator.task.xfs.XfsServiceTask;
import at.o2xfs.xfs.cim.CashInItemType;
import at.o2xfs.xfs.cim.CashInType;
import at.o2xfs.xfs.cim.CashUnitStatus;
import at.o2xfs.xfs.service.cim.CimService;
import at.o2xfs.xfs.v3_00.cim.CashIn3;
import at.o2xfs.xfs.v3_00.cim.PhysicalCashUnit3;

import java.util.HashSet;
import java.util.Set;


abstract class CIMServiceTask extends XfsServiceTask<CimService> {

	public CIMServiceTask() {
		super();
	}

	public CIMServiceTask(CimService service) {
		super(service);
	}

	@Override
	protected Class<CimService> getServiceClass() {
		return CimService.class;
	}

	protected CashIn3 createCashIn3(
			int number,
			String unitID,
			int count,
			boolean appLock,
			CashInType cashInType,
			CashInItemType cashInItemType) {
		PhysicalCashUnit3.Builder builder = new PhysicalCashUnit3.Builder(
				"",
				unitID.toCharArray(),
				CashUnitStatus.OK);
		builder.cashInCount(0);
		builder.count(count);
		builder.maximum(2700);
		builder.hardwareSensors(true);
		PhysicalCashUnit3 physicalCashUnit3 = builder.build();
		PhysicalCashUnit3[] physicalCashUnit3s = new PhysicalCashUnit3[]{physicalCashUnit3};

		Set<CashInItemType> cashInItemTypeSet = new HashSet<>();
		cashInItemTypeSet.add(cashInItemType);
		CashIn3.Builder cashInBuilder = new CashIn3.Builder(
				number,
				cashInType,
				cashInItemTypeSet,
				"1A".toCharArray(),
				"CNY".toCharArray(),
				CashUnitStatus.OK,
				physicalCashUnit3s
		);
		cashInBuilder.cashInCount(0);
		cashInBuilder.count(count);
		cashInBuilder.value(0);
		cashInBuilder.maximum(0);
		cashInBuilder.appLock(appLock);

		return cashInBuilder.build();
	}
}