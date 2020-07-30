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

package at.o2xfs.operator.task.xfs.idc;

import at.o2xfs.operator.task.CloseTaskCommand;
import at.o2xfs.operator.task.Task;
import at.o2xfs.xfs.service.cmd.event.CancelEvent;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.ErrorEvent;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.idc.IDCService;
import at.o2xfs.xfs.service.idc.execute.EjectCardCommand;

public class EjectCardTask extends Task implements CommandListener<SuccessEvent> {

	private IDCService idcService = null;

	private EjectCardCommand ejectCardCommand = null;

	public EjectCardTask(final IDCService idcService) {
		this.idcService = idcService;
	}

	@Override
	protected boolean setCloseCommandPerDefault() {
		return false;
	}

	@Override
	protected void doExecute() {
		ejectCardCommand = new EjectCardCommand(idcService, null);
		ejectCardCommand.addCommandListener(this);
		ejectCardCommand.execute();
	}


	private void finishTask() {
		resetEjectCardCommand();
		getCommands().clear();
		getCommands().setNextCommand(new CloseTaskCommand(taskManager));
	}

	private void resetEjectCardCommand() {
		ejectCardCommand.removeCommandListener(this);
		ejectCardCommand = null;
	}

	@Override
	public void onCancel(CancelEvent event) {

	}

	@Override
	public void onError(ErrorEvent event) {

	}

	@Override
	public void onComplete(SuccessEvent event) {

	}
}