package at.o2xfs.xfs.service.crd.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.crd.CrdExecuteCommand;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.crd.CRDService;
import at.o2xfs.xfs.v3_00.crd.CardUnitInfo3;

public class SetCardUnitInfoCommand extends AbstractAsyncXfsCommand<CommandListener<SuccessEvent>, SuccessEvent> {

    private final CRDService crdService;
    private final CardUnitInfo3 cardUnitInfo3;

    public SetCardUnitInfoCommand(CRDService crdService, CardUnitInfo3 cardUnitInfo3) {
        this.crdService = crdService;
        this.cardUnitInfo3 = cardUnitInfo3;
    }


    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(crdService, CrdExecuteCommand.SET_CARD_UNIT_INFO, cardUnitInfo3);
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }
}
