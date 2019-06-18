package at.o2xfs.xfs.service.idc.execute;

import at.o2xfs.log.Logger;
import at.o2xfs.log.LoggerFactory;
import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.ZList;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.idc.DataSource;
import at.o2xfs.xfs.idc.IdcExecuteCommand;
import at.o2xfs.xfs.idc.IdcMessage;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.idc.IDCService;
import at.o2xfs.xfs.service.idc.IdcFactory;
import at.o2xfs.xfs.service.util.ExceptionUtil;
import at.o2xfs.xfs.v3_00.idc.CardData3;
import at.o2xfs.xfs.win32.XfsWordBitmask;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReadRawDataCommand extends AbstractAsyncXfsCommand<ReadRawDataListener, ReadRawDataCompleteEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(ReadRawDataCommand.class);
    private final IDCService idcService;
    private final XfsWordBitmask<DataSource> xfsWordBitmask;

    public ReadRawDataCommand(IDCService idcService, Set<DataSource> dataSources) {
        if (idcService == null) {
            ExceptionUtil.nullArgument("idcService");
        }

        this.idcService = idcService;
        xfsWordBitmask =  new XfsWordBitmask<>(DataSource.class, dataSources);
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(idcService, IdcExecuteCommand.READ_RAW_DATA, xfsWordBitmask);
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
        listeners.stream().forEach(readRawDataListener -> readRawDataListener.onMediaInserted());
    }

    private void fireInsertCard() {
        listeners.stream().forEach(readRawDataListener -> readRawDataListener.onInsertCard());
    }

    private void fireInvalidMedia() {
        listeners.stream().forEach(readRawDataListener -> readRawDataListener.onInvalidMedia());
    }

    @Override
    protected ReadRawDataCompleteEvent createCompleteEvent(Pointer results) {
        final String method = "createCompleteEvent(Pointer)";
        List<CardData3> data = new ArrayList<>();
        ZList list = new ZList(results);
        for (Pointer p : list) {
            CardData3 cardData3 = IdcFactory.INSTANCE.create(idcService.getXfsVersion(), p, CardData3.class);
            if (LOG.isDebugEnabled()) {
                LOG.debug(method, "cardData3=" + cardData3);
            }
            data.add(cardData3);
        }
        return new ReadRawDataCompleteEvent(data);
    }
}
