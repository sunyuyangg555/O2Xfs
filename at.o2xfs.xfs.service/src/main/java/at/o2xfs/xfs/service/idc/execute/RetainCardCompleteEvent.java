package at.o2xfs.xfs.service.idc.execute;

import at.o2xfs.xfs.service.cmd.event.CompleteEvent;
import at.o2xfs.xfs.v3_00.idc.RetainCard3;

import java.util.Objects;
import java.util.Optional;

public class RetainCardCompleteEvent implements CompleteEvent<Optional<RetainCard3>> {

    private Optional<RetainCard3> retainCard;

    private RetainCardCompleteEvent(Optional<RetainCard3> retainCard) {
        Objects.requireNonNull(retainCard);
        this.retainCard = retainCard;
    }

    public static RetainCardCompleteEvent build(Optional<RetainCard3> retainCard) {
        return new RetainCardCompleteEvent(retainCard);
    }

    @Override
    public Optional<RetainCard3> get() {
        return retainCard;
    }
}
