package at.o2xfs.xfs.service.idc.execute;

import at.o2xfs.xfs.service.cmd.event.CompleteEvent;
import at.o2xfs.xfs.v3_00.idc.CardData3;

import java.util.List;

public class ReadRawDataCompleteEvent implements CompleteEvent<List<CardData3>> {

    private List<CardData3> cardData3s;

    public ReadRawDataCompleteEvent(List<CardData3> cardData3s) {
        this.cardData3s = cardData3s;
    }

    @Override
    public List<CardData3> get() {
        return cardData3s;
    }
}
