package at.o2xfs.xfs.service.idc.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.ZList;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.idc.IdcExecuteCommand;
import at.o2xfs.xfs.idc.IdcMessage;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.idc.IDCService;
import at.o2xfs.xfs.service.util.ExceptionUtil;
import at.o2xfs.xfs.v3_00.idc.CardData3;

import java.util.List;

public class WriteRawDataCommand extends AbstractAsyncXfsCommand<WriteRawDataListener, SuccessEvent> {

    private final IDCService idcService;
    private final List<CardData3> data;


    public WriteRawDataCommand(IDCService idcService, List<CardData3> data) {
        if (idcService == null) {
            ExceptionUtil.nullArgument("idcService");
        }
        this.idcService = idcService;
        this.data = data;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(idcService, IdcExecuteCommand.WRITE_RAW_DATA, new ZList(data));
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }

    @Override
    public void fireIntermediateEvent(WFSResult wfsResult) {
        IdcMessage message = wfsResult.getEventID(IdcMessage.class);
        switch (message) {
            case EXEE_INVALIDMEDIA:
                fireInvalidMedia();
                break;
            case EXEE_MEDIAINSERTED:
                fireMediaInserted();
                break;
            case EXEE_INSERTCARD:
                fireInsertCard();
                break;
            default:
                throw new IllegalArgumentException("IdcMessage: " + message);
        }
    }

    private void fireMediaInserted() {
        listeners.stream().forEach(writeRawDataListener -> writeRawDataListener.onMediaInserted());
    }

    private void fireInsertCard() {
        listeners.stream().forEach(writeRawDataListener -> writeRawDataListener.onInsertCard());
    }

    private void fireInvalidMedia() {
        listeners.stream().forEach(writeRawDataListener -> writeRawDataListener.onInvalidMedia());
    }
}
